package com.creditharmony.approve.management.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.base.web.ApproveController;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.entity.PageWorkFlow;
import com.creditharmony.approve.common.util.ApproveUtils;
import com.creditharmony.approve.common.view.SearchParam;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.util.DictToMapUtil;
import com.creditharmony.approve.workflow.constants.QueueConstants;
import com.creditharmony.approve.workflow.entity.VerifyFlowTaskItemEntity;
import com.creditharmony.bpm.frame.utils.Constant;
import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.common.util.StringUtils;
import com.query.ProcessQueryBuilder;

/**
 * 该部门下所有的未取单列表
 * @Class Name PoolTaskController
 * @author 刘燕军
 * @Create In 2016年3月17日
 */
@Controller
@RequestMapping(value = "${adminPath}/management/pool")
public class PoolTaskController extends ApproveController{
	@RequestMapping(value="getTaskItems")
	protected String getTaskItems(Model model,SearchParam param,PageWorkFlow page) {
		ProcessQueryBuilder queryParam =  ApproveUtils.getQuery(param);
		queryParam.put(Constant.FLOW_FRAME_FIELD_TRACK_STATE, Constant.FLOW_TASK_STATE_POOL); // 所有未分配的
		// 获取工作流任务
		flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, queryParam,page,null,VerifyFlowTaskItemEntity.class);
		List<BaseTaskItemView> itemList = null;
		// 把任务放入到list中的并返回
		if (page.getList() != null) {
			itemList = page.getList();
		}
		List<CityInfo> provinces = cityInfoService.findProvince();
		List<JkProducts> allProduct = getAllProducts();
		Map<String, String> map = DictToMapUtil.getList(ApproveConstants.POOL_STATUS,ApproveConstants.LOAN_APPLY_STATUS);
		model.addAttribute("page", page);
		model.addAttribute("map", map);
		model.addAttribute("itemList", itemList);
		model.addAttribute("search", param);
		model.addAttribute("provinces", provinces);
		model.addAttribute("allProduct", allProduct);
		return "task/taskListView";
	}
	
		/**
		 * 根据条件查询未取单列表
		 * @Class Name PoolTaskController
		 * @author 刘燕军
		 * @Create In 2016年3月17日
		 */
		@RequestMapping(value="getOneTaskItemByCondition")
		protected String getOneTaskItemByCondition(Model model,SearchParam param,PageWorkFlow page) {
			List<BaseTaskItemView> itemList = null;
			if(StringUtils.isNotEmpty(param.getLoanCode()) || StringUtils.isNotEmpty(param.getIdentityCode()) || StringUtils.isNotEmpty(param.getCustomerName())){
				ProcessQueryBuilder queryParam =  ApproveUtils.getQuery(param);
				queryParam.put(Constant.FLOW_FRAME_FIELD_TRACK_STATE, Constant.FLOW_TASK_STATE_POOL); // 所有未分配的
				// 获取工作流任务
				flowService.fetchTaskItems(QueueConstants.VERIFY_CHECK, queryParam,page,null,VerifyFlowTaskItemEntity.class);
				// 把任务放入到list中的并返回
				if (page.getList() != null) {
					itemList = page.getList();
				}
			}
			model.addAttribute("page", page);
			model.addAttribute("itemList", itemList);
			model.addAttribute("param", param);
			return "management/oneTaskListView";
		}
}
