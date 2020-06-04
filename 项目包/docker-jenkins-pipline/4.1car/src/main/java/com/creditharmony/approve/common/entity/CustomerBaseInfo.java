package com.creditharmony.approve.common.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 客户基本信息表
 * @Class Name CustomerBaseInfo
 * @author zhangping
 * @Create In 2015年11月19日
 */
public class CustomerBaseInfo extends DataEntity<CustomerBaseInfo> {

	private static final long serialVersionUID = -4826593454690408310L;
	
    private Customer customer;                 //客户信息
    private String customerCode;               //客户编码
    private String customerName;                 //客户姓名
    private String customerSex;                  //性别
    private Date customerBirthday;               //出生日期
    private String dictCertType;     			 //证件类型
    private String mateCertNum;     			 //证件号码
    private String customerCertOrg;              //发证机关
    private String idStartDate;                    //身份证有效期开始时间
    private String idEndDate;            			 //身份证有效期结束时间
    private String customerMobilePhone;     	 //手机号
    private String dictCompIndustry;    	     //行业类别
    private String customerNameOcr;    			 //客户姓名ocr地址
    private String customerCretOcr;    			 //客户身份证ocr地址
    
    
    
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerSex() {
		return customerSex;
	}
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
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
		this.dictCertType = dictCertType;
	}
	public String getMateCertNum() {
		return mateCertNum;
	}
	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}
	public String getCustomerCertOrg() {
		return customerCertOrg;
	}
	public void setCustomerCertOrg(String customerCertOrg) {
		this.customerCertOrg = customerCertOrg;
	}
	
	public String getIdStartDate() {
		return idStartDate;
	}
	public void setIdStartDate(String idStartDate) {
		this.idStartDate = idStartDate;
	}
	public String getIdEndDate() {
		return idEndDate;
	}
	public void setIdEndDate(String idEndDate) {
		this.idEndDate = idEndDate;
	}
	public String getCustomerMobilePhone() {
		return customerMobilePhone;
	}
	public void setCustomerMobilePhone(String customerMobilePhone) {
		this.customerMobilePhone = customerMobilePhone;
	}
	public String getDictCompIndustry() {
		return dictCompIndustry;
	}
	public void setDictCompIndustry(String dictCompIndustry) {
		this.dictCompIndustry = dictCompIndustry;
	}
	public String getCustomerNameOcr() {
		return customerNameOcr;
	}
	public void setCustomerNameOcr(String customerNameOcr) {
		this.customerNameOcr = customerNameOcr;
	}
	public String getCustomerCretOcr() {
		return customerCretOcr;
	}
	public void setCustomerCretOcr(String customerCretOcr) {
		this.customerCretOcr = customerCretOcr;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime
				* result
				+ ((customerBirthday == null) ? 0 : customerBirthday.hashCode());
		result = prime * result
				+ ((customerCertOrg == null) ? 0 : customerCertOrg.hashCode());
		result = prime * result
				+ ((customerCretOcr == null) ? 0 : customerCretOcr.hashCode());
		result = prime
				* result
				+ ((customerMobilePhone == null) ? 0 : customerMobilePhone
						.hashCode());
		result = prime * result
				+ ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result
				+ ((customerNameOcr == null) ? 0 : customerNameOcr.hashCode());
		result = prime * result
				+ ((customerSex == null) ? 0 : customerSex.hashCode());
		result = prime * result
				+ ((dictCertType == null) ? 0 : dictCertType.hashCode());
		result = prime
				* result
				+ ((dictCompIndustry == null) ? 0 : dictCompIndustry.hashCode());
		result = prime * result
				+ ((idEndDate == null) ? 0 : idEndDate.hashCode());
		result = prime * result
				+ ((idStartDate == null) ? 0 : idStartDate.hashCode());
		result = prime * result
				+ ((mateCertNum == null) ? 0 : mateCertNum.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerBaseInfo other = (CustomerBaseInfo) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (customerBirthday == null) {
			if (other.customerBirthday != null)
				return false;
		} else if (!customerBirthday.equals(other.customerBirthday))
			return false;
		if (customerCertOrg == null) {
			if (other.customerCertOrg != null)
				return false;
		} else if (!customerCertOrg.equals(other.customerCertOrg))
			return false;
		if (customerCretOcr == null) {
			if (other.customerCretOcr != null)
				return false;
		} else if (!customerCretOcr.equals(other.customerCretOcr))
			return false;
		if (customerMobilePhone == null) {
			if (other.customerMobilePhone != null)
				return false;
		} else if (!customerMobilePhone.equals(other.customerMobilePhone))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (customerNameOcr == null) {
			if (other.customerNameOcr != null)
				return false;
		} else if (!customerNameOcr.equals(other.customerNameOcr))
			return false;
		if (customerSex == null) {
			if (other.customerSex != null)
				return false;
		} else if (!customerSex.equals(other.customerSex))
			return false;
		if (dictCertType == null) {
			if (other.dictCertType != null)
				return false;
		} else if (!dictCertType.equals(other.dictCertType))
			return false;
		if (dictCompIndustry == null) {
			if (other.dictCompIndustry != null)
				return false;
		} else if (!dictCompIndustry.equals(other.dictCompIndustry))
			return false;
		if (idEndDate == null) {
			if (other.idEndDate != null)
				return false;
		} else if (!idEndDate.equals(other.idEndDate))
			return false;
		if (idStartDate == null) {
			if (other.idStartDate != null)
				return false;
		} else if (!idStartDate.equals(other.idStartDate))
			return false;
		if (mateCertNum == null) {
			if (other.mateCertNum != null)
				return false;
		} else if (!mateCertNum.equals(other.mateCertNum))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CustomerBaseInfo [customer=" + customer + ", customerName="
				+ customerName + ", customerSex=" + customerSex
				+ ", customerBirthday=" + customerBirthday + ", dictCertType="
				+ dictCertType + ", mateCertNum=" + mateCertNum
				+ ", customerCertOrg=" + customerCertOrg + ", idStartDate="
				+ idStartDate + ", idEndDate=" + idEndDate
				+ ", customerMobilePhone=" + customerMobilePhone
				+ ", dictCompIndustry=" + dictCompIndustry
				+ ", customerNameOcr=" + customerNameOcr + ", customerCretOcr="
				+ customerCretOcr + "]";
	}
   
    
    
	
    
}
