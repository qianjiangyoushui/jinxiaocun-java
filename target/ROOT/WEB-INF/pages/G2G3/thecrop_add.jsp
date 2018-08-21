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
		轮茬及用药记录录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a onclick="submitinfo();" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}thecrop/save.action" method="post" id="formid">
	<input type="hidden" name="type" value="${type}">
		<input type="hidden" value="${plotid }" name="plotid" />
		<input type="hidden" value="${type }" name="type" />
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种植年份</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择年份" name="year"  id="year" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden"  name="years"  id="years" />
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种植作物</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入种植作物名称" name="plantcrops" id="plantcrops" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药记录：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="medicate" id="medicate" placeholder="输入用药记录" rows="3" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
		</div>
	</form>
	<div style="height: 10px;width: 100%"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		function submitinfo(){
			
			var year=$("#year").data("values");
			if(year==""){
				$.toptip('请选择年份', 'error');
				return false;
			}
			$("#years").val(year);
			
			var plantcrops=$("#plantcrops").val().replace(/^\s+|\s+$/g,"");
			if(plantcrops==null||plantcrops==""){
				$.toptip('请输入种植作物名称', 'error');
				return false;
			}
			
			
			var medicate=$("#medicate").val().replace(/^\s+|\s+$/g,"");
			if(medicate==null||medicate==""){
				$.toptip('请输入用药记录', 'error');
				return false;
			}
			
			$("#formid").submit();
		}
		
		$("#year").select({
			  title: "选择年份",
			  items: [
			<c:forEach items="${years}" var="year">         
			    {
			      title: '${year}年',
			      value: ${year},
			    },
	    	</c:forEach>
			  ]
			});
		
	</script>
</body>
</html>