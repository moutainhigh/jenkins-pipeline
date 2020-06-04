$(function () {	
	
	$("#clearInfo").click(function(){
			$(':input','#searchForm')  
			.not(':button, :submit, :reset') 
			.val('')  
			.removeAttr('checked')  
			.removeAttr('selected');
    });	
  	
    $("#transferCancel").click(function(){
    	$('#modal_Tasktransfer').modal('hide');
    	$("#modal_Tasktransfer .modal-body").empty();
    });  
        
 	// 提交转签申请
    $("#transferSave").click(function(){
    	var newId = $(document).find("form").find("input[name='acceptUID']").val();
    	if(newId==''||newId==null) {
    		art.dialog.tips('没有选择用户!')
			return false;
    	} else {
        	var applyNums = $('#reportTable').find("input[mark='wobNum']:checked");        	
    		var param = "";
			for(var i = 0; i < applyNums.length; i++){
				param += '&wobNum=' + $(applyNums[i]).val();
			}
			$.ajax({
				type : "POST",
				data : "acceptUID=" + newId + param,
				url : ctx+"/management/taskTransfer/save",
				success : function(data) {
					art.dialog.tips('保存成功!')
					$('#modal_Tasktransfer').modal('hide');					
					window.close();
					window.location=ctx+'/management/taskTransfer/fetchTaskItems';
				}
			});
		}		
    });    
		
    $("#showAllTransfer").click(function(){
    	if($('#reportTable').find("input[mark='wobNum']:checked").size()>0){
    		var uuId = $('#currentUID').val();
    		$.get(ctx+"/management/taskTransfer/form?origin="+uuId,function(data){
    			$("#modal_Tasktransfer .modal-body").empty();
    			$("#modal_Tasktransfer .modal-body").append(data);
    			$('#modal_Tasktransfer').modal('show');
    		},"html");
    	}else {
    		art.dialog.tips('没有选择任何一项!')
			return false;
    	}
	});
    
    // 省市联动
	$("select[mark='province']").change(function() {		
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
	
});


function showTransfer(obj) {
	$('#reportTable').find("input[mark='wobNum']:checked").prop("checked",false);
	var trThis = $(obj).parents("tr:first"); 
	trThis.find("input[mark='wobNum']").prop("checked",true);
	var uuId = trThis.find("input[name='dealUser']").val();
	$.get(ctx+"/management/taskTransfer/form?origin="+uuId,function(data){
		$("#modal_Tasktransfer .modal-body").empty();
		$("#modal_Tasktransfer .modal-body").append(data);
		$('#modal_Tasktransfer').modal('show');
	},"html");
}

//绑定全选事件
function selectAllNum(obj) {
	var checked = $(obj).prop("checked")
	$('#reportTable').find("input[mark='wobNum']").prop("checked",checked);		
}
