package com.creditharmony.approve.internet.entity.ex;

import java.io.Serializable;
import java.util.List;

/**
 * 电话类
 * 
 * @author 罗俊平
 * @create in 2016-09-27
 */
public class OutSideCheckConParamEx implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<OutSideCheckConEx> phoneChecks;// 一键网查信息-电话核实信息

	/**
	 * 一键网查信息-电话核实信息
	 * 
	 * @return
	 */
	public List<OutSideCheckConEx> getPhoneChecks() {
		return phoneChecks;
	}

	/**
	 * 一键网查信息-电话核实信息
	 * 
	 * @param phoneChecks
	 */
	public void setPhoneChecks(List<OutSideCheckConEx> phoneChecks) {
		this.phoneChecks = phoneChecks;
	}

}
