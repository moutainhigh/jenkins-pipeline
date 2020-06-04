package com.creditharmony.approve.credit.entity.ex;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.approve.credit.entity.CreditLoanDetailedOne;


public class CreditLoanDetailedEx extends CreditLoanDetailedOne {

	
	private static final long serialVersionUID = 1L;
	// 详版贷款明细（二）
	//private int repayMonths;						// 剩余还款月数*
	private Date realrepayDay;						// 最近一次实际还款日期*
	private BigDecimal shouldRepayAmount;			// 本月应还款金额*
	private BigDecimal realRepayAmount;				// 本月实际还款金额*
	private int currentOverdue;						// 当前逾期期数*
	private BigDecimal currentOverdueTotal;			// 当前逾期总额*
	private int overdueNoTotal;						// 累计逾期次数*
	private int overdueNoHighest;					// 最高逾期期数*
	private BigDecimal overduePrincipalLevel1;		// 逾期31-60天未归还贷款本金*
	private BigDecimal overduePrincipalLevel2;		// 逾期61-90天未归还贷款本金*
	private BigDecimal overduePrincipalLevel3;		// 逾期91-180天未归还贷款本金*
	private BigDecimal overduePrincipalLevel4;		// 逾期180天以上为归还贷款本金*
	private Date clearingDay;						// 结算年月*
	// 参数部分
	private String loanCode;						// 借款编码
	private String loanCustomerCode;				// 借款人编号
	private String rCustomerCoborrowerId;								// 关联ID
	private String dictCustomerType;				// 借款人类型(主借人/共借人)
	private String dictCheckType;					// 类型(初审，信审初审，复议初审)
	private List<String> moneyType;					// 贷款类型（SQL查询用）
	private List<String> dkfsType;					// 贷款方式（SQL查询用）
	
	
	public Date getRealrepayDay() {
		return realrepayDay;
	}
	public void setRealrepayDay(Date realrepayDay) {
		this.realrepayDay = realrepayDay;
	}
	public BigDecimal getShouldRepayAmount() {
		return shouldRepayAmount;
	}
	public void setShouldRepayAmount(BigDecimal shouldRepayAmount) {
		this.shouldRepayAmount = shouldRepayAmount;
	}
	public BigDecimal getRealRepayAmount() {
		return realRepayAmount;
	}
	public void setRealRepayAmount(BigDecimal realRepayAmount) {
		this.realRepayAmount = realRepayAmount;
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
	public int getOverdueNoTotal() {
		return overdueNoTotal;
	}
	public void setOverdueNoTotal(int overdueNoTotal) {
		this.overdueNoTotal = overdueNoTotal;
	}
	public int getOverdueNoHighest() {
		return overdueNoHighest;
	}
	public void setOverdueNoHighest(int overdueNoHighest) {
		this.overdueNoHighest = overdueNoHighest;
	}
	public BigDecimal getOverduePrincipalLevel1() {
		return overduePrincipalLevel1;
	}
	public void setOverduePrincipalLevel1(BigDecimal overduePrincipalLevel1) {
		this.overduePrincipalLevel1 = overduePrincipalLevel1;
	}
	public BigDecimal getOverduePrincipalLevel2() {
		return overduePrincipalLevel2;
	}
	public void setOverduePrincipalLevel2(BigDecimal overduePrincipalLevel2) {
		this.overduePrincipalLevel2 = overduePrincipalLevel2;
	}
	public BigDecimal getOverduePrincipalLevel3() {
		return overduePrincipalLevel3;
	}
	public void setOverduePrincipalLevel3(BigDecimal overduePrincipalLevel3) {
		this.overduePrincipalLevel3 = overduePrincipalLevel3;
	}
	public BigDecimal getOverduePrincipalLevel4() {
		return overduePrincipalLevel4;
	}
	public void setOverduePrincipalLevel4(BigDecimal overduePrincipalLevel4) {
		this.overduePrincipalLevel4 = overduePrincipalLevel4;
	}
	public Date getClearingDay() {
		return clearingDay;
	}
	public void setClearingDay(Date clearingDay) {
		this.clearingDay = clearingDay;
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
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}
	public List<String> getDkfsType() {
		return dkfsType;
	}
	public void setDkfsType(List<String> dkfsType) {
		this.dkfsType = dkfsType;
	}
}
