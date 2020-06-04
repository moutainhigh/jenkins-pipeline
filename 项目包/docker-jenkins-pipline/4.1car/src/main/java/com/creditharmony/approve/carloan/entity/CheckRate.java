package com.creditharmony.approve.carloan.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CheckRate {
    private String loanCode;

    private BigDecimal interestRate;

    private BigDecimal monthlyInterest;

    private BigDecimal firstServiceTariffingRate;

    private BigDecimal firstServiceTariffing;

    private BigDecimal contractAmount;

    private BigDecimal feePaymentAmount;

    private BigDecimal monthRepayAmount;

    private BigDecimal comprehensiveServiceFee;

    private BigDecimal auditFee;

    private BigDecimal consultingFee;

    private BigDecimal intermediaryServiceFee;

    private BigDecimal informationServiceCharge;

    private BigDecimal defaultPenaltyInterest;

    private BigDecimal defaultPenaltyInterestRate;

    private BigDecimal defaultPenaltyRate;

    private BigDecimal defaultPenalty;

    private String createBy;

    private Date createTime;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(BigDecimal monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public BigDecimal getFirstServiceTariffingRate() {
        return firstServiceTariffingRate;
    }

    public void setFirstServiceTariffingRate(BigDecimal firstServiceTariffingRate) {
        this.firstServiceTariffingRate = firstServiceTariffingRate;
    }

    public BigDecimal getFirstServiceTariffing() {
        return firstServiceTariffing;
    }

    public void setFirstServiceTariffing(BigDecimal firstServiceTariffing) {
        this.firstServiceTariffing = firstServiceTariffing;
    }

    public BigDecimal getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public BigDecimal getFeePaymentAmount() {
        return feePaymentAmount;
    }

    public void setFeePaymentAmount(BigDecimal feePaymentAmount) {
        this.feePaymentAmount = feePaymentAmount;
    }

    public BigDecimal getMonthRepayAmount() {
        return monthRepayAmount;
    }

    public void setMonthRepayAmount(BigDecimal monthRepayAmount) {
        this.monthRepayAmount = monthRepayAmount;
    }

    public BigDecimal getComprehensiveServiceFee() {
        return comprehensiveServiceFee;
    }

    public void setComprehensiveServiceFee(BigDecimal comprehensiveServiceFee) {
        this.comprehensiveServiceFee = comprehensiveServiceFee;
    }

    public BigDecimal getAuditFee() {
        return auditFee;
    }

    public void setAuditFee(BigDecimal auditFee) {
        this.auditFee = auditFee;
    }

    public BigDecimal getConsultingFee() {
        return consultingFee;
    }

    public void setConsultingFee(BigDecimal consultingFee) {
        this.consultingFee = consultingFee;
    }

    public BigDecimal getIntermediaryServiceFee() {
        return intermediaryServiceFee;
    }

    public void setIntermediaryServiceFee(BigDecimal intermediaryServiceFee) {
        this.intermediaryServiceFee = intermediaryServiceFee;
    }

    public BigDecimal getInformationServiceCharge() {
        return informationServiceCharge;
    }

    public void setInformationServiceCharge(BigDecimal informationServiceCharge) {
        this.informationServiceCharge = informationServiceCharge;
    }

    public BigDecimal getDefaultPenaltyInterest() {
        return defaultPenaltyInterest;
    }

    public void setDefaultPenaltyInterest(BigDecimal defaultPenaltyInterest) {
        this.defaultPenaltyInterest = defaultPenaltyInterest;
    }

    public BigDecimal getDefaultPenaltyInterestRate() {
        return defaultPenaltyInterestRate;
    }

    public void setDefaultPenaltyInterestRate(BigDecimal defaultPenaltyInterestRate) {
        this.defaultPenaltyInterestRate = defaultPenaltyInterestRate;
    }

    public BigDecimal getDefaultPenaltyRate() {
        return defaultPenaltyRate;
    }

    public void setDefaultPenaltyRate(BigDecimal defaultPenaltyRate) {
        this.defaultPenaltyRate = defaultPenaltyRate;
    }

    public BigDecimal getDefaultPenalty() {
        return defaultPenalty;
    }

    public void setDefaultPenalty(BigDecimal defaultPenalty) {
        this.defaultPenalty = defaultPenalty;
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
}