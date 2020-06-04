package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditLiveInfo;

/**
 * 居住信息操作
 * @Class Name CreditliveInfoDao
 * @author 李文勇
 * @Create In 2016年2月24日
 */
@LoanBatisDao
public interface CreditliveInfoDao extends CrudDao<CreditLiveInfo> {
	
	/**
	 * 保存
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return 返回操作成功条数
	 */
	public int saveData(CreditLiveInfo param);
	
	/**
	 * 根据关联ID获取数据
	 * 2016年2月18日
	 * By 李文勇
	 * @param relationId
	 * @return 返回结果list
	 */
	public List<CreditLiveInfo> getByParam(String relationId);

	/**
	 * 更新数据
	 * 2016年2月18日
	 * By 李文勇
	 * @param param
	 * @return 返回操作成功条数
	 */
	public int updataById(CreditLiveInfo param);
	
	/**
	 * 删除数据
	 * 2016年2月19日
	 * By 李文勇
	 * @param param
	 * @return 返回操作成功条数
	 */
	public int deleteData(CreditLiveInfo param);
}