package com.creditharmony.approve.verify.dao;

import com.creditharmony.approve.verify.entity.ProposalRemarks;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 下载意见书备注信息
 * @Class Name ProposalRemarksDao
 * @author 刘燕军
 * @Create In 2015年12月17日
 */
@LoanBatisDao
public interface ProposalRemarksDao extends CrudDao<ProposalRemarks>{
	
	/**
	 * 通过借款编号和申请类型获取对应的备注信息
	 * 2015年12月17日
	 * By 刘燕军
	 * @param param
	 * @return 备注信息
	 */
	public ProposalRemarks getRemark(VerifyParamEx param);
}