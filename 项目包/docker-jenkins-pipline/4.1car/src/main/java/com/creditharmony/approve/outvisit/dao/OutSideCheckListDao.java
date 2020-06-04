package com.creditharmony.approve.outvisit.dao;

import java.util.List;

import com.creditharmony.approve.outvisit.entity.OutsideCheckList;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 外访_外访任务清单
 * @Class Name OutSideCheckListDao
 * @author 赖敏
 * @Create In 2015年12月25日
 */
@LoanBatisDao
public interface OutSideCheckListDao extends CrudDao<OutsideCheckList> {
	
	/**
	 * 添加外访任务清单
	 * 2016年1月19日
	 * By 赖敏
	 * @param checkList
	 * @return 更新的行数
	 */
	public int insertSelective(OutsideCheckList checkList);
	
	/**
	 * 根据借款编号获取外访任务清单
	 * 2016年6月19日
	 * xiaoniu.hu
	 * @param loanCode 借款编号
	 * @return 外访任务清单
	 */
	public List<OutsideCheckList> getCheckListByLoanCode(VerifyParamEx param);
	
}
