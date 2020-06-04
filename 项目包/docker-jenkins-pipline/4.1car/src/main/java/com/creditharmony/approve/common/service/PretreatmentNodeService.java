package com.creditharmony.approve.common.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.dao.CityInfoDao;
import com.creditharmony.approve.common.dao.CreditReportDao;
import com.creditharmony.approve.common.dao.InsertRepeatInfokDao;
import com.creditharmony.approve.common.dao.ProcedureDao;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.entity.CreditJson;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.common.entity.ReportJason;
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
import com.creditharmony.approve.credit.dao.CreditUnclearedImproperLoanDao;
import com.creditharmony.approve.credit.dao.CreditUnclearedLoanDao;
import com.creditharmony.approve.credit.entity.CreditBasicInfo;
import com.creditharmony.approve.credit.entity.CreditCardInfo;
import com.creditharmony.approve.credit.entity.CreditCivilJudgmentRecord;
import com.creditharmony.approve.credit.entity.CreditCreditClearedDetail;
import com.creditharmony.approve.credit.entity.CreditCreditClearedInfo;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityDetail;
import com.creditharmony.approve.credit.entity.CreditCurrentLiabilityInfo;
import com.creditharmony.approve.credit.entity.CreditExecutiveInfo;
import com.creditharmony.approve.credit.entity.CreditExternalSecurityInfo;
import com.creditharmony.approve.credit.entity.CreditGrade;
import com.creditharmony.approve.credit.entity.CreditInvestorInfo;
import com.creditharmony.approve.credit.entity.CreditLiabilityHis;
import com.creditharmony.approve.credit.entity.CreditLoanCard;
import com.creditharmony.approve.credit.entity.CreditPaidLoan;
import com.creditharmony.approve.credit.entity.CreditPunish;
import com.creditharmony.approve.credit.entity.CreditReportDetailed;
import com.creditharmony.approve.credit.entity.CreditReportSimple;
import com.creditharmony.approve.credit.entity.CreditUnclearedImproperLoan;
import com.creditharmony.approve.credit.entity.CreditUnclearedLoan;
import com.creditharmony.approve.credit.entity.EnterpriseCredit;
import com.creditharmony.approve.credit.entity.ex.CreditCardDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanInfoEx;
import com.creditharmony.approve.credit.entity.ex.CreditReportDetailedEx;
import com.creditharmony.approve.document.dao.ZlshCczmDao;
import com.creditharmony.approve.document.dao.ZlshGrzjDao;
import com.creditharmony.approve.document.dao.ZlshGrzjxlDao;
import com.creditharmony.approve.document.dao.ZlshGxhtDao;
import com.creditharmony.approve.document.dao.ZlshJydzzmDao;
import com.creditharmony.approve.document.dao.ZlshJyzmDao;
import com.creditharmony.approve.document.dao.ZlshJyzmGdxxDao;
import com.creditharmony.approve.document.dao.ZlshLoanAccountDao;
import com.creditharmony.approve.document.dao.ZlshSbgjjDao;
import com.creditharmony.approve.document.dao.ZlshYxxjcDao;
import com.creditharmony.approve.document.dao.ZlshZczmDao;
import com.creditharmony.approve.document.entity.ZlshCczm;
import com.creditharmony.approve.document.entity.ZlshGrzj;
import com.creditharmony.approve.document.entity.ZlshGrzjxl;
import com.creditharmony.approve.document.entity.ZlshGxht;
import com.creditharmony.approve.document.entity.ZlshJydzzm;
import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.document.entity.ZlshJyzmGdxx;
import com.creditharmony.approve.document.entity.ZlshLoanAccount;
import com.creditharmony.approve.document.entity.ZlshSbgjj;
import com.creditharmony.approve.document.entity.ZlshYxxjc;
import com.creditharmony.approve.document.entity.ZlshZczm;
import com.creditharmony.approve.internet.dao.LegalPersonCheckDao;
import com.creditharmony.approve.internet.dao.NetWorkConfigDao;
import com.creditharmony.approve.internet.dao.PrivateNetworkCheckDao;
import com.creditharmony.approve.internet.entity.LegalPersonCheck;
import com.creditharmony.approve.internet.entity.NetWorkConfig;
import com.creditharmony.approve.internet.entity.PrivateNetworkCheck;
import com.creditharmony.approve.phone.dao.DhzhBrhsDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsDhxxDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsJkjeDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxbrshdDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDhxxDao;
import com.creditharmony.approve.phone.dao.DhzhDhlyxxDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.phone.dao.WorkTelNumDao;
import com.creditharmony.approve.phone.entity.DhzhBrhsJkje;
import com.creditharmony.approve.phone.entity.DhzhDhgxbrshd;
import com.creditharmony.approve.phone.entity.DhzhDhgxshDhxx;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.WorkTelNum;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerInfoEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerNumEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactNumEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.approve.phone.entity.ex.WorkTelNumEx;
import com.creditharmony.approve.phone.service.TelCheckService;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.dao.CompManageDao;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCompanyDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.LoanHouseDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.LoanMateDao;
import com.creditharmony.approve.verify.dao.PersonalCertificateDao;
import com.creditharmony.approve.verify.entity.CompManage;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.approve.verify.entity.LoanHouse;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.LoanMate;
import com.creditharmony.approve.verify.service.VerifyCommonService;
import com.creditharmony.approve.verify.entity.PersonalCertificate;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.ChkReason;
import com.creditharmony.core.approve.type.CustomerType;
import com.creditharmony.core.approve.type.EvalResult;
import com.creditharmony.core.approve.type.HoriLoanFlag;
import com.creditharmony.core.approve.type.LoanDtlAccountStatus;
import com.creditharmony.core.approve.type.TelSrc;
import com.creditharmony.core.approve.type.WorkUnitNameSrc;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.loan.type.FamilyRelation;
import com.creditharmony.core.loan.type.RelationType;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.service.CoreManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 预处理节点
 * @Class Name PretreatmentNodeService
 * @author 刘燕军
 * @Create In 2016年1月18日
 */
@Service
public class PretreatmentNodeService extends CoreManager<CityInfoDao, CityInfo> {
	
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private NetWorkConfigDao workDao;
	@Autowired
	private InsertRepeatInfokDao insertRepeatInfokDao;
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	@Autowired
	private LoanCompanyDao loanCompanyDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private DhzhBrhsDao borrowerInfoDao;
	@Autowired
	private DhzhBrhsDhxxDao borrowerNumDao;	
	@Autowired
	private DhzhDhgxshDao contactPersonDao;	
	@Autowired
	private DhzhDhgxbrshdDao brhsLiveAddressDao;
	@Autowired
	private CreditReportDao creditReportDao;
	@Autowired
	private WorkTelNumDao workTelNumDao;
	@Autowired
	private CreditReportSimpleDao creditReportSimpleDao;
	@Autowired
	private CreditReportDetailedDao creditReportDetailedDao;
	@Autowired
	private ProcedureDao procedureDao;
	@Autowired
	private PrivateNetworkCheckDao privateNetworkCheckDao;
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
	private CreditPaidLoanDao creditPaidLoanDao;
	@Autowired
	private TelCheckService telCheckService;
	@Autowired
	private DhzhDhlyxxDao dhzhDhlyxxDao;
	@Autowired
	private DhzhBrhsJkjeDao brhsJkjeDao;
	@Autowired
	private DhzhDhgxbrshdDao brjzdDao;
	@Autowired
	private ZlshYxxjcDao yxxDao;
	@Autowired
	private ZlshLoanAccountDao zlshLoanAccountDao;
	@Autowired
	private ZlshZczmDao zlshZczmDao;
	@Autowired
	private ZlshCczmDao zlshCczmDao;
	@Autowired
	private ZlshJyzmDao zlshJyzmDao;
	@Autowired
	private ZlshJyzmGdxxDao jyzmGdxxDao;
	@Autowired
	private ZlshJydzzmDao zlshJydzzmDao;
	@Autowired
	private ZlshGxhtDao zlshGxhtDao;
	@Autowired
	private ZlshSbgjjDao zlshSbgjjDao;
	@Autowired
	private VerifyCommonService verifyCommonService;	
	@Autowired
	private DhzhDhgxshDhxxDao contactNumDao;	
	@Autowired
	private ZlshGrzjDao zlshGrzjDao;
	@Autowired
	private ZlshGrzjxlDao zlshGrzjxlDao;
	@Autowired
	private LoanHouseDao loanHouseDao;	
	@Autowired
	private CompManageDao compManageDao;	
	@Autowired
	private PersonalCertificateDao personalCertificateDao;	
	@Autowired
	private ZlshJyzmGdxxDao zlshJyzmGdxxDao;
	@Autowired
	private LegalPersonCheckDao legalPersonCheckDao;
	
	
	/**
	 * 审核预处理节点
	 * 在进入单子之前把相关的数据引入
	 * 2015年12月23日
	 * By 刘燕军
	 * @param loanCode 借款编号
	 * @param checkType 审核类型（信审、复议）
	 * @throws Exception
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void initOutside(String loanCode, String checkType) throws Exception{	
	    logger.debug("进入预处理节点 参数为：loanCode="+loanCode+"------checkType="+checkType);
		try {
			// 初始化电话照会需要的数据
			logger.debug("开始电话核查数据预处理");
			this.initTelCheckData(loanCode, checkType, BooleanType.TRUE);
			logger.debug("电话核查数据预处理结束");		
			
			// 根据借款编号获取征信核查数据
			CreditReportRisk creditReportRisk = new CreditReportRisk();
			creditReportRisk.setLoanCode(loanCode);
			List<CreditReportRisk> searchLis = creditReportDao.getPersonCreditReportDetailedByCode(creditReportRisk);
			if(ArrayHelper.isNotEmpty(searchLis)){
				for(CreditReportRisk risk : searchLis){
					if(risk != null && CreditReportConstants.DETAILED.equals(risk.getRiskCreditVersion())){
						logger.debug("征信个人详版预处理开始");
						deailDetail(loanCode,checkType,risk.getId(),risk.getrId(),NumberConstants.ZERO_STRING);// 调用详版处理
						logger.debug("征信个人详版预处理结束");
						// break;
					}
					if(risk != null && CreditReportConstants.SIMPLE.equals(risk.getRiskCreditVersion())){
						logger.debug("征信个人简版版预处理开始");
						deailSimple(loanCode,checkType,risk.getId(),risk.getrId(),NumberConstants.ZERO_STRING);// 调用简版处理
						logger.debug("征信个人简版版预处理结束");
						// break;
					}
				}
			}
			
			// 企业征信报告预处理
			/*EnterpriseCredit enterpriseCredit = new EnterpriseCredit();
			enterpriseCredit.setLoanCode(loanCode);
			EnterpriseCredit enterpriseResult = enterpriseCreditDao.selectByEnterpriseCredit(enterpriseCredit);
			if(enterpriseResult != null){
				LoanCustomer loanCustomer = new LoanCustomer();
				loanCustomer.setLoanCode(loanCode);
				LoanCustomer loanCustomerinfo = loanCustomerDao.viewGetByLoanCode(loanCustomer);
				if(loanCustomerinfo != null){
					logger.debug("征信企业版预处理开始");
					this.checkEnterpriseData(enterpriseResult,loanCustomerinfo.getId(),checkType);
					logger.debug("征信企业版预处理结束");
				}
			}*/		
			// 外网审核-专网查询-信息初始化数据(区分信审和复议,信审从汇金取数据,复议直接拷贝信审的数据作为初始化)
			if(checkType.equals(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode())) {
				// 获取新版旧版标识
				String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);		
				if ("1".equals(newOrOldFlag)) {
					//初审时从汇金获取资料处理相关数据
					this.initVefiryDataCheck(loanCode, checkType);
				}				
				List<PrivateNetworkCheck> outNetInofs = new ArrayList<PrivateNetworkCheck>();
				if ("1".equals(newOrOldFlag)) {
					// 通过查询身份证信息和单位名称（新版）
					outNetInofs = loanInfoDao.findOutsideNetInfoParamNew(loanCode, checkType);
				} else {
					// 通过查询身份证信息和单位名称（老版）
					outNetInofs = loanInfoDao.findOutsideNetInfoParam(loanCode, checkType);
				}
				if(ArrayHelper.isNotEmpty(outNetInofs)){
					// 外网审核-专网查询信息初始化
					List<PrivateNetworkCheck> netWorkInfo = new ArrayList<PrivateNetworkCheck>();
					for (PrivateNetworkCheck netWork : outNetInofs) {
						if(netWork != null){
							// 所有查询的对应的网站
							List<NetWorkConfig> configs = workDao.findOutsideNetInfoEx(netWork.getFlag());					
							netWork.setDictCheckType(checkType);
							netWork.setLoanCode(loanCode);
							for (NetWorkConfig netWorkConfig : configs) { // 遍历所有的网查信息
								PrivateNetworkCheck check = new PrivateNetworkCheck();
								BeanUtils.copyProperties(check, netWork);
								check.setConfigId(netWorkConfig.getId()); // 把起对应的id存入其中
								check.setIsNewRecord(false);
								check.setResult(YESNO.NO.getCode());// 默认为正常
								check.preInsert();
								netWorkInfo.add(check);
							}
						}
					}
					Map<String, Object> map = new HashMap<String, Object>();
					if(ArrayHelper.isNotEmpty(netWorkInfo)){
						map.put("netWorks", netWorkInfo);
						privateNetworkCheckDao.insertNetwork(map);
					}
				}
			} else if (checkType.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())) {
				// 复议时从信审拷贝资料审核的相关数据
				logger.debug("复议从信审复制资料核查的数据预处理开始");
				this.initReconsiderDataCheck(loanCode, checkType);
				logger.debug("复议从信审复制资料核查的数据预处理结束");
				// 复议直接拷贝信审的数据作为初始化， 外网审核转网查询以及资料审核
				// 外网信息初始化，复制专网信息
				Map<String, Object> map = Maps.newHashMap();
				map.put("loanCode", loanCode);
				map.put("checkType", ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());
				// 获取新版旧版标识
				String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
				List<PrivateNetworkCheck> privateNetList = privateNetworkCheckDao.findListByCodeType(map);				
				if(ArrayHelper.isNotEmpty(privateNetList)) {
					for(PrivateNetworkCheck privateNet : privateNetList){
						privateNet.setDictCheckType(checkType);
						String id = "";
						if ("1".equals(newOrOldFlag)) {
							id = telAuditWorkDao.findWorkIdNew(privateNet.getWorkId());
						} else {
							id = telAuditWorkDao.findWorkId(privateNet.getWorkId());
						}
						privateNet.setWorkId(id);
						privateNet.preInsert();
					}
					map.put("privateNets", privateNetList);
					privateNetworkCheckDao.insertNetList(map);
				}	
			}
			// 首次进入汇诚=====初审
			if (checkType.equals(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode())) {
				Map<String,String> mapp = Maps.newHashMap();
				mapp.put("loan_code", loanCode);
				mapp.put("dict_check_type", checkType);
				LegalPersonCheck legalPersonCheck = legalPersonCheckDao.getLegalPersonCheckByMap(mapp);
				if (legalPersonCheck != null) {
					LegalPersonCheck wd = legalPersonCheckDao.selectByPrimaryKey(mapp);
					if (wd != null) {
						wd.setDictCheckType(checkType);
						wd.preUpdate();
						legalPersonCheckDao.updateByEntity(wd);
					}
				} else {
					LegalPersonCheck wd = legalPersonCheckDao.selectByPrimaryKey(mapp);
					if (wd != null) {
						wd.setDictCheckType(checkType);
						wd.preInsert();
						legalPersonCheckDao.insertByEntity(wd);
					}
				}
			} else if (checkType.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())) {
				Map<String,String> mapp = Maps.newHashMap();
				mapp.put("loan_code", loanCode);
				mapp.put("dict_check_type", ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());
				LegalPersonCheck legalPersonCheck = legalPersonCheckDao.getLegalPersonCheckByMap(mapp);
				if (legalPersonCheck != null) {
					legalPersonCheck.setDictCheckType(checkType);
					legalPersonCheck.preInsert();
					legalPersonCheckDao.insertByEntity(legalPersonCheck);
				}
			}
			
			updateStatue(loanCode); // 字段更新
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 根据借款编号将资料审核数据从汇金同步到汇城
	 * 2016年9月9日
	 * By 赵春香
	 * @param loanCode 
	 * @param checkType 分为初审和复议两种
	 */
	private void initVefiryDataCheck(String loanCode, String checkType) {
	      LoanCustomer loanCustomer=loanCustomerDao.getCustomerByLoanCode(loanCode);
			//经营证明
			List<CompManage> compManages=compManageDao.getListByLoanCode(loanCode);			
			for (CompManage compManage:compManages) {
				ZlshJyzm zlshJyzm =new ZlshJyzm();
				zlshJyzm.setLoanCode(loanCode);
				zlshJyzm.setrCustomerCoborrowerId(loanCustomer.getId());
				//设置为主借人
				zlshJyzm.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());			
				zlshJyzm.setDictCheckType(checkType);		
				if (compManage.getCompRegisterCapital() !=null) {
					  zlshJyzm.setJyzmRegisteredCapital(compManage.getCompRegisterCapital().doubleValue());		
				}			  	
				zlshJyzm.setJyzmSetUpTime(compManage.getCompCreateDate());
				zlshJyzm.setJyzmLegalMan(compManage.getCorporateRepresent());
				zlshJyzm.setDictEnterpriseType(compManage.getCompType());
				zlshJyzm.setBusinessScope(compManage.getManageBusiness());
				zlshJyzm.setRegistNumType(compManage.getBusinessLicenceRegisterNum());
				//信用代码及组织机构代码
				zlshJyzm.setCreditCode(compManage.getCreditCode());
				zlshJyzm.setRegistNum(compManage.getOrgCode());
				zlshJyzm.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
				zlshJyzm.setCertNum(compManage.getCertNum());
				zlshJyzm.setManageProvince(compManage.getManageAddressProvince());
				zlshJyzm.setManageCity(compManage.getManageAddressCity());
				zlshJyzm.setManageArea(compManage.getManageAddressArea());
				zlshJyzm.setManageAddress(compManage.getManageAddress());
				//变更记录，默认为无
				zlshJyzm.setChangeFlag(ApproveConstants.ZLSH_CHANGE_FLAG);
				//汇金同步过来的数据为1
				zlshJyzm.setDictSysFlag(ApproveConstants.INFO_FROM_HJ);
				zlshJyzm.preInsert();
				zlshJyzmDao.insertJyzm(zlshJyzm);
				//同步汇金股东信息
				ZlshJyzmGdxx zlshJyzmGdxx = new ZlshJyzmGdxx();
				zlshJyzmGdxx.setrJyzmId(zlshJyzm.getId());
				zlshJyzmGdxx.setGdxxGdname(loanCustomer.getCustomerName());
				//默认为本人
				zlshJyzmGdxx.setGdxxRelation(ApproveConstants.INFO_FROM_HJ_GDXX);
				zlshJyzmGdxx.setDictCheckType(checkType);				
				if (compManage.getCustomerRatioComp() !=null) {
					zlshJyzmGdxx.setGdxxRatio(compManage.getCustomerRatioComp().toString());								
				}else{
					zlshJyzmGdxx.setGdxxRatio(ApproveConstants.INFO_FROM_HJ_RATIO);
				}
				zlshJyzmGdxx.preInsert();
				zlshJyzmGdxxDao.insertJyzmGdxx(zlshJyzmGdxx);
				//经营地址 同步汇金 
				ZlshJydzzm  zlshJydzzm =new ZlshJydzzm();
				zlshJydzzm.setLoanCode(loanCode);
				zlshJydzzm.setrJyzmId(zlshJyzm.getId());
				zlshJydzzm.setDictCheckType(checkType);
				zlshJydzzm.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
				zlshJydzzm.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);	 
				if (compManage.getMonthRentMoney()!=null) {
					zlshJydzzm.setJydzzmRentMonth(compManage.getMonthRentMoney().doubleValue());
				}
				if (compManage.getMonthPayMoney()!=null) {
					zlshJydzzm.setMonthPayMonth(compManage.getMonthPayMoney().doubleValue());
				}				        	
	         	if (compManage.getBusinessArea() !=null) {
	         		zlshJydzzm.setBusinessArea(compManage.getBusinessArea().doubleValue());
				}
                if (compManage.getManagePlace() !=null) {    
                	zlshJydzzm.setPlaceSituation(compManage.getManagePlace());
                    zlshJydzzm.preInsert();
                    zlshJydzzmDao.insertJydzzm(zlshJydzzm);
				}
			}			
			//房产证明
			List<LoanHouse> loanHouses= loanHouseDao.getListByLoanCode(loanCode);
			for (LoanHouse loanHouse : loanHouses) {
				ZlshZczm zlshZczm=new ZlshZczm(); 
				zlshZczm.setLoanCode(loanCode);
				zlshZczm.setrCustomerCoborrowerId(loanCustomer.getId());
				zlshZczm.setDictCustomerType(loanHouse.getLoanCustomterType());
				zlshZczm.setZczmHouseType(loanHouse.getDictHouseType());
				zlshZczm.setDictProvince(loanHouse.getHouseProvince());
				zlshZczm.setDictCity(loanHouse.getHouseCity());
				zlshZczm.setDictArer(loanHouse.getHouseArea());
				zlshZczm.setZczmHouseAddress(loanHouse.getHouseAddress());
				zlshZczm.setZczmPropertyMan(loanHouse.getDictHouseCommon());
				zlshZczm.setZczmPropertyRelation(loanHouse.getHousePropertyRelation());
				zlshZczm.setNetCheckResult(EvalResult.NORMAL.getCode());
				if (loanHouse.getHouseBuilingArea() !=null) {
				     zlshZczm.setZczmHouseArea(loanHouse.getHouseBuilingArea().doubleValue());
				}				
				zlshZczm.setDictCheckType(checkType);
				zlshZczm.setZczmPledgeFlag(loanHouse.getHousePledgeFlag());		
				zlshZczm.setRemark(loanHouse.getDictHouseTypeRemark());//房产类型其他时备注
				zlshZczm.setHouseBuyway(loanHouse.getHouseBuyway());
				zlshZczm.setHouseBuyDay(loanHouse.getHouseBuyday());//购买时间
				zlshZczm.setHouseCreateDay(loanHouse.getHouseCreateDay());
				if (loanHouse.getHouseAmount() !=null) {
					zlshZczm.setHouseAmount(loanHouse.getHouseAmount().doubleValue());
				}			
				if (loanHouse.getHouseLoanAmount() !=null ) {
					zlshZczm.setHouseLoanAmount(loanHouse.getHouseLoanAmount().doubleValue());
				}
				if (loanHouse.getHouseLessAmount() !=null ) {
					zlshZczm.setHouseLessAmount(loanHouse.getHouseLessAmount().doubleValue());
				}				
				if (loanHouse.getHouseMonthPayAmount() !=null) {
					zlshZczm.setHouseMonthRepayAmount(loanHouse.getHouseMonthPayAmount().doubleValue());
				}			
				if (loanHouse.getHouseLoanYear() !=null) {
					zlshZczm.setHouseLoanYear(loanHouse.getHouseLoanYear().doubleValue());
				}						
				zlshZczm.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
				zlshZczm.preInsert();
				zlshZczmDao.insertZczm(zlshZczm);
			}		
			//个人证件信息
			List<PersonalCertificate> personalCertificates=personalCertificateDao.getListByLoanCode(loanCode);
			for(PersonalCertificate personalCertificate:personalCertificates ){
				ZlshGrzj zlshGrzj=new ZlshGrzj();
				zlshGrzj.setLoanCode(loanCode);
				zlshGrzj.setrCustomerCoborrowerId(loanCustomer.getId());
				zlshGrzj.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
				zlshGrzj.setCustomerRelMaster(personalCertificate.getCustomerRelMaster());
				zlshGrzj.setCustomerRelMasterRemark(personalCertificate.getCustomerRelMasterRemark());
				zlshGrzj.setMasterName(personalCertificate.getMasterName());
				zlshGrzj.setMasterCertNum(personalCertificate.getMasterCertNum());
				//户籍性质
				zlshGrzj.setRegisterProperty(loanCustomer.getRegisterProperty());
				zlshGrzj.setMasterProvince(personalCertificate.getMasterAddressProvince());
				zlshGrzj.setMasterCity(personalCertificate.getMasterAddressCity());
				zlshGrzj.setMasterArea(personalCertificate.getMasterAddressArea());
				zlshGrzj.setMasterAddress(personalCertificate.getMasterAddress());
				zlshGrzj.setChildrenCertNum(personalCertificate.getChildrenCertNum());
				zlshGrzj.setChildrenName(personalCertificate.getChildrenName());
				//婚姻状况
				zlshGrzj.setDictMarryStatus(loanCustomer.getDictMarryStatus());
				zlshGrzj.setMarriageDate(personalCertificate.getWeddingTime());
				zlshGrzj.setCertificationUnit(personalCertificate.getLicenseIssuingAgency());
				zlshGrzj.setDictCheckType(checkType);
				zlshGrzj.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
				zlshGrzj.preInsert();
				zlshGrzjDao.insertGrzj(zlshGrzj);	
			   if (personalCertificate.getEducationalSchool() != null
					&& personalCertificate.getEducationalCertificateNum() != null
					&& personalCertificate.getEducationalCertificateTime() != null) {
				ZlshGrzjxl zlshGrzjxl=new ZlshGrzjxl();
				zlshGrzjxl.setLoanCode(loanCode);
				zlshGrzjxl.setrGrzjId(zlshGrzj.getId());
				zlshGrzjxl.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
				
				zlshGrzjxl.setEducationalCertifcateType(personalCertificate.getEducationalCertificateType());
				zlshGrzjxl.setEducationSchool(personalCertificate.getEducationalSchool());
				zlshGrzjxl.setEducationalCertifcateNum(personalCertificate.getEducationalCertificateNum());
				zlshGrzjxl.setEducationalCertifcateTime(personalCertificate.getEducationalCertificateTime());
				zlshGrzjxl.setDictCheckType(checkType);
				zlshGrzjxl.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
				zlshGrzjxl.preInsert();
				zlshGrzjxlDao.insertGrzjxl(zlshGrzjxl);
			}			
		}					
	}
	
	/**
	 * 根据借款编号将资料审核数据从信审复制到复议
	 * 2016年5月23日
	 * By 王浩
	 * @param loanCode 
	 * @param checkType 默认为复议
	 */
	private void initReconsiderDataCheck(String loanCode, String checkType) {
		// 以信审为查询条件
		String dictCheckType = ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode();
				
		// 有效性检查	
		ZlshYxxjc zlyxx = new ZlshYxxjc();
		zlyxx.setLoanCode(loanCode);
		zlyxx.setDictCheckType(dictCheckType);
		List<ZlshYxxjc> zlshYxxjcList = yxxDao.findYxxByCode(zlyxx);		
		if (ArrayHelper.isNotEmpty(zlshYxxjcList)) {
			for(ZlshYxxjc zlshYxxjc : zlshYxxjcList){
				// 将审核类型更新为复议		
				zlshYxxjc.setDictCheckType(checkType);
				zlshYxxjc.preInsert();
				yxxDao.insertSelective(zlshYxxjc);
			}			
		}		
		
		// 个人卡流水与对公卡流水
		ZlshLoanAccount za = new ZlshLoanAccount();
		za.setLoanCode(loanCode);//借款编号
		za.setDictCheckType(dictCheckType);//信审类型
		List<ZlshLoanAccount> pLoanAccount = zlshLoanAccountDao.getListByLoanCode(za);
		if (ArrayHelper.isNotEmpty(pLoanAccount)) {
			for (ZlshLoanAccount zlshLoan : pLoanAccount) {
				zlshLoan.setDictCheckType(checkType);
				zlshLoan.preInsert();
				zlshLoanAccountDao.insertLoanAccount(zlshLoan);
			}
		}		
		
		// 资产证明
		ZlshZczm zczm = new ZlshZczm();
		zczm.setLoanCode(loanCode);// 借款编号
		zczm.setDictCheckType(dictCheckType);// 信审类型
		List<ZlshZczm> zlshZczm = zlshZczmDao.getListByLoanCode(zczm);
		if (ArrayHelper.isNotEmpty(zlshZczm)) {
			for (ZlshZczm zczma : zlshZczm) {
				zczma.setDictCheckType(checkType);
				zczma.preInsert();
				zlshZczmDao.insertZczm(zczma);
			}
		}		
		
		// 车产证明
		ZlshCczm cczm = new ZlshCczm();
		cczm.setLoanCode(loanCode);// 借款编号
		cczm.setDictCheckType(dictCheckType);// 信审类型
		List<ZlshCczm> zlshCczm = zlshCczmDao.getListByLoanCode(cczm);
		if (ArrayHelper.isNotEmpty(zlshCczm)) {
			for (ZlshCczm cczma : zlshCczm) {
				cczma.setDictCheckType(checkType);
				cczma.preInsert();
				zlshCczmDao.insertCczm(cczma);
			}
		}
				
		// 经营证明
		ZlshJyzm jyzm = new ZlshJyzm();
		jyzm.setLoanCode(loanCode);// 借款编号
		jyzm.setDictCheckType(dictCheckType);// 信审类型		
		List<ZlshJyzm> zlshJyzmList = zlshJyzmDao.getListByLoanCode(jyzm);
		if (ArrayHelper.isNotEmpty(zlshJyzmList)) {
			for (ZlshJyzm jyzma : zlshJyzmList) {
				// preInsert之前，关联id为数据库查出的旧id，根据这个id查询关联表
				// 股东信息			
				List<ZlshJyzmGdxx> gdxxList = jyzmGdxxDao.selectByRid(jyzma.getId());
				// 经营地址证明
				ZlshJydzzm zm = new ZlshJydzzm();
				zm.setrJyzmId(jyzma.getId());
				List<ZlshJydzzm> jydzList = zlshJydzzmDao.getListByLoanCode(zm);
							
				jyzma.setDictCheckType(checkType);
				// preInsert执行后getId()获取新的id
				jyzma.preInsert();
				zlshJyzmDao.insertJyzm(jyzma);
				// 保存股东信息
				if (ArrayHelper.isNotEmpty(gdxxList)) {
					for (ZlshJyzmGdxx gdxx : gdxxList) {
						gdxx.setrJyzmId(jyzma.getId());
						gdxx.preInsert();
						jyzmGdxxDao.insertJyzmGdxx(gdxx);
					}
				}
				// 保存经营地址证明
				if (ArrayHelper.isNotEmpty(jydzList)) {
					for (ZlshJydzzm jydz : jydzList) {
						jydz.setrJyzmId(jyzma.getId());
						jydz.preInsert();
						zlshJydzzmDao.insertJydzzm(jydz);
					}
				}
				
			}
		}
		
		// 购销合同资料录入项
		ZlshGxht zt = new ZlshGxht();
		zt.setLoanCode(loanCode);//借款编号
		zt.setDictCheckType(dictCheckType);// 信审类型
		List<ZlshGxht> zlshGxhtList = zlshGxhtDao.getListByLoanCode(zt);
		if (ArrayHelper.isNotEmpty(zlshGxhtList)) {
			for (ZlshGxht gxht : zlshGxhtList) {
				gxht.setDictCheckType(checkType);
				gxht.preInsert();
				zlshGxhtDao.insertGxht(gxht);
			}
		}
		
		// 社保公积金录入项
		ZlshSbgjj sbgjj = new ZlshSbgjj();
		sbgjj.setLoanCode(loanCode);// 借款编号
		sbgjj.setDictCheckType(dictCheckType);// 信审类型
		List<ZlshSbgjj> zlshSbgjjList = zlshSbgjjDao.getListByLoanCode(sbgjj);
		if (ArrayHelper.isNotEmpty(zlshSbgjjList)) {
			for (ZlshSbgjj sbgjja : zlshSbgjjList) {
				sbgjja.setDictCheckType(checkType);
				sbgjja.preInsert();
				zlshSbgjjDao.insertSbgjj(sbgjja);
			}
		}		
	    //个人证件录入项
	    ZlshGrzj grzj=new ZlshGrzj();
	    grzj.setLoanCode(loanCode);// 借款编号
	    grzj.setDictCheckType(dictCheckType);// 信审类型
	    List<ZlshGrzj> zlshGrzjList = zlshGrzjDao.getListByLoanCode(grzj);
		if (ArrayHelper.isNotEmpty(zlshGrzjList)) {
			for (ZlshGrzj grzja : zlshGrzjList) {
				// preInsert之前，关联id为数据库查出的旧id，根据这个id查询关联表				
				// 个人证件信息
				ZlshGrzjxl xl = new ZlshGrzjxl();
				xl.setrGrzjId(grzja.getId());
				List<ZlshGrzjxl> grzjxlList = zlshGrzjxlDao.getListByLoanCode(xl);							
				grzja.setDictCheckType(checkType);
				// preInsert执行后getId()获取新的id
				grzja.preInsert();
				zlshGrzjDao.insertGrzj(grzja);
				// 保存个人证件学历
				if (ArrayHelper.isNotEmpty(grzjxlList)) {
					for (ZlshGrzjxl grzjxl : grzjxlList) {
						grzjxl.setrGrzjId(grzja.getId());
						grzjxl.preInsert();
						zlshGrzjxlDao.insertGrzjxl(grzjxl);
					}
				}				
			}
		}
		
	}
	
	/**
	 * 详版处理
	 * 2016年3月16日
	 * By 李文勇
	 * @param loanCode
	 * @param checkType
	 */
	private void deailDetail(String loanCode , String checkType , String riskId , String customerId,String isFirst){
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		creditReportDetailed.setLoanCode(loanCode);
		creditReportDetailed.setrCustomerCoborrowerId(customerId);
		List<CreditReportDetailedEx> resultList = creditReportDetailedDao.getAllByLoanCode(creditReportDetailed);
		if( ArrayHelper.isNotEmpty(resultList)){
			for( int i = 0; i < resultList.size(); i++ ){
				creditReportDetailed.setId(resultList.get(i).getId());
				creditReportDetailed.setrCustomerCoborrowerId(resultList.get(i).getrCustomerCoborrowerId());
				if( LoanManFlag.MAIN_LOAN.getCode().equals(resultList.get(i).getDictCustomerType())){
					creditReportDetailed.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
					CreditReportDetailedEx mainData = creditReportDetailedDao.getDetailInfo(creditReportDetailed);
					this.checkDate( mainData , loanCode , LoanManFlag.MAIN_LOAN.getCode() , resultList.get(i).getrCustomerCoborrowerId(),checkType,riskId,isFirst);
				}else if(LoanManFlag.COBORROWE_LOAN.getCode().equals(resultList.get(i).getDictCustomerType())){
					// 查询共借人信息
					creditReportDetailed.setDictCustomerType(LoanManFlag.COBORROWE_LOAN.getCode());
					List<CreditReportDetailedEx> borrowData = creditReportDetailedDao.getDetailBorrow(creditReportDetailed);
					this.checkBorrow( borrowData , loanCode , LoanManFlag.COBORROWE_LOAN.getCode() , resultList.get(i).getrCustomerCoborrowerId(),checkType,riskId,isFirst);
				}else{
					break;
				}
			}
		}else{
			// 没有录入数据
			creditReportDao.deleteById(riskId);
		}
	}
	
	/**
	 * 简版处理
	 * 2016年3月16日
	 * By 李文勇
	 * @param loanCode
	 * @param checkType
	 * @param riskId
	 */
	private void deailSimple(String loanCode , String checkType , String riskId , String customerId,String isFirst){
		// 获取简版对比信息并做成jason保存
		CreditReportSimple creditReportSimple = new CreditReportSimple();
		creditReportSimple.setLoanCode(loanCode);
		creditReportSimple.setrCustomerCoborrowerId(customerId);
		List<CreditReportSimple> simpLis = creditReportSimpleDao.getAllByLoanCode(creditReportSimple);
		if( ArrayHelper.isNotEmpty(simpLis) ){
			for( int i = 0; i < simpLis.size(); i++ ){
				CreditReportSimple simpleParam = new CreditReportSimple();
				simpleParam.setLoanCode(loanCode);
				simpleParam.setDictCustomerType(simpLis.get(i).getDictCustomerType());
				simpleParam.setrCustomerCoborrowerId(simpLis.get(i).getrCustomerCoborrowerId());
				if( LoanManFlag.MAIN_LOAN.getCode().equals( simpLis.get(i).getDictCustomerType())){
					checkSimpleData(simpleParam,checkType,riskId,isFirst);
				}else if( LoanManFlag.COBORROWE_LOAN.getCode().equals(simpLis.get(i).getDictCustomerType()) ){
					checkSimpleData(simpleParam,checkType,riskId,isFirst);
				}else{
					break;
				}
			}
		}else{
			// 没有录入数据
			creditReportDao.deleteById(riskId);
		}
	}
	
	/**
	 * 初始化电话照会所需电话照会保存单位信息，数据初始化
	 * 2016年1月20日
	 * By 王浩
	 * @param loanCode 借款编码
	 * @param checkType 核查类型：信审/复议
	 * @param isFirstTime 是否首次
	 * @return none
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void initTelCheckData(String loanCode, String checkType,
			String isFirstTime) {
		
		// TODO 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loanCode", loanCode);
		params.put("dictCustomerTypeMain", LoanManFlag.MAIN_LOAN.getCode());
		params.put("dictCustomerTypeCo", LoanManFlag.COBORROWE_LOAN.getCode());
		params.put("dictCheckType", checkType);
		params.put("familyValue", RelationType.FAMILY_CONTACTS.getCode());
		params.put("mateValue", FamilyRelation.MATES.getCode());
		params.put("customerTypeValue", CustomerType.SALARIED_PEOPLE.getCode());
		// 如果是复议，且首次取数据，则include_pool_flag等于1时，数据也可以取到。
		// 其他情况下都限制include_pool_flag不能等于1
		// 复议初审，第一次进入，将信审初审的数据全部拷贝一遍
		if (checkType.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode()) 
				&& isFirstTime.equals(BooleanType.TRUE)) {
			
			// 判断为旧版
			if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
				// 进件第一次进入复议系统，将信审电话照会表中保存的数据全部复制一遍
				params.put("dictCheckType", ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());			
				// 查找电话照会-关系审核表(联系人、配偶)数据，复制一遍
				List<TelCheckContactPersonEx> contactList = contactPersonDao.getListByCodeAndType(params);
				// 查找电话照会-单位信息，复制一遍
				List<TelCheckCompanyEx> loanCompanyList = telCheckService.getCompanyInfoList(params);
				// 查找电话照会-本人核实表，复制一遍
				List<TelCheckBorrowerInfoEx> brhsList = telCheckService.getBorrowerInfo(params, BooleanType.TRUE);
				// 重新保存
				this.saveReconsideTelData(contactList, loanCompanyList, brhsList);
				
			} else {
				// 判断为新版
				// 进件第一次进入复议系统，将信审电话照会表中保存的数据全部复制一遍
				params.put("dictCheckType", ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());			
				// TODO 查找电话照会-关系审核表(联系人、配偶)数据，复制一遍
				List<TelCheckContactPersonEx> contactList = telCheckService.getContactPersonListNew(params);
				// 查找电话照会-单位信息，复制一遍
				List<TelCheckCompanyEx> loanCompanyList = telCheckService.getCompanyInfoList(params);
				// 查找电话照会-本人核实表，复制一遍
				List<TelCheckBorrowerInfoEx> brhsList = telCheckService.getBorrowerInfo(params, BooleanType.TRUE);
				// 重新保存
				this.saveReconsideTelDataNew(contactList, loanCompanyList, brhsList);
				
			}
			
		} else {
			// 信审初审，第一次进入和后续多次，走这个分支；复议后续多次，走这个分支，
			// 如果核查类型是复议，设置为'true'
//			params.put("isReconsider", checkType.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode()) 
//								? BooleanType.TRUE : BooleanType.FALSE);
			// 是否第一次，如果是不是第一次，将不再取主借人信息和主借人单位信息
			params.put("isFirstTime", isFirstTime);
			// 查找主借人的配偶、联系人与共借人的联系人信息
			List<TelCheckContactPersonEx> contactList = loanCustomerDao.getAllContact(params);
			this.addContactPerson(contactList);
			// 从汇金查找主借人、共借人单位信息，并保存到电话照会单位信息表
			List<TelCheckCompanyEx> loanCompanyList = loanCompanyDao.getAllCustomerCompany(params);
			this.addCompanyInfo(loanCompanyList);
			// 取出主借人、共借人的本人数据，并保存到数据库中
			List<TelCheckBorrowerInfoEx> brhsList = new ArrayList<TelCheckBorrowerInfoEx>();
			// 判断为旧版
			if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
				brhsList = loanCustomerDao.getPersonalConfirmInfo(params);

			} else {
				brhsList = loanCustomerDao.getPersonalConfirmInfoNew(params);
			}
			this.addBorrowerInfo(brhsList);
			
		}
		
	}
	
	/**
	 * 发起复议后从汇金第一次进入汇诚，从汇诚信审审批数据拷贝一份到同一个表
	 * 2016年5月20日
	 * By 王浩
	 * @param contactList 联系人
	 * @param loanCompanyList 单位信息
	 * @param brhsList 本人核实
	 * @return 
	 */
	private String saveReconsideTelData(List<TelCheckContactPersonEx> contactList, 
			List<TelCheckCompanyEx> loanCompanyList, List<TelCheckBorrowerInfoEx> brhsList) {
		// 保存电话照会-联系人表
		if (ArrayHelper.isNotEmpty(contactList)) {
			for(TelCheckContactPersonEx contact : contactList) {
				// 将审核类型修改为复议
				contact.setDictCheckType(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
				contact.preInsert();
				contactPersonDao.insertSelective(contact);
				// 保存联系人的电话录音信息
				if (ArrayHelper.isNotEmpty(contact.getDhlyxx())) {
					for(DhzhDhlyxx lyxx : contact.getDhlyxx()) {
						lyxx.setrGxId(contact.getId());
						lyxx.preInsert();
						dhzhDhlyxxDao.insert(lyxx);
					}
				}
			}
		}
		// 保存电话照会-单位信息
		if (ArrayHelper.isNotEmpty(loanCompanyList)) {
			for (TelCheckCompanyEx loanCompany : loanCompanyList) {
				// 将审核类型修改为复议
				loanCompany.setDictCheckType(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
				loanCompany.preInsert();
				telAuditWorkDao.insert(loanCompany);
				// 单位电话，保存到单位电话号码表
				if (ArrayHelper.isNotEmpty(loanCompany.getTelNumList())) {
					for (WorkTelNumEx workTelNum : loanCompany.getTelNumList()) {
						workTelNum.setLoanId(loanCompany.getLoanId());
						workTelNum.setWorkId(loanCompany.getId());
						workTelNum.preInsert();
						workTelNumDao.insertWorkTelNum(workTelNum);
						// 保存单位电话的电话录音信息
						if (ArrayHelper.isNotEmpty(workTelNum.getDhlyxx())) {
							for (DhzhDhlyxx lyxx : workTelNum.getDhlyxx()) {
								lyxx.setrGxId(workTelNum.getId());
								lyxx.preInsert();
								dhzhDhlyxxDao.insert(lyxx);
							}
						}
					}

				}

			}
		}
		
		// 保存本人核实信息
		if (ArrayHelper.isNotEmpty(brhsList)) {
			for (TelCheckBorrowerInfoEx brhs : brhsList) {
				// 将审核类型修改为复议
				brhs.setDictCheckType(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
				brhs.preInsert();
				borrowerInfoDao.insert(brhs);
				
				// 将固话、手机号码组合为同一个list
				List<TelCheckBorrowerNumEx> allBrhsNumList = Lists.newArrayList();				
				if (ArrayHelper.isNotEmpty(brhs.getBorrowerNumList())) {
					allBrhsNumList.addAll(brhs.getBorrowerNumList());
				}
				if (ArrayHelper.isNotEmpty(brhs.getPhoneNumList())) {
					allBrhsNumList.addAll(brhs.getPhoneNumList());
				}
				
				// 保存到本人核实固定电话、手机号码表
				if (ArrayHelper.isNotEmpty(allBrhsNumList)) {
					for (TelCheckBorrowerNumEx borrowerNum : allBrhsNumList) {
						borrowerNum.setRBrhsId(brhs.getId());
						borrowerNum.setLoanId(brhs.getrCustomerCoborrowerId());
						borrowerNum.preInsert();
						borrowerNumDao.insertSelective(borrowerNum);
						// 保存本人核实电话的电话录音信息
						if (ArrayHelper.isNotEmpty(borrowerNum.getDhlyxx())) {
							for (DhzhDhlyxx lyxx : borrowerNum.getDhlyxx()) {
								lyxx.setrGxId(borrowerNum.getId());
								lyxx.preInsert();
								dhzhDhlyxxDao.insert(lyxx);
							}
						}
					}
				}
				
				// 保存本人核实-同业借款信息
				if (ArrayHelper.isNotEmpty(brhs.getOtherLoanList())) {
					for (DhzhBrhsJkje jkje : brhs.getOtherLoanList()) {
						jkje.setCheckId(brhs.getId());
						jkje.preInsert();
						brhsJkjeDao.insertSelective(jkje);
					}
				}
				
				// 保存本人核实-本人居住地
				if (ArrayHelper.isNotEmpty(brhs.getBrshdList())) {
					for (DhzhDhgxbrshd jzd : brhs.getBrshdList()) {
						jzd.setrCustomerCoborrowerId(brhs.getId());
						jzd.setLoanId(brhs.getrCustomerCoborrowerId());
						jzd.preInsert();
						brjzdDao.insert(jzd);
					}
				}				
			}
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 新版申请表add
	 * 发起复议后从汇金第一次进入汇诚，从汇诚信审审批数据拷贝一份到同一个表
	 * 2016年5月20日
	 * By 王浩
	 * @param contactList 联系人
	 * @param loanCompanyList 单位信息
	 * @param brhsList 本人核实
	 * @return 
	 */
	private String saveReconsideTelDataNew(List<TelCheckContactPersonEx> contactList, 
			List<TelCheckCompanyEx> loanCompanyList, List<TelCheckBorrowerInfoEx> brhsList) {
		// 保存电话照会-联系人表
		if (ArrayHelper.isNotEmpty(contactList)) {
			for(TelCheckContactPersonEx contact : contactList) {
				// 将审核类型修改为复议
				contact.setDictCheckType(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
				contact.preInsert();
				contactPersonDao.insertSelective(contact);
				
				// TODO 将固话、手机号码组合为同一个list
				List<TelCheckContactNumEx> allContactNumList = Lists.newArrayList();				
				if (ArrayHelper.isNotEmpty(contact.getContactNumList())) {
					allContactNumList.addAll(contact.getContactNumList());
				}
				if (ArrayHelper.isNotEmpty(contact.getPhoneNumList())) {
					allContactNumList.addAll(contact.getPhoneNumList());
				}
				
				if (ArrayHelper.isNotEmpty(allContactNumList)) {
					// 保存到本人核实固定电话、手机号码表
					if (ArrayHelper.isNotEmpty(allContactNumList)) {
						for (TelCheckContactNumEx contactNum : allContactNumList) {
							contactNum.setrDhgxshId(contact.getId());
							contactNum.setLoanId(contact.getrCustomerCoborrowerId());
							contactNum.preInsert();
							contactNumDao.insertSelective(contactNum);
							// 保存本人核实电话的电话录音信息
							if (ArrayHelper.isNotEmpty(contactNum.getDhlyxx())) {
								for (DhzhDhlyxx lyxx : contactNum.getDhlyxx()) {
									lyxx.setrGxId(contactNum.getId());
									lyxx.preInsert();
									dhzhDhlyxxDao.insert(lyxx);
								}
							}
						}
					}
				}
				
			}
		}
		// 保存电话照会-单位信息
		if (ArrayHelper.isNotEmpty(loanCompanyList)) {
			for (TelCheckCompanyEx loanCompany : loanCompanyList) {
				// 将审核类型修改为复议
				loanCompany.setDictCheckType(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
				loanCompany.preInsert();
				telAuditWorkDao.insert(loanCompany);
				// 单位电话，保存到单位电话号码表
				if (ArrayHelper.isNotEmpty(loanCompany.getTelNumList())) {
					for (WorkTelNumEx workTelNum : loanCompany.getTelNumList()) {
						workTelNum.setLoanId(loanCompany.getLoanId());
						workTelNum.setWorkId(loanCompany.getId());
						workTelNum.preInsert();
						workTelNumDao.insertWorkTelNum(workTelNum);
						// 保存单位电话的电话录音信息
						if (ArrayHelper.isNotEmpty(workTelNum.getDhlyxx())) {
							for (DhzhDhlyxx lyxx : workTelNum.getDhlyxx()) {
								lyxx.setrGxId(workTelNum.getId());
								lyxx.preInsert();
								dhzhDhlyxxDao.insert(lyxx);
							}
						}
					}

				}

			}
		}
		
		// 保存本人核实信息
		if (ArrayHelper.isNotEmpty(brhsList)) {
			for (TelCheckBorrowerInfoEx brhs : brhsList) {
				// 将审核类型修改为复议
				brhs.setDictCheckType(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode());
				brhs.preInsert();
				borrowerInfoDao.insert(brhs);
				
				// 将固话、手机号码组合为同一个list
				List<TelCheckBorrowerNumEx> allBrhsNumList = Lists.newArrayList();				
				if (ArrayHelper.isNotEmpty(brhs.getBorrowerNumList())) {
					allBrhsNumList.addAll(brhs.getBorrowerNumList());
				}
				if (ArrayHelper.isNotEmpty(brhs.getPhoneNumList())) {
					allBrhsNumList.addAll(brhs.getPhoneNumList());
				}
				
				// 保存到本人核实固定电话、手机号码表
				if (ArrayHelper.isNotEmpty(allBrhsNumList)) {
					for (TelCheckBorrowerNumEx borrowerNum : allBrhsNumList) {
						borrowerNum.setRBrhsId(brhs.getId());
						borrowerNum.setLoanId(brhs.getrCustomerCoborrowerId());
						borrowerNum.preInsert();
						borrowerNumDao.insertSelective(borrowerNum);
						// 保存本人核实电话的电话录音信息
						if (ArrayHelper.isNotEmpty(borrowerNum.getDhlyxx())) {
							for (DhzhDhlyxx lyxx : borrowerNum.getDhlyxx()) {
								lyxx.setrGxId(borrowerNum.getId());
								lyxx.preInsert();
								dhzhDhlyxxDao.insert(lyxx);
							}
						}
					}
				}
				
				// 保存本人核实-同业借款信息
				if (ArrayHelper.isNotEmpty(brhs.getOtherLoanList())) {
					for (DhzhBrhsJkje jkje : brhs.getOtherLoanList()) {
						jkje.setCheckId(brhs.getId());
						jkje.preInsert();
						brhsJkjeDao.insertSelective(jkje);
					}
				}
				
				// 保存本人核实-本人居住地
				if (ArrayHelper.isNotEmpty(brhs.getBrshdList())) {
					for (DhzhDhgxbrshd jzd : brhs.getBrshdList()) {
						jzd.setrCustomerCoborrowerId(brhs.getId());
						jzd.setLoanId(brhs.getrCustomerCoborrowerId());
						jzd.preInsert();
						brjzdDao.insert(jzd);
					}
				}				
			}
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 联系人信息保存到电话照会联系人表中
	 * 2015年12月4日
	 * By 王浩
	 * @param contactList 联系人list
	 * @return 是否添加成功
	 */
	private String addContactPerson(List<TelCheckContactPersonEx> contactList) {
		if (ArrayHelper.isNotEmpty(contactList)) {
			for (TelCheckContactPersonEx contact : contactList) {
				// 电话号码来源
				contact.setDictTelSource(TelSrc.TEL_SOURCE_APPLICATION_FORM
						.getCode());
				// 默认审核结果为正常(去掉)
				//contact.setDhgxshAssessResult(EvalResult.NORMAL.getCode());
				// 外网核查结果默认为正常
				contact.setWorkNetAssessResult(EvalResult.NORMAL.getCode());
				// 亲属身份证号码（包括配偶）网查结果默认为正常
				contact.setMateCertnumNetResult(EvalResult.NORMAL.getCode());//配偶身份证初始化
				// 亲属姓名（包括配偶）网查结果默认为正常
				contact.setNetCheckResult(EvalResult.NORMAL.getCode());
				contact.setIsRepeat(YESNO.YES.getCode());
				contact.setIsInPool(YESNO.YES.getCode());
				// 1表示不可编辑，0 表示可编辑
				contact.setEditRemark(YESNO.YES.getCode());
				contact.setIsReady(YESNO.NO.getCode());
				contact.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
				if(CertificateType.SFZ.getCode().equals(contact.getDictCertType())
						&& StringUtils.isNotEmpty(contact.getCustomerCertNum())) {
					// 亲属身份证号码（包括配偶）网查结果默认为正常
					contact.setMateCertnumNetResult(EvalResult.NORMAL.getCode());
				}
				if(StringUtils.isNotEmpty(contact.getDictCertType())){ // 如果证件不为空说明是配偶
					// 亲属姓名（包括配偶）网查结果默认为正常
					contact.setNetCheckResult(EvalResult.NORMAL.getCode());
				}
				// 配偶的单位名称网查结果默认为正常
				contact.setMateUnitnameNetResult(EvalResult.NORMAL.getCode());
				// 配偶的住址网查结果默认为正常
				contact.setMateAddressNetResult(EvalResult.NORMAL.getCode());
				// 新版申请表add 0：汇金数据
				contact.setDataSources(YESNO.NO.getCode());
				contact.preInsert();
				contactPersonDao.insertSelective(contact);
				// 新版申请表add
				// 电话号码类型，固话为0， 手机为1
				// 保存宅电电话表 
				this.addContactTelNum(contact.getId(), contact.getrCustomerCoborrowerId(),
						contact.getHomeTel(), YESNO.NO.getCode());				
				// 保存手机号码到电话表
				this.addContactTelNum(contact.getId(), contact.getrCustomerCoborrowerId(),
						contact.getTelNum(), YESNO.YES.getCode());
				
			}
		}
		return BooleanType.TRUE;
	}

	/**
	 * 保存单位信息，默认每个单位只有一个电话号码	  
	 * 2015年12月23日
	 * By 王浩
	 * @param loanCompanyList 工作单位list
	 * @return 是否保存成功
	 */
	private String addCompanyInfo(List<TelCheckCompanyEx> loanCompanyList) {
		if (ArrayHelper.isNotEmpty(loanCompanyList)) {
			for (TelCheckCompanyEx loanCompany : loanCompanyList) {
				loanCompany.setWorkInfoSource(WorkUnitNameSrc.UNIT_NAME_SOURCE_APPLICATION_FORM
								.getCode());
				loanCompany.setIsRepeat(YESNO.YES.getCode());
				loanCompany.setIsInPool(YESNO.YES.getCode());
				// 1不可编辑，0可以编辑，初始不可编辑
				loanCompany.setEditRemark(YESNO.YES.getCode());
				// 是否已经拿到数据
				loanCompany.setIsReady(YESNO.NO.getCode());
				// 外网核查，默认为正常
				loanCompany.setWorkNetAssessResult(EvalResult.NORMAL.getCode());
				// 单位地址网查，默认正常
				loanCompany.setNetCheckResultAddr(EvalResult.NORMAL.getCode());
				// 新版申请表add 0：汇金数据
				loanCompany.setDataSources(YESNO.NO.getCode());
				loanCompany.preInsert();
				telAuditWorkDao.insert(loanCompany);
				// 单位电话，保存到单位电话号码表
				if (!StringUtils.isBlank(loanCompany.getTelNum())) {
					WorkTelNum workTelNum = new WorkTelNum();
					workTelNum.setWorkId(loanCompany.getId());
					workTelNum.setWorkTelSource(TelSrc.TEL_SOURCE_APPLICATION_FORM
									.getCode());
					workTelNum.setWorkUnitTel(loanCompany.getTelNum());
					// 去掉默认值(正常)
					// workTelNum.setAssessmentResult(EvalResult.NORMAL.getCode());
					// 外网核查结果，默认正常
					workTelNum.setWorkNetAssessResult(EvalResult.NORMAL.getCode()); 
					workTelNum.setWorkUnittelTrue(EvalResult.NORMAL.getCode());
					workTelNum.setIsRepeat(YESNO.YES.getCode());
					// 1 表示不可编辑，0表示可以编辑
					workTelNum.setEditRemark(YESNO.YES.getCode());
					workTelNum.setIsInPool(YESNO.YES.getCode());
					workTelNum.setLoanId(loanCompany.getLoanId());
					workTelNum.preInsert();
					workTelNumDao.insertWorkTelNum(workTelNum);
				}
			}
		}
		return BooleanType.TRUE;
	}
		
	/**
	 * 保存信息到电话照会本人核实信息表
	 * 2015年12月3日
	 * By 王浩
	 * @param borrowerInfoList 本人核实信息list
	 * @return 是否保存成功
	 */
	private String addBorrowerInfo(List<TelCheckBorrowerInfoEx> borrowerInfoList) {
		if (ArrayHelper.isNotEmpty(borrowerInfoList)) {
			for (TelCheckBorrowerInfoEx borrowerInfo : borrowerInfoList) {
				// 外网核查默认为正常
				borrowerInfo.setNameNetResult(EvalResult.NORMAL.getCode());
				borrowerInfo.setCertNetAssessResult(EvalResult.NORMAL.getCode());				
				// 是否已经拿到数据
				borrowerInfo.setIsReady(YESNO.NO.getCode());
				borrowerInfo.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
				// 若汇金端传值“同业在还借款总笔数”和“月还款总额(元)”两个字段的值, 若两个字段均为空时，同业借款默认“无”
				if ( (null == borrowerInfo.getMonthPaybackTotalMoney() && 
						null == borrowerInfo.getComPaybackCount()) || 
						(BigDecimal.valueOf(0.00).compareTo(borrowerInfo.getMonthPaybackTotalMoney()) == 0) &&
								Integer.valueOf(0).equals(borrowerInfo.getComPaybackCount()) ) {
					borrowerInfo.setOtherLoanMark(HoriLoanFlag.NO.getCode());
				} else {
					borrowerInfo.setOtherLoanMark(HoriLoanFlag.YES.getCode());
				}
				// 新版申请表add 0：汇金数据
				borrowerInfo.setDataSources(YESNO.NO.getCode());
				borrowerInfo.preInsert();
				borrowerInfoDao.insert(borrowerInfo);				
				// 保存家庭固话到本人核实电话表 
				// 电话号码类型，固话为0， 手机为1
				this.addBorrowerTelNum(borrowerInfo.getId(), borrowerInfo.getrCustomerCoborrowerId(),
						borrowerInfo.getBrhsFamilyTel(), YESNO.NO.getCode());				
				// 保存本人手机号码到本人核实电话表
				// 号码1 
				this.addBorrowerTelNum(borrowerInfo.getId(), borrowerInfo.getrCustomerCoborrowerId(),
						borrowerInfo.getBrhsPhone(), YESNO.YES.getCode());
				// 本人号码2
				this.addBorrowerTelNum(borrowerInfo.getId(), borrowerInfo.getrCustomerCoborrowerId(),
						borrowerInfo.getBrhsPhoneTwo(), YESNO.YES.getCode());
				
				// 保存居住地址到本人核实-居住地址表
				this.addBorrowerLiveAddress(borrowerInfo);
				
			}
		}
		return BooleanType.TRUE;
	}

	/**
	 * 号码保存到本人核实电话信息表
	 * 2016年1月19日
	 * By 王浩
	 * @param rId 本人核实表的id
	 * @param telNum 电话号码
	 * @param telNumType 电话号码类型
	 * @return 是否添加成功
	 */
	private String addBorrowerTelNum(String rId, String customerId, String telNum, String telNumType) {
		if (StringUtils.isNotEmpty(telNum)) {
			// 电话号码保存到本人核实电话信息表
			TelCheckBorrowerNumEx borrowerTelNum = new TelCheckBorrowerNumEx();
			borrowerTelNum.setRBrhsId(rId);// 设置关联ID
			borrowerTelNum.setType(telNumType);
			// 是否电话照会保存的电话号码，否
			borrowerTelNum.setBrhsNewAdd(YESNO.NO.getCode());
			// 去掉默认值正常
			// borrowerTelNum.setBrhsAssessResult(EvalResult.NORMAL.getCode());
			// 外网核查结果默认正常
			borrowerTelNum.setWorkNetAssessResult(EvalResult.NORMAL.getCode());
			borrowerTelNum.setBrhsPhone(telNum);
			borrowerTelNum.setIsRepeat(YESNO.YES.getCode());
			borrowerTelNum.setIsInPool(YESNO.YES.getCode());
			borrowerTelNum.setSource(TelSrc.TEL_SOURCE_APPLICATION_FORM
					.getCode());
			//编辑标识(默认0可编辑1不可编辑)
			borrowerTelNum.setEditRemark(YESNO.YES.getCode());
			borrowerTelNum.setLoanId(customerId);
			borrowerTelNum.preInsert();
			borrowerNumDao.insert(borrowerTelNum);
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 新版申请表增加
	 * 号码保存到本人核实电话信息表
	 * 2016年1月19日
	 * By 王浩
	 * @param rId 本人核实表的id
	 * @param telNum 电话号码
	 * @param telNumType 电话号码类型
	 * @return 是否添加成功
	 */
	private String addContactTelNum(String rId, String customerId, String telNum, String telNumType) {
		if (StringUtils.isNotEmpty(telNum)) {
			// 电话号码保存到本人核实电话信息表
			DhzhDhgxshDhxx contactTelNum = new DhzhDhgxshDhxx();
			contactTelNum.setrDhgxshId(rId);// 设置关联ID
			contactTelNum.setType(telNumType);
			// 是否电话照会保存的电话号码，否
			contactTelNum.setBrhsNewAdd(YESNO.NO.getCode());
			// 去掉默认值正常
			// borrowerTelNum.setBrhsAssessResult(EvalResult.NORMAL.getCode());
			// 外网核查结果默认正常
			contactTelNum.setWorkNetAssessResult(EvalResult.NORMAL.getCode());
			// 亲属的“宅电”和“手机号码”网查结果默认为正常
			contactTelNum.setClanPhoneNetResult(EvalResult.NORMAL.getCode());
			contactTelNum.setBrhsPhone(telNum);
			contactTelNum.setIsRepeat(YESNO.YES.getCode());
			contactTelNum.setIsInPool(YESNO.YES.getCode());
			contactTelNum.setSource(TelSrc.TEL_SOURCE_APPLICATION_FORM
					.getCode());
			//编辑标识(默认0可编辑1不可编辑)
			contactTelNum.setEditRemark(YESNO.YES.getCode());
			contactTelNum.setLoanId(customerId);
			contactTelNum.preInsert();
			contactNumDao.insertSelective(contactTelNum);
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 保存到本人核实-居住地址表
	 * 2016年3月23日
	 * By 王浩
	 * @param borrowerInfo
	 * @return true
	 */
	private String addBorrowerLiveAddress(TelCheckBorrowerInfoEx borrowerInfo) {
		
		if (borrowerInfo != null) {
			DhzhDhgxbrshd liveAddress = new DhzhDhgxbrshd(); 
			// rCustomerCoborrowerId 此处为本人核实表id
			liveAddress.setrCustomerCoborrowerId(borrowerInfo.getId());
			liveAddress.setLiveProvince(borrowerInfo.getDictProvince());
			liveAddress.setLiveCity(borrowerInfo.getDictCity());
			liveAddress.setLiveArea(borrowerInfo.getDictArer());
			liveAddress.setLiveAddress(borrowerInfo.getBrhsLiveAddress());
			// 外网核查，默认为正常
			liveAddress.setNetCheckResult(EvalResult.NORMAL.getCode());
			liveAddress.setIsRepeat(YESNO.YES.getCode());
			liveAddress.setIsInPool(YESNO.YES.getCode());
			// 汇金来源表id，为loan_customer或者loan_coborrower表的id
			liveAddress.setLoanId(borrowerInfo.getrCustomerCoborrowerId());
			liveAddress.preInsert();
			brhsLiveAddressDao.insert(liveAddress);			
		}
		return BooleanType.TRUE;
	}
	
	/**
	 * 非首次预处理节点
	 * 在进入单子之前把相关的数据引入
	 * 2015年12月23日
	 * By 刘燕军
	 * @param loanCode
	 * @throws Exception
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void initOutsideOther(String loanCode,String checkType) throws Exception{	
	    logger.debug("进入预处理节点 参数为：loanCode="+loanCode+"------checkType="+checkType);
	    
		try {
			// 更新电话照会需要的信息
			logger.debug("开始电话核查数据预处理");
			this.initTelCheckData(loanCode, checkType, BooleanType.FALSE);
			logger.debug("电话核查数据预处理结束");
			
			// 根据借款编号，查询新增共借人，进行征信对比			
			CreditReportRisk creditReportRisk = new CreditReportRisk();
			creditReportRisk.setLoanCode(loanCode);// 借款编号
			List<CreditReportRisk> result = creditReportDao.getPersonCreditReportDetailedByCode(creditReportRisk);	
			if(ArrayHelper.isNotEmpty(result)){
				for(CreditReportRisk risk : result){
					if(risk != null && CreditReportConstants.DETAILED.equals(risk.getRiskCreditVersion())
							&& risk.getCreditJson() == null){
						logger.debug("征信个人详版预处理开始");
						deailDetail(loanCode, risk.getDictCheckType(), risk.getId(), risk.getrId(),NumberConstants.ONE_STRING);// 调用详版处理
						logger.debug("征信个人详版预处理结束");
					}
					if(risk != null && CreditReportConstants.SIMPLE.equals(risk.getRiskCreditVersion())
							&& risk.getCreditJson() == null){
						logger.debug("征信个人简版版预处理开始");
						deailSimple(loanCode, risk.getDictCheckType(), risk.getId(), risk.getrId(),NumberConstants.ONE_STRING);// 调用简版处理
						logger.debug("征信个人简版版预处理结束");
					}
				}
			}
			// 通过查询身份证信息和单位名称  封装为需要json
			// 获取新版旧版标识
			String newOrOldFlag = verifyCommonService.getOldornewFlag(loanCode);
			List<PrivateNetworkCheck> outNetInofs = new ArrayList<PrivateNetworkCheck>();
			if ("1".equals(newOrOldFlag)) {
				// 通过查询身份证信息和单位名称（新版）
				outNetInofs = loanInfoDao.findOutsideNetParamNew(loanCode, checkType);
			} else {
				// 通过查询身份证信息和单位名称（老版）
				outNetInofs = loanInfoDao.findOutsideNetParam(loanCode, checkType);
			}
			if(ArrayHelper.isNotEmpty(outNetInofs)){
				// 外网审核信息初始化
				List<PrivateNetworkCheck> netWorkInfo = new ArrayList<PrivateNetworkCheck>();
				for (PrivateNetworkCheck netWork : outNetInofs) {
					if(netWork != null){
						// 所有查询的对应的网站
						List<NetWorkConfig> configs = workDao.findOutsideNetInfoEx(netWork.getFlag());					
						netWork.setDictCheckType(checkType);
						netWork.setLoanCode(loanCode);
						for (NetWorkConfig netWorkConfig : configs) { // 遍历所有的网查信息
							PrivateNetworkCheck check = new PrivateNetworkCheck();
							BeanUtils.copyProperties(check, netWork);
							check.setConfigId(netWorkConfig.getId()); // 把起对应的id存入其中
							check.setIsNewRecord(false);
							check.setResult(YESNO.NO.getCode()); // 默认为正常
							check.preInsert();
							netWorkInfo.add(check);
						}					
					}
				}
				Map<String, Object> map = new HashMap<String, Object>();
				if(ArrayHelper.isNotEmpty(netWorkInfo)){
					map.put("netWorks", netWorkInfo);
					privateNetworkCheckDao.insertNetwork(map);
				}
			}
			logger.debug("第二次查重开始");
			// 第二次查重
			Map<String, String> mapRepeate = new HashMap<String, String>(); 
			mapRepeate.put("loanCode", loanCode);
			mapRepeate.put("checkType", checkType);
			procedureDao.verifyrepeatSecond(mapRepeate);
			logger.debug("第二次查重结束");
			readyForRepeatOther(loanCode); // 第二次查重信息入池
			updateStatue(loanCode); // 字段更新
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 查重信息准备非首次
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 * 无返回值
	 */
	private void readyForRepeatOther(String loanCode){
		logger.debug("进入信息入库（非首次）：readyForRepeatOther");
		// 把需要查重的信息放入指定的表中
/*		insertRepeatInfokDao.insertPhoneInfoOther(loanCode);
		insertRepeatInfokDao.insertWorkNameInfo(loanCode);
		insertRepeatInfokDao.insertWorkAddressOther(loanCode);*/
		procedureDao.initPoolOther(loanCode);
		logger.debug("非首次信息入库成功（非首次）：readyForRepeatOther");
	}
	
	/**
	 * 查重后把标记更新
	 * 2016年1月18日
	 * By 刘燕军
	 * @param loanCode
	 */
	private void updateStatue(String loanCode){
		logger.debug("进入信息更新：updateStatue");
		// 把该表指定的字段更新，表示已经放入查重表中
		insertRepeatInfokDao.updateCobo(loanCode);
		insertRepeatInfokDao.updateCompany(loanCode);
		insertRepeatInfokDao.updateContact(loanCode);
		insertRepeatInfokDao.updateMate(loanCode);
		insertRepeatInfokDao.updateCompManage(loanCode);
		// 更新为已经初始化
		telAuditWorkDao.upateFlag(loanCode);
		logger.debug("信息更新成功：updateStatue");
	}
	
	/**
	 * 征信报告简版数据对比并保存
	 * 2016年1月13日
	 * By 李文勇
	 * @param simpleParam
	 * @param checkType
	 * @return none
	 */
	private void checkSimpleData(CreditReportSimple simpleParam,String checkType,String riskId,String isFirst){
		CreditJson creditJson = new CreditJson();
		creditJson.setInitFlag(NumberConstants.ZERO_STRING);
		List<ReportJason> lis = new ArrayList<ReportJason>();
		
		logger.debug("征信简版风险点1对比开始");
		ReportJason reportA = new ReportJason();
		// 【信用卡明细信息】或【贷款明细信息】中【逾期金额】不为0，则打勾；均为0则打勾。
		String maxOverdue = this.getSimpleMaxOverdue(simpleParam);
		reportA.setCheckBox(CreditReportConstants.SIMPLE_BOX_ONE);
		reportA.setSysChooseFlag(StringUtils.isNotEmpty(maxOverdue) ? maxOverdue:CreditReportConstants.UNCHOOSE);
		reportA.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportA);
		logger.debug("征信简版风险点1对比结束");
		
		logger.debug("征信简版风险点2对比开始");
		// 【账户状态为正常】且【到期日期】已在此次判定时间之前，则打勾；否则不打。
		ReportJason reportB = new ReportJason();
		String simpleStatus = this.getSimpleStatus(simpleParam);
		reportB.setCheckBox(CreditReportConstants.SIMPLE_BOX_TWO);
		reportB.setSysChooseFlag(StringUtils.isNotEmpty(simpleStatus) ? simpleStatus:CreditReportConstants.UNCHOOSE);
		reportB.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportB);
		logger.debug("征信简版风险点2对比结束");
		
		logger.debug("征信简版风险点3对比开始");
		// 查询信息中，近半年，查询原因中出现5次及以上【信用卡审批】或3次及以上【贷款审批】，或信用卡审批次数+贷款审批次数超过5次（含5次）则打勾，否则不打。
		ReportJason reportE = new ReportJason();
		String simpleReason = this.getSimpleReason(simpleParam);
		reportE.setCheckBox(CreditReportConstants.SIMPLE_BOX_THREE);
		reportE.setSysChooseFlag(StringUtils.isNotEmpty(simpleReason) ? simpleReason:CreditReportConstants.UNCHOOSE);
		reportE.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportE);
		logger.debug("征信简版风险点3对比结束");
		
		creditJson.setReportJason(lis);
		this.saveSimpDateReaday(creditJson, simpleParam,checkType,riskId,isFirst);
	}
	
	/**
	 * 简版数据存储预处理
	 * 2016年1月13日
	 * By 李文勇
	 * @param creditJson
	 * @param simpleParam
	 * @param checkType
	 * @return none
	 */
	private void saveSimpDateReaday(CreditJson creditJson,CreditReportSimple simpleParam,String checkType,String riskId,String isFirst){
		CreditReportRisk creditReportRisk = new CreditReportRisk();
		// 借款编号
		creditReportRisk.setLoanCode(simpleParam.getLoanCode());
		// 借款人类型
		creditReportRisk.setDictCustomerType(simpleParam.getDictCustomerType());
		// 关联ID
		creditReportRisk.setrId(simpleParam.getrCustomerCoborrowerId());
		// 征信报告风险点（jason形式存储）
		creditReportRisk.setCreditJson(creditJson);
		// 征信报告版本（详：1，简：2，企业：3）
		creditReportRisk.setRiskCreditVersion(CreditReportConstants.SIMPLE);
		// 是否有效
		// creditReportRisk.setEffectiveFlag(CreditReportConstants.UNCHOOSE);
		// 信审初审或者复议初审
		creditReportRisk.setDictCheckType(checkType);
		// 如果是信审则更新数据
		if(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode().equals(checkType)){
			// id
			creditReportRisk.setId(riskId);
			// 更新数据
			creditReportRisk.preUpdate();
			logger.debug("个人征信报告简版保存开始=========借款编号"+simpleParam.getLoanCode()+"=====信审OR复议："+checkType);
			creditReportDao.updataById(creditReportRisk);
			logger.debug("个人征信报告简版保存成功=========借款编号"+simpleParam.getLoanCode()+"=====信审OR复议："+checkType);
		}
		// 如果是复议则插入
		if(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode().equals(checkType)
				&& NumberConstants.ZERO_STRING.equals(isFirst)){
			// 更新数据
			creditReportRisk.preInsert();
			logger.debug("个人征信报告简版保存开始=========借款编号"+simpleParam.getLoanCode()+"=====信审OR复议："+checkType);
			creditReportDao.asyncSaveCreditReportRiskInfo(creditReportRisk);
			logger.debug("个人征信报告简版保存成功=========借款编号"+simpleParam.getLoanCode()+"=====信审OR复议："+checkType);
		}
		
		// 如果是复议非第一次预处理则更新
		if(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode().equals(checkType)
				&& NumberConstants.ONE_STRING.equals(isFirst)){
			// id
			creditReportRisk.setId(riskId);
			// 更新数据
			creditReportRisk.preUpdate();
			logger.debug("个人征信报告简版保存开始=========借款编号"+simpleParam.getLoanCode()+"=====信审OR复议："+checkType);
			creditReportDao.updataById(creditReportRisk);
			logger.debug("个人征信报告简版保存成功=========借款编号"+simpleParam.getLoanCode()+"=====信审OR复议："+checkType);
		}
	}
	
	
	/**
	 * 获取简版逾期金额最大值（简版信用卡表和简版贷款表里的数据）
	 * 2016年1月13日
	 * By 李文勇
	 * @param loanCode
	 * @param type
	 * @param rid
	 * @return 返回对比结果
	 */
	private String getSimpleMaxOverdue(CreditReportSimple simpleParam){
		CreditCardInfo result = creditReportSimpleDao.getMaxOVerdue(simpleParam);
		if( result != null ){
			if( result.getOverdueNo() > 0 ){
				return CreditReportConstants.CHOOSE;
			}
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 简版征信报告显示贷款到期，但仍显示正常还款状态(【账户状态为正常】且【到期日期】已在此次判定时间之前，则打勾；否则不打。)
	 * 2016年1月13日
	 * By 李文勇
	 * @param simpleParam
	 * @return 返回对比结果
	 */
	private String getSimpleStatus(CreditReportSimple simpleParam){
		CreditLoanInfoEx creditLoanInfoEx = new CreditLoanInfoEx();
		// 借款编号
		creditLoanInfoEx.setLoanCode(simpleParam.getLoanCode());
		// 借款人类型
		creditLoanInfoEx.setDictCheckType(simpleParam.getDictCustomerType());
		// 关联ID
		creditLoanInfoEx.setrId(simpleParam.getrCustomerCoborrowerId());
		// 账户状态【正常】
		creditLoanInfoEx.setAccountStatus(LoanDtlAccountStatus.NORMAL.getCode());
		CreditLoanInfoEx result = creditReportSimpleDao.getSimpleStatus(creditLoanInfoEx);
		if( result != null){
			if( result.getOverdueNo() > NumberConstants.ZERO_INT ){
				return CreditReportConstants.CHOOSE;
			}
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 查询信息中，近半年，查询原因中出现5次及以上【信用卡审批】或3次及以上【贷款审批】，或信用卡审批次数+贷款审批次数超过5次（含5次）则打勾，否则不打。
	 * 2016年1月13日
	 * By 李文勇
	 * @param simpleParam
	 * @return 返回对比结果
	 */
	private String getSimpleReason( CreditReportSimple simpleParam ){
		CreditLoanInfoEx creditLoanInfoEx = new CreditLoanInfoEx();
		// 借款编号
		creditLoanInfoEx.setLoanCode(simpleParam.getLoanCode());
		// 借款人类型
		creditLoanInfoEx.setDictCheckType(simpleParam.getDictCustomerType());
		// 关联ID
		creditLoanInfoEx.setrId(simpleParam.getrCustomerCoborrowerId());
		// 【信用卡审批】
		creditLoanInfoEx.setAccountStatus(ChkReason.CREDIT_CARD_APPROVAL.getCode());
		int cardResult = creditReportSimpleDao.getSimpleReason(creditLoanInfoEx);
		// 【贷款审批】
		creditLoanInfoEx.setAccountStatus(ChkReason.LOAN_APPROVAL.getCode());
		int loanResult = creditReportSimpleDao.getSimpleReason(creditLoanInfoEx);
		if( cardResult >= 5 || loanResult >= 3 || (cardResult+loanResult) > 5 ){
			return CreditReportConstants.CHOOSE;
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 对比共借人信息
	 * 2016年1月13日
	 * By 李文勇
	 * @param resultList
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @param none
	 */
	private void checkBorrow( List<CreditReportDetailedEx> resultList,String loanCode , String type , String rId , String checkType ,String riskId,String isFirst){
		if(ArrayHelper.isNotEmpty(resultList)){
			for(int i = 0; i < resultList.size(); i++){
				this.checkDate( resultList.get(i) , loanCode , type , rId ,checkType ,riskId,isFirst);
			}
		}else{
			return;
		}
	}
	
	/**
	 * 征信基础数据对比
	 * 2016年1月13日
	 * By 李文勇
	 * @param resultList
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @param checkType
	 * @param none
	 */
	private void checkDate( CreditReportDetailedEx resultList,String loanCode , String type , String rId ,String checkType ,String riskId,String isFirst){
		CreditJson creditJson = new CreditJson();
		creditJson.setInitFlag(NumberConstants.ZERO_STRING);
		List<ReportJason> lis = new ArrayList<ReportJason>();
		
		logger.debug("征信详版风险点1对比开始");
		ReportJason reporB = new ReportJason();
		// 手机号码
		reporB.setCheckBox(CreditReportConstants.DETAILED_BOX_ONE);
		reporB.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getMobilePhone()) ? resultList.getMobilePhone():CreditReportConstants.UNCHOOSE);
		reporB.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporB);
		logger.debug("征信详版风险点1对比结束");
		
		logger.debug("征信详版风险点2对比开始");
		ReportJason reporC = new ReportJason();
		// 工作单位
		reporC.setCheckBox(CreditReportConstants.DETAILED_BOX_TWO);
		reporC.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getUnitName()) ? resultList.getUnitName():CreditReportConstants.UNCHOOSE);
		reporC.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporC);
		logger.debug("征信详版风险点2对比结束");
		
		logger.debug("征信详版风险点3对比开始");
		ReportJason reporD = new ReportJason();
		// 家庭电话 
		reporD.setCheckBox(CreditReportConstants.DETAILED_BOX_THREE);
		reporD.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getHomePhone()) ? resultList.getHomePhone():CreditReportConstants.UNCHOOSE);
		reporD.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporD);
		logger.debug("征信详版风险点3对比结束");
		
		logger.debug("征信详版风险点4对比开始");
		ReportJason reporE = new ReportJason();
		// 单位电话
		reporE.setCheckBox(CreditReportConstants.DETAILED_BOX_FOUR);
		reporE.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getUnitPhone()) ? resultList.getUnitPhone():CreditReportConstants.UNCHOOSE);
		reporE.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporE);
		logger.debug("征信详版风险点4对比结束");
		
		logger.debug("征信详版风险点5对比开始");
		ReportJason reporF = new ReportJason();
		// 单位地址
		reporF.setCheckBox(CreditReportConstants.DETAILED_BOX_FIVE);
		reporF.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getUnitAddress()) ? resultList.getUnitAddress():CreditReportConstants.UNCHOOSE);
		reporF.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporF);
		logger.debug("征信详版风险点5对比结束");
		
		logger.debug("征信详版风险点6对比开始");
		ReportJason reporG = new ReportJason();
		// 居住地址
		reporG.setCheckBox(CreditReportConstants.DETAILED_BOX_SIX);
		reporG.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getLiveAddress()) ? resultList.getLiveAddress():CreditReportConstants.UNCHOOSE);
		reporG.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporG);
		logger.debug("征信详版风险点6对比结束");
		
		logger.debug("征信详版风险点7对比开始");
		ReportJason reporH = new ReportJason();
		// 婚姻状况
		reporH.setCheckBox(CreditReportConstants.DETAILED_BOX_SEVEN);
		reporH.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getMarryStatus()) ? resultList.getMarryStatus():CreditReportConstants.UNCHOOSE);
		reporH.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporH);
		logger.debug("征信详版风险点7对比结束");
		
		logger.debug("征信详版风险点8对比开始");
		ReportJason reporI = new ReportJason();
		// 配偶工作单位
		reporI.setCheckBox(CreditReportConstants.DETAILED_BOX_EIGHT);
		reporI.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getMateCompany()) ? resultList.getMateCompany():CreditReportConstants.UNCHOOSE);
		reporI.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporI);
		logger.debug("征信详版风险点8对比结束");
		
		logger.debug("征信详版风险点9对比开始");
		ReportJason reporJ = new ReportJason();
		// 配偶联系电话
		reporJ.setCheckBox(CreditReportConstants.DETAILED_BOX_NINE);
		reporJ.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getMatePhone()) ? resultList.getMatePhone():CreditReportConstants.UNCHOOSE);
		reporJ.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporJ);
		logger.debug("征信详版风险点9对比结束");
		
		logger.debug("征信详版风险点10对比开始");
		ReportJason reporK = new ReportJason();
		// 配偶证件号码
		reporK.setCheckBox(CreditReportConstants.DETAILED_BOX_TEN);
		reporK.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getMateCertNo()) ? resultList.getMateCertNo():CreditReportConstants.UNCHOOSE);
		reporK.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporK);
		logger.debug("征信详版风险点10对比结束");
		
		logger.debug("征信详版风险点11对比开始");
		ReportJason reporL = new ReportJason();
		// 配偶姓名
		reporL.setCheckBox(CreditReportConstants.DETAILED_BOX_ELEVEN);
		reporL.setSysChooseFlag(StringUtils.isNotEmpty(resultList.getMateName()) ? resultList.getMateName():CreditReportConstants.UNCHOOSE);
		reporL.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporL);
		logger.debug("征信详版风险点11对比结束");
		
		logger.debug("征信详版风险点12对比开始");
		ReportJason reporM = new ReportJason();
		// 时间重叠时，进件时工作单位与征信报告不一致
		String chekCompanyNameTime = this.compareCompanyStartTime( loanCode , type , rId );
		reporM.setCheckBox(CreditReportConstants.DETAILED_BOX_TWELVE);
		reporM.setSysChooseFlag(StringUtils.isNotEmpty(chekCompanyNameTime) ? chekCompanyNameTime:CreditReportConstants.UNCHOOSE);
		reporM.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporM);
		logger.debug("征信详版风险点12对比结束");
		
		logger.debug("征信详版风险点13对比开始");
		ReportJason reporN = new ReportJason();
		// 征信报告显示贷款或信用卡当前逾期，需核查账单资料 
		String maxOverdue = this.getMaxOverdue(loanCode, type, rId);
		reporN.setCheckBox(CreditReportConstants.DETAILED_BOX_THIRTEEN);
		reporN.setSysChooseFlag(StringUtils.isNotEmpty(maxOverdue) ? maxOverdue:CreditReportConstants.UNCHOOSE);
		reporN.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporN);
		logger.debug("征信详版风险点13对比结束");
		
		logger.debug("征信详版风险点14对比开始");
		ReportJason reporO = new ReportJason();
		// 征信报告显示贷款到期，但仍显示正常还款状态
		String resultStatus = this.getNormalStatus( loanCode , type , rId );
		reporO.setCheckBox(CreditReportConstants.DETAILED_BOX_FOURTEEN);
		reporO.setSysChooseFlag(StringUtils.isNotEmpty(resultStatus) ? resultStatus:CreditReportConstants.UNCHOOSE);
		reporO.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporO);
		logger.debug("征信详版风险点14对比结束");
		
		logger.debug("征信详版风险点15对比开始");
		ReportJason reporQ = new ReportJason();
		// 征信报告显示有为他人担保记录(为他人贷款担保明细信息中，有值则打勾，否则不打。)
		String forOther = this.getForOther( loanCode , type , rId );
		reporQ.setCheckBox(CreditReportConstants.DETAILED_BOX_FIFTEEN);
		reporQ.setSysChooseFlag(StringUtils.isNotEmpty(forOther) ? forOther:CreditReportConstants.UNCHOOSE);
		reporQ.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporQ);
		logger.debug("征信详版风险点15对比结束");
		
		logger.debug("征信详版风险点16对比开始");
		ReportJason reporR = new ReportJason();
		// 近期出现较多贷款审批/信用卡审批(查询信息中，近半年，查询原因中出现5次及以上【信用卡审批】或3次及以上【贷款审批】，或信用卡审批次数+贷款审批次数超过5次（含5次）则打勾，否则不打。)
		String reasonResult = this.getDetailedReason( loanCode , type , rId );
		reporR.setCheckBox(CreditReportConstants.DETAILED_BOX_SIXTEEN);
		reporR.setSysChooseFlag(StringUtils.isNotEmpty(reasonResult) ? reasonResult:CreditReportConstants.UNCHOOSE);
		reporR.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporR);
		logger.debug("征信详版风险点16对比结束");
		
		logger.debug("征信详版风险点17对比开始");
		ReportJason reporS = new ReportJason();
		// 公积金信息中【单位名称】与【申请表中单位名称不一致】，则打勾，否则不打。若公积金信息中出现两个单位，取开户日期距今较近的单位名称进行比对。
		String accumulation = this.getAccumulation( loanCode , type , rId );
		reporS.setCheckBox(CreditReportConstants.DETAILED_BOX_SEVENTEEN);
		reporS.setSysChooseFlag(StringUtils.isNotEmpty(accumulation) ? accumulation:CreditReportConstants.UNCHOOSE);
		reporS.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reporS);
		logger.debug("征信详版风险点18对比结束");
		
		creditJson.setReportJason(lis);
		// 
		saveDataRaday(creditJson,loanCode , type , rId , checkType ,riskId,isFirst);
	}
	
	/**
	 * 保存数据预处理方法
	 * 2016年1月12日
	 * By 李文勇
	 * @param creditJson
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @return none
	 */
	private void saveDataRaday(CreditJson creditJson,String loanCode ,String type ,String rId,String checkType,String riskId,String isFirst){
		CreditReportRisk creditReportRisk = new CreditReportRisk();
		// 借款编号
		creditReportRisk.setLoanCode(loanCode);
		// 借款人类型
		creditReportRisk.setDictCustomerType(type);
		// 关联ID
		creditReportRisk.setrId(rId);
		// 征信报告风险点（jason形式存储）
		creditReportRisk.setCreditJson(creditJson);
		// 征信报告版本（详：1，简：2，企业：3）
		creditReportRisk.setRiskCreditVersion(CreditReportConstants.DETAILED);
		// 初审或者复议
		creditReportRisk.setDictCheckType(checkType);
		
		logger.debug("个人征信详版审核保存开始====借款编号："+loanCode+"======决策类型："+checkType);
		if(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode().equals(checkType)){
			// id
			creditReportRisk.setId(riskId);
			creditReportRisk.preUpdate();;
			creditReportDao.updataById(creditReportRisk);
		}
		if(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode().equals(checkType) 
				&& NumberConstants.ZERO_STRING.equals(isFirst)){
			creditReportRisk.preInsert();
			creditReportDao.asyncSaveCreditReportRiskInfo(creditReportRisk);
		}
		if(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode().equals(checkType) 
				&& NumberConstants.ONE_STRING.equals(isFirst)){
			creditReportRisk.setId(riskId);
			creditReportRisk.preUpdate();;
			creditReportDao.updataById(creditReportRisk);
		}
		
		logger.debug("个人征信详版审核保存成功====借款编号："+loanCode+"======决策类型："+checkType);
	}
	
	/**
	 * 判断(时间重叠时，进件时工作单位与征信报告不一致)
	 * 2016年1月12日
	 * By 李文勇
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @return
	 */
	private String compareCompanyStartTime(String loanCode , String type , String rId){
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		// 借款编号
		creditReportDetailed.setLoanCode(loanCode);
		// 借款人类型
		creditReportDetailed.setDictCustomerType(type);
		// 关联ID
		creditReportDetailed.setrCustomerCoborrowerId(rId);
		// 申请单位信息
		CreditReportDetailedEx apply = creditReportDetailedDao.getApplyCompany(creditReportDetailed);
		CreditReportDetailedEx report = creditReportDetailedDao.getReportCompany(creditReportDetailed);
		if( apply != null && report != null && apply.getUnitName() != null 
				&& report.getUnitName() != null && apply.getGetinfoTime() != null
				&& report.getGetinfoTime() != null){
			if( !apply.getUnitName().equals(report.getUnitName()) 
					&& report.getGetinfoTime().getTime() > apply.getGetinfoTime().getTime() ){
				return CreditReportConstants.CHOOSE;
			}else if( report.getGetinfoTime().getTime() <= apply.getGetinfoTime().getTime()
					||apply.getUnitName().equals(report.getUnitName())){
				if( report.getGetinfoTime() == null || "".equals(report.getUnitName()) 
						|| report.getUnitName() == null ){
					return CreditReportConstants.UNCHOOSE;
				}
			}
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 *  获取详版最大逾期金额（信用卡（二）和贷款（二）中）
	 * 2016年1月12日
	 * By 李文勇
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @return
	 */
	private String getMaxOverdue(String loanCode , String type , String rId){
		CreditReportDetailed creditReportDetailed = new CreditReportDetailed();
		// 借款编号
		creditReportDetailed.setLoanCode(loanCode);
		// 借款人类型
		creditReportDetailed.setDictCustomerType(type);
		// 关联ID
		creditReportDetailed.setrCustomerCoborrowerId(rId);
		CreditCardDetailedEx result = creditReportDetailedDao.getMaxOverdue(creditReportDetailed);
		if( result != null ){
			if( result.getCurrentOverdue() > 0 ){
				return CreditReportConstants.CHOOSE;
			}
		}
		return CreditReportConstants.UNCHOOSE;
		
	}
	
	/**
	 * 获取（征信报告显示贷款到期，但仍显示正常还款状态）
	 * 2016年1月12日
	 * By 李文勇
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @return
	 */
	private String getNormalStatus( String loanCode , String type , String rId ){
		CreditLoanDetailedEx creditLoanDetailedEx = new CreditLoanDetailedEx();
		// 借款编号
		creditLoanDetailedEx.setLoanCode(loanCode);
		// 借款人类型
		creditLoanDetailedEx.setDictCustomerType(type);
		// 关联ID
		creditLoanDetailedEx.setrCustomerCoborrowerId(rId);
		// 账户状态为（账户状态为正常）
		creditLoanDetailedEx.setAccountStatu( LoanDtlAccountStatus.NORMAL.getCode() );
		CreditLoanDetailedEx result = creditReportDetailedDao.getNormalStatus(creditLoanDetailedEx);
		if( result != null ){
			return result.getAccountStatu();
		}
		return "0";
	}
	
	/**
	 * 征信报告显示有为他人担保记录(为他人贷款担保明细信息中，有值则打勾，否则不打。)
	 * 2016年1月12日
	 * By 李文勇
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @return
	 */
	private String getForOther( String loanCode , String type , String rId ){
		CreditLoanDetailedEx creditLoanDetailedEx = new CreditLoanDetailedEx();
		// 借款编号
		creditLoanDetailedEx.setLoanCode(loanCode);
		// 借款人类型
		creditLoanDetailedEx.setDictCustomerType(type);
		// 关联ID
		creditLoanDetailedEx.setrCustomerCoborrowerId(rId);
		int result = creditReportDetailedDao.getForOther(creditLoanDetailedEx);
		if( result > 0 ){
			return CreditReportConstants.CHOOSE;
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 公积金信息中【单位名称】与【申请表中单位名称不一致】，则打勾，否则不打。若公积金信息中出现两个单位，取开户日期距今较近的单位名称进行比对。
	 * 2016年1月12日
	 * By 李文勇
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @return
	 */
	private String getAccumulation( String loanCode , String type , String rId ){
		CreditLoanDetailedEx creditLoanDetailedEx = new CreditLoanDetailedEx();
		// 借款编号
		creditLoanDetailedEx.setLoanCode(loanCode);
		// 借款人类型
		creditLoanDetailedEx.setDictCustomerType(type);
		// 关联ID
		creditLoanDetailedEx.setrCustomerCoborrowerId(rId);
		CreditLoanDetailedEx result = creditReportDetailedDao.getAccumulation(creditLoanDetailedEx);
		if( result != null ){
			if(CreditReportConstants.CHOOSE.equals(result.getAccountStatu())){
				return CreditReportConstants.CHOOSE;
			}
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 查询信息中，近半年，查询原因中出现5次及以上【信用卡审批】或3次及以上【贷款审批】，或信用卡审批次数+贷款审批次数超过5次（含5次）则打勾，否则不打。
	 * 2016年1月13日
	 * By 李文勇
	 * @param loanCode
	 * @param type
	 * @param rId
	 * @return
	 */
	private String getDetailedReason( String loanCode , String type , String rId ){
		CreditLoanDetailedEx creditLoanDetailedEx = new CreditLoanDetailedEx();
		// 借款编号
		creditLoanDetailedEx.setLoanCode(loanCode);
		// 借款人类型
		creditLoanDetailedEx.setDictCustomerType(type);
		// 关联ID
		creditLoanDetailedEx.setrCustomerCoborrowerId(rId);
		// 【信用卡审批】
		creditLoanDetailedEx.setLoanType(ChkReason.CREDIT_CARD_APPROVAL.getCode());
		int cardResult = creditReportDetailedDao.getDetailedReason(creditLoanDetailedEx);
		// 【贷款审批】
		creditLoanDetailedEx.setLoanType(ChkReason.LOAN_APPROVAL.getCode());
		int loanResult = creditReportDetailedDao.getDetailedReason(creditLoanDetailedEx);
		if( cardResult >= 5 || loanResult >= 3 || (cardResult+loanResult) > 5 ){
			return CreditReportConstants.CHOOSE;
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
//******************************  企业征信对比开始  *********************************
	/**
	 * 企业征信对比
	 * 2016年2月23日
	 * By 李文勇
	 * @param loanCode
	 */
	private void checkEnterpriseData(EnterpriseCredit enterpriseCredit, String customerId ,String checkType){
		CreditJson creditJson = new CreditJson();
		creditJson.setInitFlag(NumberConstants.ZERO_STRING);
		List<ReportJason> lis = new ArrayList<ReportJason>();
		logger.debug("企业风险点1对比开始");
		// 征信报告显示自主查询版/银行版，报告日期距进件日期超过15天，建议退回重新提供
		ReportJason reportA = new ReportJason();
		reportA.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_ONE); 
		String bigDiff = this.bigDateDiff(enterpriseCredit);
		reportA.setSysChooseFlag(bigDiff);
		reportA.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportA);
		logger.debug("企业风险点1对比结束");
		
		logger.debug("企业风险点2对比开始");
		// 征信报告显示网查版，报告日期距进件日期超过7天，建议退回重新提供
		ReportJason reportB = new ReportJason();
		reportB.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_TWO);
		String smallDiff = this.smallDateDiff(enterpriseCredit);
		reportB.setSysChooseFlag(smallDiff);
		reportB.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportB);
		logger.debug("企业风险点2对比结束");
		
		logger.debug("企业风险点3对比开始");
		// 征信报告显示贷款卡状态异常
		ReportJason reportC = new ReportJason();
		reportC.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_THREE);
		String cardStatus = this.enterpriseCardStatus(enterpriseCredit);
		reportC.setSysChooseFlag(cardStatus);
		reportC.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportC);
		logger.debug("企业风险点3对比结束");
		
		logger.debug("企业风险点4对比开始");
		// 征信报告显示出资方身份证号码与申请表不一致
		ReportJason reportD = new ReportJason();
		reportD.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_FOUR);
		String checkInvestor = this.investorIdCard(enterpriseCredit);
		reportD.setSysChooseFlag(checkInvestor);
		reportD.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportD);
		logger.debug("企业风险点4对比结束");
		
		logger.debug("企业风险点5对比开始");
		// 高管人员信息与申请信息不一致
		ReportJason reportE = new ReportJason();
		reportE.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_FIVE);
		String checkExecutive = this.executiveIdCard(enterpriseCredit);
		reportE.setSysChooseFlag(checkExecutive);
		reportE.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportE);
		logger.debug("企业风险点5对比结束");
		
		logger.debug("企业风险点6对比开始");
		// 征信报告显示空白
		ReportJason reportF = new ReportJason();
		reportF.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_SIX); 
		String general = this.getGeneral(enterpriseCredit);
		reportF.setSysChooseFlag(general);
		reportF.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportF);
		logger.debug("企业风险点6对比结束");
		
		logger.debug("企业风险点7对比开始");
		// 有新增授信
		ReportJason reportG = new ReportJason();
		reportG.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_SEVEN);
		String enterpriseHis = this.getHistory(enterpriseCredit);
		reportG.setSysChooseFlag(enterpriseHis);
		reportG.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportG);
		logger.debug("企业风险点7对比结束");
		
		logger.debug("企业风险点8对比开始");
		// 未结清授信出现当前欠息金额超过200元（不含），建议查看结清证明。
		ReportJason reportH = new ReportJason();
		reportH.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_EIGHT); 
		String debitMoney = this.debitMoney(enterpriseCredit);
		reportH.setSysChooseFlag(debitMoney);
		reportH.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportH);
		logger.debug("企业风险点8对比结束");
		
		logger.debug("企业风险点9对比开始");
		// 未结清授信出现关注，建议查看银行出具的说明
		ReportJason reportI = new ReportJason();
		reportI.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_NINE);
		String debitDetailMoney = this.debitDetailMoney(enterpriseCredit);
		reportI.setSysChooseFlag(debitDetailMoney);
		reportI.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportI);
		logger.debug("企业风险点9对比结束");
		
		logger.debug("企业风险点10对比开始");
		// 已结清授信出现关注、不良（次级、可疑、损失），建议查看有无新增授信
		ReportJason reportJ = new ReportJason();
		reportJ.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_TEN);
		String debitClearMoney = this.debitClearMoney(enterpriseCredit);
		reportJ.setSysChooseFlag(debitClearMoney);
		reportJ.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportJ);
		logger.debug("企业风险点10对比结束");
		
		logger.debug("企业风险点11对比开始");
		// 已结清授信出现垫款、由资产管理公司处置等
		ReportJason reportK = new ReportJason();
		reportK.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_ELEVEN);
		String clearMoney = this.clearMoney(enterpriseCredit);
		reportK.setSysChooseFlag(clearMoney);
		reportK.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportK);
		logger.debug("企业风险点11对比结束");
		
		logger.debug("企业风险点12对比开始");
		// 对外担保信息出现关注
		ReportJason reportL = new ReportJason();
		reportL.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_TWELVE);
		String outAssure = this.outAssure(enterpriseCredit);
		reportL.setSysChooseFlag(outAssure);
		reportL.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportL);
		logger.debug("企业风险点12对比结束");
		
		logger.debug("企业风险点13对比开始");
		// 距进件时间两周内有贷款到期或贷款目前已经到期，建议查看结清证明或续贷合同
		ReportJason reportM = new ReportJason();
		reportM.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_THIRTEEN);
		String unclearTime = this.unclearTime(enterpriseCredit);
		reportM.setSysChooseFlag(unclearTime);
		reportM.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportM);
		logger.debug("企业风险点13对比结束");
		
		logger.debug("企业风险点14对比开始");
		// 声明信息/公共信息明细
		ReportJason reportN = new ReportJason();
		reportN.setCheckBox(CreditReportConstants.ENTERPRISE_BOX_FOURTEEN);
		String declareInfo = this.declareInfo(enterpriseCredit);
		reportN.setSysChooseFlag(declareInfo);
		reportN.setUserChooseFlag(CreditReportConstants.UNCHOOSE);
		lis.add(reportN);
		logger.debug("企业风险点14对比结束");
		
		creditJson.setReportJason(lis);
		saveEnterpriseRaday(creditJson,enterpriseCredit.getLoanCode() , customerId, checkType);
	}
	
	/**
	 * 企业版征信风险点保存
	 * 2016年5月27日
	 * By 李文勇
	 * @param creditJson
	 * @param loanCode
	 * @param customerId
	 * @param checkType
	 */
	private void saveEnterpriseRaday(CreditJson creditJson,String loanCode ,String customerId,String checkType){
		CreditReportRisk creditReportRisk = new CreditReportRisk();
		// 借款编号
		creditReportRisk.setLoanCode(loanCode);
		// 借款人类型
		creditReportRisk.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
		// 关联ID
		creditReportRisk.setrId(customerId);
		// 征信报告风险点（jason形式存储）
		creditReportRisk.setCreditJson(creditJson);
		// 征信报告版本（详：1，简：2，企业：3）
		creditReportRisk.setRiskCreditVersion(CreditReportConstants.ENTERPRISE);
		// 初审或者复议
		creditReportRisk.setDictCheckType(checkType);
		creditReportRisk.preInsert();
		logger.debug("企业征信审核保存开始====借款编号："+loanCode+"======决策类型："+checkType);
		creditReportDao.asyncSaveCreditReportRiskInfo(creditReportRisk);
		logger.debug("企业征信审核保存结束====借款编号："+loanCode+"======决策类型："+checkType);
	}
	
	/**
	 * 征信报告显示自主查询版/银行版，报告日期距进件日期超过15天，建议退回重新提供
	 * 2016年2月23日
	 * By 李文勇
	 * @return
	 */
	private String bigDateDiff(EnterpriseCredit enterpriseCredit){
		LoanInfo loanResult = loanInfoDao.getLoanInfoByLoanCode(enterpriseCredit.getLoanCode());
		if(loanResult != null){
			Date intoTime = loanResult.getCustomerIntoTime();
			Date reportTime = enterpriseCredit.getReportDate();
			if(intoTime != null && reportTime != null){
				long diff = (reportTime.getTime() - intoTime.getTime())/(1000*60*60*24);
				String enterpriseType = enterpriseCredit.getCreditVersion();
				// "1"自主查询版，"2"银行版
				if((CreditReportConstants.ENTERPRISE_VERSION_SEARCHSELF.equals(enterpriseType) 
						|| CreditReportConstants.ENTERPRISE_VERSION_BANK.equals(enterpriseType)) && diff > 15){
					return CreditReportConstants.CHOOSE;
				}
			}
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 征信报告显示网查版，报告日期距进件日期超过7天，建议退回重新提供
	 * 2016年2月23日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return
	 */
	private String smallDateDiff(EnterpriseCredit enterpriseCredit){
		LoanInfo loanResult = loanInfoDao.getLoanInfoByLoanCode(enterpriseCredit.getLoanCode());
		if(loanResult != null){
			Date intoTime = loanResult.getCustomerIntoTime();
			Date reportTime = enterpriseCredit.getReportDate();
			if(intoTime != null && reportTime != null){
				long diff = (reportTime.getTime() - intoTime.getTime())/(1000*60*60*24);
				String enterpriseType = enterpriseCredit.getCreditVersion();
				// "3"代表网查版
				if(CreditReportConstants.ENTERPRISE_VERSION_SEARCHNET.equals(enterpriseType) && diff > 7){
					return CreditReportConstants.CHOOSE;
				}
			}
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 征信报告显示贷款卡状态异常
	 * 2016年2月23日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return
	 */
	private String enterpriseCardStatus(EnterpriseCredit enterpriseCredit){
		String loanCode = enterpriseCredit.getLoanCode();
		CreditBasicInfo basicInfoResult = creditBasicInfoDao.selectByLoanCode(loanCode);
		if(basicInfoResult != null && "2".equals(basicInfoResult.getDictLoanCardState())){
			return CreditReportConstants.CHOOSE;
		}
		return CreditReportConstants.UNCHOOSE;
	}
	
	/**
	 * 征信报告显示出资方身份证号码与申请表不一致
	 * 2016年2月23日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return
	 */
	private String investorIdCard(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.CHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditInvestorInfo> result = creditInvestorInfoDao.selectByLoanCode(loanCode);
		LoanCustomer loanCustomer = new LoanCustomer();
		loanCustomer.setLoanCode(loanCode);
		LoanCustomer cutomer = loanCustomerDao.viewGetByLoanCode(loanCustomer);// 主借人
		LoanCoborrower loanCoborrower = new LoanCoborrower();
		loanCoborrower.setLoanCode(loanCode);
		List<LoanCoborrower> coborrowerList = loanCoborrowerDao.getByLoanCode(loanCoborrower);// 共借人list
		LoanMate loanMate = new LoanMate();
		loanMate.setLoanCode(loanCode);
		List<LoanMate> mateList = loanMateDao.getByLoanCode(loanMate);
		
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size(); i++ ){
				if( result.get(i).getCustomerCertNum() != null 
						&& !"".equals(result.get(i).getCustomerCertNum()) ){
					// 如果出资方身份证号码与主借人相同，则不打勾
					if(result.get(i).getDictCertType().equals(cutomer.getDictCertType())
							&& result.get(i).getCustomerCertNum().equals(cutomer.getCustomerCertNum())){
						flg = CreditReportConstants.UNCHOOSE;
						return flg;
					}
					// 如果出资方身份证号码与共借人相同，则不打勾
					if(ArrayHelper.isNotEmpty(coborrowerList)){
						for( int w = 0; w < coborrowerList.size(); w++){
							if(result.get(i).getDictCertType().equals(coborrowerList.get(w).getDictCertType())
									&& result.get(i).getCustomerCertNum().equals(coborrowerList.get(w).getCoboCertNum())){
								flg = CreditReportConstants.UNCHOOSE;
								return flg;
							}
						}
					}
					// 如果出资方身份证号码与共配偶相同，则不打勾
					if(ArrayHelper.isNotEmpty(mateList)){
						for( int n = 0; n < mateList.size(); n ++){
							if(result.get(i).getDictCertType().equals(mateList.get(n).getDictCertType())
									&& result.get(i).getCustomerCertNum().equals(mateList.get(n).getMateCertNum())){
								flg = CreditReportConstants.UNCHOOSE;
								return flg;
							}
						}
					}
				}else{
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}else{
			flg = CreditReportConstants.UNCHOOSE;
			return flg;
		}
		return flg;
	}
	
	/**
	 * 高管人员信息与申请信息不一致
	 * 2016年2月23日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return
	 */
	private String executiveIdCard(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.CHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditExecutiveInfo> result = creditExecutiveInfoDao.selectByLoanCode(loanCode);
		LoanCustomer loanCustomer = new LoanCustomer();
		loanCustomer.setLoanCode(loanCode);
		LoanCustomer cutomer = loanCustomerDao.viewGetByLoanCode(loanCustomer);// 主借人
		LoanCoborrower loanCoborrower = new LoanCoborrower();
		loanCoborrower.setLoanCode(loanCode);
		List<LoanCoborrower> coborrowerList = loanCoborrowerDao.getByLoanCode(loanCoborrower);// 共借人list
		LoanMate loanMate = new LoanMate();
		loanMate.setLoanCode(loanCode);
		List<LoanMate> mateList = loanMateDao.getByLoanCode(loanMate);
		
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size(); i++ ){
				if(result.get(i).getCustomerCertNum() != null
					&& !"".equals(result.get(i).getCustomerCertNum())){
					// 如果高管人员信息身份证号码与主借人相同，则不打勾
					if(result.get(i).getDictCertType().equals(cutomer.getDictCertType())
							&& result.get(i).getCustomerCertNum().equals(cutomer.getCustomerCertNum())){
						flg = CreditReportConstants.UNCHOOSE;
						return flg;
					}
					// 如果高管人员信息身份证号码与共借人相同，则不打勾
					if(ArrayHelper.isNotEmpty(coborrowerList)){
						for( int w = 0; w < coborrowerList.size(); w++){
							if(result.get(i).getDictCertType().equals(coborrowerList.get(w).getDictCertType())
									&& result.get(i).getCustomerCertNum().equals(coborrowerList.get(w).getCoboCertNum())){
								flg = CreditReportConstants.UNCHOOSE;
								return flg;
							}
						}
					}
					// 如果高管人员信息身份证号码与共配偶相同，则不打勾
					if(ArrayHelper.isNotEmpty(mateList)){
						for( int n = 0; n < mateList.size(); n ++){
							if(result.get(i).getDictCertType().equals(mateList.get(n).getDictCertType())
									&& result.get(i).getCustomerCertNum().equals(mateList.get(n).getMateCertNum())){
								flg = CreditReportConstants.UNCHOOSE;
								return flg;
							}
						}
					}
				}else{
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}else{
			flg = CreditReportConstants.UNCHOOSE;
			return flg;
		}
		return flg;
	}
	
	/**
	 * 征信报告显示空白
	 * 2016年2月23日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return String
	 */
	private String getGeneral(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.CHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditCurrentLiabilityInfo> result1 = creditCurrentLiabilityInfoDao.getByLoanCode(loanCode);
		// 企业征信_当前负债信息概要
		if(ArrayHelper.isNotEmpty(result1)){
			for( int a = 0; a < result1.size(); a++){
				if((result1.get(a).getTransactionCount() != null
						&& result1.get(a).getTransactionCount() > 0) 
						|| result1.get(a).getBalance() != null){
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}
		// 企业征信_当前负债信息明细
		List<CreditCurrentLiabilityDetail> result2 = creditCurrentLiabilityDetailDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result2)){
			for( int b = 0; b < result2.size(); b++ ){
				if(result2.get(b).getConcernTransactionCount() != null 
						&& result2.get(b).getConcernTransactionCount() > 0 
						&& result2.get(b).getConcernBalance() != null){
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}
		// 企业征信_对外担保信息概要
		List<CreditExternalSecurityInfo> result3 = creditExternalSecurityInfoDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result3)){
			for( int c = 0; c < result3.size(); c++ ){
				if(result3.get(c).getTransactionCount() != null
					|| result3.get(c).getConcernBalance() != null
					|| result3.get(c).getBalance() != null
					|| result3.get(c).getNormalBalance() != null
					|| result3.get(c).getBadnessBalance() != null){
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}
		// 企业征信_已结清信贷信息
		List<CreditCreditClearedInfo> result4 = creditCreditClearedInfoDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result4)){
			for( int d = 0; d < result4.size(); d++ ){
				if((result4.get(d).getTransactionCount() != null
					&& result4.get(d).getTransactionCount() > 0)
					|| result4.get(d).getBalance() != null ){
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}
		// 企业征信_已结清信贷明细
		List<CreditCreditClearedDetail> result5 = creditCreditClearedDetailDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result5)){
			for( int e = 0; e < result5.size(); e++ ){
				if( result5.get(e).getLoan() != null // 贷款
					|| result5.get(e).getTradeFinancing() != null // 贸易融资
					|| result5.get(e).getFactoring() != null // 保理
					|| result5.get(e).getNotesDiscounted() != null // 票据贴现
					|| result5.get(e).getBankAcceptance() != null // 银行承兑汇票
					|| result5.get(e).getLetterCredit() != null // 信用证
					|| result5.get(e).getLetterGuarantee() != null ){ // 保函
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}
		// 企业征信_负债历史变化
		List<CreditLiabilityHis> result6 = creditLiabilityHisDao.getByLoanCode(enterpriseCredit.getLoanCode());
		if(ArrayHelper.isNotEmpty(result6)){
			for( int f = 0; f < result6.size(); f++ ){
				if(result6.get(f).getLiabilityHisTime() != null
					|| result6.get(f).getAllBalance() != null
					|| result6.get(f).getBadnessBalance() != null){
					flg = CreditReportConstants.UNCHOOSE;
					return flg;
				}
			}
		}
		return flg;
	}
	
	/**
	 * 有新增授信
	 * 2016年2月23日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return String
	 */
	private String getHistory(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		List<CreditLiabilityHis> result = creditLiabilityHisDao.getByLoanCode(enterpriseCredit.getLoanCode());
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size()-1; i++ ){
				if(result.get(i+1) != null && result.get(i) != null &&
						-1 == result.get(i+1).getBadnessBalance().compareTo(result.get(i).getBadnessBalance())){
					flg = CreditReportConstants.CHOOSE;
					return flg;
				}
			}
		}
		return flg;
	}
	
	/**
	 * 未结清授信出现当前欠息金额超过200元（不含），建议查看结清证明。
	 * 2016年2月23日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return
	 */
	private String debitMoney(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditCurrentLiabilityInfo> result = creditCurrentLiabilityInfoDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size(); i++){
				if("欠息汇总".equals(result.get(i).getInfoSummary()) 
						&&(result.get(i).getTransactionCount() != null
						&& result.get(i).getTransactionCount() > 0)
						&& result.get(i).getBalance() !=null
						&& result.get(i).getBalance().compareTo(new BigDecimal(200)) == 1 ){
					flg = CreditReportConstants.CHOOSE;
					return flg;
				}
			}
		}
		return flg;
	}
	
	/**
	 * 未结清授信出现关注，建议查看银行出具的说明
	 * 2016年2月24日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return
	 */
	private String debitDetailMoney(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditCurrentLiabilityDetail> result = creditCurrentLiabilityDetailDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size(); i++ ){
				if((result.get(i).getConcernTransactionCount() != null
					&& result.get(i).getConcernTransactionCount() > 0) 
						&& result.get(i).getConcernBalance() != null){
					flg = CreditReportConstants.CHOOSE;
					return flg;
				}
			}
		}
		return flg;
	}
	
	/**
	 * 已结清授信出现关注、不良（次级、可疑、损失），建议查看有无新增授信
	 * 2016年2月24日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return String
	 */
	private String debitClearMoney(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditCreditClearedDetail> result = creditCreditClearedDetailDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size(); i++ ){
				if( ("关注类汇总".equals(result.get(i).getInfoSummary())
				 ||"不良类汇总".equals(result.get(i).getInfoSummary()))
				 &&((result.get(i).getLoan() != null
						&& result.get(i).getLoan().compareTo(new BigDecimal(0)) == 1) // 贷款
					|| (result.get(i).getTradeFinancing() != null
						&& result.get(i).getTradeFinancing().compareTo(new BigDecimal(0)) == 1) // 贸易融资
					|| (result.get(i).getFactoring() != null 
						&& result.get(i).getFactoring().compareTo(new BigDecimal(0)) == 1)// 保理
					|| (result.get(i).getNotesDiscounted() != null 
						&& result.get(i).getNotesDiscounted().compareTo(new BigDecimal(0)) == 1)// 票据贴现
					|| (result.get(i).getBankAcceptance() != null
						&& result.get(i).getBankAcceptance().compareTo(new BigDecimal(0)) == 1) // 银行承兑汇票
					|| (result.get(i).getLetterCredit() != null
						&& result.get(i).getLetterCredit().compareTo(new BigDecimal(0)) == 1) // 信用证
					|| (result.get(i).getLetterGuarantee() != null
						&& result.get(i).getLetterGuarantee().compareTo(new BigDecimal(0)) == 1 ) )){ // 保函
					flg = CreditReportConstants.CHOOSE;
					return flg;
				}
			}
		}
		return flg;
	}
	
	/**
	 * 已结清授信出现垫款、由资产管理公司处置等
	 * 2016年2月24日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return String
	 */
	private String clearMoney(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditCreditClearedInfo> result = creditCreditClearedInfoDao.getByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size(); i++ ){
				if( ("垫款汇总".equals(result.get(i).getInfoSummary())
					 || "由资产管理公司处置的债务".equals(result.get(i).getInfoSummary()) )
					 && ( (result.get(i).getTransactionCount() != null 
					 	&& result.get(i).getTransactionCount() > 0) 
					 && ( result.get(i).getBalance() != null
						&& result.get(i).getBalance().compareTo(new BigDecimal(0)) > 0 )
					 && result.get(i).getCompletionDate() != null
					) ){
					flg = CreditReportConstants.CHOOSE;
					return flg;
				}
			}
		}
		return flg;
	}
	
	/**
	 * 对外担保信息出现关注
	 * 2016年2月24日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return String
	 */
	private String outAssure(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		List<CreditExternalSecurityInfo> result = creditExternalSecurityInfoDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(result)){
			for( int i = 0; i < result.size(); i++ ){
				if(result.get(i).getConcernBalance() != null){
					flg = CreditReportConstants.CHOOSE;
					return flg;
				}
			}
		}
		return flg;
	}
	
	/**
	 * 距进件时间两周内有贷款到期或贷款目前已经到期，建议查看结清证明或续贷合同
	 * 2016年2月24日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return String
	 */
	private String unclearTime(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		String loanCode = enterpriseCredit.getLoanCode();
		// 企业征信_未结清非正常贷款
		List<CreditUnclearedImproperLoan> result = creditUnclearedImproperLoanDao.getByLoanCode(loanCode);
		LoanInfo loanResult = loanInfoDao.getLoanInfoByLoanCode(loanCode);
		Date intoTime = loanResult.getCustomerIntoTime();
		if(ArrayHelper.isNotEmpty(result)){
			for(int i = 0; i < result.size(); i++){
				Date actuaTime = result.get(i).getActualDay();
				if(actuaTime != null && intoTime != null){
					long diff = (actuaTime.getTime() - intoTime.getTime())/(1000*60*60*24);
					if(diff > 15){
						flg = CreditReportConstants.CHOOSE;
						return flg;
					}
				}
			}
		}
		// 企业征信_未结清贷款
		List<CreditUnclearedLoan> unclearedLoan = creditUnclearedLoanDao.selectByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(unclearedLoan)){
			for(int i = 0; i < unclearedLoan.size(); i++){
				Date actuaTime = unclearedLoan.get(i).getActualDay();
				if(actuaTime != null && intoTime != null){
					long diff = (actuaTime.getTime() - intoTime.getTime())/(1000*60*60*24);
					if(diff > 15){
						flg = CreditReportConstants.CHOOSE;
						return flg;
					}
				}
			}
		}
		// 企业征信_已结清贷款
		CreditPaidLoan creditPaidLoan = new CreditPaidLoan();
		creditPaidLoan.setLoanCode(loanCode);
		List<CreditPaidLoan> paidLoan = creditPaidLoanDao.selectByPaidLoanInfo(creditPaidLoan);
		if(ArrayHelper.isNotEmpty(paidLoan)){
			for(int i = 0; i < paidLoan.size(); i++){
				Date actuaTime = paidLoan.get(i).getActualDay();
				if(actuaTime != null && intoTime != null){
					long diff = (actuaTime.getTime() - intoTime.getTime())/(1000*60*60*24);
					if(diff > 15){
						flg = CreditReportConstants.CHOOSE;
						return flg;
					}
				}
			}
		}
		return flg;
	}
	
	/**
	 *  声明信息/公共信息明细
	 * 2016年2月24日
	 * By 李文勇
	 * @param enterpriseCredit
	 * @return
	 */
	private String declareInfo(EnterpriseCredit enterpriseCredit){
		String flg = CreditReportConstants.UNCHOOSE;
		// 企业征信_民事判决记录
		CreditCivilJudgmentRecord creditCivilJudgmentRecord = new CreditCivilJudgmentRecord();
		creditCivilJudgmentRecord.setLoanCode(enterpriseCredit.getLoanCode());
		List<CreditCivilJudgmentRecord> judgmentList = creditCivilJudgmentRecordDao.selectByJudgmentInfo(creditCivilJudgmentRecord);
		// 企业征信_贷款卡
		CreditLoanCard  creditLoanCard  = new CreditLoanCard();
		creditLoanCard.setLoanCode(enterpriseCredit.getLoanCode());
		List<CreditLoanCard> loanCardList = creditLoanCardDao.selectByLoanCardInfo(creditLoanCard);
		// 企业征信_贷款卡
		CreditGrade creditGrade  = new CreditGrade();
		creditGrade.setLoanCode(enterpriseCredit.getLoanCode());
		List<CreditGrade> gradeList = creditGradeDao.selectByGradeInfo(creditGrade);
		// 企业征信_评级
		CreditPunish creditPunish = new CreditPunish();
		creditPunish.setLoanCode(enterpriseCredit.getLoanCode());
		List<CreditPunish> punishList = creditPunishDao.selectByPunishInfo(creditPunish);
		if((ArrayHelper.isNotEmpty(punishList))// 企业征信_评级
			|| (ArrayHelper.isNotEmpty(gradeList))// 企业征信_评级
			|| (ArrayHelper.isNotEmpty(loanCardList))// 企业征信_贷款卡
			|| (ArrayHelper.isNotEmpty(judgmentList))){// 企业征信_民事判决记录
			flg = CreditReportConstants.CHOOSE;
			return flg;
		}
		return flg;
	}
	
	//******************************  企业征信对比结束  *********************************
	
	
	
	
}
