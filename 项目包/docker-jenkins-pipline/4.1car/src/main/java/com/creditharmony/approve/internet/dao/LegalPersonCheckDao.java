package com.creditharmony.approve.internet.dao;

import java.util.Map;

import com.creditharmony.approve.internet.entity.LegalPersonCheck;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 法人代表人信息dao
 * 
 * @Class Name LegalPersonCheckDao
 * @Create In 2016年11月10日
 */
@LoanBatisDao
public interface LegalPersonCheckDao extends CrudDao<LegalPersonCheck> {

	/**
	 * 判断汇诚是否存在该条数据 2016年11月10日
	 * 
	 * @param mapp
	 * @return 返回对象
	 */
	public LegalPersonCheck getLegalPersonCheckByMap(Map<String, String> mapp);

	/**
	 * 根据loan_code查询汇金保存的值（表：t_jk_comp_manage） 2016年11月10日
	 * 
	 * @param mapp
	 * @return 返回对象
	 */
	public LegalPersonCheck selectByPrimaryKey(Map<String, String> mapp);

	/**
	 * 保存网查结果中的法人保证人的相关网查结果（表：t_jk_wwsh_comp_manage） 2016年11月10日
	 * 
	 * @param legalPersonCheck
	 * @return 操作成功数
	 */
	public int updateByEntity(LegalPersonCheck legalPersonCheck);

	/**
	 * 插入数据（表：t_jk_wwsh_comp_manage） 2016年11月10日
	 * 
	 * @param legalPersonCheck
	 * @return 操作成功数
	 */
	public int insertByEntity(LegalPersonCheck legalPersonCheck);

	/**
	 * 保存网查结果中的法人保证人的相关网查结果
	 * 
	 * @create in 2016-11-10
	 * @param legalPersonCheck
	 * @return
	 */
	public int updateNetCheckResult(LegalPersonCheck legalPersonCheck);

}