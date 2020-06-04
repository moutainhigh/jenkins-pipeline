
$(function(){
	// 展开收缩
    $("img[mark='change']").click(function(){
		var title = $(this).attr("title");
    	if(title == "收起"){
    		$(this).attr("src",ctxStatic+"/images/right.jpg").attr("title","展开");
        	$(this).parents("h3").nextAll().hide();
    	}else{
    		$(this).attr("src",ctxStatic+"/images/down.jpg").attr("title","收起");
        	$(this).parents("h3").nextAll().show();
    	}
	})
	// 拒借详情
	$("a[mark='refuseInfo']").click(function(){
		var resultId = $(this).attr("relation");
		$.ajax({
    		type : "POST",
    		data : {resultId:resultId},
    		url : ctx + "/verify/check/asynGetRefuseInfo",
    		dataType : 'json',
    		success : function(data) {
    			if(data != ""){
        			var html = "<table class='table2' cellspacing='0' cellpadding='0' border='0' width='500px' >";
        			if(data.subResult != undefined ){
        				$.each(data.subResult, function (index, item) {
            				var thirdCode = item.refuseThirdCode==undefined ? "-" :item.refuseThirdCode;
            				html += "<tr><td style='width:150px'>一级拒借码:</td><td>"+item.refuseFirstCode+"</td></tr><tr><td style='width: 150px'>二级拒借码:</td><td>"+item.refuseSecondCode;
            				html += "</td></tr><tr><td style='width: 150px'>三级拒借码:</td><td>"+thirdCode+"</td></tr>";
                        });
        			}
        			html += "<tr><td style='width: 150px'>审批意见：</td><td>"+data.auditCheckExamine+"</td></tr>";
        			html += "</table>";
        			art.dialog({
        				title: "审批详情",
        		    	fixed:false,
        		    	lock: true,
        		    	width:'auto',
        		    	window: "top",
        		    	border: false,
        		    	content: html
        			});
    			}
    		}
    	});
	});
	$("a[mark='showMessage']").click(function(){
		var message = $(this).attr("message");
		var html = "无对应信息";
		var result = message.split("/");
		if(result.length==4){
			html = "<table class='table2' cellspacing='0' cellpadding='0' border='0' width='500px' >";
			html += "<tr><td style='width: 16%'>产品</td><td style='width: 16%'>"+result[0]+"</td><td style='width: 16%'>额度</td><td style='width: 16%'>"+result[1]+"</td><td style='width: 16%'>期数</td><td>"+result[2]+"</td></tr>";
			html += "<tr><td>备注信息</td><td  colspan='5'>"+result[3]+"</td></tr>";
			html += "</table>";
		}else{
			var tdName = $(this).parents("td").prev().html();
			html = "<table class='table2' cellspacing='0' cellpadding='0' border='0' width='500px' >";
			html += "<tr><td style='width: 30%'>"+tdName+"码</td><td>"+result[0]+"</td></tr>";
			html += "<tr><td>备注信息</td><td>"+result[1]+"</td></tr>";
			html += "</table>";
		}
		art.dialog({
			title: "审批详情",
	    	fixed:false,
	    	lock: true,
	    	width:'auto',
	    	window: "top",
	    	border: false,
	    	content: html
		});
	});
	// 关闭拒借信息
	$("#refuseCancel").click(function(){
		$("#modal_refuse").modal('hide');
		$("#modal_refuse .modal-body").empty();
	});
	
	// 显示征信报告表格信息
	showCreditReportTable();
	$("table[str='creditRisk']").each(function(index,data){
		var len = $(data).find("tr").length;
		if(len<=3 && $(data).find("tr:last").find("td").html()==''){
			$(this).prev("h3").remove();
			$(this).remove();
		}
	});
});


function showHistory(applyId){
	window.open("${ctx}/verify/view/initPage?loanCode="+applyId);
}

//显示征信报告表格信息
function showCreditReportTable(){
	$("#detailCardDIV").hide(); // 详版贷记卡DIV隐藏
	$("#simpleCardDIV").hide(); // 简版贷记卡DIV隐藏
	$("#detailLoanDIV").hide(); // 详版贷款DIV隐藏
	$("#simpleLoanDIV").hide(); // 简版贷款DIV隐藏
	var param = $(window.parent.document).find("form").serialize();
	$.ajax({
		url:ctx+'/credit/creditRisk/initPage',
		data:param,
		type: "post",
		dataType:'json',
		success:function(data){
			if( data != null && data.length >0){
				for( var i = 0; i < data.length; i++ ){
					// 个人征信报告详
					if( data[i] != null && data[i].riskCreditVersion == 1 ){
						$("#detailCardDIV").show(); // 详版贷记卡DIV隐藏
						$("#detailLoanDIV").show(); // 详版贷款DIV隐藏
						showDetailedInfo();
					}
					// 个人征信报告简
					if( data[i] != null && data[i].riskCreditVersion == 2 ){
						$("#simpleCardDIV").show(); // 简版贷记卡DIV隐藏
						$("#simpleLoanDIV").show(); // 简版贷款DIV隐藏
						showSimpleInfo();
					}
					// 企业征信报告
					if( data[i] != null && data[i].riskCreditVersion == 3 ){
					
					}
				}
			}
		}
	});
}

// 显示详版信息
function showDetailedInfo(){
	var data = $(window.parent.document).find("form").serialize();
	
	// 显示详版贷记卡信息
	$.ajax({
		url:ctx+'/credit/creditRisk/getDetailedCard',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if( data != null){
				var htm = $("#detailCardTabble");
				if( data.shouldRepayAmount != null && typeof(data.shouldRepayAmount) != "undefined" ){
					htm.find("#CardDetailedTD1").html(formatMoney(data.shouldRepayAmount,2));
				}
				if( data.cerditLine != null && typeof(data.cerditLine) != "undefined" ){
					htm.find("#CardDetailedTD2").html(formatMoney(data.cerditLine,2));
				}
				if( data.usedAmount != null && typeof(data.usedAmount) != "undefined" ){
					htm.find("#CardDetailedTD3").html(formatMoney(data.usedAmount,2));
				}
				if( data.currentOverdueTotal != null && typeof(data.currentOverdueTotal) != "undefined" ){
					htm.find("#CardDetailedTD4").html(formatMoney(data.currentOverdueTotal,2));
				}
			}
		}
	});
	
	// 显示详版贷款负债信息
	$.ajax({
		url:ctx+'/credit/creditRisk/getDetailedLoan',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			// 如果不为空
			var htm = $("#detailLoanTable");
			if( data != null && typeof(data) != "undefined" ){
				// 抵押房贷
				if( data.house != null && typeof(data.house) != "undefined" ){
					var house = data.house;
					if( house.shouldRepayAmount != null && typeof(house.shouldRepayAmount) != "undefined" ){
						htm.find("#HouseDetailedTD2").html( formatMoney(house.shouldRepayAmount,2) );
					}
					if( house.conteactAmount != null && typeof(house.conteactAmount) != "undefined" ){
						htm.find("#HouseDetailedTD3").html( formatMoney(house.conteactAmount,2) );
					}
					if( house.loanBalance != null && typeof(house.loanBalance) != "undefined" ){
						htm.find("#HouseDetailedTD4").html( formatMoney(house.loanBalance,2) );
					}
				}
				// 抵押车贷
				if( data.car != null && typeof(data.car) != "undefined" ){
					var car = data.car;
					if( car.shouldRepayAmount != null && typeof(car.shouldRepayAmount) != "undefined" ){
						htm.find("#CarDetailedTD2").html( formatMoney(car.shouldRepayAmount,2) );
					}
					if( car.conteactAmount != null && typeof(car.conteactAmount) != "undefined" ){
						htm.find("#CarDetailedTD3").html( formatMoney(car.conteactAmount,2) );
					}
					if( car.loanBalance != null && typeof(car.loanBalance) != "undefined" ){
						htm.find("#CarDetailedTD4").html( formatMoney(car.loanBalance,2) );
					}
				}
				// 抵押类贷款
				if( data.mortgage != null && typeof(data.mortgage) != "undefined" ){
					var mortgage = data.mortgage;
					if( mortgage.shouldRepayAmount != null && typeof(mortgage.shouldRepayAmount) != "undefined" && mortgage.shouldRepayAmount != 0){
						htm.find("#MortgageDetailedTD2").html( formatMoney(mortgage.shouldRepayAmount,2) );
					}
					if( mortgage.conteactAmount != null && typeof(mortgage.conteactAmount) != "undefined" && mortgage.conteactAmount != 0){
						htm.find("#MortgageDetailedTD3").html( formatMoney(mortgage.conteactAmount,2) );
					}
					if( mortgage.loanBalance != null && typeof(mortgage.loanBalance) != "undefined" && mortgage.loanBalance != 0){
						htm.find("#MortgageDetailedTD4").html( formatMoney(mortgage.loanBalance,2) );
					}
				}
				// 非抵押类贷款
				if( data.noMortgage != null && typeof(data.noMortgage) != "undefined" ){
					var noMortgage = data.noMortgage;
					if( noMortgage.shouldRepayAmount != null && typeof(noMortgage.shouldRepayAmount) != "undefined" ){
						htm.find("#NoMortgageDetailedTD2").html( formatMoney(noMortgage.shouldRepayAmount,2) );
					}
					if( noMortgage.conteactAmount != null && typeof(noMortgage.conteactAmount) != "undefined" ){
						htm.find("#NoMortgageDetailedTD3").html( formatMoney(noMortgage.conteactAmount,2) );
					}
					if( noMortgage.loanBalance != null && typeof(noMortgage.loanBalance) != "undefined" ){
						htm.find("#NoMortgageDetailedTD4").html( formatMoney(noMortgage.loanBalance,2) );
					}
				}
			}
		}
	});
}

//显示简版表格信息
function showSimpleInfo(){
	var data = $(window.parent.document).find("form").serialize();
	// 简版贷记卡负债信息
	$.ajax({
		url:ctx+'/credit/creditRisk/getCardByLoanCode',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if( data != null ){
				$("#CardSimpleTD1").html(formatMoney(accDiv(data.usedLimit,10),2));
				$("#CardSimpleTD2").html(formatMoney(data.limit,2));
				$("#CardSimpleTD3").html(formatMoney(data.overdueAmount,2));
			}
		}
	});
	
	// 简版贷款信息
	$.ajax({
		url:ctx+'/credit/creditRisk/getLoanByLoanCode',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			// 判断返回结果
			if( data != null ){
				var param = data;
				htm = $("#simpleLoanTable");
				if( param.house != null ){
					if(param.house.overdueAmount != null && typeof(param.house.overdueAmount) != "undefined"){
						htm.find("#HouseSimpleTD2").html(formatMoney(param.house.overdueAmount,2));
					}
					if(param.house.conteactAmount != null && typeof(param.house.conteactAmount) != "undefined"){
						htm.find("#HouseSimpleTD3").html(formatMoney(param.house.conteactAmount,2));
					}
					if(param.house.loanBalance != null && typeof(param.house.loanBalance) != "undefined"){
						htm.find("#HouseSimpleTD4").html(formatMoney(param.house.loanBalance,2));
					}
				}
				if( param.car != null ){
					if(param.car.overdueAmount != null && typeof(param.car.overdueAmount) != "undefined"){
						htm.find("#CarSimpleTD2").html(formatMoney(param.car.overdueAmount,2));
					}
					if(param.car.conteactAmount != null && typeof(param.car.conteactAmount) != "undefined"){
						htm.find("#CarSimpleTD3").html(formatMoney(param.car.conteactAmount,2));
					}
					if(param.car.loanBalance != null && typeof(param.car.loanBalance) != "undefined"){
						htm.find("#CarSimpleTD4").html(formatMoney(param.car.loanBalance,2));
					}
				}
			}
		}
	});
}

