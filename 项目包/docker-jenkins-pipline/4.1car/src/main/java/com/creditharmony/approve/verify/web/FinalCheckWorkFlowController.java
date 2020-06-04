package com.creditharmony.approve.verify.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditharmony.approve.base.web.ReviewController;
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
import com.query.ProcessQueryBuilder;

/**
 * 信审终审处理类
 * @Class Name FinalCheckWorkFlowController
 * @author 赖敏
 * @Create In 2015年12月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/finalCheck")
public class FinalCheckWorkFlowController extends ReviewController {
	@Autowired
	private CheckService checkService;
	@Autowired
	private VerifyCommonService verifyCommonService;
	
	public FinalCheckWorkFlowController() {
		super(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode(),
				StepNameConstants.VERIFY_FINAL_CHECK, FlowInfoDefinitionConfig
						.getInstance().getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_VERIFY_KEY)
						.getFlowName());
	}
	
	/**
	 * 重写获取待办
	 */
	@Override
	protected String fetchTaskItems(Model model){
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.setFlowName(FlowInfoDefinitionConfig
				.getInstance().getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_VERIFY_KEY)
				.getFlowName());
		queryParam.put(FieldConstants.STEP_NAME, StepNameConstants.VERIFY_FINAL_CHECK);
		// 获取工作流任务
		FlowPage page=new FlowPage();
		page.setPageSize(30);
		flowService.fetchTaskItems(
				QueueConstants.VERIFY_CHECK, queryParam,page,
				Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_OWNER_TASK_CONDITION,
				VerifyFlowTaskItemEntity.class);
		model.addAttribute("itemList", page.getList());
		return "task/taskList";	
	}
	
	/**
	 * 初始化终审决策
	 * 2015年12月24日
	 * By 赖敏
	 * @param model
	 * @param param
	 * @return 终审决策页面
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
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		model.addAttribute("newOrOldFlag", newOrOldFlag);
		// 历史归户信息概况显示信息  message
		model.addAttribute("glRefuses", glRefuses);
		model.addAttribute("message", SurveyUtil.getMessage(checkView.getCustomerHis()));		
		model.addAttribute("InnerCheck",checkView );
		return "/verify/finalCheckInclude";
	}
	

	/**
	 * 终审决策处理
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
		if(!StringUtils.isBlank(result)){
			if(ChkResult.THROUGH.getCode().equals(result)){ // 通过
				view.setAuditAmount(view.getAuditResultEx().getAuditAmount().doubleValue());
				workItem.setResponse(ApproveRouteConstants.FINALCHECK_TO_PASS);
			}else if(ChkResult.REFUSE_TO_BORROW.getCode().equals(result)){ // 拒借
				workItem.setResponse(ApproveRouteConstants.FINALCHECK_TO_END);
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
		return BooleanType.TRUE; 
	}
}
