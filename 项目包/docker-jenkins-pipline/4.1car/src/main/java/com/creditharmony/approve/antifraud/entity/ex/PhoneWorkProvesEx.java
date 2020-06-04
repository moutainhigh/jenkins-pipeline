package com.creditharmony.approve.antifraud.entity.ex;

import com.creditharmony.core.persistence.DataEntity;


/**
 *  电话照会，单位电话，电话录音 
 * @Class Name PhoneWorkEx
 * @author wanglidong
 * @Create In 2016年3月2日
 */
public class PhoneWorkProvesEx extends DataEntity<PhoneWorkProvesEx>{
	
	private static final long serialVersionUID = 1L;
	
	private String loanCode;				// 借款编码
	private String name;					// 姓名
	private String loanManRelation;			// 与借款人关系
	private String rCustomerCoborrowerId;	// 关联ID(主借人或共借人)
	private String dictCustomerType;		// 借款人类型(主借人/共借人)
	private String dictTelSource;			// 号码来源
	private String dhgxshTel;					// 电话号码
	private String dictRelationType;		// 关系类型(工作证明人;家庭证明人)
	private String dhgxshAssessResult;		// 评估结果
	private String loanLiveAddress;			// 借款人居住地
	private String dictCheckType;			// 类型(1信审初审电话审核  2反欺诈专员电话审核)
	private String workNetAssessResult; 	// 网查结果
	private String workCheckRemark; 		// 网查备注
	private String isRepeat;				// 是否已经查重
	private String isInPool;				// 是否填入查重池
	private String editRemark;				// 编辑标识(默认0可编辑1不可编辑)
	private String type;					// 联系人类型
	private String telRemark;				// 电话备注
	private String otherTelRemark;			//其他联系人备注
	
	private String homeTel;					// 新版申请表add宅电
	private String homeTelAssessResult;		// 新版申请表add宅电评估结果
	private String homeTelRemark;			//新版申请表add宅电备注
	
	public String getTelRemark() {
		return telRemark;
	}
	public void setTelRemark(String telRemark) {
		this.telRemark = telRemark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDhgxshTel() {
		return dhgxshTel;
	}
	public void setDhgxshTel(String dhgxshTel) {
		this.dhgxshTel = dhgxshTel;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoanManRelation() {
		return loanManRelation;
	}
	public void setLoanManRelation(String loanManRelation) {
		this.loanManRelation = loanManRelation;
	}
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public String getDictTelSource() {
		return dictTelSource;
	}
	public void setDictTelSource(String dictTelSource) {
		this.dictTelSource = dictTelSource;
	}
	public String getDictRelationType() {
		return dictRelationType;
	}
	public void setDictRelationType(String dictRelationType) {
		this.dictRelationType = dictRelationType;
	}
	public String getDhgxshAssessResult() {
		return dhgxshAssessResult;
	}
	public void setDhgxshAssessResult(String dhgxshAssessResult) {
		this.dhgxshAssessResult = dhgxshAssessResult;
	}
	public String getLoanLiveAddress() {
		return loanLiveAddress;
	}
	public void setLoanLiveAddress(String loanLiveAddress) {
		this.loanLiveAddress = loanLiveAddress;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public String getWorkNetAssessResult() {
		return workNetAssessResult;
	}
	public void setWorkNetAssessResult(String workNetAssessResult) {
		this.workNetAssessResult = workNetAssessResult;
	}
	public String getWorkCheckRemark() {
		return workCheckRemark;
	}
	public void setWorkCheckRemark(String workCheckRemark) {
		this.workCheckRemark = workCheckRemark;
	}
	public String getIsRepeat() {
		return isRepeat;
	}
	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}
	public String getIsInPool() {
		return isInPool;
	}
	public void setIsInPool(String isInPool) {
		this.isInPool = isInPool;
	}
	public String getEditRemark() {
		return editRemark;
	}
	public void setEditRemark(String editRemark) {
		this.editRemark = editRemark;
	}
	public String getOtherTelRemark() {
		return otherTelRemark;
	}
	public void setOtherTelRemark(String otherTelRemark) {
		this.otherTelRemark = otherTelRemark;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getHomeTelAssessResult() {
		return homeTelAssessResult;
	}
	public void setHomeTelAssessResult(String homeTelAssessResult) {
		this.homeTelAssessResult = homeTelAssessResult;
	}
	public String getHomeTelRemark() {
		return homeTelRemark;
	}
	public void setHomeTelRemark(String homeTelRemark) {
		this.homeTelRemark = homeTelRemark;
	}
	
	
	
	

}
