package com.creditharmony.approve.phone.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 电话照会_本人核实_电话信息
 * 
 * @Class Name DhzhDhgxshDhxx
 * @author 张虎
 * @Create In 2016年9月22日
 * @update in 2016-10-27
 */
public class DhzhDhgxshDhxx extends DataEntity<DhzhDhgxshDhxx> {

	private static final long serialVersionUID = 5337244238198549728L;

	private String rDhgxshId;
	private String brhsPhone;
	private String brhsAssessResult;
	private String brhsNewAdd;
	private String brhsRemark;
	private String workNetAssessResult;
	private String workCheckRemark;
	private String isRepeat;
	private String isInPool;
	private String type;
	private String source;
	private String editRemark; // 编辑标识(默认0可编辑1不可编辑)
	private String loanId; // 数据来源汇金表id
	/**
	 * 网查结果（亲属的“宅电”和“手机号码”）
	 */
	private String clanPhoneNetResult;
	/**
	 * 网查备注（亲属的“宅电”和“手机号码”）
	 */
	private String clanPhoneNetRemark;

	/**
	 * 网查结果（亲属的“宅电”和“手机号码”）
	 */
	public String getClanPhoneNetResult() {
		return clanPhoneNetResult;
	}

	/**
	 * 网查结果（亲属的“宅电”和“手机号码”）
	 */
	public void setClanPhoneNetResult(String clanPhoneNetResult) {
		this.clanPhoneNetResult = clanPhoneNetResult;
	}

	/**
	 * 网查备注（亲属的“宅电”和“手机号码”）
	 */
	public String getClanPhoneNetRemark() {
		return clanPhoneNetRemark;
	}

	/**
	 * 网查备注（亲属的“宅电”和“手机号码”）
	 */
	public void setClanPhoneNetRemark(String clanPhoneNetRemark) {
		this.clanPhoneNetRemark = clanPhoneNetRemark;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getIsInPool() {
		return isInPool;
	}

	public void setIsInPool(String isInPool) {
		this.isInPool = isInPool;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getBrhsPhone() {
		return brhsPhone;
	}

	public void setBrhsPhone(String brhsPhone) {
		this.brhsPhone = brhsPhone;
	}

	public String getBrhsNewAdd() {
		return brhsNewAdd;
	}

	public void setBrhsNewAdd(String brhsNewAdd) {
		this.brhsNewAdd = brhsNewAdd;
	}

	public String getBrhsAssessResult() {
		return brhsAssessResult;
	}

	public void setBrhsAssessResult(String brhsAssessResult) {
		this.brhsAssessResult = brhsAssessResult == null ? null : brhsAssessResult.trim();
	}

	public String getBrhsRemark() {
		return brhsRemark;
	}

	public void setBrhsRemark(String brhsRemark) {
		this.brhsRemark = brhsRemark == null ? null : brhsRemark.trim();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEditRemark() {
		return editRemark;
	}

	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getrDhgxshId() {
		return rDhgxshId;
	}

	public void setrDhgxshId(String rDhgxshId) {
		this.rDhgxshId = rDhgxshId;
	}

}