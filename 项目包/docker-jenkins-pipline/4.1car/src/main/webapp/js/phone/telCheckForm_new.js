$(function() {
/*	var loanMark = $("input[name='otherLoanMark']:checked").val();
		
	showOrHideBorrowerInfo(loanMark,"otherloan");
	if(loanMark=='1'){
		showFirstOtherLoan();
	}*/
	// 同业借款选择
	$('input:radio[name="otherLoanMark"]').change(function() {	
		$(this).parents("td").find("font").css("color","");
		var p1 = $("input[name='otherLoanMark']:checked").val();
		
		if(p1=='1'){
			$("tr").each(function() {
				if ($(this).attr("mark") == 'otherLoanMark_Tr') {
					this.style.display = '';
					$(this).find("input[name='monthPaybackTotalMoney']").addClass("required");
					$(this).find("input[name='comPaybackCount']").addClass("required");
				}
			});
			
		}else{
			$("tr").each(function() {
				if ($(this).attr("mark") == 'otherLoanMark_Tr') {
					this.style.display = 'none';
					$(this).find("input[name='monthPaybackTotalMoney']").removeClass("required");
					$(this).find("input[name='comPaybackCount']").removeClass("required");
				
					$(this).find("input[name='monthPaybackTotalMoney']").val("");
					$(this).find("input[name='comPaybackCount']").val("");
				}
			});
		}
	});
	
	
	// 页签切换效果
	$(".table-top span").each(function(index,element) {
		$(this).click(function(){
			$(this).addClass("click").siblings("span").removeClass("click");
			$(".content").eq(index).css("display","block").siblings(".content").css("display","none");
		})
	});
	// 隐藏所有页签
	hideAllData();
	// 显示单位电话页签
	$("#companyInfoBlock").show();	
	
	//获取本人、法人代表人、配偶的身份证号
	var customerCertnum = $("#customerCertnum").val();
	var matecertNum = $("#matecertNum").val();
	var certNum = $("#certNum").val();
	var corporateRepresentMobile = $("#corporateRepresentMobile").val();
	var ctxStatic = $("#ctxStatic").val();
	if(certNum != ""){
		if(certNum != customerCertnum && certNum != matecertNum ){
			if(certNum != customerCertnum){
				//不一致,则需要将【本人】页面“法人代表人手机号码”字段变更为“手机号码”
				$("#commonTel").text("常用手机号");
			}
			if(certNum != matecertNum){
				//不一致，则需要将【家庭联系人】页签“手机号码(法人)”字段变更为“手机号码”  padding-left: 20px;
				$("#matePhone").text("手机号码");
			}
			
			var trTpls = "<tr id='frNum'>" +
			 "	<td class='listbg'>" +
			 "		<p class='tright pr5'>法定代表人手机号</p >" +
			 "	</td>" +
			 "	<td colspan='4' style='text-align: left; padding-left: 8px;'>" +
			 "		<p>"+
			 "			<input type='checkbox' checked='checked' name='localTels' value='' />" +
			 "			<input type='text' readonly='readonly' class='input_text17801' name='corporateRepresentMobile' value='"+corporateRepresentMobile+"' mark='disabled-true' str='dhlyxxTel' >&nbsp;" +
			 "			<a onclick='addCorporateCallRecord(this,\"02\",\"corporateInfoBlock\")'><img src='"+ctxStatic+"/images/phone.png' /></a>" +
			 "		</p>" +
			 "	</td>" +
			 "</tr>";
			$("#unitTable").append(trTpls);
		}
		if(certNum == customerCertnum || certNum == matecertNum ){
			if(certNum == customerCertnum){
				//若与本人“一致”则【本人】页面“手机号码”字段变更为“法人代表人手机号”
				$("#commonTel").text("法定代表人手机号");
			}
			if(certNum == matecertNum){
				//若与配偶“一致”则【家庭联系人】页面“手机号码”字段变更为“手机号码（法人）”
				$("#matePhone").text("手机号码(法人)");
			}
			//$("#unitTable tr:last").remove();
			$("#frNum").remove();
		}
	}
	// 添加工作证明人
	$('#addWorkContactInfo').click(function(){
		// 控制style
		var len = $("#workContactBlock").find("form").length;
		if(len == 0){
			$("#workContact_Copy form").attr("style","margin-top:31px;");
		}else{
			$("#workContact_Copy form").attr("style","");
		}
		$("#workContact_Copy form").clone(true).appendTo($("#workContactBlock"));			
	});
	// 添加家庭联系人
	$('#addFamilyContactInfo').click(function(){
		// 控制style
		var len = $("#familyContactBlock").find("form").length;
		if(len == 0){
			$("#familyContact_Copy form").attr("style","margin-top:31px;");
		}else{
			$("#familyContact_Copy form").attr("style","");
		}
		$("#familyContact_Copy form").clone(true).appendTo($("#familyContactBlock"));			
	});
	// 添加其他联系人
	$('#addOtherContactInfo').click(function(){
		// 控制style
		var len = $("#otherContactBlock").find("form").length;
		if(len == 0){
			$("#otherContact_Copy form").attr("style","margin-top:31px;");
		}else{
			$("#otherContact_Copy form").attr("style","");
		}
		$("#otherContact_Copy form").clone(true).appendTo($("#otherContactBlock"));			
	});		
		
	// 自述分期
	$("select[mark='brhsMonths']").click(function() {		
		var city = $("select[mark='city']");
		var provinceId = $(this).val();		 
		if(provinceId==""){
			city.empty();
			city.append("<option value='' selected>请选择</option>");
			city.trigger("change");
		}else{
			$.ajax({
				type : "POST",
				url : ctx + "/common/selectInit/asynLoadCity",
				data: {provinceId: provinceId},	
				success : function(data) {
					var resultObj = eval("("+data+")");
					city.empty();
					city.append("<option value=''>请选择</option>");
					$.each(resultObj,function(i,item){
						city.append("<option value="+item.code+">"+item.name+"</option>");
					});
					city.trigger("change");
					city.attr("disabled", false);
				}
			});
		}
	});
	
	// 初始化加载职业分类
	showZYFL();
	
	// 单选按钮后面文字变颜色
/*	$("input[type='radio']").click(function(i,item){
		$(this).parent("p").find("font").attr("color","");
	});
	*/
	// 必填项下拉框去掉标红部分
	$("select[str]").change(function(){
		var selectVal = $(this).find("option:selected").val();
		if(selectVal != undefined && selectVal != ""){
			removeMyValidateCss($(this));
		}
	});
	
	autosize(document.querySelectorAll("textarea"));

	if(window.parent.result ==-1){
		requiredRed();
	}else if(window.parent.result ==2){
		thisRed();
	}
	
});



/**
 * 电话照会-法人代表人手机号-拨打图标
 * @param obj
 * @param voiceSrc
 * @param tab
 */
function addCorporateCallRecord(obj, voiceSrc,tab) { 
	// 保存号码及录音
	var flg = saveCorporateTel($(obj));
}

// 保存法人代表人手机号
function saveCorporateTel(t){
	
	var flg = true;
	var form = t.parents("form");
	var comTr = t.parents("tr");
	if(checkForm(form)){
		var checked = comTr.find("input[name='localTels']").prop("checked");
		var id = $("#corporateId").val();
		//var id = comTr.find("input[name='telNumId']").val();
		var corporateRepresentMobile = comTr.find("input[name='corporateRepresentMobile']").val();
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		var param = "id="+id+"&corporateRepresentMobile="+corporateRepresentMobile+"&"+dataParam
		$.ajax({
			type :"POST",
			data :param +"&localTel="+checked,
			url : ctx + "/verify/telcheck/saveCorporateTel",
			beforeSend: function(XMLHttpRequest){
            	waitingDialog.show("呼叫中...");
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
			success : function(data) {
				if(data.id != "" && data.id != null){
					comTr.find("td").eq(0).find("a[name='jianImg']").attr("class","hide");
					var htm = $("#dhly_copy").find("table").find("tbody").html();
					var JQhtm = $(htm);
					autosize(JQhtm.find("textarea"));
					JQhtm.find("input[name='recordId']").val(data.id);
					JQhtm.find("input[name='rGxId']").val(data.rGxId);
					JQhtm.find("span[name='telNm']").html(data.dhlyxxTel);
					JQhtm.find("span[name='telTm']").html(formatTime(data.dhlyxxCallTime));
					form.find("table").eq(1).find("tbody").append(JQhtm);
					// 更新外网信息
					refreshWw();
				}else{
					art.dialog.tips("拨打电话失败!");
				}
			}
		});
	}
}



// 定位位置
function pageScroll(obj){
	var lastObj = $(obj).parents("div").find("form:last");		
	var topPosition = lastObj.offset().top;
	var height = lastObj.height();
	$(document).scrollTop(topPosition + height);
}
	
// 隐藏所有页签内容
function hideAllData(){
	$("#companyInfoBlock, #workContactBlock, #familyContactBlock, #otherContactBlock, #personalConfirmBlock, #busiContractBlock").hide();
}
// tab点击切换方法
function showThisPage(dataId){
	hideAllData();
	$("#"+dataId).show();
	autosize.update($("#"+dataId).find("textarea"));
}
// 拼接电话号码及电话录音参数(单位信息)
function makeCompanyDataWithAll(t){
	var form = t;
	var telNumIds = form.find("input[name='telNumId']");
	var phoneNumberSources = form.find("select[name='phoneNumberSource']");
	var workUnitTels = form.find("input[name='workUnitTel']");
	var workUnitScale = form.find("input[name='workUnitScale']");
	
	var param = "";
	for(var i = 0; i < telNumIds.length; i++){
		param += '&telNumList['+i+'].id=' + $(telNumIds[i]).val();
		param += '&telNumList['+i+'].workTelSource=' + $(phoneNumberSources[i]).val();
		param += '&telNumList['+i+'].workUnitTel=' + $(workUnitTels[i]).val();
		param += '&telNumList['+i+'].workUnitScale=' + $(workUnitScale[i]).val();
	}
	
	var lyxx = makeDataWithAll(form);
	if(lyxx != ""){
		param = param + lyxx
	}
	return param;
}

//拼接电话号码及电话录音参数(关系人信息)
function makeContactDataWithAll(t){
	var form = t;
	var phoneNumIds = form.find("input[name='phoneNumId']");
	var sources = form.find("select[name='source']");
	var brhsPhones = form.find("input[name='brhsPhone']");
	var types = form.find("input[name='phoneType']");
	var param = "";
	for(var i = 0; i < phoneNumIds.length; i++){
		param += '&contactNumList['+i+'].id=' + $(phoneNumIds[i]).val();
		param += '&contactNumList['+i+'].source=' + $(sources[i]).val();
		param += '&contactNumList['+i+'].brhsPhone=' + $(brhsPhones[i]).val();
		param += '&contactNumList['+i+'].type=' + $(types[i]).val();
	}
	
	var lyxx = makeDataWithAll(form);
	if(lyxx != ""){
		param = param + lyxx
	}
	return param;
}

// 判断是否有新的单位电话且判断来源是否为申请人
function checkNewWorkPhone(f){
	var result = "0";// 没有新加单位号码
	// 获取ID如果ID为空则为新添加的号码
	var telNumIds = f.find("input[name='telNumId']");
	// 获取电话来源01为申请人提供
	var phoneNumberSources = f.find("select[name='phoneNumberSource']");
	for(var i = 0; i < telNumIds.length; i++){
		if( $(telNumIds[i]).val() == "" ){
			result = "1"; //有新加单位号码
			if( $(phoneNumberSources[i]).val() == "01" ){
				result = "2"; //有新加单位号码且新加的号码来源为申请人提供
			}
		}
	}
	return result;
}

/**
 * 电话照会-单位电话-拨打图标
 * @param obj
 * @param voiceSrc
 * @param tab
 */
function addCompanyCallRecord(obj, voiceSrc,tab) {  
	// 保存号码及录音
	var flg = saveOneCompany($(obj));
}

// 保存单个单位号码
function saveOneCompany(t){
	var flg = true;
	var form = t.parents("form");
	var comTr = t.parents("tr");
	if(checkForm(form)){
		var checked = comTr.find("input[name='localTels']").prop("checked");
		// 用于判断是否为新添加的联系人信息
		var id = comTr.find("input[name='telNumId']").val();
		var workUnitTel = comTr.find("input[name='workUnitTel']").val();
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		var param = "id="+id+"&workUnitTel="+workUnitTel+"&"+dataParam
		$.ajax({
			type :"POST",
			data :param +"&localTel="+checked,
			url : ctx + "/verify/telcheck/saveOneCompanyNum",
			beforeSend: function(XMLHttpRequest){
            	waitingDialog.show("呼叫中...");
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
			success : function(data) {
				if(data.id != "" && data.id != null){
					comTr.find("td").eq(0).find("a[name='jianImg']").attr("class","hide");
					var htm = $("#dhly_copy").find("table").find("tbody").html();
					var JQhtm = $(htm);
					autosize(JQhtm.find("textarea"));
					JQhtm.find("input[name='recordId']").val(data.id);
					JQhtm.find("input[name='rGxId']").val(data.rGxId);
					JQhtm.find("span[name='telNm']").html(data.dhlyxxTel);
					JQhtm.find("span[name='telTm']").html(formatTime(data.dhlyxxCallTime));
					form.find("table").eq(1).find("tbody").append(JQhtm);
					// 更新外网信息
					refreshWwSh();
				}else{
					art.dialog.tips("拨打电话失败!");
				}
			}
		});
	}
}

/**
 * 添加 单个 联系人电话信息 
 * @param obj
 * @param voiceSrc
 * @param tab
 */
function addCallRecord(obj, voiceSrc, tab) {
	// 保存号码及录音
	saveOneContact($(obj));
	refreshWwSh();
}

// 点击电话按钮保存单个form
function saveOneContact(t){
	var form = t.parents("form");
	var checked = form.find("input[name='localTels']").prop("checked");
	if(checkForm(form)){
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		
		// 当前电话信息的tr
		var tr = t.parents("tr");
		// 关系人ID
		var rDhgxshId = form.find("input[name='id']").val();
		// 电话ID
		var phoneNumId = tr.find("input[name='phoneNumId']").val();
		// 单位电话
		var brhsPhone = tr.find("input[name='brhsPhone']").val();
		// 来源
		var source = tr.find("select[name='source']").val();
		// 类型
		var type = tr.find("input[name='phoneType']").val();
		
		// 提交的数据
		var data = {"rDhgxshId":rDhgxshId, "id":phoneNumId, "brhsPhone":brhsPhone, "source":source, "type":type, "localTel":checked};
		$.ajax({
			type : "POST",
			data : data,
			url : ctx + "/verify/telcheck/saveCallContactInfoNew",
			beforeSend: function(XMLHttpRequest){
            	waitingDialog.show("呼叫中...");
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
			success : function(data) {
				if( data != null){
					if(data.id != "" && data.id != null){
						// 电话只读
						tr.find("input[name='brhsPhone']").attr("readonly","readonly");
						// 来源只读
						tr.find("select[name='sourcesource']").attr("disabled","disabled");
						
						// 隐藏删除按钮
						tr.find("td").eq(0).find("a[name='jianImg']").attr("class","hide");
						form.find("a[name='aID']").attr("class","hide");
						//form.find("input[name='id']").val(data.rGxId);
						
						var htm = $("#dhly_copy").find("table").find("tbody").html();
						var JQhtm = $(htm);
						autosize(JQhtm.find("textarea"));
						JQhtm.find("input[name='recordId']").val(data.id);
						JQhtm.find("input[name='rGxId']").val(data.rGxId);
						JQhtm.find("span[name='telNm']").html(data.dhlyxxTel);
						JQhtm.find("span[name='telTm']").html(formatTime(data.dhlyxxCallTime));
						form.find("table[name='gxrDhlyxx']").eq(0).find("tbody").append(JQhtm);
					}else{
						art.dialog.tips("拨打电话失败!");
					}
				}
			}
		});
	}
}

//添加本人电话录音信息
function addPerson(obj , voiceSrc , tab){
	saveOnePerson( $(obj) )
}

// 保存
function saveOnePerson(t){
	var form = t.parents("form");
	var checked = form.find("input[name='localTels']").prop("checked");
	if(checkForm(form)){
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		$.ajax({
			type:"POST",
			data:form.serialize() + "&"+ dataParam +"&localTel="+checked,
			url :ctx + "/verify/telcheck/saveOnePersonNum",
			beforeSend: function(XMLHttpRequest){
            	waitingDialog.show("呼叫中...");
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
			success : function(data) {
				if( data != null){
					if(data.id != "" && data.id != null){
						// 隐藏删除图标
						t.parents("tr").eq(0).find("td").eq(0).find("#jian").attr("class","hide");
						// 设置为只读
						t.parents("tr").eq(0).find("td").eq(1).find("input").attr("readonly","readonly");
						// 设置为只读（禁止获取值）
						t.parents("tr").eq(0).find("td").eq(3).find("select").attr("disabled","disabled");
						
						form.find("input[name='id']").val(data.rGxId);
						var htm = $("#dhly_copy").find("table").find("tbody").html();
						var JQhtm = $(htm);
						autosize(JQhtm.find("textarea"));
						JQhtm.find("input[name='recordId']").val(data.id);
						JQhtm.find("input[name='rGxId']").val(data.rGxId);
						JQhtm.find("span[name='telNm']").html(data.dhlyxxTel);
						JQhtm.find("span[name='telTm']").html(formatTime(data.dhlyxxCallTime));
						/*form.find("table").eq(1).find("tbody").append(JQhtm);*/
						$("#personalConfirmBlock").find("#brlyxx").find("table").find("tbody").append(JQhtm);
					}else{
						art.dialog.tips("拨打电话失败!");
					}
				}
			}
		});
	}
}

// 拼接电话录音信息
function makeDataWithAll(t){
	var form = t;
	var answerInfos = form.find("textarea[name='dhlyxxAnswerInf']");
	var rGxId = form.find("input[name='rGxId']");
	var answerStates = form.find("select[name='dhlyxxAnswerState']");
	var recordIds = form.find("input[name='recordId']");
	var param = "";
	for(var i = 0; i < recordIds.length; i++){
		param += '&dhlyxx['+i+'].rGxId=' + $(rGxId[i]).val();
		param += '&dhlyxx['+i+'].dhlyxxAnswerInf=' + $(answerInfos[i]).val();
		param += '&dhlyxx['+i+'].dhlyxxAnswerState=' + $(answerStates[i]).val();
		param += '&dhlyxx['+i+'].id=' + $(recordIds[i]).val();
	}
	return param;
}

// 隐藏或显示切换
/*function showOrHideBorrowerInfo(mark, type) {
	if (mark == '1') {
		$("tr").each(function() {
			if ($(this).attr("mark") == type) {
				this.style.display = '';
				$(this).find("input").addClass("required");
			}
		});
	} else if (mark != '1') {
		$("tr").each(function() {
			if ($(this).attr("mark") == type) {
				this.style.display = 'none';
				$(this).find("input").removeClass("required");
			}
		});
	}
}*/

//隐藏或显示申请人分类切换
function dictCustomerDiffChange(t) {
	
	var val = $(t).find("option:selected").val();
	var lab = $(t).find("option:selected").html();
	if(val == '0'){
		$("tr").each(function() {
			if ($(this).attr("mark") == 'dictCustomerDiff_Tr') {
				this.style.display = '';
				$(this).find("input[name='brhsUnitScale']").addClass("required");
				$(this).find("input[name='averageMonthTurnover']").addClass("required");
			}
		});
		
	}else{
		$("tr").each(function() {
			if ($(this).attr("mark") == 'dictCustomerDiff_Tr') {
				this.style.display = 'none';
				$(this).find("input[name='brhsUnitScale']").removeClass("required");
				$(this).find("input[name='averageMonthTurnover']").removeClass("required");
			}
		});
	}
	
}


//隐藏或显示通话状态下的文本域
function dhlyxxAnswerStateChange(t) {
	//文件域所在的td
	var tdTextarea = $(t).parent().parent().next().children("td");
	var textareaVal = tdTextarea.find("textarea[name='dhlyxxAnswerInf']");
	var val = $(t).find("option:selected").val();
	var lab = $(t).find("option:selected").html();
	if(val == '0'){
	//	alert("接通");
		tdTextarea.attr("style","display:''");
	}else{
		tdTextarea.attr("style","display:none");
		textareaVal.val("");
		
	}
	
}

// 同业借款效果显示
/*function showFirstOtherLoan(){
	var length = $('tr[mark="otherloan"]').length;		
	if(length==0){
		var htmlTemplate='<tr id="otherloan_0" mark="otherloan">'+
		'<td class="listbg"><p class=" tright pr5">'+
		'<a href="#" class="mr1" onclick="addOtherLoan()">&nbsp;<img'+
		'		src="'+ctxStatic+'/images/jia.png"></a>&nbsp;<input type="hidden" name="otherLoanList[0].id" '+
		'title="otherLoanId" value="" />'+
		'<font color="red">*</font>借款金额</p></td>'+
		'<td><input type="text" class="input_text17801 required number" name="otherLoanList[0].loanAmount" class="input_text178_70" /></td>'+
		'<td class="listbg"><p class="tright pr5"><font color="red">*</font>借款期限</p></td>'+
		'<td><input type="text" onfocus="clearMsg(this);" onblur="checkJkqx(this);" class="input_text17801 required integer" mark="jkqx" name="otherLoanList[0].loanMonths" class="input_text178_70" />'+
		'	</td>'+
		'</tr>';
		$('#otherloan_src').after(htmlTemplate);
	}	
}*/

// 添加同业借款
/*function addOtherLoan(){
	var length = $('tr[mark="otherloan"]').length;
	var lastIndex = length - 1;
	if(length>0){
		var htmlTemplate='<tr id="otherloan_'+length+'" mark="otherloan">'+
		'<td class="listbg"><p class=" tright pr5">'+
		'<a href="#" class="mr1" onClick="delOtherLoan(this)">&nbsp;'+					
		'<img src="'+ctxStatic+'/images/jian.png""></a>&nbsp;<input type="hidden" name="otherLoanList['+length+'].id" '+
		'title="otherLoanId" value="" />'+
		'<font color="red">*</font>借款金额</p></td>'+
		'<td><input type="text" class="input_text17801 required number" name="otherLoanList['+length+'].loanAmount" class="input_text178_70" /></td>'+
		'<td class="listbg"><p class="tright pr5"><font color="red">*</font>借款期限</p></td>'+
		'<td><input type="text" onfocus="clearMsg(this);" onblur="checkJkqx(this);" class="input_text17801 required integer" mark="jkqx" name="otherLoanList['+length+'].loanMonths" class="input_text178_70" />'+
		'</td>'+
		'</tr>';
		$('#otherloan_'+lastIndex).after(htmlTemplate);
	}
}*/	

// 检测借款期限
/*function checkJkqx(t){
	var jkqx = $(t).val();
	if(jkqx > 400){
		addMyValidateTip($(t),"请输入小于400的值");
	}
}*/

// 删除同业借款
/*function delOtherLoan(obj){
	var otherLoanId = $(obj).parent().parent().find("input[title='otherLoanId']").val();
	if(otherLoanId != "" && typeof(otherLoanId) != "undefined"){
		if(confirm("确定删除这条记录？")){
			$.ajax({
				type : "POST",
				data: {otherLoanId: otherLoanId},
				url : ctx + "/verify/telcheck/delOtherLoan",
				success : function(data) {
					if(data == "true"){
						removeOtherLoan(obj);
						alert("删除成功！");
					}else{
						alert("删除失败！");	
					}
				}
			});
		}
	}else{
		removeOtherLoan(obj);
	}		
}*/

// 删除同业借款
function removeOtherLoan(obj){
	var currentId = $(obj).parent().parent().parent().attr('id');
	var rowNumber = currentId.charAt(currentId.length-1);
	$(obj).parent().parent().parent().remove();
	
	var length = $('tr[mark="otherloan"]').length;
	for(var i=rowNumber-1;i<length;i++){
		var j = i+1;
			$('#otherloan_'+j).attr('id','#otherloan_' + i);	
	}
}	
// 1,0互换
function reverse(p){
	//1,0互相转换
	if(p == '1'){
		p = '0';
	}else if(p == '0'){
		p = '1';
	}
	return p;
}
var successCount=new ArrayList();
/**
 * 通用的成功提示
 */
function successAlert(){
	successCount.clear();
	art.dialog.tips('保存成功!');
}
/***
 * 单位信息保存按钮click
 */
function saveUnit(){
	successCount.clear();
	successCount.setCount($("#companyInfoBlock").find("form").length);
	$("#companyInfoBlock").find("form").each(function(){
		submitCompanyInfoWithAll($(this));
	});
	refreshWwSh();
}
/**
 * 保存单位信息
 */
function submitCompanyInfoWithAll(t){
	var form = t;
	if(!checkForm(form)){
		return;
	}
	// 用于判断是否为新添加的联系人信息
	// 拼接电话号码及电话录音参数
	var param = makeCompanyDataWithAll(t);
	// 判断是否有新单位电话且电话为申请人
	//var hasNewPh = checkNewWorkPhone(t);
	$.ajax({
		type : "POST",
		data : form.serialize() + encodeURI(param),
		url : ctx + "/verify/telcheck/saveCompanyInfo",
		success : function(data) {
			if(data != null){
				if(data.telNumList != null  ){
					var numList = data.telNumList;
					var trLis = $(form).find("tr[name='unitTel']");
					for(var i = 1 ; i < numList.length; i++ ){
						$(trLis[i]).find("td").find("p").find("input[name='telNumId']").val(numList[i].id);
						$(trLis[i]).find("td").eq(0).find("p").find("a[name='jianImg']").attr("onClick","delUnitTel(this,'"+numList[i].id+"');");
						if(i > 0){
							if($(trLis[i]).attr("saveflg") == "unsave"){
								$(trLis[i]).find("td").eq(1).find("p").find("a").eq(0).attr("class","");
								$(trLis[i]).find("td").eq(1).find("input[name='workUnitTel']").attr("readonly","readonly");
								$(trLis[i]).find("td").eq(3).find("select[name='phoneNumberSource']").attr("disabled","disabled");
								$(trLis[i]).attr("saveflg","save");
							}
						}
					}
					/*if(hasNewPh == "0" || hasNewPh == "2"){
						refreshNw();
					}*/
					successCount.addCall(successAlert);
				}
			}
		}
	});
}
/***
 * 家庭联系人保存按钮click
 */
function saveFamily(){
	successCount.clear();
	successCount.setCount($("#familyContactBlock").find("form").length);
	$("#familyContactBlock").find("form").each(function(){
		submitContactInfoWithAll($(this));
	});
	// 刷新外网审核
	refreshWwSh();
}
/**
 * 工作证明人保存按钮click
 */
function saveWork(){
	successCount.clear();
	successCount.setCount($("#workContactBlock").find("form").length);
	$("#workContactBlock").find("form").each(function(){
		submitContactInfoWithAll($(this));
	});
	// 刷新外网审核
	refreshWwSh();
}
/**
 * 其它联系人保存按钮click
 */
function saveOther(){
	successCount.clear();
	successCount.setCount($("#otherContactBlock").find("form").length);
	$("#otherContactBlock").find("form").each(function(){
		submitContactInfoWithAll($(this));
	});
	// 刷新外网审核
	refreshWwSh();
}
/**
 * 保存【家庭联系人、工作证明人、其它联系人】通用方法
 */
function submitContactInfoWithAll(t) {
	var form = t;
	form.find("selected[name='dictCertType']").removeAttr("disabled");
	if(!checkForm(form)){
		return;
	}	
	// 用于判断是否为新添加的联系人信息
	var id = form.find("input[name='id']").val();
	var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	
	// 拼接电话号码及电话录音参数
	var param = makeContactDataWithAll(form);
	
	$.ajax({
		type : "POST",
		data : form.serialize()+encodeURI(param)+"&" + dataParam,
		async : false,
		url : ctx + "/verify/telcheck/saveContactInfoNew",
		success : function(data) {
			if(data.contactNumList != null  ){
				form.find("selected[name='dictCertType']").attr("disabled","disabled");

				var numList = data.contactNumList;
				var trLis = $(form).find("tr[name='dhgxshPhone']");
				for(var i = 0 ; i < numList.length; i++ ){
					// 设置主键id
					form.find("input[name='id']").val(numList[i].rDhgxshId);
					// form暂存图标隐藏
					form.find("a[name='zcContact']").attr("class","hide");
					
					$(trLis[i]).find("td").find("p").find("input[name='phoneNumId']").val(numList[i].id);
					$(trLis[i]).find("td").eq(0).find("p").find("a[name='jianImg']").attr("onClick","delContactTel(this,'"+numList[i].id+"');");
					// 显示拨打图标
					$(trLis[i]).find("td").eq(1).find("p").find("a").eq(0).attr("class","");
					// 设置电话为readonly
					$(trLis[i]).find("td").eq(1).find("input[name='brhsPhone']").attr("readonly","readonly");
					// 设置来源为disabled
					$(trLis[i]).find("td").eq(3).find("select[name='source']").attr("disabled","disabled");
					$(trLis[i]).attr("saveflg","save");
					
				}

			}
			successCount.addCall(successAlert);
		}
	});
}
/**
 * 本人核实保存按钮click
 */
function saveOwn(){
	successCount.clear();
	successCount.setCount($("#personalConfirmBlock").find("form[id='br']").length+$("#personalConfirmBlock").find("form[id='brdhxx']").length);
	$("#personalConfirmBlock").find("form[id='br']").each(function(){
		var flg = true;
/*		$(this).find("input[mark='jkqx']").each(function(){
			if($(this).val()>400){
				checkJkqx($(this));
				flg = false;
			}
		});*/
		if(flg){
			submitPersonalInfoWithAll($(this));
		}
	});
	$("#personalConfirmBlock").find("form[id='brdhxx']").each(function(){
		submitPersonalNumWithAll($(this));
	});
	refreshWwSh();//刷新外网
}
/**
 * 保存本人
 */
function submitPersonalInfoWithAll(t) {
	var form = t;
	if(!checkForm(form)){
		return;
	}
	// 拼接电话录音参数
	var lyxx = $("#personalConfirmBlock").find("#brlyxx").find("table").find("tbody");
	var param = makeDataWithAll(lyxx);
	$.ajax({
		type : "POST",
		data : form.serialize() + encodeURI(param),
		url : ctx + "/verify/telcheck/saveBorrowerInfo",
		success : function(data) {
			var type = data.split('-');
			var other = type[1].split(',');
			var brshd = type[2].split(',');
			var mainId = type[0].split(":")
			form.find("input[name=id]").val(mainId[0]);
			
			if(mainId[1] != null && mainId[1] != "" && mainId[1] == "1"){
				var otherId = form.find("input[title=otherLoanId]");
				for(var i = 0;i<otherId.length;i++){
					otherId[i].value = other[i];
				}
				var brshdId = form.find("input[title=brshdId]");
				for(var i = 0;i<brshdId.length;i++){
					brshdId[i].value = brshd[i];
				}
			}
			if(mainId[1] != null && mainId[1] != "" && mainId[1] == "0"){
				form.find("input[title=otherLoanId]").parents("tr").remove();
				
				var brshdId = form.find("input[title=brshdId]");
				for(var i = 0;i<brshdId.length;i++){
					brshdId[i].value = brshd[i];
				}
			}
			successCount.addCall(successAlert);
		}
	});
}
/**
 * 保存本人核实电话号码及录音
 */
function submitPersonalNumWithAll(t){
	var form = t;
	if(!checkForm(form)){
		return;
	}
	// 用于判断是否为新添加的本人电话号码
	var id = form.find("input[name='id']").val();
	// 拼接电话录音参数
	$.ajax({
		type : "POST",
		data : form.serialize(),
		url : ctx + "/verify/telcheck/saveBorrowerTelNum",
		success : function(data) {
			// 如果是新添加的本人电话号码，赋ID，置灰，显示拨打电话按钮
			if(id == null || id == ""){
				form.find("input[name='id']").val(data);
				form.find("a[mark='makeCall']").removeAttr("class");
				// 拨号图片显示
				t.find("tbody").find("tr").eq(0).find("td").eq(1).find("a").attr("class","");
				// 电话只读
				t.find("tbody").find("input[name='brhsPhone']").attr("readonly","readonly");
				// 来源只读
				t.find("tbody").find("select[name='source']").attr("disabled","disabled");
			}
			successCount.addCall(successAlert);
		}
	});			
}	

 
// 改变DHZH的tab状态
function changeDHZHTab(id){
 	$("#"+id).siblings("span").removeClass("click");
 	$("#"+id).addClass("click");
}	 
/***
 * 定位滚动条
 * @param p
 */
function turnScoll(p){
 	$(document).scrollTop(p);
}	 
/**
 * 聚焦页签显示
 * @param name
 * @param t
 */
function checkFormError(name,t){
	changeDHZHTab(name+'1');
	showThisPage(name);
}	 

// 插入到div下
function appendDiv(id,data){
	$(id).empty();
	$(id).append(data);
}
/***
 * 电话照会-家庭联系人：与借款人关系选择时触发
 * @param tt
 */
function relationChange(tt){
	var relation = $(tt).val();
	var form = $(tt).parents("form");
	if(relation == 2){
		art.dialog.tips("禁止选择配偶");
		$(tt).val("");
	}
}
/***
 * 省级联动
 * @param tt
 */
 function provinceId(tt){
	var t =$(tt);
	var provinceId =t.val();
	var cityId = t.parent().find("select[mark=cityId]");

	var districtId = t.parent().find("select[mark=districtId]");
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
/***
 * 市级联动
 * @param tt
 */
function cityId(tt){
	var t = $(tt);
	var cityId = t.val();
	var districtId = t.parent().find("select[mark=districtId]");
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


/**
 * 电话照会-本人：添加居住地址
 */
var trLength = 0;
function addBrshd(){
	if(trLength == null || trLength == 0 ){
		trLength = $('tr[mark="brshdTr"]').length;
	}
	
	// 复制
	$('tr[mark="brshdTr"]:last').after($('tr[mark="brshdTr"]:last').clone(true));
	// 修改添加图片为删除，修改A的事件
	$('tr[mark="brshdTr"]:last').find("a[mark='brshdA']").attr("onclick","delBrshd(this)");
	$('tr[mark="brshdTr"]:last').find("img[mark='brshdImg']").attr("src",ctxStatic+"/images/jian.png");
	
	// 改ID的name
	$('tr[mark="brshdTr"]:last').find("input[mark='brshdId']").attr("name","brshdList["+trLength+"].id");
	// 清空id
	$('tr[mark="brshdTr"]:last').find("input[mark='brshdId']").attr("value","");
	// 改省的name
	$('tr[mark="brshdTr"]:last').find("select[mark='provinceId']").attr("name","brshdList["+trLength+"].liveProvince");
	// 改市的name
	$('tr[mark="brshdTr"]:last').find("select[mark='cityId']").attr("name","brshdList["+trLength+"].liveCity");
	// 改区的name
	$('tr[mark="brshdTr"]:last').find("select[mark='districtId']").attr("name","brshdList["+trLength+"].liveArea");
	// 改地址的name
	$('tr[mark="brshdTr"]:last').find("input[mark='address']").attr("name","brshdList["+trLength+"].liveAddress");
	$('tr[mark="brshdTr"]:last').find("select").eq(0).val("");
	
	// 去除disabled
	$('tr[mark="brshdTr"]:last').find("select").each(function(){
		$(this).removeAttr("disabled"); 
	});
	
	$('tr[mark="brshdTr"]:last').find("select").eq(1).find("option").each(function(){
		if($(this).val() == ""){
			$(this).attr("selected","selected");
		}else{
			$(this).remove();
		}
	});
	$('tr[mark="brshdTr"]:last').find("select").eq(2).find("option").each(function(){
		if($(this).val() == ""){
			$(this).attr("selected","selected");
		}else{
			$(this).remove();
		}
	});
	$('tr[mark="brshdTr"]:last').find("input[mark='address']").val("");
	$('tr[mark="brshdTr"]:last').find("input[mark='address']").removeAttr("disabled");// 去除居住详细地址disabled
	
	trLength+=1;
}	



/**
 * 新增单位电话
 * 0:固话；1：手机
 */
function addContactPhone(t)
{
	// 当前电话信息的tr
	var tr = $(t).parents("tr");
	// 类型
	var type = tr.find("input[name='phoneType']").val();
	// 默认是手机
	trs = $("#contactInfoPhone_Copy tr");
	if (type=='0') {
		// 赋值宅电
		trs = $("#contactInfoContact_Copy tr");
	}
	trs.find("select[name='source']").find("option[value='02']").remove();
	trs.clone(true).appendTo($(t).parents("tbody"));
}

/**
 * 新增关系人电话
 */
function addUnitTel(t)
{
	var length = $(t).parents("form:first").find('input:radio[mark="assessmentResult"]').length/3;
	var trs = $("#companyInfo_Copy tr");
	trs.find("select[name='phoneNumberSource']").find("option[value='02']").remove();
	trs.clone(true).appendTo($(t).parents("tbody"));
}

//来源清除红色
function sourceChange(t){
	if($(t).val() != ""){
		clearMsg(t);
	}
}

//获得焦点，清除msg
function clearMsg(t){
	$(t).css("border","");
	$(t).css("border-radius","");
	$(t).next(".ketchup-error-container").remove();//.css("display","none")	;
}

/**
 * 新增固话/本人手机
 * @param t this对象
 * @param type 0：固话;1:手机号
 */
function addPersonalNums(t,type){	
	if(type=='1'){
		var temp =$("#personalConfirm_Copy").html();
		var html = $(temp);
		html.find("input[id='type']").val("1");
		html.find("font[id='phone']").html("常用手机号");
		html.find("a[id='jia']").hide();
		html.attr("name","sjhm")
		var table = $("#personalConfirmBlock").find("form[name='sjhm']:last");
		html.insertAfter(table);
		//pageScroll(html);
		html.find("input[name='brhsPhone']").focus();
	}else{
		var temp =$("#personalConfirm_Copy").html();
		var html = $(temp);
		html.find("input[id='type']").val("0");
		html.find("font[id='phone']").html("宅电");
		html.find("a[id='jia']").hide();
		html.attr("name","jtgh");
		html.find("input[name='brhsPhone']").removeClass("mobile");
		html.find("input[name='brhsPhone']").addClass("simplephone");
		var table = $("#personalConfirmBlock").find("form[name='jtgh']:last");
		html.insertAfter(table);
		//pageScroll(html);
		html.find("input[name='brhsPhone']").focus();
	}
}

// 刷新单位信息
function refreshCompany(){
	var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	$.ajax({
		url : ctx + "/verify/telcheck/refreshDwxx",
		data : dataParam,
		type : "POST",
		dataType:'json',
		success : function(data) {
			// 不为空
			if(data != null && data.companyList != null){
				// form 全部置为删除标志
				$("#companyInfoBlock").find("form").each(function(i,item){
					$(item).attr("delflg","del");
				});
				// tr 全部置为删除标志
				$("#companyInfoBlock").find("tr[name='unitTel']").each(function(i,item){
					$(item).attr("delflg","del");
				});
				// 单位列表list
				var company = data.companyList;
				for(var i = 0; i < company.length; i++){
					// 根据ID获取页面form的长度
					var formLen = $("#companyInfoBlock").find("#"+company[i].id).length;
					// 如果大于0说明页面有这个单位信息
					if( formLen > 0 ){
						$("#companyInfoBlock").find("#"+company[i].id).attr("delflg","undel");
						$("#companyInfoBlock").find("#"+company[i].id).find("p[name='workUnitname']").html(company[i].workUnitname);
						// 获取该单位下的电话列表
						var telNum = company[i].telNumList;
						// 电话不为空的场合
						if(telNum != null){
							for( var n = 0; n < telNum.length; n++ ){
								var newNum = true;// 是否为新电话的变量
								$("#companyInfoBlock").find("#"+company[i].id).find("tr[name='unitTel']").each(function(t,itm){
									
									// 如果电话ID等于页面电话tr的data属性值
									if( telNum[n].id == $(itm).attr("data") ){
										// 不是新添加的电话
										newNum = false;
										// 设置电话tr删除标志为-不删除
										$(itm).attr("delflg","undel");
										// 给手机号码重新赋值
										$(itm).find("input[name='workUnitTel']").val(telNum[n].workUnitTel);
										// 给来源重新赋值
										$(itm).find("select[name='phoneNumberSource']").val(telNum[n].workTelSource);
										// 给异常记录重新赋值
										$(itm).next("tr").find("input[name='exceptionRecord']").val(telNum[n].exceptionRecord);
									}
								});
								
								// 如果newNum为true的场合，则添加电话
								if(newNum){
									var copyTelTr = $("#companyInfo_Copy").find("table tbody").clone();
									// 更改data
									copyTelTr.find("tr[name='unitTel']").attr("data",telNum[n].id);
									// 保存标识
									copyTelTr.find("tr[name='unitTel']").attr("saveflg","save");
									// 删除标识
									copyTelTr.find("tr[name='unitTel']").attr("delflg","undel");
									// 给手机号码重新赋值
									copyTelTr.find( "input[name='telNumId']").val(telNum[n].id );
									// 给手机号码重新赋值
									copyTelTr.find( "input[name='workUnitTel']").val(telNum[n].workUnitTel );
									copyTelTr.find( "input[name='workUnitTel']").attr("readonly","readonly");
									// 给来源重新赋值
									copyTelTr.find( "select[name='phoneNumberSource']").val(telNum[n].workTelSource );
									copyTelTr.find( "select[name='phoneNumberSource']").attr("disabled","disabled");
									// 给评估结果赋值
									// copyTelTr.find( "input[title='assessmentResult']:last").attr("checked","checked");
									copyTelTr.find( "input[mark='assessmentResult']").attr("name","tel["+n+"].assessmentResult");
									// 给异常记录重新赋值
									copyTelTr.find( "input[name='exceptionRecord']").val(telNum[n].exceptionRecord );
									// 拨号图片显示
									copyTelTr.find("tr").eq(0).find("td").eq(1).find("a").attr("class","");
									// 获取页面已有电话个数
									var len = $( "#companyInfoBlock").find("#"+company[i].id).find("tr[name='unitTel']" ).length;
									if(len > 0){
										copyTelTr.find("tr").eq(0).find("td").eq(0).find("a[name='jianImg']").attr("onclick","delUnitTel(this,'"+telNum[n].id+"')");
										//copyTelTr.find("tr").eq(0).find("td").eq(0).find("img").remove();
										// 大于0的情况 放在最后一个电话后面
										copyTelTr.find("tr").insertAfter( $("#companyInfoBlock").find("#"+company[i].id).find("tr[name='unitTel']:last").next("tr") )
									}else{
										copyTelTr.find("tr").eq(0).find("td").eq(0).find("a[name='jianImg']").eq(0).attr("onClick","addUnitTel(this);");
										copyTelTr.find("tr").eq(0).find("td").eq(0).find("img").eq(0).attr("src",ctxStatic + "/images/jia.png");
										// 不大于0的情况 放在unitTel_Root后面
										copyTelTr.find("tr").insertAfter( $("#companyInfoBlock").find("#"+company[i].id).find("tr[name='unitTel_Root']") )
									}
								}
							}
						}
					}else{
						// 获取单位模板
						var JQpany = $("#company_Copy").find("form").clone();
						JQpany.attr("id",company[i].id);// id
						JQpany.attr("delflg","undel");// 设置删除标志为非删除
						JQpany.find("input[name='id']").val(company[i].id);// 设置删除标志为非删除
						JQpany.find("p[name='workUnitname']").html(company[i].workUnitname);//单位名称
						JQpany.find("select[name='workInfoSource']").val(company[i].workInfoSource);//单位来源
						var sourTxt = JQpany.find("select[name='workInfoSource']").find("option:selected").text();
						JQpany.find("select[name='workInfoSource']").hide();
						JQpany.find("select[name='workInfoSource']").parents("td").html(sourTxt);
						// 获取该单位下的电话列表
						var telNum = company[i].telNumList;
						for( var n = 0; n < telNum.length; n++ ){
							
							var copyTelTr = $("#companyInfo_Copy").find("table tbody").clone();
							// 更改data
							copyTelTr.find("tr[name='unitTel']").attr("data",telNum[n].id);
							// 保存标识
							copyTelTr.find("tr[name='unitTel']").attr("saveflg","save");
							// 删除标识
							copyTelTr.find("tr[name='unitTel']").attr("delflg","undel");
							// 给手机号码重新赋值
							copyTelTr.find( "input[name='telNumId']").val(telNum[n].id );
							// 给手机号码重新赋值
							copyTelTr.find( "input[name='workUnitTel']").val(telNum[n].workUnitTel );
							copyTelTr.find( "input[name='workUnitTel']").attr("readonly","readonly");
							// 给来源重新赋值
							copyTelTr.find( "select[name='phoneNumberSource']").val(telNum[n].workTelSource );
							copyTelTr.find( "select[name='phoneNumberSource']").attr("disabled","disabled");
							// 给评估结果赋值
							// copyTelTr.find( "input[title='assessmentResult']:last").attr("checked","checked");
							copyTelTr.find( "input[mark='assessmentResult']").attr("name","tel["+n+"].assessmentResult");
							// 给异常记录重新赋值
							copyTelTr.find( "input[name='exceptionRecord']").val(telNum[n].exceptionRecord );
							// 拨号图片显示
							copyTelTr.find("tr").eq(0).find("td").eq(1).find("a").attr("class","");
							// 获取页面已有电话个数
							var len = JQpany.find("tr[name='unitTel']" ).length;
							if(len > 0){
								copyTelTr.find("tr").eq(0).find("td").eq(0).find("a[name='jianImg']").attr("onclick","delUnitTel(this,'"+telNum[n].id+"')");
								//copyTelTr.find("tr").eq(0).find("td").eq(0).find("img").remove();
								// 大于0的情况 放在最后一个电话后面
								copyTelTr.find("tr").insertAfter( JQpany.find("tr[name='unitTel']:last").next("tr") )
							}else{
								copyTelTr.find("tr").eq(0).find("td").eq(0).find("a[name='jianImg']").eq(0).attr("onClick","addUnitTel(this);");
								copyTelTr.find("tr").eq(0).find("td").eq(0).find("a").eq("0").removeAttr("name");
								copyTelTr.find("tr").eq(0).find("td").eq(0).find("img").eq(0).attr("src",ctxStatic + "/images/jia.png");
								// 不大于0的情况 放在unitTel_Root后面
								copyTelTr.find("tr").insertAfter( JQpany.find("tr[name='unitTel_Root']") )
							}
						}
						var formLen = $("#companyInfoBlock").find("form").length;
						if(formLen > 0 ){
							JQpany.insertAfter($("#companyInfoBlock").find("form:last"));
						}else{
							$("#companyInfoBlock").append(JQpany);
						}
					}
				};
				$("#companyInfoBlock").find("form[delflg='del']").remove();
				$("#companyInfoBlock").find("tr[delflg='del']").next("tr").remove();
				$("#companyInfoBlock").find("tr[delflg='del']").remove();
			}else{
				$("#companyInfoBlock").find("form").remove();
			}
			// 控制form的style
			$("#companyInfoBlock").find("form").each(function(i,item){
				if(i == 0){
					$(this).attr("style","margin-top:31px;");
				}else{
					$(this).attr("style","");
				}
			});
		},
		error:function(data){
			$("#companyInfoBlock").find("form").remove();
		}
	});
}

// 弹出div层（行业选取用）
function showDIV(id,t){
	$("#"+id).modal('show');
}

// 隐藏行业选择页面
function hideDiv(id){
	$("#"+id).modal('hide');
}

function play(phoneNumber,key)
{
	if(key!=''&&phoneNumber!=''){
		$.ajax({
			type:"POST",
			url:ctx+"/verify/telcheck/getRecord",
			data:{callId:key},
			dataType : 'json',
			success:function(data){
				if(data.url!=undefined)
				window.parent.AudioPlay.getInstance().play(data.url,phoneNumber,key);
			}
		});
	}else{
		art.dialog.tips("没有可以播放的音频文件！");
	}
	//url="http://www.w3school.com.cn/i/horse.ogg";
	//window.parent.AudioPlay.getInstance().play(url,phoneNumber,key);
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
			day="0"+tim.getDate();
		}
		var hour = tim.getHours(); //获取系统时
		if(hour < 10){
			hour = "0"+tim.getHours();
		}
		var minite = tim.getMinutes(); //分
		if(minite < 10){
			minite = "0"+tim.getMinutes();
		}
		var second = tim.getSeconds(); //秒
		if(second < 10){
			second = "0"+tim.getSeconds();
		}
		result = tim.getFullYear()+"-"+month+"-"+day+" "+hour+":"+minite+":"+second;
	}
	return result;
}

//设置行业分类值
var dialog=null;
function setValue(event){
	var value = $(event).attr("val");// 当前所选的code
	var lab = $(event).attr("lab");// 当前所选的label
	
	var rootId = $(event).attr("rootid");// 一级行业的id
	var rootcode = $(event).attr("rootcode");// 一级行业对应你的value
	
	var secondid = $(event).attr("secondid");// 二级行业对应的id
	var secondcode = $(event).attr("secondcode");// 二级行业对应的value
	
	var thirdid = $(event).attr("thirdid");// 三级行业对应的id
	var thirdcode = $(event).attr("thirdcode");// 三级行业对应的value
	
	// 一级行业的value
	$("#dictCompIndustry").val(rootcode);
	// 二级行业的value
	$("#dictCompIndustrySecond").val(secondcode);
	// 三级行业的value
	$("#dictCompIndustryThird").val(thirdcode);
	
	// 字体内容
	$("#industryLabel").val(lab);
	// 一级ID
	$("#comp_industry_rootId").val(rootId+","+secondid+","+thirdid);
	
	// 如果选择其他，则显示备注
	if(lab == '其他（请填写）'){
		$("#industryRemark").attr("class","required");
	}else{
		$("#industryRemark").attr("class","hide");
		$("#industryRemark").val("");
	}
	if(dialog!=null) dialog.close();
	
	// 职业分类
	$.ajax({
		type :"POST",
		data :"parentId="+rootId+","+secondid+","+thirdid,
		dataType:"json",
		url : ctx + "/verify/telcheck/getOccupationType",
		success : function(data) {
			if(data != null){
				$("select[name='brhsProfessionalCode']").html("");
				var choose = "<option value=''>请选择</option>";
				$("select[name='brhsProfessionalCode']").append($(choose));
				var oth = ""
				for(var i = 0; i < data.length; i++){
					oth +="<option value='"+data[i].value+"'>"+data[i].label+"</option>" 
				}
				$("select[name='brhsProfessionalCode']").append($(oth));
			}
		}
	});
}

function selectIndustry(t){
	// 如果有标红则去掉标红
	removeMyValidateCss($("#industryLabel"));
	//隐藏行业备注
	$(t).next("input").attr("class","hide");
	dialog=art.dialog({
				title: "选择行业代码",
				fixed:false,
				lock: false,
				width:350,
				window: "top",
				border: false,
				content: $("#divIndustryCode").html()
			});
}

// 单位性质切换
function unitChange(t){
	
	var val = $(t).find("option:selected").val();
	var lab = $(t).find("option:selected").html();
	if(val == '6'){
		$("input[name='unitPropertiesRemark']").attr("class","required");
	}else{
		$("input[name='unitPropertiesRemark']").attr("class","hide");
		$("input[name='unitPropertiesRemark']").val("");
	}
}

// 职业分类切换
function professionChange(t){
	var val = $(t).find("option:selected").val();
	var lab = $(t).find("option:selected").html();
	if(val == 'd06'){
		$("input[name='professionRemark']").attr("class","required");
	}else{
		$("input[name='professionRemark']").attr("class","hide");
		$("input[name='professionRemark']").val("");
	}
}

//住宅类别切换
function customerHouseHoldPropertyChange(t){
	
	var val = $(t).find("option:selected").val();
	var lab = $(t).find("option:selected").html();
	if(val == '7'){
		$("input[name='customerHouseHoldPropertyRemark']").attr("class","required");
	}else{
		$("input[name='customerHouseHoldPropertyRemark']").attr("class","hide");
		$("input[name='customerHouseHoldPropertyRemark']").val("");
	}
}

//主要借款用途切换
function dictLoanUseDictChange(t){
	
	var val = $(t).find("option:selected").val();
	var lab = $(t).find("option:selected").html();
	if(val == '12'){
		$("input[name='dictLoanUseRemark']").attr("class","required");
	}else{
		$("input[name='dictLoanUseRemark']").attr("class","hide");
		$("input[name='dictLoanUseRemark']").val("");
	}
}

//其他收入来源切换
function otherIncomeResourceChange(mark){
	
	if(mark.checked == true && mark.value == '5'){
		$("input[name='otherIncomeResourceRemark']").attr("class","required");
		
	} else if(mark.checked == false && mark.value == '5'){
		$("input[name='otherIncomeResourceRemark']").attr("class","hide");
		$("input[name='otherIncomeResourceRemark']").val("");
	}
	
}

//工作证明人、其他联系人 关系：其他
function loanManRelationChange(t){
	// 当前电话信息的tr
	var tr = $(t).parents("tr");
	var loanManRelationRemark = tr.find("input[name='loanManRelationRemark']");
	
	var val = $(t).find("option:selected").val();
	var lab = $(t).find("option:selected").html();
	if(val == '2'){
		loanManRelationRemark.attr("class","required");
	}else{
		loanManRelationRemark.attr("class","hide");
		loanManRelationRemark.val("");
	}
}



// 初始化加载职业分类
function showZYFL(){
	var zyfl = $("input[name='professionHid']").val();
	// 职业分类
	$.ajax({
		type :"POST",
		data :"value="+zyfl,
		dataType:"json",
		url : ctx + "/verify/telcheck/getZYFL",
		success : function(data) {
			if(data != null){
				$("select[name='brhsProfessionalCode']").html("");
				var choose = "<option value=''>请选择</option>";
				$("select[name='brhsProfessionalCode']").append($(choose));
				var oth = ""
				for(var i = 0; i < data.length; i++){
					if(data[i].value == zyfl){
						oth +="<option selected='selected' value='"+data[i].value+"'>"+data[i].label+"</option>"; 
						if(data[i].label == '其他'){
							$("input[name='professionRemark']").attr("class","");
						}else{
							$("input[name='professionRemark']").attr("class","hide");
						}
					
					}else{
						oth +="<option value='"+data[i].value+"'>"+data[i].label+"</option>"; 
					}
				}
				$("select[name='brhsProfessionalCode']").append($(oth));
			}
		}
	});
}
//******************************************删除处理开始***************************************
/**
 * 删除居住地址
 * @param tt 当前对象
 */
function delBrshd(tt){
	var id = $(tt).parents("p:first").find("input[title='brshdId']").val();
	if(id != ""){
		if(confirm("确定删除这条记录？")){
			$.ajax({
				type : "POST",
				data: {id: id},
				url : ctx + "/verify/telcheck/delbrshd",
				success : function(data) {
					if(data == "true"){
						$(tt).parents("tr:first").remove();
						art.dialog.tips('删除成功!');
					}else{
						art.dialog.tips('删除失败!');
					}
				}
			});
		}
	}else{
		$(tt).parents("tr:first").remove();
	}
}

/***
 * 删除联系人共通方法
 * @param t this对象
 * @param id 对应的ID
 */
function delFamily(t,id,tab){
	if( id != "" && typeof(id) != "undefined" ){
		if (confirm("确认要删除数据")){
			delContact(t,id);
			familyStyle(tab);
		}
	}else{
		var idHid = $(t).parents("tbody").find("input[name='id']").val();
		if(idHid != "" && typeof(idHid) != "undefined" ){
			if (confirm("确认要删除数据")){
				delContact(t,idHid);
				familyStyle(tab);
			}
		}else{
			$(t).parents("form").remove();
			familyStyle(tab);
		}
	}
}
//家庭联系人style 控制
function familyStyle(tab){
	$("#"+tab).find("form").each(function(i,item){
		if(i == 0){
			$(this).attr("style","margin-top:31px;");
		}else{
			$(this).attr("style","");
		}
	});
}
/***
 * AJAX删除联系人
 * @param t this对象
 * @param id 对应的ID
 */
function delContact(t,id){
	$.ajax({
		url : ctx + "/verify/telcheck/delContactNew",
		data : {"id":id},
		type : "POST",
		dataType:'json',
		success : function(data) {
			if(data.deleteFlag == "1"){
				$(t).parents("form").remove();
				refreshWwSh();//刷新外网
				if(data.repeateFlag == "true"){
					refreshNw([]);//调用内网
				}
				art.dialog.tips("删除成功！");
			}else{
				art.dialog.tips("删除失败！");
			}
		}
	});
}

/**
 * 删除单位电话
 * @param t this对象
 * @param id 对应对象Id
 * @param isRepeat 是否查重
 */
function delUnitTel(t,id,isRepeat) {
	if (id == '') {
		deltel(t);
	} else {
		if (confirm("确认要删除数据")) {
			var currTr = $(t).parents("tr:first");
			var newNumid = currTr.find("input[name='telNumId']").val();
			$.ajax({
				type : "POST",
				data : {id : newNumid},
				url : ctx + "/verify/telcheck/deleteCompanyTelNum",
				success : function(data) {
					if(data.deleteFlag == "1"){
						deltel(t);//删除页面对应数据块
						refreshWwSh();//刷新外网
						if(data.repeateFlag == "true"){
							refreshNw([]);//调用内网
						}
						art.dialog.tips("删除成功！");
					}else{
						art.dialog.tips("删除失败！");
					}
				}
			});
		}
	}
}

/**
 * 删除关系人单位电话
 * @param t this对象
 * @param id 对应对象Id
 * @param isRepeat 是否查重
 */
function delContactTel(t,id,isRepeat) {
	if (id == '') {
		deltel(t);
	} else {
		if (confirm("确认要删除数据")) {
			var currTr = $(t).parents("tr:first");
			var newNumid = currTr.find("input[name='phoneNumId']").val();
			$.ajax({
				type : "POST",
				data : {id : newNumid},
				url : ctx + "/verify/telcheck/deleteContactTelNum",
				success : function(data) {
					if(data.deleteFlag == "1"){
						deltel(t);//删除页面对应数据块
						refreshWwSh();//刷新外网
						if(data.repeateFlag == "true"){
							refreshNw([]);//调用内网
						}
						art.dialog.tips("删除成功！");
					}else{
						art.dialog.tips("删除失败！");
					}
				}
			});
		}
	}
}

// 删除单位电话
function deltel(t) {
	var one = $(t).parents("tr");
	var two = one.next();
	one.remove();
	two.remove();
}
/**
 * 电话照会-本人： 删除本人手机/固话
 */ 
function delPersonalNums(t){
	var id = $(t).parents("form").find("input[name='id']").val();
	if(id != "" && typeof(id) != "undefined"){
		if(confirm("确定删除这条记录？")){
			var id = $(t).parents("p:first").find("input[name='id']").val();;
			$.ajax({
				type : "POST",
				data: {id: id},
				url : ctx + "/verify/telcheck/delPersonalNums",
				success : function(data) {
					if(data.deleteFlag == "1"){
						$(t).parents("form").remove();
						refreshWwSh();//刷新外网
						if(data.repeateFlag == "true"){
							refreshNw([]);//调用内网
						}
						art.dialog.tips("删除成功！");
					}else{
						art.dialog.tips("删除失败！");
					}
				}
			}); 
		}
	}else{
		$(t).parents("form").remove();
	}
}
//******************************************删除处理结束***************************************

//******************************************暂存处理开始***************************************
/**
 * 家庭联系人、工作证明人、其他联系人共用的暂存
 */
function zcContact(t,id){
	var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	var form = $(t).parents("form");
	
	if(!checkForm(form)){
		return;
	}
	
	form.find("select[name='source']").removeAttr("disabled");
	
	// 拼接电话号码及电话录音参数
	var param = makeContactDataWithAll(form);

	//执行暂存操作
	$.ajax({
		type : "POST",
		data : form.serialize() + "&" + dataParam + encodeURI(param),
		url : ctx + "/verify/telcheck/zcContactNew",
		success : function(data) {
			if(data.contactNumList != null  ){
				var numList = data.contactNumList;
				var trLis = $(form).find("tr[name='dhgxshPhone']");
				for(var i = 0 ; i < numList.length; i++ ){
					// 设置主键id
					form.find("input[name='id']").val(numList[i].rDhgxshId);
					// form暂存图标隐藏
					form.find("a[name='zcContact']").attr("class","hide");
					
					$(trLis[i]).find("td").find("p").find("input[name='phoneNumId']").val(numList[i].id);
					$(trLis[i]).find("td").eq(0).find("p").find("a[name='jianImg']").attr("onClick","delContactTel(this,'"+numList[i].id+"');");
					// 显示拨打图标
					$(trLis[i]).find("td").eq(1).find("p").find("a").eq(0).attr("class","");
					// 设置电话为readonly
					$(trLis[i]).find("td").eq(1).find("input[name='brhsPhone']").attr("readonly","readonly");
					// 设置来源为disabled
					$(trLis[i]).find("td").eq(3).find("select[name='source']").attr("disabled","disabled");
					$(trLis[i]).attr("saveflg","save");
					
				}
				refreshWwSh();
				/*if(hasNewPh == "0" || hasNewPh == "2"){
					refreshNw();
				}*/
				art.dialog.tips("保存成功!");
			}
		}
	});
	
	form.find("select[name='source']").attr("disabled","disabled");
}

/**
 * 新版申请表增加
 * 单位电话、宅电暂存
 */
function zcContactPhone(t){
	// 当前关系人的form
	var form = $(t).parents("form");
	// 当前电话信息的tr
	var tr = $(t).parents("tr");
	// 验证tr
	if(!checkNoForm(tr)){
		return;
	}
	// 来源
	tr.find("select[name='source']").removeAttr("disabled");
	
	// 关系人ID
	var rDhgxshId = form.find("input[name='id']").val();
	// 电话ID
	var phoneNumId = tr.find("input[name='phoneNumId']").val();
	// 单位电话
	var brhsPhone = tr.find("input[name='brhsPhone']").val();
	// 来源
	var source = tr.find("select[name='source']").val();
	// 类型
	var type = tr.find("input[name='phoneType']").val();
	
	// 提交的数据
	var data = {"rDhgxshId":rDhgxshId, "id":phoneNumId, "brhsPhone":brhsPhone, "source":source, "type":type};
	// 新增关系人
	if (rDhgxshId == "") {
		// 暂存整个form
		zcContact(t,'');
		return;
	}

	$.ajax({		
		type :"POST",
		data :data,
		url : ctx + "/verify/telcheck/zcContactPhone",
		success : function(data) {
			if(data != null && data != ""){
				var getID = data.split(",")[0];
				var chachong = data.split(",")[1];
				
				tr.find("a[name='jianImg']").attr("onclick","delContactTel(this,'"+getID+"')");
				
				// form暂存图标隐藏
				form.find("a[name='zcContact']").attr("class","hide");
				
				// 单位电话ID
				tr.find("input[name='phoneNumId']").val(getID);
				// 设置关系人主键
				if (rDhgxshId == "") {
					// 关系人主键
					var id = data.split(",")[2];
					// 关系人主键
					form.find("input[name='id']").val(id);
				}

				// 单位电话
				tr.find("input[name='brhsPhone']").attr("readonly","readonly");
				// 来源
				tr.find("select[name='source']").attr("disabled","disabled");
				// 暂存图标隐藏
				tr.find("a[name='zc']").attr("class","hide");
				// 显示拨打图标
				tr.find("td").eq(1).find("p").find("a").eq(0).attr("class","");
				// 刷新外网
				refreshWwSh();
				
				if(chachong == "true"){
					//调用内网
					refreshNw([brhsPhone]);
					
					art.dialog.tips("保存成功,新添加的号码有查重信息!");
				}
				art.dialog.tips("保存成功!");
			}
		}
	});
	
	tr.find("select[name='source']").attr("disabled","disabled");
}

/**
 * 单位电话暂存
 */
function zcCompany(t){
	var form = $(t).parents("form");
	var tr = $(t).parents("tr");
	if(!checkNoForm(tr)){
		return;
	}
	
	tr.find("select[name='phoneNumberSource']").removeAttr("disabled");
	
	// 关联ID
	var workId = form.find("input[name='id']").val();
	// 单位电话ID
	var telNumId = tr.find("input[name='telNumId']").val();
	// 单位电话
	var workUnitTel = tr.find("input[name='workUnitTel']").val();
	// 来源
	var phoneNumberSource = tr.find("select[name='phoneNumberSource']").val();
	$.ajax({
		type :"POST",
		data :{"workId":workId,"id":telNumId,"workUnitTel":workUnitTel,"workTelSource":phoneNumberSource},
		url : ctx + "/verify/telcheck/zcCompany",
		success : function(data) {
			if(data != null && data != ""){
				var getID = data.split(",")[0];
				var chachong = data.split(",")[1];
				tr.find("a[name='jianImg']").attr("onclick","delUnitTel(this,'"+getID+"')");
				tr.find("td").eq(1).find("a").attr("class","");
				// 单位电话ID
				tr.find("input[name='telNumId']").val(getID);
				// 单位电话
				tr.find("input[name='workUnitTel']").attr("readonly","readonly");
				// 来源
				tr.find("select[name='phoneNumberSource']").attr("disabled","disabled");
				// 暂存图标隐藏
				tr.find("a[name='zc']").attr("class","hide");
				// 刷新外网
				refreshWwSh();
				if(chachong == "true"){
					//调用内网
					refreshNw([workUnitTel]);
					art.dialog.tips("保存成功,新添加的号码有查重信息!");
				}
				art.dialog.tips("保存成功!");
			}
		}
	});
	
	tr.find("select[name='phoneNumberSource']").attr("disabled","disabled");
}

/**
 * 本人核实【家庭固话、手机号码】暂存
 */
function zcBr(t){
	var form = $(t).parents("form");
	if(!checkForm(form)){
		return;
	}
	form.find("select[name='source']").removeAttr("disabled");
	$.ajax({
		type : "POST",
		data : form.serialize(),
		url : ctx + "/verify/telcheck/zcBrTelNum",
		success : function(data) {
			// 如果是新添加的本人电话号码，赋ID，置灰，显示拨打电话按钮
			if(data != null && data != ""){
				var getId = data.split(",")[0];
				var chachong = data.split(",")[1];
				var phoneNumber = form.find("tbody").find("input[name='brhsPhone']").val();
				form.find("input[name='id']").val(getId);
				form.find("a[mark='makeCall']").removeAttr("class");
				// 拨号图片显示
				form.find("tbody").find("tr").eq(0).find("td").eq(1).find("a").attr("class","");
				// 电话只读
				form.find("tbody").find("input[name='brhsPhone']").attr("readonly","readonly");
				// 来源只读
				form.find("tbody").find("select[name='source']").attr("disabled","disabled");
				// 暂存图标隐藏
				form.find("a[name='zc']").attr("class","hide");
				//刷新外网
				refreshWwSh();
				if(chachong == "true"){
					//调用内网
					refreshNw([phoneNumber]);
					art.dialog.tips("保存成功,新添加的号码有查重信息!");
				}
				art.dialog.tips("保存成功!");
			}
		}
	});
	
	form.find("select[name='source']").attr("disabled","disabled");
}
//******************************************暂存处理结束***************************************
/**
 * 刷新外网
 */
function refreshWw(){
	if( typeof(window.parent.document.getElementById("wwsh_iframe1").contentWindow.saveAll) == "function" ){
		window.parent.document.getElementById('wwsh_iframe1').contentWindow.saveAll();
	}
}
/**
 * 刷新外网审核
 */
function refreshWwSh(){
	window.parent.document.getElementById('wwsh_iframe1').contentWindow.location.reload();
}

/**
 * 刷新内网
 */
function refreshNw(list){
	if( typeof(window.parent.document.getElementById("nwsh_iframe1").contentWindow.refreshThisTab) == "function" ){
		window.parent.document.getElementById('nwsh_iframe1').contentWindow.refreshThisTab(list);
	}
}

/***
 * 一键保存
 */	 
function saveAll(){
	var flag = true ;
	flag = checkALL();
	if(flag){
		// 保存单位信息
		$("#companyInfoBlock").find("form").each(function(){
			submitCompanyInfoWithAll($(this));
		});
		
		// 保存工作证明人
		$("#workContactBlock").find("form").each(function(){
			submitContactInfoWithAll($(this));
		});
		
		// 保存家庭联系人
		$("#familyContactBlock").find("form").each(function(){
			submitContactInfoWithAll($(this));
		});
		
		// 保存其它联系人
		$("#otherContactBlock").find("form").each(function(){
			submitContactInfoWithAll($(this));
		});
		
		// 保存本人
		$("#personalConfirmBlock").find("form[id='br']").each(function(){
			submitPersonalInfoWithAll($(this));
		});
		// 保存本人 电话信息
		$("#personalConfirmBlock").find("form[id='brdhxx']").each(function(){
			submitPersonalNumWithAll($(this));
		});
		art.dialog.tips('保存成功!');
		refreshWwSh();
	}
}

// 验证电话照会所有表单
function checkALL(){
	var flag = true ;
	// 保存单位信息
	$("#companyInfoBlock").find("form").each(function(){
		// 验证表单
		if(checkForm($(this))){
		}else{
			flag =false;
			checkFormError('companyInfoBlock',$(this));
		}
	});
	
	// 保存家庭联系人
	$("#familyContactBlock").find("form").each(function(){
		// 判断评估结果是否选取
		//验证表单
		if(checkForm($(this))){
		
		}else{
			flag =false;
			checkFormError('familyContactBlock',$(this));
		}
	});
	
	// 保存本人
	$("#personalConfirmBlock").find("form[id='br']").each(function(){
		
		var len = $(this).find("input[name='otherLoanMark']:checked").length;
		if(len == 0){
			$(this).find("input[name='otherLoanMark']").next("font").attr("color","red");
			flag =false;
			checkFormError('personalConfirmBlock',$(this));
		}
		
		$(this).find("input[mark='jkqx']").each(function(){
			if($(this).val()>400){
				flag =false;
				checkFormError('personalConfirmBlock',$(this));
			}
		});
		
		if(checkForm($(this))){
			
		}else{
			flag =false;
			checkFormError('personalConfirmBlock',$(this));
		}
	});
	
	// 保存本人 电话信息
	$("#personalConfirmBlock").find("form[id='brdhxx']").each(function(){
		//验证表单
		if(checkForm($(this))){
		
		}else{
			flag =false;
			checkFormError('personalConfirmBlock',$(this));
		}
	});
	
	// 保存工作证明人
	$("#workContactBlock").find("form").each(function(){
		
		if(checkForm($(this))){
		
		}else{
			flag =false;
			checkFormError('workContactBlock',$(this));
		}
	});
	
	// 保存其它联系人
	$("#otherContactBlock").find("form").each(function(){
		
		if(checkForm($(this))){
		
		}else{
			flag =false;
			checkFormError('otherContactBlock',$(this));
		}
	});
	return flag;
} 
/**
 * 只是行业代码标红
 */
function thisRed(){
	clearAllRed();
	$("#personalConfirmBlock1").siblings("span").removeClass("click");
	$("#personalConfirmBlock1").addClass("click");
	showThisPage("personalConfirmBlock");
	$("#personalConfirmBlock").click();
	var workAttr = $("#workAttr option:selected").val();
	if(workAttr == ""){
		addMyValidateCss($("#workAttr"));
	}
	var industryLabel = $("#industryLabel").val();
	if(industryLabel == ""){
		addMyValidateCss($("#industryLabel"));
	}
	var jobClass = $("#jobClass option:selected").val();
	if(jobClass == ""){
		addMyValidateCss($("#jobClass"));
	}
}
/**
 * 所有必填项标红
 */
function requiredRed(){
	clearAllRed();
	$("#personalConfirmBlock1").siblings("span").removeClass("click");
	$("#personalConfirmBlock1").addClass("click");
	showThisPage("personalConfirmBlock");
	$("#personalConfirmBlock").click();
	
	//先获取所有的select
	var selects = $("#personalConfirmBlock").find("select[str='required']");
	if(selects != undefined && selects.length>0){
		$(selects).each(function(index,data){
			var selected = $(data).find("option:selected").val();
			if(selected == undefined || selected == ""){
				addMyValidateCss($(data));
			}
		});
	}
	// 再后去所有的input
	var inputs = $("#personalConfirmBlock").find("input[str='required']");
	if(inputs != undefined && inputs.length>0){
		$(inputs).each(function(index,data){
			if($(data).attr("type") == "radio"){
				var thisCheck = $(data).parents("td").find("input[checked='checked']");
				if(thisCheck.length <= 0){
					$(data).parents("td").find("font").css("color","red");
				}
			}else{
				var inputValue = $(data).val();
				if(inputValue == undefined || inputValue == ""){
					addMyValidateCss($(data));
				}
			}
		});
	}
	
	// 验证其他收入来源
	var str="";
    $("#personalConfirmBlock").find("form[id='br']").find("[name='otherIncomeResource'][checked]").each(function(){
		str += $(this).val()+"n";
		
	})
	if (str == "") {
		// 其他收入来源标红
		$("#personalConfirmBlock").find("p[name='otherIncomeResourceP']").attr("style","color:red;");
	} else {
		$("#personalConfirmBlock").find("p[name='otherIncomeResourceP']").attr("style","color:black;");
	}
	    
}

function clearAllRed(){
	//先获取所有的select
	var selects = $("#personalConfirmBlock").find("select[str='required']");
	if(selects != undefined && selects.length>0){
		$(selects).each(function(index,data){
			removeMyValidateCss($(data));
		});
	}
	// 再后去所有的input
	var inputs = $("#personalConfirmBlock").find("input[str='required']");
	if(inputs != undefined && inputs.length>0){
		$(inputs).each(function(index,data){
			if($(data).attr("type") == "radio"){
				$(data).parents("td").find("font").css("color","");
			}else{
				removeMyValidateCss($(data));
			}
		});
	}

}