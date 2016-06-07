package com.pujjr.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pujjr.business.dao.SysMenuMapper;
import com.pujjr.business.dao.SysRoleMapper;
import com.pujjr.business.domain.SysMenu;
import com.pujjr.business.domain.SysRole;

@Service
@Transactional(rollbackFor=Exception.class) 
public class SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleDao;
	
	public void add(SysRole record)
	{
		sysRoleDao.insert(record);
	}
	
	public void update(SysRole record)
	{
		sysRoleDao.updateByPrimaryKey(record);
	}
	
	public void delete(String id)
	{
		sysRoleDao.deleteByPrimaryKey(id);
	}
	
	public SysRole get(String id)
	{
		return sysRoleDao.selectByPrimaryKey(id);
	}
	
	public List<SysRole> getList()
	{
		return sysRoleDao.selectList();
	
	}
}
