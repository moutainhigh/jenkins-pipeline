package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CustomerBase extends DataEntity<CustomerBase>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerCode;

    private String id;

    private String customerName;

    private String customerSex;

    private Date customerBirthday;

    private String dictCertType;

    private String customerCertNum;

    private String customerCertOrg;

    private Date idStartTimestamp;

    private Date idEndTimestamp;

    private String customerMobilePhone;

    private String dictCompIndustry;

    private String customerNameOcr;

    private String customerCretOcr;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

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
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getCustomerSex() {
        return customerSex;
    }

    public void setCustomerSex(String customerSex) {
        this.customerSex = customerSex == null ? null : customerSex.trim();
    }

    public Date getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(Date customerBirthday) {
        this.customerBirthday = customerBirthday;
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

    public String getCustomerCertOrg() {
        return customerCertOrg;
    }

    public void setCustomerCertOrg(String customerCertOrg) {
        this.customerCertOrg = customerCertOrg == null ? null : customerCertOrg.trim();
    }

    public Date getIdStartTimestamp() {
        return idStartTimestamp;
    }

    public void setIdStartTimestamp(Date idStartTimestamp) {
        this.idStartTimestamp = idStartTimestamp;
    }

    public Date getIdEndTimestamp() {
        return idEndTimestamp;
    }

    public void setIdEndTimestamp(Date idEndTimestamp) {
        this.idEndTimestamp = idEndTimestamp;
    }

    public String getCustomerMobilePhone() {
        return customerMobilePhone;
    }

    public void setCustomerMobilePhone(String customerMobilePhone) {
        this.customerMobilePhone = customerMobilePhone == null ? null : customerMobilePhone.trim();
    }

    public String getDictCompIndustry() {
        return dictCompIndustry;
    }

    public void setDictCompIndustry(String dictCompIndustry) {
        this.dictCompIndustry = dictCompIndustry == null ? null : dictCompIndustry.trim();
    }

    public String getCustomerNameOcr() {
        return customerNameOcr;
    }

    public void setCustomerNameOcr(String customerNameOcr) {
        this.customerNameOcr = customerNameOcr == null ? null : customerNameOcr.trim();
    }

    public String getCustomerCretOcr() {
        return customerCretOcr;
    }

    public void setCustomerCretOcr(String customerCretOcr) {
        this.customerCretOcr = customerCretOcr == null ? null : customerCretOcr.trim();
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