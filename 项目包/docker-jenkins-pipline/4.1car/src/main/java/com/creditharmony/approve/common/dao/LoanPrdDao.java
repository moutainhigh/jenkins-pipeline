package com.creditharmony.approve.common.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.approve.common.entity.LoanPrdEntity;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 汇金产品管理-Dao
 * 
 * @Class Name LoanPrdDao
 * @author 李静辉
 * @Create In 2015年11月30日
 */
@LoanBatisDao
public interface LoanPrdDao {
	
	/**
	 * 检索产品的键值对
	 * 
	 * @param key
	 * @return List<LoanPrdEntity>
	 */
	public List<LoanPrdEntity> selPrdKV(@Param(value = "productType") String productType);
	
	/**
	 * 根据产品ID检索产品名称
	 * 
	 * @param id
	 * @return String
	 */
	public String selPrdNamebyId(@Param(value = "productId") String productId);

	public LoanPrdEntity getPrdByTypeAndCode(HashMap<String, String> map);
	

}