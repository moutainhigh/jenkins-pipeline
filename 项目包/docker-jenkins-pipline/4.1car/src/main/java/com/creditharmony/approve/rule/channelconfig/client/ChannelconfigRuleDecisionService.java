package com.creditharmony.approve.rule.channelconfig.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * This class was generated by Apache CXF 2.3.2
 * 2016-03-11T14:56:54.419+08:00
 * Generated source version: 2.3.2
 * 
 */
 
@WebService(targetNamespace = "http://www.ibm.com/rules/decisionservice/Channelconfig_ruleapp/Channelconfig_rule", name = "Channelconfig_ruleDecisionService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ChannelconfigRuleDecisionService {

    @WebResult(name = "Channelconfig_ruleResponse", targetNamespace = "http://www.ibm.com/rules/decisionservice/Channelconfig_ruleapp/Channelconfig_rule", partName = "Channelconfig_ruleResponse")
    @WebMethod(operationName = "Channelconfig_rule", action = "Channelconfig_rule")
    public ChannelconfigRuleResponse channelconfigRule(
        @WebParam(partName = "Channelconfig_ruleRequest", name = "Channelconfig_ruleRequest", targetNamespace = "http://www.ibm.com/rules/decisionservice/Channelconfig_ruleapp/Channelconfig_rule")
        ChannelconfigRuleRequest channelconfigRuleRequest
    ) throws ChannelconfigRuleSoapFault;
}
