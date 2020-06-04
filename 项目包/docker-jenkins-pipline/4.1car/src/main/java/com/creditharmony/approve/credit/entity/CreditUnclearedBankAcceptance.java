package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_未结清银行承兑汇票
 * @Class Name CreditUnclearedBankAcceptance
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditUnclearedBankAcceptance extends DataEntity<CreditUnclearedBankAcceptance> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String loanOrg;//授信机构

    private Integer transactionCount;//笔数

    private BigDecimal actual30dayBalance;//到期小于30余额

    private BigDecimal actual60dayBalance;//到期小于60余额

    private BigDecimal actual90dayBalance;//到期小于90余额

    private BigDecimal actual91dayBalance;//到期大于90余额

    private BigDecimal totalBalance;//余额合计

    private String operation;

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

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public BigDecimal getActual30dayBalance() {
        return actual30dayBalance;
    }

    public void setActual30dayBalance(BigDecimal actual30dayBalance) {
        this.actual30dayBalance = actual30dayBalance;
    }

    public BigDecimal getActual60dayBalance() {
        return actual60dayBalance;
    }

    public void setActual60dayBalance(BigDecimal actual60dayBalance) {
        this.actual60dayBalance = actual60dayBalance;
    }

    public BigDecimal getActual90dayBalance() {
        return actual90dayBalance;
    }

    public void setActual90dayBalance(BigDecimal actual90dayBalance) {
        this.actual90dayBalance = actual90dayBalance;
    }

    public BigDecimal getActual91dayBalance() {
        return actual91dayBalance;
    }

    public void setActual91dayBalance(BigDecimal actual91dayBalance) {
        this.actual91dayBalance = actual91dayBalance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
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