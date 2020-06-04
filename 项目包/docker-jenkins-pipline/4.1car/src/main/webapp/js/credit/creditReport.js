// 祥版征信相关操作

$(function(){
	// 初始化数据
	initData();
	//金额验证
	addKeyup();
	//校验
	//$("input[name='realRepayAmount']").attr("onblur","validateNumEx($(this));");
	$("input[name^='qs']").attr("onblur","validate24Ex($(this));");
});



// 初始化数据
function initData(){
	$("#loanoneTable").find("tbody").html("");
	$("#loantwoTable").find("tbody").html("");
	$("#loan24Table").find("tbody").html("");
	var data = $(window.parent.document).find("form[id='param']").serialize();
	$.ajax({
		url: ctx+"/credit/showData",
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			
			if( data != null ){
				// 贷款明细信息
				if(data.creditLoanDetailedOneList != null && data.creditLoanDetailedOneList[0].id != null){
					for( var i=0; i<data.creditLoanDetailedOneList.length; i++ ){
						if( data.creditLoanDetailedOneList[0].id != null ){
							//append selce2难用，用clone
							var vTb=$('#loanoneTable');
							var vTr=$('#loanoneTable_trRow_1');
							var htmInfo=vTr.cloneSelect2(true,true);
							
							htmInfo.find("td[name='loanType']").text(data.creditLoanDetailedOneList[i].loanType);
							htmInfo.find("td[name='guaranteeType']").text(data.creditLoanDetailedOneList[i].guaranteeType);
							htmInfo.find("td[name='currency']").text(data.creditLoanDetailedOneList[i].currency);
							
							
							htmInfo.find("td[name='accountStatu']").text(data.creditLoanDetailedOneList[i].accountStatu);
							htmInfo.find("td[name='repayFrequency']").text(data.creditLoanDetailedOneList[i].repayFrequency);
							htmInfo.find("td[name='levelClass']").text(data.creditLoanDetailedOneList[i].levelClass);
							htmInfo.find("td[name='repayMonths']").text(data.creditLoanDetailedOneList[i].repayMonths);
							htmInfo.find("td[name='releaseDay']").text(FormatDate(data.creditLoanDetailedOneList[i].releaseDay));
							htmInfo.find("td[name='actualDay']").text(FormatDate(data.creditLoanDetailedOneList[i].actualDay));
							htmInfo.find("td[name='conteactAmount']").text(formatMoney(data.creditLoanDetailedOneList[i].conteactAmount,2));
							htmInfo.find("td[name='loanBalance']").text(formatMoney(data.creditLoanDetailedOneList[i].loanBalance,2));
							htmInfo.find("td[name='getingTime']").text(FormatDate(data.creditLoanDetailedOneList[i].getingTime));
							htmInfo.find("tr").attr("id","infoTr"+data.creditLoanDetailedOneList[i].id);
							
							htmInfo.appendTo(vTb); 
							//显示信息二
							showLoanTwo(data.creditLoanDetailedOneList[i].id,data.creditLoanDetailedTwoList);
							// 显示期数
							showQs(data.creditCycleRecordExList,data.creditLoanDetailedOneList[i].id,data.creditLoanDetailedTwoList);
						}
					}
					
					// 显示编号
					sort();
				}
			}
		}
	});
}

//显示信息二
function showLoanTwo(id,creditLoanDetailedTwoList){
	
	for( var i=0; i<creditLoanDetailedTwoList.length; i++ ){
		if( creditLoanDetailedTwoList[0].id != null &&
				 creditLoanDetailedTwoList[0].relationId==id){
			var vTb=$('#loantwoTable');
			var vTr=$('#loantwoTable_trRow_1');
			var htmInfo=vTr.cloneSelect2(true,true);
			
			/*var trInfo = $("#loantwoHide").find("tbody").html();
			var htmInfo = $(trInfo);*/
		
			
			htmInfo.find("td[name='repayMonths']").text(creditLoanDetailedTwoList[i].repayMonths);
			htmInfo.find("td[name='realRepayDay']").text(FormatDate(creditLoanDetailedTwoList[i].realRepayDay));
			htmInfo.find("td[name='shouldRepayAmount']").text(formatMoney(creditLoanDetailedTwoList[i].shouldRepayAmount));
			htmInfo.find("td[name='realRepayAmount']").text(formatMoney(creditLoanDetailedTwoList[i].realRepayAmount));
			htmInfo.find("td[name='currentOverdue']").text(creditLoanDetailedTwoList[i].currentOverdue);
			htmInfo.find("td[name='currentOverdueTotal']").text(formatMoney(creditLoanDetailedTwoList[i].currentOverdueTotal));
			
			htmInfo.find("td[name='overdueNoTotal']").text(creditLoanDetailedTwoList[i].overdueNoTotal);
			htmInfo.find("td[name='overdueNoHighest']").text(creditLoanDetailedTwoList[i].overdueNoHighest);
			htmInfo.find("td[name='overduePrincipalLevel1']").text(formatMoney(creditLoanDetailedTwoList[i].overduePrincipalLevel1));
			htmInfo.find("td[name='overduePrincipalLevel2']").text(formatMoney(creditLoanDetailedTwoList[i].overduePrincipalLevel2));
			htmInfo.find("td[name='overduePrincipalLevel3']").text(formatMoney(creditLoanDetailedTwoList[i].overduePrincipalLevel3));
			htmInfo.find("td[name='overduePrincipalLevel4']").text(formatMoney(creditLoanDetailedTwoList[i].overduePrincipalLevel4));
			
			//htmInfo.find("tr").attr("id","infoTr2"+creditLoanDetailedTwoList[i].id);
			//$("#loantwoTable").find("tbody").append(htmInfo);
			htmInfo.appendTo(vTb); 
			}
	}
}



// 显示期数
function showQs(QSData , id,LoanDetailedTwoList){
	
	if(QSData != null && QSData.length > 0 && QSData[0].relationId != null){
		for( var s = 0; s < QSData.length; s++  ){
			if(QSData[s].relationId == id){
				// 最近24个月缴交状态
				var trPeriods = $("#periodsHide").find("tbody").html();
				var htmPeriods = $(trPeriods);
				htmPeriods.find("span[name='num']").parent("td").parent("tr").attr("id","periodsTr"+id);
				htmPeriods.find("td[name='qs1']").text(QSData[s].qs1);
				htmPeriods.find("td[name='qs2']").text(QSData[s].qs2);
				htmPeriods.find("td[name='qs3']").text(QSData[s].qs3);
				htmPeriods.find("td[name='qs4']").text(QSData[s].qs4);
				htmPeriods.find("td[name='qs5']").text(QSData[s].qs5);
				htmPeriods.find("td[name='qs6']").text(QSData[s].qs6);
				htmPeriods.find("td[name='qs7']").text(QSData[s].qs7);
				htmPeriods.find("td[name='qs8']").text(QSData[s].qs8);
				htmPeriods.find("td[name='qs9']").text(QSData[s].qs9);
				htmPeriods.find("td[name='qs10']").text(QSData[s].qs10);
				htmPeriods.find("td[name='qs11']").text(QSData[s].qs11);
				htmPeriods.find("td[name='qs12']").text(QSData[s].qs12);
				htmPeriods.find("td[name='qs13']").text(QSData[s].qs13);
				htmPeriods.find("td[name='qs14']").text(QSData[s].qs14);
				htmPeriods.find("td[name='qs15']").text(QSData[s].qs15);
				htmPeriods.find("td[name='qs16']").text(QSData[s].qs16);
				htmPeriods.find("td[name='qs17']").text(QSData[s].qs17);
				htmPeriods.find("td[name='qs18']").text(QSData[s].qs18);
				htmPeriods.find("td[name='qs19']").text(QSData[s].qs19);
				htmPeriods.find("td[name='qs20']").text(QSData[s].qs20);
				htmPeriods.find("td[name='qs21']").text(QSData[s].qs21);
				htmPeriods.find("td[name='qs22']").text(QSData[s].qs22);
				htmPeriods.find("td[name='qs23']").text(QSData[s].qs23);
				htmPeriods.find("td[name='qs24']").text(QSData[s].qs24);
				
				//显示结算年月
				for( var i=0; i<LoanDetailedTwoList.length; i++ ){
				if(LoanDetailedTwoList[i]!=null && LoanDetailedTwoList[i]!=undefined){
					if(QSData[s].relationId==LoanDetailedTwoList[i].relationId){
						htmPeriods.find("td[name='balanceTime']").text(FormatDate1(LoanDetailedTwoList[i].clearingDay));
					}
					
				  }
					
				}
				
				$("#loan24Table").find("tbody").append(htmPeriods);
			}
		}
	}
}

//显示编号
function sort(){
	// 循环贷款信息(进行排序)
	var allInfoNum = $("#loanoneTable").find("tbody").find("span[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});
	// 循环贷款二信息(进行排序)
	var allInfoNum = $("#loantwoTable").find("tbody").find("span[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});
	// 循环期数(进行排序)
	var allPeriodsNum = $("#loan24Table").find("tbody").find("span[name='num']");
	allPeriodsNum.each(function(n,item){
		$(this).text(n+1);
	});
}