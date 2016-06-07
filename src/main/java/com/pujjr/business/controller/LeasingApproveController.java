package com.pujjr.business.controller;

import io.jsonwebtoken.Claims;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pujjr.business.service.SysAccountService;
import com.pujjr.business.vo.AppData;

@Controller
@RequestMapping(value="/api/leasingapproves")
public class LeasingApproveController extends BaseController 
{
	@Autowired
	private LeasingApproveService leasingApproveServ;
	@Autowired
	private SysAccountService sysAccountService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public LeasingApprove get(@PathVariable String id)
	{
		return leasingApproveServ.get(id);
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void update(@RequestBody LeasingApprove record)
	{
		leasingApproveServ.update(record);
	}
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public void saveApproveData(@RequestBody  AppData data,HttpServletRequest request )
	{
		Claims claims=(Claims)request.getAttribute("claims");
		String userid=claims.getSubject();
		SysAccount approveAccount=sysAccountService.get(userid);
		leasingApproveServ.saveApproveData(data,approveAccount);
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	@ResponseBody
//	public PageVo getList(String sqdzt,String pagesize,String startpageno)
//	{
//		PageHelper.startPage(Integer.parseInt(startpageno), Integer.parseInt(pagesize),true);
//		List<LeasingApp> list=leasingAppServ.getList();
//		PageVo page=new PageVo();
//		page.setItems(list);
//		page.setTotalItems((int) ((PageVo)list).getTotal());
//		return page;
//	}
	
}
