package com.creditharmony.approve.verify.entity.ex;

import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 查询列表、查询条件
 * @Class Name VerifyListEx
 * @author 黄维
 * @Create In 2015年11月28日
 */
public class VerifyListEx extends DataEntity<VerifyListEx> {

	private static final long serialVersionUID = 6789397380910483794L;

	private String loanCode;			// 借款编码
	private String applyId;				// 流程ID
	private String loanCustomerName;	// 客户姓名
	private String customerCertNum;		// 证件号码
	private String province;			// 门店省
	private String provinceName;		// 门店省名
	private String city;				// 门店市
	private String cityName;			// 门店市名
	private String loanTermOrgId;		// 团队组织机构ID
	private String orgName;				// 门店名
	private String productType;			// 产品类型
	private String productTypeName;		// 产品类型
	private String loanUrgentFlag;		// 是否加急
	private Double loanApplyAmount;		// 申请金额
	private Integer loanMonths;			// 分期
	private Double auditAmount;			// 批复金额
	private Date outtoLoanTime;		    // 进件时间
	private Date transactorTime;		// 办理时间、批复时间
	private String dictLoanStatus;		// 状态
	private String transactorCode;		// 当前所属员工编码
	private Date minCustomerIntoTime;	// 进件时间
	private Date maxCustomerIntoTime;	// 进件时间
	private Date minLoanAuditTime;		// 批复时间
	private Date maxLoanAuditTime;		// 批复时间
	private String loanTeamEmpcode; 	// 团队经理
	private String offendSalesName; 	// 客户经理
	private String coborroweCount;		// 共借人数量
	private String checkType;           // 信审/复议
	private String dealType;			// 查看页面的类型
	private Date judgeTime;				// 反欺诈决策时间
	private String orderBy;				// 排序字段
	private int pageSize;				// 每页显示条数
	private int pageNo;				    // 当前页数
	private String orgId;               // 当前所在机构
	private List<String> orgIds;        // 所有下级机构
	private String transactorName;      // 办理人姓名
	private String result;				// 借款结果
	private String loanInfoOldOrNewFlag;//新版or旧版loaninfo_oldornew_flag
	
	public String getTransactorName() {
		return transactorName;
	}

	public void setTransactorName(String transactorName) {
		this.transactorName = transactorName;
	}

	public Date getJudgeTime() {
		return judgeTime;
	}

	public void setJudgeTime(Date judgeTime) {
		this.judgeTime = judgeTime;
	}

	public String getLoanCode() {
		return loanCode;
	}
	
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	
	public String getLoanCustomerName() {
		return loanCustomerName;
	}
	
	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName;
	}
	
	public Integer getLoanMonths() {
		return loanMonths;
	}
	
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
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
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrgName() {
		return orgName;
	}
	
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getLoanUrgentFlag() {
		return loanUrgentFlag;
	}
	
	public void setLoanUrgentFlag(String loanUrgentFlag) {
		this.loanUrgentFlag = loanUrgentFlag;
	}
	
	public Double getLoanApplyAmount() {
		return loanApplyAmount;
	}
	
	public void setLoanApplyAmount(Double loanApplyAmount) {
		this.loanApplyAmount = loanApplyAmount;
	}
	
	public Double getAuditAmount() {
		return auditAmount;
	}
	
	public void setAuditAmount(Double auditAmount) {
		this.auditAmount = auditAmount;
	}	
	
	public Date getOuttoLoanTime() {
		return outtoLoanTime;
	}

	public void setOuttoLoanTime(Date outtoLoanTime) {
		this.outtoLoanTime = outtoLoanTime;
	}

	public Date getTransactorTime() {
		return transactorTime;
	}
	
	public void setTransactorTime(Date transactorTime) {
		this.transactorTime = transactorTime;
	}
	
	public String getDictLoanStatus() {
		return dictLoanStatus;
	}
	
	public void setDictLoanStatus(String dictLoanStatus) {
		this.dictLoanStatus = dictLoanStatus;
	}
	
	public String getTransactorCode() {
		return transactorCode;
	}
	
	public void setTransactorCode(String transactorCode) {
		this.transactorCode = transactorCode;
	}
	
	public Date getMinCustomerIntoTime() {
		return minCustomerIntoTime;
	}
	
	public void setMinCustomerIntoTime(Date minCustomerIntoTime) {
		this.minCustomerIntoTime = minCustomerIntoTime;
	}
	
	public Date getMaxCustomerIntoTime() {
		return maxCustomerIntoTime;
	}
	
	public void setMaxCustomerIntoTime(Date maxCustomerIntoTime) {
		this.maxCustomerIntoTime = maxCustomerIntoTime;
	}
	
	public Date getMinLoanAuditTime() {
		return minLoanAuditTime;
	}
	
	public void setMinLoanAuditTime(Date minLoanAuditTime) {
		this.minLoanAuditTime = minLoanAuditTime;
	}
	
	public Date getMaxLoanAuditTime() {
		return maxLoanAuditTime;
	}
	
	public void setMaxLoanAuditTime(Date maxLoanAuditTime) {
		this.maxLoanAuditTime = maxLoanAuditTime;
	}
	
	public String getProvinceName() {
		return provinceName;
	}
	
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public String getApplyId() {
		return applyId;
	}
	
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getLoanTermOrgId() {
		return loanTermOrgId;
	}

	public void setLoanTermOrgId(String loanTermOrgId) {
		this.loanTermOrgId = loanTermOrgId;
	}

	public String getLoanTeamEmpcode() {
		return loanTeamEmpcode;
	}

	public void setLoanTeamEmpcode(String loanTeamEmpcode) {
		this.loanTeamEmpcode = loanTeamEmpcode;
	}

	public String getOffendSalesName() {
		return offendSalesName;
	}

	public void setOffendSalesName(String offendSalesName) {
		this.offendSalesName = offendSalesName;
	}
	
	public String getCoborroweCount() {
		return coborroweCount;
	}
	
	public void setCoborroweCount(String coborroweCount) {
		this.coborroweCount = coborroweCount;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public List<String> getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getLoanInfoOldOrNewFlag() {
		return loanInfoOldOrNewFlag;
	}

	public void setLoanInfoOldOrNewFlag(String loanInfoOldOrNewFlag) {
		this.loanInfoOldOrNewFlag = loanInfoOldOrNewFlag;
	}
	
}
