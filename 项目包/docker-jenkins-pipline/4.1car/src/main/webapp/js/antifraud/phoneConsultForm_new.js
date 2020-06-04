$(function(){
    autosize(document.querySelectorAll("textarea"));
	/**
	 * 删除单位电话
	 */
    $("a[mark='del_tel']").click(function(){
    	var id = $(this).parents("table").find("input[name='id']").val();
    	var form=$(this).parents("table").parent();
    	if(id != ""){
	    	$.ajax({
	    		type: "POST",
	    		data:{id:id},
	    		url : ctx + "/antifraud/phoneConsult/delWorkTel",
	    		success : function(data) {
	    			if(data == "true"){
	    				form.remove();
	    			}else if(data == "false"){
	    				art.dialog.alert("删除失败，请重新操作");
	    			}else if($(data).find("input").length > 0){
		        		window.location = "${ctx}";
	    			}
	    		}
	    	}); 
    	}else{
    		form.remove();
    	}
    });
    /**
     * 删除家庭联系人
     */
    $("a[mark='del_Family']").click(function(){
    	var id = $(this).parents("table").find("input[name='id']").val();
    	var form=$(this).parents("table").parent();
    	if(id != ""){
	    	$.ajax({
	    		type: "POST",
	    		data:{id:id},
	    		url : ctx + "/antifraud/phoneConsult/delProves",
	    		success : function(data) {
	    			if(data == "true"){
	    				form.remove();
	    			}else if(data == "false"){
	    				art.dialog.alert("删除失败，请重新操作");
	    			}else if($(data).find("input").length > 0){
		        		window.location = "${ctx}";
	    			}
	    		}
	    	});  
		}else{
			form.remove();
		}	
    });
    /**
     * 删除本人核实
     */
    $("a[mark='del_Confirm']").click(function(){
    	var id = $(this).parents("table").find("input[name='id']").val();
    	var form=$(this).parents("table").parent();
    	if(id != ""){
	    	$.ajax({
	    		type: "POST",
	    		data:{id:id},
	    		url : ctx + "/antifraud/phoneConsult/delConfirm",
	    		success : function(data) {
	    			if(data == "true"){
	    				form.remove();
	    			}else if(data == "false"){
	    				art.dialog.alert("删除失败，请重新操作");
	    			}else if($(data).find("input").length > 0){
		        		
		        		window.location = "${ctx}";
	    			}
	    		}
	    	});   
		}else{
			form.remove();
		}	
    });
    
    /**
     * 添加工作证明人
     */
    $("#workProvesAdd").click(function(){
    	$("#workProvesCopy form").cloneSelect2(true,true).appendTo($("#workProvesBody"));
    });
    /**
     * 删除工作证明人
     */
    $("a[mark='del_WorkProves']").click(function(){
    	var id = $(this).parents("table").find("input[name='id']").val();
    	var form=$(this).parents("table").parent();
    	if(id != ""){
	    	$.ajax({
	    		type: "POST",
	    		data:{id:id},
	    		url : ctx + "/antifraud/phoneConsult/delProves",
	    		success : function(data) {
	    			if(data == "true"){
	    				form.remove();
	    			}else if(data == "false"){
	    				art.dialog.alert("删除失败，请重新操作");
	    			}else if($(data).find("input").length > 0){
		        		
		        		window.location = "${ctx}";
	    			}
	    		}
	    	});    
		}else{
			form.remove();
		}	
    });
    
    /**
     * 添加家庭联系人
     */
    $("#familyAdd").click(function(){
    	$("#familyCopy form").cloneSelect2(true,true).appendTo($("#familyBody"));
    });
    
    /**
     * 添加本人核实
     */
    $("#myConfirmAdd").click(function(){
    	$("#myConfirmCopy form").cloneSelect2(true,true).appendTo($("#myConfirmBody"));	
	});
    /**
     * 添加 其他联系人
     */
    $("#otherAdd").click(function(){
    	$("#otherCopy form").cloneSelect2(true,true).appendTo($("#otherBody"));
    });
    /**
     * 删除其他联系人
     */
    $("a[mark='del_other']").click(function(){
    	var id = $(this).parents("table").find("input[name='id']").val();
    	var form=$(this).parents("table").parent();
    	if(id != ""){
        	$.ajax({
        		type: "POST",
        		data:{id:id},
        		url : ctx + "/antifraud/phoneConsult/delProves",
        		success : function(data) {
        			if(data == "true"){
        				form.remove();
        			}else if(data == "false"){
        				art.dialog.alert("删除失败，请重新操作");
        			}else if($(data).find("input").length > 0){
    	        		window.location = "${ctx}";
        			}
        		}
        	}); 
    	}else{
    		form.remove();
    	}
    });
    /**
     * 本人核实 调用打电话接口
     */
    $("a[mark='myConfirmCall']").click(function(){
    	var from = $(this).parents("table").parent();
		if(!checkForm(from)){
			return ;
		}
    	var loanCode = $(window.parent.document).find("#loanCode").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&loanCode="+loanCode,
    			url : ctx + "/antifraud/phoneConsult/addConfirm",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	var tel = $(this).parent().find("input[name='brhsPhone']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_Confirm']").remove();
    				form.find("input[name='brhsPhone']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    /**
     * 本人核实 调用打宅电接口
     */
    $("a[mark='myConfirmHomeCall']").click(function(){
    	var from = $(this).parents("table").parent();
		if(!checkForm(from)){
			return ;
		}
    	var loanCode = $(window.parent.document).find("#loanCode").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&loanCode="+loanCode,
    			url : ctx + "/antifraud/phoneConsult/addConfirm",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	var tel = $(this).parent().find("input[name='homeTel']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_Confirm']").remove();
    				form.find("input[name='homeTel']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    
    /**
     * 家庭 证明人电话 调用打电话接口
     */
    $("a[mark='familyCall']").click(function(){
    	var from = $(this).parents("table").parent();
		if(!checkForm(from)){
			return ;
		}
    	var loanCode = $(window.parent.document).find("#loanCode").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&loanCode="+loanCode,
    			url : ctx + "/antifraud/phoneConsult/addfamily",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	var tel = $(this).parent().find("input[name='dhgxshTel']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_Family']").remove();
    				form.find("input[name='dhgxshTel']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    
    /**
     * 新版申请表 宅电
     * 家庭 证明人电话 调用打电话接口
     */
    $("a[mark='familyHomeCall']").click(function(){
    	var from = $(this).parents("table").parent();
		if(!checkForm(from)){
			return ;
		}
    	var loanCode = $(window.parent.document).find("#loanCode").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&loanCode="+loanCode,
    			url : ctx + "/antifraud/phoneConsult/addfamily",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	// 宅电
    	var tel = $(this).parent().find("input[name='homeTel']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_Family']").remove();
    				form.find("input[name='homeTel']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    
    /**
     * 工作证明人电话 调用打电话接口
     */
    $("a[mark='workProvesCall']").click(function(){
    	var from = $(this).parents("table").parent();
		if(!checkForm(from)){
			return ;
		}
    	var loanCode = $(window.parent.document).find("#loanCode").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&loanCode="+loanCode,
    			url : ctx + "/antifraud/phoneConsult/addContacts",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	var tel = $(this).parent().find("input[name='dhgxshTel']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_WorkProves']").remove();
    				form.find("input[name='dhgxshTel']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    
  
    
    /**
     * 其他联系人调用打电话接口
     */
    $("a[mark='othersCall']").click(function(){
    	var from = $(this).parents("table").parent();
		if(!checkForm(from)){
			return ;
		}
    	var loanCode = $(window.parent.document).find("#loanCode").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&loanCode="+loanCode,
    			url : ctx + "/antifraud/phoneConsult/addOhters",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	var tel = $(this).parent().find("input[name='dhgxshTel']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_other']").remove();
    				form.find("input[name='dhgxshTel']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    
    /**
     * 其他联系人调用打电话接口
     */
    $("a[mark='othersHomeCall']").click(function(){
    	var from = $(this).parents("table").parent();
		if(!checkForm(from)){
			return ;
		}
    	var loanCode = $(window.parent.document).find("#loanCode").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&loanCode="+loanCode,
    			url : ctx + "/antifraud/phoneConsult/addOhters",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	var tel = $(this).parent().find("input[name='homeTel']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_other']").remove();
    				form.find("input[name='homeTel']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    
    /**
     * 单位电话 调用打电话接口
     */
    $("a[mark='call']").click(function(){
    	var from = $(this).parents("table").parent();
/*		if(!checkForm(from)){
			return ;
		}*/
    	var workId = $(this).parents("div.workBody").find("input[name='workId']").val();
    	var thisId=$(this).parents("table").find("input[name='id']").val();
    	// 判断单位电话是否保存
    	if(thisId == null || thisId == ""){
    		$.ajax({
    			type : "POST",
    			data :from.serialize()+"&workId="+workId,
    			url : ctx + "/antifraud/phoneConsult/addWorkTel",
    			async:false,
    			success : function(id) {
    				if(id != "false"){
    					from.find("input[name='id']").val(id);
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    					return;
    				}
    			}
    		});
    	}
    	// 获取关系ID
    	var lxId = $(this).parents("table").find("input[name='id']").val();
    	var form = $(this).parents("form");
    	var tel = $(this).parent().find("input[name='workUnitTel']").val();
    	var longDistancePhone = "0"
    	if($(this).parent().find("input[name='longDistancePhone']").is(':checked')) {
    		var longDistancePhone = $(this).parent().find("input[name='longDistancePhone']:checked").size();
    	}
    	var nowTime= new Date().Format("yyyy-MM-dd hh:mm:ss");
    	// ajax保存录音信息
    	$.ajax({
    		type : "POST",
    		data : {rGxId: lxId,dhlyxxCallTime:nowTime,dhlyxxFilePath: "src",dhlyxxTel:tel,longDistancePhone:longDistancePhone},
    		url : ctx + "/antifraud/phoneConsult/saveRecord",
            async: true,
            beforeSend: function(XMLHttpRequest){
            	waitingDialog.show();
            },
            complete: function(XMLHttpRequest, textStatus){
            	waitingDialog.hide();
            },
    		success : function(data) {
    			if(data == "9999"){
    				art.dialog.alert("无法接通");
    			}else if(data == "ADAPTER_SERVICE_DOWN"){
    				art.dialog.alert("接口异常)");
    			}else if(data == "THIRD_PARTY_SERVICE_DOWN"){
    				art.dialog.alert("服务异常");
    			}else if(data == "false"){
    				art.dialog.alert("插入电话录音失败");
    			}else{
    				form.find("a[mark='del_tel']").remove();
    				form.find("input[name='workUnitTel']").attr("readonly","readonly");
    				
    				var table = $("#dhly table").cloneSelect2(true,true);
    				autosize(table.find("textarea"));
    				table.find("input[mark='lyId']").val(data);
    				table.find("span[mark='callTime']").text(nowTime);
    				table.addClass("telRecord").appendTo(form);
    			}
    		}
    	});
    });
    
    /**
     * 增加单位电话
     */
    $("a[mark='workTelAdd']").click(function(){
    	$("#workTelCopy form").clone(true).addClass("workForm").appendTo($("#workBody"));
    });

    /**
     * 删除录音信息
     */
    $("a[mark='delRecord']").click(function(){
    		var table = $(this).parents("table");
    		var lyId = $(this).parents("tr").find("input[mark='lyId']").val();
    		if(lyId != ""){
    			$.ajax({
    				type : "POST",
    				data: {id: lyId},
    				url : ctx + "/antifraud/phoneConsult/delRecord",
    				success : function(data) {
    					if(data != "false"){
    						table.remove();
    					}else if(data == "false"){
    						art.dialog.alert("删除失败！出错啦（请检查phoneConsultForm.js 的 删除录音信息");
    	    			}else if($(data).find("input").length > 0){
    		        		window.location = "${ctx}";
    	    			}
    				}
    			});
    		}
    });
    /**
     * 切换页签
     */
    $(".page_li").click(function(){
    	var mark = $(this).attr("mark");
    	if(mark == "1"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c1").hide();
    		$(".s1").show();
    	}else if(mark == "2"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c2").hide();
    		$(".s2").show();
    	}else if(mark == "3"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c3").hide();
    		$(".s3").show();
    	}else if(mark == "4"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c4").hide();
    		$(".s4").show();
    	}else if(mark == "5"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c5").hide();
    		$(".s5").show();
    	}
    });

});	


/**
 * 工作证明人里点击其他展示文本框
 */
function otherLoanManRelaation(loan) {
   
        if ($(loan).val() == '2') {
        	$(loan).parent().next().attr("class",'required');
        	$(loan).parent().next().show();
        	$(loan).parent().next().attr('value','');
        } else {
        	$(loan).parent().next().attr("class",'');
        	$(loan).parent().next().hide();
        	
        }
}


/**
 * 生成电话录音信息相关参数
 */
function makeData(target){
	var param = "";
	target.find(".telRecord").each(function(i){
		param += '&phoneRecordList['+i+'].answerInfo=' + $(this).find("textarea[mark='answerInfo']").val();
		param += '&phoneRecordList['+i+'].lyId=' + $(this).find("input[mark='lyId']").val();
		param += '&phoneRecordList['+i+'].answerState=' + $(this).find("select[mark='answerState']").val();
	})
	return param;
}
/**
 * 全部保存
 */
function saveAll(target){
	var v = $("#saveDoneFlag").val();
	if(v == "1"){
		return;
	}
	// 校验是否通过标识 1 代表 未通过
	var checkFlag="";
	// 当前保存页签标识
	var saveMark = $(target).attr("mark");
	// 当前页签所有 form对象
	var thisForm = $("#"+saveMark).find("form");
	
	thisForm.each(function(){
		var raNum = $(this).find("input[type='radio']:checked").length;
		if (saveMark == 'workBody' || saveMark == 'workProvesBody') {
			// 工作信息工作证明人
			if(raNum == 0){
				// 点选框标红
				$(this).find(".listbg02").find("p").attr("style","color:red;")
				checkFlag = "1";
			}
		} else {
			// 家庭联系人 其他联系人
			if (saveMark == 'familyBody' || saveMark == 'otherBody') {
				// 电话
				var dhgxshTel = $(this).find("input[name='dhgxshTel']");
				// 电话评估结果
				var dhgxshAssessResult = $(this).find("input[name='dhgxshAssessResult']:checked").val();
				
				// 电话不为空、电话评估结果为空
				if (dhgxshTel.val() != '' && dhgxshAssessResult == undefined) {
					// 电话评估结果标红
					$(this).find("p[name='dhgxshAssessP']").attr("style","color:red;");
					// 验证不通过
					checkFlag = "1";	
				} else {
					// 电话评估结果标黑
					$(this).find("p[name='dhgxshAssessP']").attr("style","color:black;");
				}
				
				// 宅电
				var homeTel = $(this).find("input[name='homeTel']");
				// 宅电评估结果
				var homeTelAssessResult = $(this).find("input[name='homeTelAssessResult']:checked").val();
				
				// 宅电不为空、宅电评估结果为空
				if (homeTel.val() != '' && homeTelAssessResult == undefined) {
					// 宅电评估结果标红
					$(this).find("p[name='homeTelAssessP']").attr("style","color:red;");
					// 验证不通过
					checkFlag = "1";	
				} else {
					// 宅电评估结果标黑
					$(this).find("p[name='homeTelAssessP']").attr("style","color:black;");
				}
				
			} else if (saveMark == 'myConfirmBody') {
				// 本人信息
				// 电话
				var brhsPhone = $(this).find("input[name='brhsPhone']");
				// 电话评估结果
				var brhsAssessResult = $(this).find("input[name='brhsAssessResult']:checked").val();
				
				// 电话不为空、电话评估结果为空
				if (brhsPhone.val() != '' && brhsAssessResult == undefined) {
					// 电话评估结果标红
					$(this).find("p[name='brhsAssessP']").attr("style","color:red;");
					// 验证不通过
					checkFlag = "1";	
				} else {
					// 电话评估结果标黑
					$(this).find("p[name='brhsAssessP']").attr("style","color:black;");
				}
				
				// 宅电
				var homeTel = $(this).find("input[name='homeTel']");
				// 宅电评估结果
				var homeTelAssessResult = $(this).find("input[name='homeTelAssessResult']:checked").val();
				
				// 宅电不为空、宅电评估结果为空
				if (homeTel.val() != '' && homeTelAssessResult == undefined) {
					// 宅电评估结果标红
					$(this).find("p[name='homeTelAssessP']").attr("style","color:red;");
					// 验证不通过
					checkFlag = "1";	
				} else {
					// 宅电评估结果标黑
					$(this).find("p[name='homeTelAssessP']").attr("style","color:black;");
				}
				
			}
		}
		// 验证电话、宅电至少一个为空
		if (saveMark == 'familyBody' || saveMark == 'otherBody') {
			// 电话
			var dhgxshTel = $(this).find("input[name='dhgxshTel']");
			// 宅电
			var homeTel = $(this).find("input[name='homeTel']");
			// 同时为空
			if (dhgxshTel.val() == '' && homeTel.val() == '') {
				// 增加验证
				dhgxshTel.attr("class",'input_text178_70 required Phone');
				homeTel.attr("class",'input_text178_70 required Phone');
			} else {
				// 取消验证
				dhgxshTel.attr("class",'input_text178_70 Phone');
				homeTel.attr("class",'input_text178_70 Phone');
			}
			
		} else if (saveMark == 'myConfirmBody') {
			// 电话
			var brhsPhone = $(this).find("input[name='brhsPhone']");
			// 宅电
			var homeTel = $(this).find("input[name='homeTel']");
			// 同时为空
			if (brhsPhone.val() == '' && homeTel.val() == '') {
				// 增加验证
				brhsPhone.attr("class",'input_text178_70 required Phone');
				homeTel.attr("class",'input_text178_70 required Phone');
			} else {
				// 取消验证
				brhsPhone.attr("class",'input_text178_70 Phone');
				homeTel.attr("class",'input_text178_70 Phone');
			}
		}
		

	})
	
	// 校验form
	if(!checkForm(thisForm)){
		checkFlag = "1";
	}
	// 校验不同通过
	if(checkFlag != ""){
		return;
	}
	var jsonstr;
	var loanCode = $(window.parent.document).find("#loanCode").val();
	
	if(saveMark == 'workBody'){
		$(".workForm").each(function(i,item){
			var workId = $(this).parents("#workBody").find("input[name='workId']").val();
			var telRemark = $(this).find("input[name='telRemark']").val();
			var id=$(this).find("input[name='id']").val();
			var assessmentResult = $(this).find("input[name='assessmentResult']:checked").val();
			var workTel = $(this).find("input[mark='workTel']").val();
			jsonstr += "&telWorkList["+i+"].workId="+workId;
			jsonstr += "&telWorkList["+i+"].id="+id;
			jsonstr += "&telWorkList["+i+"].telRemark="+telRemark;
			jsonstr += "&telWorkList["+i+"].assessmentResult="+assessmentResult;
			jsonstr += "&telWorkList["+i+"].workUnitTel="+workTel;
		})
	}else if(saveMark == 'workProvesBody'){
		$("#workProvesBody form").each(function(i,item){
			var id=$(this).find("input[name='id']").val();
			var name=$(this).find("input[name='name']").val();
			var telRemark=$(this).find("input[name='telRemark']").val();
			var type=$(this).find("input[name='type']").val();
			var loanManRelation=$(this).find("select[name='loanManRelation']").val();
			if(loanManRelation == '2'){
				var otherTelRemark = $(this).find("input[name='otherTelRemark']").val();
			}else{
				var otherTelRemark = "";
			}
			var dhgxshTel=$(this).find("input[name='dhgxshTel']").val();
			var dhgxshAssessResult = $(this).find("input[name='dhgxshAssessResult']:checked").val();
			
			jsonstr += "&phoneWorkProvesList["+i+"].id="+id;
			jsonstr += "&phoneWorkProvesList["+i+"].loanCode="+loanCode;
			jsonstr += "&phoneWorkProvesList["+i+"].telRemark="+telRemark;
			jsonstr += "&phoneWorkProvesList["+i+"].name="+name;
			jsonstr += "&phoneWorkProvesList["+i+"].type="+type;
			jsonstr += "&phoneWorkProvesList["+i+"].loanManRelation="+loanManRelation;
			jsonstr += "&phoneWorkProvesList["+i+"].dhgxshTel="+dhgxshTel;
			jsonstr += "&phoneWorkProvesList["+i+"].dhgxshAssessResult="+dhgxshAssessResult;
			jsonstr += "&phoneWorkProvesList["+i+"].otherTelRemark="+otherTelRemark;
			
		})
	}else if(saveMark == 'familyBody'){
		$("#familyBody form").each(function(i,item){
			var id=$(this).find("input[name='id']").val();
			var loanCode = $(window.parent.document).find("#loanCode").val();
			var name=$(this).find("input[name='name']").val();
			var telRemark=$(this).find("input[name='telRemark']").val();
			var type=$(this).find("input[name='type']").val();
			var loanManRelation=$(this).find("select[name='loanManRelation']").val();
			var dhgxshTel=$(this).find("input[name='dhgxshTel']").val();
			var dhgxshAssessResult = $(this).find("input[name='dhgxshAssessResult']:checked").val();
			if (dhgxshAssessResult == undefined) {
				dhgxshAssessResult = "";
			}
			var homeTel = $(this).find("input[name='homeTel']").val();
			var homeTelAssessResult = $(this).find("input[name='homeTelAssessResult']:checked").val();
			if (homeTelAssessResult == undefined) {
				homeTelAssessResult = "";
			}
			var homeTelRemark = $(this).find("input[name='homeTelRemark']").val();
			
			jsonstr += "&phoneWorkProvesList["+i+"].id="+id;
			jsonstr += "&phoneWorkProvesList["+i+"].loanCode="+loanCode;
			jsonstr += "&phoneWorkProvesList["+i+"].name="+name;
			jsonstr += "&phoneWorkProvesList["+i+"].telRemark="+telRemark;
			jsonstr += "&phoneWorkProvesList["+i+"].type="+type;
			jsonstr += "&phoneWorkProvesList["+i+"].loanManRelation="+loanManRelation;
			jsonstr += "&phoneWorkProvesList["+i+"].dhgxshTel="+dhgxshTel;
			jsonstr += "&phoneWorkProvesList["+i+"].dhgxshAssessResult="+dhgxshAssessResult;
			jsonstr += "&phoneWorkProvesList["+i+"].homeTel="+homeTel;
			jsonstr += "&phoneWorkProvesList["+i+"].homeTelAssessResult="+homeTelAssessResult;
			jsonstr += "&phoneWorkProvesList["+i+"].homeTelRemark="+homeTelRemark;
			
		})
	}else if(saveMark == 'myConfirmBody'){
		$("#myConfirmBody form").each(function(i,item){
			var id=$(this).find("input[name='id']").val();
			var loanCode = $(window.parent.document).find("#loanCode").val();
			var brhsAssessResult = $(this).find("input[name='brhsAssessResult']:checked").val();
			if(brhsAssessResult == undefined){
				brhsAssessResult = "";
			}
			var brhsPhone = $(this).find("input[name='brhsPhone']").val();
			var telRemark = $(this).find("input[name='telRemark']").val();
			var homeTel = $(this).find("input[name='homeTel']").val();
			var homeTelAssessResult = $(this).find("input[name='homeTelAssessResult']:checked").val();
			if(homeTelAssessResult == undefined){
				 homeTelAssessResult ="";
			}
			var homeTelRemark = $(this).find("input[name='homeTelRemark']").val();
			
			jsonstr += "&phoneConfirmList["+i+"].id="+id;
			jsonstr += "&phoneConfirmList["+i+"].loanCode="+loanCode;
			jsonstr += "&phoneConfirmList["+i+"].telRemark="+telRemark;
			jsonstr += "&phoneConfirmList["+i+"].brhsPhone="+brhsPhone;
			jsonstr += "&phoneConfirmList["+i+"].brhsAssessResult="+brhsAssessResult;
			jsonstr += "&phoneConfirmList["+i+"].homeTel="+homeTel;
			jsonstr += "&phoneConfirmList["+i+"].homeTelAssessResult="+homeTelAssessResult;
			jsonstr += "&phoneConfirmList["+i+"].homeTelRemark="+homeTelRemark;
		})	
	}else if(saveMark == 'otherBody'){
		$("#otherBody form").each(function(i,item){
			var id=$(this).find("input[name='id']").val();
			var loanCode = $(window.parent.document).find("#loanCode").val();
			var name=$(this).find("input[name='name']").val();
			var telRemark=$(this).find("input[name='telRemark']").val();
			var type=$(this).find("input[name='type']").val();
			var loanManRelation=$(this).find("select[name='loanManRelation']").val();
			var dhgxshTel=$(this).find("input[name='dhgxshTel']").val();
			var dhgxshAssessResult = $(this).find("input[name='dhgxshAssessResult']:checked").val();
			if(dhgxshAssessResult == undefined){
				dhgxshAssessResult = "";
			}
			var otherTelRemark = $(this).find("input[name='otherTelRemark']").val();
			var homeTel = $(this).find("input[name='homeTel']").val();
			var homeTelAssessResult = $(this).find("input[name='homeTelAssessResult']:checked").val();
			if(homeTelAssessResult == undefined){
				homeTelAssessResult = "";
			}
			var homeTelRemark = $(this).find("input[name='homeTelRemark']").val();
			
			jsonstr += "&phoneWorkProvesList["+i+"].id="+id;
			jsonstr += "&phoneWorkProvesList["+i+"].loanCode="+loanCode;
			jsonstr += "&phoneWorkProvesList["+i+"].name="+name;
			jsonstr += "&phoneWorkProvesList["+i+"].telRemark="+telRemark;
			jsonstr += "&phoneWorkProvesList["+i+"].type="+type;
			jsonstr += "&phoneWorkProvesList["+i+"].loanManRelation="+loanManRelation;
			jsonstr += "&phoneWorkProvesList["+i+"].dhgxshTel="+dhgxshTel;
			jsonstr += "&phoneWorkProvesList["+i+"].dhgxshAssessResult="+dhgxshAssessResult;
			jsonstr += "&phoneWorkProvesList["+i+"].otherTelRemark="+otherTelRemark;
			jsonstr += "&phoneWorkProvesList["+i+"].homeTel="+homeTel;
			jsonstr += "&phoneWorkProvesList["+i+"].homeTelAssessResult="+homeTelAssessResult;
			jsonstr += "&phoneWorkProvesList["+i+"].homeTelRemark="+homeTelRemark;
			
		})
	}
	var param = makeData(thisForm);
	jsonstr += param;

	$.ajax({
		type:"post",
		data:encodeURI(jsonstr),
		url : ctx + "/antifraud/phoneConsult/saveWorkAll",
		dataType:'json',
		success : function(data) {
			$("#saveDoneFlag").attr("value","0");
			if(data != false){
				
				if(saveMark == 'workBody'){
					$(".workForm").each(function(i,item){
						$(this).find("input[name='id']").val(data.telWorkList[i].id);
					})
				}else if(saveMark == 'workProvesBody'){
					$("#workProvesBody form").each(function(i,item){
						$(this).find("input[name='id']").val(data.phoneWorkProvesList[i].id);
					})
				}else if(saveMark == 'familyBody'){
					$("#familyBody form").each(function(i,item){
						$(this).find("input[name='id']").val(data.phoneWorkProvesList[i].id);
					})
				}else if(saveMark == 'myConfirmBody'){
					$("#myConfirmBody form").each(function(i,item){
						$(this).find("input[name='id']").val(data.phoneConfirmList[i].id);
					})
				}else if(saveMark == 'otherBody'){
					$("#otherBody form").each(function(i,item){
						$(this).find("input[name='id']").val(data.phoneWorkProvesList[i].id);
					})
				}
				
				art.dialog.tips("保存成功");
			}else{
				art.dialog.alert("保存失败！出错啦");
			}
		}
	});
}

/**
 * 播放录音
 * @param url
 * @param phoneNumber
 * @param key
 */
function play(phoneNumber,key)
{
	if(key!=''&&phoneNumber!=''){
		alert(key);
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
}

/**
 * 单选框选中后清除样式
 * @param event
 */
function radioClick(event){
	$(event).parents("p").removeAttr("style");
}