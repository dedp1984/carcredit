package com.pujjr.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pujjr.business.domain.GpsLvl;

public interface GpsLvlMapper {
    int deleteByPrimaryKey(String id);

    int insert(GpsLvl record);

    int insertSelective(GpsLvl record);

    GpsLvl selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GpsLvl record);

    int updateByPrimaryKey(GpsLvl record);
    
    List<GpsLvl> selectList(@Param("lvlamt")String lvlamt);
    
}