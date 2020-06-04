package com.creditharmony.approve.base.web;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.entity.SystemSetting;
import com.creditharmony.approve.workflow.constants.ApproveRouteConstants;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.loan.type.TimeOutFlag;
import com.creditharmony.core.loan.type.YESNO;

/**
 * 初审处理抽象类
 * @Class Name HandleController
 * @author xiaoniu.hu
 * @Create In 2016年1月7日
 */
public abstract class HandleController extends ApproveController {
	
	
	public HandleController(String argCheckType,String argStepName,String argFlowName) {
		super(argCheckType,argStepName,argFlowName);
	}
	
	/**
	 * 提报反欺诈(路由：TO_ANTIFRAUD)
	 * 2016年1月7日
	 * xiaoniu.hu
	 * @param workItem 工作流工作项实体
	 * @param view 业务对象实体
	 * @return TURE:成功;FALSE:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynToAntifraud")
	public String asynToAntifraud(WorkItemView workItem,VerifyBusinessView view){
		try {
			workItem.setResponse(ApproveRouteConstants.CHECK_TO_ANTIFRAUD);
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 退回门店(路由：TO_STORE)
	 * 2016年1月7日
	 * xiaoniu.hu
	 * @param workItem 工作流工作项实体
	 * @param view 业务对象实体
	 * @return TURE:成功;FALSE:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynToBackStore")
	public String asynToBackStore(WorkItemView workItem,VerifyBusinessView view){
		try {
			workItem.setResponse(ApproveRouteConstants.CHECK_TO_STORE);
			if(getFlag(view.getLoanCode())){ // 如果没有进入确认签署，则设置超时时间
				// 读取系统设置，设置回退超时标识与回退超时时间
				SystemSetting sysSetting = new SystemSetting(); 
				sysSetting.setSysFlag(TimeOutFlag.GOBACK.getCode());			
				sysSetting = systemSetMaterService.get(sysSetting);
				if (sysSetting != null && StringUtils.isNumeric(sysSetting.getSysValue())) {				
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(new Date());				
					// 当前时间加上系统设置的天数，为回退超时时间
					rightNow.add(Calendar.DAY_OF_YEAR, Integer.valueOf(sysSetting.getSysValue()));
					// 更新回退超时时间				
					view.setTimeOutPoint(rightNow.getTime());
					// 更新回退超时标识
					view.setTimeOutFlag(YESNO.YES.getCode()); 
				}
			}
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}

	/**
	 * 客户放弃(路由：TO_END)
	 * 2016年1月7日
	 * xiaoniu.hu
	 * @param workItem 工作流工作项实体
	 * @param view 业务对象实体
	 * @return TURE:成功;FALSE:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynToAbandon")
	public String asynToAbandon(WorkItemView workItem,VerifyBusinessView view){
		try {
			workItem.setResponse(ApproveRouteConstants.CHECK_TO_ABANDON);
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 发起外访(路由：TO_VISIT)
	 * 2016年1月7日
	 * xiaoniu.hu
	 * @param workItem 工作流工作项实体
	 * @param view 业务对象实体
	 * @return TURE:成功;FALSE:失败;
	 */
	@ResponseBody
	@RequestMapping(value="asynToVisit")
	public String asynToVisit(WorkItemView workItem,VerifyBusinessView view){
		try {			
			workItem.setResponse(ApproveRouteConstants.CHECK_TO_VISIT);
			view.setVisitStartTime(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));//更新外访开始时间
			
			if(getFlag(view.getLoanCode())){ // 如果没有进入确认签署则，更新外访超时标志
				// 读取系统设置，设置外访超时标识与外访超时时间
				SystemSetting sysSetting = new SystemSetting(); 
				sysSetting.setSysFlag(TimeOutFlag.SETTLE.getCode());			
				sysSetting = systemSetMaterService.get(sysSetting);
				if (sysSetting != null && StringUtils.isNumeric(sysSetting.getSysValue())) {				
					Calendar rightNow = Calendar.getInstance();
					rightNow.setTime(new Date());				
					// 当前时间加上系统设置的天数，为外访超时时间
					rightNow.add(Calendar.DAY_OF_YEAR, Integer.valueOf(sysSetting.getSysValue()));
					
					// 更新外访超时时间				
					view.setTimeOutPoint(rightNow.getTime());
					// 更新外访超时标识
					view.setTimeOutFlag(YESNO.YES.getCode()); 
				}
			}
			
			workItem.setBv(view);
			flowService.dispatch(workItem);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
}
