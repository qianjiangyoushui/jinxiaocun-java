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
		销售档案详情
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}sales/edit.action?clientid=${clientid}&salesid=${salesrecord.guid}"  style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">修改</a>
	</div>
	 
	<form action="${wechatPath}sales/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					种薯批次档案：${seed.batch}
				</div>
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					销售数量（吨）：${salesrecord.salesamount}
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					销售日期：
					<fmt:formatDate value="${salesrecord.salesdate}" pattern="yyyy-MM-dd"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					运输车牌号：${salesrecord.carlicense}
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					贮存仓库：${depot.depotcode}
				</div>
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					销售客户：${client.clientname}
				</div>
			</div>
			
		</div>
	</form>
	<div style="height: 10px;width: 100%"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
</body>
</html>