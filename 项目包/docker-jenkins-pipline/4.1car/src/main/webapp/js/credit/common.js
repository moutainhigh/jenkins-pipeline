
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
		 if(str != null && str.length > 0)
		 {
		    return true;
		 }
		 return false;
	}
}