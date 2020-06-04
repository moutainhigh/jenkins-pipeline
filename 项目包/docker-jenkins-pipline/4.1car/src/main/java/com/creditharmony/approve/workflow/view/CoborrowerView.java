package com.creditharmony.approve.workflow.view;

import java.io.Serializable;
/**
 * 共借人信息
 * @Class Name CustomerInfoView
 * @author xiaoniu.hu
 * @Create In 2015年12月19日
 */
public class CoborrowerView implements Serializable{
	private static final long serialVersionUID = 4536110667218033455L;
	
	private String name; // 客户姓名
	private String no;   // 客户编号
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
}
