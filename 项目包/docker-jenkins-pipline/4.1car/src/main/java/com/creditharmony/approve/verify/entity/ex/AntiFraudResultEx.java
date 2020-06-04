package com.creditharmony.approve.verify.entity.ex;

import com.creditharmony.approve.antifraud.entity.AntifraudReport;

/**
 * 反欺诈反馈信息实体
 * 
 * @Class Name AntiFraudResultEx
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
public class AntiFraudResultEx extends AntifraudReport {

	private static final long serialVersionUID = 1L;
	private String dictCaseResult; // 处理结果
	private String judgeRiskMsg; // 处理意见
	private String reportFrom;// 提报来源

	public String getDictCaseResult() {
		return dictCaseResult;
	}

	public void setDictCaseResult(String dictCaseResult) {
		this.dictCaseResult = dictCaseResult;
	}

	public String getJudgeRiskMsg() {
		return judgeRiskMsg;
	}

	public void setJudgeRiskMsg(String judgeRiskMsg) {
		this.judgeRiskMsg = judgeRiskMsg;
	}

	public String getReportFrom() {
		return reportFrom;
	}

	public void setReportFrom(String reportFrom) {
		this.reportFrom = reportFrom;
	}

}
