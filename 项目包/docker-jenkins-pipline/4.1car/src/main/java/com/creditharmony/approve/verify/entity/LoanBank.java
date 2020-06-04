package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LoanBank {
    private BigDecimal id;

    private String loanCode;

    private String bankAccountOpen;

    private String bankProvince;

    private String bankCity;

    private String bankOrc;

    private String dictCreaType;

    private String bankBranch;

    private String bankAccountName;

    private String bankAccount;

    private String bankSigningPlatform;

    private String bankStatus;

    private BigDecimal bankTop;

    private String bankMaintainType;

    private String bankCheckResult;

    private String bankCheckDesc;

    private String bankOriginalaId;

    private String bankIsRareword;

    private String bankJzhKhhss;

    private String bankJzhKhhqx;

    private String bankAuthorizer;

    private Date createTime;

    private String createBy;

    private String modifyBy;

    private Date modifyTime;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getBankAccountOpen() {
        return bankAccountOpen;
    }

    public void setBankAccountOpen(String bankAccountOpen) {
        this.bankAccountOpen = bankAccountOpen == null ? null : bankAccountOpen.trim();
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince == null ? null : bankProvince.trim();
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity == null ? null : bankCity.trim();
    }

    public String getBankOrc() {
        return bankOrc;
    }

    public void setBankOrc(String bankOrc) {
        this.bankOrc = bankOrc == null ? null : bankOrc.trim();
    }

    public String getDictCreaType() {
        return dictCreaType;
    }

    public void setDictCreaType(String dictCreaType) {
        this.dictCreaType = dictCreaType == null ? null : dictCreaType.trim();
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch == null ? null : bankBranch.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public String getBankSigningPlatform() {
        return bankSigningPlatform;
    }

    public void setBankSigningPlatform(String bankSigningPlatform) {
        this.bankSigningPlatform = bankSigningPlatform == null ? null : bankSigningPlatform.trim();
    }

    public String getBankStatus() {
        return bankStatus;
    }

    public void setBankStatus(String bankStatus) {
        this.bankStatus = bankStatus == null ? null : bankStatus.trim();
    }

    public BigDecimal getBankTop() {
        return bankTop;
    }

    public void setBankTop(BigDecimal bankTop) {
        this.bankTop = bankTop;
    }

    public String getBankMaintainType() {
        return bankMaintainType;
    }

    public void setBankMaintainType(String bankMaintainType) {
        this.bankMaintainType = bankMaintainType == null ? null : bankMaintainType.trim();
    }

    public String getBankCheckResult() {
        return bankCheckResult;
    }

    public void setBankCheckResult(String bankCheckResult) {
        this.bankCheckResult = bankCheckResult == null ? null : bankCheckResult.trim();
    }

    public String getBankCheckDesc() {
        return bankCheckDesc;
    }

    public void setBankCheckDesc(String bankCheckDesc) {
        this.bankCheckDesc = bankCheckDesc == null ? null : bankCheckDesc.trim();
    }

    public String getBankOriginalaId() {
        return bankOriginalaId;
    }

    public void setBankOriginalaId(String bankOriginalaId) {
        this.bankOriginalaId = bankOriginalaId == null ? null : bankOriginalaId.trim();
    }

    public String getBankIsRareword() {
        return bankIsRareword;
    }

    public void setBankIsRareword(String bankIsRareword) {
        this.bankIsRareword = bankIsRareword == null ? null : bankIsRareword.trim();
    }

    public String getBankJzhKhhss() {
        return bankJzhKhhss;
    }

    public void setBankJzhKhhss(String bankJzhKhhss) {
        this.bankJzhKhhss = bankJzhKhhss == null ? null : bankJzhKhhss.trim();
    }

    public String getBankJzhKhhqx() {
        return bankJzhKhhqx;
    }

    public void setBankJzhKhhqx(String bankJzhKhhqx) {
        this.bankJzhKhhqx = bankJzhKhhqx == null ? null : bankJzhKhhqx.trim();
    }

    public String getBankAuthorizer() {
        return bankAuthorizer;
    }

    public void setBankAuthorizer(String bankAuthorizer) {
        this.bankAuthorizer = bankAuthorizer == null ? null : bankAuthorizer.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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