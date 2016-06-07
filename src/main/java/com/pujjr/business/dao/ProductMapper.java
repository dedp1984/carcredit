package com.pujjr.business.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pujjr.business.domain.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);
    
    List<HashMap> getProductNames();
    
    List<HashMap> getProductSeqs(@Param("productName") String productName);
    
    List<HashMap> getProductPeriods(@Param("productName")String productName,@Param("productSeq")String productSeq);
    
    Product getProduct(@Param("productName")String productName,@Param("productSeq")String productSeq,@Param("periods")int periods);
}