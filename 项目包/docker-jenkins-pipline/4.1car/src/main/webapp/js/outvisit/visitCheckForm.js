$(function(){   
	/**
	 * 外访核查回显 
	 */
	var dictSurveyType= $("#clickType").val();
	var surveyFamly= $("#clickFamly").val();
	var surveyWork= $("#clickWork").val();
	// 如果 类型等于1 则显示 工薪，不等于1则显示经营类
	if(dictSurveyType == "1"){
		$("#working").attr("checked","checked");
		$("#working_famly").children("input[name='surveyFamly'][value ="+surveyFamly+"]").attr("checked",true);
		$("#working_Work").children("input[name='surveyWork'][value ="+surveyWork+"]").attr("checked",true);
		$("#businessForm").hide();
		$("#workingForm").show();       		
	}else if(dictSurveyType == "0"){
		$("#business").attr("checked","checked");
		$("#business_Famly").children("input[name='surveyFamly'][value ="+surveyFamly+"]").attr("checked",true);
		$("#business_Work").children("input[name='surveyWork'][value ="+surveyWork+"]").attr("checked",true);
		$("#workingForm").hide();
		$("#businessForm").show();       		
	}
   	/**
   	 * 备注剩余字数
   	 */
	var text=$("#textarea").val();
	var counter=text.length;
	$(".textareP").find("var").text(500-counter);
	
	$("#textarea").keyup(function() {
		var text=$("#textarea").val();
		var counter=text.length;
		$(".textareP").find("var").text(500-counter);
	});
	
	/**
	 * 外访类型切换
	 */
	$(".workBusinesRadio").click(function(){
		$(this).parents("p").removeAttr("style");
		var mark = $(this).attr("mark");
	    if(mark =="working"){
	    	$("#businessForm").hide();
	    	$("#workingForm").show();
	    	$("input[mark='business']").attr("checked",false);
	    }else if(mark =="business"){
	    	$("#businessForm").show();
	    	$("#workingForm").hide();
	    	$("input[mark='working']").attr("checked",false);
	    }
	})
	
	/**
	 * 保存
	 */
	$(".save").click(function(){
		var mark = $(".workBusinesRadio:checked").attr("mark");
		var i = $(".workBusinesRadio:checked").length;
		if(i == "0"){
			$(".workBusinesRadio").parents("p").attr("style","border: solid red;") 
			return;
		}
		if(mark == "working"){
			var i = $(".workCheck1:checked").length;
			if(i == "0"){
				$(".workCheck1").parents("p").attr("style","border: solid red;") 
				return;
			}
			var a = $(".workCheck2:checked").length;
			if(a == "0"){
				$(".workCheck2").parents("p").attr("style","border: solid red;") 
				return;
			}
		}else{
			var i = $(".businessCheck1:checked").length;
			if(i == "0"){
				$(".businessCheck1").parents("p").attr("style","border: solid red;") 
				return;
			}
			var a = $(".businessCheck2:checked").length;
			if(a == "0"){
				$(".businessCheck2").parents("p").attr("style","border: solid red;") 
				return;
			}
		}
		if(!checkForm($("#textForm"))){
			return;
		}
    	var param = $(window.parent.document).find("#param").serialize();
    	if(mark == "working"){
    		var fromSerialize = "&" + $("#workingForm").serialize();
    	}else{
    		var fromSerialize = "&" + $("#businessForm").serialize();
    	}
    	var textArea = "&" + $("#textForm").serialize();
    	// 是否保存成功标识 0 是保存成功
    	var saveFlag = $("#saveFlag").val();
    	if(saveFlag != "1"){
    		$("#saveFlag").val("1");
    		$.ajax({
    			type: "post",
    			url: ctx+'/verify/visitcheck/saveVisitCheck',
    			data:param+fromSerialize+textArea,
    			success: function(data){
    				$("#saveFlag").val("0");
    				if(data != "false"){
    					$("input[name='id']").val(data);
    					art.dialog.tips('保存成功!');
    				}else{
    					art.dialog.alert("保存失败！出错啦");
    				}
    			}
    		});	    
    	}
	})
	
});

/**
 * 单选框选中后清除样式
 * @param event
 */
function radioClick(event){
	$(event).parents("p").removeAttr("style");
}

function showTaskInfo(loanCode,historyId){
	art.dialog.open(ctx+"/check/outVisit/goView?relationId="+historyId+"&loanCode="+loanCode,{
		title:"查看外访清单",width:"700px",height:"410px",window:"top",lock: false,border: false
	});
}
