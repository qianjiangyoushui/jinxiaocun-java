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
		<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
		<link rel="stylesheet" href="${wechatPath}css/mui/app.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
		<link rel="stylesheet" href="${wechatPath}css/swiper-3.3.1.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">
<style>
			.mui-card .mui-control-content {
				padding: 10px;
			}
			
			.mui-control-content {
				height: 150px;
			}
		</style>
<body style="background-color: #FFFFFF">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		档案修改历史
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	
	<div class="bd">
		<div class="weui-cells">
		  <c:choose>
		  	<c:when test="${!empty logs}">
		  		<c:forEach var="log" items="${logs}">
		          <a class="weui-cell weui-cell_access" href="javascript:;">
		            <div class="weui-cell__bd" style="margin-top:10px;">
		              <p style="font-size:17px;color:#000000"><fmt:formatDate value="${log.operatedate}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;${log.username}${log.description}</p>
		            </div>
		            <div class="weui-cell__ft"></div>
		          </a>
		        </c:forEach>
	        </c:when>
	        <c:otherwise>
	        	<div class="weui-loadmore" >
					<span class="weui-loadmore__tips">没有数据...</span>
				</div>
	        </c:otherwise>
          </c:choose>
        </div>
	</div>

<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
	
</body>
</html>