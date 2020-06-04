package com.creditharmony.approve.credit.entity.ex;

import java.util.List;

import com.creditharmony.approve.credit.entity.CreditPaybackInfo;
import com.creditharmony.approve.credit.entity.CreditQueryRecord;

/**
 * 
 * @Class Name CreditQueryRecordEx
 * @author 李文勇
 * @Create In 2016年2月15日
 */
public class CreditQueryRecordEx {
	private String loanCode; // 借款编号
	private String type ; //借款人类型 主借人/共借人
	private String checkType; // 审批步骤 初审 复审等
	private String relId; // 关联id（客户id）
	private List<CreditQueryRecord> creditQueryList;
	private List<CreditPaybackInfo> creditPaybackInfoList;

	public List<CreditPaybackInfo> getCreditPaybackInfoList() {
		return creditPaybackInfoList;
	}

	public void setCreditPaybackInfoList(
			List<CreditPaybackInfo> creditPaybackInfoList) {
		this.creditPaybackInfoList = creditPaybackInfoList;
	}

	public List<CreditQueryRecord> getCreditQueryList() {
		return creditQueryList;
	}

	public void setCreditQueryList(List<CreditQueryRecord> creditQueryList) {
		this.creditQueryList = creditQueryList;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getRelId() {
		return relId;
	}

	public void setRelId(String relId) {
		this.relId = relId;
	}
}
