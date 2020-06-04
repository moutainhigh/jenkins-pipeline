package com.creditharmony.approve.workflow.event;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.creditharmony.approve.rule.auditrating.service.AuditRatingService;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.loan.type.LoanApplyStatus;

/**
 * 复议初审处理流程回调
 * 
 * @Class Name ReconsiderCheckEvent
 * @author 刘燕军
 * @Create In 2015年12月17日
 */
@Service("Reconsider_Check_Flow")
public class ReconsiderCheckEvent extends AuditRatingService implements ExEvent {

	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		String response = workItem.getResponse();
		// 取出在controller 中放入的业务数据，需要向下转换
		VerifyBusinessView  flowView = (VerifyBusinessView)workItem.getBv();
		String step = workItem.getStepName();
		String loanCode = flowView.getLoanCode();
		// 退回门店
		if (response.equals(ApproveRouteConstants.CHECK_TO_STORE)) {
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_BACK_STORE.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_BACK_STORE.getCode());
			flowView.setCheckResult(CheckResult.FY_FIRST_BACK.getCode());
			flowView.setFlag(true);
			flowView.setStepName(step);
			toStore(flowView);
			updateReconsiderApply(loanCode,LoanApplyStatus.RECONSIDER_BACK_STORE.getCode());
		} else if (response.equals(ApproveRouteConstants.CHECK_TO_VISIT)) { // 发起外访
			flowView.setOutsideFlag("1");
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_STORE_VISIT.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_STORE_VISIT.getCode());
			flowView.setFlag(true);
			flowView.setCheckResult(CheckResult.FY_FIRST_OUTVISIT.getCode());
			flowView.setStepName(step);
			toVisit(flowView);
			updateReconsiderApply(loanCode, LoanApplyStatus.RECONSIDER_STORE_VISIT.getCode(), "1");
		}else if (response.equals(ApproveRouteConstants.CHECK_TO_ANTIFRAUD)){ // 提报反欺诈
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_SUBMIT_ANTIFRAUD.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_SUBMIT_ANTIFRAUD.getCode());
			flowView.setCheckResult(CheckResult.FY_FIRST_ANTIFRAUD.getCode());
			flowView.setStepName(step);
			toAntifraud(flowView);
			updateReconsiderApply(loanCode, LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode()); // 更新复议申请表
		} else if (response.equals(ApproveRouteConstants.CHECK_TO_RECHECK)){ // 通过 到复审
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_CHECK_PASS_RECHECK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_CHECK_PASS_RECHECK.getCode());
			flowView.setCheckResult(CheckResult.FY_FIRST_SUBMIT.getCode());
			flowView.setStepName(step);
			fromCheck(flowView); // 更新业务数据
			updateReconsiderApply(loanCode, LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode()); // 更新复议申请表
			toNull(flowView);
			flowView.setOrderField(flowView.getLoanUrgentFlag()+DateUtils.formatDate(new Date(),"yyyyMMddHHmmss"));
			flowView.setRiskLevel(getScore(loanCode, flowView.getCheckType()));
		} else if (response.equals(ApproveRouteConstants.CHECK_PLAN_REFUSED)){ // 拟拒借 到复审
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_CHECK_REJECT_RECHECK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_CHECK_REJECT_RECHECK.getCode());
			flowView.setCheckResult(CheckResult.FY_FIRST_TOREFUSAL.getCode());
			flowView.setRefuseReason(getReason(flowView));
			flowView.setStepName(step);
			fromCheck(flowView); // 更新业务数据
			updateReconsiderApply(loanCode, LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode()); // 更新复议申请表
			toNull(flowView);
			flowView.setOrderField(flowView.getLoanUrgentFlag()+DateUtils.formatDate(new Date(),"yyyyMMddHHmmss"));
		} else if (response.equals(ApproveRouteConstants.CHECK_TO_END)){ // 拒借 结束
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_CHECK_REJECT.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_CHECK_REJECT.getCode());
			flowView.setFlag(true);
			flowView.setCheckResult(CheckResult.FY_FIRST_REFUSAL.getCode());
			flowView.setStepName(step);
			flowView.setRefuseReason(getReason(flowView));
			String id = fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
			updateReconsiderApply(loanCode,LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode());
			updateLoanTime(flowView.getLoanCode());
		} else if(response.equals(ApproveRouteConstants.CHECK_TO_ABANDON)){ // 客户放弃
			flowView.setLoanApplyStatus(LoanApplyStatus.CUSTOMER_GIVEUP.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.CUSTOMER_GIVEUP.getCode());
			flowView.setFlag(true);
			flowView.setCheckResult(CheckResult.FY_FIRST_ABANDON.getCode());
			flowView.setStepName(step);
			String id = fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
			updateReconsiderApply(loanCode,LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode());
			updateLoanTime(flowView.getLoanCode());
		}
	}
}
