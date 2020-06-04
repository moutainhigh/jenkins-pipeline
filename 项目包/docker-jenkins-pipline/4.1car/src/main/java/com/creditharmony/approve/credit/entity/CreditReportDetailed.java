package com.creditharmony.approve.credit.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 详版征信报告
 * @Class Name CreditReportDetailed
 * @author 李文勇
 * @Create In 2016年1月5日
 */
public class CreditReportDetailed extends DataEntity<CreditReportDetailed> {

	private static final long serialVersionUID = 1L;
	
	private String loanCode;					// 借款编号
	private String credit;						// 征信记录是否空白
	private String creditSource;				// 数据源
	private String creditCode;					// 编号
	private Date queryTime;						// 查询时间*
	private Date reportTime;					// 报告时间
	private String name;						// 姓名
	private String sex;							// 性别
	private String certType;					// 证件类型
	private String certNo;						// 证件号码
	private Date birthday;						// 出生日期
	private String highestDegree;				// 最高学位
	private String highestEducation;			// 最高学历*
	private String contactAddress;				// 通讯地址
	private String zipCode;						// 邮政编码
	private String nativeAddress;				// 户籍地址
	private String homePhone;					// 住宅电话
	private String unitPhone;					// 单位电话
	private String mobilePhone;					// 手机号码
	private String email;						// 电子邮箱
	private String marryStatus;					// 婚姻状况
	private String mateName;					// 配偶姓名
	private String mateCertType;				// 配偶证件类型
	private String mateCertNo;					// 配偶证件号码
	private String mateCompany;					// 配偶工作单位
	private String matePhone;					// 配偶联系电话
	private String nativeAddProvince;			// 户籍省
	private String nativeAddCity;				// 户籍市
	private String nativeAddArea;				// 户籍区
	private String contactAddProvince;			// 通讯省
	private String contactAddCity;				// 通讯市
	private String contactAddArea;				// 通讯区
	private String rCustomerCoborrowerId;		// 关联ID（区分共借人）
	private String dictCustomerType;			// 借款人类型(主借人/共借人)
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getCreditSource() {
		return creditSource;
	}
	public void setCreditSource(String creditSource) {
		this.creditSource = creditSource;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public Date getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCertType() {
		return certType;
	}
	public void setCertType(String certType) {
		this.certType = certType;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getHighestDegree() {
		return highestDegree;
	}
	public void setHighestDegree(String highestDegree) {
		this.highestDegree = highestDegree;
	}
	public String getHighestEducation() {
		return highestEducation;
	}
	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}
	
	public String getContactAddress() {
		return contactAddress;
	}
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getNativeAddress() {
		return nativeAddress;
	}
	public void setNativeAddress(String nativeAddress) {
		this.nativeAddress = nativeAddress;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getUnitPhone() {
		return unitPhone;
	}
	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMarryStatus() {
		return marryStatus;
	}
	public void setMarryStatus(String marryStatus) {
		this.marryStatus = marryStatus;
	}
	public String getMateName() {
		return mateName;
	}
	public void setMateName(String mateName) {
		this.mateName = mateName;
	}
	public String getMateCertType() {
		return mateCertType;
	}
	public void setMateCertType(String mateCertType) {
		this.mateCertType = mateCertType;
	}
	public String getMateCertNo() {
		return mateCertNo;
	}
	public void setMateCertNo(String mateCertNo) {
		this.mateCertNo = mateCertNo;
	}
	public String getMateCompany() {
		return mateCompany;
	}
	public void setMateCompany(String mateCompany) {
		this.mateCompany = mateCompany;
	}
	public String getMatePhone() {
		return matePhone;
	}
	public void setMatePhone(String matePhone) {
		this.matePhone = matePhone;
	}
	public String getNativeAddProvince() {
		return nativeAddProvince;
	}
	public void setNativeAddProvince(String nativeAddProvince) {
		this.nativeAddProvince = nativeAddProvince;
	}
	public String getNativeAddCity() {
		return nativeAddCity;
	}
	public void setNativeAddCity(String nativeAddCity) {
		this.nativeAddCity = nativeAddCity;
	}
	public String getNativeAddArea() {
		return nativeAddArea;
	}
	public void setNativeAddArea(String nativeAddArea) {
		this.nativeAddArea = nativeAddArea;
	}
	public String getContactAddProvince() {
		return contactAddProvince;
	}
	public void setContactAddProvince(String contactAddProvince) {
		this.contactAddProvince = contactAddProvince;
	}
	public String getContactAddCity() {
		return contactAddCity;
	}
	public void setContactAddCity(String contactAddCity) {
		this.contactAddCity = contactAddCity;
	}
	public String getContactAddArea() {
		return contactAddArea;
	}
	public void setContactAddArea(String contactAddArea) {
		this.contactAddArea = contactAddArea;
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
}
