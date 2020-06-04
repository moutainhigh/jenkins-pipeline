package com.creditharmony.approve.credit.entity.ex;

import java.util.Date;

import com.creditharmony.approve.credit.entity.CreditReportDetailed;


public class CreditReportDetailedEx extends CreditReportDetailed {

	private static final long serialVersionUID = 1L;
	// 居住信息
	private String mail;				// 邮箱
	private String liveSituation;		// 居住状况
	private String liveProvince;		// 居住省
	private String liveCity;			// 居住市
	private String liveArea;			// 居住区
	private String liveAddress;			// 详细地址
	// 单位信息
	private String relationId;			// 关联ID
	private String unitName;			// 工作单位名称
	private String zipCode;				// 邮政编码
	private String unitIndustry;		// 单位所属行业
	private String occupation;			// 职业信息
	private String duties;				// 职务
	private String title;				// 职称
	private int annualIncome;			// 年收入
	private int startingYear;			// 本单位工作起始年份
	private Date getinfoTime;			// 信息获取时间
	private String unitProvince;		// 单位省
	private String unitCity;			// 单位市
	private String unitArea;			// 单位区
	private String unitAddress;			// 单位地址
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLiveSituation() {
		return liveSituation;
	}
	public void setLiveSituation(String liveSituation) {
		this.liveSituation = liveSituation;
	}
	public String getLiveProvince() {
		return liveProvince;
	}
	public void setLiveProvince(String liveProvince) {
		this.liveProvince = liveProvince;
	}
	public String getLiveCity() {
		return liveCity;
	}
	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}
	public String getLiveArea() {
		return liveArea;
	}
	public void setLiveArea(String liveArea) {
		this.liveArea = liveArea;
	}
	public String getLiveAddress() {
		return liveAddress;
	}
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
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
	public int getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(int annualIncome) {
		this.annualIncome = annualIncome;
	}
	public int getStartingYear() {
		return startingYear;
	}
	public void setStartingYear(int startingYear) {
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
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
}
