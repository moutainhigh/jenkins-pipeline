var param = "";
$(function(){ 
	param =  $(window.parent.document).find("#param").serialize();
	// 所有的input、textarea 不可以输入全角
	/*$("input,textarea").blur(function(){
		var str = $(this).val();
		var len = str.length; 
	    for (var i = 0; i < len; i++) { 
	    	strCode=str.charCodeAt(i); 
	        if ((strCode>65248)||(strCode==12288)) { 
	        	art.dialog.tips("请勿输入全角");
	        	$(this).val("");
	        } 
	    } 
	});*/

	$("#addRecord").click(function(){    // 添加电话录音信息
		var tar = $(this);
		if(!myValidEmpty([$("#callNo")])){
			return false;
		}
		if(!myValidTelNumber($("#callNo"))){
			return false;
		}
		var num = $("#callNo").val();
		
		var checked = $(this).prev().prev().prop("checked");
		// 并把信息保存到数据库中
		var id = $(window.parent.document).find("#param").find("input[name='relId']").val();
		var loanCode = $(window.parent.document).find("#loanCode").val();
		var dateTime =new Date().Format("yyyy-MM-dd hh:mm:ss");
		var dictCheckType  = $(window.parent.document).find("#checkType").val();
		
	   $.ajax({
			type:"POST",
			url:ctx+"/verify/outside/asynSaveThis",
			data:"rGxId="+id+"&loanCode="+loanCode+"&dhlyxxCallTime="+dateTime+"&dhlyxxTel="+num+"&dictCheckType="+dictCheckType+"&localTel="+checked,
			dateType:'json',
			beforeSend: function(XMLHttpRequest){
            	waitingDialog.show("呼叫中...");
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
			success:function(data){
				if(data == 'false'){
			       art.dialog.tips("请检测您输入的电话");
				}else{
					var obj = eval('(' + data + ')');
					var record = $("#telRecord").find("tr").clone(true,true);
					autosize(record.find("textarea"));
					record.find("input[name='id']").val(obj.id);
					record.find("span[data='callNo']").html(obj.dhlyxxTel);
					record.find("span[data='callTime']").html(dateTime);
					$(tar).parents("table").append(record);
				} 
			}
		});
	});
	autosize(document.querySelectorAll("textarea"));
	if(window.parent.result ==-1){
		thisRed();
	};
	$('input:radio').change(function() {	
		$(this).parents("tr").find("font").css("color","");
	});
	
	if(window.parent.result != 0){
		thisRed();
	}
});

/**
 * 显示确定 取消 并让页面可以编辑
 * @param obj
 */
function showEdit(obj){
	$(obj).hide()
		  .nextAll()
		  .show();
	// 记录单位名称的行
	var trNow =  $(obj).parents("tr").next();
	trNow.find("span").hide();
	trNow.find("input:first").attr("type","text");
}

/**
 * 显示编辑 删除 并让页面不可以编辑
 * @param obj
 */
function hideEdit(obj){ 
	$(obj).hide()
          .next().hide();
	$(obj).prevAll().show();
	// 记录单位名称的行
	var trNow =  $(obj).parents("tr").next();
	trNow.find("span").show();
	trNow.find("input:first").val(trNow.find("span").html())
	trNow.find("input:first").attr("type","hidden");
    }
   
    function  inputNum(obj){
    	var maxCount = 500;  // 最高字数，这个值可以自己配置
	 var len = getStrLength($(obj).val()); // 当前输入的自数
	 var nowLen =eval( maxCount - len );
	 if(nowLen <= 0){
		 art.dialog.tips("最多输入500个字");
	 }
}

// 中文字符判断 
function getStrLength(str) { 
    var len = str.length; 
    var reLen = 0; 
    for (var i = 0; i < len; i++) {        
        if (str.charCodeAt(i) < 27 || str.charCodeAt(i) > 126) { 
            // 全角    
            reLen += 2; 
        } else { 
            reLen++; 
        } 
    } 
    return reLen;    
}
function clearVal(obj){
	$(obj).val("");
}


function openPrivate(url,content){//window.clipboardData.setData
	window.open(url);
}

/**
 * 格式化时间
 * @param strTime
 * @returns {String}
 */
function formatDate (strTime) {
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+
    date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
}
/**
 * 一键网查中所有的信息和对应的百度 搜搜网站
 * @param url
 * @param value
 */
function openCheck(url,value){
	window.open(url+value);
}

/**
 * 一键网查中的 每一个内容点击查询
 * @param data
 */
function openSearch(data){  
	window.open("http://www.gobaidugle.com/search3.php?keyword="+ data +"&one=baidu&two=sogou&three=so");	
}

/**
 * 一键网查中 网查按钮 查询 所有的内容
 * @param obj
 * @returns {Boolean}
 */
function openSearchTab(obj){ 
	var inputs = $(obj).parents("div:first").find("a");
	$(inputs).each(function(index,data){
		var str = $(data).attr("str");
		if(str == undefined){
			window.open("http://www.gobaidugle.com/search3.php?keyword="+$(data).html()+"&num=10&one=baidu&two=sogou&three=so");
		}else{
			window.open("http://www.gobaidugle.com/search3.php?keyword="+str+"&num=10&one=baidu&two=sogou&three=so");
		}
		
	});
	return false;
}


/**
 * 保存页面上所有的信息
 */
function saveAll(isCallBack,obj){
	showLoad(obj);
	// 一键网查保存
	var datas = "";  // 参数设定
	var trs = $("#workInfo").find("tr");
	var i = 0;
	$(trs).each(function(index,data){ // 获取所有的电话信息。用于更新
		var inputId = $(data).find("input[name$='id']").val();
		if(inputId != undefined){ // 如果含有id
			datas = datas + "&workTelNums["+i+"].id="+$(data).find("input[name$='id']").val();
			var radioValue = $(data).find("input[name$='workUnittelTrue']:checked").val();
			if(radioValue == undefined ){
				radioValue = "";
			}
			datas = datas + "&workTelNums["+i+"].workUnittelTrue="+radioValue;
			datas = datas + "&workTelNums["+i+"].exceptionRecord="+$(data).find("input[name$='exceptionRecord']").val();
			i ++;
		}
	});
	var data = $("#singleNet").serialize();
	// 获取所有的电话录音信息
	var recordTrs = $("#table2").find("tr:even");
	var recordData = "";
	var ind = 0;
	$(recordTrs).each(function(index,da){
		var id = $(da).find("input[name='id']").val();
		if(id != undefined){
			var se = $(da).find("select option:selected").val();
			var remark = $(da).next().find("textarea").val();
			recordData = recordData+"&recordData["+ind+"].id="+id+
			"&recordData["+ind+"].dhlyxxAnswerState="+se+
			"&recordData["+ind+"].dhlyxxAnswerInf="+remark;
			ind++;
		}
	});
	$.ajax({
		type:"POST",
		url:ctx+"/verify/outside/asynSaveNet",
		data:data+encodeURI(datas)+encodeURI(recordData),
		success:function(data){
			if(data=="true"){
				if(isCallBack == "true"){
					hideLoad(obj);
					art.dialog.tips('保存成功!');
					refreshCallPhone();
				}else{
					getReplaceInfo(changeCallBack);
				}
				
			}else{
				hideLoad(obj);
				art.dialog.tips('保存失败');
			}
		}
	});
}
function showLoad(obj){
	$(obj).val("Loading...");
	$(obj).attr("disabled",true);
}
function hideLoad(obj){
	$(obj).val("保存");
	$(obj).attr("disabled",false);
}
function play(phoneNumber,key)
{
	if(key!=''&&phoneNumber!=''){
		$.ajax({
			type:"POST",
			url:ctx+"/verify/outside/getRecord",
			data:{callId:key},
			dataType : 'json',
			success:function(data){
				if(data.url!=undefined)
				window.parent.AudioPlay.getInstance().play(data.url,phoneNumber,key);
			}
		});
	}else{
		art.dialog.tips("没有可以播放的音频文件！");
	}
}
/***
 * 弹出添加单位信息层
 */
function showUnitInfo(){
	art.dialog({
		title: "添加单位信息",
    	fixed:false,
    	lock: true,
    	width:350,
    	window: "top",
    	border: false,
    	content: $("#unitInfo").html(),
    	yesText: "保存",
    	noText: "取消",
    	yesFn: function(){
    		return saveUnitInfo(this);
    	},
		noFn: function(){}
	});
}
/***
 * 保存单位信息
 * @param target dialog对象
 * @returns {Boolean} false：验证失败;
 */
function saveUnitInfo(target){
	// 页面校验
	if(!checkForm($(target.content()).find("#unitInfoForm"))){
		return false;
	}
	// 查重项
	var workUnitname = $(target.content()).find("#workUnitname").val();
	var workUnitTel = $(target.content()).find("#workUnitTel").val();
	// 异步保存数据
	$.ajax({
		type:"POST",
		url:ctx+"/verify/outside/asynSaveWorkInfo",
		data:$(target.content()).find("#unitInfoForm").serialize()+"&"+param,
		dataType:"text",
		success:function(data){
			if(data == 'true' || data == 'repeate'){
				getReplaceInfo(changeWorkInfoCallBack);
		        if(data == 'repeate'){
		        	refreshLocalnet([workUnitname,workUnitTel]);
		        	art.dialog.tips("保存成功,新添加的信息有查重!");
		        }else{
		        	art.dialog.tips("保存成功");
		        }
			}else{
				art.dialog.tips("保存失败");
			} 
		}
	});
}
/***
 * 更新单位信息
 * @param workId 单位信息编码
 */
function updateWorkInfo(workId,beforeName){
	var unitName=$("#unitName_"+workId).val();
	if($.trim(unitName) == ""|| $.trim(unitName) == beforeName){
		return;
	}
	var loanCode = $(window.parent.document).find("#loanCode").val();;
	var rcustomercoborrowerid = $(window.parent.document).find("#relId").val();
	$.ajax({
		type:"POST",
		url:ctx+"/verify/outside/asynUpdateWork",
		data:{id:workId,workUnitname:unitName,loanCode:loanCode,rCustomerCoborrowerId:rcustomercoborrowerid},
		success:function(data){
			if(data == "true" || data == "repeate"){		
				getReplaceInfo(changeWorkInfoCallBack);
		        if(data == 'repeate'){
		        	refreshLocalnet([unitName]);
		        	art.dialog.tips("保存成功,新添加的信息有查重!");
		        }else{
		        	art.dialog.tips("保存成功");
		        }
		        refreshCallPhone();
			}else{
				art.dialog.tips("保存失败");
			}
		}
	});
}
/***
 * 删除单位信息
 * @param workId 单位信息编码
 */
function delWorkInfo(workId){ 
	if(confirm("确认要删除数据吗")){
		$.ajax({
	        type: "post",
	        url: ctx+"/verify/outside/asynDelete",
	        data:{id:workId,workName:$("#unitName_"+workId).val()},
	        success: function (data) {
	        	if(data == "true" || data == "repeate"){
	        		getReplaceInfo(changeWorkInfoCallBack);
			        if(data == 'repeate'){
			        	refreshLocalnet([]);
			        }
			        art.dialog.tips("删除成功");
	        	}else{
	        		art.dialog.tips("删除失败");
	        	}
	        }
	    });
	}
};
/***
 * 弹出添加单位电话层
 * @param unitId 单位编号
 */
function showUnitTel(unitId){
	$("#workId").val(unitId);//电话信息表用的单位信息
	art.dialog({
		title: "添加单位电话",
    	fixed:false,
    	lock: true,
    	width:350,
    	window: "top",
    	border: false,
    	content: $("#unitPhoneInfo").html(),
    	yesText: "保存",
    	noText: "取消",
    	yesFn: function(){
    		return saveUnitTel(this);
    	},
		noFn: function(){}
	});
}
/***
 * 保存单位电话
 * @param target dialog对象
 * @returns {Boolean} false：验证失败;
 */
function saveUnitTel(target){
	// 数据校验
	if(!checkForm($(target.content()).find("#phoneInfoForm"))){
		return false;
	}
	// 提交保存
    $.ajax({
		type:"POST",
		url:ctx+"/verify/outside/asynSavePhoneInfo",
		dataType:"json",
		data:$(target.content()).find("#phoneInfoForm").serializeArray(),
		success:function(data){
			if(data != null){	
				getReplaceInfo(changeWorkTelCallBack);
			    art.dialog.tips("保存成功");
			}else{
				art.dialog.tips("保存失败");
			}
		}
	}); 
}
/***
 * 删除单位电话
 * @param unitTelId 单位电话编号
 */
function delUnitTel(unitTelId){
	if(confirm("确认要删除数据吗")){
		$.ajax({
			type:"POST",
			url:ctx+"/verify/outside/asynDelPhoneInfo",
			data:"id="+unitTelId,
			success:function(data){
				if(data == "true"){	
					getReplaceInfo(changeWorkTelCallBack);
				    art.dialog.tips("删除成功");
				}else{
					art.dialog.tips("删除失败");
				}
			}
		}); 
	}
}
/***
 * AJAX获取外网最新的替换信息
 */
function getReplaceInfo(callback){
	$.ajax({
		type:"POST",
		url:ctx+"/verify/outside/getReplaceInfo",
		dataType:"html",
		data:param,
		success:function(data){
			if(typeof(callback) == "function"){
				callback(data);
			}
		}
	});
}
/**
 * 电话照会添加电话，更新外网部分页面
 */
function changeCallBack(data){
	refreshWorkInfo(data);
	//refreshNetWork(data);
	refreshOnekey(data);
	//autosize(document.querySelectorAll("textarea"));
}

/**
 * 添加单位电话刷新
 * @param data 最新的外网信息
 */
function changeWorkTelCallBack(data){
	refreshWorkInfo(data);
	refreshOnekey(data);
	refreshCallPhone();
	//autosize(document.querySelectorAll("textarea"));
}
/***
 * 添加单位信息刷新
 * @param data 最新的外网信息
 */
function changeWorkInfoCallBack(data){
	refreshWorkInfo(data);
	refreshNetWork(data);
	refreshOnekey(data);
	refreshCallPhone();
	//autosize(document.querySelectorAll("textarea"));
}
/***
 * 刷新单位信息区域
 * @param data 最新的单位信息
 */
function refreshWorkInfo(data){
	$("#workInfo").empty();
	$("#workInfo").append($(data).find("#workInfo"));
	autosize($("#workInfo").find("textarea"));
}
/***
 * 刷新专网查询区域
 * @param data 最新的专网查询信息
 */
function refreshNetWork(data){
	$("#netWorkInfo").empty();
	$("#netWorkInfo").append($(data).find("#netWorkInfo"));
	autosize($("#netWorkInfo").find("textarea"));
}
/**
 * 刷新一键网查区域
 * @param data 最新的一键网查信息
 */
function refreshOnekey(data){
	$("#oneKeyInfo").empty();
	$("#oneKeyInfo").append($(data).find("#oneKeyInfo"));
	autosize($("#oneKeyInfo").find("textarea"));
}
/**
 * 刷新电话照会中的单位信息
 */
function refreshCallPhone(){
	if(window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany){
		window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany();
    } 
}
/**
 * 刷新内网查重部分
 */
function refreshLocalnet(list){
	if(window.parent.document.getElementById("nwsh_iframe1").contentWindow.refreshThisTab){
		window.parent.document.getElementById("nwsh_iframe1").contentWindow.refreshThisTab(list);
    } 
}
function thisRed(){
	// 把未进行点选的行标红
	var radios =  $(document).find("tr:has(input)");
	if(radios != undefined && radios.length > 0){
		var name = ""; // 记录input radio 中name的值
		$(radios).each(function(index,data){
			var nameAttr = $(data).find("input[type='radio']").attr("name"); // 获取当前input的name
			if(name != nameAttr){
				name = $(data).find("input[type='radio']").attr("name");// 获取当前元素的name
				var radioObjs = $(data).find("input:radio[name='"+name+"']");
				var val=$(data).find("input:radio[name='"+name+"']:checked").val();
				if(val == null && radioObjs.length>0){ // 如果没有选中
					$(data).find("font").css("color","red");
				}
			}
		});
	}
}