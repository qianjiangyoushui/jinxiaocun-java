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
		<c:if test="${type==1 }">批次入库</c:if>
		<c:if test="${type==2}">批次出库</c:if>
		<c:if test=""></c:if>
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick=" subinfo('${type}','${depot.guid }');"  style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="subinfo('${type}','${depot.guid }');" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}depot/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入时间"
							name="recorddate" id="recorddate"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">温度</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" 
						placeholder="请输入温度" name="temperature"
						id="temperature" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">湿度</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="number"  name="humidity" id="humidity"
							style="margin-top: 5px; margin-bottom: 5px;" placeholder="请输入湿度"/>
					</div>
				</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">备注：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入备注"
							name="operatorid" id="operatorid"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell" style="background-color:white;">
				<div class="mui-content" style="background-color:#fff;border-color:#fff">
			    <ul class="mui-table-view mui-grid-view" id="images" style="background-color:#fff;padding:0;">
				</ul>
				</div>
			</div>
		</div>
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
			<input type="button" onclick="subinfo('${type}','${depot.guid }')" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
			<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
		<script type="text/javascript">
		$("#recorddate").calendar();
		
		
		
			function subinfo(type,depotid){

				var flag=true;
				$(".weui-input").each(function (i){
					if($(this).attr("value")==null||$(this).attr("value")==""){
						flag=false;
					}
				})
				
				if(flag==true){
					$.ajax({
						url : "${wechatPath}environment/save.action",
						type : "POST",
						async : false,
						dataType : 'json',
						data : {
							recorddate:$("#recorddate").attr("value"),
							temperature:$("#temperature").attr("value"),
							humidity:$("#humidity").attr("value"),
							operatorid:$("#operatorid").attr("value"),
							depotid:depotid
						},
						success : function(data) {
							$.hideLoading();
							if(data.msg=='ok'){
								$.confirm("新增成功", function() {
									window.location.href="${wechatPath}environment/environment_index.action?guid=${depot.guid}";
							  }, function() {
								  
							  });
							}
						},
						error : function(data) {
							$.hideLoading();
							$.alert("失败");
						}
					});
				}else{
					$.toptip('请完善数据!', 'error');
				}
			}
			
		</script>
	
</body>
</html>