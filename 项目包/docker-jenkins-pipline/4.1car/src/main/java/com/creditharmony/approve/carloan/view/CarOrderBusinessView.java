package com.creditharmony.approve.carloan.view;

import java.io.Serializable;

import com.creditharmony.bpm.frame.view.BaseBusinessView;

/**
 * 车借--取单View
 * 
 * @Class Name CarOrderBusinessView
 * @author 申诗阔
 * @Create In 2016年4月18日
 */
public class CarOrderBusinessView extends BaseBusinessView implements
		Serializable {

	private static final long serialVersionUID = 5135080601093888659L;

	private String auditType; // 审批节点：1复审，2终审
	// 排序字段
	private String orderField;
	// 第一次退回的源节点名称--退回标红置顶业务所需
	private String firstBackSourceStep;

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getFirstBackSourceStep() {
		return firstBackSourceStep;
	}

	public void setFirstBackSourceStep(String firstBackSourceStep) {
		this.firstBackSourceStep = firstBackSourceStep;
	}

}
