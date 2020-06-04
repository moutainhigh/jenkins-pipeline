package com.creditharmony.approve.verify.entity.ex;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.AuditResultSublist;

/**
 * 决策扩展类
 * @Class Name AuditResultEx
 * @author 赖敏
 * @Create In 2015年12月24日
 */
public class AuditResultEx extends AuditResult {
	
	private static final long serialVersionUID = -5176300914521966442L;
	private List<AuditResultSublist> subResult;			// 决策保存的拒借
	private String borrowProduct;						// 审批产品名称
	private BigDecimal limitUpper;
	private BigDecimal limitLower;
	private BigDecimal cautionerLimitAmount;
	
	public List<AuditResultSublist> getSubResult() {
		return subResult;
	}
	
	public void setSubResult(List<AuditResultSublist> subResult) {
		this.subResult = subResult;
	}
	
	public String getBorrowProduct() {
		return borrowProduct;
	}
	
	public void setBorrowProduct(String borrowProduct) {
		this.borrowProduct = borrowProduct;
	}

	public BigDecimal getLimitUpper() {
		return limitUpper;
	}

	public void setLimitUpper(BigDecimal limitUpper) {
		this.limitUpper = limitUpper;
	}

	public BigDecimal getLimitLower() {
		return limitLower;
	}

	public void setLimitLower(BigDecimal limitLower) {
		this.limitLower = limitLower;
	}

	public BigDecimal getCautionerLimitAmount() {
		return cautionerLimitAmount;
	}

	public void setCautionerLimitAmount(BigDecimal cautionerLimitAmount) {
		this.cautionerLimitAmount = cautionerLimitAmount;
	}
	
}
