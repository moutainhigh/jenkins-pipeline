package com.creditharmony.approve.rule.dao;

import java.util.Map;

import com.creditharmony.approve.rule.applyengine.client.CreditInfo;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 查询进件引擎所需的征信数据
 * @Class Name ApplyCreditInfoDao
 * @author 王浩
 * @Create In 2016年3月24日
 */
@LoanBatisDao
public interface ApplyCreditInfoDao {
	
	/**
	 * 查询借款申请的征信信息
	 * 2016年3月24日
	 * By 王浩
	 * @param params
	 * @return 返回CreditReport对象
	 */
	public CreditInfo getCreditInfo(Map<String, String> params);
	
	/**
	 * 查询借款申请的征信信息
	 * 2016年3月24日
	 * By 王浩
	 * @param params
	 * @return 返回CreditReport对象
	 */
	public CreditInfo getCreditInfoNew(Map<String, String> params);
	
	public CreditInfo testSQL(Map<String, String> params);
	
}