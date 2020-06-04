package com.creditharmony.approve.antifraud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.approve.antifraud.constants.AntifraudTelCheck;
import com.creditharmony.approve.antifraud.dao.FqzdhzhBrhsDhxxDao;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudMeConfirm;
import com.creditharmony.approve.antifraud.entity.ex.AntifraudTelEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneConfirmEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneListEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneRecordingEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneWorkEx;
import com.creditharmony.approve.antifraud.entity.ex.PhoneWorkProvesEx;
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.util.CallPhoneUtil;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.DhzhDhlyxxDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.phone.dao.WorkTelNumDao;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.WorkTelNum;
import com.creditharmony.approve.phone.entity.ex.TelCheckCompanyEx;
import com.creditharmony.approve.phone.entity.ex.TelCheckContactPersonEx;
import com.creditharmony.approve.verify.dao.LoanCompanyDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.loan.type.FamilyRelation;
import com.creditharmony.core.loan.type.RelationType;
/**
 * 反欺诈-电话照会
 * @Class Name PhoneConsultService
 * @author 赖敏
 * @Create In 2015年12月23日
 */
@Service
public class PhoneConsultService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DhzhDhgxshDao dhgxshDao;
	@Autowired 
	private DhzhDhlyxxDao dhlyxxDao;
	@Autowired
	private FqzdhzhBrhsDhxxDao brhsDhxxDao;
	@Autowired
	private LoanCustomerDao customerDao;
	@Autowired
	private WorkTelNumDao workTelNumDao;
	@Autowired
	private LoanCompanyDao loanCompanyDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	@Autowired
	private DhzhDhgxshDao dhzhDhgxshDao;
	@Autowired
	private FqzdhzhBrhsDhxxDao fqzdhzhBrhsDhxxDao;
	
	/**
	 * 获取电话照会页面初始化信息
	 * 2015年12月14日
	 * By 赖敏
	 * @param loanCode
	 * @return 初始化封装信息
	 */
	public AntifraudTelEx getDhzhInfos(String loanCode){
		AntifraudTelEx checkView = new AntifraudTelEx();
		Map<String,Object> params = new HashMap<String,Object>();
		// 借款编码
		params.put("loanCode", loanCode);
		// 设置类型：反欺诈
		params.put("dictCheckType", ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
		// 查找工作单位信息
		List<TelCheckCompanyEx> companys = telAuditWorkDao.getUnitInfoByLoanCode(params);
		checkView.setCompanys(companys);
		// 查询关系审核信息:工作证明人
		List<TelCheckContactPersonEx> workProves = this.getWorkProves(params,RelationType.WORK_VOUCHER.getCode());
		checkView.setWorkProves(workProves);
		// 查询关系审核信息:家庭联系人
		List<TelCheckContactPersonEx> familyContacts = this.getWorkProves(params,RelationType.FAMILY_CONTACTS.getCode());
		checkView.setFamilys(familyContacts);
		// 查询关系审核信息:其他联系人
		List<TelCheckContactPersonEx> otherProves = this.getWorkProves(params,RelationType.OTHER_CONTACTS.getCode());
		checkView.setOthers(otherProves);
		// 查询本人核实
		List<AntifraudMeConfirm> myConfirms = brhsDhxxDao.getMyConfirm(loanCode);
		checkView.setMyConfirms(myConfirms);
		// 评估结果：正常、异常、无效
		List<Dict> telResults = DictUtils.getDictList(LendConstants.EVAL_RESULT);
		checkView.setTelResults(telResults);
		// 家庭联系人关系
		List<Dict> familyRelations = DictUtils.getDictList(LendConstants.LOAN_FAMILY_RELATION);
		checkView.setFamilyRelations(familyRelations);
		// 工作证明人和本人关系
		List<Dict> workProveRelations = DictUtils.getDictList(LendConstants.LOAN_WORKEMATE_RELATION);
		checkView.setWorkProveRelations(workProveRelations);
		
		
		// 其他联系人和本人关系
		List<Dict> otherRelations = DictUtils.getDictList( DictionaryConstants.LOAN_OTHER_RELATION_TYPE);
		checkView.setOtherRelations(otherRelations);
		// 接听状态
		List<Dict> answerStates = DictUtils.getDictList(LendConstants.APPROVE_PHONE_STATUE);
		checkView.setAnswerStates(answerStates);
		// 借款人居住地
		String liveAddress = customerDao.getLiveAddress(loanCode);
		checkView.setLiveAddress(liveAddress);
		return checkView;
	}
	
	
	
	
	/**
	 * 新版申请表
	 * 获取电话照会页面初始化信息
	 * 2016年10月09日
	 * By 吉慧娟
	 * @param loanCode
	 * @return 初始化封装信息
	 */
	public AntifraudTelEx getDhzhInfosNew(String loanCode){
		AntifraudTelEx checkView = new AntifraudTelEx();
		Map<String,Object> params = new HashMap<String,Object>();
		// 借款编码
		params.put("loanCode", loanCode);
		// 设置类型：反欺诈
		params.put("dictCheckType", ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
		// 查找工作单位信息
		List<TelCheckCompanyEx> companys = telAuditWorkDao.getUnitInfoByLoanCode(params);
		checkView.setCompanys(companys);
		// 查询关系审核信息:工作证明人
		List<TelCheckContactPersonEx> workProves = this.getWorkProves(params,RelationType.WORK_VOUCHER.getCode());
		checkView.setWorkProves(workProves);
		// 查询关系审核信息:家庭联系人
		List<TelCheckContactPersonEx> familyContacts = this.getWorkProves(params,RelationType.FAMILY_CONTACTS.getCode());
		checkView.setFamilys(familyContacts);
		// 查询关系审核信息:其他联系人
		List<TelCheckContactPersonEx> otherProves = this.getWorkProves(params,RelationType.OTHER_CONTACTS.getCode());
		checkView.setOthers(otherProves);
		// 查询本人核实
		List<AntifraudMeConfirm> myConfirms = brhsDhxxDao.getMyConfirm(loanCode);
		checkView.setMyConfirms(myConfirms);
		// 评估结果：正常、异常、无效
		List<Dict> telResults = DictUtils.getDictList(LendConstants.EVAL_RESULT);
		checkView.setTelResults(telResults);
		// 家庭联系人关系
		List<Dict> familyRelations = DictUtils.getAllDictList(LendConstants.LOAN_FAMILY_RELATION);
		checkView.setFamilyRelations(familyRelations);
		// 工作证明人和本人关系
		List<Dict> workProveRelations = DictUtils.getNewDictList(LendConstants.LOAN_WORKEMATE_RELATION);
		checkView.setWorkProveRelations(workProveRelations);
		// 其他联系人和本人关系
		List<Dict> otherRelations = DictUtils.getNewDictList( DictionaryConstants.LOAN_OTHER_RELATION_TYPE);
		checkView.setOtherRelations(otherRelations);
		// 接听状态
		List<Dict> answerStates = DictUtils.getDictList(LendConstants.APPROVE_PHONE_STATUE);
		checkView.setAnswerStates(answerStates);
		// 借款人居住地
		String liveAddress = customerDao.getLiveAddress(loanCode);
		checkView.setLiveAddress(liveAddress);
		return checkView;
	}
	
	
	
	
	/**
	 * 通过借款编码获取联系人信息
	 * 2015年11月29日
	 * By 赖敏
	 * @param params
	 * @param relationType 关系类型(工作证明人;家庭证明人)
	 * @return 联系人信息列表
	 */
	public List<TelCheckContactPersonEx> getWorkProves(Map<String,Object> params,String relationType){
		// 关系类型(工作证明人、家庭证明人)
		params.put("dictRelationType", relationType);
		List<TelCheckContactPersonEx> contactWorkList = dhgxshDao.getContacts(params);
		return contactWorkList;
	}

	/**
	 * 删除反欺诈单位电话
	 * 2016年3月1日
	 * By wanglidong
	 * @param telNumEx
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void delWorkTel(String id){
		workTelNumDao.deleteById(id);
	}
	
	/**
	 * 保存单位电话
	 * 2016年3月1日
	 * By wanglidong
	 * @param telNumEx
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String addWorkTel(PhoneWorkEx phoneWorkEx){
		phoneWorkEx.preInsert();
		phoneWorkEx.setEditRemark(AntifraudTelCheck.EDITABLE);
		workTelNumDao.insertWorkTel(phoneWorkEx);
		return phoneWorkEx.getId(); 
	}

	/**
	 * 更新电话录音
	 * 2015年12月14日
	 * By 赖敏
	 * @param dhlyxxs
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void updataRecord(List<DhzhDhlyxx> dhlyxxs){
		if(ArrayHelper.isNotEmpty(dhlyxxs)){
			for(DhzhDhlyxx dhlyxx : dhlyxxs){
				dhlyxx.preUpdate();
				dhlyxxDao.updateByKeyAndit(dhlyxx);
			}
		}
	}
	
	/**
	 * 删除电话录音
	 * 2015年11月29日
	 * By 赖敏
	 * @param id
	 * @return 成功返回true,失败返回false
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String delRecord(String id){
		int count = dhlyxxDao.delRecord(id);
		return count > 0 ? BooleanType.TRUE : BooleanType.FALSE;
	}
	
	/**
	 * 反欺诈预处理
	 * 2015年12月22日
	 * By 赖敏
	 * @param loanCode 借款编码
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void initTelCheck(String loanCode){
		// 如果不是首次进入反欺诈，则不执行预处理
		if(fqzdhzhBrhsDhxxDao.getByLoanCode(loanCode) > 0){
			return;
		}
		Map<String,Object> params = new HashMap<String,Object>();
		// 借款编码
		params.put("loanCode", loanCode);
		// 借款人类型
		params.put("customerType",LoanManFlag.MAIN_LOAN.getCode());
		
		// ----------获取单位信息--------------------------
		List<TelCheckCompanyEx> companyExs = loanCompanyDao.getListByCodeAndType(params);
		if(ArrayHelper.isNotEmpty(companyExs)){
			for(TelCheckCompanyEx companyEx : companyExs){
				// 设置类型：反欺诈
				companyEx.setDictCheckType(ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
				// 添加至电话照会单位信息
				companyEx.preInsert();
				//companyEx.setEditRemark(AntifraudTelCheck.UNEDITABLE);
				telAuditWorkDao.insertSelective(companyEx);
				// 添加电话号码
				WorkTelNum workTelNum = new WorkTelNum();
				workTelNum.setWorkId(companyEx.getId());
				workTelNum.setEditRemark(AntifraudTelCheck.UNEDITABLE);
				workTelNum.setWorkUnitTel(companyEx.getTelNum());
				workTelNum.preInsert();
				workTelNumDao.insertWorkTelNum(workTelNum);
			}
		}
		
		// -----------获取联系人-------------------------------------------------
		List<TelCheckContactPersonEx> workProves = loanCustomerDao.selectMainContact(params);
		// 获取配偶
		TelCheckContactPersonEx mate = loanCustomerDao.selectMateByLoanCode(params);
		if(mate != null){
			mate.setLoanManRelation(FamilyRelation.MATES.getCode());
			mate.setDictRelationType(RelationType.FAMILY_CONTACTS.getCode());
			workProves.add(mate);
		}
		if(ArrayHelper.isNotEmpty(workProves)){
			for(TelCheckContactPersonEx workProve : workProves){
				// 设置类型：反欺诈
				workProve.setEditRemark(AntifraudTelCheck.UNEDITABLE);
				workProve.setDictCheckType(ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
				workProve.preInsert();
				dhzhDhgxshDao.insertSelective(workProve);
			}
		}
		
		// ------------获取本人号码----------------------------------------------
		List<AntifraudMeConfirm> confirms = loanCustomerDao.getPhonesByLoanCode(loanCode);
		if(ArrayHelper.isNotEmpty(confirms)){
			for(AntifraudMeConfirm confirm : confirms){
				if( confirm != null  && !StringUtils.isEmpty(confirm.getBrhsPhone())){
					// 添加本人核实号码
					confirm.setEditRemark(AntifraudTelCheck.UNEDITABLE);
					confirm.setLoanCode(loanCode);
					confirm.preInsert();
					fqzdhzhBrhsDhxxDao.insertSelective(confirm);
				}
			}
		}
	}

	/**
	 * 修改电话照会所有form
	 * 2016年3月2日
	 * By wanglidong
	 * @param phoneListEx
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)	
	public PhoneListEx updataTelAll(PhoneListEx phoneListEx) {
		if(ArrayHelper.isNotEmpty(phoneListEx.getTelWorkList())){
			for(PhoneWorkEx phoneWorkEx : phoneListEx.getTelWorkList()){
				if(StringUtils.isBlank(phoneWorkEx.getId())){
					phoneWorkEx.preInsert();
					phoneWorkEx.setEditRemark(AntifraudTelCheck.EDITABLE);
					workTelNumDao.insertWorkTel(phoneWorkEx);
				}else {
					phoneWorkEx.preUpdate();
					// 修改单位电话
					workTelNumDao.updataWorkTel(phoneWorkEx);
				}
			}		
		}
		
		if(ArrayHelper.isNotEmpty(phoneListEx.getPhoneWorkProvesList())){
			for(PhoneWorkProvesEx phoneWorkProvesEx : phoneListEx.getPhoneWorkProvesList()){
				if(StringUtils.isBlank(phoneWorkProvesEx.getId())){
					phoneWorkProvesEx.preInsert();
					phoneWorkProvesEx.setDictRelationType(phoneWorkProvesEx.getType());
					phoneWorkProvesEx.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
					phoneWorkProvesEx.setDictCheckType(ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
					phoneWorkProvesEx.setEditRemark(AntifraudTelCheck.EDITABLE);
					
					dhgxshDao.addContacts(phoneWorkProvesEx);
					
				}else {
					phoneWorkProvesEx.preUpdate();
					// 修改联系人（单位证明人或，家庭联系人）
					dhgxshDao.updataContacts(phoneWorkProvesEx);
				}
			}		
		}
		if(ArrayHelper.isNotEmpty(phoneListEx.getPhoneConfirmList())){
			for(PhoneConfirmEx phoneConfirmEx : phoneListEx.getPhoneConfirmList()){
				if(StringUtils.isBlank(phoneConfirmEx.getId())){
					phoneConfirmEx.preInsert();
					phoneConfirmEx.setEditRemark(AntifraudTelCheck.EDITABLE);
					brhsDhxxDao.addConfirm(phoneConfirmEx);
				}else {
					phoneConfirmEx.preUpdate();
					// 修改本人核实
					brhsDhxxDao.updataConfirm(phoneConfirmEx);
				}
			}		
		}
		List<PhoneRecordingEx> recordList = new ArrayList<PhoneRecordingEx>();
		if(ArrayHelper.isNotEmpty(phoneListEx.getPhoneRecordList())){
			for(PhoneRecordingEx phoneRecordList : phoneListEx.getPhoneRecordList()){
				phoneRecordList.preUpdate();
				recordList.add(phoneRecordList);
			}		
			// 修改电话录音
			workTelNumDao.updataTelRecordAll(recordList);
		}
		return phoneListEx;
	}

	
	
	
	/**
	 * 增加工作证明人
	 * 2016年3月3日
	 * By wanglidong
	 * @param loanCode
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)	
	public String addContacts(PhoneWorkProvesEx phoneWorkProvesEx) {
		phoneWorkProvesEx.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
		phoneWorkProvesEx.setDictRelationType(RelationType.WORK_VOUCHER.getCode());
		phoneWorkProvesEx.setDictCheckType(ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
		phoneWorkProvesEx.setEditRemark(AntifraudTelCheck.EDITABLE);
		phoneWorkProvesEx.preInsert();
		dhgxshDao.addContacts(phoneWorkProvesEx);
		return phoneWorkProvesEx.getId();
	}

	/**
	 * 删除联系人
	 * 2016年3月3日
	 * By wanglidong
	 * @param id
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void delProves(String id) {
		dhgxshDao.delProves(id);
	}

	/**
	 * 添加家庭联系人
	 * 2016年3月3日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return id
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String addfamily(PhoneWorkProvesEx phoneWorkProvesEx) {
		phoneWorkProvesEx.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
		phoneWorkProvesEx.setDictRelationType(RelationType.FAMILY_CONTACTS.getCode());
		phoneWorkProvesEx.setDictCheckType(ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
		phoneWorkProvesEx.setEditRemark(AntifraudTelCheck.EDITABLE);
		phoneWorkProvesEx.preInsert();
		dhgxshDao.addContacts(phoneWorkProvesEx);
		return phoneWorkProvesEx.getId();
	}

	/**
	 * 保存本人核实
	 * 2016年3月4日
	 * By wanglidong
	 * @param loanCode 借款编码
	 * @return id
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String addConfirm(PhoneConfirmEx phoneConfirmEx) {
		phoneConfirmEx.preInsert();
		phoneConfirmEx.setEditRemark(AntifraudTelCheck.EDITABLE); 
		brhsDhxxDao.addConfirm(phoneConfirmEx);
		return phoneConfirmEx.getId();
	}

	/**
	 * 删除本人核实
	 * 2016年3月4日
	 * By wanglidong
	 * @param id
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void delConfirm(String id) {
		brhsDhxxDao.delConfirm(id);
	}
	
	/**
	 * 保存电话录音
	 * 2015年12月11日
	 * By 赖敏
	 * @param dhlyxx
	 * @return 新增电话录音ID
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public DhzhDhlyxx saveRecord(DhzhDhlyxx dhlyxx , String ip){
		dhlyxx.preInsert();
		dhlyxxDao.insertSelective(dhlyxx);
		String phoneNo = dhlyxx.getDhlyxxTel();
		String phone = null;
		// 如果是长途加0
		if(NumberConstants.ONE_STRING.equals(dhlyxx.getLongDistancePhone())){
			phone=NumberConstants.ZERO_STRING+phoneNo.replace("-",""); 
		}else{
			phone=phoneNo.replace("-",""); 
		}
		String UUID = dhlyxx.getId();
		String clientIp = ip;
		String backCode = CallPhoneUtil.callPhone(phone, clientIp, UUID);
		if(ReturnConstant.ERROR.equals(backCode)){
			logger.debug("异常状态");
			throw new ServiceException(ReturnConstant.ERROR);
		}else if(ReturnConstant.ERROR_NET.equals(backCode)){
			logger.debug("异常状态(接口工程异常)");
			throw new ServiceException(ReturnConstant.ERROR_NET);
		}else if(ReturnConstant.ERROR_EXT_NET.equals(backCode)){
			logger.debug("异常状态(第三方服务异常)");
			throw new ServiceException(ReturnConstant.ERROR_EXT_NET);
		}
		return dhlyxx;
	}

	/**
	 * 新增其他联系人
	 * 2016年4月9日
	 * By wanglidong
	 * @param phoneWorkProvesEx
	 * @return id
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String addOhters(PhoneWorkProvesEx phoneWorkProvesEx) {
		phoneWorkProvesEx.setDictCustomerType(LoanManFlag.MAIN_LOAN.getCode());
		phoneWorkProvesEx.setDictRelationType(RelationType.OTHER_CONTACTS.getCode());
		phoneWorkProvesEx.setDictCheckType(ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
		phoneWorkProvesEx.setEditRemark(AntifraudTelCheck.EDITABLE);
		phoneWorkProvesEx.preInsert();
		dhgxshDao.addContacts(phoneWorkProvesEx);
		return phoneWorkProvesEx.getId();
	}
	
}
















