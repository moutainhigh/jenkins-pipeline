package com.creditharmony.approve.verify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.common.constants.DictionaryConstants;
import com.creditharmony.approve.document.dao.ZlshCczmDao;
import com.creditharmony.approve.document.dao.ZlshGxhtDao;
import com.creditharmony.approve.document.dao.ZlshJyzmDao;
import com.creditharmony.approve.document.dao.ZlshLoanAccountDao;
import com.creditharmony.approve.document.dao.ZlshZczmDao;
import com.creditharmony.approve.phone.dao.DhzhBrhsDao;
import com.creditharmony.approve.phone.dao.DhzhDhgxshDao;
import com.creditharmony.approve.phone.dao.TelAuditWorkDao;
import com.creditharmony.approve.verify.dao.AuditResultDao;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.dao.ProposalRemarksDao;
import com.creditharmony.approve.verify.dao.TelAuditInternetInfoDao;
import com.creditharmony.approve.verify.entity.ProposalRemarks;
import com.creditharmony.approve.verify.entity.ex.UploadProposalEx;
import com.creditharmony.approve.verify.entity.ex.VerifyParamEx;
import com.creditharmony.approve.verify.util.DictToMapUtil;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.lend.type.LendConstants;
import com.creditharmony.core.lend.type.LoanManFlag;

/**
 * 下载意见书service 初始化
 * @Class Name UploadProposalService
 * @author 刘燕军
 * @Create In 2015年12月5日
 */
@Service
public class UploadProposalService {
	@Autowired
	private LoanCustomerDao customerDao;
	@Autowired
	private LoanCoborrowerDao coborrowerDao;
	@Autowired
	private ZlshJyzmDao zlshJyzmDao;
	@Autowired
	private ZlshGxhtDao zlshGxhtDao;
	@Autowired
	private ZlshLoanAccountDao zlshLoanAccountDao;
	@Autowired
	private ZlshZczmDao zlshZczmDao;
	@Autowired
	private ZlshCczmDao zlshCczmDao;
	@Autowired
	private DhzhDhgxshDao dhzhDhgxshDao;
	@Autowired
	private AuditResultDao auditResultDao;
	@Autowired
	private ProposalRemarksDao proposalRemarksDao;
	@Autowired
	private TelAuditInternetInfoDao telAuditInternetInfoDao;
	@Autowired
	private DhzhBrhsDao dhzhBrhsDao;
	@Autowired
	private TelAuditWorkDao telAuditWorkDao;
	
	/**
	 * 下载意见书的初始化页面
	 * 2015年12月5日
	 * By 刘燕军
	 * @param LoanerType
	 * @param loanCode
	 * @return
	 */
	public UploadProposalEx findUploadProposalEx(VerifyParamEx param){
		String loanCode = param.getLoanCode();
		UploadProposalEx uploadProposalEx = new UploadProposalEx();
		// 获取主借人和配偶信息
		uploadProposalEx.setMainLoanInfoEx(customerDao.findMainLoanInfoEx(loanCode));
		// 获取共借人和配偶信息
		uploadProposalEx.setJointlyLoanInfoEx(coborrowerDao.findJointlyEx(loanCode,LoanManFlag.COBORROWE_LOAN.getCode()));
		// 获取企业信息 findCompanyInfo
		uploadProposalEx.setCompanyInfo(zlshJyzmDao.findCompanyInfo(param));
		// 获取购销合同信息
		uploadProposalEx.setContractExs(zlshGxhtDao.findContractExs(param));
		// 获取流水信息
		uploadProposalEx.setZlshLoanAccount(zlshLoanAccountDao.findZlshLoanAccounts(param));
		// 资产证明
		uploadProposalEx.setZlshZczms(zlshZczmDao.findZlshZczms(param));		
		// 车产证明
		uploadProposalEx.setZlshCczms(zlshCczmDao.findZlshCczms(param));
		// 电话核查结果
		uploadProposalEx.setTelCheckResultExs(dhzhDhgxshDao.getWork(param));
		// 外部信息核查
		uploadProposalEx.setOutNet(telAuditWorkDao.findOutNet(param));
		// 下载意见书中的其他情况
		uploadProposalEx.setRemark(proposalRemarksDao.getRemark(param));
		uploadProposalEx.setAuditResult(auditResultDao.findResult(param));
		uploadProposalEx.setPeoples(auditResultDao.findPeoples(param));
		uploadProposalEx.setMarriage(DictToMapUtil.getMap(DictionaryConstants.MARRIAGE));
		uploadProposalEx.setLoanUse(DictToMapUtil.getMap(DictionaryConstants.LOAN_USE));
		uploadProposalEx.setShareholder(DictToMapUtil.getMap(DictionaryConstants.SHAREHOLDER_RE));
		uploadProposalEx.setSettlement(DictToMapUtil.getMap(DictionaryConstants.SETTLEMENT_TYPE));
		uploadProposalEx.setUpdowncontract(DictToMapUtil.getMap(DictionaryConstants.UPDOWNCONTRACT));
		uploadProposalEx.setTelSrc(DictToMapUtil.getMap(DictionaryConstants.TEL_SOURCE));
		uploadProposalEx.setCheckResult(DictToMapUtil.getMap(LendConstants.EVAL_RESULT));
		uploadProposalEx.setSalaryTrace(DictToMapUtil.getMap(DictionaryConstants.SALARYTRACE));
		uploadProposalEx.setPledge(DictToMapUtil.getMap(DictionaryConstants.PLEDGE));
		uploadProposalEx.setHouse(DictToMapUtil.getMap(DictionaryConstants.HOUSE));
		uploadProposalEx.setProperty(DictToMapUtil.getMap(DictionaryConstants.PROPERTY));
		uploadProposalEx.setPhoneStatue(DictToMapUtil.getMap(DictionaryConstants.PHONE_STATUE));
		return uploadProposalEx;		
	}
	
	/**
	 * 下载意见书中信息保存
	 * 2016年1月13日
	 * By 刘燕军
	 * @param remark
	 * @return 保存后对应的id
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public String saveRemark(ProposalRemarks remark){
		if(StringUtils.isBlank(remark.getId())){
			remark.preInsert();
			proposalRemarksDao.insert(remark);
		}else{
			remark.preUpdate();
			proposalRemarksDao.update(remark);
		}
		return remark.getId();
	}
}
