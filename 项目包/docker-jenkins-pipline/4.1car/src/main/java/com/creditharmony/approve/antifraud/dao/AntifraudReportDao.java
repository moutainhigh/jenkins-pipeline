package com.creditharmony.approve.antifraud.dao;

import java.util.List;

import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.entity.ex.AntiFraudResultEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 提报反欺诈
 * @Class Name AntifraudReportDao
 * @author 刘燕军
 * @Create In 2016年1月19日
 */
@LoanBatisDao
public interface AntifraudReportDao  extends CrudDao<AntifraudReport>{
    
	/**
	 * 插入提报反欺诈信息
	 * 2015年12月31日
	 * By 刘燕军
	 * @param record
	 * @return 插入几条信息
	 */
    public int insertSelective(AntifraudReport record);
   
    /**
     * 通过参数，获取指定的list集合
     * 2015年12月2日
     * By 刘燕军
     * @param param
     * @return 查询的list集合
     */
    public List<AntiFraudResultEx> findListByLoanCode(VerifyParamEx param);
   
    /**
     * 获取所有的提报反欺诈信息
     * 2015年12月31日
     * By 刘燕军
     * @param id
     * @return 对应的反欺诈信息
     */
    public AntifraudReport findAntifraudReport(String id);

    /**
     * 获取一级拒借码
     * 2016年4月29日
     * By wanglidong
     */
	public List<GlRefuse> getOneLevel();

	/**
	 * 获取二级拒借码
	 * 2016年4月29日
	 * By wanglidong
	 * @return
	 */
	public List<GlRefuse> getTwoLevel(String id);
}