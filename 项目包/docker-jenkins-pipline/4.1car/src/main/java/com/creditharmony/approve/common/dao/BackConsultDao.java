package com.creditharmony.approve.common.dao;

import com.creditharmony.approve.common.entity.BackConsult;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 退回协商dao
 * @Class Name BackConsultDao
 * @author wanglidong
 * @Create In 2015年12月8日
 */
@LoanBatisDao
public interface BackConsultDao extends CrudDao<BackConsult> {

    /**
     * 保存退回协商
     * 2015年12月8日
     * By wanglidong
     * @param backConsult
     * @return
     */
	public int addBackConsult(BackConsult backConsult);

	/**
	 * 查看退回协商
	 * 2015年12月30日
	 * By wanglidong
	 * @param id
	 * @return
	 */
	public BackConsult getBackConsultView(String id);
}