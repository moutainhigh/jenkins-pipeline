$(function(){
	// 清白件
	type2 = $("#white").val();
	// 黑名单
	type0 = $("#black").val();
	// 灰名单
	type1 = $("#gray").val();
	// 退回
	type3 = $("#back").val();
	// 获取一级决策项
	var dictCaseResult = $("#dictCaseResult").val();
	// 获取二级决策项
	var dictJudgeSecondCode = $("#dictJudgeSecondCode").val();
	// 获取三级决策项
	var dictJudgeThredCode = $("#dictJudgeThredCode").val();
	// 进件来源
	var dictIntoSource = $("#dictIntoSource").val();
	// 判断显示清白还是回退 
	if(dictCaseResult != ""){
		if(dictCaseResult == '0' || dictCaseResult == '1' || dictCaseResult == '2'){
			$("#oneBack").hide();
		}else{
			$("#oneWhite").hide();
		}
	}else{
		$(".oneLevel").attr("disabled",true);
	}
	if(dictCaseResult == type0){
		// 选中一级决策项
		$("input[name='oneLevelBlack']").attr("checked","checked");
		oneBlackRadioView();
		// 选中二级决策项
		$(".twoBlack[value="+dictJudgeSecondCode+"]").attr("checked","checked");
		// 触发二级决策项选中函数
		$(".twoBlack[value="+dictJudgeSecondCode+"]").click();
		// 选中三级决策项
		$(".threeBlack[value="+dictJudgeThredCode+"]").attr("checked","checked");
		oneBlackRadio();
		// 一级决策项不可用
		$(".oneLevel[checked !='checked']").attr("disabled",true);
		// 二级决策项不可用
		$(".twoBlack[checked !='checked']").attr("disabled",true);
		// 三级决策项不可用
		$(".threeBlack[checked !='checked']").attr("disabled",true);
	// 如果是灰名单
	}else if(dictCaseResult == type1){
		// 选中一级灰名单
		$("input[name='oneLevelGray']").attr("checked","checked");
		oneGrayRadioView();
		// 选中灰名单二级
		$(".twoGray[value="+dictJudgeSecondCode+"]").attr("checked","checked");
		// 一级决策项不可用 
		$(".oneLevel[checked !='checked']").attr("disabled",true);
		// 灰名单二级决策项不可用
		$(".twoGray[checked !='checked']").attr("disabled",true);
		oneGrayRadio();
	// 如果是清白件
	}else if(dictCaseResult == type2){
		// 选中一级清白件
		$("input[name='oneLevelWhite']").attr("checked","checked");
		// 一级决策项不可用
		$(".oneLevel[checked !='checked']").attr("disabled",true);
		// 显示风险说明
		$("#textarea").show();
	// 如果是回退
	}else if(dictCaseResult == type3){
		// 选中回退按钮
		$("input[name='oneLevelBack']").attr("checked","checked");
		// 一级决策项不可用
		$(".oneLevel[checked !='checked']").attr("disabled",true);
		// 展示风险说明
		$("#textarea").show();
	}
})

// 选中一级清白件
function oneWhiteRadio(){
	// 欺诈案件编号不可用
	$("#fraudCode").attr('disabled',true);
	// 显示风险说明
	$("#textarea").show();
}
// 选中黑名单
function oneBlackRadio(){
	//显示二级决策部分
	$("#two_r").show();
	// 显示选中的三级决策项
	$("input[name='twoBlack']").parent().show();
	// 显示风险说明
	$("#textarea").show();
	// 显示欺诈案件编号
	$("#fraudCode").show();		
    $.ajax({
        type: "post",
        url:ctx+"/antifraud/antiFraudJudge/getOldBlackListOption",
        data:$(window.parent.document).find("#param").serialize(),
		async:false,
        success: function(data){
        	$('#insideBlack').append(data);     			
        }
    });		
}
// 黑名单选中的回显
function oneBlackRadioView(){
	$("#two_r").show();
	$("input[name='twoBlack']").parent().show();
	$("#textarea").show();
	$("#fraudCode").show();
}
// 选中灰名单
function oneGrayRadio(){
	// 显示二级决策项
	$("#two_r").show();
	$("input[name='twoGray']").parent().show();	
	// 显示风险说明
	$("#textarea").show();
    $.ajax({
        type: "post",
        url:ctx+"/antifraud/antiFraudJudge/getOldGrayListOption",
        data:$(window.parent.document).find("#param").serialize(),
		async:false,
        success: function(data){
        	$('#insideGray').append(data);
        }
    });		
}
// 灰名单选中的回显
function oneGrayRadioView(){
	// 显示二级决策项
	$("#two_r").show();
	$("input[name='twoGray']").parent().show();	
	//显示风险说明
	$("#textarea").show();
}
// 选中黑名单二级
function twoRadio(tr){
	// 获取二级决策项id
	var val_id= $(tr).attr("val_id");
	// 三级决策项未选中部分隐藏
	$("#three_r").find('p').eq(0).children("span").hide();
	// 三级决策项不可用
	$("input[name='antiFraudJudgeEx.threeBlack']").attr('checked',false);
	$(".three_r"+val_id).show();
	// 显示三级决策项
	$("#three_r").show();
}	