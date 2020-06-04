$(function(){
	// 隐藏所有页签内容
	hideAllData();
	// 显示单位信息
	$("#companyInfoBlock").show();
	// 地址显示效果转换（由下拉转换为内容展示）
	appendAddress();
	// 职业分类处理
	showZYFL();
})

//拼接联动的地址
function appendAddress(){
	
	$("select[mark='provinceId']").each(function(){
		var sheng = $(this).find("option:selected").text();
		var shi = $(this).parents("td").find("select[mark='cityId']").find("option:selected").text();
		var qu = $(this).parents("td").find("select[mark='districtId']").find("option:selected").text();
		var xiangxi = $(this).parents("td").find("input").val();
		var dizhi = sheng+"-"+shi+"-"+qu+"-"+xiangxi;
		$(this).parents("td").html("<p class='tleft pl10'>"+dizhi+"</p>");
	});
}


// 改变DHZH的tab状态
function changeDHZHTab(id){
 	$("#"+id+"1").siblings("span").removeClass("click");
 	$("#"+id+"1").addClass("click");
}	 

//tab点击切换方法
function showThisPage(dataId){
	hideAllData();
	$("#"+dataId).show();
	changeDHZHTab(dataId);
}
//隐藏所有页签内容
function hideAllData(){
	$("#companyInfoBlock, #workContactBlock, #familyContactBlock, #otherContactBlock, #personalConfirmBlock, #busiContractBlock").hide();
}


//初始化加载职业分类
function showZYFL(){
	var zyfl = $("input[name='professionHid']").val();
	// 职业分类
	$.ajax({
		type :"POST",
		data :"value="+zyfl,
		dataType:"json",
		url : ctx + "/verify/telcheck/getZYFL",
		success : function(data) {
			if(data != null){
				var oth = ""
				for(var i = 0; i < data.length; i++){
					if(data[i].value == zyfl){
						oth = data[i].label
						if(data[i].label == '其他'){
							$("span[name='professionRemark']").attr("class","");
						}else{
							$("span[name='professionRemark']").attr("class","hide");
						}
					}
				}
				$("font[name='zyflTD']").html(oth);
			}
		}
	});
}

function play(phoneNumber,key)
{
	if(key!=''&&phoneNumber!=''){
		$.ajax({
			type:"POST",
			url:ctx+"/verify/telcheck/getRecord",
			data:{callId:key},
			dataType : 'json',
			success:function(data){
				if(data.url!=undefined)
				window.parent.AudioPlay.getInstance().play(data.url,phoneNumber,key);
			}
		});
	}else{
		art.dialog.tips("没有可以播放的音频文件！");
	}
}