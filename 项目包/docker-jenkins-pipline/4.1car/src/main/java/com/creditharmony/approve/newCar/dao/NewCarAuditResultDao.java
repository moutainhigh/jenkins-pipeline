package com.creditharmony.approve.newCar.dao;

import java.util.List;
import java.util.Map;

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
public interface NewCarAuditResultDao extends CrudDao<NewCarAuditResult>{
    
	/**
	 * 根据loanCode获取审核记录列表(升序)
	 * @param loanCode
	 * @return 审核记录列表
	 * 
	 * @author shenshikuo
	 * @time 2016年1月22日 下午6:14:05
	 */
    public List<NewCarAuditResult> findCarAuditResultsByLoanCode(String loanCode);

	public void insertCarAuditResult(NewCarAuditResult auditResult);

	public NewCarAuditResult selectLastByLoanCodeAndCheckType(
			Map<String, String> map);

	public NewCarAuditResult getLastThroughRecord(Map<String, Object> map);

}