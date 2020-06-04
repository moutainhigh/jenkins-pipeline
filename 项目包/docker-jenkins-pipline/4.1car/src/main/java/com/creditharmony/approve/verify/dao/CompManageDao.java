package com.creditharmony.approve.verify.dao;

import java.util.List;

import com.creditharmony.approve.verify.entity.CompManage;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 资料审核  经营信息
 * @Class Name CompManageDao
 * @author 赵春香
 * @Create In 2016年9月18日
 */
@LoanBatisDao
public interface CompManageDao {
    /**
     * 经营信息
     * 2016年1月28日
     * By 路志友
     * @param zlshGrzj
     * @return list
     */
	public List<CompManage> getListByLoanCode(String  loanCode);
}