package com.creditharmony.approve.base.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.antifraud.dao.AntifraudJudgeDao;
import com.creditharmony.approve.antifraud.dao.AntifraudReportDao;
import com.creditharmony.approve.antifraud.entity.AntifraudJudge;
import com.creditharmony.approve.antifraud.entity.AntifraudReport;
import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.constants.KinnobuQuotaLConstant;
import com.creditharmony.approve.common.constants.NumberConstants;
import com.creditharmony.approve.common.dao.AuditBackDao;
import com.creditharmony.approve.common.dao.BackConsultDao;
import com.creditharmony.approve.common.dao.CustomerAbandonDao;
import com.creditharmony.approve.common.entity.AuditBack;
import com.creditharmony.approve.common.entity.BackConsult;
import com.creditharmony.approve.common.entity.CustomerAbandon;
import com.creditharmony.approve.management.dao.GlGiveupDao;
import com.creditharmony.approve.management.dao.GlRefuseDao;
import com.creditharmony.approve.outvisit.dao.OutSideCheckListDao;
import com.creditharmony.approve.outvisit.dao.OutsideCheckInfoDao;
import com.creditharmony.approve.outvisit.entity.OutsideCheckInfo;
import com.creditharmony.approve.outvisit.entity.ex.OutsideCheckListEx;
import com.creditharmony.approve.rule.channelconfig.client.ChannelconfigRuleSoapFault;
import com.creditharmony.approve.rule.channelconfig.service.ChannelConfigService;
import com.creditharmony.approve.verify.dao.AuditRecordDao;
import com.creditharmony.approve.verify.dao.AuditResultDao;
import com.creditharmony.approve.verify.dao.AuditResultSublistDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.dao.QuotaLimitDao;
import com.creditharmony.approve.verify.dao.ReconsiderApplyDao;
import com.creditharmony.approve.verify.dao.StatusChangeRecordDao;
import com.creditharmony.approve.verify.dao.TrusteeshipQuotaLimitDao;
import com.creditharmony.approve.verify.entity.AuditRecord;
import com.creditharmony.approve.verify.entity.AuditResultSublist;
import com.creditharmony.approve.verify.entity.LoanInfo;
import com.creditharmony.approve.verify.entity.QuotaLimit;
import com.creditharmony.approve.verify.entity.ReconsiderApply;
import com.creditharmony.approve.verify.entity.StatusChangeRecord;
import com.creditharmony.approve.verify.entity.TrusteeshipQuotaLimit;
import com.creditharmony.approve.verify.entity.ex.AuditResultEx;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.BlackGreyList;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.approve.type.CheckType;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.loan.type.ChannelFlag;
import com.creditharmony.core.loan.type.LoanApplyStatus;
import com.creditharmony.core.loan.type.LoanModel;
import com.creditharmony.core.loan.type.RejectDepartment;
import com.creditharmony.core.type.ModuleName;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 工作流service 共用部分 
 * @Class Name WorkFlowCommonService
 * @author 刘燕军
 * @Create In 2015年12月22日
 */
@Service
public class ApproveCommonService extends BaseService{
	@Autowired
	private StatusChangeRecordDao changeRecordDao;
	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private AntifraudJudgeDao antifraudJudgeDao;
	@Autowired
	private ReconsiderApplyDao reconsiderApplyDao;
	@Autowired
	private AuditRecordDao auditRecordDao;
	@Autowired
	private AuditResultDao auditResultDao;
	@Autowired
	private AuditResultSublistDao auditResultSublistDao;
	@Autowired
	private CustomerAbandonDao customerAbandonDao;
	@Autowired
	private AntifraudReportDao antifraudReportDao;
	@Autowired
	private GlRefuseDao refuseDao;
	@Autowired
	private GlGiveupDao giveUpDao;
	@Autowired
	private BackConsultDao backConsultDao;
	@Autowired
	private OutSideCheckListDao checkListDao;
	@Autowired
	private AuditBackDao auditBackDao;
	@Autowired
	private OutsideCheckInfoDao checkInfoDao;
	@Autowired
	private QuotaLimitDao quotaLimitDao;
	@Autowired
	ChannelConfigService channelConfigService;
	@Autowired
	TrusteeshipQuotaLimitDao trusteeshipQuotaLimitDao;
	
	/**
	 * 更新借款信息表和变更历史表
	 * 2016年2月25日
	 * By 刘燕军
	 * @param param 参数对象
	 * @return  对应id封装的map
	 */
	public Map<String, String> beforeWorkFlow(VerifyBusinessView param) {
		String historyId = insertHistory(param);
		String rercordId=  insertAuditRecord(param);
		updateLoan(param,rercordId);	
		Map<String, String> map = new HashMap<String, String>();
		map.put("his", historyId);
		map.put("change",rercordId );
		return map;
	}
	
	/**
	 * 进件引擎调用，更新借款信息表与状态历史表、审批记录表
	 * 2016年3月28日
	 * By 王浩
	 * @param param
	 * return none
	 */
	public Map<String, String> updateStatusHisAndInfo(VerifyBusinessView param) {
		String historyId = insertHistory(param);
		String recordId = insertAuditRecord(param);

		LoanInfo info = new LoanInfo();	
		// 进件引擎通过，不更新借款信息状态
		// 进件引擎退回和进件引擎拒借，更新借款信息状态
		//if (!LoanApplyStatus.ANTIFRAUD_ENGINE.getName().equals(param.getLoanApplyStatus())) {
			info.setDictLoanStatus(LoanApplyStatus.parseByName(param.getLoanApplyStatus()).getCode());
	//	}	
		if (LoanApplyStatus.APPLY_ENGINE_REFUSE.getName().equals(param.getLoanApplyStatus())) {
			info.setLoanAuditTime(new Date());
		}		
		// 把借款编号放入对象中
		info.setLoanCode(param.getLoanCode());
		info.setApproveResult(param.getCheckResult());
		info.setApproveStep(CheckType.parseByName(param.getStepName()).getCode());
		info.setResultId(param.getResultId());
		info.setRecordId(recordId);
		// 获取变更基本信息
		info.preUpdate();
		loanInfoDao.updateByPrimaryKeySelective(info);		
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("his", historyId);
		map.put("change", recordId);
		return map;
	}
	
	/**
	 * 信息插入到反欺诈判定表
	 * 2015年12月25日
	 * By 刘燕军
	 * @param antifraudReport
	 * @param source
	 */
	public void insertAntifraudJudge(AntifraudReport antifraudReport,String source){
		AntifraudJudge  antifraudJudge = new AntifraudJudge();
		antifraudJudge.setrReportId(antifraudReport.getId());
		antifraudJudge.setLoanCode(antifraudReport.getLoanCode());
		antifraudJudge.setDictIntoSource(source);
		antifraudJudge.setDictCaseResult(BlackGreyList.UNPROCESSED.getCode());
		antifraudJudge.preInsert();
		antifraudJudgeDao.insertJudge(antifraudJudge);
	}
	
	/**
	 * 更新复议申请表
	 * 2015年12月29日
	 * By 刘燕军
	 * @param loanCode
	 * @param dictCheckStatus
	 */
	public void updateReconsiderApply(String loanCode,String dictCheckStatus){
		ReconsiderApply reconsiderApply = new ReconsiderApply();
		reconsiderApply.setLoanCode(loanCode);
		reconsiderApply.setDictCheckStatus(dictCheckStatus);
		reconsiderApply.preUpdate();
		reconsiderApplyDao.updateReconsiderApply(reconsiderApply);
	}
	
	/**
	 * 更新复议申请表-外访专用
	 * 2015年12月29日
	 * By 赖敏
	 * @param loanCode
	 * @param dictCheckStatus
	 * @param outsideFlag 外访标识
	 */
	public void updateReconsiderApply(String loanCode,String dictCheckStatus,String outsideFlag){
		ReconsiderApply reconsiderApply = new ReconsiderApply();
		reconsiderApply.setOutsideFlag(outsideFlag);
		reconsiderApply.setLoanCode(loanCode);
		reconsiderApply.setDictCheckStatus(dictCheckStatus);
		reconsiderApply.preUpdate();
		reconsiderApplyDao.updateReconsiderApply(reconsiderApply);
	}
	
	/**
	 * 
	 * 2015年12月28日
	 * By 刘燕军
	 * @param flowView
	 * @param step
	 */
	protected String fromCheck(VerifyBusinessView flowView){	 // 初审拒借/ 通过/拟拒借 到复审	 
		String loanCode = flowView.getLoanCode();
		String applyId = flowView.getApplyId();
		AuditResultEx resultParam =  flowView.getAuditResultEx();
		CustomerAbandon customerAbandon = flowView.getCustomerAbandon();
		if(!StringUtils.isBlank(loanCode) && !StringUtils.isBlank(applyId)){
			if(resultParam != null){	
				// 获取id
				resultParam.preInsert();
				List<AuditResultSublist> resultSubs = resultParam.getSubResult();
				flowView.setResultId(resultParam.getId());
				LoanApplyStatus.parseByName(flowView.getLoanApplyStatus()).getCode();
							resultParam.setApplyId(applyId);
							resultParam.setLoanCode(loanCode);
							resultParam.setStepName(CheckType.parseByName(flowView.getStepName()).getCode());
							resultParam.setDictCheckType(flowView.getCheckType());			
							String subId = resultParam.getId();
							String remark = "";
							if(ArrayHelper.isNotEmpty(resultSubs)){
								Set<String> set = new HashSet<String>();
								for (AuditResultSublist auditResultSublist : resultSubs) {
									auditResultSublist.setRAuditId(subId);
									auditResultSublist.preInsert();
									auditResultSublistDao.insertSelective(auditResultSublist);
									set.add(auditResultSublist.getRefuseFirstCode());
								}
								for (String string : set) {
									if(StringUtils.isEmpty(remark)){
										remark = refuseDao.findName(string);
									}else{
										remark =remark +"-"+ refuseDao.findName(string);
									}
									
								}
							}
				flowView.setResultRemark(remark);
				Map<String, String> map = beforeWorkFlow(flowView);
				// 把关联id插入到提报申请表中去
				resultParam.setRStatusHisId(map.get("his"));
				resultParam.setSingleTastId(map.get("change"));
				// 插入数据库中
				//最优自然人保证人ID,不选的时候为空				
				resultParam.setBestCoborrowerId(flowView.getAuditResultEx().getBestCoborrowerId());								
				auditResultDao.insertByEx(resultParam);	
				return map.get("change"); // 变更记录的id
			}else if(customerAbandon != null){
				String remark = giveUpDao.getNameByCode(customerAbandon.getAbandonFirstCode());
				remark = remark +"," +giveUpDao.getNameByCode(customerAbandon.getAbandonSecondCode());
				remark = remark + "," + customerAbandon.getAbandonRemark();
				flowView.setResultRemark(remark);
				flowView.setResultId("----");
				Map<String, String> map = beforeWorkFlow(flowView);
				customerAbandon.setrStatusHisId(map.get("his"));
				customerAbandon.setDictCheckType(flowView.getCheckType());
				customerAbandon.setLoanCode(loanCode);
				customerAbandon.preInsert();
				customerAbandonDao.insertSelective(customerAbandon);
				return map.get("change"); // 变更记录的id
			}
		}
		return null;
	}
	
	/**
	 * 提报反欺诈
	 * 2015年12月30日
	 * By 刘燕军
	 * @param flowView
	 * @param step
	 */
	protected void toAntifraud(VerifyBusinessView  flowView){	// 提报反欺诈	
		String loanCode = flowView.getLoanCode();
		String applyId = flowView.getApplyId();
		AntifraudReport antifraudReport = flowView.getAntifraudReport();
		if(!StringUtils.isBlank(loanCode) && !StringUtils.isBlank(applyId)){
			antifraudReport.setLoanCode(loanCode);
			antifraudReport.setDictCheckType(flowView.getCheckType());
			flowView.setResultId("----");
			Map<String, String> map = beforeWorkFlow(flowView);
					// 把变更历史的id插入到提报申请表中去
					antifraudReport.setrStatusHisId(map.get("his"));
					// 获取id
					antifraudReport.preInsert();
					// 插入数据库中
					antifraudReportDao.insertSelective(antifraudReport);	
					insertAntifraudJudge(antifraudReport,flowView.getCheckType());
		}		
	}
	
	/**
	 * 把对应的不需要更新的字段置为空
	 * 2016年2月25日
	 * By 刘燕军
	 * @param flowView
	 */
	protected void toNull(VerifyBusinessView  flowView){
		AuditResultEx auditResultEx = flowView.getAuditResultEx();
		auditResultEx.setProductType(null);
		auditResultEx.setAuditMonths(null);
		flowView.setAuditAmount(null);
	}
	
	/**
	 * 非通过，结束流程时，更新数据
	 * 2016年2月25日
	 * By 刘燕军
	 * @param id 审核记录的id
	 * @param loanCode 借款编号
	 */
	protected void finalUpdate(String id,String loanCode){
		LoanInfo info = new LoanInfo();
		info.setAuditId(id);
		info.setLoanCode(loanCode);
		info.setLoanAuditTime(new Date());
		info.preUpdate();
		loanInfoDao.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 通过，结束流程时，更新数据
	 * 2016年2月25日
	 * By 刘燕军
	 * @param id
	 * @param flowView
	 */
	protected void finalUpdate(String id,VerifyBusinessView flowView,LoanInfo kinInfo){
		AuditResultEx result = flowView.getAuditResultEx();
		LoanInfo info = new LoanInfo();
		info.setAuditId(id);
		info.setLoanCode(result.getLoanCode());
		info.setLoanAuditMoney(result.getAuditAmount());
		
		if(kinInfo != null && StringUtils.isNotEmpty(kinInfo.getKinnobuQuotaFlag())){
			info.setKinnobuQuotaFlag(kinInfo.getKinnobuQuotaFlag());// 金信flag(页面报message用)
		}
		if(kinInfo != null && StringUtils.isNotEmpty(kinInfo.getModel())){
			info.setModel(kinInfo.getModel());// 模型(模式)
		}
		if(kinInfo != null && StringUtils.isNotEmpty(kinInfo.getLoanFlag())){
			info.setLoanFlag(kinInfo.getLoanFlag());// 出借标识（渠道）
		}
		if(kinInfo != null && StringUtils.isNotEmpty(kinInfo.getUsingFlag())){
			info.setUsingFlag(kinInfo.getUsingFlag());
		}
		
		Date date = result.getModifyTime();
		if(date == null){
			info.setLoanAuditTime(result.getCreateTime());
		}else{
			info.setLoanAuditTime(result.getModifyTime());
		}
		info.setLoanAuditProduct(result.getProductType());
		info.setLoanAuditMonths(result.getAuditMonths());
		info.preUpdate();
		loanInfoDao.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 
	 * 2016年5月10日
	 * By 刘燕军
	 * @param flowView
	 * @return 一级客户放弃码
	 */
	protected void getAbandon(VerifyBusinessView  flowView){
		if(StringUtils.isNotEmpty(flowView.getLoanCode())){// 如果借款编号不为空
			List<String> list = customerAbandonDao.findAbandonList(flowView);
			if(ArrayHelper.isNotEmpty(list)){
				String remark = list.get(0);
				// 把相同的一级拒借码排除
				for(int i=1;i<list.size();i++){
					boolean flag = true;
					for(int j=0;j<i;j++){
						if(list.get(i).equals(list.get(j))){
							flag = false;
							break;
						}
					}
					if(flag){
						remark = remark + "-" + list.get(i);
					}
				}
				flowView.setResultRemark(remark);
			}
		}
	}
	
	/**
	 * 通过拒借码获取对应的汉字
	 * 2016年2月26日
	 * By 刘燕军
	 * @param flowView
	 * @return 拒借码对应的名字
	 */
	protected String getReason(VerifyBusinessView  flowView){
		List<AuditResultSublist>  subResult = flowView.getAuditResultEx().getSubResult();
		List<String> refuses = new ArrayList<String>();
		if(ArrayHelper.isNotEmpty(subResult)){ // 获取所有的一级拒借码
			for (AuditResultSublist auditResultSublist : subResult) {
				refuses.add(auditResultSublist.getRefuseFirstCode());
			}
		}
		String refuse = "";
		if(refuses.size()>=1){ // 至少有一个拒借码 获取第一个一级拒借码
			refuse = refuseDao.findName(refuses.get(0));
		}	
		// 把相同的一级拒借码排除
		for(int i=1;i<refuses.size();i++){
			boolean remark = true;
			for(int j=0;j<i;j++){
				if(refuses.get(i).equals(refuses.get(j))){
					remark = false;
					break;
				}
			}
			if(remark){
				refuse = refuse + "-" + refuseDao.findName(refuses.get(i));
			}
		}
		return refuse;
	}
	
	/**
	 * 查询是否为利率审核退回的单子 2016年10月25日
	 * 
	 * @return
	 * @throws ChannelconfigRuleSoapFault
	 */
	public LoanInfo checkKinnobu(VerifyBusinessView flowView) {
		Double money = flowView.getAuditAmount();
		LoanInfo info = new LoanInfo();
		LoanInfo loanInfo = loanInfoDao.getByLoanCode(flowView.getLoanCode());
		if (loanInfo != null) {
			// 获取kinflg字段
			String kinFlg = loanInfo.getKinnobuQuotaFlag();
			// 如果kinflg为空说明：这个单子不是从审核利率过来的
			if (StringUtils.isEmpty(kinFlg)) {
				String productType = flowView.getAuditResultEx().getProductType();
				// 如果申请产品类型为“信易借”，则默认只能是“P2P”或“资产家”标识
				if ("A013".equals(productType) || "信易借".equals(productType)) {
					String notTG = this.checkKinnobusXyj();
					// 如果返回值为 RESULT_UNUSE 说明上限表未设置上限
					if (!KinnobuQuotaLConstant.RESULT_UNUSE_FLG.equals(notTG)) {
						if (KinnobuQuotaLConstant.ZCJFLAG.equals(notTG)) {
							info.setKinnobuQuotaFlag(notTG);
							info.setModel(LoanModel.CHP.getCode());
							info.setLoanFlag(NumberConstants.FIVE_STRING);
							info.setLoanFlagName(ChannelFlag.ZCJ.getName());
						} else {
							info.setKinnobuQuotaFlag(notTG);
							info.setModel(LoanModel.CHP.getCode());
							info.setLoanFlag(NumberConstants.ONE_STRING);
							info.setLoanFlagName(ChannelFlag.P2P.getName());
						}
					} else {
						info.setKinnobuQuotaFlag(KinnobuQuotaLConstant.CHPLAG);
						info.setModel(LoanModel.CHP.getCode());
						info.setLoanFlag(NumberConstants.ONE_STRING);
						info.setLoanFlagName(ChannelFlag.P2P.getName());
						info.setUsingFlag(NumberConstants.ONE_STRING);// 是否启用金信标识
					}
				} else {
					// 调用方法，根据返回结果判断是否为TG("true":TG "false":非TG)
					String isTG = channelConfigService.isChannelTG(flowView.getApplyId(), flowView.getLoanCode());
					// TG标识
					if (BooleanType.TRUE.equals(isTG)) {
						TrusteeshipQuotaLimit trusteeshipQuotaLimit = new TrusteeshipQuotaLimit();
						trusteeshipQuotaLimit.setTrusteeshipUsingFlag(new BigDecimal(NumberConstants.ZERO_STRING));
						TrusteeshipQuotaLimit useTG = trusteeshipQuotaLimitDao.findTG(trusteeshipQuotaLimit);
						if (useTG != null) {
							BigDecimal audMoney = new BigDecimal(money);// 批复额度
							BigDecimal limitMoney = useTG.getTrusteeshipQuotaLimit();
							BigDecimal hasUseMoney = useTG.getUseMoney();
							BigDecimal useSum = audMoney.add(hasUseMoney);
							if (useSum.compareTo(limitMoney) == -1 || useSum.compareTo(limitMoney) == 0) {
								TrusteeshipQuotaLimit param = new TrusteeshipQuotaLimit();
								param.setId(useTG.getId());
								param.setVersion(useTG.getVersion());
								param.setUseMoney(useSum);
								param.setTrusteeshipQuotaLimit(limitMoney);
								int updateNum = trusteeshipQuotaLimitDao.updateTG(param);
								if (updateNum < 1) {
									throw new ServiceException("更新TG上限表失败：loanCode=" + flowView.getLoanCode());
								}
								info.setKinnobuQuotaFlag(KinnobuQuotaLConstant.TG);// kinflag=3(TG余额足)
								info.setModel(LoanModel.TG.getCode());// 1
								info.setLoanFlag(NumberConstants.TWO_STRING);
								info.setLoanFlagName(ChannelFlag.CHP.getName());
							} else {
								info.setKinnobuQuotaFlag(KinnobuQuotaLConstant.TGNONE);// kinflag=4(TG余额不足)
								info.setModel(LoanModel.CHP.getCode());// 0
								info.setLoanFlag(NumberConstants.TWO_STRING);
								info.setLoanFlagName(ChannelFlag.CHP.getName());
							}
						} else {
							// TG没有启用的数据，默认标识财富（chp）
							// info.setKinnobuQuotaFlag(KinnobuQuotaLConstant.CHPLAG);//
							// kinflag = 4(TG余额不足)
							// info.setModel(LoanModel.CHP.getCode());// 0
							// info.setLoanFlag(NumberConstants.TWO_STRING);
							// info.setLoanFlagName(ChannelFlag.CHP.getName());
							// info.setUsingFlag(NumberConstants.ONE_STRING);
							String notTG = this.checkKinnobus();
							// 如果返回值为 RESULT_UNUSE 说明上限表未设置上限
							if (!KinnobuQuotaLConstant.RESULT_UNUSE_FLG.equals(notTG)) {
								if (KinnobuQuotaLConstant.KINFLAG.equals(notTG)) {
									info.setKinnobuQuotaFlag(notTG);
									info.setModel(LoanModel.CHP.getCode());
									info.setLoanFlag(NumberConstants.ZERO_STRING);
									info.setLoanFlagName(ChannelFlag.JINXIN.getName());
								} else if (KinnobuQuotaLConstant.P2PFLAG.equals(notTG)) {
									info.setKinnobuQuotaFlag(notTG);
									info.setModel(LoanModel.CHP.getCode());
									info.setLoanFlag(NumberConstants.ONE_STRING);
									info.setLoanFlagName(ChannelFlag.P2P.getName());
								} else if (KinnobuQuotaLConstant.ZCJFLAG.equals(notTG)) {
									info.setKinnobuQuotaFlag(notTG);
									info.setModel(LoanModel.CHP.getCode());
									info.setLoanFlag(NumberConstants.FIVE_STRING);
									info.setLoanFlagName(ChannelFlag.ZCJ.getName());
								} else {
									info.setKinnobuQuotaFlag(notTG);
									info.setModel(LoanModel.CHP.getCode());
									info.setLoanFlag(NumberConstants.TWO_STRING);
									info.setLoanFlagName(ChannelFlag.CHP.getName());
								}
							} else {
								info.setKinnobuQuotaFlag(KinnobuQuotaLConstant.CHPLAG);
								info.setModel(LoanModel.CHP.getCode());
								info.setLoanFlag(NumberConstants.TWO_STRING);
								info.setLoanFlagName(ChannelFlag.CHP.getName());
								info.setUsingFlag(NumberConstants.ONE_STRING);
							}
						}
					}
					// 非TG标识
					if (BooleanType.FALSE.equals(isTG)) {
						String notTG = this.checkKinnobus();
						// 如果返回值为 RESULT_UNUSE 说明上限表未设置上限
						if (!KinnobuQuotaLConstant.RESULT_UNUSE_FLG.equals(notTG)) {
							if (KinnobuQuotaLConstant.KINFLAG.equals(notTG)) {
								info.setKinnobuQuotaFlag(notTG);
								info.setModel(LoanModel.CHP.getCode());// 0
								info.setLoanFlag(NumberConstants.ZERO_STRING);
								info.setLoanFlagName(ChannelFlag.JINXIN.getName());
							} else if (KinnobuQuotaLConstant.P2PFLAG.equals(notTG)) {
								info.setKinnobuQuotaFlag(notTG);
								info.setModel(LoanModel.CHP.getCode());
								info.setLoanFlag(NumberConstants.ONE_STRING);
								info.setLoanFlagName(ChannelFlag.P2P.getName());
							} else if (KinnobuQuotaLConstant.ZCJFLAG.equals(notTG)) {
								info.setKinnobuQuotaFlag(notTG);
								info.setModel(LoanModel.CHP.getCode());// 0
								info.setLoanFlag(NumberConstants.FIVE_STRING);
								info.setLoanFlagName(ChannelFlag.ZCJ.getName());
							} else {
								info.setKinnobuQuotaFlag(notTG);
								info.setModel(LoanModel.CHP.getCode());// 0
								info.setLoanFlag(NumberConstants.TWO_STRING);
								info.setLoanFlagName(ChannelFlag.CHP.getName());
							}
						} else {
							info.setKinnobuQuotaFlag(KinnobuQuotaLConstant.CHPLAG);
							info.setModel(LoanModel.CHP.getCode());// 0
							info.setLoanFlag(NumberConstants.TWO_STRING);
							info.setLoanFlagName(ChannelFlag.CHP.getName());
							info.setUsingFlag(NumberConstants.ONE_STRING);
						}
					}
				}
			} else {
				info.setKinnobuQuotaFlag(kinFlg);
				// 如果是审核利率退过来的单子，并且kinFlg为2，则返回chp标识（这样页面就不会报数量不足了），
				if (KinnobuQuotaLConstant.NONE.equals(kinFlg) || KinnobuQuotaLConstant.TGNONE.equals(kinFlg)) {
					info.setKinnobuQuotaFlag(KinnobuQuotaLConstant.CHPLAG);
				}
			}
		}
		return info;
	}

	/**
	 * ************************************** 按照上限1，2，3，4，5，6，7，8，9，10 的顺序进行标识 *
	 * 财富    1 5 9  13 17 * 金信    2 6 10 14 18 *
	 * P2P 3 7 11 15 19 * ZCJ 4 8 12 16 20 *
	 * ************************************** 对比财富、金信、P2P和ZCJ剩余次数 2016年10月31日
	 * 
	 * @param flowView
	 */
	public String checkKinnobus() {
		String resultFlg = KinnobuQuotaLConstant.NONE;// 设置标识为：剩余数量全部为0
		QuotaLimit quotaLimitResult = quotaLimitDao.getDataNew(Integer.parseInt(KinnobuQuotaLConstant.KINNOBU_USE_FLG));
		QuotaLimit quotaLimitResultCopy = quotaLimitDao.getData(new BigDecimal(KinnobuQuotaLConstant.KINNOBU_USE_FLG));
		if (quotaLimitResult != null) {
			int ch1 = quotaLimitResult.getChp1(); // ch1上限
			int ch2 = quotaLimitResult.getChp2(); // ch2上限
			int ch3 = quotaLimitResult.getChp3(); // ch3上限
			int ch4 = quotaLimitResult.getChp4(); // ch4上限
			int ch5 = quotaLimitResult.getChp5(); // ch5上限
			int chpResidual = quotaLimitResult.getChpResidual(); // chp剩余数量
			int kin1 = quotaLimitResult.getGoldCredit1(); // 金信上限数量1
			int kin2 = quotaLimitResult.getGoldCredit2(); // 金信上限数量2
			int kin3 = quotaLimitResult.getGoldCredit3(); // 金信上限数量3
			int kin4 = quotaLimitResult.getGoldCredit4(); // 金信上限数量4
			int kin5 = quotaLimitResult.getGoldCredit5(); // 金信上限数量5
			int goldCreditResidual = quotaLimitResult.getGoldCreditResidual(); // 金信剩余数量
			int p2p1 = quotaLimitResult.getP2p1(); // P2P上限数量1
			int p2p2 = quotaLimitResult.getP2p2(); // P2P上限数量2
			int p2p3 = quotaLimitResult.getP2p3(); // P2P上限数量3
			int p2p4 = quotaLimitResult.getP2p4(); // P2P上限数量4
			int p2p5 = quotaLimitResult.getP2p5(); // P2P上限数量5
			int p2pResidual = quotaLimitResult.getP2pResidual(); // P2P剩余数量
			int zcj1 = quotaLimitResult.getZcj1(); // 资产家上限数量1
			int zcj2 = quotaLimitResult.getZcj2(); // 资产家上限数量2
			int zcj3 = quotaLimitResult.getZcj3(); // 资产家上限数量3
			int zcj4 = quotaLimitResult.getZcj4(); // 资产家上限数量4
			int zcj5 = quotaLimitResult.getZcj5(); // 资产家上限数量5
			int zcjResidual = quotaLimitResult.getZcjResidual(); // 资产家剩余数量
			int posit = quotaLimitResult.getPosit();// 当前位置
			int positNum = quotaLimitResult.getPositNum();// 当前位置数量
			int positXyj = quotaLimitResult.getPositXyj();// 信易借当前位置
			int positNumXyj = 0;// 信易借当前位置剩余上限数量
			Integer y = quotaLimitResult.getPositNumXyj();// 信易借当前位置剩余上限数量
			if (y != null) {
				positNumXyj = y;
			}
			int[] slsxArray = new int[] { ch1, kin1, p2p1, zcj1, ch2, kin2, p2p2, zcj2, ch3, kin3, p2p3, zcj3, ch4,
					kin4, p2p4, zcj4, ch5, kin5, p2p5, zcj5 };
			if (posit > 20) {
				resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
			} else {
				QuotaLimit param = new QuotaLimit();
				QuotaLimit paramCopy = new QuotaLimit();
				param.setId(quotaLimitResultCopy.getId());// 设置ID
				param.setVersion(quotaLimitResultCopy.getVersion());// 设置version
				paramCopy.setId(quotaLimitResult.getId());// 设置ID
				paramCopy.setVersion(quotaLimitResult.getVersion());// 设置version
				for (int i = posit; i < 21; i++) {
					if (positNum > 0) {
						if (i % 4 == 1) {
							resultFlg = KinnobuQuotaLConstant.CHPLAG;// chp标识
							param.setChpResidual(chpResidual - 1);// chp剩余数量减一
							param.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setChpResidual(chpResidual - 1);// chp剩余数量减一
							paramCopy.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
						} else if (i % 4 == 2) {
							resultFlg = KinnobuQuotaLConstant.KINFLAG;// 金信标识
							param.setChpResidual(chpResidual);// chp剩余数量不变
							param.setGoldCreditResidual(goldCreditResidual - 1);// 金信剩余数量减一
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setChpResidual(chpResidual);// chp剩余数量不变
							paramCopy.setGoldCreditResidual(goldCreditResidual - 1);// 金信剩余数量减一
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
						} else if (i % 4 == 3) {
							resultFlg = KinnobuQuotaLConstant.P2PFLAG;// P2P标识
							param.setChpResidual(chpResidual);// chp剩余数量不变
							param.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							param.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setChpResidual(chpResidual);// chp剩余数量不变
							paramCopy.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							paramCopy.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							if (i == 3) {
								paramCopy.setP2p1(p2p1 - 1);
								if (positXyj == 1) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 7) {
								paramCopy.setP2p2(p2p2 - 1);
								if (positXyj == 3) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 11) {
								paramCopy.setP2p3(p2p3 - 1);
								if (positXyj == 5) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 15) {
								paramCopy.setP2p4(p2p4 - 1);
								if (positXyj == 7) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 19) {
								paramCopy.setP2p5(p2p5 - 1);
								if (positXyj == 9) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							}
						} else {
							resultFlg = KinnobuQuotaLConstant.ZCJFLAG;// 资产家标识
							param.setChpResidual(chpResidual);// chp剩余数量不变
							param.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							paramCopy.setChpResidual(chpResidual);// chp剩余数量不变
							paramCopy.setGoldCreditResidual(goldCreditResidual);// 金信剩余数量不变
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							if (i == 4) {
								paramCopy.setZcj1(zcj1 - 1);
								if (positXyj == 2) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 8) {
								paramCopy.setZcj2(zcj2 - 1);
								if (positXyj == 4) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 12) {
								paramCopy.setZcj3(zcj3 - 1);
								if (positXyj == 6) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 16) {
								paramCopy.setZcj4(zcj4 - 1);
								if (positXyj == 8) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							} else if (i == 20) {
								paramCopy.setZcj5(zcj5 - 1);
								if (positXyj == 10) {
									param.setPositNumXyj(positNumXyj - 1);
									paramCopy.setPositNumXyj(positNumXyj - 1);
								}
							}
						}
						param.setPosit(i);// 当前位置坐标
						param.setPositNum(positNum - 1);// 当前位置剩余数量减一
						paramCopy.setPosit(i);// 当前位置坐标
						paramCopy.setPositNum(positNum - 1);// 当前位置剩余数量减一
						break;
					} else {
						if (i < 20) {
							positNum = slsxArray[i];// 取下一位置的剩余数量
							continue;
						} else {
							resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
							param.setPosit(i + 1);// 当前位置坐标
							param.setChpResidual(null);// chp剩余数量为零
							param.setGoldCreditResidual(null);// 金信剩余数量为零
							param.setP2pResidual(null);// P2P剩余数量为零
							param.setZcjResidual(null);// 资产家剩余数量为零
							paramCopy.setPosit(i + 1);// 当前位置坐标
							paramCopy.setChpResidual(null);// chp剩余数量为零
							paramCopy.setGoldCreditResidual(null);// 金信剩余数量为零
							paramCopy.setP2pResidual(null);// P2P剩余数量为零
							paramCopy.setZcjResidual(null);// 资产家剩余数量为零
							break;
						}
					}
				}
				param.preUpdate();
				paramCopy.preUpdate();
				int updateNum = quotaLimitDao.updateData(param);
				int updateNumCopy = quotaLimitDao.updateDataNew(paramCopy);
				if (updateNum == 0 || updateNumCopy == 0) {
					throw new ServiceException("更新渠道标识上限标识表失败！");
				}
			}
		} else {
			resultFlg = KinnobuQuotaLConstant.RESULT_UNUSE_FLG;// 未启用渠道标识
		}
		return resultFlg;
	}

	/**
	 * ************************************** 按照上限1，2，3，4，5，6，7，8，9，10 的顺序进行标识 *
	 * P2P 1 3 5 7 9 * ZCJ 2 4 6 8 10 * **************************************
	 * 对比P2P和资产家剩余次数 2016年10月25日
	 * 
	 * @param flowView
	 */
	public String checkKinnobusXyj() {
		String resultFlg = KinnobuQuotaLConstant.NONE;// 设置标识为：剩余数量全部为0
		QuotaLimit quotaLimitResult = quotaLimitDao.getDataNew(Integer.parseInt(KinnobuQuotaLConstant.KINNOBU_USE_FLG));
		QuotaLimit quotaLimitResultCopy = quotaLimitDao.getData(new BigDecimal(KinnobuQuotaLConstant.KINNOBU_USE_FLG));
		if (quotaLimitResult != null) {
			int p2p1 = quotaLimitResult.getP2p1(); // P2P上限数量1
			int p2p2 = quotaLimitResult.getP2p2(); // P2P上限数量2
			int p2p3 = quotaLimitResult.getP2p3(); // P2P上限数量3
			int p2p4 = quotaLimitResult.getP2p4(); // P2P上限数量4
			int p2p5 = quotaLimitResult.getP2p5(); // P2P上限数量5
			int p2pResidual = quotaLimitResult.getP2pResidual(); // P2P剩余数量
			int zcj1 = quotaLimitResult.getZcj1(); // 资产家上限数量1
			int zcj2 = quotaLimitResult.getZcj2(); // 资产家上限数量2
			int zcj3 = quotaLimitResult.getZcj3(); // 资产家上限数量3
			int zcj4 = quotaLimitResult.getZcj4(); // 资产家上限数量4
			int zcj5 = quotaLimitResult.getZcj5(); // 资产家上限数量5
			int zcjResidual = quotaLimitResult.getZcjResidual(); // 资产家剩余数量
			int positXyj = quotaLimitResult.getPositXyj();// 信易借当前位置
			int positNumXyj = 0;// 信易借当前位置剩余上限数量
			Integer x = quotaLimitResult.getPositNumXyj();// 信易借当前位置剩余上限数量
			if (x != null) {
				positNumXyj = x;
			}
			int posit = quotaLimitResult.getPosit();// 当前位置
			int positNum = quotaLimitResult.getPositNum();// 当前位置数量
			int[] slsxArray = new int[] { p2p1, zcj1, p2p2, zcj2, p2p3, zcj3, p2p4, zcj4, p2p5, zcj5 };
			if (positXyj > 10) {
				resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
			} else {
				QuotaLimit param = new QuotaLimit();
				QuotaLimit paramCopy = new QuotaLimit();
				param.setId(quotaLimitResultCopy.getId());// 设置ID
				param.setVersion(quotaLimitResultCopy.getVersion());// 设置version
				paramCopy.setId(quotaLimitResult.getId());// 设置ID
				paramCopy.setVersion(quotaLimitResult.getVersion());// 设置version
				for (int i = positXyj; i < 11; i++) {
					if (positNumXyj > 0) {
						if (i % 2 == 1) {
							resultFlg = KinnobuQuotaLConstant.P2PFLAG;// P2P标识
							param.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							param.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							paramCopy.setP2pResidual(p2pResidual - 1);// P2P剩余数量减一
							paramCopy.setZcjResidual(zcjResidual);// 资产家剩余数量不变
							if (i == 1) {
								paramCopy.setP2p1(p2p1 - 1);
								if (posit == 3) {
									param.setPosit(3);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(3);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 3) {
								paramCopy.setP2p2(p2p2 - 1);
								if (posit == 7) {
									param.setPosit(7);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(7);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 5) {
								paramCopy.setP2p3(p2p3 - 1);
								if (posit == 11) {
									param.setPosit(11);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(11);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 7) {
								paramCopy.setP2p4(p2p4 - 1);
								if (posit == 15) {
									param.setPosit(15);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(15);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 9) {
								paramCopy.setP2p5(p2p5 - 1);
								if (posit == 19) {
									param.setPosit(19);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(19);
									paramCopy.setPositNum(positNum - 1);
								}
							}
						} else {
							resultFlg = KinnobuQuotaLConstant.ZCJFLAG;// 资产家标识
							param.setP2pResidual(p2pResidual);// P2P剩余数量不变
							param.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							paramCopy.setP2pResidual(p2pResidual);// P2P剩余数量不变
							paramCopy.setZcjResidual(zcjResidual - 1);// 资产家剩余数量减一
							if (i == 2) {
								paramCopy.setZcj1(zcj1 - 1);
								if (posit == 4) {
									param.setPosit(4);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(4);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 4) {
								paramCopy.setZcj2(zcj2 - 1);
								if (posit == 8) {
									param.setPosit(8);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(8);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 6) {
								paramCopy.setZcj3(zcj3 - 1);
								if (posit == 12) {
									param.setPosit(12);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(12);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 8) {
								paramCopy.setZcj4(zcj4 - 1);
								if (posit == 16) {
									param.setPosit(16);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(16);
									paramCopy.setPositNum(positNum - 1);
								}
							} else if (i == 10) {
								paramCopy.setZcj5(zcj5 - 1);
								if (posit == 20) {
									param.setPosit(20);
									param.setPositNum(positNum - 1);
									paramCopy.setPosit(20);
									paramCopy.setPositNum(positNum - 1);
								}
							}
						}
						param.setPositXyj(i);// 当前位置坐标
						param.setPositNumXyj(positNumXyj - 1);// 当前位置剩余数量减一
						paramCopy.setPositXyj(i);// 当前位置坐标
						paramCopy.setPositNumXyj(positNumXyj - 1);// 当前位置剩余数量减一
						break;
					} else {
						if (i < 10) {
							positNumXyj = slsxArray[i];// 取下一位置的剩余数量
							continue;
						} else {
							resultFlg = KinnobuQuotaLConstant.NONE;// 数量为零
							param.setPositXyj(i + 1);// 当前位置坐标
							param.setP2pResidual(null);// P2P剩余数量为零
							param.setZcjResidual(null);// 资产家剩余数量为零
							paramCopy.setPositXyj(i + 1);// 当前位置坐标
							paramCopy.setP2pResidual(null);// P2P剩余数量为零
							paramCopy.setZcjResidual(null);// 资产家剩余数量为零
							break;
						}
					}
				}
				param.preUpdate();
				paramCopy.preUpdate();
				int updateNum = quotaLimitDao.updateData(param);
				int updateNumCopy = quotaLimitDao.updateDataNew(paramCopy);
				if (updateNum == 0 || updateNumCopy == 0) {
					throw new ServiceException("更新信易借产品渠道标识上限标识表失败！");
				}
			}
		} else {
			resultFlg = KinnobuQuotaLConstant.RESULT_UNUSE_FLG;// 未启用渠道标识
		}
		return resultFlg;
	}
	
	/**
	 * 复议审核结束后，更新复议审批时间
	 * 2016年3月24日
	 * By 刘燕军
	 * @param loanCode
	 */
	public void updateLoanTime(String loanCode){
		LoanInfo info = new LoanInfo();
		info.setLoanCode(loanCode);
		info.setLoanSecondFinishtime(new Date());
		info.preUpdate();
		loanInfoDao.updateByPrimaryKeySelective(info);
	}
	
	/**
	 * 插入借款状态变更记录表
	 * 2016年4月21日
	 * By 刘燕军
	 * @param param
	 * @return 插入信息的id
	 */
	private String insertHistory(VerifyBusinessView param) {
		StatusChangeRecord  record = new StatusChangeRecord();
		User user = UserUtils.getUser();
		record.setApplyId(param.getApplyId());
		// 设置系统类型(系统机构名称)
		// 进件引擎退回或拒绝，信审复议人员最终拒绝 ，将系统标识修改为汇金
		if (param.getLoanApplyStatus().equals(LoanApplyStatus.APPLY_ENGINE_BACK.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.APPLY_ENGINE_REFUSE.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.CUSTOMER_GIVEUP.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.RECHECK_REJECT.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.GROUP_CHECK_REJECT.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.FINAL_CHECK_REJECT.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.RECONSIDER_RECHECK_REJECT.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.RECONSIDER_FINAL_CHECK_REJECT.getName())
				|| param.getLoanApplyStatus().equals(LoanApplyStatus.DECSISON_ENGINE_REJECT.getName()) && param.getStepName().equals(CheckType.DECSISON_ENGINE_AUDIT.getName())
				) {
			record.setDictSysFlag(ModuleName.MODULE_LOAN.value);
		} else {
			record.setDictSysFlag(ModuleName.MODULE_APPROVE.value);
		}		
		// 设置借款编号
		record.setLoanCode(param.getLoanCode());
		// 设置借款状态
		record.setDictLoanStatus(LoanApplyStatus.parseByName(param.getLoanApplyStatus()).getCode());
		// 设置操作步骤 
		record.setOperateStep(param.getStepName());
		// 设置操作结果  汉字
		record.setOperateResult(CheckResult.parseByCode(param.getCheckResult()).getName());
		// 设置备注
		record.setRemark(param.getResultRemark());
		// 设置操作人		
		record.setOperator(StringUtils.isNotEmpty(user.getName()) ? user.getName() : "系统");
		// 设置操作人角色 user.getRole().getId()
		record.setOperaterRoleId(StringUtils.isNotEmpty(user.getId())?user.getId() : "SYS");
		// 设置结构编码			
		record.setOrgCode(user.getDepartment() != null && StringUtils.isNotEmpty(user.getDepartment().getId()) 
				? user.getDepartment().getId() : "");
		// 设置操作时间
		record.setOperateTime(new Date());
		// 获取变更历史的id
		record.preInsert();
		// 把变更历史插入数据库
		changeRecordDao.insert(record);
		return record.getId();
	}
	
	/**
	 * 插入审核记录表
	 * 2016年4月21日
	 * By 刘燕军
	 * @param param
	 * @return 记录表id
	 */
	private String insertAuditRecord(VerifyBusinessView param){
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		if (null == user) {
			user = new User();
		}
		AuditRecord auditRecord = new AuditRecord();
		// 设置审核步骤
		auditRecord.setOperateStep(CheckType.parseByName(param.getStepName()).getCode());
		// 设置借款编号
		auditRecord.setLoanCode(param.getLoanCode());
		// 获取更新相关信息
		auditRecord.setTransactorTime(new Date());	
		// 设置借款状态
		auditRecord.setDictLoanStatus(LoanApplyStatus.parseByName(param.getLoanApplyStatus()).getCode());
		// 设置操作结果
		auditRecord.setOperateResult(param.getCheckResult());
		// 设置拒绝原因
		auditRecord.setRefuseReason(param.getRefuseReason());
		// 设置操作人角色 user.getRole().getId()
		auditRecord.setTransactorCode(StringUtils.isNotEmpty(user.getId())?user.getId() : "SYS");
		// 设置结构编码
		auditRecord.setOrgCode(user.getDepartment() != null && StringUtils.isNotEmpty(user.getDepartment().getId()) 
				? user.getDepartment().getId() : "");		
		String relId = auditRecordDao.findId(StringUtils.isNotEmpty(user.getId()) ? user.getId() : "SYS", param.getLoanCode());
		// 如果已有记录则更新，否则，插入
		if (StringUtils.isEmpty(relId)) {
			auditRecord.preInsert();
			auditRecordDao.insertAuditRecord(auditRecord);
		} else {
			auditRecord.preUpdate();
			auditRecord.setId(relId);
			auditRecordDao.update(auditRecord);
		}
		return auditRecord.getId();
	}
	
	/**
	 * 更新借款信息表
	 * 2016年4月21日
	 * By 刘燕军
	 * @param param
	 * @param recordId
	 */
	private void updateLoan(VerifyBusinessView param,String recordId) {
		LoanInfo info = new LoanInfo();	
		// 更新借款信息表中的借款状态（如果是门店放弃或门店拒绝，则不更新借款信息表中的状态）其他的都去更新借款信息表中的状态
		String vistFlag = loanInfoDao.getVistFlag(param.getLoanCode());
		if(!RejectDepartment.LOAN_GIVE.getCode().equals(vistFlag) && !RejectDepartment.LOAN_REJECT.getCode().equals(vistFlag)){
			info.setDictLoanStatus(LoanApplyStatus.parseByName(param.getLoanApplyStatus()).getCode());
		}
		String checkType = param.getCheckType();
		String loanStatus = param.getLoanStatusCode();
		if(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode().equals(checkType)){
			if(LoanApplyStatus.FRAUD_AFFIRMED_BLACK.getCode().equals(loanStatus)
					|| LoanApplyStatus.FRAUD_AFFIRMED_GRAY.getCode().equals(loanStatus)){
				info.setLoanSecondFinishtime(new Date());
			}
		}else{
			if(LoanApplyStatus.FRAUD_AFFIRMED_BLACK.getCode().equals(loanStatus)
					|| LoanApplyStatus.FRAUD_AFFIRMED_GRAY.getCode().equals(loanStatus)
					|| LoanApplyStatus.APPLY_ENGINE_REFUSE.getCode().equals(loanStatus)){
				info.setLoanAuditTime(new Date());
			}
		}

		// 把借款编号放入对象中
		info.setLoanCode(param.getLoanCode());
		info.setApproveResult(param.getCheckResult());
		info.setApproveStep(CheckType.parseByName(param.getStepName()).getCode());
		info.setResultId(StringUtils.isNotEmpty(param.getResultId())?param.getResultId():ApproveConstants.INEFFICIENCY_ID);
		info.setOutsideFlag(param.getOutsideFlag());
		info.setRecordId(recordId);
		// 获取变更基本信息
		info.preUpdate();
		loanInfoDao.updateByPrimaryKeySelective(info);
	}
	
	
	/**
	 * 复议终审协商待复审
	 * 2015年12月25日
	 * By 王立东
	 * @param flowView
	 * @param step
	 */
	public void toVerifyRecheck(VerifyBusinessView  flowView){
		BackConsult backConsult = flowView.getBackConsult();
		String loanCode = flowView.getLoanCode();
		// RECONSIDER_FINAL_CHECK_COUSULT_RECHECK("48", "复议终审协商待复审"),
		Map<String, String> map = beforeWorkFlow(flowView);
		backConsult.preInsert();
		backConsult.setLoanCode(loanCode);
		backConsult.setBackConsult(UserUtils.getUser().getUserCode());
		backConsult.setDictCheckType(flowView.getCheckType());
		backConsult.setBackStartTime(new Date());
		backConsult.setrHisId(map.get("his"));
		backConsult.setStepname(CheckType.parseByName(flowView.getStepName()).getCode());
		backConsultDao.addBackConsult(backConsult);			
	}
	
	/**
	 * 退回门店
	 * 2015年12月25日
	 * By 赖敏
	 * @param flowView
	 * @param step
	 */
	protected void toStore(VerifyBusinessView  flowView){
		String loanCode = flowView.getLoanCode();
		AuditBack auditBack = flowView.getAuditBack();
		// 保存业务数据
		Map<String, String> map = beforeWorkFlow(flowView);
		auditBack.setrId(map.get("his"));
		auditBack.setDictCheckType(flowView.getCheckType());
		auditBack.preInsert();
		auditBack.setLoanCode(loanCode);
		auditBack.setBackStartTime(auditBack.getCreateTime());
		auditBackDao.insertAuditBack(auditBack);
	}
	
	/**
	 * 发起外访
	 * 2015年12月25日
	 * By 赖敏
	 * @param flowView
	 * @param step
	 */
	protected void toVisit(VerifyBusinessView flowView){
		OutsideCheckListEx checkListEx = flowView.getCheckListEx();
		flowView.setResultId("----");
		Map<String, String> map = beforeWorkFlow(flowView);
		// 保存业务数据
		checkListEx.setrId(map.get("his"));
		checkListEx.preInsert();
		checkListEx.setLoanCode(flowView.getLoanCode());
		checkListEx.setSurveyStartTime(checkListEx.getCreateTime());
		checkListEx.setDictCheckType(flowView.getCheckType());
		// 保存外访任务清单
		checkListDao.insertSelective(checkListEx);
		OutsideCheckInfo checkInfo = checkListEx.getCheckInfos();
		if(checkInfo!=null){
			// 保存外访任务详情
			checkInfo.setTaskId(checkListEx.getId());
			checkInfo.preInsert();
			checkInfoDao.insertSelective(checkInfo);
		}
	}
}
