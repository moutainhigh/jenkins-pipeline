package com.creditharmony.approve.credit.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCycleRecord;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;

/**
 * 期数操作
 * @Class Name CreditCycleRecordDao
 * @author 李文勇
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditCycleRecordDao extends CrudDao<CreditCycleRecord> {

	/**
	 * 插入期数
	 * 2016年2月3日
	 * By 李文勇
	 * @param record
	 * @return 返回操作成功数
	 */
	public int insertData(CreditCycleRecord record);
	
	/**
	 * 查询期数
	 * 2016年2月3日
	 * By 李文勇
	 * @param creditReportDetailed
	 * @return 返回结果list
	 */
	public List<CreditCycleRecord> getAllByParam(CreditReportDetailed creditReportDetailed);
	
	/**
	 * 删除期数
	 * 2016年2月3日
	 * By 李文勇
	 * @param record
	 * @return 返回操作成功数
	 */
	public int deleteData(CreditCycleRecord record);
	
	/**
	 * 获取贷款明细期数
	 * 2016年2月3日
	 * By 侯志斌
	 * @param filter
	 * @return 返回结果list
	 */
	List<CreditCycleRecord> getLoanAllByParam(Map<String, Object> filter);
	
	/**
	 * 获取贷款明细期数
	 * 2016年2月3日
	 * By 侯志斌
	 * @param filter
	 * @return 返回结果list
	 */
	List<CreditCycleRecord> getCardAllByParam(Map<String, Object> filter);
}