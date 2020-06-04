/**
 * 常量
 */
var CONSTANT={
	BOOLEANTYPE_TRUE : "true",
	BOOLEANTYPE_FALSE : "false",
	OUTSIDE_FLAG : "1" ,//发起外访标识
	REPEATRESUTL_REFUSE	: "1",//风险客户，直接拒绝
	REPEATRESUTL_PASS	: "2",//没有异常，正常通过
	REPEATRESUTL_BACK_INSIDE	: "3",//有新的查重信息，需要重新去判定
	REPEATRESUTL_BACK_OUTSIDE	: "4",//有新的查重信息，需要重新去判定
	REPEATRESUTL_BACK_TEL_CHECK	: "5",//有新的查重信息，需要重新去判定
	REPEATRESUTL_BACK_OUT_VISIT_CHECK : "6"//外访核查标识
};
var StepNameJson={                                                              
	"信审复审":{Url:ctx + "/verify/recheck/",TabTitle:"复审"},  
	"信审终审":{Url:ctx + "/verify/groupCheck/",TabTitle:"终审"},  
	"信审高级终审":{Url:ctx + "/verify/finalCheck/",TabTitle:"高级终审"},
	"复议复审":{Url:ctx + "/reconsider/recheck/",TabTitle:"复议复审"},
	"复议终审":{Url:ctx + "/reconsider/finalCheck/",TabTitle:"复议终审"}
};
var CheckTypeJson={
	"0": "verify",  //信审
	"1": "reconsider"   //复议
};