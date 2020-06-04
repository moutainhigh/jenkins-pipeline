// 单个解除table对象
var div;
// 全部解除table对象
var table_all;
$(function(){
	$(".show_hide_table").click(function(){
		var part_flag = $(this).attr("part_flag");
		var show_hide = $(this).attr("show_hide");
		var rules_code = $(this).attr("rules_code");
		var loan_code = $(this).attr("loan_code");
		var time_flag = $(this).attr("time_flag");
		var span =$(this);
		var divv=$(this).parent("div");
		if(show_hide == "0"){
			if(time_flag == "0"){
				span.attr("time_flag","1");
		        $.ajax({
		            type: "post",
		            url:ctx+"/antifraud/triggerRule/findCheckRepeatInfo",
		            data: {rid:rules_code,loanCode:loan_code,partFlag:part_flag},
		            async: true,
		            beforeSend: function(XMLHttpRequest){
		            	waitingDialog.show();
		            },
		            complete: function(XMLHttpRequest, textStatus){
		            	waitingDialog.hide();
		            },
		            success: function(data){
		            	divv.append(data);
		            	divv.find("p[mark='remark']").show();
		            	divv.find(".btn-xs").show();
		            	span.attr("show_hide","1");
		            }
		         });	
			}else{
				span.parent("div").find("table").show();
				span.parent("div").find("p[mark='remark']").show();
				divv.find(".btn-xs").show();
				span.attr("show_hide","1");
			}
		}else{
			span.parent("div").find("table").hide();
			span.parent("div").find("p[mark='remark']").hide();
			divv.find(".btn-xs").hide();
			span.attr("show_hide","0");
		}
	});
	
	//【单个】解除/还原按钮
	$(".jc_sf_btn").click(function(){
		div =$(this).parent("div");
		var button = div.find("button.jc_sf_btn")
		var span = div.find("span.show_hide_table")
		var status = span.attr("status");
		var id = span.attr("id");
		var p = div.find("p[mark='remark']");
		if(status == "0"){
			$('#relieveModel').modal('show');
		}else{
			var r=confirm("确认还原？");
			if (r !=true){
			 	return ;
			}
			$.ajax({
				type: "post",
				url:ctx+"/antifraud/triggerRule/updateRrelieveStatus",
				data: {rid:id,remark:"",status:status},
				success: function(data){
					if(data == "true"){
						// 还原成功 按钮变为 【解除】
						button.html("解除");
						// 解除成功 状态变为 【0】
						span.attr("status","0");
						// 将内容变为红色
						span.find("font").attr("color","red")
						// 添加解除理由
						p.html("");
						// 更改按钮颜色
						button.attr("class","btn btn-primary btn-xs jc_sf_btn");
					}else{
						art.dialog.tips('保存失败!');
					}
					$('#relieveModel').modal('hide')
					$(".remarkForm")[0].reset();
				}
			});				
		}
	})
	
	// 【单个】解除理由模态框 【确定】按钮
	$(".saveRemark").click(function(){
		if(!checkForm($("#remarkForm"))){
			return ;
		}
		var span = div.find("span.show_hide_table")
		var p = div.find("p[mark='remark']");
		var button = div.find("button.jc_sf_btn")
		var status = span.attr("status");
		var id = span.attr("id");
		var remark = $("#textarea").val();
		$.ajax({
			type: "post",
			url:ctx+"/antifraud/triggerRule/updateRrelieveStatus",
			data: {rid:id,remark:remark,status:status},
			success: function(data){
				if(data == "true"){
					// 解除成功 按钮变为 【还原】
					button.html("还原");
					// 解除成功 状态变为 【1】
					span.attr("status","1");
					// 将内容变为黑色
					span.find("font").attr("color","black")
					// 添加解除理由
					p.html("<font color='red'>解除理由：</font>"+remark);
					// 更改按钮颜色
					button.attr("class","btn btn-danger btn-xs jc_sf_btn");
				}else{
					art.dialog.tips('保存失败!');
				}
				$('#relieveModel').modal('hide')
				$(".remarkForm")[0].reset();
			}
		});
	})
	
	// 【全部】解除按钮
	$(".jc_all").click(function(){
		var ridAll="";
		table_all = $(this).parent("div").next("div").find("div");
		table_all.each(function(){
			var id=$(this).find("span.show_hide_table").attr("id");
			var this_status=$(this).find("span.show_hide_table").attr("status");
			if(this_status == "0"){
				ridAll = ridAll+id+","
			}
		})			
		if(ridAll == ""){
			art.dialog.tips("已经全部解除！");
			return;
		}
		$('#relieveModelAll').modal('show');
	})
	
	// 【全部解除】理由模态框【确定】按钮
	$(".saveRemarkAll").click(function(){
		if(!checkForm($("#remarkFormAll"))){
			return ;
		}
		var remark = $("#textareaAll").val();
		var ridAll="";
		table_all.each(function(){
			var id=$(this).find("span.show_hide_table").attr("id");
			var this_status=$(this).find("span.show_hide_table").attr("status");
			if(this_status == "0"){
				ridAll = ridAll+id+","
			}
		})			
		$.ajax({
			type: "post",
			url:ctx+"/antifraud/triggerRule/updateRemarkAll",
			data: {ridAll:ridAll,remark:remark,status:"0"},
			success: function(data){
				if(data == "true"){
					table_all.each(function(){
						var span = $(this).find("span.show_hide_table")
						var p = $(this).find("p[mark='remark']");
						var button = $(this).find("button.jc_sf_btn")					
						var this_status=$(this).find("span.show_hide_table").attr("status");
						if(this_status == "0"){
							// 解除成功 按钮变为 【还原】
							button.html("还原");
							// 解除成功 状态变为 【1】
							span.attr("status","1");
							// 将内容变为黑色
							span.find("font").attr("color","black")
							// 添加解除理由
							p.html("<font color='red'>解除理由：</font>"+remark);
							// 更改按钮颜色
							button.attr("class","btn btn-danger btn-xs jc_sf_btn");						
						}
					})					
				}else{
					art.dialog.tips('保存失败!');
				}
				$('#relieveModelAll').modal('hide')
				$(".remarkFormAll")[0].reset();
			}
		});		

	})
	
	// 【全部还原】按钮
	$(".sf_all").click(function(){
		var r=confirm("是否全部还原？");
		if (r !=true){
		 	return ;
		}
		var ridAll="";
		table_all =  $(this).parent("div").next("div").find("div");
		table_all.each(function(){
			var id=$(this).find("span.show_hide_table").attr("id");
			var this_status=$(this).find("span.show_hide_table").attr("status");
			if(this_status == "1"){
				ridAll = ridAll+id+","
			}
		})			
		if(ridAll == ""){
			art.dialog.tips("已经全部还原！");
			return;
		}
		$.ajax({
			type: "post",
			url:ctx+"/antifraud/triggerRule/updateRemarkAll",
			data: {ridAll:ridAll,remark:"",status:"1"},
			success: function(data){
				if(data == "true"){
					table_all.each(function(){
						var span = $(this).find("span.show_hide_table")
						var p = $(this).find("p[mark='remark']");
						var button = $(this).find("button.jc_sf_btn")					
						var this_status=$(this).find("span.show_hide_table").attr("status");
						if(this_status == "1"){
							// 还原成功 按钮变为 【解除】
							button.html("解除");
							// 解除成功 状态变为 【0】
							span.attr("status","0");
							// 将内容变为红色
							span.find("font").attr("color","red")
							// 添加解除理由
							p.html("");
							// 更改按钮颜色
							button.attr("class","btn btn-primary btn-xs jc_sf_btn");						
						}
					})					
				}else{
					art.dialog.tips('保存失败!');
				}
				$('#relieveModelAll').modal('hide')
				$(".remarkFormAll")[0].reset();
			}
		});			
		
	})	
    $('.btn-default').click(function(){
    	$('.modal').modal('hide');
    	$(".remarkForm")[0].reset();
    	$(".remarkFormAll")[0].reset();
    });
	//展开收缩
	$("span[mark='change']").click(function(){
		var title = $(this).attr("title");
	   	if(title == "收起"){
	   		$(this).find("img").attr("src",ctxStatic+"/images/add.jpg");
	   		$(this).attr("title","展开");
	   	}else{
	   		$(this).find("img").attr("src",ctxStatic+"/images/minus.jpg");
	   		$(this).attr("title","收起");
	   	}
	});

})
// 到反欺诈页面
function toAfraud(event){
	var loacCode = $(event).attr("val");
    $.ajax({
        type: "post",
        url: ctx+"/antifraud/triggerRule/getApplyid",
        data: {loanCode:loacCode},
        success: function(data){
        	if(data != null && data != ""){
        		if(data.afraudCheckFlag > 0){
        			window.open(ctx+'/bpm/flowController/openForm?applyId='+data.applyid+'&dealType=2');
        		}else{
        			art.dialog.tips('无反欺诈记录');
        		}
        	}else{
        		alert("服务器异常");
        	}
        }
     });	
}
// 到信审页面
function toCheck(event){
	var loacCode = $(event).attr("val");
	$.ajax({
		type: "post",
		url: ctx+"/antifraud/triggerRule/getApplyid",
		data: {loanCode:loacCode},
		success: function(data){
			if(data != null && data != ""){
				window.open(ctx+'/bpm/flowController/openForm?applyId='+data.applyid+'&dealType=1');
			}else{
				alert("服务器异常");
			}
		}
	});	
}























