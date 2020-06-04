package com.creditharmony.approve.internet.entity.ex;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 决策页面外部审核信息
 * @Class Name OutNetEx
 * @author 刘燕军
 * @Create In 2016年2月18日
 */
public class OutNetEx extends DataEntity<OutNetEx>{

	private static final long serialVersionUID = 1L;

	private String type; // 核查类型
	private String obj; // 核查对象
	private String content; // 核查内容
	private String result; // 核查结果
	private String people; // 主借人/共借人
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	
}
