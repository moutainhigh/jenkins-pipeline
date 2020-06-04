package com.creditharmony.approve.antifraud.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeEx;
import com.creditharmony.approve.antifraud.service.AntiFraudJudgeService;
import com.creditharmony.approve.antifraud.service.AntiFraudCompletedListService;
import com.creditharmony.approve.base.web.ApproveController;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.entity.ex.VerifyListEx;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.constants.FieldConstants;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.constants.StepNameConstants;
import com.creditharmony.approve.workflow.entity.VerifyFlowTaskItemEntity;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.config.FlowInfoDefinitionConfig;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.approve.type.BlackGreyList;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.loan.type.LoanApplyStatus;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.query.ProcessQueryBuilder;

/**
 * 反欺诈专员
 * @Class Name AntifraudWorkFlowController
 * @author wanglidong
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/antifraud/manager")
public class AntifraudWorkFlowController extends ApproveController {
	@Autowired
	private AntiFraudCompletedListService antiFraudCompletedListService;
	@Autowired
	private CityInfoService cityInfoService;
	@Autowired
	private AntiFraudJudgeService antiFraudJudgeService;
	
	/**
	 * 重写获取待办
	 */
	@Override
	protected String fetchTaskItems(Model model) {
		ProcessQueryBuilder queryParam = new ProcessQueryBuilder();
		queryParam.setFlowName(FlowInfoDefinitionConfig.getInstance().getFlowInfoConfigBeanByCode("loanflow").getFlowName());
		queryParam.put(FieldConstants.STEP_NAME, StepNameConstants.ANTIFRAUD_CHECK);
		// 获取工作流任务
		FlowPage page=new FlowPage();
		page.setPageSize(30);
		flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, queryParam,page,Constant.FLOW_FRAME_QUEUE_FETCH_MODEL_HUICHENG_ORDER_QUEUING,
				VerifyFlowTaskItemEntity.class);
		List<BaseTaskItemView> itemList = null;
		// 把任务放入到list中的并返回
		if (page.getList() != null) {
			itemList = page.getList();
		}
		model.addAttribute("itemList", itemList);
		return "/antifraud/antifraudFetchTaskItems";
	}

	/**
	 * 处理流程提交的action, wobNum,flowName,stepName属必输项，该字段值从openform传递过来，
	 * token预留字段,handleParam 页面输入的非流程数据,也可更新流程属性
	 * 2015年12月22日
	 * By wanglidong
	 * @param view 反欺诈决策扩展类
	 * @param workItem	工作流任务视图类
	 * @return 成功返回ture失败返回false
	 */
	@ResponseBody
	@RequestMapping(value="asynToCheck")
	public String asynToCheck(WorkItemView workItem,VerifyBusinessView view){
		try {
			AntiFraudJudgeEx antiFraudJudgeEx = view.getAntiFraudJudgeEx();
			String loanStatus =antiFraudJudgeService.getLoanStatus(view.getLoanCode());
			antiFraudJudgeEx.setLoanStatus(loanStatus);
			if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.INNOCENT_CASE.getCode()) && !LoanApplyStatus.SYS_FILTER_REJECT_ANTIFRAUD.getCode().equals(loanStatus)){
				// 反欺诈提交清白件
				workItem.setResponse(ApproveRouteConstants.ANTIFRAUD_TO_CHECK);
				workItem.setBv(view);
				flowService.dispatch(workItem);
			}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.INNOCENT_CASE.getCode()) && LoanApplyStatus.SYS_FILTER_REJECT_ANTIFRAUD.getCode().equals(loanStatus)){
				// 反欺诈提交清白件、系统过来拒
				workItem.setResponse(ApproveRouteConstants.ANTIFRAUD_TO_END);
				workItem.setBv(view);
				flowService.dispatch(workItem);
			}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.BLACK_LIST.getCode())){
				// 反欺诈提交黑名单
				workItem.setResponse(ApproveRouteConstants.ANTIFRAUD_TO_END);
				workItem.setBv(view);
				flowService.dispatch(workItem);
			}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.GREY_LIST.getCode())){
				// 反欺诈提交灰名单
				workItem.setResponse(ApproveRouteConstants.ANTIFRAUD_TO_END);
				workItem.setBv(view);
				flowService.dispatch(workItem);				
			}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.FALL_BACK.getCode())){
				// 反欺诈退回初审
				workItem.setResponse(ApproveRouteConstants.ANTIFRAUD_BACK_CHECK);
				workItem.setBv(view);
				flowService.dispatch(workItem);				
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 处理流程提交的action, wobNum,flowName,stepName属必输项，该字段值从openform传递过来，
	 * token预留字段,handleParam 页面输入的非流程数据,也可更新流程属性
	 * 2015年12月22日
	 * By 赖敏
	 * @param verifyListEx 查询条件扩展类
	 * @param request 请求
	 * @param response	响应
	 * @return 返回已办列表
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected String getDoneList(Model model, VerifyListEx verifyListEx,
			HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = jsonMapper.convertValue(verifyListEx, Map.class);
		// 查询最小时间
		map.put("minCustomerIntoTime", verifyListEx.getMinCustomerIntoTime());
		// 查询最大时间
		map.put("maxCustomerIntoTime", verifyListEx.getMaxCustomerIntoTime());
		User user = UserUtils.getUser();
		// 当前登录人员的code值，只能查询自己办理的列表，不传则查询全部
		map.put("transactorCode", user.getId());
		Page<VerifyListEx> page = antiFraudCompletedListService.findPage(new Page<VerifyListEx>(request,response), map);
		List<CityInfo> provinces = cityInfoService.findProvince();
		List<JkProducts> products = getAllProducts();
		model.addAttribute("page", page);
		model.addAttribute("provinces", provinces);
		model.addAttribute("products", products);
		model.addAttribute("verifyListEx", verifyListEx);//getCreditCompleted
		return "/antifraud/commissionerCompletedList";
	}
}
