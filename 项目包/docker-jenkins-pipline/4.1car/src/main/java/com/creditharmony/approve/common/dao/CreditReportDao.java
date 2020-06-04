package com.creditharmony.approve.common.dao;

import java.util.List;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * @Class Name CreditReportDao
 * @author 黄维
 * @Create In 2015年12月2日
 * 征信报告
 */
@LoanBatisDao
public interface CreditReportDao extends CrudDao<CreditReportRisk> {

	/**
	 * 根据借款编号获取征信报告信息,按修改时间查询最新数据
	 * 2016年5月10日
	 * @param creditReportRisk
	 */
	public List<CreditReportRisk> getPersonCreditReportDetailedInfo(CreditReportRisk creditReportRisk);
	/**
	 * 根据借款编号获取征信报告信息
	 * 2015年12月2日
	 * By 黄维
	 * @param creditReportRisk
	 */
	public List<CreditReportRisk> getPersonCreditReportDetailedByCode(CreditReportRisk creditReportRisk);
	
	/**
	 * 根据借款编号、客户id，获取单条个人祥版征信记录
	 * 2016年5月3日
	 * By 王浩
	 * @param creditReportRisk
	 * @return 
	 */
	public List<CreditReportRisk> getSingleCreditDetailedByCustomer(CreditReportRisk creditReportRisk);

	/**
	 * 2015年12月4日
	 * By 黄维
	 * @param creditReportRisk 
	 */
	public int asyncSaveCreditReportRiskInfo(CreditReportRisk creditReportRisk);
	
	/**
	 * 根据ID更新数据
	 * 2015年12月12日
	 * By 李文勇
	 * @param creditReportRiskEx
	 * @return
	 */
	public int updataById(CreditReportRisk creditReportRisk);

    /**
     * 通过条件获取对应的报告信息
     * 2015年12月24日
     * By 刘燕军
     * @param paran
     * @return
     */
    public List<CreditReportRisk> findCreditRisk(VerifyParamEx paran);
	
	/**
	 * 
	 * 2016年5月3日
	 * By 李文勇
	 * @param id
	 * @return
	 */
    public int deleteById(String id);
    
    /**
     * 根据借款编号获取征信集合
     * 2016年6月11日
     * xiaoniu.hu
     * @param applyId 
     * @return
     */
    public List<CreditReportRisk> getCreditList(String applyId);

}
