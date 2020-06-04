package com.creditharmony.approve.verify.dao;

import com.creditharmony.approve.verify.entity.JkProducts;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

import java.util.List;

/**
 * 获取产品对应的信息
 * @Class Name JkProductsDao
 * @author 刘燕军
 * @Create In 2016年1月19日
 */
@LoanBatisDao
public interface JkProductsDao extends CrudDao<JkProducts>{
	
	/**
	 * 获取全部可用产品
	 * 2015年12月22日
	 * By 李文勇
	 * @return 返回对象集合
	 */
	public List<JkProducts> getAllProducts(String productStatus,String type);
	
	/**
	 * 通过产品code获取产品
	 * 2015年12月24日
	 * By 刘燕军
	 * @param code
	 * @return 费率
	 */
	public String findRate(String code);
	
}