package com.creditharmony.approve.verify.entity.ex;


import com.creditharmony.core.persistence.DataEntity;

/**
 * 月认定收入实体
 * @Class Name MonthIncomeEx
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
public class MonthIncomeEx extends DataEntity<MonthIncomeEx>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private Double salaryMonth;//工资卡月认定收入 	
	private Double oftenMonth;//常储月认定收入
	private Double publicMonth;//对公月认定收入
	private Double securityMoney;//社保月缴纳基数
	private Double accumulationMonth;//公积金月缴纳基数
	private String type; // 主借人共借人
	/**
	 * @return the salaryMonth
	 */
	public Double getSalaryMonth() {
		return salaryMonth;
	}
	/**
	 * @param salaryMonth the Double salaryMonth to set
	 */
	public void setSalaryMonth(Double salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	/**
	 * @return the oftenMonth
	 */
	public Double getOftenMonth() {
		return oftenMonth;
	}
	/**
	 * @param oftenMonth the Double oftenMonth to set
	 */
	public void setOftenMonth(Double oftenMonth) {
		this.oftenMonth = oftenMonth;
	}
	/**
	 * @return the publicMonth
	 */
	public Double getPublicMonth() {
		return publicMonth;
	}
	/**
	 * @param publicMonth the Double publicMonth to set
	 */
	public void setPublicMonth(Double publicMonth) {
		this.publicMonth = publicMonth;
	}
	/**
	 * @return the securityMoney
	 */
	public Double getSecurityMoney() {
		return securityMoney;
	}
	/**
	 * @param securityMoney the Double securityMoney to set
	 */
	public void setSecurityMoney(Double securityMoney) {
		this.securityMoney = securityMoney;
	}
	/**
	 * @return the accumulationMonth
	 */
	public Double getAccumulationMonth() {
		return accumulationMonth;
	}
	/**
	 * @param accumulationMonth the Double accumulationMonth to set
	 */
	public void setAccumulationMonth(Double accumulationMonth) {
		this.accumulationMonth = accumulationMonth;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the String type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
}
