package com.creditharmony.approve.carloan.view;

import java.io.Serializable;

/**
 * 定制加载的流程属性，参见WorkItemView中flowProperties的用法。
 * @author chenwd
 *
 */
public class FlowProperties implements Serializable{
	
	private static final long serialVersionUID = -4040092645920508344L;

	public static final String FIRST_BACK_SOURCE_STEP_DEAULT_VALUE = "NO";

	/**
	 * 退回标红置顶业务所需
	 * 第一次退回的源节点名称
	 * 默认值NO
	 */
	private String firstBackSourceStep;
	

	public String getFirstBackSourceStep() {
		return firstBackSourceStep;
	}
	public void setFirstBackSourceStep(String firstBackSourceStep) {
		this.firstBackSourceStep = firstBackSourceStep;
	}
	
}
