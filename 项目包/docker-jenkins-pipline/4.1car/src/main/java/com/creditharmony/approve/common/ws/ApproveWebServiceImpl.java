package com.creditharmony.approve.common.ws;

import java.util.Date;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.creditharmony.approve.antifraud.service.PhoneConsultService;
import com.creditharmony.approve.common.service.PretreatmentNodeService;
import com.creditharmony.approve.common.view.PolicyEngineView;
import com.creditharmony.approve.common.view.ReconsiderInitView;
import com.creditharmony.approve.common.view.VerifyInitView;
import com.creditharmony.approve.rule.applyengine.service.ApplyEngineService;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.loan.type.LoanApplyStatus;

/**
 * 工作流调用WebService接口
 * @Class Name ApproveWebServiceImpl
 * @author 刘燕军
 * @Create In 2016年1月27日
 */
@WebService
public class ApproveWebServiceImpl implements ApproveWebService {
	
	@Autowired
	private PretreatmentNodeService pretreatmentNodeService;
	@Autowired
	private ApplyEngineService applyEngineService;	
	@Autowired
	private PhoneConsultService consultService;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 进件规则引擎调用
	 */
	@Override
	public PolicyEngineView callRule(String loanCode) throws Exception {
		return applyEngineService.loadRule(loanCode);
	}
	
	/**
	 * 信审发起初始化(汇诚审核用数据初始化、状态更新)
	 */
	@Override
	public VerifyInitView verifyInit(String loanCode, String checkLevel,
			String urgentFlag, Date intoApproveTime) throws Exception {
		VerifyInitView item=new VerifyInitView();
		log.info("信审预处理开始:loanCode="+loanCode+"checkLevel="+checkLevel+"urgentFlag="+urgentFlag+"intoApproveTime="+DateUtils.formatDate(intoApproveTime, "yyyyMMddHHmmss"));
		// 组装分单排序
		String strUrgentFlag="1";// 加急标识
		if(!StringUtils.isEmpty(urgentFlag)&&"1".equals(urgentFlag)){
			strUrgentFlag="0";
		}
		String strChekLevel="2";// 初审等级
		if(!StringUtils.isEmpty(checkLevel)){
			String temp=checkLevel.substring(checkLevel.length()-1,checkLevel.length());
			if("2".equals(temp))strChekLevel="0";
			if("1".equals(temp))strChekLevel="1";
		}
		item.setOrderField(strUrgentFlag+strChekLevel+DateUtils.formatDate(intoApproveTime, "yyyyMMddHHmmss"));
		// 执行预处理
		pretreatmentNodeService.initOutside(loanCode,ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());
		item.setLoanStatusCode(LoanApplyStatus.PRELIMINARY_CHECK.getCode());
		item.setLoanStatusName(LoanApplyStatus.PRELIMINARY_CHECK.getName());
		log.info("信审预处理结束:loanCode="+loanCode);
		return item;
	}
	
	/**
	 * 信审门店返回初始化(查重、信息入库、汇诚审核用数据初始化、状态更新)
	 */
	@Override
	public void verifyBackInit(String loanCode) throws Exception {
		pretreatmentNodeService.initOutsideOther(loanCode,ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());
	}

	/**
	 * 复议发起初始化
	 */
	@Override
	public ReconsiderInitView reconsiderInit(String loanCode, String urgentFlag,Date intoApproveTime) throws Exception {
		ReconsiderInitView item=new ReconsiderInitView();
		log.info("复议预处理开始:loanCode="+loanCode+"urgentFlag="+urgentFlag+"intoApproveTime="+DateUtils.formatDate(intoApproveTime, "yyyyMMddHHmmss"));
		// 组装分单排序
		String strUrgentFlag="1";// 加急标识
		if(!StringUtils.isEmpty(urgentFlag)&&"1".equals(urgentFlag)){
			strUrgentFlag="0";
		}
		item.setOrderField(strUrgentFlag+DateUtils.formatDate(intoApproveTime, "yyyyMMddHHmmss"));
		pretreatmentNodeService.initOutside(loanCode,ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
		item.setLoanStatusCode(LoanApplyStatus.RECONSIDER_CHECK.getCode());
		item.setLoanStatusName(LoanApplyStatus.RECONSIDER_CHECK.getName());
		log.info("复议预处理结束:loanCode="+loanCode);
		return item;
	}
	
	/**
	 * 复议门店返回初始化
	 */
	@Override
	public void reconsiderBackInit(String loanCode) throws Exception {
		pretreatmentNodeService.initOutsideOther(loanCode,ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
	}

	/**
	 * 反欺诈电话照会数据初始化
	 */
	@Override
	public void antifraudInit(String loanCode) throws Exception {
		consultService.initTelCheck(loanCode);
	}

}
