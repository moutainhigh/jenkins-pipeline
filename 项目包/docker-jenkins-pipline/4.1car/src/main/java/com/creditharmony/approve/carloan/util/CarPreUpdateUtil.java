package com.creditharmony.approve.carloan.util;

import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.LoanStatusHis;
import com.creditharmony.core.type.ModuleName;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 需要更新借款信息表和变更记录表的工具类
 * @Class Name CarPreUpdateUtil
 * @author 申诗阔
 * @Create In 2016年1月28日
 */
public class CarPreUpdateUtil {

	/**
	 * 更新借款信息表的工具类
	 * 2016年1月28日
	 * By 申诗阔
	 * @param loanCode
	 * @param status
	 * @return
	 */
	public static CarLoanInfo UpdateLoan(String loanCode, String status, String throughFlag, String loanFlag) {
		CarLoanInfo info = new CarLoanInfo();
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

	/**
	 * 封装变更历史信息
	 * 2016年1月28日
	 * By 申诗阔
	 * @param loanCode 借款编号
	 * @param status 借款状态
	 * @param step 操作步骤：回退、放弃、拒绝等
	 * @return
	 */
	public static LoanStatusHis updateStatusChangeRecord(String loanCode,
			String status, String step, String operResultName) {
		LoanStatusHis loanStatusHis = new LoanStatusHis();
		User user = UserUtils.getUser();
		// 设置系统类型(系统机构名称)
		loanStatusHis.setDictSysFlag(ModuleName.MODULE_APPROVE.value);
		// 设置借款编号
		loanStatusHis.setLoanCode(loanCode);
		// 设置借款状态
		loanStatusHis.setDictLoanStatus(status);
		// 设置操作步骤
		loanStatusHis.setOperateStep(step);
		// 设置操作结果
		loanStatusHis.setOperateResult(operResultName);
		// 设置操作人
		loanStatusHis.setOperator(user.getName());
		// 设置操作人角色 
		// user.getRole().getId()
		// TODO 设置操作人角色
		loanStatusHis.setOperatorRoleId("11");
		// 设置结构编码
		loanStatusHis.setOrgCode(user.getOrgIds());
		// 设置变更历史的id，创建人，创建时间，更新人，更新时间
		loanStatusHis.preInsert();
		// 设置操作时间
		loanStatusHis.setOperateTime(loanStatusHis.getCreateTime());
		return loanStatusHis;
	}

}
