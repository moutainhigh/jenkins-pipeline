package com.creditharmony.approve.carloan.entity;

import java.util.Date;

public class LoanCustomer {
    private String customerCode;

    private String id;

    private String customerName;

    private String dictCertType;

    private String customerCertNum;

    private Date idStartDay;

    private Date idEndDay;

    private String isLongTerm;

    private String dictSex;

    private String dictCustomerRegisterProvince;

    private String dictCustomerRegisterCity;

    private String dictCustomerRegisterArea;

    private String customerRegisterAddress;

    private Date customerBirthday;

    private String dictMarryStatus;

    private String dictEducation;

    private String customerPhoneFirst;

    private String customerTel;

    private String customerEmail;

    private String dictCustomerSource;

    private String dictCustomerHaveChildren;

    private String dictCustomerLiveProvince;

    private String dictCustomerLiveCity;

    private String dictCustomerLiveArea;

    private String customerAddress;

    private String customerCertOrg;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    private String customerTempPermit;

    private String customerHouseHoldProperty;

    private Date customerFirstLivingDay;

    private String cityPhone;

    private String customerFirtArriveYear;

    private Long creditLine;

    private Short customerFamilySupport;
    
    private String registerProperty;//户籍性质

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getDictCertType() {
        return dictCertType;
    }

    public void setDictCertType(String dictCertType) {
        this.dictCertType = dictCertType == null ? null : dictCertType.trim();
    }

    public String getCustomerCertNum() {
        return customerCertNum;
    }

    public void setCustomerCertNum(String customerCertNum) {
        this.customerCertNum = customerCertNum == null ? null : customerCertNum.trim();
    }

    public Date getIdStartDay() {
        return idStartDay;
    }

    public void setIdStartDay(Date idStartDay) {
        this.idStartDay = idStartDay;
    }

    public Date getIdEndDay() {
        return idEndDay;
    }

    public void setIdEndDay(Date idEndDay) {
        this.idEndDay = idEndDay;
    }

    public String getIsLongTerm() {
        return isLongTerm;
    }

    public void setIsLongTerm(String isLongTerm) {
        this.isLongTerm = isLongTerm == null ? null : isLongTerm.trim();
    }

    public String getDictSex() {
        return dictSex;
    }

    public void setDictSex(String dictSex) {
        this.dictSex = dictSex == null ? null : dictSex.trim();
    }

    public String getDictCustomerRegisterProvince() {
        return dictCustomerRegisterProvince;
    }

    public void setDictCustomerRegisterProvince(String dictCustomerRegisterProvince) {
        this.dictCustomerRegisterProvince = dictCustomerRegisterProvince == null ? null : dictCustomerRegisterProvince.trim();
    }

    public String getDictCustomerRegisterCity() {
        return dictCustomerRegisterCity;
    }

    public void setDictCustomerRegisterCity(String dictCustomerRegisterCity) {
        this.dictCustomerRegisterCity = dictCustomerRegisterCity == null ? null : dictCustomerRegisterCity.trim();
    }

    public String getDictCustomerRegisterArea() {
        return dictCustomerRegisterArea;
    }

    public void setDictCustomerRegisterArea(String dictCustomerRegisterArea) {
        this.dictCustomerRegisterArea = dictCustomerRegisterArea == null ? null : dictCustomerRegisterArea.trim();
    }

    public String getCustomerRegisterAddress() {
        return customerRegisterAddress;
    }

    public void setCustomerRegisterAddress(String customerRegisterAddress) {
        this.customerRegisterAddress = customerRegisterAddress == null ? null : customerRegisterAddress.trim();
    }

    public Date getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(Date customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public String getDictMarryStatus() {
        return dictMarryStatus;
    }

    public void setDictMarryStatus(String dictMarryStatus) {
        this.dictMarryStatus = dictMarryStatus == null ? null : dictMarryStatus.trim();
    }

    public String getDictEducation() {
        return dictEducation;
    }

    public void setDictEducation(String dictEducation) {
        this.dictEducation = dictEducation == null ? null : dictEducation.trim();
    }

    public String getCustomerPhoneFirst() {
        return customerPhoneFirst;
    }

    public void setCustomerPhoneFirst(String customerPhoneFirst) {
        this.customerPhoneFirst = customerPhoneFirst == null ? null : customerPhoneFirst.trim();
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel == null ? null : customerTel.trim();
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail == null ? null : customerEmail.trim();
    }

    public String getDictCustomerSource() {
        return dictCustomerSource;
    }

    public void setDictCustomerSource(String dictCustomerSource) {
        this.dictCustomerSource = dictCustomerSource == null ? null : dictCustomerSource.trim();
    }

    public String getDictCustomerHaveChildren() {
        return dictCustomerHaveChildren;
    }

    public void setDictCustomerHaveChildren(String dictCustomerHaveChildren) {
        this.dictCustomerHaveChildren = dictCustomerHaveChildren == null ? null : dictCustomerHaveChildren.trim();
    }

    public String getDictCustomerLiveProvince() {
        return dictCustomerLiveProvince;
    }

    public void setDictCustomerLiveProvince(String dictCustomerLiveProvince) {
        this.dictCustomerLiveProvince = dictCustomerLiveProvince == null ? null : dictCustomerLiveProvince.trim();
    }

    public String getDictCustomerLiveCity() {
        return dictCustomerLiveCity;
    }

    public void setDictCustomerLiveCity(String dictCustomerLiveCity) {
        this.dictCustomerLiveCity = dictCustomerLiveCity == null ? null : dictCustomerLiveCity.trim();
    }

    public String getDictCustomerLiveArea() {
        return dictCustomerLiveArea;
    }

    public void setDictCustomerLiveArea(String dictCustomerLiveArea) {
        this.dictCustomerLiveArea = dictCustomerLiveArea == null ? null : dictCustomerLiveArea.trim();
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress == null ? null : customerAddress.trim();
    }

    public String getCustomerCertOrg() {
        return customerCertOrg;
    }

    public void setCustomerCertOrg(String customerCertOrg) {
        this.customerCertOrg = customerCertOrg == null ? null : customerCertOrg.trim();
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

    public String getCustomerTempPermit() {
        return customerTempPermit;
    }

    public void setCustomerTempPermit(String customerTempPermit) {
        this.customerTempPermit = customerTempPermit == null ? null : customerTempPermit.trim();
    }

    public String getCustomerHouseHoldProperty() {
        return customerHouseHoldProperty;
    }

    public void setCustomerHouseHoldProperty(String customerHouseHoldProperty) {
        this.customerHouseHoldProperty = customerHouseHoldProperty == null ? null : customerHouseHoldProperty.trim();
    }

    public Date getCustomerFirstLivingDay() {
        return customerFirstLivingDay;
    }

    public void setCustomerFirstLivingDay(Date customerFirstLivingDay) {
        this.customerFirstLivingDay = customerFirstLivingDay;
    }

    public String getCityPhone() {
        return cityPhone;
    }

    public void setCityPhone(String cityPhone) {
        this.cityPhone = cityPhone == null ? null : cityPhone.trim();
    }

    public String getCustomerFirtArriveYear() {
        return customerFirtArriveYear;
    }

    public void setCustomerFirtArriveYear(String customerFirtArriveYear) {
        this.customerFirtArriveYear = customerFirtArriveYear == null ? null : customerFirtArriveYear.trim();
    }

    public Long getCreditLine() {
        return creditLine;
    }

    public void setCreditLine(Long creditLine) {
        this.creditLine = creditLine;
    }

    public Short getCustomerFamilySupport() {
        return customerFamilySupport;
    }

    public void setCustomerFamilySupport(Short customerFamilySupport) {
        this.customerFamilySupport = customerFamilySupport;
    }

	public String getRegisterProperty() {
		return registerProperty;
	}

	public void setRegisterProperty(String registerProperty) {
		this.registerProperty = registerProperty;
	}
    
}