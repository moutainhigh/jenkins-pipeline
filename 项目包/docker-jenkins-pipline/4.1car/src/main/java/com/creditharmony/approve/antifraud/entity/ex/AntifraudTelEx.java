package com.creditharmony.approve.antifraud.entity.ex;

import java.util.List;

import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.core.dict.entity.Dict;

/**
 * 反欺诈-电话照会-封装类
 * @Class Name AntifraudTelEx
 * @author 赖敏
 * @Create In 2015年12月14日
 */
public class AntifraudTelEx {
	
	private List<TelCheckCompanyEx> companys;			// 单位信息
	private List<TelCheckContactPersonEx> workProves;	// 工作证明人
	private List<TelCheckContactPersonEx> familys;		// 家庭联系人
	private List<AntifraudMeConfirm> myConfirms;		// 本人核实
	private List<TelCheckContactPersonEx> others;		// 其他联系人
	private List<Dict> telResults; 						// 评估结果字典
	private List<Dict> familyRelations; 				// 家庭联系人与本人关系字典
	private List<Dict> workProveRelations; 				// 工作证明人与本人关系字典
	private List<Dict> otherRelations; 				// 其他联系人与本人关系字典
	private List<Dict> answerStates; 					// 接听状态字典	
	private String liveAddress;							// 借款人居住地

	public List<Dict> getOtherRelations() {
		return otherRelations;
	}

	public void setOtherRelations(List<Dict> otherRelations) {
		this.otherRelations = otherRelations;
	}

	public List<TelCheckContactPersonEx> getOthers() {
		return others;
	}

	public void setOthers(List<TelCheckContactPersonEx> others) {
		this.others = others;
	}

	public List<TelCheckCompanyEx> getCompanys() {
		return companys;
	}
	
	public void setCompanys(List<TelCheckCompanyEx> companys) {
		this.companys = companys;
	}
	
	public List<TelCheckContactPersonEx> getWorkProves() {
		return workProves;
	}
	
	public void setWorkProves(List<TelCheckContactPersonEx> workProves) {
		this.workProves = workProves;
	}
	
	public List<TelCheckContactPersonEx> getFamilys() {
		return familys;
	}
	
	public void setFamilys(List<TelCheckContactPersonEx> familys) {
		this.familys = familys;
	}
	
	public List<AntifraudMeConfirm> getMyConfirms() {
		return myConfirms;
	}
	
	public void setMyConfirms(List<AntifraudMeConfirm> myConfirms) {
		this.myConfirms = myConfirms;
	}
	
	public void setTelResults(List<Dict> telResults) {
		this.telResults = telResults;
	}
	
	public List<Dict> getTelResults() {
		return telResults;
	}

	public List<Dict> getFamilyRelations() {
		return familyRelations;
	}

	public void setFamilyRelations(List<Dict> familyRelations) {
		this.familyRelations = familyRelations;
	}

	public List<Dict> getWorkProveRelations() {
		return workProveRelations;
	}

	public void setWorkProveRelations(List<Dict> workProveRelations) {
		this.workProveRelations = workProveRelations;
	}

	public List<Dict> getAnswerStates() {
		return answerStates;
	}
	
	public void setAnswerStates(List<Dict> answerStates) {
		this.answerStates = answerStates;
	}
	
	public String getLiveAddress() {
		return liveAddress;
	}
	
	public void setLiveAddress(String liveAddress) {
		this.liveAddress = liveAddress;
	}
	
}
