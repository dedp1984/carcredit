package com.pujjr.business.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pujjr.business.dao.SqlServerMapper;

@Service
public class WSSqlserverService 
{
	@Autowired
	private SqlServerMapper sqlServerDao;
	public List<HashMap<String,Object>> sqlserverTest()
	{
		List<HashMap<String,Object>> list = sqlServerDao.selectList();
		return  list;
	}
}
