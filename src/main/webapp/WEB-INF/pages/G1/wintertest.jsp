<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucen" />
<title></title>
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/demos.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<link rel="stylesheet" href="${wechatPath}css/mui/baocun.css">
<style type="text/css">
	 p{
		color: #000000;
		font-size: 17px;
	}
</style>
<body>
	<header class="mui-bar mui-bar-nav" >
			<a href="javascript:history.go(-1);" class="mui-action-back mui-icon mui-icon-arrowleft mui-icon-left-nav mui-pull-left" style="color:black;"></a>
			<h1 class="mui-title">冬季测试情况</h1>
	</header>
	<div  class="bd" style="margin-top: 50px;">
	
	<div class="weui-cells">
          <a class="weui-cell weui-cell_access" href="${wechatPath}wintertest/lookListPage.action?seedfileid=${seedfileid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>长势图片记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="JavaScript:upload('${seedfileid}','冬季测试检测结果图片','15');">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
     </div>
     
    
	
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
		
<script type="text/javascript">
function gog0(id){
	window.location = "${wechatPath}bottleseed/detail.action?seedfileid="+id;
}

function upload(id,desc,type){
	desc=encodeURI(encodeURI(desc));
	window.location.href='${wechatPath}detection/detectionPage.action?relatedid='+id+'&type='+type+'&url=${url}&operate=${sessionScope.operate}&description='+desc;
}
		
</script>
</body>
