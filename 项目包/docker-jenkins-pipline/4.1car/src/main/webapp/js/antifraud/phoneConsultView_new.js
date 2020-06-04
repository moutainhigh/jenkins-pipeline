$(function(){

    // 切换页签
    $(".page_li").click(function(){
    	var mark = $(this).attr("mark");
    	if(mark == "1"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c1").hide();
    		$(".s1").show();
    	}else if(mark == "2"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c2").hide();
    		$(".s2").show();
    	}else if(mark == "3"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c3").hide();
    		$(".s3").show();
    	}else if(mark == "4"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c4").hide();
    		$(".s4").show();
    	}else if(mark == "5"){
    		$(this).parents("div").find('span').removeClass("click");
    		$(this).parent().addClass("click");
    		$(".c5").hide();
    		$(".s5").show();
    	}
    })
    
    
});	

/**
 * 播放录音
 * @param url
 * @param phoneNumber
 * @param key
 */
function play(phoneNumber,key)
{
	if(key!=''&&phoneNumber!=''){
		alert(key);
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
	//url="http://www.w3school.com.cn/i/horse.ogg";
	//window.parent.AudioPlay.getInstance().play(url,phoneNumber,key);
}

































