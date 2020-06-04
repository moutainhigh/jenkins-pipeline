// 个人住房公积金信息相关操作

$(function(){
	// 初始化数据
	initData();
});

// 初始化数据
function initData(){
	$("#info").find("tbody").html("");
	$("#periods").find("tbody").html("");
	var data = $(window.parent.document).find("form[id='param']").serialize();
	$.ajax({
		url:ctx+'/creditdetailed/accumulation/showData',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if( data != null ){
				// 个人住房公积金信息
				if(data.creditCpfDetailedList != null && data.creditCpfDetailedList[0].id != null){
					for( var i=0; i<data.creditCpfDetailedList.length; i++ ){
						if( data.creditCpfDetailedList[0].id != null ){
							var trInfo = $("#infoHide").find("tbody").html();
							var htmInfo = $(trInfo);
							htmInfo.find("td[name='personAccount']").text(data.creditCpfDetailedList[i].personAccount);
							htmInfo.find("td[name='unitName']").text(data.creditCpfDetailedList[i].unitName);
							htmInfo.find("td[name='accountDay']").text(formatTime(data.creditCpfDetailedList[i].accountDay));
							htmInfo.find("td[name='payDay']").text(formatTime(data.creditCpfDetailedList[i].payDay));
							htmInfo.find("td[name='payToDay']").text(formatTime(data.creditCpfDetailedList[i].payToDay));
							htmInfo.find("td[name='payDayNear']").text(formatTime(data.creditCpfDetailedList[i].payDayNear));
							htmInfo.find("td[name='unitRation']").text(data.creditCpfDetailedList[i].unitRation+"%");
							htmInfo.find("td[name='personRation']").text(data.creditCpfDetailedList[i].personRation+"%");
							htmInfo.find("td[name='deposit']").text(data.creditCpfDetailedList[i].deposit);
							htmInfo.find("td[name='getinfoTime']").text(formatTime(data.creditCpfDetailedList[i].getinfoTime));
							$("#info").find("tbody").append(htmInfo);
							// 显示期数
							showQs(data.creditCycleRecordExList,data.creditCpfDetailedList[i].id);
						}
					}
					// 显示编号
					sort();
				}
			}
		}
	});
}

// 显示期数
function showQs(QSData , id){
	if(QSData != null && QSData.length > 0 && QSData[0].relationId != null){
		for( var s = 0; s < QSData.length; s++  ){
			if(QSData[s].relationId == id){
				// 最近24个月缴交状态
				var trPeriods = $("#periodsHide").find("tbody").html();
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
				$("#periods").find("tbody").append(htmPeriods);
			}
		}
	}
}

// 显示编号
function sort(){
	// 循环公积金信息(进行排序)
	var allInfoNum = $("#info").find("tbody").find("td[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});
	// 循环期数(进行排序)
	var allPeriodsNum = $("#periods").find("tbody").find("td[name='num']");
	allPeriodsNum.each(function(n,item){
		$(this).text(n+1);
	});
}

//格式化时间为 YYYY-MM-DD
function formatTime( param ){
	var result = "";
	if( param != null && typeof(param) != "undefined"){
		var tim = new Date(param);
		var month = (tim.getMonth()+1);
		if(tim.getMonth() < 9){
			month="0"+(tim.getMonth()+1);
		}
		var day = tim.getDate();
		if(tim.getDate() < 10){
			day="0"+tim.getDate()
		}
		result = tim.getFullYear()+"-"+month+"-"+day;
	}
	return result;
}


