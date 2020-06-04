var approve=function(){};
var loan = {};
/**
 * Ajax获取
 * @param url 
 * @param data
 * @param successCal 成功后执行回调函数
 * @param errorCal 失败后执行回调函数
 * @param params 回调函数需呀为的参数
 */
approve.getJsonForSync = function(url, data, type, successCal,errorCal,params) {
    $.ajax({
        type: type,
        url: url,
        dataType: "json",
        data: data,
        async: true,
        timeout: 10000,
        beforeSend: function(XMLHttpRequest){
        	waitingDialog.show();
        },
        complete: function(XMLHttpRequest, textStatus){
        	waitingDialog.hide();
        },
        success: function (obj) {
        	successCal(obj,params);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
        	if(errorCal != null && typeof errorCal == "function"){
        		errorCal(XMLHttpRequest,textStatus,errorThrown,params);
        	}
        }
    });
};
approve.setSelectValue=function(target,callBackFun){
	if(typeof(callBackFun)=='function') callBackFun(target);
};

var waitingDialog = waitingDialog || (function ($) {
	var $dialog = $(
		'<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="overflow-y:visible;">' +
		'<div class="modal-dialog" style="width:300px;">' +
		'<div class="modal-content">' +
			'<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
			'<div class="modal-body">' +
				'<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
			'</div>' +
		'</div></div></div>');
	return {
		show: function (message) {
			if (typeof message === 'undefined') {
				message = 'Loading';
			}

			$dialog.find('h3').text(message);
			var topBody = $(window.top.document.body);
			$dialog.appendTo(topBody);
			$dialog.modal();
		},
		hide: function () {
			$dialog.modal('hide');
		}
	};
})(jQuery);

$.fn.extend({          
	cloneSelect2:function(withDataAndEvents,  deepWithDataAndEvents) {
		  var $oldSelects2 = this.is('select') ? this : this.find('select');
		  //$oldSelects2.select2('destroy');
		  $oldSelects2.each(function(){
			  $(this).select2('destroy');
		  });

		  var $clonedEl = this.clone(withDataAndEvents,  deepWithDataAndEvents);
		  $oldSelects2.select2();
		  $clonedEl.is('select') ? $clonedEl.select2() : $clonedEl.find('select').select2();
		  return $clonedEl;         
	}
});

(function ($) {
    $.getUrlParam = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }
})(jQuery);
var Util={
	isEmpty : function(str){
		if (typeof(str)!="undefined" && str != null && str.length > 0) {
			return true;
		} 
		return false;
	}
};

loan.getstorelsit = function(inputText, hiddenText, isSingle) {
	var url = "/sys/org/selectOrgList";
	if (isSingle != "" & isSingle != "undefined" & isSingle != undefined) {
		url = url + "?isSingle=1"
	}
	$("#selectStoresBtn").click(function() {
		art.dialog.open(ctx + url, {
			title : "选择门店",
			width : 660,
			height : 450,
			lock : true,
			opacity : .1,
			id : 'artDialogWinId',
			yesBtn : '确定',
			noBtn : '取消',
			noFn : function() {
				top.art.dialog({id:'artDialogWinId'}).close();
			},
			yesFn : function() {
				var iframe = top.window;
				var windowIframe = null;
				for (var i = 0; i < iframe.length; i++) {
					if (iframe[i].document.URL.indexOf('selectOrgList') > 0) {
						windowIframe = iframe[i];
					}
				}
				var str = "";
				var strname = "";
				$("input[name='orgIds']:checked", windowIframe.document).each(function() {
					if ($(this).attr("checked")) {
						str += $(this).attr("id") + ",";
						strname += $(this).attr("sname") + ",";
					}
				});

				str = str.replace(/,$/g, "");
				strname = strname.replace(/,$/g, "");

				$("#" + inputText).val(strname);
				$("#" + hiddenText).val(str);
			}
		}, false);
	})
};
function  checkDate(data){
	if($(data).find("p[class='login-in']").html() != undefined){
		alert('未登录或登录超时。请重新登录，谢谢！');
		var win = top.window; 
		if (win.opener){
			win.opener.location = "${ctx}";
			window.close();
		}else{
			window.location = "${ctx}";
		}
		return;
	}
}
function removeValidateMsg(target){
	$(target).css("border","");
	$(target).css("border-radius","");
	$(target).next(".ketchup-error-container").remove();
}

(function(win) {
    var ArrayList = function() {
        this.datas = [];
        this.count = 0;
    };
    var proto = ArrayList.prototype;
    proto.setCount = function(value){
    	this.count = value;
    };
    proto.size = function() {
        return this.datas.length;
    };
    proto.isEmpty = function() {
        return this.size() === 0;
    };
    proto.contains = function(value) {
        return this.datas.indexOf(value) !== -1;
    };
    proto.indexOf = function(value) {
        for ( var index in this.datas) {
            if (this.datas[index] === value) {
                return index;
            }
        }
        return -1;
    };
    proto.lastIndexOf = function(value) {
        for ( var index = this.size(); index >= 0; index--) {
            if (this.datas[index] === value) {
                return index;
            }
        }
    };
    proto.toArray = function() {
        return this.datas;
    };
    proto.outOfBound = function(index) {
        return index < 0 || index > (this.size() - 1);
    };
    proto.get = function(index) {
        if (this.outOfBound(index)) {
            return null;
        }
        return this.datas[index];
    };
    proto.set = function(index, value) {
        this.datas[index] = value;
    };
    proto.add = function(value) {
        this.datas.push(value);
    };
    proto.addCall = function(fun) {
        this.datas.push("");
        if(this.size()==this.count) fun();
    };
    proto.insert = function(index, value) {
        if (this.outOfBound(index)) {
            return;
        }
        this.datas.splice(index, 0, value);
    };
    proto.remove = function(index) {
        if (this.outOfBound(index)) {
            return false;
        }
        this.datas.splice(index, 1);
        return true;
    };
    proto.removeValue = function(value) {
        if (this.contains(value)) {
            this.remove(this.indexOf(value));
            return true;
        }
        return false;
    };
    proto.clear = function() {
        this.datas.splice(0, this.size());
    };
    win.ArrayList = ArrayList;
})(window);
/**
 * 扩展date
 */
Date.prototype.Format = function(fmt)   
{
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}  