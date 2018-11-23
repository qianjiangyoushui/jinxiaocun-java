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
			事故详情
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
					<label class="weui-label">日期：</label>
				</div>
				<div class="weui-cell__bd">
					<fmt:formatDate value="${accident.reportdate}" pattern="yyyy-MM-dd"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">熏蒸情况：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.fumigation}
				</div>
			</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">入库前是否消毒：</label>
					</div>
					<div class="weui-cell__bd" style="text-align:center;">
						${accident.isdisinfect eq '0'?"否":"是" }
					</div>
				</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">温度保持范围：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.temprange }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">是否有变动：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.ischange eq '0'?"否":"是" }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">变动原因：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.changereason }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">目前库存吨数：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.instock }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害及生理异常发生日期：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					<fmt:formatDate value="${accident.occurdate }" pattern="yyyy-MM-dd"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害及生理异常发生位点：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.occurpoint }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害及生理异常发生类型：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.occurtype }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">库存马铃薯是否有位置变更：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.placechange  eq '0'?"否":"是" }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">变更详情：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.changedetail }
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">上报人员：</label>
				</div>
				<div class="weui-cell__bd" style="text-align:center;">
					${accident.reportname }
				</div>
			</div>
			
			<div class="weui-cell">
			<a  href="javascript:upload('${accident.guid}','事故图片','1');">
				<div class="weui-cell__hd">
					<label class="weui-label">拍照信息</label>
				</div>
				</a>
			</div>
			
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
		function upload(id,desc,type){
			desc=encodeURI(encodeURI(desc));
			window.location.href='${wechatPath}upload/upload.action?relatedid='+id+'&type='+type+'&url=${url}&operate=${sessionScope.operate}&description='+desc;
		}
		</script>
	
</body>
</html>