// 初始化方法

$(function(){
	
	$("select[str='province']").change(function(){
		$("#select2-drop").css("style","bottom: 500px;!important;");
		var provinceId = $(this).find("option:selected").val();
		var element = $(this).parent("td").parent("tr").next("tr").find("td select");
		if( provinceId == "" ){
			element.empty();
			element.append("<option value='' selected=true>请选择</option>");
			element.trigger("change");
		}else{
			$.ajax({
				type : "POST",
				url : ctx + "/common/selectInit/asynLoadCity",
				data: {provinceId: provinceId},	
				async: false,
				success : function(data) {
					//var cityName =  $("select[str='province']").data("cityName");
					var resultObj = eval("("+data+")");
					element.empty();
					element.append("<option value=''>请选择</option>");
					var message ="";
					$.each(resultObj,function(i,item){
						message += "<option value="+item.code+">"+item.name+"</option>";
					});
					
					element.append(message);
					element.trigger("change");
					element.attr("disabled", false);
				}
			});
		}
	});
	
	$("select[str='city']").change(function(){
		var cityId = $(this).find("option:selected").val();
		var element = $(this).parent("td").parent("tr").next("tr").find("td select");
		if( cityId == "" ){
			element.empty();
			element.append("<option value='' selected=true>请选择</option>");
			element.trigger("change");
		}else{
			$.ajax({
				type : "POST",
				url : ctx + "/common/selectInit/asynLoadDistrict",
				data: {cityId: cityId},	
				async: false,
				success : function(data) {
					//var areaName =  $("select[str='city']").data("areaName");
					var resultObj = eval("("+data+")");
					element.empty();
					element.append("<option value=''>请选择</option>");
					var message ="";
					$.each(resultObj,function(i,item){
						/*if(areaName == item.id){
							message += "<option selected value="+item.id+">"+item.name+"</option>";
						}else{*/
						message += "<option value="+item.code+">"+item.name+"</option>";
						//}
					});
					element.append(message);
					element.trigger("change");
					element.attr("disabled", false);
				}
			});
		}
	});
	
	$("select[str='provinceTD']").change(function(){
		var provinceId = $(this).find("option:selected").val();
		var element = $(this).parent("td").next("td").find("select");
		if( provinceId == "" ){
			element.empty();
			element.append("<option value='' selected=true>请选择</option>");
			element.trigger("change");
		}else{
			$.ajax({
				type : "POST",
				url : ctx + "/common/selectInit/asynLoadCity",
				data: {provinceId: provinceId},
				async: false,
				success : function(data) {
					var resultObj = eval("("+data+")");
					element.empty();
					element.append("<option value=''>请选择</option>");
					var message ="";
					$.each(resultObj,function(i,item){
						message += "<option value="+item.code+">"+item.name+"</option>";
					});
					element.append(message);
					element.trigger("change");
					element.attr("disabled", false);
				}
			});
		}
	});
	
	$("select[str='cityTD']").change(function(){
		var cityId = $(this).find("option:selected").val();
		var element = $(this).parent("td").next("td").find("select");
		if( cityId == "" ){
			element.empty();
			element.append("<option value='' selected=true>请选择</option>");
			element.trigger("change");
		}else{
			$.ajax({
				type : "POST",
				url : ctx + "/common/selectInit/asynLoadDistrict",
				data: {cityId: cityId},
				async: false,
				success : function(data) {
					var resultObj = eval("("+data+")");
					element.empty();
					element.append("<option value=''>请选择</option>");
					var message ="";
					$.each(resultObj,function(i,item){
						message += "<option value="+item.code+">"+item.name+"</option>";
					});
					element.append(message);
					element.trigger("change");
					element.attr("disabled", false);
				}
			});
		}
	});
	
	// 初始化数据
	initData();
});

// 初始化数据
function initData(){
	/*var ss = $(window.parent.document).find("form[id='param']").serialize();
	alert(ss);*/
	$(window.parent.document).find("input[name='isSave']").val("0");
	var data = $(window.parent.document).find("form[id='param']").serialize();
	var applyCertNum = $(window.parent.document).find("form[id='param']").find("input[name='applyCertNum']").val();
	$("#detailedInfo").find("td[name='certNo']").text(applyCertNum);
	$.ajax({
		url:ctx+'/creditdetailed/info/showData',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if(data != null){
				if(data.id != null && data.id != ""){
					$(window.parent.document).find("input[name='isSave']").val("1");
				}
				$("#detailedInfo").find("td[name='creditCode']").text(data.creditCode);// 报告编号
				$("#detailedInfo").find("td[name='queryTime']").text(formatTime(data.queryTime));// 查询时间
				$("#detailedInfo").find("td[name='reportTime']").text(formatTime(data.reportTime));// 报告时间
				$("#detailedInfo").find("input[name='id']").val(data.id);//隐藏项-ID
				$("#detailedInfo").find("span[name='name']").text(data.name);// 姓名
				$("#detailedInfo").find("td[name='sex']").text(data.sex);// 性别
				$("#detailedInfo").find("td[name='certType']").text(data.certType);// 证件类型
				//$("#detailedInfo").find("td[name='certNo']").text(data.certNo);// 证件号码
				$("#detailedInfo").find("td[name='birthday']").text(formatTime(data.birthday));// 出生日期
				$("#detailedInfo").find("td[name='highestEducation']").text(data.highestEducation);// 最高学历
				$("#detailedInfo").find("select[name='contactAddProvince']").select2("val",data.contactAddProvince);// 通讯省 								
				$("#detailedInfo").find("select[name='contactAddProvince']").trigger("change");
				$("#detailedInfo").find("td[name='contactAddProvince1']").text($("#detailedInfo").find("select[name='contactAddProvince']").find("option:selected").text());// 通讯省 	
				$("#detailedInfo").find("select[name='contactAddCity']").select2("val",data.contactAddCity);// 通讯市 								
				$("#detailedInfo").find("select[name='contactAddCity']").trigger("change");
				$("#detailedInfo").find("td[name='contactAddCity1']").text($("#detailedInfo").find("select[name='contactAddCity']").find("option:selected").text());
				$("#detailedInfo").find("select[name='contactAddArea']").select2("val",data.contactAddArea);// 通讯省 	
				$("#detailedInfo").find("td[name='contactAddArea1']").text($("#detailedInfo").find("select[name='contactAddArea']").find("option:selected").text());// 通讯省 	
				$("#detailedInfo").find("td[name='contactAddress']").text(data.contactAddress);// 通讯详细地址
				$("#detailedInfo").find("td[name='zipCode']").text(data.zipCode);// 邮政编码
				$("#detailedInfo").find("select[name='nativeAddProvince']").select2("val",data.nativeAddProvince);// 户籍省 								
				$("#detailedInfo").find("select[name='nativeAddProvince']").trigger("change");
				$("#detailedInfo").find("td[name='nativeAddProvince1']").text($("#detailedInfo").find("select[name='nativeAddProvince']").find("option:selected").text());// 户籍省 
				$("#detailedInfo").find("select[name='nativeAddCity']").select2("val",data.nativeAddCity);// 户籍市	
				$("#detailedInfo").find("select[name='nativeAddCity']").trigger("change");
				$("#detailedInfo").find("td[name='nativeAddCity1']").text($("#detailedInfo").find("select[name='nativeAddCity']").find("option:selected").text());// 户籍市
				$("#detailedInfo").find("select[name='nativeAddArea']").select2("val",data.nativeAddArea);// 户籍区
				$("#detailedInfo").find("td[name='nativeAddArea1']").text($("#detailedInfo").find("select[name='nativeAddArea']").find("option:selected").text());
				$("#detailedInfo").find("td[name='nativeAddress']").text(data.nativeAddress);// 户籍详细地址
				$("#detailedInfo").find("td[name='homePhone']").text(data.homePhone);// 住宅电话
				$("#detailedInfo").find("td[name='unitPhone']").text(data.unitPhone);// 单位电话
				$("#detailedInfo").find("td[name='mobilePhone']").text(data.mobilePhone);// 手机号码
				$("#detailedInfo").find("td[name='marryStatus']").text(data.marryStatus);//婚姻状态
				$("#detailedInfo").find("td[name='mateName']").text(data.mateName);// 配偶姓名
				$("#detailedInfo").find("td[name='mateCertType']").text(data.mateCertType);// 配偶证件类型
				$("#detailedInfo").find("td[name='mateCertNo']").text(data.mateCertNo);// 配偶姓名
				$("#detailedInfo").find("td[name='matePhone']").text(data.matePhone);// 配偶姓名
				showHouse(data.liveList)// 居住信息
				showWork(data.occupationList);// 显示职业信息
			}
		}
	});
}

// 显示居住信息
function showHouse(house){
	$("#houseInfo").find("tbody").html("");
	// 如果有数据
	if(house != null && typeof(house) != "undefined"){
		for( var i = 0; i < house.length; i++ ){
			var target = $("#houseInfo");
			$("#houseHid tbody tr").find("input[name='id']").parent("td").parent("tr").attr("id","");
			$("#houseHid tbody tr").find("input[name='id']").parent("td").parent("tr").attr("id",house[i].id);
			$("#houseHid tbody tr").find("a").attr("onClick","removeHouse('')");
			$("#houseHid tbody tr").find("a").attr("onClick","removeHouse('"+house[i].id+"')");
			var temp = $("#houseHid tbody tr");
			var htmInfo=temp.cloneSelect2(true,true);
			htmInfo.appendTo($(target));
			$("#houseInfo").find("#"+house[i].id).find("input[name='id']").val(house[i].id);
			$("#houseInfo").find("#"+house[i].id).find("select[name='liveProvince']").select2("val",house[i].liveProvince);
			$("#houseInfo").find("#"+house[i].id).find("select[name='liveProvince']").trigger("change");
			$("#houseInfo").find("#"+house[i].id).find("td[name='liveProvince1']").text($("#houseInfo").find("#"+house[i].id).find("select[name='liveProvince']").find("option:selected").text());
			$("#houseInfo").find("#"+house[i].id).find("select[name='liveCity']").select2("val",house[i].liveCity);
			$("#houseInfo").find("#"+house[i].id).find("select[name='liveCity']").trigger("change");
			$("#houseInfo").find("#"+house[i].id).find("td[name='liveCity1']").text($("#houseInfo").find("#"+house[i].id).find("select[name='liveCity']").find("option:selected").text());
			$("#houseInfo").find("#"+house[i].id).find("select[name='liveArea']").select2("val",house[i].liveArea);
			$("#houseInfo").find("#"+house[i].id).find("select[name='liveArea']").trigger("change");
			$("#houseInfo").find("#"+house[i].id).find("td[name='liveArea1']").text($("#houseInfo").find("#"+house[i].id).find("select[name='liveArea']").find("option:selected").text());
			$("#houseInfo").find("#"+house[i].id).find("td[name='liveAddress']").text(house[i].liveAddress);
			$("#houseInfo").find("#"+house[i].id).find("td[name='liveSituation']").text(house[i].liveSituation);
			$("#houseInfo").find("#"+house[i].id).find("td[name='getinfoTime']").text(formatTime(house[i].getinfoTime));
		}
	}
}

// 显示职业信息
function showWork(work){
	$("#workOne").find("tbody").html("");
	$("#workTwo").find("tbody").html("");
	
	if( work != null ){
		for(var i = 0 ; i < work.length; i++ ){
			// 拼接职业表格1
			var targetOne = $("#workOne");
			$("#workOneHid tbody tr").find("input[name='id']").parent("td").parent("tr").attr("id","");
			$("#workOneHid tbody tr").find("input[name='id']").parent("td").parent("tr").attr("id","One"+work[i].id);
			$("#workOneHid tbody tr").find("a").attr("onClick","removeWork('')");
			$("#workOneHid tbody tr").find("a").attr("onClick","removeWork('"+work[i].id+"')");
			var tempOne = $("#workOneHid tbody tr");
			var htmInfoOne = tempOne.cloneSelect2(true,true);
			htmInfoOne.appendTo($(targetOne));
			$("#workOne").find("#One"+work[i].id).find("input[name='id']").val(work[i].id);
			$("#workOne").find("#One"+work[i].id).find("td[name='unitName']").text(work[i].unitName);
			$("#workOne").find("#One"+work[i].id).find("select[name='unitProvince']").select2("val",work[i].unitProvince);
			$("#workOne").find("#One"+work[i].id).find("select[name='unitProvince']").trigger("change");
			$("#workOne").find("#One"+work[i].id).find("td[name='unitProvince1']").text($("#workOne").find("#One"+work[i].id).find("select[name='unitProvince']").find("option:selected").text());
			$("#workOne").find("#One"+work[i].id).find("select[name='unitCity']").select2("val",work[i].unitCity);
			$("#workOne").find("#One"+work[i].id).find("select[name='unitCity']").trigger("change");
			$("#workOne").find("#One"+work[i].id).find("td[name='unitCity1']").text($("#workOne").find("#One"+work[i].id).find("select[name='unitCity']").find("option:selected").text());
			$("#workOne").find("#One"+work[i].id).find("select[name='unitArea']").select2("val",work[i].unitArea);
			$("#workOne").find("#One"+work[i].id).find("td[name='unitArea1']").text($("#workOne").find("#One"+work[i].id).find("select[name='unitArea']").find("option:selected").text());
			$("#workOne").find("#One"+work[i].id).find("td[name='unitAddress']").text(work[i].unitAddress);
			$("#workOne").find("#One"+work[i].id).find("td[name='unitIndustry']").text(work[i].unitIndustry);
			
			// 拼接职业表格2
			var targetTwo = $("#workTwo");
			$("#workTwoHid tbody tr")
			$("#workTwoHid tbody tr").find("input[name='id']").parent("td").parent("tr").attr("id","");
			$("#workTwoHid tbody tr").find("input[name='id']").parent("td").parent("tr").attr("id","Two"+work[i].id);
			var tempTwo = $("#workTwoHid tbody tr");
			var htmInfoTwo = tempTwo.cloneSelect2(true,true);
			htmInfoTwo.appendTo($(targetTwo));
			$("#workTwo").find("#Two"+work[i].id).find("input[name='id']").val(work[i].id);
			$("#workTwo").find("#Two"+work[i].id).find("td[name='occupation']").text(work[i].occupation);
			$("#workTwo").find("#Two"+work[i].id).find("td[name='duties']").text(work[i].duties);
			$("#workTwo").find("#Two"+work[i].id).find("td[name='title']").text(work[i].title);
			$("#workTwo").find("#Two"+work[i].id).find("td[name='annualIncome']").text(work[i].annualIncome);
			$("#workTwo").find("#Two"+work[i].id).find("td[name='startingYear']").text(work[i].startingYear);
			$("#workTwo").find("#Two"+work[i].id).find("td[name='getinfoTime']").text(formatTime(work[i].getinfoTime));
		}
	}
	// 显示编号
	sort();
}

// 格式化时间为 YYYY-MM-DD
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

// 添加居住信息
function addHouse(){
	var target = $("#houseInfo");
	var temp = $("#houseHid tbody tr");
	var htmInfo=temp.cloneSelect2(true,true);
	var randomNum = getRandomNum();
	htmInfo.find("input[name='id']").parent("td").parent("tr").attr("id",randomNum);
	htmInfo.find("a").attr("onClick","removeHouse('"+randomNum+"')");
	htmInfo.appendTo($(target));
}

// 删除居住信息
function removeHouse(id){
	if(id != "" && id.length > 20){
		$.ajax({
			url:ctx+'/creditdetailed/info/removeReportHouse',
			data:"id="+id,
			type: "post",
			dataType:'json',
			success:function(data){
				if(data){
					$("#houseInfo").find("#"+id).remove();
					art.dialog.tips('删除成功!');
				}else{
					art.dialog.tips('删除失败!');
				}
			}
		});
	}else{
		$("#houseInfo").find("#"+id).remove();
	}
}

// 添加职业信息
function addWork(){
	// 拼接职业表格1
	var targetOne = $("#workOne");
	var tempOne = $("#workOneHid tbody tr");
	var htmInfoOne = tempOne.cloneSelect2(true,true);
	var randomNum = getRandomNum();
	htmInfoOne.find("input[name='id']").parent("td").parent("tr").attr("id","One"+randomNum);
	htmInfoOne.find("a").attr("onClick","removeWork('"+randomNum+"')");
	htmInfoOne.appendTo($(targetOne));
	// 拼接职业表格2
	var targetTwo = $("#workTwo");
	var tempTwo = $("#workTwoHid tbody tr");
	var htmInfoTwo = tempTwo.cloneSelect2(true,true);
	htmInfoTwo.find("input[name='id']").parent("td").parent("tr").attr("id","Two"+randomNum);
	htmInfoTwo.appendTo($(targetTwo));
	// 显示编号
	sort();
}

// 删除职位信息
function removeWork(id){
	if(id != "" && id.length > 20){
		$.ajax({
			url:ctx+'/creditdetailed/info/removeReportWork',
			data:"id="+id,
			type: "post",
			dataType:'json',
			success:function(data){
				if(data){
					$("#workOne").find("#One"+id).remove();
					$("#workTwo").find("#Two"+id).remove();
					// 显示编号
					sort();
					art.dialog.tips('删除成功!');
				}else{
					art.dialog.tips('删除失败!');
				}
			}
		});
	}else{
		$("#workOne").find("#One"+id).remove();
		$("#workTwo").find("#Two"+id).remove();
		sort();
	}
}

//显示编号
function sort(){
	// 循环公积金信息(进行排序)
	var allInfoNum = $("#workOne").find("tbody").find("span[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});
	// 循环期数(进行排序)
	var allPeriodsNum = $("#workTwo").find("tbody").find("span[name='num']");
	allPeriodsNum.each(function(n,item){
		$(this).text(n+1);
	});
}

// 保存数据
function saveData(){
	
	//验证表单
	if (!checkForm($("#formHouseWork"))) {
		return;
	}
	//验证表单
	if (!checkForm($("#detailedInfo"))) {
		return;
	}
	param = makeParam();
	var data = param+"&"+$(window.parent.document).find("form[id='param']").serialize();
	$.ajax({
		url:ctx+'/creditdetailed/info/saveData',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			initData();
			art.dialog.tips('保存成功!');
		}
	});
}

//做好保存参数
function makeParam(){
	var param = "";
	// 基本信息
	param = $("#detailedInfo").serialize();
	// 居住信息
	var houseId = $("#houseInfo").find("input[name='id']");
	var liveProvince = $("#houseInfo").find("select[name='liveProvince']");
	var liveCity = $("#houseInfo").find("select[name='liveCity']");
	var liveArea = $("#houseInfo").find("select[name='liveArea']");
	var liveAddress = $("#houseInfo").find("input[name='liveAddress']");
	var liveSituation = $("#houseInfo").find("input[name='liveSituation']");
	var getinfoTime = $("#houseInfo").find("input[name='getinfoTime']");
	
	for( var i = 0; i < houseId.length; i++){
		param+="&liveList["+i+"].id="+$(houseId[i]).val();
		param+="&liveList["+i+"].liveProvince="+$(liveProvince[i]).val();
		param+="&liveList["+i+"].liveCity="+$(liveCity[i]).val();
		param+="&liveList["+i+"].liveArea="+$(liveArea[i]).val();
		param+="&liveList["+i+"].liveAddress="+$(liveAddress[i]).val();
		param+="&liveList["+i+"].liveSituation="+$(liveSituation[i]).val();
		param+="&liveList["+i+"].getinfoTime="+$(getinfoTime[i]).val();
	}
	
	// 职业信息(第一行)
	var workId = $("#workOne").find("input[name='id']");
	var unitName = $("#workOne").find("input[name='unitName']");
	var unitProvince = $("#workOne").find("select[name='unitProvince']");
	var unitCity = $("#workOne").find("select[name='unitCity']");
	var unitArea = $("#workOne").find("select[name='unitArea']");
	var unitAddress = $("#workOne").find("input[name='unitAddress']");
	var unitIndustry = $("#workOne").find("input[name='unitIndustry']");
	// 职业信息（第二行）
	var occupation = $("#workTwo").find("input[name='occupation']");
	var duties = $("#workTwo").find("input[name='duties']");
	var title = $("#workTwo").find("input[name='title']");
	var annualIncome = $("#workTwo").find("input[name='annualIncome']");
	var startingYear = $("#workTwo").find("input[name='startingYear']");
	var getinfoTime = $("#workTwo").find("input[name='getinfoTime']");
	
	for( var w = 0; w < workId.length; w++ ){
		param+="&occupationList["+w+"].id="+$(workId[w]).val();
		param+="&occupationList["+w+"].unitName="+$(unitName[w]).val();
		param+="&occupationList["+w+"].unitProvince="+$(unitProvince[w]).val();
		param+="&occupationList["+w+"].unitCity="+$(unitCity[w]).val();
		param+="&occupationList["+w+"].unitArea="+$(unitArea[w]).val();
		param+="&occupationList["+w+"].unitAddress="+$(unitAddress[w]).val();
		param+="&occupationList["+w+"].unitIndustry="+$(unitIndustry[w]).val();
		param+="&occupationList["+w+"].occupation="+$(occupation[w]).val();
		param+="&occupationList["+w+"].duties="+$(duties[w]).val();
		param+="&occupationList["+w+"].title="+$(title[w]).val();
		param+="&occupationList["+w+"].annualIncome="+$(annualIncome[w]).val();
		param+="&occupationList["+w+"].startingYear="+$(startingYear[w]).val();
		param+="&occupationList["+w+"].getinfoTime="+$(getinfoTime[w]).val();
	}
	return param;
}
// 时间戳加上随机数(15位随机数)
function getRandomNum(){
	var data = new Date().getTime()+"";
	var num = Math.floor(Math.random()*100+1)+"";
	var randomNum = data+num;
	return randomNum;
}
