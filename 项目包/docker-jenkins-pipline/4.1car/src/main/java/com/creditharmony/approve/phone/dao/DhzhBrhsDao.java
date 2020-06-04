package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.internet.entity.ex.OutSideCheckConEx;
import com.creditharmony.approve.internet.entity.ex.OutsideNetCheckEx;
import com.creditharmony.approve.phone.entity.DhzhBrhs;
import com.creditharmony.approve.phone.entity.DhzhDhgxbrshd;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerInfoEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 电话照会 本人核实
 * @Class Name DhzhBrhsDao
 * @author 王浩
 * @Create In 2016年1月11日
 * @update in 2016-09-27
 */
@LoanBatisDao
public interface DhzhBrhsDao extends CrudDao<DhzhBrhs> {
	
    /**
     * 删除记录
     * 2016年1月11日
     * By 王浩
     * @param id
     * @return 删除记录条数
     */
    public int deleteByPrimaryKey(String id);

    /**
     * 保存记录
     * 2016年1月11日
     * By 王浩
     * @param record
     * @return 保存记录条数
     */
    public int insertSelective(DhzhBrhs record);

    /**
     * 更新记录
     * 2016年1月11日
     * By 王浩
     * @param record
     * @return 保存记录条数
     */
    public int updateByPrimaryKey(DhzhBrhs record);
    
    /**
     * 获取本人核实记录
     * 2015年12月8日
     * By 王浩
     * @param params
     * @return 本人核实list
     */
    public List<TelCheckBorrowerInfoEx> getBorrowerInfo(Map<String,Object> params); 
    
    /**
     * 获取借款编号相同的所有本人核实记录
     * 2015年12月8日
     * By 王浩
     * @param params
     * @return 本人核实list
     */
    public List<TelCheckBorrowerInfoEx> getAllBorrowerInfo(Map<String,Object> params);
    
    /**
     * 外网审核 网查信息
     * 2015年12月19日
     * By 刘燕军
     * @param param
     * @return 网查信息list
     */
    public List<OutsideNetCheckEx>  findOutsideNet(VerifyParamEx param);
    
    /**
     * 外网审核 网查信息
     * 2016年9月23日
     * @param param
     * @return 网查信息list
     */
    public List<OutsideNetCheckEx> findOutsideNetNew(VerifyParamEx param);
    
    /**
     * 外网审核-网查-联系人（手机号码和宅电）
     * 2016年10月22日
     * @param param
     * @return 网查信息list
     */
    public List<OutSideCheckConEx> findOutsideNetConNew(VerifyParamEx param);
    
    /**
     * 通过扩展类id 更新对应的信息
     * 2015年12月19日
     * By 刘燕军
     * @param outsideNetCheckEx
     * @return 保存记录条数
     */
    public int updateById(OutsideNetCheckEx outsideNetCheckEx);
    
    /**
     * 通过扩展类id 更新对应的信息
     * 2015年12月19日
     * By 刘燕军
     * @param outsideNetCheckEx
     * @return 保存记录条数
     */
    public int updatePhoneCheckResult(OutSideCheckConEx outSideCheckConEx); 
    
    /**
     * 通过信息获取是否是风险客户
     * 2016年1月11日
     * By 刘燕军
     * @param param
     * @return
     */
    public List<String> getFlag(VerifyParamEx param); 
    
    /**
     * 获取风险标识
     * 2016年1月20日
     * By 赖敏
     * @param param
     * @return 风险标识
     */
    public String getRiskFlag(VerifyParamEx param);
    
    /**
     * 决策时 外网信息核查
     * 2016年1月25日
     * By 刘燕军
     * @param param
     * @return
     */
    public int checkExceptionOut(VerifyParamEx param);
    
    /**
     * 决策时 外网信息核查
     * 2016年1月25日
     * By 刘燕军
     * @param param
     * @return
     */
    public int checkExceptionOutNew(VerifyParamEx param);
    
    /**
     * 查本人的居住地址信息
     * 2016年3月2日
     * By 董超
     * @param param
     * @return
     */
    public List<DhzhDhgxbrshd> getJzdzByRBorrowerInfoId(String rBorrowerInfoId);
    
    /**
     * 检测本人核实行业类别
     * 2016年6月21日
     * By 刘燕军
     * @param param
     * @return
     */
    public int checkMySelf(VerifyParamEx param);
    
    /**
     * 新版申请表
     * 获取借款的大纲上下限
     * 2015年12月8日
     * By 王浩
     * @param params
     * @return 本人核实
     */
    public TelCheckBorrowerInfoEx selectProductLimit(Map<String, Object> param);
    
}