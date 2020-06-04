package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.phone.entity.DhzhGxhtDhxx;
import com.creditharmony.approve.phone.entity.ex.TelCheckBusiContractEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 电话照会 购销合同 号码信息
 * @Class Name DhzhGxhtDhxxDao
 * @author 王浩
 * @Create In 2016年1月11日
 */
@LoanBatisDao
public interface DhzhGxhtDhxxDao extends CrudDao<DhzhGxhtDhxx> {

	/**
	 * 保存记录
	 * 2016年1月11日
	 * By 王浩
	 * @param record
	 * @return 保存记录条数
	 */
	public int insertSelective(DhzhGxhtDhxx record);
	
	/**
	 * 根据条件获取list
	 * 2016年1月11日
	 * By 王浩
	 * @param params
	 * @return 购销合同号码list
	 */
	public List<TelCheckBusiContractEx> findListByLoanCode(Map<String,Object> params);
	
	/**
	 * 更新记录
	 * 2016年1月11日
	 * By 王浩
	 * @param record
	 * @return 更新记录条数
	 */
	public int updateSelective(DhzhGxhtDhxx record);
	
	/**
	 * 根据资料审核-购销合同的id，更新相关联的记录
	 * 2016年2月19日
	 * By 王浩
	 * @param record
	 * @return 更新记录数
	 */
	public int updateSelectiveByRid(DhzhGxhtDhxx record);
	
	/**
	 * 根据资料审核删除购销合同
	 * 2016年5月3日
	 * By 李文勇
	 * @param rId
	 * @return
	 */
	public int deleteByRid(String rId);
}