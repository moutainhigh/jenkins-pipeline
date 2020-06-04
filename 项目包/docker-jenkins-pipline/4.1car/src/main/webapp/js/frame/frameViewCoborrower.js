var param = "";
var workParam = "";
$(function () {
	param = $('#param').serialize()
	workParam = $('#workParam').serialize();

    tabs = $('.tabs').cleverTabs({
        panelContainer: $('#jerichotab_contentholder')
    });
	tabs.add({
        url: ctx+"/verify/InsideNet/getView?"+param,
        label: "内网审核",
        selected: true
    });
	  tabs.add({
            url: ctx+"/verify/outside/getView?"+param,
            label: '外网审核'
        });

    tabHtmlAdd(TABCONSTANT.ZXHC);
	tabHtmlAdd(TABCONSTANT.ZLSH);
	tabHtmlAdd(TABCONSTANT.DHZH);
    if($("#outsideFlag").val()==CONSTANT.OUTSIDE_FLAG){
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

//征信报告
function zhengxin(t){
	tabCommonFuc(TABCONSTANT.ZXHC_IFRAME,t,tabUrl(TABCONSTANT.ZXHC)+param+"&editFlag=0");
}
//资料审核
function ziliaosh(t){
	tabCommonFuc(TABCONSTANT.ZLSH_IFRAME,t,tabUrl(TABCONSTANT.ZLSH)+param+"&editFlag=0");
}

//电话照会
function dianHuazh(t){
	tabCommonFuc(TABCONSTANT.DHZH_IFRAME,t,tabUrl(TABCONSTANT.DHZH)+param+"&editFlag=0");
}
//外访核查
function waifanghc(t){
	tabCommonFuc(TABCONSTANT.WFHC_IFRAME,t,ctx+'/verify/visitcheck/toVisitCheckView?'+param);
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