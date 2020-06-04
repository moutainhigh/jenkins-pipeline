$(function(){   
	/**
	 * 外访核查回显 
	 */
	var dictSurveyType= $("#clickType").val();
	var surveyFamly= $("#clickFamly").val();
	var surveyWork= $("#clickWork").val();

	if(dictSurveyType != ""){
    	// 如果 类型等于1 则显示 工薪，不等于1则显示经营类
       	if(dictSurveyType == "1"){
       		$("#working").attr("checked","checked");
       		$("#business").attr("disabled", true);
       		$("#working_famly").children("input[name='surveyFamly'][value ="+surveyFamly+"]").attr("checked",true);
       		$("#working_famly").children("input[name='surveyFamly'][value !="+surveyFamly+"]").attr("disabled",true);
       		$("#working_Work").children("input[name='surveyWork'][value ="+surveyWork+"]").attr("checked",true);
       		$("#working_Work").children("input[name='surveyWork'][value !="+surveyWork+"]").attr("disabled",true);
        	$("#businessForm").hide();
        	$("#workingForm").show();       		
       	}else if(dictSurveyType == "0"){
       		$("#business").attr("checked","checked");
       		$("#working").attr("disabled", true);
       		$("#business_Famly").children("input[name='surveyFamly'][value ="+surveyFamly+"]").attr("checked",true);
       		$("#business_Famly").children("input[name='surveyFamly'][value !="+surveyFamly+"]").attr("disabled",true);
       		$("#business_Work").children("input[name='surveyWork'][value ="+surveyWork+"]").attr("checked",true);
       		$("#business_Work").children("input[name='surveyWork'][value !="+surveyWork+"]").attr("disabled",true);
        	$("#workingForm").hide();
        	$("#businessForm").show();       		
       	}
       	$("#outVisitType").show();
       	$("#textForm").show();
	}else{
		$("#noOutVisit").show();
	}
});

function showTaskInfo(loanCode,historyId){
	art.dialog.open(ctx+"/check/outVisit/goView?relationId="+historyId+"&loanCode="+loanCode,{
		title:"查看外访清单",width:"700px",height:"410px",window:"top",lock: false,border: false
	});
}
