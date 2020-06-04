package com.creditharmony.approve.phone.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.constants.TableType;
import com.creditharmony.approve.common.dao.CreditReportDao;
import com.creditharmony.approve.common.entity.CityInfo;
import com.creditharmony.approve.common.service.CityInfoService;
import com.creditharmony.approve.common.service.VefiryCheckService;
import com.creditharmony.approve.common.util.CallPhoneUtil;
import com.creditharmony.approve.document.dao.ZlshGxhtDao;
import com.creditharmony.approve.internet.dao.NetWorkConfigDao;
import com.creditharmony.approve.internet.dao.PrivateNetworkCheckDao;
import com.creditharmony.approve.internet.entity.PrivateNetworkCheck;
import com.creditharmony.approve.phone.dao.DhzhBrhsDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsDhxxDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsJkjeDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxbrshdDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDhxxDao;
import com.creditharmony.approve.phone.dao.DhzhDhlyxxDao;
import com.creditharmony.approve.phone.dao.DhzhGxhtDhxxDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.phone.dao.WorkTelNumDao;
import com.creditharmony.approve.phone.entity.DhzhBrhsJkje;
import com.creditharmony.approve.phone.entity.DhzhDhgxbrshd;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.WorkTelNum;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerInfoEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckBorrowerNumEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactNumEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.approve.phone.entity.ex.WorkTelNumEx;
import com.creditharmony.approve.phone.view.TelCheckView;
import com.creditharmony.approve.rule.applyengine.client.ApplyengineRulesDecisionService;
import com.creditharmony.approve.rule.dao.LoanApplyInfoDao;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.dao.LoanCompanyDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.CompManage;
import com.creditharmony.approve.verify.entity.ex.DictList;
import com.creditharmony.approve.verify.entity.ex.IndustryTypeEx;
import com.creditharmony.approve.verify.entity.ex.IndustryTypeOneEx;
import com.creditharmony.approve.verify.entity.ex.IndustryTypeThreeEx;
import com.creditharmony.approve.verify.entity.ex.IndustryTypeTwoEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.TelRecSrc;
import com.creditharmony.core.approve.type.TelSrc;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.loan.type.RelationType;
import com.creditharmony.core.loan.type.YESNO;
import com.google.common.collect.Maps;


/**
 * 电话照会Service
 * @Class Name TelCheckService
 * @author 王浩
 * @Create In 2015年12月1日
 */
@Service
public class TelCheckService extends VefiryCheckService{
	
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	@Autowired
	private DhzhDhlyxxDao callRecordDao;
	@Autowired
	private DhzhGxhtDhxxDao busiContractDao;
	@Autowired
	private LoanCompanyDao loanCompanyDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private DhzhBrhsDao borrowerInfoDao;
	@Autowired
	private DhzhBrhsJkjeDao otherLoanDao;
	@Autowired
	private DhzhBrhsDhxxDao borrowerNumDao;	
	@Autowired
	private DhzhDhgxshDao contactPersonDao;
	@Autowired
	private DhzhDhgxshDhxxDao contactNumDao;	
	@Autowired
	private CreditReportDao creditReportDao;
	@Autowired
	private WorkTelNumDao workTelNumDao;
	@Autowired
	private LoanApplyInfoDao applyInfoDao;
	@Autowired
	private DhzhDhgxbrshdDao brshdDao;
	@Autowired
	ApplyengineRulesDecisionService ruleService;	
	@Autowired
	CityInfoService cityInfoService;	
	@Autowired
	private ZlshGxhtDao zlshGxhtDao;
	@Autowired
	private NetWorkConfigDao netWorkConfigDao;
	@Autowired
	private PrivateNetworkCheckDao privateNetworkCheckDao;
	
		
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private static DictList dictList;
	
	private static Map<String, Object> dictValues = Maps.newHashMap();
	
	// 新版申请表用
	private static DictList dictListNew;
	
	private static Map<String, Object> dictValuesNew = Maps.newHashMap();
	
	/**
	 * 返回数据字典的集成类
	 * 2016年1月11日
	 * By 王浩
	 * @return 数据字典list集中封装到DictList对象中
	 */
	private DictList getDictList() {
		if (dictList == null) {
			dictList = new DictList();
			// 接听状态
			dictList.setAnswerState(DictUtils.getDictList(LendConstants.APPROVE_PHONE_STATUE));
			// 电话录音关联表
			dictList.setTelRecordSrc(DictUtils.getDictList(DictionaryConstants.TEL_REC_SRC));
			// 关系类型，家庭联系人/其他联系人/工作证明人
			dictList.setRelationType(DictUtils.getDictList(DictionaryConstants.RELATION_TYPE));
			/*// 风险点
			dictList.setPersonalRisk(DictUtils.getDictList("jk_personal_risk_01"));*/
			// 评估结果：正常、异常、无效
			dictList.setEvalResult(DictUtils.getDictList(LendConstants.EVAL_RESULT));
			// 电话号码来源
			List<Dict> listTel=DictUtils.getDictList(DictionaryConstants.TEL_SOURCE);
			
			dictList.setTelSrc(listTel);
			// 单位名称来源
			dictList.setUnitNameSrc(DictUtils.getDictList(DictionaryConstants.WORK_UNIT_NAME_SRC));
			// 工作证明人与借款人关系
			dictList.setWorkRelation(DictUtils.getDictList(DictionaryConstants.LOAN_WORKMATE_RELATION));
			// 家庭联系人与借款人关系
			dictList.setFamilyRelation(DictUtils.getDictList(LendConstants.LOAN_FAMILY_RELATION));		
			// 其他联系人与借款人关系
			dictList.setOtherRelation(DictUtils.getDictList(DictionaryConstants.LOAN_OTHER_RELATION_TYPE));	
			/*// 行业分类
			dictList.setIndustryType(DictUtils.getDictList("jk_industry_type"));	
			// 职业分类
			dictList.setProfessionType(DictUtils.getDictList("tz_prof_son"));*/
			// 电话类型
			dictList.setPhoneType(DictUtils.getDictList(DictionaryConstants.PHONE_TYPE));
		}
		return dictList;
	}
	
	/**
	 * 新版申请表add
	 * 返回数据字典的集成类
	 * 2016年1月11日
	 * By 王浩
	 * @return 数据字典list集中封装到DictList对象中
	 */
	private DictList getDictListNew() {
		if (dictListNew == null) {
			dictListNew = new DictList();
			// 接听状态
			dictListNew.setAnswerState(DictUtils.getDictList(LendConstants.APPROVE_PHONE_STATUE));
			// 电话录音关联表
			dictListNew.setTelRecordSrc(DictUtils.getDictList(DictionaryConstants.TEL_REC_SRC));
			// 关系类型，家庭联系人/其他联系人/工作证明人
			dictListNew.setRelationType(DictUtils.getDictList(DictionaryConstants.RELATION_TYPE));
			/*// 风险点
			dictList.setPersonalRisk(DictUtils.getDictList("jk_personal_risk_01"));*/
			// 评估结果：正常、异常、无效
			dictListNew.setEvalResult(DictUtils.getDictList(LendConstants.EVAL_RESULT));
			// 电话号码来源
			List<Dict> listTel=DictUtils.getDictList(DictionaryConstants.TEL_SOURCE);
			
			dictListNew.setTelSrc(listTel);
			// 单位名称来源
			dictListNew.setUnitNameSrc(DictUtils.getDictList(DictionaryConstants.WORK_UNIT_NAME_SRC));
			// 工作证明人与借款人关系
			dictListNew.setWorkRelation(DictUtils.getNewDictList(DictionaryConstants.LOAN_WORKMATE_RELATION));
			// 家庭联系人与借款人关系
			dictListNew.setFamilyRelation(DictUtils.getNewDictList(LendConstants.LOAN_FAMILY_RELATION));		
			// 其他联系人与借款人关系
			dictListNew.setOtherRelation(DictUtils.getNewDictList(DictionaryConstants.LOAN_OTHER_RELATION_TYPE));	
			/*// 行业分类
			dictList.setIndustryType(DictUtils.getDictList("jk_industry_type"));	
			// 职业分类
			dictList.setProfessionType(DictUtils.getDictList("tz_prof_son"));*/
			// 电话类型
			dictListNew.setPhoneType(DictUtils.getDictList(DictionaryConstants.PHONE_TYPE));
			
		}
		return dictListNew;
	}
	
	/**
	 * 将数据字典保存到map中，到页面上取用
	 * 2016年1月11日
	 * By 王浩
	 * @return 返回存放数据字典的map
	 */
	private Map<String, Object> getDictValues() {
		if (dictValues.isEmpty()) {
			for(Dict dict : dictList.getTelRecordSrc()){
				dictValues.put(dict.getLabel(), dict.getValue());
			}				
			for(Dict dict : dictList.getRelationType()){
				dictValues.put(dict.getLabel(), dict.getValue());
			}
			for(Dict dict : dictList.getAnswerState()){
				dictValues.put("approve_phone_statue_" + dict.getValue(), dict.getLabel());
			}
			/*for(Dict dict : dictList.getPersonalRisk()){
				dictValues.put("personal_risk_01_" + dict.getValue(), dict.getLabel());
			}*/
			for(Dict dict : dictList.getEvalResult()){
				dictValues.put("eval_result_" + dict.getValue(), dict.getLabel());
			}	
			for(Dict dict : dictList.getUnitNameSrc()){
				dictValues.put("name_src_" + dict.getValue(), dict.getLabel());
			}			
			for(Dict dict : dictList.getTelSrc()){
				dictValues.put("tel_src_" + dict.getValue(), dict.getLabel());
			}								
			for(Dict dict : dictList.getWorkRelation()){
				dictValues.put("work_relation_" + dict.getValue(), dict.getLabel());
			}
			for(Dict dict : dictList.getFamilyRelation()){
				dictValues.put("family_relation_" + dict.getValue(), dict.getLabel());
			}
			for(Dict dict : dictList.getOtherRelation()){
				dictValues.put("other_relation_" + dict.getValue(), dict.getLabel());
			}
		}
		return dictValues;
	}	
	
	/**
	 * 新版申请表add
	 * 将数据字典保存到map中，到页面上取用
	 * 2016年1月11日
	 * By 王浩
	 * @return 返回存放数据字典的map
	 */
	private Map<String, Object> getDictValuesNew() {
		if (dictValuesNew.isEmpty()) {
			for(Dict dict : dictListNew.getTelRecordSrc()){
				dictValuesNew.put(dict.getLabel(), dict.getValue());
			}				
			for(Dict dict : dictListNew.getRelationType()){
				dictValuesNew.put(dict.getLabel(), dict.getValue());
			}
			for(Dict dict : dictListNew.getAnswerState()){
				dictValuesNew.put("approve_phone_statue_" + dict.getValue(), dict.getLabel());
			}
			/*for(Dict dict : dictList.getPersonalRisk()){
				dictValues.put("personal_risk_01_" + dict.getValue(), dict.getLabel());
			}*/
			for(Dict dict : dictListNew.getEvalResult()){
				dictValuesNew.put("eval_result_" + dict.getValue(), dict.getLabel());
			}	
			for(Dict dict : dictListNew.getUnitNameSrc()){
				dictValuesNew.put("name_src_" + dict.getValue(), dict.getLabel());
			}			
			for(Dict dict : dictListNew.getTelSrc()){
				dictValuesNew.put("tel_src_" + dict.getValue(), dict.getLabel());
			}								
			for(Dict dict : dictListNew.getWorkRelation()){
				dictValuesNew.put("work_relation_" + dict.getValue(), dict.getLabel());
			}
			for(Dict dict : dictListNew.getFamilyRelation()){
				dictValuesNew.put("family_relation_" + dict.getValue(), dict.getLabel());
			}
			for(Dict dict : dictListNew.getOtherRelation()){
				dictValuesNew.put("other_relation_" + dict.getValue(), dict.getLabel());
			}
		}
		return dictValuesNew;
	}
	
	/**
	 * 查询单位信息
	 * 2015年12月2日
	 * By 王浩
	 * @param params 参数集
	 * @return 返回单位信息的List
	 */
	public List<TelCheckCompanyEx> getCompanyInfoList(Map<String, Object> params) {
		// 查找电话照会单位信息
		List<TelCheckCompanyEx> unitInfoList = telAuditWorkDao
				.getListByParams(params);
		if (!ArrayHelper.isNotEmpty(unitInfoList)) {
			log.info("查找汇诚电话照会保存的单位信息为空");
		} else {
			// 查找每条单位信息对应的电话号码及电话录音信息
			for (TelCheckCompanyEx unitInfo : unitInfoList) {
				params.put("rUnitInfoId", unitInfo.getId());
				params.put("dictVoiceSource",TelRecSrc.PHONE_INSPECTION_UNIT.getCode());
				List<WorkTelNumEx> telNumList = telAuditWorkDao.getTelNumListByRid(params);
				unitInfo.setTelNumList(telNumList);
			}
		}
		return unitInfoList;
	}		
	
	/**
	 * 获取工作证明人或家庭联系人或其他联系人信息
	 * 2015年12月4日
	 * By 王浩
	 * @param params 参数集
	 * @param relationType 关系类型：工作证明人/家庭联系人/其他联系人
	 * @return 返回联系人信息的List
	 */
	private List<TelCheckContactPersonEx> getContactPersonList(
			Map<String, Object> params, String relationType) {
		params.put("relationType", relationType);
		params.put("dictVoiceSource", TelRecSrc.PHONE_RELATIONSHIP_INSPECTION.getCode());
		List<TelCheckContactPersonEx> contactList = contactPersonDao.getListByCodeAndType(params);
		
		// 查询关系证明人的宅电、电话信息
		this.getContactPersonphoneNumListNew(contactList, params);
		
		
		return contactList;
	}
	
	/**
	 * 新版申请表add  数据交换用
	 * 获取工作证明人或家庭联系人或其他联系人信息
	 * 2015年12月4日
	 * By 王浩
	 * @param params 参数集
	 * @param relationType 关系类型：工作证明人/家庭联系人/其他联系人
	 * @return 返回联系人信息的List
	 */
	public List<TelCheckContactPersonEx> getContactPersonListNew(
			Map<String, Object> params) {
		
		List<TelCheckContactPersonEx> contactList = contactPersonDao.getListByCodeAndType(params);
		
		// 查询电话信息
		this.getContactPersonphoneNumListNew(contactList, params);
		
		return contactList;
	}
	
	/**
	 * 新版申请表add  数据交换用
	 * 获取工作证明人或家庭联系人或其他联系人信息
	 * 2015年12月4日
	 * By 王浩
	 * @param params 参数集
	 * @param relationType 关系类型：工作证明人/家庭联系人/其他联系人
	 * @return 返回联系人信息的List
	 */
	private List<TelCheckContactPersonEx> getContactPersonphoneNumListNew(
			List<TelCheckContactPersonEx> contactList,
			Map<String, Object> params) {
		
		// 查询关系证明人的宅电、电话信息
		if (ArrayHelper.isNotEmpty(contactList)) {
			for(TelCheckContactPersonEx contactInfo : contactList){
				// 查找关系人的电话信息-家庭固话
				params.put("rContactInfoId", contactInfo.getId());
				// 类型为家庭固话
				params.put("type", "0");
				List<TelCheckContactNumEx> contactNumList = contactNumDao
						.getDhgxshDhxxListByRid(params);			
				contactInfo.setContactNumList(contactNumList);
				List<DhzhDhlyxx> lyxxList = new ArrayList<DhzhDhlyxx>();
				if(contactNumList != null){
					for(TelCheckContactNumEx contact : contactNumList){
						if(contact.getDhlyxx() != null){
							lyxxList.addAll(contact.getDhlyxx());
						}
					}
				}
				
				// 查找关系证明人的电话信息-手机号
				params.put("rContactInfoId", contactInfo.getId());
				// 类型为手机号
				params.put("type", "1");
				List<TelCheckContactNumEx> phoneNumList = contactNumDao
						.getDhgxshDhxxListByRid(params);			
				contactInfo.setPhoneNumList(phoneNumList);	
				if(phoneNumList != null){
					for(TelCheckContactNumEx contact : phoneNumList){
						if(contact.getDhlyxx() != null){
							lyxxList.addAll(contact.getDhlyxx());
						}
					}
				}
				// 判断有无录音信息，避免旧版为空。
				if (ArrayHelper.isNotEmpty(lyxxList)) {
					contactInfo.setDhlyxx(lyxxList);
				}
				
				
			}
		}
		
		return contactList;
		
	}
	
	/**
	 * 获取页面初始化时所需的信息并返回
	 * 2015年12月1日
	 * By 王浩
	 * @param verifyParam 参数集
	 * @return 返回包含页面所有信息的view
	 */
	public TelCheckView getTelCheckView(VerifyParamEx verifyParam, String newOrOldFlag) {	
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("loanCode", verifyParam.getLoanCode().trim());// 借款编码
		filter.put("dictCustomerType", verifyParam.getType().trim());// 主借人、共借人
		filter.put("dictCheckType", verifyParam.getCheckType().trim());// 信审初审、复议初审
		filter.put("rCustomerCoborrowerId", verifyParam.getRelId().trim());// 主借人或者共借人的主键
		
		TelCheckView telCheckView = new TelCheckView();
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			telCheckView.setDictList(getDictList());
			telCheckView.setDictValues(getDictValues());
		} else {
		// 判断为新版
			telCheckView.setDictList(getDictListNew());
			telCheckView.setDictValues(getDictValuesNew());				
		}
				
		// 查找工作单位信息
		List<TelCheckCompanyEx> companyInfoList = this.getCompanyInfoList(filter);
		for(TelCheckCompanyEx company:companyInfoList){
			if(StringUtils.isNotEmpty(company.getWorkProvince())){
				List<CityInfo> cityList = cityInfoService.findCity(company.getWorkProvince());
				company.setCityList(cityList);
			}
			if(StringUtils.isNotEmpty(company.getWorkCity())){
				List<CityInfo> districtList = cityInfoService.findDistrict(company.getWorkCity());
				company.setDistrictList(districtList);
			}
		}
		telCheckView.setCompanyList(companyInfoList);
		
		// 查询关系审核信息:工作证明人
		List<TelCheckContactPersonEx> contactWorkList = this.getContactPersonList(filter,
						RelationType.WORK_VOUCHER.getCode());
		// 查询单位地址信息
		for(TelCheckContactPersonEx telCheckContactPersonEx:contactWorkList){
			if(StringUtils.isNotEmpty(telCheckContactPersonEx.getDictUnitProvince())){
				List<CityInfo> cityList = cityInfoService.findCity(telCheckContactPersonEx.getDictUnitProvince());
				telCheckContactPersonEx.setCityList(cityList);
			}
			if(StringUtils.isNotEmpty(telCheckContactPersonEx.getDictUnitCity())){
				List<CityInfo> districtList = cityInfoService.findDistrict(telCheckContactPersonEx.getDictUnitCity());
				telCheckContactPersonEx.setDistrictList(districtList);
			}
		}
		telCheckView.setContactWorkList(contactWorkList);
		
		// 查询关系审核信息:家庭联系人
		List<TelCheckContactPersonEx> contactFamilyList = this.getContactPersonList(filter,
						RelationType.FAMILY_CONTACTS.getCode());
		telCheckView.setContactFamilyList(contactFamilyList);
		
		// 查询关系审核信息:其他联系人
		List<TelCheckContactPersonEx> contactOtherList = this.getContactPersonList(filter,
						RelationType.OTHER_CONTACTS.getCode());
		telCheckView.setContactOtherList(contactOtherList);
		
		// 查询本人核实信息
		List<TelCheckBorrowerInfoEx> borrowerInfo = this.getBorrowerInfo(filter, BooleanType.FALSE);
		if(ArrayHelper.isNotEmpty(borrowerInfo)){
			// 设置大纲上下限
			TelCheckBorrowerInfoEx telCheckBorrowerInfoEx =  borrowerInfoDao.selectProductLimit(filter);
			borrowerInfo.get(0).setLimitLower(telCheckBorrowerInfoEx.getLimitLower());
			borrowerInfo.get(0).setLimitUpper(telCheckBorrowerInfoEx.getLimitUpper());
			
			telCheckView.setBorrowerInfo(borrowerInfo.get(0));
		}
		return telCheckView;
	}	
	
	/**
	 * 获取本人核实信息
	 * 2015年12月3日
	 * By 王浩
	 * @param params 参数集
	 * @param isReconsiderPreTreat 是否复议预处理调用该方法
	 * @return 返回本人核实的对象
	 */
	public List<TelCheckBorrowerInfoEx> getBorrowerInfo(Map<String, Object> params, String isReconsiderPreTreat) {
		// 查找电话照会本人核实信息表
		List<TelCheckBorrowerInfoEx> borrowerInfoList = null;
		// 如果是复议预处理调用，取出同借款编号相同的所有信审类型的本人核实数据
		if(isReconsiderPreTreat.equals(BooleanType.TRUE)){
			borrowerInfoList = borrowerInfoDao.getAllBorrowerInfo(params);
		} else {
			borrowerInfoList = borrowerInfoDao.getBorrowerInfo(params);
		}
		
		if (ArrayHelper.isNotEmpty(borrowerInfoList)) {
			for(TelCheckBorrowerInfoEx borrowerInfo : borrowerInfoList){
				// 查找本人核实的电话信息-家庭固话
				params.put("rBorrowerInfoId", borrowerInfo.getId());
				// 类型为家庭固话
				params.put("type", "0");
				List<TelCheckBorrowerNumEx> borrowerNumList = borrowerNumDao
						.getBrhsDhxxListByRid(params);			
				borrowerInfo.setBorrowerNumList(borrowerNumList);
				List<DhzhDhlyxx> lyxxList = new ArrayList<DhzhDhlyxx>();
				if(borrowerNumList != null){
					for(TelCheckBorrowerNumEx borrower : borrowerNumList){
						if(borrower.getDhlyxx() != null){
							lyxxList.addAll(borrower.getDhlyxx());
						}
					}
				}
				
				// 查找本人核实的电话信息-手机号
				params.put("rBorrowerInfoId", borrowerInfo.getId());
				// 类型为手机号
				params.put("type", "1");
				List<TelCheckBorrowerNumEx> phoneNumList = borrowerNumDao
						.getBrhsDhxxListByRid(params);			
				borrowerInfo.setPhoneNumList(phoneNumList);	
				if(phoneNumList != null){
					for(TelCheckBorrowerNumEx borrower : phoneNumList){
						if(borrower.getDhlyxx() != null){
							lyxxList.addAll(borrower.getDhlyxx());
						}
					}
				}
				borrowerInfo.setDhlyxx(lyxxList);
				
				// 查本人的居住地址
				List<DhzhDhgxbrshd> brshdList = brshdDao.getByRid(borrowerInfo.getId());
				for(DhzhDhgxbrshd brshd:brshdList) {
					if(StringUtils.isNotEmpty(brshd.getLiveProvince())){
						List<CityInfo> cityList = cityInfoService.findCity(brshd.getLiveProvince());
						brshd.setCityList(cityList);
					}
					if(StringUtils.isNotEmpty(brshd.getLiveCity())){
						List<CityInfo> districtList = cityInfoService.findDistrict(brshd.getLiveCity());
						brshd.setDistrictList(districtList);
					}
				}
				borrowerInfo.setBrshdList(brshdList);
			}
			
		}		
		return borrowerInfoList;
	}	
	
/*	private List<TelCheckBusiContractEx> getBusiContractList(Map<String,Object> filter){
		filter.put("dictVoiceSource", TelRecSrc.PURCHASE_SALE_CONTRACT_NUMBER.getCode());
		List<TelCheckBusiContractEx> busiContractList = busiContractDao
				.findListByLoanCode(filter);		
		return busiContractList;
	}*/
	
	/**
	 * 每次拨打电话后保存电话录音信息
	 * 2015年12月4日
	 * By 王浩
	 * @param callRecord 通话记录信息
	 * @return 返回该条录音记录的id
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String asyncSaveCallRecord(DhzhDhlyxx callRecord) {
		callRecord.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
		callRecord.preInsert();
		callRecordDao.insert(callRecord);
		return callRecord.getId();
	}	
	
	/**
	 * 删除电话录音信息
	 * 2015年12月4日
	 * By 王浩
	 * @param id 录音id
	 * @return 返回是否删除成功
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String asyncDeleteCallRecord(String id) {
		callRecordDao.delRecord(id);
		return BooleanType.TRUE;
	}	

	/**
	 * 保存单位信息中的电话号码信息
	 * 2015年12月18日
	 * By 王浩
	 * @param workTelNum 电话号码记录
	 * @return 返回电话号码信息的id
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public WorkTelNumEx saveWorkTelNum(WorkTelNum workTelNum) throws IllegalAccessException, InvocationTargetException{
		if (StringUtils.isBlank(workTelNum.getId())) {
			workTelNum.setWorkCheckRemark("");
			workTelNum.setIsInPool(NumberConstants.ZERO_STRING);
			workTelNum.setIsRepeat(NumberConstants.ZERO_STRING);
			workTelNum.setEditRemark(NumberConstants.ZERO_STRING);
			workTelNum.preInsert();
			workTelNumDao.insertWorkTelNum(workTelNum);
		} else {
			workTelNum.preUpdate();
			workTelNumDao.update(workTelNum);
		}
		WorkTelNumEx workTelNumEx = new WorkTelNumEx();
		BeanUtils.copyProperties(workTelNumEx,workTelNum);
		
		return workTelNumEx;
	}
	
	/**
	 * 新版申请表add
	 * 保存单位信息中的电话号码信息
	 * 2015年12月18日
	 * By 王浩
	 * @param workTelNum 电话号码记录
	 * @return 返回电话号码信息的id
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public TelCheckContactNumEx saveTelCheckContactNumExNew(TelCheckContactNumEx telCheckContactNumEx) throws IllegalAccessException, InvocationTargetException{
		
		if (StringUtils.isBlank(telCheckContactNumEx.getId())) {
			telCheckContactNumEx.setBrhsNewAdd(NumberConstants.ZERO_STRING);
			telCheckContactNumEx.setIsInPool(NumberConstants.ZERO_STRING);
			telCheckContactNumEx.setIsRepeat(NumberConstants.ZERO_STRING);
			telCheckContactNumEx.setEditRemark(NumberConstants.ZERO_STRING);
			telCheckContactNumEx.preInsert();
			contactNumDao.insertSelective(telCheckContactNumEx);
		} else {
			telCheckContactNumEx.preUpdate();
			contactNumDao.update(telCheckContactNumEx);
		}
		
		TelCheckContactNumEx contactTelNumEx = new TelCheckContactNumEx();
		BeanUtils.copyProperties(contactTelNumEx,telCheckContactNumEx);
		
		return contactTelNumEx;
	}
	
	/**
	 * 保存单位信息中的电话号码信息
	 * 2015年12月18日
	 * By 王浩
	 * @param workTelNum 电话号码记录
	 * @return 返回电话号码信息的id
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String  zcSaveWorkTelNum(WorkTelNum workTelNum ) throws IllegalAccessException, InvocationTargetException{
		// 单位电话保存处理
		if (StringUtils.isBlank(workTelNum.getId())) {
			workTelNum.setWorkCheckRemark("");
			workTelNum.setIsInPool(NumberConstants.ZERO_STRING);
			workTelNum.setIsRepeat(NumberConstants.ONE_STRING);
			workTelNum.setEditRemark(NumberConstants.ZERO_STRING);
			workTelNum.preInsert();
			workTelNumDao.insertWorkTelNum(workTelNum);
		} else {
			workTelNum.setIsRepeat(NumberConstants.ONE_STRING);
			workTelNum.preUpdate();
			workTelNumDao.update(workTelNum);
		}
		// 单位电话是否需要查重判定,只有来源为【申请人提供】的需要执行查重
		boolean result = false;
		if(TelSrc.TEL_SOURCE_APPLICATION_FORM.getCode().equals(workTelNum.getWorkTelSource())){
			Map<String,String> map = new HashMap<String,String>();
			map.put(TableType.TABLE_TYPE,TableType.WORK_TEL);
			map.put(TableType.ID, workTelNum.getId());
			result = isRepeate(map);
		}
		return workTelNum.getId()+","+result;
	}
	
	/**
	 * 保存单个单位电话
	 * 2016年3月30日
	 * By 李文勇
	 * @param workTelNum
	 * @return 返回参数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public DhzhDhlyxx saveOneCompanyNum(WorkTelNum workTelNum ,String localTel, VerifyParamEx verifyParamEx ,String ip){
		// 设置编辑标识为1（不可删除）
		workTelNum.setEditRemark(NumberConstants.ONE_STRING);
		workTelNum.preUpdate();
		workTelNumDao.update(workTelNum);
		DhzhDhlyxx callRecord = callPhone(workTelNum.getWorkUnitTel(),workTelNum.getId(),localTel,ip);
		return callRecord;
	}
	
	/**
	 * 保存法人代表电话信息
	 * 2016年10月26日
	 * By Wangyanna
	 * @param compManage
	 * @param localTel
	 * @param verifyParamEx
	 * @param ip
	 * @return 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public DhzhDhlyxx saveCorporateTel(CompManage compManage ,String localTel, VerifyParamEx verifyParamEx ,String ip){
		DhzhDhlyxx callRecord = callPhone(compManage.getCorporateRepresentMobile(),compManage.getId(),localTel,ip);
		return callRecord;
	}
	
	/**
	 * 打电话
	 * 2016年4月18日
	 * By 李文勇
	 * @param phone
	 * @param rGxId
	 * @param localTel
	 * @param ip
	 * @return
	 */
	public DhzhDhlyxx callPhone(String phone, String rGxId,String localTel, String ip){
		
		// new一个电话录音信息对象并成成ID
		DhzhDhlyxx callRecord = new DhzhDhlyxx();
		callRecord.setDhlyxxTel(phone);
		callRecord.setrGxId(rGxId);
		Date now = new Date();
		callRecord.setDhlyxxCallTime(now);
		callRecord.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
		callRecord.preInsert();
		callRecordDao.insert(callRecord);
		
		phone = phone.replaceAll("-", "");
		if(BooleanType.TRUE.equals(localTel)){// 选择为外地 加0
			phone = "0" + phone;
		}
		
		String clientIp = ip;
		String UUID = callRecord.getId();
		log.debug("***************************************************");
		log.debug("ip:"+clientIp);
		log.debug("***************************************************");
		
		String result = CallPhoneUtil.callPhone(phone, clientIp, UUID);
		// 发生异常的情况
		if(ReturnConstant.ERROR_EXT_NET.equals(result)
			|| ReturnConstant.ERROR_NET.equals(result)){
			log.debug("****************************************************");
			throw new ServiceException("拨打"+phone+"异常");
		}
		return callRecord;
	}
	
	/**
	 * 通过关联ID获取电话录音信息
	 * 2016年10月26日
	 * By 王艳娜
	 * @param rGxId
	 * @return
	 */
	public List<DhzhDhlyxx> getCorporateTelByRid(String rGxId){
		return callRecordDao.getByRid(rGxId).size() == 0 ? null : callRecordDao.getByRid(rGxId);
	}
	
	
	/**
	 * 删除单位电话号码 2015年12月31日 By 王浩
	 * @param workTelNum 电话号码记录
	 * @return 返回删除记录条数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public Map<String, String> deleteWorkTelNum(WorkTelNum workTelNum) {
		Map<String, String> resultMap = Maps.newHashMap();
		String repeate = BooleanType.FALSE;
		int result = 0;
		if (StringUtils.isNotEmpty(workTelNum.getId())) {
			// 执行删除查重
			Map<String, String> map = Maps.newHashMap();
			map.put(TableType.ID, workTelNum.getId());
			map.put(TableType.TABLE_TYPE, TableType.WORK_TEL);
			if (super.deleteRepeate(map)) {
				repeate = BooleanType.TRUE;
			}
			// 执行删除单位电话号码
			result = workTelNumDao.deleteById(workTelNum.getId());
		}
		resultMap.put("deleteFlag", Integer.toString(result));
		resultMap.put("repeateFlag", repeate);
		return resultMap;
	}
	
	/**
	 * 新版申请表add
	 * 删除单位电话号码 2015年12月31日 By 王浩
	 * @param workTelNum 电话号码记录
	 * @return 返回删除记录条数
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public Map<String, String> deleteContactTelNum(TelCheckContactNumEx telCheckContactNumEx) {
		Map<String, String> resultMap = Maps.newHashMap();
		String repeate = BooleanType.FALSE;
		int result = 0;
		if (StringUtils.isNotEmpty(telCheckContactNumEx.getId())) {
			// 执行删除查重
			Map<String, String> map = Maps.newHashMap();
			map.put(TableType.ID, telCheckContactNumEx.getId());
			map.put(TableType.TABLE_TYPE, TableType.CONTACT_TEL);
			if (super.deleteRepeate(map)) {
				repeate = BooleanType.TRUE;
			}
			// 执行删除单位电话号码
			result = contactNumDao.deleteByPrimaryKey(telCheckContactNumEx.getId());
		}
		resultMap.put("deleteFlag", Integer.toString(result));
		resultMap.put("repeateFlag", repeate);
		return resultMap;
	}
	
	/**
	 * 保存单位信息
	 * 2015年12月21日
	 * By 王浩
	 * @param companyInfo 单位信息记录
	 * @return 是否保存成功
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public TelCheckCompanyEx saveCompanyInfo(TelCheckCompanyEx companyInfo) throws IllegalAccessException, InvocationTargetException {
		TelCheckCompanyEx result = new TelCheckCompanyEx();
		// 更新单位信息
		companyInfo.preUpdate();
		telAuditWorkDao.updateByCompanyId(companyInfo);
		
		BeanUtils.copyProperties(result,companyInfo);
		// 更新单位电话
		List<WorkTelNumEx> numLis = new ArrayList<WorkTelNumEx>();
		if (ArrayHelper.isNotEmpty(companyInfo.getTelNumList())) {
			for (WorkTelNumEx telNum : companyInfo.getTelNumList()) {
				telNum.setWorkId(companyInfo.getId());
				WorkTelNumEx workNum = this.saveWorkTelNum(telNum);
				numLis.add(workNum);// 把对象放到list里面
			}
			result.setTelNumList(numLis);
		}
		// 更新电话录音信息
		if (ArrayHelper.isNotEmpty(companyInfo.getDhlyxx())) {
			for (DhzhDhlyxx dhly : companyInfo.getDhlyxx()) {
				dhly.setDictVoiceSource(TelRecSrc.PHONE_INSPECTION_UNIT.getCode());
				if (StringUtils.isBlank(dhly.getId())) {
					dhly.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
					dhly.preInsert();
					callRecordDao.insert(dhly);
				} else {
					dhly.preUpdate();
					callRecordDao.updateSelective(dhly);
				}
			}
		}
		return result;	
	}			
	
	/**
	 * 保存单个联系人信息
	 * 2015年12月18日
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @return 返回联系人信息的主键id
	 * @throws Exception 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveContactPersonInfo(TelCheckContactPersonEx contactPerson, VerifyParamEx param) throws Exception {		
		// 如果id不为空
		if (contactPerson != null && StringUtils.isEmpty(contactPerson.getId())) {
			// 保存联系人
			contactPerson.setLoanCode(param.getLoanCode());
			contactPerson.setDictCheckType(param.getCheckType());
			contactPerson.setDictCustomerType(param.getType());
			contactPerson.setrCustomerCoborrowerId(param.getRelId());			
			contactPerson.setIsInPool(YESNO.NO.getCode());
			contactPerson.setIsRepeat(YESNO.NO.getCode());
			contactPerson.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			contactPerson.preInsert();
			contactPersonDao.insertSelective(contactPerson);
		} else {
			contactPerson.preUpdate();
			contactPersonDao.update(contactPerson);
		}
		this.saveCallRecordList(contactPerson.getDhlyxx(),
				contactPerson.getId(),
				TelRecSrc.PHONE_RELATIONSHIP_INSPECTION.getCode());
		return contactPerson.getId();
	}
	
	/**
	 * 新版申请表add
	 * 保存单个联系人信息
	 * 2015年12月18日
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @return 返回联系人信息的主键id
	 * @throws Exception 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public TelCheckContactPersonEx saveContactPersonInfoNew(TelCheckContactPersonEx contactPerson, VerifyParamEx param) throws Exception {		
		// 如果id不为空
		if (contactPerson != null && StringUtils.isEmpty(contactPerson.getId())) {
			// 保存联系人
			contactPerson.setLoanCode(param.getLoanCode());
			contactPerson.setDictCheckType(param.getCheckType());
			contactPerson.setDictCustomerType(param.getType());
			contactPerson.setrCustomerCoborrowerId(param.getRelId());			
			contactPerson.setIsInPool(YESNO.NO.getCode());
			contactPerson.setIsRepeat(YESNO.NO.getCode());
			contactPerson.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			contactPerson.preInsert();
			contactPersonDao.insertSelective(contactPerson);
		} else {
			contactPerson.preUpdate();
			contactPersonDao.update(contactPerson);
		}
		
		TelCheckContactPersonEx result = new TelCheckContactPersonEx();
		BeanUtils.copyProperties(result,contactPerson);
		// 更新单位电话
		List<TelCheckContactNumEx> numLis = new ArrayList<TelCheckContactNumEx>();
		if (ArrayHelper.isNotEmpty(contactPerson.getContactNumList())) {
			for (TelCheckContactNumEx telCheckContactNumEx : contactPerson.getContactNumList()) {
				telCheckContactNumEx.setrDhgxshId(contactPerson.getId());
				TelCheckContactNumEx contactNumEx = this.saveTelCheckContactNumExNew(telCheckContactNumEx);
				numLis.add(contactNumEx);// 把对象放到list里面
			}
			result.setContactNumList(numLis);
		}
		// 更新电话录音信息
		if (ArrayHelper.isNotEmpty(contactPerson.getDhlyxx())) {
			for (DhzhDhlyxx dhly : contactPerson.getDhlyxx()) {
				dhly.setDictVoiceSource(TelRecSrc.PHONE_RELATIONSHIP_INSPECTION
						.getCode());
				if (StringUtils.isBlank(dhly.getId())) {
					dhly.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
					dhly.preInsert();
					callRecordDao.insert(dhly);
				} else {
					dhly.preUpdate();
					callRecordDao.updateSelective(dhly);
				}
			}
		}
		if (RelationType.FAMILY_CONTACTS.getCode().equals(contactPerson.getDictRelationType()) && StringUtils.isNotEmpty(contactPerson.getCustomerCertNum()) && StringUtils.isNotEmpty(contactPerson.getName())) {
			// 加入专网核查
			String wd = "其他";
			List<Map<String, String>> configs = netWorkConfigDao.findOutsideNetTelCom("99");
			for (Dict dict : DictUtils.getNewDictList(DictionaryConstants.LOAN_FAMILY_RELATION)){
				if (dict.getValue().equals(contactPerson.getLoanManRelation())){
					wd = dict.getLabel();
					break;
				}
			}
			Map<String, String> mapp = Maps.newHashMap();
			mapp.put("loanCode", param.getLoanCode());
			mapp.put("workId", contactPerson.getId());
			List<PrivateNetworkCheck> privateNetworkCheckList = privateNetworkCheckDao.findListByWorkId(mapp);
			if (ArrayHelper.isNotEmpty(privateNetworkCheckList)) {
				PrivateNetworkCheck check = new PrivateNetworkCheck();
				check.setWorkId(contactPerson.getId());
				check.setDictCheckType(param.getCheckType());
				check.setContent(contactPerson.getCustomerCertNum());
				check.setDictCustomerType(param.getType());
				check.setRelId(param.getRelId());
				check.setLoanCode(param.getLoanCode());
				check.setName(contactPerson.getName());
				check.setRelationship("亲属-" + wd);
				check.preUpdate();
				privateNetworkCheckDao.updateNetworksNew(check);
			} else {
				List<PrivateNetworkCheck> netWorkInfo = new ArrayList<PrivateNetworkCheck>();
				if (ArrayHelper.isNotEmpty(configs)) {
					for (Map<String, String> netWorkConfig : configs) { // 遍历所有的网查信息
						PrivateNetworkCheck check = new PrivateNetworkCheck();
						check.setWorkId(contactPerson.getId());
						check.setDictCheckType(param.getCheckType());
						check.setContent(contactPerson.getCustomerCertNum());
						check.setDictCustomerType(param.getType());
						check.setFlag("99");
						check.setRelId(param.getRelId());
						check.setLoanCode(param.getLoanCode());
						check.setConfigId(netWorkConfig.get("id")); // 把起对应的id存入其中
						check.setIsNewRecord(false);
						check.setName(contactPerson.getName());
						check.setRelationship("亲属-" + wd);
						check.preInsert();
						netWorkInfo.add(check);
					}
				}
				Map<String, Object> netWorkMap = Maps.newHashMap();
				if (ArrayHelper.isNotEmpty(netWorkInfo)) {
					netWorkMap.put("netWorks", netWorkInfo);
					privateNetworkCheckDao.insertNetwork(netWorkMap);
				}
			}
		}
		return result;
		
	}
	
	
	/**
	 * 保存单个联系人信息
	 * 2015年12月18日
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @return 返回联系人信息的主键id
	 * @throws Exception 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String zcSaveContactPersonInfo(TelCheckContactPersonEx contactPerson, VerifyParamEx param) throws Exception {		
		// 联系人信息处理
		if (contactPerson != null && StringUtils.isEmpty(contactPerson.getId())) {
			contactPerson.setLoanCode(param.getLoanCode());
			contactPerson.setDictCheckType(param.getCheckType());
			contactPerson.setDictCustomerType(param.getType());
			contactPerson.setrCustomerCoborrowerId(param.getRelId());			
			contactPerson.setIsInPool(NumberConstants.ZERO_STRING);
			contactPerson.setIsRepeat(NumberConstants.ONE_STRING);
			contactPerson.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			contactPerson.preInsert();
			contactPersonDao.insertSelective(contactPerson);
		} else {
			contactPerson.setIsRepeat(NumberConstants.ONE_STRING);
			contactPerson.preUpdate();
			contactPersonDao.update(contactPerson);
		}
		// 联系人是否需要查重判定,只有来源为【申请人提供】的需要执行查重
		boolean result = false;
		if(TelSrc.TEL_SOURCE_APPLICATION_FORM.getCode().equals(contactPerson.getDictTelSource())){
			Map<String,String> map = new HashMap<String,String>();
			map.put(TableType.TABLE_TYPE,TableType.CONTACT);
			map.put(TableType.ID, contactPerson.getId());
			result = isRepeate(map);
		}
		return contactPerson.getId()+","+result;
	}
	
	/**
	 * 新版申请表add
	 * 保存单个联系人信息
	 * 2015年12月18日
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @return 返回联系人信息的主键id
	 * @throws Exception 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public TelCheckContactPersonEx zcSaveContactPersonInfoNew(TelCheckContactPersonEx contactPerson, VerifyParamEx param) throws Exception {		
		// 联系人信息处理
		if (contactPerson != null && StringUtils.isEmpty(contactPerson.getId())) {
			contactPerson.setLoanCode(param.getLoanCode());
			contactPerson.setDictCheckType(param.getCheckType());
			contactPerson.setDictCustomerType(param.getType());
			contactPerson.setrCustomerCoborrowerId(param.getRelId());			
			contactPerson.setIsInPool(NumberConstants.ZERO_STRING);
			contactPerson.setIsRepeat(NumberConstants.ONE_STRING);
			contactPerson.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			contactPerson.preInsert();
			contactPersonDao.insertSelective(contactPerson);
		} else {
			contactPerson.setIsRepeat(NumberConstants.ONE_STRING);
			contactPerson.preUpdate();
			contactPersonDao.update(contactPerson);
		}
		
		TelCheckContactPersonEx result = new TelCheckContactPersonEx();
		BeanUtils.copyProperties(result,contactPerson);
		// 更新单位电话
		List<TelCheckContactNumEx> numLis = new ArrayList<TelCheckContactNumEx>();
		if (ArrayHelper.isNotEmpty(contactPerson.getContactNumList())) {
			for (TelCheckContactNumEx telCheckContactNumEx : contactPerson.getContactNumList()) {
				telCheckContactNumEx.setrDhgxshId(contactPerson.getId());
				TelCheckContactNumEx contactNumEx = this.saveTelCheckContactNumExNew(telCheckContactNumEx);
				numLis.add(contactNumEx);// 把对象放到list里面
			}
			result.setContactNumList(numLis);
		}
		// 更新电话录音信息
		if (ArrayHelper.isNotEmpty(contactPerson.getDhlyxx())) {
			for (DhzhDhlyxx dhly : contactPerson.getDhlyxx()) {
				dhly.setDictVoiceSource(TelRecSrc.PHONE_RELATIONSHIP_INSPECTION
						.getCode());
				if (StringUtils.isBlank(dhly.getId())) {
					dhly.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
					dhly.preInsert();
					callRecordDao.insert(dhly);
				} else {
					dhly.preUpdate();
					callRecordDao.updateSelective(dhly);
				}
			}
		}
		if (RelationType.FAMILY_CONTACTS.getCode().equals(contactPerson.getDictRelationType()) && StringUtils.isNotEmpty(contactPerson.getCustomerCertNum()) && StringUtils.isNotEmpty(contactPerson.getName())) {
			// 加入专网核查
			String wd = "其他";
			List<Map<String, String>> configs = netWorkConfigDao.findOutsideNetTelCom("99");
			for (Dict dict : DictUtils.getNewDictList(DictionaryConstants.LOAN_FAMILY_RELATION)){
				if (dict.getValue().equals(contactPerson.getLoanManRelation())){
					wd = dict.getLabel();
					break;
				}
			}
			Map<String, String> mapp = Maps.newHashMap();
			mapp.put("loanCode", param.getLoanCode());
			mapp.put("workId", contactPerson.getId());
			List<PrivateNetworkCheck> privateNetworkCheckList = privateNetworkCheckDao.findListByWorkId(mapp);
			if (ArrayHelper.isNotEmpty(privateNetworkCheckList)) {
				PrivateNetworkCheck check = new PrivateNetworkCheck();
				check.setWorkId(contactPerson.getId());
				check.setDictCheckType(param.getCheckType());
				check.setContent(contactPerson.getCustomerCertNum());
				check.setDictCustomerType(param.getType());
				check.setRelId(param.getRelId());
				check.setLoanCode(param.getLoanCode());
				check.setName(contactPerson.getName());
				check.setRelationship("亲属-" + wd);
				check.preUpdate();
				privateNetworkCheckDao.updateNetworksNew(check);
			} else {
				List<PrivateNetworkCheck> netWorkInfo = new ArrayList<PrivateNetworkCheck>();
				if (ArrayHelper.isNotEmpty(configs)) {
					for (Map<String, String> netWorkConfig : configs) { // 遍历所有的网查信息
						PrivateNetworkCheck check = new PrivateNetworkCheck();
						check.setWorkId(contactPerson.getId());
						check.setDictCheckType(param.getCheckType());
						check.setContent(contactPerson.getCustomerCertNum());
						check.setDictCustomerType(param.getType());
						check.setFlag("99");
						check.setRelId(param.getRelId());
						check.setLoanCode(param.getLoanCode());
						check.setConfigId(netWorkConfig.get("id")); // 把起对应的id存入其中
						check.setIsNewRecord(false);
						check.setName(contactPerson.getName());
						check.setRelationship("亲属-" + wd);
						check.preInsert();
						netWorkInfo.add(check);
					}
				}
				Map<String, Object> netWorkMap = Maps.newHashMap();
				if (ArrayHelper.isNotEmpty(netWorkInfo)) {
					netWorkMap.put("netWorks", netWorkInfo);
					privateNetworkCheckDao.insertNetwork(netWorkMap);
				}
			}
		}
		return result;
		
	}
	
	/**
	 * 新版申请表
	 * 保存单个联系人电话信息
	 * 2016年9月24日
	 * By 张虎
	 * @param contactPerson 联系人信息
	 * @param param 查询参数
	 * @param telCheckContactNumEx 电话信息
	 * @return 返回联系人信息的主键id
	 * @throws Exception 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String zcSaveContactPersonInfoPhone(
			TelCheckContactPersonEx contactPerson, VerifyParamEx param,
			TelCheckContactNumEx telCheckContactNumEx)  throws Exception {
		// TODO Auto-generated method stub
		// 联系人信息处理:新增联系人信息
		if (StringUtils.isEmpty(telCheckContactNumEx.getrDhgxshId()) && contactPerson != null && StringUtils.isEmpty(contactPerson.getId())) {
			contactPerson.setLoanCode(param.getLoanCode());
			contactPerson.setDictCheckType(param.getCheckType());
			contactPerson.setDictCustomerType(param.getType());
			contactPerson.setrCustomerCoborrowerId(param.getRelId());			
			contactPerson.setIsInPool(NumberConstants.ZERO_STRING);
			contactPerson.setIsRepeat(NumberConstants.ONE_STRING);
			contactPerson.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
			contactPerson.preInsert();
			contactPersonDao.insertSelective(contactPerson);
			// 设置关联id
			telCheckContactNumEx.setrDhgxshId(contactPerson.getId());
		}
		
		// 保存电话信息
		// 本人号码暂存处理
		if (StringUtils.isBlank(telCheckContactNumEx.getId())) {
			telCheckContactNumEx.setBrhsNewAdd(NumberConstants.ZERO_STRING);
			telCheckContactNumEx.setIsInPool(NumberConstants.ZERO_STRING);
			telCheckContactNumEx.setIsRepeat(NumberConstants.ONE_STRING);
			telCheckContactNumEx.setEditRemark(NumberConstants.ZERO_STRING);
			telCheckContactNumEx.preInsert();
			contactNumDao.insertSelective(telCheckContactNumEx);
		} else {
			telCheckContactNumEx.setIsRepeat(NumberConstants.ONE_STRING);
			telCheckContactNumEx.preUpdate();
			contactNumDao.update(telCheckContactNumEx);
		}
		
		// 联系人是否需要查重判定,只有来源为【申请人提供】的需要执行查重
		boolean result = false;
		if(TelSrc.TEL_SOURCE_APPLICATION_FORM.getCode().equals(telCheckContactNumEx.getSource())){
			Map<String,String> map = new HashMap<String,String>();
			map.put(TableType.TABLE_TYPE,TableType.CONTACT_TEL);
			map.put(TableType.ID, telCheckContactNumEx.getId());
			result = isRepeate(map);
		}
		
		return telCheckContactNumEx.getId() + "," + result + "," + contactPerson.getId();
		
	}
	
	
	
	/**
	 * 保存单个联系人信息
	 * 2015年12月18日
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @return 返回联系人信息的主键id
	 * @throws Exception 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public DhzhDhlyxx saveCallContactInfo(TelCheckContactPersonEx contactPerson,String localTel, VerifyParamEx param,String ip) throws Exception {		
		// 设置编辑标识为1（不可删除）
		contactPerson.setEditRemark(NumberConstants.ONE_STRING);
		contactPerson.preUpdate();
		contactPersonDao.update(contactPerson);
		DhzhDhlyxx callRecord = callPhone(contactPerson.getTelNum(),contactPerson.getId(),localTel,ip);
		return callRecord;
	}
	
	/**
	 * 新版申请表-关系人add
	 * 保存单个联系人信息
	 * 2015年12月18日
	 * By 王浩
	 * @param contactPerson 联系人信息
	 * @param telCheckContactNumEx 关系人电话信息
	 * @return 返回联系人信息的主键id
	 * @throws Exception 
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public DhzhDhlyxx saveCallContactInfoNew(TelCheckContactPersonEx contactPerson,
			String localTel, VerifyParamEx param,String ip, TelCheckContactNumEx telCheckContactNumEx) throws Exception {		
		
		// 设置编辑标识为1（不可删除）
		TelCheckContactPersonEx contactPersonNew = new TelCheckContactPersonEx();
		contactPersonNew.setId(telCheckContactNumEx.getrDhgxshId());
		contactPersonNew.setEditRemark(NumberConstants.ONE_STRING);
		contactPersonNew.preUpdate();
		contactPersonDao.update(contactPersonNew);
		
		// 设置编辑标识为1（不可删除）
		telCheckContactNumEx.setEditRemark(NumberConstants.ONE_STRING);
		telCheckContactNumEx.preUpdate();
		contactNumDao.update(telCheckContactNumEx);
		
		DhzhDhlyxx callRecord = callPhone(telCheckContactNumEx.getBrhsPhone(),telCheckContactNumEx.getId(),localTel,ip);
		return callRecord;
	}
	
	/**
	 * 保存多个电话录音
	 * 2015年12月18日
	 * By 王浩
	 * @param dhlyList 多个电话录音
	 * @param rid 录音记录关联到的表的主键
	 * @param voiceSource 电话录音来源，具体是关联到哪个表
	 * @return none
	 */
	public void saveCallRecordList(List<DhzhDhlyxx> dhlyList, String rid,
			String voiceSource) {
		if (ArrayHelper.isNotEmpty(dhlyList)) {
			for (DhzhDhlyxx dhly : dhlyList) {
				dhly.setrGxId(rid);
				dhly.setDictVoiceSource(voiceSource);
				if (StringUtils.isBlank(dhly.getId())) {
					dhly.setDictSourceType(ApproveConstants.DATA_SOURCE_CHP3_0);
					dhly.preInsert();
					callRecordDao.insert(dhly);
				} else {
					dhly.preUpdate();
					callRecordDao.updateSelective(dhly);
				}
			}
		}
	}			
	
	/**
	 * 保存本人核实信息
	 * 2015年12月16日
	 * By 王浩
	 * @param borrowerInfo 本人核实信息
	 * @return 是否保存成功
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveBorrowerInfo(TelCheckBorrowerInfoEx borrowerInfo) {
		borrowerInfoDao.updateByPrimaryKey(borrowerInfo);
		StringBuffer sb = new StringBuffer();
		sb.append(borrowerInfo.getId());// 拼接本人id
		sb.append(":");// 拼接“：”
		// 拼接【同业借款】，返回页面判断用
		if(borrowerInfo.getOtherLoanMark() != null){
			sb.append(borrowerInfo.getOtherLoanMark());
		}else{
			sb.append(NumberConstants.ONE_STRING);
		}
		// 拼接"-"
		sb.append("-");
		// 如果同业借款为“0”即无，则删除之前已保存的同业借款数据
		if(NumberConstants.ZERO_STRING.equals(borrowerInfo.getOtherLoanMark())){
			otherLoanDao.deleteByRid(borrowerInfo.getId());
		}
		// 如果同业借款为“1”即有，则保存或更新同业借款数据
		if(NumberConstants.ONE_STRING.equals(borrowerInfo.getOtherLoanMark())){
			List<DhzhBrhsJkje> otherLoanList = borrowerInfo.getOtherLoanList();
			if (ArrayHelper.isNotEmpty(otherLoanList)) {
				for (int i=0;i<otherLoanList.size();i++) {
					otherLoanList.get(i).setCheckId(borrowerInfo.getId());
					if (StringUtils.isBlank(otherLoanList.get(i).getId())) {
						otherLoanList.get(i).preInsert();
						otherLoanDao.insertSelective(otherLoanList.get(i));
					} else {
						otherLoanList.get(i).preUpdate();
						otherLoanDao.updateByPrimaryKeySelective(otherLoanList.get(i));
					}
					// 如果i!=0则后面拼接[,]
					if(i != 0){
						sb.append(",");
					}
					sb.append(otherLoanList.get(i).getId());
				}
			}
		}
		// 拼接“-”
		sb.append("-");
		// 保存界面上居住地址
		List<DhzhDhgxbrshd> brshdList = borrowerInfo.getBrshdList();
		if (ArrayHelper.isNotEmpty(brshdList)) {
			for (int i = 0 ; i < brshdList.size();i++) {
				if(brshdList.get(i).getLiveProvince()==null&&
						brshdList.get(i).getLiveCity()==null&&
					brshdList.get(i).getLiveArea()==null&&
					brshdList.get(i).getLiveAddress()==null){
					continue;
				}
				brshdList.get(i).setrCustomerCoborrowerId(borrowerInfo.getId());
				if (StringUtils.isBlank(brshdList.get(i).getId())) {
					brshdList.get(i).setIsRepeat(NumberConstants.ZERO_STRING);
					brshdList.get(i).preInsert();
					brshdDao.insertSelective(brshdList.get(i));
				} else {
					brshdList.get(i).preUpdate();
					brshdDao.updateByPrimaryKeySelective(brshdList.get(i));
				}
				// 如果i!=0则拼接[,]
				if(i != 0){
					sb.append(",");
				}
				sb.append(brshdList.get(i).getId());
			}
		}
		// 更新电话录音
		if (ArrayHelper.isNotEmpty(borrowerInfo.getDhlyxx())) {
			for (DhzhDhlyxx dhly : borrowerInfo.getDhlyxx()) {
				if (StringUtils.isBlank(dhly.getId())) {
					dhly.preInsert();
					callRecordDao.insert(dhly);
				} else {
					dhly.preUpdate();
					callRecordDao.updateSelective(dhly);
				}
			}
		}
		return sb.toString();
	}	
	
	/**
	 * 保存本人核实的电话号码及录音
	 * 2015年12月16日
	 * By 王浩
	 * @param borrowerNum 本人核实的电话号码
	 * @return 返回本人电话号码的主键id
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String saveBorrowerTelNum(TelCheckBorrowerNumEx borrowerNum){
		if (StringUtils.isBlank(borrowerNum.getId())) {
			borrowerNum.setBrhsNewAdd(NumberConstants.ZERO_STRING);
			borrowerNum.setIsInPool(NumberConstants.ZERO_STRING);
			borrowerNum.setIsRepeat(NumberConstants.ZERO_STRING);
			borrowerNum.setEditRemark(NumberConstants.ZERO_STRING);
			borrowerNum.preInsert();
			borrowerNumDao.insertSelective(borrowerNum);
		} else {
			borrowerNum.preUpdate();
			borrowerNumDao.update(borrowerNum);
		}
		return borrowerNum.getId();
	}		
	
	
	/**
	 * 保存本人核实的电话号码及录音
	 * 2015年12月16日
	 * By 王浩
	 * @param borrowerNum 本人核实的电话号码
	 * @return 返回本人电话号码的主键id
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String zcSaveBorrowerTelNum(TelCheckBorrowerNumEx borrowerNum){
		// 本人号码暂存处理
		if (StringUtils.isBlank(borrowerNum.getId())) {
			borrowerNum.setBrhsNewAdd(NumberConstants.ZERO_STRING);
			borrowerNum.setIsInPool(NumberConstants.ZERO_STRING);
			borrowerNum.setIsRepeat(NumberConstants.ONE_STRING);
			borrowerNum.setEditRemark(NumberConstants.ZERO_STRING);
			borrowerNum.preInsert();
			borrowerNumDao.insertSelective(borrowerNum);
		} else {
			borrowerNum.setIsRepeat(NumberConstants.ONE_STRING);
			borrowerNum.preUpdate();
			borrowerNumDao.update(borrowerNum);
		}
		// 联系人是否需要查重判定,只有来源为【申请人提供】的需要执行查重
		boolean result = false;
		if(TelSrc.TEL_SOURCE_APPLICATION_FORM.getCode().equals(borrowerNum.getSource())){
			Map<String,String> map = new HashMap<String,String>();
			map.put(TableType.TABLE_TYPE,TableType.MYSELF);
			map.put(TableType.ID, borrowerNum.getId());
			result = isRepeate(map);
		}
		return borrowerNum.getId()+","+result;
	}		
	
	
	/**
	 * 保存本人核实的电话号码及录音
	 * 2015年12月16日
	 * By 王浩
	 * @param borrowerNum 本人核实的电话号码
	 * @return 返回本人电话号码的主键id
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public DhzhDhlyxx saveOnePersonNum(TelCheckBorrowerNumEx borrowerNum,String localTel,VerifyParamEx verifyParamEx,String ip){
		// 设置编辑标识为“1”（不可删除）
		borrowerNum.setEditRemark(NumberConstants.ONE_STRING);
		borrowerNum.preUpdate();
		borrowerNumDao.update(borrowerNum);
		DhzhDhlyxx callRecord = callPhone(borrowerNum.getBrhsPhone(),borrowerNum.getId(),localTel,ip);
		return callRecord;
	}
	
	/**
	 * 删除同业借款信息
	 * 2015年12月17日
	 * By 王浩
	 * @param otherLoanId 同业借款id
	 * @return 返回是否删除成功
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String deleteOtherLoan(String id) {
		otherLoanDao.deleteByPrimaryKey(id);
		return BooleanType.TRUE;
	}	
	
	/**
	 * 删除电话号码
	 * 2016-03-04
	 * By 董超
	 * @param id
	 * @return 返回是否删除成功
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public Map<String, String> delPersonalNums(String id) {
		Map<String, String> resultMap = Maps.newHashMap();
		String repeate = BooleanType.FALSE;
		int result = 0;
		if (StringUtils.isNotEmpty(id)) {
			Map<String, String> map = Maps.newHashMap();
			map.put(TableType.ID, id);
			map.put(TableType.TABLE_TYPE, TableType.MYSELF);
			if (super.deleteRepeate(map)) {
				repeate = BooleanType.TRUE;
			}
			// 执行删除联系人
			result = borrowerNumDao.deleteByPrimaryKey(id);
		}
		// callRecordDao.delRecordByRID(id);
		resultMap.put("deleteFlag", Integer.toString(result));
		resultMap.put("repeateFlag", repeate);
		return resultMap;
	}
	
	/**
	 * 删除本人居住地址信息
	 * 2016年3月3日
	 * By 董超
	 * @param id
	 * @return 返回是否删除成功
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String delbrshd(String id) {
		String repeate = BooleanType.FALSE;
		if (StringUtils.isNotEmpty(id)) {
			if (brshdDao.deleteByPrimaryKey(id) > 0) {
				repeate = BooleanType.TRUE;
			}
		}
		return repeate;
	}	
	
	/**
	 * 获取单位信息
	 * 2016年4月29日
	 * By 李文勇
	 * @param verifyParam
	 * @return
	 */
	public TelCheckView getDwxx(VerifyParamEx verifyParam) {	
		Map<String, Object> filter = new HashMap<String, Object>();
		filter.put("loanCode", verifyParam.getLoanCode().trim());// 借款编码
		filter.put("dictCustomerType", verifyParam.getType().trim());// 主借人、共借人
		filter.put("dictCheckType", verifyParam.getCheckType().trim());// 信审初审、复议初审
		filter.put("rCustomerCoborrowerId", verifyParam.getRelId().trim());// 主借人或者共借人的主键
		
		TelCheckView telCheckView = new TelCheckView();		
		telCheckView.setDictList(getDictList());
		telCheckView.setDictValues(getDictValues());				
		
		// 查找工作单位信息
		List<TelCheckCompanyEx> companyInfoList = this.getCompanyInfoList(filter);
		telCheckView.setCompanyList(companyInfoList);
		return telCheckView;
	}	
	
	/**
	 * 删除联系人
	 * 2016年4月29日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public Map<String, String> delContact(String id) {
		Map<String, String> resultMap = Maps.newHashMap();
		String repeate = BooleanType.FALSE;
		int result = 0;
		if(StringUtils.isNotEmpty(id)){
			// 执行删除查重
			Map<String, String> map = Maps.newHashMap();
			map.put(TableType.ID, id);
			map.put(TableType.TABLE_TYPE, TableType.CONTACT);
			if (super.deleteRepeate(map)) {
				repeate = BooleanType.TRUE;
			}
			// 执行删除联系人
			result = contactPersonDao.deleteByPrimaryKey(id);
		}
		resultMap.put("deleteFlag", Integer.toString(result));
		resultMap.put("repeateFlag", repeate);
		return resultMap;
	}
	
	/**
	 * 新版申请表add
	 * 删除联系人
	 * 2016年4月29日
	 * By 李文勇
	 * @param id
	 * @return
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public Map<String, String> delContactNew(String id) {
		Map<String, String> resultMap = Maps.newHashMap();
		String repeate = BooleanType.FALSE;
		int result = 0;
		if(StringUtils.isNotEmpty(id)){
			// 执行删除查重
			Map<String, String> map = Maps.newHashMap();
			map.put(TableType.ID, id);
			map.put(TableType.TABLE_TYPE, TableType.CONTACT_TEL);
			if (super.deleteRepeate(map)) {
				repeate = BooleanType.TRUE;
			}
			// 执行删除联系人
			result = contactPersonDao.deleteByPrimaryKey(id);
			
			// 执行删除单位电话号码
			contactNumDao.deleteByRid(id);
			privateNetworkCheckDao.deleteByRelIdNew(id);
			
		}
		resultMap.put("deleteFlag", Integer.toString(result));
		resultMap.put("repeateFlag", repeate);
		return resultMap;
	}
	
	/**
	 * 获取行业代码字典数据
	 * 2016年4月14日
	 * By wanglidong
	 * @return 行业代码字典数据
	 */
	public IndustryTypeEx getCompIndustry() {
		// 字典数据
		List<Dict> dictList = DictUtils.getDictList(DictionaryConstants.INDUSTRY_CODE); 
		 // 初级 
		IndustryTypeEx industryTypeEx = new IndustryTypeEx();
		// 一级
		IndustryTypeOneEx oneTypeEx = null;   
		// 一级list
		List<IndustryTypeOneEx> oneLevelList = new ArrayList<IndustryTypeOneEx>(); 
		// 二级
		IndustryTypeTwoEx twoTypeEx = null;    
		// 二级list
		List<IndustryTypeTwoEx> towLevelList = null; 
		// 三级 
		IndustryTypeThreeEx threeLevelEx = null; 
		// 三级list
		List<IndustryTypeThreeEx> threeLevelList = null; 
		for (int i = 0; i < dictList.size(); i++) {
			String parentId = dictList.get(i).getParentId(); 
			if(NumberConstants.ZERO_STRING.equals(parentId)){
				oneTypeEx = new IndustryTypeOneEx();
				oneTypeEx.setOneLevelDict(dictList.get(i));
				oneLevelList.add(oneTypeEx);				
			}
		}
		for (int i = 0; i < oneLevelList.size(); i++) {
			// 一级 id
			String id = oneLevelList.get(i).getOneLevelDict().getId(); 
			// 二级list
			towLevelList = new ArrayList<IndustryTypeTwoEx>(); 
			for (int j = 0; j < dictList.size(); j++) {
				String parentId = dictList.get(j).getParentId();
				if(id.equals(parentId)){
					twoTypeEx = new IndustryTypeTwoEx();
					twoTypeEx.setTwoLevelDict(dictList.get(j));
					towLevelList.add(twoTypeEx);
				}
			}
			oneLevelList.get(i).setTowLevelList(towLevelList);
		}
		for (int i = 0; i < oneLevelList.size(); i++) {
			List<IndustryTypeTwoEx> towList = oneLevelList.get(i).getTowLevelList();
			for (int j = 0; j < towList.size(); j++) {
				String id = towList.get(j).getTwoLevelDict().getId();
				threeLevelList = new ArrayList<IndustryTypeThreeEx>();
				for (int k = 0; k < dictList.size(); k++) {
					String parentId = dictList.get(k).getParentId();
					if(id.equals(parentId)){
						threeLevelEx = new IndustryTypeThreeEx();
						threeLevelEx.setThreeLevelDict(dictList.get(k));
						threeLevelList.add(threeLevelEx);
					}
				}
				towList.get(j).setThreeLevelList(threeLevelList);
			}
		}
		industryTypeEx.setOneLevelList(oneLevelList);
		return industryTypeEx;
	}
	
	/**
	 * 根据parentid和type获取职业类别
	 * 2016年4月19日
	 * By 李文勇
	 * @param parentId
	 * @return
	 */
	public List<Dict> getOccupationType(String parentId){
		// 字典数据
		List<Dict> dictList = DictUtils.getDictList(DictionaryConstants.OCCUPATIONAL_CODES); 
		List<Dict> result = new ArrayList<Dict>();
		// 将拼接的一级，二级，三级的ID根据逗号拆分
		String[] pid = parentId.split(",");
		// 根据type获取职业分类字典，不为空的场合
		if(dictList != null && pid.length > 0){
			for( int len = pid.length-1; len >= 0; len -- ){
				// 先获取三级对应的职业分类
				for(Dict dic : dictList){
					if(pid[len].equals( dic.getParentId() ) ){
						result.add(dic);
					}
				}
				// 如果result不为空则跳出当前循环
				if( ArrayHelper.isNotEmpty(result) ){
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * 初始化获取职业分类
	 * 2016年4月19日
	 * By 李文勇
	 * @param parentId
	 * @return
	 */
	public List<Dict> getZYFL(String value){
		
		List<Dict> dictList = DictUtils.getDictList(DictionaryConstants.OCCUPATIONAL_CODES);
		List<Dict> result = new ArrayList<Dict>();
		String parentId = "";
		// 获取parentID
		if(dictList != null){
			for(Dict dic : dictList){
				if(value.equals( dic.getValue() ) ){
					parentId = dic.getParentId();
					break;
				}
			}
		}
		// 封装对象
		if(dictList != null){
			for(Dict dic : dictList){
				if(parentId.equals( dic.getParentId() ) ){
					result.add(dic);
				}
			}
		}
		return result;
	}


}
