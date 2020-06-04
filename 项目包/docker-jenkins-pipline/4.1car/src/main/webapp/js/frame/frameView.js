var param = "";
var workParam = "";
var openList = new ArrayList();
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
	var outsideFlag = $("#outsideFlag").val();
    if(outsideFlag==CONSTANT.OUTSIDE_FLAG){
    	tabHtmlAdd(TABCONSTANT.WFHC);
    }
    
	tabs.add({
        url: ctx+"/verify/check/getView?"+param,
        label: "决策",
    });
 });   
function showHistory()
{
	if(!artDialogOne(ctx+'/common/pophistory/showHistory?'+param)){
		art.dialog.open(ctx+'/common/pophistory/showHistory?'+param,{
	    	title:"历史",width:"500px",height:"420px",left:810,top:75,window:"top",lock: false 
	   }); 
	}
}

function showView(){
	window.open(ctx+"/verify/view/initPage?loanCode="+$("#loanCode").val());
}

function showApplyInfo()
{
	if(!artDialogOne(ctx+'/common/popview/showDetail?'+param)){
		 art.dialog.open(ctx+'/common/popview/showDetail?'+param,{
		    	title:"查看",width:"500px",height:"420px",left:850,top:85,window:"top",lock: false 
		   }); 
	}
}

/***
 * 查看汇金修改历史
 */
function showChangeInfo()
{
	if(!artDialogOne(ctx+'/common/popchanges/showChangerInfo?'+param)){
		art.dialog.open(ctx+"/common/popchanges/showChangerInfo?"+param,{
			title:"汇金修改历史",width:"700px",height:"420px",left:850,top:80,window:"top",lock: false 
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
 
function showCorborrower(id,type,flag){
	for (var i=0;i<openList.size();i++){
		if(!Util.isEmpty(openList.get(i).name)){
			openList.remove(i);
			break;
		}
		if(openList.get(i).name===id){
			return;
		}
	}
	$("#coborrowerForm").find("input[name='id']").val(id);
	$("#coborrowerForm").find("input[name='checkTyp']").val(type);
	$("#coborrowerForm").find("input[name='outFlag']").val(flag);
	$("#coborrowerForm").attr("target",id);
	var newWin=window.open("about:blank",id);
	if(!openList.contains(newWin)){
		openList.add(newWin);
	}
	$("#coborrowerForm").submit();
}
function closeCorborrowerWin(){
	for (var i=0;i<openList.size();i++){
		openList.get(i).close();
	}
	openList.clear();
}