package com.creditharmony.approve.workflow.entity;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;
/**
 * 工作流待办列表实体类
 * @Class Name VerifyFlowTaskItemEntity
 * @author xiaoniu.hu
 * @Create In 2016年1月7日
 */
public class VerifyFlowTaskItemEntity extends BaseTaskItemView implements Serializable{
	private static final long serialVersionUID = 74592908455640583L;

	private String loanCode; 			// 借款编号
	private String customerName; 		// 客户姓名
	private String identityCode; 		// 证件号码
	private String provinceName; 		// 省份
	private String cityName; 			// 城市
	private String storeName; 			// 门店名称
	private String applyProductName; 	// 产品名称
	private String urgentFlag; 			// 是否加急
	private Double applyMoney;			// 申请金额
	private Integer applyMonth;			// 申请期数
	private String loanStatusName;		// 借款状态
	private Date intoApproveTime;		// 进件时间
	private String visitFinishTime;		// 外訪完成时间
	private Double replyMoney; 			// 批复金额
	private Date replyTime; 			// 批复时间
	private String replyProductName; 	// 批复产品
	private Integer replyMonth; 		// 批复期限
	private String coborrowerName;		// 共借人
	private String teamManagerName; 	// 团队经理
	private String customerManagerName; // 客户经理
	private String checkLevel; 	        // 能处理这种产品类型的审核人员级别
	private String dealUser;            // 处理用户
	private String loanStatusCode;      // 借款状态编码
	private String backFlag;			// 借款服务部退回标识
	private String dictLoanType;		// 是否结清再贷
	private String loanInfoOldOrNewFlag;//新版or旧版
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getLoanStatusName() {
		return loanStatusName;
	}
	public void setLoanStatusName(String loanStatusName) {
		this.loanStatusName = loanStatusName;
	}
	public Date getIntoApproveTime() {
		return intoApproveTime;
	}
	public void setIntoApproveTime(Date intoApproveTime) {
		this.intoApproveTime = intoApproveTime;
	}
	public String getVisitFinishTime() {
		return visitFinishTime;
	}
	public void setVisitFinishTime(String visitFinishTime) {
		this.visitFinishTime = visitFinishTime;
	}
	public Double getReplyMoney() {
		return replyMoney;
	}
	public void setReplyMoney(Double replyMoney) {
		this.replyMoney = replyMoney;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyProductName() {
		return replyProductName;
	}
	public void setReplyProductName(String replyProductName) {
		this.replyProductName = replyProductName;
	}
	public Integer getReplyMonth() {
		return replyMonth;
	}
	public void setReplyMonth(Integer replyMonth) {
		this.replyMonth = replyMonth;
	}
	public String getCoborrowerName() {
		return coborrowerName;
	}
	public void setCoborrowerName(String coborrowerName) {
		this.coborrowerName = coborrowerName;
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
	public String getLoanStatusCode() {
		return loanStatusCode;
	}
	public void setLoanStatusCode(String loanStatusCode) {
		this.loanStatusCode = loanStatusCode;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getDictLoanType() {
		return dictLoanType;
	}
	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
	}
	public String getLoanInfoOldOrNewFlag() {
		return loanInfoOldOrNewFlag;
	}
	public void setLoanInfoOldOrNewFlag(String loanInfoOldOrNewFlag) {
		this.loanInfoOldOrNewFlag = loanInfoOldOrNewFlag;
	}
}
