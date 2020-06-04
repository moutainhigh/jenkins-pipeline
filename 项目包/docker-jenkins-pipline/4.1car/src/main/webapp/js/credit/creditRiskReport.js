var versionDetailed ;// 征信报告1.0(详)
var versionSimple ;// 征信报告2.0(简)
var versionEnterprise ;// 企业报告风险点(企业)
$(function(){
	// 隐藏两块页签内容
	$("#detailed").hide();
	$("#versionDetailed").hide();
	
	$("#simple").hide();
	$("#versionSimple").hide();
	
	$("#enterprise").hide();
	$("#versionEnterprise").hide();
	
	//初始化页面
	initPageData();
	
});

//备注还剩多少字
function test(t){
	var ss = $(t).val();
	var len = ss.length;
	var shengyu = 500;
	if(typeof(len) != "undefined"){
		shengyu = 500 - len;
	}
	$(t).next("p").attr("class","");
	$(t).next("p").find("font").html(shengyu);
	//$('html, body').scrollTop( $('html, body')[0].scrollHeight );
	$('html, body').animate({scrollTop:$('html, body')[0].scrollHeight}, 'slow');
}
//失去焦点
function sqjd(t){
	$(t).next("p").attr("class","hide");
	//$('html, body').animate({scrollTop:$('html, body')[0].scrollHeight}, 'slow');
}
// 获得焦点
function hdjd(t){
	var ss = $(t).val();
	var len = ss.length;
	var shengyu = 500;
	if(typeof(len) != "undefined"){
		shengyu = 500 - len;
	}
	$(t).next("p").attr("class","");
	$(t).next("p").find("font").html(shengyu);
	//$('html, body').scrollTop( $('html, body')[0].scrollHeight );
	//$('html, body').animate({scrollTop:$('html, body')[0].scrollHeight}, 'slow');
}

// 显示简版表格信息
function showSimpleInfo(){
	var data = $(window.parent.document).find("form").serialize();
	// 简版贷记卡负债信息
	$.ajax({
		url:ctx+'/credit/creditRisk/getCardByLoanCode',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			var htm = $("#simpleCardTable");
			if( data != null ){
				htm.find("#CardSimpleTD1").html(formatMoney(accDiv(data.usedLimit,10),2));
				htm.find("#CardSimpleTD2").html(formatMoney(data.limit,2));
				htm.find("#CardSimpleTD3").html(formatMoney(data.overdueAmount,2));
			}else{
				$("#simpleCardTable").find("#CardSimpleTD1").remove();
				$("#simpleCardTable").find("#CardSimpleTD2").remove();
				$("#simpleCardTable").find("#CardSimpleTD3").remove();
			}
		},
		error:function(data){
			$("#simpleCardTable").find("#CardSimpleTD1").parent("td").remove();
			$("#simpleCardTable").find("#CardSimpleTD2").parent("td").remove();
			$("#simpleCardTable").find("#CardSimpleTD3").parent("td").remove();
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
	
	// 简版查询时间
	$.ajax({
		url:ctx+'/credit/creditRisk/selectByCreditReportSimple',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if(data != null){
				$("#simple").find("#simpeReportTime").html(formatTime(data.queryTime));
			}
		}
	});
	
	
}

// 显示详版页签显示信息
function showDetailedInfo(){
	var data = $(window.parent.document).find("form").serialize();
	// 获取基本信息
	$.ajax({
		url:ctx+'/credit/creditRisk/getBaseInfo',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			var temp = "<tr>"
				+"<td class='listbg'><p name='baseinfotd1' class=' tright'></p></td>"
				+"<td > <p name='baseinfotd2' class='tleft pl10'></p></td>"
				+"<td class='listbg'><p name='baseinfotd3' class='tright'></p></td>"
				+"<td style='width:30%;'><p name='baseinfotd4' class='tleft pl10'></p></td>"
				+"</tr>";
			if( data != null && typeof(data) != "undefined" ){
				//查询时间
				if( data.queryTime != null && data.queryTime != "undefined" ){
					var tim = new Date(data.queryTime);
					$("#detailedQueryTime").html(tim.getFullYear()+"-"+(tim.getMonth()+1)+"-"+tim.getDate());
				}else{
					$("#detailedQueryTime").html("暂无数据");
				}
				// 职业单位信息获取时间
				if( data.getinfoTime != null && data.getinfoTime != "undefined" ){
					var time = new Date(data.getinfoTime);
					$("#baseInfoTime").append(time.getFullYear()+"-"+(time.getMonth()+1)+"-"+time.getDate());
				}else{
					$("#baseInfoTime").append("暂无数据");
				}
				// 基本信息第一行
				var tr1 = $(temp);
				tr1.find("p[name='baseinfotd1']").html("本人电话：");
				tr1.find("p[name='baseinfotd3']").html("单位名称：");
				if( data.mobilePhone != null && typeof(data.mobilePhone) != "undefined" ){
					tr1.find("p[name='baseinfotd2']").html(data.mobilePhone);
				}
				if( data.unitName != null && typeof(data.unitName) != "undefined" ){
					tr1.find("p[name='baseinfotd4']").html(data.unitName);
				}
				$("#baseInfoTable").append(tr1);
				// 基本信息第二行
				var tr2 = $(temp);
				tr2.find("p[name='baseinfotd1']").html("家庭电话：");
				tr2.find("p[name='baseinfotd3']").html("单位地址：");
				if( data.homePhone != null && typeof(data.homePhone) != "undefined" ){
					tr2.find("p[name='baseinfotd2']").html(data.homePhone);
				}
				if( data.unitProvince != null && typeof(data.unitProvince) != "undefined" ){
					tr2.find("p[name='baseinfotd4']").html(data.unitProvince);
				}
				if( data.unitCity != null && typeof(data.unitCity) != "undefined" ){
					tr2.find("p[name='baseinfotd4']").append(data.unitCity);
				}
				if( data.unitArea != null && typeof(data.unitArea) != "undefined" ){
					tr2.find("p[name='baseinfotd4']").append(data.unitArea);
				}
				if( data.unitAddress != null && typeof(data.unitAddress) != "undefined" ){
					tr2.find("p[name='baseinfotd4']").append(data.unitAddress);
				}
				$("#baseInfoTable").append(tr2);
				// 基本信息第三行
				var tr3 = $(temp);
				tr3.find("p[name='baseinfotd1']").html("家庭地址：");
				tr3.find("p[name='baseinfotd3']").html("单位电话：");
				if( data.liveProvince != null && typeof(data.liveProvince) != "undefined" ){
					tr3.find("p[name='baseinfotd2']").html(data.liveProvince);
				}
				if( data.liveCity != null && typeof(data.liveCity) != "undefined" ){
					tr3.find("p[name='baseinfotd2']").append(data.liveCity);
				}
				if( data.liveArea != null && typeof(data.liveArea) != "undefined" ){
					tr3.find("p[name='baseinfotd2']").append(data.liveArea);
				}
				if( data.liveAddress != null && typeof(data.liveAddress) != "undefined" ){
					tr3.find("p[name='baseinfotd2']").append(data.liveAddress);
				}
				if( data.unitPhone != null && typeof(data.unitPhone) != "undefined" ){
					tr3.find("p[name='baseinfotd4']").html(data.unitPhone);
				}
				$("#baseInfoTable").append(tr3);
				// 基本信息第三行
				var tr4 = $(temp);
				tr4.find("p[name='baseinfotd1']").html("是否已婚：");
				tr4.find("p[name='baseinfotd3']").html("配偶联系电话：");
				if( data.marryStatus != null && typeof(data.marryStatus) != "undefined" ){
					tr4.find("p[name='baseinfotd2']").html(data.marryStatus);
				}
				if( data.matePhone != null && typeof(data.matePhone) != "undefined" ){
					tr4.find("p[name='baseinfotd4']").html(data.matePhone);
				}
				$("#baseInfoTable").append(tr4);
			}	
		}
	});
	
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
		},
		error: function(data) {
			$("#CardDetailedTD1").parent("tr").remove();
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

// 显示企业征信页面信息
function showEnterpriseTable(){
	var data = $(window.parent.document).find("form").serialize();
	// 显示企业征信页面表格
	$.ajax({
		url:ctx+'/credit/creditRisk/getEnterpriseTable',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if(data != null){
				var temp =	"<tr><td name='enterpriseTd1'>贷款</td>"
					+"<td name='enterpriseTd2'></td><td name='enterpriseTd3'></td><td name='enterpriseTd4'></td>"
					+"<td name='enterpriseTd5'></td><td name='enterpriseTd6'></td><td name='enterpriseTd7'></td>"
					+"</tr>";
				for( var i = 0; i < data.length; i++ ){
					htm = $(temp);
					htm.find("td[name='enterpriseTd1']").html(data[i].infoSummary);
					htm.find("td[name='enterpriseTd2']").html(data[i].normalTransactionCount);
					htm.find("td[name='enterpriseTd3']").html(data[i].normalBalance);
					htm.find("td[name='enterpriseTd4']").html(data[i].concernTransactionCount);
					htm.find("td[name='enterpriseTd5']").html(data[i].concernBalance);
					htm.find("td[name='enterpriseTd6']").html(data[i].badnessTransactionCount);
					htm.find("td[name='enterpriseTd7']").html(data[i].badnessBalance);
					$("#enterpriseTbody").append(htm);
				}
			}
		}
	});
	
	$.ajax({
		url:ctx+'/credit/creditRisk/getEnterpriseTime',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if(data != null){
				$("#enterprise").find("#reportTime").html(formatTime(data.reportDate));
			}
		}
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


// 切换页签显示
function showOrHideData(t,dataId){
	$("[name='pageTable']").removeClass("click");
	$(t).parent().addClass("click");
	$("#detailed").hide();
	$("#simple").hide();
	$("#enterprise").hide();
	$("#"+dataId).show();
	$("#pageFlag").val(dataId)
	//initPageData();
}
	
// 切换[有效性]效果
function changeStatus(data,t){
	// 去掉红色
	$(t).parents("td").find("font").attr("color","");
	
	var flg = $("#pageFlag").val();
	if( data == "1" ){
		$("#mark"+flg).val("");
		$("#mark"+flg).hide();
	}
	if( data == "0" ){
		$("#mark"+flg).show();
	}
}
// 初始化页签数据
function initPageData(){
	var loanCode = $("input[name='loanCode']").val();
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
						$("#markdetailed").hide();
						// 有效性初始化操作
						$("#pageFlag").val("detailed");
						if( data[i].effectiveFlag == "1" ){
							$("#isEffdetailed").attr("checked","checked");
							$("#markdetailed").hide();
						}
						if( data[i].effectiveFlag == "0" ){
							$("#notEffdetailed").attr("checked","checked");
							$("#markdetailed").show();
							$("#markdetailed").val(data[i].riskEffectiveRemark);
						}
						
						// 征信报告风险点1.0初始化操作
						versionDetailed = data[i].creditJson;
						$("#versionDetailed").show();// 页签
						$("#detailed").show();// 页面
						
						$("#simple").hide();
						
						$("#enterprise").hide();
						
						if( versionDetailed != null && typeof(versionDetailed) != "undefined"){
							// 如果initFlag=0说明只是系统做了勾选
							if( versionDetailed.initFlag == "0" ){
								var pageOne = versionDetailed.reportJason;
								for(var n = 0; n < pageOne.length; n++){
									if(pageOne[n].sysChooseFlag == "1"){
										$("#ckdetailed"+pageOne[n].checkBox).attr("checked",true);
									}
								}
							// 如果initFlag不等于0说明只是人工做了勾选
							}else{
								var pageOne = versionDetailed.reportJason;
								for(var y = 0; y < pageOne.length; y++){
									if(pageOne[y].userChooseFlag == "1"){
										$("#ckdetailed"+pageOne[y].checkBox).attr("checked",true);
									}
								}
							}
						}
						// 审核备注
						$("textarea[name='riskCheckOpiniondetailed']").val(data[i].riskCheckOpinion);
						showDetailedInfo();
					}
					// 个人征信报告简
					if( data[i] != null && data[i].riskCreditVersion == 2 ){
						// 有效性初始化操作
						if( data[i].effectiveFlag == '1' ){
							$("#isEffsimple").attr("checked","checked");
							$("#marksimple").hide();
						} 
						if( data[i].effectiveFlag == '0' ){
							$("#notEffsimple").attr("checked","checked");
							$("#marksimple").show();
							$("#marksimple").val(data[i].riskEffectiveRemark);
						}
						if(data[i].effectiveFlag == ''){
							$("#marksimple").hide();
						}
						
						//征信报告风险点2.0初始化操作
						versionSimple = data[i].creditJson;
						$("#versionSimple").show();// 页签
						if($("#detailed").attr("style") == 'display: none;'){
							$("#simple").show();// 页面
							$("#pageFlag").val("simple");
							
							// 隐藏两块页签内容
							$("#detailed").hide();
							
							$("#enterprise").hide();
						} 
						if( versionSimple != null && typeof(versionSimple) != "undefined"){
							if( versionSimple.initFlag == "0" ){
								var pageOne = versionSimple.reportJason;
								for(var w = 0; w < pageOne.length; w++){
									if(pageOne[w].sysChooseFlag == "1"){
										$("#cksimple"+pageOne[w].checkBox).attr("checked",true);
									}
								}
							// 如果initFlag不等于0说明只是rengong做了勾选
							}else{
								var pageOne = versionSimple.reportJason;
								for(var t = 0; t < pageOne.length; t++){
									if(pageOne[t].userChooseFlag == "1"){
										$("#cksimple"+pageOne[t].checkBox).attr("checked",true);
									}
								}
							}
						}
						// 审核备注
						$("textarea[name='riskCheckOpinionsimple']").val(data[i].riskCheckOpinion);
						showSimpleInfo();
					}
					
					// 企业征信报告
					if( data[i] != null && data[i].riskCreditVersion == 3 ){
						$("#markenterprise").hide();
						// 有效性初始化操作
						if( data[i].effectiveFlag == "1" ){
							$("#isEffenterprise").attr("checked","checked");
							$("#markenterprise").hide();
						}
						if( data[i].effectiveFlag == "0" ){
							$("#notEffenterprise").attr("checked","checked");
							$("#markenterprise").show();
							$("#markenterprise").val(data[i].riskEffectiveRemark);
						}
						versionEnterprise = data[i].creditJson;
						$("#versionEnterprise").show();
						if($("#detailed").attr("style") == 'display: none;'){
							if($("#simple").attr("style") == 'display: none;'){
								$("#enterprise").show();// 页面
								$("#pageFlag").val("enterprise");
							}
						}
						if( versionEnterprise != null && typeof(versionEnterprise) != "undefined"){
							if( versionEnterprise.initFlag == "0" ){
								var pageOne = versionEnterprise.reportJason;
								for(var q = 0; q < pageOne.length; q++){
									if(pageOne[q].sysChooseFlag == "1"){
										$("#ckenterprise"+pageOne[q].checkBox).attr("checked",true);
									}
								}
							// 如果initFlag不等于0说明只是rengong做了勾选
							}else{
								var pageOne = versionEnterprise.reportJason;
								for(var u = 0; u < pageOne.length; u++){
									if(pageOne[u].userChooseFlag == "1"){
										$("#ckenterprise"+pageOne[u].checkBox).attr("checked",true);
									}
								}
							}
						}
						// 审核备注
						$("textarea[name='riskCheckOpinionenterprise']").val(data[i].riskCheckOpinion);
						showEnterpriseTable();
					}
				}
			}
		},
	}); 
}

// 保存数据
function saveCreditReportRisk( pageFlg ){
	
	// 借款编号
	var loanCode = $("input[name='loanCode']").val();
	if( pageFlg == 1 ){
		
		var len = $("input[name='effectiveFlagdetailed']:checked").length;
		if(len == 0){
			$("input[name='effectiveFlagdetailed']").each(function(){
				$(this).next("font").attr("color","red");
			});
			return;
		}
		
		// 征信报告汇总信息  获取有效性
		var effectiveFlag = $("input[name='effectiveFlagdetailed']:checked").val();
		// 有效性备注
		var riskEffectiveRemark = $("input[name='riskEffectiveRemarkdetailed']").val();
		if( effectiveFlag == 1 ){
			riskEffectiveRemark = "";
		}
		// 征信报告信用风险点1.0版
		var str = "&creditJson.initFlag=1";
		if( typeof(versionDetailed) != "undefined" && versionDetailed != null && versionDetailed.reportJason != null ){
		var Det = versionDetailed.reportJason;
			for( var i = 0; i < Det.length; i++ ){
				if( Det[i].sysChooseFlag != null && typeof(Det[i].sysChooseFlag) != "undefined"){
					str +="&creditJson.reportJason["+i+"].sysChooseFlag="+Det[i].sysChooseFlag;
				}else{
					str +="&creditJson.reportJason["+i+"].sysChooseFlag=1";
				}
			}
		}
		$("input[name='creditRiskdetailed']").each(function(i,item){
			str += "&creditJson.reportJason["+i+"].checkBox="+$(this).val();
			str += "&creditJson.reportJason["+i+"].userChooseFlag="+(this.checked?"1":"0");
		});
		
		// 备注
		var riskCheckOpinion = $("textarea[name='riskCheckOpiniondetailed']").val();
	}
	
	if( pageFlg == 2 ){
		
		var len = $("input[name='effectiveFlagsimple']:checked").length;
		if(len == 0){
			$("input[name='effectiveFlagsimple']").each(function(){
				$(this).next("font").attr("color","red");
			});
			return;
		}
		
		// 征信报告汇总信息  获取有效性
		var effectiveFlag = $("input[name='effectiveFlagsimple']:checked").val();
		// 有效性备注
		var riskEffectiveRemark = $("input[name='riskEffectiveRemarksimple']").val();
		if( effectiveFlag == 1 ){
			riskEffectiveRemark = "";
		}
		// 征信报告信用风险点2.0版
		var str = "&creditJson.initFlag=1";
		if( typeof(versionSimple) != "undefined" && versionSimple != null && versionSimple.reportJason != null ){
			var Det = versionSimple.reportJason;
			for( var i = 0; i < Det.length; i++ ){
				if( Det[i].sysChooseFlag != null && typeof(Det[i].sysChooseFlag) != "undefined"){
					str +="&creditJson.reportJason["+i+"].sysChooseFlag="+Det[i].sysChooseFlag;
				}else{
					str +="&creditJson.reportJason["+i+"].sysChooseFlag=1";
				}
			}
		}
		$("input[name='creditRisksimple']").each(function(i,item){
			str += "&creditJson.reportJason["+i+"].checkBox="+$(this).val();
			str += "&creditJson.reportJason["+i+"].userChooseFlag="+(this.checked?"1":"0");
		});
		// 备注
		var riskCheckOpinion = $("textarea[name='riskCheckOpinionsimple']").val();
	}
	if( pageFlg == 3 ){
		
		var len = $("input[name='effectiveFlagenterprise']:checked").length;
		if(len == 0){
			$("input[name='effectiveFlagenterprise']").each(function(){
				$(this).next("font").attr("color","red");
			});
			return;
		}
		
		// 征信报告汇总信息  获取有效性
		var effectiveFlag = $("input[name='effectiveFlagenterprise']:checked").val();
		// 有效性备注
		var riskEffectiveRemark = $("input[name='riskEffectiveRemarkenterprise']").val();
		if( effectiveFlag == 1 ){
			riskEffectiveRemark = "";
		}
		// 征信报告企业版
		var str = "&creditJson.initFlag=1";
		if( typeof(versionEnterprise) != "undefined" && versionEnterprise != null && versionEnterprise.reportJason != null ){
			var Det = versionEnterprise.reportJason;
			for( var i = 0; i < Det.length; i++ ){
				if( Det[i].sysChooseFlag != null && typeof(Det[i].sysChooseFlag) != "undefined"){
					str +="&creditJson.reportJason["+i+"].sysChooseFlag="+Det[i].sysChooseFlag;
				}else{
					str +="&creditJson.reportJason["+i+"].sysChooseFlag=1";
				}
			}
		}
		$("input[name='creditRiskenterprise']").each(function(i,item){
			str += "&creditJson.reportJason["+i+"].checkBox="+$(this).val();
			str += "&creditJson.reportJason["+i+"].userChooseFlag="+(this.checked?"1":"0");
		});
		// 备注
		var riskCheckOpinion = $("textarea[name='riskCheckOpinionenterprise']").val();
	}
	
	var loanCode = $(window.parent.document).find("form").find("input[name='loanCode']").val();
	var relId = $(window.parent.document).find("form").find("input[name='relId']").val();
	var checkType = $(window.parent.document).find("form").find("input[name='checkType']").val();
	var type = $(window.parent.document).find("form").find("input[name='type']").val();
	
	// ajax 提交
	$.ajax({
		url:ctx+'/credit/creditRisk/asyncSaveCreditReportRiskInfo',
		data:encodeURI("loanCode="+loanCode + str + "&effectiveFlag="+effectiveFlag
			+"&riskEffectiveRemark="+riskEffectiveRemark+"&riskCheckOpinion="+riskCheckOpinion
			+"&riskCreditVersion="+pageFlg+"&dictCustomerType="+type+"&dictCheckType="+checkType+"&rId="+relId),
		type: "post",
		dataType:'json',
		success:function(data){					
			if( data == "1" ){
				art.dialog.tips('保存成功!');
			}else{
				art.dialog.tips('保存失败!');
			}
		},
	});   
}

function changImg(t){
	
	var title = $(t).find("img").attr("title");
	if(title == "收起"){
		$(t).find("img").attr("src",ctxStatic+"/images/right.jpg").attr("title","展开");
		$(t).parents("tr").nextAll().hide();
	}else{
		$(t).find("img").attr("src",ctxStatic+"/images/down.jpg").attr("title","收起");
		$(t).parents("tr").nextAll().show();
	}
	
}

function changQy(t){
	
	var title = $(t).find("img").attr("title");
	if(title == "收起"){
		$(t).find("img").attr("src",ctxStatic+"/images/right.jpg").attr("title","展开");
		$(t).parents("tr").nextAll().hide();
		$(t).parents("tbody").next("tbody").hide();
	}else{
		$(t).find("img").attr("src",ctxStatic+"/images/down.jpg").attr("title","收起");
		$(t).parents("tr").nextAll().show();
		$(t).parents("tbody").next("tbody").show();
	}
}


