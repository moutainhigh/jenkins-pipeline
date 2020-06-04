package com.creditharmony.approve.antifraud.entity;

import java.util.Date;
/**
 * 反欺诈查重entity
 * @Class Name AntifraudRepeat
 * @author wanglidong
 * @Create In 2015年12月1日
 */
public class AntifraudRepeat {
    private String id;

	private String rulesCode;			//规则编号
    private String loanCodeNow;		    //当前借款编码
    private String loanCode;			//借款编码
    private String customerName;		//客户姓名
    private Date repeatIntoTime;		//进件时间
    private String repeatResult;		//处理结果
    private String repeatResultMsg;		//信息内容
    private String repeatRelation;		//与本次借款人关系
    private String createBy;			//创建人   
    private Date createTime;			//创建时间
    private String modifyBy;			//最后修改人
    private Date modifyTime;			//最后修改时间
    private String repeatCertNum;		//证件号码
    private String repeatNameDiffFlag;	//姓名不同标识
    private String toAfraudFlag;		//是否反欺诈判定标志
    
	public String getToAfraudFlag() {
		return toAfraudFlag;
	}
	public void setToAfraudFlag(String toAfraudFlag) {
		this.toAfraudFlag = toAfraudFlag;
	}
	public String getRepeatCertNum() {
		return repeatCertNum;
	}
	public void setRepeatCertNum(String repeatCertNum) {
		this.repeatCertNum = repeatCertNum;
	}
	public String getRepeatNameDiffFlag() {
		return repeatNameDiffFlag;
	}
	public void setRepeatNameDiffFlag(String repeatNameDiffFlag) {
		this.repeatNameDiffFlag = repeatNameDiffFlag;
	}
	public String getRulesCode() {
		return rulesCode;
	}
	public void setRulesCode(String rulesCode) {
		this.rulesCode = rulesCode;
	}
	public String getLoanCodeNow() {
		return loanCodeNow;
	}
	public void setLoanCodeNow(String loanCodeNow) {
		this.loanCodeNow = loanCodeNow;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public Date getRepeatIntoTime() {
		return repeatIntoTime;
	}
	public void setRepeatIntoTime(Date repeatIntoTime) {
		this.repeatIntoTime = repeatIntoTime;
	}
	public String getRepeatResult() {
		return repeatResult;
	}
	public void setRepeatResult(String repeatResult) {
		this.repeatResult = repeatResult;
	}
	public String getRepeatResultMsg() {
		return repeatResultMsg;
	}
	public void setRepeatResultMsg(String repeatResultMsg) {
		this.repeatResultMsg = repeatResultMsg;
	}
	public String getRepeatRelation() {
		return repeatRelation;
	}
	public void setRepeatRelation(String repeatRelation) {
		this.repeatRelation = repeatRelation;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

 
}