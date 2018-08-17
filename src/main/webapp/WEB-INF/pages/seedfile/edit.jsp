<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		核心苗信息修改
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	<div class="mui-content">
	<form action="${wechatPath}seedfile/update.action" method="post" id="formid" enctype="multipart/form-data">
		<input type="hidden" name="guid" value=${seedfile.guid }>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;font-size:14px;">
			<c:if test="${seedfile.batch ne null }">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">批次编号</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="年份+来源+品种+级别+序号" value="${seedfile.batch }" name="batch" id="batch" style="margin-top: 5px;margin-bottom: 5px;">
					</div>
					<input type="hidden" name="objtype" value="batch">
					<input type="hidden" name="objtypename" value="批次编号">
				</div>
			</c:if>
			<c:if test="${seedfile.description ne null }">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">档案描述</label>
				</div>
				
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"   placeholder=" 描述该核心苗的附加信息" value="${seedfile.description }"   name="description" id="description" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
				<input type="hidden" name="objtype" value="description">
				<input type="hidden" name="objtypename" value="档案描述">
			</div>
			</c:if>
			<c:if test="${seedfile.variety ne null }">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">品种</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="核心苗品种"   name="variety_" id="variety_" value="${variety.belongsname}" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
				<input type="hidden" name="variety"  id="variety" >
				<input type="hidden" name="objtype" value="variety">
				<input type="hidden" name="objtypename" value="品种">
			</div>
			</c:if>
			
				<c:if test="${seedfile.code ne null }">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">编号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder=" 内部编号，比如中加F14"  name="code" id="code" value="${seedfile.code }" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
				<input type="hidden" name="objtype" value="code">
				<input type="hidden" name="objtypename" value="编号">
			</div>
			</c:if>
				<c:if test="${seedfile.years ne null }">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">年份</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="核心苗育种年份或者引进年份" name="years" id="years" value="${seedfile.years }" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
				<input type="hidden" name="objtype" value="years">
				<input type="hidden" name="objtypename" value="年份">
			</div>
			</c:if>
				<c:if test="${seedfile.placeid ne null }">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在位置</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder=" 种质资源库"  name="placeid" id="placeid" value="${seedfile.placeid }" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
				<input type="hidden" name="objtype" value="placeid">
				<input type="hidden" name="objtypename" value="种质资源库">
			</div>
			</c:if>
			
			<c:if test="${listimage!= null && fn:length(listimage) > 0}">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害监测报告</label>
				</div>
				<div class="weui-cell__bd">
					<c:forEach items="${listimage}" var="item">
						<img alt="" src="${item.url}">
					</c:forEach>
				</div>
				<input type="hidden" name="objtype" value="upimage">
				<input type="hidden" name="objtypename" value="病害监测报告">
			</div>
			</c:if>
		</div>
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class=" mui-action-back mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;" value="取消">
			<input type="submit" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;" value="保存">
		</div>
		</form>
</div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	<script type="text/javascript">
	var year=${getSysYear};
	var dictionary=${dictionary};
	$("#years").select({
		  title: "育种年份或引进年份",
		  items:year,
		});
	$("#variety_").select({
		  title: "核心苗品种",
		  items:dictionary
		});
	$("#formid").submit(function(){
		<c:if test="${!empty seedfile.variety}">
			$("#variety").attr("value",$("#variety_").attr("data-values"));
		</c:if>
		
	})
	</script>
	<script type="text/javascript">
	
	</script>
</body>
</html>