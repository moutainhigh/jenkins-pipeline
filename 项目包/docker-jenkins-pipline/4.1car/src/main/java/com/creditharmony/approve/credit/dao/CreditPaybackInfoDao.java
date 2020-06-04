package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.approve.credit.entity.CreditPaybackInfo;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 征信保证人代偿信息
 * @Class Name CreditPaybackInfoDao
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@LoanBatisDao
public interface CreditPaybackInfoDao extends CrudDao<CreditPaybackInfo>{
	
	/**
	 * 根据征信保证人代偿信息id删除征信保证人代偿信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 征信保证人代偿信息id
	 * @return 执行条数
	 */
	public int deleteByPrimaryKey(String id);
    
	/**
	 * 新增征信保证人代偿信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 征信保证人代偿信息
	 * @return 执行条数
	 */
    public int insertCreditPaybackInfo(CreditPaybackInfo record);

    /**
     * 根据信用卡信息检索保证人代偿信息List
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 保证人代偿信息List
     */
    public List<CreditPaybackInfo> selectByCreditPaybackInfo(CreditPaybackInfo record);

	/**
	 * 根据个人征信简版id删除保证人代偿信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param relationId 个人征信简版id
	 * @return 执行条数
	 */
    public int deleteByRelationId(String relationId);
	
	/**
	 * 查询所有数据（详版查询画面用）
	 * 2016年2月15日
	 * By 李文勇
	 * @param param
	 * @return 结果集list
	 */
	public List<CreditPaybackInfo> getAllByParam(CreditReportDetailed creditReportDetailed);
	
	/**
	 * 保存数据
	 * 2016年2月15日
	 * By 李文勇
	 * @param param
	 * @return 执行条数
	 */
	public int insertData(CreditPaybackInfo param);
	
	/**
	 * 删除数据
	 * 2016年2月16日
	 * By 李文勇
	 * @param param
	 * @return 执行条数
	 */
	public int deleteData(CreditPaybackInfo param);
	
	/**
	 * 更新数据
	 * 2016年2月16日
	 * By 李文勇
	 * @param param
	 * @return 执行条数
	 */
	public int updataDataById(CreditPaybackInfo param);
}