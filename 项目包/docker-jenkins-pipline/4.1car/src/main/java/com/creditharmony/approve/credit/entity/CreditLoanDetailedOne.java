package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CreditLoanDetailedOne extends DataEntity<CreditLoanDetailedOne>  {

	private static final long serialVersionUID = 1L;
	private String RelationId;					// 关联ID
	private String loanType;					// 贷款类型*
	private String guaranteeType;				// 担保方式*
	private String currency;					// 币种*
	private String accountStatu;				// 账户状态*
	private String repayFrequency;				// 还款频率*
	private String levelClass;					// 五级分类*
	private int repayMonths;					// 还款月数*
	private Date releaseDay;					// 发放日期*
	private Date actualDay;						// 到期日期*
	private BigDecimal conteactAmount;			// 合同金额*
	private BigDecimal loanBalance;				// 贷款余额*
	private Date getingTime;					// 信息获取时间*
	public String getRelationId() {
		return RelationId;
	}
	public void setRelationId(String relationId) {
		RelationId = relationId;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
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

	public String getRepayFrequency() {
		return repayFrequency;
	}
	public void setRepayFrequency(String repayFrequency) {
		this.repayFrequency = repayFrequency;
	}
	public String getLevelClass() {
		return levelClass;
	}
	public void setLevelClass(String levelClass) {
		this.levelClass = levelClass;
	}
	public int getRepayMonths() {
		return repayMonths;
	}
	public void setRepayMonths(int repayMonths) {
		this.repayMonths = repayMonths;
	}
	public Date getReleaseDay() {
		return releaseDay;
	}
	public void setReleaseDay(Date releaseDay) {
		this.releaseDay = releaseDay;
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
	public Date getGetingTime() {
		return getingTime;
	}
	public void setGetingTime(Date getingTime) {
		this.getingTime = getingTime;
	}
	public String getAccountStatu() {
		return accountStatu;
	}
	public void setAccountStatu(String accountStatu) {
		this.accountStatu = accountStatu;
	}
}
