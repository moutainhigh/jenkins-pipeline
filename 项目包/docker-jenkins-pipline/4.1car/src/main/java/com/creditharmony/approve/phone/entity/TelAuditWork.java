package com.creditharmony.approve.phone.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 审核_工作单位信息
 * @Class Name TelAuditWork
 * @author 赖敏
 * @Create In 2016年1月26日
 */
public class TelAuditWork extends DataEntity<TelAuditWork> {

	private static final long serialVersionUID = -6338723408450761587L;	
	
	private String loanCode;				// 借款编码
	private String rCustomerCoborrowerId;	// 关联ID(主借人或共借人)
	private String dictCustomerType;		// 借款人类型(主借人/共借人)
	private String workUnitname;			// 单位名称	
	private String workProvince;			// 单位省
	private String workCity;				// 单位市
	private String workDistrict;			// 单位区
	private String workAddress;				// 单位地址
	private Integer workUnitScale;			// 单位规模
	private String workInfoSource;			// 单位名称来源
	private String workNetAssessResult;		// 评估结果
	private String workCheckRemark;			// 核查备注
	private String dictCheckType;			// 类型(初审，信审初审，复议初审)
	private String isRepeat;				// 是否已经查重
	private String isInPool;				// 是否填入查重池
	private String editRemark;				// 编辑标识(默认0可编辑1不可编辑)
	private String dictCompIndustry;
	private String netCheckResultAddr;     // 单位地址网查结果
	private String netCheckRemarkAddr;     // 单位地址网查备注
	private String isReady;					// 是否初始化 是为1 否为0
	private String loanId;			// 数据来源汇金表id
	
	// 新版申请表追加字段
	private Date compEntryDay;				// 入职时间
	private String compDepartment;			// 部门
	private String compPost;     			// 职务
	private String compPostLevel;     		// 职务级别
	private Integer compSalaryDay;			// 每月发薪日期
	private String dictSalaryPay;			// 主要发薪方式
	
	private String dataSources;			// 数据来源：汇金0，其他汇成

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

	public String getDictCustomerType() {
		return dictCustomerType;
	}

	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType == null ? null
				: dictCustomerType.trim();
	}

	public String getWorkUnitname() {
		return workUnitname;
	}

	public void setWorkUnitname(String workUnitname) {
		this.workUnitname = workUnitname == null ? null : workUnitname.trim();
	}

	public String getDictCheckType() {
		return dictCheckType;
	}

	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType == null ? null : dictCheckType
				.trim();
	}

	public String getWorkCheckRemark() {
		return workCheckRemark;
	}

	public void setWorkCheckRemark(String workCheckRemark) {
		this.workCheckRemark = workCheckRemark;
	}

	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}

	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}

	public String getWorkProvince() {
		return workProvince;
	}

	public void setWorkProvince(String workProvince) {
		this.workProvince = workProvince;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public String getWorkDistrict() {
		return workDistrict;
	}

	public void setWorkDistrict(String workDistrict) {
		this.workDistrict = workDistrict;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public Integer getWorkUnitScale() {
		return workUnitScale;
	}

	public void setWorkUnitScale(Integer workUnitScale) {
		this.workUnitScale = workUnitScale;
	}

	public String getWorkInfoSource() {
		return workInfoSource;
	}

	public void setWorkInfoSource(String workInfoSource) {
		this.workInfoSource = workInfoSource;
	}

	public String getWorkNetAssessResult() {
		return workNetAssessResult;
	}

	public void setWorkNetAssessResult(String workNetAssessResult) {
		this.workNetAssessResult = workNetAssessResult;
	}


	public String getDictCompIndustry() {
		return dictCompIndustry;
	}

	public void setDictCompIndustry(String dictCompIndustry) {
		this.dictCompIndustry = dictCompIndustry;
	}

	public String getEditRemark() {
		return editRemark;
	}
	
	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}

	public String getNetCheckResultAddr() {
		return netCheckResultAddr;
	}

	public void setNetCheckResultAddr(String netCheckResultAddr) {
		this.netCheckResultAddr = netCheckResultAddr;
	}

	public String getNetCheckRemarkAddr() {
		return netCheckRemarkAddr;
	}

	public void setNetCheckRemarkAddr(String netCheckRemarkAddr) {
		this.netCheckRemarkAddr = netCheckRemarkAddr;
	}

	public String getIsReady() {
		return isReady;
	}

	public void setIsReady(String isReady) {
		this.isReady = isReady;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public Date getCompEntryDay() {
		return compEntryDay;
	}

	public void setCompEntryDay(Date compEntryDay) {
		this.compEntryDay = compEntryDay;
	}

	public String getCompDepartment() {
		return compDepartment;
	}

	public void setCompDepartment(String compDepartment) {
		this.compDepartment = compDepartment;
	}

	public String getCompPost() {
		return compPost;
	}

	public void setCompPost(String compPost) {
		this.compPost = compPost;
	}

	public String getCompPostLevel() {
		return compPostLevel;
	}

	public void setCompPostLevel(String compPostLevel) {
		this.compPostLevel = compPostLevel;
	}

	public Integer getCompSalaryDay() {
		return compSalaryDay;
	}

	public void setCompSalaryDay(Integer compSalaryDay) {
		this.compSalaryDay = compSalaryDay;
	}

	public String getDictSalaryPay() {
		return dictSalaryPay;
	}

	public void setDictSalaryPay(String dictSalaryPay) {
		this.dictSalaryPay = dictSalaryPay;
	}

	public String getDataSources() {
		return dataSources;
	}

	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}	
	
}