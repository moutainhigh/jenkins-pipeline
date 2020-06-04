package com.creditharmony.approve.verify.dao;

import java.math.BigDecimal;

import com.creditharmony.approve.verify.entity.KinnobuQuotaLimit;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 金信处理
 * @Class Name KinnobuQuotaLimitDao
 * @author 李文勇
 * @Create In 2016年3月2日
 */
@LoanBatisDao
public interface KinnobuQuotaLimitDao extends CrudDao<KinnobuQuotaLimit>{

	/**
	 * 获取金信数据(启用中的)
	 * 2016年3月2日
	 * By 李文勇
	 * @param flag
	 * @return 返回实体
	 */
	public KinnobuQuotaLimit getData(BigDecimal flag);
	
	/**
	 * 更新金信数据
	 * 2016年3月2日
	 * By 李文勇
	 * @param param
	 * @return 返回操作成功数
	 */
	public int updateData(KinnobuQuotaLimit param);
}
