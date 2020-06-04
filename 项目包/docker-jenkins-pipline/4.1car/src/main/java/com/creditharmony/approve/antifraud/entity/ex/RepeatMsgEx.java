package com.creditharmony.approve.antifraud.entity.ex;

/**
 * 查重匹配内容entity
 * @Class Name RepeatMsgEx
 * @author wanglidong
 * @Create In 2016年2月17日
 */
public class RepeatMsgEx {
	private String customerCertNum;		//顾客证件号
	private String customerPhoneFirst;	//顾客手机号
	private String customerName;		//顾客姓名
	private String customerAddress;		//详细地址
	private String customerRegisterAddress;		//户籍地址
	private String compName;		//单位名称
	private String compEntryDay;		//入职时间
	private String compTel;		//单位电话
	private String compAddress;		//单位详细地址
	private String mateName;		//配偶姓名
	private String mateCertNum;		//配偶证件号码
	private String mateTel;		//配偶手机号
	private String contactName;		//联系人姓名
	private String contactSex;		//联系人单位名称
	private String contactMobile;		//联系人手机号
	private String customerIntoTime;		//进件时间

	public String getCustomerIntoTime() {
		return customerIntoTime;
	}
	public void setCustomerIntoTime(String customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactSex() {
		return contactSex;
	}
	public void setContactSex(String contactSex) {
		this.contactSex = contactSex;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getCompEntryDay() {
		return compEntryDay;
	}
	public void setCompEntryDay(String compEntryDay) {
		this.compEntryDay = compEntryDay;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerRegisterAddress() {
		return customerRegisterAddress;
	}
	public void setCustomerRegisterAddress(String customerRegisterAddress) {
		this.customerRegisterAddress = customerRegisterAddress;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompTel() {
		return compTel;
	}
	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}
	public String getCompAddress() {
		return compAddress;
	}
	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}
	public String getMateName() {
		return mateName;
	}
	public void setMateName(String mateName) {
		this.mateName = mateName;
	}
	public String getMateCertNum() {
		return mateCertNum;
	}
	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}
	public String getMateTel() {
		return mateTel;
	}
	public void setMateTel(String mateTel) {
		this.mateTel = mateTel;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getCustomerPhoneFirst() {
		return customerPhoneFirst;
	}
	public void setCustomerPhoneFirst(String customerPhoneFirst) {
		this.customerPhoneFirst = customerPhoneFirst;
	}

}
