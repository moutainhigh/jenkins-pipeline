// 初始化tab
$(function(){
	
	$(".table-top span").each(function(index,element) {
		$(this).click(function(){
			$(this).addClass("click").siblings("span").removeClass("click");
			$(".content").eq(index).css("display","block").siblings(".content").css("display","none");
		})
	});
	
	hideAllData();
	$("#personCardTables").show();
	deleteCarHead();
	
	// 个人卡流水初始化操作
	$("#personCardTables").find("form[mark='grkform']").each(function(i,item){
		var result = "";
		var mt = $(this).find("td[name='lsTd']").find("tbody").find("tr").eq(0).find("td").eq(1).find("span[name='month']").html();
		if( mt.length == 2 ){
			var firstm = mt.substring(0,1);
			if(firstm == "0"){
				result = mt.substring(1,2);
			}else{
				result =mt;
			}
		}else{
			result = mt;
		}
		$(this).find("td[name='lsTd']").find("tbody").find("tr").eq(0).find("td").eq(0).find("input[name='inputMonth']").val(result);
	
		var len = $(this).find("input[mark='ch0']:checked").length;
		if( len == 12 ){
			$(this).find("input[mark='personSelectAll0']").attr("checked","checked");
		}
	
	});
	
	// 对公卡流水处理
	$("#companyCardTables").find("form[mark='dgkform']").each(function(i,item){
		var result = "";
		var mt = $(this).find("td[name='lsTd']").find("tbody").find("tr").eq(0).find("td").eq(1).find("span[name='month']").html();
		if( mt.length == 2 ){
			var firstm = mt.substring(0,1);
			if(firstm == "0"){
				result = mt.substring(1,2);
			}else{
				result =mt;
			}
		}else{
			result = mt;
		}
		$(this).find("td[name='lsTd']").find("tbody").find("tr").eq(0).find("td").eq(0).find("input[name='inputMonth']").val(result);
	
		var len = $(this).find("input[mark='ch0']:checked").length;
		if( len == 12 ){
			$(this).find("input[mark='personSelectAll0']").attr("checked","checked");
		}
	});
	
	// 单选按钮后面文字变颜色
	$("input[type='radio']").click(function(i,item){
		$(this).parents("td").find("font").attr("color","");
	});
	
	
	$("#manageProveTables").find("form").each(function(i,item){
		var len = $(this).find("td[name='dzTD']").find("span").length;
		if(len == 0){
			$(this).find("td[name='dzTD']").remove();
		}
	});
	
});

// 备注还剩多少字
function test(t){
	var ss = $(t).val();
	var len = ss.length;
	var shengyu = 500;
	if(typeof(len) != "undefined"){
		shengyu = 500 - len
	}
	$(t).parents("tr").next("tr").attr("class","");
	$(t).parents("tr").next("tr").find("font").html(shengyu);
}

//获得焦点，清除msg
function clearMsg(t){
	$(t).css("border","");
	$(t).css("border-radius","");
	$(t).next(".ketchup-error-container").remove();//.css("display","none")	;
}

// check流水
function checkLS(t){
	
	$(t).val($(t).val().replace(/^([0]{1,})|\D/g,''));
	
	
	/*var money = $(t).val();
	
	if(money != "" && typeof(money) != "undefined" && money.length > 1){
		
		for(var i = 0; i < (money.length-1); i ++){
			if(money.substring(i,1) > 0){
				$(t).val(money.substring(i,money.length));
			}
		}
	}*/
}

// 个人流水/对公流水  同业借款点选操作
function loanMarkChange(t){
	var num = $(t).val();
	if( num == "1" ){
		$(t).parents("tr").next("tr").attr("class","");
	}
	if( num == "0" ){
		$(t).parents("tr").next("tr").attr("class","hide");
	}
}

// 个人流水月输入递减显示
function showMonth(t){
	var dd = $(t).val();
	$(t).val(dd.replace(/^([0]{1,})|\D/g,''))
	var ss = $(t).val(); // 获取页面输入的月数
	if(ss > 12 || ss < 1){
		if( ss != ""){
			addMyValidateTip($(t),"请输入1-12的整数");
			$(t).val("");
			$(t).blur();
		}
	}else{
		$(t).parents("tbody").find("span[name='month']").each(function(i , item){
			if(ss != ""){
				if(ss > 0 && ss < 10){
					$(this).html("0"+ss);
				}else{
					$(this).html(ss);
				}
				ss--
				if(ss < 1){
					ss = 12;
				}
			}
		});
	}
}

// 保存有效性检查
function saveJxxjc(t){
	// 备注
	var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	saveJxxjcCommon($(t).parents("form"),dataParam);
}

// 保存有效性公共方法
function saveJxxjcCommon(f,dataParam){
	var str ="remark="+f.find("textarea[name='remark']").val();
	$("input[mark='yxxjcName']").each(function(i,item){
		str += "&YxxjcJson["+i+"].name="+$(this).val();
		str += "&YxxjcJson["+i+"].flagValue="+$(this).parent().parent("td").next("td").find("input[mark='state']:checked").val();
		str += "&YxxjcJson["+i+"].remark="+$(this).parent().parent("td").next("td").find("input[mark='flagValue']").val();
	});
	var id="";
	var inputID = f.find("input[name='id']").val();
	if(inputID != null && typeof(inputID) != "undefined"){
		id = inputID;
	}
	str = str+"&id="+id;
	$.ajax({
		type : "POST",
		data : encodeURI(str)+"&"+dataParam,
		url : ctx+"/document/datacheck/asyncSaveJxxjc",
		success : function(data) {
			f.find("input[name=id]").val(data);
		}
	});
}

// 保存个人卡/对公卡流水
function saveBankCardCommon(f,dataParam){
	
	var str = "";
	f.find("td[name='lsTd']").find("input[mark='ch0']").each(function(i,item){
		str += "&accountFlowDetail["+i+"].checkBox="+$(this).val();
		str += "&accountFlowDetail["+i+"].isChoose="+(this.checked?"1":"0");
		str += "&accountFlowDetail["+i+"].inputMoney="+$(this).parent("td").find("input[name='money']").val();
		str += "&accountFlowDetail["+i+"].inputMonth="+$(this).parent("td").find("span[name='month']").html();
	})
	
	if(checkForm(f)){
		$.ajax({
			type : "POST",
			data : f.serialize()+str+"&"+dataParam,
			url : ctx+"/document/datacheck/asyncSaveBankCard",
			success : function(data) {
				f.find("input[name=id]").val(data);
			}
		});
	}
}

// 保存资产证明
function savePropertyProve(t){
	 var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	
	
	
	savePropertyProveCom($(t).parents("form"),dataParam)
}
// 保存资产证明
function savePropertyProveCom(f,dataParam){
	var str = "";
	
	f.find("input[name='checkBox']").each(function(i,item){
		if(this.checked){
			str = str + $(this).val() + ",";
		}
	});
	if(str.length > 1){
		str = str.substring(0,str.length-1);
	}
	
	if(checkForm(f)){
		$.ajax({
			type : "POST",
			data : f.serialize()+"&"+dataParam + "&confirmStr="+str,
			url : ctx+"/document/datacheck/asyncSavePropertyProve",
			success : function(data) {
				f.find("input[name=id]").val(data);
				refreshWw();
			}
		});
	}
}

// 保存车产证明
function saveCarProve(t){
	var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	saveCarProveCom($(t).parents("form"),dataParam);
}

// 保存按钮按下调用车产证明保存
function saveCarProveCom( f , dataParam){
	var param = "";
	if(checkForm(f.parent("tbody").parent("table").parent("form"))){
		param = "id="+f.find("td input[name='id']").val();
		param = param + "&carAssessValue="+f.find("td input[name='carAssessValue']").val();
		param = param + "&pledgeFlag="+f.find("td select[name='pledgeFlag']").find("option:selected").val();
		param = param + "&vehicleBrand="+f.find("td input[name='vehicleBrand']").val();
		$.ajax({
			type : "POST",
			data : encodeURI(param)+"&"+dataParam,
			url : ctx+"/document/datacheck/asyncSaveCarProve",
			success : function(data) {
				f.find("td input[name=id]").val(data);
			}
		});
	}
}
// 保存经营证明
function saveManageProveCom(f,dataParam){
	var str = "";
	f.find("td[name='dzTD']").find("input[name='jydzzmList.id']").each(function(i,item){
		str += "&jydzzmList["+i+"].id="+$(this).val();
		str += "&jydzzmList["+i+"].placeSituation="+$(this).parent("span").find("select[name='placeSituation']").val();
		var station = $(this).parent("span").find("select[name='placeSituation']").val();
		if(station == "0"){
			str += "&jydzzmList["+i+"].jydzzmExpireDay="+"";
			str += "&jydzzmList["+i+"].jydzzmRentMonth="+"";
		}
		if(station == "1"){
			str += "&jydzzmList["+i+"].jydzzmExpireDay="+$(this).parent("span").find("input[name='jydzzmExpireDay']").val();
			str += "&jydzzmList["+i+"].jydzzmRentMonth="+$(this).parent("span").find("input[name='jydzzmRentMonth']").val();
		}
	});
	if(checkForm(f)){
		$.ajax({
			type : "POST",
			dataType:'json',
			data : f.serialize()+"&"+encodeURI(str)+"&"+dataParam,
			url : ctx+"/document/datacheck/asyncSaveManageProve",
			success : function(data) {
				if(data != null){
					f.find("input[name=id]").val(data.id);
					showGD(f, data.jyzmGdxxList);
					showDZ(f, data.jydzzmList);
				}
			}
		});
	}
}
// 保存完后显示股东信息
function showGD(f,data){
	if(data != null){
		var temp = $("#partnerCopy").html();
		f.find("td[name='gdTD']").children('span').remove();
		for(var i = 0; i < data.length; i++){
			var htm = $(temp);
			if(i == 0){
				htm.find("a[name='deleName']").remove();
			}
			htm.find("input[name='jyzmGdxx.id']").val(data[i].id);
			htm.find("a[name='deleName']").attr("onclick","partnerDel(this,'"+data[i].id+"')");
			htm.find("input[name='jyzmGdxx.gdxxGdname']").val(data[i].gdxxGdname);
			htm.find("select[name='jyzmGdxx.gdxxRelation']").val(data[i].gdxxRelation);
			htm.find("input[name='jyzmGdxx.gdxxRatio']").val(data[i].gdxxRatio);
			f.find("td[name='gdTD']").append(htm);
		}
	}
}

// 回显经营地址信息
function showDZ(f,data){
	if(data != null){
		var temp = $("#jydzCopy").html();
		f.find("td[name='dzTD']").children().remove();
		for(var i = 0; i < data.length; i++){
			var htm = $(temp);
			htm.find("input[name='jydzzmList.id']").val(data[i].id);
			htm.find("a[name='deleName']").attr("onclick","jydzDel(this,'"+data[i].id+"')");
			htm.find("select[name='placeSituation']").val(data[i].placeSituation);
			if(data[i].placeSituation == "0"){
				htm.find("input[name='jydzzmExpireDay']").val("");
				htm.find("input[name='jydzzmExpireDay']").attr("disabled","disabled");
				htm.find("input[name='jydzzmExpireDay']").removeAttr("onClick");
				htm.find("input[name='jydzzmExpireDay']").removeClass("required");
				
				htm.find("input[name='jydzzmRentMonth']").val("");
				htm.find("input[name='jydzzmRentMonth']").attr("disabled","disabled");
				htm.find("input[name='jydzzmRentMonth']").removeClass("required");
				htm.find("input[name='jydzzmRentMonth']").removeClass("number");
			}else{
				htm.find("input[name='jydzzmExpireDay']").val(formatTime(data[i].jydzzmExpireDay));
				htm.find("input[name='jydzzmRentMonth']").val(data[i].jydzzmRentMonth);
			}
			f.find("td[name='dzTD']").append(htm);
		}
	}
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
	
// 保存经营地址证明
function saveManageAddrProve(t,dataParam){
	var str = "";
	$("td[name='dzTD']").find("input[name='id']").each(function(i,item){
		str += "&jydzzmList["+i+"].id="+$(this).val();
		str += "&jydzzmList["+i+"].placeSituation="+$(this).parent("span").find("select[name='placeSituation']").val();
		var station = $(this).parent("span").find("select[name='placeSituation']").val();
		if(station == "0"){
			str += "&jydzzmList["+i+"].jydzzmExpireDay="+"";
			str += "&jydzzmList["+i+"].jydzzmRentMonth="+"";
		}
		if(station == "1"){
			str += "&jydzzmList["+i+"].jydzzmExpireDay="+$(this).parent("span").find("input[name='jydzzmExpireDay']").val();
			str += "&jydzzmList["+i+"].jydzzmRentMonth="+$(this).parent("span").find("input[name='jydzzmRentMonth']").val();
		}
	});
	$.ajax({
		type : "POST",
		data : encodeURI(str)+"&"+dataParam,
		url : ctx+"/document/datacheck/asyncSaveManageAddrProve",
		success : function(data){
			showDZ(data);
		}
	});
}
 
    // 保存购销合同
    function saveTradeContract(t){
    	 var dataParam = $(window.parent.document).find("form[id='param']").serialize();
    	 saveTradeContractCom($(t).parents("form"),dataParam);
    }
function saveTradeContractCom(f,dataParam){
	if(checkForm(f)){
		$.ajax({
			type : "POST",
			data : f.serialize()+"&"+dataParam,
			url : ctx+"/document/datacheck/asyncSaveTradeContract",
			async: false,
			success : function(data) {
				f.find("input[name=id]").val(data);
				f.attr("id",data);
			}
		});
	}
}

	// 保存社保公积金
	function saveSocialFund(t){
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		saveSocialFundCom($(t).parents("form"),dataParam);
	}
	function saveSocialFundCom(f,dataParam){
		if(checkForm(f)){
			$.ajax({
				type : "POST",
				data : f.serialize()+"&"+dataParam,
				url : ctx+"/document/datacheck/asyncSaveSocialFund",
				success : function(data) {
					f.find("input[name=id]").val(data);
				}
			});
		}
	}

// 流水全选
function personSelectAll(t, cnt){
	$(t).parents("form").find("input[mark='ch"+cnt+"']").prop("checked", t.checked);
	assignmentPersonAccount(t,cnt);
	
	
	
}

	// 给月认定收入赋值
function assignmentPersonAccount(t,cnt){
	var sum = 0;
	var personMonAvg = 0;
	var checkSize = $(t).parents("form").find("input[mark=ch"+cnt+"]:checked").length;
	$(t).parents("form").find("input[mark=ch"+cnt+"]:checked").each(function(){
		
		var ca = $(this).next("span").next("input").val()
		if(ca==""){
			$(this).next("span").next("input").val("0")
		}
		sum += parseInt($(this).next("span").next("input").val());
	});
	
	if(checkSize > 0){
		personMonAvg = (sum/checkSize).toFixed(2);
	}
	$(t).parents("form").find("input[mark='personAccount"+cnt+"']").val(personMonAvg);
	if(checkSize == 0){
		$(t).parents("form").find("input[name='accountMonthWage']").val("");
	}
}

// 个人卡流水 单个选
function personSelect(t, cnt){
	
	var size =  $(t).parents("form").find("input[mark='ch"+cnt+"']:checked").length;
	var checkSize =$(t).parents("form").find("input[mark='ch"+cnt+"']").length;
	if(size == checkSize){
		$(t).parents("form").find("input[mark='personSelectAll"+cnt+"']").prop("checked", true);
	}else{
		$(t).parents("form").find("input[mark='personSelectAll"+cnt+"']").prop("checked", false);
	}
	// 如果一个也没选，月认定收入置空
	assignmentPersonAccount(t,cnt);
}

// 经营地址证明修改
function jycsqkXiugai(t){
	var data = $(t).find("option:selected").val();
	var id = $(t).parent("span").find("input[name=id]").val();
	if(data == "0"){
		$(t).parent("span").find("input").each(function(){
			$(this).attr("disabled","disabled");
			$(this).removeClass("required");
			if($(this).attr("name") == "jydzzmExpireDay"){
				$(this).removeAttr("onClick");
				$(this).val("");
			}else{
				$(this).removeClass("number");
				$(this).val("");
			}
			
		});
	}
	if(data == "1"){
		$(t).parent("span").find("input").each(function(){
			//$(this).attr("readonly",false);
			$(this).removeAttr("disabled");
			$(this).val("");
			$(this).removeClass("required");
			$(this).addClass("required");
			if($(this).attr("name") == "jydzzmExpireDay"){
				$(this).attr("onClick","WdatePicker()");
			}else{
				$(this).addClass("number");
			}
		});
	}
	$(t).parent("span").find("input[name=id]").val(id);
	$(t).parent("span").find("input[name=id]").removeAttr("disabled");
}

//车产证明克隆
function cczmAdd(){
	temp = "<tr class='listbg'>"
	+ "<td style='width: 10%'>操作</td>"
	+ "<td style='width: 30%'>车产品牌</td>"
	+ "<td style='width: 30%'><font color='red'>*</font>车产估值(万元)</td>"
	+ "<td style='width: 30%'>抵押情况</td>"
	+ "</tr>";
	var thread = $("#carProveTables table").eq(1).find("thead").html();
	if(thread == ""){
		var html = $(temp);
		$("#carProveTables table").eq(1).find("thead").append(html);
	}
	cloneCommon($("#cczmCopy table tbody tr"),$("#carProveTables table").eq(1).find("tbody"));
}
// 经营地址证明克隆
function jydzzmAdd(){
	cloneCommon($("#jydzzmCopy form"),$("#manageAddrProveTables"));
}
// 购销合同克隆
function gxhtAdd(){
	// 购销合同 form的style控制
	var len = $("#tradeContractTables").find("form").length;
	if(len == 0){
		$("#gxhtCopy form").attr("style","margin-top:31px;");
	}else{
		$("#gxhtCopy form").attr("style","");
	}
	cloneCommon($("#gxhtCopy form"),$("#tradeContractTables"));
}
// 资产证明克隆
function zczmAdd(){
	// 房产证明 form的style控制
	var len = $("#propertyProveTables").find("form").length;
	if(len == 0){
		$("#zczmCopy form").attr("style","margin-top:31px;");
	}else{
		$("#zczmCopy form").attr("style","");
	}
	cloneCommon($("#zczmCopy form"),$("#propertyProveTables"));
}
//股东克隆
function partnerAdd(t){
	cloneCommon($("#partnerCopy span"),$(t).parents("tr").find("td"));
	$(t).next().children("span").each(function(){
		if($(this).prop("class")=="select2-chosen"){
			$(this).remove();
			$(this).next().remove();
		}
	})
}
// 经营地址克隆
function jydzAdd(t){
	var ss = $("#jydzCopy").html();
	
	var len = $(t).parents("tr").next("tr").find("td").length;
	if( len == 0 ){
		var tdTemp = "<td name='dzTD' colspan='4' style='text-align: left; padding-left: 10px'></td>";
		$(t).parents("tr").next("tr").append($(tdTemp));
	}
	$(t).parents("tr").next("tr").find("td").append($(ss));
	//$(t).parents("tr").next("tr").find("td").attr("style","border-bottom: 0;BORDER-LEFT: 0;BORDER-RIGHT:0;");
	
}
// 经营地址添加（经营地址证明信息 后面的加号触发）
function jydzJia(t,id){
	$(t).parents("tr").prev("tr").find("td").find("p").find("a").trigger("click");
}


// 经营证明克隆
function jyzmAdd(t){
	
	var i = 0
	$("#manageProveTables").find("form[name='jyzmFM']").each(function(n,itm){
		i = i+1;
	});
	if(i == 0){
		$("#jyzmCopy").find("form[name='jyzmFM']").attr("style","margin-top:31px;");
		var ss = $("#jyzmCopy").html();
		$(ss).insertAfter($(t).parents("table"))
	}else{
		$("#jyzmCopy").find("form[name='jyzmFM']").attr("style","");
		var ss = $("#jyzmCopy").html();
		$(ss).insertAfter($("#manageProveTables").find("#jyzmDIV").find("form[name='jyzmFM']:last").next("br"));
	}
}
//对公卡流水克隆
function dgklsAdd(t){
	var len = $("#companyCardTables").find("form").length;
	if(len == 0){
		$("#dgklsCopy form").attr("style","margin-top:31px;");
	}else{
		$("#dgklsCopy form").attr("style","");
	}
	cloneCommon($("#dgklsCopy form"),$("#companyCardTables"));
}
//个人卡流水克隆
function grklsAdd(t){
	
	var len = $("#personCardTables").find("form").length;
	if(len == 0){
		$("#grklsCopy form").attr("style","margin-top:31px;");
	}else{
		$("#grklsCopy form").attr("style","");
	}
	cloneCommon($("#grklsCopy form"),$("#personCardTables"));
}

// 社保公积金克隆确保不重复
function sbgjjChange(t){
	var length = $("#socialFundTables").find("form").length;
	var choose = $(t).find("option:selected").val();
	if(length == 2){
		art.dialog.tips("该选项已存在！");
		if(choose == "01"){
			$(t).val("02");
		}
		if(choose == "02"){
			$(t).val("01");
		}
	}
}

//社保公积金克隆
function sbgjjAdd(t){
	var length = $("#socialFundTables").find("form").length;
	if( length == 1){
		var choose = $("#socialFundTables").find("form").find("select[name='socialSecurityType']").find("option:selected").val();
		if(choose == "02"){
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").attr("onchange","sbgjjChange(this);");
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").find("option[value='02']").attr("selected",false);
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").find("option[value='01']").attr("selected",true);
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").val("01");
		}
		if(choose == "01"){
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").attr("onchange","sbgjjChange(this);");
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").find("option[value='01']").attr("selected",false);
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").find("option[value='02']").attr("selected",true);
			$("#sbgjjCopy form").find("select[name='socialSecurityType']").val("02");
		}
		$("#sbgjjCopy form").attr("style","");
		cloneCommon($("#sbgjjCopy form"),$("#socialFundTables"));
	}
	if( length == 0){
		
		$("#sbgjjCopy form").find("select[name='socialSecurityType']").attr("onchange","sbgjjChange(this);");
		$("#sbgjjCopy form").attr("style","margin-top:31px;");
		cloneCommon($("#sbgjjCopy form"),$("#socialFundTables"));
	}
	if(length > 1){
		art.dialog.tips("禁止添加！");
	}
		
	
	
}

//隐藏所有页签内容
function hideAllData(){
	$("#validCheckTables, #personCardTables, #companyCardTables, #propertyProveTables, #carProveTables, #manageProveTables, #manageAddrProveTables, #tradeContractTables, #socialFundTables").hide();
}
//tab点击切换方法
function showThisPage(dataId){
	hideAllData();
	$("#"+dataId).show();
}



// 删除公共方法
function  deleteCommon(t,url,val){
	var id;
	if(val!=""){
		 id = val;
	}else{
		 id = $(t).parents("form").find("input[name=id]").val();
	}
 	if(id=="" ){
 		// 如果连接为经营证明则删除父table
 		if(url == "asyncDeleteManageProve"){
 			$(t).parents("form").next("br").remove();
			$(t).parents("form").remove();
			
			$("#manageProveTables").find("form").each(function(i,item){
				if(i == 0){
 					$(this).attr("style","margin-top:31px;");
 				}else{
 					$(this).attr("style","");
 				}
			});
		}else{
			$(t).parents("form").remove();
		}
 		// 个人对公卡
 		if(url == "asyncDeleteBankCard"){
 			// 个人style控制
 			$("#personCardTables").find("form").each(function(i,item){
 				if(i == 0){
 					$(this).attr("style","margin-top:31px;");
 				}else{
 					$(this).attr("style","");
 				}
 			})
 			// 对公style控制
 			$("#companyCardTables").find("form").each(function(i,item){
 				if(i == 0){
 					$(this).attr("style","margin-top:31px;");
 				}else{
 					$(this).attr("style","");
 				}
 			})
 		}
 		// 房产信息
		if(url == "asyncDeletePropertyProve"){
			// 房产style控制
			$("#propertyProveTables").find("form").each(function(i,item){
				if(i == 0){
 					$(this).attr("style","margin-top:31px;");
 				}else{
 					$(this).attr("style","");
 				}
			});
		}
		// 购销合同
		if(url == "asyncDeleteTradeContract"){
			// 购销合同style控制
			$("#tradeContractTables").find("form").each(function(i,item){
				if(i == 0){
 					$(this).attr("style","margin-top:31px;");
 				}else{
 					$(this).attr("style","");
 				}
			});
		}
		// 社保公积金
		if(url == "asyncDeleteSocialFund"){
			// 购销合同style控制
			$("#socialFundTables").find("form").each(function(i,item){
				if(i == 0){
 					$(this).attr("style","margin-top:31px;");
 				}else{
 					$(this).attr("style","");
 				}
			});
		}
	}else{
		if(confirm("确认要删除数据吗")){
			$.ajax({
				type : "POST",
				//data : $(t).parents("form").serialize(),
				url : ctx+"/document/datacheck/"+url+"?id="+id,
				success : function(data) {
					// 如果连接为经营证明则删除父table
					if(url == "asyncDeleteManageProve"){
						$(t).parents("form").next("br").remove();
						$(t).parents("form").remove();
						
						$("#manageProveTables").find("form").each(function(i,item){
							if(i == 0){
			 					$(this).attr("style","margin-top:31px;");
			 				}else{
			 					$(this).attr("style","");
			 				}
						});
						
					}else{
						$(t).parents("form").remove();
					}
					// 个人对公流水style控制
					if(url == "asyncDeleteBankCard"){
			 			// 个人style控制
			 			$("#personCardTables").find("form").each(function(i,item){
			 				if(i == 0){
			 					$(this).attr("style","margin-top:31px;");
			 				}else{
			 					$(this).attr("style","");
			 				}
			 			})
			 			// 对公style控制
			 			$("#companyCardTables").find("form").each(function(i,item){
			 				if(i == 0){
			 					$(this).attr("style","margin-top:31px;");
			 				}else{
			 					$(this).attr("style","");
			 				}
			 			})
			 		}
					// 房产信息
					if(url == "asyncDeletePropertyProve"){
						// 房产style控制
						$("#propertyProveTables").find("form").each(function(i,item){
							if(i == 0){
			 					$(this).attr("style","margin-top:31px;");
			 				}else{
			 					$(this).attr("style","");
			 				}
						});
					}
					// 购销合同
					if(url == "asyncDeleteTradeContract"){
						// 购销合同style控制
						$("#tradeContractTables").find("form").each(function(i,item){
							if(i == 0){
			 					$(this).attr("style","margin-top:31px;");
			 				}else{
			 					$(this).attr("style","");
			 				}
						});
					}
					// 社保公积金
					if(url == "asyncDeleteSocialFund"){
						// 购销合同style控制
						$("#socialFundTables").find("form").each(function(i,item){
							if(i == 0){
			 					$(this).attr("style","margin-top:31px;");
			 				}else{
			 					$(this).attr("style","");
			 				}
						});
					}
					
				}
			}); 
		}
	}
} 

//克隆公共方法
 function cloneCommon(A,B){
 	A.clone(true).appendTo(B);
 }
//删除个人卡/对公卡流水
function deleteBankCard(t,id){
	deleteCommon(t,"asyncDeleteBankCard",id);
} 
//删除资产证明
function deletePropertyProve(t,id){
	  deleteCommon(t,"asyncDeletePropertyProve",id);
	  refreshWw();
}
//删除车产证明
function deleteCarProve(t,id){
	
	if(id != ""){
		if(confirm("确认要删除数据吗")){
		  	$.ajax({
				type : "POST",
				//data : $(t).parents("form").serialize(),
				url : ctx+"/document/datacheck/asyncDeleteCarProve?id="+id,
				success : function(data) {
					//art.dialog.tips('删除成功!');
					$(t).parents("tr").remove();
					deleteCarHead();
				}
			}); 
		}
	}else{
		var dbId = $(t).parent("td").parent("tr").find("td input[name='id']").val();
		if( dbId != "" ){
			if(confirm("确认要删除数据吗")){
			  	$.ajax({
					type : "POST",
					data : "id="+dbId,
					url : ctx+"/document/datacheck/asyncDeleteCarProve?id="+id,
					success : function(data) {
						//art.dialog.tips('删除成功!');
						$(t).parents("tr").remove();
						deleteCarHead();
					}
				}); 
			}
		}else{
			$(t).parents("tr").remove();
			deleteCarHead();
		}
	}
}
// 判断是否数据空了，若空了，清除表头
function deleteCarHead(){
	
	var i = 0;
	$("#carProveTables").find("input[name='id']").each(function(){
		i = i+1;
	});
	if(i == 0){
		$("#carProveTables table").eq(1).find("thead").html("");
	}else{
		var thread = $("#carProveTables table").eq(1).find("thead").html();
		if(thread == ""){
			temp = "<tr class='listbg'>"
				+ "<td style='width: 40%'><font color='red'>*</font>车产估值(单位/万元)</td>"
				+ "<td style='width: 40%'>抵押情况</td>"
				+ "<td style='width: 20%'>操作</td>"
				+ "</tr>";
			var html = $(temp);
			$("#carProveTables table").eq(1).find("thead").append(html);
		}
	}
}


//删除经营证明
function deleteManageProve(t,id){
	deleteCommon(t,"asyncDeleteManageProve",id);
}
//删除经营地址证明
function deleteManageAddrProve(t,id){
	deleteCommon(t,"asyncDeleteManageAddrProve",id);
}
//删除购销合同
function deleteTradeContract(t,id){
	// 删除购销合同时要刷新电话照会
	deleteCommon(t,"asyncDeleteTradeContract",id);
	
}

//删除社保公积金
function deleteSocialFund(t,id){
     deleteCommon(t,"asyncDeleteSocialFund",id);
}
//编辑
function editForm(t){
	$(t).parents("form").find("input").attr("disabled", false);
	$(t).parents("form").find("select").attr("disabled", false);
	$(t).parents("form").find("textarea").attr("disabled", false);
	$(t).parents("form").find('a[mark=editBut]').hide();
	$(t).parents("form").find('a[mark=delBut]').hide();
	$(t).parents("form").find('input[mark=saveBut]').show();
	$(t).parents("form").find('input[mark=qxBut]').show();
	$(t).parents("form").siblings("form").each(function(index,element) {
		
		$(this).find("input").attr("disabled", true);
		$(this).find("select").attr("disabled", true);
		$(this).find("textarea").attr("disabled", true);
		$(this).find('a[mark=editBut]').show();
		$(this).find('a[mark=delBut]').show();
		$(this).find('input[mark=saveBut]').hide();
		$(this).find('input[mark=qxBut]').hide();
		
	});
}
//取消编辑
function qxEditForm(t){
	
	$(t).parents("form").find("input").attr("disabled", true);
	$(t).parents("form").find("select").attr("disabled", true);
	$(t).parents("form").find("textarea").attr("disabled", true);
	$(t).parents("form").find('a[mark=editBut]').show();
	$(t).parents("form").find('a[mark=delBut]').show();
	$(t).parents("form").find('input[mark=saveBut]').hide();
	$(t).parents("form").find('input[mark=qxBut]').hide();

}
//股东删除
function partnerDel(t,id){
	var tdNum = 0;
	$(t).parent("span").parent("td").find("span").each(function(){
		tdNum = tdNum + 1;
	});
	if(tdNum == 1){
		art.dialog.tips("禁止操作！！！");
		return;
	}else{
		if(id != ""){
			var gdIdNum = 0;
			$(t).parents("td[name='gdTD']").find("input[name='jyzmGdxx.id']").each(function(s,ite){
				if($(this).val() != ""){
					gdIdNum = gdIdNum + 1;
				}
			});
			if(gdIdNum < 2){
				art.dialog.tips("请先保存一条，再删除本条！！！");
				return;
			}
		}
	}
	
	if(id==""){
		$(t).parent("span").remove();
	}else{
		if(confirm("确认要删除数据吗")){
		  	$.ajax({
				type : "POST",
				//data : $(t).parents("form").serialize(),
				url : ctx+"/document/datacheck/asyncDeleteGdxx?id="+id,
				success : function(data) {
					
					//art.dialog.tips('删除成功!');
					$(t).parent("span").remove();
				}
			}); 
		}
	}
	
}
//经营地址删除
function jydzDel(t,id){
	var form  = $(t).parents("form");
	if(id==""){
		$(t).parent("span").next("br").remove();
		$(t).parent("span").remove();
	}else{
		if(confirm("确认要删除数据吗")){
			$.ajax({
				type : "POST",
				//data : $(t).parents("form").serialize(),
				url : ctx+"/document/datacheck/asyncDeleteJydz?id="+id,
				success : function(data) {
					
					
					//art.dialog.tips('删除成功!');
					$(t).parent("span").next("br").remove();
					$(t).parent("span").remove();
					
					var len = form.find("td[name='dzTD']").find("span").length;
					
					if(len == 0){
						form.find("td[name='dzTD']").remove();
					}
				}
			}); 
		}
	}
	
	var len = form.find("td[name='dzTD']").find("span").length;
	
	if(len == 0){
		form.find("td[name='dzTD']").remove();
	}else{
		form.find("td[name='dzTD']").attr("style","text-align: left; padding-left: 10px");
	}
	
}

function biangeng(t){

var changeFlag = $(t).parents("tr").find('input:radio[name=changeFlag]:checked').val();
  if(changeFlag==1){
	   $(t).parents("tr").next("tr").find("textarea[name=jyzmChangeRemark]").val('');
	   $(t).parents("tr").next("tr").hide();
  }else{
	   $(t).parents("tr").next("tr").show();
  }
}





function provinceId(tt){
	var t =$(tt);
	var provinceId =t.val();
	var cityId = t.parents("form").find("select[mark=cityId]");
	var districtId = t.parents("form").find("select[mark=districtId]");
	if(provinceId==""){
		 cityId.empty();
		 cityId.append("<option value='' selected=true>请选择</option>");
		 cityId.trigger("change");
		 districtId.empty();
		 districtId.append("<option value='' selected=true>请选择</option>");
		 districtId.trigger("change");
	 }else{
	     $.ajax({
      		type : "POST",
      		url : ctx + "/common/selectInit/asynLoadCity",
      		data: {provinceId: provinceId},	
      		success : function(data) {
      			var resultObj = eval("("+data+")");
      	   
      		
      			cityId.empty();
      			cityId.append("<option value=''>请选择</option>");
                $.each(resultObj,function(i,item){
                	cityId.append("<option value="+item.code+">"+item.name+"</option>");
                });
                cityId.trigger("change");
                cityId.attr("disabled", false);
      		}
      	});
	 }
}


function cityId(tt){
	var t = $(tt);
	 var cityId = t.val();
	 var districtId = t.parents("form").find("select[mark=districtId]");
	 if(cityId==""){
		 districtId.empty();
		 districtId.append("<option value='' selected>请选择</option>");
		 districtId.trigger("change");
	 }else{
		 districtId.empty();
	     $.ajax({
      		type : "POST", 
      		url: ctx + "/common/selectInit/asynLoadDistrict",
      		data: {cityId: cityId},	
      		success : function(data) {
      			var resultObj = eval("("+data+")");
      			districtId.empty();
      			districtId.append("<option value='' selected>请选择</option>");
                $.each(resultObj,function(i,item){
                	districtId.append("<option value="+item.code+">"+item.name+"</option>");
                });
                districtId.trigger("change");
                districtId.attr("disabled", false);
      		}
        });
	}
}


// 限制股东信息关系属性  本人只能选择一次
function checkGdRe(t){
	var i = 0;
	$(t).parents("form").find("select[name='jyzmGdxx.gdxxRelation']").each(function(index,element){
		if($(this).find("option:selected").val() == "01"){
			i++;
		}
	})
	
	if(i > 1){
		$(t).find("option[value='02']").prop("selected",true); 
	}
}

//限制股东信息关系属性  股份之和不能大于100
function checkGdRa(t){
	var num = 0.0;
	$(t).parents("form").find("input[name='jyzmGdxx.gdxxRatio']").each(function(index,element){
		if($(this).val()!=''){
			//num = parseInt($(this).val())+ num;
			num = accAdd(num,$(this).val());
		}
		
	})
	
	if(num > 100){
		//art.dialog.tips('共计股份不能大于100');
		addMyValidateTip($(t),'和应小于100');
		$(t).val(''); 
	}
}

function zszyChange(t){
	var form = $(t).parents("form");
	
	if($(t).val() == '04'){
		form.find("td[mark=zszy]").show();
		form.find("td[mark=zszy]").next("td").show();
		form.find("td[mark=zszy]").next("td").find("input[name='accountMyselfAbstract']").addClass("required");
	}else{
		form.find("td[mark=zszy]").hide();
		form.find("td[mark=zszy]").next("td").hide();
		form.find("td[mark=zszy]").next("td").find("input[name='accountMyselfAbstract']").val('');
		form.find("td[mark=zszy]").next("td").find("input[name='accountMyselfAbstract']").removeClass("required");
	}
} 

function saveAll(){
	var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	var flag = true ;
	// 有效性检查保存
	var yxxForm = $("#validCheckTables").find("form");
	saveJxxjcCommon(yxxForm,dataParam)
	flag = checkAllForm();
	// 个人卡流水保存
	$("#personCardTables").find("form").each(function(){
		saveBankCardCommon($(this),dataParam);
	});
	// 对公卡流水保存
	$("#companyCardTables").find("form").each(function(){
		saveBankCardCommon($(this),dataParam);
	});
	// 资产证明保存
	$("#propertyProveTables").find("form[mark='zczmform']").each(function(){
		savePropertyProveCom($(this),dataParam);
	});
	// 车产证明保存
	$("#carProveTables").find("form").each(function(){
			$(this).find("tbody").find("tr").each(function(i,item){
				saveCarProveCom($(item),dataParam);
			})
	});
	// 经营证明保存
	$("#manageProveTables").find("#jyzmDIV").find("form").each(function(){
		saveManageProveCom($(this),dataParam);
	});
	// 购销合同证明保存
	$("#tradeContractTables").find("form").each(function(){
			saveTradeContractCom($(this),dataParam);
	});
	// 社保公积金保存
	$("#socialFundTables").find("form").each(function(){
			saveSocialFundCom($(this),dataParam);
	});
	if(flag){
		art.dialog.tips('保存成功!');
	}
}

// 改变zlsh的tab状态
function changeZLSHTab(id){
	$("#"+id).siblings("span").removeClass("click");
	$("#"+id).addClass("click");
}

// 定位滚动条
function turnScoll(p){
	$(document).scrollTop(p) 
}

// 
function checkFormError(name,t){
	changeZLSHTab(name+'1');
	showThisPage(name);
	turnScoll(getValidErorTop(t));
}

// 检查所有的form验证是否通过
function checkAllForm(){
	var flag = true ;
	// 个人卡流水保存
	$("#personCardTables").find("form").each(function(){
		// check工资流水
		var len = $(this).find("input[name='accountFlowMark']:checked").length;
		if(len == 0){
			$(this).find("input[name='accountFlowMark']").next("font").attr("color","red");
			flag = false;
			checkFormError('personCardTables',$(this))
		}
		
		var len = $(this).find("input[name='otherLoanMark']:checked").length;
		if(len == 0){
			$(this).find("input[name='otherLoanMark']").next("font").attr("color","red");
			flag = false;
			checkFormError('personCardTables',$(this));
		}
		
		if(!checkForm($(this))){
			flag = false;
			checkFormError('personCardTables',$(this))
		}
	});

	// 对公卡流水保存
	$("#companyCardTables").find("form").each(function(){
		
		var len = $(this).find("input[name='otherLoanMark']:checked").length;
		if(len == 0){
			$(this).find("input[name='otherLoanMark']").next("font").attr("color","red");
			checkFormError('companyCardTables',$(this))
			flag = false;
		}
		
		if(!checkForm($(this))){
			flag = false;
			checkFormError('companyCardTables',$(this));
		}
	});
	
	// 资产证明保存
	$("#propertyProveTables").find("form[mark='zczmform']").each(function(){
		if(!checkForm($(this))){
			flag = false;
			checkFormError('propertyProveTables',$(this));
		}
	});
	
	// 车产证明保存
	$("#carProveTables").find("form").each(function(){
		if(!checkForm($(this))){
			flag = false;
			checkFormError('carProveTables',$(this));
		}
	});
	
	// 经营证明保存
	$("#manageProveTables").find("form").each(function(){
		if(!checkForm($(this))){
			flag = false;
			checkFormError('manageProveTables',$(this));
		}
	});
	
	// 购销合同证明保存
	$("#tradeContractTables").find("form").each(function(){
		if(!checkForm($(this))){
			flag = false;
			checkFormError('tradeContractTables',$(this));
		}
	});
	
	// 社保公积金保存
	$("#socialFundTables").find("form").each(function(){
		if(!checkForm($(this))){
			flag = false;
			checkFormError('socialFundTables',$(this));
		}
	});
	return flag;
}

// 电话照会回调资料审核购销合同
function refreshZLSHGxht(id){
	$("#tradeContractTables").find("#"+id).find("a[name='jianImg']").attr("class","hide");
}
/**
 * 刷新外网
 */
function refreshWw(){
	if( typeof(window.parent.document.getElementById("wwsh_iframe1").contentWindow.saveAll) == "function" ){
		window.parent.document.getElementById('wwsh_iframe1').contentWindow.saveAll();
	}
}
