package com.creditharmony.approve.credit.entity.ex;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CreditCardDetailedEx {

	// 详版信用卡明细信息（一）
	private String cardType;				// 卡类型*
	private String guaranteeType;			// 担保方式*
	private String currency;				// 币种*
	private Date accountDay;				// 开户日期*
	private BigDecimal cerditLine;			// 信用额度*
	private BigDecimal shareCreditLine;		// 共享授信额度*
	private BigDecimal liabilitiesLine;		// 最大负债额*
	private BigDecimal usedAmount;			// 透支余额/已使用额度*
	// 详版信用卡明细信息（二）
	private String accountStatus;			// 账户状态*
	private BigDecimal shouldRepayAmount;	// 本月应还款金额*
	private BigDecimal realRepayaAmount;	// 本月实际还款金额*
	private String realRepayDay;			// 最近一次实际还款日期*
	private int currentOverdue;				// 当前逾期期数*
	private BigDecimal currentOverdueTotal;	// 当前逾期总额*
	private BigDecimal overdraftBalance;	// 准贷记卡透支180天以上未付余额*
	private int repaymentNo;				// 贷记卡12个月内未最低还款额次数*
	private Date getinfo_time;				// 信息获取时间
	private Date clearing_day;				// 结算年月*
	
	// 参数部分
	private String loanCode;						// 借款编码
	private String loanCustomerCode;				// 借款人编号
	private String rCustomerCoborrowerId;								// 关联ID
	private String dictCustomerType;				// 借款人类型(主借人/共借人)
	private String dictCheckType;					// 类型(初审，信审初审，复议初审)
	private List<String> moneyType;					// 贷款类型（SQL查询用）
	
	
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Date getAccountDay() {
		return accountDay;
	}
	public void setAccountDay(Date accountDay) {
		this.accountDay = accountDay;
	}
	public BigDecimal getCerditLine() {
		return cerditLine;
	}
	public void setCerditLine(BigDecimal cerditLine) {
		this.cerditLine = cerditLine;
	}
	public BigDecimal getShareCreditLine() {
		return shareCreditLine;
	}
	public void setShareCreditLine(BigDecimal shareCreditLine) {
		this.shareCreditLine = shareCreditLine;
	}
	public BigDecimal getLiabilitiesLine() {
		return liabilitiesLine;
	}
	public void setLiabilitiesLine(BigDecimal liabilitiesLine) {
		this.liabilitiesLine = liabilitiesLine;
	}
	public BigDecimal getUsedAmount() {
		return usedAmount;
	}
	public void setUsedAmount(BigDecimal usedAmount) {
		this.usedAmount = usedAmount;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public BigDecimal getShouldRepayAmount() {
		return shouldRepayAmount;
	}
	public void setShouldRepayAmount(BigDecimal shouldRepayAmount) {
		this.shouldRepayAmount = shouldRepayAmount;
	}
	public BigDecimal getRealRepayaAmount() {
		return realRepayaAmount;
	}
	public void setRealRepayaAmount(BigDecimal realRepayaAmount) {
		this.realRepayaAmount = realRepayaAmount;
	}
	public String getRealRepayDay() {
		return realRepayDay;
	}
	public void setRealRepayDay(String realRepayDay) {
		this.realRepayDay = realRepayDay;
	}
	public int getCurrentOverdue() {
		return currentOverdue;
	}
	public void setCurrentOverdue(int currentOverdue) {
		this.currentOverdue = currentOverdue;
	}
	public BigDecimal getCurrentOverdueTotal() {
		return currentOverdueTotal;
	}
	public void setCurrentOverdueTotal(BigDecimal currentOverdueTotal) {
		this.currentOverdueTotal = currentOverdueTotal;
	}
	public BigDecimal getOverdraftBalance() {
		return overdraftBalance;
	}
	public void setOverdraftBalance(BigDecimal overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}
	public int getRepaymentNo() {
		return repaymentNo;
	}
	public void setRepaymentNo(int repaymentNo) {
		this.repaymentNo = repaymentNo;
	}
	public Date getGetinfo_time() {
		return getinfo_time;
	}
	public void setGetinfo_time(Date getinfo_time) {
		this.getinfo_time = getinfo_time;
	}
	public Date getClearing_day() {
		return clearing_day;
	}
	public void setClearing_day(Date clearing_day) {
		this.clearing_day = clearing_day;
	}
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
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public List<String> getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(List<String> moneyType) {
		this.moneyType = moneyType;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}
}
