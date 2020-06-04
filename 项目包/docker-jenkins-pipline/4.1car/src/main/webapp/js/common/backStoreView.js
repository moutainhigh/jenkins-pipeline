$(function(){
	$.ajax({
		type : "POST",
		dataType: "json", 
		url : ctx + "/common/backStore/getById?relationId="+$("#relId").val(),
		success : function(data) {
			$("#feedBack").val(data.feedBack);
			var checks = data.json.split(",");
			$.each(checks, function (index, item) {
				$("#"+item).attr("checked",true);
				
            }); 
			$("#feedBackText").val(data.feedBack);
		}
	});
});
