var param = "";
var workParam = "";
$(function () {
	param = $('#param').serialize()
	workParam ="&"+$('#workParam').serialize();

    tabs = $('.tabs').cleverTabs({
        panelContainer: $('#jerichotab_contentholder')
    });
    // 生成内网审核，外网审核tab标签
    tabHtmlAdd(TABCONSTANT.NWSH);
    tabHtmlAdd(TABCONSTANT.WWSH);
    // 初始化默认内网审核选中
    tabCommonFucoOther(TABCONSTANT.NWSH_IFRAME,$("#"+AddTabsJson[TABCONSTANT.NWSH].ID),tabUrl(TABCONSTANT.NWSH)+param);

	   
    tabHtmlAdd(TABCONSTANT.ZXHC);
	tabHtmlAdd(TABCONSTANT.ZLSH);
	tabHtmlAdd(TABCONSTANT.DHZH);
	
	var outsideFlag = $("#outsideFlag").val();
    if(outsideFlag == CONSTANT.OUTSIDE_FLAG){
    	tabHtmlAdd(TABCONSTANT.WFHC);
    }
});   
 
function showHistory()
{
	if(!artDialogOne(ctx+'/common/pophistory/showHistory?'+param)){
		art.dialog.open(ctx+"/common/pophistory/showHistory?"+param,{
			title:"历史",width:"500px",height:"420px",left:850,top:80,window:"top",lock: false 
		});
	};
}
function showApplyInfo()
{
	if(!artDialogOne(ctx+'/common/popview/showDetail?'+param)){
		art.dialog.open(ctx+"/common/popview/showDetail?"+param,{
			title:"查看",width:"500px",height:"420px",left:850,top:80,window:"top",lock: false 
		});
	};
}

function showtab()
{
	tabs.add({
        url: ctx+"/verify/check/initPage?"+param,
        label: "初审",
        selected: true
    });
}

var iframeFlag=0;
//公共加载方法
function iframeLoad(){
	if(iframeFlag==0){
    $("#"+TABCONSTANT.COMMOM_IFRAME).attr('src',$("#sunyardUrl").val());
	$("#"+TABCONSTANT.COMMOM_IFRAME).load(function() {
		iframeFlag=1;
	})
	}
}

//内网审核
function neiwangsh(t){
	tabCommonFucoOther(TABCONSTANT.NWSH_IFRAME,t,tabUrl(TABCONSTANT.NWSH)+param);
}
//外网审核
function waiwangsh(t){
	tabCommonFucoOther(TABCONSTANT.WWSH_IFRAME,t,tabUrl(TABCONSTANT.WWSH)+param);
}
//征信报告
function zhengxin(t){
	tabCommonFuc(TABCONSTANT.ZXHC_IFRAME,t,tabUrl(TABCONSTANT.ZXHC)+param);
}
//资料审核
function ziliaosh(t){
	tabCommonFuc(TABCONSTANT.ZLSH_IFRAME,t,tabUrl(TABCONSTANT.ZLSH)+param);
}

//电话照会
function dianHuazh(t){
	tabCommonFuc(TABCONSTANT.DHZH_IFRAME,t,tabUrl(TABCONSTANT.DHZH)+param);
}
//外访核查
function waifanghc(t){
	tabCommonFuc(TABCONSTANT.WFHC_IFRAME,t,tabUrl(TABCONSTANT.WFHC)+param+workParam);
}

function tabCommonFuc(tabIframeId,t,url){
    showLoader(tabIframeId);
    changeTabStyle(tabIframeId,t,url);
	iframeLoad();
	if($("#"+tabIframeId).attr("src")==''){
	$("#"+tabIframeId).attr('src',url);
	$("#"+tabIframeId).load(function(){
		removeLoader();
	});
	}
}