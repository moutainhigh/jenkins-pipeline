package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.approve.verify.entity.StatusChangeRecord;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 
 * @Class Name StatusChangeRecordDao
 * @author 李文勇
 * @Create In 2015年12月4日
 */
@LoanBatisDao
public interface StatusChangeRecordDao extends CrudDao<StatusChangeRecord> {
    
	/**
	 * 
	 * 2016年1月18日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 
	 * 2016年1月18日
	 * By 李文勇
	 * @param record
	 * @return
	 */
    public int insertSelective(StatusChangeRecord record);

    /**
     * 
     * 2016年1月18日
     * By 李文勇
     * @param id
     * @return
     */
    public StatusChangeRecord selectByPrimaryKey(String id);

    /**
     * 
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(StatusChangeRecord record);

    /**
     * 
     * 2016年1月18日
     * By 李文勇
     * @param record
     * @return
     */
    public int updateByPrimaryKey(StatusChangeRecord record);

	/**
	 * 显示历史弹出画面
	 * 2015年12月7日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
    public List<StatusChangeRecord> historyGetByLoanCode(StatusChangeRecord record);
    
	/**
	 * 根据借款编号查询对应的记录
	 * 2016年7月26日
	 * By 刘燕军
	 * @param loanCode
	 * @return 记录的总数
	 */
	public int getFlag(String loanCode);
}