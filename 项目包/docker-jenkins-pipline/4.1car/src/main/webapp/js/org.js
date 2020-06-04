
var org = {};

/**
 * 表单验证
 */
org.validate = function(){
	$("#inputForm").validate({
		rules: {
			name: {remote: ctx + "/sys/org/asynCheckName?oldName=" + encodeURIComponent("${org.name}")},
			code: {remote: ctx + "/sys/org/asynCheckCode?oldCode=" + encodeURIComponent("${org.code}")}
		},
		messages: {
			name: {remote: "机构名称已存在"},
			code: {remote: "机构编码已存在"}
		},
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
};

/**
 * 省市区联动
 */
$(function(){
	$("#provinceId").change(function() { 
		 var provinceId = $("#provinceId option:selected").val();
		 if(provinceId==""){
			 $("#cityId").empty();
			 $("#cityId").append("<option value='' selected=true>请选择</option>");
			 $("#cityId").trigger("change");
			 $("#districtId").empty();
			 $("#districtId").append("<option value='' selected=true>请选择</option>");
			 $("#districtId").trigger("change");
		 }else{
		     $.ajax({
          		type : "POST",
          		url : ctx + "/common/selectInit/asynLoadCity",
          		data: {provinceId: provinceId},	
          		success : function(data) {
          			var resultObj = eval("("+data+")");
          		    $("#cityId").empty();
          		    $("#cityId").append("<option value=''>请选择</option>");
	                $.each(resultObj,function(i,item){
	                   $("#cityId").append("<option value="+item.code+">"+item.name+"</option>");
	                });
	                $("#cityId").trigger("change");
	                $("#cityId").attr("disabled", false);
          		}
          	});
		 }
	});
	/**
	 * 获取区
	 */
	$("#cityId").change(function() {
		 var cityId = $("#cityId option:selected").val();
		 if(cityId==""){
			 $("#districtId").empty();
			 $('#districtId').append("<option value='' selected>请选择</option>");
			 $("#districtId").trigger("change");
		 }else{
			 $("#districtId").empty();
		     $.ajax({
          		type : "POST",
          		url: ctx + "/common/selectInit/asynLoadDistrict",
          		data: {cityId: cityId},	
          		success : function(data) {
          			var resultObj = eval("("+data+")");
          		    $("#districtId").empty();
          		    $("#districtId").append("<option value='' selected>请选择</option>");
	                $.each(resultObj,function(i,item){
	                   $("#districtId").append("<option value="+item.code+">"+item.name+"</option>");
	                });
	                $("#districtId").trigger("change");
	                $("#districtId").attr("disabled", false);
          		}
            });
		}
  });
});