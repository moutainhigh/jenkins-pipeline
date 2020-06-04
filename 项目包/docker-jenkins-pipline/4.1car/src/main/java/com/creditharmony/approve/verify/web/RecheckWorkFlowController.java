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
import com.creditharmony.approve.verify.entity.ex.InnerCheckEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.CheckService;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.approve.verify.util.SurveyUtil;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.constants.FieldConstants;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.constants.StepNameConstants;
import com.creditharmony.approve.workflow.constants.WorkFlowConstants;
import com.creditharmony.approve.workflow.entity.VerifyFlowTaskItemEntity;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.config.FlowInfoDefinitionConfig;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.ChkResult;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.query.ProcessQueryBuilder;

/**
 * 信审复审处理类
 * @Class Name RecheckWorkFlowController
 * @author xiaoniu.hu
 * @Create In 2016年1月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/recheck")
public class RecheckWorkFlowController extends ReviewController {
	@Autowired
	private CheckService firstVerifyService;
	@Autowired
	private VerifyCommonService verifyCommonService;
	
	public RecheckWorkFlowController() {
		super(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode(),
				StepNameConstants.VERIFY_RECHECK, FlowInfoDefinitionConfig
						.getInstance().getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_VERIFY_KEY)
						.getFlowName());
	}
	
	/**
	 * 重写获取待办,追加所在组织的机构编码的条件
	 */
	@Override
	protected String fetchTaskItems(Model model) {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.setFlowName(FlowInfoDefinitionConfig.getInstance().getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_VERIFY_KEY).getFlowName());
		queryParam.put(FieldConstants.STEP_NAME, StepNameConstants.VERIFY_RECHECK);
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		queryParam.put(FieldConstants.DEAL_USER_ORGID, user.getDepartment().getId());
		// 获取工作流任务
		FlowPage page=new FlowPage();
		page.setPageSize(30);
		flowService.fetchTaskItems(
				QueueConstants.VERIFY_CHECK, queryParam,page,
				Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_HUICHENG_ORDER_QUEUING,
				VerifyFlowTaskItemEntity.class);
		model.addAttribute("itemList", page.getList());
		return "task/taskList";		
	}


	/**
	 * 复审决策页签初始化
	 * 2015年12月24日
	 * xiaoniu.hu
	 * @param model
	 * @param param
	 * @return 返回到指定的页面
	 * @throws Exception 
	 */
	@RequestMapping(value="initPage")
	public String initPage(Model model,VerifyParamEx param) throws Exception{
		String loanCode = param.getLoanCode();
		List<GlRefuse> glRefuses = null;
		InnerCheckEx  checkView = null;
		if(!StringUtils.isBlank(loanCode)){
			param.setRelId(null);
			param.setType(null);
			checkView = firstVerifyService.getCheckInfo(param);	
			glRefuses = getRefuseCode(RefuseConstants.ROOT);
		}else{
			throw new Exception("借款编号为空");
		}		
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		// 历史归户信息概况显示信息  message
		model.addAttribute("glRefuses", glRefuses);
		model.addAttribute("message", SurveyUtil.getMessage(checkView.getCustomerHis()));		
		model.addAttribute("InnerCheck",checkView );
		model.addAttribute("newOrOldFlag", newOrOldFlag);
		return "verify/recheckInclude";
	}
	
	/**
	 * 复审决策
	 * 2015年12月24日
	 * xiaoniu.hu
	 * @param workItem
	 * @param loanApply
	 * @return 是否成功
	 */
	@RequestMapping(value="asynReCheckOperation")
	@ResponseBody
	public String asynReCheckOperation(WorkItemView workItem,VerifyBusinessView loanApply,String auditRulesCode){
		try {
			String result = loanApply.getAuditResultEx().getAuditResult();
			loanApply.getAuditResultEx().setAuditRulesCode(auditRulesCode);
			if(StringUtils.isNotEmpty(result)){
				if (ChkResult.THROUGH.getCode().equals(result)) { // 通过
					loanApply.setAuditAmount(loanApply.getAuditResultEx().getAuditAmount().doubleValue());
					// 获取字典查询的结果
					String temp = DictUtils.getDictLabel(ApproveConstants.RECHECK_MONEY,
							LendConstants.APPROVE_RESPONE_AMOUNT, null);
					BigDecimal money = null;
					if (temp == null) {
						throw new Exception("没有对应的额度");
					} else {// 把字典的结果转换为需要的类型
						money = new BigDecimal(temp);
					}
					BigDecimal auditAmount = loanApply.getAuditResultEx()
							.getAuditAmount();
					if (auditAmount.compareTo(money) <= 0) {
						workItem.setResponse(ApproveRouteConstants.RECHECK_TO_INTERESTRATE);
					} else {
						workItem.setResponse(ApproveRouteConstants.RECHECK_TO_GROUPCHECK);
					}
				} else if (ChkResult.REFUSE_TO_BORROW.getCode().equals(result)) { // 拒借
					workItem.setResponse(ApproveRouteConstants.RECHECK_TO_END);
				} else {
					return BooleanType.FALSE;
				}
				workItem.setBv(loanApply);
				flowService.dispatch(workItem);
				LoanInfo kinInfo = getkinInfo(loanApply.getAuditResultEx().getLoanCode());
				if (kinInfo != null && StringUtils.isNotEmpty(kinInfo.getKinnobuQuotaFlag())) {
					return kinInfo.getKinnobuQuotaFlag();
				}
			} else {
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
}
