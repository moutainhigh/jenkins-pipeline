package com.creditharmony.approve.antifraud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.antifraud.dao.AntifraudOutsideSurveyDao;
import com.creditharmony.approve.antifraud.entity.AntifraudOutsideSurvey;
import com.creditharmony.core.service.CoreManager;

/**
 * 外部调查 service
 * @Class Name OutsideResearchService
 * @author 赖敏
 * @Create In 2015年11月30日
 */
@Service
public class OutsideResearchService extends CoreManager<AntifraudOutsideSurveyDao, AntifraudOutsideSurvey>{
	@Autowired
	private AntifraudOutsideSurveyDao antifraudOutsideSurveyDao;
	
	/**
	 * 添加外部调查
	 * 2015年11月30日
	 * By 赖敏
	 * @param outsideSurvey
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void insertSurvey(AntifraudOutsideSurvey outsideSurvey){
		outsideSurvey.preInsert();
		super.dao.insert(outsideSurvey);
	}
	
	/**
	 * 修改外部调查
	 * 2015年11月30日
	 * By 赖敏
	 * @param outsideSurvey
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void updateSurvey(AntifraudOutsideSurvey outsideSurvey){
		outsideSurvey.preUpdate();
		super.dao.update(outsideSurvey);
	}
	
	/**
	 * 获取单条数据
	 * 2016年1月22日
	 * By 赖敏
	 * @param outsideSurvey
	 * @return 单个外部调查
	 */
	public AntifraudOutsideSurvey getSurvey(AntifraudOutsideSurvey outsideSurvey){
		return super.dao.get(outsideSurvey);
	}

	/**
	 * 获取进件时间
	 * 2016年4月7日
	 * By wanglidong
	 * @param loancode 借款编码
	 */
	public String getIntoTime(String loanCode) {
		String intoTime = antifraudOutsideSurveyDao.getIntoTime(loanCode);
		return intoTime;
	}

}
