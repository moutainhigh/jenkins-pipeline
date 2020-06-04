package com.creditharmony.approve.verify.service;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.constants.RefuseConstants;
import com.creditharmony.approve.common.dao.CityInfoDao;
import com.creditharmony.approve.common.dao.CreditReportDao;
import com.creditharmony.approve.common.dao.RoeffRefeDao;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.common.entity.RoeffRefe;
import com.creditharmony.approve.credit.constants.CreditReportConstants;
import com.creditharmony.approve.document.dao.ZlshJyzmDao;
import com.creditharmony.approve.document.dao.ZlshLoanAccountDao;
import com.creditharmony.approve.document.dao.ZlshYxxjcDao;
import com.creditharmony.approve.document.entity.ZlshJyzm;
import com.creditharmony.approve.internet.dao.PrivateNetworkCheckDao;
import com.creditharmony.approve.management.dao.GlRefuseDao;
import com.creditharmony.approve.management.entity.GlRefuse;
import com.creditharmony.approve.outvisit.dao.OutsideCheckInfoDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.dao.ApproveUserDao;
import com.creditharmony.approve.verify.dao.AuditRatingResultDao;
import com.creditharmony.approve.verify.dao.AuditRecordDao;
import com.creditharmony.approve.verify.dao.AuditResultDao;
import com.creditharmony.approve.verify.dao.AuditResultSublistDao;
import com.creditharmony.approve.verify.dao.JkProductsDao;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;
import com.creditharmony.approve.verify.dao.TelAuditInternetInfoDao;
import com.creditharmony.approve.verify.entity.AuditResult;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.verify.entity.ex.AuditResultSublistEx;
import com.creditharmony.approve.verify.entity.ex.FirstCheckEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.util.DictToMapUtil;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.ChkResult;
import com.creditharmony.core.common.type.CityConstant;
import com.creditharmony.core.common.type.DeleteFlagType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.role.type.ApproveRole;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.type.LoanOrgType;

/**
 * 初审决策页面service
 * @Class Name CheckService
 * @author 刘燕军
 * @Create In 2015年12月2日
 */
@Service
public class CheckService extends  VerifyCommonService{
	
	@Autowired
	private AuditResultDao resutlDao;
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	@Autowired
	private TelAuditInternetInfoDao telAuditInternetInfoDao;
	@Autowired
	private AuditRatingResultDao auditRatingResultDao;
	@Autowired
	private ZlshLoanAccountDao zlshLoanAccountDao;
	@Autowired
	private ZlshYxxjcDao zlshYxxjcDao;	
	@Autowired
	private DhzhDhgxshDao dhzhDhgxshDao;
	@Autowired
	private AuditResultSublistDao auditResultSublistDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private StatusChangeRecordDao changeRecordDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private AuditRecordDao singleTastDao;
	@Autowired
	private DhzhBrhsDao dhzhBrhsDao;
	@Autowired 
	private JkProductsDao productsDao;
	@Autowired
	private GlRefuseDao refuseDao;
	@Autowired
	private CreditReportDao creditReportDao;
	@Autowired
	private ZlshJyzmDao zlshJyzmDao;
	@Autowired
	private ApproveUserDao userDao;
	@Autowired
	private PrivateNetworkCheckDao privateNetworkCheckDao;
	@Autowired
	private RoeffRefeDao roeffRefeDao;
	@Autowired
	private CityInfoDao cityInfoDao;
	@Autowired
	private OutsideCheckInfoDao outsideCheckInfoDao;
	@Autowired
	private LoanCoborrowerDao loanCoborrowerDao;
	
	/**
	 * 初审决策页面初始化controller
	 * 2015年12月2日
	 * By 刘燕军
	 * @param param
	 * @return 返回初审决策页面上需要显示的数据
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ParseException 
	 */
	public FirstCheckEx getCheckInfo(VerifyParamEx param) throws IllegalAccessException, InvocationTargetException, ParseException{
		String loanCode = param.getLoanCode();
		FirstCheckEx  innerCheckView = new FirstCheckEx();
		// 客户咨询是否发生在风险定价上线之后
		innerCheckView.setConsultAfterRateOnline(getConsultFlag(loanCode));
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
		innerCheckView.setInnerRepeats(getExceptionInnerRepeat(param));
		// 查灰信息
		innerCheckView.setGreyListViews(getGreyListEx(loanCode));
		// 查黑信息
		innerCheckView.setBlackListViews(getBlackList(loanCode));
		// 审核历史决策情况
		innerCheckView.setAuditResults(resutlDao.findAuditResults(param));
		// 外部信息核查
		innerCheckView.setOutNet(telAuditWorkDao.findOutNet(param));
		// 推荐信息
		innerCheckView.setAuditRatingResult(auditRatingResultDao.findByParam(loanCode,param.getCheckType()));
		// 月认定收入
		innerCheckView.setMonthIncomeEx(zlshLoanAccountDao.findSalaryMonth(param));
		// 资料审核有效性检查
		//innerCheckView.setZlshYxxjc(zlshYxxjcDao.findZlshYxxjc(param));
		// 电话核查结果
		//List<TelCheckResultEx> phoneCheck = dhzhDhgxshDao.getWork(param);
/*		phoneCheck.addAll(dhzhDhgxshDao.getContact(param));
		phoneCheck.addAll(dhzhDhgxshDao.getSelf(param));*/
		// 获取所有的产品列表
		innerCheckView.setJkProducts(productsDao.getAllProducts(UseFlag.QY.value,LendConstants.PRODUCTS_TYPE_LOAN_CREDIT));
		//innerCheckView.setTelCheckResultExs(phoneCheck);
		// 获取所有的共借人列表  新版设置最优自然人保证人
		String oldornewFlag = loanInfoDao.selectOldornewFlag(loanCode);
		if (oldornewFlag.equals(ApplicationConstants.LOANINFO_NEW_FLAG)) {
		    innerCheckView.setBestCoborrowerIds(loanCoborrowerDao.getCoborrowersByLoanCode(loanCode));
		}
		//主借人年龄
		innerCheckView.setCustomerAge(getCustomerAge(loanCode));		
		// 征信报告信息
		innerCheckView.setCreditRisks(creditReportDao.findCreditRisk(param));
		// 复议原因
		innerCheckView.setReconsiderApply(getReconsiderApply(loanCode));		
		// 获取最新一条审核记录 
		AuditResult auditResult = getAuditResult(param);
		innerCheckView.setAuditResult(auditResult);
		// 所有省列表
		innerCheckView.setProvinceList(cityInfoDao.findByParams(CityConstant.ROOT_ID+""));
		
		// 根据省获取市
		if(auditResult!=null && StringUtils.isNotEmpty(auditResult.getEnsuremanBusinessProvince())){
			List<CityInfo> cityList = cityInfoDao.findByParams(auditResult.getEnsuremanBusinessProvince());
			innerCheckView.setCityList(cityList);
		}
		// 根据市获取区
		if(auditResult!=null && StringUtils.isNotEmpty(auditResult.getEnsuremanBusinessCity())){
			List<CityInfo> districtList = cityInfoDao.findByParams(auditResult.getEnsuremanBusinessCity());
			innerCheckView.setDistrictList(districtList);
		}
		// 核查结果对应字典
		innerCheckView.setCheckResults(DictToMapUtil.getMap(LendConstants.EVAL_RESULT));
		innerCheckView.setCredirVersion(DictToMapUtil.getMap(DictionaryConstants.CREDIT_VERSION));
		innerCheckView.setLoanMan(DictToMapUtil.getMap(DictionaryConstants.LOAN_MAN));
		//  根据选择的通过或拒借  查询通过或拒借的最新一条记录
		// 如果是拟拒绝， 则查出列表
		if(!ObjectHelper.isEmpty(auditResult)){
			if(StringUtils.isNotBlank(auditResult.getAuditResult()) && 
					auditResult.getAuditResult().equals(ChkResult.TO_REFUSE_TO_BORROW.getCode())){
				List<AuditResultSublistEx> sublists = auditResultSublistDao.getByRid(auditResult.getId());
				// 如果有相应的拒借码，则查出相应的列表并赋值
				if(ArrayHelper.isNotEmpty(sublists)){
					for(AuditResultSublistEx sublist : sublists){
						List<GlRefuse> secondList = refuseDao.getNewDataByParentCode(sublist.getRefuseFirstCode());
						List<GlRefuse> thirdList = refuseDao.getNewDataByParentCode(sublist.getRefuseSecondCode());
						sublist.setSecondList(secondList);
						sublist.setThirdList(thirdList);
					}
				}
				innerCheckView.setResultSublists(sublists);
			}
		}
		// 征信报告相关信息
		List<CreditReportRisk> credits = innerCheckView.getCreditRisks();
		int enterprise = 0;
		int person1 = 0;
		int person2 = 0;
		for (CreditReportRisk creditRisk : credits) { 
			// 判断个人版的征信报告是详版还是简版
			if(person1 ==0 && CreditReportConstants.DETAILED.equals(creditRisk.getRiskCreditVersion())){
				innerCheckView.setPersonal(DictToMapUtil.getMap(DictionaryConstants.PERSONAL+creditRisk.getRiskCreditVersion()));
			}
			if(person2 == 0 && CreditReportConstants.SIMPLE.equals(creditRisk.getRiskCreditVersion())){
				innerCheckView.setPersonalSimple(DictToMapUtil.getMap(DictionaryConstants.PERSONAL+creditRisk.getRiskCreditVersion()));
			}
			// 取出企业版征信报告对应的map
			if(enterprise == 0 && CreditReportConstants.ENTERPRISE.equals(creditRisk.getRiskCreditVersion())){
				innerCheckView.setEnterprise(DictToMapUtil.getMap(DictionaryConstants.ENTERPRISE));
				enterprise ++;
			}
		}
		// 终审审批人
		if(param.getCheckType().equals(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode())){
			innerCheckView.setUsers(userDao.getFinalChecks(ApproveRole.APPROVE_VERIFY_FINAL_CHECK.id));
		}else if(param.getCheckType().equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())){
			innerCheckView.setUsers(userDao.getFinalChecks(ApproveRole.APPROVE_RECONSIDER_FINAL_CHECK.id));
		}
		//  获取风险标识
		innerCheckView.setRiskFlag(dhzhBrhsDao.getRiskFlag(param));
		return innerCheckView;
	}
	
	/**
	 * 获取所有的拒绝码
	 * 2015年12月24日
	 * @param 无
	 * By 刘燕军
	 * @return 拒绝码列表
	 */
	public List<GlRefuse> findAllRefuse(){
		return refuseDao.findAllGlRefuses();
	} 
	
	/**
	 * 获取所有的拒绝码
	 * 2016年1月18日
	 * @param 无
	 * By 刘燕军
	 * @return 拒绝码列表
	 */
	public List<GlRefuse> findSecondRefuse(){
		GlRefuse glRefuse = new GlRefuse();
		glRefuse.setParentIds(RefuseConstants.ROOT);
		glRefuse.setRefuseGrade(RefuseConstants.TYPE_APPROVE);
		return refuseDao.getDataByParentId(glRefuse);
	}
	
	/**
	 * 获取追加保证人的公司信息
	 * 2016年1月5日
	 * By 刘燕军
	 * @param param
	 * @return 所有的保证人
	 */
	public List<ZlshJyzm> getCautioner(VerifyParamEx param){
		return zlshJyzmDao.getCautioner(param);
	}
	
	/**
	 * 获取追加保证人的公司信息
	 * 2016年1月5日
	 * By 刘燕军
	 * @param param
	 * @return 所有的保证人
	 */
	public List<ZlshJyzm> getCautionerNew(VerifyParamEx param){
		return zlshJyzmDao.getCautionerNew(param);
	}
	/**
	 * 获取追加保证人的公司信息
	 * 2016年1月5日
	 * By 刘燕军
	 * @param id
	 * @return 对应的保证人信息
	 */
	public ZlshJyzm getCautionerInfo(String id){
		ZlshJyzm cautioner = zlshJyzmDao.getEntityWithVisit(id);
		// 把省市区转换为对应的汉字
//		String provice = ProvinceCityCache.getInstance().getProvinceCity(cautioner.getJyzmRegisteredProvince(), 
//				cautioner.getJyzmRegisteredCity(), cautioner.getJyzmRegisteredArea());
		cautioner.setJyzmRegisteredAddress(cautioner.getJyzmRegisteredAddress());
		return cautioner;
	}
	
	/**
	 * 通过审核结果获取拒借信息
	 * 2016年1月12日
	 * By 赖敏
	 * @param resultId 审批结果ID
	 * @return 拒借信息
	 */
	public AuditResultEx getRefuseInfo(String resultId){
		return resutlDao.getRefuseInfo(resultId);
	}
	
	/**
	 * 根据借款编号查询借款信息
	 * 2016年3月2日
	 * By 李文勇
	 * @param loanCode
	 * @return 结果对象
	 */
	public LoanInfo getkinInfo(String loanCode){
		LoanInfo result = loanInfoDao.getByLoanCode(loanCode);
		return result;
	}
	
	/**
	 * 通过机构获取门店名称
	 * 2016年3月18日
	 * By 刘燕军
	 * @param org
	 * @return 门店集合
	 */
	public List<Org> findStoresPage(Org org){
		org.setDelFlag(DeleteFlagType.NORMAL);
		org.setType(LoanOrgType.STORE.key);
		List<Org> pageList = dao.findOrgsByParams(org);
        return pageList;
	}
	
	/**
	 * 获取所有的产品分期
	 * 2016年4月22日
	 * By 刘燕军
	 * @return
	 */
	public List<Integer> getAllMonths(String consultFlag){
		RoeffRefe refe = new RoeffRefe();
		refe.setConsultFlag(consultFlag);		
		return roeffRefeDao.getAllMonths(refe);
	}
	
	/**
	 * 获取分期对应的费率 
	 * 2016年4月22日
	 * By 刘燕军
	 * @return 费率对应的list
	 */
	public List<Double> getOneTypeRate(int months){
		return roeffRefeDao.getOneTypeRate(months);
	}
	
	/**
	 * 通过产品编码获取对应的产品利率
	 * 2016年5月10日
	 * By 刘燕军
	 * @param productCode
	 * @return 利率
	 */
	public String getRateByCode(String productCode){
		return productsDao.findRate(productCode);
	}
	
	/**
	 * 获取外访的经营地址
	 * 2016年6月22日
	 * xiaoniu.hu
	 * @param param
	 * @return
	 */
	public AuditResult getVisitAddress(VerifyParamEx param){
		return outsideCheckInfoDao.getVisitAddress(param.getLoanCode());
	}
	
	/**
	 * 根据分期和风险等级，获取新规则下对应的费率
	 * 2016年7月6日
	 * By 王浩
	 * @param months
	 * @param riskLevel
	 * @return 
	 */
	public List<Double> getRateByRisk(int months, String riskLevel){
		RoeffRefe refe = new RoeffRefe();
		refe.setMonths(months);
		refe.setRiskLevel(riskLevel);
		return roeffRefeDao.getRateByRisk(refe);
	}
	
	/**
	 * 根据借款编号，判断进件时间是风险定价上线后，true或者false
	 * 2016年7月6日
	 * By 王浩
	 * @param loanCode
	 * @return 
	 */
	public String getConsultFlag(String loanCode){
		return loanCustomerDao.getConsultTimeFlag(loanCode, SystemConfigConstant.LOAN_ONLINE_DATE);
	}
	
}
