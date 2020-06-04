package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.core.persistence.DataEntity;

public class CheckTelephoneResultEx extends DataEntity<CheckTelephoneResultEx> {
	private static final long serialVersionUID = 1L;
	private String number;// 号码
	private String category;// 类别
	private String relation;// 关系
	private String name;// 名字
	private String result;// 结果
	private String message;// 描述

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
