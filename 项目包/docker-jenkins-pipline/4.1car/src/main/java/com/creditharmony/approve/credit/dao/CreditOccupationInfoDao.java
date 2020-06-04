package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditOccupationInfo;

/**
 * 详版信用报告职业信息
 * @Class Name CreditOccupationInfoDao
 * @author 李文勇
 * @Create In 2016年1月6日
 */
@LoanBatisDao
public interface CreditOccupationInfoDao extends CrudDao<CreditOccupationInfo>{

	/**
	 * 保存数据
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public int saveData(CreditOccupationInfo param);
	
	/**
	 * 根据关联ID获取数据
	 * 2016年2月18日
	 * By 李文勇
	 * @param relationId
	 * @return
	 */
	public List<CreditOccupationInfo> getByParam(String relationId);
	
	/**
	 * 更新数据
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public int updataById(CreditOccupationInfo param);
	
	/**
	 * 删除数据
	 * 2016年2月19日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public int deleteData(CreditOccupationInfo param);
}
