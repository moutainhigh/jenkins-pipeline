package com.creditharmony.approve.phone.entity;

import com.creditharmony.core.persistence.DataEntity;

public class DhzhGxhtDhxx extends DataEntity<DhzhGxhtDhxx> {

	private static final long serialVersionUID = 7502169169910431112L;
	private String rGxhtId;					// 关联id
	private String gxhtTel;					// 电话号码
	private String gxhtAssessResult;		// 评估结果
	private String gxhtRemark;				// 备注
	private String dictTelSource;			// 电话来源
	
	public String getrGxhtId() {
		return rGxhtId;
	}
	public void setrGxhtId(String rGxhtId) {
		this.rGxhtId = rGxhtId;
	}
	public String getGxhtTel() {
		return gxhtTel;
	}
	public void setGxhtTel(String gxhtTel) {
		this.gxhtTel = gxhtTel == null ? null : gxhtTel.trim();
	}
	public String getGxhtAssessResult() {
		return gxhtAssessResult;
	}
	public void setGxhtAssessResult(String gxhtAssessResult) {
		this.gxhtAssessResult = gxhtAssessResult == null ? null
				: gxhtAssessResult.trim();
	}
	public String getGxhtRemark() {
		return gxhtRemark;
	}
	public void setGxhtRemark(String gxhtRemark) {
		this.gxhtRemark = gxhtRemark == null ? null : gxhtRemark.trim();
	}
	public String getDictTelSource() {
		return dictTelSource;
	}
	public void setDictTelSource(String dictTelSource) {
		this.dictTelSource = dictTelSource;
	}
}