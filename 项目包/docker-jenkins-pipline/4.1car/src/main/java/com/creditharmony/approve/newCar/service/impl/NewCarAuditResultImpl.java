package com.creditharmony.approve.newCar.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.dao.LoanStatusHisDao;
import com.creditharmony.approve.carloan.entity.LoanStatusHis;
import com.creditharmony.approve.carloan.util.CarPreUpdateUtil;
import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.newCar.dao.NewCarAuditResultDao;
import com.creditharmony.approve.newCar.dao.NewCarLoanInfoDao;
import com.creditharmony.approve.newCar.entity.NewCarAuditResult;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.approve.newCar.service.NewCarAuditResultService;
import com.creditharmony.approve.newCar.util.NewCarPreUpdateUtil;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.loan.type.CarLoanDeadline;
import com.creditharmony.core.loan.type.CarLoanProductType;
import com.creditharmony.core.loan.type.CarLoanResult;
import com.creditharmony.core.loan.type.CarLoanStatus;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.loan.type.CarLoanThroughFlag;

@Service
public class NewCarAuditResultImpl implements NewCarAuditResultService {

	@Autowired
	private NewCarAuditResultDao carAuditResultDao;
	@Autowired
	private LoanStatusHisDao loanStatusHisDao;
	@Autowired
	private NewCarLoanInfoDao carLoanInfoDao;
	
	@Override
	public List<NewCarAuditResult> findCarAuditResultsByLoanCode(String loanCode) {
		return carAuditResultDao.findCarAuditResultsByLoanCode(loanCode);
	}

	@Override
	public void insertCarAuditResult(NewCarAuditResult auditResult) {
		carAuditResultDao.insertCarAuditResult(auditResult);
		
	}

	@Override
	public NewCarAuditResult selectLastByLoanCodeAndCheckType(String loanCode,
			String checkType) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("loanCode", loanCode);
		map.put("checkType", checkType);
		return carAuditResultDao.selectLastByLoanCodeAndCheckType(map);
	}

	/**
	 * 车借--审批保存以及退回保存：更新借款信息表、借款状态变更历史表、插入审核结果表
	 * 2016年1月28日
	 * By 申诗阔
	 * @param flowView 审批所需字段 实体
	 * @param stepName 审批步骤名称
	 * @return Map集合 用于后期相关处理逻辑
	 */
	@Override
	public Map<String, String> auditHandle(CarVerifyBusinessView flowView,
			String stepName) {
	
			String loanCode = flowView.getLoanCode();
			String auditResult = flowView.getAuditResult();			//审核结果
			String loanStatus = flowView.getDictStatus();
			String operResultName = flowView.getOperResultName();
			// 审批步骤
			String stepNode = CarLoanSteps.parseByName(stepName).getCode();
			// 通过工具类把借款状态变更历史信息封装
			LoanStatusHis loanStatusHis = CarPreUpdateUtil.updateStatusChangeRecord(loanCode, loanStatus, stepNode, operResultName);
			loanStatusHisDao.insertLoanStatusHis(loanStatusHis);
			
			String hisId = loanStatusHis.getId();
			// 获取审批数据
			NewCarAuditResult carAuditResult = new NewCarAuditResult();
			Object json = flowView.getJson();
			// 将审批数据封装入审核结果实体
			carAuditResult.preInsert();
			carAuditResult.setrStatusHisId(hisId);
			carAuditResult.setLoanCode(loanCode);
			String loanFlag = null;
			if (json == null) {	// 若回退为null,则获取审批金额等字段
				carAuditResult.setAuditAmount(flowView.getAuditAmount());					//设置 审批金额
				carAuditResult.setDictProductType(flowView.getAuditBorrowProductCode());	//设置审批产品类型code
				carAuditResult.setDictAuditMonths(flowView.getAuditLoanMonths());			//审批期限
				carAuditResult.setGrossRate(flowView.getGrossRate());						//总费率
				carAuditResult.setFirstServiceTariffing(flowView.getFirstServiceTariffing());//首期服务费率
				carAuditResult.setFinalEvaluatedPrice(flowView.getFinalEvaluatedPrice());	//终审评估价格
				carAuditResult.setAuditCheckExamine(flowView.getAuditCheckExamine());		//审批意见
				carAuditResult.setDictCheckType(flowView.getAuditType());					//审核类型 （初审、复审、终审）
				carAuditResult.setAuditResult(auditResult);									//审核结果
				carAuditResult.setIndustry(flowView.getIndustry());							//行业
				carAuditResult.setOwnEstate(flowView.getOwnEstate());						//有无房产
				carAuditResult.setWhetherLocal(flowView.getWhetherLocal());					//是否本地
				carAuditResult.setWorkNature(flowView.getWorkNature());						//工作性质
				carAuditResult.setCreditStatus(flowView.getCreditStatus());					//信用状况
				carAuditResult.setOutVisitDistance(flowView.getOutVisitDistance());			//外访距离
				if (CarLoanSteps.FINAL_AUDIT.getCode().equals(flowView.getAuditType())) {
					if (CarLoanResult.THROUGH.getCode().equals(auditResult)) {
						loanFlag = CarLoanThroughFlag.HARMONY.getCode();
					} else if (CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(auditResult)) {
						loanFlag = CarLoanThroughFlag.CONDITIONAL.getCode();
					}
				}
			} else {
				carAuditResult.setAuditCheckExamine(flowView.getAuditCheckExamine());		// 退回原因
				carAuditResult.setAuditJson(json.toString());								//JSON
				carAuditResult.setReturnType(flowView.getFirstJson());						//退回类型（补传资料，追加共借人，修改数据，其它）	
				carAuditResult.setDictCheckType(flowView.getAuditType());					//审核类型 （初审、复审、终审）
				carAuditResult.setAuditResult(CarLoanResult.BACK.getCode());				//审核结果  退回		
			}
			// 保存审核结果
			carAuditResultDao.insertCarAuditResult(carAuditResult);
			
			if (flowView.getIsWait() != null) { // 若是待定，则把借款状态置空，即不更新
				loanStatus = null;
			}
			// 若终审决策不是附条件通过，则把借款信息表中附条件通过标识字段置空
			if (!CarLoanSteps.FINAL_AUDIT.getCode().equals(flowView.getAuditType()) || !CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(auditResult)) {
				auditResult = null;
			}
			// 通过工具类把需要变更的借款信息封装
			NewCarLoanInfo info = NewCarPreUpdateUtil.UpdateLoan(loanCode, loanStatus, auditResult, loanFlag);
			if(null!=flowView.getAuditLoanMonths()&&!"".equals(flowView.getAuditLoanMonths())&&Integer.parseInt(flowView.getAuditLoanMonths())>0){
				info.setLoanMonths(Short.parseShort(flowView.getAuditLoanMonths()));
			}
			//更新首期服务费率
			if(null!=flowView.getFirstServiceTariffing()&&!"".equals(flowView.getFirstServiceTariffing())){
				info.setFirstServiceTariffingRate(new BigDecimal(flowView.getFirstServiceTariffing()));
			}
			
			if(StringUtils.isNotEmpty(flowView.getContractVersion()) 
					&& !"1.4".equals(flowView.getContractVersion()))
			{
				//如果审批产品类型是GPS，更新设备使用费，计算规则：设备使用费=100元*审批期数(30天/期)
				if(null != flowView.getAuditBorrowProductCode() && CarLoanProductType.GPS.getCode().equals(flowView.getAuditBorrowProductCode())){
					NewCarLoanInfo carInfo = carLoanInfoDao.findByLoanCode(info.getLoanCode());
					 //展期不更新设备使用费
					if(!"1".equals(carInfo.getIsextension())){
						//不是结清再结
						if(! "1".equals(carInfo.getDictSettleRelend())){
							Integer months = Integer.parseInt(flowView.getAuditLoanMonths());
							Integer thirty = new Integer(CarLoanDeadline.THIRTY.getName());
							Double deviceUsedFee = (double) (100 * (months/thirty));
							info.setDeviceUsedFee(deviceUsedFee);
						}
					}
				}	
			}
			//将借款标识置空(终审取单条件)
			info.setDictLoanFlag("");
			carLoanInfoDao.update(info);
			Map<String, String> map = new HashMap<String, String>();
			map.put("his", hisId);
			map.put("auditId", carAuditResult.getId());
			return map;
		}

	@Override
	public Map<String, String> auditHandleFinal(CarVerifyBusinessView flowView,
			String stepName) {
		
		String loanCode = flowView.getLoanCode();
		String auditResult = flowView.getAuditResult();			//审核结果
		String loanStatus = flowView.getDictStatus();
		String operResultName = flowView.getOperResultName();
		// 审批步骤
		String stepNode = CarLoanSteps.FINAL_AUDIT.getCode();
		// 通过工具类把借款状态变更历史信息封装
		LoanStatusHis loanStatusHis = CarPreUpdateUtil.updateStatusChangeRecord(loanCode, loanStatus, stepNode, operResultName);
		loanStatusHisDao.insertLoanStatusHis(loanStatusHis);
		
		String hisId = loanStatusHis.getId();
		// 获取审批数据
		NewCarAuditResult carAuditResult = new NewCarAuditResult();
		Object json = flowView.getJson();
		// 将审批数据封装入审核结果实体
		carAuditResult.preInsert();
		carAuditResult.setrStatusHisId(hisId);
		carAuditResult.setLoanCode(loanCode);
		String loanFlag = null;
		if (json == null) {	// 若回退为null,则获取审批金额等字段
			carAuditResult.setAuditAmount(flowView.getAuditAmount());					//设置 审批金额
			carAuditResult.setDictProductType(flowView.getAuditBorrowProductCode());	//设置审批产品类型code
			carAuditResult.setDictAuditMonths(flowView.getAuditLoanMonths());			//审批期限
			carAuditResult.setGrossRate(flowView.getGrossRate());						//总费率
			carAuditResult.setFirstServiceTariffing(flowView.getFirstServiceTariffing());//首期服务费率
			carAuditResult.setFinalEvaluatedPrice(flowView.getFinalEvaluatedPrice());	//终审评估价格
			carAuditResult.setAuditCheckExamine(flowView.getAuditCheckExamine());		//审批意见
			carAuditResult.setDictCheckType(flowView.getAuditType());					//审核类型 （初审、复审、终审）
			carAuditResult.setAuditResult(auditResult);									//审核结果
			carAuditResult.setIndustry(flowView.getIndustry());							//行业
			carAuditResult.setOwnEstate(flowView.getOwnEstate());						//有无房产
			carAuditResult.setWhetherLocal(flowView.getWhetherLocal());					//是否本地
			carAuditResult.setWorkNature(flowView.getWorkNature());						//工作性质
			carAuditResult.setCreditStatus(flowView.getCreditStatus());					//信用状况
			carAuditResult.setOutVisitDistance(flowView.getOutVisitDistance());			//外访距离
			if (CarLoanSteps.FINAL_AUDIT.getCode().equals(flowView.getAuditType())) {
				if (CarLoanResult.THROUGH.getCode().equals(auditResult)) {
					loanFlag = CarLoanThroughFlag.HARMONY.getCode();
				} else if (CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(auditResult)) {
					loanFlag = CarLoanThroughFlag.CONDITIONAL.getCode();
				}
			}
		} else {
			carAuditResult.setAuditCheckExamine(flowView.getAuditCheckExamine());		// 退回原因
			carAuditResult.setAuditJson(json.toString());								//JSON
			carAuditResult.setReturnType(flowView.getFirstJson());						//退回类型（补传资料，追加共借人，修改数据，其它）	
			carAuditResult.setDictCheckType(flowView.getAuditType());					//审核类型 （初审、复审、终审）
			carAuditResult.setAuditResult(CarLoanResult.BACK.getCode());				//审核结果  退回		
		}
		// 保存审核结果
		carAuditResultDao.insertCarAuditResult(carAuditResult);
		
		if (flowView.getIsWait() != null) { // 若是待定，则把借款状态置空，即不更新
			loanStatus = null;
		}
		// 若终审决策不是附条件通过，则把借款信息表中附条件通过标识字段置空
		if (!CarLoanSteps.FINAL_AUDIT.getCode().equals(flowView.getAuditType()) || !CarLoanResult.CONDITIONAL_THROUGH.getCode().equals(auditResult)) {
			auditResult = null;
		}
		// 通过工具类把需要变更的借款信息封装
		NewCarLoanInfo info = NewCarPreUpdateUtil.UpdateLoan(loanCode, loanStatus, auditResult, loanFlag);
		if(null!=flowView.getAuditLoanMonths()&&!"".equals(flowView.getAuditLoanMonths())&&Integer.parseInt(flowView.getAuditLoanMonths())>0){
			info.setLoanMonths(Short.parseShort(flowView.getAuditLoanMonths()));
		}
		//更新首期服务费率
		if(null!=flowView.getFirstServiceTariffing()&&!"".equals(flowView.getFirstServiceTariffing())){
			info.setFirstServiceTariffingRate(new BigDecimal(flowView.getFirstServiceTariffing()));
		}
		NewCarLoanInfo carInfo = carLoanInfoDao.findByLoanCode(info.getLoanCode());
		
		if(StringUtils.isNotEmpty(flowView.getContractVersion()) 
				&& !"1.4".equals(flowView.getContractVersion()))
		{
			//如果审批产品类型是GPS，更新设备使用费，计算规则：设备使用费=100元*审批期数(30天/期)
			if(null != flowView.getAuditBorrowProductCode() && CarLoanProductType.GPS.getCode().equals(flowView.getAuditBorrowProductCode())){
				//NewCarLoanInfo carInfo = carLoanInfoDao.findByLoanCode(info.getLoanCode());
				 //展期不更新设备使用费
				if(!"1".equals(carInfo.getIsextension())){
					//不是结清再结
					//if(! "1".equals(carInfo.getDictSettleRelend())){
						Integer months = Integer.parseInt(flowView.getAuditLoanMonths());
						Integer thirty = new Integer(CarLoanDeadline.THIRTY.getName());
						Double deviceUsedFee = (double) (100 * (months/thirty));
						info.setDeviceUsedFee(deviceUsedFee);
					//}
				}
			}	
		}
		// 更新队列名称
		if("1".equals(carInfo.getIsextension())){
			
			info.setQueueName("HJ_CAR_EXTEND_CONTRACT_RATECHECK");
		}else if(CarLoanStatus.SUPPLY_PENDING_FINAL_AUDIT.getCode().equals(carInfo.getDictLoanStatus())){
			info.setQueueName("HJ_CAR_CONTRACT_CHECK");
		}
		else{
			info.setQueueName("HJ_CAR_RATE_CHECK");
		}
		info.setDictLoanFlag("");
		carLoanInfoDao.update(info);
		Map<String, String> map = new HashMap<String, String>();
		map.put("his", hisId);
		map.put("auditId", carAuditResult.getId());
		return map;
	}
}
