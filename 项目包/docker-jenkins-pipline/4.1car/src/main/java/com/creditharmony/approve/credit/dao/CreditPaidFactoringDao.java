package com.creditharmony.approve.credit.dao;

import com.creditharmony.approve.credit.entity.CreditPaidFactoring;

public interface CreditPaidFactoringDao {
    int deleteByPrimaryKey(String id);

    int insert(CreditPaidFactoring record);

    int insertSelective(CreditPaidFactoring record);

    CreditPaidFactoring selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPaidFactoring record);

    int updateByPrimaryKey(CreditPaidFactoring record);
}