package com.creditharmony.approve.verify.util;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.creditharmony.approve.verify.entity.AuditRecord;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.StatusChangeRecord;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.approve.type.CheckType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.type.ModuleName;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 需要更新借款信息表和变更记录表的工具类
 * @Class Name PreUpdateUtil
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
public class PreUpdateUtil {
	
	/**
	 * 更新借款信息表的工具类
	 * 2015年12月5日
	 * By 刘燕军
	 * @param loanCode 借款编号
	 * @param status  借款状态
	 * @param id  审核结果的id
	 * @return
	 */
	public static LoanInfo UpdateLoan(String loanCode,String status,String result,String step,String resultId,String recordId){
		LoanInfo info = new LoanInfo();	
		// 更新借款信息表中的借款状态
		info.setDictLoanStatus(status);
		// 把借款编号放入对象中
		info.setLoanCode(loanCode);
		info.setApproveResult(result);
		info.setApproveStep(step);
		info.setResultId(resultId);
		info.setRecordId(recordId);
		// 获取变更基本信息
		info.preUpdate();
		return info;
	}
	
	/**
	 * 封装变更历史信息
	 * 2015年12月9日
	 * By 刘燕军
	 * @param applyId
	 * @param loanCode
	 * @param status
	 * @param step
	 * @param result
	 * @return
	 */
	public static StatusChangeRecord updateStatusChangeRecord(String applyId,
			String loanCode, String status, String step, String remark,String result) {
		StatusChangeRecord  record = new StatusChangeRecord();
		User user = UserUtils.getUser();
		record.setApplyId(applyId);
		// 设置系统类型(系统机构名称)
		record.setDictSysFlag(ModuleName.MODULE_APPROVE.value);
		// 设置借款编号
		record.setLoanCode(loanCode);
		// 设置借款状态
		record.setDictLoanStatus(status);
		// 设置操作步骤 
		record.setOperateStep(CheckType.parseByCode(step).getName());
		// 设置操作结果  汉字
		record.setOperateResult(CheckResult.parseByCode(result).getName());
		// 设置备注
		record.setRemark(remark);
		// 设置操作人		
		record.setOperator(StringUtils.isNotEmpty(user.getName()) ? user.getName() : "系统");
		// 设置操作人角色 user.getRole().getId()
		//TODO
		record.setOperaterRoleId("11");
		// 设置结构编码
		record.setOrgCode(user.getOrgIds());
		// 设置操作时间
		record.setOperateTime(new Date());
		// 获取变更历史的id
		record.preInsert();
		return  record;
	}
	
	/**
	 * 插入审核记录表
	 * 2015年12月28日
	 * By 刘燕军
	 * @param checkType 类型 初审、复审、终审 等
	 * @param loanCode 借款编号
	 * @param loanStatus 借款状态
	 * @param result 操作结果
	 * @param reason 拒绝原因
	 * @return
	 */
	public static AuditRecord insertAuditRecord(String checkType,String loanCode,String loanStatus,String result,String reason){
		//User user = UserUtils.getUser();
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		AuditRecord auditRecord = new AuditRecord();
		// 设置审核结果
		auditRecord.setOperateStep(checkType);
		// 设置借款编号
		auditRecord.setLoanCode(loanCode);
		// 获取更新相关信息
		auditRecord.setTransactorTime(new Date());		
		auditRecord.setDictLoanStatus(loanStatus);
		auditRecord.setOperateResult(result);
		auditRecord.setRefuseReason(reason);
		if (user != null ) {
			auditRecord.setTransactorCode(user.getId());
			if(user.getDepartment() != null){
				auditRecord.setOrgCode(user.getDepartment().getId());
			}
		}				
		return auditRecord;
	}

}
