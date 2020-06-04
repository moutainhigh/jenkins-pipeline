package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;

import com.creditharmony.core.dict.entity.Dict;

/**
 * 字典封装
 * @Class Name DictList
 * @author wanglidong
 * @Create In 2016年4月13日
 */
public class AntifraudDictListEx {
	
	private List<Dict> blackGreyEList;		// 黑灰名单列表
	private String white;				// 清白件
	private String black;				// 黑名单
	private String gary;				// 灰名单
	private String back;				// 回退
	private String reportType;			// 提报类型
	
	public String getWhite() {
		return white;
	}
	public void setWhite(String white) {
		this.white = white;
	}
	public List<Dict> getBlackGreyEList() {
		return blackGreyEList;
	}
	public void setBlackGreyEList(List<Dict> blackGreyEList) {
		this.blackGreyEList = blackGreyEList;
	}
	public String getBlack() {
		return black;
	}
	public void setBlack(String black) {
		this.black = black;
	}
	public String getGary() {
		return gary;
	}
	public void setGary(String gary) {
		this.gary = gary;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}


}
