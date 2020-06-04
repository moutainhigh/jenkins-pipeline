package com.creditharmony.approve.verify.entity.ex;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.core.dict.entity.Dict;

/**
 * 字典封装
 * @Class Name DictList
 * @author 王浩
 * @Create In 2015年1月11日
 */
public class DictList implements Serializable{
	
	private static final long serialVersionUID = -3585218287445955809L;

	private List<Dict> telSrc;

	private List<Dict> evalResult;

	private List<Dict> answerState;

	private List<Dict> telRecordSrc;
	
	private List<Dict> relationType;	
	
	private List<Dict> personalRisk;
	
	private List<Dict> unitNameSrc;
	
	private List<Dict> workRelation;
	
	private List<Dict> familyRelation;
	
	private List<Dict> otherRelation;
	
	private List<Dict> industryType;
	
	private	List<Dict> professionType;
	
	private	List<Dict> phoneType;
	
	//新版申请表add
	// 主要发薪方式 jk_paysalary_way
	private	List<Dict> paysalaryWay;
	
	// 职务 jk_job_type
	private	List<Dict> jobType;
	
	// 其他收入来源 jk_repay_source_new_else
	private	List<Dict> repaySourceNewElse;
	
	
	
	
	public List<Dict> getTelSrc() {
		return telSrc;
	}

	public void setTelSrc(List<Dict> telSrc) {
		this.telSrc = telSrc;
	}

	public List<Dict> getEvalResult() {
		return evalResult;
	}

	public void setEvalResult(List<Dict> evalResult) {
		this.evalResult = evalResult;
	}

	public List<Dict> getAnswerState() {
		return answerState;
	}

	public void setAnswerState(List<Dict> answerState) {
		this.answerState = answerState;
	}

	public List<Dict> getTelRecordSrc() {
		return telRecordSrc;
	}

	public void setTelRecordSrc(List<Dict> telRecordSrc) {
		this.telRecordSrc = telRecordSrc;
	}

	public List<Dict> getRelationType() {
		return relationType;
	}

	public void setRelationType(List<Dict> relationType) {
		this.relationType = relationType;
	}

	public List<Dict> getPersonalRisk() {
		return personalRisk;
	}

	public void setPersonalRisk(List<Dict> personalRisk) {
		this.personalRisk = personalRisk;
	}

	public List<Dict> getUnitNameSrc() {
		return unitNameSrc;
	}

	public void setUnitNameSrc(List<Dict> unitNameSrc) {
		this.unitNameSrc = unitNameSrc;
	}

	public List<Dict> getWorkRelation() {
		return workRelation;
	}

	public void setWorkRelation(List<Dict> workRelation) {
		this.workRelation = workRelation;
	}

	public List<Dict> getFamilyRelation() {
		return familyRelation;
	}

	public void setFamilyRelation(List<Dict> familyRelation) {
		this.familyRelation = familyRelation;
	}

	public List<Dict> getOtherRelation() {
		return otherRelation;
	}

	public void setOtherRelation(List<Dict> otherRelation) {
		this.otherRelation = otherRelation;
	}

	public List<Dict> getIndustryType() {
		return industryType;
	}

	public void setIndustryType(List<Dict> industryType) {
		this.industryType = industryType;
	}

	public List<Dict> getProfessionType() {
		return professionType;
	}

	public void setProfessionType(List<Dict> professionType) {
		this.professionType = professionType;
	}

	public List<Dict> getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(List<Dict> phoneType) {
		this.phoneType = phoneType;
	}

	public List<Dict> getPaysalaryWay() {
		return paysalaryWay;
	}

	public void setPaysalaryWay(List<Dict> paysalaryWay) {
		this.paysalaryWay = paysalaryWay;
	}

	public List<Dict> getJobType() {
		return jobType;
	}

	public void setJobType(List<Dict> jobType) {
		this.jobType = jobType;
	}

	public List<Dict> getRepaySourceNewElse() {
		return repaySourceNewElse;
	}

	public void setRepaySourceNewElse(List<Dict> repaySourceNewElse) {
		this.repaySourceNewElse = repaySourceNewElse;
	}	
	
}
