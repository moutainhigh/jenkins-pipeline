package com.creditharmony.approve.credit.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditLoanDetailedOne;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
/**
 * 征信祥版贷款信息一数据库操作dao
 * @Class Name CreditLoanDetailedOneDao
 * @author 侯志斌
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditLoanDetailedOneDao {
	
	/**
	 * 按主键删除实体对象
	 * 2016年2月24日
	 * By 侯志斌
	 * @param id
	 * @return 返回数值操作结果
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 增加贷款一信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record
	 * @return 返回数值操作结果
	 */
	public int insert(CreditLoanDetailedOne record);

	/**
	 * 动态增加贷款一信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record
	 * @return 返回数值操作结果
	 */
	public int insertSelective(CreditLoanDetailedOne record);

	/**
	 * 查询贷款一信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param id
	 * @return 返回贷款实体
	 */
	public CreditLoanDetailedOne selectByPrimaryKey(String id);

	/**
	 * 根据参数获取贷款一信息1
	 * 2016年2月24日
	 * By 侯志斌
	 * @param params
	 * @return
	 */
	public List<CreditLoanDetailedOne> findByParams(Map<String,Object> params);
	
	/**
	 * 根据参数获取贷款一信息2
	 * 2016年2月24日
	 * By 侯志斌
	 * @param creditReportDetailed
	 * @return
	 */
	public List<CreditLoanDetailedOne> getAllByParam(CreditReportDetailed creditReportDetailed);

	/**
	 * 更新数据
	 * 2016年2月24日
	 * By 侯志斌
	 * @param creditLoanDetailedOne
	 * @return
	 */
	public int updateData(CreditLoanDetailedOne creditLoanDetailedOne);

	/**
	 * 删除实体对象
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record
	 * @return 返回数值操作结果
	 */
	public int delete(CreditLoanDetailedOne record);


	

}