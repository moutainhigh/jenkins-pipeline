package com.creditharmony.approve.localnet.entity;


import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class InnerCustomerHis extends DataEntity<InnerCustomerHis>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String customerCode;
    private String hisTimeInterval;
    private String loanCode;
    private Date applyTime;
    private String productType;
    private String dictCheckStatus;
    private String hisRefuseReson;
    private String hisUnitname;
    private String hisAmountMonths;
    private String hisOverMonths;
    private String hisMonthMoney;
    private String hisSurplusCapital;
    private String maxOverdueDays;
    private String totalOverdueDays;
    private String totalOverdueTimes;
    private String loanCustomterCode;
    private String dictCustomerType;
    private String hisLoanCode;
    private String hisAmount;

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getHisTimeInterval() {
        return hisTimeInterval;
    }

    public void setHisTimeInterval(String hisTimeInterval) {
        this.hisTimeInterval = hisTimeInterval;
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getDictCheckStatus() {
        return dictCheckStatus;
    }

    public void setDictCheckStatus(String dictCheckStatus) {
        this.dictCheckStatus = dictCheckStatus == null ? null : dictCheckStatus.trim();
    }

    public String getHisRefuseReson() {
        return hisRefuseReson;
    }

    public void setHisRefuseReson(String hisRefuseReson) {
        this.hisRefuseReson = hisRefuseReson == null ? null : hisRefuseReson.trim();
    }

    public String getHisUnitname() {
        return hisUnitname;
    }

    public void setHisUnitname(String hisUnitname) {
        this.hisUnitname = hisUnitname == null ? null : hisUnitname.trim();
    }

    public String getHisAmountMonths() {
        return hisAmountMonths;
    }

    public void setHisAmountMonths(String hisAmountMonths) {
        this.hisAmountMonths = hisAmountMonths == null ? null : hisAmountMonths.trim();
    }

    public String getHisOverMonths() {
        return hisOverMonths;
    }

    public void setHisOverMonths(String hisOverMonths) {
        this.hisOverMonths = hisOverMonths == null ? null : hisOverMonths.trim();
    }

    public String getHisMonthMoney() {
        return hisMonthMoney;
    }

    public void setHisMonthMoney(String hisMonthMoney) {
        this.hisMonthMoney = hisMonthMoney;
    }

    public String getHisSurplusCapital() {
        return hisSurplusCapital;
    }

    public void setHisSurplusCapital(String hisSurplusCapital) {
        this.hisSurplusCapital = hisSurplusCapital == null ? null : hisSurplusCapital.trim();
    }

    public String getMaxOverdueDays() {
        return maxOverdueDays;
    }

    public void setMaxOverdueDays(String maxOverdueDays) {
        this.maxOverdueDays = maxOverdueDays;
    }

    public String getTotalOverdueDays() {
        return totalOverdueDays;
    }

    public void setTotalOverdueDays(String totalOverdueDays) {
        this.totalOverdueDays = totalOverdueDays;
    }

    public String getTotalOverdueTimes() {
        return totalOverdueTimes;
    }

    public void setTotalOverdueTimes(String totalOverdueTimes) {
        this.totalOverdueTimes = totalOverdueTimes;
    }

    public String getLoanCustomterCode() {
        return loanCustomterCode;
    }

    public void setLoanCustomterCode(String loanCustomterCode) {
        this.loanCustomterCode = loanCustomterCode == null ? null : loanCustomterCode.trim();
    }

    public String getDictCustomerType() {
        return dictCustomerType;
    }

    public void setDictCustomerType(String dictCustomerType) {
        this.dictCustomerType = dictCustomerType == null ? null : dictCustomerType.trim();
    }

	public String getHisLoanCode() {
		return hisLoanCode;
	}

	public void setHisLoanCode(String hisLoanCode) {
		this.hisLoanCode = hisLoanCode;
	}

	public String getHisAmount() {
		return hisAmount;
	}

	public void setHisAmount(String hisAmount) {
		this.hisAmount = hisAmount;
	}
}