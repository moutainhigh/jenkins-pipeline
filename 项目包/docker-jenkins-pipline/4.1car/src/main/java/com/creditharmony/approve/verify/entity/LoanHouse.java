package com.creditharmony.approve.verify.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class LoanHouse extends DataEntity<LoanHouse>{
    private String id;
    private String rId;
    private String loanCustomterType;
    private String loanCode;
    private String houseProvince;
    private String houseCity;
    private String houseArea;
    private String houseAddress;
    
    private Date houseCreateDay;
    private Date houseBuyday;
    private String houseBuyway; 
    private String housePropertyRight; //产权人
    private String dictHouseCommon; // 房屋所有权
    private String housePropertyRelation; //与共有人关系
    private String housePledgeMark; //抵押情况
    private String housePledgeFlag; // 抵押标志
    private Date propertyGetDay;  // 产权取得时间  
    private BigDecimal houseBuilingArea;
    private String dictHouseBank;

    private BigDecimal houseLoanAmount;
    private BigDecimal houseLoanYear;
    private BigDecimal houseAmount;
    private BigDecimal houseLessAmount;
    private BigDecimal houseMonthPayAmount;
    private String houseBaseInfo;
    private String dictHouseType;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
    private String dictHouseTypeRemark;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getrId() {
		return rId;
	}

	public void setrId(String rId) {
		this.rId = rId;
	}

	public String getLoanCustomterType() {
		return loanCustomterType;
	}

	public void setLoanCustomterType(String loanCustomterType) {
		this.loanCustomterType = loanCustomterType;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getHouseProvince() {
		return houseProvince;
	}

	public void setHouseProvince(String houseProvince) {
		this.houseProvince = houseProvince;
	}

	public String getHouseCity() {
		return houseCity;
	}

	public void setHouseCity(String houseCity) {
		this.houseCity = houseCity;
	}

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public Date getHouseBuyday() {
		return houseBuyday;
	}

	public void setHouseBuyday(Date houseBuyday) {
		this.houseBuyday = houseBuyday;
	}

	public String getHouseBuyway() {
		return houseBuyway;
	}

	public void setHouseBuyway(String houseBuyway) {
		this.houseBuyway = houseBuyway;
	}

	public String getHousePropertyRight() {
		return housePropertyRight;
	}

	public void setHousePropertyRight(String housePropertyRight) {
		this.housePropertyRight = housePropertyRight;
	}

	public String getDictHouseCommon() {
		return dictHouseCommon;
	}

	public void setDictHouseCommon(String dictHouseCommon) {
		this.dictHouseCommon = dictHouseCommon;
	}

	public String getHousePropertyRelation() {
		return housePropertyRelation;
	}

	public void setHousePropertyRelation(String housePropertyRelation) {
		this.housePropertyRelation = housePropertyRelation;
	}

	public String getHousePledgeMark() {
		return housePledgeMark;
	}

	public void setHousePledgeMark(String housePledgeMark) {
		this.housePledgeMark = housePledgeMark;
	}

	public String getHousePledgeFlag() {
		return housePledgeFlag;
	}

	public void setHousePledgeFlag(String housePledgeFlag) {
		this.housePledgeFlag = housePledgeFlag;
	}

	public Date getPropertyGetDay() {
		return propertyGetDay;
	}

	public void setPropertyGetDay(Date propertyGetDay) {
		this.propertyGetDay = propertyGetDay;
	}

	public BigDecimal getHouseBuilingArea() {
		return houseBuilingArea;
	}

	public void setHouseBuilingArea(BigDecimal houseBuilingArea) {
		this.houseBuilingArea = houseBuilingArea;
	}

	public String getDictHouseBank() {
		return dictHouseBank;
	}

	public void setDictHouseBank(String dictHouseBank) {
		this.dictHouseBank = dictHouseBank;
	}

	public BigDecimal getHouseLoanYear() {
		return houseLoanYear;
	}

	public void setHouseLoanYear(BigDecimal houseLoanYear) {
		this.houseLoanYear = houseLoanYear;
	}

	public BigDecimal getHouseLoanAmount() {
		return houseLoanAmount;
	}

	public void setHouseLoanAmount(BigDecimal houseLoanAmount) {
		this.houseLoanAmount = houseLoanAmount;
	}

	public BigDecimal getHouseAmount() {
		return houseAmount;
	}

	public void setHouseAmount(BigDecimal houseAmount) {
		this.houseAmount = houseAmount;
	}

	public BigDecimal getHouseLessAmount() {
		return houseLessAmount;
	}

	public void setHouseLessAmount(BigDecimal houseLessAmount) {
		this.houseLessAmount = houseLessAmount;
	}

	public BigDecimal getHouseMonthPayAmount() {
		return houseMonthPayAmount;
	}

	public void setHouseMonthPayAmount(BigDecimal houseMonthPayAmount) {
		this.houseMonthPayAmount = houseMonthPayAmount;
	}

	public String getHouseBaseInfo() {
		return houseBaseInfo;
	}

	public void setHouseBaseInfo(String houseBaseInfo) {
		this.houseBaseInfo = houseBaseInfo;
	}

	public String getDictHouseType() {
		return dictHouseType;
	}

	public void setDictHouseType(String dictHouseType) {
		this.dictHouseType = dictHouseType;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getHouseCreateDay() {
		return houseCreateDay;
	}

	public void setHouseCreateDay(Date houseCreateDay) {
		this.houseCreateDay = houseCreateDay;
	}

	public String getDictHouseTypeRemark() {
		return dictHouseTypeRemark;
	}

	public void setDictHouseTypeRemark(String dictHouseTypeRemark) {
		this.dictHouseTypeRemark = dictHouseTypeRemark;
	}
	
}