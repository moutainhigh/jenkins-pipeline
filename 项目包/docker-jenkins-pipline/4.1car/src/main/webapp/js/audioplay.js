/**
 * 录音播放
 */
var AudioPlay=(function(){
	var instance;
	var titleTemplate="号码：{0}";
	var audioPlayTemplate='<audio controls="controls" autoplay="autoplay" height="100" width="100"><source src="{url}"/></audio>';
	var dialog=null;
	var callKey=null;
	function init(){  
        return { 
            play: function(url,phoneNumber,key){
            	if(callKey==key) return false;
            	else callKey=key;
            	if(dialog==null){
	            	dialog=art.dialog({
		    		    	title: titleTemplate.replace(/\{0\}/,phoneNumber||""),
		    		    	fixed:false,
		    		    	lock: false,
		    		    	top: "5px",
		    		    	content: audioPlayTemplate.replace(/\{url\}/,url),
		    		    	closeFn: function(){
		    		    		dialog=null;
		    		    		callKey=null;
		    		    		return false;
		    		    	}
		    		});
            	}else{
            		dialog.title(titleTemplate.replace(/\{0\}/,phoneNumber||""));
            		dialog.content(audioPlayTemplate.replace(/\{url\}/,url));
            	}
            }  
        }; 
    };
	return {  
        getInstance :function(){
            if (!instance) {
            	instance = init();
            }
            return instance;
        }
    };
})();