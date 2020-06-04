package com.creditharmony.approve.workflow.entity;

import java.util.Date;

/**
 * 工作流查询条件实体类
 * @Class Name ApproveQueryEntity
 * @author xiaoniu.hu
 * @Create In 2015年12月14日
 */
public class ApproveQueryEntity {
	private String loanCode; 			// 借款编号
	private String customerName; 		// 客户姓名
	private String identityCode; 		// 证件号码
	private String provinceName; 		// 省份
	private String provinceCode;		// 省份code
	private String cityName; 			// 城市
	private String cityCode; 			// 城市code
	private String storeName; 			// 门店名称
	private String applyProductName; 	// 产品名称
	private String applyProductCode;	// 产品编码
	private String urgentFlag; 			// 是否加急
	private Double applyMoney;			// 申请金额
	private Integer applyMonth;			// 申请期数
	private String teamManagerName; 	// 团队经理
	private String customerManagerName; // 客户经理
	private Date intoApproveTime;		// 进件时间
	private Date visitFinishTime;		// 外訪完成时间
	private String loanStatusName;		// 借款状态
	private String checkLevel; 	        // 能处理这种产品类型的审核人员级别
	private String dealUser;            // 处理用户
	private String dealUserOrgId;       // 处理用户 所在机构
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCustomerName() {
		return customerName;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getApplyProductName() {
		return applyProductName;
	}

	public void setApplyProductName(String applyProductName) {
		this.applyProductName = applyProductName;
	}

	public String getUrgentFlag() {
		return urgentFlag;
	}

	public void setUrgentFlag(String urgentFlag) {
		this.urgentFlag = urgentFlag;
	}

	public Double getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(Double applyMoney) {
		this.applyMoney = applyMoney;
	}

	public Integer getApplyMonth() {
		return applyMonth;
	}

	public void setApplyMonth(Integer applyMonth) {
		this.applyMonth = applyMonth;
	}

	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}

	public String getCustomerManagerName() {
		return customerManagerName;
	}

	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}

	public Date getIntoApproveTime() {
		return intoApproveTime;
	}

	public void setIntoApproveTime(Date intoApproveTime) {
		this.intoApproveTime = intoApproveTime;
	}

	public Date getVisitFinishTime() {
		return visitFinishTime;
	}

	public void setVisitFinishTime(Date visitFinishTime) {
		this.visitFinishTime = visitFinishTime;
	}

	public String getLoanStatusName() {
		return loanStatusName;
	}

	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName;
	}

	public String getCheckLevel() {
		return checkLevel;
	}

	public void setCheckLevel(String checkLevel) {
		this.checkLevel = checkLevel;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getApplyProductCode() {
		return applyProductCode;
	}

	public void setApplyProductCode(String applyProductCode) {
		this.applyProductCode = applyProductCode;
	}

	public String getDealUserOrgId() {
		return dealUserOrgId;
	}

	public void setDealUserOrgId(String dealUserOrgId) {
		this.dealUserOrgId = dealUserOrgId;
	}	
	
}
