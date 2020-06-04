package com.creditharmony.approve.common.ws;

import java.util.Date;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.creditharmony.approve.common.view.PolicyEngineView;
import com.creditharmony.approve.common.view.ReconsiderInitView;
import com.creditharmony.approve.common.view.VerifyInitView;

/**
 * 工作流调用WebService接口
 * @Class Name ApproveWebService
 * @author 刘燕军
 * @Create In 2016年1月27日
 */
@WebService
public interface ApproveWebService {
	
	/**
	 * 进件规则引擎调用
	 * 2016年1月15日
	 * xiaoniu.hu
	 * @param loanCode 借款编号
	 * @return 判定结果
	 * @throws Exception
	 */
	public PolicyEngineView callRule(@WebParam(name="loanCode")String loanCode)throws Exception;
	
	/***
	 * 反欺诈初始化
	 * 2016年4月29日
	 * xiaoniu.hu
	 * @param loanCode
	 * @throws Exception
	 */
	public void antifraudInit(@WebParam(name="loanCode")String loanCode)throws Exception;
	
	/**
	 * 首次进入信审
	 * 2016年1月15日
	 * xiaoniu.hu
	 * @param loanCode 借款编号
	 * @return 判定结果
	 * @throws Exception
	 */
	public VerifyInitView verifyInit(@WebParam(name="loanCode")String loanCode,@WebParam(name="checkLevel")String checkLevel,@WebParam(name="urgentFlag")String urgentFlag,@WebParam(name="intoApproveTime")Date intoApproveTime)throws Exception;
	
	/**
	 * 信审门店返回初始化(查重、信息入库、汇诚审核用数据初始化、状态更新)
	 * 2016年1月18日
	 * xiaoniu.hu
	 * @param loanCode 借款编号
	 * @return 无
	 * @throws Exception
	 */
	public void verifyBackInit(@WebParam(name="loanCode")String loanCode)throws Exception;
	
	/**
	 * 复议发起初始化
	 * 2016年1月15日
	 * xiaoniu.hu
	 * @param loanCode 借款编号
	 * @return 初始化结果
	 * @throws Exception
	 */
	public ReconsiderInitView reconsiderInit(@WebParam(name="loanCode")String loanCode,@WebParam(name="urgentFlag")String urgentFlag,@WebParam(name="intoApproveTime")Date intoApproveTime)throws Exception;
	
	/**
	 * 复议门店返回初始化
	 * 2016年1月20日
	 * xiaoniu.hu
	 * @param loanCode 借款编号
	 * @return 无
	 * @throws Exception
	 */
	public void reconsiderBackInit(@WebParam(name="loanCode")String loanCode)throws Exception;
	
}
