package com.pujjr.business.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pujjr.business.domain.LeasingApp;
import com.pujjr.business.vo.AppManageDetail;

public interface LeasingAppMapper {

	int deleteByPrimaryKey(String id);

    int insert(LeasingApp record);

    int insertSelective(LeasingApp record);

    LeasingApp selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeasingApp record);

    int updateByPrimaryKey(LeasingApp record);
    
    List<LeasingApp> selectList(@Param("id")String id,
			   					@Param("name")String name,
			   					@Param("sqfqrq")String sqfqrq,
			   					@Param("sqdzt")List sqdzt);
    
    List<LeasingApp> selectJxsList(@Param("id")String id,
    							   @Param("name")String name,
    							   @Param("sqfqrq")String sqfqrq,
    							   @Param("sqdzt")List sqdzt,
    							   @Param("sqfqr")String sqfqr);
    
    List<LeasingApp> selectCheckList(@Param("id")String id,
			   						 @Param("name")String name,
			   						 @Param("sqfqrq")String sqfqrq,
			   						 @Param("sqdzt")List sqdzt,
			   						 @Param("shr")String shr);
    
    List<AppManageDetail> selectManagerAppDetail(@Param("id")String id,
												 @Param("name")String name,
												 @Param("sqfqrq")String sqfqrq,
												 @Param("sqdzt")List sqdzt);
    
    List<Map> selectInCaseList(@Param("id")String id,
			 					@Param("name")String name,
			 					@Param("sqfqrq")String sqfqrq,
			 					@Param("sqdzt")List sqdzt);
    List<Map> selectBranchAvailablyGpsLvl(
    										@Param("branchId")String branchId,
    										@Param("rzje")double rzje
    									  );
    Map selectOnApproveRecord(@Param("id")String id);
}