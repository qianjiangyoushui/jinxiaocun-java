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
		添加区域经理
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
	</div>
	
	<form action="${wechatPath}employee/areaupdate.action" method="post" id="formid">
	    <input type="hidden" name="guid" value="${areapresident.guid}"/>
	    <input type="hidden" name="userid" value="${areapresident.userid}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">职工姓名</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" value="${areapresident.users.username}" readonly="readonly"name="username" id="username" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">手机号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="tel" pattern="[0-9]*"  value="${areapresident.users.telphone}"readonly="readonly" name="telphone" id="telphone" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责区域</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="选择区域" value="${areapresident.area}" name="area" id="batch" style="margin-top: 5px;margin-bottom: 5px;">
					<input class="weui-input" type="hidden"name="roles" id="roles" >
				</div>
			</div>



	<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="button" onclick="operation.delete()" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="删除">
			<input type="button" onclick="operation.update();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="更改">
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
			}
			var password=$("#password").val().replace(/^\s+|\s+$/g,"");
			if(password==null || password==""){
				$.toptip('请输入密码', 'error');
				return false;
			}
			$("#formid").submit();
		});
		var operation = {
		    update:function(){
		        $("#formid").submit();
		    },
		    delete:function(){
		      $.confirm("确定删除该区域经理吗？", function() {
              $("#formid").attr('action',"${wechatPath}employee/areadelete.action");
              $("#formid").submit();
              }, function() {
              });
		    }
		};

        $("#batch").select({
              title: "选择角色",
              multi: true,
              items: ["北京",
                      "天津",
                      "河北省",
                      "山西省",
                      "内蒙古自治区",
                      "辽宁省",
                      "吉林省",
                      "黑龙江省",
                      "上海",
                      "江苏省",
                      "浙江省",
                      "安徽省",
                      "福建省",
                      "江西省",
                      "山东省",
                      "河南省",
                      "湖北省",
                      "湖南省",
                      "广东省",
                      "广西壮族自治区",
                      "海南省",
                      "重庆",
                      "四川省",
                      "贵州省",
                      "云南省",
                      "西藏自治区",
                      "陕西省",
                      "甘肃省",
                      "青海省",
                      "宁夏回族自治区",
                      "新疆维吾尔自治区",
                      "台湾省",
                      "香港特别行政区",
                      "澳门特别行政区"],
              onClose:function(e){
                batchArray=[];
                var data = e.data.valuesArray;
                for(let i=0;i<data.length;i++){
                    batchArray.push(data[i]);
                }
                $("#roles").val(batchArray);
              }
            });
	</script>
</body>
</html>