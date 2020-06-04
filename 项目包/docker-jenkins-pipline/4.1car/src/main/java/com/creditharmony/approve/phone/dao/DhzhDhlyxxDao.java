package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.adapter.service.outbound.bean.OutboundCallStateInBean;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 电话录音
 * @Class Name DhzhDhlyxxDao
 * @author 赖敏
 * @Create In 2016年1月6日
 */
@LoanBatisDao
public interface DhzhDhlyxxDao extends CrudDao<DhzhDhlyxx>{
    
	/**
	 * 保存电话录音信息
	 * 2015年12月10日
	 * By 刘燕军
	 * @param record
	 * @return 保存记录条数
	 */
    public int insertSelective(DhzhDhlyxx record);
    
    /**
     * 修改接听描述
     * 2016年1月6日
     * By 赖敏
     * @param dhlyxx
     * @return none
     */
    public void updateByKeyAndit(DhzhDhlyxx dhlyxx);
    
    /**
     * 删除电话录音
     * 2016年1月6日
     * By 赖敏
     * @param id
     * @return 删除记录条数
     */
    public int delRecord(String id);
    /**
     * 删除电话录音By关联ID
     * 2016年3月4日
     * By 董超
     * @param rid
     * @return 删除记录条数
     */
    public int delRecordByRID(String rId);
    
    /**
     * 选择性更新某几个字段
     * 2016年1月11日
     * By 王浩
     * @param dhlyxx
     * @return 保存记录条数
     */
    public int updateSelective(DhzhDhlyxx dhlyxx); 
    
    /**
     * 查询114电话录音信息
     * 2016年3月28日
     * By 刘燕军
     * @param param
     * @return 电话录音信息集合
     */
    public List<DhzhDhlyxx> findByParam(VerifyParamEx param);
    
    /**
     * 更新所有的电话录音信息
     * 2016年3月29日
     * By 刘燕军
     * @param records
     */
    public void updateByList(Map<String, List<DhzhDhlyxx>> map);
    
    /**
     * 更新电话录音中的唯一标识
     * 2016年3月31日
     * By 刘燕军
     * @param bean
     */
    public void updateFromRecord(OutboundCallStateInBean bean);
	
	/**
	 * 根据关联ID获取电话录音信息
	 * 2016年4月14日
	 * By 李文勇
	 * @param rGxId
	 * @return 返回结果集
	 */
	public List<DhzhDhlyxx> getByRid(String rGxId);
	

}