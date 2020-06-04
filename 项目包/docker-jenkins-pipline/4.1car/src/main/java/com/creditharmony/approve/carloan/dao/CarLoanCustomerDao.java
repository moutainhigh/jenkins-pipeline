package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.LoanCustomer;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CarLoanCustomerDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CarLoanCustomerDao {
    int deleteByPrimaryKey(String customerCode);

    int insert(LoanCustomer record);

    int insertSelective(LoanCustomer record);

    LoanCustomer selectByPrimaryKey(String customerCode);

    int updateByPrimaryKeySelective(LoanCustomer record);

    int updateByPrimaryKey(LoanCustomer record);
}