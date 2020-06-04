package com.creditharmony.approve.verify.entity.ex;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 内网审核逾期
 * @Class Name PayBackMonthEx
 * @author wanglidong
 * @Create In 2015年12月7日
 */
public class PayBackMonthEx extends DataEntity<PayBackMonthEx>{

	private static final long serialVersionUID = 3383834653227463837L;
	private String loanCode;// 借款编号
	private Integer loanNum;// 结清再贷次数
	private Date monthPayDay;// 还款日
	private Date monthPayActualday;// 实际还款日
	private String monthOverdueDays;// 预期天数
	private String monthOverdueMes;// 逾期原因
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public Integer getLoanNum() {
		return loanNum;
	}
	public void setLoanNum(Integer loanNum) {
		this.loanNum = loanNum;
	}
	public Date getMonthPayDay() {
		return monthPayDay;
	}
	public void setMonthPayDay(Date monthPayDay) {
		this.monthPayDay = monthPayDay;
	}
	public Date getMonthPayActualday() {
		return monthPayActualday;
	}
	public void setMonthPayActualday(Date monthPayActualday) {
		this.monthPayActualday = monthPayActualday;
	}
	public String getMonthOverdueDays() {
		return monthOverdueDays;
	}
	public void setMonthOverdueDays(String monthOverdueDays) {
		this.monthOverdueDays = monthOverdueDays;
	}
	public String getMonthOverdueMes() {
		return monthOverdueMes;
	}
	public void setMonthOverdueMes(String monthOverdueMes) {
		this.monthOverdueMes = monthOverdueMes;
	}

	
}
