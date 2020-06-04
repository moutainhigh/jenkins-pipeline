package com.creditharmony.approve.verify.dao;

import java.math.BigDecimal;

import com.creditharmony.approve.verify.entity.QuotaLimit;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 添加标识（金信或者chp）
 * 
 * @Class Name QuotaLimitDao
 * @author 李文勇
 * @Create In 2016年4月21日
 * @update in 2016-10-31
 */
@LoanBatisDao
public interface QuotaLimitDao extends CrudDao<QuotaLimit> {

	/**
	 * 获取金信数据(启用中的) 2016年3月2日 By 李文勇
	 * 
	 * @param flag
	 * @return 返回实体
	 */
	public QuotaLimit getData(BigDecimal flag);

	/**
	 * 获取金信数据(启用中的) 2016年10月31日 By 罗俊平
	 * 
	 * @param flag
	 * @return 返回实体
	 */
	public QuotaLimit getDataNew(Integer flag);

	/**
	 * 更新金信数据 2016年3月2日 By 李文勇
	 * 
	 * @param param
	 * @return 返回操作成功数
	 */
	public int updateData(QuotaLimit param);

	/**
	 * 更新金信数据 2016年10月31日 By 罗俊平
	 * 
	 * @param param
	 * @return 返回操作成功数
	 */
	public int updateDataNew(QuotaLimit paramCopy);

}
