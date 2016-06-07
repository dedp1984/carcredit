package com.pujjr.business.dao;

import com.pujjr.business.domain.SysAccountRoleKey;

public interface SysAccountRoleMapper {
    int deleteByPrimaryKey(SysAccountRoleKey key);

    int insert(SysAccountRoleKey record);

    int insertSelective(SysAccountRoleKey record);
    
    int deleteByAccountid(String accountid);
}