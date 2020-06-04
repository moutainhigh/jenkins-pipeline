package com.creditharmony.approve.credit.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;

/**
 * 企业征信
 * @Class Name EnterpriseCreditDao
 * @author zhanghu
 * @Create In 2016年1月18日
 */
@LoanBatisDao
public interface  EnterpriseCreditDao extends CrudDao<EnterpriseCredit>{
    
    /**
     * 新增所有列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
	public int insertEnterpriseCredit(EnterpriseCredit record);

    /**
     * 根据企业征信信息对象查询征信信息
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 企业征信信息对象
     */
    public EnterpriseCredit selectByEnterpriseCredit(EnterpriseCredit record);
    
    /**
     * 根据企业征信信息对象查询征信信息
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 企业征信信息对象
     */
    public EnterpriseCredit selectEnterpriseCredit(EnterpriseCredit record);
    
    /**
	 * 根据借款编号查询数据
	 * 2016年2月22日
	 * By 李文勇
	 * @param record
	 * @return
	 */
	public EnterpriseCredit getByLoanCode(EnterpriseCredit record);
    
    /**
     * 更新所有有值列数据
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 返回条数
     */
	public int updateByPrimaryKeySelective(EnterpriseCredit record);
	
	/**
	 * 获取版本（连表查出来的版本）
	 * 2016年2月29日
	 * By 李文勇
	 * @param loanCode
	 * @return 返回查询结果
	 */
	public EnterpriseCredit getVersion(String loanCode);
    
}