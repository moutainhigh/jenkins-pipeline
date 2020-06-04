package com.creditharmony.approve.antifraud.entity.ex;


import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 反欺诈匹配规则扩展entity
 * @Class Name AntifraudOffendInfoEx
 * @author wanglidong
 * @Create In 2015年12月1日
 */
public class AntifraudOffendInfoEx extends DataEntity<AntifraudOffendInfoEx>{

	private static final long serialVersionUID = 1L;
	private String id;
    private String loanCode;					// 借款编号
    private String rulesCode; 					// 规则编号
    private String dictOffendType; 			    // 触犯类型
    private String offendMsg; 					// 触犯内容
    private String offendStatus; 				// 办理状态
    private String offendRelieveStatus; 		// 解除状态
    private String offendRemark; 				// 解除规则理由
    private String createBy;					// 创建人
    private Date createTime;					// 创建时间
    private String modifyBy;					// 修改人
    private Date modifyTime;					// 修改时间
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
	 * @return the rulesCode
	 */
	public String getRulesCode() {
		return rulesCode;
	}
	/**
	 * @param rulesCode the String rulesCode to set
	 */
	public void setRulesCode(String rulesCode) {
		this.rulesCode = rulesCode;
	}
	/**
	 * @return the dictOffendType
	 */
	public String getDictOffendType() {
		return dictOffendType;
	}
	/**
	 * @param dictOffendType the String dictOffendType to set
	 */
	public void setDictOffendType(String dictOffendType) {
		this.dictOffendType = dictOffendType;
	}
	/**
	 * @return the offendMsg
	 */
	public String getOffendMsg() {
		return offendMsg;
	}
	/**
	 * @param offendMsg the String offendMsg to set
	 */
	public void setOffendMsg(String offendMsg) {
		this.offendMsg = offendMsg;
	}
	/**
	 * @return the offendStatus
	 */
	public String getOffendStatus() {
		return offendStatus;
	}
	/**
	 * @param offendStatus the String offendStatus to set
	 */
	public void setOffendStatus(String offendStatus) {
		this.offendStatus = offendStatus;
	}
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
	 * @return the offendRemark
	 */
	public String getOffendRemark() {
		return offendRemark;
	}
	/**
	 * @param offendRemark the String offendRemark to set
	 */
	public void setOffendRemark(String offendRemark) {
		this.offendRemark = offendRemark;
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