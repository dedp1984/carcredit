package com.pujjr.business.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SqlServerMapper 
{
	public List<HashMap<String,Object>> selectInCaseList(@Param("sqfqrq")String sqfqrq);
	
	public List<HashMap<String,Object>> selectChargeBackList();
	
	public List<HashMap<String,Object>> selectCreditDataList(@Param("sqfqrq")String sqfqrq);
}
