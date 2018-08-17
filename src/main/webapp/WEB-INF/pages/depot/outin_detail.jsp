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
		<c:if test="${outinstorage.type==1 }">批次入库</c:if>
		<c:if test="${outinstorage.type==2}">批次出库</c:if>
		<c:if test=""></c:if>
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
<%-- 		<a href="javascript:;" onclick=" subinfo('${type}','${depot.guid }');"  style="position: absolute; right: 15px; top: 0px;"> --%>
<%-- 			<img src="${wechatPath}icon/save.png" style="width: 20px" /> --%>
<!-- 		</a> -->
<%-- 		<a href="javascript:;" onclick="subinfo('${type}','${depot.guid }');" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a> --%>
	</div>
	
	<form action="${wechatPath}depot/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种薯批次：</label>
				</div>
				<div class="weui-cell__bd">
					${outinstorage.batchcode}
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label"><c:if test="${outinstorage.type==1 }">入库吨数</c:if>
											<c:if test="${outinstorage.type==2}">出库吨数</c:if></label>
				</div>
				<div class="weui-cell__bd">
					${outinstorage.outamount}
				</div>
			</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label"><c:if test="${outinstorage.type==1}">贮存方式：</c:if>
											<c:if test="${outinstorage.type==2}">出库用途：</c:if></label>
					</div>
					<div class="weui-cell__bd">
						${outinstorage.outuse }
					</div>
				</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label"><c:if test="${outinstorage.type==1 }">入库时间</c:if>
											<c:if test="${outinstorage.type==2}">出库时间</c:if></label>
				</div>
				<div class="weui-cell__bd">
					<fmt:formatDate value="${outinstorage.outdate }" pattern="yyyy-MM-dd"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">备注：</label>
				</div>
				<div class="weui-cell__bd">
					${outinstorage.description }
				</div>
			</div>
			
			<div class="weui-cell">
			<a  href="javascript:upload('${outinstorage.guid}','出入库图片','1');">
				<div class="weui-cell__hd">
					<label class="weui-label">拍照信息</label>
				</div>
				</a>
			</div>
			
		</div>
<!-- 		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;"> -->
<!-- 			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消"> -->
<%-- 			<input type="button" onclick="subinfo('${type}','${depot.guid }')" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存"> --%>
<!-- 		</div> -->
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
		function upload(id,desc,type){
			desc=encodeURI(encodeURI(desc));
			window.location.href='${wechatPath}upload/upload.action?relatedid='+id+'&type='+type+'&url=${url}&operate=${sessionScope.operate}&description='+desc;
		}
		</script>
	
</body>
</html>