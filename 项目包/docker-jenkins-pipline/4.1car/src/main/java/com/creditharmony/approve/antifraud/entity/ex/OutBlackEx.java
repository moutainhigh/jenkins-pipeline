package com.creditharmony.approve.antifraud.entity.ex;

/**
 * 外部拉黑entity
 * @Class Name OutBlackEx
 * @author wanglidong
 * @Create In 2015年12月1日
 */
public class OutBlackEx {
	private String loanCode;					//借款编号
	private String certNum;					//证件号
	private String mobilePhone;				//手机号
	private String familyPhone;				//固话
	private String unitName;				//单位名称
	//private String linkmanPhone;				//单位名称
		

	/**
	 * @return the linkmanPhone
	 */
/*	public String getLinkmanPhone() {
		return linkmanPhone;
	}
	*//**
	 * @param linkmanPhone the String linkmanPhone to set
	 *//*
	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}*/
	
	public String getCertNum() {
		return certNum;
	}

	public String getLoanCode() {
		return loanCode;
	}
	/**
	 * @param loanCode the String loanCode to set
	 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
