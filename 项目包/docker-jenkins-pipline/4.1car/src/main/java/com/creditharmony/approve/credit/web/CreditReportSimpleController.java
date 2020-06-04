package com.creditharmony.approve.credit.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.credit.entity.CreditCardInfo;
import com.creditharmony.approve.credit.entity.CreditLoanInfo;
import com.creditharmony.approve.credit.entity.CreditPaybackInfo;
import com.creditharmony.approve.credit.entity.CreditQueryRecord;
import com.creditharmony.approve.credit.entity.CreditReportSimple;
import com.creditharmony.approve.credit.service.CreditCardInfoService;
import com.creditharmony.approve.credit.service.CreditDetailedInfoService;
import com.creditharmony.approve.credit.service.CreditLoanInfoService;
import com.creditharmony.approve.credit.service.CreditPaybackInfoService;
import com.creditharmony.approve.credit.service.CreditQueryRecordService;
import com.creditharmony.approve.credit.service.CreditReportSimpleService;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.web.BaseController;

/**
 * 简版信用报告
 * @Class Name CreditReportSimpleController
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Controller
@RequestMapping(value = "${adminPath}/credit/creditReportSimple")
public class CreditReportSimpleController extends BaseController {
	
	@Autowired
	private CreditReportSimpleService creditReportSimpleService;
	
	@Autowired
	private CreditCardInfoService creditCardInfoService;
	
	@Autowired
	private CreditLoanInfoService creditLoanInfoService;
	
	@Autowired
	private CreditQueryRecordService creditQueryRecordService;
	
	@Autowired
	private CreditDetailedInfoService creditDetailedInfoService;
	
	@Autowired
	private CreditPaybackInfoService creditPaybackInfoService;
	
	
	/**
	 * 进入征信个人简版
	 * 2016年1月29日
	 * By zhanghu
	 * @param model
	 * @param creditReportSimple
	 * @return String 征信个人简版页面地址
	 */
	@RequestMapping(value = "form")
	public String initForm(Model model, CreditReportSimple creditReportSimple, String customerType) {
		creditReportSimple.setDictCustomerType(customerType);				
		//初始化信息
		initCreditReportSimple(model, creditReportSimple);
		
		// 如果为共借人，获取共借人身份证号
		if(LoanManFlag.COBORROWE_LOAN.getCode().equals(customerType)){
			LoanCoborrower result = creditDetailedInfoService.selectCoboNameAndCertNum(
					creditReportSimple.getLoanCode(),creditReportSimple.getrCustomerCoborrowerId());
			if(result != null){
				model.addAttribute("applyCertNum", result.getCoboCertNum());
			}
		}
		// 如果为主借人，获取主借人身份证号
		if(LoanManFlag.MAIN_LOAN.getCode().equals(customerType)){
			LoanCustomer result = creditDetailedInfoService.getCustomer(
					creditReportSimple.getLoanCode());
			if(result != null){
				model.addAttribute("applyCertNum", result.getCustomerCertNum());
			}
		}
		
		
		return "credit/creditReportSimpleView";		
	}
	
	/**
	 * 保存保证人代偿信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditReportSimple
	 * @return String 执行结果
	 */
	@ResponseBody
	@RequestMapping(value="savePaybackInfo")
	public String savePaybackInfo(CreditReportSimple creditReportSimple){

		try {
			// 清空原有数据
			creditPaybackInfoService.deleteByRelationId(creditReportSimple.getId());
			// 保存现有数据
			List<CreditPaybackInfo> creditPaybackInfoList = creditReportSimple.getCreditPaybackInfoList();
			if (ArrayHelper.isNotEmpty(creditPaybackInfoList)) {
				for (CreditPaybackInfo creditPaybackInfo : creditPaybackInfoList) {
					creditPaybackInfoService.insertCreditPaybackInfo(creditPaybackInfo);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	
	/**
	 * 初始化信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditReportSimple
	 */
	private void initCreditReportSimple(Model model, CreditReportSimple creditReportSimple) {		
		
		//征信信息初始化
		CreditReportSimple creditReportSimpleNew = creditReportSimpleService.selectByCreditReportSimple(creditReportSimple);
		if(creditReportSimpleNew != null) {			
			// 获取借款人姓名信息
			creditReportSimpleNew.setCustomerName(creditReportSimpleService.getCustomerName(creditReportSimple));						
			
			// 根据征信id获取保证人信息记录
			List<CreditPaybackInfo> creditPaybackInfoList = creditPaybackInfoService.selectByCreditPaybackInfo(creditReportSimpleNew.getId());
			
			// 根据征信id获取信用卡征信记录
			List<CreditCardInfo> creditCardInfoList = creditCardInfoService.selectByCreditCardInfo(creditReportSimpleNew.getId());
			for (CreditCardInfo cf : creditCardInfoList) {
				String accountStatus = DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_CARDINFO_ACCOUNTSTATUS, cf.getAccountStatus());
				cf.setAccountStatus(accountStatus);
				String currency = DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_CARDINFO_CURRENCY, cf.getCurrency());
				cf.setCurrency(currency);
			}
		
			// 根据征信id获取贷款记录
			List<CreditLoanInfo> creditLoanInfoList = creditLoanInfoService.selectByCreditLoanInfo(creditReportSimpleNew.getId());
			for (CreditLoanInfo lf : creditLoanInfoList) {
				String accountStatus = DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_LOANINFO_ACCOUNTSTATUS, lf.getAccountStatus());
				lf.setAccountStatus(accountStatus);
				String currency = DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_LOAN_TYPE, lf.getCurrency());
				lf.setCurrency(currency);
			}		
		
			// 根据征信id获取查询记录
			List<CreditQueryRecord> creditQueryRecordList = creditQueryRecordService.selectByCreditQueryRecord(creditReportSimpleNew.getId());
			for (CreditQueryRecord queryRecord : creditQueryRecordList) {
				String queryType = DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_QUERY_TYPE, queryRecord.getQueryType());
				queryRecord.setQueryType(queryType);
			}
		
			model.addAttribute("creditReportSimple", creditReportSimpleNew);
			model.addAttribute("creditPaybackInfoList", creditPaybackInfoList);
			model.addAttribute("creditCardInfoList", creditCardInfoList);
			model.addAttribute("creditLoanInfoList", creditLoanInfoList);
			model.addAttribute("creditQueryRecordList", creditQueryRecordList);
			
		} else {
			
		}
	}
	
	/**
	 * 保存信用卡信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditReportSimple
	 * @return String 执行结果
	 */
	@ResponseBody
	@RequestMapping(value="saveCardInfo")
	public String saveCardInfo(CreditReportSimple creditReportSimple){

		try {
			// 清空原有数据
			creditCardInfoService.deleteByRelationId(creditReportSimple.getId());
			// 保存现有数据
			List<CreditCardInfo> creditCardInfoList = creditReportSimple.getCreditCardInfoList();
			if (ArrayHelper.isNotEmpty(creditCardInfoList)) {
				for (CreditCardInfo creditCardInfo : creditCardInfoList) {
					creditCardInfoService.insertCreditCardInfo(creditCardInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 保存贷款信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditReportSimple
	 * @return String 执行结果
	 */
	@ResponseBody
	@RequestMapping(value="saveLoanInfo")
	public String saveLoanInfo(CreditReportSimple creditReportSimple){

		try {
			// 清空原有数据
			creditLoanInfoService.deleteByRelationId(creditReportSimple.getId());
			// 保存现有数据
			List<CreditLoanInfo> creditLoanInfoList = creditReportSimple.getCreditLoanInfoList();
			if (ArrayHelper.isNotEmpty(creditLoanInfoList)) {
				for (CreditLoanInfo creditLoanInfo : creditLoanInfoList) {
					creditLoanInfoService.insertCreditLoanInfo(creditLoanInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 保存查询信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditReportSimple
	 * @return String 执行结果
	 */
	@ResponseBody
	@RequestMapping(value="saveQueryInfo")
	public String saveQueryInfo(CreditReportSimple creditReportSimple){

		try {
			// 清空原有数据
			creditQueryRecordService.deleteByRelationId(creditReportSimple.getId());
			// 保存现有数据
			List<CreditQueryRecord> creditQueryRecordList = creditReportSimple.getCreditQueryRecordList();
			if (ArrayHelper.isNotEmpty(creditQueryRecordList)) {
				for (CreditQueryRecord creditQueryRecord : creditQueryRecordList) {
					creditQueryRecordService.insertCreditQueryRecord(creditQueryRecord);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 删除信用卡信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditCardInfo
	 * @return String 执行结果
	 */
	@ResponseBody
	@RequestMapping(value="deleteCardInfoById")
	public String deleteCardInfoById(CreditCardInfo creditCardInfo){

		try {
			// 删除信用卡信息
			creditCardInfoService.deleteCardInfoById(creditCardInfo.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 删除贷款信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditLoanInfo
	 * @return String
	 */
	@ResponseBody
	@RequestMapping(value="deleteLoanInfoById")
	public String deleteLoanInfoById(CreditLoanInfo creditLoanInfo){

		try {
			// 删除贷款信息
			creditLoanInfoService.deleteLoanInfoById(creditLoanInfo.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 删除查询信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditLoanInfo
	 * @return String 执行结果
	 */
	@ResponseBody
	@RequestMapping(value="deleteQueryInfoById")
	public String deleteQueryInfoById(CreditQueryRecord creditQueryRecord){

		try {
			// 删除查询信息
			creditQueryRecordService.deleteQueryInfoById(creditQueryRecord.getId());

		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 初始化信用卡信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param model
	 * @param creditReportSimple
	 * @return  信用卡信息地址
	 */
	@RequestMapping(value = "initCardInfo")
	public String initCardInfo(Model model, CreditReportSimple creditReportSimple) {			
		
		// 根据征信id获取信用卡征信记录
		List<CreditCardInfo> creditCardInfoList = creditCardInfoService.selectByCreditCardInfo(creditReportSimple.getId());
		model.addAttribute("creditCardInfoList", creditCardInfoList);
		return "credit/creditReportSimpleCardInfoView";		
	}
	
	/**
	 * 初始化贷款信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param model
	 * @param creditReportSimple
	 * @return 贷款信息地址
	 */
	@RequestMapping(value = "initLoanInfo")
	public String initLoanInfo(Model model, CreditReportSimple creditReportSimple) {			
		
		// 根据征信id获取贷款记录
		List<CreditLoanInfo> creditLoanInfoList = creditLoanInfoService.selectByCreditLoanInfo(creditReportSimple.getId());
		model.addAttribute("creditLoanInfoList", creditLoanInfoList);
		return "credit/creditReportSimpleLoanInfoView";		
	}
	
	/**
	 * 初始化查询信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param model
	 * @param creditReportSimple
	 * @return 查询信息地址
	 */
	@RequestMapping(value = "initQueryInfo")
	public String initQueryInfo(Model model, CreditReportSimple creditReportSimple) {			
		
		// 根据征信id获取查询信息记录
		List<CreditQueryRecord> creditQueryRecordList = creditQueryRecordService.selectByCreditQueryRecord(creditReportSimple.getId());
		model.addAttribute("creditQueryRecordList", creditQueryRecordList);
		return "credit/creditReportSimpleQueryInfoView";		
	}
	
	/**
	 * 删除查询信息
	 * 2016年1月29日
	 * By zhanghu
	 * @param creditLoanInfo
	 * @return String 执行结果
	 */
	@ResponseBody
	@RequestMapping(value="saveQueryTime")
	public String saveQueryTime(CreditReportSimple creditReportSimple){

		try {
			// 更新简版基本信息
			creditReportSimpleService.updateCreditReportSimple(creditReportSimple);

		} catch (Exception e) {
			e.printStackTrace();
			return BooleanType.FALSE;
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 进入网络查询系统登录页面
	 * 2016年1月29日
	 * By zhanghu
	 * @param model
	 * @param creditReportSimple
	 * @return String 征信个人网络登录页面地址
	 */
	@RequestMapping(value = "initWeb")
	public String initWeb(Model model, CreditReportSimple creditReportSimple) {			
		
		// 根据关联ID(主借人，共借人) R_CUSTOMER_COBORROWER_ID查询征信信息
		creditReportSimple = creditReportSimpleService.selectByCreditReportSimple(creditReportSimple);
		
		// 调用接口获取验证码
	   /* ClientPoxy service = new ClientPoxy(ServiceType.Type.PBC_GETLOGINPAGE);
	    SimpleInInfo spi = new SimpleInInfo();*/
	    
	    // 获取登录页信息
	 /*   PbcGetLoginPageOutInfo pbcGetLoginPageOutInfo = new PbcGetLoginPageOutInfo();
	    pbcGetLoginPageOutInfo = (PbcGetLoginPageOutInfo) service.callService(spi);
	    creditReportSimple.setPbcGetLoginPageOutInfo(pbcGetLoginPageOutInfo);*/
	    
	    // 登陆信息
	   /* PbcLoginInfo pbcLoginInfo = new PbcLoginInfo();
	    pbcLoginInfo.setCookies(pbcGetLoginPageOutInfo.getCookies());
	    creditReportSimple.setPbcLoginInfo(pbcLoginInfo);
	    
	    PbcLoginOutInfo pbcLoginOutInfo = new PbcLoginOutInfo();
	    creditReportSimple.setPbcLoginOutInfo(pbcLoginOutInfo);*/
	    
		model.addAttribute("creditReportSimple", creditReportSimple);
		
		return "credit/creditReportSimpleWebLoad";		
	}
	
	
	
	
	
	/**
	 * 进入静态网页
	 * 2016年1月29日
	 * By zhanghu
	 * @param model
	 * @param creditReportSimple
	 * @return String 征信个人网络登录页面地址
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "initHtmlUrl")
	public String initHtmlUrl(Model model, CreditReportSimple creditReportSimple) throws UnsupportedEncodingException {			
		
		creditReportSimple = creditReportSimpleService.selectByCreditReportSimple(creditReportSimple);
		
		String htmTxt = creditReportSimple.getHtmlUrl();
		if(StringUtils.isNotEmpty(htmTxt)){
			String htmlUrl = new String(htmTxt.getBytes(),"utf-8");
			System.out.println(htmlUrl);
			model.addAttribute("htmlUrl",  htmlUrl);
		}
		
		return "credit/creditReportSimpleHtmlUrl";
		
	}
	
	
}
