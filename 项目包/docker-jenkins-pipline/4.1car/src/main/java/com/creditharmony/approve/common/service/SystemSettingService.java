package com.creditharmony.approve.common.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.dao.SystemSetMaterDao;
import com.creditharmony.approve.common.entity.SystemSetting;
import com.creditharmony.core.service.CoreManager;


/**
 * 操作系统设置表system_setting
 * @Class Name SystemSetMaterService
 * @author 王浩
 * @Create In 2016年5月19日
 */
@Service
public class SystemSettingService extends CoreManager<SystemSetMaterDao, SystemSetting> {

	/**
	 * 插入记录
	 * 2016年5月19日
	 * By 王浩
	 * @param sys
	 * @return 
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public SystemSetting insertSysSetting(SystemSetting sys) {
		sys.preInsert();
		dao.insert(sys);
		return sys;
	}

	/**
	 * 根据条件查询配置信息实体
	 * 2016年5月19日
	 * By 王浩
	 * @param systemSetting
	 * @return 
	 */
	public SystemSetting get(SystemSetting systemSetting) {
		return dao.get(systemSetting);
	}

	/**
	 * 更新系统设置
	 * 2016年5月19日
	 * By 王浩
	 * @param systemSetting 
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void updateBySysFlag(SystemSetting systemSetting) {
		dao.updateBySysFlag(systemSetting);
	}
}
