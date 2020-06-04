package com.creditharmony.approve.common.entity;


import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name BackConsult
 * @author wanglidong
 * @Create In 2015年12月31日
 */
public class BackConsult extends DataEntity<BackConsult> {

	private static final long serialVersionUID = 1L;
	private String id;
    private String rHisId;
    private String loanCode;
    private String backCode;
    private String backCode2;
    private String backCode3;
    private String backConsult;
    private String backRemark;
    private String dictCheckType;
    private String stepname;
    private Date backStartTime;
    private Date backEndTime;


	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getrHisId() {
        return rHisId;
    }

    public void setrHisId(String rHisId) {
        this.rHisId = rHisId == null ? null : rHisId.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getBackCode() {
        return backCode;
    }

    public void setBackCode(String backCode) {
        this.backCode = backCode == null ? null : backCode.trim();
    }

    public String getBackCode2() {
        return backCode2;
    }

    public void setBackCode2(String backCode2) {
        this.backCode2 = backCode2 == null ? null : backCode2.trim();
    }

    public String getBackCode3() {
        return backCode3;
    }

    public void setBackCode3(String backCode3) {
        this.backCode3 = backCode3 == null ? null : backCode3.trim();
    }

    public String getBackConsult() {
        return backConsult;
    }

    public void setBackConsult(String backConsult) {
        this.backConsult = backConsult == null ? null : backConsult.trim();
    }

    public String getBackRemark() {
        return backRemark;
    }

    public void setBackRemark(String backRemark) {
        this.backRemark = backRemark == null ? null : backRemark.trim();
    }

    public String getDictCheckType() {
        return dictCheckType;
    }

    public void setDictCheckType(String dictCheckType) {
        this.dictCheckType = dictCheckType == null ? null : dictCheckType.trim();
    }

    public Date getBackStartTime() {
        return backStartTime;
    }

    public void setBackStartTime(Date backStartTime) {
        this.backStartTime = backStartTime;
    }

    public Date getBackEndTime() {
        return backEndTime;
    }

    public void setBackEndTime(Date backEndTime) {
        this.backEndTime = backEndTime;
    }

/*    public Date getCreateTime() {
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
    }*/
}