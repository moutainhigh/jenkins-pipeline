package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LoanGrantRecord {
    private BigDecimal id;

    private BigDecimal contractCode;

    private String loanCode;

    private String midId;

    private String dictLoanType;

    private String dictLoanWay;

    private BigDecimal grantAmount;

    private Date grantOfflineTime;

    private String grantCustId;

    private String grantRecepicResult;

    private String grantFailResult;

    private String grantBackMes;

    private String checkEmpId;

    private String checkResult;

    private Date checkTime;

    private String createBy;

    private Date createTime;

    private String modifyId;

    private Date modifyTime;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getContractCode() {
        return contractCode;
    }

    public void setContractCode(BigDecimal contractCode) {
        this.contractCode = contractCode;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getMidId() {
        return midId;
    }

    public void setMidId(String midId) {
        this.midId = midId == null ? null : midId.trim();
    }

    public String getDictLoanType() {
        return dictLoanType;
    }

    public void setDictLoanType(String dictLoanType) {
        this.dictLoanType = dictLoanType == null ? null : dictLoanType.trim();
    }

    public String getDictLoanWay() {
        return dictLoanWay;
    }

    public void setDictLoanWay(String dictLoanWay) {
        this.dictLoanWay = dictLoanWay == null ? null : dictLoanWay.trim();
    }

    public BigDecimal getGrantAmount() {
        return grantAmount;
    }

    public void setGrantAmount(BigDecimal grantAmount) {
        this.grantAmount = grantAmount;
    }

    public Date getGrantOfflineTime() {
        return grantOfflineTime;
    }

    public void setGrantOfflineTime(Date grantOfflineTime) {
        this.grantOfflineTime = grantOfflineTime;
    }

    public String getGrantCustId() {
        return grantCustId;
    }

    public void setGrantCustId(String grantCustId) {
        this.grantCustId = grantCustId == null ? null : grantCustId.trim();
    }

    public String getGrantRecepicResult() {
        return grantRecepicResult;
    }

    public void setGrantRecepicResult(String grantRecepicResult) {
        this.grantRecepicResult = grantRecepicResult == null ? null : grantRecepicResult.trim();
    }

    public String getGrantFailResult() {
        return grantFailResult;
    }

    public void setGrantFailResult(String grantFailResult) {
        this.grantFailResult = grantFailResult == null ? null : grantFailResult.trim();
    }

    public String getGrantBackMes() {
        return grantBackMes;
    }

    public void setGrantBackMes(String grantBackMes) {
        this.grantBackMes = grantBackMes == null ? null : grantBackMes.trim();
    }

    public String getCheckEmpId() {
        return checkEmpId;
    }

    public void setCheckEmpId(String checkEmpId) {
        this.checkEmpId = checkEmpId == null ? null : checkEmpId.trim();
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
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

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId == null ? null : modifyId.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}