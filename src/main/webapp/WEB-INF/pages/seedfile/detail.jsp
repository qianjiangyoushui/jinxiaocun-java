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
<link rel="stylesheet" href="${wechatPath}css/mui/baocun.css">
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		核心苗详情
		<c:choose>
		<c:when test="${!empty sessionScope.operate}">
			<a href="${wechatPath}bottleseed/detail.action?seedfileid=${sessionScope.g0guid}" style="position: absolute; left: 10px; top: 8px">
				<img src="${wechatPath}icon/back.png" style="width: 20px" />
			</a>
		</c:when>
		<c:otherwise>
			<a href="${wechatPath}seedfile/list.action?years=${seedfile.years}" style="position: absolute; left: 10px; top: 8px">
				<img src="${wechatPath}icon/back.png" style="width: 20px" />
			</a>
		</c:otherwise>
		</c:choose>
	</div>
	<div class="mui-content">
	
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;font-size:14px;">
			
			<ul class="mui-table-view" style="margin-top:10px;">
				 <li class="mui-table-view-cell" >
				<a class="" href="#" style="font:normal normal 14px/1.5em arial;color:#525e71">批次编号:${seedfile.batch}</a>
				 </li>
			 </ul>
			 <ul class="mui-table-view" style="margin-top:10px;">
				 <li class="mui-table-view-cell" >
				<a class="" href="#" style="font:normal normal 14px/1.5em arial;color:#525e71">扩繁级别:${seedfile.levels.belongsname}</a>
				 </li>
			 </ul>
			<ul class="mui-table-view" style="margin-top:10px;">
				<a class="mui-navigate-right" method="post"  para="description"  paravalue="${seedfile.description }" value="${wechatPath}seedfile/edit.action?guid=${seedfile.guid }" style="font:normal normal 14px/1.5em arial;color:#525e71">
				 <li class="mui-table-view-cell" >
				档案描述:${seedfile.description }
				 </li>
				 </a>
			 </ul>
			<ul class="mui-table-view" style="margin-top:10px;">
				<a class="mui-navigate-right" method="post" para="variety"  paravalue="${seedfile.variety}" value="${wechatPath}seedfile/edit.action?guid=${seedfile.guid }" style="font:normal normal 14px/1.5em arial;color:#525e71">
				 <li class="mui-table-view-cell" >
				品种:${seedfile.varietys.belongsname}
				 </li>
				 </a>
			 </ul>
			<ul class="mui-table-view" style="margin-top:10px;">
				<a class="mui-navigate-right"  method="post" para="code"  paravalue="${seedfile.code}" value="${wechatPath}seedfile/edit.action?guid=${seedfile.guid }" style="font:normal normal 14px/1.5em arial;color:#525e71">
				 <li class="mui-table-view-cell" >
				编号:${seedfile.code }
				 </li>
				 </a>
			 </ul>
			<ul class="mui-table-view" style="margin-top:10px;">
				<a class="mui-navigate-right" method="post" para="years"  paravalue="${seedfile.years }" value="${wechatPath}seedfile/edit.action?guid=${seedfile.guid }" style="font:normal normal 14px/1.5em arial;color:#525e71">
				 <li class="mui-table-view-cell" >
				年份:${seedfile.years }
				 </li>
				 </a>
			 </ul>
			<ul class="mui-table-view" style="margin-top:10px;">
				 <li class="mui-table-view-cell" >
				<a class="" href="#" style="font:normal normal 14px/1.5em arial;color:#525e71">所在位置:${seedfile.placeid }</a>
				 </li>
			 </ul>
			<ul class="mui-table-view" style="margin-top:10px;">
				<a   onclick="upload('${seedfile.guid}','病害监测报告','1')" style="font:normal normal 14px/1.5em arial;color:#525e71">
				 <li class="mui-table-view-cell" >
				病害监测报告
				 </li>
				 </a>
			 </ul>
			
			<ul class="mui-table-view" style="margin-top:10px;">
				<a  href="${wechatPath}logs/list.action?relatedid=${seedfile.guid}" style="font:normal normal 14px/1.5em arial;color:#525e71"> 
				 <li class="mui-table-view-cell" >
				变动历史
				 </li>
				 </a>
			 </ul>
		</div>
		<form action="${wechatPath}seedfile/edit.action" method="post" id="formid" enctype="multipart/form-data">
			<input class="postpara" type="hidden" name="" value="">
		</form>
</div>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
		<script type="text/javascript">
		function upload(id,desc,type){
			desc=encodeURI(encodeURI(desc));
			window.location.href='${wechatPath}upload/upload.action?relatedid='+id+'&type='+type+'&url=${url}&operate=${sessionScope.operate}&description='+desc;
		}
		
			$(".mui-navigate-right").each(function (i){
				$(this).on("tap",function(){
				$(".postpara").attr("name",$(this).attr("para"))
				$(".postpara").attr("value",$(this).attr("paravalue"))
				$("#formid").attr("method",$(this).attr("method"));
				$("#formid").attr("action",$(this).attr("value")).submit();
			})
			})
		</script>
</body>
