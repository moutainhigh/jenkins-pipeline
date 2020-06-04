package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;

/**
 * 共借人内部加黑项
 * @Class Name AntiFraudJudgeOptionEx
 * @author wanglidong
 * @Create In 2015年12月14日
 */
public class CoborrowerOptionEx {
	
	private String id;						// id
	private String coboName;				// 共借人姓名
	private String coboCertNum;				// 共借人证件号码
	private String coboFamilyTel;			// 共借人固定电话
	private String coboMobile;				// 共借人手机号1
	private String coboMobile2;				// 共借人手机号2
	private String coboNowAddress;			// 共借人现住址	
	private List<String> contactMobile;		// 共借人联系人手机
	
	private List<String> contactHomeTel;		// 共借人联系人宅电
	
	public String getCoboName() {
		return coboName;
	}
	public void setCoboName(String coboName) {
		this.coboName = coboName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(List<String> contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getCoboCertNum() {
		return coboCertNum;
	}
	public void setCoboCertNum(String coboCertNum) {
		this.coboCertNum = coboCertNum;
	}
	public String getCoboFamilyTel() {
		return coboFamilyTel;
	}

	public void setCoboFamilyTel(String coboFamilyTel) {
		this.coboFamilyTel = coboFamilyTel;
	}
	public String getCoboMobile() {
		return coboMobile;
	}
	public void setCoboMobile(String coboMobile) {
		this.coboMobile = coboMobile;
	}
	public String getCoboMobile2() {
		return coboMobile2;
	}
	public void setCoboMobile2(String coboMobile2) {
		this.coboMobile2 = coboMobile2;
	}
	public String getCoboNowAddress() {
		return coboNowAddress;
	}
	public void setCoboNowAddress(String coboNowAddress) {
		this.coboNowAddress = coboNowAddress;
	}
	public List<String> getContactHomeTel() {
		return contactHomeTel;
	}
	public void setContactHomeTel(List<String> contactHomeTel) {
		this.contactHomeTel = contactHomeTel;
	}


}
