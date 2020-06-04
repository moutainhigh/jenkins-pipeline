package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditCardDetailedTwo extends DataEntity<CreditCardDetailedTwo>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String relationId;//关联ID

    private String accountStatus;//账户状态

    private BigDecimal shouldRepayAmount;//本月应还款金额

    private BigDecimal realRepayAmount;//本月实际还款金额

    private Date realRepayDay;//最近一次实际还款日期

    private Integer currentOverdue;//当前逾期期数

    private BigDecimal currentOverdueTotal;//当前逾期总额

    private BigDecimal overdraftBalance;//准贷记卡透支180天以上未付余额

    private Integer repaymentNo;//贷记卡12个月内未最低还款额次数

    private Date getinfoTime;//信息获取时间

    private Date clearingDay;//结算年月

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    public BigDecimal getShouldRepayAmount() {
        return shouldRepayAmount;
    }

    public void setShouldRepayAmount(BigDecimal shouldRepayAmount) {
        this.shouldRepayAmount = shouldRepayAmount;
    }

    public BigDecimal getRealRepayAmount() {
        return realRepayAmount;
    }

    public void setRealRepayAmount(BigDecimal realRepayAmount) {
        this.realRepayAmount = realRepayAmount;
    }

    public Date getRealRepayDay() {
        return realRepayDay;
    }

    public void setRealRepayDay(Date realRepayDay) {
        this.realRepayDay = realRepayDay;
    }

    public Integer getCurrentOverdue() {
        return currentOverdue;
    }

    public void setCurrentOverdue(Integer currentOverdue) {
        this.currentOverdue = currentOverdue;
    }

    public BigDecimal getCurrentOverdueTotal() {
        return currentOverdueTotal;
    }

    public void setCurrentOverdueTotal(BigDecimal currentOverdueTotal) {
        this.currentOverdueTotal = currentOverdueTotal;
    }

    public BigDecimal getOverdraftBalance() {
        return overdraftBalance;
    }

    public void setOverdraftBalance(BigDecimal overdraftBalance) {
        this.overdraftBalance = overdraftBalance;
    }

    public Integer getRepaymentNo() {
        return repaymentNo;
    }

    public void setRepaymentNo(Integer repaymentNo) {
        this.repaymentNo = repaymentNo;
    }

    public Date getGetinfoTime() {
        return getinfoTime;
    }

    public void setGetinfoTime(Date getinfoTime) {
        this.getinfoTime = getinfoTime;
    }

    public Date getClearingDay() {
        return clearingDay;
    }

    public void setClearingDay(Date clearingDay) {
        this.clearingDay = clearingDay;
    }
}