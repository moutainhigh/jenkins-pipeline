package com.creditharmony.approve.antifraud.entity.ex;


import com.creditharmony.core.persistence.DataEntity;

/**
 * 解除备注
 * @Class Name remarkEx
 * @author wanglidong
 * @Create In 2015年12月4日
 */
public class RemarkEx  extends DataEntity<RemarkEx> {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String remark;
	private String offendRelieveStatus;
	
	/**
	 * @return the offendRelieveStatus
	 */
	public String getOffendRelieveStatus() {
		return offendRelieveStatus;
	}
	/**
	 * @param offendRelieveStatus the String offendRelieveStatus to set
	 */
	public void setOffendRelieveStatus(String offendRelieveStatus) {
		this.offendRelieveStatus = offendRelieveStatus;
	}
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the String remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}




}

