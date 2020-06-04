package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 详版信用报告居住信息
 * @Class Name CreditLiveInfo
 * @author 李文勇
 * @Create In 2016年1月6日
 */
public class CreditLiveInfo extends DataEntity<CreditLiveInfo> {

	
	private static final long serialVersionUID = 1L;
	
	private String relationId;			// 关联详版信用报告ID
	private String mail;				// 邮箱
	private String liveSituation;		// 居住状况
	private Date getinfoTime;			// 信息获取时间
	private String liveProvince;		// 居住省
	private String liveCity;			// 居住市
	private String liveArea;			// 居住区
	private String liveAddress;			// 详细地址
	
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLiveSituation() {
		return liveSituation;
	}
	public void setLiveSituation(String liveSituation) {
		this.liveSituation = liveSituation;
	}
	public Date getGetinfoTime() {
		return getinfoTime;
	}
	public void setGetinfoTime(Date getinfoTime) {
		this.getinfoTime = getinfoTime;
	}
	public String getLiveProvince() {
		return liveProvince;
	}
	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	public String getLiveArea() {
		return liveArea;
	}
	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
}
