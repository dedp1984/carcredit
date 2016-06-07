package com.pujjr.business.dao;

import java.util.List;

import com.pujjr.business.domain.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> selectList();
}