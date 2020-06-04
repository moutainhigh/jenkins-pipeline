function loadingCredit(loanCode){
	var targetul=$("ul[data='"+loanCode+"']");
	if(typeof(targetul.attr("loaded"))!="undefined"){
		return;
	}
	$.ajax({
		url: ctx+"/creditdetailed/info/getCreditList",
		data: {"loanCode":loanCode},
		type: "POST",
		dataType:"json",
		error: function(){
			targetul.find("li").remove();
			targetul.dropdown("toggle");
		},
		beforeSend: function(){
			targetul.prepend("<li style=\"text-align: center;\"><a>&nbsp;&nbsp;loading....</a></li>");
		},
		success:function(data){
			targetul.attr("loaded","1");//加载标识
			targetul.find("li").remove();
			var keyName="主";
			var templi="";
			if($(data).length==0){
				targetul.prepend("<li style=\"text-align: center;\"><a>该客户没有征信报告</a></li>");
				return;
			}
			$(data).each(function(i,item) {
				if(item.dictCustomerType!="0")keyName="共";
				templi+="<li style=\"text-align: center;\"><a href=\"javascript:void(0);\" onclick=\"javascript:showCreditPage('"+item.loanCustomerCode+"','"+item.dictCustomerType+"','"+item.loanCode+"','"+item.riskCreditVersion+"');\">"+item.customerName+"("+keyName+")</a></li>";
			});
			if(templi!=""){
				targetul.prepend(templi);
			}
		}
	});
}

//点击查看
function showCreditPage(rId,customerType,loanCode,version){
	var param = "";
	if (version == "2") {
		var win = art.dialog.parent;//来源页面
		art.dialog.close();
		window.open(ctx + "/credit/creditReportSimple/form?loanCode=" + loanCode + "&rCustomerCoborrowerId=" + rId + "&customerType=" + customerType + "&version=" + version);
	} 
	if(version == "1"){
		param = "rId="+ rId + "&customerType=" + customerType + "&loanCode=" + loanCode + "&version=" + version;
		var win = art.dialog.parent;		
		art.dialog.close();
		window.open(ctx + "/creditdetailed/info/detail?"+param);
	}
}
function enter(loanCode){
	if(loanCode != ""){
		window.open(ctx + "/credit/enterpriseCredit/form?loanCode="+loanCode);
	}
}