package com.pujjr.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pujjr.business.dao.BranchGpsLvlMapper;
import com.pujjr.business.dao.SysBranchMapper;
import com.pujjr.business.domain.BranchGpsLvl;
import com.pujjr.business.domain.GpsLvl;
import com.pujjr.business.domain.SysBranch;
import com.pujjr.common.Utils;

@Service
@Transactional(rollbackFor=Exception.class) 
public class SysBranchService {

	@Autowired
	private SysBranchMapper sysBranchDao;
	
	@Autowired
	private BranchGpsLvlMapper branchGpsLvlDao;
	
	private void addBranchGpsLvl(String branchId,double minLvlAmt,double maxLvlAmt,List<GpsLvl> lvls)
	{
		if(lvls.size()==0)
			return;
		BranchGpsLvl lvl=new BranchGpsLvl();
		lvl.setId(Utils.get16UUID());
		lvl.setBranchid(branchId);
		lvl.setMinlvlamt(minLvlAmt);
		lvl.setMaxlvlamt(maxLvlAmt);
		String list="";
		for(int i=0;i<lvls.size();i++)
		{
			GpsLvl l=lvls.get(i);
			list+=l.getId()+",";
		}
		if(list.length()>0)
		{
			list=list.substring(0, list.length()-1);
		}
		lvl.setLvls(list);
		
		branchGpsLvlDao.insert(lvl);
	}
	public void add(SysBranch record)
	{
		addBranchGpsLvl(record.getId(),0.00,40000.00,record.getGpslvl0t4s());
		addBranchGpsLvl(record.getId(),40000.00,60000.00,record.getGpslvl4t6s());
		addBranchGpsLvl(record.getId(),60000.00,1000000000.00,record.getGpslvl6s());
		sysBranchDao.insert(record);
	}
	
	public void update(SysBranch record)
	{
		branchGpsLvlDao.deleteByBranchId(record.getId());
		addBranchGpsLvl(record.getId(),0.00,40000.00,record.getGpslvl0t4s());
		addBranchGpsLvl(record.getId(),40000.00,60000.00,record.getGpslvl4t6s());
		addBranchGpsLvl(record.getId(),60000.00,1000000000.00,record.getGpslvl6s());
		sysBranchDao.updateByPrimaryKey(record);
	}
	
	public void delete(String id)
	{
		branchGpsLvlDao.deleteByBranchId(id);
		sysBranchDao.deleteByPrimaryKey(id);
	}
	
	public SysBranch get(String id)
	{
		return sysBranchDao.selectByPrimaryKey(id);
	}
	
	public List<SysBranch> getList()
	{
		return sysBranchDao.selectList();
	}
}
