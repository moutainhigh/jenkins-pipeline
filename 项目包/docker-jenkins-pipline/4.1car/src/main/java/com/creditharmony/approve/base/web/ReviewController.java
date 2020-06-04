package com.creditharmony.approve.base.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.common.type.BooleanType;

/**
 * 初审处理以外抽象类
 * @Class Name ReviewController
 * @author xiaoniu.hu
 * @Create In 2016年1月7日
 */
public abstract class ReviewController extends ApproveController {	
	
	public ReviewController(String argCheckType,String argStepName,String argFlowName) {
		super(argCheckType,argStepName,argFlowName);
	}

	/**
	 * 退回协商(路由：TO_PREVIOUS)
	 * 2016年1月7日
	 * xiaoniu.hu
	 * @param workItem 工作流工作项实体
	 * @param view 业务对象实体
	 * @return TURE:成功;FALSE:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynBackPrevious")
	public String asynBackPrevious(WorkItemView workItem,VerifyBusinessView view){
		try {
			workItem.setResponse(ApproveRouteConstants.CHECK_TO_PREVIOUS);
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
}
