package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.ex.PhoneRecordingEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneWorkEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneWorkProvesEx;
import com.creditharmony.approve.phone.entity.WorkTelNum;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 单位-单位电话信息
 * @Class Name WorkTelNumDao
 * @author 刘燕军
 * @Create In 2015年12月19日
 */
@LoanBatisDao
public interface WorkTelNumDao extends CrudDao<WorkTelNum>{
	
	/**
	 * 保存单位电话信息
	 * 2016年1月14日
	 * By 王浩
	 * @param workTelNum
	 * @return 保存数据条数
	 */	
	public int insertWorkTelNum(WorkTelNum workTelNum);
	
	/**
	 * 添加单位电话
	 * 2016年3月1日
	 * By wanglidong
	 * @param workTelNum
	 * @return
	 */
	public int insertWorkTel(PhoneWorkEx phoneWorkEx);
	
    /**
     * 根据id删除对应的信息
     * 2016年1月14日
     * By 刘燕军
     * @param id
     * @return 删除记录数
     */
    public int deleteById(String id);
	
    /**
     * 根据workId删除对应的信息
     * 2016年1月14日
     * By 刘燕军
     * @param id
     * @return 删除记录数
     */
    public int deleteByWorkId(String workId);

    /**
     * 修改电话照会所有单位电话
     * 2016年3月2日
     * By wanglidong
     * @param phoneWorkListEx
     */
	public void updataWorkTel(PhoneWorkEx phoneWorkEx);

	/**
	 * 修改电话照会所有电话录音
	 * 2016年3月2日
	 * By wanglidong
	 * @param phoneWorkListEx
	 */
	public void updataTelRecordAll(List<PhoneRecordingEx> phoneRecord);

	/**
	 * 修改反欺诈电话照会所有联系人（单位，家庭）
	 * 2016年3月3日
	 * By wanglidong
	 * @param phoneWorkProvesEx
	 */
	public void updataContacts(List<PhoneWorkProvesEx> phoneWorkProves);
	
	/**
	 * 更新单位信息中的异常情况
	 * 2016年3月23日
	 * By 刘燕军
	 * @param map
	 */
	public void updateNums(Map<String, List<WorkTelNum>> map);
	
	/**
	 * 通过id获取来源
	 * 2016年5月21日
	 * By 刘燕军
	 * @param id
	 * @return 来源
	 */
	public String getSrc(String id);
}
