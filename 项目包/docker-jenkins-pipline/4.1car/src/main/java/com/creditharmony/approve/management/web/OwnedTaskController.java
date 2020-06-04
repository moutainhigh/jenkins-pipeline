package com.creditharmony.approve.management.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.base.web.ApproveController;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.dao.CompletedListDao;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.entity.PageWorkFlow;
import com.creditharmony.approve.common.util.ApproveUtils;
import com.creditharmony.approve.common.view.SearchParam;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.util.DictToMapUtil;
import com.creditharmony.approve.workflow.constants.FieldConstants;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.entity.VerifyFlowTaskItemEntity;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.cache.UserCache;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.role.type.ApproveRole;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.query.ProcessAndQueryBuilderContainer;
import com.query.ProcessOrQueryBuilderContainer;
import com.query.ProcessQueryBuilder;

/**
 * 获取该部门下所有的待办信息
 * @Class Name OwnedTaskController
 * @author 刘燕军
 * @Create In 2016年3月17日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/owned")
public class OwnedTaskController extends ApproveController{
	
	@Autowired
	private CompletedListDao listDao;
	
	/**
	 * 待办列表查询
	 * 2016年3月14日
	 * By 刘燕军
	 * @param model
	 * @return 某角色下所有的待办信息
	 */
	@RequestMapping(value="getTaskItems")
	protected String getTaskItems(Model model,SearchParam param,PageWorkFlow page ) {
		// 把查询实体转换为查询队列格式
		ProcessQueryBuilder queryParam =  ApproveUtils.getQuery(param);
		// or 类型的队列
		ProcessOrQueryBuilderContainer orQuery = new ProcessOrQueryBuilderContainer();
		// and 类型的队列
		ProcessAndQueryBuilderContainer anQuery = new ProcessAndQueryBuilderContainer();
		// 用户查询队列
		ProcessQueryBuilder userQuery1 =  new ProcessQueryBuilder();
		ProcessQueryBuilder userQuery2 =  new ProcessQueryBuilder();
		queryParam.put(Constant.FLOW_FRAME_FIELD_TRACK_STATE + "@<>", Constant.FLOW_TASK_STATE_POOL); // 已取单列表
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		List<Role> list = user.getRoleList();
		String Roles = "";
		for (Role role : list) {
			Roles = Roles+","+role.getId();
		}
		userQuery1.put(FieldConstants.DEAL_USER_ORGID, user.getDepartment().getId());
		userQuery2.put(FieldConstants.DEAL_USER_ORG_PARENTIDS +"@LIKE", "%" + user.getDepartment().getId()+"%");
		orQuery.addQueryBuilder(userQuery1);
		orQuery.addQueryBuilder(userQuery2);
		anQuery.addQueryBuilder(queryParam);
		anQuery.addQueryBuilder(orQuery);
		// 获取工作流任务
			flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, anQuery,page,null,VerifyFlowTaskItemEntity.class);
			List<BaseTaskItemView> itemList = null;
			// 把任务放入到list中的并返回
			if (page.getList() != null) {
				itemList = page.getList();
			}
		model.addAttribute("itemList", itemList);
		Map<String, String> map = null;
		if(Roles.contains(ApproveRole.APPROVE_VERIFY_MANAGER.id) || Roles.contains(ApproveRole.APPROVE_VERIFY_TEAM_LEADER.id)){
			map = DictToMapUtil.getList(ApproveConstants.VERIFY_STATUS, ApproveConstants.LOAN_APPLY_STATUS);
		}else{
			map = DictToMapUtil.getList(ApproveConstants.RECONSIDER_STATUS, ApproveConstants.LOAN_APPLY_STATUS);
		}
		List<CityInfo> provinces = cityInfoService.findProvince();
		List<JkProducts> allProduct = getAllProducts();
		model.addAttribute("page", page);
		model.addAttribute("search", param);
		model.addAttribute("provinces", provinces);
		model.addAttribute("map", map);
		model.addAttribute("allProduct", allProduct);
		return "task/taskListView";
	}
	
	/**
	 * 待办列表查询
	 * 2016年3月14日
	 * By 刘燕军
	 * @param model
	 * @return 所有的待办信息
	 */
	@RequestMapping(value="getTaskItemsAll")
	protected String getTaskItemsAll(Model model,SearchParam param,PageWorkFlow page ) {
		// 把查询实体转换为查询队列格式
		ProcessQueryBuilder queryParam =  ApproveUtils.getQuery(param);
		// or 类型的队列
		ProcessOrQueryBuilderContainer orQuery = new ProcessOrQueryBuilderContainer();
		// and 类型的队列
		ProcessAndQueryBuilderContainer anQuery = new ProcessAndQueryBuilderContainer();
		// 用户查询队列
		ProcessQueryBuilder userQuery1 =  new ProcessQueryBuilder();
		queryParam.put(Constant.FLOW_FRAME_FIELD_TRACK_STATE + "@<>", Constant.FLOW_TASK_STATE_POOL); // 已取单列表
		userQuery1.put(FieldConstants.DEAL_USER + "@<>", "");
		orQuery.addQueryBuilder(userQuery1);
		anQuery.addQueryBuilder(queryParam);
		anQuery.addQueryBuilder(orQuery);
		// 获取工作流任务
			flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, anQuery,page,null,VerifyFlowTaskItemEntity.class);
			List<BaseTaskItemView> itemList = null;
			// 把任务放入到list中的并返回
			if (page.getList() != null) {
				itemList = page.getList();
			}
		model.addAttribute("itemList", itemList);
		Map<String, String> map = null;
		map = DictToMapUtil.getMap(ApproveConstants.LOAN_APPLY_STATUS);
		List<CityInfo> provinces = cityInfoService.findProvince();
		List<JkProducts> allProduct = getAllProducts();
		model.addAttribute("page", page);
		model.addAttribute("search", param);
		model.addAttribute("provinces", provinces);
		model.addAttribute("map", map);
		model.addAttribute("allProduct", allProduct);
		return "task/taskListView";
	}
	/**
	 * 反欺诈经理所有 待办列表查询
	 * 2016年3月14日
	 * By 刘燕军
	 * @param model
	 * @return 所有的待办信息
	 */
	@RequestMapping(value="getAntifraudTaskItems")
	protected String getAntifraudTaskItems(Model model,SearchParam param,PageWorkFlow page ) {
		// 把查询实体转换为查询队列格式
		ProcessQueryBuilder queryParam =  ApproveUtils.getQuery(param);
		// or 类型的队列
		ProcessOrQueryBuilderContainer orQuery = new ProcessOrQueryBuilderContainer();
		// and 类型的队列
		ProcessAndQueryBuilderContainer anQuery = new ProcessAndQueryBuilderContainer();
		// 用户查询队列
		ProcessQueryBuilder userQuery1 =  new ProcessQueryBuilder();
		ProcessQueryBuilder userQuery2 =  new ProcessQueryBuilder();
		queryParam.put(Constant.FLOW_FRAME_FIELD_TRACK_STATE + "@<>", Constant.FLOW_TASK_STATE_POOL); // 已取单列表
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		List<Role> list = user.getRoleList();
		String Roles = "";
		for (Role role : list) {
			Roles = Roles+","+role.getId();
		}
		userQuery1.put(FieldConstants.DEAL_USER_ORGID, user.getDepartment().getId());
		userQuery2.put(FieldConstants.DEAL_USER_ORG_PARENTIDS +"@LIKE", "%" + user.getDepartment().getId());
		orQuery.addQueryBuilder(userQuery1);
		orQuery.addQueryBuilder(userQuery2);
		anQuery.addQueryBuilder(queryParam);
		anQuery.addQueryBuilder(orQuery);
		// 获取工作流任务
		flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, anQuery,page,null,VerifyFlowTaskItemEntity.class);
		List<BaseTaskItemView> itemList = null;
		// 把任务放入到list中的并返回
		if (page.getList() != null) {
			itemList = page.getList();
		}
		model.addAttribute("itemList", itemList);
		Map<String, String> map = null;
		if(Roles.contains(ApproveRole.APPROVE_VERIFY_MANAGER.id) || Roles.contains(ApproveRole.APPROVE_VERIFY_TEAM_LEADER.id)){
			map = DictToMapUtil.getList(ApproveConstants.VERIFY_STATUS, ApproveConstants.LOAN_APPLY_STATUS);
		}else{
			map = DictToMapUtil.getList(ApproveConstants.RECONSIDER_STATUS, ApproveConstants.LOAN_APPLY_STATUS);
		}
		List<CityInfo> provinces = cityInfoService.findProvince();
		List<JkProducts> allProduct = getAllProducts();
		model.addAttribute("page", page);
		model.addAttribute("search", param);
		model.addAttribute("provinces", provinces);
		model.addAttribute("map", map);
		model.addAttribute("allProduct", allProduct);
		return "task/antifraudManagerFetchTaskItems";
	}
	
	/**
	 * 通过id获取对应name
	 * 2016年6月25日
	 * By 刘燕军
	 * @param id
	 * @return 用户名
	 */
	@ResponseBody
	@RequestMapping(value="getUserName")
	public String getUserName(String id){
		User user = UserCache.getInstance().get(id);
		return user==null?null:user.getDepartment().getName();
	}
	/**
	 * 根据条件获取待办列表查询
	 * 2016年3月14日 
	 * By 刘燕军
	 * @param model
	 * @return 所有的待办信息
	 */
	@RequestMapping(value="getOneTaskItemByCondition")
	protected String getOneTaskItemByCondition(Model model,SearchParam param,PageWorkFlow page ) {
		List<BaseTaskItemView> itemList = null;
		if(StringUtils.isNotEmpty(param.getLoanCode()) || StringUtils.isNotEmpty(param.getIdentityCode()) || StringUtils.isNotEmpty(param.getCustomerName())){
			// 把查询实体转换为查询队列格式
			ProcessQueryBuilder queryParam =  ApproveUtils.getQuery(param);
			// or 类型的队列
			ProcessOrQueryBuilderContainer orQuery = new ProcessOrQueryBuilderContainer();
			// and 类型的队列
			ProcessAndQueryBuilderContainer anQuery = new ProcessAndQueryBuilderContainer();
			// 用户查询队列
			ProcessQueryBuilder userQuery1 =  new ProcessQueryBuilder();
			queryParam.put(Constant.FLOW_FRAME_FIELD_TRACK_STATE + "@<>", Constant.FLOW_TASK_STATE_POOL); // 已取单列表
			userQuery1.put(FieldConstants.DEAL_USER + "@<>", "");
			orQuery.addQueryBuilder(userQuery1);
			anQuery.addQueryBuilder(queryParam);
			anQuery.addQueryBuilder(orQuery);
			// 获取工作流任务
			flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, anQuery,page,null,VerifyFlowTaskItemEntity.class);
			// 把任务放入到list中的并返回
			if (page.getList() != null) {
				itemList = page.getList();
			}
		}
		model.addAttribute("itemList", itemList);
		model.addAttribute("page", page);
		model.addAttribute("param", param);
		return "management/oneTaskListView";
	}
}
