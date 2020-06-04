package com.creditharmony.approve.carloan.entity.ex;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 车借--展期额外所需字段，如总借款期数，总逾期次数，总逾期天数，最长逾期天数
 * @Class Name CarLoanExtendExtraEx
 * @author 申诗阔
 * @Create In 2016年3月7日
 */
public class CarLoanExtendExtraEx extends DataEntity<CarLoanExtendExtraEx> {

	private static final long serialVersionUID = -1027955222638860393L;
	
	private String totalLoanMonths;			// 总借款期限
	private String totalExt;				// 总展期次数
	private String maxMonths;	// 最大借款期限
	private BigDecimal extensionAssessAmount; // 展期评估金额
	private Double grossRate; // 总费率
	
	public String getTotalLoanMonths() {
		return totalLoanMonths;
	}
	public void setTotalLoanMonths(String totalLoanMonths) {
		this.totalLoanMonths = totalLoanMonths;
	}
	public String getTotalExt() {
		return totalExt;
	}
	public void setTotalExt(String totalExt) {
		this.totalExt = totalExt;
	}
	public String getMaxMonths() {
		return maxMonths;
	}
	public void setMaxMonths(String maxMonths) {
		this.maxMonths = maxMonths;
	}
	public BigDecimal getExtensionAssessAmount() {
		return extensionAssessAmount;
	}
	public void setExtensionAssessAmount(BigDecimal extensionAssessAmount) {
		this.extensionAssessAmount = extensionAssessAmount;
	}
	public Double getGrossRate() {
		return grossRate;
	}
	public void setGrossRate(Double grossRate) {
		this.grossRate = grossRate;
	}
	
}
