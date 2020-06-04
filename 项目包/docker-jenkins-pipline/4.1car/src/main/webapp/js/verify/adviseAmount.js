
$(function(){
	// 初始化推荐额度
	setAdviseAmount();
	
});

// 设置推荐额度
function setAdviseAmount(){
	var ratingAdviseAmount = $("#ratingAdviseAmount").html();
	
	// 额度卡值不存在，初始化
	if (ratingAdviseAmount == null || ratingAdviseAmount == "") {
		// 后台初始化
		getAdviseAmount("", "");
	}
}

// 设置推荐额度(审批分期、产品类型code)
function getAdviseAmount(auditMonths, productCode){
	// 查询条件form
	var formInfo = $(window.parent.document).find("form[id='param']");
	// 借款编号
	var loanCode = $(formInfo).find("input[name='loanCode']")[0];	
	// 主借人（或共借人）ID
	var rCustomerCoborrowerId = $(formInfo).find("input[name='relId']")[0];
	// 借款人类型（主借人或共借人）
	var dictCustomerType = $(formInfo).find("input[name='type']")[0];			
	// 信审或者复议
	var dictCheckType = $(formInfo).find("input[name='checkType']")[0];             
	
	// 参数设置
	var param = "";
	param += "&loanCode=" + $(loanCode).val();
	param += "&rCustomerCoborrowerId=" + $(rCustomerCoborrowerId).val();
	param += "&dictCustomerType=" + $(dictCustomerType).val();
	param += "&dictCheckType=" + $(dictCheckType).val();
	param += "&auditMonths=" + auditMonths;
	param += "&productCode=" + productCode;
	
	data = encodeURI(param);
	url = ctx + "/verify/adviseAmount/getAdviseAmount";
	
	// 简版贷记卡负债信息
	$.ajax({
		url: url,
		data: data,
		type: "post",
		dataType: 'json',
		async : false,
		success:function(data){
			if(data != null) {
				// 如果div隐藏，将它显示
				if($("#verifyRateScoreDiv").is(":hidden")){
					$("#verifyRateScoreDiv").show();    
				}
			    // 设置推荐额度
				$("#ratingAdviseAmount").html(data);

			} 
		}
	});
}

// 动态计算推荐额度
function changeAdviseAmount(){
	// 产品类型
	var productType = $("#productType").val();
	// 审批分期
	var auditMonths = $("#auditMonths").val();
	if ((productType != null && productType != "") &&
			(auditMonths != null && auditMonths != "")) {
		// 计算推荐额度
		getAdviseAmount(auditMonths, productType);
	}
}

// 保存推荐额度
function saveAdviseAmount(){
	// 查询条件form
	var formInfo = $(window.parent.document).find("form[id='param']");
	// 借款编号
	var loanCode = $(formInfo).find("input[name='loanCode']")[0];	
	var loanCodeVal = $(loanCode).val();
	var ratingAdviseAmount = $("#ratingAdviseAmount").html();
	
	// 额度卡值不存在
	if (ratingAdviseAmount == null || ratingAdviseAmount == "") {
		return "true";
	}
	
	// 参数设置
	var param = "";
	param += "&loanCode=" + loanCodeVal;
	param += "&ratingAdviseAmount=" + ratingAdviseAmount;
	
	data = encodeURI(param);
	url = ctx + "/verify/adviseAmount/saveAdviseAmount";
	var result;
	// 简版贷记卡负债信息
	$.ajax({
		url: url,
		data: data,
		type: "post",
		dataType: 'json',
		async : false,
		success:function(data){
			
			if(data == false) {
				art.dialog.tips("推荐额度保存失败");
				result = "false";
			} else { 
				
				result = "true";
			}
		}
	});
	
	return result;
	
}


