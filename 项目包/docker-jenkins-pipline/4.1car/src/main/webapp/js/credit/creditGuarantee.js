// 祥版征信相关操作（担保明细）


$(function(){
	// 初始化数据
	initGuaranteeData();
});

// 初始化数据
function initGuaranteeData(){
	$("#guaranteeoneTable").find("tbody").html("");
	
	var data = $(window.parent.document).find("form[id='param']").serialize();
	
	$.ajax({
		url: ctx+"/credit/showGuaranteeData",
		data:data,
		type: "post",
		dataType:'json',
		success:function(data){
		 
			if( data != null ){
				// 担保明细信息
				if(data != null && data.creditGuaranteeDetailedList[0].id != null){
					for( var i=0; i<data.creditGuaranteeDetailedList.length; i++ ){
						if( data.creditGuaranteeDetailedList[0].id != null ){
							//append selce2难用，用clone
							var vTb=$('#guaranteeoneTable');
							var vTr=$('#guaranteeoneTable_trRow_1');
							var htmInfo=vTr.cloneSelect2(true,true);
							
							htmInfo.find("td[name='otherGuaranteeAmount']").text(formatMoney(data.creditGuaranteeDetailedList[i].otherGuaranteeAmount));
							htmInfo.find("td[name='realPrincipal']").text(formatMoney(data.creditGuaranteeDetailedList[i].realPrincipal));
							htmInfo.find("tr").attr("id","infoTr"+data.creditGuaranteeDetailedList[i].id);
							
							htmInfo.appendTo(vTb); 
							
							
						}
					}
				
					// 显示编号
					sortGuarantee();
				}
				 
			}
		}
	});
}

//显示编号
function sortGuarantee(){
	// 循环担保信息(进行排序)
	var allInfoNum = $("#guaranteeoneTable").find("tbody").find("td[name='num']");
	allInfoNum.each(function(i,item){
		$(this).text(i+1);
	});

}
