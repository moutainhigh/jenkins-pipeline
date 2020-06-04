package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LoanCreditInfo {
    private BigDecimal id;

    private String loanCode;

    private String creditAuthorizer;

    private String dictMortgageType;

    private String creditMortgageGoods;

    private String orgId;

    private BigDecimal creditWorkQuota;

    private BigDecimal creditMonths;

    private BigDecimal creditSurplus;

    private BigDecimal creditNum;

    private String createBy;

    private Date createTime;

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

    public String getCreditAuthorizer() {
        return creditAuthorizer;
    }

    public void setCreditAuthorizer(String creditAuthorizer) {
        this.creditAuthorizer = creditAuthorizer == null ? null : creditAuthorizer.trim();
    }

    public String getDictMortgageType() {
        return dictMortgageType;
    }

    public void setDictMortgageType(String dictMortgageType) {
        this.dictMortgageType = dictMortgageType == null ? null : dictMortgageType.trim();
    }

    public String getCreditMortgageGoods() {
        return creditMortgageGoods;
    }

    public void setCreditMortgageGoods(String creditMortgageGoods) {
        this.creditMortgageGoods = creditMortgageGoods == null ? null : creditMortgageGoods.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public BigDecimal getCreditWorkQuota() {
        return creditWorkQuota;
    }

    public void setCreditWorkQuota(BigDecimal creditWorkQuota) {
        this.creditWorkQuota = creditWorkQuota;
    }

    public BigDecimal getCreditMonths() {
        return creditMonths;
    }

    public void setCreditMonths(BigDecimal creditMonths) {
        this.creditMonths = creditMonths;
    }

    public BigDecimal getCreditSurplus() {
        return creditSurplus;
    }

    public void setCreditSurplus(BigDecimal creditSurplus) {
        this.creditSurplus = creditSurplus;
    }

    public BigDecimal getCreditNum() {
        return creditNum;
    }

    public void setCreditNum(BigDecimal creditNum) {
        this.creditNum = creditNum;
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