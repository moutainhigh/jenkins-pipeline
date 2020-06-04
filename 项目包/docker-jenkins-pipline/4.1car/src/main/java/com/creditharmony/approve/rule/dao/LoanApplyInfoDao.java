package com.creditharmony.approve.rule.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.rule.applyengine.client.LoanApply;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 进件引擎的数据模型的数据库操作dao
 * @Class Name LoanApplyInfoDao
 * @author 王浩
 * @Create In 2016年1月12日
 */
@LoanBatisDao
public interface LoanApplyInfoDao {

	/**
	 * 查询借款申请的相关信息
	 * 2016年1月12日
	 * By 王浩
	 * @param loanCode
	 * @return 返回LoanApply对象
	 */
	public List<LoanApply> selectByLoanCode(String loanCode);
	
	/**
	 * 查询是否有共借人
	 * 2016年1月12日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String findLegalPersonExist(Map<String, Object> map);
	
	/**
	 * 查询是否有共借人
	 * 2016年1月12日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String findCoborrowerExist(Map<String, Object> map);
	
	/**
	 * 查询本人是否有借款未还清
	 * 2016年1月12日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String findHisLoanUnpaid(Map<String, Object> map);
	
	/**
	 * 查询配偶是否有借款未还清
	 * 2016年1月12日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String findCoupleHisLoanUnpaid(Map<String, Object> map);
	
	/**
	 * 查询本人作为共借人，是否还有借款未还清
	 * 2016年1月12日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String findHisLoanUnpaidAsCoborrow(Map<String, Object> map);
	
	/**
	 * 查询配偶作为共借人，是否还有借款未还清
	 * 2016年1月12日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String findCoupleHisLoanUnpaidAsCoborrow(Map<String, Object> map);	
	
	/**
	 * 配偶当前借款是否逾期
	 * 2016年1月12日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String findCoupleCurrentLoanOverdue(Map<String, Object> map);
	
	/**
	 * 联系人中是否有父母
	 * 2016年3月22日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String getContactParents(Map<String, Object> map);
	
	/**
	 * 是否录入征信报告
	 * 2016年3月22日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String getNoCreditReport(Map<String, Object> map);
	
	/**
	 * 是否征信白户(信用卡及贷款)
	 * 2016年3月22日
	 * By 王浩
	 * @param map
	 * @return 返回true或者false
	 */
	public String getNoCreditCardAndLoan(Map<String, Object> map);
	
	/**
	 * 同一经营主体是否有同一经营主体以老板借/小微企业借批借尚未结清
	 * 2016年4月6日
	 * By 王浩
	 * @param map
	 * @return 
	 */
	public boolean getCompanyHisLoanUnpaid(Map<String, Object> map);
	
}