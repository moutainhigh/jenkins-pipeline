package com.creditharmony.approve.phone.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 电话照会_电话录音信息 实体类
 * 
 * @Class Name DhzhDhlyxx
 * @author 赖敏
 * @Create In 2015年11月29日
 */
public class DhzhDhlyxx extends DataEntity<DhzhDhlyxx> {

	private static final long serialVersionUID = -153556078714812431L;

	private String rGxId;
	private String loanCode;
	private String dhlyxxTel;
	private String dictCheckType;
	private Date dhlyxxCallTime;
	private String lyCode; // 电话录音唯一编号
	private String dhlyxxAnswerState;
	private String dhlyxxAnswerInf;
	private String dictVoiceSource;
	private String dhlyxxFilePath;
	private String dhlyxxRemark;
	private String dictSourceType;
	private String longDistancePhone; // 长途电话标识 1 为选中
	
	public String getLongDistancePhone() {
		return longDistancePhone;
	}

	public void setLongDistancePhone(String longDistancePhone) {
		this.longDistancePhone = longDistancePhone;
	}

	public String getDhlyxxTel() {
		return dhlyxxTel;
	}

	public void setDhlyxxTel(String dhlyxxTel) {
		this.dhlyxxTel = dhlyxxTel == null ? null : dhlyxxTel.trim();
	}

	public Date getDhlyxxCallTime() {
		return dhlyxxCallTime;
	}

	public void setDhlyxxCallTime(Date dhlyxxCallTime) {
		this.dhlyxxCallTime = dhlyxxCallTime;
	}

	public String getDhlyxxAnswerState() {
		return dhlyxxAnswerState;
	}

	public void setDhlyxxAnswerState(String dhlyxxAnswerState) {
		this.dhlyxxAnswerState = dhlyxxAnswerState == null ? null
				: dhlyxxAnswerState.trim();
	}

	public String getDhlyxxAnswerInf() {
		return dhlyxxAnswerInf;
	}

	public void setDhlyxxAnswerInf(String dhlyxxAnswerInf) {
		this.dhlyxxAnswerInf = dhlyxxAnswerInf == null ? null : dhlyxxAnswerInf
				.trim();
	}	

	public String getDictSourceType() {
		return dictSourceType;
	}

	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}

	public String getDictVoiceSource() {
		return dictVoiceSource;
	}

	public void setDictVoiceSource(String dictVoiceSource) {
		this.dictVoiceSource = dictVoiceSource == null ? null : dictVoiceSource
				.trim();
	}

	public String getDhlyxxFilePath() {
		return dhlyxxFilePath;
	}

	public void setDhlyxxFilePath(String dhlyxxFilePath) {
		this.dhlyxxFilePath = dhlyxxFilePath;
	}

	public String getDhlyxxRemark() {
		return dhlyxxRemark;
	}

	public void setDhlyxxRemark(String dhlyxxRemark) {
		this.dhlyxxRemark = dhlyxxRemark == null ? null : dhlyxxRemark.trim();
	}

	public String getrGxId() {
		return rGxId;
	}

	public void setrGxId(String rGxId) {
		this.rGxId = rGxId;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}

	public String getLyCode() {
		return lyCode;
	}

	public void setLyCode(String lyCode) {
		this.lyCode = lyCode;
	}

}