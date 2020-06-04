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
 * 复议终审
 * @Class Name ReconsiderFinalCheckEvent
 * @author 李文勇
 * @Create In 2015年12月25日
 */
@Service("Reconsider_FinalCheck_Flow")
public class ReconsiderFinalCheckEvent extends ApproveCommonService implements ExEvent {	
	/**
	 * 重写父类方法
	 * 2015年12月25日
	 * By 李文勇
	 * @param workItem
	 */
	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		String response = workItem.getResponse();
		// 取出在controller 中放入的业务数据，需要向下转换
		VerifyBusinessView  flowView = (VerifyBusinessView)workItem.getBv();
		flowView.setOutApproveTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		String step = workItem.getStepName();
		String loanCode = flowView.getLoanCode();
		if(response.equals(ApproveRouteConstants.FINALCHECK_TO_PASS)){ //通过
			flowView.setLoanApplyStatus(LoanApplyStatus.RATE_TO_VERIFY.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RATE_TO_VERIFY.getCode());
			flowView.setCheckResult(CheckResult.FY_FINAL_PASS.getCode());
			flowView.setFlag(true);
			flowView.setStepName(step);
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
			updateLoanTime(flowView.getLoanCode());
		}else if(response.equals(ApproveRouteConstants.FINALCHECK_TO_END)){ //拒借
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_FINAL_CHECK_REJECT.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_FINAL_CHECK_REJECT.getCode());
			flowView.setCheckResult(CheckResult.FY_FINAL_REFUSAL.getCode());
			flowView.setFlag(true);
			flowView.setRefuseReason(getReason(flowView));
			flowView.setStepName(step);
			String id = super.fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
			updateReconsiderApply(loanCode,LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode());
			updateLoanTime(flowView.getLoanCode());
		}else if(response.equals(ApproveRouteConstants.CHECK_TO_PREVIOUS)){// 终审退回协商到到复审
			flowView.setLoanApplyStatus(LoanApplyStatus.RECONSIDER_FINAL_CHECK_COUSULT_RECHECK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.RECONSIDER_FINAL_CHECK_COUSULT_RECHECK.getCode());
			flowView.setCheckResult(CheckResult.FY_FINAL_BACK.getCode());
			flowView.setStepName(step);
			toVerifyRecheck(flowView);
			updateReconsiderApply(loanCode,LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode());
		}
	}
}
