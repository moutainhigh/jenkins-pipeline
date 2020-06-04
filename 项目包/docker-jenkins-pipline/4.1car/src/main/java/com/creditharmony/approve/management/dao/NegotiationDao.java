package com.creditharmony.approve.management.dao;

import java.util.List;

import com.creditharmony.approve.management.entity.Negotiation;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 退回协商dao
 * @Class Name NegotiationDao
 * @author wanglidong
 * @Create In 2016年1月13日
 */
@LoanBatisDao
public interface NegotiationDao extends CrudDao<Negotiation>{
    
	/**
     * 获取一级协商码
     * 2015年12月10日
     * By wanglidong
     * @param negLel 一级协商码类型
     * @return 一级协商码
     */
	public List<Negotiation> getNegotiationCodeOne(String negLel);
	
	/**
	 * 获取二级协商码
	 * 2015年12月10日
	 * By wanglidong
	 * @param id 二级协商码父id
	 * @return 二级协商码
	 */
	public List<Negotiation> getNegotiationCodeTwo(String id);
	
	/**
	 * 根据参数（协商码 或 协商名）查询数据
	 * 2016年1月27日
	 * By 李文勇
	 * @param negotiation
	 * @return
	 */
	public List<Negotiation> findByParam(Negotiation negotiation);
}