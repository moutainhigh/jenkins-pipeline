package com.creditharmony.approve.credit.entity.ex;

import java.util.List;

import com.creditharmony.approve.credit.entity.CreditCardInfo;
import com.creditharmony.approve.credit.entity.CreditLoanInfo;

/**
 * 下载页面简版贷款信息
 * @Class Name CreditSimpleDownUseEx
 * @author 李文勇
 * @Create In 2016年4月12日
 */
public class CreditDownUseEx {

	private String flg;
	
	private List<CreditCardInfo> downSimpleCardList;		// 下载页面简版贷记卡信息
	
	private List<CreditLoanInfo> downSimpleloanList;		// 下载页面简版贷款信息
	
	private List<CreditLoanDetailedEx> downLoanDetailList;	// 下载页面详版贷款信息
	
	private List<CreditCardDetailedEx> downCardDetailList;	// 下载页面详版贷记卡信息

	
	
	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public List<CreditCardInfo> getDownSimpleCardList() {
		return downSimpleCardList;
	}

	public void setDownSimpleCardList(List<CreditCardInfo> downSimpleCardList) {
		this.downSimpleCardList = downSimpleCardList;
	}

	public List<CreditLoanInfo> getDownSimpleloanList() {
		return downSimpleloanList;
	}

	public void setDownSimpleloanList(List<CreditLoanInfo> downSimpleloanList) {
		this.downSimpleloanList = downSimpleloanList;
	}

	public List<CreditLoanDetailedEx> getDownLoanDetailList() {
		return downLoanDetailList;
	}

	public void setDownLoanDetailList(List<CreditLoanDetailedEx> downLoanDetailList) {
		this.downLoanDetailList = downLoanDetailList;
	}

	public List<CreditCardDetailedEx> getDownCardDetailList() {
		return downCardDetailList;
	}

	public void setDownCardDetailList(List<CreditCardDetailedEx> downCardDetailList) {
		this.downCardDetailList = downCardDetailList;
	}
	
}
