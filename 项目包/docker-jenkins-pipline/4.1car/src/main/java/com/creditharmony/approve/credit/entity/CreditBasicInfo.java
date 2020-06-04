package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信_基础信息 
 * @Class Name CreditBasicInfo
 * @author zhanghu
 * @Create In 2015年12月31日
 */
public class CreditBasicInfo extends DataEntity<CreditBasicInfo> {

	private static final long serialVersionUID = 1L;

    private String loanCode;//借款编码

    private String creditName;//名称

    private String registrationNumber;//登记注册号

    private String registrationType;//登记注册类型

    private String taxRegistrationNumber;//国税登记号

    private String dictLoanCardState;//贷款卡状态

    private String landTaxRegistrationNumber;//地税登记号

    private Date registrationDate;//登记注册日期

    private Date expireDate;//有效截止日期
    
    private String province;//所在省
    
    private String city;//所在市
    
    private String area;//所在区
    
    private String address;//地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName == null ? null : creditName.trim();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber == null ? null : registrationNumber.trim();
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType == null ? null : registrationType.trim();
    }

    public String getTaxRegistrationNumber() {
        return taxRegistrationNumber;
    }

    public void setTaxRegistrationNumber(String taxRegistrationNumber) {
        this.taxRegistrationNumber = taxRegistrationNumber == null ? null : taxRegistrationNumber.trim();
    }

    public String getDictLoanCardState() {
        return dictLoanCardState;
    }

    public void setDictLoanCardState(String dictLoanCardState) {
        this.dictLoanCardState = dictLoanCardState == null ? null : dictLoanCardState.trim();
    }

    public String getLandTaxRegistrationNumber() {
        return landTaxRegistrationNumber;
    }

    public void setLandTaxRegistrationNumber(String landTaxRegistrationNumber) {
        this.landTaxRegistrationNumber = landTaxRegistrationNumber == null ? null : landTaxRegistrationNumber.trim();
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}