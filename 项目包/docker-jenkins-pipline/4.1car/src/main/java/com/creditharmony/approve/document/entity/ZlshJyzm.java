package com.creditharmony.approve.document.entity;

import java.util.Date;
import java.util.List;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核 经营证明
 * @Class Name ZlshJyzm
 * @author 黄维
 * @Create In 2015年12月14日
 */
public class ZlshJyzm extends DataEntity<ZlshJyzm>{
    
	private static final long serialVersionUID = 1L;

	private String loanCode;				// 借款编码
	private String rCustomerCoborrowerId;	// 关联ID(主借人，共借人)
	private String dictCustomerType;		// 借款人类型(主借人/共借人)
	private double jyzmRegisteredCapital;	// 注册资本
	private String jyzmRegisteredProvince;	// 省
	private String jyzmRegisteredCity;		// 市
	private String jyzmRegisteredArea;		// 区
	private String jyzmRegisteredAddress;	// 地址
	private Date   jyzmSetUpTime;			// 成立时间
	private String jyzmLegalMan;			// 法定代表人
	private String dictEnterpriseType;		// 企业类型
	private String jyzmCompanyName;			// 公司名称
	private String changeFlag;				// 是否变更
	private String jyzmChangeRemark;		// 变更备注
	private String dictCheckType;			// 类型(初审，信审初审，复议初审)
	private String businessScope;			// 经营范围
	
	private String registNumType;			// 企业注册码
	private String registNum;				// 组织机构码
	private String creditCode;				// 信用代码
	private String dictSourceType; 

	private List<CityInfo> provinceList;
	private List<CityInfo> cityList;
	private List<CityInfo> districtList;
	
	private List<CityInfo> manageProvinceList;
	private List<CityInfo> manageCityList;
	private List<CityInfo> manageDistrictList;
	
	private ZlshJyzmGdxx jyzmGdxx; // 股东信息保存
	private ZlshJydzzm  jydzzm; // 经营地址证明
	private List<ZlshJyzmGdxx> jyzmGdxxList;// 股东list
	private List<ZlshJydzzm> jydzzmList;// 经营地址证明List
	
	private String certNum;//法人代表身份证号	
	private String manageProvince;//经营地址省
	private String manageCity;//经营地址市
	private String manageArea;//经营地址区
	private String manageAddress;	// 经营地址
	private String dictSysFlag;     //汇金汇城数据标识（汇金同步过来的数据为1,汇城数据为0）
	
	
	public String getRegistNumType() {
		return registNumType;
	}
	public void setRegistNumType(String registNumType) {
		this.registNumType = registNumType;
	}
	public String getRegistNum() {
		return registNum;
	}
	public void setRegistNum(String registNum) {
		this.registNum = registNum;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public ZlshJydzzm getJydzzm() {
		return jydzzm;
	}
	public void setJydzzm(ZlshJydzzm jydzzm) {
		this.jydzzm = jydzzm;
	}
	public List<ZlshJydzzm> getJydzzmList() {
		return jydzzmList;
	}
	public void setJydzzmList(List<ZlshJydzzm> jydzzmList) {
		this.jydzzmList = jydzzmList;
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
	public List<ZlshJyzmGdxx> getJyzmGdxxList() {
		return jyzmGdxxList;
	}
	public void setJyzmGdxxList(List<ZlshJyzmGdxx> jyzmGdxxList) {
		this.jyzmGdxxList = jyzmGdxxList;
	}
	public ZlshJyzmGdxx getJyzmGdxx() {
		return jyzmGdxx;
	}
	public void setJyzmGdxx(ZlshJyzmGdxx jyzmGdxx) {
		this.jyzmGdxx = jyzmGdxx;
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
    
	public double getJyzmRegisteredCapital() {
		return jyzmRegisteredCapital;
	}
	public void setJyzmRegisteredCapital(double jyzmRegisteredCapital) {
		this.jyzmRegisteredCapital = jyzmRegisteredCapital;
	}
	public String getJyzmRegisteredAddress() {
		return jyzmRegisteredAddress;
	}
	public void setJyzmRegisteredAddress(String jyzmRegisteredAddress) {
		this.jyzmRegisteredAddress = jyzmRegisteredAddress;
	}
	public Date getJyzmSetUpTime() {
		return jyzmSetUpTime;
	}
	public void setJyzmSetUpTime(Date jyzmSetUpTime) {
		this.jyzmSetUpTime = jyzmSetUpTime;
	}
	public String getJyzmLegalMan() {
		return jyzmLegalMan;
	}
	public void setJyzmLegalMan(String jyzmLegalMan) {
		this.jyzmLegalMan = jyzmLegalMan;
	}
	public String getDictEnterpriseType() {
		return dictEnterpriseType;
	}
	public void setDictEnterpriseType(String dictEnterpriseType) {
		this.dictEnterpriseType = dictEnterpriseType;
	}
	public String getJyzmCompanyName() {
		return jyzmCompanyName;
	}
	public void setJyzmCompanyName(String jyzmCompanyName) {
		this.jyzmCompanyName = jyzmCompanyName;
	}
	public String getChangeFlag() {
		return changeFlag;
	}
	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}
	public String getJyzmChangeRemark() {
		return jyzmChangeRemark;
	}
	public void setJyzmChangeRemark(String jyzmChangeRemark) {
		this.jyzmChangeRemark = jyzmChangeRemark;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getJyzmRegisteredProvince() {
		return jyzmRegisteredProvince;
	}
	public void setJyzmRegisteredProvince(String jyzmRegisteredProvince) {
		this.jyzmRegisteredProvince = jyzmRegisteredProvince;
	}
	public String getJyzmRegisteredCity() {
		return jyzmRegisteredCity;
	}
	public void setJyzmRegisteredCity(String jyzmRegisteredCity) {
		this.jyzmRegisteredCity = jyzmRegisteredCity;
	}
	public String getJyzmRegisteredArea() {
		return jyzmRegisteredArea;
	}
	public void setJyzmRegisteredArea(String jyzmRegisteredArea) {
		this.jyzmRegisteredArea = jyzmRegisteredArea;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}
	
	public List<CityInfo> getManageProvinceList() {
		return manageProvinceList;
	}
	public void setManageProvinceList(List<CityInfo> manageProvinceList) {
		this.manageProvinceList = manageProvinceList;
	}
	public List<CityInfo> getManageCityList() {
		return manageCityList;
	}
	public void setManageCityList(List<CityInfo> manageCityList) {
		this.manageCityList = manageCityList;
	}
	public List<CityInfo> getManageDistrictList() {
		return manageDistrictList;
	}
	public void setManageDistrictList(List<CityInfo> manageDistrictList) {
		this.manageDistrictList = manageDistrictList;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	public String getManageProvince() {
		return manageProvince;
	}
	public void setManageProvince(String manageProvince) {
		this.manageProvince = manageProvince;
	}
	public String getManageCity() {
		return manageCity;
	}
	public void setManageCity(String manageCity) {
		this.manageCity = manageCity;
	}
	public String getManageArea() {
		return manageArea;
	}
	public void setManageArea(String manageArea) {
		this.manageArea = manageArea;
	}
	public String getManageAddress() {
		return manageAddress;
	}
	public void setManageAddress(String manageAddress) {
		this.manageAddress = manageAddress;
	}
	public String getDictSysFlag() {
		return dictSysFlag;
	}
	public void setDictSysFlag(String dictSysFlag) {
		this.dictSysFlag = dictSysFlag;
	}	
}