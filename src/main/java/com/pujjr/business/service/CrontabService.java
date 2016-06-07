package com.pujjr.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pujjr.business.dao.SequenceMapper;
import com.pujjr.business.domain.Sequence;

@Service
public class CrontabService 
{
	@Autowired
	private SequenceMapper seqDao;
	
	public void resetSequence()
	{
		Sequence seq=seqDao.selectByPrimaryKey("appid");
		seq.setCurval(1);
		seqDao.updateByPrimaryKey(seq);
	}
}
