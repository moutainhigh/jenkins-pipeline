package com.creditharmony.approve.internet.entity.ex;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 外网审核-网查-家庭联系人（手机号码和宅电）
 * 
 * @author 罗俊平
 * @create in 2016-09-27
 */
public class OutSideCheckConEx extends DataEntity<OutSideCheckConEx> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3993418317960009207L;

	/**
	 * 借款编码
	 */
	private String loanCode;
	/**
	 * 与借款人关系
	 */
	private String loanManRelation;
	/**
	 * 关联ID(主借人或共借人)
	 */
	private String rCustomerCoborrowerId;
	/**
	 * 借款人类型(0:主借人;1:共借人)
	 */
	private String dictCustomerType;
	/**
	 * 关系类型（0：家庭证明人；1：工作证明人；2：其他联系人）
	 */
	private String dictRelationType;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 手机号码或宅电
	 */
	private String phoneNum;
	/**
	 * 号码类型（0：宅电；1：手机号码）
	 */
	private String phoneType;
	/**
	 * 网查结果（亲属的“宅电”）
	 */
	private String clanPhoneNetResult;
	/**
	 * 网查备注（亲属的“宅电”）
	 */
	private String clanPhoneNetRemark;

	/**
	 * 借款编码
	 */
	public String getLoanCode() {
		return loanCode;
	}

	/**
	 * 借款编码
	 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/**
	 * 与借款人关系
	 */
	public String getLoanManRelation() {
		return loanManRelation;
	}

	/**
	 * 与借款人关系
	 */
	public void setLoanManRelation(String loanManRelation) {
		this.loanManRelation = loanManRelation;
	}

	/**
	 * 关联ID(主借人或共借人)
	 */
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}

	/**
	 * 关联ID(主借人或共借人)
	 */
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}

	/**
	 * 借款人类型(0:主借人;1:共借人)
	 */
	public String getDictCustomerType() {
		return dictCustomerType;
	}

	/**
	 * 借款人类型(0:主借人;1:共借人)
	 */
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}

	/**
	 * 关系类型（0：家庭证明人；1：工作证明人；2：其他联系人）
	 */
	public String getDictRelationType() {
		return dictRelationType;
	}

	/**
	 * 关系类型（0：家庭证明人；1：工作证明人；2：其他联系人）
	 */
	public void setDictRelationType(String dictRelationType) {
		this.dictRelationType = dictRelationType;
	}

	/**
	 * 姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 手机号码或宅电
	 */
	public String getPhoneNum() {
		return phoneNum;
	}

	/**
	 * 手机号码或宅电
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * 号码类型（0：宅电；1：手机号码）
	 */
	public String getPhoneType() {
		return phoneType;
	}

	/**
	 * 号码类型（0：宅电；1：手机号码）
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	/**
	 * 网查结果（亲属的“宅电”）
	 */
	public String getClanPhoneNetResult() {
		return clanPhoneNetResult;
	}

	/**
	 * 网查结果（亲属的“宅电”）
	 */
	public void setClanPhoneNetResult(String clanPhoneNetResult) {
		this.clanPhoneNetResult = clanPhoneNetResult;
	}

	/**
	 * 网查备注（亲属的“宅电”）
	 */
	public String getClanPhoneNetRemark() {
		return clanPhoneNetRemark;
	}

	/**
	 * 网查备注（亲属的“宅电”）
	 */
	public void setClanPhoneNetRemark(String clanPhoneNetRemark) {
		this.clanPhoneNetRemark = clanPhoneNetRemark;
	}

}
