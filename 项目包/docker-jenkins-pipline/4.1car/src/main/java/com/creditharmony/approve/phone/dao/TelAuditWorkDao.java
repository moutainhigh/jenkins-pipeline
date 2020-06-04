package com.creditharmony.approve.phone.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.internet.entity.ex.OutNetEx;
import com.creditharmony.approve.phone.entity.TelAuditWork;
import com.creditharmony.approve.phone.entity.ex.TelAuditWorkEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.phone.entity.ex.WorkTelNumEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 单位信息审核的dao
 * @Class Name TelAuditWorkDao
 * @author 王浩
 * @Create In 2016年1月11日
 */
@LoanBatisDao
public interface TelAuditWorkDao  extends CrudDao<TelAuditWork>{
	
	/**
	 * 删除单位信息
	 * 2016年2月18日
	 * By 刘燕军
	 * @param id
	 * @return
	 */
	public int deleteWork(String id);
	
    /**
     * 外网审核 单位信息
     * 2015年12月8日
     * By 刘燕军
     * @param param
     * @return 单位信息list
     */
	public List<TelAuditWorkEx> findCompanys(VerifyParamEx param);

    /**
     * 保存外网审核的单位信息
     * 2015年12月10日
     * By 刘燕军
     * @param record
     * @return 保存记录条数
     */
	public int insertSelective(TelAuditWork record);
    
    /**
     * 通过扩展类id更新数据
     * 2015年12月19日
     * By 刘燕军
     * @param telAuditWorkEx
     * @return 保存记录条数
     */
    public int updateById(TelAuditWorkEx telAuditWorkEx);
    
    /**
     * 决策页面 通过参数 获取指定的公司信息
     * 2015年12月22日
     * By 刘燕军
     * @param param
     * @return 单位信息list
     */    
    public List<TelAuditWorkEx> findTelAuditWorkEx(VerifyParamEx param); 
    
    /**
     * 选择性更新字段
     * 2015年12月21日
     * By 王浩
     * @param telCheckCompanyEx
     * @return 保存记录条数
     */
    public int updateByCompanyId(TelCheckCompanyEx telCheckCompanyEx);

    /**
     * 根据参数查询单位信息list
     * 2016年1月11日
     * By 王浩
     * @param map
     * @return 单位信息list
     */
    public List<TelCheckCompanyEx> getListByParams(Map<String, Object> map);
    
    /**
     * 根据单位信息id，关联查出单位电话信息
     * 2016年1月11日
     * By 王浩
     * @param params
     * @return 单位电话list
     */
    public List<WorkTelNumEx> getTelNumListByRid(Map<String,Object> params);
    
    /**
     * 获取反欺诈-单位信息核查列表
     * 2015年12月9日
     * By 赖敏
     * @param params 借款编码及审核类型
     * @return 单位信息list
     */
    public List<TelCheckCompanyEx> getUnitInfoByLoanCode(Map<String,Object> params);
    
    /**
     * 获取反欺诈-单位信息 count
     * 2015年12月9日
     * By 赖敏
     * @param params 借款编码及审核类型
     * @return 保存记录条数
     */
    public int getAntifraudCount(Map<String,Object> params);
    
    /**
     * 添加 反欺诈-单位核查
     * 2015年12月9日
     * By 赖敏
     * @param record
     */
    public void insertAntifraud(TelAuditWork record); 
    
    /**
     * 获取点击决策时把要查重的单位名称放入查重池中
     * 2016年1月5日
     * By 刘燕军
     * @param param
     * @return
     */    
    public int insertWorkNameInfo(String loanCode);
    
    /**
     * 获取点击决策时把要查重的电话放入查重池中
     * 2016年1月5日
     * By 刘燕军
     * @param param
     * @return
     */
    public int insertTelInfo(String loanCode);
    
    /**
     * 获取点击决策时把要查重的地址放入查重池中
     * 2016年1月5日
     * By 刘燕军
     * @param param
     * @return
     */
    public int insertAddressInfo(String loanCode);
    
    /**
     * 更新编辑标识
     * 2016年1月26日
     * By 赖敏
     * @param params
     */
    public void updateEditRemark(Map<String, Object> params);
    
    /**
     * 决策时 电话照会页签校验
     * 2016年1月25日
     * By 刘燕军
     * @param param
     * @return
     */
    public int checkExceptionInfo(VerifyParamEx param);
    
    /**
     * 新版申请表
     * 决策时 电话照会页签校验
     * 2016年1月25日
     * By 刘燕军
     * @param param
     * @return
     */
    public int checkExceptionInfoNew(VerifyParamEx param);
     
     /**
      * 决策时 电话照会页签校验
      * 2016年1月25日
      * By 刘燕军
      * @param param
      * @return
      */
     public int checkExceptionData(Map<String, String> map);
     
     /**
      * 决策页面外部核查信息
      * 2016年2月18日
      * By 刘燕军
      * @param param
      * @return 核查的详细信息
      */
     public List<OutNetEx> findOutNet(VerifyParamEx param);
     
     /**
      * 更新对应的标志
      * 2016年4月19日
      * By 刘燕军
      * @param loanCode 借款编号
      * @return 更新的行数
      */
     public int upateFlag(String loanCode);
     
     /**
      * 获取复制到复议时的单位id
      * 2016年6月21日
      * By 刘燕军
      * @param id 复议单位id
      * @return
      */
     public String findWorkId(String id);
     
     /**
      * 获取复制到复议时的单位id
      * 2016年10月11日
      * @param id 复议单位id
      * @return
      */
     public String findWorkIdNew(String id);
     
}