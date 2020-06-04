package com.creditharmony.approve.verify.entity;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 下载意见书  备注
 * @Class Name proposalRemarks
 * @author 刘燕军
 * @Create In 2015年12月17日
 */
public class ProposalRemarks extends DataEntity<ProposalRemarks> {
	private static final long serialVersionUID = 1L;
	private String loanCode; // 借款编号
	private String companyInfo; // 企业情况
	private String traceInfo; // 流水情况
	private String liabilitiesInfo; // 负债情况
	private String assetsInfo; // 资产情况
	private String visitInfo; // 外访情况
	private String riskInfo; // 风险点说明
	private String dictLoanStatus; // 审核状态

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public String getTraceInfo() {
		return traceInfo;
	}

	public void setTraceInfo(String traceInfo) {
		this.traceInfo = traceInfo;
	}

	public String getLiabilitiesInfo() {
		return liabilitiesInfo;
	}

	public void setLiabilitiesInfo(String liabilitiesInfo) {
		this.liabilitiesInfo = liabilitiesInfo;
	}

	public String getAssetsInfo() {
		return assetsInfo;
	}

	public void setAssetsInfo(String assetsInfo) {
		this.assetsInfo = assetsInfo;
	}

	public String getVisitInfo() {
		return visitInfo;
	}

	public void setVisitInfo(String visitInfo) {
		this.visitInfo = visitInfo;
	}

	public String getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(String riskInfo) {
		this.riskInfo = riskInfo;
	}

	public String getDictLoanStatus() {
		return dictLoanStatus;
	}

	public void setDictLoanStatus(String dictLoanStatus) {
		this.dictLoanStatus = dictLoanStatus;
	}

}
