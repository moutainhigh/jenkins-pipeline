package com.creditharmony.approve.rule.applyengine.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineCreditDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineCreditSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineLoanDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlineLoanSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifOnlinePersonalInBean;
import com.creditharmony.adapter.bean.out.crif.CrifOnlineOutBean;
import com.creditharmony.adapter.bean.out.crif.CrifPersonalReasonDetailOutBean;
import com.creditharmony.adapter.bean.out.crif.CrifReasonDetailOutBean;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.approve.base.service.ApproveCommonService;
import com.creditharmony.approve.common.util.DateUtils;
import com.creditharmony.approve.rule.dao.CrifApplyEngineDao;
import com.creditharmony.approve.rule.dao.CrifOnlineDao;
import com.creditharmony.approve.rule.entity.CrifOnline;
/**
 * 调用新进件引擎进行进件引擎判定是否可以借款
 * @Class Name CrifApplyEngineService
 * @author 安艳东
 * @Create In 2016-09-22
 */
@Service
public class CrifApplyEngineService extends ApproveCommonService {
	
	@Autowired
	private CrifApplyEngineDao crifApplyEngineDao;
	@Autowired
	private CrifOnlineDao crifOnlineDao;

	/*
	 * 联机输入接口主要信息
	 */
	public CrifOnlineOutBean getOnlineCrifApplyEngine(String loanCode){

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loanCode", loanCode);

		List<CrifOnlinePersonalInBean> personalInfos = new ArrayList<CrifOnlinePersonalInBean>();
		//联机输入接口主要信息
		CrifOnlineInBean cob = crifApplyEngineDao.getOnlineCrifApplyEngine(map);
		//联机输入接口-个人信息-配偶
		CrifOnlinePersonalInBean mate = crifApplyEngineDao.getMateCrifApplyEngine(map); 
		if(null != mate){
			personalInfos.add(mate);
		}
		//联机输入接口-个人信息-申请人
		map.put("recentlyMonths3", DateUtils.addMonth(DateUtils.getDate(new Date()), -3));
		map.put("recentlyMonths6", DateUtils.addMonth(DateUtils.getDate(new Date()), -6));
		CrifOnlinePersonalInBean proposer = crifApplyEngineDao.getProposerCrifApplyEngine(map); 
		map.put("customer_type", "0");
		//联机输入接口-个人信息-申请人-简版贷款信息
		List<CrifOnlineLoanSimpleInBean> loanSimpleList = crifApplyEngineDao.getSimpleLoan(map);
		if(null != loanSimpleList && loanSimpleList.size() > 0){
			proposer.setLoanSimpleList(loanSimpleList);
		}
		//联机输入接口-个人信息-申请人-简版信用卡信息
		List<CrifOnlineCreditSimpleInBean> creditSimpleList = crifApplyEngineDao.getSimpleCredit(map);
		if(null != creditSimpleList && creditSimpleList.size() > 0){
			proposer.setCreditSimpleList(creditSimpleList);
		}
		//联机输入接口-个人信息-申请人-详版贷款信息
		List<CrifOnlineLoanDetailInBean> loanDetailList = crifApplyEngineDao.getDetailLoan(map);
		if(null != loanDetailList && loanDetailList.size() > 0){
			proposer.setLoanDetailList(loanDetailList);
		}
		//联机输入接口-个人信息-申请人-详版信用卡信息
		List<CrifOnlineCreditDetailInBean> creditDetailList = crifApplyEngineDao.getDetailCredit(map);
		if(null != creditDetailList && creditDetailList.size() > 0){
			proposer.setCreditDetailList(creditDetailList);
		}
		personalInfos.add(proposer);
		
		//联机输入接口-个人信息-保证人
		CrifOnlinePersonalInBean bail = crifApplyEngineDao.getBailCrifApplyEngine(map);
		if(null != bail){
			map.put("customer_type", "1");
			//联机输入接口-个人信息-保证人-简版贷款信息
			List<CrifOnlineLoanSimpleInBean> bailLoanSimpleList = crifApplyEngineDao.getSimpleLoan(map);
			if(null != bailLoanSimpleList && bailLoanSimpleList.size() > 0){
				bail.setLoanSimpleList(bailLoanSimpleList);
			}
			//联机输入接口-个人信息-保证人-简版信用卡信息
			List<CrifOnlineCreditSimpleInBean> bailCreditSimpleList = crifApplyEngineDao.getSimpleCredit(map);
			if(null != bailCreditSimpleList && bailCreditSimpleList.size() > 0){
				bail.setCreditSimpleList(bailCreditSimpleList);
			}
			//联机输入接口-个人信息-保证人-详版贷款信息
			List<CrifOnlineLoanDetailInBean> bailLoanDetailList = crifApplyEngineDao.getDetailLoan(map);
			if(null != bailLoanDetailList && bailLoanDetailList.size() > 0){
				bail.setLoanDetailList(bailLoanDetailList);
			}
			//联机输入接口-个人信息-保证人-详版信用卡信息
			List<CrifOnlineCreditDetailInBean> bailCreditDetailList = crifApplyEngineDao.getDetailCredit(map);
			if(null != bailCreditDetailList && bailCreditDetailList.size() > 0){
				bail.setCreditDetailList(bailCreditDetailList);
			}
			personalInfos.add(bail);
		}
		cob.setPersonalInfos(personalInfos);
		//调用CRIF接口
		ClientPoxy service = new ClientPoxy(ServiceType.Type.CRIF_ONLINE_SERVICE);
		CrifOnlineOutBean clob = (CrifOnlineOutBean)service.callService(cob);
		//根据返回信息进行保存
		if(null != clob){
			CrifOnline co = new CrifOnline();
			co.setRetCode(clob.getRetCode());
			co.setRetMsg(clob.getRetMsg());
			co.setDecision(clob.getDecision());
			co.setRate(clob.getRate());
			co.setRiskLevel(clob.getRiskLevel());
			co.setQuota(clob.getLimit());
			co.setScore(clob.getScore());
			JSONArray json = new JSONArray();
			List<CrifPersonalReasonDetailOutBean> personals = clob.getPersonals();
			if(null != personals){
	            for(CrifPersonalReasonDetailOutBean a : personals){
	            	if(null !=  a.getItems()){
		            	for(CrifReasonDetailOutBean b : a.getItems()){
			                JSONObject jo = new JSONObject();
			                jo.put("reasonCode", b.getReasonCode());
			                jo.put("reasonDetail", b.getReasonDetail());
			                json.add(jo);
		            	}
	            	}
	            }
	            co.setItems(json.toString());
			}
            co.preInsert();
			crifOnlineDao.insert(co);
		}
		return clob;
	}
}