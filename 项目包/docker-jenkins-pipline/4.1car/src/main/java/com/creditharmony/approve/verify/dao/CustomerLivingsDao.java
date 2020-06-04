package com.creditharmony.approve.verify.dao;

import com.creditharmony.approve.verify.entity.CustomerLivings;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 客户居住情况
 * @Class Name CustomerLivingsDao
 * @author 李文勇
 * @Create In 2015年12月10日
 */
@LoanBatisDao
public interface CustomerLivingsDao {

	/**
	 * 2016年1月18日
	 * By 李文勇
	 * @param record
	 * @return 返回操作成功数
	 */
    public int insertSelective(CustomerLivings record);

    /**
     * 查看弹出画面用
     * 2015年12月10日
     * By 李文勇
     * @param customerLivings
     * @return 返回居住情况对象
     */
    public CustomerLivings viewByLoanCode(CustomerLivings customerLivings);
}