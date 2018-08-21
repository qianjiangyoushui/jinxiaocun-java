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
		<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
		<link rel="stylesheet" href="${wechatPath}css/mui/app.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
		<link rel="stylesheet" href="${wechatPath}css/swiper-3.3.1.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">
<style type="text/css">
	 p{
		color: #000000;
		font-size: 17px;
	}
</style>
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		个人信息
			<a href="${wechatPath}setup/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div style="margin-top: -20px;">
	<div  class="bd" >
		<div class="weui-cells">
          <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>姓名：${sessionScope.user.username}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        
          <a class="weui-cell weui-cell_access"  >
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>手机号码：${sessionScope.user.telphone}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" >
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>部门：${sessionScope.user.depart.departname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
	</div>
    
    </div>

	

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
	
</body>
</html>