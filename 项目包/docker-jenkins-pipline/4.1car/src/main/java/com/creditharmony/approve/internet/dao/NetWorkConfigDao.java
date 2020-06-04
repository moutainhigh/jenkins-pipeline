package com.creditharmony.approve.internet.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.internet.entity.NetWorkConfig;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 专网查询（专用）
 * @Class Name NetWorkConfigDao
 * @author 刘燕军
 * @Create In 2015年12月22日
 */
@LoanBatisDao
public interface NetWorkConfigDao extends CrudDao<NetWorkConfig>{
	
	/**
	 * 根据检测类型  获取对应的检查网站
	 * 2015年12月22日
	 * By 刘燕军
	 * @param type
	 * @return 对应的网站
	 */
	public List<NetWorkConfig> findOutsideNetInfoEx(String type);
	
	/**
	 * 根据借款人的id获取对应的检查网站
	 * 2016年3月2日
	 * By 刘燕军
	 * @param relId
	 * @return 对应的网站
	 */
	public List<Map<String, String>> findOutsideNet(String relId);
	
	/**
	 * 根据借款人的id获取对应的检查网站
	 * 2016年3月2日
	 * By 刘燕军
	 * @param relId
	 * @return 对应的网站
	 */
	public List<Map<String, String>> findOutsideNetTelCom(String id);
	
}
