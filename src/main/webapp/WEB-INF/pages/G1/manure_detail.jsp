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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		查看施肥记录
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		 </a>
			<%--<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/save.png" style="width: 20px" />
			</a>
			<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a> --%>
	</div>
	
	<form action="${wechatPath}manure/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${manure.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">施用日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="manuredate" id="manuredate" style="margin-top: 5px;margin-bottom: 5px" readonly="readonly" value="<fmt:formatDate value="${manure.manuredate}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">肥料名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入肥料名称" name="muck" id="muck" style="margin-top: 5px;margin-bottom: 5px;" readonly="readonly" value="${manure.muck}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">肥料厂家</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入肥料厂家" name="muckfactory" id="muckfactory" style="margin-top: 5px;margin-bottom: 5px;" readonly="readonly" value="${manure.muckfactory}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">有效成分含量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入有效成分含量" name="content" id="content" style="margin-top: 5px;margin-bottom: 5px;" readonly="readonly" value="${manure.content}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">亩施用量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="亩施用量" name="dosage" id="dosage" value="${manure.dosage }" style="margin-top: 5px;margin-bottom: 5px;width:110px" readonly="readonly">
					<input class="weui-input" type="text" placeholder="单位" name="dosageunit" id="dosageunit" value="${manure.dosageunit}" style="margin-top: 5px;margin-bottom: 5px;width:110px" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">施用方法</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入施用方法" name="usetype" id="usetype" style="margin-top: 5px;margin-bottom: 5px;" readonly="readonly" value="${manure.usetype}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">施用人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入施用人" name="byperson" id="byperson" style="margin-top: 5px;margin-bottom: 5px;" readonly="readonly" value="${manure.byperson}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入负责人" name="principal" id="principal" style="margin-top: 5px;margin-bottom: 5px;" readonly="readonly" value="${manure.principal}">
				</div>							
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">拍照上传</label>
				</div>
				<div class="weui-cell__bd">
					<!-- <input class="" accept="image/*"  id="imgElement" type="file"  style="margin-top: 5px; margin-bottom: 5px;" /> -->
					<a href="javascript:;" id="pb1" class="weui-btn weui-btn_mini weui-btn_default" style="height:25px;">点击查看</a>
				</div>
			</div>
			
			
		</div>
	</form>
<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/swiper.js"></script>
	<script type="text/javascript">
	var pb1 = $.photoBrowser({
        items: [
			<c:forEach items="${list}" var="image">
				"${image.url}",
			</c:forEach>
        ],

        onSlideChange: function(index) {
          console.log(this, index);
        },

        onOpen: function() {
          console.log("onOpen", this);
        },
        onClose: function() {
          console.log("onClose", this);
        }
      });
	
      $("#pb1").click(function() {
        pb1.open();
      });
      
		
	</script>
</body>
</html>