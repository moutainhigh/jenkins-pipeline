package com.creditharmony.approve.antifraud.web;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.antifraud.constants.AntifraudJudgeConstants;
import com.creditharmony.approve.antifraud.entity.AntifraudCase;
import com.creditharmony.approve.antifraud.entity.AntifraudOffendSales;
import com.creditharmony.approve.antifraud.entity.AntifraudRepeat;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudBlacklistEx;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudOffendInfoEx;
import com.creditharmony.approve.antifraud.service.AntiFraudTriggerRuleService;
import com.creditharmony.core.approve.type.AfraudListStatus;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.web.BaseController;

/**
 * 反欺诈触发规则controller
 * @Class Name AntiFraudTriggerRule
 * @author wanglidong
 * @Create In 2015年11月29日
 */
@Controller
@RequestMapping(value = "${adminPath}/antifraud/triggerRule")
public class AntifraudTriggerRuleController extends BaseController {
	
	@Autowired
	private AntiFraudTriggerRuleService antiFraudTriggerRuleService;
	
	/**
	 * 跳转到触发规则【操作】页面
	 * 2015年11月29日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 跳转到触发规则页面
	 */
	@RequestMapping(value = {"goForm"})
	public String goForm(Model model,String loanCode) {	
		// 获取查重匹配内容列表
		List<AntifraudOffendInfoEx> checkRepeatInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoCheckRepeat(loanCode);
		// 获取销售人员匹配内容列表
		List<AntifraudOffendInfoEx> saleInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoSaleInfo(loanCode);
		// 获取黑名单匹配内容列表
		List<AntifraudOffendInfoEx> blackListInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoBlackList(loanCode);
		// 获取欺诈案件匹配内容列表
		List<AntifraudOffendInfoEx> judgeCaseInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoJudgeCase(loanCode);
		model.addAttribute("checkRepeatInfo", checkRepeatInfo);
		model.addAttribute("saleInfo", saleInfo);
		model.addAttribute("blackListInfo", blackListInfo);
		model.addAttribute("judgeCaseInfo", judgeCaseInfo);
		return "/antifraud/antifraudTriggerRuleForm";
	}
	
	/**
	 * 跳转到触发规则【查看】页面
	 * 2016年1月4日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 跳转到触发规则查看页面
	 */
	@RequestMapping(value = {"goView"})
	public String goView(Model model,String loanCode) {	
		// 获取查重匹配内容列表
		List<AntifraudOffendInfoEx> checkRepeatInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoCheckRepeat(loanCode);
		// 获取销售人员匹配内容列表
		List<AntifraudOffendInfoEx> saleInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoSaleInfo(loanCode);
		// 获取黑名单匹配内容列表
		List<AntifraudOffendInfoEx> blackListInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoBlackList(loanCode);
		// 获取欺诈案件匹配内容列表
		List<AntifraudOffendInfoEx> judgeCaseInfo = antiFraudTriggerRuleService.getAntifraudOffendInfoJudgeCase(loanCode);
		model.addAttribute("checkRepeatInfo", checkRepeatInfo);
		model.addAttribute("saleInfo", saleInfo);
		model.addAttribute("blackListInfo", blackListInfo);
		model.addAttribute("judgeCaseInfo", judgeCaseInfo);
		return "/antifraud/antifraudTriggerRuleView";
	}
	
	/**
	 * 获取触发规则四部分的【查重】列表
	 * 2015年12月2日
	 * By wanglidong
	 * @param model
	 * @param rid 关联id
	 * @return 查重集合
	 */
	@RequestMapping(value = {"findCheckRepeatInfo"})
	public String findCheckRepeatInfo(Model model,String rid,String loanCode,String partFlag) {
		String url=null;
		// 查重
		if(AntifraudJudgeConstants.ANTIFRAUD_REPEAT.equals(partFlag)){
			List<AntifraudRepeat> checkRepeatInfo = antiFraudTriggerRuleService.getAntifraudRepeat(rid,loanCode);
			model.addAttribute("checkRepeatInfo", checkRepeatInfo);
			url = "/antifraud/triggerRuleRepeat";
		// 销售查重
		}else if(AntifraudJudgeConstants.ANTIFRAUD_SALES.equals(partFlag)){
			List<AntifraudOffendSales> saleInfo = antiFraudTriggerRuleService.getSaleInfo(rid,loanCode);
			model.addAttribute("saleInfo", saleInfo);
			url = "/antifraud/salesRepeat";			
		// 黑名单查重
		}else if(AntifraudJudgeConstants.ANTIFRAUD_BLACK.equals(partFlag)){
			List<AntifraudBlacklistEx> blackListInfo = antiFraudTriggerRuleService.getBlackListInfo(rid,loanCode);
			model.addAttribute("blackListInfo", blackListInfo);
			url = "/antifraud/blackListRepeat";		
		// 欺诈案件
		}else if(AntifraudJudgeConstants.ANTIFRAUD_CASE.equals(partFlag)){
			List<AntifraudCase> judgeCaseInfo = antiFraudTriggerRuleService.getJudgeCaseInfo(rid,loanCode);
			model.addAttribute("judgeCaseInfo", judgeCaseInfo);
			url = "/antifraud/caseRepeat";			
		}
		return url;
	}

	/**
	 * 修改触发规则解除状态
	 * 2015年12月3日
	 * By wanglidong
	 * @param model
	 * @param rid 关联id
	 * @param status 解除状态
	 * @return 解除状态
	 */
	@ResponseBody
	@RequestMapping(value = {"updateRrelieveStatus"})
	public String updateRrelieveStatus(Model model,String rid,String status,String remark){
		try {
			// 将状态改为未解除
			if(AfraudListStatus.IS_LIFTED.getCode().equals(status)){
				antiFraudTriggerRuleService.updateRrelieveStatus(rid,AfraudListStatus.NOT_LIFTED.getCode(),remark);
			// 将状态改为已解除
			}else{
				antiFraudTriggerRuleService.updateRrelieveStatus(rid,AfraudListStatus.IS_LIFTED.getCode(),remark);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}		
	
	/**
	 * 全部解除/释放
	 * 2015年12月7日
	 * By wanglidong
	 * @param ridAll关联id
	 * @param remark 解除理由
	 * @param reliveStatus 解除状态
	 * @return 解除状态
	 */
	@ResponseBody
	@RequestMapping(value = {"updateRemarkAll"})
	public String updateRemarkAll( String ridAll,String remark,String status) throws IOException {
		try {
			// 解除
			if(AfraudListStatus.NOT_LIFTED.getCode().equals(status)){
				antiFraudTriggerRuleService.updateRemarkAll(ridAll,remark,AfraudListStatus.IS_LIFTED.getCode());
			// 还原
			}else{
				antiFraudTriggerRuleService.updateRemarkAll(ridAll,remark,AfraudListStatus.NOT_LIFTED.getCode());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE; 
		}
		return BooleanType.TRUE;
	}	
	
	/**
	 * 获取applyid
	 * 2016年1月12日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return applyid
	 */
	@ResponseBody
	@RequestMapping(value = {"getApplyid"})
	public Map<String, String> getApplyid(String loanCode) {
		Map<String, String> map = new HashMap<String, String>();
		String applyid = antiFraudTriggerRuleService.getApplyid(loanCode);
		map.put("applyid", applyid);
		return map;
	}
	
}












