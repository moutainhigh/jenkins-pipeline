package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.ex.JointlyLoanInfoEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 共借人信息
 * @Class Name LoanCoborrowerDao
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@LoanBatisDao
public interface LoanCoborrowerDao extends CrudDao<LoanCoborrower>{

	/**
     * 查询固定的共借人信息
     * 2015年12月5日
     * By 刘燕军
     * @param loanCode
     * @param type
     * @return 共借人信息
     */
    public List<JointlyLoanInfoEx> findJointlyEx(String loanCode,String type);
  
    /**
     * 通过id获取用户指定信息
     * 2015年12月26日
     * By 刘燕军
     * @param id
     * @return 对应的参数信息
     */
    public VerifyParamEx findJotinly(String id);
    
    /**
     * 查看弹出画面用
     * 2015年12月30日
     * By 李文勇
     * @param loanCoborrower
     * @return 共借人信息
     */
    public LoanCoborrower viewGetByLoanCode(LoanCoborrower loanCoborrower);
    
    /**
     * 查看弹出画面用
     * 2015年10月10日
     * @param loanCoborrower
     * @return 共借人信息
     */
    public LoanCoborrower viewGetByLoanCodeNew(LoanCoborrower loanCoborrower);
    
    /**
     * 根据借款编号查询数据
     * 2016年2月23日
     * By 李文勇
     * @param loanCoborrower
     * @return 共借人信息list
     */
    public List<LoanCoborrower> getByLoanCode(LoanCoborrower loanCoborrower);
    
    /**
     * 根据id 查找共借人信息
     * 2016年5月3日
     * By 王浩
     * @param id
     * @return 
     */
    public LoanCoborrower getCoborrower(String id);
    
    /**
     * 根据借款编号查询数据
     * 2016年2月23日
     * By 李文勇
     * @param loanCoborrower
     * @return 共借人信息list
     */
    public List<LoanCoborrower> findNewCoborrowers(String loanCode);
    /**
     * 根据借款编号查询数据
     * 2016年2月23日
     * By 赵春香
     * @param loanCoborrower
     * @return 共借人信息list
     */
    public List<LoanCoborrower> getCoborrowersByLoanCode(String loanCode);
    
}