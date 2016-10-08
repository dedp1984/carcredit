package com.pujjr.business.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SqlServerMapper 
{
	public List<HashMap<String,Object>> selectInCaseList(@Param("sqfqrq")String sqfqrq);
	
	public List<HashMap<String,Object>> selectChargeBackList();
	
	public List<HashMap<String,Object>> selectCreditDataList(@Param("sqfqrq")String sqfqrq);
	
	public List<HashMap<String,Object>> selectCustRepayList(@Param("qyrq")String qyrq);
	
	public List<HashMap<String,Object>> selectFkmxList(@Param("fkrq")String fkrq);
	
	public List<HashMap<String,Object>> selectCustomerInfo();
	
	public List<HashMap<String,Object>> selectContactInfo();
	
	public void updateCustomerInfo(HashMap<String,Object> customInfo);
	
	public void updateContactTelInfo(@Param("appId")String appId,@Param("tel")String tel);
}
