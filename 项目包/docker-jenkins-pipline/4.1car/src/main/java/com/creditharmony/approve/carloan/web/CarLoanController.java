package com.creditharmony.approve.carloan.web;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.FirstServiceCharge;
import com.creditharmony.approve.carloan.entity.LoanStatusHis;
import com.creditharmony.approve.carloan.entity.ex.ApplyDetailInfokEx;
import com.creditharmony.approve.carloan.entity.ex.CarVerifyListEx;
import com.creditharmony.approve.carloan.service.CarCheckService;
import com.creditharmony.approve.carloan.service.CarLoanInfoService;
import com.creditharmony.approve.carloan.service.LoanStatusHisService;
import com.creditharmony.approve.carloan.view.CarOrderBusinessView;
import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.verify.entity.ex.DictEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.ReconsiderFinalExamineService;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.entity.CarLoanFlowTaskItemEntity;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.Reflections;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.loan.type.CarLoanStatus;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.query.ProcessQueryBuilder;
/**
 * 汇诚车借待办已办
 * @Class Name CarLoanController
 * @author 李静辉
 * @Create In 2016年1月12日
 */
@Controller
@RequestMapping(value = "${adminPath}/carloanOld/carLoanCheck")
public  class CarLoanController extends BaseController {
	
	//FlowService 查询流程待办列表、提交流程 
	@Resource(name="appFrame_flowServiceImpl")
	protected FlowService flowService;
	
	@Autowired
	private CarLoanInfoService carLoanInfoService;
	@Autowired
	private CarCheckService carCheckService;
	@Autowired
	private LoanStatusHisService loanStatusHisService;
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private ReconsiderFinalExamineService reconsiderFinalExamineService;
	
	/**
	 * 获取车借复审待办列表
	 * 2016年1月20日
	 * By 李静辉
	 * @param model
	 * @return
	 */
	@RequestMapping(value="reFetchTaskItems")
	public String reFetchTaskItems(Model model) throws Exception {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.put("dealUser", UserUtils.getUser().getLoginName());
		// 获取工作流任务
		FlowPage page = new FlowPage();
		page.setPageSize(100);
        page.setPageNo(1);
		flowService.fetchTaskItems(QueueConstants.CAR_VERIFY_RECHECK, queryParam, page, null, CarLoanFlowTaskItemEntity.class);
		List<CarLoanFlowTaskItemEntity> itemList = null;
		List<BaseTaskItemView> sourceWorkItems = page.getList();
		itemList = this.convertList(sourceWorkItems);
		// 把任务放入到list中的并返回
		if (itemList != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("extensionFlag", "jk_extend_loan_flag");
			map.put("applyStatusCode", "jk_car_loan_status");
			map.put("dictStatus", "jk_car_loan_status");
			for (Map.Entry<String, String> entry : map.entrySet()) { // 遍历需要进行字典转换的属性
				for (CarLoanFlowTaskItemEntity listItem : itemList) { // for-each获取数据列表，获得每一条工作流中获取的数据
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
		return "carloan/list/checkToDoList";
	}
	
	/**
	 * 获取车借终审待办列表
	 * 2016年1月20日
	 * By 李静辉
	 * @param model
	 * @return
	 */
	@RequestMapping(value="finalFetchTaskItems")
	protected String finalFetchTaskItems(Model model) throws Exception {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.put("dealUser", UserUtils.getUser().getLoginName());

		// 获取工作流任务
		FlowPage page = new FlowPage();
		page.setPageSize(100);
        page.setPageNo(1);
		flowService.fetchTaskItems(QueueConstants.CAR_VERIFY_FINAL_CHECK, queryParam, page, null, CarLoanFlowTaskItemEntity.class);
		List<CarLoanFlowTaskItemEntity> itemList = null;
		List<BaseTaskItemView> sourceWorkItems = page.getList();
		itemList = this.convertList(sourceWorkItems);
		// 把任务放入到list中的并返回
		if (itemList != null) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("extensionFlag", "jk_extend_loan_flag");
			map.put("applyStatusCode", "jk_car_loan_status");
			map.put("dictStatus", "jk_car_loan_status");
			for (Map.Entry<String, String> entry : map.entrySet()) { // 遍历需要进行字典转换的属性
				for (CarLoanFlowTaskItemEntity listItem : itemList) { // for-each获取数据列表，获得每一条工作流中获取的数据
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
		
		ProcessQueryBuilder paramRe = new ProcessQueryBuilder();
		paramRe.put("dealUser", "");
		FlowPage pageRe = new FlowPage();
		pageRe.setPageSize(50000);
		pageRe.setPageNo(1);
		String recheckQueue = QueueConstants.CAR_VERIFY_RECHECK;
		flowService.fetchTaskItems(recheckQueue, paramRe, pageRe, null, CarLoanFlowTaskItemEntity.class);
		List<BaseTaskItemView> sourceWorkItemsRe = pageRe.getList();
		model.addAttribute("recheckNotTakeCount", (sourceWorkItemsRe != null && sourceWorkItemsRe.size() > 0) ? sourceWorkItemsRe.size() : 0);
		
		ProcessQueryBuilder paramFinal = new ProcessQueryBuilder();
		paramFinal.put("dealUser", "");
		FlowPage pageFinal = new FlowPage();
		pageFinal.setPageSize(50000);
		pageFinal.setPageNo(1);
		String finalcheckQueue = QueueConstants.CAR_VERIFY_FINAL_CHECK;
		flowService.fetchTaskItems(finalcheckQueue, paramFinal, pageFinal, null, CarLoanFlowTaskItemEntity.class);
		List<BaseTaskItemView> sourceWorkItemsFinal = pageFinal.getList();
		model.addAttribute("finalcheckNotTakeCount", (sourceWorkItemsFinal != null && sourceWorkItemsFinal.size() > 0) ? sourceWorkItemsFinal.size() : 0);
		
		model.addAttribute("itemList", itemList);
		model.addAttribute("auditType", CarLoanSteps.RECHECK_AUDIT.getCode());
		return "carloan/list/checkToDoList";
	}
	/**
	 * 获取车借待办任务列表
	 * 2016年2月22日
	 * By 李静辉
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="pendingFetchTaskItems")
	protected String pendingFetchTaskItems(Model model) {
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();

		// 获取工作流任务
		TaskBean taskBean = flowService.fetchTaskItems(
				QueueConstants.CAR_VERIFY_RECHECK, queryParam,
				CarLoanFlowTaskItemEntity.class);
		List<CarLoanFlowTaskItemEntity> itemList = null;
		// 把任务放入到list中的并返回
		if (taskBean.getItemList() != null) 
		{
			itemList = (List<CarLoanFlowTaskItemEntity>) taskBean.getItemList() ;
		}
		model.addAttribute("itemList", itemList);
		model.addAttribute("auditType", CarLoanSteps.RECHECK_AUDIT.getCode());
		return "carloan/list/checkToDoList";
	}
	
	/**
	 * 车借--获取退回类型全部列表
	 * 2016年1月25日
	 * By 申诗阔
	 * @param model
	 * @param verifyParamEx
	 * @return 退回类型全部列表
	 */
	@RequestMapping(value="backCheck")
	public String backCheck(Model model, VerifyParamEx verifyParamEx) {
		List<DictEx> dicts = carCheckService.findAllBackCheckDicts(LendConstants.CAR_BACK_REASON);
		model.addAttribute("dicts", dicts);
		return "carloan/form/backForm";
	}
	
	/**
	 * 根据借款编号获取详细借款申请信息，含有客户信息、客户联系人信息、基本申请信息、车辆信息等
	 * 2016年1月25日
	 * By 申诗阔
	 * @param model
	 * @param verifyParamEx
	 * @return 详细借款申请信息
	 */
	@RequestMapping(value="applyDetail")
	public String applyDetail(Model model, VerifyParamEx verifyParamEx) {
		String loanCode = verifyParamEx.getLoanCode();
		ApplyDetailInfokEx applyDetailInfokEx = carCheckService.getCheckInfo(loanCode);
		model.addAttribute("applyDetailInfokEx", applyDetailInfokEx);
		return "carloan/view/applyDetailView";
	}
	
	/**
	 * 通过借款编号loanCode获取审批历史
	 * 2016年1月25日
	 * By 申诗阔
	 * @param model
	 * @param verifyParamEx
	 * @return 审批历史
	 */
	@RequestMapping(value="checkHistory")
	public String checkHistory(HttpServletRequest request,
			HttpServletResponse response, Model model, LoanStatusHis loanStatusHis) {
		Page<LoanStatusHis> page = new Page<LoanStatusHis>(request, response);
		//数据库查询列表数据
		page = loanStatusHisService.findByLoanCode(page, loanStatusHis);
		//传递数据到前台页面展示
		model.addAttribute("page", page);
		model.addAttribute("info", loanStatusHis);
		return "carloan/view/checkHistoryView";
	}
	
	/**
	 * 获取复审已办列表
	 * 2016年1月26日
	 * By 李静辉
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getDoneList")
	public String getDoneList(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("auditType", CarLoanSteps.FIRST_AUDIT.getCode());
		this.dealWithList(model, carVerifyListEx, request, response);
		return "carloan/list/checkDoneList";
	}
	
	/**
	 * 获取复审已办列表
	 * 2016年1月26日
	 * By 李静辉
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getFinalDoneList")
	public String getFinalDoneList(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("auditType", CarLoanSteps.RECHECK_AUDIT.getCode());
		this.dealWithList(model, carVerifyListEx, request, response);
		return "carloan/list/checkDoneList";
	}
	
	/**
	 * 获取复审已办列表
	 * 2016年1月26日
	 * By 李静辉
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getFinalRecheckList")
	public String getFinalRecheckList(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		model.addAttribute("auditType", CarLoanSteps.RECHECK_AUDIT.getCode());
		this.getCheckList(model, carVerifyListEx, request, response,CarLoanSteps.RECHECK_AUDIT.getCode());
		return "carloan/list/checkDoneList";
	}
	
	/**
	 * 获取复审未取单
	 * 2016年1月26日
	 * By 李静辉
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getRecheckAuditCount")
	public String getRecheckAuditCount(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		ProcessQueryBuilder paramRe = new ProcessQueryBuilder();
		paramRe.put("dealUser", "");
		FlowPage pageRe = new FlowPage();
		pageRe.setPageSize(50000);
		pageRe.setPageNo(1);
		String recheckQueue = QueueConstants.CAR_VERIFY_RECHECK;
		flowService.fetchTaskItems(recheckQueue, paramRe, pageRe, null, CarLoanFlowTaskItemEntity.class);
		List<BaseTaskItemView> sourceWorkItemsRe = pageRe.getList();
		int count = (sourceWorkItemsRe != null && sourceWorkItemsRe.size() > 0) ? sourceWorkItemsRe.size() : 0;
		return ""+count;
	}
	
	/**
	 * 获取终审未取单
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getFinalAuditCount")
	public String getFinalAuditCount(Model model, CarVerifyListEx carVerifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		ProcessQueryBuilder paramFinal = new ProcessQueryBuilder();
		paramFinal.put("dealUser", "");
		FlowPage pageFinal = new FlowPage();
		pageFinal.setPageSize(50000);
		pageFinal.setPageNo(1);
		String finalcheckQueue = QueueConstants.CAR_VERIFY_FINAL_CHECK;
		flowService.fetchTaskItems(finalcheckQueue, paramFinal, pageFinal, null, CarLoanFlowTaskItemEntity.class);
		List<BaseTaskItemView> sourceWorkItemsFinal = pageFinal.getList();
		int count = (sourceWorkItemsFinal != null && sourceWorkItemsFinal.size() > 0) ? sourceWorkItemsFinal.size() : 0;
		return ""+count;
	}

	/**
	 * 抽出复审和终审共通方法
	 * 2016年1月26日
	 * By 申诗阔
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
		Page<CarVerifyListEx> page = carCheckService.findPage(new Page<CarVerifyListEx>(request, response), map);
		model.addAttribute("carVerifyListEx", carVerifyListEx);
		model.addAttribute("provinces", provinces);
		model.addAttribute("page", page);
	}
	
	/**
	 * 抽出复审和终审共通方法
	 * 2016年1月26日
	 * By 申诗阔
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
			Page<CarVerifyListEx> page = carCheckService.findPage(new Page<CarVerifyListEx>(request, response), map);
			model.addAttribute("carVerifyListEx", carVerifyListEx);
			model.addAttribute("provinces", provinces);
			model.addAttribute("page", page);
			return page.getList().size();
		}else if(type.equals(CarLoanSteps.FIRST_AUDIT.getCode())){
			int count = carCheckService.findCount(map);
			return count;
		}
		return 0;
	}
	
	/**
	 * 延迟处理
	 * 2016年2月15日
	 * By 申诗阔
	 * @param workItem
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="asynDelay")
	public String asynCarDelay(WorkItemView workItem, CarVerifyBusinessView view) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("trackState", Constant.FLOW_TASK_STATE_DELAY);
			CarLoanInfo carLoanInfo = carLoanInfoService.findByLoanCode(view.getLoanCode());
			if (YESNO.YES.getCode().equals(carLoanInfo.getIsextension())) {
				map.put("applyStatusCode", CarLoanStatus.DELAY.getCode());
			} else {
				map.put("dictStatus", CarLoanStatus.DELAY.getCode());
			}
			view.setDictStatus(CarLoanStatus.DELAY.getCode());
			workItem.setBv(view);
			flowService.saveData(workItem.getWobNum(), map);
			carCheckService.delayHandle(workItem);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 车借--复审、终审取单
	 * 2016年4月18日
	 * By 申诗阔
	 * @param workItem
	 * @param view
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getOrderByAuditType")
	public String getOrderByAuditType(CarOrderBusinessView view) {
		int num = 5;
		
		ProcessQueryBuilder param = new ProcessQueryBuilder();
		param.put("dealUser", UserUtils.getUser().getLoginName());
		
		String auditType = view.getAuditType();
		String queue = "";
		if (CarLoanSteps.RECHECK_AUDIT.getCode().equals(auditType)) { // 复审取单
			queue = QueueConstants.CAR_VERIFY_RECHECK;
		} else if (CarLoanSteps.FINAL_AUDIT.getCode().equals(auditType)) { // 终审取单
			queue = QueueConstants.CAR_VERIFY_FINAL_CHECK;
		}
		
		// 获取工作流任务
		FlowPage page = new FlowPage();
		page.setPageSize(50000);
        page.setPageNo(1);
        flowService.fetchTaskItems(queue, param, page, null, CarLoanFlowTaskItemEntity.class);
		List<CarLoanFlowTaskItemEntity> itemList = null;
		List<BaseTaskItemView> sourceWorkItems = page.getList();
		itemList = this.convertList(sourceWorkItems);
		String flag = BooleanType.FALSE;
		if (itemList != null && itemList.size() < num) {
			// 取单
			param = new ProcessQueryBuilder();
			param.put("dealUser", "");
			FlowPage pageGet = new FlowPage();
			pageGet.setPageSize(50000);
			pageGet.setPageNo(1);
			flowService.fetchTaskItems(queue, param, pageGet, null, CarLoanFlowTaskItemEntity.class);
			List<CarLoanFlowTaskItemEntity> itemListGet = null;
			List<BaseTaskItemView> sourceWorkItemsGet = pageGet.getList();
			itemListGet = this.convertList(sourceWorkItemsGet);
			if (itemListGet != null && itemListGet.size() > 0) {
				// 可取单
				CarLoanFlowTaskItemEntity item = itemListGet.get(0);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("dealUser", UserUtils.getUser().getLoginName());
				flowService.saveDataByApplyId(item.getApplyId(), map);
				flag = BooleanType.TRUE;
			} else {
				// 无单可取
				flag = "empty";
			}
		}
		return flag;
	}

	/**
	 * 将流程中查询出来的数据类型进行转封装
	 * 2016年4月21日
	 * By 申诗阔
	 * @param sourceWorkItems
	 * @return
	 */
    private List<CarLoanFlowTaskItemEntity> convertList(
            List<BaseTaskItemView> sourceWorkItems) {
        List<CarLoanFlowTaskItemEntity> targetList = new ArrayList<CarLoanFlowTaskItemEntity>();
        if (!ObjectHelper.isEmpty(sourceWorkItems)) {
            for (BaseTaskItemView currItem : sourceWorkItems)
                targetList.add((CarLoanFlowTaskItemEntity) currItem);
        }
        return targetList;
    }

	/**
	 * 根据借款编码获取费率对应的关系表
	 * By zqa
	 * @param loanCode  借款编码
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "asynLoadFirstServiceRate", method = RequestMethod.POST)
	public String asynLoadFirstServiceRate(String loanCode){
		CarLoanInfo carLoanInfo = carLoanInfoService.findByLoanCode(loanCode); // 借款信息 
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
}
