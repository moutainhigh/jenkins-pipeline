package com.creditharmony.approve.carloan.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 客户咨询
 * @Class Name CarCustomerConsultation
 * @author 安子帅
 * @Create In 2016年1月21日
 */
public class CarCustomerConsultation extends DataEntity<CarCustomerConsultation> {

	private static final long serialVersionUID = -882875434530168423L;
	private String id;        //编号
	private String customerCode;//客户编码
	private String managerCode;//客户经理CODE
	private String consTeamManagerCode;// 团队经理编号
	private BigDecimal  consLoanAmount;//借款金额
	private String  dictLoanUse;// 借款用途
	private String  dictLoanType;//借款类型
	private String consLoanRemarks;// 沟通记录
	private Date consCommunicateDate;//沟通时间
	private Date  planArrivalTime;//预计到店时间
	private String  consTelesalesFlag ;//是否电销（0：否，1：是）
	private String  consServiceUserCode ;//客服人员（门店用）
	private String  consTelesalesSource ;// 客服人员（电销用）
	private String  createBy;// 创建人
	private String  modifyBy;// 修改人
	private Date  createTime ;// 创建时间
	private Date  modifyTime;// 最后修改时间
	private String consTelesalesOrgcode ;// 电销组织机构编码
	private String  dictOperStatus;// 下一步操作状态
	private String consTeamEmpName;// 团队经理名字
	private String loanCode; 
	
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getConsTeamEmpName() {
		return consTeamEmpName;
	}
	public void setConsTeamEmpName(String consTeamEmpName) {
		this.consTeamEmpName = consTeamEmpName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getConsTeamManagerCode() {
		return consTeamManagerCode;
	}
	public void setConsTeamManagerCode(String consTeamManagerCode) {
		this.consTeamManagerCode = consTeamManagerCode;
	}
	
	public BigDecimal getConsLoanAmount() {
		return consLoanAmount;
	}
	public void setConsLoanAmount(BigDecimal consLoanAmount) {
		this.consLoanAmount = consLoanAmount;
	}
	public String getDictLoanUse() {
		return dictLoanUse;
	}
	public void setDictLoanUse(String dictLoanUse) {
		this.dictLoanUse = dictLoanUse;
	}
	public String getDictLoanType() {
		return dictLoanType;
	}
	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
	}
	public String getConsLoanRemarks() {
		return consLoanRemarks;
	}
	public void setConsLoanRemarks(String consLoanRemarks) {
		this.consLoanRemarks = consLoanRemarks;
	}
	public Date getConsCommunicateDate() {
		return consCommunicateDate;
	}
	public void setConsCommunicateDate(Date consCommunicateDate) {
		this.consCommunicateDate = consCommunicateDate;
	}
	public Date getPlanArrivalTime() {
		return planArrivalTime;
	}
	public void setPlanArrivalTime(Date planArrivalTime) {
		this.planArrivalTime = planArrivalTime;
	}
	public String getConsTelesalesFlag() {
		return consTelesalesFlag;
	}
	public void setConsTelesalesFlag(String consTelesalesFlag) {
		this.consTelesalesFlag = consTelesalesFlag;
	}
	public String getConsServiceUserCode() {
		return consServiceUserCode;
	}
	public void setConsServiceUserCode(String consServiceUserCode) {
		this.consServiceUserCode = consServiceUserCode;
	}
	public String getConsTelesalesSource() {
		return consTelesalesSource;
	}
	public void setConsTelesalesSource(String consTelesalesSource) {
		this.consTelesalesSource = consTelesalesSource;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getConsTelesalesOrgcode() {
		return consTelesalesOrgcode;
	}
	public void setConsTelesalesOrgcode(String consTelesalesOrgcode) {
		this.consTelesalesOrgcode = consTelesalesOrgcode;
	}
	public String getDictOperStatus() {
		return dictOperStatus;
	}
	public void setDictOperStatus(String dictOperStatus) {
		this.dictOperStatus = dictOperStatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
