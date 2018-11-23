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
<title>用户登录</title>
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/demos.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<body style="background-color: #FFFFFF">
	<header class='demos-header'>
		<h6 class="demos-title" style="font-size: 28px;">用户登录</h6>
	</header>
	<form  method="post" id="formid">
		<input type="hidden" name="uri" value="${uri}"/>
		<div class="weui-cells weui-cells_form">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">手机号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="tel" pattern="[0-9]*" placeholder="请输入手机号" name="telphone" id="telphone" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">密码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="password"  placeholder="请输入密码" name="password" id="password" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
		</div>
		
		<div style="width: 100%;height: 20px;"><span style="color: red;">${msg}</span></div>
		<a href="javascript:;" id="loginid" class="weui-btn weui-btn_plain-primary">登录</a>
		<a href="${wechatPath}users/regist.action" class="weui-btn weui-btn_plain-default">注册</a>
	</form>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		var uri='${uri}';
	
		$("#loginid").on("click",function(){
			var telphone=$("#telphone").val().replace(/^\s+|\s+$/g,"");
			var password=$("#password").val().replace(/^\s+|\s+$/g,"");
			
			var re = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9]|17[0-9])\d{8}$/i;
			if (!re.test(telphone)) {
				$.alert("请输入正确的手机号！");
				return false;
			}
			if(password==null || password ==""){
				$.alert("请输入密码！");
				return false;
			}
			
			$.ajax({
	  			url : "${wechatPath}users/dologin.action",
	  			type : "POST",
	  			async : false,
	  			dataType : 'json',
	  			data : {
	  				telphone:telphone,
	  				password:password
	  			},
	  			success : function(data) {
	  				if(data.msg=='ok'){
	  					 if(uri!=null&&uri!=""){
	  						 window.location.href="${wechatPath}"+uri;
	  					 }else{
	  						window.location.href="${wechatPath}index/index.action";
	  					 }
	  					getUserInfo();
	  				}else{
	  					$.alert(data.msg);
	  				}
	  			},
	  			error : function(data) {
	  				$.alert(data.msg);
	  			}
	  		});
		})
		
		
	function getUserInfo(){
		$.ajax({
  			url : "${wechatPath}users/getUserInfo.action",
  			type : "POST",
  			async : false,
  			dataType : 'json',
  			data : {},
  			success : function(data) {
  				if(data.msg=='ok'){
//   				app.save(data.telphone,data.password);
  					OCModel.showAlertMsg(data.telphone,data.password);
  				}
  			},
  			error : function(data) {
  				$.alert("失败");
  			}
  		});
	}
		
	</script>
</body>
</html>