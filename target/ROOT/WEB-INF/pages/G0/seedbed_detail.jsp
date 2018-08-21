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
<style>
.center-left {
  width: 47.3%;
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
  width: 47.3%;
  float: right;
  margin-left: 7px;
  margin-bottom: 7px;
  margin-right:5px;
  text-align: center;
  line-height:70px; 
  height: 70px;
  font-size: 16px;
}
</style>
<body >
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		组培室信息
			<c:choose>
				<c:when test="${operate eq '1'}">
					<a href="${wechatPath}bottleseed/index.action" style="position: absolute; left: 10px; top: 8px">
				</c:when>
				<c:otherwise>
					<a href="${wechatPath}seedbed/list.action" style="position: absolute; left: 10px; top: 8px">
				</c:otherwise>
			</c:choose>
			
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div>
		<div style="height: 222px;border:1px solid  #F0F0F0;background-color: #FFFFFF "  >
			<div style="height: 52px;line-height: 52px;width: 50%;float: left;">
				<span style="color: #FF9800;margin-left: 18px;">${seedbed.seedbedname}</span>
			</div>
			<div style="height: 52px;line-height: 52px;width: 50%;float: right;"></div>
		</div>
		
		<c:choose>
			<c:when test="${!empty seedbed.seedfiles}">
				<c:forEach var="seed" items="${seedbed.seedfiles}">
				<div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >
					<div class="bd">
						<div>
						<c:if test="${!empty seed.guid}">
				          <a class="weui-cell weui-cell_access" href="${wechatPath}bottleseed/detail.action?seedfileid=${seed.guid}&operate=${operate}">
				         </c:if>
				          <c:if test="${empty seed.guid}">
				          <a class="weui-cell weui-cell_access" href="javascript:;">
				         </c:if>
				            <div class="weui-cell__bd">
				              <strong style="font-size: 16px;color: #000000;">批次${seed.batch} &nbsp; ${seed.varietys.belongsname}</strong>
				              <p></p>
				              <span style="font-size: 12px;color: #666666">种植组培室：${seedbed.seedbedname}</span><br/>
				              <span style="font-size: 12px;color: #666666">扩繁时间段：<fmt:formatDate value="${seed.startdate}" pattern="yyyy-MM-dd"/>-<fmt:formatDate value="${seed.enddate}" pattern="yyyy-MM-dd"/></span><br/>
				              <span style="font-size: 12px;color: #666666">品种：${seed.varietys.belongsname}</span><br/>
				            </div>
				            <div class="weui-cell__ft"></div>
				          </a>
			        	</div>
		        	</div>
				</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >
					没有种植作物！
				</div>
			</c:otherwise>
		</c:choose>
		
	</div>
	
	<div style="margin-top: 5px;overflow:hidden">
			<div  style="background-color: #9CCC65;" class="center-left">
				<span style="color: #FFFFFF">实时长势</span>
			</div>
		<div style="background-color: #738FFE ; " class="center-right">
			<span tyle="color: #FFFFFF">生产管理决策</span>
		</div>
	</div>
	
	<div style="margin-top: 5px;overflow:hidden">
		<div  style="background-color: #2BAF2B;" class="center-left">
			<span tyle="color: #FFFFFF">专家服务</span>
		</div>
		<div style="background-color: #AB47BC; " class="center-right">
			<span tyle="color: #FFFFFF">苗床详细信息</span>
		</div>
	</div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	
	
</body>
</html>