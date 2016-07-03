package com.pujjr.business.dao;

import org.apache.ibatis.annotations.Param;

import com.pujjr.business.domain.TmpExportKey;

public interface TmpExportMapper {
    int deleteByPrimaryKey(TmpExportKey key);

    int insert(TmpExportKey record);

    int insertSelective(TmpExportKey record);
    
    int deleteBySessionid(@Param("sessionid") String sessionid);
}