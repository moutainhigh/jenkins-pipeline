package com.creditharmony.approve.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.approve.common.entity.LoanPrdTypeEntity;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 汇金产品类型管理-Dao
 * 
 * @Class Name LoanPrdTypeDao
 * @author 李静辉
 * @Create In 2015年11月30日
 */
@LoanBatisDao
public interface LoanPrdTypeDao {
	
	/**
	 * 检索产品类型的键值对
	 * 
	 * @return List<LoanPrdTypeEntity>                   
	 */                                                  
	List<LoanPrdTypeEntity> selPrdTypeKV(@Param(value = "productTypeStatus") String productTypeStatus);
	
	/**
	 * 根据产品类型ID检索产品类型名称
	 * 
	 * @param id
	 * @return String
	 */
	String selPrdTypeNamebyId(@Param(value = "productTypeCode") String productTypeCode);
}