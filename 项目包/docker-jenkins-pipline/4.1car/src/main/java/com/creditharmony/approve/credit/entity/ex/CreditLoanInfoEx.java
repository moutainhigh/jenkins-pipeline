package com.creditharmony.approve.credit.entity.ex;


import java.util.List;

import com.creditharmony.approve.credit.entity.CreditLoanInfo;


/**
 * 简版贷款信息
 * @Class Name CreditLoanInfo
 * @author 李文勇
 * @Create In 2015年12月31日
 */
public class CreditLoanInfoEx extends CreditLoanInfo {
	
	private static final long serialVersionUID = 1L;
	private String loanCode;						// 借款编码
	private String loanCustomerCode;				// 借款人编号
	private String rId;								// 关联ID
	private String dictCustomerType;				// 借款人类型(主借人/共借人)
	private String dictCheckType;					// 类型(初审，信审初审，复议初审)
	private List<String> moneyType;					// 贷款类型（SQL查询用）
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getLoanCustomerCode() {
		return loanCustomerCode;
	}
	public void setLoanCustomerCode(String loanCustomerCode) {
		this.loanCustomerCode = loanCustomerCode;
	}
	public String getrId() {
		return rId;
	}
	public void setrId(String rId) {
		this.rId = rId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public List<String> getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(List<String> moneyType) {
		this.moneyType = moneyType;
	}
}
