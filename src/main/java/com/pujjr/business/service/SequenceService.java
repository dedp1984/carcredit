package com.pujjr.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pujjr.business.dao.SequenceMapper;
import com.pujjr.business.domain.Sequence;

@Service
public class SequenceService 
{
	@Autowired
	private SequenceMapper seqDao;
	
	public synchronized int getNextVal(String name)
	{
		
		Sequence seq=seqDao.selectByPrimaryKey(name);
		int curVal=seq.getCurval();
		int increment=seq.getIncrement();
		seq.setCurval(curVal+increment);
		seqDao.updateByPrimaryKey(seq);
		return curVal;
	}
}
