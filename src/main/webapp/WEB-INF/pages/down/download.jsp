<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zh_CN">
<head runat="server">
<title>APP下载</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=yes" />
<link href="${path}css/weixin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-size: 16px;
	font-family: 'Microsoft YaHei';
}

body {
	background: #e8e8e8;
}

.down {
	width: 80%;
	margin: 20px auto;
	display: block;
	text-align: center;
	background: #353f49;
	padding: 10px 0
}

a.down:link {
	color: #FFF;
	text-decoration: none
}

.down img {
	margin-right: 10px;
	width: 10%;
	vertical-align: -7px
}

#weixin-tip{display:none;position:fixed;left:0;top:0;background:rgba(0,0,0,0.8);filter:alpha(opacity=80);width:100%;height:100%;z-index:100;}
#weixin-tip p{text-align:center;margin-top:10%;padding:0 5%;position:relative;}
#weixin-tip .close{color:#fff;padding:5px;font:bold 20px/24px simsun;text-shadow:0 1px 0 #ddd;position:absolute;top:0;left:5%;}
</style>
</head>
<body>
	<div style=" width:100%;" align="center"><img src="${path}icon/down.gif" border="0" style=" width:100%"/></div>
	<a href="http://zhongshu.tudouji.com.cn/potatoes-quality-traceability.apk" class="down" ><img src="${path}icon/android.png" border="0" />Android下载</a>
	<a href="http://zhongshu.tudouji.com.cn/TestProject.ipa" class="down"><img src="${path}icon/apple.png" border="0"/>iPhone下载</a> 
	<div id="weixin-tip"><p><img src="${path}icon/live_weixin.png" style="height:311px;width: 365px;" alt="微信打开"/><span id="close" title="关闭" class="close">×</span></p></div>
	
<script type="text/javascript">
var is_weixin = (function(){return navigator.userAgent.toLowerCase().indexOf('micromessenger') !== -1})();
window.onload = function() {
	var winHeight = typeof window.innerHeight != 'undefined' ? window.innerHeight : document.documentElement.clientHeight; //兼容IOS，不需要的可以去掉
	var btn = document.getElementById('J_weixin');
	var tip = document.getElementById('weixin-tip');
	var close = document.getElementById('close');
	if (is_weixin) {
		btn.onclick = function(e) {
			tip.style.height = winHeight + 'px'; //兼容IOS弹窗整屏
			tip.style.display = 'block';
			return false;
		}
		close.onclick = function() {
			tip.style.display = 'none';
		}
	}
}	 
</script>
</body>
</html>