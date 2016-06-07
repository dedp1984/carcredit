package com.pujjr.business.dao;

import com.pujjr.business.domain.BranchGpsLvl;

public interface BranchGpsLvlMapper {
    int deleteByPrimaryKey(String id);

    int insert(BranchGpsLvl record);

    int insertSelective(BranchGpsLvl record);

    BranchGpsLvl selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BranchGpsLvl record);

    int updateByPrimaryKey(BranchGpsLvl record);
    
    int deleteByBranchId(String branchId);
}