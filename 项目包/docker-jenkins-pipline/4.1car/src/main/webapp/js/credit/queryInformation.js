// 初始化方法
$(function(){
	// 初始化数据
	initData();
});

// 初始化数据
function initData(){
	$("#queryInfoId").find("tbody").html("");
	var data = $(window.parent.document).find("form[id='param']").serialize();
	// 公积金
	$.ajax({
		url:ctx+'/creditdetailed/queryrecord/showData',
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
			if(data != null){
				for( var i = 0; i < data.length; i++ ){
					if(data[i].id != null){
						var target = $("#queryInfoId");
						var temp = $("#model tbody tr");
						var htmInfo=temp.cloneSelect2(true,true);
						htmInfo.find("td[name='queryType']").text(data[i].queryType);
						htmInfo.find("td[name='queryDay']").text(formatTime(data[i].queryDay))
						htmInfo.appendTo($(target));
						sort();
					}
				}
			}
		}
	});
}

//格式化时间为 YYYY-MM-DD
function formatTime( param ){
	var result = "";
	if( param != null && typeof(param) != "undefined"){
		var tim = new Date(param);
		var month = (tim.getMonth()+1);
		if(tim.getMonth() < 9){
			month="0"+(tim.getMonth()+1);
		}
		var day = tim.getDate();
		if(tim.getDate() < 10){
			day="0"+tim.getDate()
		}
		result = tim.getFullYear()+"-"+month+"-"+day;
	}
	return result;
}

//显示编号
function sort(){
	// 循环公积金信息(进行排序)
	var allInfoNum = $("#queryInfoId").find("tbody").find("td[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});
}