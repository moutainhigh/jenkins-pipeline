package com.creditharmony.approve.newCar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeEx;
import com.creditharmony.approve.common.entity.AuditBack;
import com.creditharmony.approve.common.entity.BackConsult;
import com.creditharmony.approve.common.entity.CustomerAbandon;
import com.creditharmony.approve.outvisit.entity.ex.OutsideCheckListEx;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.bpm.frame.view.BaseBusinessView;

public class NewVerifyBusinessView extends BaseBusinessView implements Serializable {
	private static final long serialVersionUID = -5672321679467683678L;

	private String loanCode;        					// 借款编号
	private String type;            					// 借款人类型 主借人/共借人
	private String checkType;       					// 审批步骤 复议 信审
	private String customerId;      					// 客户id
	private String customerName;    					// 客户姓名
	private String customerCertNum; 					// 身份证号
	private String customerSex;     					// 性别
	private String outsideFlag;     					// 外访标识
	private List<NewCoborrowerView> coborrowerList; 		// 共借人
	private AntifraudReport antifraudReport;     		// 提报反欺诈
	private AuditBack auditBack;                 		// 保存回退清单数据
	private OutsideCheckListEx checkListEx;   			// 保存发起外访数据
	private AuditResultEx auditResultEx;         		// 复议终审决策数据保存
	private CustomerAbandon customerAbandon;     		// 客户放弃
	private BackConsult backConsult;             		// 退回协商
	private Double auditAmount; 				 		// 批复金额
	private String loanApplyStatus;              		// 审批状态
	private String loanStatusCode;              		// 审批状态
	private AntiFraudJudgeEx antiFraudJudgeEx;          // 审批状态
	private String sunyardUrl;        				    // 信雅达地址信息
	private String visitStartTime;						// 外访开始时间
	private String outApproveTime;						// 审批时间
	private String userCode;                            // 工号
	private String visitFlag;							// 门店拒借或放弃
	private String timeOutFlag;							// 超时标识
	private Date timeOutPoint;						    // 外访超时时间
	private String backFlag;			            // 借款服务部退回标识
	private String checkResult;				            // 每步的操作结果
	private String resultRemark;					    // 备注信息
	private String stepName;				            // 操作步骤
	private String refuseReason;			            // 拒绝原因
	private String resultId;				            // 最新结果id
	private boolean flag= false;
	private String model; 			                    // 模型
	private String loanFlag;		                    // 出借标识
	private String loanFlagName;	                    // 渠道name批单处理用
	private String riskLevel;                           // 评分等级
	private String dictSourceType;                      // 数据来源
	private String loanUrgentFlag;                      // 是否加急
	private String orderField;                          // 更新排序
	private String loanInfoOldOrNewFlag;                // 在信借申请列表点击办理根据此标识判断是跳转到旧版申请表页面还是新版申请表页面,默认为0,0表示跳到旧版,为空或1表示跳到新版
	
	public String getLoanInfoOldOrNewFlag() {
		return loanInfoOldOrNewFlag;
	}
	public void setLoanInfoOldOrNewFlag(String loanInfoOldOrNewFlag) {
		this.loanInfoOldOrNewFlag = loanInfoOldOrNewFlag;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getCustomerSex() {
		return customerSex;
	}
	public void setCustomerSex(String customerSex) {
		this.customerSex = customerSex;
	}
	public String getOutsideFlag() {
		return outsideFlag;
	}
	public void setOutsideFlag(String outsideFlag) {
		this.outsideFlag = outsideFlag;
	}
	public List<NewCoborrowerView> getCoborrowerList() {
		return coborrowerList;
	}
	public void setCoborrowerList(List<NewCoborrowerView> coborrowerList) {
		this.coborrowerList = coborrowerList;
	}
	public AntifraudReport getAntifraudReport() {
		return antifraudReport;
	}
	public void setAntifraudReport(AntifraudReport antifraudReport) {
		this.antifraudReport = antifraudReport;
	}
	public AuditBack getAuditBack() {
		return auditBack;
	}
	public void setAuditBack(AuditBack auditBack) {
		this.auditBack = auditBack;
	}
	public OutsideCheckListEx getCheckListEx() {
		return checkListEx;
	}
	public void setCheckListEx(OutsideCheckListEx checkListEx) {
		this.checkListEx = checkListEx;
	}
	public AuditResultEx getAuditResultEx() {
		return auditResultEx;
	}
	public void setAuditResultEx(AuditResultEx auditResultEx) {
		this.auditResultEx = auditResultEx;
	}
	public CustomerAbandon getCustomerAbandon() {
		return customerAbandon;
	}
	public void setCustomerAbandon(CustomerAbandon customerAbandon) {
		this.customerAbandon = customerAbandon;
	}
	public BackConsult getBackConsult() {
		return backConsult;
	}
	public void setBackConsult(BackConsult backConsult) {
		this.backConsult = backConsult;
	}
	public Double getAuditAmount() {
		return auditAmount;
	}
	public void setAuditAmount(Double auditAmount) {
		this.auditAmount = auditAmount;
	}
	public String getLoanApplyStatus() {
		return loanApplyStatus;
	}
	public void setLoanApplyStatus(String loanApplyStatus) {
		this.loanApplyStatus = loanApplyStatus;
	}
	public AntiFraudJudgeEx getAntiFraudJudgeEx() {
		return antiFraudJudgeEx;
	}
	public void setAntiFraudJudgeEx(AntiFraudJudgeEx antiFraudJudgeEx) {
		this.antiFraudJudgeEx = antiFraudJudgeEx;
	}
	public String getSunyardUrl() {
		return sunyardUrl;
	}
	public void setSunyardUrl(String sunyardUrl) {
		this.sunyardUrl = sunyardUrl;
	}
	public String getVisitStartTime() {
		return visitStartTime;
	}
	public void setVisitStartTime(String visitStartTime) {
		this.visitStartTime = visitStartTime;
	}
	public String getOutApproveTime() {
		return outApproveTime;
	}
	public void setOutApproveTime(String outApproveTime) {
		this.outApproveTime = outApproveTime;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getVisitFlag() {
		return visitFlag;
	}
	public void setVisitFlag(String visitFlag) {
		this.visitFlag = visitFlag;
	}
	public String getTimeOutFlag() {
		return timeOutFlag;
	}
	public void setTimeOutFlag(String timeOutFlag) {
		this.timeOutFlag = timeOutFlag;
	}
	public Date getTimeOutPoint() {
		return timeOutPoint;
	}
	public void setTimeOutPoint(Date timeOutPoint) {
		this.timeOutPoint = timeOutPoint;
	}
	public String getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	public String getResultRemark() {
		return resultRemark;
	}
	public void setResultRemark(String resultRemark) {
		this.resultRemark = resultRemark;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getLoanFlag() {
		return loanFlag;
	}
	public void setLoanFlag(String loanFlag) {
		this.loanFlag = loanFlag;
	}
	public String getLoanFlagName() {
		return loanFlagName;
	}
	public void setLoanFlagName(String loanFlagName) {
		this.loanFlagName = loanFlagName;
	}
	public String getRiskLevel() {
		return riskLevel;
	}
	public void setRiskLevel(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}
	public String getLoanStatusCode() {
		return loanStatusCode;
	}
	public void setLoanStatusCode(String loanStatusCode) {
		this.loanStatusCode = loanStatusCode;
	}
	public String getLoanUrgentFlag() {
		return loanUrgentFlag;
	}
	public void setLoanUrgentFlag(String loanUrgentFlag) {
		this.loanUrgentFlag = loanUrgentFlag;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}		
	
}
