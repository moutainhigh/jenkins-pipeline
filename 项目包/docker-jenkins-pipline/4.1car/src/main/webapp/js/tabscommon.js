var TABCONSTANT={
	NWSH : "内网审核",
	WWSH : "外网审核",
	ZXHC : "征信核查",
	ZLSH : "资料审核",
	DHZH : "电话照会",
	WFHC : "外访核查",
	NWSH_IFRAME : "nwsh_iframe",
	WWSH_IFRAME : "wwsh_iframe",
	ZXHC_IFRAME : "zxhc_iframe",
	ZLSH_IFRAME : "zlsh_iframe",
	DHZH_IFRAME : "dhzh_iframe",
	WFHC_IFRAME : "wfhc_iframe",
	COMMOM_IFRAME : "common_iframe",
	IFRAMEURL :'${workItem.bv.sunyardUrl }'
};
var AddTabsJson={
	"内网审核":{ID:"neiwangsh",FucName:"neiwangsh",Title:"内网审核",TabUrl:ctx + "/verify/InsideNet/list?"},  
	"外网审核":{ID:"waiwangsh",FucName:"waiwangsh",Title:"外网审核",TabUrl:ctx + "/verify/outside/getList?"},
	"征信核查":{ID:"zhengxin",FucName:"zhengxin",Title:"征信核查",TabUrl:ctx + "/credit/creditRisk/loadCreditReportPage?"},  
	"资料审核":{ID:"ziliaosh",FucName:"ziliaosh",Title:"资料审核",TabUrl:ctx + "/document/datacheck/loadDataCheckPage?"},
	"电话照会":{ID:"dianHuazh",FucName:"dianHuazh",Title:"电话照会",TabUrl:ctx + "/verify/telcheck/form?"},
	"外访核查":{ID:"waifanghc",FucName:"waifanghc",Title:"外访核查",TabUrl:ctx + "/verify/visitcheck/toVisitCheckForm?"}
};
// 返回tab拼接串
function tabHtml(tabName){
	return  "<li id='"+AddTabsJson[tabName].ID+"'  onclick='"+AddTabsJson[tabName].FucName+"(this)' style='width: 110px; opacity: 1; background-color: rgb(255, 255, 255);' class='jericho_tabs tab_unselect' datalink=''>" +                                                                                                                                                                               
		"<div class='tab_left' style='width:105px'><div class='tab_text' title='"+AddTabsJson[tabName].Title+"'     style='width:105px'>"+AddTabsJson[tabName].Title+"</div></div><div class='tab_right'></div></li>";
}
//tab添加
function tabHtmlAdd(tabName){
	 $(".tabs>ul").append(tabHtml(tabName));
}
// 返回taburl
function tabUrl(tabName){
	return AddTabsJson[tabName].TabUrl;
}
// 显示加载条目
function showLoader(tabIframeId){
	if($("#"+tabIframeId).attr("src")==''){
		$('#jerichotab_contentholder>.righttag').css({ display: 'block' });
	}
	 
}
// 移除加载条目
function removeLoader(){
	$('#jerichotab_contentholder>.righttag').css({ display: 'none' });
}

// iframe 静态html串 共用影响插件
function iframeHtml(){
	return  "<div class='ui-tabs-hide' id='iframeContent'>"+  
	        "<iframe style='float:left; margin-top:4px; width:59.1%;height:99.5%;' id='"+TABCONSTANT.COMMOM_IFRAME+"' scrolling='yes' frameborder='0' width='50%' height='100%' src=''>"+
			"</iframe> "+
			"<iframe style='float:right; width:40.8%;' id='"+TABCONSTANT.ZXHC_IFRAME+"' scrolling='yes' frameborder='0' width='50%' height='100%' src=''></iframe>"+
			"<iframe style='float:right; width:40.8%;display:none'  id='"+TABCONSTANT.ZLSH_IFRAME+"' scrolling='yes' frameborder='0' width='50%' height='100%' src=''></iframe>"+
			"<iframe style='float:right; width:40.8%;display:none'  id='"+TABCONSTANT.DHZH_IFRAME+"' scrolling='yes' frameborder='0' width='50%' height='100%' src=''></iframe>"+
			"<iframe style='float:right; width:40.8%;display:none'  id='"+TABCONSTANT.WFHC_IFRAME+"' scrolling='yes' frameborder='0' width='50%' height='100%' src=''></iframe>"+
			
			"</div> ";
}

// 初始化添加iframe
$(function(){
	$("#jerichotab_contentholder").append(iframeHtml());
	$("#jerichotab_contentholder").append(iframeHtmlOther());
})


// 为了防止一个页面打开多层的artDialog对话框。
function artDialogOne(url){
	var flag = false;
	$(".aui_dialog_wrap").each(function(){
		if($(this).find("iframe").attr("data-xurl") == url){
			flag = true;
		};
	})
	return flag;
}

// 非共用影响插件的的tab操作静态iframe
function iframeHtmlOther(){
	return "<div class='ui-tabs-hide' id='"+TABCONSTANT.NWSH_IFRAME+"'><iframe id='"+TABCONSTANT.NWSH_IFRAME+"1'  style='width: 100%;display: inline; height:100%;' src='' frameborder='0'></iframe></div>"+
	       "<div class='ui-tabs-hide' id='"+TABCONSTANT.WWSH_IFRAME+"'><iframe id='"+TABCONSTANT.WWSH_IFRAME+"1' style='width: 100%;display: inline; height:100%;' src='' frameborder='0'></iframe></div>";
}
//改变tabs状态
function changeTabStyle(tabIframeId,t,url){
	$(t).siblings("li").attr("class","jericho_tabs tab_unselect"); 
	$(t).attr("class","jericho_tabs tab_selected");  
	$("#iframeContent").siblings("div").attr("class","ui-tabs-hide");
	$("#righttag").attr("class","righttag");
	$("#iframeContent").removeClass('ui-tabs-hide');
	$("#"+tabIframeId).show();
	$("#"+tabIframeId).siblings("iframe").hide();
	$("#"+TABCONSTANT.COMMOM_IFRAME).show();
}
// 不是公用影像插件的共用方法
function tabCommonFucoOther(tabIframeId,t,url){
	showLoader(tabIframeId+"1");
	$(t).siblings("li").attr("class","jericho_tabs tab_unselect"); 
	$(t).attr("class","jericho_tabs tab_selected");  
	$("#"+tabIframeId).siblings("div").attr("class","ui-tabs-hide");
	$("#righttag").attr("class","righttag");
	$("#"+tabIframeId).removeClass('ui-tabs-hide');
	if($("#"+tabIframeId+"1").attr("src") == ''){
		$("#"+tabIframeId+"1").attr('src',url);
		$("#"+tabIframeId+"1").load(function(){
			if($("#"+tabIframeId+"1").attr("src") != ''){
			removeLoader();
			}
		});
		}
}

function wshRefrash(tabIframeId,url){
	if(tabIframeId == 'wwsh_iframe'){
		$("#"+tabIframeId+"1").attr('src',url);
		$("#"+tabIframeId+"1").load(function(){
			if($("#"+tabIframeId+"1").attr("src") != ''){
			removeLoader();
			}
		});
	}
}
/**
 * 无影像插件tab刷新并跳转
 */
function refreshMytab(tabIframeId){
	showLoader(tabIframeId);
	$("#"+tabIframeId+"1").load(function(){
		removeLoader();
	});
}


