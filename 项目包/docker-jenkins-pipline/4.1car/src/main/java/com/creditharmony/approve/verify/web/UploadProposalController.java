package com.creditharmony.approve.verify.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.approve.common.constants.ApproveConstants;
import com.creditharmony.approve.common.entity.CreditReportRisk;
import com.creditharmony.approve.credit.constants.CreditReportConstants;
import com.creditharmony.approve.credit.entity.CreditCardInfo;
import com.creditharmony.approve.credit.entity.CreditLoanInfo;
import com.creditharmony.approve.credit.entity.ex.CreditCardDetailedEx;
import com.creditharmony.approve.credit.entity.ex.CreditDownUseEx;
import com.creditharmony.approve.credit.entity.ex.CreditLoanDetailedEx;
import com.creditharmony.approve.credit.service.CreditRiskReportService;
import com.creditharmony.approve.verify.entity.ProposalRemarks;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.service.UploadProposalService;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.Global;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.web.BaseController;

/**
 * 下载意见书
 * @Class Name UploadProposalController
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@Controller
@RequestMapping(value = "${adminPath}/verify/uploadProposal")
public class UploadProposalController extends BaseController{
	@Autowired
	private UploadProposalService uploadProposalService;
	@Autowired
	CreditRiskReportService creditRiskReportService;
	
	/**
	 * 下载意见书的初始化
	 * 2015年12月5日
	 * By 刘燕军
	 * @param loanCode
	 * @return 转向路径
	 */
	@RequestMapping(value = "list")
	public String getList(Model model,VerifyParamEx param){
		model.addAttribute("param", param);
		model.addAttribute("uploadProposalEx", uploadProposalService.findUploadProposalEx(param));
		
		CreditDownUseEx main = new CreditDownUseEx();
		List<CreditDownUseEx> coborrowe = new ArrayList<CreditDownUseEx>();
		// 获取所有的录入征信数据的人
		List<CreditReportRisk> allPerson = creditRiskReportService.getAllPerson(param);
		if(ArrayHelper.isNotEmpty(allPerson)){
			for(CreditReportRisk risk:allPerson){
				// 如果为详版（主借人）
				if(risk.getDictCustomerType().equals(LoanManFlag.MAIN_LOAN.getCode()) 
						&& risk.getRiskCreditVersion().equals(CreditReportConstants.DETAILED)){
					// 详版贷记卡
					List<CreditCardDetailedEx> detaCard = creditRiskReportService.downloadUseDetailCard(param,
							LoanManFlag.MAIN_LOAN.getCode());
					main.setDownCardDetailList(detaCard);
					// 详版贷款
					List<CreditLoanDetailedEx> detaLoan = creditRiskReportService.downloadUseDetailLoan(param,
							LoanManFlag.MAIN_LOAN.getCode());
					main.setDownLoanDetailList(detaLoan);
					// 把主借人的数据放到页面
					model.addAttribute("main", main);
				}
				// 如果主借人为简版
				if(risk.getDictCustomerType().equals(LoanManFlag.MAIN_LOAN.getCode()) 
						&& risk.getRiskCreditVersion().equals(CreditReportConstants.SIMPLE)){
					
					List<CreditCardInfo> simpCard = creditRiskReportService.downloadUseSimpleCard(param,
							LoanManFlag.MAIN_LOAN.getCode());
					main.setDownSimpleCardList(simpCard);
					List<CreditLoanInfo> simpLoan = creditRiskReportService.downloadUseSimpleLoan(param,
							LoanManFlag.MAIN_LOAN.getCode());
					main.setDownSimpleloanList(simpLoan);
					model.addAttribute("main", main);
				}
				// 如果为共借人详版
				if(risk.getDictCustomerType().equals(LoanManFlag.COBORROWE_LOAN.getCode()) 
						&& risk.getRiskCreditVersion().equals(CreditReportConstants.DETAILED)){
					
					CreditDownUseEx gong = new CreditDownUseEx();
					// 详版贷记卡
					VerifyParamEx verify = new VerifyParamEx();
					verify.setLoanCode(param.getLoanCode());
					verify.setType(LoanManFlag.COBORROWE_LOAN.getCode());
					verify.setRelId(risk.getrId());
					List<CreditCardDetailedEx> detaCard = creditRiskReportService.downloadUseDetailCard(verify,
							LoanManFlag.COBORROWE_LOAN.getCode());
					gong.setDownCardDetailList(detaCard);
					// 详版贷款
					List<CreditLoanDetailedEx> detaLoan = creditRiskReportService.downloadUseDetailLoan(verify,
							LoanManFlag.COBORROWE_LOAN.getCode());
					gong.setDownLoanDetailList(detaLoan);
					// 把共借人的数据放到页面
					coborrowe.add(gong);
				}
				// 如果共借人为简版
				if(risk.getDictCustomerType().equals(LoanManFlag.COBORROWE_LOAN.getCode()) 
						&& risk.getRiskCreditVersion().equals(CreditReportConstants.SIMPLE)){
					CreditDownUseEx gong = new CreditDownUseEx();
					// 详版贷记卡
					VerifyParamEx verify = new VerifyParamEx();
					verify.setLoanCode(param.getLoanCode());
					verify.setType(LoanManFlag.COBORROWE_LOAN.getCode());
					verify.setRelId(risk.getrId());
					List<CreditCardInfo> simpCard = creditRiskReportService.downloadUseSimpleCard(verify,
							LoanManFlag.COBORROWE_LOAN.getCode());
					gong.setDownSimpleCardList(simpCard);
					List<CreditLoanInfo> simpLoan = creditRiskReportService.downloadUseSimpleLoan(verify,
							LoanManFlag.COBORROWE_LOAN.getCode());
					gong.setDownSimpleloanList(simpLoan);
					// 把共借人的数据放到页面
					coborrowe.add(gong);
				}
			}
		}
		model.addAttribute("coborrowe", coborrowe);
		return "/verify/uploadProposalView";		
	}
	
	/**
	 * 下载意见书中意见保存
	 * 2016年1月13日
	 * By 刘燕军
	 * @param remark
	 * @return 成功或者失败
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "asynSave")
	public String asynSave(ProposalRemarks remark) throws Exception{
		String id = null;
		if(!StringUtils.isBlank(remark.getLoanCode())){
			try{
				id = uploadProposalService.saveRemark(remark);
			}catch(Exception e){
				logger.error(e.getMessage());
				return id;
			}
			return id;
		}else{
			throw new Exception("对象内容有异常");
		}
	}
	
	/**
	 * 
	 * 2016年4月8日
	 * By 刘燕军
	 * @param paran
	 * @return 定向到下载文件的路径
	 * @throws Exception
	 */
	@RequestMapping(value = "uploadFile")
	public String uploadFile(VerifyParamEx paran) throws Exception{
		if(StringUtils.isNotEmpty(paran.getLoanCode())){
			try{
				String url = Global.getConfig(ApproveConstants.UPLOAD_URL);
				return "redirect:"+url+"loan_Code="+paran.getLoanCode()+"&check_Type="+paran.getCheckType();
			}catch(Exception e){
				logger.error(e.getMessage());
				return null;
			}
		}else{
			throw new Exception("对象内容有异常");
		}
	}
}
