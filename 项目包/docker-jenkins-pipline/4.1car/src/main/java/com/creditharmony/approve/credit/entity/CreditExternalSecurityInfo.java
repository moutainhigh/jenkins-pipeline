package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_对外担保信息概要
 * @Class Name CreditExternalSecurityInfo
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditExternalSecurityInfo extends DataEntity<CreditExternalSecurityInfo> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String infoSummary;//信息概要

    private Integer transactionCount;//笔数

    private BigDecimal balance;//余额

    private BigDecimal normalBalance;//正常余额

    private BigDecimal concernBalance;//关注余额

    private BigDecimal badnessBalance;//不良余额

    private Integer sort;//排序

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

    public String getInfoSummary() {
        return infoSummary;
    }

    public void setInfoSummary(String infoSummary) {
        this.infoSummary = infoSummary == null ? null : infoSummary.trim();
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getNormalBalance() {
        return normalBalance;
    }

    public void setNormalBalance(BigDecimal normalBalance) {
        this.normalBalance = normalBalance;
    }

    public BigDecimal getConcernBalance() {
        return concernBalance;
    }

    public void setConcernBalance(BigDecimal concernBalance) {
        this.concernBalance = concernBalance;
    }

    public BigDecimal getBadnessBalance() {
        return badnessBalance;
    }

    public void setBadnessBalance(BigDecimal badnessBalance) {
        this.badnessBalance = badnessBalance;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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