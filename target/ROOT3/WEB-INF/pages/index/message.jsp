<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/demos.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">

<!--标准mui.css-->
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<style type="text/css">
.card-body{
    margin-left:5px;
    margin-right:5px;
    width:98%;
    display:-webkit-flex;
    display: flex;
    flex-direction:column;
    justify-content:center;
    align-items:flex-start;
    align-content:center;
    padding-left:10px;
    margin-top:10px;
    border:1px solid #BEBEBE;
    border-radius:10px;
    background-color: #FFFFFF;
}
.card-tittle{
    padding-top:3px;
}
.card-des{
    font-style:italic;
    font-size:14px;
    padding-top:3px;
}
.card-sub{
    display:-webkit-flex;
    display: flex;
    flex-direction:row;
    justify-content:space-around;
    align-items:center;
    align-content:center;
    font-size:14px;
    padding-top:3px;
    padding-bottom:3px;
}
.card-link{
    width:100%;
    border-top:1px solid #E0E0E0;
    color:#0080FF;
    align-self:center;
}
.card-date{
    font-size:12px;
}
.card-state{
    font-size:12px;
    margin-left:10px;
}
</style>
<body style="background-color: #F0F0F0">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		 种薯质量可追溯系统
	</div>
<c:forEach items="${messageList}" var="message">
    <div class="card-body">
        <div class="card-tittle">${message.tittle}</div>
        <div class="card-des">${message.des}</div>
        <div class="card-sub"><div class="card-date"><fmt:formatDate value="${message.createdate}" type="date"/></div><div class="card-state">${message.state}</div></div>
        <a href="${wechatPath}${message.url}?&busid=${message.busid}&mid=${message.guid}"  class="card-link">处理</a>
    </div>
</c:forEach>



	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
  	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	</script>
</body>
</html>