package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CreditPaidNotesDiscounted {
    private String id;

    private String loanCode;

    private String loanOrg;

    private String dictCurrency;

    private BigDecimal discountAmount;

    private Date discountDay;

    private Date acceptActualDay;

    private Date paidDay;

    private String dictLevelClass;

    private String operation;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Date getDiscountDay() {
        return discountDay;
    }

    public void setDiscountDay(Date discountDay) {
        this.discountDay = discountDay;
    }

    public Date getAcceptActualDay() {
        return acceptActualDay;
    }

    public void setAcceptActualDay(Date acceptActualDay) {
        this.acceptActualDay = acceptActualDay;
    }

    public Date getPaidDay() {
        return paidDay;
    }

    public void setPaidDay(Date paidDay) {
        this.paidDay = paidDay;
    }

    public String getDictLevelClass() {
        return dictLevelClass;
    }

    public void setDictLevelClass(String dictLevelClass) {
        this.dictLevelClass = dictLevelClass == null ? null : dictLevelClass.trim();
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