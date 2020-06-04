package com.creditharmony.approve.localnet.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.localnet.dao.InnerRepeatDao;
import com.creditharmony.approve.localnet.entity.InnerRepeat;
import com.creditharmony.approve.verify.entity.ex.InnerCheckEx;
import com.creditharmony.approve.verify.entity.ex.PayBackMonthEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.approve.verify.util.DictToMapUtil;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.lend.type.LendConstants;
/**
 * 内网审核service
 * @Class Name InnerCheckService
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
@Service
public class InsideNetService extends VerifyCommonService{
	@Autowired
	private InnerRepeatDao repeatDao;
	
	/**
	 * 内网审核 页面初始化
	 * 2015年12月2日
	 * By 刘燕军
	 * @param loanCode
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public InnerCheckEx  getInnerCheckView(VerifyParamEx param) throws IllegalAccessException, InvocationTargetException{		
		String loanCode = param.getLoanCode();
		InnerCheckEx  innerCheckView = new InnerCheckEx();
		// 申请信息		
		innerCheckView.setLoanInfoEx(getLoanInfoEx(param));
		// 备注信息
		innerCheckView.setLoanRemarks(getLoanRemark(loanCode));
		// 申请信息历史对比异常点
		innerCheckView.setInnerApplyContrasts(getInnerApplyContrast(param));
		// 历史归户信息
		innerCheckView.setCustomerHis(getCustomerHis(param));
		// 反欺诈反馈信息		
		innerCheckView.setAntiFraudResultViews(getAntiFraudResultEx(param));
		// 查重信息
		innerCheckView.setInnerRepeats(getInnerRepeat(param));
		// 查灰信息
		innerCheckView.setGreyListViews(getGreyListEx(loanCode));
		// 查黑信息
		innerCheckView.setBlackListViews(getBlackList(loanCode));	
		// 复议原因
		innerCheckView.setReconsiderApply(getReconsiderApply(loanCode));
		// 判定正常 异常 无效对应的map
		innerCheckView.setCheckResults(DictToMapUtil.getMap(LendConstants.EVAL_RESULT));
		return innerCheckView;
	}
	
	/**
	 * 内网审核  查重信息的更新部分 
	 * 2015年12月4日
	 * By 刘燕军
	 * @param ids
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void updateInnerCheck(InnerRepeat repeat,VerifyParamEx param){	
		String checkType = param.getCheckType(); 
		// 如果是复议
		if(StringUtils.isNotBlank(checkType) && checkType.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())){
			String exceptionFlag = repeat.getExceptionFlag();
			String remark = repeat.getRemark();
			repeat.setExceptionReconsiderFlag(exceptionFlag);
			repeat.setReconsiderRemark(remark);
			repeat.setExceptionFlag(null);
			repeat.setRemark(null);
		}else{
			String exceptionFlag = repeat.getExceptionFlag();
			String remark = repeat.getRemark();
			repeat.setExceptionReconsiderFlag(exceptionFlag);
			repeat.setReconsiderRemark(remark);
		}
		repeat.preUpdate();
		repeatDao.update(repeat);
	}
	
	/**
	 * 根据借款编号查询逾期次数
	 * 2015年12月7日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return  询逾期次数
	 */
	public List<PayBackMonthEx> getLateTime(String loanCode) {
		List<PayBackMonthEx> PayBackMonthEx =repeatDao.getLateTime(loanCode);
		return PayBackMonthEx;		
	}
	
	/**
	 * 根据借款编号查询逾期记录
	 * 2015年12月7日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return 逾期记录
	 */
	public List<PayBackMonthEx> getLateTimeRecord(String loanCode) {
		return  repeatDao.getLateTimeRecord(loanCode);
	}
	/**
	 * 获取查重部分信息
	 * 2016年5月18日
	 * By 刘燕军
	 * @param param
	 * @return
	 */
	public InnerCheckEx getInnerRepeats(VerifyParamEx param){
		InnerCheckEx  innerCheckView = new InnerCheckEx();
		// 查重信息
		innerCheckView.setInnerRepeats(getInnerRepeat(param));
		innerCheckView.setCheckResults(DictToMapUtil.getMap(LendConstants.EVAL_RESULT));
		return innerCheckView;
	}
}
