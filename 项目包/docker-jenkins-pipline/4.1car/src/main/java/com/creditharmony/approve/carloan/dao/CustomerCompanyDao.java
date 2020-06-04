package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.CustomerCompany;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CustomerCompanyDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CustomerCompanyDao {
    int deleteByPrimaryKey(String loanCode);

    int insert(CustomerCompany record);

    int insertSelective(CustomerCompany record);

    CustomerCompany selectByPrimaryKey(String loanCode);

    int updateByPrimaryKeySelective(CustomerCompany record);

    int updateByPrimaryKey(CustomerCompany record);
}