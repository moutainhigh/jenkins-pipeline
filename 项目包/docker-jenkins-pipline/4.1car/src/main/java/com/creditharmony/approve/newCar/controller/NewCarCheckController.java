package com.creditharmony.approve.newCar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.carloan.view.FlowProperties;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.newCar.dao.NewLoanStatusHisDao;
import com.creditharmony.approve.newCar.entity.NewApplicationInterviewInfo;
import com.creditharmony.approve.newCar.entity.NewCarAuditResult;
import com.creditharmony.approve.newCar.entity.NewCarBusinessView;
import com.creditharmony.approve.newCar.entity.NewCarContract;
import com.creditharmony.approve.newCar.entity.NewCarExamineEntity;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.approve.newCar.entity.NewLoanCustomerEx;
import com.creditharmony.approve.newCar.entity.NewVehicleInfo;
import com.creditharmony.approve.newCar.service.NewApplicationInterviewInfoService;
import com.creditharmony.approve.newCar.service.NewCarAuditResultService;
import com.creditharmony.approve.newCar.service.NewCarCheckService;
import com.creditharmony.approve.newCar.service.NewCarContractService;
import com.creditharmony.approve.newCar.service.NewCarContractVersionService;
import com.creditharmony.approve.newCar.service.NewCarCustomerService;
import com.creditharmony.approve.newCar.service.NewCarImageService;
import com.creditharmony.approve.newCar.service.NewCarInfoService;
import com.creditharmony.approve.newCar.service.NewVehicleInfoService;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.loan.type.CarLoanOperateResult;
import com.creditharmony.core.loan.type.CarLoanProductType;
import com.creditharmony.core.loan.type.CarLoanResult;
import com.creditharmony.core.loan.type.CarLoanStatus;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.loan.type.CarLoanThroughFlag;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
/**
 * 车借汇诚复审、终审
 * @Class Name NewCarLoanCheckController
 * @Create In 2017年3月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/carloan/carLoanCheckInfo")
public class NewCarCheckController extends BaseController {
	
	@Autowired
	private NewCarCheckService newCarCheckService;
	@Autowired
	private NewCarInfoService carInfoService;
	@Autowired
	private NewCarContractService carContractService;
	@Autowired
	private NewCarContractVersionService contractVers;
	@Autowired
	private NewCarImageService carImageService;
	@Autowired
	private NewCarCustomerService carCustomerService;
	@Autowired
	private NewCarAuditResultService carAuditResultService;
	@Autowired
	private NewVehicleInfoService vehicleInfoService;
	@Autowired
	private NewLoanStatusHisDao loanStatusHisDao;
	@Autowired
	private NewApplicationInterviewInfoService applicationInterviewInfoService;
/*	*//**
	 * 获取车借复审待办列表
	 * 2017年3月27日
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value="reCheckList")
	public String reCheckList(Model model,NewCarExamineEntity entity,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		entity.setDictStatus(CarLoanStatus.PENDING_REVIEW.getCode());
        Page<NewCarExamineEntity> itemList = newCarCheckService.getReCheckList(new Page<NewCarExamineEntity>(request, response), entity);
		// 把任务放入到list中的并返回
		if (ListUtils.isNotEmptyList(itemList.getList())) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("extensionFlag", "jk_extend_loan_flag");
			map.put("applyStatusCode", "jk_car_loan_status");
			map.put("dictStatus", "jk_car_loan_status");
			for (Map.Entry<String, String> entry : map.entrySet()) { // 遍历需要进行字典转换的属性
				for (NewCarExamineEntity listItem : itemList.getList()) { // for-each获取数据列表，获得每一条工作流中获取的数据
					PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(listItem.getClass()).getPropertyDescriptors();
					for (int i = 0; i < propertyDescriptors.length; i++) { // for循环获取每一条数据的属性，用于获取属性名（如dictStatus）、属性值
		                PropertyDescriptor descriptor = propertyDescriptors[i];
		                String propertyName = descriptor.getName();
		                if (!propertyName.equals("class")) {
		                    Method readMethod = descriptor.getReadMethod();
		                    if (readMethod != null) {
		                    	if (entry.getKey().equalsIgnoreCase(propertyName)) {
		                    		Object result = readMethod.invoke(listItem, new Object[0]);
		                    		if (result != null) {
		                    			Reflections.setFieldValue(listItem, propertyName, DictCache.getInstance().getDictLabel(entry.getValue(), result.toString()));
		                    		}
		                    	}
		                    }
		                }
		            }
				}
			}
		}
		model.addAttribute("itemList", itemList);
		model.addAttribute("auditType", CarLoanSteps.FIRST_AUDIT.getCode());
		return "newCar/newCheckToDoList";
	}*/

	/**
	 * 获取车借复审待办基本数据
	 * 2017年3月27日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="reTransactList")
	public String reTransactList(Model model,NewCarExamineEntity entity, String loancode,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		NewCarBusinessView res = new NewCarBusinessView();
		NewCarLoanInfo loanInfo = carInfoService.findByLoanCode(loancode);	// 借款信息
		
		if (YESNO.YES.getCode().equals(loanInfo.getIsextension())) { // 若是展期，则获取展期上一次终审审批金额
			NewCarContract carContract = carContractService.getLastByLoanCodeOfExtend(loanInfo.getLoanCode());
			model.addAttribute("carContract", carContract);
			res.setCarContract(carContract);
		}
		String loanCode = loanInfo.getLoanCode();
		//获取合同版本号
	  	res.setContractVersion(contractVers.getFlowContractVersion(loanCode, loanInfo.getIsextension()));
		String imageUrl = carImageService.getImageUrl(entity.getStepName(), loanCode,res.getContractVersion());
		
		res.setImageUrl(imageUrl);
		NewLoanCustomerEx loanCustomerEx = carCustomerService.findCustomerInfo(loanCode);	// 客户信息

		List<NewCarAuditResult> list = carAuditResultService.findCarAuditResultsByLoanCode(loanCode);	// 审批信息
		
		//获取面审信息
		NewApplicationInterviewInfo applicationInterviewInfo = applicationInterviewInfoService.getInfoByLoanCode(loanCode);
		res.setApplicationInterviewInfo(applicationInterviewInfo);
		NewVehicleInfo vehicleInfo = vehicleInfoService.findByLoanCode(loanCode);	// 车辆信息
		// 若为终审待办办理，则获取最后一条复审(非附条件)
//		if (CarLoanSteps.FINAL_AUDIT.getName().equals(entity.getStepName()) && list != null && list.size() > 0) {
//			NewCarAuditResult lastResult = list.get(list.size() - 1);
//			if (CarLoanSteps.RECHECK_AUDIT.getCode().equals(lastResult.getDictCheckType())) {
//				res.setCarAuditResult(lastResult);
//			}
//		}
		// TODO 这里type，checkType用于流程和查询，先写死
//		res.setType(LoanManFlag.MAIN_LOAN.getCode()); // 主借人、2 共借人
//		res.setLoanInfo(loanInfo);
//		res.setLoanCustomerEx(loanCustomerEx);
//		res.setCarAuditResultList(list);
//		res.setVehicleInfo(vehicleInfo);
		model.addAttribute("list", list);
		model.addAttribute("businessView", res);
		model.addAttribute("recheckMsg", list.get(list.size()-1));
		model.addAttribute("loanInfo", loanInfo);
		model.addAttribute("vehicleInfo", vehicleInfo);
		model.addAttribute("loanCustomerEx", loanCustomerEx);
		model.addAttribute("type", LoanManFlag.MAIN_LOAN.getCode());
		if(loanInfo.getDictLoanStatus().equals(CarLoanStatus.PENDING_REVIEW.getCode()) ||
			loanInfo.getDictLoanStatus().equals(CarLoanStatus.FINAL_AUDIT_BACK.getCode()) ||
			 loanInfo.getDictLoanStatus().equals(CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode())){
			return "newCar/newCarVerifyRecheckForm";
		}
		return "newCar/newCarVerifyFinalcheckForm";
	}
	
	/**
	 * 复审 -- 决策
	 * by 李高远
	 * 2017年3月27日
	 */
	@ResponseBody
	@RequestMapping(value="asynCarLoanReCheckHandle")
	public String asynCarLoanReCheckHandle(CarVerifyBusinessView view,
			FlowProperties flowProperties){
		User user = UserUtils.getUser();
		String result = view.getAuditResult(); 						// 审批结果：  "1","通过"、"2","附条件通过"、"3","拒绝"、"4","退回"、"5","客户放弃"、"6","待定"
		Object json = view.getJson(); 								// 退回时用到的，退回json数据
		view.setAuditUserName(UserUtils.getUser().getName()); 		// 设置审批人员姓名
		view.setAuditType(CarLoanSteps.RECHECK_AUDIT.getCode()); 	// 设置审核类型
		view.setConditionalThroughFlag("");
		String loanCode = view.getLoanCode();
		NewCarLoanInfo carInfo = new NewCarLoanInfo();
		carInfo.setLoanCode(loanCode);
		NewCarLoanInfo carLoanInfo = carInfoService.findByLoanCode(loanCode);
		if (StringUtils.isNotBlank(result) && !CarLoanResult.DETERMINED.getCode().equals(result)) { // 若审批结果不为空
			if (CarLoanResult.THROUGH.getCode().equals(result)) { // 通过
				if (CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode().equals(carLoanInfo.getDictLoanStatus()) 
						|| CarLoanStatus.SUPPLY_BACK_PENDING_FINAL_AUDIT.getCode().equals(carLoanInfo.getDictLoanStatus())) {
					view.setDictStatus(CarLoanStatus.SUPPLY_PENDING_FINAL_AUDIT.getCode());
					view.setOperResultName(CarLoanOperateResult.TO_SUPPLY_FINALCHECK.getCode());
					NewCarAuditResult carRet = carAuditResultService.selectLastByLoanCodeAndCheckType(loanCode, CarLoanSteps.RECHECK_AUDIT.getCode());
					if (carRet != null) {
						view.setAuditAmount(carRet.getAuditAmount());
						view.setAuditBorrowProductCode(carRet.getDictProductType());
						view.setAuditLoanMonths(carRet.getDictAuditMonths());
						view.setGrossRate(carRet.getGrossRate());
						view.setFirstServiceTariffing(carRet.getFirstServiceTariffing());
					}
				} else {
					view.setDictStatus(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
					view.setOperResultName(CarLoanOperateResult.TO_FINALCHECK.getCode());
					view.setAuditBorrowProductName(CarLoanProductType.parseByCode(view.getAuditBorrowProductCode()).getName());
				}
				carInfo.setQueueName("HJ_CAR_FINAL_CHECK");
				carInfo.setCreateBy(user.getId());
				carInfoService.updateQueueName(carInfo);
			} else if (CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(result)){ //附条件通过
				view.setConditionalThroughFlag("附条件");
				view.setDictStatus(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
				view.setOperResultName(CarLoanOperateResult.TO_CONDITION_FINALCHECK.getCode());
				view.setAuditBorrowProductName(CarLoanProductType.parseByCode(view.getAuditBorrowProductCode()).getName());
				carInfo.setQueueName("HJ_CAR_FINAL_CHECK");
				carInfo.setCreateBy(user.getId());
				carInfoService.updateQueueName(carInfo);
			}
		} else if (CarLoanResult.REFUSED.getCode().equals(result)){ //拒绝
				view.setDictStatus(CarLoanStatus.REVIEW_REJECT.getCode());
				view.setOperResultName(CarLoanOperateResult.RECHECK_REFUSE.getCode());
		} else if (CarLoanResult.CUSTOMER_GIVE_UP.getCode().equals(result)){ //客户放弃
			if ("1".equals(carLoanInfo.getIsextension())) {
				view.setDictStatus(CarLoanStatus.EXTENDED_GIVE_UP.getCode());
			} else {
				view.setDictStatus(CarLoanStatus.CUSTOMER_GIVE_UP.getCode());
			}
				view.setOperResultName(CarLoanOperateResult.RECHECK_GIVEUP.getCode());
		} else if (CarLoanResult.DETERMINED.getCode().equals(result)) { // 待定
				view.setDictStatus(CarLoanStatus.PENDING_REVIEW.getCode());
				view.setIsWait(ApproveConstants.WAIT_FLAG);
				newCarCheckService.waitHandle(view);
				return BooleanType.TRUE;
		} else if (json != null) { // 若为退回
			if (CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode().equals(carLoanInfo.getDictLoanStatus()) 
					|| CarLoanStatus.SUPPLY_BACK_PENDING_FINAL_AUDIT.getCode().equals(carLoanInfo.getDictLoanStatus())) {
				view.setConditionalThroughFlag("附条件");
				view.setDictStatus(CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode());
			} else {
				carInfo.setQueueName("HJ_CAR_FACE_AUDIT");
				carInfoService.updateQueueName(carInfo);
				view.setDictStatus(CarLoanStatus.REVIEW_BACK.getCode());
				view.setAuditAmount(0d);
				view.setAuditBorrowProductCode("");
				view.setAuditBorrowProductName("");
				view.setAuditLoanMonths("0");
				view.setGrossRate(0d);
				view.setOperResultName(CarLoanOperateResult.RECHECK_BACK_FIRSTCHECK.getCode());
			}
		} else {
			return BooleanType.FALSE;
		}
		carAuditResultService.auditHandle(view, CarLoanSteps.RECHECK_AUDIT.getName());
		return BooleanType.TRUE; 
	}
	
	/**
	 * 车借--终审决策
	 * 2017年3月31日
	 * By 李高远
	 * @param view 业务对象实体
	 * @return ture:成功;false:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynCarLoanFinalCheckHandle")
	public String asynCarLoanFinalCheckHandle(CarVerifyBusinessView view,FlowProperties flowProperties){
		view.setConditionalThroughFlag("");
		String result = view.getAuditResult(); // 审批结果：通过，拒绝，放弃，附条件通过等等
		Object json = view.getJson(); // 退回时用到的，退回json数据
		String loanCode = view.getLoanCode();
		view.setAuditUserName(UserUtils.getUser().getName()); // 设置审批人员姓名
		view.setAuditType(CarLoanSteps.FINAL_AUDIT.getCode());	// 设置审核类型
		NewCarLoanInfo loanInfo = carInfoService.findByLoanCode(loanCode);	// 借款信息
		String isextensionId = loanInfo.getIsextension() != null ? loanInfo.getIsextension() : "";
		if (StringUtils.isNotBlank(result) && !CarLoanResult.DETERMINED.getCode().equals(result)) { // 若审批结果不为空
			if (CarLoanResult.THROUGH.getCode().equals(result)) { // 通过
				NewCarLoanInfo carLoanInfo = carInfoService.findByLoanCode(loanCode);
				view.setLoanFlag(CarLoanThroughFlag.HARMONY.getCode());
				if (CarLoanStatus.SUPPLY_PENDING_FINAL_AUDIT.getCode().equals(carLoanInfo.getDictLoanStatus())) {  //补资料待终审
					view.setDictStatus(CarLoanStatus.PENDING_CONTRACT_REVIEW.getCode());
					view.setOperResultName(CarLoanOperateResult.SUPPLY_FINALCHECK_TO_CONTRACT_REVIEW.getCode());
					NewCarAuditResult carRet = carAuditResultService.selectLastByLoanCodeAndCheckType(loanCode, CarLoanSteps.FINAL_AUDIT.getCode());
					if (carRet != null) {
						view.setAuditAmount(carRet.getAuditAmount());
						view.setAuditBorrowProductCode(carRet.getDictProductType());
						view.setAuditLoanMonths(carRet.getDictAuditMonths());
						view.setGrossRate(carRet.getGrossRate());
						view.setFirstServiceTariffing(carRet.getFirstServiceTariffing());
						view.setFinalEvaluatedPrice(carRet.getFinalEvaluatedPrice());
					}
				} else {
					view.setContractVersion(contractVers.getFlowContractVersion(loanCode, isextensionId));
					view.setDictStatus(CarLoanStatus.AUDIT_INTEREST_RATE.getCode());
					view.setOperResultName(CarLoanOperateResult.TO_INTEREST_RATE.getCode());
					view.setAuditBorrowProductName(CarLoanProductType.parseByCode(view.getAuditBorrowProductCode()).getName());
				}
			} else if (CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(result)) { // 附条件通过
				view.setConditionalThroughFlag("附条件");
				view.setLoanFlag(CarLoanThroughFlag.CONDITIONAL.getCode());
				view.setContractVersion(contractVers.getFlowContractVersion(loanCode, isextensionId));			
				view.setDictStatus(CarLoanStatus.AUDIT_INTEREST_RATE.getCode());
				view.setOperResultName(CarLoanOperateResult.FINALCHECK_CONDITION_TO_INTEREST_RATE.getCode());
				view.setAuditBorrowProductName(CarLoanProductType.parseByCode(view.getAuditBorrowProductCode()).getName());
			} else if (CarLoanResult.REFUSED.getCode().equals(result)) { // 拒绝
				view.setDictStatus(CarLoanStatus.FINAL_AUDIT_REJECT.getCode());
				view.setOperResultName(CarLoanOperateResult.FINALCHECK_REFUSE.getCode());
			} else if (CarLoanResult.CUSTOMER_GIVE_UP.getCode().equals(result)) { // 放弃
				//是否展期数据
				if ("1".equals(loanInfo.getIsextension())) {
					view.setDictStatus(CarLoanStatus.EXTENDED_GIVE_UP.getCode());
				} else {
					view.setDictStatus(CarLoanStatus.CUSTOMER_GIVE_UP.getCode());
				}
				view.setOperResultName(CarLoanOperateResult.FINALCHECK_GIVEUP.getCode());
			}
			view.setContractVersion(contractVers.getFlowContractVersion(loanInfo.getLoanCode(), loanInfo.getIsextension()));
		} else if (CarLoanResult.DETERMINED.getCode().equals(result)) { // 待定
			view.setDictStatus(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
			view.setIsWait(ApproveConstants.WAIT_FLAG);
			newCarCheckService.waitHandle(view);
			return BooleanType.TRUE;
		} else if (json != null) { // 若为退回
			NewCarLoanInfo carLoanInfo = carInfoService.findByLoanCode(loanCode);
			if (CarLoanStatus.SUPPLY_PENDING_FINAL_AUDIT.getCode().equals(carLoanInfo.getDictLoanStatus())) {
				view.setDictStatus(CarLoanStatus.SUPPLY_BACK_PENDING_FINAL_AUDIT.getCode());	// 设置借款状态
				view.setOperResultName(CarLoanOperateResult.SUPPLY_FINALCHECK_BACK_SUPPLY_RECHECK.getCode());
			} else {
				view.setDictStatus(CarLoanStatus.FINAL_AUDIT_BACK.getCode());	// 设置借款状态
				view.setOperResultName(CarLoanOperateResult.FINALCHECK_BACK_RECHECK.getCode());
			}
		} else {
			return BooleanType.FALSE;
		}
		carAuditResultService.auditHandleFinal(view, CarLoanSteps.RECHECK_AUDIT.getName());
		return BooleanType.TRUE; 
	}
	
	@RequestMapping(value = "getDetailView")
	public String getDetailView(Model model, String loancode,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		NewCarBusinessView res = new NewCarBusinessView();
		NewCarExamineEntity entity = new NewCarExamineEntity();
		NewCarLoanInfo loanInfo = carInfoService.findByLoanCode(loancode);	// 借款信息
		
		if (YESNO.YES.getCode().equals(loanInfo.getIsextension())) { // 若是展期，则获取展期上一次终审审批金额
			NewCarContract carContract = carContractService.getLastByLoanCodeOfExtend(loanInfo.getLoanCode());
			model.addAttribute("carContract", carContract);
			res.setCarContract(carContract);
		}
		String loanCode = loanInfo.getLoanCode();
		//获取合同版本号
	  	res.setContractVersion(contractVers.getFlowContractVersion(loanCode, loanInfo.getIsextension()));
		String imageUrl = carImageService.getImageUrl(entity.getStepName(), loanCode,res.getContractVersion());
		
		res.setImageUrl(imageUrl);
		NewLoanCustomerEx loanCustomerEx = carCustomerService.findCustomerInfo(loanCode);	// 客户信息

		List<NewCarAuditResult> list = carAuditResultService.findCarAuditResultsByLoanCode(loanCode);	// 审批信息
		
		//获取面审信息
		NewApplicationInterviewInfo applicationInterviewInfo = applicationInterviewInfoService.getInfoByLoanCode(loanCode);
		res.setApplicationInterviewInfo(applicationInterviewInfo);
		NewVehicleInfo vehicleInfo = vehicleInfoService.findByLoanCode(loanCode);	// 车辆信息
		model.addAttribute("list", list);
		model.addAttribute("businessView", res);
		model.addAttribute("loanInfo", loanInfo);
		model.addAttribute("vehicleInfo", vehicleInfo);
		model.addAttribute("loanCustomerEx", loanCustomerEx);
		model.addAttribute("type", LoanManFlag.MAIN_LOAN.getCode());
		return "newCar/newCarVerifyDetailView";	
	}
}
