package com.creditharmony.approve.phone.entity.ex;

import com.creditharmony.approve.phone.entity.DhzhDhgxsh;

/**
 * 初审-电话核查结果
 * @Class Name PhoneCheckResultEx
 * @author 赖敏
 * @Create In 2015年12月2日
 */
public class TelCheckResultEx extends DhzhDhgxsh{

	private static final long serialVersionUID = 1744945663380599092L;

	private String answerInfo; //接听描述
	private String telNum; //电话号码
	
	public String getAnswerInfo() {
		return answerInfo;
	}
	
	public void setAnswerInfo(String answerInfo) {
		this.answerInfo = answerInfo;
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	
}
