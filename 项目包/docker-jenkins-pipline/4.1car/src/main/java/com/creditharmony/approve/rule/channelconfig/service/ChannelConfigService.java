
package com.creditharmony.approve.rule.channelconfig.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.approve.rule.channelconfig.client.ChannelConfigParam;
import com.creditharmony.approve.rule.channelconfig.client.ChannelconfigRuleDecisionService;
import com.creditharmony.approve.rule.channelconfig.client.ChannelconfigRuleRequest;
import com.creditharmony.approve.rule.channelconfig.client.ChannelconfigRuleResponse;
import com.creditharmony.approve.rule.channelconfig.client.ChannelconfigRuleSoapFault;
import com.creditharmony.approve.rule.channelconfig.client.Params;
import com.creditharmony.approve.verify.dao.LoanInfoDao;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.loan.type.LoanModel;
import com.google.common.collect.Maps;

/**
 * 根据门店判断是否TG
 * 
 * @Class Name ChannelConfigService
 * @author 张灏
 * @Create In 2015年12月22日
 */
@Service
public class ChannelConfigService{

	@Autowired
	private LoanInfoDao loanInfoDao;
	@Autowired
	private ChannelconfigRuleDecisionService channelConfigService;

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * 配置渠道 2016年3月2日 
	 * By 王浩 
	 * @param applyId
	 * @param loanCode
	 * @return String 是否为TG渠道
	 */
	public String isChannelTG(String applyId, String loanCode) {
		log.info(" 调用规则引擎判断渠道标识开始：" + applyId);
		// 获取调用进件引擎webserice需要传入的参数
		Map<String, Object> map = Maps.newHashMap();
		map.put("applyId", applyId);
		map.put("loanCode", loanCode);
		// 查询所需参数(门店名字)
		ChannelConfigParam configParam = loanInfoDao.selectChannelParam(map);
		ChannelConfigParam result = null;
		if (configParam != null) {
			// 调用进件引擎判断渠道
			try {
				result = this.getApplyEngineResult(configParam);
			} catch (ChannelconfigRuleSoapFault e) {
				log.info(e.getMessage());
				e.printStackTrace();
				return BooleanType.FALSE;
			}
			log.info(" 调用规则引擎判断渠道标识结束：" + applyId);
			// 返回结果
			if (result != null) {				
				return StringUtils.isNotEmpty(result.getLoanModel()) && result.getLoanModel().equals(LoanModel.TG.getCode())
							? BooleanType.TRUE : BooleanType.FALSE;
			}
		}		
		return BooleanType.FALSE;
	}

	/**
	 * 调用进件引擎 2016年3月2日 
	 * By 王浩 
	 * @param configParam
	 * @return ChannelConfigParam 返回已封装判定结果的对象
	 * @throws ChannelconfigRuleSoapFault
	 */
	private ChannelConfigParam getApplyEngineResult(
			ChannelConfigParam configParam) throws ChannelconfigRuleSoapFault {
		log.info("invoking channelConfigEngine, params:"
				+ configParam.toString());
		ChannelconfigRuleRequest channelConfigRequest = new ChannelconfigRuleRequest();
		Params params = new Params();
		params.setParams(configParam);
		channelConfigRequest.setParams(params);
		ChannelconfigRuleResponse response = channelConfigService
				.channelconfigRule(channelConfigRequest);
		ChannelConfigParam result = response.getParams().getParams();
		log.info("result:" + result.toString());
		return result;
	}
	
}
