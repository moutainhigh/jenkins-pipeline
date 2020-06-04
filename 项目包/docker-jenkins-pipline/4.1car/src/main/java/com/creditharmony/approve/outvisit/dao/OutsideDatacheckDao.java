package com.creditharmony.approve.outvisit.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.outvisit.entity.OutsideDatacheck;
import com.creditharmony.approve.outvisit.entity.ex.OutsideDatacheckEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 外访核查dao
 * @author wanglidong
 * @Class Name OutsideDatacheckDao
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface OutsideDatacheckDao  {

	/**
	 * 添加外访核查
	 * 2015年12月7日
	 * By wanglidong
	 * @param outsideDatacheck 需要插入的外访核查数据
	 * @return
	 */
	public int insertVisitCheck(OutsideDatacheck outsideDatacheck);
	
	/**
	 * 信审出具外访说明
	 * 2016年1月7日
	 * By wanglidong
	 * @param map 查询参数
	 * @return
	 */
	public List<OutsideDatacheckEx> getVisitCheckInfo(Map<String, String> map);

	/**
	 * 获取外访核查
	 * 2016年1月7日
	 * By wanglidong
	 * @param map 查询参数
	 */
	public OutsideDatacheckEx getOutsideDatacheck(Map<String, String> map);
	
	/**
	 * 
	 * 2016年1月8日
	 * By wanglidong
	 * @param outsideDatacheck 外访数据实体类
	 */
	public int updateVisitCheck(OutsideDatacheck outsideDatacheck);

	/**
	 * 判断外访核查是否保存
	 * 2016年5月17日
	 * By wanglidong
	 * @param param
	 * @return
	 */
	public int checkOutsideDatacheck(VerifyParamEx param);

	/**
	 * 判断是否发起过外访
	 * 2016年7月21日
	 * By 刘燕军
	 * @param param
	 * @return 外访标志
	 */
	public String getOutVist(VerifyParamEx param);
}