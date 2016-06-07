package com.pujjr.business.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pujjr.business.domain.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    
    List<SysMenu> selectSubMenuList(@Param("parentid")String parentid);
    
    List<SysMenu> selectSubMenuListByAccountIdAndParentMenuId(@Param("accountid")String accountid,@Param("parentid")String parentid);
    
    List<SysMenu> selectAuthMenusByAccountId(@Param("accountid")String accountid);
    
}