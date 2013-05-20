/* runonload */
function runOnLoad(f) {
    if (runOnLoad.loaded) f();
    else runOnLoad.funcs.push(f);
}
runOnLoad.funcs = [];
runOnLoad.loaded = false;
runOnLoad.run = function() {
    if (runOnLoad.loaded) return;

    for(var i = 0; i < runOnLoad.funcs.length; i++) {
        try { runOnLoad.funcs[i](); }
        catch(e) { }
    }

    runOnLoad.loaded = true;
    delete runOnLoad.funcs;
    delete runOnLoad.run;
};
if (window.addEventListener)
	window.addEventListener("load", runOnLoad.run, false);
else if (window.attachEvent) window.attachEvent("onload", runOnLoad.run);
else window.onload = runOnLoad.run;

/* Chinese initialisation for the jQuery UI date picker plugin. */
/* Written by Cloudream (cloudream@gmail.com). */
jQuery(function($){
	$.datepicker.regional['zh-CN'] = {
		closeText: '关闭',
		prevText: '&#x3c;上月',
		nextText: '下月&#x3e;',
		currentText: '今天',
		monthNames: ['一月','二月','三月','四月','五月','六月',
		'七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort: ['一','二','三','四','五','六',
		'七','八','九','十','十一','十二'],
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
		dayNamesMin: ['日','一','二','三','四','五','六'],
		weekHeader: '周',
		dateFormat: 'yy-mm-dd',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: true,
		yearSuffix: '年'};
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
});

/* jquery.ui.tabs.js  refresh and close able start*/
(function() {
	var c = $.ui.tabs.prototype._tabify;
	$.extend($.ui.tabs.prototype, {
		_tabify: function() {
			var a = this;
			c.apply(this, arguments);
			a.options.refreshable === true && this.lis.filter(function() {
				return $("span.ui-icon-refresh", this).length === 0
			}).each(function() {
				$(this).append('<span class="ui-tabs-refresh ui-icon ui-icon-refresh" title="刷新"></span>').find("span:last").hover(function() {
					$(this).css("cursor", "pointer")
				},
				function() {
					$(this).css("cursor", "default")
				}).click(function() {
					var b = a.lis.index($(this).parent());
					if (b > -1) {
						if (false === a._trigger("refreshableClick", null, a._ui($(a.lis[b]).find("a")[0], a.panels[b]))) return;
						var oldcache;
						if( a.options.ajaxOptions.cache!=null || a.options.ajaxOptions.cache!=undefined ){
							oldcache = a.options.ajaxOptions.cache
						}else{
							oldcache = null
						}
						a.options.ajaxOptions.cache=false
						a.select(b)
						a.load(b)
						if(oldcache != null){
							a.options.ajaxOptions.cache=oldcache
						}else{
							a.options.ajaxOptions.cache=true
						}
						
					}
					return false
				}).end()
			})
		}
	})
})(jQuery);

(function() {
	var c = $.ui.tabs.prototype._tabify;
	$.extend($.ui.tabs.prototype, {
		_tabify: function() {
			var a = this;
			c.apply(this, arguments);
			a.options.closable === true && this.lis.filter(function() {
				return $("span.ui-icon-circle-close", this).length === 0
			}).each(function() {
				$(this).append('<span class="ui-tabs-close ui-icon ui-icon-circle-close" title="关闭"></span>').find("span:last").hover(function() {
					$(this).css("cursor", "pointer")
				},
				function() {
					$(this).css("cursor", "default")
				}).click(function() {
					var b = a.lis.index($(this).parent());
					if (b > -1) {
						if (false === a._trigger("closableClick", null, a._ui($(a.lis[b]).find("a")[0], a.panels[b]))) return;
						a.remove(b)
					}
					return false
				}).end()
			})
		}
	})
})(jQuery);
/* jquery.ui.tabs.js  refresh and close able end*/
/* jquery.cookies.js start*/
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        // CAUTION: Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};
/* jquery.cookies.js end*/

/* jquery.hotkeys.js start*/

(function(jQuery){
	
	jQuery.hotkeys = {
		version: "0.8",

		specialKeys: {
			8: "backspace", 9: "tab", 13: "return", 16: "shift", 17: "ctrl", 18: "alt", 19: "pause",
			20: "capslock", 27: "esc", 32: "space", 33: "pageup", 34: "pagedown", 35: "end", 36: "home",
			37: "left", 38: "up", 39: "right", 40: "down", 45: "insert", 46: "del", 
			96: "0", 97: "1", 98: "2", 99: "3", 100: "4", 101: "5", 102: "6", 103: "7",
			104: "8", 105: "9", 106: "*", 107: "+", 109: "-", 110: ".", 111 : "/", 
			112: "f1", 113: "f2", 114: "f3", 115: "f4", 116: "f5", 117: "f6", 118: "f7", 119: "f8", 
			120: "f9", 121: "f10", 122: "f11", 123: "f12", 144: "numlock", 145: "scroll", 191: "/", 224: "meta"
		},
	
		shiftNums: {
			"`": "~", "1": "!", "2": "@", "3": "#", "4": "$", "5": "%", "6": "^", "7": "&", 
			"8": "*", "9": "(", "0": ")", "-": "_", "=": "+", ";": ": ", "'": "\"", ",": "<", 
			".": ">",  "/": "?",  "\\": "|"
		}
	};

	function keyHandler( handleObj ) {
		// Only care when a possible input has been specified
		if ( typeof handleObj.data !== "string" ) {
			return;
		}
		
		var origHandler = handleObj.handler,
			keys = handleObj.data.toLowerCase().split(" ");
	
		handleObj.handler = function( event ) {
			// Don't fire in text-accepting inputs that we didn't directly bind to
			if ( this !== event.target && (/textarea|select/i.test( event.target.nodeName ) ||
				 event.target.type === "text") ) {
				return;
			}
			
			// Keypress represents characters, not special keys
			var special = event.type !== "keypress" && jQuery.hotkeys.specialKeys[ event.which ],
				character = String.fromCharCode( event.which ).toLowerCase(),
				key, modif = "", possible = {};

			// check combinations (alt|ctrl|shift+anything)
			if ( event.altKey && special !== "alt" ) {
				modif += "alt+";
			}

			if ( event.ctrlKey && special !== "ctrl" ) {
				modif += "ctrl+";
			}
			
			// TODO: Need to make sure this works consistently across platforms
			if ( event.metaKey && !event.ctrlKey && special !== "meta" ) {
				modif += "meta+";
			}

			if ( event.shiftKey && special !== "shift" ) {
				modif += "shift+";
			}

			if ( special ) {
				possible[ modif + special ] = true;

			} else {
				possible[ modif + character ] = true;
				possible[ modif + jQuery.hotkeys.shiftNums[ character ] ] = true;

				// "$" can be triggered as "Shift+4" or "Shift+$" or just "$"
				if ( modif === "shift+" ) {
					possible[ jQuery.hotkeys.shiftNums[ character ] ] = true;
				}
			}

			for ( var i = 0, l = keys.length; i < l; i++ ) {
				if ( possible[ keys[i] ] ) {
					return origHandler.apply( this, arguments );
				}
			}
		};
	}

	jQuery.each([ "keydown", "keyup", "keypress" ], function() {
		jQuery.event.special[ this ] = { add: keyHandler };
	});

})( jQuery );
/* jquery.hotkeys.js end*/
/* jquery.loading.mask.js start*/
;(function($){
	$.fn.mask = function(label, delay){
		$(this).each(function() {
			if(delay !== undefined && delay > 0) {
		        var element = $(this);
		        element.data("_mask_timeout", setTimeout(function() { $.maskElement(element, label)}, delay));
			} else {
				$.maskElement($(this), label);
			}
		});
	};
	
	/**
	 * Removes mask from the element(s). Accepts both single and multiple selectors.
	 */
	$.fn.unmask = function(){
		$(this).each(function() {
			$.unmaskElement($(this));
		});
	};
	
	/**
	 * Checks if a single element is masked. Returns false if mask is delayed or not displayed. 
	 */
	$.fn.isMasked = function(){
		return this.hasClass("masked");
	};

	$.maskElement = function(element, label){
	
		//if this element has delayed mask scheduled then remove it and display the new one
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}

		if(element.isMasked()) {
			$.unmaskElement(element);
		}
		
		if(element.css("position") == "static") {
			element.addClass("masked-relative");
		}
		
		element.addClass("masked");
		
		var maskDiv = $('<div class="loadmask"></div>');
		
		//auto height fix for IE
		if(navigator.userAgent.toLowerCase().indexOf("msie") > -1){
			maskDiv.height(element.height() + parseInt(element.css("padding-top")) + parseInt(element.css("padding-bottom")));
			maskDiv.width(element.width() + parseInt(element.css("padding-left")) + parseInt(element.css("padding-right")));
		}
		
		//fix for z-index bug with selects in IE6
		if(navigator.userAgent.toLowerCase().indexOf("msie 6") > -1){
			element.find("select").addClass("masked-hidden");
		}
		
		element.append(maskDiv);
		
		if(label !== undefined) {
			var maskMsgDiv = $('<div class="loadmask-msg" style="display:none;"></div>');
			maskMsgDiv.append('<div>' + label + '</div>');
			element.append(maskMsgDiv);
			
			//calculate center position
			maskMsgDiv.css("top", Math.round(element.height() / 2 - (maskMsgDiv.height() - parseInt(maskMsgDiv.css("padding-top")) - parseInt(maskMsgDiv.css("padding-bottom"))) / 2)+"px");
			maskMsgDiv.css("left", Math.round(element.width() / 2 - (maskMsgDiv.width() - parseInt(maskMsgDiv.css("padding-left")) - parseInt(maskMsgDiv.css("padding-right"))) / 2)+"px");
			
			maskMsgDiv.show();
		}
		
	};
	
	$.unmaskElement = function(element){
		//if this element has delayed mask scheduled then remove it
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}
		
		element.find(".loadmask-msg,.loadmask").remove();
		element.removeClass("masked");
		element.removeClass("masked-relative");
		element.find("select").removeClass("masked-hidden");
	};
 
})(jQuery);

/*apMessageBox.js start*/
var apMessageBoxCount = 1;
var apMessageBox = apMessageBox || {};
(function($) {

	apMessageBox = function(config)
	{
		this.initialize = function()
		{

			__buildDOM(function() {
			
				$("#" + __config.messageEl).html(__config.message);
				
				$("#" + __config.dialogEl).dialog({
					modal:  __config.modal,
					width: __config.width,
					height: __config.height,
					resizable: false,
					close: function(e, ui) {
						//$("#" + __config.dialogEl).detach();
					    jQuery.event.remove($("#" + __config.dialogEl));
          				jQuery.removeData($("#" + __config.dialogEl));
						$("#" + __config.dialogEl).empty().remove();
						/*
						 * Execute any defined callback.
						 */
						if (__config.callback !== null && __config.callback !== undefined)
						{
							if (__config.scope !== null && __config.scope !== undefined)
							{
								__config.callback.call(__config.scope);
							}
							else
							{
								__config.callback();
							}
						}
					},
					buttons: __config.buttons
				});
				if(__config.messageType == "url"){
					$("#" + __config.dialogEl).ready(function(){
				        if(__config.url!=null && __config.url!=''){
							$("#" + __config.dialogEl).load(__config.url, function(responseText, textStatus, XMLHttpReques) {
							});
				        }
						
					});
				}
				
			});
		};
		
		var __buildDOM = function(callback)
		{
			/*
			 * Outer message containing div and message <p>
			 */
			var outer = $("<div />");
			var img = null;
			if (__config.messageType == "url"){
					img = $("<img />").attr({
						src: "/images/main/loading.gif"
					}).css({ float: "left", "margin-right": "10px" });
					
					$(outer).append(img);
			}else{
				var pEl = $("<p />").attr({ 
					id: __config.messageEl
				}).css({ "text-align": "left" });

				/*
				 * If this is an error message attach an error icon.
				 * Otherwise attach an information icon.
				 */
				if (__config.messageType == "error")
				{
					img = $("<img />").attr({
						src: __config.errorImage
					}).css({ float: "left", "margin-right": "10px" });
					
					$(outer).append(img);
				}
				else
				{
					img = $("<img />").attr({
						src: __config.informationImage
					}).css({ float: "left", "margin-right": "10px" });
					
					$(outer).append(img);
				}
				
				/*
				 * Append the <p> to the <div>
				 */
				$(outer).append(pEl);
				
				/*
				 * Build the dialog <div>. Attach the message and icon <div>
				 * to it, then attach the dialog <div> to the body.
				 */
			}
				var dialogEl = $("<div />").attr({
					id: __config.dialogEl,
					title: __config.title
				}).css({
					display: "none"
				});
	
				$(dialogEl).append(outer);
			
			$("body").append(dialogEl);
			
			/*
			 * When all is ready execute our callback which
			 * uses jQuery UI to do the dialog box.
			 */
			$(document).ready(callback);
		};
		var __config = $.extend({
			dialogEl: "messageDialog_"+ (apMessageBoxCount),
			messageEl: "message_"+ (apMessageBoxCount++),
			errorImage: apMessageBox.errorImage || "error.png",
			informationImage: apMessageBox.informationImage || "information.png",
			messageType: "information",
			title: "Notice!",
			width: 350,
			height: 200,
			message: "",
			modal:true,
			url:null,
			buttons: {
				'确定': function() {
					$(this).dialog('close');
				}
			},
			callback: null,
			scope: null
		}, config);
		var __this = this;
		
		this.initialize();
	};
	
	apMessageBox.show = function(config)
	{
		var msg = new apMessageBox(config || {});
	};
	
	apMessageBox.error = function(config)
	{
		var newConfig = $.extend({
			messageType: "error",
			title: "Error!"
		}, config);
		
		apMessageBox.show(newConfig);
	};
	
	apMessageBox.information = function(config)
	{
		var newConfig = $.extend({
			messageType: "information",
			title: "Notice!"
		}, config);
		
		apMessageBox.show(newConfig);
	};
	
	apMessageBox.url = function(config)
	{
		var newConfig = $.extend({
			messageType: "url",
			title: "Notice!"
		}, config);
		
		apMessageBox.show(newConfig);
	};
	
})(jQuery);
apMessageBox.errorImage = "/js/error-32x32.png"; 
apMessageBox.informationImage = "/js/information-32x32.png"; 

/*apMessageBox.js end*/

/*ui.tabs.paging.js start*/
$.extend($.ui.tabs.prototype, {
	paging: function(options) {
		var opts = {
			tabsPerPage: 0,
			nextButton: '&#187;',
			prevButton: '&#171;',
			follow: false,
			cycle: false,
			selectOnAdd: false,
			followOnSelect: false
		};
		
		opts = $.extend(opts, options);

		var self = this, initialized = false, currentPage, 
			buttonWidth, containerWidth, allTabsWidth, tabWidths, 
			maxPageWidth, pages, resizeTimer = null, 
			windowHeight = $(window).height(), windowWidth = $(window).width();
		
		function init() {
			destroy();
			
			allTabsWidth = 0, currentPage = 0, maxPageWidth = 0, buttonWidth = 0,
				pages = new Array(), tabWidths = new Array(), selectedTabWidths = new Array();
			
			containerWidth = self.element.width();
			
			// loops through LIs, get width of each tab when selected and unselected.
			var maxDiff = 0;  // the max difference between a selected and unselected tab
			self.lis.each(function(i) {			
				if (i == self.options.selected) {
					selectedTabWidths[i] = $(this).outerWidth({ margin: true });
					tabWidths[i] = self.lis.eq(i).removeClass('ui-tabs-selected').outerWidth({ margin: true });
					self.lis.eq(i).addClass('ui-tabs-selected');
					maxDiff = Math.min(maxDiff, Math.abs(selectedTabWidths[i] - tabWidths[i]));
					allTabsWidth += tabWidths[i];
				} else {
					tabWidths[i] = $(this).outerWidth({ margin: true });
					selectedTabWidths[i] = self.lis.eq(i).addClass('ui-tabs-selected').outerWidth({ margin: true });
					self.lis.eq(i).removeClass('ui-tabs-selected');
					maxDiff = Math.max(maxDiff, Math.abs(selectedTabWidths[i] - tabWidths[i]));
					allTabsWidth += tabWidths[i];
				}
			});
            // fix padding issues with buttons
            // TODO determine a better way to handle this
			allTabsWidth += maxDiff + ($.browser.msie?4:0) + 9;  

			// if the width of all tables is greater than the container's width, calculate the pages
			if (allTabsWidth > containerWidth) {
				// create next button			
				li = $('<li></li>')
					.addClass('ui-state-default ui-tabs-paging-next')
					.append($('<a href="#"></a>')
							.click(function() { page('next'); return false; })
							.html(opts.nextButton));
				
				self.lis.eq(self.length()-1).after(li);
				buttonWidth = li.outerWidth({ margin: true });
				
				// create prev button
				li = $('<li></li>')
					.addClass('ui-state-default ui-tabs-paging-prev')
					.append($('<a href="#"></a>')
							.click(function() { page('prev'); return false; })
							.html(opts.prevButton));
				self.lis.eq(0).before(li);
				buttonWidth += li.outerWidth({ margin: true });
				
				// TODO determine fix for padding issues to next button
				buttonWidth += 19; 
								
				var pageIndex = 0, pageWidth = 0, maxTabPadding = 0;
				
				// start calculating pageWidths
				for (var i = 0; i < tabWidths.length; i++) {
					// if first tab of page or selected tab's padding larger than the current max, set the maxTabPadding
					if (pageWidth == 0 || selectedTabWidths[i] - tabWidths[i] > maxTabPadding)
						maxTabPadding = (selectedTabWidths[i] - tabWidths[i]);
					
					// if first tab of page, initialize pages variable for page 
					if (pages[pageIndex] == null) {
						pages[pageIndex] = { start: i };
					
					} else if ((i > 0 && (i % opts.tabsPerPage) == 0) || (tabWidths[i] + pageWidth + buttonWidth + 12) > containerWidth) {
						if ((pageWidth + maxTabPadding) > maxPageWidth)	
							maxPageWidth = (pageWidth + maxTabPadding);
						pageIndex++;
						pages[pageIndex] = { start: i };			
						pageWidth = 0;
					}
					pages[pageIndex].end = i+1;
					pageWidth += tabWidths[i];
					if (i == self.options.selected) currentPage = pageIndex;
				}
				if ((pageWidth + maxTabPadding) > maxPageWidth)	
					maxPageWidth = (pageWidth + maxTabPadding);				

			    // hide all tabs then show tabs for current page
				self.lis.hide().slice(pages[currentPage].start, pages[currentPage].end).show();
				if (currentPage == (pages.length - 1) && !opts.cycle) 
					disableButton('next');			
				if (currentPage == 0 && !opts.cycle) 
					disableButton('prev');
				
				// calculate the right padding for the next button
				buttonPadding = containerWidth - maxPageWidth - buttonWidth;
				if (buttonPadding > 0) 
					//$('.ui-tabs-paging-next', self.element).css({ paddingRight: buttonPadding + 'px' });
				
				initialized = true;
			} else {
				destroy();
			}
			
			$(window).bind('resize', handleResize);
		}
		
		function page(direction) {
			currentPage = currentPage + (direction == 'prev'?-1:1);
			
			if ((direction == 'prev' && currentPage < 0 && opts.cycle) ||
				(direction == 'next' && currentPage >= pages.length && !opts.cycle))
				currentPage = pages.length - 1;
			else if ((direction == 'prev' && currentPage < 0) || 
					 (direction == 'next' && currentPage >= pages.length && opts.cycle))
				currentPage = 0;
			
			var start = pages[currentPage].start;
			var end = pages[currentPage].end;
			self.lis.hide().slice(start, end).show();
			
			if (direction == 'prev') {
				enableButton('next');
				if (opts.follow && (self.options.selected < start || self.options.selected > (end-1))) self.select(end-1);
				if (!opts.cycle && start <= 0) disableButton('prev');
			} else {
				enableButton('prev');
				if (opts.follow && (self.options.selected < start || self.options.selected > (end-1))) self.select(start);
				if (!opts.cycle && end >= self.length()) disableButton('next');
			}
		}
		
		function disableButton(direction) {
			$('.ui-tabs-paging-'+direction, self.element).addClass('ui-tabs-paging-disabled');
		}
		
		function enableButton(direction) {
			$('.ui-tabs-paging-'+direction, self.element).removeClass('ui-tabs-paging-disabled');
		}
		
		// special function defined to handle IE6 and IE7 resize issues
		function handleResize() {
			if (resizeTimer) clearTimeout(resizeTimer);
			
			if (windowHeight != $(window).height() || windowWidth != $(window).width()) 
				resizeTimer = setTimeout(reinit, 100);
		}
		
		function reinit() {	
			windowHeight = $(window).height();
			windowWidth = $(window).width();
			init();
		}
		
		function destroy() {
			// remove buttons
			$('.ui-tabs-paging-next', self.element).remove();
			$('.ui-tabs-paging-prev', self.element).remove();
			
			// show all tabs
			self.lis.show();
			
			initialized = false;
			
			$(window).unbind('resize', handleResize);
		}
		
		// reconfigure "ui.tabs" add/remove events to reinit paging
		var tabsAdd = self.add;
		self.add = function(url, label, index) {
			// remove paging buttons before adding a tab
			if (initialized)
				destroy();
			
			tabsAdd.apply(this, [url, label, index]);
		    
			if (opts.selectOnAdd) {
				if (index == undefined) index = this.lis.length-1;
				this.select(index);
			}
			// re-initialize paging buttons
			init();
		};
		var tabsRemove = self.remove;
		self.remove = function(index) {
			// remove paging buttons before removing a tab
			if (initialized)
				destroy();
			
			tabsRemove.apply(this, [index]);
			
			// re-initialize paging buttons
			init();
		};
		// reconfigure "ui.tabs" select event to change pages if new tab is selected
		var tabsSelect = self.select;
		self.select = function(index) {
			tabsSelect.apply(this, [index]);
			
			// if paging is not initialized or it is not configured to 
			// change pages when a new tab is selected, then do nothing
			if (!initialized || !opts.followOnSelect)
				return;
			
			// find the new page based on index of the tab selected
			for (i in pages) {
				var start = pages[i].start;
				var end = pages[i].end;
				if (index >= start && index < end) {
					// if the the tab selected is not within the currentPage of tabs, then change pages
					if (i != currentPage) {
						self.lis.hide().slice(start, end).show();
						
						currentPage = parseInt(i);
						if (currentPage == 0) {
							enableButton('next');
							if (!opts.cycle && start <= 0) disableButton('prev');
						} else {
							enableButton('prev');
							if (!opts.cycle && end >= self.length()) disableButton('next');
						}
					}
					break;
				}
			}
		};
		
		// add, remove, and destroy functions specific for paging 
		$.extend($.ui.tabs.prototype, {
			pagingDestroy: function() {
				destroy();
			}
		});
		
		init();
	}
});
/*ui.tabs.paging.js end*/

/*jquery.form.js start*/
/*!
 * jQuery Form Plugin
 * version: 2.45 (09-AUG-2010)
 */
;(function($) {
$.fn.ajaxSubmit = function(options) {
	// fast fail if nothing selected (http://dev.jquery.com/ticket/2752)
	if (!this.length) {
		log('ajaxSubmit: skipping submit process - no element selected');
		return this;
	}

	if (typeof options == 'function') {
		options = { success: options };
	}

	var url = $.trim(this.attr('action'));
	if (url) {
		// clean url (don't include hash vaue)
		url = (url.match(/^([^#]+)/)||[])[1];
	}
	url = url || window.location.href || '';

	options = $.extend(true, {
		url:  url,
		type: this.attr('method') || 'GET',
		iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank'
	}, options);

	// hook for manipulating the form data before it is extracted;
	// convenient for use with rich editors like tinyMCE or FCKEditor
	var veto = {};
	this.trigger('form-pre-serialize', [this, options, veto]);
	if (veto.veto) {
		log('ajaxSubmit: submit vetoed via form-pre-serialize trigger');
		return this;
	}

	// provide opportunity to alter form data before it is serialized
	if (options.beforeSerialize && options.beforeSerialize(this, options) === false) {
		log('ajaxSubmit: submit aborted via beforeSerialize callback');
		return this;
	}

	var n,v,a = this.formToArray(options.semantic);
	if (options.data) {
		options.extraData = options.data;
		for (n in options.data) {
			if(options.data[n] instanceof Array) {
				for (var k in options.data[n]) {
					a.push( { name: n, value: options.data[n][k] } );
				}
			}
			else {
				v = options.data[n];
				v = $.isFunction(v) ? v() : v; // if value is fn, invoke it
				a.push( { name: n, value: v } );
			}
		}
	}

	// give pre-submit callback an opportunity to abort the submit
	if (options.beforeSubmit && options.beforeSubmit(a, this, options) === false) {
		log('ajaxSubmit: submit aborted via beforeSubmit callback');
		return this;
	}

	// fire vetoable 'validate' event
	this.trigger('form-submit-validate', [a, this, options, veto]);
	if (veto.veto) {
		log('ajaxSubmit: submit vetoed via form-submit-validate trigger');
		return this;
	}

	var q = $.param(a);

	if (options.type.toUpperCase() == 'GET') {
		options.url += (options.url.indexOf('?') >= 0 ? '&' : '?') + q;
		options.data = null;  // data is null for 'get'
	}
	else {
		options.data = q; // data is the query string for 'post'
	}

	var $form = this, callbacks = [];
	if (options.resetForm) {
		callbacks.push(function() { $form.resetForm(); });
	}
	if (options.clearForm) {
		callbacks.push(function() { $form.clearForm(); });
	}

	// perform a load on the target only if dataType is not provided
	if (!options.dataType && options.target) {
		var oldSuccess = options.success || function(){};
		callbacks.push(function(data) {
			var fn = options.replaceTarget ? 'replaceWith' : 'html';
			$(options.target)[fn](data).each(oldSuccess, arguments);
		});
	}
	else if (options.success) {
		callbacks.push(options.success);
	}

	options.success = function(data, status, xhr) { // jQuery 1.4+ passes xhr as 3rd arg
		var context = options.context || options;   // jQuery 1.4+ supports scope context 
		for (var i=0, max=callbacks.length; i < max; i++) {
			callbacks[i].apply(context, [data, status, xhr || $form, $form]);
		}
	};

	// are there files to upload?
	var fileInputs = $('input:file', this).length > 0;
	var mp = 'multipart/form-data';
	var multipart = ($form.attr('enctype') == mp || $form.attr('encoding') == mp);

	// options.iframe allows user to force iframe mode
	// 06-NOV-09: now defaulting to iframe mode if file input is detected
   if (options.iframe !== false && (fileInputs || options.iframe || multipart)) {
	   // hack to fix Safari hang (thanks to Tim Molendijk for this)
	   // see:  http://groups.google.com/group/jquery-dev/browse_thread/thread/36395b7ab510dd5d
	   if (options.closeKeepAlive) {
		   $.get(options.closeKeepAlive, fileUpload);
		}
	   else {
		   fileUpload();
		}
   }
   else {
	   $.ajax(options);
   }

	// fire 'notify' event
	this.trigger('form-submit-notify', [this, options]);
	return this;


	// private function for handling file uploads (hat tip to YAHOO!)
	function fileUpload() {
		var form = $form[0];

		if ($(':input[name=submit],:input[id=submit]', form).length) {
			// if there is an input with a name or id of 'submit' then we won't be
			// able to invoke the submit fn on the form (at least not x-browser)
			alert('Error: Form elements must not have name or id of "submit".');
			return;
		}

		var s = $.extend(true, {}, $.ajaxSettings, options);
		s.context = s.context || s;
		var id = 'jqFormIO' + (new Date().getTime());
		var $io = $('<iframe id="' + id + '" name="' + id + '" src="'+ s.iframeSrc +'" onload="var f = jQuery(this).data(\'form-plugin-onload\'); if (f) f();" />');
		var io = $io[0];

		$io.css({ position: 'absolute', top: '-1000px', left: '-1000px' });

		var xhr = { // mock object
			aborted: 0,
			responseText: null,
			responseXML: null,
			status: 0,
			statusText: 'n/a',
			getAllResponseHeaders: function() {},
			getResponseHeader: function() {},
			setRequestHeader: function() {},
			abort: function() {
				this.aborted = 1;
				$io.attr('src', s.iframeSrc); // abort op in progress
			}
		};

		var g = s.global;
		// trigger ajax global events so that activity/block indicators work like normal
		if (g && ! $.active++) {
			$.event.trigger("ajaxStart");
		}
		if (g) {
			$.event.trigger("ajaxSend", [xhr, s]);
		}

		if (s.beforeSend && s.beforeSend.call(s.context, xhr, s) === false) {
			if (s.global) { 
				$.active--;
			}
			return;
		}
		if (xhr.aborted) {
			return;
		}

		var cbInvoked = false;
		var timedOut = 0;

		// add submitting element to data if we know it
		var sub = form.clk;
		if (sub) {
			var n = sub.name;
			if (n && !sub.disabled) {
				s.extraData = s.extraData || {};
				s.extraData[n] = sub.value;
				if (sub.type == "image") {
					s.extraData[n+'.x'] = form.clk_x;
					s.extraData[n+'.y'] = form.clk_y;
				}
			}
		}

		// take a breath so that pending repaints get some cpu time before the upload starts
		function doSubmit() {
			// make sure form attrs are set
			var t = $form.attr('target'), a = $form.attr('action');

			// update form attrs in IE friendly way
			form.setAttribute('target',id);
			if (form.getAttribute('method') != 'POST') {
				form.setAttribute('method', 'POST');
			}
			if (form.getAttribute('action') != s.url) {
				form.setAttribute('action', s.url);
			}

			// ie borks in some cases when setting encoding
			if (! s.skipEncodingOverride) {
				$form.attr({
					encoding: 'multipart/form-data',
					enctype:  'multipart/form-data'
				});
			}

			// support timout
			if (s.timeout) {
				setTimeout(function() { timedOut = true; cb(); }, s.timeout);
			}

			// add "extra" data to form if provided in options
			var extraInputs = [];
			try {
				if (s.extraData) {
					for (var n in s.extraData) {
						extraInputs.push(
							$('<input type="hidden" name="'+n+'" value="'+s.extraData[n]+'" />')
								.appendTo(form)[0]);
					}
				}

				// add iframe to doc and submit the form
				$io.appendTo('body');
				$io.data('form-plugin-onload', cb);
				form.submit();
			}
			finally {
				// reset attrs and remove "extra" input elements
				form.setAttribute('action',a);
				if(t) {
					form.setAttribute('target', t);
				} else {
					$form.removeAttr('target');
				}
				$(extraInputs).remove();
			}
		}

		if (s.forceSync) {
			doSubmit();
		}
		else {
			setTimeout(doSubmit, 10); // this lets dom updates render
		}
	
		var data, doc, domCheckCount = 100;

		function cb() {
			if (cbInvoked) {
				return;
			}

			$io.removeData('form-plugin-onload');
			
			var ok = true;
			try {
				if (timedOut) {
					throw 'timeout';
				}
				// extract the server response from the iframe
				doc = io.contentWindow ? io.contentWindow.document : io.contentDocument ? io.contentDocument : io.document;
				
				var isXml = s.dataType == 'xml' || doc.XMLDocument || $.isXMLDoc(doc);
				log('isXml='+isXml);
				if (!isXml && (doc.body == null || doc.body.innerHTML == '')) {
					if (--domCheckCount) {
						// in some browsers (Opera) the iframe DOM is not always traversable when
						// the onload callback fires, so we loop a bit to accommodate
						log('requeing onLoad callback, DOM not available');
						setTimeout(cb, 250);
						return;
					}
					log('Could not access iframe DOM after 100 tries.');
					throw 'DOMException: not available';
				}

				log('response detected');
				cbInvoked = true;
				xhr.responseText = doc.documentElement ? doc.documentElement.innerHTML : null; 
				xhr.responseXML = doc.XMLDocument ? doc.XMLDocument : doc;
				xhr.getResponseHeader = function(header){
					var headers = {'content-type': s.dataType};
					return headers[header];
				};

				var scr = /(json|script)/.test(s.dataType);
				if (scr || s.textarea) {
					// see if user embedded response in textarea
					var ta = doc.getElementsByTagName('textarea')[0];
					if (ta) {
						xhr.responseText = ta.value;
					}
					else if (scr) {
						// account for browsers injecting pre around json response
						var pre = doc.getElementsByTagName('pre')[0];
						if (pre) {
							xhr.responseText = pre.innerHTML;
						}
					}			  
				}
				else if (s.dataType == 'xml' && !xhr.responseXML && xhr.responseText != null) {
					xhr.responseXML = toXml(xhr.responseText);
				}
				data = $.httpData(xhr, s.dataType);
			}
			catch(e){
				log('error caught:',e);
				ok = false;
				xhr.error = e;
				$.handleError(s, xhr, 'error', e);
			}

			// ordering of these callbacks/triggers is odd, but that's how $.ajax does it
			if (ok) {
				s.success.call(s.context, data, 'success');
				if (g) {
					$.event.trigger("ajaxSuccess", [xhr, s]);
				}
			}
			if (g) {
				$.event.trigger("ajaxComplete", [xhr, s]);
			}
			if (g && ! --$.active) {
				$.event.trigger("ajaxStop");
			}
			if (s.complete) {
				s.complete.call(s.context, xhr, ok ? 'success' : 'error');
			}

			// clean up
			setTimeout(function() {
				$io.removeData('form-plugin-onload');
				$io.remove();
				xhr.responseXML = null;
			}, 100);
		}

		function toXml(s, doc) {
			if (window.ActiveXObject) {
				doc = new ActiveXObject('Microsoft.XMLDOM');
				doc.async = 'false';
				doc.loadXML(s);
			}
			else {
				doc = (new DOMParser()).parseFromString(s, 'text/xml');
			}
			return (doc && doc.documentElement && doc.documentElement.tagName != 'parsererror') ? doc : null;
		}
	}
};

$.fn.ajaxForm = function(options) {
	// in jQuery 1.3+ we can fix mistakes with the ready state
	if (this.length === 0) {
		var o = { s: this.selector, c: this.context };
		if (!$.isReady && o.s) {
			log('DOM not ready, queuing ajaxForm');
			$(function() {
				$(o.s,o.c).ajaxForm(options);
			});
			return this;
		}
		// is your DOM ready?  http://docs.jquery.com/Tutorials:Introducing_$(document).ready()
		log('terminating; zero elements found by selector' + ($.isReady ? '' : ' (DOM not ready)'));
		return this;
	}
	
	return this.ajaxFormUnbind().bind('submit.form-plugin', function(e) {
		if (!e.isDefaultPrevented()) { // if event has been canceled, don't proceed
			e.preventDefault();
			$(this).ajaxSubmit(options);
		}
	}).bind('click.form-plugin', function(e) {
		var target = e.target;
		var $el = $(target);
		if (!($el.is(":submit,input:image"))) {
			// is this a child element of the submit el?  (ex: a span within a button)
			var t = $el.closest(':submit');
			if (t.length == 0) {
				return;
			}
			target = t[0];
		}
		var form = this;
		form.clk = target;
		if (target.type == 'image') {
			if (e.offsetX != undefined) {
				form.clk_x = e.offsetX;
				form.clk_y = e.offsetY;
			} else if (typeof $.fn.offset == 'function') { // try to use dimensions plugin
				var offset = $el.offset();
				form.clk_x = e.pageX - offset.left;
				form.clk_y = e.pageY - offset.top;
			} else {
				form.clk_x = e.pageX - target.offsetLeft;
				form.clk_y = e.pageY - target.offsetTop;
			}
		}
		// clear form vars
		setTimeout(function() { form.clk = form.clk_x = form.clk_y = null; }, 100);
	});
};

// ajaxFormUnbind unbinds the event handlers that were bound by ajaxForm
$.fn.ajaxFormUnbind = function() {
	return this.unbind('submit.form-plugin click.form-plugin');
};

$.fn.formToArray = function(semantic) {
	var a = [];
	if (this.length === 0) {
		return a;
	}

	var form = this[0];
	var els = semantic ? form.getElementsByTagName('*') : form.elements;
	if (!els) {
		return a;
	}
	
	var i,j,n,v,el;
	for(i=0, max=els.length; i < max; i++) {
		el = els[i];
		n = el.name;
		if (!n) {
			continue;
		}

		if (semantic && form.clk && el.type == "image") {
			// handle image inputs on the fly when semantic == true
			if(!el.disabled && form.clk == el) {
				a.push({name: n, value: $(el).val()});
				a.push({name: n+'.x', value: form.clk_x}, {name: n+'.y', value: form.clk_y});
			}
			continue;
		}

		v = $.fieldValue(el, true);
		if (v && v.constructor == Array) {
			for(j=0, jmax=v.length; j < jmax; j++) {
				a.push({name: n, value: v[j]});
			}
		}
		else if (v !== null && typeof v != 'undefined') {
			a.push({name: n, value: v});
		}
	}

	if (!semantic && form.clk) {
		// input type=='image' are not found in elements array! handle it here
		var $input = $(form.clk), input = $input[0];
		n = input.name;
		if (n && !input.disabled && input.type == 'image') {
			a.push({name: n, value: $input.val()});
			a.push({name: n+'.x', value: form.clk_x}, {name: n+'.y', value: form.clk_y});
		}
	}
	return a;
};


$.fn.formSerialize = function(semantic) {
	//hand off to jQuery.param for proper encoding
	return $.param(this.formToArray(semantic));
};


$.fn.fieldSerialize = function(successful) {
	var a = [];
	this.each(function() {
		var n = this.name;
		if (!n) {
			return;
		}
		var v = $.fieldValue(this, successful);
		if (v && v.constructor == Array) {
			for (var i=0,max=v.length; i < max; i++) {
				a.push({name: n, value: v[i]});
			}
		}
		else if (v !== null && typeof v != 'undefined') {
			a.push({name: this.name, value: v});
		}
	});
	//hand off to jQuery.param for proper encoding
	return $.param(a);
};


$.fn.fieldValue = function(successful) {
	for (var val=[], i=0, max=this.length; i < max; i++) {
		var el = this[i];
		var v = $.fieldValue(el, successful);
		if (v === null || typeof v == 'undefined' || (v.constructor == Array && !v.length)) {
			continue;
		}
		v.constructor == Array ? $.merge(val, v) : val.push(v);
	}
	return val;
};


$.fieldValue = function(el, successful) {
	var n = el.name, t = el.type, tag = el.tagName.toLowerCase();
	if (successful === undefined) {
		successful = true;
	}

	if (successful && (!n || el.disabled || t == 'reset' || t == 'button' ||
		(t == 'checkbox' || t == 'radio') && !el.checked ||
		(t == 'submit' || t == 'image') && el.form && el.form.clk != el ||
		tag == 'select' && el.selectedIndex == -1)) {
			return null;
	}

	if (tag == 'select') {
		var index = el.selectedIndex;
		if (index < 0) {
			return null;
		}
		var a = [], ops = el.options;
		var one = (t == 'select-one');
		var max = (one ? index+1 : ops.length);
		for(var i=(one ? index : 0); i < max; i++) {
			var op = ops[i];
			if (op.selected) {
				var v = op.value;
				if (!v) { // extra pain for IE...
					v = (op.attributes && op.attributes['value'] && !(op.attributes['value'].specified)) ? op.text : op.value;
				}
				if (one) {
					return v;
				}
				a.push(v);
			}
		}
		return a;
	}
	return $(el).val();
};


$.fn.clearForm = function() {
	return this.each(function() {
		$('input,select,textarea', this).clearFields();
	});
};


$.fn.clearFields = $.fn.clearInputs = function() {
	return this.each(function() {
		var t = this.type, tag = this.tagName.toLowerCase();
		if (t == 'text' || t == 'password' || tag == 'textarea') {
			this.value = '';
		}
		else if (t == 'checkbox' || t == 'radio') {
			this.checked = false;
		}
		else if (tag == 'select') {
			this.selectedIndex = -1;
		}
	});
};


$.fn.resetForm = function() {
	return this.each(function() {
		// guard against an input with the name of 'reset'
		// note that IE reports the reset function as an 'object'
		if (typeof this.reset == 'function' || (typeof this.reset == 'object' && !this.reset.nodeType)) {
			this.reset();
		}
	});
};


$.fn.enable = function(b) {
	if (b === undefined) {
		b = true;
	}
	return this.each(function() {
		this.disabled = !b;
	});
};


$.fn.selected = function(select) {
	if (select === undefined) {
		select = true;
	}
	return this.each(function() {
		var t = this.type;
		if (t == 'checkbox' || t == 'radio') {
			this.checked = select;
		}
		else if (this.tagName.toLowerCase() == 'option') {
			var $sel = $(this).parent('select');
			if (select && $sel[0] && $sel[0].type == 'select-one') {
				// deselect all other options
				$sel.find('option').selected(false);
			}
			this.selected = select;
		}
	});
};

function log() {
	if ($.fn.ajaxSubmit.debug) {
		var msg = '[jquery.form] ' + Array.prototype.join.call(arguments,'');
		if (window.console && window.console.log) {
			window.console.log(msg);
		}
		else if (window.opera && window.opera.postError) {
			window.opera.postError(msg);
		}
	}
};

})(jQuery);
/*jquery.form.js end*/

/*jquery.Autocomplete.js start*/
;(function($){$.fn.extend({autocomplete:function(urlOrData,options){var isUrl=typeof urlOrData=="string";options=$.extend({},$.Autocompleter.defaults,{url:isUrl?urlOrData:null,data:isUrl?null:urlOrData,delay:isUrl?$.Autocompleter.defaults.delay:10,max:options&&!options.scroll?10:150},options);options.highlight=options.highlight||function(value){return value;};options.formatMatch=options.formatMatch||options.formatItem;return this.each(function(){new $.Autocompleter(this,options);});},result:function(handler){return this.bind("result",handler);},search:function(handler){return this.trigger("search",[handler]);},flushCache:function(){return this.trigger("flushCache");},setOptions:function(options){return this.trigger("setOptions",[options]);},unautocomplete:function(){return this.trigger("unautocomplete");}});$.Autocompleter=function(input,options){var KEY={UP:38,DOWN:40,DEL:46,TAB:9,RETURN:13,ESC:27,COMMA:188,PAGEUP:33,PAGEDOWN:34,BACKSPACE:8};var $input=$(input).attr("autocomplete","off").addClass(options.inputClass);var timeout;var previousValue="";var cache=$.Autocompleter.Cache(options);var hasFocus=0;var lastKeyPressCode;var config={mouseDownOnSelect:false};var select=$.Autocompleter.Select(options,input,selectCurrent,config);var blockSubmit;$.browser.opera&&$(input.form).bind("submit.autocomplete",function(){if(blockSubmit){blockSubmit=false;return false;}});$input.bind(($.browser.opera?"keypress":"keydown")+".autocomplete",function(event){hasFocus=1;lastKeyPressCode=event.keyCode;switch(event.keyCode){case KEY.UP:event.preventDefault();if(select.visible()){select.prev();}else{onChange(0,true);}break;case KEY.DOWN:event.preventDefault();if(select.visible()){select.next();}else{onChange(0,true);}break;case KEY.PAGEUP:event.preventDefault();if(select.visible()){select.pageUp();}else{onChange(0,true);}break;case KEY.PAGEDOWN:event.preventDefault();if(select.visible()){select.pageDown();}else{onChange(0,true);}break;case options.multiple&&$.trim(options.multipleSeparator)==","&&KEY.COMMA:case KEY.TAB:case KEY.RETURN:if(selectCurrent()){event.preventDefault();blockSubmit=true;return false;}break;case KEY.ESC:select.hide();break;default:clearTimeout(timeout);timeout=setTimeout(onChange,options.delay);break;}}).focus(function(){hasFocus++;}).blur(function(){hasFocus=0;if(!config.mouseDownOnSelect){hideResults();}}).click(function(){if(hasFocus++>1&&!select.visible()){onChange(0,true);}}).bind("search",function(){var fn=(arguments.length>1)?arguments[1]:null;function findValueCallback(q,data){var result;if(data&&data.length){for(var i=0;i<data.length;i++){if(data[i].result.toLowerCase()==q.toLowerCase()){result=data[i];break;}}}if(typeof fn=="function")fn(result);else $input.trigger("result",result&&[result.data,result.value]);}$.each(trimWords($input.val()),function(i,value){request(value,findValueCallback,findValueCallback);});}).bind("flushCache",function(){cache.flush();}).bind("setOptions",function(){$.extend(options,arguments[1]);if("data"in arguments[1])cache.populate();}).bind("unautocomplete",function(){select.unbind();$input.unbind();$(input.form).unbind(".autocomplete");});function selectCurrent(){var selected=select.selected();if(!selected)return false;var v=selected.result;previousValue=v;if(options.multiple){var words=trimWords($input.val());if(words.length>1){var seperator=options.multipleSeparator.length;var cursorAt=$(input).selection().start;var wordAt,progress=0;$.each(words,function(i,word){progress+=word.length;if(cursorAt<=progress){wordAt=i;return false;}progress+=seperator;});words[wordAt]=v;v=words.join(options.multipleSeparator);}v+=options.multipleSeparator;}$input.val(v);hideResultsNow();$input.trigger("result",[selected.data,selected.value]);return true;}function onChange(crap,skipPrevCheck){if(lastKeyPressCode==KEY.DEL){select.hide();return;}var currentValue=$input.val();if(!skipPrevCheck&&currentValue==previousValue)return;previousValue=currentValue;currentValue=lastWord(currentValue);if(currentValue.length>=options.minChars){$input.addClass(options.loadingClass);if(!options.matchCase)currentValue=currentValue.toLowerCase();request(currentValue,receiveData,hideResultsNow);}else{stopLoading();select.hide();}};function trimWords(value){if(!value)return[""];if(!options.multiple)return[$.trim(value)];return $.map(value.split(options.multipleSeparator),function(word){return $.trim(value).length?$.trim(word):null;});}function lastWord(value){if(!options.multiple)return value;var words=trimWords(value);if(words.length==1)return words[0];var cursorAt=$(input).selection().start;if(cursorAt==value.length){words=trimWords(value)}else{words=trimWords(value.replace(value.substring(cursorAt),""));}return words[words.length-1];}function autoFill(q,sValue){if(options.autoFill&&(lastWord($input.val()).toLowerCase()==q.toLowerCase())&&lastKeyPressCode!=KEY.BACKSPACE){$input.val($input.val()+sValue.substring(lastWord(previousValue).length));$(input).selection(previousValue.length,previousValue.length+sValue.length);}};function hideResults(){clearTimeout(timeout);timeout=setTimeout(hideResultsNow,200);};function hideResultsNow(){var wasVisible=select.visible();select.hide();clearTimeout(timeout);stopLoading();if(options.mustMatch){$input.search(function(result){if(!result){if(options.multiple){var words=trimWords($input.val()).slice(0,-1);$input.val(words.join(options.multipleSeparator)+(words.length?options.multipleSeparator:""));}else{$input.val("");$input.trigger("result",null);}}});}};function receiveData(q,data){if(data&&data.length&&hasFocus){stopLoading();select.display(data,q);autoFill(q,data[0].value);select.show();}else{hideResultsNow();}};function request(term,success,failure){if(!options.matchCase)term=term.toLowerCase();var data=cache.load(term);if(data&&data.length){success(term,data);}else if((typeof options.url=="string")&&(options.url.length>0)){var extraParams={timestamp:+new Date()};$.each(options.extraParams,function(key,param){extraParams[key]=typeof param=="function"?param():param;});$.ajax({type:'POST',mode:"abort",port:"autocomplete"+input.name,dataType:options.dataType,url:options.url,data:$.extend({q:lastWord(term),limit:options.max},extraParams),success:function(data){var parsed=options.parse&&options.parse(data)||parse(data);cache.add(term,parsed);success(term,parsed);}});}else{select.emptyList();failure(term);}};function parse(data){var parsed=[];var rows=data.split("\n");for(var i=0;i<rows.length;i++){var row=$.trim(rows[i]);if(row){row=row.split("|");parsed[parsed.length]={data:row,value:row[0],result:options.formatResult&&options.formatResult(row,row[0])||row[0]};}}return parsed;};function stopLoading(){$input.removeClass(options.loadingClass);};};$.Autocompleter.defaults={inputClass:"ac_input",resultsClass:"ac_results",loadingClass:"ac_loading",minChars:1,delay:400,matchCase:false,matchSubset:true,matchContains:false,cacheLength:10,max:100,mustMatch:false,extraParams:{},selectFirst:true,formatItem:function(row){return row[0];},formatMatch:null,autoFill:false,width:0,multiple:false,multipleSeparator:", ",highlight:function(value,term){return value.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)("+term.replace(/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi,"\\$1")+")(?![^<>]*>)(?![^&;]+;)","gi"),"<strong>$1</strong>");},scroll:true,scrollHeight:180};$.Autocompleter.Cache=function(options){var data={};var length=0;function matchSubset(s,sub){if(!options.matchCase)s=s.toLowerCase();var i=s.indexOf(sub);if(options.matchContains=="word"){i=s.toLowerCase().search("\\b"+sub.toLowerCase());}if(i==-1)return false;return i==0||options.matchContains;};function add(q,value){if(length>options.cacheLength){flush();}if(!data[q]){length++;}data[q]=value;}function populate(){if(!options.data)return false;var stMatchSets={},nullData=0;if(!options.url)options.cacheLength=1;stMatchSets[""]=[];for(var i=0,ol=options.data.length;i<ol;i++){var rawValue=options.data[i];rawValue=(typeof rawValue=="string")?[rawValue]:rawValue;var value=options.formatMatch(rawValue,i+1,options.data.length);if(value===false)continue;var firstChar=value.charAt(0).toLowerCase();if(!stMatchSets[firstChar])stMatchSets[firstChar]=[];var row={value:value,data:rawValue,result:options.formatResult&&options.formatResult(rawValue)||value};stMatchSets[firstChar].push(row);if(nullData++<options.max){stMatchSets[""].push(row);}};$.each(stMatchSets,function(i,value){options.cacheLength++;add(i,value);});}setTimeout(populate,25);function flush(){data={};length=0;}return{flush:flush,add:add,populate:populate,load:function(q){if(!options.cacheLength||!length)return null;if(!options.url&&options.matchContains){var csub=[];for(var k in data){if(k.length>0){var c=data[k];$.each(c,function(i,x){if(matchSubset(x.value,q)){csub.push(x);}});}}return csub;}else
if(data[q]){return data[q];}else
if(options.matchSubset){for(var i=q.length-1;i>=options.minChars;i--){var c=data[q.substr(0,i)];if(c){var csub=[];$.each(c,function(i,x){if(matchSubset(x.value,q)){csub[csub.length]=x;}});return csub;}}}return null;}};};$.Autocompleter.Select=function(options,input,select,config){var CLASSES={ACTIVE:"ac_over"};var listItems,active=-1,data,term="",needsInit=true,element,list;function init(){if(!needsInit)return;element=$("<div/>").hide().addClass(options.resultsClass).css("position","absolute").appendTo(document.body);list=$("<ul/>").appendTo(element).mouseover(function(event){if(target(event).nodeName&&target(event).nodeName.toUpperCase()=='LI'){active=$("li",list).removeClass(CLASSES.ACTIVE).index(target(event));$(target(event)).addClass(CLASSES.ACTIVE);}}).click(function(event){$(target(event)).addClass(CLASSES.ACTIVE);select();input.focus();return false;}).mousedown(function(){config.mouseDownOnSelect=true;}).mouseup(function(){config.mouseDownOnSelect=false;});if(options.width>0)element.css("width",options.width);needsInit=false;}function target(event){var element=event.target;while(element&&element.tagName!="LI")element=element.parentNode;if(!element)return[];return element;}function moveSelect(step){listItems.slice(active,active+1).removeClass(CLASSES.ACTIVE);movePosition(step);var activeItem=listItems.slice(active,active+1).addClass(CLASSES.ACTIVE);if(options.scroll){var offset=0;listItems.slice(0,active).each(function(){offset+=this.offsetHeight;});if((offset+activeItem[0].offsetHeight-list.scrollTop())>list[0].clientHeight){list.scrollTop(offset+activeItem[0].offsetHeight-list.innerHeight());}else if(offset<list.scrollTop()){list.scrollTop(offset);}}};function movePosition(step){active+=step;if(active<0){active=listItems.size()-1;}else if(active>=listItems.size()){active=0;}}function limitNumberOfItems(available){return options.max&&options.max<available?options.max:available;}function fillList(){list.empty();var max=limitNumberOfItems(data.length);for(var i=0;i<max;i++){if(!data[i])continue;var formatted=options.formatItem(data[i].data,i+1,max,data[i].value,term);if(formatted===false)continue;var li=$("<li/>").html(options.highlight(formatted,term)).addClass(i%2==0?"ac_even":"ac_odd").appendTo(list)[0];$.data(li,"ac_data",data[i]);}listItems=list.find("li");if(options.selectFirst){listItems.slice(0,1).addClass(CLASSES.ACTIVE);active=0;}if($.fn.bgiframe)list.bgiframe();}return{display:function(d,q){init();data=d;term=q;fillList();},next:function(){moveSelect(1);},prev:function(){moveSelect(-1);},pageUp:function(){if(active!=0&&active-8<0){moveSelect(-active);}else{moveSelect(-8);}},pageDown:function(){if(active!=listItems.size()-1&&active+8>listItems.size()){moveSelect(listItems.size()-1-active);}else{moveSelect(8);}},hide:function(){element&&element.hide();listItems&&listItems.removeClass(CLASSES.ACTIVE);active=-1;},visible:function(){return element&&element.is(":visible");},current:function(){return this.visible()&&(listItems.filter("."+CLASSES.ACTIVE)[0]||options.selectFirst&&listItems[0]);},show:function(){var offset=$(input).offset();element.css({width:typeof options.width=="string"||options.width>0?options.width:$(input).width(),top:offset.top+input.offsetHeight,left:offset.left}).show();if(options.scroll){list.scrollTop(0);list.css({maxHeight:options.scrollHeight,overflow:'auto'});if($.browser.msie&&typeof document.body.style.maxHeight==="undefined"){var listHeight=0;listItems.each(function(){listHeight+=this.offsetHeight;});var scrollbarsVisible=listHeight>options.scrollHeight;list.css('height',scrollbarsVisible?options.scrollHeight:listHeight);if(!scrollbarsVisible){listItems.width(list.width()-parseInt(listItems.css("padding-left"))-parseInt(listItems.css("padding-right")));}}}},selected:function(){var selected=listItems&&listItems.filter("."+CLASSES.ACTIVE).removeClass(CLASSES.ACTIVE);return selected&&selected.length&&$.data(selected[0],"ac_data");},emptyList:function(){list&&list.empty();},unbind:function(){element&&element.remove();}};};$.fn.selection=function(start,end){if(start!==undefined){return this.each(function(){if(this.createTextRange){var selRange=this.createTextRange();if(end===undefined||start==end){selRange.move("character",start);selRange.select();}else{selRange.collapse(true);selRange.moveStart("character",start);selRange.moveEnd("character",end);selRange.select();}}else if(this.setSelectionRange){this.setSelectionRange(start,end);}else if(this.selectionStart){this.selectionStart=start;this.selectionEnd=end;}});}var field=this[0];if(field.createTextRange){var range=document.selection.createRange(),orig=field.value,teststring="<->",textLength=range.text.length;range.text=teststring;var caretAt=field.value.indexOf(teststring);field.value=orig;this.selection(caretAt,caretAt+textLength);return{start:caretAt,end:caretAt+textLength}}else if(field.selectionStart!==undefined){return{start:field.selectionStart,end:field.selectionEnd}}};})(jQuery);
/*jquery.Autocomplete.js end*/

/*jQuery UI selectmenu start*/
(function($) {

$.widget("ui.selectmenu", {
	_init: function() {
		var self = this, o = this.options;
		
		//quick array of button and menu id's
		this.ids = [this.element.attr('id') + '-' + 'button', this.element.attr('id') + '-' + 'menu'];
		
		//define safe mouseup for future toggling
		this._safemouseup = true;
		
		//create menu button wrapper
		this.newelement = $('<a class="'+ this.widgetBaseClass +' ui-widget ui-state-default ui-corner-all" id="'+this.ids[0]+'" role="button" href="#" aria-haspopup="true" aria-owns="'+this.ids[1]+'"></a>')
			.insertAfter(this.element);
		
		//transfer tabindex
		var tabindex = this.element.attr('tabindex');
		if(tabindex){ this.newelement.attr('tabindex', tabindex); }
		
		//save reference to select in data for ease in calling methods
		this.newelement.data('selectelement', this.element);
		
		//menu icon
		this.selectmenuIcon = $('<span class="'+ this.widgetBaseClass +'-icon ui-icon"></span>')
			.prependTo(this.newelement)
			.addClass( (o.style == "popup")? 'ui-icon-triangle-2-n-s' : 'ui-icon-triangle-1-s' );	

			
		//make associated form label trigger focus
		$('label[for='+this.element.attr('id')+']')
			.attr('for', this.ids[0])
			.bind('click', function(){
				self.newelement[0].focus();
				return false;
			});	

		//click toggle for menu visibility
		this.newelement
			.bind('mousedown', function(event){
				self._toggle(event);
				//make sure a click won't open/close instantly
				if(o.style == "popup"){
					self._safemouseup = false;
					setTimeout(function(){self._safemouseup = true;}, 300);
				}	
				return false;
			})
			.bind('click',function(){
				return false;
			})
			.keydown(function(event){
				var ret = true;
				switch (event.keyCode) {
					case $.ui.keyCode.ENTER:
						ret = true;
						break;
					case $.ui.keyCode.SPACE:
						ret = false;
						self._toggle(event);	
						break;
					case $.ui.keyCode.UP:
					case $.ui.keyCode.LEFT:
						ret = false;
						self._moveSelection(-1);
						break;
					case $.ui.keyCode.DOWN:
					case $.ui.keyCode.RIGHT:
						ret = false;
						self._moveSelection(1);
						break;	
					case $.ui.keyCode.TAB:
						ret = true;
						break;	
					default:
						ret = false;
						self._typeAhead(event.keyCode, 'mouseup');
						break;	
				}
				return ret;
			})
			.bind('mouseover focus', function(){ 
				$(this).addClass(self.widgetBaseClass+'-focus ui-state-hover'); 
			})
			.bind('mouseout blur', function(){  
				$(this).removeClass(self.widgetBaseClass+'-focus ui-state-hover'); 
			});
		
		//document click closes menu
		$(document)
			.mousedown(function(event){
				self.close(event);
			});

		//change event on original selectmenu
		this.element
			.click(function(){ this._refreshValue(); })
			.focus(function(){ this.newelement[0].focus(); });
		
		//create menu portion, append to body
		var cornerClass = (o.style == "dropdown")? " ui-corner-bottom" : " ui-corner-all"
		this.list = $('<ul class="' + self.widgetBaseClass + '-menu ui-widget ui-widget-content'+cornerClass+'" aria-hidden="true" role="listbox" aria-labelledby="'+this.ids[0]+'" id="'+this.ids[1]+'"></ul>').appendTo('body');				
		
		//serialize selectmenu element options	
		var selectOptionData = [];
		this.element
			.find('option')
			.each(function(){
				selectOptionData.push({
					value: $(this).attr('value'),
					text: self._formatText(jQuery(this).text()),
					selected: $(this).attr('selected'),
					classes: $(this).attr('class'),
					parentOptGroup: $(this).parent('optgroup').attr('label')
				});
			});		
				
		//active state class is only used in popup style
		var activeClass = (self.options.style == "popup") ? " ui-state-active" : "";
		
		//write li's
		for(var i in selectOptionData){
			var thisLi = $('<li role="presentation"><a href="#" tabindex="-1" role="option" aria-selected="false">'+ selectOptionData[i].text +'</a></li>')
				.data('index',i)
				.addClass(selectOptionData[i].classes)
				.data('optionClasses', selectOptionData[i].classes|| '')
				.mouseup(function(event){
						if(self._safemouseup){
							var changed = $(this).data('index') != self._selectedIndex();
							self.value($(this).data('index'));
							self.select(event);
							if(changed){ self.change(event); }
							self.close(event,true);
						}
					return false;
				})
				.click(function(){
					return false;
				})
				.bind('mouseover focus', function(){ 
					self._selectedOptionLi().addClass(activeClass); 
					self._focusedOptionLi().removeClass(self.widgetBaseClass+'-item-focus ui-state-hover'); 
					$(this).removeClass('ui-state-active').addClass(self.widgetBaseClass + '-item-focus ui-state-hover'); 
				})
				.bind('mouseout blur', function(){ 
					if($(this).is( self._selectedOptionLi() )){ $(this).addClass(activeClass); }
					$(this).removeClass(self.widgetBaseClass + '-item-focus ui-state-hover'); 
				});
				
			//optgroup or not...
			if(selectOptionData[i].parentOptGroup){
				var optGroupName = self.widgetBaseClass + '-group-' + selectOptionData[i].parentOptGroup;
				if(this.list.find('li.' + optGroupName).size()){
					this.list.find('li.' + optGroupName + ':last ul').append(thisLi);
				}
				else{
					$('<li role="presentation" class="'+self.widgetBaseClass+'-group '+optGroupName+'"><span class="'+self.widgetBaseClass+'-group-label">'+selectOptionData[i].parentOptGroup+'</span><ul></ul></li>')
						.appendTo(this.list)
						.find('ul')
						.append(thisLi);
				}
			}
			else{
				thisLi.appendTo(this.list);
			}
			
			//this allows for using the scrollbar in an overflowed list
			this.list.bind('mousedown mouseup', function(){return false;});
			
			//append icon if option is specified
			if(o.icons){
				for(var j in o.icons){
					if(thisLi.is(o.icons[j].find)){
						thisLi
							.data('optionClasses', selectOptionData[i].classes + ' ' + self.widgetBaseClass + '-hasIcon')
							.addClass(self.widgetBaseClass + '-hasIcon');
						var iconClass = o.icons[j].icon || "";
						
						thisLi
							.find('a:eq(0)')
							.prepend('<span class="'+self.widgetBaseClass+'-item-icon ui-icon '+iconClass + '"></span>');
					}
				}
			}
		}	
		
		//add corners to top and bottom menu items
		this.list.find('li:last').addClass("ui-corner-bottom");
		if(o.style == 'popup'){ this.list.find('li:first').addClass("ui-corner-top"); }
		
		//transfer classes to selectmenu and list
		if(o.transferClasses){
			var transferClasses = this.element.attr('class') || ''; 
			this.newelement.add(this.list).addClass(transferClasses);
		}
		
		//original selectmenu width
		var selectWidth = this.element.width();
		
		//set menu button width
		this.newelement.width( (o.width) ? o.width : selectWidth);
		
		//set menu width to either menuWidth option value, width option value, or select width 
		if(o.style == 'dropdown'){ this.list.width( (o.menuWidth) ? o.menuWidth : ((o.width) ? o.width : selectWidth)); }
		else { this.list.width( (o.menuWidth) ? o.menuWidth : ((o.width) ? o.width - o.handleWidth : selectWidth - o.handleWidth)); }	
		
		//set max height from option 
		if(o.maxHeight && o.maxHeight < this.list.height()){ this.list.height(o.maxHeight); }	
		
		//save reference to actionable li's (not group label li's)
		this._optionLis = this.list.find('li:not(.'+ self.widgetBaseClass +'-group)');
				
		//transfer menu click to menu button
		this.list
			.keydown(function(event){
				var ret = true;
				switch (event.keyCode) {
					case $.ui.keyCode.UP:
					case $.ui.keyCode.LEFT:
						ret = false;
						self._moveFocus(-1);
						break;
					case $.ui.keyCode.DOWN:
					case $.ui.keyCode.RIGHT:
						ret = false;
						self._moveFocus(1);
						break;	
					case $.ui.keyCode.HOME:
						ret = false;
						self._moveFocus(':first');
						break;	
					case $.ui.keyCode.PAGE_UP:
						ret = false;
						self._scrollPage('up');
						break;	
					case $.ui.keyCode.PAGE_DOWN:
						ret = false;
						self._scrollPage('down');
						break;
					case $.ui.keyCode.END:
						ret = false;
						self._moveFocus(':last');
						break;			
					case $.ui.keyCode.ENTER:
					case $.ui.keyCode.SPACE:
						ret = false;
						self.close(event,true);
						$(event.target).parents('li:eq(0)').trigger('mouseup');
						break;		
					case $.ui.keyCode.TAB:
						ret = true;
						self.close(event,true);
						break;	
					case $.ui.keyCode.ESCAPE:
						ret = false;
						self.close(event,true);
						break;	
					default:
						ret = false;
						self._typeAhead(event.keyCode,'focus');
						break;		
				}
				return ret;
			});
			
		//selectmenu style
		if(o.style == 'dropdown'){
			this.newelement
				.addClass(self.widgetBaseClass+"-dropdown");
			this.list
				.addClass(self.widgetBaseClass+"-menu-dropdown");	
		}
		else {
			this.newelement
				.addClass(self.widgetBaseClass+"-popup");
			this.list
				.addClass(self.widgetBaseClass+"-menu-popup");	
		}
		
		//append status span to button
		this.newelement.prepend('<span class="'+self.widgetBaseClass+'-status">'+ selectOptionData[this._selectedIndex()].text +'</span>');
		
		//hide original selectmenu element
		this.element.hide();
		
		//transfer disabled state
		if(this.element.attr('disabled') == true){ this.disable(); }
		
		//update value
		this.value(this._selectedIndex());
	},
	destroy: function() {
		this.element.removeData(this.widgetName)
			.removeClass(this.widgetBaseClass + '-disabled' + ' ' + this.namespace + '-state-disabled')
			.removeAttr('aria-disabled');
	
		//unbind click on label, reset its for attr
		$('label[for='+this.newelement.attr('id')+']')
			.attr('for',this.element.attr('id'))
			.unbind('click');
		this.newelement.remove();
		this.list.remove();
		this.element.show();	
	},
	_typeAhead: function(code, eventType){
		var self = this;
		//define self._prevChar if needed
		if(!self._prevChar){ self._prevChar = ['',0]; }
		var C = String.fromCharCode(code);
		c = C.toLowerCase();
		var focusFound = false;
		function focusOpt(elem, ind){
			focusFound = true;
			$(elem).trigger(eventType);
			self._prevChar[1] = ind;
		};
		this.list.find('li a').each(function(i){	
			if(!focusFound){
				var thisText = $(this).text();
				if( thisText.indexOf(C) == 0 || thisText.indexOf(c) == 0){
						if(self._prevChar[0] == C){
							if(self._prevChar[1] < i){ focusOpt(this,i); }	
						}
						else{ focusOpt(this,i); }	
				}
			}
		});
		this._prevChar[0] = C;
	},
	_uiHash: function(){
		return {
			value: this.value()
		};
	},
	open: function(event){
		var self = this;
		var disabledStatus = this.newelement.attr("aria-disabled");
		if(disabledStatus != 'true'){
			this._refreshPosition();
			this._closeOthers(event);
			this.newelement
				.addClass('ui-state-active');
			
			this.list
				.appendTo('body')
				.addClass(self.widgetBaseClass + '-open')
				.attr('aria-hidden', false)
				.find('li:not(.'+ self.widgetBaseClass +'-group):eq('+ this._selectedIndex() +') a')[0].focus();	
			if(this.options.style == "dropdown"){ this.newelement.removeClass('ui-corner-all').addClass('ui-corner-top'); }	
			this._refreshPosition();
			this._trigger("open", event, this._uiHash());
		}
	},
	close: function(event, retainFocus){
		if(this.newelement.is('.ui-state-active')){
			this.newelement
				.removeClass('ui-state-active');
			this.list
				.attr('aria-hidden', true)
				.removeClass(this.widgetBaseClass+'-open');
			if(this.options.style == "dropdown"){ this.newelement.removeClass('ui-corner-top').addClass('ui-corner-all'); }
			if(retainFocus){this.newelement[0].focus();}	
			this._trigger("close", event, this._uiHash());
		}
	},
	change: function(event) {
		this.element.trigger('change');
		this._trigger("change", event, this._uiHash());
	},
	select: function(event) {
		this._trigger("select", event, this._uiHash());
	},
	_closeOthers: function(event){
		$('.'+ this.widgetBaseClass +'.ui-state-active').not(this.newelement).each(function(){
			$(this).data('selectelement').selectmenu('close',event);
		});
		$('.'+ this.widgetBaseClass +'.ui-state-hover').trigger('mouseout');
	},
	_toggle: function(event,retainFocus){
		if(this.list.is('.'+ this.widgetBaseClass +'-open')){ this.close(event,retainFocus); }
		else { this.open(event); }
	},
	_formatText: function(text){
		return this.options.format ? this.options.format(text) : text;
	},
	_selectedIndex: function(){
		return this.element[0].selectedIndex;
	},
	_selectedOptionLi: function(){
		return this._optionLis.eq(this._selectedIndex());
	},
	_focusedOptionLi: function(){
		return this.list.find('.'+ this.widgetBaseClass +'-item-focus');
	},
	_moveSelection: function(amt){
		var currIndex = parseInt(this._selectedOptionLi().data('index'), 10);
		var newIndex = currIndex + amt;
		return this._optionLis.eq(newIndex).trigger('mouseup');
	},
	_moveFocus: function(amt){
		if(!isNaN(amt)){
			var currIndex = parseInt(this._focusedOptionLi().data('index'), 10);
			var newIndex = currIndex + amt;
		}
		else { var newIndex = parseInt(this._optionLis.filter(amt).data('index'), 10); }
		
		if(newIndex < 0){ newIndex = 0; }
		if(newIndex > this._optionLis.size()-1){
			newIndex =  this._optionLis.size()-1;
		}
		var activeID = this.widgetBaseClass + '-item-' + Math.round(Math.random() * 1000);
		
		this._focusedOptionLi().find('a:eq(0)').attr('id','');
		this._optionLis.eq(newIndex).find('a:eq(0)').attr('id',activeID)[0].focus();
		this.list.attr('aria-activedescendant', activeID);
	},
	_scrollPage: function(direction){
		var numPerPage = Math.floor(this.list.outerHeight() / this.list.find('li:first').outerHeight());
		numPerPage = (direction == 'up') ? -numPerPage : numPerPage;
		this._moveFocus(numPerPage);
	},
	_setData: function(key, value) {
		this.options[key] = value;
		if (key == 'disabled') {
			this.close();
			this.element
				.add(this.newelement)
				.add(this.list)
					[value ? 'addClass' : 'removeClass'](
						this.widgetBaseClass + '-disabled' + ' ' +
						this.namespace + '-state-disabled')
					.attr("aria-disabled", value);
		}
	},
	value: function(newValue) {
		if (arguments.length) {
			this.element[0].selectedIndex = newValue;
			this._refreshValue();
			this._refreshPosition();
		}
		return this.element[0].selectedIndex;
	},
	_refreshValue: function() {
		var activeClass = (this.options.style == "popup") ? " ui-state-active" : "";
		var activeID = this.widgetBaseClass + '-item-' + Math.round(Math.random() * 1000);
		//deselect previous
		this.list
			.find('.'+ this.widgetBaseClass +'-item-selected')
			.removeClass(this.widgetBaseClass + "-item-selected" + activeClass)
			.find('a')
			.attr('aria-selected', 'false')
			.attr('id', '');
		//select new
		this._selectedOptionLi()
			.addClass(this.widgetBaseClass + "-item-selected"+activeClass)
			.find('a')
			.attr('aria-selected', 'true')
			.attr('id', activeID);
			
		//toggle any class brought in from option
		var currentOptionClasses = this.newelement.data('optionClasses') ? this.newelement.data('optionClasses') : "";
		var newOptionClasses = this._selectedOptionLi().data('optionClasses') ? this._selectedOptionLi().data('optionClasses') : "";
		this.newelement
			.removeClass(currentOptionClasses)
			.data('optionClasses', newOptionClasses)
			.addClass( newOptionClasses )
			.find('.'+this.widgetBaseClass+'-status')
			.html( 
				this._selectedOptionLi()
					.find('a:eq(0)')
					.html() 
			);
			
		this.list.attr('aria-activedescendant', activeID)
	},
	_refreshPosition: function(){	
		//set left value
		this.list.css('left', this.newelement.offset().left);
		
		//set top value
		var menuTop = this.newelement.offset().top;
		var scrolledAmt = this.list[0].scrollTop;
		this.list.find('li:lt('+this._selectedIndex()+')').each(function(){
			scrolledAmt -= $(this).outerHeight();
		});
		
		if(this.newelement.is('.'+this.widgetBaseClass+'-popup')){
			menuTop+=scrolledAmt; 
			this.list.css('top', menuTop); 
		}	
		else { 
			menuTop += this.newelement.height();
			this.list.css('top', menuTop); 
		}
	}
});

$.extend($.ui.selectmenu, {
	getter: "value",
	version: "@VERSION",
	eventPrefix: "selectmenu",
	defaults: {
		transferClasses: true,
		style: 'popup', 
		width: null, 
		menuWidth: null, 
		handleWidth: 26, 
		maxHeight: null,
		icons: null, 
		format: null
	}
});

})(jQuery);
/*jQuery UI selectmenu end*/
