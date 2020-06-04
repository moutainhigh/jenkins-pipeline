package com.creditharmony.approve.credit.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditCardDetailedOne;

import com.creditharmony.approve.credit.entity.CreditReportDetailed;

/**
 * 征信祥版信用卡信息一数据库操作dao
 * @Class Name CreditCardDetailedOneDao
 * @author 侯志斌
 * @Create In 2016年2月2日
 */
@LoanBatisDao
public interface CreditCardDetailedOneDao {
   
	/**
	 * 按主键删除实体对象
	 * 2016年2月2日
	 * By 侯志斌
	 * @param id
	 * @return 返回数值操作结果
	 */
   public int deleteByPrimaryKey(String id);

   /**
	 * 增加信用卡一信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡一信息
	 * @return 返回数值操作结果
	 */
   public int insert(CreditCardDetailedOne record);

   /**
	 * 动态增加信用卡一信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡一信息
	 * @return 返回数值操作结果
	 */
   public int insertSelective(CreditCardDetailedOne record);

   /**
	 * 查询信用卡一信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param id 用卡一主键
	 * @return 返回信用卡实体
	 */
   public CreditCardDetailedOne selectByPrimaryKey(String id);

   /**
	 * 动态修改信用卡一信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡一信息
	 * @return 返回数值操作结果
	 */
   public int updateByPrimaryKeySelective(CreditCardDetailedOne record);
   
   /**
	 * 修改信用卡一信息
	 * 2016年2月2日
	 * By 侯志斌
	 * @param record 信用卡一信息
	 * @return 返回数值操作结果
	 */
   public int updateByPrimaryKey(CreditCardDetailedOne record);

    /**
	 * 根据参数获取信息一
	 * 2016年2月2日
	 * By 侯志斌
	 * @param params
	 * @return 信用卡信息列表
	 */
   public List<CreditCardDetailedOne> findByParams(Map<String,Object> params);
   
   /**
	 * 根据参数获取一信息
	 * 2016年2月24日
	 * By 侯志斌
	 * @param creditReportDetailed
	 * @return
	 */
	public List<CreditCardDetailedOne> getAllByParam(CreditReportDetailed creditReportDetailed);
    
    
}