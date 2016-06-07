package com.pujjr.business.dao;

import java.util.List;

import com.pujjr.business.domain.SysBranch;

public interface SysBranchMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysBranch record);

    int insertSelective(SysBranch record);

    SysBranch selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysBranch record);

    int updateByPrimaryKey(SysBranch record);
    
    List<SysBranch> selectList();
    
}