$(function () {
	// 退回门店
	$('#backCancel').click(function() {
		$('#modal_Back').modal('hide');
    	$("#modal_Back .modal-body").empty();
	});
	$('#backSave').click(function() {
		var first = $("#backform").find("input[mark='first']:checked");
		var dataParam = $(window.parent.document).find("form[id='param']").serialize();
		var workItemParam = $(parent.document).find("#workParam").serialize();
		var checkBoxs = $("#backform").find("input[type='checkbox']:checked");
		var auditCheckExamine = $("#auditCheckExamineBackId").val();
		if (checkBoxs.length < 1) {
			alert("请选择回退清单!");
			return;
		} else if (auditCheckExamine == "") {
			alert("请选择退回原因!");
			return;
		}
		var params = "&json=";
		for (var i = 0; i < checkBoxs.length; i++) {
			if (i == 0) {
				params += $(checkBoxs[i]).val();
			} else {
				params += "," + $(checkBoxs[i]).val();
			}
		}
		var firstParam = "&firstJson=";
		for (var i = 0; i < first.length; i++) {
			if (i == 0) {
				firstParam += $(first[i]).val();
			} else {
				firstParam += "," + $(first[i]).val();
			}
		}
		var checkType = $(window.parent.document).find("#stepName").val();
		$.ajax({
			type : "POST",
			data : dataParam + "&" + workItemParam + params + firstParam + "&auditCheckExamine=" + encodeURI(auditCheckExamine),
			url : ctx + "/carloan/carLoanCheckInfo/" + ((checkType == '10'  || checkType == '11' ) ? "asynCarLoanReCheckHandle" : "asynCarLoanFinalCheckHandle"),
			success : function (data) {
				if (data == "true") {
					$('#modal_Back').modal('hide');
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
});