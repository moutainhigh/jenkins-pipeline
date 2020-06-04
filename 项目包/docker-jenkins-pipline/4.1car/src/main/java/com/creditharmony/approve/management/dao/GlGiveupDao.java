package com.creditharmony.approve.management.dao;

import java.util.List;

import com.creditharmony.approve.management.entity.GlGiveup;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 客户放弃Dao
 * @Class Name GlGiveupDao
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@LoanBatisDao
public interface GlGiveupDao extends CrudDao<GlGiveup>{
	
	/**
	 * 查询所有的一级码
	 * 2015年12月5日
	 * By 刘燕军
	 * @param none
	 * @return 返回结果集list
	 */
	public List<GlGiveup> findGlGiveups();
	
	/**
	 * 通过一级码获取对应的二级码
	 * 2015年12月5日
	 * By 刘燕军
	 * @param giveUpCode  一级码的giveUpCode
	 * @return 返回结果集list
	 */
	public List<GlGiveup> findGlGiveupsByParentId(String id);
	
	/**
	 * 根据参数（放弃码名称  或  放弃码编码 ）查询数据
	 * 2016年1月27日
	 * By 李文勇
	 * @param glGiveup
	 * @return 返回结果集list
	 */
	public List<GlGiveup> findByParam(GlGiveup glGiveup);
	
	/**
	 * 根据客户放弃码，获取放弃原因
	 * 2016年4月8日
	 * By 王浩
	 * @param abandonCode
	 * @return 
	 */
	public String getNameByCode(String abandonCode);
	
}
