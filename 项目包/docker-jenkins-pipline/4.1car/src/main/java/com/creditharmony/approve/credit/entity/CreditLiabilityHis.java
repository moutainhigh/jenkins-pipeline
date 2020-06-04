package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 企业征信_负债历史变化 
 * @Class Name CreditLiabilityHis
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditLiabilityHis extends DataEntity<CreditLiabilityHis> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private Date liabilityHisTime;//负债历史变化时间

    private BigDecimal allBalance;//全部负债余额

    private BigDecimal badnessBalance;//不良负债余额

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

    public Date getLiabilityHisTime() {
        return liabilityHisTime;
    }

    public void setLiabilityHisTime(Date liabilityHisTime) {
        this.liabilityHisTime = liabilityHisTime;
    }

    public BigDecimal getAllBalance() {
        return allBalance;
    }

    public void setAllBalance(BigDecimal allBalance) {
        this.allBalance = allBalance;
    }

    public BigDecimal getBadnessBalance() {
        return badnessBalance;
    }

    public void setBadnessBalance(BigDecimal badnessBalance) {
        this.badnessBalance = badnessBalance;
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