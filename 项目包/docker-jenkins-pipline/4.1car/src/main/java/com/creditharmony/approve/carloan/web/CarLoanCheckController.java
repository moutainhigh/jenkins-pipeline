package com.creditharmony.approve.carloan.web;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.carloan.dao.CarLoanInfoDao;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.service.CarCheckService;
import com.creditharmony.approve.carloan.service.CarContractVersionService;
import com.creditharmony.approve.carloan.service.CarLoanInfoService;
import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.carloan.view.FlowProperties;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.workflow.constants.CarLoanCheckRouteConstants;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.loan.type.CarLoanResult;
import com.creditharmony.core.loan.type.CarLoanStatus;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.loan.type.CarLoanThroughFlag;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
/**
 * 
 * @Class Name CarLoanCheckController
 * @author 申诗阔
 * @Create In 2016年1月29日
 */
@Controller
@RequestMapping(value = "${adminPath}/carloanOld/carLoanCheckInfo")
public class CarLoanCheckController extends BaseController {
	
	//FlowService 查询流程待办列表、提交流程 
	@Resource(name="appFrame_flowServiceImpl")
	protected FlowService flowService;
	
	@Autowired
	private CarCheckService carCheckService;
	@Autowired
	private CarLoanInfoService carLoanInfoService;
	@Autowired
	private CarLoanInfoDao carLoanInfoDao;
	@Autowired
	private CarContractVersionService contractVers;
	
	/**
	 * 车借--复审决策
	 * 2016年1月27日
	 * By 申诗阔
	 * @param workItem 工作流工作项实体
	 * @param view 业务对象实体
	 * @return ture:成功;false:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynCarLoanReCheckHandle")
	public String asynCarLoanReCheckHandle(WorkItemView workItem, CarVerifyBusinessView view,FlowProperties flowProperties){
		//标红置顶业务所需参数
		workItem.setFlowProperties(flowProperties);
		
		String result = view.getAuditResult(); 						// 审批结果：  "1","通过"、"2","附条件通过"、"3","拒绝"、"4","退回"、"5","客户放弃"、"6","待定"
		Object json = view.getJson(); 								// 退回时用到的，退回json数据
		view.setAuditUserName(UserUtils.getUser().getName()); 		// 设置审批人员姓名
		view.setAuditType(CarLoanSteps.RECHECK_AUDIT.getCode()); 	// 设置审核类型
		view.setConditionalThroughFlag("");
		if (StringUtils.isNotBlank(result) && !CarLoanResult.DETERMINED.getCode().equals(result)) { // 若审批结果不为空
			if (CarLoanResult.THROUGH.getCode().equals(result)) { // 通过
				workItem.setResponse(CarLoanCheckRouteConstants.TO_FINAL_CHECK);
			} else if (CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(result)) { // 附条件通过
				workItem.setResponse(CarLoanCheckRouteConstants.TO_FINAL_CHECK_CONDICTION);
				view.setConditionalThroughFlag("附条件");
			} else if (CarLoanResult.REFUSED.getCode().equals(result)) { // 拒绝
				workItem.setResponse(CarLoanCheckRouteConstants.RECHECK_REFUSED);
			} else if (CarLoanResult.CUSTOMER_GIVE_UP.getCode().equals(result)) { // 放弃
				workItem.setResponse(CarLoanCheckRouteConstants.RECHECK_ABANDON);
			}
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} else if (CarLoanResult.DETERMINED.getCode().equals(result)) { // 待定
			view.setDictStatus(CarLoanStatus.PENDING_REVIEW.getCode());
			view.setIsWait(ApproveConstants.WAIT_FLAG);
			workItem.setBv(view);
			carCheckService.waitHandle(workItem);
		} else if (json != null) { // 若为退回
			String loanCode = view.getLoanCode();
			CarLoanInfo carLoanInfo = carLoanInfoService.findByLoanCode(loanCode);
			if (CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode().equals(carLoanInfo.getDictLoanStatus()) 
					|| CarLoanStatus.SUPPLY_BACK_PENDING_FINAL_AUDIT.getCode().equals(carLoanInfo.getDictLoanStatus())) {
				view.setConditionalThroughFlag("附条件");
				workItem.setResponse(CarLoanCheckRouteConstants.BACK_CONTRACT_SIGN_UPLOAD);
			} else {
				workItem.setResponse(CarLoanCheckRouteConstants.BACK_FIRST_CHECK);
			}
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} else {
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE; 
	}
	

	/**
	 * 车借--终审决策
	 * 2016年2月1日
	 * By 申诗阔
	 * @param workItem 工作流工作项实体
	 * @param view 业务对象实体
	 * @return ture:成功;false:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynCarLoanFinalCheckHandle")
	public String asynCarLoanFinalCheckHandle(WorkItemView workItem, CarVerifyBusinessView view,FlowProperties flowProperties){
		//标红置顶业务参数
		workItem.setFlowProperties(flowProperties);
		
		view.setConditionalThroughFlag("");
		String result = view.getAuditResult(); // 审批结果：通过，拒绝，放弃，附条件通过等等
		Object json = view.getJson(); // 退回时用到的，退回json数据
		view.setAuditUserName(UserUtils.getUser().getName()); // 设置审批人员姓名
		view.setAuditType(CarLoanSteps.FINAL_AUDIT.getCode());	// 设置审核类型
		if (StringUtils.isNotBlank(result) && !CarLoanResult.DETERMINED.getCode().equals(result)) { // 若审批结果不为空
			if (CarLoanResult.THROUGH.getCode().equals(result)) { // 通过
				String loanCode = view.getLoanCode();
				CarLoanInfo carLoanInfo = carLoanInfoService.findByLoanCode(loanCode);
				view.setLoanFlag(CarLoanThroughFlag.HARMONY.getCode());
				if (CarLoanStatus.SUPPLY_PENDING_FINAL_AUDIT.getCode().equals(carLoanInfo.getDictLoanStatus())) {
					workItem.setResponse(CarLoanCheckRouteConstants.TO_AUDIT_CONTRACT);
				} else {
					workItem.setResponse(CarLoanCheckRouteConstants.FINAL_CHECK_PASS);
				}
			} else if (CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(result)) { // 附条件通过
				workItem.setResponse(CarLoanCheckRouteConstants.FINAL_CHECK_PASS_CONDICTION);
				view.setConditionalThroughFlag("附条件");
				view.setLoanFlag(CarLoanThroughFlag.CONDITIONAL.getCode());
			} else if (CarLoanResult.REFUSED.getCode().equals(result)) { // 拒绝
				workItem.setResponse(CarLoanCheckRouteConstants.FINAL_CHECK_REFUSED);
			} else if (CarLoanResult.CUSTOMER_GIVE_UP.getCode().equals(result)) { // 放弃
				workItem.setResponse(CarLoanCheckRouteConstants.FINAL_CHECK_ABANDON);
			}
			CarLoanInfo loanInfo = carLoanInfoDao.findByApplyId(view.getApplyId());	// 借款信息
			view.setContractVersion(contractVers.getFlowContractVersion(loanInfo.getLoanCode(), loanInfo.getIsextension()));
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} else if (CarLoanResult.DETERMINED.getCode().equals(result)) { // 待定
			view.setDictStatus(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
			view.setIsWait(ApproveConstants.WAIT_FLAG);
			workItem.setBv(view);
			carCheckService.waitHandle(workItem);
		} else if (json != null) { // 若为退回
			workItem.setResponse(CarLoanCheckRouteConstants.BACK_RECHECK);
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} else {
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE; 
	}
	
}
