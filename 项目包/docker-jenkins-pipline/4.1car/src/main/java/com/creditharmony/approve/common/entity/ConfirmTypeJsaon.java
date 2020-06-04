package com.creditharmony.approve.common.entity;

public class ConfirmTypeJsaon {

	private String checkBox;		// 复选框name
	private String label;			// 复选框label
	private String isChoose;		// 是否选中 1：选中；    0未选中；
	public String getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getIsChoose() {
		return isChoose;
	}
	public void setIsChoose(String isChoose) {
		this.isChoose = isChoose;
	}
}
