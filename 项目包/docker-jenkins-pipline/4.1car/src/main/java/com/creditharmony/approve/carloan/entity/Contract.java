package com.creditharmony.approve.carloan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Contract {
    private String loanCode;

    private String id;

    private String contractCode;

    private Date contractDueDay;

    private Date contractFactDay;

    private Date contractReplayDay;

    private Date contractEndDay;

    private String productType;

    private BigDecimal auditAmount;

    private Integer contractMonths;

    private String midId;

    private String dictRepayMethod;

    private BigDecimal contractAmount;

    private BigDecimal contractExpectAmount;

    private BigDecimal contractMonthRepayAmount;

    private String dictCheckStatus;

    private String contractBackResult;

    private String contractVersion;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode == null ? null : contractCode.trim();
    }

    public Date getContractDueDay() {
        return contractDueDay;
    }

    public void setContractDueDay(Date contractDueDay) {
        this.contractDueDay = contractDueDay;
    }

    public Date getContractFactDay() {
        return contractFactDay;
    }

    public void setContractFactDay(Date contractFactDay) {
        this.contractFactDay = contractFactDay;
    }

    public Date getContractReplayDay() {
        return contractReplayDay;
    }

    public void setContractReplayDay(Date contractReplayDay) {
        this.contractReplayDay = contractReplayDay;
    }

    public Date getContractEndDay() {
        return contractEndDay;
    }

    public void setContractEndDay(Date contractEndDay) {
        this.contractEndDay = contractEndDay;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public BigDecimal getAuditAmount() {
        return auditAmount;
    }

    public void setAuditAmount(BigDecimal auditAmount) {
        this.auditAmount = auditAmount;
    }

    public Integer getContractMonths() {
        return contractMonths;
    }

    public void setContractMonths(Integer contractMonths) {
        this.contractMonths = contractMonths;
    }

    public String getMidId() {
        return midId;
    }

    public void setMidId(String midId) {
        this.midId = midId == null ? null : midId.trim();
    }

    public String getDictRepayMethod() {
        return dictRepayMethod;
    }

    public void setDictRepayMethod(String dictRepayMethod) {
        this.dictRepayMethod = dictRepayMethod == null ? null : dictRepayMethod.trim();
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getContractExpectAmount() {
        return contractExpectAmount;
    }

    public void setContractExpectAmount(BigDecimal contractExpectAmount) {
        this.contractExpectAmount = contractExpectAmount;
    }

    public BigDecimal getContractMonthRepayAmount() {
        return contractMonthRepayAmount;
    }

    public void setContractMonthRepayAmount(BigDecimal contractMonthRepayAmount) {
        this.contractMonthRepayAmount = contractMonthRepayAmount;
    }

    public String getDictCheckStatus() {
        return dictCheckStatus;
    }

    public void setDictCheckStatus(String dictCheckStatus) {
        this.dictCheckStatus = dictCheckStatus == null ? null : dictCheckStatus.trim();
    }

    public String getContractBackResult() {
        return contractBackResult;
    }

    public void setContractBackResult(String contractBackResult) {
        this.contractBackResult = contractBackResult == null ? null : contractBackResult.trim();
    }

    public String getContractVersion() {
        return contractVersion;
    }

    public void setContractVersion(String contractVersion) {
        this.contractVersion = contractVersion == null ? null : contractVersion.trim();
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