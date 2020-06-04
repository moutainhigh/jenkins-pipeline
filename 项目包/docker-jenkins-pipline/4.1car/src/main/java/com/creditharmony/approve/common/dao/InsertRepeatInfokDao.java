package com.creditharmony.approve.common.dao;

import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 查重预准备节点
 * @Class Name InsertRepeatInfokDao
 * @author 刘燕军
 * @Create In 2016年1月18日
 */
@LoanBatisDao
public interface InsertRepeatInfokDao extends CrudDao<CityInfo> {
	
	/**
	 * 查重电话信息准备第一次
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 插入的行数
	 */
	public int insertPhoneInfoFirst(String loanCode);
	
	/**
	 * 查重电话信息准备其他
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 插入的行数
	 */
	public int insertPhoneInfoOther(String loanCode);
	
	/**
	 * 查重单位名称准备
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 插入的行数
	 */ 
	public int insertWorkNameInfo(String loanCode);
	
	/**
	 * 查重单位地址首次准备
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 插入的行数
	 */
	public int insertWorkAddressFirst(String loanCode);
	
	/**
	 * 查重单位地址其他准备
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 插入的行数
	 */
	public int insertWorkAddressOther(String loanCode);
	
	/**
	 * 更新共借人表
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateCobo(String loanCode);
	
	/**
	 * 更新配偶表
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateMate(String loanCode);
	
	/**
	 * 更新联系人表
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateContact(String loanCode);
	
	/**单位信息表
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateCompany(String loanCode);
	
	/**单位表
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateWork(String loanCode);
	
	/**电话号码表
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateTelnum(String loanCode);
	
	/**关系人表
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateTelContact(String loanCode);
	
	/**本人核实电话信息更新
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateMyTel(String loanCode);
	
	/**本人核实家庭固话信息更新
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateMyFamilyTel(String loanCode);
	
	/**本人核实居住地址信息更新
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateMyFamilyAddress(String loanCode);
	
	/**法人保证人信息更新
	 * 2016年1月18日
	 * By 安艳东
	 * @param loanCode
	 * @return 更新的行数
	 */
	public int updateCompManage(String loanCode);
	

}
