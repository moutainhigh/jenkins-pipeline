package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCpfDetailed;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;

/**
 * 保存公积金信息
 * @Class Name CreditCpfDetailedDao
 * @author 李文勇
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditCpfDetailedDao extends CrudDao<CreditCpfDetailed> {

	/**
	 * 保存公积金信息
	 * 2016年2月2日
	 * By 李文勇
	 * @param record
	 * @return 操作成功数
	 */
	public int insertData(CreditCpfDetailed record);
	
	/**
	 * 根据参数获取公积金信息
	 * 2016年2月2日
	 * By 李文勇
	 * @param record
	 * @return 公积金信息list
	 */
	public List<CreditCpfDetailed> getAllByParam(CreditReportDetailed creditReportDetailed);

	/**
	 * 根据ID删除数据
	 * 2016年2月2日
	 * By 李文勇
	 * @param record
	 * @return 操作成功数
	 */
	public int deleteData(CreditCpfDetailed record);
	
	/**
	 * 更新数据
	 * 2016年2月2日
	 * By 李文勇
	 * @param record
	 * @return
	 */
	public int updateData(CreditCpfDetailed record);
}