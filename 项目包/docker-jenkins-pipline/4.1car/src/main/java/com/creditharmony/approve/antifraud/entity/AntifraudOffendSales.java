package com.creditharmony.approve.antifraud.entity;

import java.util.Date;
/**
 * 销售人员匹配信息entity
 * @Class Name AntifraudOffendSales
 * @author wanglidong
 * @Create In 2015年12月2日
 */
public class AntifraudOffendSales {
    private String id;

    private String rOffendId;				// 关联ID（触犯规则表）

    private String loanCode;		// 借款编码

    private String offendSalesName; // 销售人员

    private String offendTel;		// 手机号

    private String offendName;		// 查重匹配姓名

    private String workFlag;	// 是否在职

    private String dictJobGrade;	// 职位等级

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
	 * @return the rOffendId
	 */
	public String getrOffendId() {
		return rOffendId;
	}

	/**
	 * @param rOffendId the String rOffendId to set
	 */
	public void setrOffendId(String rOffendId) {
		this.rOffendId = rOffendId;
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
	 * @return the offendSalesName
	 */
	public String getOffendSalesName() {
		return offendSalesName;
	}

	/**
	 * @param offendSalesName the String offendSalesName to set
	 */
	public void setOffendSalesName(String offendSalesName) {
		this.offendSalesName = offendSalesName;
	}

	/**
	 * @return the offendTel
	 */
	public String getOffendTel() {
		return offendTel;
	}

	/**
	 * @param offendTel the String offendTel to set
	 */
	public void setOffendTel(String offendTel) {
		this.offendTel = offendTel;
	}

	/**
	 * @return the offendName
	 */
	public String getOffendName() {
		return offendName;
	}

	/**
	 * @param offendName the String offendName to set
	 */
	public void setOffendName(String offendName) {
		this.offendName = offendName;
	}





	/**
	 * @return the workFlag
	 */
	public String getWorkFlag() {
		return workFlag;
	}

	/**
	 * @param workFlag the String workFlag to set
	 */
	public void setWorkFlag(String workFlag) {
		this.workFlag = workFlag;
	}

	/**
	 * @return the dictJobGrade
	 */
	public String getDictJobGrade() {
		return dictJobGrade;
	}

	/**
	 * @param dictJobGrade the String dictJobGrade to set
	 */
	public void setDictJobGrade(String dictJobGrade) {
		this.dictJobGrade = dictJobGrade;
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