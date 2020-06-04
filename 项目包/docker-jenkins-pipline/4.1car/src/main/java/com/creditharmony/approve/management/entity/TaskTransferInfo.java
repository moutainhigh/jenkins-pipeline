package com.creditharmony.approve.management.entity;


/**
 * 审批单子转签
 * 
 * @Class Name TaskTransferInfo
 * @author 王浩
 * @Create In 2015年12月15日
 */
public class TaskTransferInfo {
	// 原处理用户id
	private String origin;
	// 原处理用户姓名
	private String originName;
	// 转签后用户id
	private String acceptUID;
	// 需转签数据
	private String[] wobNum;

	public String getAcceptUID() {
		return acceptUID;
	}

	public void setAcceptUID(String acceptUID) {
		this.acceptUID = acceptUID;
	}

	public String[] getWobNum() {
		return wobNum;
	}

	public void setWobNum(String[] wobNum) {
		this.wobNum = wobNum;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}
	
}
