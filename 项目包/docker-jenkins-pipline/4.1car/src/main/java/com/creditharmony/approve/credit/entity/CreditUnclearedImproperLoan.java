package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditUnclearedImproperLoan extends DataEntity<CreditUnclearedImproperLoan>{

	private static final long serialVersionUID = 1L;

	private String businessType;

    private String loanCode;

    private String loanOrg;

    private String dictCurrency;

    private BigDecimal businessAmount;

    private BigDecimal businessBalance;

    private Date businessDay;

    private Date actualDay;

    private String dictLevelClass;

    private String dictLoanType;

    private Integer marginLevel;

    private String dictGuarantee;

    private String dictExhibition;

    private String makeAdvances;

    private String operation;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
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

    public BigDecimal getBusinessBalance() {
        return businessBalance;
    }

    public void setBusinessBalance(BigDecimal businessBalance) {
        this.businessBalance = businessBalance;
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

    public Integer getMarginLevel() {
        return marginLevel;
    }

    public void setMarginLevel(Integer marginLevel) {
        this.marginLevel = marginLevel;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}