package com.pujjr.business.dao;

import com.pujjr.business.domain.LeasingCheck;

public interface LeasingCheckMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeasingCheck record);

    int insertSelective(LeasingCheck record);

    LeasingCheck selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeasingCheck record);

    int updateByPrimaryKey(LeasingCheck record);
}