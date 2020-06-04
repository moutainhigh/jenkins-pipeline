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
 * 终审
 * @Class Name VerifyFinalCheckEvent
 * @author 赖敏
 * @Create In 2015年12月24日
 */
@Service("Verify_FinalCheck_Flow")
public class VerifyFinalCheckEvent extends ApproveCommonService implements ExEvent{

	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		String response = workItem.getResponse();
		// 取出在controller 中放入的业务数据，需要向下转换
		VerifyBusinessView flowView = (VerifyBusinessView)workItem.getBv();
		flowView.setOutApproveTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
		String step = workItem.getStepName();
		if(response.equals(ApproveRouteConstants.FINALCHECK_TO_PASS)){ // 通过
			flowView.setLoanApplyStatus(LoanApplyStatus.RATE_TO_VERIFY.getName());
			flowView.setCheckResult(CheckResult.XS_FINAL_PASS.getCode());
			flowView.setLoanStatusCode(LoanApplyStatus.RATE_TO_VERIFY.getCode());
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
		}else if(response.equals(ApproveRouteConstants.FINALCHECK_TO_END)){ // 拒借
			flowView.setCheckResult(CheckResult.XS_FINAL_REFUSAL.getCode());
			flowView.setFlag(true);
			flowView.setStepName(step);
			flowView.setRefuseReason(getReason(flowView));
			flowView.setLoanApplyStatus(LoanApplyStatus.FINAL_CHECK_REJECT.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.FINAL_CHECK_REJECT.getCode());
			String id = super.fromCheck(flowView);
			finalUpdate(id,flowView.getLoanCode());
		}else if(response.equals(ApproveRouteConstants.CHECK_TO_PREVIOUS)){// 终审退回协商到到终审组
			flowView.setLoanApplyStatus(LoanApplyStatus.FINAL_CHECK_COUSULT_GROUP_CHECK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.FINAL_CHECK_COUSULT_GROUP_CHECK.getCode());
			flowView.setCheckResult(CheckResult.XS_FINAL_BACK.getCode());
			flowView.setStepName(step);
			toVerifyRecheck(flowView);
		}
	}
}
