package com.creditharmony.approve.credit.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.credit.entity.CreditAffiliatedEnterprise;
import com.creditharmony.approve.credit.entity.CreditBasicInfo;
import com.creditharmony.approve.credit.entity.CreditCivilJudgmentRecord;
import com.creditharmony.approve.credit.entity.CreditCreditClearedDetail;
import com.creditharmony.approve.credit.entity.CreditCreditClearedInfo;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityDetail;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityInfo;
import com.creditharmony.approve.credit.entity.CreditExecutiveInfo;
import com.creditharmony.approve.credit.entity.CreditExternalGuaranteeRecord;
import com.creditharmony.approve.credit.entity.CreditExternalSecurityInfo;
import com.creditharmony.approve.credit.entity.CreditGrade;
import com.creditharmony.approve.credit.entity.CreditInvestorInfo;
import com.creditharmony.approve.credit.entity.CreditLiabilityHis;
import com.creditharmony.approve.credit.entity.CreditLoanCard;
import com.creditharmony.approve.credit.entity.CreditPaidLoan;
import com.creditharmony.approve.credit.entity.CreditPunish;
import com.creditharmony.approve.credit.entity.CreditUnclearedBankAcceptance;
import com.creditharmony.approve.credit.entity.CreditUnclearedFactoring;
import com.creditharmony.approve.credit.entity.CreditUnclearedImproperLoan;
import com.creditharmony.approve.credit.entity.CreditUnclearedLetterCredit;
import com.creditharmony.approve.credit.entity.CreditUnclearedLetterGuarantee;
import com.creditharmony.approve.credit.entity.CreditUnclearedLoan;
import com.creditharmony.approve.credit.entity.CreditUnclearedNotesDiscounted;
import com.creditharmony.approve.credit.entity.CreditUnclearedTradeFinancing;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;
import com.creditharmony.approve.credit.service.CreditAffiliatedEnterpriseService;
import com.creditharmony.approve.credit.service.CreditBasicInfoService;
import com.creditharmony.approve.credit.service.CreditCivilJudgmentRecordService;
import com.creditharmony.approve.credit.service.CreditCreditClearedDetailService;
import com.creditharmony.approve.credit.service.CreditCreditClearedInfoService;
import com.creditharmony.approve.credit.service.CreditCurrentLiabilityDetailService;
import com.creditharmony.approve.credit.service.CreditCurrentLiabilityInfoService;
import com.creditharmony.approve.credit.service.CreditExecutiveInfoService;
import com.creditharmony.approve.credit.service.CreditExternalGuaranteeRecordService;
import com.creditharmony.approve.credit.service.CreditExternalSecurityInfoService;
import com.creditharmony.approve.credit.service.CreditGradeService;
import com.creditharmony.approve.credit.service.CreditInvestorInfoService;
import com.creditharmony.approve.credit.service.CreditLiabilityHisService;
import com.creditharmony.approve.credit.service.CreditLoanCardService;
import com.creditharmony.approve.credit.service.CreditPaidLoanService;
import com.creditharmony.approve.credit.service.CreditPunishService;
import com.creditharmony.approve.credit.service.CreditReportSimpleService;
import com.creditharmony.approve.credit.service.CreditUnclearedBankAcceptanceService;
import com.creditharmony.approve.credit.service.CreditUnclearedFactoringService;
import com.creditharmony.approve.credit.service.CreditUnclearedImproperLoanService;
import com.creditharmony.approve.credit.service.CreditUnclearedLetterCreditService;
import com.creditharmony.approve.credit.service.CreditUnclearedLetterGuaranteeService;
import com.creditharmony.approve.credit.service.CreditUnclearedLoanService;
import com.creditharmony.approve.credit.service.CreditUnclearedNotesDiscountedService;
import com.creditharmony.approve.credit.service.CreditUnclearedTradeFinancingService;
import com.creditharmony.approve.credit.service.EnterpriseCreditService;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.web.BaseController;

/**
 * 企业征信报告
 * @Class Name EnterpriseCreditControl
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Controller
@RequestMapping(value = "${adminPath}/credit/enterpriseCredit")
public class EnterpriseCreditControl extends BaseController {
	
	@Autowired
	private EnterpriseCreditService enterpriseCreditService;
	
	@Autowired
	private CreditInvestorInfoService creditInvestorInfoService;	
	
	@Autowired
	private CreditExecutiveInfoService creditExecutiveInfoService;

	@Autowired
	private CreditPunishService creditPunishService;
	
	@Autowired
	private CreditGradeService creditGradeService;
	
	@Autowired
	private CreditLoanCardService creditLoanCardService;
	
	@Autowired
	private CreditCivilJudgmentRecordService civilJudgmentService;
	
	@Autowired
	private CreditExternalGuaranteeRecordService externalGuaranteeService;
	
	@Autowired
	private CreditAffiliatedEnterpriseService creditAffiliatedEnterpriseService;
	
	@Autowired
	private CreditBasicInfoService creditBasicInfoService;
	
	@Autowired
	private CreditCurrentLiabilityInfoService creditCurrentLiabilityInfoService;	
	
	@Autowired
	private CreditCurrentLiabilityDetailService creditCurrentLiabilityDetailService;	
	
	@Autowired
	private CreditExternalSecurityInfoService creditExternalSecurityInfoService;
	
	@Autowired
	private CreditCreditClearedInfoService creditCreditClearedInfoService;
	
	@Autowired
	private CreditCreditClearedDetailService creditCreditClearedDetailService;

	@Autowired
	private CreditLiabilityHisService creditLiabilityHisService;
	
	@Autowired
	private CreditPaidLoanService paidLoanService;
	
	@Autowired
	private CreditUnclearedLoanService creditUnclearedLoanService;
	
	@Autowired
	private CreditUnclearedTradeFinancingService creditUnclearedTradeFinancingService;
	
	@Autowired
	private CreditUnclearedFactoringService creditUnclearedFactoringService;
	
	@Autowired
	private CreditUnclearedNotesDiscountedService creditUnclearedNotesDiscountedService;
	
	@Autowired
	private CreditUnclearedBankAcceptanceService creditUnclearedBankAcceptanceService;
	
	@Autowired
	private CreditUnclearedLetterCreditService creditUnclearedLetterCreditService;
	
	@Autowired
	private CreditUnclearedLetterGuaranteeService creditUnclearedLetterGuaranteeService;
	
	@Autowired
	private CreditUnclearedImproperLoanService unclearedImproperLoanService;
	
	@Autowired
	private CreditReportSimpleService creditReportSimpleService;
	
	/**
	 * 进入企业征信报告
	 * 2016年1月29日
	 * By zhanghu
	 * @param model
	 * @param EnterpriseCredit
	 * @return String 征信个人简版页面地址
	 */
	@RequestMapping(value = "form")
	public String initForm(Model model, EnterpriseCredit enterpriseCredit) {	
		enterpriseCredit = enterpriseCreditService.selectEnterpriseCredit(enterpriseCredit);
		// 判断有无征信信息
		if (null != enterpriseCredit && StringUtils.isNotEmpty(enterpriseCredit.getLoanCode())) {
			enterpriseCredit.setCreditVersion(
				DictCache.getInstance().getDictLabel(DictionaryConstants.CREDIT_REPORT_TYPE, enterpriseCredit.getCreditVersion()));		
			// 根据借款编码获取出资人征信记录
			List<CreditInvestorInfo> creditInvestorInfoList = creditInvestorInfoService.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditInvestorInfo creditInvestorInfo : creditInvestorInfoList) {
				creditInvestorInfo.setDictCertType(
						DictCache.getInstance().getDictLabel(DictionaryConstants.CART_TYPE, creditInvestorInfo.getDictCertType()));	
				creditInvestorInfo.setDictCurrency(
						DictCache.getInstance().getDictLabel(DictionaryConstants.ENTERPRISE_CREDIT_CURRENCY, creditInvestorInfo.getDictCurrency()));				
			}		

			// 根据借款编码获取高管人员征信记录
			List<CreditExecutiveInfo> creditExecutiveInfoList = creditExecutiveInfoService.selectByLoanCode(enterpriseCredit.getLoanCode());	
			for (CreditExecutiveInfo creditExecutiveInfo : creditExecutiveInfoList) {
				creditExecutiveInfo.setDictCertType(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CART_TYPE, creditExecutiveInfo.getDictCertType()));
				creditExecutiveInfo.setDictCompPost(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.POSITION_TYPE, creditExecutiveInfo.getDictCompPost()));
			}	
		
		// 已结清负债明细
			CreditPaidLoan paidLoan = new CreditPaidLoan();
			paidLoan.setLoanCode(enterpriseCredit.getLoanCode());
			List<CreditPaidLoan> paidLoanList = paidLoanService
					.selectByPaidLoanInfo(paidLoan);
			for (CreditPaidLoan payLoan : paidLoanList) {
				payLoan.setDictCurrency(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_CREDIT_CURRENCY, payLoan.getDictCurrency()));
				payLoan.setDictLevelClass(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.LOAN_LEVEL, payLoan.getDictLevelClass()));
				payLoan.setDictLoanType(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_LOAN_TYPE, payLoan.getDictLoanType()));
				payLoan.setDictGuarantee(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, payLoan.getDictGuarantee()));
				payLoan.setDictExhibition(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE,payLoan.getDictExhibition()));
				payLoan.setMakeAdvances(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, payLoan.getMakeAdvances()));
			}
		
			// 外部担保记录
			List<CreditExternalGuaranteeRecord> externalGuaranteeList = externalGuaranteeService
					.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditExternalGuaranteeRecord externalGuaranteeRecord : externalGuaranteeList) {
				externalGuaranteeRecord.setDictGuaranteeType(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.EX_GUARANTEE_TYPE, externalGuaranteeRecord.getDictGuaranteeType()));
				externalGuaranteeRecord.setDictCertType(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.GURANTEE_CARD_TYPE, externalGuaranteeRecord.getDictCertType()));
				externalGuaranteeRecord.setDictCurrency(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_CREDIT_CURRENCY, externalGuaranteeRecord.getDictCurrency()));
				externalGuaranteeRecord.setDictGuaranteeForm(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.GURANTEE_FORM, externalGuaranteeRecord.getDictGuaranteeForm()));
			}

			// 民事判决记录
			List<CreditCivilJudgmentRecord> civilJudgmentList = civilJudgmentService.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditCivilJudgmentRecord civilJudgmentRecord : civilJudgmentList) {
				civilJudgmentRecord.setDictClosedManner(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CLOSED_MANNER, civilJudgmentRecord.getDictClosedManner()));
				civilJudgmentRecord.setDictLawsuitPosition(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.LAWSUIT_POSITION, civilJudgmentRecord.getDictLawsuitPosition()));
			}

			// 贷款卡记录
			List<CreditLoanCard> creditLoanCardList = creditLoanCardService.selectByLoanCode(enterpriseCredit.getLoanCode());

			// 信用评级记录
			List<CreditGrade> creditGradeList = creditGradeService.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditGrade creditGrade : creditGradeList) {
				creditGrade.setDictOrgName(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_ORG_NAME, creditGrade.getDictOrgName()));
				creditGrade.setDictRank(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_RANK, creditGrade.getDictRank()));
			}

			// 处罚记录
			List<CreditPunish> creditPunishList = creditPunishService
					.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditPunish creditPunish : creditPunishList) {
				creditPunish.setItem(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_ITEM, creditPunish.getItem()));
			}

			// 直接关联企业
			List<CreditAffiliatedEnterprise> creditAffiliatedEnterpriseList = creditAffiliatedEnterpriseService.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditAffiliatedEnterprise affiliatedEnterprise : creditAffiliatedEnterpriseList) {
				affiliatedEnterprise.setDictRepeatRelation(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CONNECT_RELATION, affiliatedEnterprise.getDictRepeatRelation()));
			}

			// 基础信息
			CreditBasicInfo creditBasicInfo = creditBasicInfoService.selectByLoanCode(enterpriseCredit.getLoanCode());
			if (creditBasicInfo != null) {
				creditBasicInfo.setDictLoanCardState(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_CARED_STATUS, creditBasicInfo.getDictLoanCardState()));
			}
			// 当前负债信息概要
			List<CreditCurrentLiabilityInfo> creditCurrentLiabilityInfoList = creditCurrentLiabilityInfoService
					.selectByLoanCode(enterpriseCredit.getLoanCode());
		
			// 当前负债信息明细
			List<CreditCurrentLiabilityDetail> creditCurrentLiabilityDetailList = creditCurrentLiabilityDetailService
					.selectByLoanCode(enterpriseCredit.getLoanCode());
		
			// 对外担保信息概要
			List<CreditExternalSecurityInfo> creditExternalSecurityInfoList = creditExternalSecurityInfoService
						.selectByLoanCode(enterpriseCredit.getLoanCode());		
			// 企业征信_已结清信贷信息
			List<CreditCreditClearedInfo> creditCreditClearedInfoList = creditCreditClearedInfoService
					.selectByLoanCode(enterpriseCredit.getLoanCode());
		
			// 企业征信_已结清信贷明细
			List<CreditCreditClearedDetail> creditCreditClearedDetailList = creditCreditClearedDetailService.selectByLoanCode(enterpriseCredit.getLoanCode());
		
			// 企业征信_负债历史变化
			List<CreditLiabilityHis> creditLiabilityHisList = creditLiabilityHisService.selectByLoanCode(enterpriseCredit.getLoanCode());
		
			// 企业征信_未结清贷款
			List<CreditUnclearedLoan> creditUnclearedLoanList =  creditUnclearedLoanService.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditUnclearedLoan unclearedLoan : creditUnclearedLoanList) {
				unclearedLoan.setDictCurrency(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_CREDIT_CURRENCY, unclearedLoan.getDictCurrency()));
				unclearedLoan.setDictLoanType(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_LOAN_TYPE, unclearedLoan.getDictLoanType()));
				unclearedLoan.setDictGuarantee(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedLoan.getDictGuarantee()));
				unclearedLoan.setDictExhibition(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedLoan.getDictExhibition()));	
			}
		
			// 企业征信_未结清贸易融资
			List<CreditUnclearedTradeFinancing> creditUnclearedTradeFinancingList =  creditUnclearedTradeFinancingService.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditUnclearedTradeFinancing unclearedTradeFinancing : creditUnclearedTradeFinancingList) {
				unclearedTradeFinancing.setDictCurrency(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_CREDIT_CURRENCY, unclearedTradeFinancing.getDictCurrency()));
				unclearedTradeFinancing.setDictGuarantee(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedTradeFinancing.getDictGuarantee()));
				unclearedTradeFinancing.setDictExhibition(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedTradeFinancing.getDictExhibition()));		
			}
		
			// 企业征信_未结清保理
			List<CreditUnclearedFactoring> creditUnclearedFactoringList =  creditUnclearedFactoringService.selectByLoanCode(enterpriseCredit.getLoanCode());
			for (CreditUnclearedFactoring unclearedFactoring : creditUnclearedFactoringList) {
				unclearedFactoring.setDictCurrency(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_CREDIT_CURRENCY, unclearedFactoring.getDictCurrency()));
				unclearedFactoring.setDictGuarantee(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedFactoring.getDictGuarantee()));
				unclearedFactoring.setMakeAdvances(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedFactoring.getMakeAdvances()));			
			}
		
			// 企业征信_未结清票据贴现
			List<CreditUnclearedNotesDiscounted> creditUnclearedNotesDiscountedList = creditUnclearedNotesDiscountedService.selectByLoanCode(enterpriseCredit.getLoanCode());
			
			// 企业征信_未结清银行承兑汇票
			List<CreditUnclearedBankAcceptance> creditUnclearedBankAcceptanceList = creditUnclearedBankAcceptanceService.selectByLoanCode(enterpriseCredit.getLoanCode());
		
			// 企业征信_未结清信用证
			List<CreditUnclearedLetterCredit> creditUnclearedLetterCreditList = creditUnclearedLetterCreditService.selectByLoanCode(enterpriseCredit.getLoanCode());
			
			// 企业征信_未结清保函
			List<CreditUnclearedLetterGuarantee> creditUnclearedLetterGuaranteeList = creditUnclearedLetterGuaranteeService.selectByLoanCode(enterpriseCredit.getLoanCode());
		
			//未结清业务:不良、关注类
			CreditUnclearedImproperLoan unclearedImproperLoan=new CreditUnclearedImproperLoan();
			unclearedImproperLoan.setLoanCode(enterpriseCredit.getLoanCode());
			List<CreditUnclearedImproperLoan> unclearedImproperLoanList = unclearedImproperLoanService.selectByCreditUnclearedImproperLoan(unclearedImproperLoan);
			for (CreditUnclearedImproperLoan unclearedLoan : unclearedImproperLoanList) {
				unclearedLoan.setDictCurrency(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_CREDIT_CURRENCY, unclearedLoan.getDictCurrency()));
				unclearedLoan.setDictLevelClass(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.LOAN_LEVEL_CLASS, unclearedLoan.getDictLevelClass()));
				unclearedLoan.setDictLoanType(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.ENTERPRISE_LOAN_TYPE, unclearedLoan.getDictLoanType()));
				unclearedLoan.setDictGuarantee(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedLoan.getDictGuarantee()));
				unclearedLoan.setDictExhibition(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedLoan.getDictExhibition()));
				unclearedLoan.setMakeAdvances(DictCache.getInstance()
						.getDictLabel(DictionaryConstants.CREDIT_IS_GUARANTEE, unclearedLoan.getMakeAdvances()));			
			}		
		
			model.addAttribute("enterpriseCredit", enterpriseCredit);
			model.addAttribute("creditInvestorInfoList", creditInvestorInfoList);//出资人征信记录
			model.addAttribute("creditExecutiveInfoList", creditExecutiveInfoList);//高管人员征信记录
			model.addAttribute("creditAffiliatedEnterpriseList", creditAffiliatedEnterpriseList);//直接关联企业
			
			model.addAttribute("paidLoanList", paidLoanList); 
			model.addAttribute("externalGuaranteeList", externalGuaranteeList);
			model.addAttribute("civilJudgmentList", civilJudgmentList);
			model.addAttribute("creditLoanCardList", creditLoanCardList);
			model.addAttribute("creditGradeList", creditGradeList);
			model.addAttribute("creditPunishList", creditPunishList);

			model.addAttribute("creditAffiliatedEnterpriseList", creditAffiliatedEnterpriseList);//直接关联企业
			model.addAttribute("creditBasicInfo", creditBasicInfo);//基础信息
			model.addAttribute("creditCurrentLiabilityInfoList", creditCurrentLiabilityInfoList);//当前负债信息概要
			model.addAttribute("creditCurrentLiabilityDetailList", creditCurrentLiabilityDetailList);//当前负债信息明细
			model.addAttribute("creditExternalSecurityInfoList", creditExternalSecurityInfoList);//对外担保信息概要
			model.addAttribute("creditCreditClearedInfoList", creditCreditClearedInfoList);// 企业征信_已结清信贷信息
			
			model.addAttribute("creditCreditClearedDetailList", creditCreditClearedDetailList);// 企业征信_已结清信贷明细
			model.addAttribute("creditLiabilityHisList", creditLiabilityHisList);// 企业征信_负债历史变化
			
			// 企业征信_未结清贷款
			model.addAttribute("creditUnclearedLoanList", creditUnclearedLoanList);
			// 企业征信_未结清贸易融资
			model.addAttribute("creditUnclearedTradeFinancingList", creditUnclearedTradeFinancingList);
			// 企业征信_未结清保理
			model.addAttribute("creditUnclearedFactoringList", creditUnclearedFactoringList);
			// 企业征信_未结清票据贴现
			model.addAttribute("creditUnclearedNotesDiscountedList", creditUnclearedNotesDiscountedList);
			// 企业征信_未结清银行承兑汇票
			model.addAttribute("creditUnclearedBankAcceptanceList", creditUnclearedBankAcceptanceList);
			// 企业征信_未结清信用证
			model.addAttribute("creditUnclearedLetterCreditList", creditUnclearedLetterCreditList);
			// 企业征信_未结清保函
			model.addAttribute("creditUnclearedLetterGuaranteeList", creditUnclearedLetterGuaranteeList);
			// 未结清非正常贷款
			model.addAttribute("unclearedImproperLoanList", unclearedImproperLoanList); 
			
		} else {
			return "credit/enterpriseCreditView_Empty";
		}
		
		return "credit/enterpriseCreditView";		
	}
	
}