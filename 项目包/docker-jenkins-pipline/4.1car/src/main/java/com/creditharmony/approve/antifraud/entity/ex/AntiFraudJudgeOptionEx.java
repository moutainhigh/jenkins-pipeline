package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;


/**
 * 反欺诈内部加黑灰项
 * @Class Name AntiFraudJudgeOptionEx
 * @author wanglidong
 * @Create In 2015年12月14日
 */
public class AntiFraudJudgeOptionEx {
	
	private String id;						// 主借人id
	private String customerPhoneFirst;		// 移动电话
	private String customerPhoneSecond;		// 移动电话2
	private String customerCertNum;			// 申请人证件号码
	private String customerTel;				// 固定电话
	private String customerAddress;			// 详细地址
	private String compName;				// 单位名称
	private String compAddress;				// 详细地址
	private String compTel;					// 公司电话
	private String mateTel;					// 配偶手机
	private String mateCertNum;				// 配偶证件号码
	private List<String> contactMobile;		// 联系人手机
	
	private List<String> contactHomeTel;		// 联系人手机
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerPhoneFirst() {
		return customerPhoneFirst;
	}
	public String getCompTel() {
		return compTel;
	}
	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}
	public void setCustomerPhoneFirst(String customerPhoneFirst) {
		this.customerPhoneFirst = customerPhoneFirst;
	}
	public String getCustomerPhoneSecond() {
		return customerPhoneSecond;
	}
	public void setCustomerPhoneSecond(String customerPhoneSecond) {
		this.customerPhoneSecond = customerPhoneSecond;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCompName() {
		return compName;
	}
	public void setCompName(String compName) {
		this.compName = compName;
	}
	public String getCompAddress() {
		return compAddress;
	}
	public void setCompAddress(String compAddress) {
		this.compAddress = compAddress;
	}
	public String getMateTel() {
		return mateTel;
	}
	public void setMateTel(String mateTel) {
		this.mateTel = mateTel;
	}
	public String getMateCertNum() {
		return mateCertNum;
	}
	public void setMateCertNum(String mateCertNum) {
		this.mateCertNum = mateCertNum;
	}
	public List<String> getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(List<String> contactMobile) {
		this.contactMobile = contactMobile;
	}
	public List<String> getContactHomeTel() {
		return contactHomeTel;
	}
	public void setContactHomeTel(List<String> contactHomeTel) {
		this.contactHomeTel = contactHomeTel;
	}
	

}
