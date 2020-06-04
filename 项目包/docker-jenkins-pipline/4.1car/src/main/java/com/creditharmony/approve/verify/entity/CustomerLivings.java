package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CustomerLivings {
    private BigDecimal id;

    private String loanCode;

    private String rId;

    private String loanCustomterType;

    private String customertmpResidentialPermit;

    private String customerFirtArriveYear;

    private String customerResidential;

    private Date customerFirtLivingTime;

    private String customerFamilySupport;

    private String customerHousingSituation;

    private String customerHouseHoldHold;

    private String customerHouseholdProvince;

    private String customerHouseholdCity;

    private String customerHouseholdArea;

    private String customerHouseholdAddress;

    private BigDecimal customerMonthpay;

    private BigDecimal customerMonrental;

    private String customerHouseHoldProperty;

    private String customerHaveLive;

    private String customerLivingNum;
    
    private String loanCustomerType;

    private String dictHouseType;

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

    public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getLoanCustomterType() {
        return loanCustomterType;
    }

    public void setLoanCustomterType(String loanCustomterType) {
        this.loanCustomterType = loanCustomterType == null ? null : loanCustomterType.trim();
    }

    public String getCustomertmpResidentialPermit() {
        return customertmpResidentialPermit;
    }

    public void setCustomertmpResidentialPermit(String customertmpResidentialPermit) {
        this.customertmpResidentialPermit = customertmpResidentialPermit == null ? null : customertmpResidentialPermit.trim();
    }

    public String getCustomerFirtArriveYear() {
        return customerFirtArriveYear;
    }

    public void setCustomerFirtArriveYear(String customerFirtArriveYear) {
        this.customerFirtArriveYear = customerFirtArriveYear == null ? null : customerFirtArriveYear.trim();
    }

    public String getCustomerResidential() {
        return customerResidential;
    }

    public void setCustomerResidential(String customerResidential) {
        this.customerResidential = customerResidential == null ? null : customerResidential.trim();
    }

    public Date getCustomerFirtLivingTime() {
        return customerFirtLivingTime;
    }

    public void setCustomerFirtLivingTime(Date customerFirtLivingTime) {
        this.customerFirtLivingTime = customerFirtLivingTime;
    }

    public String getCustomerFamilySupport() {
        return customerFamilySupport;
    }

    public void setCustomerFamilySupport(String customerFamilySupport) {
        this.customerFamilySupport = customerFamilySupport == null ? null : customerFamilySupport.trim();
    }

    public String getCustomerHousingSituation() {
        return customerHousingSituation;
    }

    public void setCustomerHousingSituation(String customerHousingSituation) {
        this.customerHousingSituation = customerHousingSituation == null ? null : customerHousingSituation.trim();
    }

    public String getCustomerHouseHoldHold() {
        return customerHouseHoldHold;
    }

    public void setCustomerHouseHoldHold(String customerHouseHoldHold) {
        this.customerHouseHoldHold = customerHouseHoldHold == null ? null : customerHouseHoldHold.trim();
    }

    public String getCustomerHouseholdProvince() {
        return customerHouseholdProvince;
    }

    public void setCustomerHouseholdProvince(String customerHouseholdProvince) {
        this.customerHouseholdProvince = customerHouseholdProvince == null ? null : customerHouseholdProvince.trim();
    }

    public String getCustomerHouseholdCity() {
        return customerHouseholdCity;
    }

    public void setCustomerHouseholdCity(String customerHouseholdCity) {
        this.customerHouseholdCity = customerHouseholdCity == null ? null : customerHouseholdCity.trim();
    }

    public String getCustomerHouseholdArea() {
        return customerHouseholdArea;
    }

    public void setCustomerHouseholdArea(String customerHouseholdArea) {
        this.customerHouseholdArea = customerHouseholdArea == null ? null : customerHouseholdArea.trim();
    }

    public String getCustomerHouseholdAddress() {
        return customerHouseholdAddress;
    }

    public void setCustomerHouseholdAddress(String customerHouseholdAddress) {
        this.customerHouseholdAddress = customerHouseholdAddress == null ? null : customerHouseholdAddress.trim();
    }

    public BigDecimal getCustomerMonthpay() {
        return customerMonthpay;
    }

    public void setCustomerMonthpay(BigDecimal customerMonthpay) {
        this.customerMonthpay = customerMonthpay;
    }

    public BigDecimal getCustomerMonrental() {
        return customerMonrental;
    }

    public void setCustomerMonrental(BigDecimal customerMonrental) {
        this.customerMonrental = customerMonrental;
    }

    public String getCustomerHouseHoldProperty() {
        return customerHouseHoldProperty;
    }

    public void setCustomerHouseHoldProperty(String customerHouseHoldProperty) {
        this.customerHouseHoldProperty = customerHouseHoldProperty == null ? null : customerHouseHoldProperty.trim();
    }

    public String getCustomerHaveLive() {
        return customerHaveLive;
    }

    public void setCustomerHaveLive(String customerHaveLive) {
        this.customerHaveLive = customerHaveLive == null ? null : customerHaveLive.trim();
    }

    public String getCustomerLivingNum() {
        return customerLivingNum;
    }

    public void setCustomerLivingNum(String customerLivingNum) {
        this.customerLivingNum = customerLivingNum == null ? null : customerLivingNum.trim();
    }

    public String getDictHouseType() {
        return dictHouseType;
    }

    public void setDictHouseType(String dictHouseType) {
        this.dictHouseType = dictHouseType == null ? null : dictHouseType.trim();
    }

    
    
    public String getLoanCustomerType() {
		return loanCustomerType;
	}

	public void setLoanCustomerType(String loanCustomerType) {
		this.loanCustomerType = loanCustomerType == null ? null : loanCustomerType.trim();
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