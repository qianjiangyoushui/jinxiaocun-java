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
.center-left {
  width: 47%;
  float: left;
  margin-left: 7px;
  margin-bottom: 7px;
  height: 70px;
  text-align: center;
  line-height:70px;
  font-size: 16px;
  top: 50%
}
.center-right {
  width: 47%;
  float: right;
  margin-left: 7px;
  margin-bottom: 7px;
  margin-right:5px;
  text-align: center;
  line-height:70px;
  height: 70px;
  font-size: 16px;
}
span {
	color: #FFFFFF;
}
</style>
<body style="background-color: #FFFFFF">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		 种薯质量可追溯系统
	</div>

	<div class="weui-grids">
	<c:forEach items="${menuList}" var="menu">
            <a href="${wechatPath}${menu.url}" class="weui-grid js_grid">
                <div class="weui-grid__icon">
                  <img src="${wechatPath}${menu.icon}" alt="">
                </div>
                <p class="weui-grid__label">
                  ${menu.name}
                </p>
            </a>
    </c:forEach>
     </div>
	<hr/>

	<div class="weui-footer">
	  <p class="weui-footer__links">
	    <a href="javascript:void(0);" class="weui-footer__link">${sessionScope.user.company.companyname}</a>
	  </p>
	  <p class="weui-footer__text">种薯质量可追溯</p>
	</div>
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
  	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
  	<script src="${wechatPath}js/weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	var departid='${sessionScope.user.depart.departid}';
	$("#employeeid").on("click",function(){
		if(departid=='2'||departid=='1'){
			window.location.href="${wechatPath}employee/list.action";
		}else{
			$.alert("您没有操作权限！");
		}
	})
	
	$("#g0").on("click",function(){
			window.location.href="${wechatPath}bottleseed/index.action";
	})
	</script>
</body>
</html>