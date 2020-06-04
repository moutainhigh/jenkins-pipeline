package com.creditharmony.approve.credit.dao;

import com.creditharmony.approve.credit.entity.CreditPaidLetterGuarantee;

public interface CreditPaidLetterGuaranteeDao {
    int deleteByPrimaryKey(String id);

    int insert(CreditPaidLetterGuarantee record);

    int insertSelective(CreditPaidLetterGuarantee record);

    CreditPaidLetterGuarantee selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPaidLetterGuarantee record);

    int updateByPrimaryKey(CreditPaidLetterGuarantee record);
}