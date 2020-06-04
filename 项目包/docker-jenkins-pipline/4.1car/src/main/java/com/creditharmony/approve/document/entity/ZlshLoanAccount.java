package com.creditharmony.approve.document.entity;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.approve.document.entity.ex.ZlshAccountJasonEx;
import com.creditharmony.core.persistence.DataEntity;
/**
 * 资料审核-账户流水
 * @Class Name ZlshLoanAccount
 * @author 黄维
 * @Create In 2015年12月7日
 */
public class ZlshLoanAccount extends DataEntity<ZlshLoanAccount>{
    
	private static final long serialVersionUID = 1L;

	private String loanCode;					// 借款编码
	private String rCustomerCoborrowerId;		// 关联ID(主借人，共借人)
	private String dictCustomerType;			// 借款人类型(主借人/共借人)
	private String bankCardNo;					// 银行帐号
	private String dictAccountType;				// 账户类型(个人/公卡)
	private String accountFlowMark;				// 工资流水标记
	private Double accountLastMonth;			// 最近一个月
	private Double accountN1Months;				// N_1个月
	private Double accountN2Months;				// N_2个月
	private Double accountN3Months;				// N_3个月
	private Double accountN4Months;				// N_4个月
	private Double accountN5Months;				// N_5个月
	private Double accountStreamScope;			// 流水范围
	private Double accountFreeAmount;			// 账户余额
	private Double accountInterest3;			// 结息3月
	private Double accountInterest6;			// 结息6月
	private Double accountInterest9;			// 结息9月
	private Double accountInterest12;			// 结息12月
	private Double accountMonthWage;			// 月认定收入
	private String accountConsumeHabit;			// 花费习惯
	private String accountFlowLength;			// 流水长度
	private String accountAbstractInfo;			// 摘要信息
	private String accountMyselfAbstract;		// 自述摘要
	private String dictCheckType;				// 类型(初审，信审初审，复议初审)
	private List<ZlshAccountJasonEx> accountFlowDetail;		// 流水详细
	// 2016-03-28  添加
	private String otherLoanMark;				// 同业借款
	private BigDecimal monthUseMoney;			// 月扣款金额
	private String monthUseNum;					// 月扣款数量
	private String dictSourceType;
	
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getRCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	public void setRCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}
	public String getDictCustomerType() {
		return dictCustomerType;
	}
	public void setDictCustomerType(String dictCustomerType) {
		this.dictCustomerType = dictCustomerType;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getDictAccountType() {
		return dictAccountType;
	}
	public void setDictAccountType(String dictAccountType) {
		this.dictAccountType = dictAccountType;
	}
	public String getAccountFlowMark() {
		return accountFlowMark;
	}
	public void setAccountFlowMark(String accountFlowMark) {
		this.accountFlowMark = accountFlowMark;
	}
	public Double getAccountLastMonth() {
		return accountLastMonth;
	}
	public void setAccountLastMonth(Double accountLastMonth) {
		this.accountLastMonth = accountLastMonth;
	}
	public Double getAccountN1Months() {
		return accountN1Months;
	}
	public void setAccountN1Months(Double accountN1Months) {
		this.accountN1Months = accountN1Months;
	}
	public Double getAccountN2Months() {
		return accountN2Months;
	}
	public void setAccountN2Months(Double accountN2Months) {
		this.accountN2Months = accountN2Months;
	}
	public Double getAccountN3Months() {
		return accountN3Months;
	}
	public void setAccountN3Months(Double accountN3Months) {
		this.accountN3Months = accountN3Months;
	}
	public Double getAccountN4Months() {
		return accountN4Months;
	}
	public void setAccountN4Months(Double accountN4Months) {
		this.accountN4Months = accountN4Months;
	}
	public Double getAccountN5Months() {
		return accountN5Months;
	}
	public void setAccountN5Months(Double accountN5Months) {
		this.accountN5Months = accountN5Months;
	}
	public Double getAccountStreamScope() {
		return accountStreamScope;
	}
	public void setAccountStreamScope(Double accountStreamScope) {
		this.accountStreamScope = accountStreamScope;
	}
	public Double getAccountFreeAmount() {
		return accountFreeAmount;
	}
	public void setAccountFreeAmount(Double accountFreeAmount) {
		this.accountFreeAmount = accountFreeAmount;
	}
	public Double getAccountInterest3() {
		return accountInterest3;
	}
	public void setAccountInterest3(Double accountInterest3) {
		this.accountInterest3 = accountInterest3;
	}
	public Double getAccountInterest6() {
		return accountInterest6;
	}
	public void setAccountInterest6(Double accountInterest6) {
		this.accountInterest6 = accountInterest6;
	}
	public Double getAccountInterest9() {
		return accountInterest9;
	}
	public void setAccountInterest9(Double accountInterest9) {
		this.accountInterest9 = accountInterest9;
	}
	public Double getAccountInterest12() {
		return accountInterest12;
	}
	public void setAccountInterest12(Double accountInterest12) {
		this.accountInterest12 = accountInterest12;
	}
	public Double getAccountMonthWage() {
		return accountMonthWage;
	}
	public void setAccountMonthWage(Double accountMonthWage) {
		this.accountMonthWage = accountMonthWage;
	}
	public String getAccountConsumeHabit() {
		return accountConsumeHabit;
	}
	public void setAccountConsumeHabit(String accountConsumeHabit) {
		this.accountConsumeHabit = accountConsumeHabit;
	}
	public String getAccountFlowLength() {
		return accountFlowLength;
	}
	public void setAccountFlowLength(String accountFlowLength) {
		this.accountFlowLength = accountFlowLength;
	}
	public String getAccountAbstractInfo() {
		return accountAbstractInfo;
	}
	public void setAccountAbstractInfo(String accountAbstractInfo) {
		this.accountAbstractInfo = accountAbstractInfo;
	}
	public String getAccountMyselfAbstract() {
		return accountMyselfAbstract;
	}
	public void setAccountMyselfAbstract(String accountMyselfAbstract) {
		this.accountMyselfAbstract = accountMyselfAbstract;
	}
	public String getDictCheckType() {
		return dictCheckType;
	}
	public void setDictCheckType(String dictCheckType) {
		this.dictCheckType = dictCheckType;
	}
	public List<ZlshAccountJasonEx> getAccountFlowDetail() {
		return accountFlowDetail;
	}
	public void setAccountFlowDetail(List<ZlshAccountJasonEx> accountFlowDetail) {
		this.accountFlowDetail = accountFlowDetail;
	}
	public String getOtherLoanMark() {
		return otherLoanMark;
	}
	public void setOtherLoanMark(String otherLoanMark) {
		this.otherLoanMark = otherLoanMark;
	}
	public BigDecimal getMonthUseMoney() {
		return monthUseMoney;
	}
	public void setMonthUseMoney(BigDecimal monthUseMoney) {
		this.monthUseMoney = monthUseMoney;
	}
	public String getMonthUseNum() {
		return monthUseNum;
	}
	public void setMonthUseNum(String monthUseNum) {
		this.monthUseNum = monthUseNum;
	}
	public String getrCustomerCoborrowerId() {
		return rCustomerCoborrowerId;
	}
	public void setrCustomerCoborrowerId(String rCustomerCoborrowerId) {
		this.rCustomerCoborrowerId = rCustomerCoborrowerId;
	}
	public String getDictSourceType() {
		return dictSourceType;
	}
	public void setDictSourceType(String dictSourceType) {
		this.dictSourceType = dictSourceType;
	}
	
}