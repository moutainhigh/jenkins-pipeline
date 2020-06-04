package com.creditharmony.approve.credit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.approve.credit.dao.CreditReportSimpleDao;
import com.creditharmony.approve.credit.entity.CreditReportSimple;
import com.creditharmony.approve.verify.dao.LoanCoborrowerDao;
import com.creditharmony.approve.verify.dao.LoanCustomerDao;
import com.creditharmony.approve.verify.entity.LoanCoborrower;
import com.creditharmony.approve.verify.entity.LoanCustomer;
import com.creditharmony.core.lend.type.LoanManFlag;
import com.creditharmony.core.service.CoreManager;

/**
 * 简版信用报告Service
 * @Class Name CreditReportSimpleService
 * @author zhanghu
 * @Create In 2016年1月29日
 */
@Service
public class CreditReportSimpleService extends  CoreManager<CreditReportSimpleDao,CreditReportSimple>{
	
	@Autowired
	private LoanCoborrowerDao loanCoborrowerDao;
	
	@Autowired
	private LoanCustomerDao loanCustomerDao;
	
	@Autowired
	private CreditCardInfoService creditCardInfoService;
	
	@Autowired
	private CreditLoanInfoService creditLoanInfoService;
	
	@Autowired
	private CreditQueryRecordService creditQueryRecordService;
	
    /**
     * 根据征信信息对象查询征信信息
     * 2016年2月2日
     * By zhanghu
     * @param record
     * @return 征信信息对象
     */
    public CreditReportSimple selectByCreditReportSimple(CreditReportSimple record) {		
    	return this.dao.selectByCreditReportSimple(record);    	
    }
    
	/**
	 * 根据个人征信简版id更新有值列信息
	 * 2016年2月3日
	 * By zhanghu
	 * @param creditReportSimple 个人征信简版
	 * @return 执行条数
	 */
	@Transactional(value="loanTransactionManager",readOnly=false)
	public int updateCreditReportSimple(CreditReportSimple creditReportSimple) {
		// 初始化默认数据
		creditReportSimple.preUpdate();
		return this.dao.updateByPrimaryKeySelective(creditReportSimple);
	}
	
	/**
	 *通过LoanCode查询共借人 
	 *@author zhanghu
     *@Create In 2015年12月25日
	 *@param loanCode
	 *@return List<LoanCoborrower>
	 */
	public List<LoanCoborrower> selectByLoanCode(String loanCode) {
		//return this.loanCoborrowerDao.selectByLoanCode(loanCode);
		LoanCoborrower loanCoborrower=new LoanCoborrower();
		loanCoborrower.setLoanCode(loanCode);
		return this.loanCoborrowerDao.getByLoanCode(loanCoborrower);
	}
	
    /**
     * 2015年12月25日
     * @author zhanghao
     * @param applyId
     * @return LoanCustomer
     */
    public LoanCustomer selectByApplyId(String applyId) {
    	return this.loanCustomerDao.selectByApplyId(applyId);
    }    

	/**
	 * 查找客户姓名
	 * 2016年5月3日
	 * By 王浩
	 * @param creditReportSimple
	 * @return 
	 */
	public String getCustomerName(CreditReportSimple creditReportSimple) {
		if (LoanManFlag.MAIN_LOAN.getCode().equals(creditReportSimple.getDictCustomerType())) {
			LoanCustomer loanCustomer = this.loanCustomerDao.getLoanCustomer(creditReportSimple.getrCustomerCoborrowerId());
			return loanCustomer.getCustomerName();
		} else if(LoanManFlag.COBORROWE_LOAN.getCode().equals(creditReportSimple.getDictCustomerType())) {
			LoanCoborrower coborrower = this.loanCoborrowerDao.getCoborrower(creditReportSimple.getrCustomerCoborrowerId());
			return coborrower.getCoboName();
		}
		return null;
	}
	
	/**
     * 2016年5月7日
     * @param id
     * @return LoanCustomer
     */
    public LoanCustomer selectCustomerById(String id) {
    	return this.loanCustomerDao.getLoanCustomer(id);
    }
    
    /**
     * 2016年5月7日
     * @param id
     * @return LoanCoborrower
     */
    public LoanCoborrower selectCoborrowerById(String id) {
    	return this.loanCoborrowerDao.getCoborrower(id);
    }

  /*  *//**
     * 初始化征信信息-爬虫
     * 2016年2月2日
     * By zhanghu
     * @param pbcGetReportOutInfo
     * @param creditReportSimple
     *//*
    @Transactional(value="loanTransactionManager",readOnly=false)
	public void playReport(PbcGetReportOutInfo pbcGetReportOutInfo, CreditReportSimple creditReportSimple) {
		
    	String creditReportSimpleId = creditReportSimple.getId();
    	
    	// 基本信息
    	CreditReportSimple creditReportSimpleNew = new CreditReportSimple();
    	creditReportSimpleNew.setId(creditReportSimpleId);
    	creditReportSimpleNew.setQueryTime(new Date());
    	creditReportSimpleNew.setHtmlUrl(pbcGetReportOutInfo.getHtmlUrl());//静态网页
    	this.updateCreditReportSimple(creditReportSimpleNew);
    	
    	// 信用卡信息list
		try {

			// 数据
	    	List<CreditCardDetails> creditCardInfoList = pbcGetReportOutInfo.getCreditCardInfoList();
			if (ArrayHelper.isNotEmpty(creditCardInfoList)) {
				// 清空原有数据
				creditCardInfoService.deleteByRelationId(creditReportSimpleId);
				
				for (CreditCardDetails creditCardDetails : creditCardInfoList) {
					CreditCardInfo creditCardInfoNew = new CreditCardInfo();
					
					//数据转换
					creditCardInfoNew.setRelationId(creditReportSimpleId);
					
					//账户状态
					creditCardInfoNew.setAccountStatus(getDicCode("jk_credit_cardinfo_accountstatus", creditCardDetails.getAccountStatus()));
					//币种
					creditCardInfoNew.setCurrency(getDicCode("jk_enterprise_currency", creditCardDetails.getCurrency()));
					//是否发生过逾期
					if (!StringUtils.isNotEmpty(creditCardDetails.getIsOverdue())) {
						creditCardDetails.setIsOverdue(EnterpriseCreditConstants.ISOVERDUE_1);
					}
					creditCardInfoNew.setIsOverdue(getDicCode("jk_credit_isoverdue", creditCardDetails.getIsOverdue()));
					//发放日期
					creditCardInfoNew.setIssueDay(DateUtils.parseDate((Object)(creditCardDetails.getIssueDay())));
					//截至年月
					creditCardInfoNew.setAbortDay(DateUtils.parseDate((Object)(creditCardDetails.getAbortDay())));
					//额度
					creditCardInfoNew.setLimit(getBigDecimal(creditCardDetails.getLimit()));
					//已使用额度
					creditCardInfoNew.setUsedLimit(getBigDecimal(creditCardDetails.getUsedLimit()));
					//逾期金额
					creditCardInfoNew.setOverdueAmount(getBigDecimal(creditCardDetails.getOverdueAmount()));
					//最近5年逾期次数
					creditCardInfoNew.setOverdueNo(getInteger(creditCardDetails.getOverdueNo()));
					//最近五年90天以上逾期次数
					creditCardInfoNew.setOverdueForNo(getInteger(creditCardDetails.getOverdueForNo()));
					//销户年月
					creditCardInfoNew.setCancellationDay(DateUtils.parseDate((Object)(creditCardDetails.getCancellationDay())));

					creditCardInfoService.insertCreditCardInfo(creditCardInfoNew);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 贷款明细信息list
		try {

			// 数据
			List<CreditLoanDetails> creditLoanInfoList = pbcGetReportOutInfo.getCreditLoanInfoList();
			if (ArrayHelper.isNotEmpty(creditLoanInfoList)) {
				// 清空原有数据
				creditLoanInfoService.deleteByRelationId(creditReportSimpleId);
				
				for (CreditLoanDetails creditLoanDetails : creditLoanInfoList) {
					CreditLoanInfo creditLoanInfoNew = new CreditLoanInfo();
					
					//数据转换
					creditLoanInfoNew.setRelationId(creditReportSimpleId);
					//账户状态
					creditLoanInfoNew.setAccountStatus(getDicCode("jk_credit_loaninfo_accountstatus", creditLoanDetails.getAccountStatus()));
					//贷款种类
					creditLoanInfoNew.setCurrency(getDicCode("jk_credit_loan_type_flag", creditLoanDetails.getCurrency()));
					//是否发生过逾期
					creditLoanInfoNew.setIsOverdue(getDicCode("jk_credit_isoverdue", creditLoanDetails.getIsOverdue()));
					//发放日期
					creditLoanInfoNew.setIssueDay(DateUtils.parseDate((Object)(creditLoanDetails.getIssueDay())));
					//截至年月
					creditLoanInfoNew.setAbortDay(DateUtils.parseDate((Object)(creditLoanDetails.getAbortDay())));
					//截至年月
					creditLoanInfoNew.setActualDay(DateUtils.parseDate((Object)(creditLoanDetails.getActualDay())));
					//贷款合同金额
					creditLoanInfoNew.setConteactAmount(getBigDecimal(creditLoanDetails.getConteactAmount()));
					//贷款余额
					creditLoanInfoNew.setLoanBalance(getBigDecimal(creditLoanDetails.getLoanBalance()));
					//逾期金额
					creditLoanInfoNew.setOverdueAmount(getBigDecimal(creditLoanDetails.getOverdueAmount()));
					//最近5年逾期次数
					creditLoanInfoNew.setOverdueNo(getInteger(creditLoanDetails.getOverdueNo()));
					//最近五年90天以上逾期次数
					creditLoanInfoNew.setOverdueForNo(getInteger(creditLoanDetails.getOverdueForNo()));
					//结清年月
					creditLoanInfoNew.setSettleDay(DateUtils.parseDate((Object)(creditLoanDetails.getSettleDay())));

					creditLoanInfoService.insertCreditLoanInfo(creditLoanInfoNew);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
		// 查询信息list
		try {

			// 数据
			List<CreditQueryRecord> creditQueryRecordList = pbcGetReportOutInfo.getCreditQueryRecordList();
			if (ArrayHelper.isNotEmpty(creditQueryRecordList)) {
				// 清空原有数据
				creditQueryRecordService.deleteByRelationId(creditReportSimpleId);
				
				for (CreditQueryRecord creditQueryRecord : creditQueryRecordList) {
					com.creditharmony.approve.credit.entity.CreditQueryRecord creditQueryRecordNew = new com.creditharmony.approve.credit.entity.CreditQueryRecord();
					
					//数据转换
					creditQueryRecordNew.setRelationId(creditReportSimpleId);
					//发放日期
					creditQueryRecordNew.setQueryDay(DateUtils.parseDate((Object)(creditQueryRecord.getQueryDate())));
					//查询原因
					creditQueryRecordNew.setQueryType(getDicCode("jk_credit_queryrecord_querytype", creditQueryRecord.getQueryCause()));

					creditQueryRecordService.insertCreditQueryRecord(creditQueryRecordNew);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	
	}*/

//    /**
//     * 获取数据字典code-爬虫
//     * 2016年2月2日
//     * By zhanghu
//     * @param type
//     * @param str
//     * @return String 
//     */
//    private String getDicCode(String type, String str) {
//    	//空直接返回
//    	if (!StringUtils.isNotEmpty(str)) {
//    		return "";
//    	}
//    	//获取数据字典list
//    	List<Dict> dictList = DictUtils.getDictList(type);
//    	if (ArrayHelper.isNotEmpty(dictList)) {
//    		for (Dict dict : dictList) {
//    			
//    			if (dict.getLabel().equals(str)) {
//    				return dict.getValue();
//    			}
//    		}
//    		
//    	}
//    	
//    	return "";
//    }
//    
//    /**
//     * 获取BigDecimal
//     * 2016年2月2日
//     * By zhanghu
//     * @param type
//     * @param str
//     * @return String 
//     */
//    private BigDecimal getBigDecimal(String str) {
//    	//空直接返回
//    	if (!StringUtils.isNotEmpty(str)) {
//    		return null;
//    	}
//    	
//		try {
//
//			return new BigDecimal(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("str:" + str);
//		}
//		return null;
//
//    }
//    
//    /**
//     * 获取Integer
//     * 2016年2月2日
//     * By zhanghu
//     * @param str
//     * @return Integer 
//     */
//    private Integer getInteger(String str) {
//    	//空直接返回
//    	if (!StringUtils.isNotEmpty(str)) {
//    		return 0;
//    	}
//    	
//		try {
//
//			return Integer.valueOf(str);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("str:" + str);
//		}
//		return 0;
//
//    }    
    
}
