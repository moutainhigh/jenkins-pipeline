package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditPaidLoan extends DataEntity<CreditPaidLoan>{
	
	private static final long serialVersionUID = 1L;

    private String loanCode;

    private String businessType;

    private String loanOrg;

    private String dictCurrency;

    private BigDecimal businessAmount;

    private Date businessDay;

    private Date actualDay;

    private Date paidDay;

    private String dictRepayMethod;

    private String dictLevelClass;

    private String dictLoanType;

    private String dictGuarantee;

    private String dictExhibition;

    private String makeAdvances;

    private BigDecimal marginLevel;

    private String operation;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getLoanOrg() {
        return loanOrg;
    }

    public void setLoanOrg(String loanOrg) {
        this.loanOrg = loanOrg == null ? null : loanOrg.trim();
    }

    public String getDictCurrency() {
        return dictCurrency;
    }

    public void setDictCurrency(String dictCurrency) {
        this.dictCurrency = dictCurrency == null ? null : dictCurrency.trim();
    }

    public BigDecimal getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(BigDecimal businessAmount) {
        this.businessAmount = businessAmount;
    }

    public Date getBusinessDay() {
        return businessDay;
    }

    public void setBusinessDay(Date businessDay) {
        this.businessDay = businessDay;
    }

    public Date getActualDay() {
        return actualDay;
    }

    public void setActualDay(Date actualDay) {
        this.actualDay = actualDay;
    }

    public Date getPaidDay() {
        return paidDay;
    }

    public void setPaidDay(Date paidDay) {
        this.paidDay = paidDay;
    }

    public String getDictRepayMethod() {
        return dictRepayMethod;
    }

    public void setDictRepayMethod(String dictRepayMethod) {
        this.dictRepayMethod = dictRepayMethod == null ? null : dictRepayMethod.trim();
    }

    public String getDictLevelClass() {
        return dictLevelClass;
    }

    public void setDictLevelClass(String dictLevelClass) {
        this.dictLevelClass = dictLevelClass == null ? null : dictLevelClass.trim();
    }

    public String getDictLoanType() {
        return dictLoanType;
    }

    public void setDictLoanType(String dictLoanType) {
        this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
    }

    public String getDictGuarantee() {
        return dictGuarantee;
    }

    public void setDictGuarantee(String dictGuarantee) {
        this.dictGuarantee = dictGuarantee == null ? null : dictGuarantee.trim();
    }

    public String getDictExhibition() {
        return dictExhibition;
    }

    public void setDictExhibition(String dictExhibition) {
        this.dictExhibition = dictExhibition == null ? null : dictExhibition.trim();
    }

    public String getMakeAdvances() {
        return makeAdvances;
    }

    public void setMakeAdvances(String makeAdvances) {
        this.makeAdvances = makeAdvances;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

	public BigDecimal getMarginLevel() {
		return marginLevel;
	}

	public void setMarginLevel(BigDecimal marginLevel) {
		this.marginLevel = marginLevel;
	}

}