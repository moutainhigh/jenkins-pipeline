package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.phone.entity.DhzhBrhsDhxx;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerNumEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 本人核实 电话号码信息
 * @Class Name DhzhBrhsDhxxDao
 * @author 王浩
 * @Create In 2016年1月11日
 */
@LoanBatisDao
public interface DhzhBrhsDhxxDao extends CrudDao<DhzhBrhsDhxx> {
	
	/**
	 * 保存电话信息的某几个字段
	 * 2016年1月11日
	 * By 王浩
	 * @param record
	 * @return 保存记录条数
	 */
	public int insertSelective(DhzhBrhsDhxx record);
    
	/**	
	 * 根据本人核实id，查询出对应的电话号码
	 * 2016年1月11日
	 * By 王浩
	 * @param params
	 * @return 返回电话号码list
	 */
	public List<TelCheckBorrowerNumEx> getBrhsDhxxListByRid(Map<String,Object> params);
    
	/**
	 * 更新某电话号码信息中某几个字段
	 * 2016年1月11日
	 * By 王浩
	 * @param brhsDhxx
	 * @return 保存记录条数
	 */
	public int updateSelective(DhzhBrhsDhxx brhsDhxx);
	
	/**
	 * 删除
	 * by 董超
	 */
	public int deleteByPrimaryKey(String id);
	
}