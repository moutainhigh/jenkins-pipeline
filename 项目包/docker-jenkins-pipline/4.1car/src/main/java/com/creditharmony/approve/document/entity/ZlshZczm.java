package com.creditharmony.approve.document.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.entity.ConfirmTypeJsaon;
import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核 资产证明
 * @Class Name ZlshZczm
 * @author 黄维
 * @Create In 2015年12月12日
 */
public class ZlshZczm extends DataEntity<ZlshZczm>{
    
	private static final long serialVersionUID = 1L;

	private String loanCode;					// 借款编码
	private String rCustomerCoborrowerId;		// 关联ID(主借人，共借人)
	private String dictCustomerType;			// 借款人类型(主借人/共借人)
	private String zczmHouseType;				// 房产种类
	private String dictProvince;				// 房产地址省
	private String dictCity;					// 房产地址市
	private String dictArer;					// 房产地址区
	private String zczmHouseAddress;			// 房产地址
	private String zczmPropertyMan;				// 房产共有
	private String zczmPropertyRelation;		// 与共有人关系
	private Double zczmHouseArea;				// 房屋面积
	private Double zczmHouseValue;				// 房产估值
	private String zczmPledgeFlag;				// 抵押标志
	private String dictCheckType;				// 类型(初审，信审初审，复议初审)
	private List<ConfirmTypeJsaon> confirmType;	// 确认方式(jason类型)
	private String confirmStr;					// 确认方式(String拼接逗号类型)
	private Map<String,String> confirmMap;		// 确认方式map(页面展示用)
	private List<CityInfo> provinceList;
	private List<CityInfo> cityList;
	private List<CityInfo> districtList;
	private String dictSourceType;
	private String netCheckResult; // 网查结果
	private String netCheckRemark; // 网查备注
	//一下为新申请表新增字段
	private String remark;//备注(房产类型选其他时备注)	
	private String ownerHouseRatio;   //本人房屋所有权占比
	private String mateHouseRatio;   //配偶房屋所有权占比
	private String houseBuyway;      //购买方式
	private Date   houseBuyDay;//购买时间
	private Date   houseCreateDay;//建筑年份
	private Double houseAmount;  //购买价格
	private Double houseLoanAmount; //贷款总额
	private Double houseLessAmount;//贷款余额 
	private Double houseMonthRepayAmount;//月还款
	private Double houseLoanYear;      //贷款年限
	public List<ConfirmTypeJsaon> getConfirmType() {
		return confirmType;
	}
	public void setConfirmType(List<ConfirmTypeJsaon> confirmType) {
		this.confirmType = confirmType;
	}
	public List<CityInfo> getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List<CityInfo> provinceList) {
		this.provinceList = provinceList;
	}
	public List<CityInfo> getCityList() {
		return cityList;
	}
	public void setCityList(List<CityInfo> cityList) {
		this.cityList = cityList;
	}
	public List<CityInfo> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<CityInfo> districtList) {
		this.districtList = districtList;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public String getZczmHouseType() {
		return zczmHouseType;
	}
	public void setZczmHouseType(String zczmHouseType) {
		this.zczmHouseType = zczmHouseType;
	}
	public String getDictProvince() {
		return dictProvince;
	}
	public void setDictProvince(String dictProvince) {
		this.dictProvince = dictProvince;
	}
	public String getDictCity() {
		return dictCity;
	}
	public void setDictCity(String dictCity) {
		this.dictCity = dictCity;
	}
	public String getDictArer() {
		return dictArer;
	}
	public void setDictArer(String dictArer) {
		this.dictArer = dictArer;
	}
	public String getZczmHouseAddress() {
		return zczmHouseAddress;
	}
	public void setZczmHouseAddress(String zczmHouseAddress) {
		this.zczmHouseAddress = zczmHouseAddress;
	}
	public String getZczmPropertyMan() {
		return zczmPropertyMan;
	}
	public void setZczmPropertyMan(String zczmPropertyMan) {
		this.zczmPropertyMan = zczmPropertyMan;
	}
	public String getZczmPropertyRelation() {
		return zczmPropertyRelation;
	}
	public void setZczmPropertyRelation(String zczmPropertyRelation) {
		this.zczmPropertyRelation = zczmPropertyRelation;
	}
	public Double getZczmHouseArea() {
		return zczmHouseArea;
	}
	public void setZczmHouseArea(Double zczmHouseArea) {
		this.zczmHouseArea = zczmHouseArea;
	}
	public Double getZczmHouseValue() {
		return zczmHouseValue;
	}
	public void setZczmHouseValue(Double zczmHouseValue) {
		this.zczmHouseValue = zczmHouseValue;
	}
	public String getZczmPledgeFlag() {
		return zczmPledgeFlag;
	}
	public void setZczmPledgeFlag(String zczmPledgeFlag) {
		this.zczmPledgeFlag = zczmPledgeFlag;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getConfirmStr() {
		return confirmStr;
	}
	public void setConfirmStr(String confirmStr) {
		this.confirmStr = confirmStr;
	}
	public Map<String, String> getConfirmMap() {
		return confirmMap;
	}
	public void setConfirmMap(Map<String, String> confirmMap) {
		this.confirmMap = confirmMap;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}
	public String getNetCheckResult() {
		return netCheckResult;
	}
	public void setNetCheckResult(String netCheckResult) {
		this.netCheckResult = netCheckResult;
	}
	public String getNetCheckRemark() {
		return netCheckRemark;
	}
	public void setNetCheckRemark(String netCheckRemark) {
		this.netCheckRemark = netCheckRemark;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOwnerHouseRatio() {
		return ownerHouseRatio;
	}
	public void setOwnerHouseRatio(String ownerHouseRatio) {
		this.ownerHouseRatio = ownerHouseRatio;
	}
	public String getMateHouseRatio() {
		return mateHouseRatio;
	}
	public void setMateHouseRatio(String mateHouseRatio) {
		this.mateHouseRatio = mateHouseRatio;
	}
	public String getHouseBuyway() {
		return houseBuyway;
	}
	public void setHouseBuyway(String houseBuyway) {
		this.houseBuyway = houseBuyway;
	}
	public Date getHouseCreateDay() {
		return houseCreateDay;
	}
	public void setHouseCreateDay(Date houseCreateDay) {
		this.houseCreateDay = houseCreateDay;
	}
	public Double getHouseAmount() {
		return houseAmount;
	}
	public void setHouseAmount(Double houseAmount) {
		this.houseAmount = houseAmount;
	}
	public Double getHouseLoanAmount() {
		return houseLoanAmount;
	}
	public void setHouseLoanAmount(Double houseLoanAmount) {
		this.houseLoanAmount = houseLoanAmount;
	}
	public Double getHouseLessAmount() {
		return houseLessAmount;
	}
	public void setHouseLessAmount(Double houseLessAmount) {
		this.houseLessAmount = houseLessAmount;
	}
	public Double getHouseMonthRepayAmount() {
		return houseMonthRepayAmount;
	}
	public void setHouseMonthRepayAmount(Double houseMonthRepayAmount) {
		this.houseMonthRepayAmount = houseMonthRepayAmount;
	}
    
	public Double getHouseLoanYear() {
		return houseLoanYear;
	}
	public void setHouseLoanYear(Double houseLoanYear) {
		this.houseLoanYear = houseLoanYear;
	}
	public Date getHouseBuyDay() {
		return houseBuyDay;
	}
	public void setHouseBuyDay(Date houseBuyDay) {
		this.houseBuyDay = houseBuyDay;
	}
	
}