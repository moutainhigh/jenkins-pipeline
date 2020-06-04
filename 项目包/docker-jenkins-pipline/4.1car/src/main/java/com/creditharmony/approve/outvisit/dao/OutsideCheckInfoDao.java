package com.creditharmony.approve.outvisit.dao;

import java.util.Map;
import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;
import com.creditharmony.approve.outvisit.entity.OutsideCheckList;
import com.creditharmony.approve.outvisit.entity.ex.OutsideCheckInfoEx;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.LoanBatisDao;

/**
 * 外访_外访任务详情
 * @Class Name OutsideCheckInfoDao
 * @author 赖敏
 * @Create In 2015年12月7日
 */
@LoanBatisDao
public interface OutsideCheckInfoDao extends CrudDao<OutsideCheckInfo>{
	
	/**
	 * 插入外访_外访任务详情
	 * 2015年12月28日
	 * By 赖敏
	 * @param checkInfo
	 * @return 改变的行数
	 */
    public int insertSelective(OutsideCheckInfo checkInfo);
    
    /**
     * 根据历史ID获取外访清单
     * 2015年12月28日
     * By 赖敏
     * @param relationId 关联ID(变更历史表)
     * @return 外访清单
     */
    public OutsideCheckInfo getInfosByRid(OutsideCheckList checkList);
    
    /**
     * 查询主借人居住地址、公司地址
     * 2015年12月31日
     * By 赖敏
     * @param params
     * @return 居住地址、公司地址
     */
    public OutsideCheckInfoEx getAddressSimple(Map<String, Object> params);
    
    /**
     * 获取外访经营地址作为保证人的经营地址
     * 2016年6月22日
     * xiaoniu.hu
     * @param param
     * @return
     */
    public AuditResult getVisitAddress(String loanCode);
    
}