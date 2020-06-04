package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_已结清信贷明细
 * @Class Name CreditCreditClearedDetail
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditCreditClearedDetail extends DataEntity<CreditCreditClearedDetail> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String infoSummary;//信息概要

    private BigDecimal loan;//贷款

    private BigDecimal tradeFinancing;//贸易融资

    private BigDecimal factoring;//保理

    private BigDecimal notesDiscounted;//票据贴现

    private BigDecimal bankAcceptance;//银行承兑汇票

    private BigDecimal letterCredit;//信用证

    private BigDecimal letterGuarantee;//保函

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

    public BigDecimal getLoan() {
        return loan;
    }

    public void setLoan(BigDecimal loan) {
        this.loan = loan;
    }

    public BigDecimal getTradeFinancing() {
        return tradeFinancing;
    }

    public void setTradeFinancing(BigDecimal tradeFinancing) {
        this.tradeFinancing = tradeFinancing;
    }

    public BigDecimal getFactoring() {
        return factoring;
    }

    public void setFactoring(BigDecimal factoring) {
        this.factoring = factoring;
    }

    public BigDecimal getNotesDiscounted() {
        return notesDiscounted;
    }

    public void setNotesDiscounted(BigDecimal notesDiscounted) {
        this.notesDiscounted = notesDiscounted;
    }

    public BigDecimal getBankAcceptance() {
        return bankAcceptance;
    }

    public void setBankAcceptance(BigDecimal bankAcceptance) {
        this.bankAcceptance = bankAcceptance;
    }

    public BigDecimal getLetterCredit() {
        return letterCredit;
    }

    public void setLetterCredit(BigDecimal letterCredit) {
        this.letterCredit = letterCredit;
    }

    public BigDecimal getLetterGuarantee() {
        return letterGuarantee;
    }

    public void setLetterGuarantee(BigDecimal letterGuarantee) {
        this.letterGuarantee = letterGuarantee;
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