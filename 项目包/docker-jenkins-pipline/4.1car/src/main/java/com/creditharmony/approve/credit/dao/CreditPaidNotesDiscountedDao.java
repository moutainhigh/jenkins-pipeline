package com.creditharmony.approve.credit.dao;

import com.creditharmony.approve.credit.entity.CreditPaidNotesDiscounted;

public interface CreditPaidNotesDiscountedDao {
    int deleteByPrimaryKey(String id);

    int insert(CreditPaidNotesDiscounted record);

    int insertSelective(CreditPaidNotesDiscounted record);

    CreditPaidNotesDiscounted selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CreditPaidNotesDiscounted record);

    int updateByPrimaryKey(CreditPaidNotesDiscounted record);
}