package com.creditharmony.approve.credit.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;
import com.creditharmony.approve.credit.entity.CreditQueryRecord;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;

/**
 * 征信查询记录
 * @Class Name CreditQueryRecordDao
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@LoanBatisDao
public interface CreditQueryRecordDao extends CrudDao<CreditQueryRecord>{
	
	/**
	 * 根据征信查询记录id删除征信查询记录
	 * 2016年2月3日
	 * By zhanghu
	 * @param id 征信查询记录id
	 * @return 执行条数
	 */
	public int deleteByPrimaryKey(String id);
    
	/**
	 * 新增征信查询记录信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param record 征信查询记录信息
	 * @return 执行条数
	 */
    public int insertCreditQueryRecord(CreditQueryRecord record);

    /**
     * 根据信用卡信息检索查询信息List
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 查询信息List
     */
    public List<CreditQueryRecord> selectByCreditQueryRecord(CreditQueryRecord record);

	/**
	 * 根据个人征信简版id删除查询信息
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
	public List<CreditQueryRecord> getAllByParam(CreditReportDetailed param);
	
	/**
	 * 保存数据
	 * 2016年2月15日
	 * By 李文勇
	 * @param param
	 * @return 执行条数
	 */
	public int insertData(CreditQueryRecord param);
	
	/**
	 * 删除数据
	 * 2016年2月16日
	 * By 李文勇
	 * @param param
	 * @return 执行条数
	 */
	public int deleteData(CreditQueryRecord param);
	
	/**
	 * 更新数据
	 * 2016年2月16日
	 * By 李文勇
	 * @param param
	 * @return 执行条数
	 */
	public int updataDataById(CreditQueryRecord param);
}