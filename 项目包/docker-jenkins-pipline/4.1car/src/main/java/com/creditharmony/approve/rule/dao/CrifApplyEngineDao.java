package com.creditharmony.approve.rule.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.adapter.bean.in.crif.CrifOnlineCreditDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineCreditSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineLoanDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineLoanSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlinePersonalInBean;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 新进件引擎的数据模型的数据库操作dao
 * @Class Name CrifApplyEngineDao
 * @author 安艳东
 * @Create In 2016年9月22日
 */
@LoanBatisDao
public interface CrifApplyEngineDao {
	//获取联机接口主要信息
	public CrifOnlineInBean getOnlineCrifApplyEngine(Map<String, Object> map);
	//获取联机接口-个人信息-申请人信息
	public CrifOnlinePersonalInBean getProposerCrifApplyEngine(Map<String, Object> map);
	//获取联机接口-个人信息-保证人信息
	public CrifOnlinePersonalInBean getBailCrifApplyEngine(Map<String, Object> map);
	//获取联机接口-个人信息-配偶信息
	public CrifOnlinePersonalInBean getMateCrifApplyEngine(Map<String, Object> map);
	//联机输入接口-个人信息-简版贷款信息
	public List<CrifOnlineLoanSimpleInBean> getSimpleLoan(Map<String, Object> map);
	//联机输入接口-个人信息-简版信用卡信息
	public List<CrifOnlineCreditSimpleInBean> getSimpleCredit(Map<String, Object> map);
	//联机输入接口-个人信息-详版贷款信息
	public List<CrifOnlineLoanDetailInBean> getDetailLoan(Map<String, Object> map);
	//联机输入接口-个人信息-详版信用卡信息
	public List<CrifOnlineCreditDetailInBean> getDetailCredit(Map<String, Object> map);
	
}