package com.creditharmony.approve.credit.dao;

import com.creditharmony.approve.credit.entity.CreditPaidTradeFinancing;

public interface CreditPaidTradeFinancingDao {
    int deleteByPrimaryKey(String id);

    int insert(CreditPaidTradeFinancing record);

    int insertSelective(CreditPaidTradeFinancing record);

    CreditPaidTradeFinancing selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPaidTradeFinancing record);

    int updateByPrimaryKey(CreditPaidTradeFinancing record);
}