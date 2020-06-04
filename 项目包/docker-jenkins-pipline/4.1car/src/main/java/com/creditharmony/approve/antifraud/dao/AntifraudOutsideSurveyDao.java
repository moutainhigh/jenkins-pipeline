package com.creditharmony.approve.antifraud.dao;

import com.creditharmony.approve.antifraud.entity.AntifraudOutsideSurvey;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 外部调查 
 * @Class Name AntifraudOutsideSurveyDao
 * @author 赖敏
 * @Create In 2015年11月30日
 */
@LoanBatisDao
public interface AntifraudOutsideSurveyDao extends CrudDao<AntifraudOutsideSurvey>{

	/**
	 * 获取进件时间
	 * 2016年4月7日
	 * By wanglidong
	 * @param loancode 借款编码
	 */
	public String getIntoTime(String loanCode);
    
}