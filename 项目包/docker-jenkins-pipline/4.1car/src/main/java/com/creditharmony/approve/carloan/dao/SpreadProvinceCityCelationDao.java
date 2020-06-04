package com.creditharmony.approve.carloan.dao;

import com.creditharmony.approve.carloan.entity.SpreadProvinceCityCelation;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 省市信息
 * @Class Name SpreadProvinceCityCelationDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */
@LoanBatisDao
public interface SpreadProvinceCityCelationDao {
 
	/**
	 * 保存信息
	 * 2016年2月19日
	 * By 李静辉
	 * @param record
	 * @return
	 */
    public int insert(SpreadProvinceCityCelation record);
}