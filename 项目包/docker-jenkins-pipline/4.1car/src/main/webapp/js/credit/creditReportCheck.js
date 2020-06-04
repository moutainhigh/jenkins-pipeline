
$(function(){
	getRiskList();
});

//跳转到共借人征信简版
function intoCreditReportSimple(rCustomerCoborrowerId) {

	// 如果父页面重载或者关闭其子对话框全部会关闭
	var win = art.dialog.open.origin;//来源页面
	art.dialog.close();
	win.location.href = ctx + "/credit/creditReportSimple/form.do?loanCode=" + $("#creditReportSimpleLoanCode").val() + "&rCustomerCoborrowerId=" + rCustomerCoborrowerId + "&customerId=" + $("#creditReportSimpleCustomerId").val();
	
}

//个人征信检查
function creditCheck1(applyId  ){
	window.location.href =ctx + "/creditdetailed/info/creditCheck?applyId="+applyId;
}

//个人征信跳转
function creditCheck( rId , customerType , loanCode  ){
	var customerId=$("#creditReportSimpleCustomerId").val();
	var param = "rId="+ rId + "&customerType=" + customerType + "&loanCode=" + loanCode+"&customerId="+customerId ;	
	
	var win = art.dialog.parent;
	art.dialog.close();
	win.location.href =ctx + "/creditdetailed/info/creditCheck?"+param;
}

//点击查看
function readyParamgo( rId , customerType , loanCode , version ){
	var param = "";
	
	if (version == "2") {
		var win = art.dialog.parent;//来源页面
		art.dialog.close();
		window.open(ctx + "/credit/creditReportSimple/form?loanCode=" + loanCode + "&rCustomerCoborrowerId=" + rId + "&customerId=" + $("#creditReportSimpleCustomerId").val() + "&customerType=" + customerType + "&version=" + version);
	} 
	if(version == "1"){
		param = "rId="+ rId + "&customerType=" + customerType + "&loanCode=" + loanCode + "&version=" + version;
		var win = art.dialog.parent;		
		art.dialog.close();
		window.open(ctx + "/creditdetailed/info/detail?"+param);
	}
}

function getRiskList(){
	var loanCode = $("input[name='loanCode']").val();
	$.ajax({
		url: ctx+"/creditdetailed/info/getRiskList",
		data:"loanCode="+loanCode,
		type: "post",
		dataType:'json',
		success:function(data){
			if(data != null){
				for(var i = 0; i < data.length; i++ ){
					
					$("input[mark='"+data[i].rId+"']").each(function(){
						$(this).attr("checked",false);
					});
					if(data[i].riskCreditVersion == "1"){
						$("input[mark='"+data[i].rId+"']").eq(0).attr('checked', 'checked');
					}
					if(data[i].riskCreditVersion == "2"){
						$("input[mark='"+data[i].rId+"']").eq(1).attr('checked', 'checked');
					}
				}
			}
		}
	});
}