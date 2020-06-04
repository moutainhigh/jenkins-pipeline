package com.creditharmony.approve.base.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.common.service.CompletedListService;
import com.creditharmony.approve.common.service.SystemSettingService;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.dao.ApproveDictDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.approve.verify.service.CheckService;
import com.creditharmony.approve.verify.service.ReconsiderFinalExamineService;
import com.creditharmony.approve.workflow.constants.FieldConstants;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.entity.VerifyFlowTaskItemEntity;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.query.ProcessQueryBuilder;

/**
 * 汇诚待办已办抽象类
 * @Class Name ApproveController
 * @author xiaoniu.hu
 * @Create In 2016年1月7日
 */
public abstract class ApproveController extends BaseController {	
	
	/**
	 * FlowService 查询流程待办列表、提交流程 
	 */
	@Resource(name="appFrame_flowServiceImpl")
	protected FlowService flowService;
	@Autowired
	ReconsiderFinalExamineService reconsiderFinalExamineService;
	@Autowired
	private CompletedListService completedListService;
	@Autowired
	protected CityInfoService cityInfoService;
	@Autowired
	private ApproveDictDao dictDao;
	@Autowired
	private CheckService checkService;
	@Autowired
	protected SystemSettingService systemSetMaterService;
	@Autowired
	private StatusChangeRecordDao statusChangeRecordDao;
	
	private String approveCheckType; //审批类型
	private String stepName;         //工作流步骤
	private String flowName;         //流程名称
	/**
	 * 无参构造函数
	 */
	public ApproveController(){
	}
	
	/**
	 * 有参构造函数
	 * @param argCheckType 审批类型 (0:信审;1:复议)
	 * @param argStepName 工作流步骤
	 */
	public ApproveController(String argCheckType,String argStepName,String argFlowName){
		this.approveCheckType=argCheckType;
		this.stepName=argStepName;
		this.flowName=argFlowName;
	}
	
	/**
	 * 获取待办
	 * 2015年12月28日
	 * xiaoniu.hu
	 * @param model 
	 * @return 获取待办列表 抽象父类
	 */
	@RequestMapping(value="fetchTaskItems")
	protected String fetchTaskItems(Model model)
	{
		// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.setFlowName(flowName);
		queryParam.put(FieldConstants.STEP_NAME, stepName);
		// 获取工作流任务
		FlowPage page=new FlowPage();
		page.setPageSize(30);
		flowService.fetchTaskItems(
				QueueConstants.VERIFY_CHECK, queryParam,page,
				Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_HUICHENG_ORDER_QUEUING,
				VerifyFlowTaskItemEntity.class);
		model.addAttribute("itemList", page.getList());
		return "task/taskList";		
	}
	
	/**
	 * 延时处理
	 * 2016年1月19日
	 * xiaoniu.hu
	 * @param workItem
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="asynDelay")
	public String asynDelay(WorkItemView workItem){
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("trackState", Constant.FLOW_TASK_STATE_DELAY);
			flowService.saveData(workItem.getWobNum(), map);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 获取已办列表
	 * 2015年12月28日
	 * xiaoniu.hu
	 * @param model
	 * @param verifyListEntity
	 * @param request
	 * @param response
	 * @return 已办列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="getDoneList")
	protected String getDoneList(Model model, VerifyListEx verifyListEntity,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = jsonMapper.convertValue(verifyListEntity, Map.class);
		map.put("minCustomerIntoTime", verifyListEntity.getMinCustomerIntoTime());
		map.put("maxCustomerIntoTime", verifyListEntity.getMaxCustomerIntoTime());
		map.put("minLoanAuditTime", verifyListEntity.getMinLoanAuditTime());
		map.put("maxLoanAuditTime", verifyListEntity.getMaxLoanAuditTime());
		map.put("checkType", approveCheckType);
		List<CityInfo> provinces = cityInfoService.findProvince();
		User user = UserUtils.getUser();
		// 当前登录人员的code值，只能查询自己办理的列表，不传则查询全部
		map.put("transactorCode",user.getId());
		Page<VerifyListEx> page = completedListService.findPage(new Page<VerifyListEx>(request, response), map);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("type", "jk_loan_step");
		List<JkProducts> allProduct = getAllProducts();
		model.addAttribute("verifyListEntity", verifyListEntity);
		model.addAttribute("allProduct", allProduct);
		model.addAttribute("page", page);
		model.addAttribute("provinces", provinces);
		return "task/completedList";
	}
	
	/**
	 * 获取拒绝码
	 * 2015年12月15日
	 * By 李文勇
	 * @param parentId
	 * @return 返回指定的拒绝码
	 */
	protected List<GlRefuse> getRefuseCode( String parentId ){
		List<GlRefuse> list = reconsiderFinalExamineService.getRefuseCode( parentId );
		return list;
	} 
	
	/**
	 * 获取全部产品
	 * 2015年12月22日
	 * By 李文勇
	 * @param id
	 * @return 返回所有的产品
	 */
	protected List<JkProducts> getAllProducts(){
		return reconsiderFinalExamineService.getAllProducts();
	}
	
	/**
	 * 根据父value和类型获取各个节点的状态
	 * 2016年1月14日
	 * By 赖敏
	 * @param parentValue 父value
	 * @return 状态字典列表
	 */
	protected List<Dict> getByParentValue(String parentValue){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("parentValue", parentValue);
		params.put("type", "jk_check_type");
		return dictDao.getByParentId(params);
	}
	
	/**
	 * 根据借款编号查询借款信息
	 * 2016年3月2日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	protected LoanInfo getkinInfo(String loanCode){
		LoanInfo result = checkService.getkinInfo(loanCode);
		return result;
	}
	

	/**
	 * 获取所有的产品分期
	 * 2016年4月22日
	 * By 刘燕军
	 * @return 产品分期对应的list
	 */
	@ResponseBody
	@RequestMapping(value = "findAllMonths")
	public List<Integer> findAllMonths(String consultFlag){
		List <Integer> list = null;
		try{
			list = checkService.getAllMonths(consultFlag);			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 获取分期对应的所有的费率
	 * 2016年4月22日
	 * By 刘燕军
	 * @return 产品分期对应的list
	 */
	@ResponseBody
	@RequestMapping(value = "getRate")
	public List<Double> getRate(int months){
		List <Double> list = null;
		try{
			list = checkService.getOneTypeRate(months);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 根据分期和风险等级，获取对应的所有的费率
	 * 2016年7月6日
	 * By 王浩
	 * @return 产品分期对应的list
	 */
	@ResponseBody
	@RequestMapping(value = "getRateByRisk")
	public List<Double> getRateNew(int months, String riskLevel){
		List <Double> list = null;
		try{
			list = checkService.getRateByRisk(months, riskLevel);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 根据借款编号查看是否有进入确认签署节点
	 * 2016年7月26日
	 * By 刘燕军
	 * @param loanCode
	 * @return 是否有进入
	 */
	protected boolean getFlag(String loanCode){
		// 获取进入确认签署节点的数量
		int result = statusChangeRecordDao.getFlag(loanCode);
		
		return result == 0 ? true :false;
	}
	
}
