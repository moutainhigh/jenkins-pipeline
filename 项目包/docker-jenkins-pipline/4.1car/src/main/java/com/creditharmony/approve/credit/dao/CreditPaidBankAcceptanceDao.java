package com.creditharmony.approve.credit.dao;

import com.creditharmony.approve.credit.entity.CreditPaidBankAcceptance;

public interface CreditPaidBankAcceptanceDao {
    int deleteByPrimaryKey(String id);

    int insert(CreditPaidBankAcceptance record);

    int insertSelective(CreditPaidBankAcceptance record);

    CreditPaidBankAcceptance selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPaidBankAcceptance record);

    int updateByPrimaryKey(CreditPaidBankAcceptance record);
}