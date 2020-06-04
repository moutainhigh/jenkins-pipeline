package com.creditharmony.approve.carloan.service;

import static com.creditharmony.approve.common.util.CryptoUtils.decryptPhones;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.base.service.CarCommonService;
import com.creditharmony.approve.carloan.dao.CarAuditResultDao;
import com.creditharmony.approve.carloan.dao.CarCompletedListDao;
import com.creditharmony.approve.carloan.dao.CarLoanCoborrowerDao;
import com.creditharmony.approve.carloan.dao.CarLoanInfoDao;
import com.creditharmony.approve.carloan.dao.CustomerContactPersonDao;
import com.creditharmony.approve.carloan.dao.VehicleInfoDao;
import com.creditharmony.approve.carloan.entity.CarAuditResult;
import com.creditharmony.approve.carloan.entity.CarLoanCoborrower;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.CustomerContactPerson;
import com.creditharmony.approve.carloan.entity.VehicleInfo;
import com.creditharmony.approve.carloan.entity.ex.ApplyDetailInfokEx;
import com.creditharmony.approve.carloan.entity.ex.CarLoanExtendExtraEx;
import com.creditharmony.approve.carloan.entity.ex.CarVerifyListEx;
import com.creditharmony.approve.carloan.view.CarVerifyBusinessView;
import com.creditharmony.approve.verify.dao.ApproveDictDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.ex.DictEx;
import com.creditharmony.approve.verify.entity.ex.LoanCustomerEx;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.cache.DictCache;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.loan.type.CarLoanOperateResult;
import com.creditharmony.core.loan.type.CarLoanResult;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.loan.type.YESNO;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;

@Service
public class CarCheckService extends CarCommonService {

	@Autowired
	private CarLoanInfoDao carLoanInfoDao;
	@Autowired
	private CustomerContactPersonDao customerContactPersonDao;
	@Autowired
	private VehicleInfoDao vehicleInfoDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private ApproveDictDao dictDao;
	@Autowired
	private CarCompletedListDao carCompletedListDao;
	@Autowired
	private CarLoanCoborrowerDao carLoanCoborrowerDao;
	@Autowired
	private CarAuditResultDao carAuditResultDao;
	
	/**
	 * 根据借款编号获取详细借款申请信息，含有客户信息、客户联系人信息、基本申请信息、车辆信息、共借人（含共借人联系人）等
	 * 2016年1月25日
	 * By 申诗阔
	 * @param loanCode
	 * @return 详细借款申请信息
	 */
	public ApplyDetailInfokEx getCheckInfo(String loanCode) {
		ApplyDetailInfokEx applyDetailInfokEx = new ApplyDetailInfokEx();

		// 此次借款申请信息，例如：车借，展期1，展期2，展期3，...，那么此记录为最晚的展期3的申请信息
		CarLoanInfo loanInfo = carLoanInfoDao.findByLoanCode(loanCode);
		loanInfo.setDictLoanStatus(DictCache.getInstance().getDictLabel("jk_car_loan_status", loanInfo.getDictLoanStatus()));
		if (YESNO.YES.getCode().equals(loanInfo.getIsextension())) { // 若是展期，则赋予展期
			String sLoanCode = loanInfo.getLoanRawcode(); // 起始借款编码
			CarLoanInfo sloanInfo = carLoanInfoDao.findByLoanCode(sLoanCode);
			applyDetailInfokEx.setLoanInfo(sloanInfo);
			applyDetailInfokEx.setExtendLoanInfo(loanInfo);
			
			CarLoanExtendExtraEx carLoanExtendExtraEx = carLoanInfoDao.getSumData(sLoanCode);
			VehicleInfo extVehicleInfo = vehicleInfoDao.findByLoanCode(loanCode);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("loanCode", sLoanCode);
			map.put("checkType", CarLoanSteps.FINAL_AUDIT.getCode());
			List<String> codes = new ArrayList<String>();
			codes.add(CarLoanResult.THROUGH.getCode());
			codes.add(CarLoanResult.CONDITIONAL_THROUGH.getCode());
			map.put("throughCodes", codes);
			CarAuditResult carAuditResult = carAuditResultDao.getLastThroughRecord(map);
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
		List<CustomerContactPerson> customerContactPersons = customerContactPersonDao
				.findByLoanCodeAndType(conMap);
		applyDetailInfokEx.setCustomerContactPersons(customerContactPersons);

		// 车辆信息
		VehicleInfo vehicleInfo = vehicleInfoDao.findByLoanCode(loanCode);
		applyDetailInfokEx.setVehicleInfo(vehicleInfo);
		
		Map<String, String> coboMap = new HashMap<String, String>();
		coboMap.put("loanCode", loanCode);
		coboMap.put("loanType", LoanManFlag.COBORROWE_LOAN.getCode());
		// 客户共借人、以及共借人联系人
		List<CarLoanCoborrower> coBorrowers = carLoanCoborrowerDao
				.selectByLoanCodeAndLoanType(coboMap);
		applyDetailInfokEx.setCoborrowers(coBorrowers);

		return applyDetailInfokEx;
	}
	
	/**
	 * 获取回退清单
	 * 2016年1月26日
	 * By 申诗阔
	 * @param type
	 * @return 回退清单
	 */
	public List<DictEx> findAllBackCheckDicts(String type){
    	return dictDao.getDictsByType(type);
    }
	
	/**
	 * 获取已办，参数map中key及说明：
	 * <li>key：transactorCode，说明：当前登录人员的code值，只能查询自己办理的列表，不传则查询全部</li>
	 * <br>其他说明：其他参数可封入map
	 * 2016年1月26日
	 * By 申诗阔
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<CarVerifyListEx> findPage(Page<CarVerifyListEx> page, Map<String, Object> map) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), page.getPageSize());
		pageBounds.setCountBy("apply_id");
		pageBounds.setFilterOrderBy(BooleanType.FALSE);
		PageList<CarVerifyListEx> pageList = carCompletedListDao.getCompletedListDoneList(map, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	public int findCount(Map<String, Object> map) {
		List<CarVerifyListEx> list = carCompletedListDao.getCompletedListDoneList(map);
		return list.size();
	}
	
	/**
	 * 处理复审、终审待定
	 * 2016年2月16日
	 * By 申诗阔
	 * @param workItem
	 */
	public void waitHandle(WorkItemView workItem) {
		CarVerifyBusinessView flowView = (CarVerifyBusinessView) workItem.getBv();
		String stepName = workItem.getStepName();
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
	
	/**
	 * 处理复审、终审待定
	 * 2016年2月16日
	 * By 申诗阔
	 * @param workItem
	 */
	public void delayHandle(WorkItemView workItem) {
		CarVerifyBusinessView flowView = (CarVerifyBusinessView) workItem.getBv();
		String stepName = workItem.getStepName();
		// 审批步骤
		String stepNode = CarLoanSteps.parseByName(stepName).getCode();
		if (CarLoanSteps.RECHECK_AUDIT.getCode().equals(stepNode)) {
			flowView.setOperResultName(CarLoanOperateResult.RECHECK_DELAY.getCode());
			flowView.setAuditType(CarLoanSteps.RECHECK_AUDIT.getCode());
		} else if (CarLoanSteps.FINAL_AUDIT.getCode().equals(stepNode)) {
			flowView.setOperResultName(CarLoanOperateResult.FINALCHECK_DELAY.getCode());
			flowView.setAuditType(CarLoanSteps.FINAL_AUDIT.getCode());
		} else {
			flowView.setOperResultName("");
			flowView.setAuditType("");
		}
		auditHandle(flowView, stepName);
	}
}
