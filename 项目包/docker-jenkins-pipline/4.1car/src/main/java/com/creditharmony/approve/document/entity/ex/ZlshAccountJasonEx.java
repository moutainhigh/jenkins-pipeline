package com.creditharmony.approve.document.entity.ex;

/**
 * 资料审核页面-个人/对公流水存取对象类
 * @Class Name ZlshAccountJasonEx
 * @author 李文勇
 * @Create In 2016年3月27日
 */
public class ZlshAccountJasonEx {

	private String checkBox;		// 个人/对公流水复选框编号
	private String isChoose;		// 复选框选中状态（1：选中  0：未选中）
	private String inputMoney;		// 录入的金额
	private String inputMonth;		// 录入的月份
	public String getCheckBox() {
		return checkBox;
	}
	public void setCheckBox(String checkBox) {
		this.checkBox = checkBox;
	}
	public String getIsChoose() {
		return isChoose;
	}
	public void setIsChoose(String isChoose) {
		this.isChoose = isChoose;
	}
	public String getInputMoney() {
		return inputMoney;
	}
	public void setInputMoney(String inputMoney) {
		this.inputMoney = inputMoney;
	}
	public String getInputMonth() {
		return inputMonth;
	}
	public void setInputMonth(String inputMonth) {
		this.inputMonth = inputMonth;
	}
}
