package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.document.entity.ZlshYxxjc;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.phone.entity.TelAuditWork;
import com.creditharmony.approve.phone.entity.ex.TelCheckResultEx;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.entity.ReconsiderApply;

/**
 * 复议复审除内网核查数据的其他数据
 * @Class Name ReconsiderRecheckEx
 * @author 王浩
 * @Create In 2015年12月23日
 */
public class ReconsiderRecheckEx implements  Serializable {

	private static final long serialVersionUID = 1L;

	private List<AuditResult> auditResults;		             // 审核结果
	private List<TelAuditWork> telAuditWorks;	             // 外部核查
	private AuditRatingResult auditRatingResult;	         // 推荐信息
	private MonthIncomeEx monthIncomeEx;	                 // 月认定收入
	private List<TelCheckResultEx> telCheckResultExs;	     // 电话核查结果
	private ZlshYxxjc zlshYxxjc;	                         // 资料审核有效性检查 
	private ReconsiderApply reconsiderApply;                 // 复议原因
	private CreditReportRisk creditReportPerson;	         // 征信报告  个人版
	private CreditReportRisk creditReportCompany;			 // 征信报告  企业版
	private List<GlRefuse> glRefuseList;                     // 拒绝码
	private List<JkProducts> productsList;                   // 审批产品列表
	
	public List<AuditResult> getAuditResults() {
		return auditResults;
	}
	public void setAuditResults(List<AuditResult> auditResults) {
		this.auditResults = auditResults;
	}
	public List<TelAuditWork> getTelAuditWorks() {
		return telAuditWorks;
	}
	public void setTelAuditWorks(List<TelAuditWork> telAuditWorks) {
		this.telAuditWorks = telAuditWorks;
	}
	public AuditRatingResult getAuditRatingResult() {
		return auditRatingResult;
	}
	public void setAuditRatingResult(AuditRatingResult auditRatingResult) {
		this.auditRatingResult = auditRatingResult;
	}
	public MonthIncomeEx getMonthIncomeEx() {
		return monthIncomeEx;
	}
	public void setMonthIncomeEx(MonthIncomeEx monthIncomeEx) {
		this.monthIncomeEx = monthIncomeEx;
	}
	public List<TelCheckResultEx> getTelCheckResultExs() {
		return telCheckResultExs;
	}
	public void setTelCheckResultExs(List<TelCheckResultEx> telCheckResultExs) {
		this.telCheckResultExs = telCheckResultExs;
	}
	public ZlshYxxjc getZlshYxxjc() {
		return zlshYxxjc;
	}
	public void setZlshYxxjc(ZlshYxxjc zlshYxxjc) {
		this.zlshYxxjc = zlshYxxjc;
	}

	public ReconsiderApply getReconsiderApply() {
		return reconsiderApply;
	}

	public void setReconsiderApply(ReconsiderApply reconsiderApply) {
		this.reconsiderApply = reconsiderApply;
	}

	public CreditReportRisk getCreditReportPerson() {
		return creditReportPerson;
	}
	public void setCreditReportPerson(CreditReportRisk creditReportPerson) {
		this.creditReportPerson = creditReportPerson;
	}
	public CreditReportRisk getCreditReportCompany() {
		return creditReportCompany;
	}
	public void setCreditReportCompany(CreditReportRisk creditReportCompany) {
		this.creditReportCompany = creditReportCompany;
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
}
