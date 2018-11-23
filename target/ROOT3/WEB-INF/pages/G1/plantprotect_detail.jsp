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
			<%-- <a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/save.png" style="width: 20px" />
			</a>
			<a href="javascript:;" id="submitid" class="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a> --%>
	</div>
	
	<form action="${wechatPath}plantprotect/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${plantprotect.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择日期" name="dodate" id="dodate" style="margin-top: 5px;margin-bottom: 5px" value="<fmt:formatDate value="${plantprotect.dodate}" pattern="yyyy-MM-dd"/>" readonly="readonly">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用途</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用途" name="usetype" id="usetype" style="margin-top: 5px;margin-bottom: 5px;" value="${plantprotect.usetype}" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药种类</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药种类" name="drugkind" id="drugkind" style="margin-top: 5px;margin-bottom: 5px;" value="${plantprotect.drugkind}" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">亩用药量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入亩用药量" name="drugdose" id="drugdose" style="margin-top: 5px;margin-bottom: 5px; width:110px" value="${plantprotect.drugdose}" readonly="readonly">
					<input class="weui-input" type="text" placeholder="单位" name="drugdoseunit" id="drugdoseunit" style="margin-top: 5px;margin-bottom: 5px;width:110px" value="${plantprotect.drugdoseunit}" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">亩用水量(L)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入亩用水量" name="waterdose" id="waterdose" style="margin-top: 5px;margin-bottom: 5px;" value="${plantprotect.waterdose}" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药方式</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药方式" name="drugusetype" id="drugusetype" style="margin-top: 5px;margin-bottom: 5px;" value="${plantprotect.drugusetype}" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药时间段</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="startdate" id="startdate" style="margin-top: 5px;margin-bottom: 5px; width:110px" value="${plantprotect.startdate}" readonly="readonly">
					<input class="weui-input" type="text" name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px; width:110px" value="${plantprotect.enddate}" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">上传人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" readonly="readonly" placeholder="请输入上传人" name="uploadperson" id="uploadperson" style="margin-top: 5px;margin-bottom: 5px;" value="${user.username}" readonly="readonly">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">图片</label>
				</div>
				<div class="weui-cell__bd">
					<!-- <input class="" accept="image/*"  id="imgElement" type="file"  style="margin-top: 5px; margin-bottom: 5px;" /> -->
					<a href="javascript:;" id="pb1" class="weui-btn weui-btn_mini weui-btn_default" style="height:25px;">点击查看</a>
				</div>
			</div>
			
			<!-- <div class="button_sp_area" style="margin:0 30%;">
		        <a href="javascript:history.go(-1);" class="weui-btn weui-btn_mini weui-btn_primary">取消</a>
		        <a href="javascript:;"   class=" submitid weui-btn weui-btn_mini weui-btn_default">确认</a>
	      	</div> -->
			
			<div class="mui-content" style="background-color:#fff">
		    	<ul class="mui-table-view mui-grid-view" id="images">
		    	
				</ul>
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