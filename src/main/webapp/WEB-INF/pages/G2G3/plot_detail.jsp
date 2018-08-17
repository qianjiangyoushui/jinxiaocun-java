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
		地块圈信息
		<a href="${wechatPath}farm/detail.action?type=${type}&farmid=${plot.farmid}&operate=${sessionScope.operate}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div>
		<div style="min-height: 125px;border:1px solid  #F0F0F0;background-color: #FFFFFF ">
			<div style="height: 35px;line-height: 52px;width: 50%;float: left;">
				<span style="color: #FF9800;margin-left: 18px;font-size: 16px;">${plot.plotname}</span>
			</div>
			<a href="${wechatPath}plot/edit.action?plotid=${plot.guid}&type=${type}">
				<div style="height: 52px;line-height: 52px;width: 50%;float: right;">
					<span style="color:#3F51B5;margin-left: 18px;font-size: 12px;">地块信息维护</span>
				</div>
			</a>
			<div style="margin-left: 18px;">
				<span style="font-size: 13px;">灌溉类型：${plot.irrigatetype }</span><br/>
				<span style="font-size: 13px;">喷灌机品牌：${plot.irrigbrand }</span><br/>
				<span style="font-size: 13px;">供水情况：${plot.wellsamount }口井${plot.wellsdisc }</span>
			</div>
		</div>
		
		<div style="height: 110px; margin-top: 5px;border:1px solid  #F0F0F0;background-color: #FFFFFF "  >
			<div style="height: 42px;line-height:42px;width: 50%;float: left;">
				<span style="color:#000000;margin-left: 18px;font-size: 14px;">轮茬用药记录</span>
			</div>
			<a href="${wechatPath}thecrop/add.action?plotid=${plot.guid}&type=${type}">
				<div style="height: 42px;line-height: 42px;width: 50%;float: right;">
					<span style="color:#3F51B5;margin-left: 18px;font-size: 12px;">轮茬及用药记录维护</span>
				</div>
			</a>
			<div>
				<table style="margin-left: 18px;font-size: 13px;">
					<c:forEach var="crop" items="${plot.thecrops}">
						<tr>
							<td>${crop.years}年 &nbsp;</td>
							<td>${crop.plantcrops}&nbsp; </td>
							<td>${crop.medicate}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		
		<c:choose>
			<c:when test="${!empty plot.seedfiles}">
				<c:forEach var="seed" items="${plot.seedfiles}">
				<div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >
					<div class="bd">
						<div>
				          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/detail.action?seedfileid=${seed.guid}">
				            <div class="weui-cell__bd">
				              <strong style="font-size: 16px;color: #000000;">批次${seed.batch} &nbsp; ${seed.varietys.belongsname}</strong>
				              <p></p>
				              <span style="font-size: 12px;color: #666666">种植基地：${plot.plotname}</span><br/>
				              <span style="font-size: 12px;color: #666666">种植日期：<fmt:formatDate value="${seed.startdate}" pattern="yyyy-MM-dd"/><fmt:formatDate value="${seed.enddate}" pattern="yyyy-MM-dd"/></span><br/>
				              <span style="font-size: 12px;color: #666666">种植面积：${seed.muamount}亩</span><br/>
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
		<div style="background-color: #FFA726 ; " class="center-right">
			<span tyle="color: #FFFFFF"> 喷灌圈浇水控制</span>
		</div>
	</div>
	
	<div style="margin-top: 5px;overflow:hidden">
		<div  style="background-color: #738FFE;" class="center-left">
			<span tyle="color: #FFFFFF">生产管理决策</span>
		</div>
		<div style="background-color: #AB47BC; " class="center-right">
			<span tyle="color: #FFFFFF">基地详细信息</span>
		</div>
	</div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	
	
</body>
</html>