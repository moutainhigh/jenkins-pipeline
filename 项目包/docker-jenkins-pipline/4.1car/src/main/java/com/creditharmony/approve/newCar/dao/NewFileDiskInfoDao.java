package com.creditharmony.approve.newCar.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.newCar.entity.NewFileDiskInfo;
import com.creditharmony.approve.verify.entity.FileDiskInfo;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 查询文件存放磁盘信息
 * @author 张建雄
 * @Class Name DiskInfoDao
 * @Create In 2016年3月29日
 */
@LoanBatisDao
public interface NewFileDiskInfoDao extends CrudDao<NewFileDiskInfo> {
	
	/**
	 * 查询文件存放磁盘名信息
	 * @param queryTime 进件时间
	 * @return 磁盘名
	 */
	public Map<String,String> findDiskInfobyQueryTime(String queryTime);
	
	/**
	 * 查询文件存放磁盘名信息
	 * 2016年5月5日
	 * By 王浩
	 * @param queryTime 当前时间
	 * @return 磁盘信息类
	 */
	public List<NewFileDiskInfo> getDiskInfoListByQueryTime(String queryTime);
	
	/**
	 * 查询汇金索引以及部件名
	 * 2016年5月5日
	 * By 王浩
	 * @param queryTime 当前时间
	 * @param sysFlag 系统标识
	 * @return 磁盘信息类
	 */
	public Map<String,String> getIndexComponentByQueryTime(String queryTime, String sysFlag);
	
	/**
     * 通过指定参数查找磁盘信息
     * @author zhanghao
     * @CREAT IN 2016年03月24日
     * @param param  queryTime
     * @return FileDiskInfo
     * 
     */
    public NewFileDiskInfo getByParam(Map<String,Object> param);
	
}
