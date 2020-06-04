package com.creditharmony.approve.credit.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditLoanDetailedTwo;

/**
 * 征信祥版贷款信息二数据库操作dao
 * @Class Name CreditLoanDetailedTwoDao
 * @author 侯志斌
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditLoanDetailedTwoDao {
	
	/**
	 * 按主键删除实体
	 * 2016年2月24日
	 * By 侯志斌
	 * @param id
	 * @return 返回数值操作结果
	 */
	public int deleteByPrimaryKey(String id);

    /**
	 * 增加贷款二信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record
	 * @return 返回数值操作结果
	 */
	public int insert(CreditLoanDetailedTwo record);

    /**
	 * 动态增加贷款二信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record
	 * @return 返回数值操作结果
	 */
	public int insertSelective(CreditLoanDetailedTwo record);

    /**
	 * 查询贷款二信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param id 
	 * @return 返回贷款实体
	 */
	public CreditLoanDetailedTwo selectByPrimaryKey(String id);

    /**
	 * 动态修改贷款二信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record
	 * @return 返回数值操作结果
	 */
	public int updateByPrimaryKeySelective(CreditLoanDetailedTwo record);

    /**
	 * 修改贷款二信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param record
	 * @return 返回数值操作结果
	 */
	public int updateByPrimaryKey(CreditLoanDetailedTwo record);
    
    /**
	 * 按关联ID删除实体
	 * 2016年2月24日
	 * By 侯志斌
	 * @param id
	 * @return 返回数值操作结果
	 */
	public int deleteByRelationId(String id);
    
    /**
	 * 根据参数获取信息二
	 * 2016年2月24日
	 * By 侯志斌
	 * @param params
	 * @return 贷款信息列表
	 */
	public List<CreditLoanDetailedTwo> findByParams(Map<String,Object> params);
}