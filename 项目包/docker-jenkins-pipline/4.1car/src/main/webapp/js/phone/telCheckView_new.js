$(function(){
	// 隐藏所有页签内容
	hideAllData();
	// 显示单位信息
	$("#companyInfoBlock").show();
	
	//获取本人、法人代表人、配偶的身份证号
	var customerCertnum = $("#customerCertnum").val();
	var matecertNum = $("#matecertNum").val();
	var certNum = $("#certNum").val();
	var corporateRepresentMobile = $("#corporateRepresentMobile").val();
	if(certNum != ""){
		if(certNum != customerCertnum && certNum != matecertNum ){
			if(certNum != customerCertnum){
				//不一致,则需要将【本人】页面“法人代表人手机号码”字段变更为“手机号码”
				$("#commonTel").text("常用手机号");
			}
			if(certNum != matecertNum){
				//不一致，则需要将【家庭联系人】页签“手机号码(法人)”字段变更为“手机号码”
				$("#matePhone").text("手机号码");
			}
			
			var trTpls = "<tr id='frNum'>" +
			 "	<td class='listbg'>" +
			 "		<p class='tright pr5'>法定代表人手机号</p >" +
			 "	</td>" +
			 "	<td colspan='4' style='text-align: left; padding-left: 8px;'>" +
			 "		<p>"+corporateRepresentMobile+
			 "		</p>" +
			 "	</td>" +
			 "</tr>";
			$("#unitTable").append(trTpls);
		}
		if(certNum == customerCertnum || certNum == matecertNum ){
			if(certNum == customerCertnum){
				//若与本人“一致”则【本人】页面“手机号码”字段变更为“法人代表人手机号”
				$("#commonTel").text("法定代表人手机号");
			}
			if(certNum == matecertNum){
				//若与配偶“一致”则【家庭联系人】页面“手机号码”字段变更为“手机号码（法人）”
				$("#matePhone").text("手机号码(法人)");
			}
			//$("#unitTable tr:last").remove();
			$("#frNum").remove();
		}
	}
	
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