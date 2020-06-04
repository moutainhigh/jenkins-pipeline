package com.creditharmony.approve.antifraud.entity.ex;


import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 黑灰名单扩展Entity
 * @Class Name BlackList
 * @author wanglidong
 * @Create In 2015年11月23日
 */
public class BlacklistAllEx  extends DataEntity<BlacklistAllEx> {
	
	private static final long serialVersionUID = 1L;
	private String judgeProcBy;           // 员工姓名
	private Date judgeProcDate;           // 反欺诈处理日期
	private Date judgeProcDateFrom;       // 反欺诈处理日期from
	private Date judgeProcDateTo;         // 反欺诈处理日期to
	private Date customerIntoTime;        // 进件时间
	private Date customerIntoTimeFrom;    // 进件时间from
	private Date customerIntoTimeTo;      // 进件时间to
	private String loanCode;    		  // 借款编号
	private String loanCustomerName;      // 借款人姓名
	private String dictCaseResult;        // 处理结果码值
	private String caseResult;        	  // 处理结果
	private String dictBlackType;   	  // 加黑类型
	private String blackMsg;       		  // 加黑内容	
	private String orgName;          	  // 进件门店
	private String productType;           // 产品类型
	private String loanManagercode;              // 销售人员
	private String applyId;               // applyId
	private String province;              // 进件省份
	private String dictSource;              //进件来源（0外部，1内部）
	private String judgeCaseCode;              //进件来源（0外部，1内部）
	private String blackLoanCode;              //外部加黑借款编号
	public String getBlackLoanCode() {
		return blackLoanCode;
	}
	public void setBlackLoanCode(String blackLoanCode) {
		this.blackLoanCode = blackLoanCode;
	}

	public String getJudgeCaseCode() {
		return judgeCaseCode;
	}
	
	public void setJudgeCaseCode(String judgeCaseCode) {
		this.judgeCaseCode = judgeCaseCode;
	}

	public String getLoanManagercode() {
		return loanManagercode;
	}

	public void setLoanManagercode(String loanManagercode) {
		this.loanManagercode = loanManagercode;
	}
	public String getDictSource() {
		return dictSource;
	}
	public void setDictSource(String dictSource) {
		this.dictSource = dictSource;
	}

	public String getCaseResult() {
		return caseResult;
	}

	public void setCaseResult(String caseResult) {
		this.caseResult = caseResult;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getJudgeProcBy() {
		return judgeProcBy;
	}

	public void setJudgeProcBy(String judgeProcBy) {
		this.judgeProcBy = judgeProcBy;
	}

	public Date getJudgeProcDate() {
		return judgeProcDate;
	}

	public void setJudgeProcDate(Date judgeProcDate) {
		this.judgeProcDate = judgeProcDate;
	}

	public Date getJudgeProcDateFrom() {
		return judgeProcDateFrom;
	}

	public void setJudgeProcDateFrom(Date judgeProcDateFrom) {
		this.judgeProcDateFrom = judgeProcDateFrom;
	}

	public Date getJudgeProcDateTo() {
		return judgeProcDateTo;
	}

	public void setJudgeProcDateTo(Date judgeProcDateTo) {
		this.judgeProcDateTo = judgeProcDateTo;
	}

	public Date getCustomerIntoTime() {
		return customerIntoTime;
	}

	public void setCustomerIntoTime(Date customerIntoTime) {
		this.customerIntoTime = customerIntoTime;
	}

	public Date getCustomerIntoTimeFrom() {
		return customerIntoTimeFrom;
	}

	public void setCustomerIntoTimeFrom(Date customerIntoTimeFrom) {
		this.customerIntoTimeFrom = customerIntoTimeFrom;
	}

	public Date getCustomerIntoTimeTo() {
		return customerIntoTimeTo;
	}

	public void setCustomerIntoTimeTo(Date customerIntoTimeTo) {
		this.customerIntoTimeTo = customerIntoTimeTo;
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

	public String getDictCaseResult() {
		return dictCaseResult;
	}

	public void setDictCaseResult(String dictCaseResult) {
		this.dictCaseResult = dictCaseResult;
	}

	public String getDictBlackType() {
		return dictBlackType;
	}

	public void setDictBlackType(String dictBlackType) {
		this.dictBlackType = dictBlackType;
	}

	public String getBlackMsg() {
		return blackMsg;
	}

	public void setBlackMsg(String blackMsg) {
		this.blackMsg = blackMsg;
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
	
}

