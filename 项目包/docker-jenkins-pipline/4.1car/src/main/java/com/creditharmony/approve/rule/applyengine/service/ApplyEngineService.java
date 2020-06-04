package com.creditharmony.approve.rule.applyengine.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.creditharmony.adapter.bean.in.crif.CrifFrontCreditDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontCreditSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontLoanDetailInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontLoanSimpleInBean;
import com.creditharmony.adapter.bean.in.crif.CrifFrontPersonalInBean;
import com.creditharmony.adapter.bean.in.img.Img_GetExistImgBarCodeListInBean;
import com.creditharmony.adapter.bean.out.crif.CrifFrontOutBean;
import com.creditharmony.adapter.bean.out.crif.CrifPersonalReasonDetailOutBean;
import com.creditharmony.adapter.bean.out.crif.CrifReasonDetailOutBean;
import com.creditharmony.adapter.bean.out.img.Img_GetExistImgBarCodeListOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.approve.base.service.ApproveCommonService;
import com.creditharmony.approve.common.constants.CustomerConstants;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.type.FileCategoryType;
import com.creditharmony.approve.common.view.PolicyEngineView;
import com.creditharmony.approve.management.dao.GlRefuseDao;
import com.creditharmony.approve.rule.applyengine.client.ApplyResult;
import com.creditharmony.approve.rule.applyengine.client.ApplyengineRulesDecisionService;
import com.creditharmony.approve.rule.applyengine.client.ApplyengineRulesRequest;
import com.creditharmony.approve.rule.applyengine.client.ApplyengineRulesResponse;
import com.creditharmony.approve.rule.applyengine.client.ApplyengineRulesSoapFault;
import com.creditharmony.approve.rule.applyengine.client.Credit;
import com.creditharmony.approve.rule.applyengine.client.CreditInfo;
import com.creditharmony.approve.rule.applyengine.client.Loan;
import com.creditharmony.approve.rule.applyengine.client.LoanApply;
import com.creditharmony.approve.rule.applyengine.client.Rating;
import com.creditharmony.approve.rule.applyengine.client.RatingParam;
import com.creditharmony.approve.rule.applyengine.client.Result;
import com.creditharmony.approve.rule.dao.ApplyCreditInfoDao;
import com.creditharmony.approve.rule.dao.CrifFrontDao;
import com.creditharmony.approve.rule.dao.CrifFrontRuleEngineDao;
import com.creditharmony.approve.rule.dao.LoanApplyInfoDao;
import com.creditharmony.approve.rule.entity.CrifFront;
import com.creditharmony.approve.verify.dao.AuditRatingResultDao;
import com.creditharmony.approve.verify.dao.AuditResultDao;
import com.creditharmony.approve.verify.dao.AuditResultSublistDao;
import com.creditharmony.approve.verify.dao.FileDiskInfoDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.approve.type.CheckType;
import com.creditharmony.core.approve.type.ChkResult;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.loan.type.LoanApplyStatus;
import com.creditharmony.core.persistence.BaseEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
/**
 * 调用进件引擎进行进件引擎判定是否可以借款
 * @Class Name ApplyEngineService
 * @author 王浩
 * @Create In 2015-12-30
 */
@Service
public class ApplyEngineService extends ApproveCommonService {
	
	@Autowired
	private LoanApplyInfoDao loanApplyInfoDao;
	@Autowired
	private ApplyCreditInfoDao applyCreditInfoDao;
	@Autowired
	private AuditRatingResultDao ratingResultDao;
	@Autowired
	private AuditResultDao auditResultDao;
	@Autowired
	private AuditResultSublistDao auditResultSublistDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private FileDiskInfoDao diskInfoDao;
	@Autowired
	GlRefuseDao refuseDao;	
	@Autowired
	ApplyengineRulesDecisionService ruleService;	
	@Autowired
	CrifFrontRuleEngineDao crifFrontRuleEngineDao;
	@Autowired
	private CrifFrontDao crifFrontDao;
	private Thread thread;
	
	private Logger log = LoggerFactory.getLogger(getClass());	
	
	/**
	 * 根据借款编号，调用进件引擎判定结果以及原因(测试用)
	 * 2016年4月7日
	 * By 王浩
	 * @param loanCode
	 * @return
	 * @throws ServiceException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public ApplyResult testApplyRule(String loanCode) throws ServiceException {	
		
		ApplyResult result = null;
		try {
			// 借款信息
			Map<String,String> applyInfo = this.getApplyInfo(loanCode);
			// 获取调用进件引擎webserice需要传入的参数
			// 大纲规则相关参数
			LoanApply apply = this.prepareWSApplyParam(applyInfo);
			// 人行规则相关参数
			CreditInfo credit = this.prepareWSCreditParam(applyInfo);
			// 进件评分相关参数
			RatingParam rateCardParam = this.prepareWSRatingParam(applyInfo);
			if (apply != null) {
				// 调用进件引擎
				result = this.getApplyEngineResult(loanCode, apply, credit, rateCardParam);				
			} else {
				throw new ServiceException("借款编码：" + loanCode + "，未查到相应数据");
			}
		} catch (Exception e) {
			log.error("进件判定出错" + e);
			throw new ServiceException(e);
		}	
		return result;
	}
	
	
	/**
	 * 引擎-前置策略接口
	 * 2016年9月24日
	 * By 王艳娜
	 * @param loanCode
	 * @return
	 * @throws ServiceException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public CrifFrontOutBean applyEngineCrifFron(String loanCode) throws ServiceException {	
		//构造保证人Map
		Map<String,Object> guarantorMap = new HashMap<String,Object>();
		guarantorMap.put("loanCode", loanCode);
		guarantorMap.put("customterType", CustomerConstants.guarantorCustomterType);
		//构造配偶Map
		Map<String,Object> mateMap = new HashMap<String,Object>();
		mateMap.put("loanCode", loanCode);
		mateMap.put("customterType",  CustomerConstants.mateType);
		//构造申请人Map
		Map<String,Object> appMap = new HashMap<String,Object>();
		appMap.put("loanCode", loanCode);
		appMap.put("customterType",  CustomerConstants.appCustomterType);
		
		List<CrifFrontPersonalInBean> personalInfos =new ArrayList<CrifFrontPersonalInBean>();
		try {
			//前置策略数据
			CrifFrontInBean crifFrontInBean = crifFrontRuleEngineDao.getCrifFrontInfo(appMap);
			
			//前置策略-个人信息-配偶
			CrifFrontPersonalInBean mateInfo = crifFrontRuleEngineDao.getCrifFrontMateInfo(mateMap);
			if(null != mateInfo){
				personalInfos.add(mateInfo);//配偶
			}

			//前置策略-个人信息-保证人
			CrifFrontPersonalInBean guarantorInfo = crifFrontRuleEngineDao.getCrifFrontGuarantorInfo(guarantorMap);
			//前置策略-个人信息-保证人-简版贷款信息
			List<CrifFrontLoanSimpleInBean> loanSimpleList = crifFrontRuleEngineDao.getCrifFrontShortLoanInfo(guarantorMap);
			if(null != loanSimpleList && loanSimpleList.size() > 0){
				guarantorInfo.setLoanSimpleList(loanSimpleList);
			}
			//前置策略-个人信息-保证人-简版信用卡信息
			List<CrifFrontCreditSimpleInBean> creditSimpleList = crifFrontRuleEngineDao.getCrifFrontShortCreditCardInfo(guarantorMap);
			if(null != creditSimpleList && creditSimpleList.size() > 0){
				guarantorInfo.setCreditSimpleList(creditSimpleList);
			}
		    //前置策略-个人信息-保证人-详版贷款信息
			List<CrifFrontLoanDetailInBean> loanDetailList = crifFrontRuleEngineDao.getCrifFrontDetailLoanInfo(guarantorMap);
			if(null != loanDetailList && loanDetailList.size() > 0){
				guarantorInfo.setLoanDetailList(loanDetailList);
			}
			//前置策略-个人信息-保证人-详版信用卡信息
			List<CrifFrontCreditDetailInBean> creditDetailList= crifFrontRuleEngineDao.getCrifFrontDetailCreditCardInfo(guarantorMap);
			if(null != creditDetailList && creditDetailList.size()>0){
				guarantorInfo.setCreditDetailList(creditDetailList);
			}
			if(null != guarantorInfo){
			    personalInfos.add(guarantorInfo);//保证人
			}
		    
			//前置策略-个人信息-申请人
			CrifFrontPersonalInBean appInfo = crifFrontRuleEngineDao.getCrifFrontAppInfo(appMap);
			//前置策略-个人信息-申请人-简版贷款信息
			List<CrifFrontLoanSimpleInBean> appLoanSimpleList = crifFrontRuleEngineDao.getCrifFrontShortLoanInfo(appMap);
			if(null != appLoanSimpleList && appLoanSimpleList.size() > 0){
				appInfo.setLoanSimpleList(appLoanSimpleList);
			}
			//前置策略-个人信息-申请人-简版信用卡信息
			List<CrifFrontCreditSimpleInBean> appCreditSimpleList = crifFrontRuleEngineDao.getCrifFrontShortCreditCardInfo(appMap);
			if(null != appCreditSimpleList && appCreditSimpleList.size() > 0){
				appInfo.setCreditSimpleList(appCreditSimpleList);
			}
			//前置策略-个人信息-申请人-详版贷款信息
			List<CrifFrontLoanDetailInBean> appLoanDetailList = crifFrontRuleEngineDao.getCrifFrontDetailLoanInfo(appMap);
			if(null != appLoanDetailList && appLoanDetailList.size() > 0){
				appInfo.setLoanDetailList(appLoanDetailList);
			}
			//前置策略-个人信息-申请人-详版信用卡信息
			List<CrifFrontCreditDetailInBean> appCreditDetailList = crifFrontRuleEngineDao.getCrifFrontDetailCreditCardInfo(appMap);
			if(null != appCreditDetailList && appCreditDetailList.size() > 0){
				appInfo.setCreditDetailList(appCreditDetailList);
			}
			if(null != appInfo){
			    personalInfos.add(appInfo);//申请人
			} 
			
			if(null != personalInfos){
				crifFrontInBean.setPersonalInfos(personalInfos);
			}
			
			//调用CRIF接口
			ClientPoxy clientPoxy = new ClientPoxy(ServiceType.Type.CRIF_FRONT_SERVICE);
			CrifFrontOutBean front =(CrifFrontOutBean) clientPoxy.callService(crifFrontInBean);
			
			//根据返回信息进行保存
			if(null != front){
				CrifFront cf = new CrifFront();
				cf.setRetCode(front.getRetCode());
				cf.setRetMsg(front.getRetMsg());
				cf.setDecision(front.getDecision());
				List<CrifPersonalReasonDetailOutBean> personals = front.getPersonals();
				JSONArray json = new JSONArray();
	            for(CrifPersonalReasonDetailOutBean a : personals){
	            	for(CrifReasonDetailOutBean b : a.getItems()){
		                JSONObject jo = new JSONObject();
		                jo.put("reasonCode", b.getReasonCode());
		                jo.put("reasonDetail", b.getReasonDetail());
		                json.add(jo);
	            	}
	            }
	            cf.setItems(json.toString());
	            cf.setLoanCode(loanCode);
	            cf.preInsert();
	            crifFrontDao.insert(cf);
			}
			return front;
		} catch (Exception e) {
			log.error("前置策略接口出错" + e);
			throw new ServiceException(e);
		}	
	}
	
	/**
	 * 根据借款编号，查询借款申请数据，并调用进件引擎进行判断，返回通过或者拒借的结果
	 * 2016年1月7日
	 * By 王浩
	 * @param loanCode 借款编码
	 * @return 返回参数到工作流中，更新工作流中字段值
	 * @throws ApplyengineRulesSoapFault 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public PolicyEngineView loadRule(final String loanCode) throws ServiceException{		
		PolicyEngineView returnView = new PolicyEngineView();
		try {
			// 借款信息
			Map<String,String> applyInfo = this.getApplyInfo(loanCode);
			// 获取调用进件引擎webserice需要传入的参数
			// 大纲规则相关参数
			LoanApply apply = this.prepareWSApplyParam(applyInfo);
			// 人行规则相关参数
			CreditInfo credit = this.prepareWSCreditParam(applyInfo);
			// 进件评分相关参数
			RatingParam rateCardParam = this.prepareWSRatingParam(applyInfo);	
			
			if (apply != null) {
				// 调用进件引擎
				ApplyResult result = this.getApplyEngineResult(loanCode, apply, credit, rateCardParam);
				// 保存历史记录
				this.saveResult(applyInfo, result);
				// 通过
				if (result.getRuleResult().equals(CheckResult.XS_SECOND_PASS.getName())) {
					returnView.setPolicyEngine(LoanApplyStatus.ANTIFRAUD_ENGINE.getName());
					returnView.setLoanStatusCode("");
					returnView.setLoanStatusName("");
					
				} else if (result.getRuleResult().equals(CheckResult.APPLY_ENGINE_BACK.getName())) {
					// 退回
					returnView.setPolicyEngine(LoanApplyStatus.APPLY_ENGINE_BACK.getName());
					returnView.setLoanStatusCode(LoanApplyStatus.APPLY_ENGINE_BACK.getCode());
					returnView.setLoanStatusName(LoanApplyStatus.APPLY_ENGINE_BACK.getName());
					
				} else if (result.getRuleResult().equals(CheckResult.APPLY_ENGINE_REFUSAL.getName())){
					// 拒借
					returnView.setPolicyEngine(LoanApplyStatus.APPLY_ENGINE_REFUSE.getName());
					returnView.setLoanStatusCode(LoanApplyStatus.APPLY_ENGINE_REFUSE.getCode());
					returnView.setLoanStatusName(LoanApplyStatus.APPLY_ENGINE_REFUSE.getName());
					
				} else {				
					throw new ServiceException("没有匹配的判定结果："+loanCode);
				}
			} else {
				throw new ServiceException("借款编码：" + loanCode + "，未查到相应数据");
			}
			
			//新起一个线程调用前置策略接口
//			if(thread  == null){
//				thread = new Thread(){
//					public void run(){
//						applyEngineCrifFron(loanCode);
//					}
//				};
//				thread.start();
//			}
		} catch (Exception e) {
			log.error("", e);
			e.printStackTrace();			
			throw new ServiceException(e);
		}	
		return returnView;
	}
		
	/**
	 * 获取loanCode,进件时间,applyId,主借人关联id等信息
	 * 2016年4月6日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	private Map<String, String> getApplyInfo(String loanCode) {
		return loanInfoDao.getCustomerIntoTimeByLoanCode(loanCode);
	}
	
	/**
	 * 根据输入的借款编码，拼接调用进件引擎的输入参数对象
	 * 2016年1月21日
	 * By 王浩
	 * @param loanCode
	 * @return 调用进件引擎的入参
	 */
	private LoanApply prepareWSApplyParam(Map<String, String> applyInfo) {
		String loanCode = applyInfo.get("loan_code");
		// 进件时间
		String customerIntoTime = applyInfo.get("customer_into_time");
					
		// 根据参数拼接调用规则引擎webservice服务时的对象参数
		List<LoanApply> applyList = loanApplyInfoDao.selectByLoanCode(loanCode);
		if (ArrayHelper.isNotEmpty(applyList)) {
			LoanApply apply = applyList.get(0);
			// 多个共借人，取共借人最大年龄
			int coborrowAge = apply.getCoborrowAge();
			String coborrowBirthPlace = "";
			String coborrowWorkCity = "";
			String coborrowLiveCity = "";			
			String coborrowIdentityNo = "";
			// 多个共借人，分别拼接共借人户籍地、工作地、居住地
			// 多个共借人，拼接共借人身份证号前六位
			if (applyList.size() > 1) {
				for (LoanApply loan : applyList) {
					if(coborrowAge < loan.getCoborrowAge()) {
						coborrowAge = loan.getCoborrowAge();
					}
					if (StringUtils.isNotEmpty(loan.getCoborrowBirthPlace())) {
						coborrowBirthPlace += loan.getCoborrowBirthPlace();  
					}
					if (StringUtils.isNotEmpty(loan.getCoborrowBirthPlace())) {
						coborrowWorkCity += loan.getCoborrowWorkCity();
					}
					if (StringUtils.isNotEmpty(loan.getCoborrowBirthPlace())) {
						coborrowLiveCity += loan.getCoborrowLiveCity();
					}
					// 18位身份证号
					if (StringUtils.isNotEmpty(loan.getCoborrowIdentityNo())
							&& loan.getCoborrowIdentityNo().length() == NumberConstants.IDENTITY_LENGTH) {
						coborrowIdentityNo += loan.getCoborrowIdentityNo().substring(0, 6);
					}
				}
			}
	
			// 更新共借人信息
			apply.setCoborrowAge(coborrowAge);
			if (StringUtils.isNotEmpty(coborrowBirthPlace)) {
				apply.setCoborrowBirthPlace(coborrowBirthPlace);  
			}
			if (StringUtils.isNotEmpty(coborrowWorkCity)) {
				apply.setCoborrowWorkCity(coborrowWorkCity);
			}
			if (StringUtils.isNotEmpty(coborrowLiveCity)) {
				apply.setCoborrowLiveCity(coborrowLiveCity);
			}
			if (StringUtils.isNotEmpty(coborrowIdentityNo)) {
				apply.setCoborrowIdentityNo(coborrowIdentityNo);
			}			
			
			// 将查询时用到的参数封装到map中
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("loanCode", loanCode);			
			// 主借人id
			String customerId = applyInfo.get("customerid");
			params.put("customerId", customerId);
			// 删除标识		
			params.put("delFlag", BaseEntity.DEL_FLAG_NORMAL);			
			
			String loaninfoOldornewFlag = applyInfo.get("loaninfo_oldornew_flag");
			// 共借人是否存在
			apply.setCoborrowerExist(loanApplyInfoDao.findCoborrowerExist(params));
			// 自然人保证人是否存在
			apply.setNaturalPersonExist(loanApplyInfoDao.findCoborrowerExist(params));
			// 法人保证人是否存在
			apply.setLegalPersonExist(loanApplyInfoDao.findLegalPersonExist(params));
			// 是否有历史还款未还清
			apply.setHisLoanUnpaid(loanApplyInfoDao.findHisLoanUnpaid(params));
			// 配偶是否有历史还款未还清
			apply.setCoupleHisLoanUnpaid(loanApplyInfoDao.findCoupleHisLoanUnpaid(params));
			// 借款人作为他人的共借人，是否有历史还款未还清
			apply.setHisLoanUnpaidAsCoborrow(loanApplyInfoDao.findHisLoanUnpaidAsCoborrow(params));
			// 配偶作为他人的共借人，是否有历史还款未还清
			apply.setCoupleHisLoanUnpaidAsCoborrow(loanApplyInfoDao
					.findCoupleHisLoanUnpaidAsCoborrow(params));
			// 配偶是否有借款当前逾期
			apply.setCoupleCurrentLoanOverdue(loanApplyInfoDao
					.findCoupleCurrentLoanOverdue(params));
			// 联系人中是否有父母
			apply.setParentsContactExists(loanApplyInfoDao.getContactParents(params));
			// 是否无征信报告
			apply.setNoCreditReport(loanApplyInfoDao.getNoCreditReport(params));
			// 是否征信白户
			apply.setNoCreditHistory(loanApplyInfoDao.getNoCreditCardAndLoan(params));			
			// 同一经营主体是否有同一经营主体以老板借/小微企业借批借尚未结清
//			apply.setCompanyHisLoanUnpaid(loanApplyInfoDao.getCompanyHisLoanUnpaid(params));
					
			// 获取汇金影像插件的索引 与部件
			Map<String,String> diskMap = diskInfoDao.getIndexComponentByQueryTime(customerIntoTime, ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());			
			
			// 影像插件文件夹是否为空判断
			ClientPoxy service = new ClientPoxy(ServiceType.Type.IMG_GET_EXIST_IMG_BARCODE);
			Img_GetExistImgBarCodeListInBean inParam = new Img_GetExistImgBarCodeListInBean();
			inParam.setIndex(diskMap.get("image_index_hj"));
			inParam.setParts(diskMap.get("image_component_hj"));
			inParam.setBatchNo(loanCode);
			try {
				if (StringUtils.isNotEmpty(customerIntoTime)) {
					inParam.setSerachDate(DateUtils.parseDate(
							customerIntoTime.replace("-", "").substring(0, 8), "yyyyMMdd"));
				} else {
					// 进件时间为空，截取借款编号中的年月日
					inParam.setSerachDate(DateUtils.parseDate(
							loanCode.substring(2, 10), "yyyyMMdd"));
				}
				
			} catch (ParseException e) {
				log.error(e.getMessage());
				e.printStackTrace();
				throw new ServiceException(e);
			}
			
			// 影像系统_取得有有影像数据的文件夹的id集合
			Img_GetExistImgBarCodeListOutBean outParam = (Img_GetExistImgBarCodeListOutBean) service.callService(inParam);
			// 不为空的影像文件夹的id集合
			String barCodesString = "";
			// 查询成功，返回数据也不为空
			if (ReturnConstant.SUCCESS.equals(outParam.getRetCode())
					&& ArrayHelper.isNotEmpty(outParam.getBarCodeList())) {				
				barCodesString = outParam.getBarCodeList().toString(); 				
			}				
			// 更新文件夹是否为空的信息
			apply = this.setFileCategoryEmpty(apply, barCodesString, loaninfoOldornewFlag);
			return apply;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据输入的借款编码以及主借人编号，拼接调用进件引擎的征信信息
	 * 2016年4月6日
	 * By 王浩
	 * @param applyInfo
	 * @return 
	 */
	private CreditInfo prepareWSCreditParam(Map<String, String> applyInfo) {
		Map<String, String> params = Maps.newHashMap();
		
		params.put("loanCode", applyInfo.get("loan_code"));
		// 主借人id
		String customerId = applyInfo.get("customerid");
		params.put("customerId", customerId);
		// 删除标识
		params.put("delFlag", BaseEntity.DEL_FLAG_NORMAL);

		// 人行规则所需的征信信息
		CreditInfo credit = applyCreditInfoDao.getCreditInfoNew(params);
		return credit;
	}
	
	/**
	 * 查询评分所需要的数据
	 * 2016年3月23日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	private RatingParam prepareWSRatingParam(Map<String, String> applyInfo) {
		Map<String, Object> params = Maps.newHashMap();		
		params.put("loanCode", applyInfo.get("loan_code"));
		// 主借人id
		params.put("customerId", applyInfo.get("customerid"));
		// 删除标识
		params.put("delFlag", BaseEntity.DEL_FLAG_NORMAL);

		// 根据参数拼接调用规则引擎webservice服务时的对象参数
		List<RatingParam> rateParamList = ratingResultDao.findApplyRatingParam(params);
		if (ArrayHelper.isNotEmpty(rateParamList)) {
			RatingParam rateParam = rateParamList.get(0);
			// 查询记录次数统计
			List<Integer> queryCounts = ratingResultDao.queryRecordCount(params);
			// 贷款审批查询
			rateParam.setQueriesLoanSp(queryCounts.get(0));
			// 本人查询次数
			rateParam.setPersonalQueryNum(queryCounts.get(1));
			// 半年内总查询次数
			rateParam.setNSixQueriesNum(queryCounts.get(2));
			// 发生过逾期的贷款个数
			rateParam.setNumLoanOverdue(ratingResultDao.countLoanOverdue(params));
			// 半年内是否有新增贷款或信用卡
			rateParam.setNSixNewLoanCredit(ratingResultDao.newCreditLoanExists(params));
			// 正常信用卡额度使用率
			rateParam.setNormalCreditUseRate(ratingResultDao.selectMaxCreditUseCentage(params));
			// 最早贷款账龄
			rateParam.setEarliestLoanMonth(ratingResultDao.selectLoanEarliestMonth(params));
			// 最早的信用卡账龄
			rateParam.setEarliestCreditMonth(ratingResultDao.selectCreditEarliestMonth(params));
			// 最近的信用卡账龄
			rateParam.setLatestCreditMonth(ratingResultDao.selectCreditLatestMonth(params));
			return rateParam;
		} else {
			return null;
		}
	}
	
	/**
	 * 调用进件引擎
	 * 2016年1月18日
	 * By 王浩
	 * @param apply
	 * @return 返回已封装判定结果的对象
	 * @throws ApplyengineRulesSoapFault 
	 */
	public ApplyResult getApplyEngineResult(String loanCode, LoanApply apply, CreditInfo creditInfo, RatingParam rateCardParam) throws ApplyengineRulesSoapFault {
		log.info("invoking applyengine, params:" + apply.toString() + ";" + rateCardParam.toString());			
		ApplyengineRulesRequest request = new ApplyengineRulesRequest();
		// 进件数据
		Loan loan = new Loan();
		loan.setLoan(apply);
		request.setLoan(loan);
		// 征信信息
		Credit credit = new Credit();
		credit.setCredit(creditInfo);
		request.setCredit(credit);
		// 评分数据
		Rating rate = new Rating();
		rate.setRating(rateCardParam);
		request.setRating(rate);
		// 判定结果
		Result ruleResult = new Result();
		ruleResult.setResult(new ApplyResult());
		request.setResult(ruleResult);
		// 获取进件引擎返回结果
		ApplyengineRulesResponse response = ruleService.applyengineRules(request);
		ApplyResult result = response.getResult().getResult();
		log.info("loanCode：" + loanCode + 
				 ", ruleResult：" + result.getRuleResult() + 
				(StringUtils.isNotEmpty(result.getRefuseCode()) 
						? ", refuseCode：" + result.getRefuseCode() : "") + 
				(StringUtils.isNotEmpty(result.getReturnReason()) 
						? ", returnReason：" + result.getReturnReason() : "") + 
				(result.getFinalScore() != null && result.getFinalScore().intValue() != 0 
						? ", finalScore：" + result.getFinalScore() : "") + 
				(StringUtils.isNotEmpty(result.getRiskLevel()) 
						? ", riskLevel：" + result.getRiskLevel() : ""));
		return result;
	}

	/**
	 * 如果进件引擎结果为回退或者拒借，那么保存到历史记录表中
	 * 2016年1月12日
	 * By 王浩
	 * @param loanCode 借款编码
	 * @param result 进件引擎返回的结果
	 * @return none
	 */
	private void saveResult(Map<String, String> applyInfo, ApplyResult result) {
		String loanCode = applyInfo.get("loan_code");		
		String applyId = applyInfo.get("apply_id");
		Map<String, String> map = null;
		VerifyBusinessView param = new VerifyBusinessView();
		param.setLoanCode(loanCode);		
		param.setApplyId(applyId);
		// 通过或拒借，保存评分数据
		// 拒借时，如果评分大于0，保存数据
		if (result.getRuleResult().equals(CheckResult.XS_SECOND_PASS.getName())
				|| (result.getRuleResult().equals(CheckResult.APPLY_ENGINE_REFUSAL.getName()) 
						&& result.getFinalScore() > 0)) {
			this.saveRatingScore(loanCode, result);
		}
		
		// 保存历史记录，更新数据
		if (result.getRuleResult().equals(CheckResult.XS_SECOND_PASS.getName())) {
			param.setLoanApplyStatus(LoanApplyStatus.ANTIFRAUD_ENGINE.getName());
			param.setCheckResult(CheckResult.APPLY_ENGINE_SUBMIT.getCode());
			param.setStepName(CheckType.APPLY_ENGINE_AUDIT.getName());
			param.setResultRemark(CheckType.APPLY_ENGINE_AUDIT.getName()
					+ CheckResult.XS_SECOND_PASS.getName());
			// 通过，保存审核历史记录
			super.updateStatusHisAndInfo(param);
		} else if (result.getRuleResult().equals(CheckResult.APPLY_ENGINE_BACK.getName())) {
			// 退回
			// 去除退回原因中第一个分隔符|
			String returnReason = this.removeSeparator(result.getReturnReason());
			param.setLoanApplyStatus(LoanApplyStatus.APPLY_ENGINE_BACK.getName());
			param.setCheckResult(CheckResult.APPLY_ENGINE_BACK.getCode());
			param.setStepName(CheckType.APPLY_ENGINE_AUDIT.getName());
			param.setResultRemark(returnReason);
			param.setFlag(true);
			// 保存业务数据
			super.updateStatusHisAndInfo(param);

		} else if (result.getRuleResult().equals(
				CheckResult.APPLY_ENGINE_REFUSAL.getName())) {			
			// 拒借
			AuditResultEx resultParam = new AuditResultEx();
			resultParam.preInsert();
			
			// 将拒借码保存到审核结果子表
			String[] refuseCode = this.removeSeparator(result.getRefuseCode()).split("\\|");			
			String refuseRemark = "";
			List<AuditResultSublist> resultSubList = Lists.newArrayList();
			for (int i = 0; i < refuseCode.length; i++) {				
				if (StringUtils.isNotEmpty(refuseCode[i])) {
					AuditResultSublist resultSub = new AuditResultSublist();
					// 去除拒借码中的X，
					String refuseCodeNoX = refuseCode[i].substring(refuseCode[i].indexOf("X") + 1);
					// 一级拒借码，将其中N01、N02、N03等的0去除掉
					String refuseFirstCode = splitRefuseCode(refuseCodeNoX, 2).replaceFirst("0", "");
					// 拼接拒借码相关的提示信息
					refuseRemark +="|" + refuseDao.findName(refuseFirstCode);					
					// 设置一级拒绝码
					resultSub.setRefuseFirstCode(refuseFirstCode);
					// 设置二级拒绝码
					resultSub.setRefuseSecondCode(splitRefuseCode(refuseCodeNoX, 4));
					// 设置三级拒绝码
					resultSub.setRefuseThirdCode(refuseCodeNoX);
					resultSubList.add(resultSub);
				}
			}
			
			param.setLoanApplyStatus(LoanApplyStatus.APPLY_ENGINE_REFUSE.getName());
			param.setCheckResult(CheckResult.APPLY_ENGINE_REFUSAL.getCode());
			param.setStepName(CheckType.APPLY_ENGINE_AUDIT.getName());
			param.setRefuseReason("进件引擎拒借");
			// 将一级拒借码对应的拒借原因设置到备注中
			param.setResultRemark(removeSeparator(refuseRemark));
			param.setResultId(resultParam.getId());
			param.setFlag(true);
			// 保存业务数据
			map = super.updateStatusHisAndInfo(param);

			resultParam.setAuditResult(ChkResult.parseByName(
					result.getRuleResult()).getCode());
			resultParam.setApplyId(applyId);
			resultParam.setLoanCode(loanCode);
			resultParam.setStepName(CheckType.APPLY_ENGINE_AUDIT.getCode());
			resultParam.setDictCheckType(ApproveCheckType.XS_APPROVE_CHECK_TYPE
					.getCode());
			// 把关联id保存到审批结果表里
			resultParam.setRStatusHisId(map.get("his"));
			resultParam.setSingleTastId(map.get("change"));
			// 将拒借结果保存到审核结果表
			if (StringUtils.isEmpty(resultParam.getModifyBy())) {
				resultParam.setModifyBy("SYS");
			}
			auditResultDao.insertByEx(resultParam);
			
			// 拒借码list保存到审批结果拒借码子表中
			if (ArrayHelper.isNotEmpty(resultSubList)) {
				for (AuditResultSublist resultSub : resultSubList) {
					// 关联审核结果表
					resultSub.setRAuditId(resultParam.getId());
					resultSub.preInsert();
					if (StringUtils.isEmpty(resultSub.getModifyBy())) {
						resultSub.setModifyBy("SYS");
					}
					auditResultSublistDao.insertSelective(resultSub);
				}				
			}
		}
	}
	
	/**
	 * 保存评分数据到业务库中
	 * 2016年3月21日
	 * By 王浩
	 * @param loanCode
	 * @param rateResult
	 * @return none
	 */
	public void saveRatingScore(String loanCode, ApplyResult result) {		
		AuditRatingResult ratingResult = ratingResultDao.findByLoanCode(loanCode);
		if (ratingResult == null) {
			ratingResult = new AuditRatingResult();
			ratingResult.setLoanCode(loanCode);
		}
		// 评分分数			
		ratingResult.setRatingScore(result.getFinalScore());
		// 通过、拒借 转换成ChkResult的code
		ratingResult.setRatingScoreDecision(ChkResult.parseByName(result.getRuleResult()).getCode());
		ratingResult.setApplyRiskLevel(result.getRiskLevel());
		// 如果数据库中已经有该条记录，那么就更新该条记录
		if (StringUtils.isNotEmpty(ratingResult.getId())) {			
			ratingResult.preUpdate();			
			
			if (StringUtils.isEmpty(ratingResult.getModifyBy())) {
				ratingResult.setModifyBy("SYS");
			}
			ratingResultDao.updateSelective(ratingResult);
		} else {			
			ratingResult.preInsert();
			
			if (StringUtils.isEmpty(ratingResult.getModifyBy())) {
				ratingResult.setCreateBy("SYS");
				ratingResult.setModifyBy("SYS");
			}
			ratingResultDao.insertSelective(ratingResult);
		}		
	}
	
	/**
	 * 如果String第一个字母是一个分隔符，去除这个分隔符
	 * 2016年1月21日
	 * By 王浩
	 * @param str
	 * @return 
	 */
	public String removeSeparator(String str) {
		if (str != null && str.startsWith("|")) {
			str = str.substring(1);
		}
		return str;
	}
	
	/**
	 * 将拒借码切割成一级拒借码或者二级拒借码
	 * 2016年1月21日
	 * By 王浩
	 * @param refuseCode 拒借码
	 * @param gap 一级或者二级拒借码中含有几个数字
	 * @return 一级或者二级拒借码
	 */
	private String splitRefuseCode(String refuseCode, int gap) {
		if (StringUtils.isNotEmpty(refuseCode)) {
			Pattern pattern = Pattern.compile("[0-9]");
			Matcher matcher = pattern.matcher(refuseCode);
			int index = -1;
			if (matcher.find()) {
				index = refuseCode.indexOf(matcher.group());
			}
			if (index >= 0) {
				return refuseCode.substring(0, index + gap > refuseCode
						.length() ? refuseCode.length() : index + gap);
			} else {
				return refuseCode;
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 2016年4月1日
	 * By 王浩
	 * @param originApply
	 * @param unEmptyCatoryIds
	 * @return 
	 */
	public LoanApply setFileCategoryEmpty(LoanApply originApply, String unEmptyCatoryIds, String loaninfoOldornewFlag) {
		// 居住证明及资产证明 中的 居住证明文件夹为空
		originApply.setLivingMaterialEmpty(unEmptyCatoryIds
				.indexOf(FileCategoryType.LIVING_MATERIAL.getCode()) > -1 ? false : true);
		// 居住证明及资产证明 中的 房产证明文件夹为空
		originApply.setRealEastateEmpty(unEmptyCatoryIds
				.indexOf(FileCategoryType.REAL_EASTATE.getCode()) > -1 ? false : true);
		// 婚姻证明文件夹为空
		originApply.setMarriageDocEmpty(unEmptyCatoryIds
				.indexOf(FileCategoryType.MARRIAGE_DOC.getCode()) > -1 ? false : true);
		// 身份证明文件夹空
		originApply.setIdentityDocEmpty(unEmptyCatoryIds.
				indexOf(FileCategoryType.IDENTITY_DOC.getCode()) > -1 ? false : true);
		// 申请证明文件夹空
		originApply.setApplyMaterialEmpty(unEmptyCatoryIds
				.indexOf(FileCategoryType.APPLY_MATERIAL.getCode()) > -1 ? false : true);
		// 工作证明文件夹为空
		originApply.setWorkDocEmpty(unEmptyCatoryIds
				.indexOf(FileCategoryType.WORK_DOC.getCode()) > -1 ? false : true);
		// 经营证明文件夹为空
		originApply.setManageDocEmpty(unEmptyCatoryIds
						.indexOf(FileCategoryType.MANAGE_DOC.getCode()) > -1 ? false : true);
		// 征信报告 企业征信文件夹为空
//		originApply.setCreditEnterpriseEmpty(unEmptyCatoryIds
//						.indexOf(FileCategoryType.CREDIT_REPORT_ENTERPRISE.getCode()) > -1 ? false : true);
		// 征信报告文件夹 个人详版 为空
		originApply.setCreditReportDetailEmpty(unEmptyCatoryIds
						.indexOf(FileCategoryType.CREDIT_REPORT_DETAIL.getCode()) > -1 ? false : true);
		// 征信报告文件夹 个人简版 为空
		originApply.setCreditReportSimpleEmpty(unEmptyCatoryIds
						.indexOf(FileCategoryType.CREDIT_REPORT_SIMPLE.getCode()) > -1 ? false : true);
		// 常用储蓄文件夹为空
		originApply.setBankSavingsEmpty(unEmptyCatoryIds
				.indexOf(FileCategoryType.BANK_SAVINGS.getCode()) > -1 ? false : true);
		// 对公流水文件夹为空
		originApply.setCompanyBankAccountEmpty(unEmptyCatoryIds
						.indexOf(FileCategoryType.COMPANY_BANK_ACCOUNT.getCode()) > -1 ? false : true);
		// 工资流水文件夹
		originApply.setSalaryDocEmpty(unEmptyCatoryIds
						.indexOf(FileCategoryType.SALARY_DOC.getCode()) > -1 ? false : true);
		// 其他文件夹空
		originApply.setOtherDocEmpty(unEmptyCatoryIds
				.indexOf(FileCategoryType.OTHER_DOC.getCode()) > -1 ? false : true);
		if ("0".equals(loaninfoOldornewFlag)) {
			// 共同借款人文件夹为空
			originApply.setCoborrowDocEmpty(unEmptyCatoryIds
							.indexOf(FileCategoryType.COBORROW_DOC.getCode()) > -1 ? false : true);
			// 自然人保证人文件夹为空
			originApply.setNaturalPersonDocEmpty(false);
			originApply.setLegalPersonDocEmpty(false);
		} else {
			// 共同借款人文件夹为空
			originApply.setCoborrowDocEmpty(false);
			// 自然人保证人文件夹为空
			originApply.setNaturalPersonDocEmpty(unEmptyCatoryIds
							.indexOf(FileCategoryType.NATURALPERSON_DOC.getCode()) > -1 ? false : true);
			// 法人保证人文件夹为空
			originApply.setLegalPersonDocEmpty(unEmptyCatoryIds
							.indexOf(FileCategoryType.LEGALPERSON_DOC.getCode()) > -1 ? false : true);
		}
		return originApply;		
	}
	
}