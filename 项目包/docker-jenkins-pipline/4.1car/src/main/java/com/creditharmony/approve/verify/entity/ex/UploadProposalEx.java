package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.document.entity.ZlshCczm;
import com.creditharmony.approve.document.entity.ZlshGxht;
import com.creditharmony.approve.document.entity.ZlshLoanAccount;
import com.creditharmony.approve.document.entity.ZlshZczm;
import com.creditharmony.approve.document.entity.ex.CompanyInfoEx;
import com.creditharmony.approve.internet.entity.ex.OutNetEx;
import com.creditharmony.approve.internet.entity.ex.UploadPhoneResult;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.ProposalRemarks;

/**
 * 下载意见书页面ex
 * 
 * @Class Name UploadProposalEx
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
public class UploadProposalEx implements Serializable {
	private static final long serialVersionUID = 1L;

	private MainLoanInfoEx mainLoanInfoEx; // 主借人及配偶信息
	private List<JointlyLoanInfoEx> jointlyLoanInfoEx; // 共借人及配偶信息
	private List<CompanyInfoEx> companyInfo; // 企业信息
	private List<ZlshGxht> contractExs; // 购销合同
	private List<ZlshLoanAccount> zlshLoanAccount; // 账户流水
	private List<ZlshZczm> zlshZczms; // 资产证明
	private List<ZlshCczm> zlshCczms; // 车产证明
	private List<UploadPhoneResult> telCheckResultExs; // 电话核查结果
	private AuditResult auditResult; // 审核结果
	private ProposalRemarks remark; // 备注信息
	private List<AuditResult> peoples; // 审核人
	private List<OutNetEx> outNet;; // 外部审核
	private Map<String, String> marriage; // 婚姻状况
	private Map<String, String> loanUse; // 借款用途
	private Map<String, String> shareholder; // 股东
	private Map<String, String> settlement; // 付款方式
	private Map<String, String> updowncontract; // 合同类型
	private Map<String, String> telSrc; // 电话来源
	private Map<String, String> checkResult; // 判定结果
	private Map<String, String> salaryTrace; // 流水类型
	private Map<String, String> pledge; // 抵押标志
	private Map<String, String> house; // 房产种类
	private Map<String, String> property; // 产权人
	private String uploadUrl; // 下载意见书对应的url
	private Map<String, String> phoneStatue; // 产权人
	
	public MainLoanInfoEx getMainLoanInfoEx() {
		return mainLoanInfoEx;
	}

	public void setMainLoanInfoEx(MainLoanInfoEx mainLoanInfoEx) {
		this.mainLoanInfoEx = mainLoanInfoEx;
	}

	public List<ZlshLoanAccount> getZlshLoanAccount() {
		return zlshLoanAccount;
	}

	public void setZlshLoanAccount(List<ZlshLoanAccount> zlshLoanAccount) {
		this.zlshLoanAccount = zlshLoanAccount;
	}

	public List<ZlshZczm> getZlshZczms() {
		return zlshZczms;
	}

	public void setZlshZczms(List<ZlshZczm> zlshZczms) {
		this.zlshZczms = zlshZczms;
	}

	public List<ZlshCczm> getZlshCczms() {
		return zlshCczms;
	}

	public void setZlshCczms(List<ZlshCczm> zlshCczms) {
		this.zlshCczms = zlshCczms;
	}

	public List<UploadPhoneResult> getTelCheckResultExs() {
		return telCheckResultExs;
	}

	public void setTelCheckResultExs(List<UploadPhoneResult> telCheckResultExs) {
		this.telCheckResultExs = telCheckResultExs;
	}

	public List<JointlyLoanInfoEx> getJointlyLoanInfoEx() {
		return jointlyLoanInfoEx;
	}

	public void setJointlyLoanInfoEx(List<JointlyLoanInfoEx> jointlyLoanInfoEx) {
		this.jointlyLoanInfoEx = jointlyLoanInfoEx;
	}

	public ProposalRemarks getRemark() {
		return remark;
	}

	public void setRemark(ProposalRemarks remark) {
		this.remark = remark;
	}

	public AuditResult getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(AuditResult auditResult) {
		this.auditResult = auditResult;
	}

	public List<AuditResult> getPeoples() {
		return peoples;
	}

	public void setPeoples(List<AuditResult> peoples) {
		this.peoples = peoples;
	}

	public List<CompanyInfoEx> getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(List<CompanyInfoEx> companyInfo) {
		this.companyInfo = companyInfo;
	}

	public List<OutNetEx> getOutNet() {
		return outNet;
	}

	public void setOutNet(List<OutNetEx> outNet) {
		this.outNet = outNet;
	}

	public Map<String, String> getMarriage() {
		return marriage;
	}

	public void setMarriage(Map<String, String> marriage) {
		this.marriage = marriage;
	}

	public Map<String, String> getLoanUse() {
		return loanUse;
	}

	public void setLoanUse(Map<String, String> loanUse) {
		this.loanUse = loanUse;
	}

	public Map<String, String> getShareholder() {
		return shareholder;
	}

	public void setShareholder(Map<String, String> shareholder) {
		this.shareholder = shareholder;
	}

	public Map<String, String> getSettlement() {
		return settlement;
	}

	public void setSettlement(Map<String, String> settlement) {
		this.settlement = settlement;
	}

	public Map<String, String> getUpdowncontract() {
		return updowncontract;
	}

	public void setUpdowncontract(Map<String, String> updowncontract) {
		this.updowncontract = updowncontract;
	}

	public Map<String, String> getTelSrc() {
		return telSrc;
	}

	public void setTelSrc(Map<String, String> telSrc) {
		this.telSrc = telSrc;
	}

	public Map<String, String> getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(Map<String, String> checkResult) {
		this.checkResult = checkResult;
	}

	public Map<String, String> getSalaryTrace() {
		return salaryTrace;
	}

	public void setSalaryTrace(Map<String, String> salaryTrace) {
		this.salaryTrace = salaryTrace;
	}

	public Map<String, String> getPledge() {
		return pledge;
	}

	public void setPledge(Map<String, String> pledge) {
		this.pledge = pledge;
	}

	public Map<String, String> getHouse() {
		return house;
	}

	public void setHouse(Map<String, String> house) {
		this.house = house;
	}

	public Map<String, String> getProperty() {
		return property;
	}

	public void setProperty(Map<String, String> property) {
		this.property = property;
	}

	public String getUploadUrl() {
		return uploadUrl;
	}

	public void setUploadUrl(String uploadUrl) {
		this.uploadUrl = uploadUrl;
	}

	public List<ZlshGxht> getContractExs() {
		return contractExs;
	}

	public void setContractExs(List<ZlshGxht> contractExs) {
		this.contractExs = contractExs;
	}

	public Map<String, String> getPhoneStatue() {
		return phoneStatue;
	}

	public void setPhoneStatue(Map<String, String> phoneStatue) {
		this.phoneStatue = phoneStatue;
	}

}
