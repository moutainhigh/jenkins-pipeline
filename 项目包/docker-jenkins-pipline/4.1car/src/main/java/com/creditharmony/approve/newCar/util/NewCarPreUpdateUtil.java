package com.creditharmony.approve.newCar.util;


import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;

/**
 * 需要更新借款信息表和变更记录表的工具类
 * @Class Name CarPreUpdateUtil
 * @author 李高远
 * @Create In 2017年3月30日
 */
public class NewCarPreUpdateUtil {

	/**
	 * 更新借款信息表的工具类
	 * 2017年3月30日
	 * By 李高远
	 * @param loanCode
	 * @param status
	 * @return
	 */
	public static NewCarLoanInfo UpdateLoan(String loanCode, String status, String throughFlag, String loanFlag) {
		NewCarLoanInfo info = new NewCarLoanInfo();
		// 更新借款信息表中的借款状态
		info.setDictLoanStatus(status);
		// 设置借款编号
		info.setLoanCode(loanCode);
		info.setConditionalThroughFlag(throughFlag);
		info.setLoanFlag(loanFlag);
		// 更新 更改用户及更改时间
		info.preUpdate();
		return info;
	}
}
