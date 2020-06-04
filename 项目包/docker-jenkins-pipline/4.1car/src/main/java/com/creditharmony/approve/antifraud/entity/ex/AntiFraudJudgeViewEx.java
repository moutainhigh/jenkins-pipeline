package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;

import com.creditharmony.approve.antifraud.entity.AntifraudJudge;

/**
 * 反欺诈判定扩展Entity
 * @Class Name AntiFraudJudge
 * @author wanglidong
 * @Create In 2015年11月26日
 */
public class AntiFraudJudgeViewEx extends AntifraudJudge{
	
	private static final long serialVersionUID = 1L;
	private List<AntiFraudJsonListEx> jsonList; // 内部加黑灰json
	private String detailJudgeRiskMsg; // 详细风险说明


	public String getDetailJudgeRiskMsg() {
		return detailJudgeRiskMsg;
	}

	public void setDetailJudgeRiskMsg(String detailJudgeRiskMsg) {
		this.detailJudgeRiskMsg = detailJudgeRiskMsg;
	}

	public List<AntiFraudJsonListEx> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<AntiFraudJsonListEx> jsonList) {
		this.jsonList = jsonList;
	}

}
