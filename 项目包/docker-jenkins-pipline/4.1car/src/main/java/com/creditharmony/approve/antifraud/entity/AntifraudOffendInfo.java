package com.creditharmony.approve.antifraud.entity;


import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 反欺诈触犯规则信息
 * @Class Name AntifraudOffendInfo
 * @author wanglidong
 * @Create In 2015年12月2日
 */
public class AntifraudOffendInfo extends DataEntity<AntifraudOffendInfo>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String rOffendId;

    private String loanCode;

    private String rulesCode;

    private String dictOffendType;

    private String offendMsg;

    private String offendStatus;

    private String offendRelieveStatus;

    private String offendRemark;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getId() {
        return id;
    }

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

	public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getRulesCode() {
        return rulesCode;
    }

    public void setRulesCode(String rulesCode) {
        this.rulesCode = rulesCode == null ? null : rulesCode.trim();
    }

    public String getDictOffendType() {
        return dictOffendType;
    }

    public void setDictOffendType(String dictOffendType) {
        this.dictOffendType = dictOffendType == null ? null : dictOffendType.trim();
    }

    public String getOffendMsg() {
        return offendMsg;
    }

    public void setOffendMsg(String offendMsg) {
        this.offendMsg = offendMsg == null ? null : offendMsg.trim();
    }

    public String getOffendStatus() {
        return offendStatus;
    }

    public void setOffendStatus(String offendStatus) {
        this.offendStatus = offendStatus == null ? null : offendStatus.trim();
    }

    public String getOffendRelieveStatus() {
        return offendRelieveStatus;
    }

    public void setOffendRelieveStatus(String offendRelieveStatus) {
        this.offendRelieveStatus = offendRelieveStatus == null ? null : offendRelieveStatus.trim();
    }

    public String getOffendRemark() {
        return offendRemark;
    }

    public void setOffendRemark(String offendRemark) {
        this.offendRemark = offendRemark == null ? null : offendRemark.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}