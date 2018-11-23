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
		添加职工用户
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}employee/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">职工姓名</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入职工姓名" name="username" id="username" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">手机号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="tel" pattern="[0-9]*"  placeholder="请输入手机号" onkeyup="checkphone();" name="telphone" id="telphone" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">登录密码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="password"  placeholder="请输入密码" name="password" id="password" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在部门</label>
				</div>
				<div class="weui-cell__bd">
					<select  name="departid"  id="departid" style="margin-top: 5px;margin-bottom: 5px;">
						<c:forEach items="${dicts}" var="dict">
							<option value="${dict.keyvalue}">${dict.belongsname}</option>
						</c:forEach>
					</select>
				</div>							
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">选择角色</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="选择角色" name="batch" id="batch" style="margin-top: 5px;margin-bottom: 5px;">
					<input class="weui-input" type="hidden"name="roles" id="roles" >
				</div>
			</div>


		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	var batchArray=[];
		$("#submitid").on("click",function(){
			var username=$("#username").val().replace(/^\s+|\s+$/g,"");
			if(username==null||username==""){
				$.toptip('请输入职工姓名', 'error');
				return false;
			}
			var telphone=$("#telphone").val().replace(/^\s+|\s+$/g,"");
			var re = /^(13[0-9]|17[0-9]|15[0-9]|18[0-9])\d{8}$/i;
			if(!re.test(telphone)){
				$.toptip('请输入正确手机号', 'error');
				return false;
			}else{
				var flag=checkphone();
				if(!flag){
					$.toptip('电话号码已存在!', 'error');
					return false;
				}
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
						}
					},
					error : function(data) {
						return false;
					}
				});
			}
			
			var password=$("#password").val().replace(/^\s+|\s+$/g,"");
			if(password==null || password==""){
				$.toptip('请输入密码', 'error');
				return false;
			}
			$("#formid").submit();
		})
		
		
		function checkphone(){
			var telphone=$("#telphone").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			if(telphone!=null && telphone!=""){
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
						}else if(data.msg=='fail'){
							flag= true;
						}
					},
					error : function(data) {
						flag= false;
					}
				});
			}
			return flag;
		};
        $("#batch").select({
              title: "选择角色",
              multi: true,
              items: [],
              onClose:function(e){
                batchArray=[];
                var data = e.data.valuesArray;
                for(let i=0;i<data.length;i++){
                    batchArray.push(data[i]);
                }
                $("#roles").val(batchArray);
              }
            });
            $(function(){
            $.ajax({
                url : "${wechatPath}employee/roleList.action",
                type : "GET",
                async : false,
                dataType : 'json',
                data : {
                },
                success : function(data) {
                    let items = data;
                    let persons = [];
                    batchItem = "[";
                    for(let i=0;i<items.length;i++){
                        let item = items[i];
                        persons.push({title: item.title, value: item.value});
                     }
                    $("#batch").select("update", { items: persons })
                },
                error : function(data) {
                    $.hideLoading();
                    $.alert("失败"+data);
                }
            });
            });
	</script>
</body>
</html>