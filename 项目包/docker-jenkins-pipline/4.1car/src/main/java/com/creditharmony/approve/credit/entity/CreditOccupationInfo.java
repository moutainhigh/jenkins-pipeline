package com.creditharmony.approve.credit.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 详版信用报告职业信息
 * @Class Name CreditOccupationInfo
 * @author 李文勇
 * @Create In 2016年1月6日
 */
public class CreditOccupationInfo extends DataEntity<CreditOccupationInfo> {

	
	private static final long serialVersionUID = 1L;

	private String relationId;			// 关联ID
	private String unitName;			// 工作单位名称
	private String zipCode;				// 邮政编码
	private String unitIndustry;		// 单位所属行业
	private String occupation;			// 职业信息
	private String duties;				// 职务
	private String title;				// 职称
	private BigDecimal annualIncome;			// 年收入
	private Integer startingYear;			// 本单位工作起始年份
	private Date getinfoTime;			// 信息获取时间
	private String unitProvince;		// 单位省
	private String unitCity;			// 单位市
	private String unitArea;			// 单位区
	private String unitAddress;			// 单位地址
	
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getUnitIndustry() {
		return unitIndustry;
	}
	public void setUnitIndustry(String unitIndustry) {
		this.unitIndustry = unitIndustry;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDuties() {
		return duties;
	}
	public void setDuties(String duties) {
		this.duties = duties;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public BigDecimal getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
	}
	public Integer getStartingYear() {
		return startingYear;
	}
	public void setStartingYear(Integer startingYear) {
		this.startingYear = startingYear;
	}
	public Date getGetinfoTime() {
		return getinfoTime;
	}
	public void setGetinfoTime(Date getinfoTime) {
		this.getinfoTime = getinfoTime;
	}
	public String getUnitProvince() {
		return unitProvince;
	}
	public void setUnitProvince(String unitProvince) {
		this.unitProvince = unitProvince;
	}
	public String getUnitCity() {
		return unitCity;
	}
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}
	public String getUnitArea() {
		return unitArea;
	}
	public void setUnitArea(String unitArea) {
		this.unitArea = unitArea;
	}
}
