var ZLSH_URL;
var ZXHC_URL;
var DHZH_URL;
var NWSH_URL;
var WWSH_URL;
var WFHC_URL;
var dhzhFlag = true;
var readOnly = false;
var param = "";
var result=0;
var workParam = "";
var openList = new ArrayList();
$(function () {		
	param = $('#param').serialize();
	workParam ="&"+$('#workParam').serialize();
    tabs = $('.tabs').cleverTabs({
        panelContainer: $('#jerichotab_contentholder')
    });
    
    // 初始化tab url
    initUrl(); 	 
    // 生成内网审核，外网审核tab标签
    tabHtmlAdd(TABCONSTANT.NWSH);
    tabHtmlAdd(TABCONSTANT.WWSH);
    // 初始化默认内网审核选中
    tabCommonFucoOther(TABCONSTANT.NWSH_IFRAME,$("#"+AddTabsJson[TABCONSTANT.NWSH].ID),tabUrl(TABCONSTANT.NWSH)+param);

    // 生成征信核查，资料审核，电话照会tab标签
	tabHtmlAdd(TABCONSTANT.ZXHC);
	tabHtmlAdd(TABCONSTANT.ZLSH);
	tabHtmlAdd(TABCONSTANT.DHZH);

    if($("#outsideFlag").val() == CONSTANT.OUTSIDE_FLAG){
    	// 生成外访核查tab页
    	tabHtmlAdd(TABCONSTANT.WFHC);
    }
    initAntifraud();//初始化反欺诈DIV
    initAbandon();  //初始化客户放弃DIV
});

 //初始化资料审核，征信核查，电话照会，内网审核，外网审核url
function initUrl(){
	 ZLSH_URL = tabUrl(TABCONSTANT.ZLSH)+param;
     ZXHC_URL = tabUrl(TABCONSTANT.ZXHC)+param;
     DHZH_URL = tabUrl(TABCONSTANT.DHZH)+param;
     WFHC_URL = tabUrl(TABCONSTANT.WFHC)+param+workParam;
     NWSH_URL = ctx+"/verify/InsideNet/list?"+param;
     WWSH_URL = ctx+"/verify/outside/getList?"+param;
}
/***
 * 初始化提报反欺诈页面
 */
function initAntifraud(){
	$.get(ctx+"/common/report/getReportLevelOne",function(data){
		if($(data).find("p[class='login-in']").html() == undefined){
			$("#hideAntifraud").append(data);
		}else{
    		alert('未登录或登录超时。请重新登录，谢谢！');
    		window.location = "${ctx}";
    	}
	},"html");
}
/***
 * 提报反欺诈按钮事件
 */
function showAntifraud()
{	
	art.dialog({
		title: "提报反欺诈",
    	fixed:false,
    	lock: true,
    	width:450,
    	window: "top",
    	border: false,
    	content: $("#hideAntifraud").html(),
    	yesText: "保存",
    	noText: "取消",
    	yesFn: function(){
    		return saveAntifraud(this);
    	},
		noFn: function(){}
	});
}
/***
 * 提报反欺诈2级联动
 * @param target
 */
function consultChange(target){		
	var oneLevelCode = $(target).val();
	var oneRrefuseId = $("option[value='"+oneLevelCode+"']").attr("refuseId");
	var secondCode = $(target).parent().find("#backSecondCode");
	if(oneLevelCode != ""){ //  如果为 "" 则表明没有选择
		$.ajax({
			type:"POST",
			url: ctx + "/common/report/getReportLevelTwo",
			data:{id:oneRrefuseId},
			success:function(data){
				secondCode.empty();
				secondCode.append("<option value=''>请选择</option>");
				$(data).each(function(index,da){
					secondCode.append("<option value="+da.refuseCode+">"+da.refuseName+"</option>");
					secondCode.attr("disabled", false);
				});
			}
		});
	} else {
		secondCode.empty();
		secondCode.append("<option value=''>请选择</option>");
		secondCode.attr("disabled", true);
	}				
}
/***
 * 保存反欺诈数据
 * @param target 反欺诈dialog对象
 * @returns {Boolean}
 */
function saveAntifraud(target){
	// 页面校验
	if(!checkForm($(target.content()).find("#antifraudForm"))){
		return false;
	}
	$.ajax({
		type : "POST",
		data : param+workParam+"&"+$(target.content()).find("#antifraudForm").serialize(),
		url : ctx+"/"+CheckTypeJson[$("#checkType").val()]+"/check/asynToAntifraud",
		success : function(data) {
			if(data=="true"){
				closeMainWindow();
			}else{
				alert("操作失败！请重新操作");
			}
		}
	});
}
/***
 * 回退门店按钮事件
 */
function showBackStore()
{
	storeInfo();
}
/**
 *  显示回退门店的详细信息
 */
function storeInfo(){
	art.dialog.open(ctx+"/common/backStore/goForm",{
		title:"回退门店",width:"800px",height:"500px",window:"top",lock: false,border: false,
		yesFn: function(){
    		return saveBackStore(this);
    	},
		noFn: function(){}
	});
}
/***
 * 保存回退门店提交信息
 * @param target 回退门店dialog对象
 * @returns {Boolean}
 */
function saveBackStore(target){
	var topForm=$(target.content()).find("iframe")[0].contentWindow.$("#backStoreForm");
	var checkBoxs = topForm.find("input[type='checkbox']:checked");
	if(checkBoxs.length < 1){
		art.dialog.tips("请选择回退清单!");
		return false;
	}
	var text = topForm.find("#feedBackText").val();
	if(text == "" || text == null){
		art.dialog.tips("请填写回馈意见！");
		return false;
	}
	var params = "&auditBack.json=";
	for(var i = 0; i < checkBoxs.length; i++){
		if(i == 0){
			params += $(checkBoxs[i]).val();
		}else{
			params += ","+$(checkBoxs[i]).val();
		}
	}
	$.ajax({
		type : "POST",
		data : param+workParam+"&"+topForm.serialize() + params,
		url : ctx+"/"+CheckTypeJson[$("#checkType").val()]+"/check/asynToBackStore",
		success : function(data) {
			//checkDate(data);
			if(data=="true"){
				closeMainWindow();
			}
			else{
				alert("操作失败！请重新操作");
			}
		}
	});
}
/***
 * 初始化客户放弃页面
 */
function initAbandon(){
	$.get(ctx+"/common/abandon/initCustomerAbandon",function(data){
		if($(data).find("p[class='login-in']").html() == undefined){
			$("#hideAbandon").append(data);
		}else{
    		alert('未登录或登录超时。请重新登录，谢谢！');
    		window.location = "${ctx}";
    	}
	},"html");
}
/***
 * 客户放弃按钮事件
 */
function showAbandon()
{
	art.dialog({
		title: "客户放弃",
    	fixed:false,
    	lock: true,
    	width:450,
    	window: "top",
    	border: false,
    	content: $("#hideAbandon").html(),
    	yesText: "保存",
    	noText: "取消",
    	yesFn: function(){
    		return saveAbandon(this);
    	},
		noFn: function(){}
	});
}
/***
 * 保存客户放弃提交信息
 * @param target 客户放弃dialog对象
 */
function saveAbandon(target){
	//页面校验
	if(!checkForm($(target.content()).find("#abandonForm"))){
		return false;
	}
	$.ajax({
			type : "POST",
			data : param+workParam+"&"+$(target.content()).find("#abandonForm").serialize(),
			url : ctx+"/"+CheckTypeJson[$("#checkType").val()]+"/check/asynToAbandon",
			success : function(data) {
				if(data=="true"){
					closeMainWindow();
				}
				else{
					alert("操作失败！请重新操作");
				}
			}
	});
}
/***
 * 客户放弃码2级联动
 * @param target
 */
function abandonChange(target){
	var id = $(target).find("option:selected").attr("mark");
	var indexFirst = $(target).val();
	var sencodCode=$(target).parent().find("#abandonSecondCode"); 
	var thirdCode=$(target).parent().find("#abandonThirdCode"); 
	if (indexFirst != "") { // 如果为-1 则表明没有选择
		$.ajax({
			type : "POST",
			url : ctx + "/common/abandon/getSecondCode",
			data : "id=" + id,
			success : function(data) {
				var resultObj = eval("(" + data + ")");
				sencodCode.empty();
				sencodCode.append("<option value='-1'>请选择</option>");
				$(resultObj).each(function(index, da) {
					sencodCode.append("<option  mark=" + da.id
									+ " value=" + da.giveupCode + ">"
									+ da.giveupName + "</option>");
					sencodCode.attr("disabled", false);
				});
			}
		});
	} else {
		sencodCode.empty();
		sencodCode.append("<option value='-1'>请选择</option>");
		sencodCode.attr("disabled", true);
		thirdCode.empty();
		thirdCode.append("<option value='-1'>请选择</option>");
		thirdCode.attr("disabled", true);
	}
}
/***
 * 客户放弃码3级联动
 * @param target
 */
function abandonSecondChange(target){
    var id = $(target).find("option:selected").attr("mark");
	var sencodCode = $(target).val();
	var thirdCode=$(target).parent().find("#abandonThirdCode"); 
	if (sencodCode != "") { // 如果为-1 则表明没有选择
		$.ajax({
			type : "POST",
			url : ctx + "/common/abandon/getSecondCode",
			data : "id=" + id,
			success : function(data) {
				var resultObj = eval("(" + data + ")");
				thirdCode.empty();
				thirdCode.append("<option value='-1'>请选择</option>");
				$(resultObj).each(function(index, da) {
					thirdCode.append("<option value="
											+ da.giveupCode + ">"
											+ da.giveupName
											+ "</option>");
				});
			}
		});
	    thirdCode.attr("disabled", false);
	} else {
		thirdCode.empty();
		thirdCode.append("<option value='-1'>请选择</option>");
		thirdCode.attr("disabled", true);
	}
}
/***
 * 外访按钮事件
 */
function showVisit()
{
	visitInfo();
}
/**
 * 显示外访清单信息
 */
function visitInfo(){
	art.dialog.open(ctx+"/check/outVisit/goForm?loanCode="+$("#loanCode").val(),{
		title:"发起外访",width:"700px",height:"480px",window:"top",lock: false,border: false,scrolling: false,
		yesFn: function(){
    		return saveVisit(this);
    	},
		noFn: function(){}
	});
}
function saveVisit(target){
	var form = $(target.content()).find("iframe")[0].contentWindow.$("#outvisitForm");	
	var checkBoxs = form.find("input[type='checkbox']:checked");	
	if(checkBoxs.length == 0) {
		alert("请勾选外访类型！");
		return false;
	}
	//必须项校验
	if(!checkForm(form)){
		return false;
	}
	//发送外访请求
	$.ajax({
		type : "POST",
		data : form.serialize() + "&" +  $(document).find("form").serialize(),
		url : ctx+"/verify/check/asynToVisit",
		success : function(data) {
			if(data==true||data=="true"){
				closeMainWindow();
			}
		}
	});
}
/***
 * 查看历史信息
 */
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
/***
 * 申请信息查看
 */
function showApplyInfo()
{
	if(!artDialogOne(ctx+'/common/popview/showDetail?'+param)){
		art.dialog.open(ctx+"/common/popview/showDetail?"+param,{
			title:"查看",width:"500px",height:"420px",left:850,top:80,window:"top",lock: false 
		});
	};
}

function showView(){
	window.open(ctx+"/verify/view/initPage?loanCode="+$("#loanCode").val());
}

// 重新审核方法
function  reApply(){
	closeCorborrowerWin();
	// 根据url获取指定的tab对象
	var tab = tabs.getTabByUrl(ctx + "/"+CheckTypeJson[$("#checkType").val()]+"/check/initPage?"+param+"&result="+result);
	// 删除tab页签
	tab.killTab(); 
	// 将所有页面切换到可编辑状态
	changeToEdit()
	// 选中内网审核
	tabCommonFucoOther(TABCONSTANT.NWSH_IFRAME,$("#"+AddTabsJson[TABCONSTANT.NWSH].ID),tabUrl(TABCONSTANT.NWSH)+param);
	// 重新审核按钮隐藏
	$("#reApply").hide();
	// 决策按钮显示
	$("#showtab").show();
	readOnly = false;
}

/**
 * 显示决策页签信息
 * @param flag
 */
function showtab(flag){
	result = -1;
	// 外访核查标识 1为发过外访
	var outSideFlag = $('#outsideFlag').val();
	closeCorborrowerWin();
	if(flag == '0' || flag == '1'){ // 门店放弃，门店拒绝状态
		if(flag == '0'){
			art.dialog.tips("门店已放弃,此单只能拒借");
		}else if(flag == '1'){
			art.dialog.tips("门店已拒绝,此单只能拒借");
		}
		inteLocal(showApprove);
	}else{  // 正常状态，门店未结束流程
		art.dialog({
		    title: "提示",
		    content: "请确认决策结果",
		    width: '20em',
		    noText:"拒借",
		    yesText:"通过",
		    yesFn: function(){ // getResultRefuse
		    	$.ajax({
					type:"POST",
					url:ctx+"/verify/innerRepeat/getResult",
					data:param+"&outsideFlag="+outSideFlag,
			        beforeSend: function(XMLHttpRequest){
			        	waitingDialog.show();
			        },
			        complete: function(XMLHttpRequest, textStatus){
			        	waitingDialog.hide();
			        },
					success:function(data){
						checkDate(data);
						if(data == CONSTANT.REPEATRESUTL_BACK_OUTSIDE){// 重新审核 外网
							art.dialog.tips("请检测【外网】判定信息");
							// 选中外网审核
						    tabCommonFucoOther(TABCONSTANT.WWSH_IFRAME,$("#"+AddTabsJson[TABCONSTANT.WWSH].ID),WWSH_URL);
							if(document.getElementById("wwsh_iframe1").contentWindow.thisRed){
								document.getElementById("wwsh_iframe1").contentWindow.thisRed();
						    } 
						    readOnly = false;
						}else if(data == CONSTANT.REPEATRESUTL_BACK_TEL_CHECK){// 重新审核 电话照会
							art.dialog.tips("请检测【电话照会】本人核实");
							if(document.getElementById("dhzh_iframe").contentWindow.requiredRed){
								document.getElementById("dhzh_iframe").contentWindow.requiredRed();
						    } 
							// 选中电话照会
							tabCommonFuc(TABCONSTANT.DHZH_IFRAME,$("#"+AddTabsJson[TABCONSTANT.DHZH].ID),DHZH_URL);
						    readOnly = false;
						}else if(data == CONSTANT.REPEATRESUTL_BACK_OUT_VISIT_CHECK){// 重新审核外访核查
							art.dialog.tips("请检测【外访核查】判定信息");
							// 选中外访核查 
							tabCommonFuc(TABCONSTANT.WFHC_IFRAME,$("#"+AddTabsJson[TABCONSTANT.WFHC].ID),WFHC_URL);
						    readOnly = false;
						}else if(data == CONSTANT.REPEATRESUTL_PASS){//通过 选择的通过
							result = 0;
							tabs.add({
					            url: ctx + "/"+CheckTypeJson[$("#checkType").val()]+"/check/initPage?"+param+"&result="+result,
					            label: $("#stepName").val(),
					            selected: true
					        });
							// 重新审核按钮显示
							$("#reApply").show();
							// 决策按钮隐藏
							$("#showtab").hide();
							// 切换到不可编辑的页面
							readOnly = true;
							changeToDetail();
						}else{
							alert("系统异常中。。。。。");
						}
					}
			  }); 
		    },
		    noFn: function(){
		    	inteLocal(showApprove);
		    }
		});
	}
}

// 将所有页面切换到不可编辑url
function changeToDetail(){
	ZLSH_URL = tabUrl(TABCONSTANT.ZLSH)+param+"&editFlag=0";
	$("#"+TABCONSTANT.ZLSH_IFRAME).attr("src","");
	ZXHC_URL = tabUrl(TABCONSTANT.ZXHC)+param+"&editFlag=0";
	$("#"+TABCONSTANT.ZXHC_IFRAME).attr("src","");
	DHZH_URL = tabUrl(TABCONSTANT.DHZH)+param+"&editFlag=0";
	$("#"+TABCONSTANT.DHZH_IFRAME).attr("src","");
	// 外访核查
	WFHC_URL = tabUrl(TABCONSTANT.WFHC)+param+workParam+"&editFlag=0";
	$("#"+TABCONSTANT.WFHC_IFRAME).attr("src","");
	// 内网审核
	NWSH_URL = ctx+"/verify/InsideNet/getView?"+param;
	$("#"+TABCONSTANT.NWSH_IFRAME+"1").attr("src","");
	// 外网审核
	WWSH_URL = ctx+"/verify/outside/getView?"+param;
	$("#"+TABCONSTANT.WWSH_IFRAME+"1").attr("src","");
}
// 将所有页面切换到可编辑的url
function changeToEdit(){
	ZLSH_URL = tabUrl(TABCONSTANT.ZLSH)+param;
	$("#"+TABCONSTANT.ZLSH_IFRAME).attr("src","");
	ZXHC_URL = tabUrl(TABCONSTANT.ZXHC)+param;
	$("#"+TABCONSTANT.ZXHC_IFRAME).attr("src","");
	DHZH_URL = tabUrl(TABCONSTANT.DHZH)+param;
	$("#"+TABCONSTANT.DHZH_IFRAME).attr("src","");
	// 外访核查
	WFHC_URL = tabUrl(TABCONSTANT.WFHC)+param+workParam;
	$("#"+TABCONSTANT.WFHC_IFRAME).attr("src","");
	// 内网审核
	NWSH_URL = ctx+"/verify/InsideNet/list?"+param;
	$("#"+TABCONSTANT.NWSH_IFRAME+"1").attr("src","");
	// 外网审核
	WWSH_URL = ctx+"/verify/outside/getList?"+param;
	$("#"+TABCONSTANT.WWSH_IFRAME+"1").attr("src","");
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
	tabCommonFucoOther(TABCONSTANT.NWSH_IFRAME,t,NWSH_URL);
}
//外网审核
function waiwangsh(t){
	tabCommonFucoOther(TABCONSTANT.WWSH_IFRAME,t,WWSH_URL);
}

//征信报告
function zhengxin(t){
	tabCommonFuc(TABCONSTANT.ZXHC_IFRAME,t,ZXHC_URL);
}
//资料审核
function ziliaosh(t){
	tabCommonFuc(TABCONSTANT.ZLSH_IFRAME,t,ZLSH_URL);
}

//电话照会
function dianHuazh(t){
	tabCommonFuc(TABCONSTANT.DHZH_IFRAME,t,DHZH_URL);
}
//外访核查
function waifanghc(t){
	tabCommonFuc(TABCONSTANT.WFHC_IFRAME,t,WFHC_URL);
}

function tabCommonFuc(tabIframeId,t,url){
	showLoader(tabIframeId);
	changeTabStyle(tabIframeId,t,url);
	iframeLoad();
	
	if($("#"+tabIframeId).attr("src")==''){
		$("#"+tabIframeId).attr('src',url);
		$("#"+tabIframeId).load(function(){
			if($("#"+tabIframeId).attr("src") != ''){
			removeLoader();
			}
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
	$("#coborrowerForm").attr("action",readOnly?ctx+"/coborrower/initCoborrower":ctx+"/coborrower/initPage");
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
/**
 * 延时处理
 */
function delay()
{
	$.ajax({
		type : "POST",
		data : $(document).find("#workParam").serialize(),
		url : ctx+"/"+CheckTypeJson[$("#checkType").val()]+"/check/asynDelay",
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
/**
 * 内网，外网审核判定，并回调相应的函数
 */
function inteLocal(callBack){
	result = 2;
	$.ajax({
		type:"POST",
		url:ctx+"/verify/innerRepeat/getResultRefuse",
		data:param,
        beforeSend: function(XMLHttpRequest){
        	waitingDialog.show();
        },
        complete: function(XMLHttpRequest, textStatus){
        	waitingDialog.hide();
        },
		success:function(data){
			checkDate(data);
			if(data == CONSTANT.REPEATRESUTL_BACK_TEL_CHECK){// 重新审核 外网
				 art.dialog.tips("请检测【电话照会】本人核实");
				  // 选中电话照会页签
				 tabCommonFuc(TABCONSTANT.DHZH_IFRAME,$("#"+AddTabsJson[TABCONSTANT.DHZH].ID),DHZH_URL);
					if(document.getElementById("dhzh_iframe").contentWindow.thisRed){
						document.getElementById("dhzh_iframe").contentWindow.thisRed();
				    } 
			      // 刷新外网审核
			     readOnly = false;
			}else if(data == CONSTANT.REPEATRESUTL_PASS){//通过  选择的拒借
				callBack();
			}else{
				alert("系统异常中。。。。。");
			}
		}
  }); 
}
function refreshLocal(){
	document.getElementById("nwsh_iframe1").contentWindow.refreshThisTab();
}
/**
 * 显示拒绝相关信息
 */
function showApprove(){
	result = 1;
	tabs.add({
        url: ctx + "/"+CheckTypeJson[$("#checkType").val()]+"/check/initPage?"+param+"&result="+result,
        label: $("#stepName").val(),
        selected: true
    });
	// 重新审核按钮显示
	$("#reApply").show();
	// 决策按钮隐藏
	$("#showtab").hide();
	// 切换到不可编辑的页面
	readOnly = true;
	changeToDetail();
}