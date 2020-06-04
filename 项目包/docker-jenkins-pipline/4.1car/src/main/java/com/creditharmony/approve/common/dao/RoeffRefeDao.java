package com.creditharmony.approve.common.dao;

import java.util.List;

import com.creditharmony.approve.common.entity.RoeffRefe;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 系数参照表（期数、费率、前期综合费）DAO
 * @Class Name RoeffRefeDao
 * @author 刘燕军
 * @Create In 2016年4月22日
 */
@LoanBatisDao
public interface RoeffRefeDao extends CrudDao<RoeffRefe>{
	
	/**
	 * 获取所有的分期
	 * 2016年4月22日
	 * By 刘燕军
	 * @return 分期对应的list
	 */
	public List<Integer> getAllMonths(RoeffRefe refe);
	
	/**
	 * 获取分期对应的费率
	 * 2016年4月22日
	 * By 刘燕军
	 * @return 费率对应的list
	 */
	public List<Double> getOneTypeRate(int months);
	
	/**
	 * 风险定价：根据风险等级和期数获取费率
	 * 2016年7月6日
	 * By 王浩
	 * @param months 期数
	 * @param riskLevel 风险等级
	 * @return 
	 */
	public List<Double> getRateByRisk(RoeffRefe refe);
	
}
