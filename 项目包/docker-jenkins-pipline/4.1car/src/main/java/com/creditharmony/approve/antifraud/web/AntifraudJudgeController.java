package com.creditharmony.approve.antifraud.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.antifraud.entity.AntifraudJudge;
import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.antifraud.entity.BacklistAll;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeEx;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeOptionEx;
import com.creditharmony.approve.antifraud.entity.ex.BlackGraylistOptionEx;
import com.creditharmony.approve.antifraud.entity.ex.CoborrowerOptionEx;
import com.creditharmony.approve.antifraud.entity.ex.OutBlackEx;
import com.creditharmony.approve.antifraud.service.AntiFraudJudgeService;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.AfraudListStatus;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;

/**
 * 反欺诈判定Controller
 * @Class Name AutiFraudJudgeController
 * @author wanglidong
 * @Create In 2015年11月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/antifraud/antiFraudJudge")
public class AntifraudJudgeController extends BaseController {
	
	@Autowired
	private AntiFraudJudgeService antiFraudJudgeService;
	
	@Autowired
	private VerifyCommonService verifyCommonService;

	/**
	 * 到反欺诈决策  【判定】页面 
	 * 2016年5月20日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 决策初始化数据
	 */
	@RequestMapping(value = {"goForm"})
	public String goForm(Model model ,String loanCode){
		
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		
		AntiFraudJudgeEx antiFraudJudgeEx = antiFraudJudgeService.getAfraudJudgeInitData(loanCode);
		model.addAttribute("antiFraudJudgeEx", antiFraudJudgeEx);
		
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			return "/antifraud/antifraudJudgeForm";
		}
		// 判断为新版
		return "/antifraud/antifraudJudgeForm_new";
		
		
	}
	
	/**
	 * 到反欺诈决策  【查看】页面
	 * 2016年4月26日
	 * By wanglidong
	 * @param model
	 * @param loanCode
	 * @return
	 */
	@RequestMapping(value = {"goView"})
	public String goAutifraudJudgeView(Model model ,String loanCode){
		AntiFraudJudgeEx antiFraudJudgeEx = antiFraudJudgeService.getAfraudJudgeViewData(loanCode);
		model.addAttribute("antiFraudJudgeEx", antiFraudJudgeEx);
		return "/antifraud/antifraudJudgeView";
	}
	
	/**
	 * 【未决策】单子，判断是否显示【查看提报反欺诈原因按钮】
	 * 2016年2月1日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 提报类型
	 */
	@ResponseBody
	@RequestMapping(value = {"checkReportType"})
	public String checkReportType(Model model ,String loanCode){
		AntifraudReport antifraudReport = antiFraudJudgeService.getAntifraudReportType(loanCode);
		return antifraudReport.getDictCheckType();		
	}	
	
	/**
	 * 【已决策】单子，判断是否显示【查看提报反欺诈原因按钮】
	 * 2016年2月1日
	 * By wanglidong
	 * @param model
	 * @param loanCode 借款编码
	 * @return 提报类型
	 */
	@ResponseBody
	@RequestMapping(value = {"checkReportTypeView"})
	public String checkReportTypeView(Model model ,String loanCode){
		// 获取提报类型
		AntifraudReport antifraudReport = antiFraudJudgeService.getAntifraudReportTypeView(loanCode);
		String dictCheckType = null;
		if(antifraudReport != null){
			dictCheckType = antifraudReport.getDictCheckType();
		}else{
			logger.error("******************无迁移数据未获取到提报类型*******************fqz******");
		}
		return dictCheckType;
	}	
	
	/**
	 * 获取  提报反欺诈 信息
	 * 2016年1月5日
	 * By wanglidong
	 * @param model
	 * @param loanCode
	 * @return 打开提报原因窗口
	 * @throws Exception
	 */
	@RequestMapping(value = {"toAfraudReportView"})
	public String toAfraudReportView(Model model ,String loanCode){
		// 获取提报反欺诈信息
		AntifraudReport antifraudReport = antiFraudJudgeService.getAntifraudReport(loanCode);	
		if(antifraudReport != null){
			User user = UserUtils.get(antifraudReport.getMentionUserId());
			model.addAttribute("user", user);
		}else{
			logger.error("*****************无迁移数据未获取到提报反欺诈信息**********fqz***");
		}
		model.addAttribute("antifraudReport", antifraudReport);		
		return "/antifraud/antifraudReportView";
	}

	
	/**
	 * 获取 反欺诈内部加 黑项【操作】
	 * 2015年12月14日
	 * By wanglidong
	 * @param param 公用实体类
	 * @return	返回加黑加灰项
	 * @throws Exception
	 */
	@RequestMapping(value = {"getBlackListOption"})
	public String getBlackListOption(Model model,VerifyParamEx param) throws Exception {
		// 获取主借人内部加黑项
		AntiFraudJudgeOptionEx antifraudOption = antiFraudJudgeService.getAntifraudOption(param);
		// 获取共借人内部加黑项
		List<CoborrowerOptionEx> coborrowerOption = antiFraudJudgeService.getCoborrowerOption(param);
		model.addAttribute("antifraudOption", antifraudOption);
		model.addAttribute("coborrowerOption", coborrowerOption);
		return "/antifraud/insideBlackList";
	}
	
	/**
	 * 新版申请表add
	 * 获取 反欺诈内部加 黑项【操作】
	 * 2015年12月14日
	 * By wanglidong
	 * @param param 公用实体类
	 * @return	返回加黑加灰项
	 * @throws Exception
	 */
	@RequestMapping(value = {"getBlackListOptionNew"})
	public String getBlackListOptionNew(Model model,VerifyParamEx param) throws Exception {
		// 获取主借人内部加黑项
		AntiFraudJudgeOptionEx antifraudOption = antiFraudJudgeService.getAntifraudOption(param);
		// 获取共借人内部加黑项
		List<CoborrowerOptionEx> coborrowerOption = antiFraudJudgeService.getCoborrowerOption(param);
		model.addAttribute("antifraudOption", antifraudOption);
		model.addAttribute("coborrowerOption", coborrowerOption);
		return "/antifraud/insideBlackList_new";
	}
	
	/**
	 * 获取 反欺诈内部加 黑项【回显】
	 * 2016年1月11日
	 * By wanglidong
	 * @param param 公用实体类
	 * @param model
	 * @return 返回反欺诈内部加灰项
	 * @throws Exception
	 */
	@RequestMapping(value = {"getOldBlackListOption"})
	public String getOldBlackListOption(Model model,VerifyParamEx param){
		List<BlackGraylistOptionEx> coborrower = antiFraudJudgeService.getCoborrower(param);
		List<BacklistAll> oldBlackListOption = antiFraudJudgeService.getOldBlackListOption(param);
		model.addAttribute("oldBlackListOption", oldBlackListOption);
		model.addAttribute("coborrower", coborrower);
		return "/antifraud/insideOldBlackList";
	}
	
	/**
	 * 获取反欺诈 内部 加灰项 【操作】
	 * 2016年1月11日
	 * By wanglidong
	 * @param param 公用实体类
	 * @param model
	 * @return 返回反欺诈内部加灰项
	 * @throws Exception
	 */
	@RequestMapping(value = {"getGrayListOption"})
	public String getGrayListOption(Model model,VerifyParamEx param) throws Exception {
		// 获取主借人内部加灰项
		AntiFraudJudgeOptionEx antifraudOption = antiFraudJudgeService.getGrayListOption(param);
		// 获取共借人内部加灰项
		List<CoborrowerOptionEx> coborrowerOption = antiFraudJudgeService.getCoborrowerGrayOption(param);
		model.addAttribute("antifraudOption", antifraudOption);
		model.addAttribute("coborrowerOption", coborrowerOption);
		return "/antifraud/insideGrayList";
	}	
	
	/**
	 * 获取反欺诈 内部 加灰项 【回显】
	 * 2016年1月11日
	 * By wanglidong
	 * @param param 公用实体类
	 * @param model
	 * @return 返回反欺诈内部加灰项
	 * @throws Exception
	 */
	@RequestMapping(value = {"getOldGrayListOption"})
	public String getOldGrayListOption(Model model,VerifyParamEx param){
		List<BlackGraylistOptionEx> blackGraylistOptionEx = antiFraudJudgeService.getOldGrayListOption(param);
		List<BacklistAll> oldGrayListOption = antiFraudJudgeService.getOldGrayList(param);
		model.addAttribute("oldGrayListOption", oldGrayListOption);
		model.addAttribute("blackGraylistOptionEx", blackGraylistOptionEx);
		return "/antifraud/insideOldGrayList";
	}	
	
	/**
	 * 获取外部加黑项
	 * 2016年1月11日
	 * By wanglidong
	 * @param model
	 * @param param 公用实体类
	 * @return 返回外部加黑项
	 * @throws Exception
	 */
	@RequestMapping(value = {"getOutBlackList"})
	public String getOutBlackList(Model model,VerifyParamEx param){
		AntiFraudJudgeOptionEx antifraudOption = antiFraudJudgeService.getGrayListOption(param);
		model.addAttribute("antifraudOption", antifraudOption);
		return "/antifraud/insideGrayList";
	}	
	
	/**
	 * 决策时判断【规则是否全部解除】
	 * 2015年12月17日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return	成功返回true失败返回false
	 */
	@ResponseBody
	@RequestMapping(value = {"getRelieveStatus"})
	public  String getRelieveStatus(String loanCode){
		// 获取触发的规则
		List<String> relieveStatus = antiFraudJudgeService.getRelieveStatus(loanCode);
		if(ArrayHelper.isNotEmpty(relieveStatus)){
			for (int i = 0; i < relieveStatus.size(); i++) {
				// 如果有未解除的规则
				if(relieveStatus.get(i).equals(AfraudListStatus.NOT_LIFTED.getCode())){
					return BooleanType.FALSE; 
				}
			}
		}
		return BooleanType.TRUE;			
	}
	
	/**
	 * 保存【外部加黑灰】信息
	 * 2016年1月28日
	 * By wanglidong
	 * @param outBlackEx 外部黑名单扩展类
	 * @return 外部拉黑信息列表
	 */
	@ResponseBody
	@RequestMapping(value = {"saveOutBlackList"})
	public String saveOutBlackList(OutBlackEx outBlackEx) {
		List<BacklistAll> blackList = null;
		try {
			blackList = antiFraudJudgeService.saveOutBlackList(outBlackEx);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return jsonMapper.toJson(blackList);
	}
	
	/**
	 * 删除【外部拉黑】信息
	 * 2015年12月29日
	 * By wanglidong
	 * @param id 外部拉黑id
	 * @return 成功返回true失败返回false
	 */
	@ResponseBody
	@RequestMapping(value = {"delOutBlack"})
	public String delOutBlack(String id) {
		try {
			antiFraudJudgeService.delOutBlack(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}

	/**
	 * 判断【内部/外部】加黑灰信息在数据库中是否存在
	 * 2016年1月15日
	 * By wanglidong
	 * @param value 加黑灰内容
	 * @return 存在返回true，失败返回false
	 */
	@ResponseBody
	@RequestMapping(value = {"checkExists"})
	public String checkExists(String value) {
		int exist = antiFraudJudgeService.getCheckExists(value);
		// 如果外部加黑项已经在 返回false
		if(exist > 0){
			return BooleanType.TRUE;
		}
		return BooleanType.FALSE;
	}
	
	/**
	 * 点击【历史按钮】打开历史窗口，点击详细链接调用此方法，打开决策信息窗口
	 * 2016年1月28日
	 * By wanglidong
	 * @param relationId 关联id
	 * @param model
	 * @return 决策信息
	 */
	@RequestMapping(value = {"getJudgeHistory"})
	public String getJudgeHistory(String relationId,Model model) {
		AntifraudJudge judgeHistory = antiFraudJudgeService.getJudgeHistory(relationId);
		model.addAttribute("judgeHistory", judgeHistory);
		return "/antifraud/antifarudJudgeHistory";
	}

}
