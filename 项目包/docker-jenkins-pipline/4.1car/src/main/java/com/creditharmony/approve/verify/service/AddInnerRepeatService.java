package com.creditharmony.approve.verify.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.constants.RepeatResult;
import com.creditharmony.approve.common.constants.TableType;
import com.creditharmony.approve.common.dao.InsertRepeatInfokDao;
import com.creditharmony.approve.common.dao.ProcedureDao;
import com.creditharmony.approve.common.entity.RepeateParam;
import com.creditharmony.approve.localnet.dao.InnerRepeatDao;
import com.creditharmony.approve.localnet.dao.VerifyRepeateDao;
import com.creditharmony.approve.outvisit.dao.OutsideDatacheckDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.verify.constants.ApplicationConstants;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 新增信息查重
 * @Class Name AddInnerReaptService
 * @author 刘燕军
 * @Create In 2016年1月5日
 */
@Service
public class AddInnerRepeatService extends  VerifyCommonService{
	/**
	 * 日志对象
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	@Autowired
	private InnerRepeatDao innerRepeatDao;
	@Autowired
	private DhzhBrhsDao brhsDao;
	@Autowired
	private DhzhDhgxshDao dhzhDhgxshDao;
	@Autowired
	private InsertRepeatInfokDao insertRepeatInfokDao;
	@Autowired
	private ProcedureDao procedureDao;
	@Autowired
	private OutsideDatacheckDao outsideDatacheckDao;
	@Autowired
	private VerifyRepeateDao verifyRepeateDao;
	
	@Autowired
	private VerifyCommonService verifyCommonService;
	
	/**
	 * 单位名称或号码查重
	 * 2016年5月20日
	 * By 刘燕军
	 * @param map
	 * @return 是否有查重
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String isRepeate(Map<String, String> map){
		int flag = 0;
		int returnFlag = 0;
		RepeateParam paramMap = verifyRepeateDao.getParam(map); // 获取查重需要的所有的参数
		if(!TableType.WORK_NAME.equals(map.get(TableType.TABLE_TYPE))){ // 如果是电话号码。则需要判断是否为无效号码
			flag = verifyRepeateDao.getPhoneUse(paramMap.getRepeateContent());
		}
		// 如果是单位名称，或者非无效号码，则开始其他操作
		if(TableType.WORK_NAME.equals(map.get(TableType.TABLE_TYPE)) || flag==0){
			if(paramMap != null){
				paramMap.setUser(UserUtils.getUser().getId());;
				returnFlag = verifyRepeateDao.repeateCheck(paramMap);
			}
		}
		if(returnFlag >0){ // 如果大于0，则有查重信息
			return BooleanType.TRUE;
		}
		return BooleanType.FALSE;
	}
	
	/**
	 * 根据参数删除对应的查重信息
	 * 2016年5月21日
	 * By 刘燕军
	 * @param map
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public void deleteRepeate(Map<String, String> map){
		RepeateParam paramMap = verifyRepeateDao.getParam(map); // 获取查重需要的所有的参数
		if(paramMap != null){ // 如果参数不为空，则删除对应的查重信息
			verifyRepeateDao.deleteRepeate(paramMap);
		}
	}
	/**
	 * 校验是否有发起外访
	 * 2016年7月21日
	 * By 刘燕军
	 * @param param
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=true)
	public String checkOutVisit(VerifyParamEx param){
		String outVist = outsideDatacheckDao.getOutVist(param);
		if(YESNO.YES.getCode().equals(outVist)){ // 发起外访
			logger.info("是否校验完成，结果"+outVist);
			return BooleanType.TRUE;
		}
		return BooleanType.FALSE;
	}
	
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String checkInfo(VerifyParamEx param){
		/*logger.debug("校验开始");
		int repeatNum = innerRepeatDao.checkException(param);
		// 内网核查 信息校验
		if(repeatNum > 0){ // 如果存在，则需要进行判定才可以进入决策页面
			return RepeatResult.BACK_INSIDE;
		}
		logger.debug("内网校验完成");*/
		// 获取新版旧版标识
		String newOrOldFlag = verifyCommonService.getOldornewFlag(param.getLoanCode());
		// 外网核查 信息校验
		int outside = 0;
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			outside = brhsDao.checkExceptionOut(param);
		} else {
			outside = brhsDao.checkExceptionOutNew(param);
		}
		if(outside>0){// 如果存在，则需要进行判定才可以进入决策页面
			return RepeatResult.BACK_OUTSIDE;
		}
		logger.debug("外网校验完成");
		
		int telWork = 0;
		// 判断为旧版
		if (newOrOldFlag.equals(ApplicationConstants.LOANINFO_OLD_FLAG)) {
			// 电话照会 信息校验
			telWork = telAuditWorkDao.checkExceptionInfo(param);
		} else {
			telWork = telAuditWorkDao.checkExceptionInfoNew(param);
		}

		if(telWork>0){
			return RepeatResult.BACK_TEL_CHECK;
		}
		logger.debug("电话照会校验完成");
		// 外访核查校验判断如果做过外访则进行校验outsideFlag 为1是做过外访
		if(param.getOutsideFlag().equals(NumberConstants.ONE_STRING)){
			int outsideDatacheck = outsideDatacheckDao.checkOutsideDatacheck(param);
			if(outsideDatacheck <= 0){
				return RepeatResult.BACK_OUT_VISIT_CHECK;
			}
			logger.debug("外访核查校验完成");
		}
		/*Map<String, String> mapType = getDatum(param.getLoanCode());
		mapType.put("checkType", param.getCheckType());
		mapType.put("loanCode", param.getLoanCode());
		logger.debug("获取图片文件夹完成"+mapType);
		// 资料审核 信息校验
		int dataCheck = telAuditWorkDao.checkExceptionData(mapType);
		if(dataCheck>0){
			return RepeatResult.BACK;
		}
		logger.debug("资料审核校验完成"+dataCheck);*/
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanCode", param.getLoanCode());
		logger.debug("决策查重开始，查重参数"+map);
		procedureDao.verifyRepeate(map);
		logger.debug("决策查重完成");
		return RepeatResult.PASS;
	}
	
	/**
	 * 拒绝的时候校验，内网和外网的校验
	 * 2016年3月14日
	 * By 刘燕军
	 * @param param
	 * @return
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String checkForRefuse(VerifyParamEx param){
/*		int repeatNum = innerRepeatDao.checkException(param);
		// 内网核查 信息校验
		if(repeatNum>0){ // 如果存在，则需要进行判定才可以进入决策页面
			return RepeatResult.BACK_INSIDE; 
		}*/
		// 本人核实  行业校验
		int outside = brhsDao.checkMySelf(param);
		if(outside>0){// 如果存在，则需要进行判定才可以进入决策页面
			return RepeatResult.BACK_TEL_CHECK;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanCode", param.getLoanCode());
		procedureDao.verifyRepeate(map);
		/*Object obj = map.get("result");
		Integer repeat = Integer.valueOf(obj.toString());
		if(repeat>0){ // 如果存在，则需要进行判定才可以进入决策页面
			return RepeatResult.BACK_INSIDE; 
		}*/
		return RepeatResult.PASS;
	}
	
	/**
	 * 新增信息查重方法
	 * 2016年1月5日
	 * By 刘燕军
	 * @param param
	 * @return 查重的结果
	 */
	public boolean  getInnerReapt(VerifyParamEx param){
		String resource = param.getCheckType();
		String loanCode = param.getLoanCode();
			boolean workRemark = true; // 单位名称查重标记
			boolean telRemark =  true; // 电话查重标记
			boolean addressRemark = true; // 地址查重标记
			int work =innerRepeatDao.insertInnerRepeats(loanCode,resource); // 获取单位名称的查重信息并插入查重表中
			telAuditWorkDao.insertWorkNameInfo(loanCode); // 把要查重的单位名称放入单位名称查重池中
			if(work>0){
				workRemark = false; // 有新的查重信息
				innerRepeatDao.insertRepeatHisName(loanCode,resource); // 把单位名称查重信息放入历史的借款信息中
			}
			// 电话查重 具体步骤同单位名称
			int tel =innerRepeatDao.insertInnerRepeatsByTel(loanCode,resource); // 获取电话的查重信息并插入查重表中
			telAuditWorkDao.insertTelInfo(loanCode); // 把要查重的电话放入单位名称查重池中
			if(tel>0){
				telRemark = false; // 有新的查重信息
				innerRepeatDao.insertInnerRepeatHisTel(loanCode,resource); // 把电话查重信息放入历史的借款信息中
			}
			insertRepeatInfokDao.updateTelnum(loanCode); // 把对应的字段更新为 已查重状态
			insertRepeatInfokDao.updateTelContact(loanCode); // 把对应的字段更新为 已查重状态
			insertRepeatInfokDao.updateMyTel(loanCode); // 把对应的字段更新为 已查重状态
			insertRepeatInfokDao.updateMyFamilyTel(loanCode); // 把对应的字段更新为 已查重状态
			int address =innerRepeatDao.insertInnerRepeatsByAddress(loanCode,resource); // 获取单位地址的查重信息并插入查重表中
			telAuditWorkDao.insertAddressInfo(loanCode); // 把要查重的单位地址放入单位名称查重池中
			if(address>0){
				addressRemark = false; // 有新的查重信息
				innerRepeatDao.insertInnerRepeatHisAddress(loanCode,resource); // 把单位地址查重信息放入历史的借款信息中
			}
			insertRepeatInfokDao.updateWork(loanCode); // 把对应的字段更新为 已查重状态
			insertRepeatInfokDao.updateMyFamilyAddress(loanCode);
			if(workRemark&&telRemark&&addressRemark){//正常 无查重信息 可以显示决策页面
				return false;
			}else{// 有新的查重信息 需要重新进入内网审核
				return true;
			}
		}
}
