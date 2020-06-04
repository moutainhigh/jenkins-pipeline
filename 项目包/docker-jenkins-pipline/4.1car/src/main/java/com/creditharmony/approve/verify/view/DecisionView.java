/**
 * @Probject Name: chp-approve
 * @Path: com.creditharmony.approve.verify.viewDecisionView.java
 * @Create By 黄维
 * @Create In 2015年11月28日 下午5:21:26
 */
package com.creditharmony.approve.verify.view;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name DecisionView
 * @author 黄维
 * @Create In 2015年11月28日
 * 决策
 */
public class DecisionView extends DataEntity<DecisionView> {

	private static final long serialVersionUID = 6385257702254502855L;
	
	private String loanCode;//借款编码
	private String auditResult;//审批结果
	private String productType;//审批产品
	private Double auditMoney;//批复额度
	private Integer auditMonths;//审批分期
	private Double auditContractMoney;//合同额度
	private Double auditMonthPaymoney;//月还款额
	private String auditRulesCode;//规则测试码
	private String auditCheckExamine;//审批意见
	private String dictCheckType;//审核类型
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public Double getAuditMoney() {
		return auditMoney;
	}
	public void setAuditMoney(Double auditMoney) {
		this.auditMoney = auditMoney;
	}
	public Integer getAuditMonths() {
		return auditMonths;
	}
	public void setAuditMonths(Integer auditMonths) {
		this.auditMonths = auditMonths;
	}
	public Double getAuditContractMoney() {
		return auditContractMoney;
	}
	public void setAuditContractMoney(Double auditContractMoney) {
		this.auditContractMoney = auditContractMoney;
	}
	public Double getAuditMonthPaymoney() {
		return auditMonthPaymoney;
	}
	public void setAuditMonthPaymoney(Double auditMonthPaymoney) {
		this.auditMonthPaymoney = auditMonthPaymoney;
	}
	public String getAuditRulesCode() {
		return auditRulesCode;
	}
	public void setAuditRulesCode(String auditRulesCode) {
		this.auditRulesCode = auditRulesCode;
	}
	public String getAuditCheckExamine() {
		return auditCheckExamine;
	}
	public void setAuditCheckExamine(String auditCheckExamine) {
		this.auditCheckExamine = auditCheckExamine;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
}
