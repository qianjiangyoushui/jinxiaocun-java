<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucen" />
<title>用户注册</title>
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/demos.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">

<!--标准mui.css-->
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		职工用户管理
		<a href="${wechatPath}setup/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<form action="${wechatPath}setup/savepwd.action" id="formid" method="post">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">原密码：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="oldpwd" id="oldpwd"  placeholder="请输入原密码" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			 <div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">新密码：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="password" name="newpwd1" id="newpwd1" placeholder="请输入新密码" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">确认密码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="password" name="newpwd2" id="newpwd2" placeholder="请再次确认密码" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
		</div>
		<a href="javascript:;" id="updatepwd" class="weui-btn weui-btn_plain-primary">确认</a>
		<div style="width: 100%;height: 5px;"></div>
		<div style="width: 100%;height: 25px;<c:if test="${empty msg}">display: none;</c:if>"  ><span style="color: red;">${msg}</span></div>
	</form>
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script>
	 
		
		
		$("#updatepwd").on("click",function(){
			var oldpwd=$("#oldpwd").val().replace(/^\s+|\s+$/g,"");
			if(oldpwd==null||oldpwd==""){
				$.toptip('请输入原始密码', 'error');
				return false;
			}
			var newpwd1=$("#newpwd1").val().replace(/^\s+|\s+$/g,"");
			if(newpwd1==null || newpwd1==""){
				$.toptip('请输入新密码', 'error');
				return false;
			}
			
			var newpwd2=$("#newpwd2").val().replace(/^\s+|\s+$/g,"");
			if(newpwd2==null || newpwd2==""){
				$.toptip('请再次输入新密码', 'error');
				return false;
			}
			
			if(newpwd1!=newpwd2){
				$.toptip('两次输入密码不同，请重新输入', 'error');
				return false;
			}
			
			$("#formid").submit();
		})
		
	</script>
	
	

</body>
</html>