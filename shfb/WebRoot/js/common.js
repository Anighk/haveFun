//得到页面元素值
function getE(id) {
	var temp=document.getElementById(id).value.replace(/(?:^\s*)|(?:\s*$)/g, "").replace(/(['\\])/g, "\\$1");
	var rex=/[*?<>&={}~!\`\[\]]|[,;-]$|\([^)]*(\(|$)|(^|\))[^(]*\)|\(\)|^%$|\$$|\/$/;
	if(rex.test(temp)){
		return "^msgError^";
	}else{
		return temp;
	}	
}


//退出系统
function  esc(){
	 $.layer({
	    shade: [0],
	    area: ['280px','150px'],
	    title:'提示',
	    dialog: {
	        msg: '确认要退出吗？',
	        btns: 2,                    
	        type: 4,
	        btn: ['确定','取消'],
	        yes: function(){
	        	window.location.replace('logout.do');
	        }, no: function(){
	        	return;
	        }
	    }
	});
};

//全选
function allchk() {
	var chk = document.getElementById("allchk").checked;
	$("input[name='an']").each(function(){
		$(this).prop("checked", chk);
	});
}
/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2013 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// CommonJS
		factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (value !== undefined && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setTime(+t + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {};

		// To prevent the for loop in the first place assign an empty array
		// in case there are no cookies at all. Also prevents odd result when
		// calling $.cookie().
		var cookies = document.cookie ? document.cookie.split('; ') : [];

		for (var i = 0, l = cookies.length; i < l; i++) {
			var parts = cookies[i].split('=');
			var name = decode(parts.shift());
			var cookie = parts.join('=');

			if (key && key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		if ($.cookie(key) === undefined) {
			return false;
		}

		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));


//折叠效果
function effect(ObjectId){
	$("#"+ObjectId).toggle();
	if($("#"+ObjectId+"_a").text() == "收起") {
		$("#"+ObjectId+"_a").text("展开")
	} else {
		$("#"+ObjectId+"_a").text("收起")
	}
}

//非负整数验证
function checkNum(value){
	var reg=/^\d+$/;
	var flag=reg.test(value);
	return !flag;
}