package com.creditharmony.approve.phone.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 电话照会_本人核实_借款金额
 * 
 * @Class Name DhzhBrhsJkje
 * @author 王浩
 * @Create In 2015年12月1日
 */
public class DhzhBrhsJkje extends DataEntity<DhzhBrhsJkje> {

	private static final long serialVersionUID = 3838135483339408436L;

	private String checkId;

	private BigDecimal loanAmount;

	private BigDecimal loanMonths;

	public String getCheckId() {
		return checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public BigDecimal getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(BigDecimal loanMonths) {
		this.loanMonths = loanMonths;
	}

}