package com.creditharmony.approve.rule.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.adapter.bean.in.crif.CrifFrontCreditDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontCreditSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontLoanDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontLoanSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontPersonalInBean;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 前置策略接口的数据库操作dao
 * @Class Name CrifFrontRuleEngineDao
 * @author 王艳娜
 * @Create In 2016年9月23日
 */
@LoanBatisDao
public interface CrifFrontRuleEngineDao {

	/**
	 * 查询前置策略数据
	 */
	public CrifFrontInBean getCrifFrontInfo(Map<String,Object> map);
	
	/**
	 * 查询前置策略-个人信息-保证人 
	 */
	public CrifFrontPersonalInBean getCrifFrontGuarantorInfo(Map<String,Object> map);
	
	/**
	 * 前置策略-个人信息-简版贷款信息
	 */
	public List<CrifFrontLoanSimpleInBean> getCrifFrontShortLoanInfo(Map<String,Object> map);
	
	/**
	 * 前置策略-个人信息-简版信用卡信息 
	 */
	public List<CrifFrontCreditSimpleInBean> getCrifFrontShortCreditCardInfo(Map<String,Object> map);
	
	/**
	 * 前置策略-个人信息-详版贷款信息
	 */
	public List<CrifFrontLoanDetailInBean> getCrifFrontDetailLoanInfo(Map<String, Object> map);
	
	/**
	 * 前置策略-个人信息-详版信用卡信息
	 */
	public List<CrifFrontCreditDetailInBean> getCrifFrontDetailCreditCardInfo(Map<String, Object> map);	
	
	/**
	 * 前置策略-个人信息-配偶
	 */
	public CrifFrontPersonalInBean getCrifFrontMateInfo(Map<String, Object> map);
	
	/**
	 *  前置策略-个人信息-申请人
	 */
	public CrifFrontPersonalInBean getCrifFrontAppInfo(Map<String, Object> map);
}