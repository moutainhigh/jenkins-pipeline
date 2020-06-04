package com.creditharmony.approve.internet.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.internet.entity.PrivateNetworkCheck;
import com.creditharmony.approve.phone.entity.ex.TelAuditWorkEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 专网核查dao
 * 
 * @Class Name PrivateNetworkCheckDao
 * @author 刘燕军
 * @Create In 2016年2月4日
 * @update in 2016-10-25
 */
@LoanBatisDao
public interface PrivateNetworkCheckDao extends CrudDao<PrivateNetworkCheck> {

	/**
	 * 插入几行信息 2016年2月4日 By 刘燕军
	 * 
	 * @param map
	 * @return 插入的行数
	 */
	public int insertNetwork(Map<String, Object> map);

	/**
	 * 通过条件查找需要的信息 2016年2月4日 By 刘燕军
	 * 
	 * @param param
	 * @return 信息集合
	 */
	public List<PrivateNetworkCheck> findList(VerifyParamEx param);

	/**
	 * 通过id更新对应的信息 2016年2月4日 By 刘燕军
	 * 
	 * @param map
	 * @return 更新的行数
	 */
	public int updateNetworks(Map<String, Object> map);

	/**
	 * 通过关联id删除对应的单位信息 2016年2月22日 By 刘燕军
	 * 
	 * @param content
	 * @return 删除的行数
	 */
	public int deleteByRelId(String id, String name);

	/**
	 * 通过关联id删除对应的单位信息 2016年2月22日 By 刘燕军
	 * 
	 * @param content
	 * @return 删除的行数
	 */
	public int deleteByRelIdNew(String id);

	/**
	 * 通过借款编号、单位名称，把旧的单位名称改为新的 2016年2月22日 By 刘燕军
	 * 
	 * @param content
	 * @return 删除的行数
	 */
	public int updateByCodeName(TelAuditWorkEx work);

	/**
	 * 插入几行信息 2016年2月4日 By 刘燕军
	 * 
	 * @param map
	 * @return 插入的行数
	 */
	public int insertNetList(Map<String, Object> map);

	public List<PrivateNetworkCheck> findListByCodeType(Map<String, Object> map);

	public List<PrivateNetworkCheck> findListByWorkId(Map<String, String> mapp);

	public int updateNetworksNew(PrivateNetworkCheck check);

}
