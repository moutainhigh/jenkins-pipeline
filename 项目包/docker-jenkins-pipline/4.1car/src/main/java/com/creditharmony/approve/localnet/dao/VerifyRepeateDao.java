package com.creditharmony.approve.localnet.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.common.entity.RepeateParam;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 号码，单位名称查重相关操作
 * @Class Name VerifyDecisionDao
 * @author 刘燕军
 * @Create In 2016年5月20日
 */
@LoanBatisDao
public interface VerifyRepeateDao extends CrudDao<RepeateParam>{
	
	/**
	 * 判断该号码是否在无效表中
	 * 2016年5月20日
	 * By 刘燕军
	 * @param phone
	 * @return  存在的数量
	 */
	public int getPhoneUse(String phone);
	
	/**
	 * 通过参数获取需要的信息
	 * 2016年5月20日
	 * By 刘燕军
	 * @param map
	 * @return 参数信息 
	 */
	public RepeateParam getParam(Map<String, String> map);
	
	/**
	 * 根据参数进行查重
	 * 2016年5月20日
	 * By 刘燕军
	 * @param map
	 * @return 插入的行数
	 */
	public int repeateCheck(RepeateParam  param);
	
	/**
	 * 根据参数进行查重
	 * 2016年5月20日
	 * By 刘燕军
	 * @param map
	 * @return 插入的行数
	 */
	public int repeateCheckName(RepeateParam  param);
	
	/**
	 * 删除对应的查重信息
	 * 2016年5月21日
	 * By 刘燕军 
	 * @param map 删除需要的参数信息
	 */
	public int deleteRepeate(RepeateParam  param); 
	
	/**
	 * 获取查重信息参数
	 * 2016年5月21日
	 * By 刘燕军 
	 * @param map 参数集合
	 */
	public List<RepeateParam> getParamList(Map<String, String> map); 
}
