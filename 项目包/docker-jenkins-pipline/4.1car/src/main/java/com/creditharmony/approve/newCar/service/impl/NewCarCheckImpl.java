package com.creditharmony.approve.newCar.service.impl;

import static com.creditharmony.approve.common.util.CryptoUtils.decryptPhones;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.dao.CarCompletedListDao;
import com.creditharmony.approve.carloan.dao.LoanStatusHisDao;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.LoanStatusHis;
import com.creditharmony.approve.carloan.entity.ex.CarVerifyListEx;
import com.creditharmony.approve.carloan.util.CarPreUpdateUtil;
import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.newCar.dao.NewCarAuditResultDao;
import com.creditharmony.approve.newCar.dao.NewCarCheckDao;
import com.creditharmony.approve.newCar.dao.NewCarLoanCoborrowerDao;
import com.creditharmony.approve.newCar.dao.NewCarLoanInfoDao;
import com.creditharmony.approve.newCar.dao.NewCustomerContactPersonDao;
import com.creditharmony.approve.newCar.dao.NewVehicleInfoDao;
import com.creditharmony.approve.newCar.entity.NewApplyDetailInfokEx;
import com.creditharmony.approve.newCar.entity.NewCarAuditResult;
import com.creditharmony.approve.newCar.entity.NewCarExamineEntity;
import com.creditharmony.approve.newCar.entity.NewCarLoanCoborrower;
import com.creditharmony.approve.newCar.entity.NewCarLoanExtendExtraEx;
import com.creditharmony.approve.newCar.entity.NewCarLoanInfo;
import com.creditharmony.approve.newCar.entity.NewCustomerContactPerson;
import com.creditharmony.approve.newCar.entity.NewVehicleInfo;
import com.creditharmony.approve.newCar.service.NewCarCheckService;
import com.creditharmony.approve.verify.dao.ApproveDictDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.ex.DictEx;
import com.creditharmony.approve.verify.entity.ex.LoanCustomerEx;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.loan.type.CarLoanDeadline;
import com.creditharmony.core.loan.type.CarLoanOperateResult;
import com.creditharmony.core.loan.type.CarLoanProductType;
import com.creditharmony.core.loan.type.CarLoanResult;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.loan.type.CarLoanThroughFlag;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;

@Service
public class NewCarCheckImpl implements NewCarCheckService {
	@Autowired
	private NewCarCheckDao newCarCheckDao;
	@Autowired
	private LoanStatusHisDao loanStatusHisDao;
	@Autowired
	private NewCarLoanInfoDao carLoanInfoDao;
	@Autowired
	private NewCarAuditResultDao carAuditResultDao;
	@Autowired
	private CarCompletedListDao carCompletedListDao;
	@Autowired
	private ApproveDictDao dictDao;
	@Autowired
	private NewVehicleInfoDao vehicleInfoDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private NewCustomerContactPersonDao customerContactPersonDao;
	@Autowired
	private NewCarLoanCoborrowerDao carLoanCoborrowerDao;
	
	@Override
	public Page<NewCarExamineEntity> getReCheckList(Page<NewCarExamineEntity> page,List<String> statusList) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<NewCarExamineEntity> pageList = (PageList<NewCarExamineEntity>) newCarCheckDao
				.getReCheckList(pageBounds, statusList);
		PageUtil.convertPage(pageList, page);		
		return page;
	}

	/**
	 * 处理复审、终审待定
	 * 2017年3月29日
	 * By 李高远
	 * @param workItem
	 */
	@Override
	public void waitHandle(CarVerifyBusinessView flowView) {
		String stepName = CarLoanSteps.RECHECK_AUDIT.getName();
		// 审批步骤
		String stepNode = CarLoanSteps.parseByName(stepName).getCode();
		if (CarLoanSteps.RECHECK_AUDIT.getCode().equals(stepNode)) {
			flowView.setOperResultName(CarLoanOperateResult.RECHECK_DETERMINED.getCode());
			flowView.setAuditType(CarLoanSteps.RECHECK_AUDIT.getCode());
		} else if (CarLoanSteps.FINAL_AUDIT.getCode().equals(stepNode)) {
			flowView.setOperResultName(CarLoanOperateResult.FINALCHECK_DETERMINED.getCode());
			flowView.setAuditType(CarLoanSteps.FINAL_AUDIT.getCode());
		} else {
			flowView.setOperResultName("");
			flowView.setAuditType("");
		}
		auditHandle(flowView, stepName);
	}
	
	public Map<String, String> auditHandle(CarVerifyBusinessView flowView, String stepName) {
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
		CarLoanInfo info = CarPreUpdateUtil.UpdateLoan(loanCode, loanStatus, auditResult, loanFlag);
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
		// 通过借款编号 更新借款信息表
		
		carLoanInfoDao.updateByLoanCode(info);
		Map<String, String> map = new HashMap<String, String>();
		map.put("his", hisId);
		map.put("auditId", carAuditResult.getId());
		return map;
	}

	@Override
	public Page<CarVerifyListEx> findPage(Page<CarVerifyListEx> page,
			Map<String, Object> map) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("apply_id");
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		PageList<CarVerifyListEx> pageList = carCompletedListDao.getCompletedListDoneList(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	@Override
	public int findCount(Map<String, Object> map) {
		List<CarVerifyListEx> list = carCompletedListDao.getCompletedListDoneList(map);
		return list.size();
	}

	@Override
	public List<DictEx> findAllBackCheckDicts(String type) {
		return dictDao.getDictsByType(type);
	}

	@Override
	public Page<NewCarExamineEntity> getOrderReCheckList(
			Page<NewCarExamineEntity> page, NewCarExamineEntity entity) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<NewCarExamineEntity> pageList = (PageList<NewCarExamineEntity>) newCarCheckDao
				.getOrderReCheckList(pageBounds, entity);
		PageUtil.convertPage(pageList, page);		
		return page;
	}

	@Override
	public Page<NewCarExamineEntity> getFinalCheckList(
			Page<NewCarExamineEntity> page, NewCarExamineEntity entity) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),page.getPageSize());
		PageList<NewCarExamineEntity> pageList = (PageList<NewCarExamineEntity>) newCarCheckDao
				.getFinalCheckList(pageBounds, entity);
		PageUtil.convertPage(pageList, page);		
		return page;
	}

	@Override
	public NewApplyDetailInfokEx getCheckInfo(String loanCode) {
		NewApplyDetailInfokEx applyDetailInfokEx = new NewApplyDetailInfokEx();

		// 此次借款申请信息，例如：车借，展期1，展期2，展期3，...，那么此记录为最晚的展期3的申请信息
		NewCarLoanInfo loanInfo = carLoanInfoDao.findByLoanCode(loanCode);
		loanInfo.setDictLoanStatus(DictCache.getInstance().getDictLabel("jk_car_loan_status", loanInfo.getDictLoanStatus()));
		if (YESNO.YES.getCode().equals(loanInfo.getIsextension())) { // 若是展期，则赋予展期
			String sLoanCode = loanInfo.getLoanRawcode(); // 起始借款编码
			NewCarLoanInfo sloanInfo = carLoanInfoDao.findByLoanCode(sLoanCode);
			applyDetailInfokEx.setLoanInfo(sloanInfo);
			applyDetailInfokEx.setExtendLoanInfo(loanInfo);
			
			NewCarLoanExtendExtraEx carLoanExtendExtraEx = carLoanInfoDao.getSumData(sLoanCode);
			NewVehicleInfo extVehicleInfo = vehicleInfoDao.findByLoanCode(loanCode);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loanCode", sLoanCode);
			map.put("checkType", CarLoanSteps.FINAL_AUDIT.getCode());
			List<String> codes = new ArrayList<String>();
			codes.add(CarLoanResult.THROUGH.getCode());
			codes.add(CarLoanResult.CONDITIONAL_THROUGH.getCode());
			map.put("throughCodes", codes);
			NewCarAuditResult carAuditResult = carAuditResultDao.getLastThroughRecord(map);
			if (carAuditResult != null) {
				carLoanExtendExtraEx.setGrossRate(carAuditResult.getGrossRate());
			}
			carLoanExtendExtraEx.setExtensionAssessAmount(extVehicleInfo.getExtensionAssessAmount());
			applyDetailInfokEx.setCarLoanExtendExtraEx(carLoanExtendExtraEx);
			
		} else {
			applyDetailInfokEx.setLoanInfo(loanInfo);
		}

		// 客户个人信息
		LoanCustomerEx loanCustomerEx = loanCustomerDao.findCustomerByLoanCode(loanCode);	// 客户信息
		applyDetailInfokEx.setLoanCustomerEx(loanCustomerEx);
		loanCustomerEx.setCustomerPhoneFirst(decryptPhones(loanCustomerEx.getCustomerPhoneFirst(),"T_JK_LOAN_CUSTOMER","customer_phone_first"));

		Map<String, String> conMap = new HashMap<String, String>();
		conMap.put("loanCode", loanCode);
		conMap.put("loanType", LoanManFlag.MAIN_LOAN.getCode());
		// 主借人联系人信息
		List<NewCustomerContactPerson> customerContactPersons = customerContactPersonDao
				.findByLoanCodeAndType(conMap);
		applyDetailInfokEx.setCustomerContactPersons(customerContactPersons);

		// 车辆信息
		NewVehicleInfo vehicleInfo = vehicleInfoDao.findByLoanCode(loanCode);
		applyDetailInfokEx.setVehicleInfo(vehicleInfo);
		
		Map<String, String> coboMap = new HashMap<String, String>();
		coboMap.put("loanCode", loanCode);
		coboMap.put("loanType", LoanManFlag.COBORROWE_LOAN.getCode());
		// 客户共借人、以及共借人联系人
		List<NewCarLoanCoborrower> coBorrowers = carLoanCoborrowerDao
				.selectByLoanCodeAndLoanType(coboMap);
		applyDetailInfokEx.setCoborrowers(coBorrowers);

		return applyDetailInfokEx;
	}


}
