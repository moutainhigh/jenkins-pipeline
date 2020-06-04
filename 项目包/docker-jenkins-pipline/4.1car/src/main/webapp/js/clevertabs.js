$.fn.cleverTabs = function (options) {
    var self = this;
    var tabs = new CleverTabs(self, options);
  
    return tabs;
};

//定义CleverTabs对象
function CleverTabs(self, options) {
    var self = self;
    this.hashtable = new Array();
    this.options = $.extend({
        contentCss: {
            'height': '500px'
        },
        titleHeight: 26,
        //Tab用于控制的头模板
        tabHeaderTemplate: '<li id="cleverTabHeaderItem-#{id}" style="width: 110px; opacity: 1; background-color: rgb(255, 255, 255);" class="jericho_tabs" dataLink="#{dataLink}"><div class="tab_left"  style="width:105px"><div class="tab_text" title="#{title}"  style="width:105px"  >#{label}</div></div><div class="tab_right">&nbsp;</div></li>',
        //Tab用于显示的Panel模板
        tabPanelTemplate: '<div id="cleverTabPanelItem-#{id}"></div>',
      //Tab用于显示的Panel模板
        tabIframeTemplate: '<iframe frameBorder="0" style="width: 100%; display: inline; height: #{height}px;" src="#{src}"></iframe>',
        //Tab唯一id生成器
        uidGenerator: function () { return new Date().getTime(); }

    }, options || {});

    this.wrapper = self;
    var el = self.find('ol,ul').eq(0);
    this.element = el;
    this.panelContainer = this.options.panelContainer;
    this.panelContainer.height($("#main_content").height() - 26);
};

//添加Tab
CleverTabs.prototype.add = function (options) {
	
    var self = this;
    var uid = self.options.uidGenerator(self);
    var options = $.extend({
        id: uid,
        url: '#',
        label: 'New Tab',
        load: false,
        selected: false,
        closeRefresh: null,
        closeActivate: null,
        callback: function () { }
    }, options || {});

    //验证指定Url的Tab是否已经开启，如果开启则激活它
    var exsitsTab = self.getTabByUrl(options.url);
    if (exsitsTab) {
        if (!exsitsTab.activate()) {
            return false;
        }
    }

    //生成Tab头
    var tabHeader = $(self.options.tabHeaderTemplate
    .replace(/#\{id\}/g, options.id)
    .replace(/#\{dataLink\}/g, options.url)
    .replace(/#\{title\}/g, options.label)
    .replace(/#\{label\}/g, function () {
        //如果Tab的Label大于7个字符，则强制使其为前7个字符加'...'
        if (options.label.length > 7) {
            return options.label.substring(0, 7) + '...';
        }
        return options.label;
    } ()));

    //Tab头绑定click事件
    tabHeader.bind("click", function () {
    	
        if (!$(this).hasClass('tab_selected')) {
        	
            tab.activate();
           
            if($("#jerichotab_contentholder>#cleverTabPanelItem-"+tab.id).find("iframe").size() == 0)
            {
            	
            	tab.showLoader();
            	
            	var tabIframe = $(self.options.tabIframeTemplate
        				.replace(/#\{height\}/g, $("#main_content").height() - 26)
        				.replace(/#\{src\}/g, tab.url));
            	
            	self.panelContainer.find("#cleverTabPanelItem-"+tab.id).append(tabIframe);
            
            	tab.addEvent("load",function(){
            		tab.removeLoader();
            	});
            }
        }
    });
    
     
    	   self.element.append(tabHeader);
       
    
    	//self.element.append(tabHeader);
    
    var panel = $(self.options.tabPanelTemplate.replace(/#\{id\}/g, options.id));
	self.panelContainer.append(panel);
	

    self.hashtable[options.id] = { 'callback': options.callback, 'closerefresh': options.closeRefresh, 'closeActivate': options.closeActivate };
  
    var tab = new CleverTab(self, options.id);

    if(options.selected)
    {
    	tab.activate();
    	tab.showLoader();
    	var tabIframe = $(self.options.tabIframeTemplate
				.replace(/#\{height\}/g, $("#main_content").height() - 26)
				.replace(/#\{src\}/g, options.url));
    	self.panelContainer.find("#cleverTabPanelItem-"+options.id).append(tabIframe);
    	tab.addEvent("load",function(){
    		tab.removeLoader();
    	});
    }
    else
    	tab.deactivate();

    return tab;
};

//获取当前选中的Tab的唯一Id
CleverTabs.prototype.getCurrentUniqueId = function () {
    var self = this;
    if (self.element.find(' > li').size() > 0) {
        var current = self.element.find('li.tab_selected');
        return current.size() > 0 ? CleverTab.getUniqueByHeaderId(current.attr('id')) : null;
    } else {
        return null;
    }
}
//获取当前选中的Tab
CleverTabs.prototype.getCurrentTab = function () {
    var self = this;
    var uid = self.getCurrentUniqueId();
    return uid ? new CleverTab(self, self.getCurrentUniqueId()) : null;
}
//获取指定Url的Tab
CleverTabs.prototype.getTabByUrl = function (url) {
    if (!url) {
        return null;
    }
    var self = this;
    var frames = self.panelContainer.find('div[id^="cleverTabPanelItem-"]>iframe');
    var tab;
    for (i = 0; i < frames.size(); i++) {
        var frame = $(frames[i]);
        var src = frame.attr('src');
        if (src.indexOf('clever_tabs_time_stamp=') > 0) {
            src = src.substring(0, src.indexOf('clever_tabs_time_stamp=') - 1);
        }
        if (src.toLowerCase() == url.toLowerCase()) {
            tab = new CleverTab(self, CleverTab.getUniqueByPanelId(frame.parent('div:first').attr('id')));
        }
    }
    return tab;
}
//清除所有Tab页面
CleverTabs.prototype.clear = function () {
    var self = this;
    var lis = self.element.find('>li');
    var hasLock = self.element.find('span.ui-icon-locked').size() > 0;
    for (i = self.lockOnlyOne && !hasLock ? 1 : 0; i < lis.size(); i++) {
        var id = CleverTab.getUniqueByHeaderId(lis.eq(i).attr('id'));
        var tab = new CleverTab(self, id);
        tab.kill();
    }
}
//定义Tab页面类
function CleverTab(tabs, id) {
    //Tab控件
    this.tabs = tabs;
    //Tab页面Id
    this.id = id
    //Tab页面头
    this.header = this.tabs.element.find('li#' + CleverTab.getFullHeaderId(id));
    this.headerId = this.header.attr('id');
    //Tab页面有于显示内容的面板
    this.panel = this.tabs.panelContainer.find('div#' + CleverTab.getFullPanelId(id));
    //Tab页面id
    this.panelId = this.panel.attr('id');
    //Tab标题
    this.label = (this.header ? this.header.find('a:first').attr('title') : null);
    //Tab中iframe的url
    this.url = this.tabs.element.find('li#' + CleverTab.getFullHeaderId(id)).attr("dataLink");
    //Tab关闭时的回调函数
    this.callback = this.tabs.hashtable[id].callback;
    //当关闭Tab时需要刷新的Tab的url
    this.closeRefresh = this.tabs.hashtable[id].closeRefresh;
    //当关闭当前Tab时需要激活的Tab的url
   this.closeActivate = this.tabs.hashtable[id].closeActivate;
};
//使Tab页面处于未激活状态，不建议在代码中使用
CleverTab.prototype.deactivate = function () {
    var self = this;
    self.header.removeClass('tab_selected');
    self.header.addClass('tab_unselect');
    self.panel.addClass('ui-tabs-hide');

};
CleverTab.prototype.addEvent = function(e, h) {
    var target = $("#"+this.panelId).find("iframe").get(0);
    if (target.addEventListener) {
        target.addEventListener(e, h, false);
    } else if (target.attachEvent) {
        target.attachEvent('on' + e, h);
    }
};
CleverTab.prototype.dataLoader = function(){
	
};
CleverTab.prototype.removeLoader = function(){
	$('#jerichotab_contentholder>.righttag').css({ display: 'none' });
};
CleverTab.prototype.showLoader = function(){
	if ($('#jerichotab_contentholder>.righttag').length == 0){
        $('<div class="righttag" style="width:auto">正在加载...</div>').appendTo($(this));
	}else{
        $('#jerichotab_contentholder>.righttag').css({ display: 'block' });
	}
};
//使Tab页面处于激活状态
CleverTab.prototype.activate = function () {
	
    var self = this;
   
    if(self.tabs.getCurrentUniqueId()!='zhengxin'&&self.tabs.getCurrentUniqueId()!='ziliaosh'&&
    		self.tabs.getCurrentUniqueId()!='dianHuazh'&&
    		self.tabs.getCurrentUniqueId()!='waifanghc'&&self.tabs.getCurrentUniqueId()!='neiwangsh'&&self.tabs.getCurrentUniqueId()!='waiwangsh'){
    	
    
    var currentTab = self.tabs.getCurrentTab();
   
    if (currentTab) {
        if (currentTab.id == self.id) {
            return false;
        }

        currentTab.deactivate();
    }
    }else{
    	 $("#neiwangsh").attr("class","jericho_tabs tab_unselect");
   	     $("#waiwangsh").attr("class","jericho_tabs tab_unselect");
    	 $("#zhengxin").attr("class","jericho_tabs tab_unselect");
    	 $("#ziliaosh").attr("class","jericho_tabs tab_unselect");
    	 $("#dianHuazh").attr("class","jericho_tabs tab_unselect");
    	 $("#waifanghc").attr("class","jericho_tabs tab_unselect");
    	 $("#iframeContent").attr('class','ui-tabs-hide');
    	 $("#nwsh_iframe").attr('class','ui-tabs-hide');
    	 $("#wwsh_iframe").attr('class','ui-tabs-hide');
    }
    self.header.addClass('tab_selected');
    self.header.removeClass('tab_unselect');
    
    self.panel.removeClass('ui-tabs-hide');

}
//获取该Tab之前的Tab
CleverTab.prototype.prevTab = function () {
    var self = this;
    var prev = self.header.prev();
    if (prev.size() > 0) {
        var headerId = prev.attr('id');
        return new CleverTab(tabs, CleverTab.getUniqueByHeaderId(headerId));
    } else {
        return null;
    }
}
//获取该Tab之后的Tab
CleverTab.prototype.nextTab = function () {
    var self = this;
    var next = self.header.next();
    if (next.size() > 0) {
        var headerId = next.attr('id');
        return new CleverTab(tabs, CleverTab.getUniqueByHeaderId(headerId));
    } else {
        return null;
    }
}
//移移该Tab
CleverTab.prototype.kill = function () {
    var self = this;

    var nextTab = self.nextTab();
    if (!nextTab) {
        nextTab = self.prevTab();
    }
    
    var callback = self.callback;
    var refresh = self.closeRefresh;
    var activate = self.closeActivate;
    self.header.remove();
    self.panel.remove();
    delete self.tabs.hashtable[self.id];

    var refreshTab = self.tabs.getTabByUrl(refresh);
    if (refreshTab) {
        refreshTab.refresh();
    }
    var activateTab = self.tabs.getTabByUrl(activate);
    if (activateTab) {
        activateTab.activate();
    } else {
        if (nextTab) {
            nextTab.activate();
        }
    }
    callback();
}

//移移该Tab
CleverTab.prototype.killTab = function () {
    var self = this;
    self.header.remove();
    self.panel.remove();
    delete self.tabs.hashtable[self.id];
}




//刷新该Tab的iframe中的内容
CleverTab.prototype.refresh = function () {
    var self = this;
    if (self.url.indexOf('clever_tabs_time_stamp=') > 0) {
        self.url = self.url.substring(0, self.url.indexOf('clever_tabs_time_stamp=') - 1);
    }
    var newUrl = self.url.concat(self.url.indexOf('?') < 0 ? '?' : '&').concat('clever_tabs_time_stamp=').concat(new Date().getTime());
    self.panel.find(' > iframe:first').attr('src', newUrl);
}

//获取指定Tab头完全id的唯一id
CleverTab.getUniqueByHeaderId = function (id) {
    return id.replace('cleverTabHeaderItem-', '');
}
//获取指定Tab页面完全id的唯一id
CleverTab.getUniqueByPanelId = function (id) {
    return id.replace('cleverTabPanelItem-', '');
}
//获取指定Tab头唯一id的完全id
CleverTab.getFullHeaderId = function (uid) {
    return 'cleverTabHeaderItem-'.concat(uid);
}
//获取指定Tab页面唯一id的完全id
CleverTab.getFullPanelId = function (uid) {
    return 'cleverTabPanelItem-'.concat(uid);
}