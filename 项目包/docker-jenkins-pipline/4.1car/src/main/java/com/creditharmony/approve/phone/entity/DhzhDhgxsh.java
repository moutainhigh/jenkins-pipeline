package com.creditharmony.approve.phone.entity;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 电话照会_电话关系审核 及相应的电话录音
 * 
 * @Class Name DhzhDhgxsh
 * @author 王浩
 * @Create In 2015年12月1日
 * @update in 2016-10-17
 */
public class DhzhDhgxsh extends DataEntity<DhzhDhgxsh> {

	private static final long serialVersionUID = 2220614452750067793L;

	private String loanCode; // 借款编码
	private String name; // 姓名
	private String loanManRelation; // 与借款人关系
	private String rCustomerCoborrowerId; // 关联ID(主借人或共借人)
	private String dictCustomerType; // 借款人类型(主借人/共借人)
	private String dictTelSource; // 号码来源
	private String telNum; // 电话号码
	private String dictRelationType; // 关系类型(工作证明人;家庭证明人)
	private String dhgxshAssessResult; // 评估结果
	private BigDecimal dhgxshEmployeeNum; // 员工人数（经营类）
	private String loanLiveAddress; // 借款人居住地
	private String dictCheckType; // 类型(1信审初审电话审核 2反欺诈专员电话审核)
	private String workNetAssessResult; // 网查结果
	private String workCheckRemark; // 网查备注
	private String isRepeat; // 是否已经查重
	private String isInPool; // 是否填入查重池
	private String editRemark; // 编辑标识(默认0可编辑1不可编辑)
	private String dictCertType;
	private String customerCertNum; // 身份证号
	private String telRemark; // 电话备注
	private String mateCertnumNetResult; // 网查结果（配偶身份证）
	private String mateCertnumNetRemark; // 网查备注（配偶身份证）
	private String isReady; // 是否初始化 是为1 否为0
	private String dictSourceType;
	private String netCheckResult; // 姓名网查结果
	private String netCheckRemark; // 姓名网查备注
	private String loanId; // 数据来源汇金表id

	// 新版申请表增加
	private String unitName; // 单位名称
	private String province; // 住址省
	private String city; // 住址市
	private String arer; // 住址区
	private String address; // 住址地址
	private List<CityInfo> cityList; // 城市列表
	private List<CityInfo> districtList; // 区县列表

	private String dictUnitProvince; // 单位地址省
	private String dictUnitCity; // 单位地址市
	private String dictUnitArer; // 单位地址区
	private String unitAddress; // 单位地址
	private String department; // 部门 V2
	private String post; // 职务 V2

	private String homeTel; // 汇金录入宅电

	private String loanManRelationRemark; // 反欺诈add关系备注
	private String homeTelAssessResult; // 反欺诈add宅电评估结果
	private String homeTelRemark; // 反欺诈add宅电备注
	
	private String dataSources; // 数据来源：汇金0，其他汇成

	public String getTelRemark() {
		return telRemark;
	}

	public void setTelRemark(String telRemark) {
		this.telRemark = telRemark;
	}

	public String getIsInPool() {
		return isInPool;
	}

	public void setIsInPool(String isInPool) {
		this.isInPool = isInPool;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getLoanManRelation() {
		return loanManRelation;
	}

	public void setLoanManRelation(String loanManRelation) {
		this.loanManRelation = loanManRelation == null ? null : loanManRelation.trim();
	}

	public String getDictCustomerType() {
		return dictCustomerType;
	}

	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType == null ? null : dictCustomerType.trim();
	}

	public String getDictTelSource() {
		return dictTelSource;
	}

	public void setDictTelSource(String dictTelSource) {
		this.dictTelSource = dictTelSource == null ? null : dictTelSource.trim();
	}

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getDictRelationType() {
		return dictRelationType;
	}

	public void setDictRelationType(String dictRelationType) {
		this.dictRelationType = dictRelationType == null ? null : dictRelationType.trim();
	}

	public String getDhgxshAssessResult() {
		return dhgxshAssessResult;
	}

	public void setDhgxshAssessResult(String dhgxshAssessResult) {
		this.dhgxshAssessResult = dhgxshAssessResult == null ? null : dhgxshAssessResult.trim();
	}

	public BigDecimal getDhgxshEmployeeNum() {
		return dhgxshEmployeeNum;
	}

	public void setDhgxshEmployeeNum(BigDecimal dhgxshEmployeeNum) {
		this.dhgxshEmployeeNum = dhgxshEmployeeNum;
	}

	public String getLoanLiveAddress() {
		return loanLiveAddress;
	}

	public void setLoanLiveAddress(String loanLiveAddress) {
		this.loanLiveAddress = loanLiveAddress == null ? null : loanLiveAddress.trim();
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
	}

	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}

	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}

	public String getWorkNetAssessResult() {
		return workNetAssessResult;
	}

	public void setWorkNetAssessResult(String workNetAssessResult) {
		this.workNetAssessResult = workNetAssessResult;
	}

	public String getWorkCheckRemark() {
		return workCheckRemark;
	}

	public void setWorkCheckRemark(String workCheckRemark) {
		this.workCheckRemark = workCheckRemark;
	}

	public String getEditRemark() {
		return editRemark;
	}

	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}

	public String getDictCertType() {
		return dictCertType;
	}

	public void setDictCertType(String dictCertType) {
		this.dictCertType = dictCertType;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getMateCertnumNetResult() {
		return mateCertnumNetResult;
	}

	public void setMateCertnumNetResult(String mateCertnumNetResult) {
		this.mateCertnumNetResult = mateCertnumNetResult;
	}

	public String getMateCertnumNetRemark() {
		return mateCertnumNetRemark;
	}

	public void setMateCertnumNetRemark(String mateCertnumNetRemark) {
		this.mateCertnumNetRemark = mateCertnumNetRemark;
	}

	public String getIsReady() {
		return isReady;
	}

	public void setIsReady(String isReady) {
		this.isReady = isReady;
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

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	/**
	 * 网查结果（亲属的“姓名”）
	 */
	private String clanNameNetResult;
	/**
	 * 网查备注（亲属的“姓名”）
	 */
	private String clanNameNetRemark;
	/**
	 * 网查结果（亲属的“身份证号码”）
	 */
	private String clanCertnumNetResult;
	/**
	 * 网查备注（亲属的“身份证号码”）
	 */
	private String clanCertnumNeRemark;

	/**
	 * 网查结果（亲属的“姓名”）
	 */
	public String getClanNameNetResult() {
		return clanNameNetResult;
	}

	/**
	 * 网查结果（亲属的“姓名”）
	 */
	public void setClanNameNetResult(String clanNameNetResult) {
		this.clanNameNetResult = clanNameNetResult;
	}

	/**
	 * 网查备注（亲属的“姓名”）
	 */
	public String getClanNameNetRemark() {
		return clanNameNetRemark;
	}

	/**
	 * 网查备注（亲属的“姓名”）
	 */
	public void setClanNameNetRemark(String clanNameNetRemark) {
		this.clanNameNetRemark = clanNameNetRemark;
	}

	/**
	 * 网查结果（亲属的“身份证号码”）
	 */
	public String getClanCertnumNetResult() {
		return clanCertnumNetResult;
	}

	/**
	 * 网查结果（亲属的“身份证号码”）
	 */
	public void setClanCertnumNetResult(String clanCertnumNetResult) {
		this.clanCertnumNetResult = clanCertnumNetResult;
	}

	/**
	 * 网查备注（亲属的“身份证号码”）
	 */
	public String getClanCertnumNeRemark() {
		return clanCertnumNeRemark;
	}

	/**
	 * 网查备注（亲属的“身份证号码”）
	 */
	public void setClanCertnumNeRemark(String clanCertnumNeRemark) {
		this.clanCertnumNeRemark = clanCertnumNeRemark;
	}

	/**
	 * 网查结果（配偶的“单位名称”）
	 */
	private String mateUnitnameNetResult;
	/**
	 * 网查备注（配偶的“单位名称”）
	 */
	private String mateUnitnameNetRemark;
	/**
	 * 网查结果（配偶的“住址”）
	 */
	private String mateAddressNetResult;
	/**
	 * 网查备注（配偶的“住址”）
	 */
	private String mateAddressNetRemark;

	/**
	 * 网查结果（配偶的“单位名称”）
	 */
	public String getMateUnitnameNetResult() {
		return mateUnitnameNetResult;
	}

	/**
	 * 网查结果（配偶的“单位名称”）
	 */
	public void setMateUnitnameNetResult(String mateUnitnameNetResult) {
		this.mateUnitnameNetResult = mateUnitnameNetResult;
	}

	/**
	 * 网查备注（配偶的“单位名称”）
	 */
	public String getMateUnitnameNetRemark() {
		return mateUnitnameNetRemark;
	}

	/**
	 * 网查备注（配偶的“单位名称”）
	 */
	public void setMateUnitnameNetRemark(String mateUnitnameNetRemark) {
		this.mateUnitnameNetRemark = mateUnitnameNetRemark;
	}

	/**
	 * 网查结果（配偶的“住址”）
	 */
	public String getMateAddressNetResult() {
		return mateAddressNetResult;
	}

	/**
	 * 网查结果（配偶的“住址”）
	 */
	public void setMateAddressNetResult(String mateAddressNetResult) {
		this.mateAddressNetResult = mateAddressNetResult;
	}

	/**
	 * 网查备注（配偶的“住址”）
	 */
	public String getMateAddressNetRemark() {
		return mateAddressNetRemark;
	}

	/**
	 * 网查备注（配偶的“住址”）
	 */
	public void setMateAddressNetRemark(String mateAddressNetRemark) {
		this.mateAddressNetRemark = mateAddressNetRemark;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArer() {
		return arer;
	}

	public void setArer(String arer) {
		this.arer = arer;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDictUnitProvince() {
		return dictUnitProvince;
	}

	public void setDictUnitProvince(String dictUnitProvince) {
		this.dictUnitProvince = dictUnitProvince;
	}

	public String getDictUnitCity() {
		return dictUnitCity;
	}

	public void setDictUnitCity(String dictUnitCity) {
		this.dictUnitCity = dictUnitCity;
	}

	public String getDictUnitArer() {
		return dictUnitArer;
	}

	public void setDictUnitArer(String dictUnitArer) {
		this.dictUnitArer = dictUnitArer;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getLoanManRelationRemark() {
		return loanManRelationRemark;
	}

	public void setLoanManRelationRemark(String loanManRelationRemark) {
		this.loanManRelationRemark = loanManRelationRemark;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getHomeTelAssessResult() {
		return homeTelAssessResult;
	}

	public void setHomeTelAssessResult(String homeTelAssessResult) {
		this.homeTelAssessResult = homeTelAssessResult;
	}

	public String getHomeTelRemark() {
		return homeTelRemark;
	}

	public void setHomeTelRemark(String homeTelRemark) {
		this.homeTelRemark = homeTelRemark;
	}

	public String getDataSources() {
		return dataSources;
	}

	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}

}