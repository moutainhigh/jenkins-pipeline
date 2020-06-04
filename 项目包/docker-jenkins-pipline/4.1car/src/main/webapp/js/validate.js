/**
 * 1  class需要添加
 * 不为空：required    身份证：card 手机号：mobile 座机号：simplePhone 手机和座机：Phone 只允许输入数字包含小数点：number 只允许输入数字不包含小数点：integer 邮政编码：zipCode 百分比：percent
 * 
 * 2  验证时需要判断 t 为form表单对象 比如$("#form") 、 $(t).parents("form") 等
 *   checkForm(t)
 */
/*
 * card
 * 验证身份证号方法 
 */
function isValidationIdCard(idcard){ 
	var ereg;
	var Errors=new Array("true","身份证号码位数不对!","身份证号码出生日期超出范围或含有非法字符!","身份证号码校验错误!","身份证地区非法!"); 
	var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"xinjiang",71:"台湾",81:"香港",82:"澳门",91:"国外"} 
	var idcard,Y,JYM; 
	var S,M; 
	var idcard_array = new Array(); 
	idcard_array = idcard.split(""); 
	if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4]; 
	switch(idcard.length){ 
		case 15: 
			if((parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){ 
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性 
			}else{ 
				ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性 
			} 
			if(ereg.test(idcard)){ 
				return Errors[0]; 
			}else{ 
				return Errors[2]; 
			}
			break; 
		case 18: 
			if( parseInt(idcard.substr(6,4)) % 4 == 0 || ( parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){ 
				ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式 
			}else{ 
				ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式 
			} 
			if(ereg.test(idcard)){ 
				S =(parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7 + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9 + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10 + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5 + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8 + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4 + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2 + parseInt(idcard_array[7]) * 1 + parseInt(idcard_array[8]) * 6 + parseInt(idcard_array[9]) * 3 ; 
				Y = S % 11; 
				M = "F"; 
				JYM = "10X98765432"; 
				M = JYM.substr(Y,1); 
				if(M == idcard_array[17]){
					return Errors[0]; 
				}else{
					return Errors[3]; 
				}
			}else{
				return Errors[2]; 
			}
			break; 
		default: 
			return Errors[1]; 
	} 
} 


// 判断字符串是否为空，或者空格
function isEmpty(str) {
    var reNullStr = /^\s*$/g;

    return (str == null || str.match(reNullStr));
}

// 判断字符串是不是非空，与isEmpty相反
function isNotEmpty(str) {
    return !isEmpty(str);
}

// 去掉字符串两端的空格
function trimString(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}

/*
 * 验证数字
 * len 为数字长度
 */
function validateNum(str, len) {
     var pattern = "^\\d{" + len + "}$";
     var re = new RegExp(pattern, "g");
     return str.match(re);
 }
 

 /*
  * 验证Email
  */
 function validateEmail(str) {
     var pattern = "^\\w+@\\w+(\\.\\w+)+$";
     var re = new RegExp(pattern, "g");
     return str.match(re);
 }
 /**
 验证是否为空
 **/
function checkSubject(str){
	if(trimString(str)==""){
		return true;
	} else {
		return false;
	}
}
 
/**
 * mobile
 * 验证手机号码 匹配：11位手机号码
 */
function validateAllMobile(str){
	var length = str.length;
	var mobile = /^(13|14|15|17|18)[0-9]{9}$/;
	if(mobile.test(str)){
		return "true";
	}				 
	return  "请正确填写号码！";
}

/**
 * simplePhone
 * 固定电话码验证
 */
function validateAllTel(str){
	var tel = /^0\d{2,3}-\d{7,8}$/;
	if(tel.test(str)){
		return "true";
	}
	return  "请正确填写号码！";
}

/**
 * 邮政编码验证
 */
function validateZipCode(str){
	var tel = /^[0-9]{6}$/;
	if(tel.test(str)){
		return "true";
	}
	return "请正确填写您的邮政编码";
}

/**
 * 百分比验证
 */
function validatePercent(str){
	var pattern = /^([1-9]{1}[0-9]{0,1}|0)(\.\d{1,2}){0,1}$/;  
	if(pattern.test(str)){
		return "true";
	}
	return "请正确填写您的百分比";
}

/**
 * Phone
 * 验证手机号和座机号
 */
function validateAllPhone(str){
	var isPhone = /^0\d{2,3}-\d{7,8}$/;
	var isMob=/^(13|14|15|17|18)[0-9]{9}$/;
	if(isMob.test(str)||isPhone.test(str)){
		return "true";
	}
	return  "请正确填写号码！";
}

function validateAllTelNumber(str){
	var isPhone = /^(0[1-9][0-9]{1,2}-)?[0-9]{3,8}$/;
	var isMob=/^(13|14|15|17|18)[0-9]{9}$/;
	if(isMob.test(str)||isPhone.test(str)){
		return "true";
	}
	return  "请正确填写号码！";
}

/**
 * number
 * 包含小数点
 */
function validateNumber(str){
	var newstr = str.replace(",","")
	if(newstr >= 0){
		var tel = /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/;
		if(tel.test(str)){
			return "true";
		}
	}
	return  "请输入正确的数字";
}
	
/**
 * integer 不包含小数点
 * @param str
 * @returns {String}
 */	
function validateInteger(str){
	var tel = /^[1-9]\d*$/; 
	if(tel.test(str)){
		return "true";
	}
	return  "请正确输入正整数";
} 

/**
 * 企业注册资本 包括0和正整数
 * @param str
 * @returns {String}
 */	
function validateJyzmregcap(str){
	var tel = /^(0|([1-9]\d*))$/; 
	if(tel.test(str)){
		return "true";
	}
	return  "请正确输入整数";
} 
/**
 * 验证最近24个月缴交状态，只能输入（＊／ｎ１２３４５６７）
 * @param str
 * @returns {String}
 */	
function validatePayStatus(str){
	var ss = /^[1-7\*n\/]$/;
	if(ss.test(str)){
		return "true";
	}
	return "请正确输入(1-7/*n)";
}
	
/**
 * 校验银行卡号
 * @param str
 * @returns {String}
 */	
function validataBankCard(str){
	var tel = /^\d*$/; 
	if(tel.test(str)){
		return "true";
	}
	return "请正确输入银行卡号"
}

/**
 * 字母和数字
 * @param str
 * @returns {String}
 */	
function validataNumAndZimu(str){
	var tel = /^[0-9a-zA-Z]*$/g; 
	if(tel.test(str)){
		return "true";
	}
	return "请正确输入"
}
/**
 * 字母和数字18位
 * @param str
 * @returns {String}
 */	
function validataNumAndZimu18(str){
	var tel = /^[0-9a-zA-Z]{18}$/; 
	if(tel.test(str)){
		return "true";
	}
	return "请正确输入"
}
/***
 * 校验组织机构码
 * @param str
 * @returns {String}
 */
function validataOrg(str){
	var tel =/^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/;
	if(tel.test(str)){
		return "true";
	}
	return "请正确输入"
}

/***
 * 校验申请额度
 * @param str
 * @returns {String}
 */
function validataLimitCheck(str){
	
	if(str!=''){
		var limitLower = $('#limitLower').val();
		var limitUpper = $('#limitUpper').val();
		if(limitLower!='' && limitLower!=null){
			limitLower = parseFloat(limitLower);
		}
		if(limitUpper!='' && limitUpper!=null){
			limitUpper = parseFloat(limitUpper);
		}
		if(parseFloat(str)<limitLower || parseFloat(str)>limitUpper){
			return "申请额度不在有效的额度限制范围内";
		}
    }
	
	return "true";
}

/**
 * 
 * @param t  form 对象
 * @returns {Boolean}
 */
var flag ;
function checkForm(t){
	if( !$(t[0]).is("form")){
		 return false;
	}
	flag =true;
	t.find("input[type='text'],select,textarea").each(function(){
		if(($(this).prop("class").toLowerCase().indexOf("required")) != -1){
			if ("" == $(this).val().trim()) { //判断元素对象的value值  
				addMyValidateCss($(this));
				flag = false;
			}else{
				removeMyValidateCss($(this))
				checkValidate( $(this));
			}
		}else{
			if("" != $(this).val()){
				checkValidate( $(this));
			}
		}
	});
	return flag;
}
function checkNoForm(target){
	flag =true;
	target.find("input[type='text'],select,textarea").each(function(){
		if(($(this).prop("class").toLowerCase().indexOf("required")) != -1){
			if ("" == $(this).val().trim()) { //判断元素对象的value值  
				addMyValidateCss($(this));
				flag = false;
			}else{
				removeMyValidateCss($(this))
				checkValidate( $(this));
			}
		}else{
			if("" != $(this).val()){
				checkValidate( $(this));
			}
		}
	});
	return flag;
}
 
// 检查表单项目
function checkValidate(t){
	if((t.prop("class").indexOf("card")) != -1){
		if(isValidationIdCard(t.val())!='true'){
			addMyValidateTip(t,isValidationIdCard(t.val()));
			flag = false;
			addMyValidateCss(t);
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("mobile")) != -1){
		if(validateAllMobile(t.val())!='true'){
			addMyValidateTip(t,validateAllMobile(t.val()))
			flag = false;
			addMyValidateCss(t);
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("simplephone")) != -1){
		if(validateAllTel(t.val())!='true'){
			addMyValidateTip(t,validateAllTel(t.val()));
			flag = false;
			addMyValidateCss(t);
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("phone")) != -1){
		if(validateAllPhone(t.val())!='true'){
			addMyValidateTip(t,validateAllPhone(t.val()));
			flag = false;
			addMyValidateCss(t);
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("number")) != -1){
		if(validateNumber(t.val())!='true'){
			addMyValidateTip(t,validateNumber(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("integer")) != -1){
		if(validateInteger(t.val())!='true'){
			addMyValidateTip(t,validateInteger(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("jyzmregcap")) != -1){
		if(validateJyzmregcap(t.val())!='true'){
			addMyValidateTip(t,validateJyzmregcap(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("zipcode")) != -1){
		if(validateZipCode(t.val())!='true'){
			addMyValidateTip(t,validateZipCode(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("percent")) != -1){
		if(validatePercent(t.val())!='true'){
			addMyValidateTip(t,validatePercent (t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("paystatus")) != -1){
		if(validatePayStatus(t.val())!='true'){
			addMyValidateTip(t,validatePayStatus(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if( (t.prop("class").toLowerCase().indexOf("banknum")) != -1){
		if(validataBankCard(t.val())!='true'){
			addMyValidateTip(t,validataBankCard(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("aandnum")) != -1){
		if(validataNumAndZimu(t.val())!='true'){
			addMyValidateTip(t,validataNumAndZimu(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("regnum")) != -1){
		if(validataNumAndZimu18(t.val())!='true'){
			addMyValidateTip(t,validataNumAndZimu18(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("orgnum")) != -1){
		if(validataOrg(t.val())!='true'){
			addMyValidateTip(t,validataOrg(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
	if((t.prop("class").toLowerCase().indexOf("limitcheck")) != -1){
		if(validataLimitCheck(t.val())!='true'){
			addMyValidateTip(t,validataLimitCheck(t.val()));
			flag = false;
			addMyValidateCss(t);
			t.prop("value","");
			return;
		}
	}
}
 
$(function(){
	$("input[type='text']").focus(function(){
		$(this).css("border","");
		$(this).css("border-radius","");
		$(this).next(".ketchup-error-container").remove();
	});
});

var timer = null;
function addMyValidateCss(t){
	 if(t[0].tagName=="SELECT"){
		  t.prev("div").find("a[class=select2-choice]").css("border","1px solid #FF4500");
		  t.css("border","1px solid #FF4500");
	      t.css("border-radius","4px");
	  }else{
		  t.css("border","1px solid #FF4500");
	      t.css("border-radius","4px");
	  }
}
function removeMyValidateCss(t){
	if(t[0].tagName=="SELECT"){
		t.prev("div").find("a[class=select2-choice]").css("border","1px solid #AAA");
	}
	t.css("border","");
	t.css("border-radius",""); 
}

function addMyValidateTip(t,val){
	  var fOffset = t.offset();
	  var top = t.position().top- t.height()-30;//fOffset.top ;//- t.height()-30;
	  var left =t.position().left;//+ t.width() - 30;// fOffset.left ;//+ t.width() - 10;
	  var html="<div  style='top: "+top+"px; display: block; left: "+left+"px;' class='ketchup-error-container'><ol><li>"+val+"</li></ol><span></span></div>";
	  //t.parent("td").append(html);
	
	  if(t.next(".ketchup-error-container").length==0){
		  t.after(html);  
	  }else{
		  t.next(".ketchup-error-container").css("display","")
	  }
}

/**
 * 针对没有form表单的非空验证
 * @param arr
 * @returns {Boolean}
 */
function myValidEmpty(arr){
	var fg = true;
	for(var i=0;i<arr.length;i++){
	 	if ("" == arr[i].val().trim()) { //判断元素对象的value值  
	 		addMyValidateCss(arr[i]);
	 		fg = false ;
	 	}else{  
	 		removeMyValidateCss(arr[i])
	 	}
	} 
	return fg; 
}

function myValidTelNumber(t){
	var fg = true;
	if(validateAllTelNumber(t.val())!='true'){
		addMyValidateTip(t,validateAllTelNumber(t.val()));
		fg = false;
		addMyValidateCss(t);
	}
	return fg;
}

function myValidPhone(t){
	var fg = true;
	if(validateAllPhone(t.val())!='true'){
		addMyValidateTip(t,validateAllPhone(t.val()));
		fg = false;
		addMyValidateCss(t);
	}
	return fg;
}

function myValidSimplePhone(t){
	var fg = true;
	if(validateAllTel(t.val().trim())!='true'){
	   		addMyValidateTip(t,validateAllTel(t.val()));
	   	    fg = false;
	   		addMyValidateCss(t);
	   		}
	return fg;
}

var px = 0;
// 获取定位
function getValidErorTop(t){
	if( !$(t[0]).is("form")){
		return 0;
	}
	t.find("input[type='text'],select,textarea").each(function(){
	if(($(this).prop("class").toLowerCase().indexOf("required")) != -1){
		if ("" == $(this).val().trim()) { //判断元素对象的value值  
			px =  $(this).position().top;
		}else{  
			px =  getValidErorTopNei( $(this));
		} 
		} else {
			if("" != $(this).val()){
				px = getValidErorTopNei( $(this));
			}
		}
    });
	return px;
}

function getValidErorTopNei(t){
	 if((t.prop("class").indexOf("card")) != -1){
	   		if(isValidationIdCard(t.val())!='true'){
	   			 return t.position().top;
	   		}
	   	 }else if((t.prop("class").toLowerCase().indexOf("mobile")) != -1){
	   		 
	   		if(validateAllMobile(t.val())!='true'){
	   			return t.position().top;
	   		}
	   	 }else if((t.prop("class").toLowerCase().indexOf("simplephone")) != -1){
	   		 
	   	   		if(validateAllTel(t.val())!='true'){
	   	   		return t.position().top;
	   	   		}
	   	 }else if((t.prop("class").toLowerCase().indexOf("phone")) != -1){
	   		 
	   	   		if(validateAllPhone(t.val())!='true'){
	   	   		return t.position().top;
	   	   		}
	   	 }else if((t.prop("class").toLowerCase().indexOf("number")) != -1){
	   		 
	   		if(validateNumber(t.val())!='true'){
	   			return t.position().top;
	   		}
	   	 }else if((t.prop("class").toLowerCase().indexOf("integer")) != -1){
	   		   
		   		if(validateInteger(t.val())!='true'){
		   			return t.position().top;
		   		}
		 }
}
/**
 * 短暂提示
 * @param	{String}	提示内容
 * @param	{Number}	显示时间 (默认1.5秒)
 */
/*artDialog.tips = function (content, time) {
    return artDialog({
        id: 'Tips',
        title: false,
        cancel: false,
        fixed: true,
        lock: true
    })
    .content('<div style="padding: 0 1em;">' + content + '</div>')
    .time(time || 1);
};*/


/**
 *金额格式化 9,999,999,999.00
 *@params money {Number or String} 金额
 *@params digit {Number} 小数点的位数，不够补0
 *@returns {String} 格式化后的金额
 */
function formatMoney(money, digit){
	var tpMoney = '0.00';
	if(undefined != money){
		tpMoney = money;
	}
	tpMoney = new Number(tpMoney);
	if(isNaN(tpMoney)){
		return '0.00';
	}
	tpMoney = tpMoney.toFixed(digit) + '';
	var re = /^(-?\d+)(\d{3})(\.?\d*)/;
	while(re.test(tpMoney)){
		tpMoney = tpMoney.replace(re, "$1,$2$3");
	}
	return tpMoney;
}

//两个浮点数求和
function accAdd(num1,num2){
	var r1,r2,m;
	try{
		r1 = num1.toString().split('.')[1].length;
	}catch(e){
		r1 = 0;
	}
	try{
		r2=num2.toString().split(".")[1].length;
	}catch(e){
		r2=0;
	}
	m=Math.pow(10,Math.max(r1,r2));
	// return (num1*m+num2*m)/m;
	return Math.round(num1*m+num2*m)/m;
}

//两个浮点数相减
function accSub(num1,num2){
	var r1,r2,m;
	try{
		r1 = num1.toString().split('.')[1].length;
	}catch(e){
		r1 = 0;
	}
	try{
		r2=num2.toString().split(".")[1].length;
	}catch(e){
		r2=0;
	}
	m=Math.pow(10,Math.max(r1,r2));
	n=(r1>=r2)?r1:r2;
	return (Math.round(num1*m-num2*m)/m).toFixed(n);
}

//两个浮点数相除
function accDiv(num1,num2){
	var t1,t2,r1,r2;
	try{
		t1 = num1.toString().split('.')[1].length;
	}catch(e){
		t1 = 0;
	}
	try{
		t2=num2.toString().split(".")[1].length;
	}catch(e){
		t2=0;
	}
	r1=Number(num1.toString().replace(".",""));
	r2=Number(num2.toString().replace(".",""));
	return (r1/r2)*Math.pow(10,t2-t1);
}

//两个浮点数相乘
function accMul(num1,num2){
	var m=0,s1=num1.toString(),s2=num2.toString();
	try{m+=s1.split(".")[1].length}catch(e){};
	try{m+=s2.split(".")[1].length}catch(e){};
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
}


