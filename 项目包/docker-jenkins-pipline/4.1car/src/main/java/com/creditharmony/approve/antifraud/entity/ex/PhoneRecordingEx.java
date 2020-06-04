package com.creditharmony.approve.antifraud.entity.ex;

import com.creditharmony.core.persistence.DataEntity;

/**
 *  电话照会，单位电话，电话录音 
 * @Class Name PhoneWorkEx
 * @author wanglidong
 * @Create In 2016年3月2日
 */
public class PhoneRecordingEx extends DataEntity<PhoneRecordingEx> {
	
	private static final long serialVersionUID = 1L;

	private String lyId;					//录音id
	private String answerState;				//接听状态
	private String answerInfo;				//接听描述

	public String getAnswerInfo() {
		return answerInfo;
	}
	public void setAnswerInfo(String answerInfo) {
		this.answerInfo = answerInfo;
	}
	public String getLyId() {
		return lyId;
	}
	public void setLyId(String lyId) {
		this.lyId = lyId;
	}
	public String getAnswerState() {
		return answerState;
	}
	public void setAnswerState(String answerState) {
		this.answerState = answerState;
	}

}
