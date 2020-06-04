package com.creditharmony.approve.rule.auditrating.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.base.service.ApproveCommonService;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingParam;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingRulesDecisionService;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingRulesRequest;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingRulesResponse;
import com.creditharmony.approve.rule.auditrating.client.AuditRatingRulesSoapFault;
import com.creditharmony.approve.rule.auditrating.client.Param;
import com.creditharmony.approve.verify.dao.AuditRatingResultDao;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.approve.verify.entity.AuditRatingResult;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.approve.type.ApproveCheckType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.persistence.BaseEntity;
import com.google.common.collect.Maps;

/**
 * 评分卡规则判定评分，返回评分判定结果
 * @Class Name RatingCardService
 * @author 王浩
 * @Create In 2016年3月21日
 */
@Service
public class AuditRatingService extends ApproveCommonService{

	@Autowired
	private AuditRatingResultDao ratingResultDao;	
	@Autowired
	private LoanInfoDao loanInfoDao;
	
	@Autowired
	private AuditRatingRulesDecisionService auditRatingDecisionService;

	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 根据借款编码，获取评分并返回评分判定拒借或通过
	 * 2016年3月21日
	 * By 王浩
	 * @param loanCode
	 * @return 评分排定结果
	 * @throws Exception 
	 */
	public AuditRatingParam testVerifyScore(String loanCode) throws ServiceException {
		AuditRatingParam response = null;
		try {
			// 获取调用评分卡规则webserice需要传入的参数
			AuditRatingParam requestParam = this.prepareWSParam(loanCode, ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode());
			if (requestParam != null) {
				// 调用评分卡规则
				response = this.getRateScore(requestParam);
				log.info(response.toString());				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return response;
	}

	/**
	 * 根据借款编码，获取评分并返回评分判定拒借或通过
	 * 2016年3月21日
	 * By 王浩
	 * @param loanCode
	 * @return 评分排定结果
	 * @throws Exception 
	 */
	public String getScore(String loanCode, String dictCheckType) throws ServiceException {
		String riskLevel = null;
		try {
			// 获取调用评分卡规则webserice需要传入的参数
			AuditRatingParam requestParam = this.prepareWSParam(loanCode, dictCheckType);
			if (requestParam != null) {
				// 调用评分卡规则
				AuditRatingParam response = this.getRateScore(requestParam);
				// 将风险等级返回
				riskLevel = response.getRateCheckResult();
				// 保存评分
				this.saveRatingScore(loanCode, dictCheckType, response);
				log.info(response.toString());				
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
		return riskLevel;
	}

	/**
	 * 查询评分所需的所有数据
	 * 2016年3月21日
	 * By 王浩
	 * @param loanCode 借款编码
	 * @return 返回数据
	 */
	private AuditRatingParam prepareWSParam(String loanCode, String dictCheckType) {
		Map<String,String> applyInfo = loanInfoDao.getCustomerIntoTimeByLoanCode(loanCode);
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("loanCode", loanCode);
		map.put("dictCheckType", dictCheckType);
		map.put("customerId", applyInfo.get("customerid"));
		map.put("delFlag", BaseEntity.DEL_FLAG_NORMAL);
		// 根据参数拼接调用规则引擎webservice服务时的对象参数
		List<AuditRatingParam> rateParamList = ratingResultDao.findAuditRatingParam(map);
		if (ArrayHelper.isNotEmpty(rateParamList)) {
			AuditRatingParam rateParam = rateParamList.get(0);	
			List<Integer> queryCounts = ratingResultDao.queryRecordCount(map);
			// 贷款审批查询
			rateParam.setQueriesLoanSp(queryCounts.get(0));
			// 本人查询次数
			rateParam.setPersonalQueryNum(queryCounts.get(1));
			// 半年内总查询次数
			rateParam.setNSixQueriesNum(queryCounts.get(2));
			// 发生过逾期的贷款个数
			rateParam.setNumLoanOverdue(ratingResultDao.countLoanOverdue(map));
			// 半年内是否有新增贷款或信用卡
			rateParam.setNSixNewLoanCredit(ratingResultDao.newCreditLoanExists(map));
			// 正常信用卡额度使用率
			rateParam.setNormalCreditUseRate(ratingResultDao.selectMaxCreditUseCentage(map));
			// 最早贷款账龄
			rateParam.setEarliestLoanMonth(ratingResultDao.selectLoanEarliestMonth(map));
			// 最早的信用卡账龄
			rateParam.setEarliestCreditMonth(ratingResultDao.selectCreditEarliestMonth(map));
			// 最近的信用卡账龄
			rateParam.setLatestCreditMonth(ratingResultDao.selectCreditLatestMonth(map));
			
			return rateParam;
		} else {
			return null;
		}
	}

	/**
	 * 调用评分规则
	 * 2016年3月21日
	 * By 王浩
	 * @param rateCard
	 * @return 返回评分分数与判定结果
	 * @throws RatingRulesSoapFault 
	 */
	public AuditRatingParam getRateScore(AuditRatingParam rateParam)
			throws AuditRatingRulesSoapFault {
		log.info("invoking ratetingCardRule, params:" + rateParam.toString());
		// 封装参数
		AuditRatingRulesRequest ratingRulesRequest = new AuditRatingRulesRequest();
		Param param = new Param();
		param.setParam(rateParam);
		ratingRulesRequest.setParam(param);
		// 获取评分判定结果
		AuditRatingRulesResponse ruleResponse = auditRatingDecisionService
				.auditRatingRules(ratingRulesRequest);
		AuditRatingParam rateResultScore = ruleResponse.getParam().getParam();

		log.info("loanCode：" + rateResultScore.getLoanCode()
				+ ", ratingScore："	+ rateResultScore.getFinalScore() 
				+ ", ratingResult："	+ rateResultScore.getRateCheckResult());
		return rateResultScore;
	}

	/**
	 * 保存评分数据到业务库中
	 * 2016年3月21日
	 * By 王浩
	 * @param loanCode
	 * @param rateResult
	 * @return none
	 */
	private void saveRatingScore(String loanCode, String dictCheckType, AuditRatingParam rateResult) {		
		AuditRatingResult ratingResult = new AuditRatingResult();		
		ratingResult.setLoanCode(loanCode);
		
		AuditRatingResult prevRatingResult = ratingResultDao.findByLoanCode(loanCode);			
		if (prevRatingResult != null) {
			ratingResult.setId(prevRatingResult.getId());
		}
		
		if (dictCheckType.equals(ApproveCheckType.XS_APPROVE_CHECK_TYPE.getCode())) {
			// 信审评分分数			
			ratingResult.setVerifyRateScore(rateResult.getFinalScore());
			// 信审风险等级		
			ratingResult.setVerifyRiskLevel(rateResult.getRateCheckResult());
		} else if (dictCheckType.equals(ApproveCheckType.FY_APPROVE_CHECK_TYPE.getCode())) {		
			// 复议评分分数			
			ratingResult.setReconsiderRateScore(rateResult.getFinalScore());
			// 复议风险等级		
			ratingResult.setReconsiderRiskLevel(rateResult.getRateCheckResult());			
		}
		// 如果数据库中已经有该条记录，那么就更新该条记录
		if (StringUtils.isNotEmpty(ratingResult.getId())) {
			ratingResult.preUpdate();
			ratingResultDao.updateSelective(ratingResult);
		} else {
			ratingResult.preInsert();
			ratingResultDao.insertSelective(ratingResult);
		}		
	}

}