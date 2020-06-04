package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditLoanDetailedTwo extends DataEntity<CreditLoanDetailedTwo>{
	

	private static final long serialVersionUID = 1L;
	private String id;                //ID
    private String relationId;        //关联ID
    private Integer repayMonths;      //剩余还款月数
    private Date realRepayDay;        //最近一次实际还款日期
    private BigDecimal shouldRepayAmount;             //本月应还款金额
    private BigDecimal realRepayAmount;               //本月实际还款金额
    private Integer currentOverdue;                   //当前逾期期数
    private BigDecimal currentOverdueTotal;           //当前逾期总额
    private Integer overdueNoTotal;                   //累计逾期次数
    private Integer overdueNoHighest;                 //最高逾期期数
    private BigDecimal overduePrincipalLevel1;        //逾期31-60天未归还贷款本金
    private BigDecimal overduePrincipalLevel2;        //逾期61-90天未归还贷款本金
    private BigDecimal overduePrincipalLevel3;        //逾期91-180天未归还贷款本金
    private BigDecimal overduePrincipalLevel4;        //逾期180天以上为归还贷款本金
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

    public Integer getRepayMonths() {
        return repayMonths;
    }

    public void setRepayMonths(Integer repayMonths) {
        this.repayMonths = repayMonths;
    }

    public Date getRealRepayDay() {
        return realRepayDay;
    }

    public void setRealRepayDay(Date realRepayDay) {
        this.realRepayDay = realRepayDay;
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

    public Integer getOverdueNoTotal() {
        return overdueNoTotal;
    }

    public void setOverdueNoTotal(Integer overdueNoTotal) {
        this.overdueNoTotal = overdueNoTotal;
    }

    public Integer getOverdueNoHighest() {
        return overdueNoHighest;
    }

    public void setOverdueNoHighest(Integer overdueNoHighest) {
        this.overdueNoHighest = overdueNoHighest;
    }

    public BigDecimal getOverduePrincipalLevel1() {
        return overduePrincipalLevel1;
    }

    public void setOverduePrincipalLevel1(BigDecimal overduePrincipalLevel1) {
        this.overduePrincipalLevel1 = overduePrincipalLevel1;
    }

    public BigDecimal getOverduePrincipalLevel2() {
        return overduePrincipalLevel2;
    }

    public void setOverduePrincipalLevel2(BigDecimal overduePrincipalLevel2) {
        this.overduePrincipalLevel2 = overduePrincipalLevel2;
    }

    public BigDecimal getOverduePrincipalLevel3() {
        return overduePrincipalLevel3;
    }

    public void setOverduePrincipalLevel3(BigDecimal overduePrincipalLevel3) {
        this.overduePrincipalLevel3 = overduePrincipalLevel3;
    }

    public BigDecimal getOverduePrincipalLevel4() {
        return overduePrincipalLevel4;
    }

    public void setOverduePrincipalLevel4(BigDecimal overduePrincipalLevel4) {
        this.overduePrincipalLevel4 = overduePrincipalLevel4;
    }

    public Date getClearingDay() {
        return clearingDay;
    }

    public void setClearingDay(Date clearingDay) {
        this.clearingDay = clearingDay;
    }
}