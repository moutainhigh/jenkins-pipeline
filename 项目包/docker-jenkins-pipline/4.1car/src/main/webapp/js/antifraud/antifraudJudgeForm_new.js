$(function(){
	// 顾客姓名
	var customerName = $(parent.document).find("input[name='customerName']").val();
	$("#loanCustomerName").val(customerName);
	//外部拉黑，模态框关闭按钮
    $('#consultCancel').click(function(){
    	$('#modal_Consult').modal('hide');
    	$("#modal_Consult .modal-body").empty();
    });
    
    //简单风险说明剩余字数
	var simpText=$("#simpTextare").val();
	var simpCounter=simpText.length;
	$(".simpTextareP").find("var").text(100-simpCounter);
	$("#simpTextare").keyup(function() {
		var simpText=$("#simpTextare").val();
		var simpCounter=simpText.length;
		$(".simpTextareP").find("var").text(100-simpCounter);
	});
	
	//详细风险说明剩余字数
	var text=$("#textare").val();
	var counter=text.length;
	$(".textareP").find("var").text(500-counter);
	$("#textare").keyup(function() {
		var text=$("#textare").val();
		var counter=text.length;
		$(".textareP").find("var").text(500-counter);
	});
})

// 黑名单点击次数
var balackOpenFlag=0;
// 灰名单点击次数
var grayOpenFlag=0;
// 欺诈案件编号是否正确标识
var fraudCodeformat;

//检查外部黑名单信息是否存在
function judge_checkExists(thisb){
	// 选中的复选框的值
	var value = $(thisb).val();
	$.ajax({
	    type: "post",
	    url:ctx+'/antifraud/antiFraudJudge/checkExists',
	    async: false,
	    data:{value:value},
	    success: function(data){
			if(data == 'true'){
				$(thisb).val("");
				alert(value+"-在黑名单数据库中已经存在，不能重复添加");
			}
	    }
	});  		
}
// 检查内部黑灰名单信息是否存在
function insideCheckExists(thisa){
	// 选中的复选框的值
	var value = $(thisa).val();
	$.ajax({
	    type: "post",
	    url:ctx+'/antifraud/antiFraudJudge/checkExists',
	    async: false,
	    data:{value:value},
	    success: function(data){
			if(data > 0){
				$(thisa).attr("checked",false);
				alert(value+"-在黑名单数据库中已经存在，不能重复添加");
			}
	    }
	});  		
}

// 清空表单
function resetForm(){
	$("#form")[0].reset();
}
// 打开外部黑名单模态框
function outBlackList(){
	art.dialog({
		title: "外部拉黑",
    	fixed:false,
    	lock: true,
    	width:850,
    	window: "top",
    	border: false,
    	content: $("#outBlackInfo").html(),
    	yesText: "保存",
    	noText: "取消",
    	yesFn: function(){
    		return saveOutBlack(this);
    	},
		noFn: function(){}
	});
}
//保存外部加黑
function saveOutBlack(target){
	var loanCode = $(parent.document).find("[name='loanCode']").val();
	var num = "0";
	$(target.content()).find(".input_text178").each(function(i,event){
		var val = $(event).val();
		if(val != ""){
			num = "1";
		}
	})
	if(num == "0"){
		return false;
	}
	if(!checkForm($(target.content()).find("#outBlackForm"))){
		return false;
	}
	$.ajax({
		type:"POST",
		url:ctx+'/antifraud/antiFraudJudge/saveOutBlackList',
		data:$(target.content()).find("#outBlackForm").serialize()+"&loanCode="+loanCode,
		success:function(data){
			addOutBlack(data);
		}
	}); 
}
//添加外部拉黑到外部拉黑区域
function addOutBlack(msg){
	var data = eval(msg);
	if(data != false){
		var html="";	
		for(var i=0; i<data.length;i++){
			html +='<tr id="'+data[i].id+'"><td  style="width:200px;">'+data[i].dictBlackTypeName+'</td><td>'+data[i].blackMsg+'</td><td style="width:44px;"><a  href="#" onclick="delOutBlack(\''+data[i].id+'\')" > 删除</a></td></tr>';
		}
		$("#addOutBlackTable").append(html); 		
	}else{
		art.dialog.alert("保存失败！");
	}
}
// 删除外部拉黑
function delOutBlack(id){
	$.ajax({
        type: "GET",
        url:ctx+'/antifraud/antiFraudJudge/delOutBlack',
        data:{id:id},
        success: function (data) {
			if(data == 'true'){
				$("#"+id).remove();
			}else{
				alert("删除失败！");
			}
        }
    });
}	
// 点击清白件
function oneWhiteRadio(){
	// 隐藏一级决策项弹出框
	$('#oneR').popover('hide');
	// 隐藏二级黑名单决策项弹出框
	$('#twoB').popover('hide');
	// 隐藏三级灰名单决策项弹出框
	$('#twoG').popover('hide');
	// 隐藏三级黑名单决策项弹出框
	$('#threeB').popover('hide');
	// 隐藏欺诈案件编号项弹出框
	$('#fraud').popover('hide');
	$("#fraud").attr("data-content","请填写欺诈案件编号");
	// 内部加黑复选框不可用
	$(".blackCheck").attr('disabled',true);
	// 内部加灰复选框不可用
	$(".grayCheck").attr('disabled',false);
	// 隐藏内部加黑部分
	$('#insideBlack').hide(); 
	// 隐藏内部加灰部分
	$('#insideGray').hide(); 
	// 二级灰名单单选框不可用
	$("input[name='antiFraudJudgeEx.twoGray']:checked").attr('disabled',true);
	// 二级黑名单单选框不可用
	$("input[name='antiFraudJudgeEx.twoBlack']:checked").attr('disabled',true);
	// 三级黑名单单选框不可用
	$("input[name='antiFraudJudgeEx.threeBlack']:checked").attr('disabled',true);
	// 欺诈案件编号不可用
	$("#fraCode").attr('disabled',true);
	// 隐藏二级决策项
	$("#two_r").hide();
	// 隐藏三级决策项
	$("#three_r").hide();
	// 隐藏欺诈案件编号
	$("#fraudCode").hide();	
	// 隐藏文本域
	$("#textarea").show();
}
// 点击黑名单
function oneBlackRadio(){
	// 隐藏一级决策项弹出框
	$('#oneR').popover('hide');
	// 隐藏二级黑名单决策项弹出框
	$('#twoB').popover('hide');
	// 隐藏二级灰名单决策项弹出框
	$('#twoG').popover('hide');
	// 隐藏三级黑名单决策项弹出框
	$('#threeB').popover('hide');
	// 隐藏欺诈案件编号项弹出框
	$('#fraud').popover('hide');
	$("#fraud").attr("data-content","请填写欺诈案件编号");
	// 获取 公共参数表单
	var param = $(parent.document).find("#param").serialize();
	// 显示二级决策项
	$("#two_r").show();
	// 二级灰名单单选框不可用
	$("input[name='antiFraudJudgeEx.twoGray']:checked").attr('disabled',true);
	// 二级黑名单单选框不可用
	$("input[name='antiFraudJudgeEx.twoBlack']:checked").attr('disabled',false);
	// 三级黑名单单选框不可用
	$("input[name='antiFraudJudgeEx.threeBlack']:checked").attr('disabled',false);
	// 二级灰名单部分隐藏
	$("input[name='antiFraudJudgeEx.twoGray']").parent().hide();
	// 二级黑名单部分显示
	$("input[name='antiFraudJudgeEx.twoBlack']").parent().show();
	// 获取选中的二级黑名单的决策码的id
	var tr=$("input[name='antiFraudJudgeEx.twoBlack']:checked").attr('val_id');
	if(tr != null){
		twoRadio2(tr)
	}
	// 欺诈案件编号可用
	$("#fraCode").attr('disabled',false);
	// 显示欺诈案件编号
	$("#fraudCode").show();		
	// 显示风险说明
	$("#textarea").show();
	// 判断是否是第一次点击
	if(balackOpenFlag == 0){
        $.ajax({
            type: "post",
            url:ctx+'/antifraud/antiFraudJudge/getBlackListOptionNew',
            data:$(parent.document).find("#param").serialize(),
            success: function(data){
            	// 将内部黑名单加黑项插入
            	$('#insideBlack').append(data);
            	// 内部加灰项不可用
        		$(".grayCheck").attr('disabled',true);
        		// 内部
        		//$(".blackCheck").attr('disabled',false);
        		// 隐藏内部灰名单部分
        		$('#insideGray').hide(); 
        		// 显示内部黑名单部分
        		$('#insideBlack').show(); 
        		// 黑名单点击次数标识
        		balackOpenFlag = 1;
            }
        });						
	}else{
		// 内部灰名单不可用
		$(".grayCheck").attr('disabled',true);
		// 内部
		$(".blackCheck").attr('disabled',false);
		// 隐藏内部灰名单
		$('#insideGray').hide(); 
		// 显示内部黑名单
		$('#insideBlack').show();   
	}
}
// 第二次点击黑名单，展示第一次操作结果
function twoRadio2(tr){
	// 隐藏第一次未选中的
	$("#three_r").find('p').eq(0).children("span").hide();
	// 显示第一次选中的
	$(".three_r"+tr).show();
	// 显示三级决策部分
	$("#three_r").show();
}	
// 点击灰名单
function oneGrayRadio(){
	// 隐藏一级决策项弹出框
	$('#oneR').popover('hide');
	// 隐藏二级黑名单决策项弹出框
	$('#twoB').popover('hide');
	// 隐藏二级灰名单决策项弹出框
	$('#twoG').popover('hide');
	// 隐藏三级黑名单决策项弹出框
	$('#threeB').popover('hide');
	// 隐藏欺诈案件编号决策项弹出框
	$('#fraud').popover('hide');
	$("#fraud").attr("data-content","请填写欺诈案件编号");
	// 获取公共参数表单
	var param = $(parent.document).find("#param").serialize();
	// 显示二级决策项
	$("#two_r").show();
	// 二级灰名单单选框不可用
	$("input[name='antiFraudJudgeEx.twoGray']:checked").attr('disabled',false);
	// 二级灰名单单选框可用
	$("input[name='antiFraudJudgeEx.twoBlack']:checked").attr('disabled',true);		
	// 三级灰名单单选框可用
	$("input[name='antiFraudJudgeEx.threeBlack']:checked").attr('disabled',true);
	// 欺诈案件编号可用
	$("#fraCode").attr('disabled',true);
	// 隐藏三级决策项部分
	$("input[name='antiFraudJudgeEx.threeBlack']").parent().hide();
	// 隐藏二级黑名单部分
	$("input[name='antiFraudJudgeEx.twoBlack']").parent().hide();
	// 显示二级灰名单部分
	$("input[name='antiFraudJudgeEx.twoGray']").parent().show();	
	// 显示欺诈说明
	$("#textarea").show();
	// 隐藏三级决策项部分
	$("#three_r").hide();
	// 隐藏欺诈案件编号
	$("#fraudCode").hide();
	// 判定是否是第一次点击
	if(grayOpenFlag == 0){
 	    $.ajax({
            type: "post",
            url:ctx+'/antifraud/antiFraudJudge/getGrayListOption',
            data:$(parent.document).find("#param").serialize(),
            success: function(data){
            	// 插入内部加灰部分
        		$('#insideGray').append(data);
        		// 内部加黑不可用
        		$(".blackCheck").attr('disabled',true);
        		// 内部加灰可用
        		$(".grayCheck").attr('disabled',false);
        		// 隐藏内部加黑
        		$('#insideBlack').hide(); 
        		// 显示内部加灰
        		$('#insideGray').show(); 
        		// 内部加灰点击次数标识
        		grayOpenFlag = 1;
            }
        });						
	}else{
		// 内部加黑不可用
		$(".blackCheck").attr('disabled',true);
		// 内部加灰可用
		$(".grayCheck").attr('disabled',false);
		// 隐藏内部加黑
		$('#insideBlack').hide(); 
		// 显示内部加灰
		$('#insideGray').show();  
	}				
}
// 点击回退
function oneBackRadio(){
	// 隐藏一级决策项弹出框
	$('#oneR').popover('hide');
	// 隐藏二级决黑名单策项弹出框
	$('#twoB').popover('hide');
	// 隐藏二级灰名单决策项弹出框
	$('#twoG').popover('hide');
	// 隐藏三级决策项弹出框
	$('#threeB').popover('hide');
	// 隐藏欺诈案件编号弹出框
	$('#fraud').popover('hide');
	$("#fraud").attr("data-content","请填写欺诈案件编号");
	// 显示内部加灰
	$('#insideGray').hide(); 
	// 隐藏内部加黑
	$('#insideBlack').hide();
	// 二级灰名单单选框不可用
	$("input[name='antiFraudJudgeEx.twoGray']:checked").attr('disabled',true);
	// 二级黑名单单选框不可用
	$("input[name='antiFraudJudgeEx.twoBlack']:checked").attr('disabled',true);
	// 三级黑名单单选框不可用
	$("input[name='antiFraudJudgeEx.threeBlack']:checked").attr('disabled',true);	
	// 欺诈案件编号不可用
	$("#fraCode").attr('disabled',true);
	// 隐藏二级决策项
	$("#two_r").hide();
	// 隐藏三级决策项
	$("#three_r").hide();
	// 隐藏欺诈案件编号
	$("#fraudCode").hide();
	// 显示风险说明
	$("#textarea").show();		
}
// 点击灰名单二级
function twoGrayRadio(){
	// 隐藏二级灰名单弹出框
	$('#twoG').popover('hide');
}	
// 点击黑名单二级弹出对应三级
function twoRadio(tr){
	// 隐藏二级黑名单弹出框
	$('#twoB').popover('hide');
	// 隐藏三级黑名单弹出框
	$('#threeB').popover('hide');
	// 获取点击二级决策码的id
	var val_id= $(tr).attr("val_id");
	// 显示对应的三级决策码
	$("#three_r").find('p').eq(0).children("span").hide();
	// 未显示的三级决策码隐藏
	$("input[name='antiFraudJudgeEx.threeBlack']").attr('checked',false);
	// 显示对应的三级决策项部分
	$(".three_r"+val_id).show();
	// 显示三级决策项
	$("#three_r").show();
}
// 点击黑名单三级
function threeBlackRadio(){
	// 隐藏三级决策项弹出框
	$('#threeB').popover('hide');
}
// 保存决策页面
function saveForm(){
	// 是否满足提交条件标识
	var statusFlag = true;
	// 获取共用参数form
	var workItem = "&" + $(parent.document).find("#workParam").serialize();
	// 获取共用参数form
	workItem += "&" + $(parent.document).find("#param").serialize();
	// 获取一级决策选择项
	var checkType = $("input[class='oneLevel']:checked").val();
	// 如果未选择一级决策项弹出提示框
	if(typeof(checkType) == 'undefined'){
		$('#oneR').popover('show');
		return ;
	}
	// 获取借款编码
	var loancode = $(parent.document).find("[name='loanCode']").val();
	// 判断决策是否选择清白，回退,触发规则是否全部解除、如果未解除提示请先解除规则
	if(checkType == $("#white").val() || checkType == $("#back").val() ){
		$.ajax({
		    type: "post",
		    url:ctx+'/antifraud/antiFraudJudge/getRelieveStatus',
		    async: false,
		    data:{loanCode:loancode},
		    success: function(data){
		    	if(data != 'true'){
		    		statusFlag = false;
		    		alert("请先解除所有规则！再提交！");
		    		return ;
		    	}
		    }
		}); 	
	}else{
		// 选择黑名单
		if(checkType == $("#black").val()){
			// 如果没有选中二级决策项
			if(!$(".twoB").is(":checked")){
				// 弹出框提示选择二级决策项
				$('#twoB').popover('show');
				return ;
			}
			// 如果二级决策项选中
			if($(".twoB").is(":checked")){
				// 如果三级决策项未选中
				if(!$(".threeB").is(":checked")){
					// 提示选中三级决策项
					$('#threeB').popover('show');
					return ;
				}
			}
			// 欺诈案件校验
			if($("#fraCode").val() == ""){
				$('#fraud').popover('show');
				return ;
			}
			// 判定欺诈编号格式是否正确
			if(fraudCodeformat == 0){
				$('#fraud').popover('show');
				return ;
			}
		}
		// 选中灰名单
		if(checkType == $("#gray").val()){
			// 如何灰名单二级决策项未选中
			if(!$(".twoG").is(":checked")){
				// 弹出框提示选中二级决策项
				$('#twoG').popover('show');
				return ;
			}
		}
	}
	if(!checkForm($("#form"))){
		return ;
	}
	
	var oneLevelFlag = $('.oneLevel:checked').attr("id");
	if(oneLevelFlag == "oneBlack"){
		var checkNum = $("#insideBlack .blackCheck:checked").length;
		if(checkNum == 0){
			art.dialog.alert('内部加黑项最少选择一项');
			return ;
		}
	}else if(oneLevelFlag == "oneGray"){
		var checkNum = $("#insideGray .grayCheck:checked").length;
		if(checkNum == 0){
			art.dialog.alert('内部加灰项最少选择一项');
			return ;
		}
	}
	//保存决策信息
	if(statusFlag == true){
	    $.ajax({
            type: "post",
            url:ctx+'/antifraud/manager/asynToCheck',
            async: false,
            data:$("#form").serialize()+workItem,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
            success: function(data){
            	if(data != false){
            		art.dialog.tips('保存成功!');
					parent.opener.location.reload();
					parent.close();	            		
            	}else{
            		art.dialog.alert("保存失败！出错啦");
            	}
            }
        }); 
	} 
}

// 校验欺诈案件编号
function checkFraudCode(obj){
	var code = $(obj).val();
	var fraudCode = code.substring(2);
	var val = fraudCode.substring(0,1);
	var regg = /^[A-Za-z]$/;
	var rega = /^[A-Za-z]{2}[0-9]{3}\d{6}\d+$/;
	var regb = /^[A-Za-z]{3}[0-9]{3}\d{6}\d+$/;
	if(regg.test(val)){
		if(!regb.test(code)){
			$("#fraud").attr("data-content","格式错误");
			fraudCodeformat = 0;
			$('#fraud').popover('show');
		}else{
			fraudCodeformat = 1;
			$('#fraud').popover('hide');
		}
	}else{
		if(!rega.test(code)){
			$("#fraud").attr("data-content","格式错误");
			fraudCodeformat = 0;
			$('#fraud').popover('show');
		}else{
			fraudCodeformat = 1;
			$('#fraud').popover('hide');
		}
	}
	
}
