package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 决策页面 外部审核信息扩展类
 * 
 * @Class Name WorkInofCheckEx
 * @author 刘燕军
 * @Create In 2015年12月22日
 */
public class WorkInofCheckEx extends DataEntity<WorkInofCheckEx> {

	private static final long serialVersionUID = 1L;
	private String checkType;
	private String checkObject;
	private String checkContent;
	private String checkResult;

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public String getCheckObject() {
		return checkObject;
	}

	public void setCheckObject(String checkObject) {
		this.checkObject = checkObject;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

}
