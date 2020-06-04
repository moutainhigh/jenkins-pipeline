package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditGuaranteeDetailed;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;

/**
 * 征信祥版担保信息数据库操作dao
 * @Class Name CreditGuaranteeDetailedDao
 * @author 侯志斌
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditGuaranteeDetailedDao {
	
	/**
	 * 按主键删除实体
	 * 2016年2月24日
	 * By 侯志斌
	 * @param id
	 * @return 返回数值操作结果
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 增加担保信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record 担保信息
	 * @return 返回数值操作结果
	 */
	public int insert(CreditGuaranteeDetailed record);

	/**
	 * 动态增加担保信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record 担保信息
	 * @return 返回数值操作结果
	 */
	public int insertSelective(CreditGuaranteeDetailed record);

	/**
	 * 查询担保信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param id  
	 * @return 返回担保实体
	 */
	public CreditGuaranteeDetailed selectByPrimaryKey(String id);

	/**
	 * 动态修改担保信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record 担保信息
	 * @return 返回数值操作结果
	 */
	public int updateByPrimaryKeySelective(CreditGuaranteeDetailed record);

	/**
	 * 修改担保信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record 担保信息
	 * @return 返回数值操作结果
	 */
	public int updateByPrimaryKey(CreditGuaranteeDetailed record);

	/**
	 * 根据参数获取信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param creditReportDetailed
	 * @return 担保信息列表
	 */
	public List<CreditGuaranteeDetailed> getAllByParam(
			CreditReportDetailed creditReportDetailed);
}