package com.creditharmony.approve.common.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 审核结果表_审核回退
 * @Class Name AuditBack
 * @author 赖敏
 * @Create In 2016年1月12日
 */
public class AuditBack extends DataEntity<AuditBack> {
	
	private static final long serialVersionUID = 1371338740525862120L;
	private String rId; 			// 关联ID(变更历史表)
	private String loanCode;		// 借款编码
	private String dictCheckType;	// 类型(信审初审，复议初审)
	private String feedBack; 		// 回馈意见
	private Object json;			// 回退清单
	private Date backStartTime;		// 开始时间
	private Date backEndtime;		// 结果时间
	
	public String getrId() {
		return rId;
	}
	
	public void setrId(String rId) {
		this.rId = rId;
	}
	
	public String getLoanCode() {
		return loanCode;
	}
	
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	
	public String getDictCheckType() {
		return dictCheckType;
	}
	
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	
	public String getFeedBack() {
		return feedBack;
	}
	
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	
	public Object getJson() {
		return json;
	}
	
	public void setJson(Object json) {
		this.json = json;
	}
	
	public Date getBackStartTime() {
		return backStartTime;
	}
	
	public void setBackStartTime(Date backStartTime) {
		this.backStartTime = backStartTime;
	}
	
	public Date getBackEndtime() {
		return backEndtime;
	}
	
	public void setBackEndtime(Date backEndtime) {
		this.backEndtime = backEndtime;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}