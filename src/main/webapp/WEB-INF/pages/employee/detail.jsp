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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		职工用户信息
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}employee/update.action" method="post" id="formid">
		<input  type="hidden" name="guid" value="${users.guid}"/>
		<input  type="hidden" name="departguid" value="${users.depart.guid}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">职工姓名</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" value="${users.username}" name="username" id="username" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">手机号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="tel" pattern="[0-9]*"  value="${users.telphone}"  onkeyup="checkphone();" name="telphone" id="telphone" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
<!-- 			<div class="weui-cell"> -->
<!-- 				<div class="weui-cell__hd"> -->
<!-- 					<label class="weui-label">登录密码</label> -->
<!-- 				</div> -->
<!-- 				<div class="weui-cell__bd"> -->
<%-- 					<input class="weui-input" type="password"  value="${users.password}" name="password" id="password" style="margin-top: 5px;margin-bottom: 5px;"> --%>
<!-- 				</div> -->
<!-- 			</div> -->
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在部门</label>
				</div>
				<div class="weui-cell__bd"> 
					<select  name="departid"  id="departid" style="margin-top: 5px;margin-bottom: 5px;">
						<c:forEach items="${dicts}" var="dict">
							<option value="${dict.keyvalue}" <c:if test="${users.depart.departid eq dict.keyvalue }"> selected="selected"</c:if>>${dict.belongsname}</option>
						</c:forEach>
					</select>
				</div>							
			</div>
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		var oldphone='${users.telphone}';
		$("#submitid").on("click",function(){
			var username=$("#username").val().replace(/^\s+|\s+$/g,"");
			if(username==null||username==""){
				$.toptip('请输入职工姓名', 'error');
				return false;
			}
			
			
			var telphone=$("#telphone").val().replace(/^\s+|\s+$/g,"");
			
			if(telphone != oldphone){
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
			}
			
// 			var password=$("#password").val().replace(/^\s+|\s+$/g,"");
// 			if(password==null || password==""){
// 				$.toptip('请输入密码', 'error');
// 				return false;
// 			}
			$("#formid").submit();
		})
		
		
		function checkphone(){
			var telphone=$("#telphone").val().replace(/^\s+|\s+$/g,"");
			if(telphone!=null && telphone!="" && telphone !=oldphone){
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
		
	</script>
</body>
</html>