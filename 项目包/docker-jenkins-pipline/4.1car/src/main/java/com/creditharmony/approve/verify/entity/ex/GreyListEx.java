package com.creditharmony.approve.verify.entity.ex;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class GreyListEx extends DataEntity<GreyListEx> {
	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String nowLoanCode;// 当前借款编号
	private String hisLoanCode;// 历史借款编号
	private String dictMarkType;// 加灰类型
	private String dictBlacklistType;// 匹配关键子类型
	private String blacklistMsg;// 关键字内容
	private String blacklistRiskMsg;// 加黑灰原因
	private Date createTime;// 加黑灰时间
	private String loanCustomerName;// 借款人
	private String productType;// 产品类型
	private String type;  // 加黑灰
	
	public String getNowLoanCode() {
		return nowLoanCode;
	}

	public void setNowLoanCode(String nowLoanCode) {
		this.nowLoanCode = nowLoanCode;
	}

	public String getHisLoanCode() {
		return hisLoanCode;
	}

	public void setHisLoanCode(String hisLoanCode) {
		this.hisLoanCode = hisLoanCode;
	}

	public String getBlacklistMsg() {
		return blacklistMsg;
	}

	public void setBlacklistMsg(String blacklistMsg) {
		this.blacklistMsg = blacklistMsg;
	}

	public String getBlacklistRiskMsg() {
		return blacklistRiskMsg;
	}

	public void setBlacklistRiskMsg(String blacklistRiskMsg) {
		this.blacklistRiskMsg = blacklistRiskMsg;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLoanCustomerName() {
		return loanCustomerName;
	}

	public void setLoanCustomerName(String loanCustomerName) {
		this.loanCustomerName = loanCustomerName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getDictMarkType() {
		return dictMarkType;
	}

	public void setDictMarkType(String dictMarkType) {
		this.dictMarkType = dictMarkType;
	}

	public String getDictBlacklistType() {
		return dictBlacklistType;
	}

	public void setDictBlacklistType(String dictBlacklistType) {
		this.dictBlacklistType = dictBlacklistType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
