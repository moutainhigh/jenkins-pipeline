package com.creditharmony.approve.verify.entity.ex;

import java.util.List;

import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.approve.verify.entity.AuditResult;

/**
 * 下载意见书 其他情况
 * @Class Name AuditRatingEx
 * @author 刘燕军
 * @Create In 2015年12月8日
 */
public class AuditRatingEx extends  AuditRatingResult{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AuditResult> auditResult; // 借款编号对应的所有的审核结果 

	public List<AuditResult> getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(List<AuditResult> auditResult) {
		this.auditResult = auditResult;
	}

}
