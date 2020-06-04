package com.creditharmony.approve.common.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name CreditRiskView
 * @author 黄维
 * @Create In 2015年12月2日
 * 信用报告风险
 */
public class CreditReportRisk extends DataEntity<CreditReportRisk> {

	private static final long serialVersionUID = -44734278678592031L;

	private String id;// ID
	private String loanCode;// 借款编码
	private String loanCustomerCode;// 借款人编号
	private String rId;// 关联ID
	private String dictCustomerType;// 借款人类型(主借人/共借人)
	private Date riskSearchTime;// 查询时间
	private String effectiveFlag;// 是否有效
	private String riskEffectiveRemark;// 有效性备注
	private String riskCreditVersion;// 征信报告版本
	private CreditJson creditJson;// 审核结果
	private String dictCheckType;// 类型(初审，信审初审，复议初审)
	private String riskCheckOpinion;// 审核意见
	private String customerName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getLoanCustomerCode() {
		return loanCustomerCode;
	}
	public void setLoanCustomerCode(String loanCustomerCode) {
		this.loanCustomerCode = loanCustomerCode;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public Date getRiskSearchTime() {
		return riskSearchTime;
	}
	public void setRiskSearchTime(Date riskSearchTime) {
		this.riskSearchTime = riskSearchTime;
	}
	public String getEffectiveFlag() {
		return effectiveFlag;
	}
	public void setEffectiveFlag(String effectiveFlag) {
		this.effectiveFlag = effectiveFlag;
	}
	public String getRiskEffectiveRemark() {
		return riskEffectiveRemark;
	}
	public void setRiskEffectiveRemark(String riskEffectiveRemark) {
		this.riskEffectiveRemark = riskEffectiveRemark;
	}
	public String getRiskCreditVersion() {
		return riskCreditVersion;
	}
	public void setRiskCreditVersion(String riskCreditVersion) {
		this.riskCreditVersion = riskCreditVersion;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getRiskCheckOpinion() {
		return riskCheckOpinion;
	}
	public void setRiskCheckOpinion(String riskCheckOpinion) {
		this.riskCheckOpinion = riskCheckOpinion;
	}
	public CreditJson getCreditJson() {
		return creditJson;
	}
	public void setCreditJson(CreditJson creditJson) {
		this.creditJson = creditJson;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}	
	
}
