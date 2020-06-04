package com.creditharmony.approve.users.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.users.dao.OrgInfoDao;
import com.creditharmony.approve.users.entity.OrgInfo;
import com.creditharmony.core.service.CoreManager;

/**
 * 组织机构服务
 * @Class Name OrgInfoService
 * @author 王彬彬
 * @Create In 2016年3月11日
 */
@Component
@Service
public class OrgInfoService extends CoreManager<OrgInfoDao, OrgInfo> {
	
	/**
	 * 组织机构
	 * 2016年3月11日
	 * By 王彬彬
	 * @param id 机构ID
	 * @return 组织机构
	 */
	public OrgInfo getOrg(String id){
		return dao.get(id);
	}
	
	/**
	 * 保存组织机构
	 * 2016年3月11日
	 * By 王彬彬
	 * @param orgInfo 保存的组织机构
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void saveOrg(OrgInfo orgInfo){
		dao.insert(orgInfo);
	}
	
	/**
	 * 更新组织机构
	 * 2016年3月11日
	 * By 王彬彬
	 * @param orgInfo 更新对象
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void update(OrgInfo orgInfo){
		dao.update(orgInfo);
	}
	
	/**
	 * 删除组织机构
	 * 2016年3月11日
	 * By 王彬彬
	 * @param id 删除机构id
	 */
	@Transactional(readOnly = false, value = "loanTransactionManager")
	public void delete(String id){
		dao.delete(id);
	}
	
	
}
