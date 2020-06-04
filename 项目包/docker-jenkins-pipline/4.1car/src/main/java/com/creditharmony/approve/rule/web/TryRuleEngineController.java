package com.creditharmony.approve.rule.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.adapter.bean.in.crif.CrifOnlineInBean;
import com.creditharmony.adapter.bean.out.crif.CrifOnlineOutBean;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.approve.rule.applyengine.client.ApplyResult;
import com.creditharmony.approve.rule.applyengine.service.ApplyEngineService;
import com.creditharmony.approve.rule.applyengine.service.CrifApplyEngineService;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingParam;
import com.creditharmony.approve.rule.auditrating.service.AuditRatingService;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;

/**
 * 直接调用进件引擎(测试规则用)
 * @Class Name TryRuleEngineController
 * @author 王浩
 * @Create In 2016年4月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/rules/apply")
public class TryRuleEngineController extends BaseController{
	
	@Autowired
	private ApplyEngineService applyEngineServie;
	
	@Autowired
	private AuditRatingService auditRatingService;
	
	@Autowired 
	private LoanCustomerDao loanCustomerDao;
	
	@Autowired
	private CrifApplyEngineService crifApplyEngineServie;
	
	/**
	 * 进入页面
	 * 2016年4月7日
	 * By 王浩
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="goForm")
	public String initForm(Model model) throws Exception{
		User user = UserUtils.getUser();
		if (user == null) {
			throw new ServiceException("未登录系统！");
		} 
		return "/management/testApplyRule";
	}
	
	@RequestMapping(value="goRating")
	public String initRating(Model model) throws Exception{
		User user = UserUtils.getUser();
		if (user == null) {
			throw new ServiceException("未登录系统！");
		} 
		return "/management/testRating";
	}
	
	/**
	 * 调用进件引擎，拼接返回信息
	 * 2016年4月8日
	 * By 王浩
	 * @param applyCode 客户编号或者借款编号
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "getRuleResult", method = RequestMethod.POST)
	public String testApplyEngine(String applyCode) throws Exception {// 通过
		String rtnMsg = "";
		String loanCode = null;
		if (StringUtils.isNotEmpty(applyCode)) {
			if (applyCode.startsWith("JKKH")) {
				loanCode = loanCustomerDao.getLoanCodeByCustomer(applyCode);
			} else {
				loanCode = applyCode;
			}
		}
		
		if (StringUtils.isEmpty(loanCode)) {
			return "输入信息有误";
		} else {
			rtnMsg += "借款编号：" + loanCode + "<br>";
		}
		
		ApplyResult result = applyEngineServie.testApplyRule(loanCode);
		if (result != null) {
			rtnMsg += "决策引擎结果：" + result.getRuleResult() + "<br>";
			if (result.getRuleResult().equals(CheckResult.XS_SECOND_PASS.getName())) {
				rtnMsg += "评分分数：" + result.getFinalScore() + "<br>";
				rtnMsg += "风险等级：" + result.getRiskLevel() + "<br>";
			} else if (result.getRuleResult().equals(CheckResult.APPLY_ENGINE_BACK.getName())) {
				// 退回
				rtnMsg += "退回原因：" + applyEngineServie.removeSeparator(result.getReturnReason()) + "<br>";
			} else if (result.getRuleResult().equals(CheckResult.APPLY_ENGINE_REFUSAL.getName())) {
				// 拒借
				rtnMsg += "拒借码：" + applyEngineServie.removeSeparator(result.getRefuseCode()) + "<br>";
				// 评分大于0，可能是评分分数过低，所以显示出评分分数
				if (result.getFinalScore() > 0) {
					rtnMsg += "评分分数：" + result.getFinalScore() + "<br>";
				}				
			}
		} else {
			rtnMsg += "没有匹配的判定结果";
		}
		return rtnMsg;
	}
	
	/**
	 * 调用进件引擎，拼接返回信息
	 * 2016年4月8日
	 * By 王浩
	 * @param applyCode 客户编号或者借款编号
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "getAuditRating", method = RequestMethod.POST)
	public String testRating(String applyCode) throws Exception {// 通过
		String rtnMsg = "";
		String loanCode = null;
		if (StringUtils.isNotEmpty(applyCode)) {
			if (applyCode.startsWith("JKKH")) {
				loanCode = loanCustomerDao.getLoanCodeByCustomer(applyCode);
			} else {
				loanCode = applyCode;
			}
		}
		
		if (StringUtils.isEmpty(loanCode)) {
			return "输入信息有误";
		} else {
			rtnMsg += "借款编号：" + loanCode + "<br>";
		}
		
		AuditRatingParam result = auditRatingService.testVerifyScore(loanCode);
		if (result != null) {
			rtnMsg += "评分分数：" + result.getFinalScore() + "<br>";
			rtnMsg += "风险等级：" + result.getRateCheckResult() + "<br>";
			
		} else {
			rtnMsg += "没有匹配的判定结果";
		}
		return rtnMsg;
	}
	
	/**
	 * 调用进件引擎，拼接返回信息
	 * 2016年9月22日
	 * By 安艳东
	 * @param loanCode 借款编号
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "crifApplyEngine", method = RequestMethod.POST)
	public String crifApplyEngine(VerifyParamEx param) throws Exception {
		param.setLoanCode("JK2016080400000734");
		//新线程执行方法
		Thread thread = new Thread(){
			public void run(){
				System.out.println("Thread Running");
			}
		};
		thread.start();
		//联机输入接口主要信息
		CrifOnlineOutBean cob = crifApplyEngineServie.getOnlineCrifApplyEngine(param.getLoanCode());
		//根据返回结果进行返回到前端
		
		return "";
	}
}
