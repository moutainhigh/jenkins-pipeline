package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.phone.entity.DhzhDhgxbrshd;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 本人核实居住地址
 * @Class Name DhzhDhgxbrshdDao
 * @author 李文勇
 * @Create In 2016年3月24日
 */
@LoanBatisDao
public interface DhzhDhgxbrshdDao extends CrudDao<DhzhDhgxbrshd> {
	
	/**
	 * 
	 * 2016年3月24日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(String id);
	
	/**
	 * 
	 * 2016年3月24日
	 * By 李文勇
	 * @param record
	 * @return
	 */
	public int insert(DhzhDhgxbrshd record);

	/**
	 * 
	 * 2016年3月24日
	 * By 李文勇
	 * @param record
	 * @return
	 */
	public int insertSelective(DhzhDhgxbrshd record);

	/**
	 * 
	 * 2016年3月24日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	public DhzhDhgxbrshd selectByPrimaryKey(String id);

	/**
	 * 
	 * 2016年3月24日
	 * By 李文勇
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(DhzhDhgxbrshd record);

	/**
	 * 
	 * 2016年3月24日
	 * By 李文勇
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKey(DhzhDhgxbrshd record);
	
	/**
	 * 根据关联ID获取居住地址
	 * 2016年3月24日
	 * By 李文勇
	 * @param rId
	 * @return 结果集
	 */
	public List<DhzhDhgxbrshd> getByRid(String rId);
	
	/**
	 * 更新传入的所有的居住地址判定
	 * 2016年3月31日
	 * By 刘燕军
	 * @param map
	 * @return 更新的行数
	 */
	public int updateByList(Map<String, List<DhzhDhgxbrshd>> map);
}