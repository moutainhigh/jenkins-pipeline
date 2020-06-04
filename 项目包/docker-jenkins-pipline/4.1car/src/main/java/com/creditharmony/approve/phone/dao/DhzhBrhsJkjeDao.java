package com.creditharmony.approve.phone.dao;

import com.creditharmony.approve.phone.entity.DhzhBrhsJkje;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 本人核实 同业借款金额
 * @Class Name DhzhBrhsJkjeDao
 * @author 王浩
 * @Create In 2016年1月11日
 */
@LoanBatisDao
public interface DhzhBrhsJkjeDao extends CrudDao<DhzhBrhsJkje> {
	
	/**
	 * 根据id删除记录
	 * 2016年1月11日
	 * By 王浩
	 * @param id
	 * @return 删除记录条数
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 保存借款金额信息
	 * 2016年1月11日
	 * By 王浩
	 * @param record
	 * @return 保存记录条数
	 */
	public int insertSelective(DhzhBrhsJkje record);

	/**
	 * 更新某几个字段
	 * 2016年1月11日
	 * By 王浩
	 * @param record
	 * @return 保存记录条数
	 */
	public int updateByPrimaryKeySelective(DhzhBrhsJkje record);

	/**
	 * 根据关联id删除数据
	 * 2016年3月24日
	 * By 李文勇
	 * @param rId
	 * @return 返回操作成功数
	 */
	public int deleteByRid(String rId);
}