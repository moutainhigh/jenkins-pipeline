package com.creditharmony.approve.antifraud.entity.ex;


/**
 * 反欺诈判定内部加黑/灰json
 * @Class Name AntiFraudJudge
 * @author wanglidong
 * @Create In 2015年11月26日
 */
public class AntiFraudJsonListEx{
	
	private String checkId;				// 复选框序号
	private String checkStatus;			// 复选框状态
	
	public String getCheckId() {
		return checkId;
	}
	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

}
