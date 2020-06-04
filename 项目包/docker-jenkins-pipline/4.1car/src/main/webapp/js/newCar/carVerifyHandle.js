var param = "";
var auditAmount = "";
var evaluate = "";
$(function() {
	var text = $("#textareaSeleteiId").val();
	if(text!=undefined){
		var counter = text.length;
		$(".textareP").find("var").text(200-counter);
		$("#textareaSeleteiId").keyup(function() {
			var text = $("#textareaSeleteiId").val();
			var counter = text.length;
			$(".textareP").find("var").text(200-counter);
		});
	}
	
	var text2 = $("#auditCheckExamineGiveup").val();
	if(text2!=undefined){
		var counter2 = text2.length;
		$("#abandonFormId .textareP").find("var").text(500-counter2);
		$("#auditCheckExamineGiveup").keyup(function() {
			var text = $("#auditCheckExamineGiveup").val();
			var counter = text.length;
			$("#abandonFormId .textareP").find("var").text(500-counter);
		});
	}
	
	var text3 = $("#auditCheckExamineRefuse").val();
	if(text3!=undefined){
		var counter3 = text3.length;
		$("#refusedFormId .textareP").find("var").text(500-counter3);
		$("#auditCheckExamineRefuse").keyup(function() {
			var text = $("#auditCheckExamineRefuse").val();
			var counter = text.length;
			$("#refusedFormId .textareP").find("var").text(500-counter);
		});
	}
	
	var text4 = $("#creditStatusId").val();
	if(text4!=undefined){
		var counter4 = text4.length;
		$(".varTdClassId .textareP").find("var").text(200-counter4);
		$("#creditStatusId").keyup(function() {
			var text = $("#creditStatusId").val();
			var counter = text.length;
			$(".varTdClassId .textareP").find("var").text(200-counter);
		});
	}
	param = $('#param').serialize();
	$("#saveCarLoanRecheckInfo").click(function() {
		if (!checkForm($(this).parents("form:first"))) {
			return false;
		}
		$("#productTypeID").removeAttr("disabled");
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		var workItemParam = $(parent.document).find("#workParam").serialize();
		var checkType = $(window.parent.document).find("#stepName").val();
		$.ajax({
			type: "POST",
			data: $(this).parents("form:first").serialize() + "&" + dataParam + "&" + workItemParam,
			url: ctx + "/newcar/newCarLoanCheck/" + ((checkType.indexOf("复审") >= 0) ? "asynCarLoanReCheckHandle" : "asynCarLoanFinalCheckHandle"),
			success: function(data){
				if (data == "true") {
					var win = top.window;
					if (win.opener) {
						win.opener.location.reload();
					}
					win.close();
				} else {
					alert("操作失败！请重新操作");
				}
			},
			error: function(data){
				
			}
		});
	});
	//客户放弃取消事件
	$('#abandonCancel').click(function() {
		$('#modal_Abandon').modal('hide');
	});
	//客户放弃保存事件
	$('#abandonSave').click(function() {
		/*var first = $("#abandonFirstCode").children("option:selected").val();
		var second = $("#abandonSecondCode").children("option:selected").val();*/
		if (!checkForm($("#abandonFormId"))) {
			return;
		}
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		var workItemParam = $(parent.document).find("#workParam").serialize();
		var checkType = $(window.parent.document).find("#stepName").val();
		$.ajax({
			type : "POST",
			data: $(parent.document).find("#abandonFormId").serialize() + "&" + dataParam + "&" + workItemParam,
			url: ctx + "/newcar/newCarLoanCheck/" + ((checkType.indexOf("复审") >= 0) ? "asynCarLoanReCheckHandle" : "asynCarLoanFinalCheckHandle"),
			success : function(data) {
				if (data == "true") {
					$('#modal_Abandon').modal('hide');
					var win = top.window;
					if (win.opener) {
						win.opener.location.reload();
					}
					win.close();
				} else {
					alert("操作失败！请重新操作");
				}
			}
		});
	});
	//拒绝取消事件
	$('#refusedCancel').click(function() {
		$('#modal_Refused').modal('hide');
	});
	//拒绝保存事件
	$('#refusedSave').click(function() {
		if (!checkForm($("#refusedFormId"))) {
			return false;
		}
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		var workItemParam = $(parent.document).find("#workParam").serialize();
		var checkType = $(window.parent.document).find("#stepName").val();
		$.ajax({
			type : "POST",
			data: $(parent.document).find("#refusedFormId").serialize() + "&" + dataParam + "&" + workItemParam,
			url: ctx + "/newcar/newCarLoanCheck/" + ((checkType.indexOf("复审") >= 0) ? "asynCarLoanReCheckHandle" : "asynCarLoanFinalCheckHandle"),
			success : function(data) {
				if (data == "true") {
					$('#modal_Refused').modal('hide');
					var win = top.window;
					if (win.opener) {
						win.opener.location.reload();
					}
					win.close();
				} else {
					alert("操作失败！请重新操作");
				}
			}
		});
	});
	function commonValidate(self) {
		var flag = false;
		if (self == true) {
			flag = true;
		} else {
			flag = checkNumber(self);
		}
		if (flag && evaluate != '' && !isNaN(evaluate)) {
			if (parseFloat(auditAmount) > parseFloat(evaluate)) {
				alert("审批金额不能大于评估金额！");
				$("#auditAmount").val(null);
				$("#loanMoneyPercent").val(null);
			} else {
				$("#loanMoneyPercent").val(parseFloat(auditAmount / evaluate * 100).toFixed(2));
			}
		}
	}
	// 复审审批金额
	$("#auditAmountRecheck").blur(function() {
		auditAmount = $(this).val();
		if (auditAmount != '') {
			if (isNaN(auditAmount)) {
				addMyValidateTip($(this), "请输入数字");
				$(this).val(null);
			} else {
				checkNumber(this);
			}
		}
	});
	// 终审审批金额
	$("#auditAmount").blur(function() {
		auditAmount = $(this).val();
		if (auditAmount != '') {
			if (isNaN(auditAmount)) {
				addMyValidateTip($(this), "请输入数字");
				$(this).val("");
			} else {
				commonValidate(this);
			}
		}
	});
	$("#finalEvaluatedPrice").blur(function() {
		evaluate = $(this).val();
		if (evaluate != '') {
			if (isNaN(evaluate)) {
				addMyValidateTip($(this), "请输入数字");
				$(this).val("");
			} else {
				commonValidate(true);
			}
		}
	});
	$("#auditResultSelectId").change(function() {
		var val = $(this).val();
		if (val == '2') {
			$("#spanRedId").css("display", "inline");
			$("#textareaSeleteiId").addClass("required");
		} else {
			$("#spanRedId").css("display", "none");
			$("#textareaSeleteiId").removeClass("required");
		}
	});
});
/**
 * 回退按钮点击事件
 */
function showBack() {
	$.ajax({
		type : "GET",
		url : ctx + "/carloan/carLoanCheck/backCheck?" + param,
		dataType : "html",
		timeout : 10000,
		beforeSend : function(XMLHttpRequest) {
			waitingDialog.show();
		},
		complete : function(XMLHttpRequest, textStatus) {
			waitingDialog.hide();
		},
		success : function(data) {
			$("#modal_Back .modal-body").empty();
			$("#modal_Back .modal-body").append(data);
			$('#modal_Back').modal('show');
		}
	});
};
/**
 * 查看
 */
function showApplyInfo() {
	art.dialog.open(ctx + "/carloan/carLoanCheck/applyDetail?" + param, {
		id : 'detailDialogId',
		title : "查看",
		width : "750px",
		height : "550px",
		left : 850,
		top : 50,
		window : "top",
		lock : false
	});
};
/**
 * 历史
 */
function showHistory() {
	art.dialog.open(ctx + "/carloan/carLoanCheck/checkHistory?" + param, {
		id : 'historyDialogId',
		title : "历史",
		width : "650px",
		height : "450px",
		left : 850,
		top : 50,
		window : "top",
		lock : false
	});
};
/**
 * 客户放弃弹出框
 */
function showAbandon(){
	$('#modal_Abandon').modal('show');
};
/**
 * 拒绝弹出框
 */
function auditRefused(){
	$('#modal_Refused').modal('show');
};
/**
 * 延时处理
 */
function delay() {
	var dataParam = $(window.parent.document).find("form[id='param']").serialize();
	var workItemParam = $(parent.document).find("#workParam").serialize();
	$.ajax({
		type : "POST",
		data : $(parent.document).find("#workParam").serialize() + "&" + dataParam + "&auditResult=7",
		url : ctx + "/carloan/carLoanCheck/asynDelay",
		beforeSend : function(data, url) {
			console.log(url);
			console.log(data);
		},
		success : function(data) {
			if (data == "true") {
				var win = top.window;
				if (win.opener) {
					win.opener.location.reload();
				}
				win.close();
			} else {
				alert("延时处理失败!");
			}
		}
	});
};
// 校验是否为0~100之间的数字
function checkBlur(self){
	var value = $(self).val();
	if (value != '' && !isNaN(value)) {
		var regex = /^(100|([1-9]?\d)(\.\d+)?)$/;
		if (!regex.test(value)) {
			addMyValidateTip($(self), "请输入0-100之间的数字");
			$(self).val(null);
		}
	}
};
// 校验审批金额
function checkNumber(self) {
	var value = $(self).val();
	if (value != '' && !isNaN(value)) {
		var regex = /^\d+(\.\d{0,2})?$/;
		if (!regex.test(value)) {
			addMyValidateTip($(self), "请输入小数位最长为2位的数字！");
			$(self).val(parseFloat(value).toFixed(2));
			return false;
		} else {
			var lastAuditAmount = parseFloat($("#auditAmountId").val()); // 汇诚终审审批金额
			var isextension = $("#isextensionId").val(); // 是否展期
			if (isNaN(lastAuditAmount)) {lastAuditAmount = 0;}
			
			auditAmount = parseFloat(value);
			
			if (isextension == '1' && lastAuditAmount < auditAmount) {
				alert("批复金额不能大于前一次终审审批金额：" + lastAuditAmount + "元");
				$(self).val(null);
				return false;
			} else {
				var suggestAmount = parseFloat($("#suggestLoanAmountId").val()); // 门店，建议借款金额
				if (isNaN(suggestAmount)) {suggestAmount = 0;}
				
				if (suggestAmount < auditAmount) {
					alert("批复金额不能大于建议借款金额：" + suggestAmount + "元");
					$(self).val(null);
					return false;
				} else {
					return true;
				}
			}
		}
	} else {
		return true;
	}
};