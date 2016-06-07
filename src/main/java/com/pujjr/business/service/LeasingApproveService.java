package com.pujjr.business.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.pujjr.business.dao.LeasingAppMapper;
import com.pujjr.business.dao.LeasingApproveMapper;
import com.pujjr.business.dao.LeasingCheckMapper;
import com.pujjr.business.domain.LeasingApp;
import com.pujjr.business.domain.LeasingApprove;
import com.pujjr.business.domain.LeasingCheck;
import com.pujjr.business.domain.SysAccount;
import com.pujjr.business.vo.AppData;

@Service
@Transactional(rollbackFor=Exception.class) 
public class LeasingApproveService {
	
	@Autowired
	private LeasingApproveMapper leasingApproveDao;
	@Autowired
	private LeasingAppMapper leasingAppDao;
	
	public LeasingApprove get(String id)
	{
		return leasingApproveDao.selectByPrimaryKey(id);
	}
	
	public void update(LeasingApprove record)
	{
		leasingApproveDao.updateByPrimaryKey(record);
	}
	
	public void delete(String id)
	{
		leasingApproveDao.deleteByPrimaryKey(id);
	}
	
	public void add(LeasingApprove record,SysAccount operator)
	{
		record.setSpr(operator.getId());
		record.setSpsj(new Timestamp(new Date().getTime()));
		leasingApproveDao.insert(record);
	}
	
	public void saveApproveData( AppData data,SysAccount operator)
	{
		
		data.getApprove().setSpr(operator.getId());
		data.getApprove().setSpsj(new Timestamp(new Date().getTime()));
		leasingApproveDao.updateByPrimaryKey(data.getApprove());
		leasingAppDao.updateByPrimaryKey(data.getApp());
	}
}
