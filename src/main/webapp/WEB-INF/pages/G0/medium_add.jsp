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
		培养基信息录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}medium/save.action" method="post" id="formid">
		<input  type="hidden" name="seedfileid" value="${seedfileid}"/>
		<input  type="hidden" name="operate" value="${operate}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">培养基名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入培养基名称" name="mediumname" id="mediumname" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">培养基来源</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择培养基来源" name="comefromss" id="comefromss" style="margin-top: 5px;margin-bottom: 5px;">
					<input  type="hidden" value="" name="comefrom" id="comefrom"/>
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">比例描述</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" placeholder="请输入培养基比例描述" name="scale" id="scale" rows="3" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
		</div>
		
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
			<input type="button" onclick="submitinfo();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	
		function submitinfo(){
			var mediumname=$("#mediumname").val().replace(/^\s+|\s+$/g,"");
			if(mediumname==null||mediumname==""){
				$.toptip('请输入培养基名称', 'error');
				return false;
			}
			
			var comefrom=$("#comefromss").data("values");
			$("#comefrom").val(comefrom);
			if($("#comefrom").val()==""){
				$.toptip('请选择培养基来源', 'error');
				return false;
			}
			
			
			var scale=$("#scale").val().replace(/^\s+|\s+$/g,"");
			if(scale==null||scale==""){
				$.toptip('请输入培养基描述', 'error');
				return false;
			}
			
			$("#formid").submit();
		}
		
		$("#comefromss").select({
			  title: "选择培养基来源",
			  items: [
			<c:forEach items="${comes}" var="come">
			    {
			      title: '${come.belongsname}',
			      value: '${come.keyvalue}',
			    },
	    	</c:forEach>
			  ]
			});
		
	</script>
</body>
</html>