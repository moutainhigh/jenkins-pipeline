// 祥版征信相关操作（信用卡）
//金额验证
function addKeyup(){
	$('input[type=text][money=1]').each(function(index, element) {
		$(element).keyup(function(){
			
			//只输入数字和三位小数
			this.value = this.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符
			this.value = this.value.replace(/^\./g,""); //验证第一个字符是数字而不是
			this.value = this.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
			this.value = this.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d\d).*$/,'$1$2.$3'); //只能输入三位小数
		});
	});
}

$(function(){
	// 初始化数据
	initCardData();
});

// 初始化数据
function initCardData(){
	$("#cardoneTable").find("tbody").html("");
	$("#cardtwoTable").find("tbody").html("");
	$("#card24Table").find("tbody").html("");
	var data = $(window.parent.document).find("form[id='param']").serialize();
	$.ajax({
		url: ctx+"/credit/showCardData",
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if( data != null ){
				// 信用卡明细信息
				if(data.creditCardDetailedOneList != null && data.creditCardDetailedOneList[0].id != null){
					for( var i=0; i<data.creditCardDetailedOneList.length; i++ ){
						if( data.creditCardDetailedOneList[0].id != null ){
							//append selce2难用，用clone
							var vTb=$('#cardoneTable');
							var vTr=$('#cardoneTable_trRow_1');
							var htmInfo=vTr.cloneSelect2(true,true);
							
							htmInfo.find("td[name='cardType']").text(data.creditCardDetailedOneList[i].cardType);
							
							htmInfo.find("td[name='currency']").text(data.creditCardDetailedOneList[i].currency);
							

							htmInfo.find("td[name='accountDay']").text(FormatDate(data.creditCardDetailedOneList[i].accountDay));
							htmInfo.find("td[name='cerditLine']").text(formatMoney(data.creditCardDetailedOneList[i].cerditLine));
							htmInfo.find("td[name='shareCreditLine']").text(formatMoney(data.creditCardDetailedOneList[i].shareCreditLine));
							htmInfo.find("td[name='liabilitiesLine']").text(formatMoney(data.creditCardDetailedOneList[i].liabilitiesLine));
							htmInfo.find("td[name='usedAmount']").text(formatMoney(data.creditCardDetailedOneList[i].usedAmount));
							htmInfo.find("td[name='deleteName']").attr("onClick","removeCardData(this,'"+data.creditCardDetailedOneList[i].id+"')");
							htmInfo.find("tr").attr("id","infoTr"+data.creditCardDetailedOneList[i].id);
							
							htmInfo.appendTo(vTb); 
							
							//绑定信息二
							showCardTwo(data.creditCardDetailedOneList[i].id,data.creditCardDetailedTwoList);
	
							// 显示期数
							showQsCard(data.creditCycleRecordExList,data.creditCardDetailedOneList[i].id,data.creditCardDetailedTwoList);
						}
					}
					
					// 显示编号
					sortCard();
				}
			}
		}
	});
}

//显示信息二
function showCardTwo(id,creditCardDetailedTwoList){
	for( var n=0;n<creditCardDetailedTwoList.length; n++ ){
		if( creditCardDetailedTwoList[0].id != null && creditCardDetailedTwoList[n].relationId == id){
			var vTb=$('#cardtwoTable');
			var vTr=$('#cardtwoTable_trRow_1');
			var htmInfo=vTr.cloneSelect2(true,true);
		
			htmInfo.find("td[name='accountStatus']").text(creditCardDetailedTwoList[n].accountStatus);
		 
			htmInfo.find("td[name='shouldRepayAmount']").text(formatMoney(creditCardDetailedTwoList[n].shouldRepayAmount));

			htmInfo.find("td[name='realRepayAmount']").text(formatMoney(creditCardDetailedTwoList[n].realRepayAmount));
			htmInfo.find("td[name='realRepayDay']").text(FormatDate(creditCardDetailedTwoList[n].realRepayDay));
			htmInfo.find("td[name='currentOverdue']").text(creditCardDetailedTwoList[n].currentOverdue);
			
			htmInfo.find("td[name='currentOverdueTotal']").text(formatMoney(creditCardDetailedTwoList[n].currentOverdueTotal));
			htmInfo.find("td[name='overdraftBalance']").text(formatMoney(creditCardDetailedTwoList[n].overdraftBalance));
			htmInfo.find("td[name='repaymentNo']").text(creditCardDetailedTwoList[n].repaymentNo);
			htmInfo.find("td[name='getinfoTime']").text(FormatDate(creditCardDetailedTwoList[n].getinfoTime));
			
			//htmInfo.find("tr").attr("id","infoTr2"+creditCardDetailedTwoList[n].id);
			htmInfo.appendTo(vTb); 
		
			}
	}
}


// 显示期数
function showQsCard(QSData , id,CardDetailedTwoList){

	if(QSData != null && QSData.length > 0 && QSData[0].relationId != null){
		for( var s = 0; s < QSData.length; s++  ){
			if(QSData[s].relationId == id){
				// 最近24个月缴交状态
				var trPeriods = $("#periodscardHide").find("tbody").html();
				var htmPeriods = $(trPeriods);
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
				for( var i=0; i<CardDetailedTwoList.length; i++ ){
					if(CardDetailedTwoList[i]!=null && CardDetailedTwoList[i]!=undefined){
						if(QSData[s].relationId==CardDetailedTwoList[i].relationId){
							htmPeriods.find("td[name='balanceTime']").text(FormatDate1(CardDetailedTwoList[i].clearingDay));
						}
					}
				}
				
				$("#card24Table").find("tbody").append(htmPeriods);
			}
		}
	}
}

//显示编号
function sortCard(){
	// 循环信用卡信息(进行排序)
	var allInfoNum = $("#cardoneTable").find("td[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});
	// 循环信用卡二信息(进行排序)
	var allInfoNum = $("#cardtwoTable").find("tbody").find("span[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});
	// 循环期数(进行排序)
	var allPeriodsNum = $("#card24Table").find("tbody").find("span[name='num']");
	allPeriodsNum.each(function(n,item){
		$(this).text(n+1);
	});
}




function FormatDate1 (strTime) {
	if(strTime==undefined || strTime==null){
		return "";
	}
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1);
}
function FormatDate (strTime) {
	if(strTime==undefined || strTime==null){
		return "";
	}
    var date = new Date(strTime);
    return date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
}
function validateNumEx(t){
	var msg=validateNumber(t.val());
	if(msg!='true'){
		t.focus();
		art.dialog.tips(msg);
	}
}
function validate24Ex(t){
	if(t==undefined || t==null || t.val()==''){
		return false;
	}
	var msg=validate24(t.val());
	if(msg!='true'){
		addMyValidateCss(t);
		t.val('');
		art.dialog.tips(msg);
	}
}

function formatMoneyEx(money){
	var tpMoney=money;
	if(money!=undefined && money!=null){
		 tpMoney = money.toString().replace(',', '');
	}
	return tpMoney;
}
