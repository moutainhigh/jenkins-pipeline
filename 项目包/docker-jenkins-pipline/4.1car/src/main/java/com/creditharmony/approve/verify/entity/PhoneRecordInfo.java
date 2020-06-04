package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 电话录音信息
 * @Class Name PhoneRecordInfo
 * @author 刘燕军
 * @Create In 2015年12月10日
 */
public class PhoneRecordInfo extends DataEntity<PhoneRecordInfo> {
	private static final long serialVersionUID = 1L;
	private String relationId; // 关联id
	private String loanCode; // 借款编号
	private String phone; // 电话号码
	private Date callTime; // 拨打时间
	private String answerState; // 接听状态
	private String answerInfo; // 接听描述
	private String phoneSource; // 电话来源
	private String tableSource; // 来源表
	private String filePath; // 文件路径
	private String remark; // 备注信息

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCallTime() {
		return callTime;
	}

	public void setCallTime(Date callTime) {
		this.callTime = callTime;
	}

	public String getAnswerState() {
		return answerState;
	}

	public void setAnswerState(String answerState) {
		this.answerState = answerState;
	}

	public String getAnswerInfo() {
		return answerInfo;
	}

	public void setAnswerInfo(String answerInfo) {
		this.answerInfo = answerInfo;
	}

	public String getPhoneSource() {
		return phoneSource;
	}

	public void setPhoneSource(String phoneSource) {
		this.phoneSource = phoneSource;
	}

	public String getTableSource() {
		return tableSource;
	}

	public void setTableSource(String tableSource) {
		this.tableSource = tableSource;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
