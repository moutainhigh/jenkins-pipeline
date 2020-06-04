package com.creditharmony.approve.antifraud.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.approve.antifraud.entity.FqzdhzhBrhsDhxx;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudMeConfirm;
import com.creditharmony.approve.antifraud.entity.ex.PhoneConfirmEx;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 反欺诈-电话照会-本人核实
 * @Class Name FqzdhzhBrhsDhxxDao
 * @author 赖敏
 * @Create In 2015年12月14日
 */
@LoanBatisDao
public interface FqzdhzhBrhsDhxxDao extends CrudDao<FqzdhzhBrhsDhxx>{
	
	/**
	 * 添加本人核实信息
	 * 2015年12月14日
	 * By 赖敏
	 * @param record
	 * @return 更新的行数
	 */
    public int insertSelective(FqzdhzhBrhsDhxx record);
    
    /**
     * 获取本人核实列表
     * 2015年12月14日
     * By 赖敏
     * @param loanCode 借款编码
     * @return 本人核实列表
     */
    public List<AntifraudMeConfirm> getMyConfirm(String loanCode);
    
    /**
     * 根据借款编码修改评估结果
     * 2015年12月14日
     * By 赖敏
     * @param record 
     * @return
     */
    public void updateByKeyAndit(FqzdhzhBrhsDhxx record);
    
    /**
     * 获取反欺诈-本人核实表行数
     * 2016年1月25日
     * By 赖敏
     * @param loanCode 借款编码
     * @return 总行数
     */
    public int getByLoanCode(String loanCode);
    
    /**
     * 更新编辑标识
     * 2016年1月26日
     * By 赖敏
     * @param params
     */
    public void updateEditRemark(Map<String, Object> params);

    /**
     * 反欺诈电话照会本人核实
     * 2016年3月4日
     * By wanglidong
     * @param phoneConfirmEx
     */
	public void addConfirm(PhoneConfirmEx phoneConfirmEx);

	/**
	 * 删除本人核实
	 * 2016年3月4日
	 * By wanglidong
	 * @param id
	 */
	public void delConfirm(String id);
	
	/**
	 * 修改反欺诈电话照会本人核实
	 * 2016年3月4日
	 * By wanglidong
	 * @param phoneConfirmEx
	 */
	public void updataConfirm(PhoneConfirmEx phoneConfirmEx);
}





















