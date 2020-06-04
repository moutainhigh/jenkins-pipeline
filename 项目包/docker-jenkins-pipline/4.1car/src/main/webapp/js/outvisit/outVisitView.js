$(function(){
	var visitType = $("#visitType").val();
	if(visitType == "") {
		$(".mt10").each(function(){
			$(this).removeClass("hide");
		});		
		$(".table1").find("tr").each(function(){
			$(this).removeClass("hide");
		});		
	}
	
	if(visitType!=undefined && visitType!="" && visitType!=null){
		var trs = $(".table1").find("tr");
		var types = visitType.split(",");
		for(var k = 0; k < types.length; k++){
			trs.eq(types[k]).removeClass("hide");
			if(types[k]=="0") {				
				$("#familyRemark").removeClass("hide");
			}
			if(types[k]=="1"){				
				$("#workRemark").removeClass("hide");				
				$("#showWork").html("外访单位");
			}
			if(types[k]=="2"){
				$("#workRemark").removeClass("hide");
				$("#showWork").html("外访经营地址");
			}
		}
	}	
});
