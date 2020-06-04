package com.creditharmony.approve.document.entity;

import java.util.Date;
import java.util.List;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核 个人证件
 * @Class Name ZlshGrzj
 * @author 赵春香
 * @Create In 2016年9月9日
 */
public class ZlshGrzj extends DataEntity<ZlshGrzj>{
    
	private static final long serialVersionUID = 1L;

	private String loanCode;					   // 借款编码
	private String rCustomerCoborrowerId;		   // 关联ID(主借人，共借人)
	private String dictCustomerType;			   // 借款人类型(主借人/共借人)
	private String customerRelMaster;              //申请人与户主关系
	private String masterName;                     //户主姓名
	private String masterCertNum;                  //户主身份证号码
	private String registerProperty;               //户籍性质
	private String masterProvince;				   // 户主页住址省
	private String masterCity;					   // 户主页住址市
	private String masterArea;					   // 户主页住址区
	private String masterAddress;		           // 户主页地址
	private String childrenCertNum;				   // 子女身份证号码
	private String childrenName;				   // 子女姓名
	private String dictMarryStatus;                //婚姻状况
	private Date   marriageDate;		           // 结婚日期
	private String certificationUnit;	           // 发证机构
	private String dictCheckType;                  // 类型(初审，信审初审，复议初审)
	private String dictSourceType;
	private List<CityInfo> provinceList;
	private List<CityInfo> cityList;
	private List<CityInfo> districtList;

	private ZlshGrzjxl  grxl;//个人证件学历信息
	private List<ZlshGrzjxl> grxlList;//个人证件学历信息List
	
	private String customerRelMasterRemark;          //申请人与户主关系
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
	public String getCustomerRelMaster() {
		return customerRelMaster;
	}
	public void setCustomerRelMaster(String customerRelMaster) {
		this.customerRelMaster = customerRelMaster;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getMasterCertNum() {
		return masterCertNum;
	}
	public void setMasterCertNum(String masterCertNum) {
		this.masterCertNum = masterCertNum;
	}
	public String getRegisterProperty() {
		return registerProperty;
	}
	public void setRegisterProperty(String registerProperty) {
		this.registerProperty = registerProperty;
	}
	public String getMasterProvince() {
		return masterProvince;
	}
	public void setMasterProvince(String masterProvince) {
		this.masterProvince = masterProvince;
	}
	public String getMasterCity() {
		return masterCity;
	}
	public void setMasterCity(String masterCity) {
		this.masterCity = masterCity;
	}
	
	public String getMasterArea() {
		return masterArea;
	}
	public void setMasterArea(String masterArea) {
		this.masterArea = masterArea;
	}
	public String getMasterAddress() {
		return masterAddress;
	}
	public void setMasterAddress(String masterAddress) {
		this.masterAddress = masterAddress;
	}
	public String getChildrenCertNum() {
		return childrenCertNum;
	}
	public void setChildrenCertNum(String childrenCertNum) {
		this.childrenCertNum = childrenCertNum;
	}
	public String getChildrenName() {
		return childrenName;
	}
	public void setChildrenName(String childrenName) {
		this.childrenName = childrenName;
	}
	public String getDictMarryStatus() {
		return dictMarryStatus;
	}
	public void setDictMarryStatus(String dictMarryStatus) {
		this.dictMarryStatus = dictMarryStatus;
	}
	public Date getMarriageDate() {
		return marriageDate;
	}
	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}
	public String getCertificationUnit() {
		return certificationUnit;
	}
	public void setCertificationUnit(String certificationUnit) {
		this.certificationUnit = certificationUnit;
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
	
	public ZlshGrzjxl getGrxl() {
		return grxl;
	}
	public void setGrxl(ZlshGrzjxl grxl) {
		this.grxl = grxl;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}
	
	public String getCustomerRelMasterRemark() {
		return customerRelMasterRemark;
	}
	public void setCustomerRelMasterRemark(String customerRelMasterRemark) {
		this.customerRelMasterRemark = customerRelMasterRemark;
	}
	public List<ZlshGrzjxl> getGrxlList() {
		return grxlList;
	}
	public void setGrxlList(List<ZlshGrzjxl> grxlList) {
		this.grxlList = grxlList;
	}
	
}