var fixedHeight = 0;
$(function(){

	/**
	 * 清除表单
	 */
	$("#rateAuditFormClrBtn").click(function(){
		$(':input','#searchForm').not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');
		$("#searchForm select").val("");
		$('#searchForm select').trigger("change");
	});
	
	/**
	 * 省份下拉框
	 */
	$("#provinceId").change(function() { 
		 var provinceId = $("#provinceId option:selected").attr("str");
		 if(provinceId==""){
			 $("#cityId").empty();
			 $("#cityId").append("<option str='' value='' selected=true>请选择</option>");
			 $("#cityId").trigger("change");
		 }else{
		     $.ajax({
         		type : "POST",
         		url : ctx + "/common/selectInit/asynLoadCity",
         		data: {provinceId: provinceId},	
         		success : function(data) {
         			var resultObj = eval("("+data+")");
         		    $("#cityId").empty();
         		    $("#cityId").append("<option str='' value=''>请选择</option>");
         		    var cityId = $("#cityCode").val();
	                $.each(resultObj,function(i,item){
	                	if(cityId == item.name){
	                		$("#cityId").append("<option selected str='"+item.id+"' value="+item.name+">"+item.name+"</option>");
	                	}else{
	                		$("#cityId").append("<option str='"+item.id+"' value="+item.name+">"+item.name+"</option>");
	                	}
	                });
	                $("#cityId").trigger("change");
         		}
         	});
		 }
	});
	$("#provinceId").trigger("change");
	orderbyInit();
	$("#searchBtn").click(function(){
		$("#pageNo").val(1);
		$("#searchForm").submit();
		return false;
	});
	var windwnHeight = $(window).height();
	fixedHeight	= windwnHeight*0.32;
	$(".fixed-table-container").css("height",(windwnHeight*0.68-25)+"px");
	$(window).resize(function () {
		windowResize();
	});
});
function windowResize(){
	var windwnHeight = $(window).height();
	$(".fixed-table-container").css("height",(windwnHeight-fixedHeight-25)+"px");
}
/**
 * 分页
 */
function page(n,s){
	if(n) $("#pageNo").val(n);
	if(s) $("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}
/**
 * 对页面元素进行排序
 * @param target
 */
function orderby(target){
	var sortKeyword = $(target).attr("sortKeyword");
	var hidetarget=$("#reportTable thead th span[sortKeyword='"+sortKeyword+"']");
	var sort = hidetarget.attr("sort");
	if(sort == "ASC"){
		sort ="DESC";
	}else{
		sort = "ASC";
	}
	$("#orderBy").val(sortKeyword+" "+ sort);
	$("#searchForm").submit();
}
function orderbyInit(){
	var key=$("#orderBy").val();
	if(key!=""){
		var array = key.split(" ");
		if(array.length==2){
			var orderspan=$("table thead th span[sortKeyword='"+array[0]+"']");
			if(orderspan.size()==1){
				orderspan.attr("sort",array[1]);
			}
		}
	}
}
/**
 * 显示隐藏检索条件
 * @param obj
 */
function showSome(obj){
	var src = $(obj).attr("src");
	var parent = $(obj).parents("tr");
	var sizeString = $(parent).css("height");
	var sizeInt = sizeString.substring(0,sizeString.indexOf("px"));
	var heightOld = $(".fixed-table-container").css("height");
	heightOld = heightOld.substring(0,heightOld.indexOf("px"));
	if(src.contains("down")){ // 展示
		$(parent).prev("tr").show()
						.prev("tr").show();
		src = src.replace("down","up");
		$(obj).attr("src",src);
		var newHeight = (parseFloat(heightOld)-parseFloat(2*sizeInt))+"px";
		$(".fixed-table-container").css("height",newHeight);
	}else{// 隐藏
		$(parent).prev("tr").hide()
							.prev("tr").hide();
		src = src.replace("up","down");
		$(obj).attr("src",src);
		var newHeight = (parseFloat(heightOld)+parseFloat(2*sizeInt))+"px";
		$(".fixed-table-container").css("height",newHeight);
	}
}