package com.creditharmony.approve.carloan.entity;

import java.util.Date;

public class CustomerCompany {
    private String loanCode;

    private String id;

    private String rCustomerCoborrowerCode;

    private String dictCustomerType;

    private String companyName;

    private String dictCompanyProvince;

    private String dictCompanyCity;

    private String dictCompanyArea;

    private String companyAddress;

    private String dictDepartment;

    private Date establishedTime;

    private String workTelephone;

    private String dictPositionLevel;

    private Double monthlyPay;

    private String isOtherRevenue;

    private String dictUnitNature;

    private String dictEnterpriseNature;

    private Date firstServiceDate;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getrCustomerCoborrowerCode() {
        return rCustomerCoborrowerCode;
    }

    public void setrCustomerCoborrowerCode(String rCustomerCoborrowerCode) {
        this.rCustomerCoborrowerCode = rCustomerCoborrowerCode == null ? null : rCustomerCoborrowerCode.trim();
    }

    public String getDictCustomerType() {
        return dictCustomerType;
    }

    public void setDictCustomerType(String dictCustomerType) {
        this.dictCustomerType = dictCustomerType == null ? null : dictCustomerType.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getDictCompanyProvince() {
        return dictCompanyProvince;
    }

    public void setDictCompanyProvince(String dictCompanyProvince) {
        this.dictCompanyProvince = dictCompanyProvince == null ? null : dictCompanyProvince.trim();
    }

    public String getDictCompanyCity() {
        return dictCompanyCity;
    }

    public void setDictCompanyCity(String dictCompanyCity) {
        this.dictCompanyCity = dictCompanyCity == null ? null : dictCompanyCity.trim();
    }

    public String getDictCompanyArea() {
        return dictCompanyArea;
    }

    public void setDictCompanyArea(String dictCompanyArea) {
        this.dictCompanyArea = dictCompanyArea == null ? null : dictCompanyArea.trim();
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    public String getDictDepartment() {
        return dictDepartment;
    }

    public void setDictDepartment(String dictDepartment) {
        this.dictDepartment = dictDepartment == null ? null : dictDepartment.trim();
    }

    public Date getEstablishedTime() {
        return establishedTime;
    }

    public void setEstablishedTime(Date establishedTime) {
        this.establishedTime = establishedTime;
    }

    public String getWorkTelephone() {
        return workTelephone;
    }

    public void setWorkTelephone(String workTelephone) {
        this.workTelephone = workTelephone == null ? null : workTelephone.trim();
    }

    public String getDictPositionLevel() {
        return dictPositionLevel;
    }

    public void setDictPositionLevel(String dictPositionLevel) {
        this.dictPositionLevel = dictPositionLevel == null ? null : dictPositionLevel.trim();
    }

    public Double getMonthlyPay() {
        return monthlyPay;
    }

    public void setMonthlyPay(Double monthlyPay) {
        this.monthlyPay = monthlyPay;
    }

    public String getIsOtherRevenue() {
        return isOtherRevenue;
    }

    public void setIsOtherRevenue(String isOtherRevenue) {
        this.isOtherRevenue = isOtherRevenue == null ? null : isOtherRevenue.trim();
    }

    public String getDictUnitNature() {
        return dictUnitNature;
    }

    public void setDictUnitNature(String dictUnitNature) {
        this.dictUnitNature = dictUnitNature == null ? null : dictUnitNature.trim();
    }

    public String getDictEnterpriseNature() {
        return dictEnterpriseNature;
    }

    public void setDictEnterpriseNature(String dictEnterpriseNature) {
        this.dictEnterpriseNature = dictEnterpriseNature == null ? null : dictEnterpriseNature.trim();
    }

    public Date getFirstServiceDate() {
        return firstServiceDate;
    }

    public void setFirstServiceDate(Date firstServiceDate) {
        this.firstServiceDate = firstServiceDate;
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