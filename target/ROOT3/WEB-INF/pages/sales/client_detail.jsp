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
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<body style="background-color: #FFFFFF">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		客户信息
		<a href="${wechatPath}client/index.action?clientid=${client.guid}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}client/edit.action?guid=${client.guid}" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}client/edit.action?guid=${client.guid}"  style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">编辑</a>
	</div>
	
	<form action="${wechatPath}client/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					客户姓名：${client.clientname }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					联系方式：${client.tlephone }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
				种植面积：${client.plantarea }
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					地址：${client.province }${client.city }${client.area }${client.street }
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">头像</label>
				</div>
				<div class="weui-cell__bd">
					<img src="${client.url} " style="height:50px;width:50px;"/>
				</div>
			</div>
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/city-picker.min.js" type="text/javascript"></script>
	<!--图片上传 -->
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	
</body>
</html>