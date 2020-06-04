package com.creditharmony.approve.carloan.entity;

import static com.creditharmony.approve.common.util.CryptoUtils.decryptPhones;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CarLoanCoborrower implements Serializable{

	private static final long serialVersionUID = -3401697376752721548L;

	private String loanCode;

    private String id;

    private String coboName;

    private String dictSex;

    private String certNum;

    private String dictHouseholdProvince;

    private String dictHouseholdCity;

    private String dictHouseholdArea;

    private String householdAddress;

    private String mobile;

    private String familyTel;

    private String dictMarryStatus;

    private String haveChildFlag;

    private String dictLiveProvince;

    private String dictLiveCity;

    private String dictLiveArea;

    private String nowAddress;

    private String email;

    private String contactIsKnow;

    private String dictRelationType;

    private String dictRelationCustomer;

    private String houseOther;

    private String dictSocialSecurity;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    
    private List<CustomerContactPerson> bContactPersons;

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

    public String getCoboName() {
        return coboName;
    }

    public void setCoboName(String coboName) {
        this.coboName = coboName == null ? null : coboName.trim();
    }

    public String getDictSex() {
        return dictSex;
    }

    public void setDictSex(String dictSex) {
        this.dictSex = dictSex == null ? null : dictSex.trim();
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum == null ? null : certNum.trim();
    }

    public String getDictHouseholdProvince() {
        return dictHouseholdProvince;
    }

    public void setDictHouseholdProvince(String dictHouseholdProvince) {
        this.dictHouseholdProvince = dictHouseholdProvince == null ? null : dictHouseholdProvince.trim();
    }

    public String getDictHouseholdCity() {
        return dictHouseholdCity;
    }

    public void setDictHouseholdCity(String dictHouseholdCity) {
        this.dictHouseholdCity = dictHouseholdCity == null ? null : dictHouseholdCity.trim();
    }

    public String getDictHouseholdArea() {
        return dictHouseholdArea;
    }

    public void setDictHouseholdArea(String dictHouseholdArea) {
        this.dictHouseholdArea = dictHouseholdArea == null ? null : dictHouseholdArea.trim();
    }

    public String getHouseholdAddress() {
        return householdAddress;
    }

    public void setHouseholdAddress(String householdAddress) {
        this.householdAddress = householdAddress == null ? null : householdAddress.trim();
    }

    public String getMobile() {
        if(mobile != null){
        	mobile = decryptPhones(mobile,"t_cj_loan_coborrower","mobile");
        }
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getFamilyTel() {
        return familyTel;
    }

    public void setFamilyTel(String familyTel) {
        this.familyTel = familyTel == null ? null : familyTel.trim();
    }

    public String getDictMarryStatus() {
        return dictMarryStatus;
    }

    public void setDictMarryStatus(String dictMarryStatus) {
        this.dictMarryStatus = dictMarryStatus == null ? null : dictMarryStatus.trim();
    }

    public String getHaveChildFlag() {
        return haveChildFlag;
    }

    public void setHaveChildFlag(String haveChildFlag) {
        this.haveChildFlag = haveChildFlag == null ? null : haveChildFlag.trim();
    }

    public String getDictLiveProvince() {
        return dictLiveProvince;
    }

    public void setDictLiveProvince(String dictLiveProvince) {
        this.dictLiveProvince = dictLiveProvince == null ? null : dictLiveProvince.trim();
    }

    public String getDictLiveCity() {
        return dictLiveCity;
    }

    public void setDictLiveCity(String dictLiveCity) {
        this.dictLiveCity = dictLiveCity == null ? null : dictLiveCity.trim();
    }

    public String getDictLiveArea() {
        return dictLiveArea;
    }

    public void setDictLiveArea(String dictLiveArea) {
        this.dictLiveArea = dictLiveArea == null ? null : dictLiveArea.trim();
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress == null ? null : nowAddress.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContactIsKnow() {
        return contactIsKnow;
    }

    public void setContactIsKnow(String contactIsKnow) {
        this.contactIsKnow = contactIsKnow == null ? null : contactIsKnow.trim();
    }

    public String getDictRelationType() {
        return dictRelationType;
    }

    public void setDictRelationType(String dictRelationType) {
        this.dictRelationType = dictRelationType == null ? null : dictRelationType.trim();
    }

    public String getDictRelationCustomer() {
        return dictRelationCustomer;
    }

    public void setDictRelationCustomer(String dictRelationCustomer) {
        this.dictRelationCustomer = dictRelationCustomer == null ? null : dictRelationCustomer.trim();
    }

    public String getHouseOther() {
        return houseOther;
    }

    public void setHouseOther(String houseOther) {
        this.houseOther = houseOther == null ? null : houseOther.trim();
    }

    public String getDictSocialSecurity() {
        return dictSocialSecurity;
    }

    public void setDictSocialSecurity(String dictSocialSecurity) {
        this.dictSocialSecurity = dictSocialSecurity == null ? null : dictSocialSecurity.trim();
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

	public List<CustomerContactPerson> getbContactPersons() {
		return bContactPersons;
	}

	public void setbContactPersons(List<CustomerContactPerson> bContactPersons) {
		this.bContactPersons = bContactPersons;
	}
}