package com.pujjr.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pujjr.business.dao.LeasingAppMapper;
import com.pujjr.business.dao.LeasingApproveMapper;
import com.pujjr.business.dao.LeasingCheckMapper;
import com.pujjr.business.domain.LeasingApp;
import com.pujjr.business.vo.AppData;
import com.pujjr.business.vo.AppManageDetail;


@Service
@Transactional(rollbackFor=Exception.class) 
public class LeasingAppService {
	@Autowired
	private LeasingAppMapper leasingAppDao;
	@Autowired
	private LeasingCheckMapper leasingCheckDao;
	@Autowired
	private LeasingApproveMapper leasingApproveDao;
	
	public  LeasingApp get(String id)
	{
		return leasingAppDao.selectByPrimaryKey(id);
	}
	
	public void update(LeasingApp record)
	{
		leasingAppDao.updateByPrimaryKey(record);
	}
	
	public void delete(String id)
	{
		leasingAppDao.deleteByPrimaryKey(id);
	}
	
	public void add(LeasingApp record)
	{
		leasingAppDao.insert(record);
	}
	public List<LeasingApp> getList(String id,String name,String sqfqrq,String sqdzt)
	{
		List<String> l=new ArrayList<String>();
		if(sqdzt!=null)
		{
			String[] s=sqdzt.split(",");
			
			for(int i=0;i<s.length;i++)
			{
				l.add(s[i]);
			}
		}
		
		return leasingAppDao.selectList(id,name,sqfqrq,l);
	}
	public List<LeasingApp> getJxsList(String id,String name,String sqfqrq,String sqdzt,String sqr)
	{
		List<String> l=new ArrayList<String>();
		if(sqdzt!=null && !sqdzt.equals(""))
		{
			String[] s=sqdzt.split(",");
			
			for(int i=0;i<s.length;i++)
			{
				l.add(s[i]);
			}
		}
		
		return leasingAppDao.selectJxsList(id,name,sqfqrq,l, sqr);
	}
	public List<LeasingApp> getCheckList(String id,String name,String sqfqrq,String sqdzt,String shr)
	{
		List<String> l=new ArrayList<String>();
		if(sqdzt!=null && !sqdzt.equals(""))
		{
			String[] s=sqdzt.split(",");
			
			for(int i=0;i<s.length;i++)
			{
				l.add(s[i]);
			}
		}
		
		return leasingAppDao.selectCheckList(id,name,sqfqrq,l,shr);
	}
	
	public void updateAllAppData(AppData data)
	{
		leasingAppDao.updateByPrimaryKey(data.getApp());
		leasingCheckDao.updateByPrimaryKey(data.getCheck());
		leasingApproveDao.updateByPrimaryKey(data.getApprove());
	}
	
	public List<AppManageDetail> selectManagerAppDetail(String id,String name,String sqfqrq,String sqdzt)
	{
		List<String> l=new ArrayList<String>();
		if(sqdzt!=null && !sqdzt.equals(""))
		{
			String[] s=sqdzt.split(",");
			
			for(int i=0;i<s.length;i++)
			{
				l.add(s[i]);
			}
		}
		return leasingAppDao.selectManagerAppDetail(id,name,sqfqrq,l);
	}
	
	public String queryCustType(LeasingApp app)
	{
		if(Double.compare(app.getSfbl(),0.4)>=0&&(Double.compare(app.getGzs(),0)==0 && Double.compare(app.getRzje(),15.5)<0))
		{
			return "A";
		}
		if(Integer.valueOf(app.getCzr1dwlx())>=5)
		{
			return "A";
		}
		if(Double.compare(app.getSfbl(), 20)==0&&Double.compare(app.getGzs(),0)>0)
		{
			return "C";
		}
		if((Integer.valueOf(app.getCzr1sshy())<=4)&&(app.getCzr1zj().equals("4")||app.getCzr1zj().equals("6")||app.getCzr1zj().equals("7")))
		{
			return "C";
		}
		return "B";
	}
	
	public List<Map> getInCaseList(String id,String name,String sqfqrq,String sqdzt)
	{
		List<String> l=new ArrayList<String>();
		if(sqdzt!=null && !sqdzt.equals(""))
		{
			String[] s=sqdzt.split(",");
			
			for(int i=0;i<s.length;i++)
			{
				l.add(s[i]);
			}
		}
		List<Map> list=leasingAppDao.selectInCaseList(id, name, sqfqrq, l);
		int size=list.size();
		for(int i=0;i<size;i++)
		{
			Map item=list.get(i);
			double rzje=(Double)item.get("rzje");
			double fwf=(Double)item.get("fwf");
			double wyyhk=(Double)item.get("wyyhk");
			int  rzqx=(Integer)item.get("rzqx");
			double ygk=Math.round((rzje-fwf)/10000*wyyhk+Math.ceil(fwf/rzqx));
			item.put("ygk", ygk);
			list.set(i, item);
		}
		return list;
		
	}
	
	public List<Map>  getBranchAvailablyGpsLvl(String branchId,double rzje)
	{
		return leasingAppDao.selectBranchAvailablyGpsLvl(branchId, rzje);
	}
}
