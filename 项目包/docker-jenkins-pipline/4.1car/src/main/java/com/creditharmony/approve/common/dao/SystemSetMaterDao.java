/**
 * @Probject Name: chp-loan
 * @Path: com.creditharmony.loan.common.daoSystemSetMaterDao.java
 * @Create By 王彬彬
 * @Create In 2016年3月6日 下午2:04:30
 */
package com.creditharmony.approve.common.dao;

import java.util.Map;

import com.creditharmony.approve.common.entity.SystemSetting;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * @Class Name SystemSetMaterDao
 * @author 王彬彬
 * @Create In 2016年3月6日
 */
@LoanBatisDao
public interface SystemSetMaterDao extends CrudDao<SystemSetting> {
	public void getSettingByFlag(Map<String,String> map);
	
	public SystemSetting get(SystemSetting systemSetting);
	
	/**
	 * 通过标识修改系统配置
	 * 2016年3月28日
	 * By zhangfeng
	 * @param ss
	 */
	public void updateBySysFlag(SystemSetting ss);
}
