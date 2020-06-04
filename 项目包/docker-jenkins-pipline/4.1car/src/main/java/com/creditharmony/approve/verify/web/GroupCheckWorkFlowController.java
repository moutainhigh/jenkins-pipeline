package com.creditharmony.approve.verify.web;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditharmony.approve.base.web.ReviewController;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.RefuseConstants;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.verify.entity.ex.InnerCheckEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.CheckService;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.approve.verify.util.SurveyUtil;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.constants.StepNameConstants;
import com.creditharmony.approve.workflow.constants.WorkFlowConstants;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.config.FlowInfoDefinitionConfig;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.ChkResult;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.lend.type.LendConstants;

/**
 * 信审终审组处理类
 * @Class Name GroupCheckWorkFlowController
 * @author 李文勇
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/groupCheck")
public class GroupCheckWorkFlowController extends ReviewController {
	@Autowired
	private CheckService checkService;
	@Autowired
	private VerifyCommonService verifyCommonService;
	
	public GroupCheckWorkFlowController() {
		super(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode(),
				StepNameConstants.VERIFY_GROUP_CHECK, FlowInfoDefinitionConfig
						.getInstance().getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_VERIFY_KEY)
						.getFlowName());
	}
	
	
	/**
	 * 终审组决策初始化
	 * 2015年12月24日
	 * By 赖敏
	 * @param model
	 * @param param
	 * @return 终审组决策初始化页面
	 * @throws Exception
	 */
	@RequestMapping(value="initPage")
	public String initPage(Model model, VerifyParamEx param) throws Exception{
		String loanCode = param.getLoanCode();
		List<GlRefuse> glRefuses = null;
		InnerCheckEx  checkView = null;
		param.setRelId(null);
		if(!StringUtils.isBlank(loanCode)){
			param.setRelId(null);
			param.setType(null);
			checkView = checkService.getCheckInfo(param);	
			glRefuses = getRefuseCode(RefuseConstants.ROOT);
		}else{
			throw new Exception("借款编号为空");
		}		
		// 历史归户信息概况显示信息  message
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		model.addAttribute("newOrOldFlag", newOrOldFlag);
		model.addAttribute("glRefuses", glRefuses);
		model.addAttribute("message", SurveyUtil.getMessage(checkView.getCustomerHis()));		
		model.addAttribute("InnerCheck",checkView );
		return "/verify/groupCheckInclude";
	}
	
	/**
	 * 终审组决策处理
	 * 2015年12月24日
	 * By 赖敏
	 * @param workItem
	 * @param view
	 * @return 成功返回 true,失败返回false
	 */
	@ResponseBody
	@RequestMapping(value="asynGroupCheckHandle")
	public String asynGroupCheckHandle(WorkItemView workItem,VerifyBusinessView view,String auditRulesCode){
		AuditResultEx auditResultEx = view.getAuditResultEx();
		// 决策结果
		String result = auditResultEx.getAuditResult();
		auditResultEx.setAuditRulesCode(auditRulesCode);
		try {
			if(StringUtils.isNotEmpty(result)){
				if(ChkResult.THROUGH.getCode().equals(result)){ // 通过
					view.setAuditAmount(view.getAuditResultEx().getAuditAmount().doubleValue());
					String temp = DictUtils.getDictLabel(ApproveConstants.GROUPCHECK_MONEY,LendConstants.APPROVE_RESPONE_AMOUNT,null);
					BigDecimal money = null;
					if(temp == null){
						throw new Exception("没有对应的额度");						
					}else{// 把字典的结果转换为需要的类型
						money = new BigDecimal(temp);
					}
					BigDecimal auditAmount = auditResultEx.getAuditAmount();
					if(auditAmount.compareTo(money) <= 0){ // 小于30万，终审组提交至审核利率
						workItem.setResponse(ApproveRouteConstants.GROUPCHECK_TO_INTERESTRATE);
					}else{ // 大于等于30万，终审组提交到终审
						workItem.setResponse(ApproveRouteConstants.GROUPCHECK_TO_FINALCHECK);
					}
				}else if(ChkResult.REFUSE_TO_BORROW.getCode().equals(result)){ // 拒借
					workItem.setResponse(ApproveRouteConstants.GROUPCHECK_TO_END);
				}
				workItem.setBv(view);
				flowService.dispatch(workItem);
				LoanInfo kinInfo = getkinInfo(auditResultEx.getLoanCode());
				if(kinInfo != null && StringUtils.isNotEmpty(kinInfo.getKinnobuQuotaFlag())){
					return kinInfo.getKinnobuQuotaFlag();
				}
			}else{
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return BooleanType.TRUE; 
	}
	
}
