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
		售后服务信息
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}saledafter/edit.action?clientid=${saledservice.clientid}&guid=${saledservice.guid}" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}saledafter/edit.action?clientid=${saledservice.clientid}&guid=${saledservice.guid}"  style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">编辑</a>
	</div>
	
	<form action="${wechatPath}saledafter/edit.action" method="post" id="formid">
		<input  type="hidden" id="clientid" name="clientid" value="${client.guid}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				销售记录：
					${saledservice.batch }
			</div>
			
			
			<div class="weui-cell">
				培养方式:
					${saledservice.traintype }
			</div>
			
			<div class="weui-cell">
				种植密度：${saledservice.tlantdensity }
			</div>
			
			<div class="weui-cell">
				切刀是否消毒：${saledservice.isdisinfect eq 0?'否':'是' }
			</div>
			
			<div class="weui-cell">
				烂薯是否剔除：${saledservice.isremoved eq 0?'否':'是'  }
			</div>
			
			<div class="weui-cell">
				烂薯率：${saledservice.rottenrate }
			</div>
			
			<div class="weui-cell">
				切种时是否拌药：${saledservice.ismedicine eq 0?'否':'是' }
			</div>
			
			<div class="weui-cell">
				拌药种类：${saledservice.medicinetype }
			</div>
			
			<div class="weui-cell">
				播种时否沟施杀菌剂：
					${saledservice.isdisinfecte eq 0?'否':'是' }
										
			</div>
			
			<div class="weui-cell">
				沟施杀菌剂种类：
					${saledservice.disinfecttype }
			</div>
			
			<div class="weui-cell">
				浇水情况：
					${saledservice.watering }
			</div>
			
			<div class="weui-cell">
				施肥情况：
					${saledservice.manure }
			</div>
			
			<div class="weui-cell">
				病虫害防治情况：
					${saledservice.pests }
			</div>
			
			<div class="weui-cell">
				记录人：
					${saledservice.recordby }
			</div>
			
			<div class="weui-cell">
				时间：
				<fmt:formatDate value="${saledservice.lastupdate }" pattern="yyyy-MM-dd"/>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">拍照上传：</label>
				</div>
				<div class="weui-cell__bd">
				<c:forEach var="img" items="${listimg}">
					<img src="${img.url }" style="height:50px;width:50px;">
				</c:forEach>
				</div>
			</div>
		</div>
		
		
		<div class="mui-content" style="background-color:#fff">
		    <ul class="mui-table-view mui-grid-view" id="images">
		    	
			</ul>
		</div>
	</form>
	<div style="height: 10px;width: 100%"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
</body>
</html>