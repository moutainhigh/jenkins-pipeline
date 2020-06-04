$(function(){
	// 全选
	$("#fallback").bind("click", function () {
		$(this).parents("tr").siblings("tr").find("input[type='checkbox']").prop("checked",this.checked);
	});
});

// 三级框单击事件
function thirdClick(own,parId){
	$("#"+parId).prop("checked",false);
	$("#fallback").prop("checked",false);
	
	var levelBrothers = $(own).parent("td").find("input[type='checkbox']");
	var brothers = $(own).parents("table").find("input[mark='third']");

	for(var i = 0; i < levelBrothers.length; i++){
		if($(levelBrothers[i]).prop("checked") == true){
			$("#"+parId).prop("checked",true);
			$("#fallback").prop("checked",true);
		}
	}
	
	for(var i = 0; i < brothers.length; i++){
		if($(brothers[i]).prop("checked") == true){
			$("#fallback").prop("checked",true);
		}
	}
}