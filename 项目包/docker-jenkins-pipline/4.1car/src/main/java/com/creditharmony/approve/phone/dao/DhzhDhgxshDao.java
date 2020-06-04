package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.ex.PhoneWorkProvesEx;
import com.creditharmony.approve.internet.entity.ex.UploadPhoneResult;
import com.creditharmony.approve.phone.entity.DhzhDhgxsh;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckResultEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 电话关系审核联系人
 * 
 * @Class Name DhzhDhgxshDao
 * @author 王浩
 * @Create In 2016年1月11日
 */
@LoanBatisDao
public interface DhzhDhgxshDao extends CrudDao<DhzhDhgxsh> {

	/**
	 * 根据id删除记录 2016年1月11日 By 王浩
	 * 
	 * @param id
	 * @return 删除记录条数
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 更新记录中某几个字段 2016年1月11日 By 王浩
	 * 
	 * @param record
	 * @return 保存记录条数
	 */
	public int insertSelective(DhzhDhgxsh record);

	/**
	 * 根据条件查找联系人 2015年12月5日 By 王浩
	 * 
	 * @param params
	 * @return 返回联系人list
	 */
	public List<TelCheckContactPersonEx> getListByCodeAndType(Map<String, Object> params);

	/**
	 * 单位核实 2015年12月21日 By 李文勇
	 * 
	 * @param param
	 * @return 返回单位核查结果list
	 */
	public List<UploadPhoneResult> getWork(VerifyParamEx param);

	/**
	 * 联系人核实 2015年12月21日 By 李文勇
	 * 
	 * @param param
	 * @return 返回联系人list
	 */
	public List<TelCheckResultEx> getContact(VerifyParamEx param);

	/**
	 * 本人核实 2015年12月21日 By 李文勇
	 * 
	 * @param param
	 * @return 返回联系人list
	 */
	public List<TelCheckResultEx> getSelf(VerifyParamEx param);

	/**
	 * 获取家庭联系人、工作证明人列表 2015年12月14日 By 赖敏
	 * 
	 * @param params
	 * @return 返回联系人list
	 */
	public List<TelCheckContactPersonEx> getContacts(Map<String, Object> params);

	/**
	 * 获取反欺诈-电话关系审核 count 2015年12月14日 By 赖敏
	 * 
	 * @param params
	 * @return 返回count记录数
	 */
	public int getAntifraudCount(Map<String, Object> params);

	/**
	 * 根据ID修改评估结果 2015年12月14日 By 赖敏
	 * 
	 * @param record
	 * @return none
	 */
	public void updateByKeyAndit(DhzhDhgxsh record);

	/**
	 * 获取需要查重的手机号码 2016年1月11日 By 刘燕军
	 * 
	 * @param param
	 * @return 联系人list
	 */
	public List<DhzhDhgxsh> findPhone(VerifyParamEx param);

	/**
	 * 更新编辑标识 2016年1月26日 By 赖敏
	 * 
	 * @param params
	 */
	public void updateEditRemark(Map<String, Object> params);

	/**
	 * 保存联系人（工作或家庭） 2016年3月3日 By wanglidong
	 * 
	 * @param loanCode
	 */
	public void addContacts(PhoneWorkProvesEx phoneWorkProvesEx);

	/**
	 * 删除联系人 2016年3月3日 By wanglidong
	 * 
	 * @param id
	 */
	public int delProves(String id);

	/**
	 * 修改反欺诈电话照会所有联系人（单位，家庭） 2016年3月3日 By wanglidong
	 * 
	 * @param phoneWorkProvesEx
	 */
	public void updataContacts(PhoneWorkProvesEx phoneWorkProvesEx);

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public int updateNewApply(DhzhDhgxsh dhzhDhgxsh);

}