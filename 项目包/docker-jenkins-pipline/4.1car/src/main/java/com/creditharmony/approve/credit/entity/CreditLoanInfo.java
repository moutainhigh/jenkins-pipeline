package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 简版贷款信息
 * @Class Name CreditLoanInfo
 * @author 李文勇
 * @Create In 2015年12月31日
 */
public class CreditLoanInfo extends DataEntity<CreditLoanInfo> {
	
	private static final long serialVersionUID = 1L;
	private String relationId;					// 关联ID
	private String accountStatus;				// 账户状态*
	private String currency;					// 贷款种类*
	private String isOverdue;					// 是否发生过逾期*
	private Date issueDay;						// 发放日期*
	private Date abortDay;						// 到期日期*
	private Date actualDay;						// 截止年月*
	private BigDecimal conteactAmount;			// 贷款合同金额*
	private BigDecimal loanBalance;				// 贷款余额*
	private BigDecimal overdueAmount;			// 逾期金额*
	private int overdueNo;						// 最近5年内逾期次数*
	private int overdueForNo;					// 最近5年内90天以上的逾期次数*
	private Date settleDay;						// 结清年月
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(String isOverdue) {
		this.isOverdue = isOverdue;
	}
	public Date getIssueDay() {
		return issueDay;
	}
	public void setIssueDay(Date issueDay) {
		this.issueDay = issueDay;
	}
	public Date getAbortDay() {
		return abortDay;
	}
	public void setAbortDay(Date abortDay) {
		this.abortDay = abortDay;
	}
	public Date getActualDay() {
		return actualDay;
	}
	public void setActualDay(Date actualDay) {
		this.actualDay = actualDay;
	}
	public BigDecimal getConteactAmount() {
		return conteactAmount;
	}
	public void setConteactAmount(BigDecimal conteactAmount) {
		this.conteactAmount = conteactAmount;
	}
	public BigDecimal getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(BigDecimal loanBalance) {
		this.loanBalance = loanBalance;
	}
	public BigDecimal getOverdueAmount() {
		return overdueAmount;
	}
	public void setOverdueAmount(BigDecimal overdueAmount) {
		this.overdueAmount = overdueAmount;
	}
	public int getOverdueNo() {
		return overdueNo;
	}
	public void setOverdueNo(int overdueNo) {
		this.overdueNo = overdueNo;
	}
	public int getOverdueForNo() {
		return overdueForNo;
	}
	public void setOverdueForNo(int overdueForNo) {
		this.overdueForNo = overdueForNo;
	}
	public Date getSettleDay() {
		return settleDay;
	}
	public void setSettleDay(Date settleDay) {
		this.settleDay = settleDay;
	}
}
