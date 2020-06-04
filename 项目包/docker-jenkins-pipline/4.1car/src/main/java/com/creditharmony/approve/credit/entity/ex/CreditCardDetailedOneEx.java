package com.creditharmony.approve.credit.entity.ex;

import java.util.List;

import com.creditharmony.approve.credit.entity.CreditCardDetailedOne;
import com.creditharmony.approve.credit.entity.CreditCardDetailedTwo;



public class CreditCardDetailedOneEx {
	
	
	private String loanCode; // 借款编号
	private String type ; //借款人类型 主借人/共借人
	private String checkType; // 审批步骤 初审 复审等
	private String relId; // 关联id（客户id）
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
	private List<CreditCardDetailedOne> creditCardDetailedOneList;
	public List<CreditCardDetailedOne> getCreditCardDetailedOneList() {
		return creditCardDetailedOneList;
	}
	public void setCreditCardDetailedOneList(
			List<CreditCardDetailedOne> creditCardDetailedOneList) {
		this.creditCardDetailedOneList = creditCardDetailedOneList;
	}
	public List<CreditCardDetailedTwo> getCreditCardDetailedTwoList() {
		return creditCardDetailedTwoList;
	}
	public void setCreditCardDetailedTwoList(
			List<CreditCardDetailedTwo> creditCardDetailedTwoList) {
		this.creditCardDetailedTwoList = creditCardDetailedTwoList;
	}
	public List<CreditCycleRecordEx> getCreditCycleRecordExList() {
		return creditCycleRecordExList;
	}
	public void setCreditCycleRecordExList(
			List<CreditCycleRecordEx> creditCycleRecordExList) {
		this.creditCycleRecordExList = creditCycleRecordExList;
	}
	private List<CreditCardDetailedTwo> creditCardDetailedTwoList;
	private List<CreditCycleRecordEx> creditCycleRecordExList;
}
