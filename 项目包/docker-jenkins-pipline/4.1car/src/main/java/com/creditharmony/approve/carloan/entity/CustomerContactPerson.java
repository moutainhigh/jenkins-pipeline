package com.creditharmony.approve.carloan.entity;

import static com.creditharmony.approve.common.util.CryptoUtils.decryptPhones;

import java.io.Serializable;
import java.util.Date;

public class CustomerContactPerson implements Serializable{
	
	private static final long serialVersionUID = 5564584895385226484L;

	private String loanCode;

    private String id;

    private String rCustomerCoborrowerCode;

    private String loanCustomterType;

    private String contactName;

    private String dictContactRelation;

    private String contactUint;

    private String dictContactNowAddress;

    private String contactUnitTel;

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

    public String getLoanCustomterType() {
        return loanCustomterType;
    }

    public void setLoanCustomterType(String loanCustomterType) {
        this.loanCustomterType = loanCustomterType == null ? null : loanCustomterType.trim();
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getDictContactRelation() {
        return dictContactRelation;
    }

    public void setDictContactRelation(String dictContactRelation) {
        this.dictContactRelation = dictContactRelation == null ? null : dictContactRelation.trim();
    }

    public String getContactUint() {
        return contactUint;
    }

    public void setContactUint(String contactUint) {
        this.contactUint = contactUint == null ? null : contactUint.trim();
    }

    public String getDictContactNowAddress() {
        return dictContactNowAddress;
    }

    public void setDictContactNowAddress(String dictContactNowAddress) {
        this.dictContactNowAddress = dictContactNowAddress == null ? null : dictContactNowAddress.trim();
    }

    public String getContactUnitTel() {
        if(contactUnitTel != null){
        	contactUnitTel = decryptPhones(contactUnitTel,"t_cj_customer_contact_person","contact_unit_tel");
        }
        return contactUnitTel;
    }

    public void setContactUnitTel(String contactUnitTel) {
        this.contactUnitTel = contactUnitTel == null ? null : contactUnitTel.trim();
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