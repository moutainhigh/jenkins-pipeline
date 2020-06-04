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

@Service("Verify_Recheck_Flow")
public class VerifyRecheckEvent extends ApproveCommonService implements ExEvent {

	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		String response = workItem.getResponse();
		// 取出在controller 中放入的业务数据，需要向下转换
		VerifyBusinessView  flowView = (VerifyBusinessView)workItem.getBv();
		flowView.setOutApproveTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		String step = workItem.getStepName();
		// 复审提交到终审组
		if (response.equals(ApproveRouteConstants.RECHECK_TO_GROUPCHECK)) {// 复审提交到终审组
			flowView.setLoanApplyStatus(LoanApplyStatus.RECHECK_PASS_GROUP_CHECK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECHECK_PASS_GROUP_CHECK.getCode());
			flowView.setCheckResult(CheckResult.XS_SECOND_SUBMIT.getCode());
			flowView.setStepName(step);
			fromCheck(flowView);
			toNull(flowView);
		} else if (response.equals(ApproveRouteConstants.RECHECK_TO_INTERESTRATE)) { // 复审提交至审核利率
			flowView.setLoanApplyStatus(LoanApplyStatus.RATE_TO_VERIFY.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RATE_TO_VERIFY.getCode());
			flowView.setCheckResult(CheckResult.XS_SECOND_PASS.getCode());
			flowView.setFlag(true);
			flowView.setStepName(step);
			String id = fromCheck(flowView);
			LoanInfo kinFlg = checkKinnobu(flowView);
			finalUpdate(id,flowView,kinFlg);
			if(kinFlg != null){
				flowView.setLoanFlag(kinFlg.getLoanFlag());// 出借标识 
				flowView.setLoanFlagName(kinFlg.getLoanFlagName());// 出借标识名称 
				flowView.setModel(kinFlg.getModel());// 模型
				workItem.setBv(flowView);
			}
		}else if (response.equals(ApproveRouteConstants.RECHECK_TO_END)){ // 复审提交结束
			flowView.setLoanApplyStatus(LoanApplyStatus.RECHECK_REJECT.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECHECK_REJECT.getCode());
			flowView.setCheckResult(CheckResult.XS_SECOND_REFUSAL.getCode());
			flowView.setRefuseReason(getReason(flowView));
			flowView.setFlag(true);
			flowView.setStepName(step);
			String id = fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
		}else if(response.equals(ApproveRouteConstants.CHECK_TO_PREVIOUS)){//复审退回协商到初审
			flowView.setLoanApplyStatus(LoanApplyStatus.RECHECK_CONSULT_CHECK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECHECK_CONSULT_CHECK.getCode());
			flowView.setCheckResult(CheckResult.XS_SECOND_BACK.getCode());
			flowView.setStepName(step);
			toVerifyRecheck(flowView);
		} 
	}
}
