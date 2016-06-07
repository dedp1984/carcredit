package com.pujjr.business.dao;

import java.util.List;

import com.pujjr.business.domain.SysAccount;

public interface SysAccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysAccount record);

    int insertSelective(SysAccount record);

    SysAccount selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysAccount record);

    int updateByPrimaryKey(SysAccount record);
    
    List<SysAccount> selectList();
}