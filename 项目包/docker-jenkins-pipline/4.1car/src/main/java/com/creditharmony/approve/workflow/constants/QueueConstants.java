package com.creditharmony.approve.workflow.constants;

/**
 * 工作流队列
 * @Class Name QueueConstants
 * @author xiaoniu.hu
 * @Create In 2015年11月30日
 */
public interface QueueConstants {
	/**
	 * 反欺诈轮循队列
	 */
	public static final String ANTIFRAUD_CHECK_LOOP = "HC_ANTIFRAUD_CHECK_LOOP";
	/**
	 * 汇诚审批队列
	 */
	public static final String VERIFY_CHECK = "HC_VERIFY_CHECK";
	/**
	 * 车借流程(复审)
	 */
	public static final String CAR_VERIFY_RECHECK = "HC_CAR_RECHECK";
	/**
	 * 车借流程(终审)
	 */
	public static final String CAR_VERIFY_FINAL_CHECK = "HC_CAR_FINAL_CHECK";
}
