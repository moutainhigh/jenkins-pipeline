package com.creditharmony.approve.workflow.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.antifraud.constants.AntifraudTelCheck;
import com.creditharmony.approve.antifraud.dao.AntifraudJudgeDao;
import com.creditharmony.approve.antifraud.dao.FqzdhzhBrhsDhxxDao;
import com.creditharmony.approve.antifraud.entity.BacklistAll;
import com.creditharmony.approve.antifraud.entity.ex.AntiFraudJudgeEx;
import com.creditharmony.approve.base.service.ApproveCommonService;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.workflow.view.VerifyBusinessView;
import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.approve.type.AfraudListSource;
import com.creditharmony.core.approve.type.AfraudListType;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.approve.type.BlackGreyList;
import com.creditharmony.core.approve.type.CheckResult;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.loan.type.LoanApplyStatus;
import com.creditharmony.core.users.util.UserUtils;
import com.google.common.collect.Maps;

/**
 * 反欺诈决策工作流service
 * @Class Name antiFraudFlowService
 * @author wanglidong
 * @Create In 2015年12月24日
 */
@Service("Antifarud_Flow")
public class AntiFraudEvent extends ApproveCommonService implements ExEvent {

	@Autowired
	private AntifraudJudgeDao antifraudJudgeDao;
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	@Autowired
	private DhzhDhgxshDao dhzhDhgxshDao;
	@Autowired
	private FqzdhzhBrhsDhxxDao fqzdhzhBrhsDhxxDao;
	
	@Override
	public void invoke(WorkItemView workItem) {
		// 流程图路由
		// 取出在controller 中放入的业务数据，需要向下转换
		VerifyBusinessView  flowView = (VerifyBusinessView)workItem.getBv();
		flowView.setStepName(workItem.getStepName());
		saveAntiFraudJudge(flowView);
	}
	
	/**
	 * 保存反欺诈决策信息
	 * 2015年12月26日
	 * By wanglidong
	 * @param flowView 反欺诈工作流视图类
	 * @param step 流程步骤
	 */
	public void saveAntiFraudJudge(VerifyBusinessView  flowView) {
		AntiFraudJudgeEx antiFraudJudgeEx = flowView.getAntiFraudJudgeEx();
		antiFraudJudgeEx.preInsert();
		// 决策人
		antiFraudJudgeEx.setJudgeProcBy(UserUtils.getUser().getName());
		// 判定选择的是黑名单二级还是灰名单二级
		if(antiFraudJudgeEx.getTwoBlack() == null){
			antiFraudJudgeEx.setTwoBlack(antiFraudJudgeEx.getTwoGray());
		}
		// 获取借款编码
		String loanCode = flowView.getLoanCode();	
		Map<String, String> map = Maps.newHashMap();
		// 获取借款主表状态(触发反欺诈+进件引擎【拒】：5/触发反欺诈+进件引擎【过】：6)
		String loanStatus = antiFraudJudgeEx.getLoanStatus();
		// 如果判定为清白件+借款状态【不是】进件引擎拒
		if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.INNOCENT_CASE.getCode()) && !LoanApplyStatus.SYS_FILTER_REJECT_ANTIFRAUD.getCode().equals(loanStatus)){
			// PRELIMINARY_CHECK("14", "待初审")
			flowView.setLoanApplyStatus(LoanApplyStatus.PRELIMINARY_CHECK.getName());
			flowView.setCheckResult(CheckResult.FRAUDS_CREDIT_INNOCENCE.getCode());
			flowView.setLoanStatusCode(LoanApplyStatus.PRELIMINARY_CHECK.getCode());
			map = beforeWorkFlow(flowView);	
		// 如果判定为清白件+借款状态【是】进件引擎拒
		}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.INNOCENT_CASE.getCode()) && LoanApplyStatus.SYS_FILTER_REJECT_ANTIFRAUD.getCode().equals(loanStatus)){
			// SYS_FILTER_REJECT_ANTIFRAUD("5", "系统过滤（拒）待反欺诈","")
			flowView.setLoanApplyStatus(LoanApplyStatus.APPLY_ENGINE_REFUSE.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.APPLY_ENGINE_REFUSE.getCode());
			flowView.setCheckResult(CheckResult.FRAUDS_CREDIT_INNOCENCE.getCode());
			flowView.setRefuseReason(LoanApplyStatus.APPLY_ENGINE_REFUSE.getName());
			flowView.setFlag(true);
			map = beforeWorkFlow(flowView); 
		}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.BLACK_LIST.getCode())){
			// FRAUD_AFFIRMED_BLACK("7", "欺诈认定黑名单")
			flowView.setLoanApplyStatus(LoanApplyStatus.FRAUD_AFFIRMED_BLACK.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.FRAUD_AFFIRMED_BLACK.getCode());
			flowView.setCheckResult(CheckResult.FRAUDS_CREDIT_BOLDING.getCode());
			flowView.setRefuseReason(LoanApplyStatus.FRAUD_AFFIRMED_BLACK.getName());
			flowView.setFlag(true);
			map = beforeWorkFlow(flowView);		
		}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.GREY_LIST.getCode())){
			// FRAUD_AFFIRMED_GRAY("8", "欺诈认定灰名单")
			flowView.setLoanApplyStatus(LoanApplyStatus.FRAUD_AFFIRMED_GRAY.getName());
			flowView.setLoanStatusCode(LoanApplyStatus.FRAUD_AFFIRMED_GRAY.getCode());
			flowView.setCheckResult(CheckResult.FRAUDS_CREDIT_LIMING.getCode());
			flowView.setRefuseReason(LoanApplyStatus.FRAUD_AFFIRMED_GRAY.getName());
			flowView.setFlag(true);
			map = beforeWorkFlow(flowView);		
		}else if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.FALL_BACK.getCode())){
			// ANTIFRAUD_BACK_CHECK("12", "反欺诈退回待初审")
			String checkType = flowView.getCheckType();
			if(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode().equals(checkType)){
				flowView.setLoanApplyStatus(LoanApplyStatus.ANTIFRAUD_BACK_RECONSIDER_CHECK.getName());
				flowView.setLoanStatusCode(LoanApplyStatus.ANTIFRAUD_BACK_RECONSIDER_CHECK.getCode());
			}else{
				flowView.setLoanApplyStatus(LoanApplyStatus.ANTIFRAUD_BACK_CHECK.getName());
				flowView.setLoanStatusCode(LoanApplyStatus.ANTIFRAUD_BACK_CHECK.getCode());
			}

			flowView.setCheckResult(CheckResult.FRAUDS_CREDIT_BACK.getCode());
			map = beforeWorkFlow(flowView);		
		}		
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("loanCode",loanCode);
		params.put("checkType",ApproveCheckType.FQZ_APPROVE_CHECK_TYPE.getCode());
		params.put("editRemark",AntifraudTelCheck.UNEDITABLE);
		telAuditWorkDao.updateEditRemark(params);
		dhzhDhgxshDao.updateEditRemark(params);
		fqzdhzhBrhsDhxxDao.updateEditRemark(params);
		// 获取变更历史表id
		String hisId = map.get("his");
		antiFraudJudgeEx.setRelationId(hisId);
		// 更新反欺诈决策信息
		antifraudJudgeDao.updateJudge(antiFraudJudgeEx);	
		Map<String,Object> mapp = new HashMap<String,Object>();
		mapp.put("auditId", map.get("change"));
		mapp.put("loanCode", loanCode);
		// 更新借款信息表中的审核结果ID
		antifraudJudgeDao.updateLoanInfo(mapp);
		
		if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx() != null){
			List<BacklistAll> insideBlackGrayList = new ArrayList<BacklistAll>();
			BacklistAll blackGrayInfo = null;	
			// 如果一级决策项不是清白件或回退
			if(!antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.INNOCENT_CASE.getCode()) && !antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.FALL_BACK.getCode())){
				String markType = "";
				// BLACK_LIST("0","黑名单")
				if(antiFraudJudgeEx.getDictCaseResult().equals(BlackGreyList.BLACK_LIST.getCode())){
					markType = BlackGreyList.BLACK_LIST.getCode();
				}else{
					// GREY_LIST("1","灰名单"),
					markType = BlackGreyList.GREY_LIST.getCode();
				}
				
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerPhoneFirst() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);	
					// APPLICANT_CELL_PHONE_NUMBER("1","申请人手机号"),
					blackGrayInfo.setDictBlackType(AfraudListType.APPLICANT_CELL_PHONE_NUMBER_1.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerPhoneFirst());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerPhoneSecond() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// APPLICANT_CELL_PHONE_NUMBER_2("1","申请人手机号2"),
					blackGrayInfo.setDictBlackType(AfraudListType.APPLICANT_CELL_PHONE_NUMBER_2.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerPhoneSecond());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerCertNum() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);	
					// APPLICANT_ID_NUMBER("2","申请人证件号"),
					blackGrayInfo.setDictBlackType(AfraudListType.APPLICANT_ID_NUMBER.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerCertNum());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerTel() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// FIXED_TELEPHONE_NUMBER_OF_THE_APPLICANT("3","申请人固定电话"),
					blackGrayInfo.setDictBlackType(AfraudListType.FIXED_TELEPHONE_NUMBER_OF_THE_APPLICANT.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerTel());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerAddress() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// RESIDENTIAL_ADDRESS_OF_THE_APPLICANT("4","申请人住宅地址"),
					blackGrayInfo.setDictBlackType(AfraudListType.RESIDENTIAL_ADDRESS_OF_THE_APPLICANT.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCustomerAddress());
					insideBlackGrayList.add(blackGrayInfo);			
				}		
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCompName() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// UNIT_NAME("5","单位名称"),
					blackGrayInfo.setDictBlackType(AfraudListType.UNIT_NAME.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCompName());
					insideBlackGrayList.add(blackGrayInfo);			
				}		
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCompAddress() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// UNIT_ADDRESS("6","单位地址"),
					blackGrayInfo.setDictBlackType(AfraudListType.UNIT_ADDRESS.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCompAddress());
					insideBlackGrayList.add(blackGrayInfo);			
				}		
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCompTel() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// WORKPHONE("7","单位电话"),
					blackGrayInfo.setDictBlackType(AfraudListType.WORKPHONE.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getCompTel());
					insideBlackGrayList.add(blackGrayInfo);			
				}		
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getMateTel() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// SPOUSE_CELL_PHONE_NUMBER("8","配偶手机号"),
					blackGrayInfo.setDictBlackType(AfraudListType.SPOUSE_CELL_PHONE_NUMBER.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getMateTel());
					insideBlackGrayList.add(blackGrayInfo);			
				}		
				if(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getMateCertNum() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// SPOUSE_ID_NUMBER("9","配偶证件号"),
					blackGrayInfo.setDictBlackType(AfraudListType.SPOUSE_ID_NUMBER.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getMateCertNum());
					insideBlackGrayList.add(blackGrayInfo);			
				}		
				if(ArrayHelper.isNotEmpty(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getContactMobile())){
					for (int i = 0; i < antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getContactMobile().size(); i++) {
						blackGrayInfo = new BacklistAll();
						blackGrayInfo.preInsert();
						blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
						blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
						blackGrayInfo.setLoanCode(loanCode);
						blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
						blackGrayInfo.setDictMarkType(markType);
						// CONTACT_PHONE_NUMBER("10","联系人手机号")
						blackGrayInfo.setDictBlackType(AfraudListType.CONTACT_PHONE_NUMBER.getCode());
						blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getContactMobile().get(i));
						insideBlackGrayList.add(blackGrayInfo);
					}			
				}
				// 新版申请表add 联系人宅电
				if(ArrayHelper.isNotEmpty(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getContactHomeTel())){
					for (int i = 0; i < antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getContactHomeTel().size(); i++) {
						blackGrayInfo = new BacklistAll();
						blackGrayInfo.preInsert();
						blackGrayInfo.setLoanCustomterType(LoanManFlag.MAIN_LOAN.getCode());
						blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getId());
						blackGrayInfo.setLoanCode(loanCode);
						blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
						blackGrayInfo.setDictMarkType(markType);
						// TODO CONTACT_HOME_TEL("11","联系人宅电")
						blackGrayInfo.setDictBlackType(AfraudListType.CONTACT_HOME_TEL.getCode());
						blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getAntiFraudJudgeOptionEx().getContactHomeTel().get(i));
						insideBlackGrayList.add(blackGrayInfo);
					}			
				}
				insideBlackGrayList = addCoborrowerOption(insideBlackGrayList,loanCode, markType,antiFraudJudgeEx);
				if(insideBlackGrayList.size() > 0){
					antifraudJudgeDao.addBlackList(insideBlackGrayList);
				}
			}
		}
	}
	
	/**
	 * 保存共借人的加黑灰项
	 * 2016年2月20日
	 * By wanglidong
	 * @param insideBlackGrayList
	 * @param blackGrayInfo
	 * @param loanCode
	 * @param markType
	 * @param antiFraudJudgeEx
	 * @return
	 */
	public List<BacklistAll> addCoborrowerOption(List<BacklistAll> insideBlackGrayList,String loanCode,String markType,AntiFraudJudgeEx antiFraudJudgeEx) {
		if(ArrayHelper.isNotEmpty(antiFraudJudgeEx.getCoborrowerOptionList())){
			for (int a = 0; a < antiFraudJudgeEx.getCoborrowerOptionList().size(); a++) {
				BacklistAll blackGrayInfo = null;
				if(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboMobile() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.COBORROWE_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);	
					// APPLICANT_CELL_PHONE_NUMBER("1","申请人手机号"),
					blackGrayInfo.setDictBlackType(AfraudListType.APPLICANT_CELL_PHONE_NUMBER_1.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboMobile());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboMobile2() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.COBORROWE_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// APPLICANT_CELL_PHONE_NUMBER_2("1","申请人手机号2"),
					blackGrayInfo.setDictBlackType(AfraudListType.APPLICANT_CELL_PHONE_NUMBER_2.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboMobile2());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboCertNum() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.COBORROWE_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);	
					// APPLICANT_ID_NUMBER("2","申请人证件号"),
					blackGrayInfo.setDictBlackType(AfraudListType.APPLICANT_ID_NUMBER.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboCertNum());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboFamilyTel() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.COBORROWE_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// FIXED_TELEPHONE_NUMBER_OF_THE_APPLICANT("3","申请人固定电话"),
					blackGrayInfo.setDictBlackType(AfraudListType.FIXED_TELEPHONE_NUMBER_OF_THE_APPLICANT.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboFamilyTel());
					insideBlackGrayList.add(blackGrayInfo);			
				}
				if(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboNowAddress() != null){
					blackGrayInfo = new BacklistAll();
					blackGrayInfo.preInsert();
					blackGrayInfo.setLoanCustomterType(LoanManFlag.COBORROWE_LOAN.getCode());
					blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getId());
					blackGrayInfo.setLoanCode(loanCode);
					blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
					blackGrayInfo.setDictMarkType(markType);
					// RESIDENTIAL_ADDRESS_OF_THE_APPLICANT("4","申请人住宅地址"),
					blackGrayInfo.setDictBlackType(AfraudListType.RESIDENTIAL_ADDRESS_OF_THE_APPLICANT.getCode());
					blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getCoboNowAddress());
					insideBlackGrayList.add(blackGrayInfo);			
				}		
				if(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getContactMobile() != null){
					for (int i = 0; i < antiFraudJudgeEx.getCoborrowerOptionList().get(a).getContactMobile().size(); i++) {
						blackGrayInfo = new BacklistAll();
						blackGrayInfo.preInsert();
						blackGrayInfo.setLoanCustomterType(LoanManFlag.COBORROWE_LOAN.getCode());
						blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getId());
						blackGrayInfo.setLoanCode(loanCode);
						blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
						blackGrayInfo.setDictMarkType(markType);
						// CONTACT_PHONE_NUMBER("10","联系人手机号")
						blackGrayInfo.setDictBlackType(AfraudListType.CONTACT_PHONE_NUMBER.getCode());
						blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getContactMobile().get(i));
						insideBlackGrayList.add(blackGrayInfo);
					}			
				}
				// 新版申请表 add 联系人宅电
				if(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getContactHomeTel() != null){
					for (int i = 0; i < antiFraudJudgeEx.getCoborrowerOptionList().get(a).getContactHomeTel().size(); i++) {
						blackGrayInfo = new BacklistAll();
						blackGrayInfo.preInsert();
						blackGrayInfo.setLoanCustomterType(LoanManFlag.COBORROWE_LOAN.getCode());
						blackGrayInfo.setrCustomerCoborrowerId(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getId());
						blackGrayInfo.setLoanCode(loanCode);
						blackGrayInfo.setDictSource(AfraudListSource.INSIDE_PLUS_BLACK_GREY.getCode());
						blackGrayInfo.setDictMarkType(markType);
						// TODO CONTACT_HOME_TEL("11","联系人宅电")
						blackGrayInfo.setDictBlackType(AfraudListType.CONTACT_HOME_TEL.getCode());
						blackGrayInfo.setBlackMsg(antiFraudJudgeEx.getCoborrowerOptionList().get(a).getContactHomeTel().get(i));
						insideBlackGrayList.add(blackGrayInfo);
					}			
				}
			}	
		}	
		return insideBlackGrayList;	
	}
}
