package com.creditharmony.approve.common.dao;

import java.util.Map;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
 
/**
 * 存储过程调用
 * @Class Name ProcedureDao
 * @author 陈伟东
 * @Create In 2016年1月11日
 */
@LoanBatisDao
public interface ProcedureDao {
	
	/**
	 * 反欺诈决策
	 * 2016年1月20日
	 * By 陈伟东
	 * @param map 查询条件
	 * @return none
	 */
	public void antifraudCheck(Map<?, ?> map);
	
	/**
	 * 信审查重
	 * 2016年1月20日
	 * By 陈伟东
	 * @param loanCode 借款编号 
	 * @return none
	 */
	public void verifyRepeatCheck(String loanCode);
	
	/**
	 * 反欺诈初始化
	 * 2016年1月20日
	 * By 陈伟东
	 * @param loanCode 借款编号
	 * @return none
	 */
	public void antifraudInit(String loanCode);
	
	/**
	 * 首次信息入池
	 * 2016年3月4日
	 * By 刘燕军
	 * @param loanCode
	 */
	public void initPoolFirst(String loanCode);
	
	/**
	 * 再次信息入池
	 * 2016年3月4日
	 * By 刘燕军
	 * @param loanCode
	 */
	public void initPoolOther(String loanCode); 
	
	/**
	 * 再次信息入池
	 * 2016年3月4日
	 * By 刘燕军
	 * @param loanCode
	 */
	public void verifyRepeate(Map<?, ?> map); 
	
	/**
	 * 回退到门店，返回的时进行
	 * 2016年3月24日
	 * By 刘燕军
	 * @param loanCode
	 */
	public void verifyrepeatSecond(Map<?, ?> map);
}
