package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.CustomerConsultation;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CustomerConsultationDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CustomerConsultationDao {
    int deleteByPrimaryKey(String id);

    int insert(CustomerConsultation record);

    int insertSelective(CustomerConsultation record);

    CustomerConsultation selectByPrimaryKey(String id);
    
    CustomerConsultation selectByLoanCode(String loanCode);

    int updateByPrimaryKeySelective(CustomerConsultation record);

    int updateByPrimaryKey(CustomerConsultation record);
}