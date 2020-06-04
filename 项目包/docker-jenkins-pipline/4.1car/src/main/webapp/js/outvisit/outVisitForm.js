$(function(){
	// 省市联动
	$("select[mark='province']").change(function() { 
		 var mark = $(this).attr("id").split("_");
		 var city = $("#"+mark[0]+"_cityId");
		 var district = $("#"+mark[0]+"districtId");
		 var provinceId = $(this).val();
		 if(provinceId==""){
			 city.empty();
			 city.append("<option value='' selected>请选择</option>");
			 city.trigger("change");
			 district.empty();
			 district.append("<option value='' selected>请选择</option>");
			 district.trigger("change");
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
	
	// 市区联动
	$("select[mark='city']").change(function() {
		var mark = $(this).attr("id").split("_");
		var district = $("#"+mark[0]+"_districtId");
		var cityId = $(this).val();
		 if(cityId==""){
			 district.empty();
			 district.append("<option id='' value='' selected>请选择</option>");
			 district.trigger("change");
		 }else{
		     $.ajax({
         		type : "POST",
         		url: ctx + "/common/selectInit/asynLoadDistrict",
         		data: {cityId: cityId},	
         		success : function(data) {
         			var resultObj = eval("("+data+")");
         			district.empty();
         			district.append("<option value='' selected>请选择</option>");
	                $.each(resultObj,function(i,item){
	                	district.append("<option value="+item.code+">"+item.name+"</option>");
	                });
	                district.trigger("change");
	                district.attr("disabled", false);
         		}
           });
		}
	});

	//选择外访类型后操作
	$(".checkBox").on("click", function () {
		var checked = this.checked;
		var val = $(this).val();
		// 选择外访家庭
		if(val=='0') {
			trValid("family", checked);
			textAreaValid("familyRemark", checked);		
		} 
		// 选择外访单位
		if(val=='1'){			
			trValid("work", checked);
			textAreaValid("workRemark", checked);
			if (checked) {		
				$("#showWork").html("外访单位");		
				// 选择外访单位与外访经营地址互斥
				$("#business").addClass("hide");
				$("#business input,select").attr("disabled",true);
				$(".checkBox").eq(2).prop("checked",false);
				
				if ($("#business input,select").hasClass("required")) {
					$("#business input,select").removeClass("required");
				}
			}	
		}
		// 选择外访经营地址
		if(val=='2') {
			trValid("business", checked);	
			textAreaValid("workRemark", checked);
			if (checked) {
				$("#showWork").html("外访经营地址");
				
				$("#work").addClass("hide");
				$("#work input").attr("disabled",true);
				
				$(".checkBox").eq(1).prop("checked",false);
				if (!$("#business input,select").hasClass("required")) {
					$("#business input,select").addClass("required");
				}
			} 
		}
	});	
	
	$(".textarea_refuse").each(function() {
		showWordCount(this);
	});
	
	$(".textarea_refuse").keyup(function() {
		showWordCount(this);
	});	
	
	$(".mt10").find("input,select,textarea").each(function(){
		$(this).attr("disabled",true);
		if($(this).hasClass("required")) {
			$(this).removeClass("required");
		}		
	});
	
})

function showWordCount(obj){
	var text=$(obj).val();
	var counter=text.length;
	$(obj).parents("div:first").find(".textareP").find("var").text(1000-counter);
}

// tr行隐藏，行内input disable|或者相反
function trValid(id, checked) {
	if(checked) {
		$("#"+id+"").removeClass("hide");
		$("#"+id+" input,select").removeAttr("disabled");
	} else {
		$("#"+id+"").addClass("hide");
		$("#"+id+" input,select").attr("disabled",true);
	}	
}

// 文本输入框的隐藏失效或者相反
function textAreaValid(id, checked) {
	if (checked) {
		$("#"+id+"").removeClass("hide");
		$("#"+id+" textarea").removeAttr("disabled");
		if (!$("#"+id+" textarea").hasClass("required")) {
			$("#"+id+" textarea").addClass("required");
		}		
	} else {
		$("#"+id+"").addClass("hide");
		$("#"+id+" textarea").attr("disabled",true);
		if ($("#"+id+" textarea").hasClass("required")) {
			$("#"+id+" textarea").removeClass("required");
		}		
	}
}

