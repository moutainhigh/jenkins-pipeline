package com.creditharmony.approve.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 系数参照表（期数、费率、前期综合费）
 * @Class Name RoeffRefe
 * @author 刘燕军
 * @Create In 2016年4月22日
 */
public class RoeffRefe extends DataEntity<RoeffRefe>{

	private static final long serialVersionUID = 1L;
	
	private Integer months; // 期数
	private String systemFlag; // 系统标志
	private Double productUsableRate; // 产品总费率
	private Double comprehensiveFeeCoeff; // 前期综合费用系数
	private Double monthGatherRation; // 分期服务费每月收取比例
	private String consultFlag; // 咨询时间在风险定价上线时间之后的标识，true或false
	private String riskLevel; // 风险等级
	
	public Integer getMonths() {
		return months;
	}
	public void setMonths(Integer months) {
		this.months = months;
	}
	public String getSystemFlag() {
		return systemFlag;
	}
	public void setSystemFlag(String systemFlag) {
		this.systemFlag = systemFlag;
	}
	public Double getProductUsableRate() {
		return productUsableRate;
	}
	public void setProductUsableRate(Double productUsableRate) {
		this.productUsableRate = productUsableRate;
	}
	public Double getComprehensiveFeeCoeff() {
		return comprehensiveFeeCoeff;
	}
	public void setComprehensiveFeeCoeff(Double comprehensiveFeeCoeff) {
		this.comprehensiveFeeCoeff = comprehensiveFeeCoeff;
	}
	public Double getMonthGatherRation() {
		return monthGatherRation;
	}
	public void setMonthGatherRation(Double monthGatherRation) {
		this.monthGatherRation = monthGatherRation;
	}
	public String getConsultFlag() {
		return consultFlag;
	}
	public void setConsultFlag(String consultFlag) {
		this.consultFlag = consultFlag;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}	
}
