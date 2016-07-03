package com.pujjr.business.controller;

import io.jsonwebtoken.Claims;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pujjr.business.domain.LeasingApp;
import com.pujjr.business.domain.LeasingApprove;
import com.pujjr.business.domain.LeasingCheck;
import com.pujjr.business.domain.SysAccount;
import com.pujjr.business.service.LeasingAppService;
import com.pujjr.business.service.LeasingApproveService;
import com.pujjr.business.service.LeasingCheckService;
import com.pujjr.business.service.SequenceService;
import com.pujjr.business.service.SysAccountService;
import com.pujjr.business.vo.AppData;
import com.pujjr.business.vo.AppManageDetail;
import com.pujjr.business.vo.PageVo;
import com.pujjr.common.CellStyleCache;
import com.pujjr.common.Utils;

@Controller
@RequestMapping("/api/leasingapps")
public class LeasingAppController extends BaseController 
{
	@Autowired
	private LeasingAppService leasingAppServ;
	@Autowired
	private SysAccountService sysAccountService;
	@Autowired
	private LeasingCheckService leasingCheckServ;
	@Autowired
	private LeasingApproveService leasingApproveServ;
	@Autowired
	private SequenceService seqService;
	
	private CellStyleCache cellStyleCache;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public LeasingApp get(@PathVariable String id)
	{
		return leasingAppServ.get(id);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody LeasingApp record)
	{
		//如果为待审核则更新申请发起时间
		if(record.getSqdzt().equals("待审核"))
		{
			record.setSqfqsj(new Timestamp(new Date().getTime()));
		}
		if(record.getSqdzt().equals("已锁定"))
		{
			record.setReserver7(Utils.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		}
		leasingAppServ.update(record);
	}
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void add(@RequestBody LeasingApp record,HttpServletRequest request )
	{
		/*获取用户信息*/
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount sysAccount=sysAccountService.get(userid);
		record.setReserver5(leasingAppServ.queryCustType(record));
		record.setSqdzt("未确认");
		record.setJxsid(sysAccount.getSysBranch().getId());
		record.setSqfqr(sysAccount.getId());
		record.setSqfqsj(new Timestamp(new Date().getTime()));
		leasingAppServ.add(record);
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable String id)
	{
		leasingAppServ.delete(id);
	}
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public PageVo getList(String sqdzt,
						  String id,
				     	  String name,
						  String sqfqrq,
						  String curPage ,
						  String pageSize) throws UnsupportedEncodingException, ParseException
	{
		sqdzt=Utils.convertStr2Utf8(sqdzt);
		name=Utils.convertStr2Utf8(name);
		if(sqfqrq!=null && sqfqrq!="")
		{
			sqfqrq = sqfqrq.replace("Z", " UTC");//注意是空格+UTC
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
			sqfqrq=Utils.getFormatDate(format.parse(sqfqrq), "yyyy-MM-dd");
		}
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<LeasingApp> list=leasingAppServ.getList(id,name,sqfqrq,sqdzt);
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	@RequestMapping(value="/jxs",method=RequestMethod.GET)
	@ResponseBody
	public PageVo getJxsAppList(String sqdzt,
								String id,
								String name,
								String sqfqrq,
								String curPage ,
								String pageSize,
								HttpServletRequest request) throws UnsupportedEncodingException, ParseException
	{
		sqdzt=Utils.convertStr2Utf8(sqdzt);
		name=Utils.convertStr2Utf8(name);
		if(sqfqrq!=null && sqfqrq!="")
		{
			sqfqrq = sqfqrq.replace("Z", " UTC");//注意是空格+UTC
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
			sqfqrq=Utils.getFormatDate(format.parse(sqfqrq), "yyyy-MM-dd");
		}
		
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount sysAccount=sysAccountService.get(userid);
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<LeasingApp> list=leasingAppServ.getJxsList(id,name,sqfqrq,sqdzt,sysAccount.getId());
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	@RequestMapping(value="/check",method=RequestMethod.GET)
	@ResponseBody
	public PageVo getCheckAppList(String sqdzt,
								  String id,
								  String name,
								  String sqfqrq,
								  String curPage ,
								  String pageSize,
								  HttpServletRequest request) throws UnsupportedEncodingException, ParseException
	{
		sqdzt=Utils.convertStr2Utf8(sqdzt);
		name=Utils.convertStr2Utf8(name);
		if(sqfqrq!=null && sqfqrq!="")
		{
			sqfqrq = sqfqrq.replace("Z", " UTC");//注意是空格+UTC
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
			sqfqrq=Utils.getFormatDate(format.parse(sqfqrq), "yyyy-MM-dd");
		}
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount sysAccount=sysAccountService.get(userid);
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<LeasingApp> list=leasingAppServ.getCheckList(id,name,sqfqrq,sqdzt,sysAccount.getId());
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	
	@RequestMapping(value="/getId",method=RequestMethod.GET)
	@ResponseBody
	public HashMap<String,String> getAppId(HttpServletRequest request) throws Exception
	{
		//throw new Exception("申请单编号服务器故障");
		
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount sysAccount=sysAccountService.get(userid);
		String seq=String.format("%04d", seqService.getNextVal("appid"));
		String appid=sysAccount.getBranchid()+"-"+Utils.getFormatDate(new Date(), "yyyyMMdd")+"-"+seq+"-";
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("id", appid);
		return map;
	}
	
	@RequestMapping(value="/updateAllData",method=RequestMethod.POST)
	@ResponseBody
	public void updateAllData(@RequestBody AppData data,HttpServletRequest request )
	{
		leasingAppServ.updateAllAppData(data);
	}
	@RequestMapping(value="/managerAppDetail",method=RequestMethod.GET)
	@ResponseBody
	public PageVo queryManagerAppDetail(String sqdzt,
										String id,
										String name,
										String sqfqrq,
										String curPage,
										String pageSize) throws UnsupportedEncodingException, ParseException
	{
		sqdzt=Utils.convertStr2Utf8(sqdzt);
		name=Utils.convertStr2Utf8(name);
		if(sqfqrq!=null && sqfqrq!="")
		{
			sqfqrq = sqfqrq.replace("Z", " UTC");//注意是空格+UTC
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
			sqfqrq=Utils.getFormatDate(format.parse(sqfqrq), "yyyy-MM-dd");
		}
		PageHelper.startPage(Integer.parseInt(curPage), Integer.parseInt(pageSize),true);
		List<AppManageDetail> list=leasingAppServ.selectManagerAppDetail(id,name,sqfqrq,sqdzt);
		PageVo page=new PageVo();
		page.setTotalItem(((Page)list).getTotal());
		page.setData(list);
		return page;
	}
	
	@RequestMapping("/downloadAppDetail")
	@ResponseBody
	public ResponseEntity<byte[]> downloadAppDetail(String sqdzt,
			  										String id,
			  										String name,
			  										String sqfqrq, HttpSession session) throws IOException, ParseException
	{
		sqdzt=Utils.convertStr2Utf8(sqdzt);
		name=Utils.convertStr2Utf8(name);
		if(sqfqrq!=null && sqfqrq!="")
		{
			sqfqrq = sqfqrq.replace("Z", " UTC");//注意是空格+UTC
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
			sqfqrq=Utils.getFormatDate(format.parse(sqfqrq), "yyyy-MM-dd");
		}
		List<LeasingApp> list=leasingAppServ.getList(id,name,sqfqrq,sqdzt);
		
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		cellStyleCache=new CellStyleCache(wb);
		Font font=wb.createFont();
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		
		sheet.setColumnWidth(0, 5000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 5500);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 15000);
		sheet.setColumnWidth(7, 8000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 15000);
		sheet.setColumnWidth(10, 2500);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		
		Row row=sheet.createRow(0);
		writeCellValue(wb,sheet,row,0,"申请时间",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,1,"姓名",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,2,"客户等级",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,3,"身份证号码",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,4,"手机号码",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,5,"QQ号码",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,6,"现住址",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,7,"公司名称",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,8,"公司电话",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,9,"公司地址",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,10,"首付比例",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,11,"融资金额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,12,"购置税",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		
		Font fontNormal=wb.createFont();		
		fontNormal.setFontHeightInPoints((short)10);
		
		for(int i=0;i<list.size();i++)
		{
			row=sheet.createRow(i+1);
			LeasingApp item=list.get(i);
			writeCellValue(wb,sheet,row,0,item.getSqfqsj().toString(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,1,item.getName(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,2,item.getReserver5(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,3,item.getIdno(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,4,item.getMobile(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,5,item.getQq(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,6,item.getXxxdz(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,7,item.getCzr1dwmc(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,8,item.getCzr1dwdh(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,9,item.getCzr1dwdz(),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			DecimalFormat df=new DecimalFormat("#,###,###,###,##0.00");
			double sfbl=Double.valueOf((item.getSfbl()==null)?"0.00":String.valueOf(item.getSfbl()));
			writeCellAmount(wb,sheet,row,10,df.format(sfbl),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			double rzje=Double.valueOf((item.getRzje()==null)?"0.00":String.valueOf(item.getRzje()));
			writeCellValue(wb,sheet,row,11,df.format(rzje),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			double gzs=Double.valueOf((item.getGzs()==null)?"0.00":String.valueOf(item.getGzs()));
			writeCellValue(wb,sheet,row,12,df.format(gzs),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);	
		}
		
		String fileName=Utils.get16UUID();
		String filePath=session.getServletContext().getRealPath("/")+"download"+File.separator+fileName+".xls";
		FileOutputStream fileOut = new FileOutputStream(filePath);   
		wb.write(fileOut);
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", new String("申请单明细".getBytes("GB2312"), "ISO_8859_1")+".xls");  
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)),  
	                                      headers, HttpStatus.OK);  
		
	}
	
	private void  writeCellValue(Workbook wb,Sheet sheet,Row row,int colNum,String value,Font font,short color,short align)
	{
		Cell cell=row.createCell(colNum);
		cell.setCellStyle(cellStyleCache.getCellStyle(align, color, false,false));
		cell.setCellValue(value);
	}
	private void  writeCellAmount(Workbook wb,Sheet sheet,Row row,int colNum,String value,Font font,short color,short align)
	{
		Cell cell=row.createCell(colNum);
		cell.setCellValue(Double.valueOf(value.replaceAll(",","")));
		cell.setCellStyle(cellStyleCache.getCellStyle(align, color, false,true));		
	}
	@RequestMapping("/exportInCase")
	@ResponseBody
	public ResponseEntity<byte[]> exportInCase(String sqdzt,
							 				   String id,
							 				   String name,
							 				   String sqfqrq, 
							 				   HttpSession session) throws ParseException, IOException
	{
		sqdzt=Utils.convertStr2Utf8(sqdzt);
		name=Utils.convertStr2Utf8(name);
		if(sqfqrq!=null && sqfqrq!="")
		{
			sqfqrq = sqfqrq.replace("Z", " UTC");//注意是空格+UTC
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");//注意格式化的表达式
			sqfqrq=Utils.getFormatDate(format.parse(sqfqrq), "yyyy-MM-dd");
		}
		List<Map> list=leasingAppServ.getInCaseList(id, name, sqfqrq, sqdzt);
		
		
		
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		cellStyleCache=new CellStyleCache(wb);
		Font font=wb.createFont();
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 12000);
		sheet.setColumnWidth(4, 2000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 6000);
		sheet.setColumnWidth(7, 8000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 5000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 4000);
		sheet.setColumnWidth(15, 4000);
		sheet.setColumnWidth(16, 4000);
		sheet.setColumnWidth(17, 4000);
		sheet.setColumnWidth(18, 4000);
		sheet.setColumnWidth(19, 4000);
		sheet.setColumnWidth(20, 4000);
		sheet.setColumnWidth(21, 4000);
		sheet.setColumnWidth(22, 4000);
		sheet.setColumnWidth(23, 4000);
		sheet.setColumnWidth(24, 4000);
		sheet.setColumnWidth(25, 4000);
		sheet.setColumnWidth(26, 4000);
		
		Row row=sheet.createRow(0);
		writeCellValue(wb,sheet,row,0,"序号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,1,"产品类型",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,2,"产品编号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,3,"车型",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,4,"客户评级",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,5,"客户姓名",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,6,"身份证号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,7,"经销商",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,8,"审核员",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,9,"审批员",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,10,"进件日期",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,11,"批核日期",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,12,"审核时长",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,13,"放款/取消日期",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,14,"批核金额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,15,"GPS费",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,16,"服务费",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,17,"购置税",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,18,"审批期限",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,19,"首付比例",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,20,"执行利率",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,21,"月供款",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,22,"案件状态",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,23,"婚姻状况",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,24,"附加条件",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,25,"审核员意见",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,26,"审批人一意见",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		
		Font fontNormal=wb.createFont();		
		fontNormal.setFontHeightInPoints((short)10);
		
		for(int i=0;i<list.size();i++)
		{
			row=sheet.createRow(i+1);
			Map item=list.get(i);
			System.out.println(String.valueOf(i)+item.get("producttype").toString());
			String seq=String.valueOf(i+1);
			String productType=item.get("producttype").toString();
			String productSerial=item.get("productname").toString().split("-")[1];
			String ppcx=item.get("ppcx").toString();
			String custType=item.get("reserver5").toString();
			String custName=item.containsKey("name")?item.get("name").toString():"";
			String idNo=item.get("idno").toString();
			String jxs=item.get("sqfqr").toString();
			Object tmpShr=item.get("shr");
			String shr=tmpShr==null?"":tmpShr.toString();
			Object tmpSpr=item.get("spr");
			String spr=tmpSpr==null?"":tmpSpr.toString();
			String sqfqsj=item.get("sqfqsj").toString();
			Object tmpSpsj=item.get("spsj");
			String spsj=tmpSpsj==null?"":tmpSpsj.toString();
			double phje=(Double)item.get("rzje");
			double gpsfee=(Double)item.get("gpsfee");
			double gzs=(Double)item.get("gzs");
			double fwf=(Double)item.get("fwf");
			String rzqx=String.valueOf((Integer)item.get("rzqx"));
			double sfbl=(Double)item.get("sfbl");
			double rate=(Double)item.get("rate");
			String sqdzt1=item.get("sqdzt").toString();
			double ygk=(Double)item.get("ygk");
			String hyzk=item.containsKey("hyzk")?item.get("hyzk").toString():"";
			String fkfjtj=item.containsKey("fkfjtj")?item.get("fkfjtj").toString():"";
			String shyyj=item.containsKey("shyyj")?item.get("shyyj").toString():"";
			String qxr1yj=item.containsKey("qxr1yj")?item.get("qxr1yj").toString():"";
			writeCellValue(wb,sheet,row,0,seq,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,1,productType,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,2,productSerial,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,3,ppcx,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,4,custType,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,5,custName,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,6,idNo,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,7,jxs,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,8,shr,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,9,spr,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,10,sqfqsj,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,11,spsj,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,12,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,13,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			DecimalFormat df=new DecimalFormat("#,###,###,###,##0.00");
			DecimalFormat df1=new DecimalFormat("#.000000");
			writeCellAmount(wb,sheet,row,14,df.format(phje),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,15,df.format(gpsfee),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,16,df.format(gzs),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);	
			writeCellAmount(wb,sheet,row,17,df.format(fwf),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellValue(wb,sheet,row,18,rzqx,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,19,df1.format(sfbl*100)+"%",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,20,df1.format(rate*100)+"%",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,21,df.format(ygk),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellValue(wb,sheet,row,22,sqdzt1,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,23,hyzk,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,24,fkfjtj,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,25,shyyj,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,26,qxr1yj,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			
		}
		
		String fileName=Utils.get16UUID();
		String filePath=session.getServletContext().getRealPath("/")+"download"+File.separator+fileName+".xls";
		FileOutputStream fileOut = new FileOutputStream(filePath);   
		wb.write(fileOut);
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", new String("申请单明细".getBytes("GB2312"), "ISO_8859_1")+".xls");  
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)),  
	                                      headers, HttpStatus.OK);  
		
	}
	
	@RequestMapping(value="/getBranchAvailablyGpsLvl")
	@ResponseBody
	public List<Map> getBranchAvailablyGpsLvl(String rzje,HttpServletRequest request)
	{
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount sysAccount=sysAccountService.get(userid);
		return leasingAppServ.getBranchAvailablyGpsLvl(sysAccount.getBranchid(), Double.valueOf(rzje));
	}
	@RequestMapping(value="/exportZsajb")
	@ResponseBody
	public ResponseEntity<byte[]> exportZsajb(String ids,HttpSession session) throws ParseException, IOException
	{
		String sessionid=Utils.get16UUID();
		leasingAppServ.saveTmpExports(sessionid, ids);
		List<Map> list=leasingAppServ.getOnApproveRecord(sessionid);
		
		
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		cellStyleCache=new CellStyleCache(wb);
		Font font=wb.createFont();
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		for(int i=0;i<12;i++){
			sheet.setColumnWidth(i, 5000);
		}
		Row row=sheet.createRow(0);
		writeCellValue(wb,sheet,row,0,"序号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,1,"经销商名称",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,2,"经销商开户银行",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,3,"经销商放款账号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,4,"客户姓名",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,5,"身份证号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,6,"还款卡开户行",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,7,"还款账号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,8,"合同金额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,9,"放款金额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,10,"期数",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,11,"经销商返佣",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		
		Font fontNormal=wb.createFont();		
		fontNormal.setFontHeightInPoints((short)10);
		
		for(int i=0;i<list.size();i++)
		{
			row=sheet.createRow(i+1);
			Map item=list.get(i);
			String seq=String.valueOf(i+1);
			String id=item.containsKey("id")?item.get("id").toString():"";
			String sqdzt=item.containsKey("sqdzt")?item.get("sqdzt").toString():"";
			String branchname=item.containsKey("branchname")?item.get("branchname").toString():"";
			String openbankname=item.containsKey("openbankname")?item.get("openbankname").toString():"";
			String openbankno=item.containsKey("openbankno")?item.get("openbankno").toString():"";
			String name=item.containsKey("name")?item.get("name").toString():"";
			String idno=item.containsKey("idno")?item.get("idno").toString():"";
			String refundbankno=item.containsKey("refundbankno")?item.get("refundbankno").toString():"";
			String refundacctno=item.containsKey("refundacctno")?item.get("refundacctno").toString():"";
			double rzje=item.containsKey("rzje")?(Double)item.get("rzje"):0;
			double reserver1=item.containsKey("reserver1")?(Double)item.get("reserver1"):0;
			double reserver2=item.containsKey("reserver2")?(Double)item.get("reserver2"):0;
			int rzqx=item.containsKey("rzqx")?(Integer)item.get("rzqx"):0;
			double reserver3=item.containsKey("reserver3")?(Double)item.get("reserver3"):0;
			double yhk=item.containsKey("yhk")?(Double)item.get("yhk"):0;
			double fwf=item.containsKey("fwf")?(Double)item.get("fwf"):0;
			double ygk=calygk(item.get("id").toString(),rzje,fwf,yhk,rzqx);
			DecimalFormat df=new DecimalFormat("#,###,###,###,##0.00");
			DecimalFormat df1=new DecimalFormat("#.000000");
			writeCellValue(wb,sheet,row,0,seq,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,1,branchname,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,2,openbankname,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,3,openbankno,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,4,name,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,5,idno,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,6,refundbankno,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,7,refundacctno,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellAmount(wb,sheet,row,8,df.format(rzje),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,9,df.format(ygk),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,10,df.format(rzqx),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);	
			writeCellAmount(wb,sheet,row,11,df.format(reserver2),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);		
		}
		
		String fileName=Utils.get16UUID();
		String filePath=session.getServletContext().getRealPath("/")+"download"+File.separator+fileName+".xls";
		FileOutputStream fileOut = new FileOutputStream(filePath);   
		wb.write(fileOut);
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", new String("申请单明细".getBytes("GB2312"), "ISO_8859_1")+".xls");  
	    leasingAppServ.deleteTmpExportBySessionid(sessionid);
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)),  
	                                      headers, HttpStatus.OK);  
		
	}
	
	@RequestMapping("/exportKhhzhzb")
	@ResponseBody
	public ResponseEntity<byte[]> exportKhhzhzb(String ids,HttpSession session) throws ParseException, IOException
	{
		String sessionid=Utils.get16UUID();
		leasingAppServ.saveTmpExports(sessionid, ids);
		List<Map> list=leasingAppServ.getkhhzhzb(sessionid);
		
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		cellStyleCache=new CellStyleCache(wb);
		Font font=wb.createFont();
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 10000);
		sheet.setColumnWidth(2, 5000);
		sheet.setColumnWidth(3, 8000);
		sheet.setColumnWidth(4, 5000);
		sheet.setColumnWidth(5, 5000);
		sheet.setColumnWidth(6, 6000);
		sheet.setColumnWidth(7, 5000);
		sheet.setColumnWidth(8, 5000);
		sheet.setColumnWidth(9, 5000);
		sheet.setColumnWidth(10, 5000);
		sheet.setColumnWidth(11, 5000);
		sheet.setColumnWidth(12, 5000);
		sheet.setColumnWidth(13, 5000);
		sheet.setColumnWidth(14, 5000);
		sheet.setColumnWidth(15, 5000);
		
		Row row=sheet.createRow(0);
		writeCellValue(wb,sheet,row,0,"序号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,1,"申请编号",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,2,"姓名",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,3,"经销商",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,4,"融资金额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,5,"放款金额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,6,"融资期限",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,7,"融资利率",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,8,"月租金",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,9,"月租金总额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,10,"利息总额",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,11,"GPS档位",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,12,"GPS价位",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,13,"返GPS服务费",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,14,"评估费",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		writeCellValue(wb,sheet,row,15,"服务费",font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		
		Font fontNormal=wb.createFont();		
		fontNormal.setFontHeightInPoints((short)10);
		
		for(int i=0;i<list.size();i++)
		{
			row=sheet.createRow(i+1);
			Map item=list.get(i);
			String seq=String.valueOf(i+1);
			String id=item.containsKey("id")?item.get("id").toString():"";
			String name=item.containsKey("name")?item.get("name").toString():"";
			String sqfqr=item.containsKey("sqfqr")?item.get("sqfqr").toString():"";
			double yhk=item.containsKey("yhk")?(Double)item.get("yhk"):0;
			double rzje=item.containsKey("rzje")?(Double)item.get("rzje"):0;
			int rzqx=item.containsKey("rzqx")?(Integer)item.get("rzqx"):0;
			double rate=item.containsKey("rate")?(Double)item.get("rate"):0;
			double gpsfee=item.containsKey("gpsfee")?(Double)item.get("gpsfee"):0;
			double gpsjw=item.containsKey("gpsjw")?(Double)item.get("gpsjw"):0;
			double jxsfy=item.containsKey("jxsfy")?(Double)item.get("jxsfy"):0;
			double pgf=item.containsKey("pgf")?(Double)item.get("pgf"):0;
			double fwf=item.containsKey("fwf")?(Double)item.get("fwf"):0;
			double rzsxf=item.containsKey("rzsxf")?(Double)item.get("rzsxf"):0;
			double fkje=0.00;
			double ygk=0.00;
			
			int appdate=Integer.valueOf(id.split("-")[1]);
			if(appdate<20160618)
			{
				if(Double.compare(gpsfee, 0.00)>0)
				{
					fkje=Math.round(rzje-1998-fwf)-rzsxf;
				}
				else
				{
					fkje=Math.round(rzje-fwf)-rzsxf;
				}
				ygk=Math.round((rzje-fwf)/10000*yhk)+Math.ceil(fwf/rzqx);
				
			}
			else if(appdate>=20160618&&appdate<20160622)
			{
				fkje=Math.round(rzje-gpsfee+jxsfy-pgf);
				ygk=Math.round(rzje/10000*yhk);
			}
			else
			{
				fkje=Math.round(rzje-gpsfee-pgf);
				ygk=Math.round(rzje/10000*yhk);
			}
			double ygkze=ygk*rzqx;
			double lxze=ygkze-rzje;
			DecimalFormat df=new DecimalFormat("#,###,###,###,##0.00");
			DecimalFormat df1=new DecimalFormat("#.000000");
			writeCellValue(wb,sheet,row,0,seq,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,1,id,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,2,name,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,3,sqfqr,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellAmount(wb,sheet,row,4,df.format(rzje),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,5,df.format(fkje),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,6,df.format(rzqx),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,7,df.format(rate),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,8,df.format(ygk),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,9,df.format(ygkze),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,10,df.format(lxze),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,11,df.format(gpsfee),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,12,df.format(gpsjw),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,13,df.format(jxsfy),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,14,df.format(pgf),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,15,df.format(fwf),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);		
		}
		
		String fileName=Utils.get16UUID();
		String filePath=session.getServletContext().getRealPath("/")+"download"+File.separator+fileName+".xls";
		FileOutputStream fileOut = new FileOutputStream(filePath);   
		wb.write(fileOut);
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", new String("申请单明细".getBytes("GB2312"), "ISO_8859_1")+".xls");  
	    leasingAppServ.deleteTmpExportBySessionid(sessionid);
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)),  
	                                      headers, HttpStatus.OK);  
		
	}
	private double calygk(String appid,double rzje,double fwf,double yhk,int rzqx)
	{
		double ygk=0.00;
		int appdate=Integer.valueOf(appid.split("-")[1]);
		if(appdate<20160618)
		{
			ygk=Math.round((rzje-fwf)/10000*yhk)+Math.ceil(fwf/rzqx);
			
		}
		else if(appdate>=20160618&&appdate<20160622)
		{
			ygk=Math.round(rzje/10000*yhk);
		}
		else
		{
			ygk=Math.round(rzje/10000*yhk);
		}
		return ygk;
	}
	/**
	 * 到处客户还款信息表
	 * **/
	@RequestMapping("/exportKhhkxxb")
	@ResponseBody
	public ResponseEntity<byte[]> exportKhhkxxb(String ids,HttpSession session) throws ParseException, IOException
	{
		String sessionid=Utils.get16UUID();
		leasingAppServ.saveTmpExports(sessionid, ids);
		List<Map> list=leasingAppServ.getKhhkxxb(sessionid);
		
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		cellStyleCache=new CellStyleCache(wb);
		Font font=wb.createFont();
		font.setFontHeightInPoints((short)10);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		//设置列宽
		for(int i=0;i<75;i++){
			sheet.setColumnWidth(i, 5000);
		}
		String[] colnames={"序号",	
							"还款日",	
							"合同编号",	
							"经销商",	
							"二级经销商",	
							"客户类型",	
							"产品类别",	
							"客户类别",	
							"放款日期",	
							"五级分类",	
							"融资状态",	
							"逾期天数",	
							"客户姓名",	
							"身份证号",	
							"性别",	
							"年龄",	
							"民族",	
							"行业",	
							"单位名称",	
							"单位电话",	
							"单位性质",	
							"单位地址",	
							"职业",	
							"家庭住址",	
							"省份",	
							"电话",	
							"联系人一",	
							"联系人一电话",	
							"联系人二",	
							"联系人二电话",	
							"配偶",	
							"配偶电话",	
							"车型",	
							"颜色",	
							"车架号",	
							"发动机号",	
							"车牌号",	
							"上牌地",	
							"中登登记",	
							"是否完成归档",	
							"裸车价",	
							"交强险开始日期",	
							"交强险费用",	
							"保险公司（商险）",	
							"商业险开始日期",	
							"商业险保险费",	
							"融购置税金额",	
							"GPS费用",	
							"融资手续费",	
							"服务费",	
							"融资比例",	
							"合同金额",	
							"合同期限",	
							"合同利率",	
							"还款方式",	
							"月供款",	
							"五级分类",	
							"融资状态",	
							"已还期数",	
							"已还本金",	
							"已付利息",	
							"已付费用",	
							"尚欠本金",	
							"尚欠利息",	
							"尚欠费用",	
							"还款户名",	
							"开户行",	
							"账号",	
							"供应商",	
							"明GPS编码",	
							"暗GPS编码",	
							"发票号码",	
							"二手车评估公司名称",	
							"评估报告出具时间",	
							"备注"
						 };
		Row row=sheet.createRow(0);
		for(int i=0;i<colnames.length;i++)
		{
			writeCellValue(wb,sheet,row,i,colnames[i],font,IndexedColors.GREY_25_PERCENT.getIndex(),CellStyle.ALIGN_CENTER);
		}
		
		
		Font fontNormal=wb.createFont();		
		fontNormal.setFontHeightInPoints((short)10);
		
		for(int i=0;i<list.size();i++)
		{
			row=sheet.createRow(i+1);
			Map item=list.get(i);
			String seq=String.valueOf(i+1);
			String contractid=item.containsKey("contractid")?item.get("contractid").toString():"";
			String sqfqr=item.containsKey("sqfqr")?item.get("sqfqr").toString():"";
			String producttype=item.containsKey("producttype")?item.get("producttype").toString():"";
			String reserver5=item.containsKey("reserver5")?item.get("reserver5").toString():"";
			String name=item.containsKey("name")?item.get("name").toString():"";
			String idno=item.containsKey("idno")?item.get("idno").toString():"";
			String czr1sshy=item.containsKey("czr1sshy")?item.get("czr1sshy").toString():"";
			String czr1dwmc=item.containsKey("czr1dwmc")?item.get("czr1dwmc").toString():"";
			String czr1dwdh=item.containsKey("czr1dwdh")?item.get("czr1dwdh").toString():"";
			String czr1dwlx=item.containsKey("czr1dwlx")?item.get("czr1dwlx").toString():"";
			String czr1dwdz=item.containsKey("czr1dwdz")?item.get("czr1dwdz").toString():"";
			String xxxdz=item.containsKey("xxxdz")?item.get("xxxdz").toString():"";
			String mobile=item.containsKey("mobile")?item.get("mobile").toString():"";
			String lxr1name=item.containsKey("lxr1name")?item.get("lxr1name").toString():"";
			String lxr1mobile=item.containsKey("lxr1mobile")?item.get("lxr1mobile").toString():"";
			String lxr2name=item.containsKey("lxr2name")?item.get("lxr2name").toString():"";
			String lxr2mobile=item.containsKey("lxr2mobile")?item.get("lxr2mobile").toString():"";
			String poname=item.containsKey("poname")?item.get("poname").toString():"";
			String pomobile=item.containsKey("pomobile")?item.get("pomobile").toString():"";
			String ppcx=item.containsKey("ppcx")?item.get("ppcx").toString():"";
			String clys=item.containsKey("clys")?item.get("clys").toString():"";
			String cjh=item.containsKey("cjh")?item.get("cjh").toString():"";
			String fdjh=item.containsKey("fdjh")?item.get("fdjh").toString():"";
	
			double lcj=item.containsKey("lcj")?(Double)item.get("lcj"):0;
			double gzs=item.containsKey("gzs")?(Double)item.get("gzs"):0;
			double gpsfee=item.containsKey("gpsfee")?(Double)item.get("gpsfee"):0;
			double rzsxf=item.containsKey("rzsxf")?(Double)item.get("rzsxf"):0;
			double fwf=item.containsKey("fwf")?(Double)item.get("fwf"):0;
			int periods=item.containsKey("periods")?(Integer)item.get("periods"):0;
			double rate=item.containsKey("rate")?(Double)item.get("rate"):0;
			
			/**计算月供款**/
			
			double yhk=item.containsKey("yhk")?(Double)item.get("yhk"):0;
			double rzje=item.containsKey("rzje")?(Double)item.get("rzje"):0;
			int rzqx=item.containsKey("rzqx")?(Integer)item.get("rzqx"):0;
			double ygk=calygk(item.get("id").toString(),rzje,fwf,yhk,rzqx);
			
			String refundbankno=item.containsKey("refundbankno")?item.get("refundbankno").toString():"";
			String refundacctno=item.containsKey("refundacctno")?item.get("refundacctno").toString():"";
			DecimalFormat df=new DecimalFormat("#,###,###,###,##0.00");
			writeCellValue(wb,sheet,row,0,seq,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,1,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,2,contractid,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,3,sqfqr,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,4,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,5,"个人",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,6,producttype,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,7,reserver5,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,8,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,9,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,10,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,11,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,12,name,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,13,idno,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,14,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,15,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,16,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,17,czr1sshy,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,18,czr1dwmc,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,19,czr1dwdh,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,20,czr1dwlx,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,21,czr1dwdz,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,22,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,23,xxxdz,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,24,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,25,mobile,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,26,lxr1name,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,27,lxr1mobile,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,28,lxr2name,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,29,lxr2mobile,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,30,poname,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,31,pomobile,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,32,ppcx,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,33,clys,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,34,cjh,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,35,fdjh,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			
			writeCellValue(wb,sheet,row,36,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,37,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,38,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,39,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			
			writeCellAmount(wb,sheet,row,40,df.format(lcj),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellValue(wb,sheet,row,41,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,42,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,43,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,44,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,45,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			
			writeCellAmount(wb,sheet,row,46,df.format(gzs),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,47,df.format(gpsfee),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,48,df.format(rzsxf),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellAmount(wb,sheet,row,49,df.format(fwf),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellValue(wb,sheet,row,50,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,51,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,52,String.valueOf(periods),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellAmount(wb,sheet,row,53,df.format(rate),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellValue(wb,sheet,row,54,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellAmount(wb,sheet,row,55,df.format(ygk),fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_RIGHT);
			writeCellValue(wb,sheet,row,56,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,57,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,58,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,59,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,60,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,61,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,62,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,63,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,64,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,65,name,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,66,refundbankno,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,67,refundacctno,fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_LEFT);
			writeCellValue(wb,sheet,row,68,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,69,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,70,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,71,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,72,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,73,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
			writeCellValue(wb,sheet,row,74,"",fontNormal,IndexedColors.WHITE.getIndex(),CellStyle.ALIGN_CENTER);
		}
		
		String fileName=Utils.get16UUID();
		String filePath=session.getServletContext().getRealPath("/")+"download"+File.separator+fileName+".xls";
		FileOutputStream fileOut = new FileOutputStream(filePath);   
		wb.write(fileOut);
		HttpHeaders headers = new HttpHeaders();  
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  
	    headers.setContentDispositionFormData("attachment", new String("申请单明细".getBytes("GB2312"), "ISO_8859_1")+".xls");  
	    leasingAppServ.deleteTmpExportBySessionid(sessionid);
	    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(filePath)),  
	                                      headers, HttpStatus.OK);  
		
	}
	
}
