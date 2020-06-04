package com.creditharmony.approve.internet.entity.ex;

import java.io.Serializable;
import java.util.List;

/**
 * 外网审核公司名专网查询
 * @Class Name WorkNameEx
 * @author 刘燕军
 * @Create In 2016年1月6日
 */
public class WorkNameEx implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	List<OutsideNetInfoEx> nameCheck;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<OutsideNetInfoEx> getNameCheck() {
		return nameCheck;
	}
	public void setNameCheck(List<OutsideNetInfoEx> nameCheck) {
		this.nameCheck = nameCheck;
	}
	
	
}
