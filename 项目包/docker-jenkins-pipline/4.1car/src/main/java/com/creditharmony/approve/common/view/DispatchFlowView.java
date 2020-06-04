package com.creditharmony.approve.common.view;

import com.creditharmony.approve.common.entity.AuditBack;
import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.bpm.frame.view.BaseBusinessView;

/**
 * 用于封装流程处理时需要的保存的数据对象
 * @Class Name DispatchFlowView
 * @author 赖敏
 * @Create In 2015年12月17日
 */
public class DispatchFlowView extends BaseBusinessView{

	private AuditBack auditBack; //保存回退清单数据
	private OutsideCheckInfo outsideCheckInfo; //保存发起外访数据
	private AuditResultEx auditResultEx; // 复议终审决策数据保存
	
	public AuditBack getAuditBack() {
		return auditBack;
	}
	
	public void setAuditBack(AuditBack auditBack) {
		this.auditBack = auditBack;
	}
	
	public void setOutsideCheckInfo(OutsideCheckInfo outsideCheckInfo) {
		this.outsideCheckInfo = outsideCheckInfo;
	}
	
	public OutsideCheckInfo getOutsideCheckInfo() {
		return outsideCheckInfo;
	}

	public AuditResultEx getAuditResultEx() {
		return auditResultEx;
	}

	public void setAuditResultEx(AuditResultEx auditResultEx) {
		this.auditResultEx = auditResultEx;
	}
	
}
