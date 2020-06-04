package com.creditharmony.approve.antifraud.entity;

import java.util.Date;
/**
 * 反欺诈欺诈案件
 * @Class Name AntifraudCase
 * @author wanglidong
 * @Create In 2015年12月2日
 */
public class AntifraudCase {
    private String id;

    private String rJudgeId;

    private String caseCode;
    
    private String loanCode;

    private Date caseHandleDay;

    private String caseHandleBy;

    private String loanCustomerName;

    private String dictAntifraudType;

    private String caseRiskMsg;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the String id to set
	 */
	public void setId(String id) {
		this.id = id;
	}





	/**
	 * @return the caseCode
	 */
	public String getCaseCode() {
		return caseCode;
	}

	/**
	 * @param caseCode the String caseCode to set
	 */
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	/**
	 * @return the loanCode
	 */
	public String getLoanCode() {
		return loanCode;
	}

	/**
	 * @param loanCode the String loanCode to set
	 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}



	/**
	 * @return the rJudgeId
	 */
	public String getrJudgeId() {
		return rJudgeId;
	}

	/**
	 * @param rJudgeId the String rJudgeId to set
	 */
	public void setrJudgeId(String rJudgeId) {
		this.rJudgeId = rJudgeId;
	}

	/**
	 * @return the caseHandleDay
	 */
	public Date getCaseHandleDay() {
		return caseHandleDay;
	}

	/**
	 * @param caseHandleDay the Date caseHandleDay to set
	 */
	public void setCaseHandleDay(Date caseHandleDay) {
		this.caseHandleDay = caseHandleDay;
	}

	/**
	 * @return the caseHandleBy
	 */
	public String getCaseHandleBy() {
		return caseHandleBy;
	}

	/**
	 * @param caseHandleBy the String caseHandleBy to set
	 */
	public void setCaseHandleBy(String caseHandleBy) {
		this.caseHandleBy = caseHandleBy;
	}

	/**
	 * @return the loanCustomerName
	 */
	public String getLoanCustomerName() {
		return loanCustomerName;
	}

	/**
	 * @param loanCustomerName the String loanCustomerName to set
	 */
	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName;
	}

	/**
	 * @return the dictAntifraudType
	 */
	public String getDictAntifraudType() {
		return dictAntifraudType;
	}

	/**
	 * @param dictAntifraudType the String dictAntifraudType to set
	 */
	public void setDictAntifraudType(String dictAntifraudType) {
		this.dictAntifraudType = dictAntifraudType;
	}

	/**
	 * @return the caseRiskMsg
	 */
	public String getCaseRiskMsg() {
		return caseRiskMsg;
	}

	/**
	 * @param caseRiskMsg the String caseRiskMsg to set
	 */
	public void setCaseRiskMsg(String caseRiskMsg) {
		this.caseRiskMsg = caseRiskMsg;
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the String createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the Date createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the modifyBy
	 */
	public String getModifyBy() {
		return modifyBy;
	}

	/**
	 * @param modifyBy the String modifyBy to set
	 */
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	/**
	 * @return the modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * @param modifyTime the Date modifyTime to set
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

 
}