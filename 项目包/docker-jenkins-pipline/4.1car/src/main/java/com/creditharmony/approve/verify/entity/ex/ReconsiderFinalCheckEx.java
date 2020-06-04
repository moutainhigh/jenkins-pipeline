package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.entity.ReconsiderApply;

public class ReconsiderFinalCheckEx implements  Serializable {

	private static final long serialVersionUID = 1L;

	private ReconsiderApply reconsiderApply;                 // 复议原因
	private List<CreditReportRisk> creditReportRiskList;     // 征信报告
	private List<GlRefuse> glRefuseList;                     // 拒绝码
	private List<JkProducts> productsList;                   // 产品
	
	
	public ReconsiderApply getReconsiderApply() {
		return reconsiderApply;
	}
	public void setReconsiderApply(ReconsiderApply reconsiderApply) {
		this.reconsiderApply = reconsiderApply;
	}
	public List<GlRefuse> getGlRefuseList() {
		return glRefuseList;
	}
	public void setGlRefuseList(List<GlRefuse> glRefuseList) {
		this.glRefuseList = glRefuseList;
	}
	public List<JkProducts> getProductsList() {
		return productsList;
	}
	public void setProductsList(List<JkProducts> productsList) {
		this.productsList = productsList;
	}
	public List<CreditReportRisk> getCreditReportRiskList() {
		return creditReportRiskList;
	}
	public void setCreditReportRiskList(List<CreditReportRisk> creditReportRiskList) {
		this.creditReportRiskList = creditReportRiskList;
	}
}
