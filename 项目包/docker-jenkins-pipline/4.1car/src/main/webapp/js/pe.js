(function(){ 
	var doc=document, 
	ua = navigator.userAgent.toLowerCase(), 
	check = function(r){
		return r.test(ua);
	}, 
	isOpera = check(/opera/), 
	isChrome = check(/chrome/), 
	isWebKit = check(/webkit/), 
	isSafari = !isChrome && check(/safari/), 
	isSafari2 = isSafari && check(/applewebkit\/4/), 
	isSafari3 = isSafari && check(/version\/3/), 
	isSafari4 = isSafari && check(/version\/4/), 
	isIE = !isOpera && check(/msie/), 
	isIE7 = isIE && check(/msie 7/), 
	isIE8 = isIE && check(/msie 8/), 
	isIE9 = isIE && check(/msie 9/), 
	isIE10 = isIE && check(/msie 10/), 
	isIE11 = isIE && check(/msie 11/), 
	isIE6 = isIE && !isIE7 && !isIE8 && !isIE9 && !isIE10 && !isIE11, isGecko = !isWebKit && check(/gecko/), isGecko2 = isGecko && check(/rv:1\.8/), isGecko3 = isGecko && check(/rv:1\.9/); 
	function preventKey(e) { 
		var ev = e || window.event,
		obj = ev.target || ev.srcElement,
		t = obj.type || obj.getAttribute('type'),
		readonly = obj.readOnly||obj.getAttribute('readonly'), 
		code = ev.keyCode||ev.which||ev.charCode,
		charcode = String.fromCharCode(code).toLowerCase();
		if ( (code == 8 && t != "password" && t != "text" && t != "textarea")||(readonly&&(t == "password" || t == "text" || t == "textarea"))) 
		{return false; } 
		if (((isOpera || isGecko)?ev.which==0:true)&&(code == 116 || code == 122|| code == 123 || (ev.shiftKey && code == 121))||(((isOpera || isGecko)?ev.which!=0:true)&&ev.ctrlKey && (charcode == 'a' || charcode == 's'))) {
			if (isIE) ev.keyCode = 0; ev.returnValue = false; 
			return false; 
			} 
		}; 
		if (isOpera || isGecko) doc.onkeypress = preventKey; 
		else if (isIE || isChrome || isSafari) doc.onkeydown = preventKey; 
		if(isIE)document.onselectstart=function(){return false;}; 
		doc.oncontextmenu = function(){ 
			if(window.event){ 
				window.event.cancelBubble = true; 
				window.event.returnValue=false; } 
			return false; }; 
			
		try{window.history.forward(1);}catch(e){} 
		$(document).keydown(function(event){
			var ev = event || window.event;
			 //屏蔽F5刷新键
	        if(ev.keyCode == '116'){
	               return false; 
	        }
			 //屏蔽F12
	        if(ev.keyCode == '123'){
	               return false; 
	        }
	        //屏蔽ctrl+R 
	        if((ev.ctrlKey) && (ev.keyCode=='82')){
	              return false;
	        }
	        //屏蔽ctrl+C
	        if((ev.ctrlKey) && (ev.keyCode=='67')){
	              return false;
	        }
	        
		});
		$(document).click(function(event){// 屏蔽点击
			var ev = event || window.event;
			var obj = ev.target || ev.srcElement;
			var t = obj.type || obj.getAttribute('type');
			if((event.button == 0 || event.button == 1 || event.button == 2 || event.button == 3 || event.button == 4|| event.button == 5|| event.button == 6|| event.button == 7) && t != "text" && t != "button" && t != "submit"){
	       	 return false;
	       }
		});
		$(document).mousedown(function(event){// 屏蔽点击
			var ev = event || window.event;
			var obj = ev.target || ev.srcElement;
			var t = obj.type || obj.getAttribute('type');
			if((event.button == 0 || event.button == 1 || event.button == 2 || event.button == 3 || event.button == 4|| event.button == 5|| event.button == 6|| event.button == 7) && t != "text" && t != "button" && t != "submit"){
	       	 return false;
	       }
		});
		$(document).dblclick(function(event){// 屏蔽双击
			if(event.button == 0 || event.button == 1 || event.button == 2 || event.button == 3 || event.button == 4|| event.button == 5|| event.button == 6|| event.button == 7){
	       	 return false;
	       }
		});
})(); 