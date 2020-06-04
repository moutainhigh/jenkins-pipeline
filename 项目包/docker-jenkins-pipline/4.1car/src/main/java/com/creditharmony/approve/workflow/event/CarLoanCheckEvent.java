package com.creditharmony.approve.workflow.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.base.service.CarCommonService;
import com.creditharmony.approve.carloan.entity.CarAuditResult;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.service.CarAuditResultService;
import com.creditharmony.approve.carloan.service.CarLoanInfoService;
import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.carloan.view.FlowProperties;
import com.creditharmony.approve.workflow.constants.CarLoanCheckRouteConstants;
import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.loan.type.CarLoanOperateResult;
import com.creditharmony.core.loan.type.CarLoanProductType;
import com.creditharmony.core.loan.type.CarLoanStatus;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 复审处理流程回调
 * @Class Name CarLoanCheckEvent
 * @author 申诗阔
 * @Create In 2016年1月27日
 */
@Service("Car_Loan_Check_Flow")
public class CarLoanCheckEvent extends CarCommonService implements ExEvent {
	
	@Autowired
	private CarLoanInfoService carLoanInfoService;
	@Autowired
	private CarAuditResultService carAuditResultService;


	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		String response = workItem.getResponse();
		// 取出在controller中放入的业务数据，需要向下转换
		CarVerifyBusinessView flowView = (CarVerifyBusinessView) workItem.getBv();
		String stepName = workItem.getStepName();
		flowView.setRecheckName(UserUtils.getUser().getName());							//设置复审姓名
		flowView.setAuditTime(new Date());												//设置审批时间
		
		
		if (response.equals(CarLoanCheckRouteConstants.BACK_FIRST_CHECK)) { 			// 退回初审
			flowView.setDictStatus(CarLoanStatus.REVIEW_BACK.getCode());
			flowView.setAuditAmount(0d);
			flowView.setAuditBorrowProductCode("");
			flowView.setAuditBorrowProductName("");
			flowView.setAuditLoanMonths("0");
			flowView.setGrossRate(0d);
			flowView.setOperResultName(CarLoanOperateResult.RECHECK_BACK_FIRSTCHECK.getCode());
			
			redTopBack(workItem, flowView,CarLoanSteps.RECHECK_AUDIT.getName());
					
		} else if (response.equals(CarLoanCheckRouteConstants.BACK_CONTRACT_SIGN_UPLOAD)) { // 退回待签订合同
			flowView.setDictStatus(CarLoanStatus.SUPPLY_BACK_PENDING_SIGNED_CONTRACT.getCode());				// 设置借款状态code
			flowView.setOperResultName(CarLoanOperateResult.SUPPLY_RECHECK_BACK_SIGNED_CONTRACT.getCode());
			redTopBack(workItem, flowView,CarLoanSteps.RECHECK_AUDIT.getName());
		} else if (response.equals(CarLoanCheckRouteConstants.TO_FINAL_CHECK)){ 		// 复审通过到终审
			String loanCode = flowView.getLoanCode();
			CarLoanInfo carLoanInfo = carLoanInfoService.findByLoanCode(loanCode);
			if (CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode().equals(carLoanInfo.getDictLoanStatus()) 
					|| CarLoanStatus.SUPPLY_BACK_PENDING_FINAL_AUDIT.getCode().equals(carLoanInfo.getDictLoanStatus())) {
				flowView.setDictStatus(CarLoanStatus.SUPPLY_PENDING_FINAL_AUDIT.getCode());
				flowView.setOperResultName(CarLoanOperateResult.TO_SUPPLY_FINALCHECK.getCode());
				
				CarAuditResult carRet = carAuditResultService.selectLastByLoanCodeAndCheckType(loanCode, CarLoanSteps.RECHECK_AUDIT.getCode());
				if (carRet != null) {
					flowView.setAuditAmount(carRet.getAuditAmount());
					flowView.setAuditBorrowProductCode(carRet.getDictProductType());
					flowView.setAuditLoanMonths(carRet.getDictAuditMonths());
					flowView.setGrossRate(carRet.getGrossRate());
					flowView.setFirstServiceTariffing(carRet.getFirstServiceTariffing());
				}
			} else {
				flowView.setDictStatus(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
				flowView.setOperResultName(CarLoanOperateResult.TO_FINALCHECK.getCode());
				flowView.setAuditBorrowProductName(CarLoanProductType.parseByCode(flowView.getAuditBorrowProductCode()).getName());
			}
			
			redTopCommit(workItem, flowView,stepName);
			
		} else if (response.equals(CarLoanCheckRouteConstants.TO_FINAL_CHECK_CONDICTION)){ // 复审附条件通过到终审
			flowView.setDictStatus(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
			flowView.setOperResultName(CarLoanOperateResult.TO_CONDITION_FINALCHECK.getCode());
			flowView.setAuditBorrowProductName(CarLoanProductType.parseByCode(flowView.getAuditBorrowProductCode()).getName());
			redTopCommit(workItem, flowView,stepName);
		}  else if (response.equals(CarLoanCheckRouteConstants.RECHECK_REFUSED)){ 		// 复审拒绝 结束
			flowView.setDictStatus(CarLoanStatus.REVIEW_REJECT.getCode());
			flowView.setOperResultName(CarLoanOperateResult.RECHECK_REFUSE.getCode());
		} else if(response.equals(CarLoanCheckRouteConstants.RECHECK_ABANDON)){ 		// 客户放弃
			if ("车借展期流程".equals(workItem.getFlowName())) {
				flowView.setDictStatus(CarLoanStatus.EXTENDED_GIVE_UP.getCode());
			} else {
				flowView.setDictStatus(CarLoanStatus.CUSTOMER_GIVE_UP.getCode());
			}
			flowView.setOperResultName(CarLoanOperateResult.RECHECK_GIVEUP.getCode());
		}
		auditHandle(flowView, stepName);
	}
	
	//标红置顶退回业务
	private void redTopBack(WorkItemView workItem, CarVerifyBusinessView bv,
			String cureentStep) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		   Object object = workItem.getFlowProperties();
		   if(object != null){
		       FlowProperties flowProperties = (FlowProperties)object;
		       if(StringUtils.isEmpty(flowProperties.getFirstBackSourceStep()) || 
		    		   flowProperties.getFirstBackSourceStep().contains(FlowProperties.FIRST_BACK_SOURCE_STEP_DEAULT_VALUE)){
		    	   bv.setFirstBackSourceStep(cureentStep);
		        }
		       bv.setOrderField("0," + sdf.format(new Date()));
		   }
	}
	//标红置顶提交业务
	private void redTopCommit(WorkItemView workItem, CarVerifyBusinessView bv,
			String currentStepName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		    Object object = workItem.getFlowProperties();
		    if(object != null){
		        FlowProperties flowProperties = (FlowProperties)object;
		        if(currentStepName.equals(flowProperties.getFirstBackSourceStep())){
		        	bv.setFirstBackSourceStep(FlowProperties.FIRST_BACK_SOURCE_STEP_DEAULT_VALUE);
		        	bv.setOrderField("1," + sdf.format(new Date()));
		        }else{
		            if(StringUtils.isEmpty(flowProperties.getFirstBackSourceStep()) || 
		            		 flowProperties.getFirstBackSourceStep().contains(FlowProperties.FIRST_BACK_SOURCE_STEP_DEAULT_VALUE)){
		            	bv.setOrderField("1," + sdf.format(new Date()));
		            }else{
		            	bv.setOrderField("0," + sdf.format(new Date()));
		            }
		        }
		    }
	}
}
