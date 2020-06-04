package com.creditharmony.approve.antifraud.entity.ex;


/**
 * 外部黑名单扩展Entity
 * @Class Name AntiFraudJudge
 * @author wanglidong
 * @Create In 2015年11月26日
 */
public class ExternalBlackListEx{

	private String dictMarkType;			//标记类型code
	private String markType;				//标记类型
	private String dictSource;			    //来源
	private String loanCode;				//借款编码
	private String blackListType;			// 加黑类型
	private String blackListMsg;			// 加黑内容
	private String linkmanPhone;			// 联系人手机号
	
	public String getMarkType() {
		return markType;
	}
	public void setMarkType(String markType) {
		this.markType = markType;
	}
	public String getDictMarkType() {
		return dictMarkType;
	}
	public void setDictMarkType(String dictMarkType) {
		this.dictMarkType = dictMarkType;
	}
	public String getDictSource() {
		return dictSource;
	}
	public void setDictSource(String dictSource) {
		this.dictSource = dictSource;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getLinkmanPhone() {
		return linkmanPhone;
	}
	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}
	public String getBlackListType() {
		return blackListType;
	}
	public void setBlackListType(String blackListType) {
		this.blackListType = blackListType;
	}
	public String getBlackListMsg() {
		return blackListMsg;
	}
	public void setBlackListMsg(String blackListMsg) {
		this.blackListMsg = blackListMsg;
	}	
}
