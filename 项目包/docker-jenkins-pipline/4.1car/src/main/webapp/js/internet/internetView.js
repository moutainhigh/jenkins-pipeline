function play(phoneNumber,key)
{
	if(key!=''&&phoneNumber!=''){
		$.ajax({
			type:"POST",
			url:ctx+"/verify/outside/getRecord",
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
