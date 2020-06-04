package com.creditharmony.approve.credit.service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.dao.CreditReportDao;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.credit.constants.CreditReportConstants;
import com.creditharmony.approve.credit.dao.CreditBasicInfoDao;
import com.creditharmony.approve.credit.dao.CreditCivilJudgmentRecordDao;
import com.creditharmony.approve.credit.dao.CreditCreditClearedDetailDao;
import com.creditharmony.approve.credit.dao.CreditCreditClearedInfoDao;
import com.creditharmony.approve.credit.dao.CreditCurrentLiabilityDetailDao;
import com.creditharmony.approve.credit.dao.CreditCurrentLiabilityInfoDao;
import com.creditharmony.approve.credit.dao.CreditExecutiveInfoDao;
import com.creditharmony.approve.credit.dao.CreditExternalSecurityInfoDao;
import com.creditharmony.approve.credit.dao.CreditGradeDao;
import com.creditharmony.approve.credit.dao.CreditInvestorInfoDao;
import com.creditharmony.approve.credit.dao.CreditLiabilityHisDao;
import com.creditharmony.approve.credit.dao.CreditLoanCardDao;
import com.creditharmony.approve.credit.dao.CreditPaidLoanDao;
import com.creditharmony.approve.credit.dao.CreditPunishDao;
import com.creditharmony.approve.credit.dao.CreditReportDetailedDao;
import com.creditharmony.approve.credit.dao.CreditReportSimpleDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedBankAcceptanceDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedFactoringDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedImproperLoanDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedLetterCreditDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedLetterGuaranteeDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedLoanDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedNotesDiscountedDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedTradeFinancingDao;
import com.creditharmony.approve.credit.dao.EnterpriseCreditDao;
import com.creditharmony.approve.credit.entity.CreditCardInfo;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityDetail;
import com.creditharmony.approve.credit.entity.CreditLoanInfo;
import com.creditharmony.approve.credit.entity.CreditPaidLoan;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.CreditReportSimple;
import com.creditharmony.approve.credit.entity.CreditUnclearedBankAcceptance;
import com.creditharmony.approve.credit.entity.CreditUnclearedFactoring;
import com.creditharmony.approve.credit.entity.CreditUnclearedImproperLoan;
import com.creditharmony.approve.credit.entity.CreditUnclearedLetterCredit;
import com.creditharmony.approve.credit.entity.CreditUnclearedLetterGuarantee;
import com.creditharmony.approve.credit.entity.CreditUnclearedLoan;
import com.creditharmony.approve.credit.entity.CreditUnclearedNotesDiscounted;
import com.creditharmony.approve.credit.entity.CreditUnclearedTradeFinancing;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;
import com.creditharmony.approve.credit.entity.ex.CreditCardDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditEnterpriseDownEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanInfoEx;
import com.creditharmony.approve.credit.entity.ex.CreditReportDetailedEx;
import com.creditharmony.approve.document.dao.ZlshYxxjcDao;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.LoanMateDao;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.CardType;
import com.creditharmony.core.approve.type.CreditCardAccountStatus;
import com.creditharmony.core.approve.type.CurrencyFlag;
import com.creditharmony.core.approve.type.GuaranteeType;
import com.creditharmony.core.approve.type.LoanDtlAccountStatus;
import com.creditharmony.core.approve.type.LoanDtlLoanType;
import com.creditharmony.core.service.CoreManager;

/**
 * 征信报告
 * @Class Name CreditReportService
 * @author 李文勇
 * @Create In 2015年12月2日
 */
@Service
public class CreditRiskReportService extends CoreManager<CreditReportDao, CreditReportRisk> {

	@Autowired
	private CreditReportDao creditReportDao;
	@Autowired
	private CreditReportSimpleDao creditReportSimpleDao;
	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private EnterpriseCreditDao enterpriseCreditDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private CreditBasicInfoDao creditBasicInfoDao;
	@Autowired
	private CreditInvestorInfoDao creditInvestorInfoDao;
	@Autowired
	private LoanCoborrowerDao loanCoborrowerDao;
	@Autowired
	private LoanMateDao loanMateDao;
	@Autowired
	private CreditExecutiveInfoDao creditExecutiveInfoDao;
	@Autowired
	private CreditCreditClearedInfoDao creditCreditClearedInfoDao;
	@Autowired
	private CreditLiabilityHisDao creditLiabilityHisDao;
	@Autowired
	private CreditCurrentLiabilityInfoDao creditCurrentLiabilityInfoDao;
	@Autowired
	private CreditCurrentLiabilityDetailDao creditCurrentLiabilityDetailDao;
	@Autowired
	private CreditCreditClearedDetailDao creditCreditClearedDetailDao;
	@Autowired
	private CreditExternalSecurityInfoDao creditExternalSecurityInfoDao;
	@Autowired
	private CreditUnclearedImproperLoanDao creditUnclearedImproperLoanDao;
	@Autowired
	private CreditCivilJudgmentRecordDao creditCivilJudgmentRecordDao;
	@Autowired
	private CreditLoanCardDao creditLoanCardDao;
	@Autowired
	private CreditGradeDao creditGradeDao;
	@Autowired
	private CreditPunishDao creditPunishDao;
	@Autowired
	private CreditUnclearedLoanDao creditUnclearedLoanDao;
	@Autowired
	private CreditUnclearedTradeFinancingDao creditUnclearedTradeFinancingDao;
	@Autowired
	private CreditUnclearedFactoringDao creditUnclearedFactoringDao;
	@Autowired
	private CreditUnclearedNotesDiscountedDao creditUnclearedNotesDiscountedDao;
	@Autowired
	private CreditUnclearedBankAcceptanceDao creditUnclearedBankAcceptanceDao;
	@Autowired
	private CreditUnclearedLetterCreditDao creditUnclearedLetterCreditDao;
	@Autowired
	private CreditUnclearedLetterGuaranteeDao creditUnclearedLetterGuaranteeDao;
	@Autowired
	private CreditPaidLoanDao creditPaidLoanDao;
	@Autowired
	private ZlshYxxjcDao zlshYxxjcDao;
	
	/**
	 * 根据借款编号，信审类型获，关联ID，借款人类型，取征信报告信息
	 * 2015年12月2日
	 * By 黄维
	 * @param loanCode
	 */
	public List<CreditReportRisk> getPersonCreditReportDetailedByCode( VerifyParamEx verifyParamEx ) {
		List<CreditReportRisk> result = new ArrayList<CreditReportRisk>();
		CreditReportRisk creditReportRisk = new CreditReportRisk();
		if( verifyParamEx != null ){
			creditReportRisk.setLoanCode(verifyParamEx.getLoanCode());// 借款编号
			creditReportRisk.setrId(verifyParamEx.getRelId());// 关联ID
			creditReportRisk.setDictCheckType(verifyParamEx.getCheckType());// 信审类型
			creditReportRisk.setDictCustomerType(verifyParamEx.getType());// 借款人类型
			result = creditReportDao.getPersonCreditReportDetailedByCode(creditReportRisk);
		}
		return result;
	}

	/**
	 * 保存征信核查页面数据
	 * 2015年12月12日
	 * By 李文勇
	 * @param creditReportRiskEx
	 * @return
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public int asyncSaveCreditReportRiskInfo(CreditReportRisk creditReportRisk) {
		// 如果传过来的对象为null,返回0
		if(creditReportRisk == null){
			return NumberConstants.ZERO_INT;
		}
		// 根据借款编号，和征信报告版本查询ID
		CreditReportRisk bean = new CreditReportRisk();
		
		// 根据参数查询数据（正常情况下应该只能查询出来一条）
		List<CreditReportRisk> lis = creditReportDao.getPersonCreditReportDetailedByCode(creditReportRisk);
		// 如果查询结果不为null , 并且查询结果只有一条的情况，把查询结果赋值给bean对象
		if(lis != null && lis.size() == 1){
			bean = lis.get(0);
		}
		
		// 如果 id不存在 则添加数据
		if( bean == null || bean.getId() == null || "".equals(bean.getId()) ){
			creditReportRisk.preInsert();
			// 插入新数据
			int count = creditReportDao.asyncSaveCreditReportRiskInfo(creditReportRisk);
			return count;
		}// 如果 id 存在，并且借款编号相等和征信报告版本相等则更新数据
		else if(bean.getLoanCode().equals(creditReportRisk.getLoanCode()) && bean.getRiskCreditVersion()
				.equals(creditReportRisk.getRiskCreditVersion())){
			creditReportRisk.setId(bean.getId());
			creditReportRisk.preUpdate();
			// 更新数据
			int count = creditReportDao.updataById(creditReportRisk);
			return count;
		}else{
			return NumberConstants.ZERO_INT;
		}
	}
	
	/**
	 * 简版贷记卡负债信息
	 * 2015年12月29日
	 * By 李文勇
	 * @param loanCode
	 * @return
	 */
	public CreditCardInfo getCardByLoanCode( VerifyParamEx verifyParamEx ){
		CreditReportRisk creditReportRisk = new CreditReportRisk();
		// 借款编号
		creditReportRisk.setLoanCode(verifyParamEx.getLoanCode());
		// 借款人类型 主借人/共借人
		creditReportRisk.setDictCustomerType(verifyParamEx.getType());
		// 审批步骤 初审 复审等
		creditReportRisk.setDictCheckType(verifyParamEx.getCheckType());
		// 币种为人民币
		creditReportRisk.setEffectiveFlag(CurrencyFlag.YUAN.getCode());
		// 账户状态为正常
		creditReportRisk.setRiskEffectiveRemark(CreditCardAccountStatus.NORMAL.getCode());
		CreditCardInfo result = creditReportSimpleDao.getCardByLoanCode(creditReportRisk);
		return result;
	}
	
	/**
	 * 查询简版贷款信息
	 * 2016年1月4日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public Map<String,CreditLoanInfo> getLoanByLoanCode( VerifyParamEx verifyParamEx ){
		CreditLoanInfoEx creditLoanInfoEx = new CreditLoanInfoEx();
		// 设置借款编号
		creditLoanInfoEx.setLoanCode(verifyParamEx.getLoanCode());
		//借款人类型 主借人/共借人
		creditLoanInfoEx.setDictCustomerType(verifyParamEx.getType());
		// 审批步骤 初审 复审等
		creditLoanInfoEx.setDictCheckType(verifyParamEx.getCheckType());
		// new一个返回map(返回List用)
		Map<String,CreditLoanInfo> map = new HashMap<String,CreditLoanInfo>();
		
		//抵押房贷
		List<String> lisparam = new ArrayList<String>();
		// 个人住房贷款(0)
		lisparam.add(LoanDtlLoanType.PERSONAL_INDIVIDUAL_HOUSING_LOANS.getCode());
		// 个人住房公积金贷款(4)
		lisparam.add(LoanDtlLoanType.PERSONAL_HOUSING_ACCUMULATION_FUND_LOANS.getCode());
		// 个人商用房（包括商住两用）贷款(5)
		lisparam.add(LoanDtlLoanType.PERSONAL_COMMERCIAL_HOUSING_LOANS.getCode());
		creditLoanInfoEx.setMoneyType(lisparam);
		// 账户状态为正常
		creditLoanInfoEx.setAccountStatus(LoanDtlAccountStatus.NORMAL.getCode());
		CreditLoanInfo house = creditReportSimpleDao.getLoanByLoanCode(creditLoanInfoEx);
		map.put(CreditReportConstants.SIMPLE_PAGE_LOAN_HOUSE, house);
		
		// 抵押车贷
		lisparam.clear();
		// 个人汽车贷款(3)
		lisparam.add(LoanDtlLoanType.PERSONAL_CAR_LOANS.getCode());
		// 账户状态为正常
		creditLoanInfoEx.setAccountStatus(LoanDtlAccountStatus.NORMAL.getCode());
		CreditLoanInfo car = creditReportSimpleDao.getLoanByLoanCode(creditLoanInfoEx);
		map.put(CreditReportConstants.SIMPLE_PAGE_LOAN_CAR, car);
		
		return map;
	}
	
	
	/**
	 * 查询简版贷款信息
	 * 2016年1月4日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public CreditReportSimple selectByCreditReportSimple( VerifyParamEx verifyParamEx ){
		CreditReportSimple creditReportSimple = new CreditReportSimple();
		creditReportSimple.setLoanCode(verifyParamEx.getLoanCode());
		creditReportSimple.setDictCustomerType(verifyParamEx.getType());
		creditReportSimple.setrCustomerCoborrowerId(verifyParamEx.getRelId());
		CreditReportSimple result = creditReportSimpleDao.selectByCreditReportSimple(creditReportSimple);
		return result;
	}
	
	
	
	/**
	 * 获取详版基本信息
	 * 2016年1月7日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public CreditReportDetailedEx getBaseInfo( VerifyParamEx verifyParamEx ){
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		// 借款编号
		creditReportDetailed.setLoanCode(verifyParamEx.getLoanCode());
		// 借款人类型
		creditReportDetailed.setDictCustomerType(verifyParamEx.getType());
		// 关联ID
		creditReportDetailed.setrCustomerCoborrowerId(verifyParamEx.getRelId());
		CreditReportDetailedEx result = creditReportDetailedDao.getBaseInfo(creditReportDetailed);
		return result;
	}
	
	/**
	 * 获取详版信用卡信息
	 * 2016年1月7日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public CreditCardDetailedEx getDetailedCard( VerifyParamEx verifyParamEx ){
		CreditCardDetailedEx creditCardDetailedEx = new CreditCardDetailedEx();
		// 借款编号
		creditCardDetailedEx.setLoanCode(verifyParamEx.getLoanCode());
		// 借款人类型
		creditCardDetailedEx.setDictCustomerType(verifyParamEx.getType());
		// 关联ID
		creditCardDetailedEx.setrCustomerCoborrowerId(verifyParamEx.getRelId());
		// 卡类型为贷记卡
		creditCardDetailedEx.setCardType(CardType.CREDIT_CARD.getCode());
		// 币种为人民币
		creditCardDetailedEx.setCurrency(CurrencyFlag.YUAN.getCode());
		// 账户状态为正常
		creditCardDetailedEx.setAccountStatus(CreditCardAccountStatus.NORMAL.getCode());
		CreditCardDetailedEx result = creditReportDetailedDao.getDetailedCard(creditCardDetailedEx);
		return result;
	}
	
	/**
	 * 获取详版贷款负债信息
	 * 2016年1月8日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public Map<String,CreditLoanDetailedEx> getDetailedLoan(VerifyParamEx verifyParamEx){
		CreditLoanDetailedEx param = new CreditLoanDetailedEx();
		// 设置借款编号
		param.setLoanCode( verifyParamEx.getLoanCode() );
		//借款人类型 主借人/共借人
		param.setDictCustomerType( verifyParamEx.getType() );
		// 审批步骤 初审 复审等
		param.setDictCheckType( verifyParamEx.getCheckType() );
		
		// new一个返回map(返回List用)
		Map<String,CreditLoanDetailedEx> map = new HashMap<String,CreditLoanDetailedEx>();
		
		//贷款类型-集合
		List<String> lisparam = new ArrayList<String>();
		//贷款方式-集合
		List<String> dkfs = new ArrayList<String>();
		
		// -------抵押房贷
		//清空参数集合
		lisparam.clear();
		dkfs.clear();
		// 个人住房贷款(0)
		lisparam.add( LoanDtlLoanType.PERSONAL_INDIVIDUAL_HOUSING_LOANS.getCode() );
		// 个人住房公积金贷款(4)
		lisparam.add( LoanDtlLoanType.PERSONAL_HOUSING_ACCUMULATION_FUND_LOANS.getCode() );
		// 个人商用房（包括商住两用）贷款(5)
		lisparam.add( LoanDtlLoanType.PERSONAL_COMMERCIAL_HOUSING_LOANS.getCode() );
		param.setMoneyType( lisparam );
		param.setDkfsType(dkfs);
		// 账户状态：正常
		param.setAccountStatu(LoanDtlAccountStatus.NORMAL.getCode());
		// 币种：人民币
		param.setCurrency(CurrencyFlag.YUAN.getCode());
		CreditLoanDetailedEx house = creditReportDetailedDao.getDetailedLoan( param );
		map.put( CreditReportConstants.DETAILED_PAGE_LOAN_HOUSE , house );
		
		// -------抵押车贷
		//清空参数集合
		lisparam.clear();
		dkfs.clear();
		// 【担保方式】为抵押
		dkfs.add(GuaranteeType.MORTGAGE.getCode());
		// 【担保方式】质押（含保证金）
		dkfs.add(GuaranteeType.PLEDGE_INCLUDING_MARGIN.getCode());
		
		// 个人汽车贷款(3)
		lisparam.add(LoanDtlLoanType.PERSONAL_CAR_LOANS.getCode());
		// 账户状态：正常
		param.setAccountStatu(LoanDtlAccountStatus.NORMAL.getCode());
		// 币种：人民币
		param.setCurrency(CurrencyFlag.YUAN.getCode());
		CreditLoanDetailedEx car = creditReportDetailedDao.getDetailedLoan(param);
		map.put( CreditReportConstants.DETAILED_PAGE_LOAN_CAR , car );
		
		// -------抵押类贷款
		//清空参数集合
		lisparam.clear();
		dkfs.clear();
		// 担保方式为抵押
		dkfs.add(GuaranteeType.MORTGAGE.getCode());
		// 【担保方式】质押（含保证金）
		dkfs.add(GuaranteeType.PLEDGE_INCLUDING_MARGIN.getCode());
		// 账户状态：正常
		param.setAccountStatu(LoanDtlAccountStatus.NORMAL.getCode());
		// 币种：人民币
		param.setCurrency(CurrencyFlag.YUAN.getCode());
		CreditLoanDetailedEx mortgage = creditReportDetailedDao.getDetailedLoan(param);
		map.put( CreditReportConstants.DETAILED_PAGE_LOAN_MORTGAGE , mortgage );
		
		// --------无抵押贷款
		lisparam.clear();
		dkfs.clear();
		// 担保方式为非抵押的项（保证，信用\\免担保，组合(不含保证)，组合(含保证)，农户联保，其他）
		dkfs.add(GuaranteeType.GUARANTEE.getCode());
		dkfs.add(GuaranteeType.CREDIT_FREE_GUARANTEE.getCode());
		dkfs.add(GuaranteeType.COMBINATION_NOT_GUARANTEED.getCode());
		dkfs.add(GuaranteeType.COMPOSITION_INCLUDING_GUARANTEES.getCode());
		dkfs.add(GuaranteeType.OFGUARANTEED.getCode());
		dkfs.add(GuaranteeType.OTHER.getCode());
		// 个人经营性贷款(1)
		lisparam.add(LoanDtlLoanType.PERSONAL_BUSINESS_LOANS.getCode());
		// 农户贷款(2)
		lisparam.add(LoanDtlLoanType.PEASANT_HOUSEHOLD_LOAN.getCode());
		// 个人助学贷款(6)
		lisparam.add(LoanDtlLoanType.INDIVIDUAL_STUDENT_LOANS.getCode());
		// 个人消费贷款(7)
		lisparam.add(LoanDtlLoanType.PERSONAL_CONSUMPTION_LOANS.getCode());
		// 其他(8)
		lisparam.add(LoanDtlLoanType.Other.getCode());
		// 账户状态：正常
		param.setAccountStatu(LoanDtlAccountStatus.NORMAL.getCode());
		// 币种：人民币
		param.setCurrency(CurrencyFlag.YUAN.getCode());
		CreditLoanDetailedEx noMortgage = creditReportDetailedDao.getDetailedLoan(param);
		map.put( CreditReportConstants.DETAILED_PAGE_LOAN_NOMORTGAGE , noMortgage );
		
		return map;
	}
	
	/**
	 * 下载意见书用（详版贷记卡信息）
	 * 2016年1月21日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public List<CreditCardDetailedEx> downloadUseDetailCard(VerifyParamEx verifyParamEx,String customerType){
		
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		creditReportDetailed.setLoanCode(verifyParamEx.getLoanCode());
		creditReportDetailed.setDictCustomerType(customerType);
		creditReportDetailed.setrCustomerCoborrowerId(verifyParamEx.getRelId());
		// 把账户状态：正常放到name属性里面
		creditReportDetailed.setName(CreditCardAccountStatus.NORMAL.getCode());
		return creditReportDetailedDao.downloadUseDetailCard(creditReportDetailed);
	}
	
	/**
	 * 下载意见书用（详版贷款信息）
	 * 2016年1月21日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public List<CreditLoanDetailedEx> downloadUseDetailLoan(VerifyParamEx verifyParamEx,String customerType){
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		creditReportDetailed.setLoanCode(verifyParamEx.getLoanCode());
		creditReportDetailed.setDictCustomerType(customerType);
		creditReportDetailed.setrCustomerCoborrowerId(verifyParamEx.getRelId());
		List<CreditLoanDetailedEx> result = creditReportDetailedDao.downloadUseDetailLoan(creditReportDetailed);
		return result;
	}
	
	/**
	 * 获取所有录征信的主借人/共借人
	 * 2016年5月18日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public List<CreditReportRisk> getAllPerson(VerifyParamEx verifyParamEx){
		CreditReportRisk creditReportRisk = new CreditReportRisk();
		// 设置借款编号
		creditReportRisk.setLoanCode(verifyParamEx.getLoanCode());
		// 设置初信审或复议
		creditReportRisk.setDictCheckType(verifyParamEx.getCheckType());
		// 查询主借人和共借人简版录入情况
		List<CreditReportRisk> reportList = creditReportDao.getPersonCreditReportDetailedByCode(creditReportRisk);
		
		return reportList;
	}
	
	/**
	 * 下载页面用（简版贷记卡信息）
	 * 2016年1月21日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public List<CreditCardInfo> downloadUseSimpleCard(VerifyParamEx verifyParamEx , String customerType){
		CreditReportSimple creditReportSimple = new CreditReportSimple();
		creditReportSimple.setLoanCode(verifyParamEx.getLoanCode());
		creditReportSimple.setDictCustomerType(customerType);
		creditReportSimple.setrCustomerCoborrowerId(verifyParamEx.getRelId());
		// 把账户状态：正常放到name属性里
		creditReportSimple.setName(CreditCardAccountStatus.NORMAL.getCode());
		List<CreditCardInfo> result = creditReportSimpleDao.downloadUseSimpleCard(creditReportSimple);
		return result;
	}
	
	/**
	 * 下载页面用（简版贷款信息）
	 * 2016年1月22日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public List<CreditLoanInfo> downloadUseSimpleLoan(VerifyParamEx verifyParamEx , String customerType){
		CreditReportSimple creditReportSimple = new CreditReportSimple();
		creditReportSimple.setLoanCode(verifyParamEx.getLoanCode());
		creditReportSimple.setDictCustomerType(customerType);
		creditReportSimple.setrCustomerCoborrowerId(verifyParamEx.getRelId());
		List<CreditLoanInfo> result = creditReportSimpleDao.downloadUseSimpleLoan(creditReportSimple);
		return result;
	}
	
	/**
	 * 企业征信表格页面显示（征信核查页面用）
	 * 2016年2月24日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return 结果集list
	 */
	public List<CreditCurrentLiabilityDetail> getEnterpriseTable(VerifyParamEx verifyParamEx){
		return creditCurrentLiabilityDetailDao.getByLoanCode(verifyParamEx.getLoanCode());
	}
	
	/**
	 * 企业征信表格页面显示（征信核查页面用）
	 * 2016年2月24日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return 结果集list
	 */
	public EnterpriseCredit getEnterpriseTime(VerifyParamEx verifyParamEx){
		return enterpriseCreditDao.getVersion(verifyParamEx.getLoanCode());
	}
	
	/**
	 * 企业征信表格页面显示（下载意见书页面用）
	 * 2016年2月25日
	 * By 李文勇
	 * @param verifyParamEx
	 * @return
	 */
	public CreditEnterpriseDownEx downloadUseEnterprise(VerifyParamEx verifyParamEx){
		String loanCode = verifyParamEx.getLoanCode();
		CreditEnterpriseDownEx creditEnterpriseDownEx = new CreditEnterpriseDownEx();
		// 企业征信（内含版本）
		EnterpriseCredit enterpriseResult = enterpriseCreditDao.getVersion(loanCode);
		if( enterpriseResult != null && enterpriseResult.getCreditVersion() != null){
			creditEnterpriseDownEx.setVersion(enterpriseResult.getCreditVersion());
		}
		// 调用私有方法获取机构数量
		Map<String,Object> resultData = getOrgNum(loanCode);
		creditEnterpriseDownEx.setOrgNum((String)resultData.get("orgNum"));
		creditEnterpriseDownEx.setFirstTime((Date)resultData.get("firstTime"));
		creditEnterpriseDownEx.setNearCreditTime((Date)resultData.get("nearCreditTime"));
		creditEnterpriseDownEx.setNearCreditMoney((BigDecimal)resultData.get("nearCreditMoney"));
		creditEnterpriseDownEx.setNearDueMoney((BigDecimal)resultData.get("nearDueMoney"));
		
		// 企业征信_当前负债信息明细
		List<CreditCurrentLiabilityDetail> currentLis = creditCurrentLiabilityDetailDao.getByLoanCode(verifyParamEx.getLoanCode());
		if(ArrayHelper.isNotEmpty(currentLis)){
			for( int i = 0; i < currentLis.size(); i++){
				int count1 = currentLis.get(i).getNormalTransactionCount() != null?currentLis.get(i).getNormalTransactionCount():0;
				int count2 = currentLis.get(i).getConcernTransactionCount() != null?currentLis.get(i).getConcernTransactionCount():0;
				int count3 = currentLis.get(i).getBadnessTransactionCount() != null?currentLis.get(i).getBadnessTransactionCount():0;
				currentLis.get(i).setNormalTransactionCount(count1+count2+count3);
				BigDecimal blance1 = currentLis.get(i).getNormalBalance();
				BigDecimal blance2 = currentLis.get(i).getConcernBalance();
				BigDecimal blance3 = currentLis.get(i).getBadnessBalance();
				if(blance1 != null){
					if(blance2 != null){
						blance1 = blance1.add(blance2);
					}
					if(blance3 != null){
						blance1 = blance1.add(blance3);
					} 
					currentLis.get(i).setNormalBalance(blance1);
				}
			}
		}
		creditEnterpriseDownEx.setCurrentLiabilityDetailLis(currentLis);
		return creditEnterpriseDownEx;
	}
	
	/**
	 * 计算机构数量
	 * 2016年2月25日
	 * By 李文勇
	 * @param loanCode
	 * @return String
	 */
	private Map<String,Object> getOrgNum(String loanCode){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		LoanInfo loanResult = loanInfoDao.getLoanInfoByLoanCode(loanCode);
		Date intoTime = loanResult.getCustomerIntoTime();// 进件时间
		Date nearCreditTime = null;// 最近授信时间
		long nearCreditLong = -1;
		long nearDueLong = -1;
		BigDecimal nearCreditMoney = null;// 最近授信金额
		BigDecimal nearDueMoney = null;// 最近到期金额
		
		int orgNum = 0;// 机构数量
		Map<String,String> map = new HashMap<String,String>();
		Date firstTime = null;// 首笔时间
		// 企业征信_未结清贷款
		List<CreditUnclearedLoan> unclearedLoan = creditUnclearedLoanDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(unclearedLoan)){
			for( int i = 0; i < unclearedLoan.size(); i++ ){
				if(StringUtils.isNotEmpty(unclearedLoan.get(i).getLoanOrg())){
					map.put(unclearedLoan.get(i).getLoanOrg(), unclearedLoan.get(i).getLoanOrg());
				}
				// 如果首笔时间不为null
				if(firstTime != null ){
					// 如果放款时间不为null
					if(unclearedLoan.get(i).getLendingDay() != null){
						Date comTime = unclearedLoan.get(i).getLendingDay();// 放款日期
						BigDecimal creditmoney = unclearedLoan.get(i).getIousAmount();// 借据金额
						BigDecimal duemoney = unclearedLoan.get(i).getIousBalance();// 借据余额
						Date actualDay = unclearedLoan.get(i).getActualDay();//到期日期
						long diffAct = Math.abs(actualDay.getTime() - intoTime.getTime());// 到期日期-进件日期
						firstTime = firstTime.before(comTime) ? firstTime : comTime;// 较早的时间
						long diffLen = Math.abs((comTime.getTime() - intoTime.getTime()));// 放款日期-进件日期
						// 如果nearCreditLong大于-1的场合
						if(nearCreditLong > -1){
							// 算出最近授信时间和最近授信金额
							if(nearCreditLong > diffLen){
								nearCreditLong = diffLen;
								nearCreditTime = comTime;
								nearCreditMoney = creditmoney;
							}
						// 如果nearCreditLong等于-1的场合
						}else{
							nearCreditLong = diffLen;
							nearCreditTime=comTime;
							nearCreditMoney = creditmoney;
						}
						// 如果nearDueLong>-1的场合
						if(nearDueLong > -1){
							// 算出最近到期金额
							if(nearDueLong > diffAct){
								nearDueLong = diffAct;
								nearDueMoney = duemoney;
							}
						}else{
							nearDueLong = diffAct;
							nearDueMoney = duemoney;
						}
					}
				}
				// 如果firstTime为null的场合
				else{
					firstTime = unclearedLoan.get(i).getLendingDay();
					nearCreditTime = unclearedLoan.get(i).getLendingDay();
					nearCreditMoney = unclearedLoan.get(i).getIousAmount();
					nearDueMoney = unclearedLoan.get(i).getIousBalance();
				}
			}
		}
		// 企业征信_未结清贸易融资
		List<CreditUnclearedTradeFinancing> unclearedTrade = creditUnclearedTradeFinancingDao.selectByLoanCode(loanCode);
		// 查询【未结清贸易融资】不为空的场合
		if(ArrayHelper.isNotEmpty(unclearedTrade)){
			for( int i = 0; i < unclearedTrade.size(); i++ ){
				// 如果机构不为空，则放到map里面
				if(StringUtils.isNotEmpty(unclearedTrade.get(i).getLoanOrg())){
					map.put(unclearedTrade.get(i).getLoanOrg(), unclearedTrade.get(i).getLoanOrg());
				}
				if(firstTime != null ){
					if(unclearedTrade.get(i).getLendingDay() != null){
						BigDecimal creditmoney = unclearedTrade.get(i).getFinancingAmount();// 融资金额
						BigDecimal duemoney = unclearedTrade.get(i).getFinancingBalance();// 融资余额
						Date comTime = unclearedTrade.get(i).getLendingDay(); // 放款日期
						firstTime = firstTime.before(comTime) ? firstTime : comTime;
						long diffLen = Math.abs((comTime.getTime() - intoTime.getTime())); //放款日期 - 进件日期
						Date actualDay = unclearedTrade.get(i).getActualDay();//到期日期
						long diffAct = Math.abs(actualDay.getTime() - intoTime.getTime());// 到期日期-进件日期
						if(nearCreditLong > -1){
							// 算出最近授信时间和最近授信金额
							if(nearCreditLong > diffLen){
								nearCreditLong = diffLen;
								nearCreditTime = comTime;
								nearCreditMoney = creditmoney;
								nearDueMoney = duemoney;
							}
						}else{
							nearCreditLong = diffLen;
							nearCreditTime=comTime;
							nearCreditMoney = creditmoney;
							
						}
						if(diffAct > -1){
							// 算出最近到期金额
							if(nearDueLong > diffAct){
								nearDueLong = diffAct;
								nearDueMoney = duemoney;
							}
						}else{
							nearDueLong = diffAct;
							nearDueMoney = duemoney;
						}					
					}
				}else{
					firstTime = unclearedTrade.get(i).getLendingDay();
					nearCreditTime = unclearedTrade.get(i).getLendingDay();// 放款日期
					nearCreditMoney = unclearedTrade.get(i).getFinancingAmount();// 融资金额
					nearDueMoney = unclearedTrade.get(i).getFinancingBalance();// 融资余额
				}
			}
		}
		// 企业征信_未结清保理
		List<CreditUnclearedFactoring> unclearedFacto = creditUnclearedFactoringDao.selectByLoanCode(loanCode);
		// 查询【企业征信_未结清保理】不为空的场合
		if(ArrayHelper.isNotEmpty(unclearedFacto)){
			for( int i = 0; i < unclearedFacto.size(); i++ ){
				if(StringUtils.isNotEmpty(unclearedFacto.get(i).getLoanOrg())){
					map.put(unclearedFacto.get(i).getLoanOrg(), unclearedFacto.get(i).getLoanOrg());
				}
				if(firstTime != null ){
					if(unclearedFacto.get(i).getFactoringDay() != null){
						BigDecimal creditmoney = unclearedFacto.get(i).getFactoringAmount();
						Date comTime = unclearedFacto.get(i).getFactoringDay();
						firstTime = firstTime.before(comTime) ? firstTime : comTime;
						long diff = Math.abs((comTime.getTime() - intoTime.getTime()));
						if(nearCreditLong > -1){
							// 算出最近授信时间和最近授信金额
							if(nearCreditLong > diff){
								nearCreditLong = diff;
								nearCreditTime = comTime;
								nearCreditMoney = creditmoney;
							}
						}else{
							nearCreditLong = diff;
							nearCreditTime=comTime;
							nearCreditMoney = creditmoney;
						}
					}
				}else{
					firstTime = unclearedFacto.get(i).getFactoringDay();
					nearCreditTime = unclearedFacto.get(i).getFactoringDay();
					nearCreditMoney = unclearedFacto.get(i).getFactoringAmount();
				}
			}
		}
		// 企业征信_未结清票据贴现
		List<CreditUnclearedNotesDiscounted> unclearedNotes = creditUnclearedNotesDiscountedDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(unclearedNotes)){
			for( int i = 0; i < unclearedNotes.size(); i++ ){
				if(StringUtils.isNotEmpty(unclearedNotes.get(i).getLoanOrg())){
					map.put(unclearedNotes.get(i).getLoanOrg(), unclearedNotes.get(i).getLoanOrg());
				}
			}
		}
		// 企业征信_未结清银行承兑汇票
		List<CreditUnclearedBankAcceptance> unclearedBan = creditUnclearedBankAcceptanceDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(unclearedBan)){
			for( int i = 0; i < unclearedBan.size(); i++ ){
				if(StringUtils.isNotEmpty(unclearedBan.get(i).getLoanOrg())){
					map.put(unclearedBan.get(i).getLoanOrg(), unclearedBan.get(i).getLoanOrg());
				}
			}
		}
		// 企业征信_未结清信用证
		List<CreditUnclearedLetterCredit> unclearedLetterCredit = creditUnclearedLetterCreditDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(unclearedLetterCredit)){
			for( int i = 0; i < unclearedLetterCredit.size(); i++ ){
				if(StringUtils.isNotEmpty(unclearedLetterCredit.get(i).getLoanOrg())){
					map.put(unclearedLetterCredit.get(i).getLoanOrg(), unclearedLetterCredit.get(i).getLoanOrg());
				}
			}
		}
		// 企业征信_未结清保函
		List<CreditUnclearedLetterGuarantee> unclearedLetterGuarantee = creditUnclearedLetterGuaranteeDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(unclearedLetterGuarantee)){
			for( int i = 0; i < unclearedLetterGuarantee.size(); i++ ){
				if(StringUtils.isNotEmpty(unclearedLetterGuarantee.get(i).getLoanOrg())){
					map.put(unclearedLetterGuarantee.get(i).getLoanOrg(), unclearedLetterGuarantee.get(i).getLoanOrg());
				}
			}
		}
		// 企业征信_未结清非正常贷款
		List<CreditUnclearedImproperLoan> improper = creditUnclearedImproperLoanDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(improper)){
			for( int i = 0; i < improper.size(); i++ ){
				if(StringUtils.isNotEmpty(improper.get(i).getLoanOrg())){
					map.put(improper.get(i).getLoanOrg(), improper.get(i).getLoanOrg());
				}
				if(firstTime != null ){
					if(improper.get(i).getBusinessDay() != null){
						BigDecimal creditmoney = improper.get(i).getBusinessAmount();
						BigDecimal duemoney = improper.get(i).getBusinessBalance();
						Date comTime = improper.get(i).getBusinessDay();
						if(comTime != null){
							firstTime = firstTime.before(comTime) ? firstTime : comTime;
						}else{
							continue;
						}
						long diff = Math.abs((comTime.getTime() - intoTime.getTime()));
						Date actualDay = improper.get(i).getActualDay();
						long diffAct = Math.abs(actualDay.getTime() - intoTime.getTime());
						
						if(nearCreditLong > -1){
							// 算出最近授信时间和最近授信金额
							if(nearCreditLong > diff){
								nearCreditLong = diff;
								nearCreditTime = comTime;
								nearCreditMoney = creditmoney;
							}
						}else{
							nearCreditLong = diff;
							nearCreditTime=comTime;
							nearCreditMoney = creditmoney;
						}
						if(diffAct > -1){
							// 算出最近到期金额
							if(nearDueLong > diffAct){
								nearDueLong = diffAct;
								nearDueMoney = duemoney;
							}
						}else{
							nearDueLong = diffAct;
							nearDueMoney = duemoney;
						}		
					}
				}else{
					firstTime = improper.get(i).getBusinessDay();
					nearCreditTime = improper.get(i).getBusinessDay();
					nearCreditMoney = improper.get(i).getBusinessAmount();
					nearDueMoney = improper.get(i).getBusinessBalance();
				}
			}
		}
		// 企业征信_已结清贷款
		CreditPaidLoan creditPaidLoan = new CreditPaidLoan();
		creditPaidLoan.setLoanCode(loanCode);
		List<CreditPaidLoan> paidLoan = creditPaidLoanDao.selectByPaidLoanInfo(creditPaidLoan);
		if(ArrayHelper.isNotEmpty(paidLoan)){
			for( int i = 0; i < paidLoan.size(); i++ ){
				if(StringUtils.isNotEmpty(paidLoan.get(i).getLoanOrg())){
					map.put(paidLoan.get(i).getLoanOrg(), paidLoan.get(i).getLoanOrg());
				}
				if(firstTime != null ){
					if(paidLoan.get(i).getBusinessDay() != null){
						BigDecimal creditmoney = paidLoan.get(i).getBusinessAmount();
						Date comTime = paidLoan.get(i).getBusinessDay();
						if(comTime != null){
							firstTime = firstTime.before(comTime) ? firstTime : comTime;
						}else{
							continue;
						}
						long diff = Math.abs((comTime.getTime() - intoTime.getTime()));
						if(nearCreditLong > -1){
							if(nearCreditLong > diff){
								nearCreditLong = diff;
								nearCreditTime = comTime;
								nearCreditMoney = creditmoney;
							}
						}else{
							nearCreditLong = diff;
							nearCreditTime=comTime;
							nearCreditMoney = creditmoney;
						}
					}
				}else{
					firstTime = paidLoan.get(i).getBusinessDay();
					nearCreditTime = paidLoan.get(i).getBusinessDay();
					nearCreditMoney = paidLoan.get(i).getBusinessAmount();
				}
			}
		}
		if(map != null && map.size() > 0){
			orgNum = map.size();
		}
		resultMap.put("orgNum", orgNum+"");
		resultMap.put("firstTime", firstTime);
		resultMap.put("nearCreditTime", nearCreditTime);
		resultMap.put("nearCreditMoney", nearCreditMoney);
		resultMap.put("nearDueMoney", nearDueMoney);
		return resultMap;
	}

	/**
	 * 获取办理贷款的征信内容
	 * 2016年3月30日
	 * By 侯志斌
	 * @param creditReportRisk
	 * @return List<CreditReportRisk>
	 */
	public List<CreditReportRisk> getPersonCreditReportDetailedByCode(
			CreditReportRisk creditReportRisk) {
		return creditReportDao.getPersonCreditReportDetailedByCode(creditReportRisk);
		
	}
	/**
	 * 获取办理贷款的征信内容，按修改时间查询最新数据
	 * 2016年5月10日
	 * @param creditReportRisk
	 * @return List<CreditReportRisk>
	 */
	public List<CreditReportRisk> getPersonCreditReportDetailedInfo(
			CreditReportRisk creditReportRisk) {
		return creditReportDao.getPersonCreditReportDetailedInfo(creditReportRisk);
		
	}
	
	/**
	 * 通过applyId 获取对应的个人征信信息
	 * 2016年6月28日
	 * By 刘燕军
	 * @param applyId
	 * @return 个人征信信息集合
	 */
	public List<CreditReportRisk> getCreditList(String applyId) {
		return creditReportDao.getCreditList(applyId);
	}
}
