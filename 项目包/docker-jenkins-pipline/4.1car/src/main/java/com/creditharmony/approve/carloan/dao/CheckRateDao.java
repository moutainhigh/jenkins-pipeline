package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.CheckRate;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CheckRateDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface CheckRateDao {
    int deleteByPrimaryKey(String loanCode);

    int insert(CheckRate record);

    int insertSelective(CheckRate record);

    CheckRate selectByPrimaryKey(String loanCode);

    int updateByPrimaryKeySelective(CheckRate record);

    int updateByPrimaryKey(CheckRate record);
}