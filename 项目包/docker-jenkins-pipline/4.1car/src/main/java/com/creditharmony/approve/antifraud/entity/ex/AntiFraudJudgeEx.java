package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;

import com.creditharmony.approve.antifraud.entity.AntifraudJudge;
import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.antifraud.entity.BacklistAll;
import com.creditharmony.approve.antifraud.entity.Refuse;

/**
 * 反欺诈判定扩展Entity
 * @Class Name AntiFraudJudge
 * @author wanglidong
 * @Create In 2015年11月26日
 */
public class AntiFraudJudgeEx extends AntifraudJudge{
	
	private static final long serialVersionUID = 1L;
	private String twoBlack;										// 黑名单二级决策项
	private String twoGray;											// 灰名单二级决策项
	private String threeBlack;										// 黑名单三级决策项
	private String loanStatus;										// 借款状态
	private AntiFraudJudgeOptionEx antiFraudJudgeOptionEx;			// 主借人 内部加黑灰项
	private List<CoborrowerOptionEx> CoborrowerOptionList;			// 共借人内部加黑灰项
	private List<BacklistAll> blackListAll;							// 外部拉黑项
	private AntifraudReport antifraudReport;						// 提报反欺诈信息
	private AntifraudDictListEx antifraudDict;						// 反欺诈决策字典表数据
	private List<Refuse> twoGrayRefuseCode;							// 一级据借码
	private List<Refuse> twoBlackRefuseCode;							// 二级据借码
	private List<Refuse> threeBlackRefuseCode;							// 三级据借码
	
	public List<Refuse> getTwoGrayRefuseCode() {
		return twoGrayRefuseCode;
	}
	public void setTwoGrayRefuseCode(List<Refuse> twoGrayRefuseCode) {
		this.twoGrayRefuseCode = twoGrayRefuseCode;
	}
	public List<Refuse> getTwoBlackRefuseCode() {
		return twoBlackRefuseCode;
	}
	public void setTwoBlackRefuseCode(List<Refuse> twoBlackRefuseCode) {
		this.twoBlackRefuseCode = twoBlackRefuseCode;
	}
	public List<Refuse> getThreeBlackRefuseCode() {
		return threeBlackRefuseCode;
	}
	public void setThreeBlackRefuseCode(List<Refuse> threeBlackRefuseCode) {
		this.threeBlackRefuseCode = threeBlackRefuseCode;
	}
	public String getTwoBlack() {
		return twoBlack;
	}
	public void setTwoBlack(String twoBlack) {
		this.twoBlack = twoBlack;
	}
	public String getTwoGray() {
		return twoGray;
	}
	public void setTwoGray(String twoGray) {
		this.twoGray = twoGray;
	}
	public String getThreeBlack() {
		return threeBlack;
	}
	public void setThreeBlack(String threeBlack) {
		this.threeBlack = threeBlack;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public AntiFraudJudgeOptionEx getAntiFraudJudgeOptionEx() {
		return antiFraudJudgeOptionEx;
	}
	public void setAntiFraudJudgeOptionEx(
			AntiFraudJudgeOptionEx antiFraudJudgeOptionEx) {
		this.antiFraudJudgeOptionEx = antiFraudJudgeOptionEx;
	}
	public AntifraudReport getAntifraudReport() {
		return antifraudReport;
	}
	public void setAntifraudReport(AntifraudReport antifraudReport) {
		this.antifraudReport = antifraudReport;
	}
	public AntifraudDictListEx getAntifraudDict() {
		return antifraudDict;
	}
	public void setAntifraudDict(AntifraudDictListEx antifraudDict) {
		this.antifraudDict = antifraudDict;
	}
	public List<CoborrowerOptionEx> getCoborrowerOptionList() {
		return CoborrowerOptionList;
	}
	public void setCoborrowerOptionList(
			List<CoborrowerOptionEx> coborrowerOptionList) {
		CoborrowerOptionList = coborrowerOptionList;
	}
	public List<BacklistAll> getBlackListAll() {
		return blackListAll;
	}
	public void setBlackListAll(List<BacklistAll> blackListAll) {
		this.blackListAll = blackListAll;
	}

}
