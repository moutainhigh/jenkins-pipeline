package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditCivilJudgmentRecord extends DataEntity<CreditCivilJudgmentRecord>{

	private static final long serialVersionUID = 1L;

	private String loanCode;

    private String filingCourt;

    private String matterCase;

    private String caseReference;

    private String objectLitigation;

    private String dictClosedManner;

    private Date filingDay;

    private String dictLawsuitPosition;

    private String trialProcedure;

    private BigDecimal objectLitigationAmount;

    private Date effectiveDay;

    private String operation;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getFilingCourt() {
        return filingCourt;
    }

    public void setFilingCourt(String filingCourt) {
        this.filingCourt = filingCourt == null ? null : filingCourt.trim();
    }

    public String getMatterCase() {
        return matterCase;
    }

    public void setMatterCase(String matterCase) {
        this.matterCase = matterCase == null ? null : matterCase.trim();
    }

    public String getCaseReference() {
		return caseReference;
	}

	public void setCaseReference(String caseReference) {
		this.caseReference = caseReference;
	}

	public String getObjectLitigation() {
        return objectLitigation;
    }

    public void setObjectLitigation(String objectLitigation) {
        this.objectLitigation = objectLitigation == null ? null : objectLitigation.trim();
    }

    public String getDictClosedManner() {
        return dictClosedManner;
    }

    public void setDictClosedManner(String dictClosedManner) {
        this.dictClosedManner = dictClosedManner == null ? null : dictClosedManner.trim();
    }

    public Date getFilingDay() {
        return filingDay;
    }

    public void setFilingDay(Date filingDay) {
        this.filingDay = filingDay;
    }

    public String getDictLawsuitPosition() {
        return dictLawsuitPosition;
    }

    public void setDictLawsuitPosition(String dictLawsuitPosition) {
        this.dictLawsuitPosition = dictLawsuitPosition == null ? null : dictLawsuitPosition.trim();
    }

    public String getTrialProcedure() {
        return trialProcedure;
    }

    public void setTrialProcedure(String trialProcedure) {
        this.trialProcedure = trialProcedure == null ? null : trialProcedure.trim();
    }

    public BigDecimal getObjectLitigationAmount() {
        return objectLitigationAmount;
    }

    public void setObjectLitigationAmount(BigDecimal objectLitigationAmount) {
        this.objectLitigationAmount = objectLitigationAmount;
    }

    public Date getEffectiveDay() {
        return effectiveDay;
    }

    public void setEffectiveDay(Date effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

}