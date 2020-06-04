package com.creditharmony.approve.verify.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 审核结果子表_拒绝信息
 * @Class Name AuditResultSublist
 * @author 赖敏
 * @Create In 2016年1月12日
 */
public class AuditResultSublist extends DataEntity<AuditResultSublist>{
	
	private static final long serialVersionUID = 1L;
    private String rAuditId;			// 关联ID(审核结果表)
    private String refuseFirstCode;		// 一级拒绝码
    private String refuseSecondCode;	// 二级拒绝码
    private String refuseThirdCode;		// 三级拒绝码

	public String getRAuditId() {
		return rAuditId;
	}

	public void setRAuditId(String rAuditId) {
		this.rAuditId = rAuditId;
	}

	public String getRefuseFirstCode() {
		return refuseFirstCode;
	}

	public void setRefuseFirstCode(String refuseFirstCode) {
		this.refuseFirstCode = refuseFirstCode;
	}

	public String getRefuseSecondCode() {
		return refuseSecondCode;
	}

	public void setRefuseSecondCode(String refuseSecondCode) {
		this.refuseSecondCode = refuseSecondCode;
	}

	public String getRefuseThirdCode() {
		return refuseThirdCode;
	}

	public void setRefuseThirdCode(String refuseThirdCode) {
		this.refuseThirdCode = refuseThirdCode;
	}


   
   

}