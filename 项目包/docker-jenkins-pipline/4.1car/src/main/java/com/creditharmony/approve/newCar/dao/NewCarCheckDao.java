package com.creditharmony.approve.newCar.dao;

import java.util.List;

import com.creditharmony.approve.carloan.entity.ex.ApplyDetailInfokEx;
import com.creditharmony.approve.newCar.entity.NewCarExamineEntity;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 复审、初审
 * @Class Name NewCarCheckDao
 * @Create In 2017年3月27日
 */
@LoanBatisDao
public interface NewCarCheckDao extends CrudDao<NewCarExamineEntity> {

	/** 
	 * 复审待办列表
	 * @param page
	 * @param entity
	 * @return
	 */
	public List<NewCarExamineEntity> getReCheckList(PageBounds pageBounds,List<String> statusList);

	public PageList<NewCarExamineEntity> getOrderReCheckList(
			PageBounds pageBounds, NewCarExamineEntity entity);

	public PageList<NewCarExamineEntity> getFinalCheckList(
			PageBounds pageBounds, NewCarExamineEntity entity);

	public ApplyDetailInfokEx getCheckInfo(String loanCode);


}
