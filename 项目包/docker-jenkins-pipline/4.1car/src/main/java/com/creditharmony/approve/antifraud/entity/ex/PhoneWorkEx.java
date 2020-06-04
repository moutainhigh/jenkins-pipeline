package com.creditharmony.approve.antifraud.entity.ex;

import com.creditharmony.core.persistence.DataEntity;


/**
 *  电话照会，单位电话，电话录音 
 * @Class Name PhoneWorkEx
 * @author wanglidong
 * @Create In 2016年3月2日
 */
public class PhoneWorkEx extends DataEntity<PhoneWorkEx> {
	
	private static final long serialVersionUID = 1L;
	
	private String workId; // 关联 id
	private String workUnitTel; // 单位电话
	private String workTelSource; // 号码来源
	private String workUnittelTrue;//	查询情况
	private String exceptionRecord	; // 异常记录
	private String assessmentResult; // 评估结果(正常、异常、无效)
	private String workNetAssessResult; //	网查结果
	private String workCheckRemark; //	核查备注
	private String isRepeat;
	private String isInPool;
	private String editRemark;		// 是否可以编辑0编辑，1不可编辑
	private String telRemark;		// 电话备注
	
	private String loanId;		// 电话备注

	public String getTelRemark() {
		return telRemark;
	}
	public void setTelRemark(String telRemark) {
		this.telRemark = telRemark;
	}
	public String getEditRemark() {
		return editRemark;
	}
	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getWorkUnitTel() {
		return workUnitTel;
	}
	public void setWorkUnitTel(String workUnitTel) {
		this.workUnitTel = workUnitTel;
	}
	public String getWorkTelSource() {
		return workTelSource;
	}
	public void setWorkTelSource(String workTelSource) {
		this.workTelSource = workTelSource;
	}
	public String getWorkUnittelTrue() {
		return workUnittelTrue;
	}
	public void setWorkUnittelTrue(String workUnittelTrue) {
		this.workUnittelTrue = workUnittelTrue;
	}
	public String getExceptionRecord() {
		return exceptionRecord;
	}
	public void setExceptionRecord(String exceptionRecord) {
		this.exceptionRecord = exceptionRecord;
	}
	public String getAssessmentResult() {
		return assessmentResult;
	}
	public void setAssessmentResult(String assessmentResult) {
		this.assessmentResult = assessmentResult;
	}
	public String getWorkNetAssessResult() {
		return workNetAssessResult;
	}
	public void setWorkNetAssessResult(String workNetAssessResult) {
		this.workNetAssessResult = workNetAssessResult;
	}
	public String getWorkCheckRemark() {
		return workCheckRemark;
	}
	public void setWorkCheckRemark(String workCheckRemark) {
		this.workCheckRemark = workCheckRemark;
	}
	public String getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getIsInPool() {
		return isInPool;
	}
	public void setIsInPool(String isInPool) {
		this.isInPool = isInPool;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	
}
