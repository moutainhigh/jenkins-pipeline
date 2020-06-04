package com.creditharmony.approve.newCar.controller;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.carloan.entity.FirstServiceCharge;
import com.creditharmony.approve.carloan.entity.ex.CarVerifyListEx;
import com.creditharmony.approve.carloan.service.CarLoanInfoService;
import com.creditharmony.approve.carloan.view.CarOrderBusinessView;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.newCar.entity.NewApplyDetailInfokEx;
import com.creditharmony.approve.newCar.entity.NewCarExamineEntity;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.approve.newCar.entity.NewLoanStatusHis;
import com.creditharmony.approve.newCar.service.NewCarCheckService;
import com.creditharmony.approve.newCar.service.NewCarInfoService;
import com.creditharmony.approve.newCar.service.NewLoanStatusHisService;
import com.creditharmony.approve.verify.entity.ex.DictEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.Reflections;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.loan.type.CarLoanStatus;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
/**
 * 汇诚车借待办已办
 * @Class Name CarLoanController
 * @author 李静辉
 * @Create In 2016年1月12日
 */
@Controller
@RequestMapping(value = "${adminPath}/carloan/carLoanCheck")
public  class NewCarController extends BaseController {
	
	@Autowired
	private NewCarCheckService newCarCheckService;
	@Autowired
	private NewCarInfoService newCarInfoService;
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private CarLoanInfoService carLoanInfoService;
	@Autowired
	private NewLoanStatusHisService loanStatusHisService;
	
	/**
	 * 获取车借复审待办列表
	 * 2017年3月27日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="reFetchTaskItems")
	public String reCheckList(Model model,NewCarExamineEntity entity,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		List<String> statusList = new ArrayList<String>();
		statusList.add(CarLoanStatus.PENDING_REVIEW.getCode());
		statusList.add(CarLoanStatus.FINAL_AUDIT_BACK.getCode());
		statusList.add(CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode());
        Page<NewCarExamineEntity> itemList = newCarCheckService.getReCheckList(new Page<NewCarExamineEntity>(request, response), statusList);
		// 把任务放入到list中的并返回
		if (itemList != null) {
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
	}
	
	/**
	 * 获取车借终审待办列表
	 * 2017年3月27日
	 * @param model
	 * @return
	 */
	@RequestMapping(value="finalFetchTaskItems")
	public String finalFetchTaskItems(Model model,NewCarExamineEntity entity,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		entity.setDictStatus(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
		Page<NewCarExamineEntity> itemList = newCarCheckService.getFinalCheckList(new Page<NewCarExamineEntity>(request, response), entity);
		// 把任务放入到list中的并返回
		if (itemList != null) {
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
		model.addAttribute("auditType", CarLoanSteps.RECHECK_AUDIT.getCode());
		return "newCar/newCheckToDoList";
	}
	
	/**
	 * 车借--复审、终审取单
	 * 2017年4月5日
	 * By 李高远
	 * @param workItem
	 * @param view
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getOrderByAuditType")
	public String getOrderByAuditType(CarOrderBusinessView view,
			HttpServletRequest request, HttpServletResponse response) {
		int num = 5;
		List<String> statusList = new ArrayList<String>();
		String loanStatus = "";
		if ("1".equals(view.getAuditType())) { // 复审取单
			statusList.add(CarLoanStatus.PENDING_REVIEW.getCode());
			statusList.add(CarLoanStatus.FINAL_AUDIT_BACK.getCode());
			statusList.add(CarLoanStatus.SUPPLY_PENDING_REVIEW.getCode());
		} else if ("2".equals(view.getAuditType())) { // 终审取单
			statusList.add(CarLoanStatus.PENDING_FINAL_AUDIT.getCode());
			statusList.add(CarLoanStatus.PENDING_AUDIT_INTEREST_RATE_BACK.getCode());
			statusList.add(CarLoanStatus.SUPPLY_PENDING_FINAL_AUDIT.getCode());
		}
		// 获取可取单的数据
		NewCarLoanInfo entity = new NewCarLoanInfo();
		entity.setDictLoanStatus(loanStatus);
		Page<NewCarLoanInfo> itemList = newCarInfoService.getOrderByLoanStatus(new Page<NewCarLoanInfo>(request, response), statusList);
		List<String> didstatusList = new ArrayList<String>();
		didstatusList.add(CarLoanStatus.PENDING_REVIEW.getCode());
		didstatusList.add(CarLoanStatus.FINAL_AUDIT_BACK.getCode());
        Page<NewCarExamineEntity> didList = newCarCheckService.getReCheckList(new Page<NewCarExamineEntity>(request, response), didstatusList);
		String flag = BooleanType.FALSE;
		if(didList.getList().size() < num){
			if(itemList.getCount() > 0){
				NewCarLoanInfo newCarLoanInfo = itemList.getList().get(0);
				//取单后更新借款标识
				newCarLoanInfo.setDictLoanFlag("1"); // 0:未取单 ; 1:已取单
				newCarInfoService.updateLoanFlag(newCarLoanInfo);
					flag = BooleanType.TRUE;
			} else {
				// 无单可取
				flag = "empty";
			}
		}
		return flag;
	}
	
	/**
	 * 获取复审已办列表
	 * 2017年4月7日
	 * By 李高远
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getFinalRecheckList")
	public String getFinalRecheckList(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("auditType", CarLoanSteps.RECHECK_AUDIT.getCode());
		this.getCheckList(model, carVerifyListEx, request, response,CarLoanSteps.RECHECK_AUDIT.getCode());
		return "newCar/newCheckDoneList";
	}
	
	
	/**
	 * 抽出复审和终审共通方法
	 * 2017年4月7日
	 * By 李高远
	 * 使用的用户条件不同
	 * @param model
	 * @param carVerifyListEx
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	private int getCheckList(Model model, CarVerifyListEx carVerifyListEx,
		HttpServletRequest request, HttpServletResponse response,String type) {
		Map<String, Object> map = jsonMapper.convertValue(carVerifyListEx, Map.class);
		map.put("minCustomerIntoTime", carVerifyListEx.getMinCustomerIntoTime());
		map.put("maxCustomerIntoTime", carVerifyListEx.getMaxCustomerIntoTime());
		List<CityInfo> provinces = cityInfoService.findProvince();
		User user = UserUtils.getUser();
		// 当前登人员的组，同组复审已办
		map.put("departmentId", user.getDepartment().getId());
		map.put("lastUser", model.asMap().get("auditType"));
		String orgListStr = carVerifyListEx.getLoanTermOrgId();
		if (orgListStr != null && !"".equals(orgListStr)) {
			List<String> orgList = new ArrayList<String>();
			for (String org : orgListStr.split(",")) {
				orgList.add(org);
			}
			map.put("orgList", orgList);
		}
		if(type.equals(CarLoanSteps.RECHECK_AUDIT.getCode())){
			Page<CarVerifyListEx> page = newCarCheckService.findPage(new Page<CarVerifyListEx>(request, response), map);
			model.addAttribute("carVerifyListEx", carVerifyListEx);
			model.addAttribute("provinces", provinces);
			model.addAttribute("page", page);
			return page.getList().size();
		}else if(type.equals(CarLoanSteps.FIRST_AUDIT.getCode())){
			int count = newCarCheckService.findCount(map);
			return count;
		}
		return 0;
	}
	
	/**
	 * 获取复审已办列表
	 * 2017年4月7日
	 * By 李高远
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getDoneList")
	public String getDoneList(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("auditType", CarLoanSteps.FIRST_AUDIT.getCode());
		this.dealWithList(model, carVerifyListEx, request, response);
		return "newCar/newCheckDoneList";
	}
	
	/**
	 * 抽出复审和终审共通方法
	 * 2017年4月7日
	 * By 李高远
	 * @param model
	 * @param carVerifyListEx
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	private void dealWithList(Model model, CarVerifyListEx carVerifyListEx,
		HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = jsonMapper.convertValue(carVerifyListEx, Map.class);
		map.put("minCustomerIntoTime", carVerifyListEx.getMinCustomerIntoTime());
		map.put("maxCustomerIntoTime", carVerifyListEx.getMaxCustomerIntoTime());
		List<CityInfo> provinces = cityInfoService.findProvince();
		User user = UserUtils.getUser();
		// 当前登录人员的code值，只能查询自己办理的列表，不传则查询全部
		map.put("transactorCode", user.getId());
		map.put("lastUser", model.asMap().get("auditType"));
		String orgListStr = carVerifyListEx.getLoanTermOrgId();
		if (orgListStr != null && !"".equals(orgListStr)) {
			List<String> orgList = new ArrayList<String>();
			for (String org : orgListStr.split(",")) {
				orgList.add(org);
			}
			map.put("orgList", orgList);
		}
		Page<CarVerifyListEx> page = newCarCheckService.findPage(new Page<CarVerifyListEx>(request, response), map);
		model.addAttribute("carVerifyListEx", carVerifyListEx);
		model.addAttribute("provinces", provinces);
		model.addAttribute("page", page);
	}
	
	/**
	 * 获取终审已办列表
	 * 2017年4月7日
	 * By 李高远
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getFinalDoneList")
	public String getFinalDoneList(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("auditType", CarLoanSteps.RECHECK_AUDIT.getCode());
		this.dealWithList(model, carVerifyListEx, request, response);
		return "newCar/newCheckDoneList";
	}
	
	/**
	 * 车借--获取退回类型全部列表
	 * 2017年4月10日
	 * By 李高远
	 * @param model
	 * @param verifyParamEx
	 * @return 退回类型全部列表
	 */
	@RequestMapping(value="backCheck")
	public String backCheck(Model model, VerifyParamEx verifyParamEx) {
		List<DictEx> dicts = newCarCheckService.findAllBackCheckDicts(LendConstants.CAR_BACK_REASON);
		model.addAttribute("dicts", dicts);
		return "newCar/newBackForm";
	}
	
	/**
	 * 根据借款编码获取费率对应的关系表
	 * By 李高远
	 * @param loanCode  借款编码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "asynLoadFirstServiceRate", method = RequestMethod.POST)
	public String asynLoadFirstServiceRate(String loanCode){
		NewCarLoanInfo carLoanInfo = newCarInfoService.findByLoanCode(loanCode); // 借款信息 
		if(null!=carLoanInfo){
			if(null!=carLoanInfo.getFirstServiceChargeId()){ 
					FirstServiceCharge f = carLoanInfoService.findFirstServiceChargeById(carLoanInfo.getFirstServiceChargeId()); 
					return jsonMapper.toJson(f); 
			}else{ 
				FirstServiceCharge f = new FirstServiceCharge(); 
				if(carLoanInfo.getFirstServiceTariffingRate().compareTo(new BigDecimal("0"))==0){ 
					f.setNinetyAboveRate("2"); 
					f.setNinetyBelowRate("0"); 
				}else if (carLoanInfo.getFirstServiceTariffingRate().compareTo(new BigDecimal("4"))==0){ 
						f.setNinetyAboveRate("4"); 
						f.setNinetyBelowRate("2"); 
				}else{ 
						if(carLoanInfo.getLoanMonths() > 100){ 
							f.setNinetyAboveRate("2"); 
							f.setNinetyBelowRate("0"); 
						}else{ 
							f.setNinetyAboveRate("4"); 
							f.setNinetyBelowRate("2"); 
						} 
				}
				return jsonMapper.toJson(f); 
			} 
		} 
		return null; 
	}
	
	/**
	 * 根据借款编号获取详细借款申请信息，含有客户信息、客户联系人信息、基本申请信息、车辆信息等
	 * 2017年4月12日
	 * By 李高远
	 * @param model
	 * @param verifyParamEx
	 * @return 详细借款申请信息
	 */
	@RequestMapping(value="applyDetail")
	public String applyDetail(Model model, VerifyParamEx verifyParamEx) {
		String loanCode = verifyParamEx.getLoanCode();
		NewApplyDetailInfokEx applyDetailInfokEx = newCarCheckService.getCheckInfo(loanCode);
		model.addAttribute("applyDetailInfokEx", applyDetailInfokEx);
		return "newCar/newApplyDetailView";
	}
	
	/**
	 * 通过借款编号loanCode获取审批历史
	 * 2017年4月12日
	 * By 李高远
	 * @param model
	 * @param verifyParamEx
	 * @return 审批历史
	 */
	@RequestMapping(value="checkHistory")
	public String checkHistory(HttpServletRequest request,
			HttpServletResponse response, Model model, NewLoanStatusHis loanStatusHis) {
		Page<NewLoanStatusHis> page = new Page<NewLoanStatusHis>(request, response);
		//数据库查询列表数据
		page = loanStatusHisService.findByLoanCode(page, loanStatusHis);
		//传递数据到前台页面展示
		model.addAttribute("page", page);
		model.addAttribute("info", loanStatusHis);
		return "newCar/newCheckHistoryView";
	}
}
