package com.creditharmony.approve.carloan.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.carloan.entity.CarAuditResult;
import com.creditharmony.approve.newCar.entity.NewCarAuditResult;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
/**
 * 
 * @Class Name CarAuditResultDao
 * @author 李静辉
 * @Create In 2016年1月22日
 */ 
@LoanBatisDao
public interface CarAuditResultDao extends CrudDao<NewCarAuditResult>{
    
	/**
	 * 根据loanCode获取审核记录列表(升序)
	 * @param loanCode
	 * @return 审核记录列表
	 * 
	 * @author shenshikuo
	 * @time 2016年1月22日 下午6:14:05
	 */
    public List<CarAuditResult> findCarAuditResultsByLoanCode(String loanCode);
    
    /**
     * 插入审核结果
     * 2017年3月29日
     * By 李高远
     * @param carAuditResult
     * @return 
     */
    public int insertCarAuditResult(CarAuditResult carAuditResult);
    
    /**
     * 根据借款编号获取 最终终审 通过的记录
     * 2016年2月18日
     * By 申诗阔
     * @param map
     * @return 最终 终审通过 的记录
     */
    public CarAuditResult getLastThroughRecord(Map<String, Object> map);
    
    /**
     * 根据借款编码和审核类型获得附条件通过的记录等
     * 2017年3月30日
     * By 李高远
     * @param map
     * @return
     */
    public CarAuditResult selectLastByLoanCodeAndCheckType(Map<String, String> map);
}