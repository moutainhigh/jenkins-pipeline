package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditRisk extends DataEntity<CreditRisk> {

	private static final long serialVersionUID = 735107514838157541L;

    private String loanCode;

    private String loanCustomerCode;

    private String rId;

    private String dictCustomerType;

    private Date riskSearchTime;

    private String effectiveFlag;

    private String riskEffectiveRemark;

    private String riskCreditVersion;

    private String creditJson;

    private String dictCheckType;

    private String riskCheckOpinion;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getLoanCustomerCode() {
        return loanCustomerCode;
    }

    public void setLoanCustomerCode(String loanCustomerCode) {
        this.loanCustomerCode = loanCustomerCode == null ? null : loanCustomerCode.trim();
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId == null ? null : rId.trim();
    }

    public String getDictCustomerType() {
        return dictCustomerType;
    }

    public void setDictCustomerType(String dictCustomerType) {
        this.dictCustomerType = dictCustomerType == null ? null : dictCustomerType.trim();
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
        this.effectiveFlag = effectiveFlag == null ? null : effectiveFlag.trim();
    }

    public String getRiskEffectiveRemark() {
        return riskEffectiveRemark;
    }

    public void setRiskEffectiveRemark(String riskEffectiveRemark) {
        this.riskEffectiveRemark = riskEffectiveRemark == null ? null : riskEffectiveRemark.trim();
    }

    public String getRiskCreditVersion() {
        return riskCreditVersion;
    }

    public void setRiskCreditVersion(String riskCreditVersion) {
        this.riskCreditVersion = riskCreditVersion == null ? null : riskCreditVersion.trim();
    }

	public String getCreditJson() {
		return creditJson;
	}

	public void setCreditJson(String creditJson) {
		this.creditJson = creditJson;
	}

	public String getDictCheckType() {
        return dictCheckType;
    }

    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }

    public String getRiskCheckOpinion() {
        return riskCheckOpinion;
    }

    public void setRiskCheckOpinion(String riskCheckOpinion) {
        this.riskCheckOpinion = riskCheckOpinion == null ? null : riskCheckOpinion.trim();
    }

}