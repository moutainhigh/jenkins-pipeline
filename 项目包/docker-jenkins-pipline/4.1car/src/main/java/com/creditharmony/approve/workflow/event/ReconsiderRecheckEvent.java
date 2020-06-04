package com.creditharmony.approve.workflow.event;


import org.springframework.stereotype.Service;

import com.creditharmony.approve.base.service.ApproveCommonService;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.loan.type.LoanApplyStatus;

/**
 * 复议复审处理流程回调
 * @Class Name VerifyGroupCheckEvent
 * @author 王浩
 * @Create In 2015年12月25日
 */
@Service("Reconsider_Recheck_Flow")
public class ReconsiderRecheckEvent extends ApproveCommonService implements ExEvent{
	/**
	 * 继承父类方法 
	 * (non-Javadoc)
	 * @see com.creditharmony.bpm.frame.face.ExEvent#invoke(com.creditharmony.bpm.frame.view.WorkItemView)
	 */
	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		String response = workItem.getResponse();
		// 取出在controller 中放入的业务数据，需要向下转换
		VerifyBusinessView flowView = (VerifyBusinessView) workItem.getBv();
		String loanCode = flowView.getLoanCode();
		flowView.setOutApproveTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		String step = workItem.getStepName();
		if (response.equals(ApproveRouteConstants.RECHECK_TO_INTERESTRATE)) { 
			flowView.setLoanApplyStatus(LoanApplyStatus.RATE_TO_VERIFY.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RATE_TO_VERIFY.getCode());
			flowView.setCheckResult(CheckResult.FY_SECOND_PASS.getCode());
			flowView.setFlag(true);
			flowView.setStepName(step);
			// 利率审核
			String id = super.fromCheck(flowView);
			LoanInfo kinFlg = checkKinnobu(flowView);
			finalUpdate(id,flowView,kinFlg);
			if(kinFlg != null){
				flowView.setLoanFlag(kinFlg.getLoanFlag());// 出借标识 
				flowView.setLoanFlagName(kinFlg.getLoanFlagName());// 出借标识名称 
				flowView.setModel(kinFlg.getModel());// 模型
				workItem.setBv(flowView);
			}
			updateReconsiderApply(loanCode,LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode());
			updateLoanTime(loanCode);
		} else if (response.equals(ApproveRouteConstants.RECHECK_TO_FINALCHECK)) { 
			// 大于15万，复审提交到终审
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_RECHECK_COUSULT_FINAL_CHECK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_RECHECK_COUSULT_FINAL_CHECK.getCode());
			flowView.setCheckResult(CheckResult.FY_SECOND_SUBMIT.getCode());
			flowView.setStepName(step);
			super.fromCheck(flowView);
			updateReconsiderApply(loanCode,LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode());
			toNull(flowView);
		} else if (response.equals(ApproveRouteConstants.RECHECK_TO_END)) { 
			// 复审拒借
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_RECHECK_REJECT.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_RECHECK_REJECT.getCode());
			flowView.setCheckResult(CheckResult.FY_SECOND_REFUSAL.getCode());
			flowView.setFlag(true);
			flowView.setRefuseReason(getReason(flowView));
			flowView.setStepName(step);
			String id = super.fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
			updateReconsiderApply(loanCode,LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode());
			updateLoanTime(flowView.getLoanCode());
		} else if (response.equals(ApproveRouteConstants.CHECK_TO_PREVIOUS)) { // 复审退回协商到初审
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_RECHECK_PENDING.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_RECHECK_PENDING.getCode());
			flowView.setCheckResult(CheckResult.FY_SECOND_BACK.getCode());
			flowView.setStepName(step);
			toVerifyRecheck(flowView);
			updateReconsiderApply(loanCode, LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode()); // 更新复议申请表
		}
	}
}
