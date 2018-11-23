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
		查看植保方案实施情况
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	
	<form action="${wechatPath}eppo/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${eppo.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择日期" readonly="readonly" name="dodate" id="dodate" style="margin-top: 5px;margin-bottom: 5px" value="<fmt:formatDate value="${eppo.dodate}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用途</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用途" readonly="readonly" name="usetype" id="usetype" style="margin-top: 5px;margin-bottom: 5px;" value="${eppo.usetype}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药种类</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药种类" readonly="readonly" name="drugkind" id="drugkind" style="margin-top: 5px;margin-bottom: 5px;" value="${eppo.drugkind}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药总量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药总量" readonly="readonly" name="drugdose" id="drugdose" style="margin-top: 5px;margin-bottom: 5px;width:110px" value="${eppo.drugdose}">
					<input class="weui-input" type="text" placeholder="单位" readonly="readonly" name="drugdoseunit" id="drugdoseunit" style="margin-top: 5px;margin-bottom: 5px;width:110px" value="${eppo.drugdoseunit}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">平米用药量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入平米用药量" readonly="readonly" name="squaredose" id="squaredose" style="margin-top: 5px;margin-bottom: 5px;width:110px" value="${eppo.squaredose}">
					<input class="weui-input" type="text" placeholder="单位" readonly="readonly" name="squarunit" id="squarunit" style="margin-top: 5px;margin-bottom: 5px;width:110px" value="${eppo.squarunit}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">总用水量(L)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入总用水量" readonly="readonly" name="waterdose" id="waterdose" style="margin-top: 5px;margin-bottom: 5px;" value="${eppo.waterdose}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药方式</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药方式" readonly="readonly" name="drugusetype" id="drugusetype" style="margin-top: 5px;margin-bottom: 5px;" value="${eppo.drugusetype}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药时间段</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="startdate" readonly="readonly" id="startdate" style="margin-top: 5px;margin-bottom: 5px; width:110px" value="${eppo.startdate}">
					<input class="weui-input" type="text" name="enddate" readonly="readonly" id="enddate" style="margin-top: 5px;margin-bottom: 5px; width:110px" value="${eppo.enddate}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">操作人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="请输入操作人" readonly="readonly" name="principal" id="principal" style="margin-top: 5px;margin-bottom: 5px;" value="${eppo.principal}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入负责人" readonly="readonly" name="uploadperson" id="uploadperson" style="margin-top: 5px;margin-bottom: 5px;" value="${eppo.uploadperson}">
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