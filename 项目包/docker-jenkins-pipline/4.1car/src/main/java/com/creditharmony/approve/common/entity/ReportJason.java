package com.creditharmony.approve.common.entity;

/**
 * 征信报告复选框操作
 * @Class Name ReportJason
 * @author 李文勇
 * @Create In 2015年12月23日
 */
public class ReportJason {

	private String checkBox; // 复选框编号（ID）
	private String sysChooseFlag; // 系统复选框选中状态（1：选中		0：未选中）
	private String userChooseFlag; // 人工复选框选中状态（1：选中	0:未选中）
	
	public String getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}
	public String getSysChooseFlag() {
		return sysChooseFlag;
	}
	public void setSysChooseFlag(String sysChooseFlag) {
		this.sysChooseFlag = sysChooseFlag;
	}
	public String getUserChooseFlag() {
		return userChooseFlag;
	}
	public void setUserChooseFlag(String userChooseFlag) {
		this.userChooseFlag = userChooseFlag;
	}
}
