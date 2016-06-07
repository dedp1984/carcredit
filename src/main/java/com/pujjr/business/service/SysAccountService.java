package com.pujjr.business.service; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pujjr.business.dao.SysAccountMapper;
import com.pujjr.business.dao.SysAccountRoleMapper;
import com.pujjr.business.domain.SysAccount;
import com.pujjr.business.domain.SysAccountRoleKey;
import com.pujjr.business.domain.SysRole;

@Service
@Transactional(rollbackFor=Exception.class) 
public class SysAccountService {

	@Autowired
	private SysAccountMapper sysAccountDao;
	@Autowired
	private SysAccountRoleMapper sysAccountRoleDao;
	
	public void add(SysAccount record) throws Exception
	{
		if(sysAccountDao.selectByPrimaryKey(record.getId())!=null)
		{
			throw new Exception("用户名已存在");
		}
		sysAccountDao.insert(record);
		sysAccountRoleDao.deleteByAccountid(record.getId());
		for(int i=0;i<record.getRoles().size();i++)
		{
			SysRole role=record.getRoles().get(i);
			SysAccountRoleKey key=new SysAccountRoleKey();
			key.setAccountid(record.getId());
			key.setRoleid(role.getId());
			sysAccountRoleDao.insert(key);
		}
	}
	
	
	public void update(SysAccount record)
	{
		sysAccountDao.updateByPrimaryKeySelective(record);
		sysAccountRoleDao.deleteByAccountid(record.getId());
		for(int i=0;i<record.getRoles().size();i++)
		{
			SysRole role=record.getRoles().get(i);
			SysAccountRoleKey key=new SysAccountRoleKey();
			key.setAccountid(record.getId());
			key.setRoleid(role.getId());
			sysAccountRoleDao.insert(key);
		}
	}
	
	public void onlyUpdateAccount(SysAccount record)
	{
		sysAccountDao.updateByPrimaryKeySelective(record);
	}
	public void delete(String id)
	{
		sysAccountDao.deleteByPrimaryKey(id);
	}
	
	public SysAccount get(String id)
	{
		return sysAccountDao.selectByPrimaryKey(id);
	}
	
	public List<SysAccount> getList()
	{
		return sysAccountDao.selectList();
	}
	
}
