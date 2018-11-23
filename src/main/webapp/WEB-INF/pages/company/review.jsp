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
		 注册企业审核
	</div>
<div class="weui-cells">
  <div class="weui-cell">
    <div class="weui-cell__bd">
      <p>企业名称</p>
    </div>
    <div class="weui-cell__ft">${company.companyname}</div>
  </div>
  <div class="weui-cell">
    <div class="weui-cell__bd">
      <p>负责人姓名</p>
    </div>
    <div class="weui-cell__ft">${company.contactperson}</div>
  </div>
  <div class="weui-cell">
    <div class="weui-cell__bd">
      <p>所在地区</p>
    </div>
    <div class="weui-cell__ft">${company.province}${company.city}${company.area}</div>
  </div>
  <div class="weui-cell">
    <div class="weui-cell__bd">
      <p>种植面积</p>
    </div>
    <div class="weui-cell__ft">${company.plantarea}</div>
  </div>
  <div class="weui-cell">
    <div class="weui-cell__bd">
      <p>种植起始年份</p>
    </div>
    <div class="weui-cell__ft">${company.plantyears}</div>
  </div>
  <div class="weui-cell">
    <div class="weui-cell__bd">
      <p>注册日期</p>
    </div>
    <div class="weui-cell__ft"><fmt:formatDate value="${company.registdate}" type="date"/></div>
  </div>
</div>

<div style="width:100%;margin-bottom:100px;"></div>
<c:choose>
   <c:when test="${company.status==2}">
   <a href="${wechatPath}company/check.action?mid=${mid}&guid=${company.guid}&status=1" class="weui-btn weui-btn_plain-default">已审核通过,点击后拒绝</a>
   </c:when>
   <c:otherwise>
   <a href="${wechatPath}company/check.action?mid=${mid}&guid=${company.guid}&status=2" class="weui-btn weui-btn_plain-primary">点击通过审核</a>
   </c:otherwise>
</c:choose>


	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
  	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script type="text/javascript">

	</script>
</body>
</html>