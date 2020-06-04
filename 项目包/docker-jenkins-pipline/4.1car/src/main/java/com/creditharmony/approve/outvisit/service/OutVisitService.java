package com.creditharmony.approve.outvisit.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.creditharmony.approve.localnet.dao.InnerRepeatDao;
import com.creditharmony.approve.outvisit.dao.OutsideCheckInfoDao;
import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;
import com.creditharmony.approve.outvisit.entity.OutsideCheckList;
import com.creditharmony.approve.outvisit.entity.ex.OutsideCheckInfoEx;
import com.creditharmony.approve.phone.dao.DhzhBrhsDao;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.service.CoreManager;

/**
 * 外访_发起外访清单
 * @Class Name OutVisitService
 * @author 赖敏
 * @Create In 2015年12月7日
 */
@Service
public class OutVisitService extends CoreManager<OutsideCheckInfoDao, OutsideCheckInfo>{
	
	@Autowired 
	private OutsideCheckInfoDao checkInfoDao;
	@Autowired
	private InnerRepeatDao innerRepeatDao;
	@Autowired
	private DhzhBrhsDao brhsDao;
    
    /**
	 * 获取外访清单
	 * 2015年12月16日
	 * By 赖敏
	 * @param relationId 关联ID(变更历史表)
	 * @return 外访清单
	 */
	public OutsideCheckInfo getInfosByRid(OutsideCheckList checkList){
		return checkInfoDao.getInfosByRid(checkList);
	}   

    /**
     * 根据借款编号判定是否打开外访标签
     * 2016年3月30日
     * By 刘燕军
     * @param loanCode
     * @param checkType
     * @return 是否打开外访页签
     */
	public String isShow(String loanCode,String checkType) {
		if(StringUtils.isNotEmpty(loanCode) && StringUtils.isNotEmpty(checkType)){
			VerifyParamEx param =  new VerifyParamEx();
			param.setLoanCode(loanCode);
			param.setCheckType(checkType);
			int repeatNum = innerRepeatDao.checkException(param);
			// 内网核查 信息校验
			if(repeatNum>0){ // 如果存在，则需要进行判定才可以进入决策页面
				return BooleanType.FALSE; 
			}
			// 外网核查 信息校验
			int outside = brhsDao.checkExceptionOut(param);
			if(outside>0){// 如果存在，则需要进行判定才可以进入决策页面
				return BooleanType.FALSE;
			}
			return BooleanType.TRUE;
		}
		return BooleanType.FALSE;
	}	
    
    /**
     * 查询主借人居住地址、公司地址
     * 2015年12月31日
     * By 赖敏
     * @param loanCode 借款编码
     * @return 居住地址、公司地址
     */
    public OutsideCheckInfoEx getAddressSimple(String loanCode) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("loanCode", loanCode);
    	params.put("mainLoan", LoanManFlag.MAIN_LOAN.getCode());
    	OutsideCheckInfoEx checkInfoEx = checkInfoDao.getAddressSimple(params);
    	
    	return checkInfoEx;
    }
}
