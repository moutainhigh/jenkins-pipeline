package com.creditharmony.approve.verify.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 企业征信报告字表
 * 
 * @Class Name EnterpriceLoanType
 * @author 刘燕军
 * @Create In 2015年12月25日
 */
public class EnterpriceLoanType extends DataEntity<EnterpriceLoanType> {
	private static final long serialVersionUID = 1L;
	private String loanCode; // 借款编码
	private String enterpriceLoanId; // 企业征信贷款信息ID
	private String loanType; // 贷款种类
	private String loanCount; // 贷款笔数
	private String loanBalance; // 贷款余额（万元）
	private String createOrg; // 审核人员机构2
	private String lastModifyBy; // 修改人员编号2
	private String lastModifyOrg; // 修改人员机构2
	private String lastModifyTime; // 修改时间2

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public String getEnterpriceLoanId() {
		return enterpriceLoanId;
	}

	public void setEnterpriceLoanId(String enterpriceLoanId) {
		this.enterpriceLoanId = enterpriceLoanId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getLoanCount() {
		return loanCount;
	}

	public void setLoanCount(String loanCount) {
		this.loanCount = loanCount;
	}

	public String getLoanBalance() {
		return loanBalance;
	}

	public void setLoanBalance(String loanBalance) {
		this.loanBalance = loanBalance;
	}

	public String getCreateOrg() {
		return createOrg;
	}

	public void setCreateOrg(String createOrg) {
		this.createOrg = createOrg;
	}

	public String getLastModifyBy() {
		return lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	public String getLastModifyOrg() {
		return lastModifyOrg;
	}

	public void setLastModifyOrg(String lastModifyOrg) {
		this.lastModifyOrg = lastModifyOrg;
	}

	public String getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(String lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

}
