package com.creditharmony.approve.antifraud.dao;

import java.util.List;

import com.creditharmony.approve.antifraud.entity.AntifraudCase;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 反欺诈欺诈案件匹配信息dao
 * @Class Name AntifraudCaseDao
 * @author wanglidong
 * @Create In 2015年12月2日
 */
@LoanBatisDao
public interface AntifraudCaseDao extends CrudDao<AntifraudCase>{

	/**
     * 获取反欺诈欺诈案件匹配信息
     * 2015年12月2日
     * By wanglidong
     * @param id 关联id
     * @return
     */
    public List<AntifraudCase> getAntifraudCase(String id,String loanCode);   
}