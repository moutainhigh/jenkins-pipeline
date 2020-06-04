package com.creditharmony.approve.internet.entity.ex;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.creditharmony.approve.internet.entity.PrivateNetworkCheck;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.ex.TelAuditWorkEx;

/**
 * 外网审核ex
 * 
 * @Class Name OutSideCheckEx
 * @author 刘燕军
 * @Create In 2015年12月8日
 * @update in 2016-09-27
 */
public class OutSideCheckEx implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<TelAuditWorkEx> telAuditWorks; // 外网审核公司信息
	private List<DhzhDhlyxx> dhzhDhlyxxs; // 外网审核电话录音信息
	private List<OutsideNetCheckEx> outNet; // 一键网查信息
	private List<PrivateNetworkCheck> netWorks; // 网查信息
	private Map<String, String> privateType; // 专网查询的类型
	private Map<String, String> telSource; // 专网查询的类型
	private List<OutSideCheckConEx> phoneChecks;// 一键网查信息-电话核实信息

	/**
	 * 一键网查信息-电话核实信息
	 * @return
	 */
	public List<OutSideCheckConEx> getPhoneChecks() {
		return phoneChecks;
	}

	/**
	 * 一键网查信息-电话核实信息
	 * @param phoneChecks
	 */
	public void setPhoneChecks(List<OutSideCheckConEx> phoneChecks) {
		this.phoneChecks = phoneChecks;
	}

	public List<TelAuditWorkEx> getTelAuditWorks() {
		return telAuditWorks;
	}

	public void setTelAuditWorks(List<TelAuditWorkEx> telAuditWorks) {
		this.telAuditWorks = telAuditWorks;
	}

	public List<OutsideNetCheckEx> getOutNet() {
		return outNet;
	}

	public void setOutNet(List<OutsideNetCheckEx> outNet) {
		this.outNet = outNet;
	}

	public List<PrivateNetworkCheck> getNetWorks() {
		return netWorks;
	}

	public void setNetWorks(List<PrivateNetworkCheck> netWorks) {
		this.netWorks = netWorks;
	}

	public List<DhzhDhlyxx> getDhzhDhlyxxs() {
		return dhzhDhlyxxs;
	}

	public void setDhzhDhlyxxs(List<DhzhDhlyxx> dhzhDhlyxxs) {
		this.dhzhDhlyxxs = dhzhDhlyxxs;
	}

	public Map<String, String> getPrivateType() {
		return privateType;
	}

	public void setPrivateType(Map<String, String> privateType) {
		this.privateType = privateType;
	}

	public Map<String, String> getTelSource() {
		return telSource;
	}

	public void setTelSource(Map<String, String> telSource) {
		this.telSource = telSource;
	}




	

}
