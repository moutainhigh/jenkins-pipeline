var param = "";
var workParam = "";
var openList = new ArrayList();
var stepNameCode = "";
$(function () {		
	param = $('#param').serialize();
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
  
	if($("#outsideFlag").val() == CONSTANT.OUTSIDE_FLAG){
    	tabHtmlAdd(TABCONSTANT.WFHC);
    }
    stepNameCode = $("#stepName").val();
    if(Util.isEmpty(stepNameCode)){
    	tabs.add({
            url: StepNameJson[stepNameCode].Url+"initPage"+"?"+param,
            label: StepNameJson[stepNameCode].TabTitle
        });
    }
  	//退回协商  
    initBackConsult();
});

/***
 * 初始化退回协商页面
 */
function initBackConsult(){
	$.get(ctx+"/common/backconsult/toBackConsult",function(data){
		if($(data).find("p[class='login-in']").html() == undefined){
			$("#hideConsult").append(data);
		}else{
    		alert('未登录或登录超时。请重新登录，谢谢！');
    		window.location = "${ctx}";
    	}
	},"html");
}

// 显示退回协商弹窗
function showBackConsult() {
	art.dialog({
		title: "退回协商",
    	fixed:false,
    	lock: true,
    	width:450,
    	window: "top",
    	border: false,
    	content: $("#hideConsult").html(),
    	yesText: "保存",
    	noText: "取消",
    	yesFn: function(){
    		return saveBackConsult(this);
    	},
		noFn: function(){}
	});
}

/***
 * 退回协商码2级联动
 * @param target
 */
function consultChange(target){		
	var oneLevelCode = $(target).val();
	var indexFirst = $("option[value='"+oneLevelCode+"']").attr("val");	
	var secondCode = $(target).parents("form").find("#backSecondCode");
	if(indexFirst != ""){ //  如果为"" 则表明没有选择
		$.ajax({
			type:"POST",
			url: ctx + "/common/backconsult/asynToBackConsult",
			data:{id:indexFirst},
			success:function(data){
				secondCode.empty();
				if(data.length == "0"){
					secondCode.hide();
					secondCode.attr("class","select78");
				}else{
					secondCode.attr("class","select78 required");
					secondCode.show();
					secondCode.append("<option value=''>请选择</option>");
					$(data).each(function(index,da){
						secondCode.append("<option value="+da.negotiationCode+">"+da.negotiationName+"</option>");
						secondCode.attr("disabled", false);
					});
				}
				

			}
		});
	} else {
		secondCode.empty();
		secondCode.append("<option value=''>请选择</option>");
		secondCode.attr("disabled", true);
	}				
}

function saveBackConsult(target){
	var form = $(target.content()).find("#backform");		
	if(!checkForm(form)) {
		return false;
	}
	$.ajax({
		type:"POST",
		url: StepNameJson[stepNameCode].Url+"asynBackPrevious",
		data:param + "&" + workParam + "&" + form.serialize(),
		success:function(data){
			if(data=="true"){
				closeMainWindow();
			}
			else{
				alert("操作失败！请重新操作");
			}
		}
	}); 
}

function showHistory()
{
	if(!artDialogOne(ctx+'/common/pophistory/showHistory?'+param)){
		art.dialog.open(ctx+"/common/pophistory/showHistory?"+param,{
			title:"历史",width:"500px",height:"420px",left:850,top:80,window:"top",lock: false 
		});
	};
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
// 跳转到共借人
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
function closeMainWindow(){
	var win = top.window; 
	if (win.opener) win.opener.location.reload();
	closeCorborrowerWin();
	window.close();
}

function closeCorborrowerWin(){
	for (var i=0;i<openList.size();i++){
		openList.get(i).close();
	}
	openList.clear();
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
	tabCommonFuc(TABCONSTANT.DHZH_IFRAME,t,tabUrl(TABCONSTANT.DHZH)+param+'&editFlag=0');
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
function delay()
{
	$.ajax({
		type : "POST",
		data : $(document).find("#workParam").serialize(),
		url : StepNameJson[$("#stepName").val()].Url+"asynDelay",
		success : function(data) {
			checkDate(data);
			if(data==CONSTANT.BOOLEANTYPE_TRUE){
				closeMainWindow();
			}
			else{
				alert("延时处理失败!");
			}
		}
	});
}
function showView(){
	window.open(ctx+"/verify/view/initPage?loanCode="+$("#loanCode").val());
}