package com.creditharmony.approve.workflow.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.carloan.dao.CarAuditResultDao;
import com.creditharmony.approve.carloan.dao.CarContractDao;
import com.creditharmony.approve.carloan.dao.CarLoanInfoDao;
import com.creditharmony.approve.carloan.dao.CustomerConsultationDao;
import com.creditharmony.approve.carloan.dao.VehicleInfoDao;
import com.creditharmony.approve.carloan.entity.CarAuditResult;
import com.creditharmony.approve.carloan.entity.CarContract;
import com.creditharmony.approve.carloan.entity.CarLoanInfo;
import com.creditharmony.approve.carloan.entity.VehicleInfo;
import com.creditharmony.approve.carloan.service.CarContractVersionService;
import com.creditharmony.approve.carloan.service.CarImageService;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.ex.LoanCustomerEx;
import com.creditharmony.approve.workflow.view.CarLoanBusinessView;
import com.creditharmony.bpm.frame.face.BusinessLoadCallBack;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.loan.type.CarLoanSteps;
import com.creditharmony.core.loan.type.YESNO;

/**
 * 流程加载业务数据
 * @Class Name CarLoanFlowData
 * @author 李静辉
 * @Create In 2016年1月21日
 */
@Service("Car_Loan_Flow_Data")
public class CarLoanFlowData extends BaseService implements BusinessLoadCallBack {

	@Autowired
	private CarLoanInfoDao carLoanInfoDao;
	@Autowired
	private CarAuditResultDao carAuditResultDao;
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	@Autowired
	private VehicleInfoDao vehicleInfoDao;
	@Autowired
	private CarContractDao carContractDao;
	@Autowired
	private CarImageService carImageService;
	@Autowired
	private CustomerConsultationDao customerConsultationDao;
	@Autowired
	private CarContractVersionService contractVers;
	
	/**
	 * 点击办理   回调方法获取页面需要展示的数据
	 * 2016年1月22日
	 * By 李静辉
	 * @param applyId  流程ID
	 * @param stepName 流程步骤
	 * @return		     返回业务数据
	 */
	@Override
	public BaseBusinessView load(String applyId, String stepName) {
		CarLoanBusinessView res = new CarLoanBusinessView();
		CarLoanInfo loanInfo = carLoanInfoDao.findByApplyId(applyId);	// 借款信息
		if (YESNO.YES.getCode().equals(loanInfo.getIsextension())) { // 若是展期，则获取展期上一次终审审批金额
			CarContract carContract = carContractDao.getLastByLoanCodeOfExtend(loanInfo.getLoanCode());
			res.setCarContract(carContract);
		}
		String loanCode = loanInfo.getLoanCode();
		//获取合同版本号
	  	res.setContractVersion(contractVers.getFlowContractVersion(loanCode, loanInfo.getIsextension()));
		String imageUrl = carImageService.getImageUrl(stepName, loanCode,res.getContractVersion());
		res.setImageUrl(imageUrl);
		LoanCustomerEx loanCustomerEx = loanCustomerDao.findCustomerInfo(loanCode);	// 客户信息

		List<CarAuditResult> list = carAuditResultDao.findCarAuditResultsByLoanCode(loanCode);	// 审批信息
		
		VehicleInfo vehicleInfo = vehicleInfoDao.findByLoanCode(loanCode);	// 车辆信息
		// 若为终审待办办理，则获取最后一条复审(非附条件)
		if (CarLoanSteps.FINAL_AUDIT.getName().equals(stepName) && list != null && list.size() > 0) {
			CarAuditResult lastResult = list.get(list.size() - 1);
			if (CarLoanSteps.RECHECK_AUDIT.getCode().equals(lastResult.getDictCheckType())) {
				res.setCarAuditResult(lastResult);
			}
		}
		
		// TODO 这里type，checkType用于流程和查询，先写死
		res.setType(LoanManFlag.MAIN_LOAN.getCode()); // 主借人、2 共借人
		res.setLoanInfo(loanInfo);
		res.setLoanCustomerEx(loanCustomerEx);
		res.setCarAuditResultList(list);
		res.setVehicleInfo(vehicleInfo);
		System.out.println("我的待办--办理----调用工作流加载业务数据");
		return res;
	}
	
}
