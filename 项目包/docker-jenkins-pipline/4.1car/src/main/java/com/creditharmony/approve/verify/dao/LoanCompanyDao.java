package com.creditharmony.approve.verify.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.phone.entity.TelAuditWork;
import com.creditharmony.approve.phone.entity.ex.TelAuditWorkEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.verify.entity.LoanCompany;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 公司信息dao
 * 
 * @Class Name LoanCompanyDao
 * @author 刘燕军
 * @Create In 2016年1月19日
 * @update in 2016-09-20
 */
@LoanBatisDao
public interface LoanCompanyDao extends CrudDao<LoanCompany> {

	/**
	 * 删除数据 2016年1月28日 By 李文勇
	 * 
	 * @param id
	 * @return 返回操作成功数
	 */
	public int deleteByPrimaryKey(String id);

	/**
	 * 插入数据 2016年1月28日 By 李文勇
	 * 
	 * @param record
	 * @return 返回操作成功数
	 */
	public int insertSelective(LoanCompany record);

	/**
	 * 查询数据 2016年1月28日 By 李文勇
	 * 
	 * @param id
	 * @return 返回对象
	 */
	public LoanCompany selectByPrimaryKey(String id);

	/**
	 * 更新数据 2016年1月28日 By 李文勇
	 * 
	 * @param record
	 * @return 返回对象
	 */
	public int updateByPrimaryKeySelective(LoanCompany record);

	/**
	 * 更新数据 2016年1月28日 By 李文勇
	 * 
	 * @param record
	 * @return 操作成功数
	 */
	public int updateByPrimaryKey(LoanCompany record);

	/**
	 * 查看弹出画面用 2015年12月10日 By 李文勇
	 * 
	 * @param loanCompany
	 * @return 返回对象
	 */
	public LoanCompany viewGetByLoanCode(LoanCompany loanCompany);
	
	/**
	 * 查看弹出画面用 2016年10月10日
	 * 
	 * @param loanCompany
	 * @return 返回对象
	 */
	public LoanCompany viewGetByLoanCodeNew(LoanCompany loanCompany);

	/**
	 * 获取数据 2016年1月28日 By 李文勇
	 * 
	 * @param params
	 * @return 返回对象
	 */
	public LoanCompany getUnitInfoByParam(Map<String, Object> params);

	/**
	 * 获取汇金端录入的单位信息 2015年12月9日 By 赖敏
	 * 
	 * @param params
	 *            关联类型(0 主借人，1共借人，2配偶)
	 * @return 返回对象集合
	 */
	public List<TelCheckCompanyEx> getListByCodeAndType(Map<String, Object> params);

	/**
	 * 外网审核 2015年12月8日 By 刘燕军
	 * 
	 * @param param
	 * @return 返回对象集合
	 */
	public List<TelAuditWork> selectByOutsideCheck(VerifyParamEx param);

	/**
	 * 根据借款编码查出主借人、共借人所有单位信息 2016年1月19日 By 王浩
	 * 
	 * @param params
	 * @return 返回对象集合
	 */
	public List<TelCheckCompanyEx> getAllCustomerCompany(Map<String, Object> params);

	/**
	 * 外网修改单位名称时，同步修改汇金处的单位名称 2016年6月21日 By 刘燕军
	 * 
	 * @param work
	 *            单位名称
	 * @return 修改的行数
	 */
	public int updateWorkName(TelAuditWorkEx work);

	/**
	 * 保存网查结果中的法人保证人的相关网查结果
	 * 
	 * @create in 2016-09-20
	 * @param companyInfo
	 * @return
	 */
	public int updateNetCheckResult(LoanCompany companyInfo);

}