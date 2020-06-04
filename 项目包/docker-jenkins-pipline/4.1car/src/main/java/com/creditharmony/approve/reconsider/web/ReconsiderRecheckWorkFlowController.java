package com.creditharmony.approve.reconsider.web;

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
 * 复议复审处理类
 * @Class Name ReconsiderRecheckWorkFlowController
 * @author xiaoniu.hu
 * @Create In 2016年1月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/reconsider/recheck")
public class ReconsiderRecheckWorkFlowController extends ReviewController {
	@Autowired
	private CheckService checkService;	
	@Autowired
	private VerifyCommonService verifyCommonService;

	public ReconsiderRecheckWorkFlowController() {
		super(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode(),
				StepNameConstants.RECONSIDER_RECHECK, FlowInfoDefinitionConfig
						.getInstance()
						.getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_RECONSIDER_KEY)
						.getFlowName());
	}
	
	/**
	 * 重写获取待办,追加所在组织的机构编码的条件
	 *//*
	@Override
	@RequestMapping(value="fetchTaskItems")
	protected String fetchTaskItems(Model model) {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.setFlowName(FlowInfoDefinitionConfig.getInstance()
						.getFlowInfoConfigBeanByCode(WorkFlowConstants.FLOWNAME_RECONSIDER_KEY)
						.getFlowName());
		queryParam.put(FieldConstants.STEP_NAME, StepNameConstants.RECONSIDER_RECHECK);
		// 获取工作流任务
		FlowPage page=new FlowPage();
		page.setPageSize(30);
		flowService.fetchTaskItems(
				QueueConstants.VERIFY_CHECK, queryParam,page,
				Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_HUICHENG_ORDER_QUEUING,
				VerifyFlowTaskItemEntity.class);
		model.addAttribute("itemList", page.getList());
		return "task/taskList";		
	}*/

	/**
	 * 准备复议复审决策页面所需数据
	 * 2015年12月22日
	 * By 王浩
	 * @param model
	 * @param param
	 * @return 返回页面数据
	 * @throws Exception 
	 */
	@RequestMapping(value = "initPage")
	public String initPage(Model model, VerifyParamEx param) throws Exception {
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
		// 历史归户信息概况显示信息  message
		model.addAttribute("glRefuses", glRefuses);
		model.addAttribute("message", SurveyUtil.getMessage(checkView.getCustomerHis()));		
		model.addAttribute("InnerCheck",checkView );
		model.addAttribute("newOrOldFlag", newOrOldFlag);
		return "/reconsider/recheckInclude";
	}
	
	/**
	 * 保存复议复审决策
	 * 2015年12月23日
	 * By 王浩
	 * @param workItem
	 * @param view
	 * @return 返回是否保存成功
	 */
	@ResponseBody
	@RequestMapping(value="saveRecheckResult")
	public String saveAuditResult(WorkItemView workItem, VerifyBusinessView view,String auditRulesCode) {
		AuditResultEx auditResultEx = view.getAuditResultEx();
		// 决策结果
		String result = auditResultEx.getAuditResult();
		auditResultEx.setAuditRulesCode(auditRulesCode);
		try {
			if (StringUtils.isNotEmpty(result)) {
				if (ChkResult.THROUGH.getCode().equals(result)) { // 通过
					view.setAuditAmount(view.getAuditResultEx().getAuditAmount().doubleValue());
					String temp = DictUtils.getDictLabel(ApproveConstants.RECHECK_MONEY,LendConstants.APPROVE_RESPONE_AMOUNT,null);
					BigDecimal money = null;
					if(temp == null){
						throw new Exception("没有对应的额度");						
					}else{// 把字典的结果转换为需要的类型
						money = new BigDecimal(temp);
					}
					BigDecimal auditAmount = auditResultEx.getAuditAmount();
					if (auditAmount.compareTo(money) <= 0) {
						// 小于等于15万，复议复审提交至审核利率
						workItem.setResponse(ApproveRouteConstants.RECHECK_TO_INTERESTRATE);
					} else {
						// 大于15万，复审提交到复议终审
						workItem.setResponse(ApproveRouteConstants.RECHECK_TO_FINALCHECK);
					}
				} else if (ChkResult.REFUSE_TO_BORROW.getCode().equals(result)) { // 拒借
					workItem.setResponse(ApproveRouteConstants.CHECK_TO_END);
				}
				workItem.setBv(view);
				flowService.dispatch(workItem);
				LoanInfo kinInfo = getkinInfo(auditResultEx.getLoanCode());
				if(kinInfo != null && StringUtils.isNotEmpty(kinInfo.getKinnobuQuotaFlag())){
					return kinInfo.getKinnobuQuotaFlag();
				}
			} else {
				return BooleanType.FALSE;
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		return BooleanType.TRUE;
	}	
	
	/**
	 * 根据父节点Id,获取下一级别的拒绝码
	 * 2015年12月24日
	 * By 王浩
	 * @param parentId 父节点Id
	 * @return 返回拒借码list
	 */
	@ResponseBody
	@RequestMapping(value = "getRefuseCode")
	public List<GlRefuse> getRefuseCode(String parentId) {
		return super.getRefuseCode(parentId);
	}

	
}
