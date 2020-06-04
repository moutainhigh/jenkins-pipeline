package com.creditharmony.approve.verify.entity.ex;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.document.entity.ZlshYxxjc;
import com.creditharmony.approve.internet.entity.ex.OutNetEx;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.phone.entity.TelAuditWork;
import com.creditharmony.approve.phone.entity.ex.TelCheckResultEx;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.ReconsiderApply;
import com.creditharmony.core.users.entity.User;

/**
 * 初审决策实体
 * 
 * @Class Name FirstAuditEx
 * @author 刘燕军
 * @Create In 2015年12月2日
 */

public class FirstCheckEx extends InnerCheckEx {

	private static final long serialVersionUID = -4277048633898450092L;
	private ApplicantInfoEx applicantInfo; // 申请人资格
	private List<AuditResult> auditResults; // 审核结果
	private List<TelAuditWork> telAuditWorks; // 外部核查
	private AuditRatingResult auditRatingResult; // 推荐信息
	private List<MonthIncomeEx> monthIncomeEx; // 月认定收入
	private List<TelCheckResultEx> telCheckResultExs; // 电话核查结果
	private List<ZlshYxxjc> zlshYxxjc; // 资料审核有效性检查
	private ReconsiderApply reconsiderApply; // 复议原因
	private List<OutNetEx> outNet; // 外部审核-网查
	private List<JkProducts> jkProducts; // 所有的产品信息
	private List<GlRefuse> glRefuses; // 所有的1级拒绝码
	private List<CreditReportRisk> creditRisks; // 征信报告有效性检查
	private AuditResult auditResult; // 审核结果
	private List<AuditResultSublistEx> resultSublists; // 拒借码列表
	private List<User> users; // 终审角色列表
	private String riskFlag; // 风险标识
	private Map<String, String> credirVersion; // 征信报告版本
	private Map<String, String> loanMan; // 主借人共借人
	private Map<String, String> personal; // 征信报告个人版
	private Map<String, String> personalSimple; // 征信报告个人版
	private Map<String, String> enterprise; // 征信报告企业版
	private String consultAfterRateOnline; // 客户咨询时间是否在“风险定价”上线时间之后
	
	private List<LoanCoborrower> bestCoborrowerIds; //最有自然人保证人
	private String customerAge; //主借人年龄
	public AuditRatingResult getAuditRatingResult() {
		return auditRatingResult;
	}

	public void setAuditRatingResult(AuditRatingResult auditRatingResult) {
		this.auditRatingResult = auditRatingResult;
	}

	public List<TelAuditWork> getTelAuditWorks() {
		return telAuditWorks;
	}

	public void setTelAuditWorks(List<TelAuditWork> telAuditWorks) {
		this.telAuditWorks = telAuditWorks;
	}

	public List<AuditResult> getAuditResults() {
		return auditResults;
	}

	public void setAuditResults(List<AuditResult> auditResults) {
		this.auditResults = auditResults;
	}

	public List<TelCheckResultEx> getTelCheckResultExs() {
		return telCheckResultExs;
	}

	public void setTelCheckResultExs(List<TelCheckResultEx> telCheckResultExs) {
		this.telCheckResultExs = telCheckResultExs;
	}

	public ApplicantInfoEx getApplicantInfo() {
		return applicantInfo;
	}

	public void setApplicantInfo(ApplicantInfoEx applicantInfo) {
		this.applicantInfo = applicantInfo;
	}

	public ReconsiderApply getReconsiderApply() {
		return reconsiderApply;
	}

	public void setReconsiderApply(ReconsiderApply reconsiderApply) {
		this.reconsiderApply = reconsiderApply;
	}

	public List<JkProducts> getJkProducts() {
		return jkProducts;
	}

	public void setJkProducts(List<JkProducts> jkProducts) {
		this.jkProducts = jkProducts;
	}

	public List<GlRefuse> getGlRefuses() {
		return glRefuses;
	}

	public void setGlRefuses(List<GlRefuse> glRefuses) {
		this.glRefuses = glRefuses;
	}

	public List<CreditReportRisk> getCreditRisks() {
		return creditRisks;
	}

	public void setCreditRisks(List<CreditReportRisk> creditRisks) {
		this.creditRisks = creditRisks;
	}

	public List<MonthIncomeEx> getMonthIncomeEx() {
		return monthIncomeEx;
	}

	public void setMonthIncomeEx(List<MonthIncomeEx> monthIncomeEx) {
		this.monthIncomeEx = monthIncomeEx;
	}

	public List<ZlshYxxjc> getZlshYxxjc() {
		return zlshYxxjc;
	}

	public void setZlshYxxjc(List<ZlshYxxjc> zlshYxxjc) {
		this.zlshYxxjc = zlshYxxjc;
	}

	public AuditResult getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(AuditResult auditResult) {
		this.auditResult = auditResult;
	}

	public List<AuditResultSublistEx> getResultSublists() {
		return resultSublists;
	}

	public void setResultSublists(List<AuditResultSublistEx> resultSublists) {
		this.resultSublists = resultSublists;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}

	public List<OutNetEx> getOutNet() {
		return outNet;
	}

	public void setOutNet(List<OutNetEx> outNet) {
		this.outNet = outNet;
	}

	public Map<String, String> getCredirVersion() {
		return credirVersion;
	}

	public void setCredirVersion(Map<String, String> credirVersion) {
		this.credirVersion = credirVersion;
	}

	public Map<String, String> getLoanMan() {
		return loanMan;
	}

	public void setLoanMan(Map<String, String> loanMan) {
		this.loanMan = loanMan;
	}

	public Map<String, String> getPersonal() {
		return personal;
	}

	public void setPersonal(Map<String, String> personal) {
		this.personal = personal;
	}

	public Map<String, String> getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Map<String, String> enterprise) {
		this.enterprise = enterprise;
	}

	public Map<String, String> getPersonalSimple() {
		return personalSimple;
	}

	public void setPersonalSimple(Map<String, String> personalSimple) {
		this.personalSimple = personalSimple;
	}

	public String getConsultAfterRateOnline() {
		return consultAfterRateOnline;
	}

	public void setConsultAfterRateOnline(String consultAfterRateOnline) {
		this.consultAfterRateOnline = consultAfterRateOnline;
	}

	public List<LoanCoborrower> getBestCoborrowerIds() {
		return bestCoborrowerIds;
	}

	public void setBestCoborrowerIds(List<LoanCoborrower> bestCoborrowerIds) {
		this.bestCoborrowerIds = bestCoborrowerIds;
	}

	public String getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(String customerAge) {
		this.customerAge = customerAge;
	}	
	
}
