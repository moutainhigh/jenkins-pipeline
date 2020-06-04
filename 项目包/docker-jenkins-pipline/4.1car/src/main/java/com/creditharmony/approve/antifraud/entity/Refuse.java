package com.creditharmony.approve.antifraud.entity;

import java.util.Date;
/**
 * 拒借码entity
 * @Class Name Refuse
 * @author wanglidong
 * @Create In 2015年12月16日
 */
public class Refuse {
    private String id;
    private String refuseCode;
    private String refuseName;
    private String refuseGrade;
    private String parentId;
    private String deleteFlag;
    private String createBy;
    private Date createTime;
    private String modifyBy;
    private Date modifyTime;
    
    public String getId() {
        return id;
    }
	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRefuseCode() {
        return refuseCode;
    }

    public void setRefuseCode(String refuseCode) {
        this.refuseCode = refuseCode == null ? null : refuseCode.trim();
    }

    public String getRefuseName() {
        return refuseName;
    }

    public void setRefuseName(String refuseName) {
        this.refuseName = refuseName == null ? null : refuseName.trim();
    }

    public String getRefuseGrade() {
        return refuseGrade;
    }

    public void setRefuseGrade(String refuseGrade) {
        this.refuseGrade = refuseGrade == null ? null : refuseGrade.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
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