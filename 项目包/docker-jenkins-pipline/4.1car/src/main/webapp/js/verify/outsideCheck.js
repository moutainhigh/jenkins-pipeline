var param = "";
$(function(){ 
	param =  $(window.parent.document).find("#param").serialize();
	// 记录查重判定是否做完
	$("input[name='input']").blur(function(){
		var val = $(this).val();
		if(val == '' || val == undefined){
			$(this).val(par);
		}
	});
	$("input[name='input']").focus(function(){
		var par1 = $(this).val();
		if(par1 == '区号' || par1 == '电话号码'){
			$(this).val("");
			par =par1;
		}
	});
	$("#addRecord").click(function(){    // 添加电话录音信息
		var tar = $(this);
		var num = $(this).prev().val();
		var checked = $(this).prev().prev().prop("checked");
		var reg=/^[1-9]\d{2,}$/;
/*		if((areaNum.indexOf("0") != '0' && areaNum != '400') || areaNum.length < 3 || num.length < 3 || !reg.test(num)){
			art.dialog.tips("请正确输入电话信息");
			return ;
		}*/
		// 如果接听则记录对应的id信息
		// 并把信息保存到数据库中
		var id = $(window.parent.document).find("#param").find("input[name='relId']").val();
		var loanCode = $(window.parent.document).find("#loanCode").val();
		var dateTime =formatDate(new Date());
		var dictCheckType  = $(window.parent.document).find("#checkType").val();
		
		   $.ajax({
				type:"POST",
				url:ctx+"/verify/outside/asynSaveThis",
				data:"rGxId="+id+"&loanCode="+loanCode+"&dhlyxxCallTime="+dateTime+"&dhlyxxTel="+num+"&dictCheckType="+dictCheckType+"&localTel="+checked,
				dateType:'json',
				success:function(data){
					if(data == 'false'){
				       art.dialog.tips("请检测您输入的电话");
					}else{
						var obj = eval('(' + data + ')');
						var record = $("#telRecord").find("tr").clone(true,true);
						record.find("input[name='id']").val(obj.id);
						record.find("input[name='tel']").val(obj.dhlyxxTel);
						record.find("td:first").find("span").html(obj.dhlyxxTel);
						record.find("input[name='time']").val(formatDate(obj.dhlyxxCallTime));
						record.find("td:eq(1)").find("span").html(formatDate(obj.dhlyxxCallTime));
						$(tar).parents("table").append(record);
					} 
				}
			});
	});
    $('#inputSave').click(function(){ // 新增单位名称和单位电话
    	var wName = /^[\u4E00-\u9FA5\(\)a-zA-Z0-9]+$/;
    	if(!wName.test($("#workUnitname").val())){
    		addMyValidateCss($("#workUnitname"));
    		return;
    	}
    	if(!checkForm($("#searchForm"))){
    		return;
    	}
    	var divObj = $('#input');
    	var workValue = divObj.find("select[name='workInfoSource']").find("option:selected").val();
	   $.ajax({
			type:"POST",
			url:ctx+"/verify/outside/asynSaveWorkInfo",
			data:divObj.find("#searchForm").serialize()+"&"+param,
			dataType:"text",
			success:function(data){
				if(data == 'true'){
			       if(workValue == '01'){ // 如果添加的是来源与申请表，整个页面重新加载
			    	   refreshAll();
			    	   $('#input').modal('hide');
			       }else{
			    	   refreshThis();
			    	   $('#input').modal('hide');
			       }
			       art.dialog.tips("保存成功");
				}else{
					art.dialog.tips("保存失败");
				} 
			}
		}); 
    });
    $("#inputPhoneSave").click(function(){ // 新增电话信息保存
    	if(!myValidEmpty([$("#addPhoneInfo").parent("tr").find("input[name=workUnitTel]"),$("#addPhoneInfo").parent("tr").find("select[name=workTelSource]")])||!myValidSimplePhone($("#addPhoneInfo").parent("tr").find("input[name=workUnitTel]"))){
    		return;
    	}
    	var id = $("#inputPhone").data("workId");
    	var obj = $("#"+id);
    	var workValue = $(obj).prev().find("td:last").html();
    	var srcValue = $("#inputPhone").find("select").find("option:selected").val();
    	//异步提交方法
		var phoneInfo = $("#addPhoneInfo").parents("tr").clone(true,true);
		 $(phoneInfo).find("form").removeAttr("id");
	     $.ajax({
			type:"POST",
			url:ctx+"/verify/outside/asynSavePhoneInfo",
			dataType:"json",
			data:$("#addPhoneInfo").serialize(),
			success:function(data){
				if(data != null){	
				     if(workValue == '申请人提供'){
				    	 refreshAll();
				    	 $('#inputPhone').modal('hide');
				     }else{
				    	 refreshWorkTel();
				    	 $('#inputPhone').modal('hide');
				     }
				     art.dialog.tips("保存成功");
				}else{
					art.dialog.tips("保存失败");
				}
			}
		});   
    });  
   // $("#netCheck").find()
});
function hideDiv(str){
	$("#"+str).modal('hide');
}
function delThis(str,obj){
	if(confirm("确认要删除数据吗")){
		$.ajax({
			type:"POST",
			url:ctx+"/verify/outside/asynDelPhoneInfo",
			data:"id="+str,
			success:function(data){
				if(data == "true"){	
				     refreshAll();
				     art.dialog.tips("删除成功");
				}else{
					art.dialog.tips("删除失败");
				}
			}
		}); 
	}
}
function showDiv(str,id,obj){
	$("#"+str).modal('show');
	if(id != ''){
		$("#inputPhone").data("workId",id);
		$("#"+str).find("input[name='workId']").val(id);
		$(obj).parents("tr").attr("id",id);
	}
}
function updatePhone(obj){ // 单位名称更改并保存
	// 记录单位名称的行
	var trNow =  $(obj).parents("tr").next();
	// 记录单位名称 并让其显示在页面上
	var workName = trNow.find("input:first").val();
	var oldName = trNow.find("span").html();
	trNow.find("span").html(workName);
	var id = trNow.find("input:last").val();
	var loanCode = $(window.parent.document).find("#loanCode").val();
	var relId = $(window.parent.document).find("#relId").val();
	$.ajax({
		type:"POST",
		url:ctx+"/verify/outside/asynUpdateWork",
		data:{id:id,workUnitname:workName,oldWorkName:oldName,loanCode:loanCode,rCustomerCoborrowerId:relId},
		success:function(data){
			if(data == "true"){		
				 refreshAll();
			     art.dialog.tips("保存成功");
			}else{
				art.dialog.tips("保存失败");
			}
		}
	});
}

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
	window.open("http://www.gobaidugle.com/search2.php?keyword="+ data +"&one=baidu&two=so");	
}

/**
 * 一键网查中 网查按钮 查询 所有的内容
 * @param obj
 * @returns {Boolean}
 */
function openSearchTab(obj){ 
	var inputs = $(obj).parents("div:first").find("a");
	$(inputs).each(function(index,data){
		window.open("http://www.gobaidugle.com/search2.php?keyword="+$(data).html()+"&num=10&one=baidu&two=so");
	});
	return false;
}

/**
 * 单位名称信息删除
 * @param id
 * @param obj
 */
function delWorkInfo(id,obj){ 
	var workValue = $(obj).parents("tr").next().find("td:last").find("span").html()
	var workName = $(obj).parents("tr").next().find("td:first").find("span").html();
	var param = $(window.parent.document).find("#param").serialize();
	$.ajax({
        type: "post",
        url: ctx+"/verify/outside/asynDelete",
        data: {id:id,workName:workName,loanCode:loanCode,rCustomerCoborrowerId:relId},
        success: function (data) {
        	if(data == "true"){
        		refreshAll();
			    art.dialog.tips("删除成功");
        	}else{
        		art.dialog.tips("删除失败");
        	}
        }
    });
};

//初始化服务事件
   function InService()
    {    
	   sp.CallBarVisible=false;
       sp.CaptionVisible=false;
       sp.StatusBarVisible=true;
	   sp.AgentStateRegionVisible=false;
	   sp.CallControlRegionVisible=false;
       sp.SetLoginAgentState(1104);
       //sp.SetReadConfig();
   var retCode = sp.SPInit(txt_AgentID.value, 2);
       
   if (retCode > 1){
   	  alert(retCode);
   }else {
       sp.AgentLogin();
    }
   sp.MonDataStop();
   sp.MonDataStart();
   var caption=sp.GetCaptionText();
   str_AgentID =caption.split(',')[0];
   //alert(str_AgentID);
   str_StationNo=caption.split(',')[2];
   //alert(str_StationNo.length);
}
function refreshWorkTel(){
	 $.ajax({ // 更新单位信息部分
			type:"POST",
			url:ctx+"/verify/outside/getOutsideWorkInfo",
			dataType:"html",
			data:param,
			success:function(data){
	        	$("#newWorkInfo").empty();
				$("#newWorkInfo").append(data);
				 if(window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany){
			    	 window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany();
			     } 
			}
		}); 
}
/**
 * 刷新单位信息部分
 */
function refreshThis(){
	 $.ajax({ // 更新单位信息部分
			type:"POST",
			url:ctx+"/verify/outside/getOutsideWorkInfo",
			dataType:"html",
			data:param,
			success:function(data){
	        	$("#newWorkInfo").empty();
				$("#newWorkInfo").append(data);
				 if(window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany){
			    	 window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany();
			     } 
			}
		});  
	     $.ajax({// 更新一键网查部分
				type:"POST",
				url:ctx+"/verify/outside/getListNet",
				dataType:"html",
				data:param,
				success:function(data){
		        	$("#internetCheck").empty();
					$("#internetCheck").append(data);
				}
			}); 
}
/**
 * 刷新整个页面
 */
function refreshAll(){
    $.ajax({ 
		type:"POST",
		url:ctx+"/verify/outside/getListSecond",
		dataType:"html",
		data:param,
		success:function(data){
			$("#bigBody").empty();
			$("#bigBody").append(data);
			$('#input').modal('hide');
    	    $('#input').modal('hide');
		     if(window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany){
		    	 window.parent.document.getElementById("dhzh_iframe").contentWindow.refreshCompany();
		     }
		}
	});  
}

/**
 * 保存页面上所有的信息
 */
function saveAll(){
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
					art.dialog.tips('保存成功!');
				}else{
					art.dialog.tips('保存失败');
				}
			}
		});
}
function play(url,phoneNumber,key)
{
	url="http://www.w3school.com.cn/i/horse.ogg";
	window.parent.AudioPlay.getInstance().play(url,phoneNumber,key);
}
