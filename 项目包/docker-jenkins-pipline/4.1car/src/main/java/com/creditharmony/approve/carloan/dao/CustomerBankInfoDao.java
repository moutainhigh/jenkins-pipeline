package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.CustomerBankInfo;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CustomerBankInfoDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CustomerBankInfoDao {
    int deleteByPrimaryKey(String loanCode);

    int insert(CustomerBankInfo record);

    int insertSelective(CustomerBankInfo record);

    CustomerBankInfo selectByPrimaryKey(String loanCode);

    int updateByPrimaryKeySelective(CustomerBankInfo record);

    int updateByPrimaryKey(CustomerBankInfo record);
}