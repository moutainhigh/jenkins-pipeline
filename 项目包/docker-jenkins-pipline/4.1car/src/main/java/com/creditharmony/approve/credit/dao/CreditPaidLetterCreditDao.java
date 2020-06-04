package com.creditharmony.approve.credit.dao;

import com.creditharmony.approve.credit.entity.CreditPaidLetterCredit;

public interface CreditPaidLetterCreditDao {
    int deleteByPrimaryKey(String id);

    int insert(CreditPaidLetterCredit record);

    int insertSelective(CreditPaidLetterCredit record);

    CreditPaidLetterCredit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPaidLetterCredit record);

    int updateByPrimaryKey(CreditPaidLetterCredit record);
}