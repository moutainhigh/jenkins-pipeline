package com.creditharmony.approve.common.entity;

import java.util.List;

/**
 * 存放征信报告复选框的实体
 * @Class Name ReportJason
 * @author 李文勇
 * @Create In 2015年12月12日
 */
public class CreditJson {

	private String initFlag; // 是否为第一次初始化的标志（0：显示系统勾选 	1：显示人工勾选）
	private List<ReportJason> reportJason; // 复选框集合
	
	public String getInitFlag() {
		return initFlag;
	}
	public void setInitFlag(String initFlag) {
		this.initFlag = initFlag;
	}
	public List<ReportJason> getReportJason() {
		return reportJason;
	}
	public void setReportJason(List<ReportJason> reportJason) {
		this.reportJason = reportJason;
	}
}
