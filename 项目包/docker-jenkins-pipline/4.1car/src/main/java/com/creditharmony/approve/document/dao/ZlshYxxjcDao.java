package com.creditharmony.approve.document.dao;

import java.util.List;

import com.creditharmony.approve.document.entity.ZlshYxxjc;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 资料审核 有效性检查
 * @Class Name ZlshYxxjcDao
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
@LoanBatisDao
public interface ZlshYxxjcDao extends CrudDao<ZlshYxxjc>{
	
	/**
	 * 插入方法
	 * 2016年1月18日
	 * By 路志友
	 * @param record
	 * @return int
	 */
	public int insert(ZlshYxxjc record);
    
	/**
	 * 插入方法
	 * 2016年1月18日
	 * By 路志友
	 * @param record
	 * @return int
	 */
	public int insertSelective(ZlshYxxjc record);
	
    /**
     * 通过loanCode获取对应的实体
     * 2016年1月28日
     * By 路志友
     * @param param
     * @return list
     */
	public List<ZlshYxxjc> findZlshYxxjc(VerifyParamEx param);
	
	
	/**
	 * 资料核查页面用
	 * 2016年3月11日
	 * By 李文勇
	 * @param param
	 * @return 对象
	 */
	public List<ZlshYxxjc> findYxx(VerifyParamEx param);

	/**
	 * 根据借款编号获取所有有效性信息
	 * 2016年5月23日
	 * By 王浩
	 * @param record
	 * @return 
	 */
	public List<ZlshYxxjc> findYxxByCode(ZlshYxxjc record);
	
}