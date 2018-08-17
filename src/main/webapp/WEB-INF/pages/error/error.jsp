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
<title>错误</title>
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
<body>
	<div class="weui-msg">
		<div class="weui-msg__icon-area">
			<i class="weui-icon-success weui-icon_msg"></i>
		</div>
		<div class="weui-msg__text-area">
			<h2 class="weui-msg__title">系统挂了！</h2>
			<p class="weui-msg__desc">
				不好意思，系统挂了.......!
			</p>
		</div>
<!-- 		<div class="weui-msg__opr-area"> -->
<!-- 			<p class="weui-btn-area"> -->
<!-- 				<a href="javascript:;" class="weui-btn weui-btn_primary">推荐操作</a> <a -->
<!-- 					href="javascript:;" class="weui-btn weui-btn_default">辅助操作</a> -->
<!-- 			</p> -->
<!-- 		</div> -->
		<div class="weui-msg__extra-area">
			<div class="weui-footer">
				<p class="weui-footer__links">
					<a href="javascript:void(0);" class="weui-footer__link">种薯质量追溯系统</a>
				</p>
				<p class="weui-footer__text">Copyright © 2008-2016 tudouji.com.cn</p>
			</div>
		</div>
	</div>
</body>
</html>