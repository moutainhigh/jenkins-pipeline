package com.creditharmony.approve.workflow.event;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.rule.auditrating.service.AuditRatingService;
import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.approve.type.CheckType;
import com.creditharmony.core.loan.type.LoanApplyStatus;

/**
 * 初审处理流程回调
 * 
 * @Class Name FirstCheckEx
 * @author 赖敏
 * @Create In 2015年12月17日
 */
@Service("Verify_Check_Flow")
public class VerifyCheckEvent extends AuditRatingService implements ExEvent {

	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		String response = workItem.getResponse();
		// 取出在controller 中放入的业务数据，需要向下转换
		VerifyBusinessView  flowView = (VerifyBusinessView)workItem.getBv();
		String step = workItem.getStepName();
		// 退回门店
		if (response.equals(ApproveRouteConstants.CHECK_TO_STORE)) {
			flowView.setLoanApplyStatus(LoanApplyStatus.BACK_STORE.getName());
			flowView.setCheckResult(CheckResult.XS_FIRST_BACK.getCode());
			flowView.setFlag(true);
			flowView.setLoanStatusCode(LoanApplyStatus.BACK_STORE.getCode());
			flowView.setStepName(step);
			toStore(flowView);
		} else if (response.equals(ApproveRouteConstants.CHECK_TO_VISIT)) { // 发起外访
			flowView.setOutsideFlag("1");
			flowView.setLoanApplyStatus(LoanApplyStatus.STORE_ALLOT_VISIT.getName());
			flowView.setCheckResult(CheckResult.XS_FIRST_OUTVISIT.getCode());
			flowView.setFlag(true);
			flowView.setLoanStatusCode(LoanApplyStatus.STORE_ALLOT_VISIT.getCode());
			flowView.setStepName(step);
			toVisit(flowView);
		}else if (response.equals(ApproveRouteConstants.CHECK_TO_ANTIFRAUD)){ // 提报反欺诈
			flowView.setLoanApplyStatus(LoanApplyStatus.CHECK_SUBMIT_ANTIFRAUD.getName());
			flowView.setCheckResult(CheckResult.XS_FIRST_ANTIFRAUD.getCode());
			flowView.setLoanStatusCode(LoanApplyStatus.CHECK_SUBMIT_ANTIFRAUD.getCode());
			flowView.setStepName(step);
			toAntifraud(flowView);
		} else if (response.equals(ApproveRouteConstants.CHECK_TO_RECHECK)){ // 通过 到复审
			flowView.setLoanApplyStatus(LoanApplyStatus.CHECK_PASS_RECHECK.getName());
			flowView.setCheckResult(CheckResult.XS_FIRST_SUBMIT.getCode());
			flowView.setLoanStatusCode(LoanApplyStatus.CHECK_PASS_RECHECK.getCode());
			flowView.setStepName(step);
			fromCheck(flowView);
			toNull(flowView);
			//更新排序
			flowView.setOrderField(flowView.getLoanUrgentFlag()+DateUtils.formatDate(new Date(),"yyyyMMddHHmmss"));
			flowView.setRiskLevel(getScore(flowView.getLoanCode(), flowView.getCheckType()));
		} else if (response.equals(ApproveRouteConstants.CHECK_PLAN_REFUSED)){ // 拟拒借 到复审
			flowView.setLoanApplyStatus(LoanApplyStatus.CHECK_REJECT_RECHECK.getName());
			flowView.setCheckResult(CheckResult.XS_FIRST_TOREFUSAL.getCode());
			flowView.setLoanStatusCode(LoanApplyStatus.CHECK_REJECT_RECHECK.getCode());
			flowView.setStepName(step);
			flowView.setRefuseReason(getReason(flowView));
			fromCheck(flowView);
			toNull(flowView);
			//更新排序
			flowView.setOrderField(flowView.getLoanUrgentFlag()+DateUtils.formatDate(new Date(),"yyyyMMddHHmmss"));
		} else if (response.equals(ApproveRouteConstants.CHECK_TO_END)){ // 拒借 结束
			flowView.setLoanApplyStatus(LoanApplyStatus.CHECK_REJECT.getName());
			flowView.setCheckResult(CheckResult.XS_FIRST_REFUSAL.getCode());
			flowView.setLoanStatusCode(LoanApplyStatus.CHECK_REJECT.getCode());
			flowView.setFlag(true);
			flowView.setStepName(step);
			flowView.setRefuseReason(getReason(flowView));
			// 信审初审时评分过低，状态改为进件引擎拒借
			List<AuditResultSublist> subList = flowView.getAuditResultEx().getSubResult();
			if (ArrayHelper.isNotEmpty(subList)) {
				for (AuditResultSublist sub : subList) {
					if (sub.getRefuseSecondCode().equals(ApproveConstants.SCORE_REFUSE_CODE)) {
						flowView.setLoanApplyStatus(LoanApplyStatus.DECSISON_ENGINE_REJECT.getName());
						flowView.setCheckResult(CheckResult.XS_FIRST_REFUSAL.getCode());
						flowView.setLoanStatusCode(LoanApplyStatus.DECSISON_ENGINE_REJECT.getCode());
						flowView.setStepName(CheckType.DECSISON_ENGINE_AUDIT.getName());
					}
				}
			}			
			String id = fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
		} else if(response.equals(ApproveRouteConstants.CHECK_TO_ABANDON)){ // 客户放弃
			flowView.setLoanApplyStatus(LoanApplyStatus.CUSTOMER_GIVEUP.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.CUSTOMER_GIVEUP.getCode());
			flowView.setCheckResult(CheckResult.XS_FIRST_ABANDON.getCode());
			flowView.setFlag(true);
			flowView.setStepName(step);
			String id = fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
		}
	}
}
