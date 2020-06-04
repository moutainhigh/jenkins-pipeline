package com.creditharmony.approve.management.dao;

import java.util.List;

import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.management.entity.Refuse;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 拒绝信息Dao
 * @Class Name GlRefuseDao
 * @author 刘燕军
 * @Create In 2015年12月4日
 */
@LoanBatisDao
public interface GlRefuseDao extends CrudDao<GlRefuse>{
	
	/**
	 * 提报反欺诈中，查询指定的提报信息
	 * 2015年12月4日
	 * By 刘燕军
	 * @param none
	 * @return 返回结果集list
	 */
	public List<GlRefuse> findGlRefuses();
	
	/**
	 * 根据parentID 获取数据
	 * 2015年12月15日
	 * By 李文勇
	 * @param parentId
	 * @return 返回结果集list
	 */
	public List<GlRefuse> getDataByParentId(GlRefuse param);
	
	/**
	 * 获取所有的拒绝码
	 * 2015年12月24日
	 * By 刘燕军
	 * @param none
	 * @return 返回结果集list
	 */
	public List<GlRefuse> findAllGlRefuses();
	
	/**
	 * 根据parentCode 获取数据
	 * 2016年1月12日
	 * By 赖敏
	 * @param parentCode
	 * @return 返回结果集list
	 */
	public List<GlRefuse> getDataByParentCode(String parentCode);
	
	/**
	 * 根据parentCode 获取数据
	 * 2016年1月12日
	 * By 赖敏
	 * @param parentCode
	 * @return 返回结果集list
	 */
	public List<GlRefuse> getNewDataByParentCode(String parentCode);
	
	
	/**
	 * 2015年12月14日
	 * By 李志毅
	 * @param refuse
	 * @return 返回结果集list
	 */
	public List<GlRefuse> findByParentIdsLike(GlRefuse glRefuse);
	
	/**
	 * 根据ID更新数据
	 * 2015年12月16日
	 * By 李文勇
	 * @param refuse
	 * @return 返回更新成功条数
	 */
	public int updateDataById(GlRefuse glRefuse);
	
	/**
	 * 根据ID查询数据
	 * 2015年12月16日
	 * By 李文勇
	 * @param id
	 * @return 返回查询实体
	 */
	public Refuse getDataById(String id);
	
	/**
	 * 添加数据
	 * 2015年12月16日
	 * By 李文勇
	 * @param refuse
	 * @return 返回操作成功条数
	 */
	public int insertData(GlRefuse glRefuse);
	
	/**
	 * 根据参数查询（refuseName，refuseCode）数据
	 * 2016年1月26日
	 * By 李文勇
	 * @param refuse
	 * @return
	 */
	public List<GlRefuse> findByParam(GlRefuse glRefuse);
	
	/**
	 * 通过拒借码获取对应的名字
	 * 2016年2月26日
	 * By 刘燕军
	 * @param refuseCode 拒借码
	 * @return 拒借名称
	 */
	public String findName(String refuseCode);
}
