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
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<form action="${wechatPath}users/doregist.action" id="formid" method="post">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">企业名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="companyname" onkeyup="checkcompany();" style="margin-top: 5px;margin-bottom: 5px;"
						id="companyname" placeholder="请输入企业名称">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责人姓名</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="contactperson" style="margin-top: 5px;margin-bottom: 5px;"
						id="contactperson" placeholder="请输入负责人">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在地区</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="address" id='address' placeholder="省 市 区县" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种植面积(亩)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="plantarea" id="plantarea"  placeholder="请输入种植面积" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种植起始年份</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="plantyears" id="plantyears"  placeholder="请输入种植马铃薯起始年份" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">注册人姓名</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="username" id="username"  placeholder="请输入注册人姓名" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell weui-cell_vcode">
			    <div class="weui-cell__hd">
			      <label class="weui-label">手机号</label>
			    </div>
			    <div class="weui-cell__bd">
			      <input class="weui-input" type="tel" name="telphone" id="telphone" placeholder="请输入手机号" onkeyup="checkphone();" style="margin-top: 5px;margin-bottom: 5px;">
			    </div>
			    <div class="weui-cell__ft">
			      <a class="weui-vcode-btn" onclick='validate_yz(this)'>获取验证码</a>
			    </div>
			 </div>
			
			 <div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">验证码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="code" id="code" placeholder="请输入验证码" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">密码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="password" name="password" id="password" placeholder="请输入密码" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
		</div>
		
		<div style="width: 100%;height: 25px;<c:if test="${empty msg}">display: none;</c:if>"  ><span style="color: red;">${msg}</span></div>
		<a href="javascript:;" id="registid" class="weui-btn weui-btn_disabled weui-btn_default">注册</a>
		<div style="width: 100%;height: 5px;"></div>
	</form>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/city-picker.min.js" type="text/javascript"></script>
	<script>
	  $("#address").cityPicker({
	    title: "请选择所在地"
	  });
	  
	   var wait = 60;//倒计时初始值
	  
		//点击获取验证码按钮   ,手机号码的验证（13开头和158，159开头，共11位）
		var validate_yz = function(o){
			var telphone = $("#telphone").val();
			var re = /^(13[0-9]|17[0-9]|15[0-9]|18[0-9])\d{8}$/i;
			if(!re.test(telphone)){
				$.alert("请输入正确的手机号！");
				return false;
			}
			
			$.ajax({
				url : "${path}token/get.action",
				type : "POST",
				async : true,
				dataType : 'json',
				data : {
					telphone:telphone,
					type:"1"
				},
				success : function(data) {
					if(data.msg=='ok'){
						$.alert("验证码发送成功，请注意查收");
						time(o);
					}else{
						$.alert("验证码发送失败，请联系系统管理员！");
					}
				},
				error : function(data) {
					$.alert("验证码发送失败，请联系系统管理员！");
				}
			});
		}
	  
		function time(o){
			if(wait == 0){
				o.setAttribute("href","javascript:void(0);");
				o.setAttribute("onclick", "validate_yz(this);");
				o.innerHTML="获取验证码";
				wait = 60;
			}else{
				o.removeAttribute("href");
				o.removeAttribute("onclick");
				o.innerHTML = wait+"秒后重新获取";
				wait--;
				setTimeout(function() {
					time(o)
				}, 1000)
			}
		}
		
		
		$("#registid").on("click",function(){
			var companyname=$("#companyname").val().replace(/^\s+|\s+$/g,"");
			
			if(companyname==null||companyname==""){
				$.toptip('请输入企业名称', 'error');
				return false;
			}else{
				var flag1=true;
				$.ajax({
					url : "${path}users/checkcompany.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						companyname:companyname
					},
					success : function(data) {
						if(data.msg=='ok'){
							$.toptip('公司已存在!', 'error');
							flag1=false;
						}
					},
					error : function(data) {
						flag1=false;
					}
				});
				if(flag1==false){
					return flag1;
				}
			}
			
			var contactperson=$("#contactperson").val().replace(/^\s+|\s+$/g,"");
			
			if(contactperson==null||contactperson==""){
				$.toptip('请输入负责人', 'error');
				return false;
			}
			
			var address=$("#address").val();
			
			if(address==null||address==""){
				$.toptip('请选择所在地', 'error');
				return false;
			}
			
			var plantarea=$("#plantarea").val().replace(/^\s+|\s+$/g,"");
			var reg=/^[0-9]+(.[0-9]{0,2})?$/;
			if(!reg.test(plantarea)){
				$.toptip('请输入正确种植面积,最多两位小数', 'error');
				return false;
			}
			
			var plantyears=$("#plantyears").val().replace(/^\s+|\s+$/g,"");
			var r = /^\d{4}$/;
			if(!r.test(plantyears)){
				$.toptip('请输入正确种植年份,正整数', 'error');
				return false;
			}
			
			var username=$("#username").val().replace(/^\s+|\s+$/g,"");
			if(username==null||username==""){
				$.toptip('请输入注册人姓名', 'error');
				return false;
			}
			
			var telphone=$("#telphone").val().replace(/^\s+|\s+$/g,"");
			var re = /^(13[0-9]|17[0-9]|15[0-9]|18[0-9])\d{8}$/i;
			if(!re.test(telphone)){
				$.toptip('请输入正确手机号', 'error');
				return false;
			}else{
				var flag=true;
				$.ajax({
					url : "${path}users/checkphone.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						telphone:telphone
					},
					success : function(data) {
						if(data.msg=='ok'){
							$.toptip('电话号码已存在!', 'error');
							flag=false;
						}
					},
					error : function(data) {
						flag= false;
					}
				});
				if(flag==false){
					return flag;
				}
			}
			
			var password=$("#password").val().replace(/^\s+|\s+$/g,"");
			if(password==null || password==""){
				$.toptip('请输入密码', 'error');
				return false;
			}
			
			var code=$("#code").val().replace(/^\s+|\s+$/g,"");
			if(code!=null && code !=''){
				$.ajax({
					url : "${path}token/check.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						telphone:telphone,
						code:code,
						type:"1"
					},
					success : function(data) {
						if(data.msg=='ok'){
							$("#formid").submit();
						}else if(data.msg=='fail'){
							$.toptip('验证码错误!', 'error');
							return false;
						}else if(data.msg=='shixiao'){
							$.toptip('验证码已失效!', 'error');
							return false;
						}
					},
					error : function(data) {
						return false;
					}
				});
			}else{
				$.toptip('请输入验证码', 'error');
				return false;
			}
		})
		
		
		function checkphone(){
			var telphone=$("#telphone").val().replace(/^\s+|\s+$/g,"");
			if(telphone !=null && telphone!=""){
				$.ajax({
					url : "${path}users/checkphone.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						telphone:telphone
					},
					success : function(data) {
						if(data.msg=='ok'){
							$.toptip('电话号码已存在!', 'error');
							return false;
						}else if(data.msg=='fail'){
							return true;
						}
					},
					error : function(data) {
						return false;
					}
				});
				
			}
		}
		
		function checkcompany(){
			var companyname=$("#companyname").val().replace(/^\s+|\s+$/g,"");
			if(companyname !=null && companyname != ""){
				$.ajax({
					url : "${path}users/checkcompany.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						companyname:companyname
					},
					success : function(data) {
						if(data.msg=='ok'){
							$.toptip('公司已存在!', 'error');
							return false;
						}else if(data.msg=='fail'){
							return true;
						}
					},
					error : function(data) {
						return false;
					}
				});
			}
		}
	</script>
	
	

</body>
</html>