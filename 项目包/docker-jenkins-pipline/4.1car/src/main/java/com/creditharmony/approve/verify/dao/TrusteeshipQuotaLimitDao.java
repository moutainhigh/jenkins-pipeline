package com.creditharmony.approve.verify.dao;

import com.creditharmony.approve.verify.entity.TrusteeshipQuotaLimit;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * TG操作
 * @Class Name TrusteeshipQuotaLimitDao
 * @author 李文勇
 * @Create In 2016年5月17日
 */
@LoanBatisDao
public interface TrusteeshipQuotaLimitDao extends CrudDao<TrusteeshipQuotaLimit>{

	/**
	 * 查询TG数据（启用的）
	 * 2016年5月17日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public TrusteeshipQuotaLimit findTG(TrusteeshipQuotaLimit param);
	
	/**
	 * 更新TG
	 * 2016年5月17日
	 * By 李文勇
	 * @param param
	 * @return
	 */
	public int updateTG(TrusteeshipQuotaLimit param);
}
