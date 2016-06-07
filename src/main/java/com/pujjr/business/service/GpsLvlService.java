package com.pujjr.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pujjr.business.dao.GpsLvlMapper;
import com.pujjr.business.domain.GpsLvl;

@Service
public class GpsLvlService 
{
	@Autowired
	private GpsLvlMapper gpsLvlDao;
	
	public List<GpsLvl> queryGpsLvlList(String lvlamt)
	{
		return gpsLvlDao.selectList(lvlamt);
	}
}
