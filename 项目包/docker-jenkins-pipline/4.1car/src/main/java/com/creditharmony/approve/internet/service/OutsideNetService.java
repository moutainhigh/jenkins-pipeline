package com.creditharmony.approve.internet.service;

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
import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.common.constants.TableType;
import com.creditharmony.approve.common.service.VefiryCheckService;
import com.creditharmony.approve.common.util.CallPhoneUtil;
import com.creditharmony.approve.document.dao.ZlshZczmDao;
import com.creditharmony.approve.document.entity.ZlshZczm;
import com.creditharmony.approve.internet.dao.LegalPersonCheckDao;
import com.creditharmony.approve.internet.dao.NetWorkConfigDao;
import com.creditharmony.approve.internet.dao.PrivateNetworkCheckDao;
import com.creditharmony.approve.internet.entity.LegalPersonCheck;
import com.creditharmony.approve.internet.entity.PrivateNetworkCheck;
import com.creditharmony.approve.internet.entity.ex.OutSideCheckConEx;
import com.creditharmony.approve.internet.entity.ex.OutSideCheckConParamEx;
import com.creditharmony.approve.internet.entity.ex.OutSideCheckEx;
import com.creditharmony.approve.internet.entity.ex.OutsideNetCheckEx;
import com.creditharmony.approve.internet.entity.ex.OutsideNetInfoEx;
import com.creditharmony.approve.phone.dao.DhzhBrhsDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsDhxxDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxbrshdDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.DhzhDhlyxxDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.phone.dao.WorkTelNumDao;
import com.creditharmony.approve.phone.entity.DhzhBrhsDhxx;
import com.creditharmony.approve.phone.entity.DhzhDhgxbrshd;
import com.creditharmony.approve.phone.entity.DhzhDhgxsh;
import com.creditharmony.approve.phone.entity.DhzhDhlyxx;
import com.creditharmony.approve.phone.entity.TelAuditWork;
import com.creditharmony.approve.phone.entity.WorkTelNum;
import com.creditharmony.approve.phone.entity.ex.TelAuditWorkEx;
import com.creditharmony.approve.verify.dao.LoanCompanyDao;
import com.creditharmony.approve.verify.entity.ex.RecordListEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.entity.ex.WorkTelNumParamEx;
import com.creditharmony.approve.verify.util.DictToMapUtil;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.TelSrc;
import com.creditharmony.core.approve.type.WorkUnitNameSrc;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.loan.type.YESNO;
import com.google.common.collect.Maps;

/**
 * 外网审核 相关service
 * 
 * @Class Name OutsideNetService
 * @author 刘燕军
 * @Create In 2015年12月8日
 * @update in 2016-10-17
 */
@Service
public class OutsideNetService extends VefiryCheckService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private LoanCompanyDao loanCompanyDao;
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	@Autowired
	private DhzhBrhsDao dhzhBrhsDao;
	@Autowired
	private DhzhBrhsDhxxDao dhzhBrhsDhxxDao;
	@Autowired
	private WorkTelNumDao workTelNumDao;
	@Autowired
	private DhzhDhgxshDao dhzhDhgxshDao;
	@Autowired
	private NetWorkConfigDao workDao;
	@Autowired
	private PrivateNetworkCheckDao privateNetworkCheckDao;
	@Autowired
	private DhzhDhlyxxDao dhlyDao;
	@Autowired
	private DhzhDhgxbrshdDao brshDao;
	@Autowired
	private ZlshZczmDao zczmDao;
	@Autowired
	private LegalPersonCheckDao legalPersonCheckDao;

	/**
	 * 外网审核初始化 2015年12月8日 By 刘燕军 外网审核 初始化数据
	 * 
	 * @param param
	 * @return 外网审核中需要显示的数据
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public OutSideCheckEx getOutsideCheckView(VerifyParamEx param) {
		OutSideCheckEx outsideCheckEx = new OutSideCheckEx();
		List<TelAuditWorkEx> telAuditWorks = telAuditWorkDao.findCompanys(param);
		// 查询电话录音信息
		List<DhzhDhlyxx> dhzhDhlyxxs = dhlyDao.findByParam(param);
		// 一键网查
		List<OutsideNetCheckEx> outNet = dhzhBrhsDao.findOutsideNet(param);
		outsideCheckEx.setOutNet(outNet);
		// 专网查询
		outsideCheckEx.setNetWorks(privateNetworkCheckDao.findList(param));
		outsideCheckEx.setTelAuditWorks(telAuditWorks);
		// 电话录音
		outsideCheckEx.setDhzhDhlyxxs(dhzhDhlyxxs);
		outsideCheckEx.setPrivateType(DictToMapUtil.getMap(DictionaryConstants.PRIVATE_TYPE));
		outsideCheckEx.setTelSource(DictToMapUtil.getMap(DictionaryConstants.TEL_SOURCE));
		return outsideCheckEx;
	}

	/**
	 * 外网审核初始化（新版申请表） 2016年9月23日 外网审核 初始化数据
	 * 
	 * @param param
	 * @return 外网审核中需要显示的数据
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public OutSideCheckEx getOutsideCheckViewNew(VerifyParamEx param) {
		OutSideCheckEx outsideCheckEx = new OutSideCheckEx();
		List<TelAuditWorkEx> telAuditWorks = telAuditWorkDao.findCompanys(param);
		// 查询电话录音信息
		List<DhzhDhlyxx> dhzhDhlyxxs = dhlyDao.findByParam(param);
		// 一键网查
		List<OutsideNetCheckEx> outNet = dhzhBrhsDao.findOutsideNetNew(param);
		outsideCheckEx.setOutNet(outNet);
		List<OutSideCheckConEx> phoneChecks = dhzhBrhsDao.findOutsideNetConNew(param);
		outsideCheckEx.setPhoneChecks(phoneChecks);
		// 专网查询
		outsideCheckEx.setNetWorks(privateNetworkCheckDao.findList(param));
		outsideCheckEx.setTelAuditWorks(telAuditWorks);
		// 电话录音
		outsideCheckEx.setDhzhDhlyxxs(dhzhDhlyxxs);
		outsideCheckEx.setPrivateType(DictToMapUtil.getMap(DictionaryConstants.PRIVATE_TYPE));
		outsideCheckEx.setTelSource(DictToMapUtil.getMap(DictionaryConstants.TEL_SOURCE));
		return outsideCheckEx;
	}

	/**
	 * 一键网查 信息更新 2015年12月21日 By 刘燕军
	 * 
	 * @param param
	 *            一键网查更新参数
	 * @return 无返回值
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void saveNet(OutsideNetInfoEx netInfo, OutsideNetCheckEx param, WorkTelNumParamEx workNum,
			RecordListEx records) {
		// 本人核实信息更新
		if (!StringUtils.isBlank(param.getId())) {
			param.preUpdate();
			dhzhBrhsDao.updateById(param);
		}
		// 更新联系人中配偶的身份证信息
		if (StringUtils.isNotEmpty(param.getRelId())) {
			DhzhDhgxsh gsxh = new DhzhDhgxsh();
			gsxh.setId(param.getRelId());
			gsxh.setMateCertnumNetResult(param.getResult());
			gsxh.setMateCertnumNetRemark(param.getRemark());
			gsxh.setNetCheckRemark(param.getNameRemark());
			gsxh.setNetCheckResult(param.getNameResult());
			gsxh.preUpdate();
			dhzhDhgxshDao.update(gsxh);
		}

		List<DhzhBrhsDhxx> phoneInfo = param.getPhoneInfo();
		List<TelAuditWorkEx> workInof = param.getWorkInof();
		List<DhzhDhgxsh> contacts = param.getContacts();
		List<ZlshZczm> houseAddress = param.getHouseAdderss();
		if (ArrayHelper.isNotEmpty(phoneInfo)) { // 跟新本人核实电话信息
			for (DhzhBrhsDhxx dhzhBrhsDhxx : phoneInfo) {
				if (!StringUtils.isBlank(dhzhBrhsDhxx.getId())) {
					dhzhBrhsDhxx.preUpdate();
					dhzhBrhsDhxxDao.update(dhzhBrhsDhxx);
				}
			}
		}
		if (ArrayHelper.isNotEmpty(workInof)) { // 更新单位信息
			for (TelAuditWorkEx telAuditWorkEx : workInof) {
				if (!StringUtils.isBlank(telAuditWorkEx.getId())) {
					telAuditWorkEx.preUpdate();
					telAuditWorkDao.updateById(telAuditWorkEx);
				}
				List<WorkTelNum> workTelNums = telAuditWorkEx.getWorkTelNums();
				if (ArrayHelper.isNotEmpty(workTelNums)) { // 更新单位 电话信息
					for (WorkTelNum workTelNum : workTelNums) {
						if (!StringUtils.isBlank(workTelNum.getId())) {
							workTelNum.preUpdate();
							workTelNumDao.update(workTelNum);
						}
					}
				}
			}
		}
		if (ArrayHelper.isNotEmpty(contacts)) { // 更新联系人信息
			for (DhzhDhgxsh dhzhDhgxsh : contacts) {
				if (!StringUtils.isBlank(dhzhDhgxsh.getId())) {
					dhzhDhgxsh.preUpdate();
					dhzhDhgxshDao.update(dhzhDhgxsh);
				}
			}
		}
		if (ArrayHelper.isNotEmpty(houseAddress)) { // 更新房产信息
			for (ZlshZczm zlshZczm : houseAddress) {
				zlshZczm.preUpdate();
				zczmDao.updateByPrimaryKeySelective(zlshZczm);
			}
		}
		List<PrivateNetworkCheck> netWorks = netInfo.getNetWorks();
		if (ArrayHelper.isNotEmpty(netWorks)) { // 专网查询不为空
			Map<String, Object> map = new HashMap<String, Object>();
			for (PrivateNetworkCheck privateNetworkCheck : netWorks) { // 调用更新方法
				privateNetworkCheck.preUpdate();
			}
			map.put("netWorks", netWorks);
			privateNetworkCheckDao.updateNetworks(map); // 更新专网查询信息
		}
		List<WorkTelNum> nums = workNum.getWorkTelNums();
		if (ArrayHelper.isNotEmpty(nums)) {
			Map<String, List<WorkTelNum>> map = new HashMap<String, List<WorkTelNum>>();
			map.put("nums", nums);
			workTelNumDao.updateNums(map);
		}
		if (ArrayHelper.isNotEmpty(records.getRecordData())) {
			Map<String, List<DhzhDhlyxx>> map = new HashMap<String, List<DhzhDhlyxx>>();
			map.put("recordData", records.getRecordData());
			dhlyDao.updateByList(map);
		}
		// 更新本人居住地址
		List<DhzhDhgxbrshd> address = param.getLiveAddress();
		if (ArrayHelper.isNotEmpty(address)) {
			Map<String, List<DhzhDhgxbrshd>> map = new HashMap<String, List<DhzhDhgxbrshd>>();
			map.put("address", address);
			brshDao.updateByList(map);
		}
	}

	/**
	 * 一键网查-信息更新（新版申请表） 2016年10月17日 By luojunping
	 * 
	 * @param param
	 *            一键网查更新参数
	 * @return 无返回值
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void saveNetNew(OutsideNetInfoEx netInfo, OutsideNetCheckEx param, WorkTelNumParamEx workNum,
			RecordListEx records, OutSideCheckConParamEx outSideCheckConParamEx) {
		// 本人核实信息更新
		if (!StringUtils.isBlank(param.getId())) {
			param.preUpdate();
			dhzhBrhsDao.updateById(param);
		}
		// 更新联系人中配偶的身份证信息（新版申请表增加配偶的单位名称和住址）
		if (StringUtils.isNotEmpty(param.getRelId())) {
			DhzhDhgxsh gsxh = new DhzhDhgxsh();
			gsxh.setId(param.getRelId());
			gsxh.setMateCertnumNetResult(param.getResult());
			gsxh.setMateCertnumNetRemark(param.getRemark());
			gsxh.setNetCheckRemark(param.getNameRemark());
			gsxh.setNetCheckResult(param.getNameResult());
			gsxh.setMateUnitnameNetResult(param.getMateUnitnameNetResult());
			gsxh.setMateUnitnameNetRemark(param.getMateUnitnameNetRemark());
			gsxh.setMateAddressNetResult(param.getMateAddressNetResult());
			gsxh.setMateAddressNetRemark(param.getMateAddressNetRemark());
			gsxh.preUpdate();
			dhzhDhgxshDao.update(gsxh);
		}
		List<DhzhBrhsDhxx> phoneInfo = param.getPhoneInfo();
		List<TelAuditWorkEx> workInof = param.getWorkInof();
		List<DhzhDhgxsh> contacts = param.getContacts();
		List<ZlshZczm> houseAddress = param.getHouseAdderss();
		if (ArrayHelper.isNotEmpty(phoneInfo)) { // 跟新本人核实电话信息
			for (DhzhBrhsDhxx dhzhBrhsDhxx : phoneInfo) {
				if (!StringUtils.isBlank(dhzhBrhsDhxx.getId())) {
					dhzhBrhsDhxx.preUpdate();
					dhzhBrhsDhxxDao.update(dhzhBrhsDhxx);
				}
			}
		}
		if (ArrayHelper.isNotEmpty(workInof)) { // 更新单位信息
			for (TelAuditWorkEx telAuditWorkEx : workInof) {
				if (!StringUtils.isBlank(telAuditWorkEx.getId())) {
					telAuditWorkEx.preUpdate();
					telAuditWorkDao.updateById(telAuditWorkEx);
				}
				List<WorkTelNum> workTelNums = telAuditWorkEx.getWorkTelNums();
				if (ArrayHelper.isNotEmpty(workTelNums)) { // 更新单位 电话信息
					for (WorkTelNum workTelNum : workTelNums) {
						if (!StringUtils.isBlank(workTelNum.getId())) {
							workTelNum.preUpdate();
							workTelNumDao.update(workTelNum);
						}
					}
				}
			}
		}
		if (ArrayHelper.isNotEmpty(contacts)) { // 更新联系人信息
			for (DhzhDhgxsh dhzhDhgxsh : contacts) {
				if (!StringUtils.isBlank(dhzhDhgxsh.getId())) {
					dhzhDhgxsh.preUpdate();
					dhzhDhgxshDao.updateNewApply(dhzhDhgxsh);
				}
			}
		}
		if (ArrayHelper.isNotEmpty(houseAddress)) { // 更新房产信息
			for (ZlshZczm zlshZczm : houseAddress) {
				zlshZczm.preUpdate();
				zczmDao.updateByPrimaryKeySelective(zlshZczm);
			}
		}
		List<PrivateNetworkCheck> netWorks = netInfo.getNetWorks();
		if (ArrayHelper.isNotEmpty(netWorks)) { // 专网查询不为空
			Map<String, Object> map = new HashMap<String, Object>();
			for (PrivateNetworkCheck privateNetworkCheck : netWorks) { // 调用更新方法
				privateNetworkCheck.preUpdate();
			}
			map.put("netWorks", netWorks);
			privateNetworkCheckDao.updateNetworks(map); // 更新专网查询信息
		}
		List<WorkTelNum> nums = workNum.getWorkTelNums();
		if (ArrayHelper.isNotEmpty(nums)) {
			Map<String, List<WorkTelNum>> map = new HashMap<String, List<WorkTelNum>>();
			map.put("nums", nums);
			workTelNumDao.updateNums(map);
		}
		if (ArrayHelper.isNotEmpty(records.getRecordData())) {
			Map<String, List<DhzhDhlyxx>> map = new HashMap<String, List<DhzhDhlyxx>>();
			map.put("recordData", records.getRecordData());
			dhlyDao.updateByList(map);
		}
		// 更新本人居住地址
		List<DhzhDhgxbrshd> address = param.getLiveAddress();
		if (ArrayHelper.isNotEmpty(address)) {
			Map<String, List<DhzhDhgxbrshd>> map = new HashMap<String, List<DhzhDhgxbrshd>>();
			map.put("address", address);
			brshDao.updateByList(map);
		}
		// 新版申请表新增法人保证人信息网查记录
		if (StringUtils.isNotEmpty(param.getCompLegalId())) {
			LegalPersonCheck legalPersonCheck = new LegalPersonCheck();
			legalPersonCheck.setId(param.getCompLegalId());
			legalPersonCheck.setLoanCode(param.getLoanCode());
			legalPersonCheck.setComLegalManResult(param.getComLegalManResult());
			legalPersonCheck.setComLegalManRemark(param.getComLegalManRemark());
			legalPersonCheck.setComLegalManNumResult(param.getComLegalManNumResult());
			legalPersonCheck.setComLegalManNumRemark(param.getComLegalManNumRemark());
			legalPersonCheck.setComLegalManMoblieResult(param.getComLegalManMoblieResult());
			legalPersonCheck.setComLegalManMoblieRemark(param.getComLegalManMoblieRemark());
			legalPersonCheck.setComEmailNetResult(param.getComEmailNetResult());
			legalPersonCheck.setComEmailNetRemark(param.getComEmailNetRemark());
			legalPersonCheck.preUpdate();
			legalPersonCheckDao.updateNetCheckResult(legalPersonCheck);
		}
		// 新版申请表亲属电话和宅电网查记录变更（包括配偶）
		List<OutSideCheckConEx> phoneChecks = outSideCheckConParamEx.getPhoneChecks();
		if (ArrayHelper.isNotEmpty(phoneChecks)) {
			for (OutSideCheckConEx outSideCheckConEx : phoneChecks) {
				outSideCheckConEx.preUpdate();
				dhzhBrhsDao.updatePhoneCheckResult(outSideCheckConEx);
			}
		}
	}

	/**
	 * 单位信息删除 2015年12月21日 By 刘燕军
	 * 
	 * @param id
	 * @return 无返回值
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public boolean asynDelete(String id, String workName) {
		boolean workFlag = false;
		boolean telFlag = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put(TableType.TABLE_TYPE, TableType.WORK_NAME);
		map.put(TableType.ID, id);
		workFlag = super.deleteRepeate(map);
		map.put(TableType.TABLE_TYPE, TableType.WORK_TEL);
		telFlag = super.deleteRepeates(map);
		workTelNumDao.deleteByWorkId(id);
		telAuditWorkDao.deleteWork(id);
		privateNetworkCheckDao.deleteByRelId(id, workName);
		if (workFlag || telFlag) {
			return true;
		}
		return false;
	}

	/**
	 * 公司 电话信息添加 2015年12月21日 By 刘燕军
	 * 
	 * @param work
	 * @param workNum
	 * @param param
	 * @return 把添加的对象更新并返回
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public String asynSaveWorkInfo(TelAuditWork work, WorkTelNum workNum, VerifyParamEx param) {
		String result = BooleanType.FALSE;
		boolean nameFlag = false;
		boolean telFlag = false;
		// 单位信息追加
		work.setIsInPool(YESNO.NO.getCode());
		work.setIsRepeat(YESNO.YES.getCode());
		work.setEditRemark(YESNO.NO.getCode());
		work.setIsReady(YESNO.YES.getCode());
		work.preInsert();
		work.setLoanCode(param.getLoanCode());
		work.setrCustomerCoborrowerId(param.getRelId());
		work.setDictCustomerType(param.getType());
		work.setDictCheckType(param.getCheckType());
		telAuditWorkDao.insertSelective(work);
		Map<String, String> nameMap = new HashMap<String, String>();
		// 如果添加的信息来源与申请人提供，则需要把其对应的单位名称加入到专网查询中去
		if (WorkUnitNameSrc.UNIT_NAME_SOURCE_APPLICATION_FORM.getCode().equals(work.getWorkInfoSource())) {
			// 单位名称查重
			nameMap.put(TableType.ID, work.getId());
			nameMap.put(TableType.TABLE_TYPE, TableType.WORK_NAME);
			nameFlag = super.isRepeate(nameMap);
			// 加入专网核查
			List<Map<String, String>> configs = workDao.findOutsideNet(param.getRelId());
			List<PrivateNetworkCheck> netWorkInfo = new ArrayList<PrivateNetworkCheck>();
			if (ArrayHelper.isNotEmpty(configs)) {
				for (Map<String, String> netWorkConfig : configs) { // 遍历所有的网查信息
					PrivateNetworkCheck check = new PrivateNetworkCheck();
					check.setWorkId(work.getId());
					check.setDictCheckType(param.getCheckType());
					check.setContent(work.getWorkUnitname());
					check.setDictCustomerType(param.getType());
					check.setFlag(netWorkConfig.get("flag"));
					check.setRelId(work.getrCustomerCoborrowerId());
					check.setLoanCode(work.getLoanCode());
					check.setConfigId(netWorkConfig.get("id")); // 把起对应的id存入其中
					check.setIsNewRecord(false);
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
		// 单位电话追加
		if (workNum != null && !StringUtils.isBlank(workNum.getWorkUnitTel())) {
			workNum.setWorkId(work.getId());
			workNum.setIsInPool(YESNO.NO.getCode());
			workNum.setIsRepeat(YESNO.YES.getCode());
			workNum.setEditRemark(YESNO.NO.getCode());
			workNum.preInsert();
			workTelNumDao.insertWorkTelNum(workNum);
			if (TelSrc.TEL_SOURCE_APPLICATION_FORM.getCode().equals(workNum.getWorkTelSource())) {
				nameMap.put(TableType.ID, workNum.getId());
				nameMap.put(TableType.TABLE_TYPE, TableType.WORK_TEL);
				telFlag = super.isRepeate(nameMap);
			}
		}
		if (telFlag || nameFlag) { // 单位名称或者单位电话，如果有一个有查重结果。则返回true
			result = BooleanType.TRUE;
		}
		return result;
	}

	/**
	 * 电话信息添加 2015年12月21日 By 刘燕军
	 * 
	 * @param workNum
	 * @return 把添加的信息返回
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public WorkTelNum asynSavePhoneInfo(WorkTelNum workNum) {
		if (StringUtils.isBlank(workNum.getId())) {
			workNum.setIsInPool(YESNO.NO.getCode());
			workNum.setIsRepeat(YESNO.NO.getCode());
			workNum.setWorkTelSource(TelSrc.TEL_SOURCE_114_QUERIES.getCode());
			workNum.preInsert();
			workTelNumDao.insertWorkTelNum(workNum);
		} else {
			workNum.preUpdate();
			workTelNumDao.update(workNum);
		}

		return workNum;
	}

	/**
	 * 更新所有的电话信息 2015年12月21日 By 刘燕军
	 * 
	 * @param workNum
	 * @throws Exception
	 * @return 无返回值
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void asynUpdatePhoneInfo(WorkTelNum workNum) throws Exception {
		workNum.preUpdate();
		workTelNumDao.update(workNum);
	}

	/**
	 * 根据id删除对应的记录 2016年1月14日 By 刘燕军
	 * 
	 * @param id
	 * @return 无返回值
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public boolean asynDelPhoneInfo(String id) {
		boolean flag = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put(TableType.ID, id);
		map.put(TableType.TABLE_TYPE, TableType.WORK_TEL);
		flag = super.deleteRepeate(map);
		workTelNumDao.deleteById(id);
		return flag;
	}

	/**
	 * 根据id更新对应的单位名称 2016年1月14日 By 刘燕军
	 * 
	 * @param id
	 * @return 无返回值
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public boolean asynUpdateWork(TelAuditWorkEx telAuditWor) {
		// 更新单位名称
		telAuditWor.preUpdate();
		telAuditWorkDao.updateById(telAuditWor);
		// 更新一键网查中对应的单位名称
		privateNetworkCheckDao.updateByCodeName(telAuditWor);
		Map<String, String> map = new HashMap<String, String>();
		map.put(TableType.ID, telAuditWor.getId());
		map.put(TableType.TABLE_TYPE, TableType.WORK_NAME);
		boolean flag = super.isRepeate(map);
		// 更新汇金数据库中主借人的单位名称 某些情况下更新的判定条件
		loanCompanyDao.updateWorkName(telAuditWor);
		return flag;
	}

	/**
	 * 保存电话录音 2016年3月31日 By 刘燕军
	 * 
	 * @param dhly
	 * @throws Exception
	 */
	@Transactional(value = "loanTransactionManager", readOnly = false)
	public void asynSaveThis(DhzhDhlyxx dhly, String ip, String localTel) throws Exception {
		logger.debug("开始拨打电话");
		dhly.preInsert();
		logger.debug("电话信息实体：" + dhly.toString() + "电话号码：" + dhly.getDhlyxxTel() + "----ip:" + ip);
		dhlyDao.insertSelective(dhly);
		String tel = dhly.getDhlyxxTel();
		tel = tel.replaceAll("-", "");
		if (BooleanType.TRUE.equals(localTel)) {// 选择为外地 加0
			tel = "0" + tel;
		}
		String result = CallPhoneUtil.callPhone(tel, ip, dhly.getId());
		logger.debug("拨打结果：" + result);
		if (!ReturnConstant.SUCCESS.equals(result)) {
			logger.debug("呼叫失败！拨打结果：" + result);
			throw new ServiceException("电话拨打失败");
		}
	}

}