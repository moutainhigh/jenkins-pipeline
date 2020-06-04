package com.creditharmony.approve.credit.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCardDetailedTwo;

/**
 * 征信祥版信用卡信息二数据库操作dao
 * @Class Name CreditCardDetailedTwoDao
 * @author 侯志斌
 * @Create In 2016年2月2日
 */
@LoanBatisDao
public interface CreditCardDetailedTwoDao {
	
	/**
	 * 按主键删除实体
	 * 2016年2月2日
	 * By 侯志斌
	 * @param id
	 * @return 返回数值操作结果
	 */
	public int deleteByPrimaryKey(String id);

    /**
	 * 增加信用卡二信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡二信息
	 * @return 返回数值操作结果
	 */
	public int insert(CreditCardDetailedTwo record);

    /**
	 * 动态增加信用卡二信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡二信息
	 * @return 返回数值操作结果
	 */
	public int insertSelective(CreditCardDetailedTwo record);

    /**
	 * 查询信用卡二信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡二信息
	 * @return 返回信用卡实体
	 */
	public CreditCardDetailedTwo selectByPrimaryKey(String id);

    /**
	 * 动态修改信用卡二信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡二信息
	 * @return 返回数值操作结果
	 */
	public int updateByPrimaryKeySelective(CreditCardDetailedTwo record);

    /**
	 * 修改信用卡二信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡二信息
	 * @return 返回数值操作结果
	 */
	public int updateByPrimaryKey(CreditCardDetailedTwo record);

    /**
	 * 根据参数获取信息二
	 * 2016年2月2日
	 * By 侯志斌
	 * @param filter2
	 * @return 信用卡信息列表
	 */
	public List<CreditCardDetailedTwo> findByParams(Map<String, Object> filter2);
	
	/**
	 * 按关联ID删除实体
	 * 2016年2月2日
	 * By 侯志斌
	 * @param id
	 * @return 返回数值操作结果
	 */
	public int deleteByRelationId(String id);
}