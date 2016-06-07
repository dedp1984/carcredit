package com.pujjr.business.dao;

import com.pujjr.business.domain.LeasingApprove;

public interface LeasingApproveMapper {
    int deleteByPrimaryKey(String id);

    int insert(LeasingApprove record);

    int insertSelective(LeasingApprove record);

    LeasingApprove selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LeasingApprove record);

    int updateByPrimaryKey(LeasingApprove record);
}