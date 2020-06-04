package com.creditharmony.approve.verify.entity.ex;

import java.util.Date;

import com.creditharmony.approve.verify.entity.LoanCustomer;

/**
 * 申请人资格bean
 * @Class Name ApplicantInfo
 * @author 刘燕军
 * @Create In 2015年11月30日
 */
public class ApplicantInfoEx extends LoanCustomer{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	private String age;// 年龄
	private String operatTime;// 经营时间
	private String compSalary;// 收入
	private String compWorkExperience;// 工作年限
	private Date jyzmSetUpTime;// 成立时间
	private Double jyzmRegisteredCapital;// 注册资本
	private String gdxxRatio;// 占股比例
	public String getCompSalary() {
		return compSalary;
	}
	public void setCompSalary(String compSalary) {
		this.compSalary = compSalary;
	}
	public String getCompWorkExperience() {
		return compWorkExperience;
	}
	public void setCompWorkExperience(String compWorkExperience) {
		this.compWorkExperience = compWorkExperience;
	}
	
	public Double getJyzmRegisteredCapital() {
		return jyzmRegisteredCapital;
	}
	public void setJyzmRegisteredCapital(Double jyzmRegisteredCapital) {
		this.jyzmRegisteredCapital = jyzmRegisteredCapital;
	}
	public Date getJyzmSetUpTime() {
		return jyzmSetUpTime;
	}
	public void setJyzmSetUpTime(Date jyzmSetUpTime) {
		this.jyzmSetUpTime = jyzmSetUpTime;
	}
	public String getGdxxRatio() {
		return gdxxRatio;
	}
	public void setGdxxRatio(String gdxxRatio) {
		this.gdxxRatio = gdxxRatio;
	}
	public String getOperatTime() {
		return operatTime;
	}
	public void setOperatTime(String operatTime) {
		this.operatTime = operatTime;
	}
	
}
