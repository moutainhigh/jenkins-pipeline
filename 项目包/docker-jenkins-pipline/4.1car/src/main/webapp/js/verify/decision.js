var money = 300000; // 终审组到终审 
var fyMoney = 150000; // 复议复审到终审
var bestCoMoney = 80000; // 8w以上必须自然人保证人必填
var setRate = "";
$(function(){
	var exSubData = $(window.parent.document).find("#exSubData").val();
	if(exSubData == '1'){
		$("#dealUser").parent().html("");
	}
    var type =  $(window.parent.document).find("#param").find("input[name='checkType']").val();
    if(type == 0){//信审
    	if($("#auditAmountHide").val() <= money){
    		$("#dealUser").attr("disabled",true).removeClass("required");
    	}
    }
    if(type == 1){//复议
    	if($("#auditAmountHide").val() <= fyMoney){
    		$("#dealUser").attr("disabled",true).removeClass("required");
    	}
    }
    // 咨询时间标识，如果为true，费率采用新规则，如果为false,费率采用原有规则
    var consultFlag = $("#consultFlag").val();    
   // getStage(consultFlag); // 加载产品分期
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
	
	// 上传截图
	$("#upload").click(function(){
		var url = $("#sunyardUrl", window.parent.document).val();
		window.open(url);
	});
	
	// 清空
	$("#clearForm").click(function(){
		$(':input','#form')  
		 .not(':button, :submit, :reset, :hidden , #productType')  
		 .val('')  
		 .removeAttr('checked')  
		 .removeAttr('selected');
		$("#form select").not("#productType").val("");
		$('#form select').trigger("change");
		$("#form").find("td[str='clearThis']").html("");//
	});
	
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
	
	// 审核结果改变
	$("#selectResult").change(function(){
		$("#refuseTip").remove();
		// 用于标记是否为姒拒绝的初始化
		var mark = $("#sublistMark").val();
		if(mark == "true"){
			$("#sublistMark").remove();
			return;
		}
        var p1=$(this).val();
    	var trList = $(this).parents("table").find("tr");//找到所有子类tr
        if(Util.isEmpty(p1)){		
        	if(p1 == '0'){//通过   
        		dealUserChange();
        		hideEle(trList,"pass");        
			}else if(p1 == '1' || p1 == '2'){//拒绝
				$("#dealUser").attr("disabled",true).removeClass("required");
				if($("#dealUser").html() != undefined){
					removeMyValidateCss($("#dealUser"));
				}
				hideEle(trList,"result");
				$("#refuseCopy tr").cloneSelect2(true,true).insertAfter(trList[0]);
			}else{
				art.dialog.tips("请正确选择！！");
			}
        }else{
        	hideEle(trList,"");   
        }
    });
	
	// 一级拒借码
	$("select[str='refuseFirstCode']").change(function(){
		var refuseFirstCode = $(this).find("option:selected").attr("mark");
		var element = $(this).parent().next().children("select");
		if( refuseFirstCode == "" ){
			 element.empty();
			 element.append("<option value='' selected=true>请选择</option>");
			 element.trigger("change");
		}else{
		    $.ajax({
	    		type : "POST",
	    		url : ctx + "/common/selectInit/getOtherGlRefuses",
	    		data: "id="+refuseFirstCode,	
	    		success : function(data) {
	    			element.empty();
	    			element.append("<option value=''>请选择</option>");
	    			var message ="";
	    			var dataJson = eval("(" + data + ")");
	    			$(dataJson).each(function(index,da){
	    				message += "<option mark="+da.id+" value="+da.refuseCode+">"+da.refuseName+"</option>";
	    			});
		            element.append(message);
		            element.trigger("change");
		            element.attr("disabled", false);
	    		}
		    });
		 }
	});
	
	// 二级拒借码
	$("select[str='refuseSecondCode']").change(function() {
		var refuseSecondCode = $(this).find("option:selected").attr("mark");
		var element = $(this).parent().next().children("select");
		if( refuseSecondCode == ""){
			element.empty();
			element.append("<option value='' selected>请选择</option>");
			element.trigger("change");
		}else{
			element.empty();
		    $.ajax({
         		type : "POST",
         		url: ctx + "/common/selectInit/getOtherGlRefuses",
         		data: "id="+refuseSecondCode,
         		success : function(data) {
         			element.empty();
         			element.append("<option value=''>请选择</option>");
         			var message = "";
         			var dataJson = eval("(" + data + ")");
         			$(dataJson).each(function(index,da){
         				message += "<option mark="+da.id+" value="+da.refuseCode+">"+da.refuseName+"</option>";
         			});
                   element.append(message);
                   element.trigger("change");
                   element.attr("disabled", false);
         		}
      		});
		}
	});
	
	// 批复金额
	$("#auditAmount").blur(function(){
		var inputMenoy = $(this).val();		
		var max = null;
		var min = null;
		var prodInput = $("#productType")[0].type;
		if(prodInput=="select-one"){
			max =$("#productType").find("option:selected").attr("max");
			min =$("#productType").find("option:selected").attr("min");
		}else if(prodInput=="hidden"){
			max =$("#productType").attr("max");
			min =$("#productType").attr("min");
		}		
		var applyMax = $("#applyMax").val();
		if(inputMenoy != '' && typeof(max) != "undefined"){
			if(isNaN(inputMenoy)||inputMenoy.indexOf(".")>0){
				addMyValidateTip($(this),"请正确输入整数金额");   
				$(this).val("");
				$("#contractAmount").html("");
			}else{
				if(parseFloat(inputMenoy)>parseFloat(max)){
    				addMyValidateTip($(this),"批复金额不能超过上限");
    				$(this).val("");
    				$("#contractAmount").html("");
    			}else if(parseFloat(inputMenoy)>parseFloat(applyMax)){
    				addMyValidateTip($(this),"批复金额不能超过申请金额");
    				$(this).val("");
    				$("#contractAmount").html("");
    			}else if(parseFloat(inputMenoy)<parseFloat(min)){
    				addMyValidateTip($(this),"批复金额不能低于下限");
    				$(this).val("");
    				$("#contractAmount").html("");
    			}else{// 获取保证人的金额
    				var cautionerAmount = null;
    				if(prodInput=="select-one"){
    					cautionerAmount = $("#productType").find("option:selected").attr("cautionerAmount");
    				}else if(prodInput=="hidden"){
    					cautionerAmount = $("#productType").attr("cautionerAmount");
    				}
    				if(cautionerAmount != '' && cautionerAmount !='-1' &&(parseFloat(inputMenoy)>parseFloat(cautionerAmount))){ // 输入的金额大于需要添加保证人的额度
    					$("#tableResult").find("tr[str='bigMoney']").show();
    					$("#tableResult").find("tr[str='bigMoney']").find("select").addClass("required")
    					                                             .find("input").addClass("required");
    					
    					var data = $(window.parent.document).find("form").serialize();
    					$.ajax({
    			         		type : "POST",
    			         		url: ctx + "/verify/check/getCautioner",
    			         		data: data,
    			         		dataType:"json",
    			         		success : function(data) {
    			         			$("#companyName").html("");
    			         			$("#companyName").html("<option str='' legalMan='' value=''>请选择</option>");
    			         			if(data != null){
    			         				var message = "";
    			         				var ensureName = $("#ensureNameHide").val();
    			         				$(data).each(function(index,da){
    			         					if(ensureName == da.jyzmCompanyName){
    			         						message+="<option str='"+da.id+"' value='"+da.jyzmCompanyName+"' legalMan='"+da.jyzmLegalMan+"'>" + da.jyzmCompanyName+"</option>";
    			         					}else if(da.jyzmCompanyName != undefined){
    			         						message+="<option str='"+da.id+"' value='"+da.jyzmCompanyName+"' legalMan='"+da.jyzmLegalMan+"'>" + da.jyzmCompanyName+"</option>";
    			         					}
    			         					$("#businessCertNum").val(da.certNum);
    			         				});
    			         				$("#companyName").append(message);
    			         				$("#companyName").select2().select2("val",ensureName);
    			         				//$("#companyName").trigger("change");
    			         			}
    			         		}
    			      		});
    				}else{
    					$("#tableResult").find("tr[str='bigMoney']").hide()
    									 .find(".required").removeClass("required");
    					//清空保证人信息
    					$("#companyId").val('');
     					$("#companyName").val('');
     					$("#ensureNameHide").val('');
    					var t1 = $("#companyName");
    					t1.trigger("change");
     					$("#legalMan").val('');
     					$("#ensureProvinceId").val('');
    					var t =$("#ensureProvinceId");
    					t.trigger("change");
    					emptySelect($("#ensureCityId"));
    					emptySelect($("#ensureDistrictId"));
    					$("#registeredAddr").val('');
    					$("#businessCertNum").val('');
    				}
    			}
			}
		}
		dealUserChange();
	});
	
	// companyName  根据保证人公司 获取其他信息
	$("#companyName").change(function(){
		var companyId=$(this).children("option:selected").attr("str");
		var legalMan=$(this).children("option:selected").attr("legalMan");
		$("#companyId").val(companyId);
		$("#legalMan").val(legalMan);
		/*var chooseThis = $(this).children("option:selected").val();
		if(chooseThis != ''){
			$.ajax({
		    	type : "POST",
		    	data : "id="+value,
		    	url : ctx + "/verify/check/getCautionerInfo",
		    	success : function(data) {
			    	if (data == "null") {
			    		art.dialog.tips("没有该公司的具体信息");
			    	} else {
			    		$("#companyId").val(value);
			    		$("#legalMan").val(data.jyzmLegalMan);		    			
			    	}
		    	}
		   });
		}else{
    		$("#legalMan").val("");
		}*/
	});	 
	
	// 产品类型
	$("#productType").change(function(){
		setRate = "";
		var auditAmountHide = $("#auditAmountHide").val();
		$("#auditAmount").val(auditAmountHide);
		$("#auditAmountHide").remove();
		$("#contractAmount").html("");
		var value=$(this).val();		
		if(value == ""){
			$("#auditMonths").select2("val","");
			$("#auditMonths").attr("disabled","disabled");
			// 费率
			$("#selectedRate").val("");
//			$("#interestRate").html("");
			return;
		}
		$("#tableResult").find("tr[str='bigMoney']").hide()
								 .find(".required").removeClass("required");
		$("#auditMonths").removeAttr("disabled");
		
		//changeAuditMonths();
		getStage(consultFlag);

		// 产品选定后，如果分期为空，不更新费率；否则更新费率
		var months = $("#auditMonths").val(); 
		if(months==""){
			return;
		}	
		getRate(months);
		
	});	 
	// 产品分期改变，对应的费率跟着改变
	$("#auditMonths").change(function(){
		var value=$(this).val();
		if(value == ""){
		//	$("#interestRate").html("");
			$("#selectedRate").val("");
			return;
		}
		getRate(value);
	});
	$("#selectResult").trigger("change");
	$("#productType").trigger("change");
	if($("#ensureNameHide").val() != ""){
		$("#auditAmount").trigger("blur");
	}
	// 显示征信报告表格信息
	showCreditReportTable();
	$("table[str='creditRisk']").each(function(index,data){
		var len = $(data).find("tr").length;
		if(len<=3 && $(data).find("tr:last").find("td").html()==''){
			$(this).prev("h3").remove();
			$(this).remove();
		}
	});
	// 下载意见书
	$("#download").click(function(){
		var data = $(window.parent.document).find("form").serialize();
		window.open(ctx + '/verify/uploadProposal/list?'+data);
	});
	//
	autosize(document.querySelectorAll("textarea"));
});

// 终审审批人启用禁用控制
function dealUserChange(){
    var type =  $(window.parent.document).find("#param").find("input[name='checkType']").val();
    if(type == 0){
    	if($("#auditAmount").val() > money || $("#auditAmountHide").val() > money){
    		$("#dealUser").attr("disabled",false).addClass("required");
    	}else{
    		$("#dealUser").attr("disabled",true).removeClass("required");
    		if($("#dealUser").html() != undefined){
    			removeMyValidateCss($("#dealUser"));
    		}
    	}
    }
    if(type == 1){
    	if($("#auditAmount").val() > fyMoney || $("#auditAmountHide").val() > fyMoney){
    		$("#dealUser").attr("disabled",false).addClass("required");
    	}else{
    		$("#dealUser").attr("disabled",true).removeClass("required");
    		if($("#dealUser").html() != undefined){
    			removeMyValidateCss($("#dealUser"));
    		}
    	}
    }
}
function hideEle(lis,atr){
	for(var i=0;i<lis.length;i++){ // 遍历所有的tr 
		if($(lis[i]).attr("str") != "always"){ // 如果不是当前要显示的元素
			if($(lis[i]).attr("str") == "result"){ // 如果是拒绝码则移除
				$(lis[i]).remove();
			}else{ // 如果是其他的信息 则隐藏
				$(lis[i]).hide()
				        .find(".required").removeClass("required");
			}
			if($(lis[i]).attr("str") == atr){ // 如果是当前要显示的元素 则显示
				$(lis[i]).show();
				var remark = $(lis[i]).find("select");
				if(remark.length!=0){
					remark.addClass("required")
				}else{
					$(lis[i]).find("input[type='text']").addClass("required");
				}
				$(lis[i]).find("select[id='bestCoborrowerId']").removeClass("required");
			}
		}			
	}
}

//添加一行拒绝码
function addRow(obj){
	var target = $(obj).parents("tr");
	var trClone = $("#refuseCopy tr").cloneSelect2(true,true);
	trClone.find("img").attr("src",ctxStatic+"/images/jian.png");
	trClone.find("img").attr("onclick","deleteRow(this)");
	trClone.insertAfter($(target));
}
function deleteRow(obj){
	$(obj).parents("tr").remove();
}
// 生成参数
function makeData(){
	var form = $("#form");
	var refuseFirstCode = form.find("select[str='refuseFirstCode']");
	var refuseSecondCode = form.find("select[str='refuseSecondCode']");
	var refuseThirdCode = form.find("select[str='refuseThirdCode']");
	var param = "";
	for(var i = 0; i < refuseFirstCode.length; i++){
		param += '&auditResultEx.subResult['+i+'].refuseFirstCode=' + $(refuseFirstCode[i]).val();
		param += '&auditResultEx.subResult['+i+'].refuseSecondCode=' + $(refuseSecondCode[i]).val();
		param += '&auditResultEx.subResult['+i+'].refuseThirdCode=' + $(refuseThirdCode[i]).val();
	}
	return param;
}

// 验证拒借码是否重复
function valiRepeat(){
	var refuses = $("#form").find("tr[str='result']");
	if(refuses.length > 1){
		for(var i = 0; i < refuses.length-1; i++){
			for(var j = i+1; j < refuses.length; j++){
				var before = $(refuses[i]).find("select");
				var after = $(refuses[j]).find("select");
				if($(before[0]).val() == $(after[0]).val()){
					if($(before[1]).val() == $(after[1]).val()){
						if($(before[2]).val() == $(after[2]).val()){
							var tip = '<font color="red" id="refuseTip">拒借码重复！</font>';
							if($("#refuseTip").html() == undefined){
								$("#selectResult").parent().append(tip);
							}
							return true;
						}
					}
				}
			}
		}
	}
	$("#refuseTip").remove();
	return false;
}

// 提交表单
function submitForm(url){
	if(valiRepeat()){
		return;
	}
	if(!checkForm($("#form"))){
		return;
	}
	if(checkBlur()){
		return;
	}
	var result = $("#selectResult").val();
	// 所有必填项已经填写完整
	var product = $("#productType").find("option:selected").val();
	if(result == '0' && (product == "A006" ||  product=="A005")){ // 如果产品是老板借,小微企业借 则  判断是否发起外访
		var data = $(window.parent.document).find("#param").serialize();
		$.ajax({
			type : "POST",
			data : data,
			url : ctx+"/verify/innerRepeat/getOutVisit",
			success : function(data) {
				if(data == 'false'){
					window.parent.visitInfo();
				}else{
					submitThis(url);
				}
			}
		}); 
	}else{
		submitThis(url);
	}

}
/**
 * 提交
 */
function submitThis(url){
	// 审批结果
	var result = $("#selectResult").val();
	if(Util.isEmpty(result)){
			var message = "";
			var data = $(window.parent.document).find("form[id!=toCoborrower]").serialize();
			// 通过则序列化参数，拒借则拼接参数
			if(result == '0'){
				var prodInput = $("#productType")[0].type;
				var borrowProduct = null;
				if(prodInput=="select-one"){
					borrowProduct = $.trim($("#productType").find("option:selected").html());
				}else if(prodInput=="hidden"){
					borrowProduct = $("#productType").attr("borrowProduct");
				}				
				message += $("#form").serialize()+"&"+data+"&auditResultEx.borrowProduct="+encodeURI(borrowProduct);
			}else{
				// 拼接拒借码参数
				var param = makeData();
				message += data + "&" + encodeURI(param) + "&auditResultEx.auditResult="+result;
				// 审批意见
				var remark = $("#remark").val();
				message += encodeURI("&auditResultEx.auditCheckExamine="+remark);
			}
			var code = $("#form").find("input[str='auditRulesCode']");
			var auditRulesCode = "";
			$(code).each(function(index,data){
				var val = $(data).val();
				if(index != (code.length-1)){
					if(val != ""){
						auditRulesCode +=val+",";
					}
				}else if(index == (code.length-1)){
					if(val != ""){
						auditRulesCode +=val;
					}
				}
			});
			if(auditRulesCode.lastIndexOf(",") == auditRulesCode.length-1){
				auditRulesCode = auditRulesCode.substring(0,auditRulesCode.length-1);
			}
			message += encodeURI("&auditRulesCode="+auditRulesCode);
			
    		$.ajax({
    			type : "POST",
    			data : message,
    			url : url,
    			success : function(data) {
    				if (data == "false") {
    					art.dialog.tips("保存失败！");
    				}     				
    				if(data == "refuse"){
    					alert("由于客户评分不足，此单已拒借！");
    					var win = top.window; 
    					if (win.opener) win.opener.location.reload();
    					window.parent.closeCorborrowerWin();
    					window.parent.close();
    				}    				
    				if(data == "true" || data == "1" || data == "0" || data == "3" || data == "5" || data == "6"){
						art.dialog.tips("保存成功");
    					var win = top.window; 
    					if (win.opener) win.opener.location.reload();
    					window.parent.closeCorborrowerWin();
    					window.parent.close();
    					//调用新规则引擎方法
    					//crifApplyEngine(message);
    				}
    				if(data == "2"){
    					alert("数量剩余不足");
    					var win = top.window; 
    					if (win.opener) win.opener.location.reload();
    					window.parent.closeCorborrowerWin();
    					window.parent.close();
    				}
    				if(data == "4"){
    					alert("TG剩余额度不足");
    					var win = top.window; 
    					if (win.opener) win.opener.location.reload();
    					window.parent.closeCorborrowerWin();
    					window.parent.close();
    				}
    			}
    		}); 
	}else{
		art.dialog.tips("请正确选择您的决策！！");
	}
}

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
function showInput(obj){
	var trInfo = $(obj).parents("tr");
	var trClone = $("#RulesCode").clone(true,true);
	trClone.show();
	trClone.find("input").val("");								
	trClone.insertAfter(trInfo);
}

function auditRulesCode(rulesCode){ // 规则测试码初始化显示
	var codes = rulesCode.split(","); // 规则拆分
	$(codes).each(function(index,data){
		if(index == 0){
			$("#insertCode").find("input").val(data);
		}else{
			var trT = $("#insertCode").clone(true,true);
			trT.removeAttr("id");
			$(trT).find("input").val(data);
			trT.insertAfter($("#insertCode"));
		}
		
	});
}
function checkBlur(){ // 判定规则测试码是否重复
	var codes = $("#form").find("input[str='auditRulesCode']");
	for(var i=0;i<codes.length;i++){
		for(var j=i+1;j<codes.length;j++){
			if($(codes[i]).val() != '' && $(codes[i]).val() == $(codes[j]).val()){
				addMyValidateCss($(codes[i]));
				addMyValidateCss($(codes[j]));
				return true;
			}
		}
	}
}	
function getMoney(){
	var money = $("#auditAmount").val();
//	var rate = $("#interestRate").html();
	var rate = $("#selectedRate").val();
	var months = $("#auditMonths").find("option:selected").val();
	var customerAge=$("#customerAge").val();
	if(money != '' && rate != '' && months != '' && months!= '请选择'){
		money = parseInt(money);
		rate = parseFloat(rate);
		months = parseInt(months);
		var mo = money/months+money*rate/100;
		mo = ((mo+1).toFixed(2)-1).toFixed(2);
		$("#auditContractAmount").val(mo);
		$("#contractAmount").html(mo);
		if (money>bestCoMoney || customerAge>=50) {
			 $("#bestCoborrowerId").addClass("required");
		}else{
			 $("#bestCoborrowerId").removeClass("required");			
		}
	}
}


/**
 * 页面加载完成后，加载所有的分期信息
 * @consultFlag 如果为true,取新规则下的分期，如果为false,取原来的分期
 */
function getStage(consultFlag){
	var months = $("#auditMonthsHide").val();
	if(consultFlag==null || consultFlag==undefined){
		consultFlag="";
	}
	var productType = $("#productType").val();
	$.ajax({
		url:ctx+'/verify/check/findAllMonths',
		type: "post",
		dataType:'json',
		data : "consultFlag="+consultFlag,
		success:function(data){
			if(data != null){
				$("#auditMonths").empty();
				var message = "<option selected='selected' value=''>请选择</option>";
				$(data).each(function(index,str){  
					var select = "";
					
					if(parseInt(months) == parseInt(str)){
						select = "selected";
					}
					if(productType == 'A004' || productType == 'A005' ||productType == 'A006'){
						if(parseInt(str)!=48){
							message += "<option "+select+" value='"+str+"'>"+str+"</option>";
						}
					}else{
						message += "<option "+select+" value='"+str+"'>"+str+"</option>";
					}
				});
				$("#auditMonths").append(message);
			}
			$('#auditMonths').trigger("change");
		}
		
	});		
}

/**
 * 获取产品费率
 * @param value
 */
function getRate(value){
	// consultFlag 如果为true,根据新规则,判断风险等级取费率；如果为false,取原来的费率
	var consultFlag = $("#consultFlag").val();	
	if(consultFlag==null || consultFlag==undefined){
		consultFlag="";
	}
	if(consultFlag=="true"){
		setRate = ""; 
		var riskLevel = $("#riskLevel").html();
		$.ajax({
	    	type : "POST",
	    	dataType : 'json',
	    	data : "months="+value+"&riskLevel="+riskLevel,
	    	url : ctx + "/verify/check/getRateByRisk",
	    	success : function(data) {
		    	if (data != null) {
		    	//	$("#interestRate").html(data);
		    		$("#selectedRate").val(data);
		    		getMoney();
		    	}
	    	}
	   });
	} else {
		if(parseInt(value)<=12){ // 分期在12期以下，则去费率表取
			// 解决从15期以上，变为小于12期，再变为15期以上时，setRate已经有值不再更新费率的bug
			setRate = ""; 
			$.ajax({
		    	type : "POST",
		    	dataType : 'json',
		    	data : "months="+value,
		    	url : ctx + "/verify/check/getRate",
		    	success : function(data) {
			    	if (data != null) {
			    	//	$("#interestRate").html(data);
			    		$("#selectedRate").val(data);
			    		getMoney();
			    	}
		    	}
		   });
		}else{ // 15期以上，则根据产品的费率走
				var code = $("#productType").find("option:selected").val();
				if(code == undefined ){
					code = $("#productType").val();
				}			
				$.ajax({
			    	type : "POST",
			    	dataType : 'json',
			    	data : "productCode="+code,
			    	url : ctx + "/verify/check/getRateByCode",
			    	success : function(data) {
			    		setRate = data; 
				    	if (data != null && data != "false") {				    		
				    	//	$("#interestRate").html(data);
				    		$("#selectedRate").val(data);
				    		getMoney();
				    	}else{
				    		alert("系统异常。。。。。。。。");
				    	}
			    	}
			   });
		}
	}
	
}




/**
 * 省市联动 
 * @param pid 省id
 * @param cid 市id
 */
function provinceId(tt, pid, cid){
	var t =$(tt);
	var provinceId =t.val(); 
	if (provinceId=="" &&  pid!="" && pid!=null) {
		provinceId = pid;
		t.find("option[value="+pid+"]").attr("selected",true);
		t.trigger("change");
	}
	var cityId = $("#ensureCityId");
	var districtId = $("#ensureDistrictId");
	if(provinceId==""){
		emptySelect(cityId);
		emptySelect(districtId);
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
                	cityId.append("<option mark="+i+" value="+item.code+""+((cid!=""&&cid==item.code)?" selected":"")+">"+item.name+"</option>");                	
//                	cityId.append("<option value="+item.code+">"+item.name+"</option>");
                });
                cityId.trigger("change");
                cityId.attr("disabled", false);
      		}
      	});
	 }
	
}

/**
 * 市区联动 
 * @param cid 市id
 * @param aid 区id
 */
function cityId(tt, cid, aid){
	var t = $(tt);
	var districtId = $("#ensureDistrictId");
	 var cityId = t.val();
	 if(cityId=="" && cid!="" && cid!=null){
		 cityId = cid;
		 t.trigger("change"); 
	 }	 
	 if(cityId==""){		 
		 emptySelect(districtId);
	 }else{
		 districtId.empty();
	     $.ajax({
      		type : "POST", 
      		url: ctx + "/common/selectInit/asynLoadDistrict",
      		data: {cityId: cityId},	
      		success : function(data) {
      			var resultObj = eval("("+data+")");
      			districtId.empty();
      			districtId.append("<option value=''>请选择</option>");
                $.each(resultObj,function(i,item){
                	districtId.append("<option mark="+i+" value="+item.code+""+((aid!=""&&aid==item.code)?" selected":"")+">"+item.name+"</option>");
                });
                districtId.trigger("change");          
                districtId.attr("disabled", false);
      		}
        });
	}
}

function emptySelect(obj) { 
	$(obj).empty();
	$(obj).append("<option value='' selected>请选择</option>");
	$(obj).trigger("change");
}

function crifApplyEngine(message){
	var url = ctx + "/rules/apply/crifApplyEngine";
	$.ajax({
		type : "POST",
		data : message,
		url : url,
		success : function(data) {
			if (data == "false") {
				art.dialog.tips("保存失败！");
			}     
		}
	}); 
}
