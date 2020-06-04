package com.creditharmony.approve.credit.web;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.credit.entity.CreditCardInfo;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityDetail;
import com.creditharmony.approve.credit.entity.CreditLoanInfo;
import com.creditharmony.approve.credit.entity.CreditReportSimple;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;
import com.creditharmony.approve.credit.entity.ex.CreditCardDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditEnterpriseDownEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditReportDetailedEx;
import com.creditharmony.approve.credit.service.CreditRiskReportService;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.web.BaseController;

/**
 * @Class Name CreditReportController
 * @author 黄维
 * @Create In 2015年11月30日
 * 征信报告
 */
@Controller
@RequestMapping(value = "${adminPath}/credit/creditRisk")
public class CreditRiskReprotController extends BaseController {

	@Autowired
	private CreditRiskReportService creditRiskService;
	
	/**
	 * 跳转加载征信报告页面
	 * 2015年11月30日
	 * By 黄维
	 * @param model
	 * @param verifyParamEx
	 */
	@RequestMapping(value="loadCreditReportPage")
	public String loadCreditReportPage(Model model, VerifyParamEx verifyParamEx,String editFlag){
		if(!StringUtils.isEmpty(editFlag)){
			return "credit/creditRiskReportView";
		}
		return "credit/creditRiskReportForm";
	}
	
	/**
	 * 初始化参数
	 * 2015年12月14日
	 * By 李文勇
	 * @param model
	 * @param creditReportRisk
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="initPage")
	public List<CreditReportRisk> initPage( VerifyParamEx verifyParamEx ){
		List<CreditReportRisk> result = creditRiskService.getPersonCreditReportDetailedByCode(verifyParamEx);
		return result;
	}
	
	/**
	 * 2015年12月4日
	 * By 黄维
	 * ajax保存信用报告风险信息
	 */
	@ResponseBody
	@RequestMapping(value="asyncSaveCreditReportRiskInfo")
	public int asyncSaveCreditReportRiskInfo( CreditReportRisk creditReportRisk ) {
		int result = creditRiskService.asyncSaveCreditReportRiskInfo( creditReportRisk );
		return result;
	}
	
	/**
	 * 查询简版贷记卡信息
	 * 2015年12月29日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getCardByLoanCode")
	public CreditCardInfo getCardByLoanCode( VerifyParamEx verifyParamEx ) {
		CreditCardInfo result = creditRiskService.getCardByLoanCode( verifyParamEx );
		return result;
	}
	
	/**
	 * 查询简版贷款信息
	 * 2016年1月4日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getLoanByLoanCode")
	public Map<String,CreditLoanInfo> getLoanByLoanCode( VerifyParamEx verifyParamEx ){
		Map<String,CreditLoanInfo> result = creditRiskService.getLoanByLoanCode( verifyParamEx );
		return result;
	}
	
	/**
	 * 查询简版时间
	 * 2016年1月4日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="selectByCreditReportSimple")
	public CreditReportSimple selectByCreditReportSimple( VerifyParamEx verifyParamEx ){
		CreditReportSimple result = creditRiskService.selectByCreditReportSimple( verifyParamEx );
		return result;
	}
	
	
	/**
	 * 获取基本信息
	 * 2016年1月7日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getBaseInfo")
	public CreditReportDetailedEx getBaseInfo( VerifyParamEx verifyParamEx ){
		CreditReportDetailedEx result = creditRiskService.getBaseInfo(verifyParamEx);
		return result;
	}
	
	/**
	 * 获取详版贷记卡信息
	 * 2016年1月7日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getDetailedCard")
	public CreditCardDetailedEx getDetailedCard( VerifyParamEx verifyParamEx ){
		CreditCardDetailedEx result = creditRiskService.getDetailedCard(verifyParamEx);
		return result;
	}
	
	/**
	 * 获取详版贷款负债信息
	 * 2016年1月8日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getDetailedLoan")
	public Map<String,CreditLoanDetailedEx> getDetailedLoan(VerifyParamEx verifyParamEx){
		Map<String,CreditLoanDetailedEx> result = creditRiskService.getDetailedLoan(verifyParamEx);
		return result;
	}
	
	/**
	 * 显示企业征信报告表格(征信核查页面用)
	 * 2016年2月24日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getEnterpriseTable")
	public List<CreditCurrentLiabilityDetail> getEnterpriseTable(VerifyParamEx verifyParamEx){
		List<CreditCurrentLiabilityDetail> result = creditRiskService.getEnterpriseTable(verifyParamEx);
		return result;
	}
	
	/**
	 * 获取报告时间
	 * 2016年2月24日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getEnterpriseTime")
	public EnterpriseCredit getEnterpriseTime(VerifyParamEx verifyParamEx){
		EnterpriseCredit result = creditRiskService.getEnterpriseTime(verifyParamEx);
		return result;
	}
	
	/**
	 * 显示企业征信报告表格(下载意见书页面用)
	 * 2016年2月25日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="downloadUseEnterprise")
	public CreditEnterpriseDownEx downloadUseEnterprise(VerifyParamEx verifyParamEx){
		CreditEnterpriseDownEx result = creditRiskService.downloadUseEnterprise(verifyParamEx);
		return result;
	}
	
}
