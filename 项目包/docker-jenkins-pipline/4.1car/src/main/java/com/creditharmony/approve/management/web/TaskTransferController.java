package com.creditharmony.approve.management.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.type.RoleType;
import com.creditharmony.approve.management.entity.TaskTransferInfo;
import com.creditharmony.approve.management.service.JkEntrustService;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.entity.ApproveQueryEntity;
import com.creditharmony.approve.workflow.entity.VerifyFlowTaskItemEntity;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.entity.ex.EntrustEx;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.persistence.BaseEntity;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.service.UserManager;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.query.ProcessAndQueryBuilderContainer;
import com.query.ProcessOrQueryBuilderContainer;
import com.query.ProcessQueryBuilder;


/**
 * 转签列表
 * 
 * @Class Name TaskTransferController
 * @author 王浩
 * @Create In 2015年12月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/taskTransfer")
public class TaskTransferController extends BaseController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private JkEntrustService entrustService;	
	
	@Resource(name="appFrame_flowServiceImpl")
	private FlowService flowService;
	
	/**
	 * 获取某个队列的所有待办列表
	 * 2015年12月15日
	 * By 王浩 
	 * @param model
	 * @param queryEntity
	 * @return 返回跳转页面的路径
	 */
	@RequestMapping(value="fetchTaskItems")
	protected String fetchTaskItems(Model model, ApproveQueryEntity queryEntity, TaskTransferInfo transInfo) {
		// 获取当前登录用户
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		// 没有选择用户名，不做任何操作，直接返回页面
		if (StringUtils.isNotEmpty(transInfo.getOrigin())) {
			// 通过工具类把查询的实体转换为对应的ProcessQueryBuilder参数
			ProcessQueryBuilder qryParam = new ProcessQueryBuilder();
			qryParam.put(Constant.FLOW_FRAME_FIELD_DEAL_USER, transInfo.getOrigin());
			qryParam.put(Constant.FLOW_FRAME_FIELD_TRACK_STATE + "@<>", Constant.FLOW_TASK_STATE_POOL); // 已取单列表
			
			//或查询的条件
			ProcessQueryBuilder childOrgQuery =  new ProcessQueryBuilder();
			ProcessQueryBuilder sameOrgQuery =  new ProcessQueryBuilder();
			// 默认只能查询当前登录用户下属部门或同部门用户的待办
			if (user.getDepartment() != null
					&& StringUtils.isNotEmpty(user.getDepartment().getId())) {		
				// 子机构
				childOrgQuery.put(Constant.FLOW_FRAME_FIELD_DEAL_USER_ORG_PARENTIDS + "@like", "%" + user.getDepartment().getId() + "%");
				// 同机构
				sameOrgQuery.put(Constant.FLOW_FRAME_FIELD_DEAL_USER_ORG, user.getDepartment().getId());
			}		
			// // 子机构或同机构 查询
			ProcessOrQueryBuilderContainer orQuery = new ProcessOrQueryBuilderContainer();
			orQuery.addQueryBuilder(childOrgQuery);
			orQuery.addQueryBuilder(sameOrgQuery);
			
			// and 查询
			ProcessAndQueryBuilderContainer finalQuery = new ProcessAndQueryBuilderContainer();			
			finalQuery.addQueryBuilder(qryParam);
			finalQuery.addQueryBuilder(orQuery);			
			
			FlowPage page = new FlowPage();
			page.setPageSize(30);
			
			List<BaseTaskItemView> itemList = null;					
			try {
				// 获取工作流任务
				flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, finalQuery, page, null,
								VerifyFlowTaskItemEntity.class);				
				if (page.getList() != null) {
					itemList = page.getList();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new ServiceException(e);
			}
			model.addAttribute("itemList", itemList);			
			model.addAttribute("page", page);
			model.addAttribute("currInfo", transInfo);
		}		
		return "/management/taskTransferList";
		
	}		
		
	/**
	 * 准备数据，申请的当前用户的信息
	 * 2015年12月15日
	 * By 王浩
	 * @param model
	 * @param transInfo
	 * @return 
	 */
	@RequestMapping(value = "form")
	public String initForm(Model model, TaskTransferInfo transInfo) {			
		model.addAttribute("transInfo", transInfo);				
		return "/management/taskTransferForm";		
	}
	
	/**
	 * 将待办任务，转签给另外一个用户
	 * 2015年12月15日
	 * By 王浩
	 * @param info
	 * @param model
	 * @return 返回成功标识
	 */
	@ResponseBody
	@RequestMapping(value="save") 
	public String saveTransferInfo(TaskTransferInfo info, Model model) {
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();			
			if (StringUtils.isNotEmpty(info.getAcceptUID())) {				
				User acceptUser = userManager.get(info.getAcceptUID());
				if (acceptUser != null) {
					// 获取新选择用户所在的组织机构
					Org currentOrg = acceptUser.getDepartment();
					if (currentOrg != null) {
						// 设置新处理用户的组织机构编码
						map.put(Constant.FLOW_FRAME_FIELD_DEAL_USER_ORG, currentOrg.getId());
						// 设置新处理用户的所有父节点组织机构编码
						map.put(Constant.FLOW_FRAME_FIELD_DEAL_USER_ORG_PARENTIDS,
								currentOrg.getParentIds());
					}
					// 设置新处理用户
					map.put(Constant.FLOW_FRAME_FIELD_DEAL_USER, info.getAcceptUID());
					
					if (info.getWobNum() != null && info.getWobNum().length > 0) {
						String[] wobNum = info.getWobNum();
						for (int i = 0; i < wobNum.length; i++) {
							flowService.saveData(wobNum[i], map);
						}
					} else {
						return "数据有误，请重新输入";
					}
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	
		return BooleanType.TRUE;
	}
	
	/**
	 * 获取树形用户数据
	 * 2016年1月2t日
	 * By 王浩
	 * @param info
	 * @param response
	 * @return 将查询数据封装成List<Map>后返回页面
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String params,
			HttpServletResponse response) {		

		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<User> userList = null;
		Map<String, Object> filter = Maps.newHashMap();
		String currentUID = "";
		if (StringUtils.isNotEmpty(params)) {
			String[] param = params.replace(" ", "").split("-");
			currentUID = param[0];
		}
		
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		String userDeptId = userInfo.getDepartment().getId();
		filter.put("delFlag", BaseEntity.DEL_FLAG_NORMAL);
		filter.put("departmentId", userDeptId);
		
		if (StringUtils.isNotEmpty(currentUID)) {
			filter.put("exceptUserId", currentUID);
			// 查询等级与当前用户同级的用户
			EntrustEx currUserEntrust = entrustService.getCurrentUser(filter);
			if (currUserEntrust != null && currUserEntrust.getLevel() != null
					&& RoleType.APPROVE_VERIFY_CHECK.getName().equals(currUserEntrust.getRoleType())) {
				// 如果是信审初审，加入等级判定的内容					
				filter.put("checkLevel", currUserEntrust.getLevel());
			}
			userList = entrustService.findOtherByPrevUser(filter);
		} else {
			userList = entrustService.findUserByRole(filter);
		}

		for (int i = 0; i < userList.size(); i++) {
			User e = userList.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getId());
			map.put("ucode", e.getUserCode());
			map.put("name", StringUtils.replace(e.getName(), " ", ""));
			mapList.add(map);
		}

		return mapList;
	}
			
}
