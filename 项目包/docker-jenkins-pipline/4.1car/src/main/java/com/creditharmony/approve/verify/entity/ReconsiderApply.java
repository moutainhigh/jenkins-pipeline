package com.creditharmony.approve.verify.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 复议申请
 * @Class Name ReconsiderApply
 * @author 刘燕军
 * @Create In 2015年12月25日
 */
public class ReconsiderApply extends DataEntity<ReconsiderApply>{
	private static final long serialVersionUID = 1L;

	private String loanCode; 			// 借款编号
	private String dictReconsiderType; 	// 复议类型
	private String secondReconsiderMsg; // 复议原因
	private String applyBy; 			// 发起人
	private String orgCode; 			// 发起机构
	private String dictCheckStatus; 	// 审核状态
	private String outsideFlag; 		// 外访标识
	private String applyId; 			// 流程ID
	
	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode == null ? null : loanCode.trim();
	}

	public String getDictReconsiderType() {
		return dictReconsiderType;
	}

	public void setDictReconsiderType(String dictReconsiderType) {
		this.dictReconsiderType = dictReconsiderType == null ? null
				: dictReconsiderType.trim();
	}

	public String getSecondReconsiderMsg() {
		return secondReconsiderMsg;
	}

	public void setSecondReconsiderMsg(String secondReconsiderMsg) {
		this.secondReconsiderMsg = secondReconsiderMsg == null ? null
				: secondReconsiderMsg.trim();
	}

	public String getApplyBy() {
		return applyBy;
	}

	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy == null ? null : applyBy.trim();
	}


	public String getDictCheckStatus() {
		return dictCheckStatus;
	}

	public void setDictCheckStatus(String dictCheckStatus) {
		this.dictCheckStatus = dictCheckStatus == null ? null : dictCheckStatus
				.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy == null ? null : modifyBy.trim();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOutsideFlag() {
		return outsideFlag;
	}
	
	public void setOutsideFlag(String outsideFlag) {
		this.outsideFlag = outsideFlag;
	}
	
	public String getApplyId() {
		return applyId;
	}
	
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
}