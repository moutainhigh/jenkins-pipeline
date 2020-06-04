package com.creditharmony.approve.newCar.service;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.carloan.entity.ex.CarVerifyListEx;
import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.newCar.entity.NewApplyDetailInfokEx;
import com.creditharmony.approve.newCar.entity.NewCarExamineEntity;
import com.creditharmony.approve.verify.entity.ex.DictEx;
import com.creditharmony.core.persistence.Page;

public interface NewCarCheckService{
	
	/** 
	 * 复审待办列表
	 * @param page
	 * @param entity
	 * @return
	 */
	public Page<NewCarExamineEntity> getReCheckList(Page<NewCarExamineEntity> page,List<String> statusList);

	public void waitHandle(CarVerifyBusinessView view);

	public Page<CarVerifyListEx> findPage(Page<CarVerifyListEx> page,
			Map<String, Object> map);

	public int findCount(Map<String, Object> map);

	public List<DictEx> findAllBackCheckDicts(String carBackReason);

	public Page<NewCarExamineEntity> getOrderReCheckList(
			Page<NewCarExamineEntity> page, NewCarExamineEntity entity);

	public Page<NewCarExamineEntity> getFinalCheckList(
			Page<NewCarExamineEntity> page, NewCarExamineEntity entity);

	public NewApplyDetailInfokEx getCheckInfo(String loanCode);

}
