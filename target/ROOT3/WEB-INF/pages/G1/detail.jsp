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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		大棚基地信息
			<c:choose>
				<c:when test="${sessionScope.operate eq '3'}">
					<a href="${wechatPath}greenhouses/index.action" style="position: absolute; left: 10px; top: 8px">
				</c:when>
				<c:otherwise>
					<a href="${wechatPath}greenhouses/houseList.action" style="position: absolute; left: 10px; top: 8px">
				</c:otherwise>
			</c:choose>
			
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div>
	<div style="height: 222px; margin-top: -20px;border:1px solid  #F0F0F0;background-color: #FFFFFF "  >
		<span style="color:red">${seedfile.greenhouses.housename}</span>
		<table style="font-size:12px">
			<tr>
				<td>土壤湿度：</td>
				<td style="padding-right:20px;">45.33%</td>
				<td>2017-08-02 15:27:26</td>
			</tr>
			<tr>
				<td>土壤湿度1：</td>
				<td style="padding-right:20px;">45.33%</td>
				<td>2017-08-02 15:27:26</td>
			</tr>
			<tr>
				<td>土壤湿度2：</td>
				<td style="padding-right:20px;">45.33%</td>
				<td>2017-08-02 15:27:26</td>
			</tr>
			<tr>
				<td>盐分度：</td>
				<td style="padding-right:20px;">12%</td>
				<td>2017-08-02 15:27:26</td>
			</tr>
		</table>
	</div>
			<c:choose>
			<c:when test="${!empty house.seedfiles}">
				<c:forEach var="seed" items="${house.seedfiles}">
				<div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >
					<div class="bd">
						<div>
						<c:if test="${!empty seed.guid}">
				          <a class="weui-cell weui-cell_access" href="${wechatPath}greenhouses/housedetail.action?guid=${seed.guid}">
				         </c:if>
				          <c:if test="${empty seed.guid}">
				          <a class="weui-cell weui-cell_access" href="javascript:;">
				         </c:if>
				            <div class="weui-cell__bd">
				              <strong style="font-size: 16px;color: #000000;">批次${seed.batch} &nbsp; ${seed.varietys.belongsname}</strong>
				              <p></p>
				               <span style="font-size: 12px;color: #666666">种植大棚：${house.housename}</span><br/>
				              <span style="font-size: 12px;color: #666666">定植日期：<fmt:formatDate value="${seed.startdate}" pattern="yyyy-MM-dd"/>-<fmt:formatDate value="${seed.enddate}" pattern="yyyy-MM-dd"/></span><br/>
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
		<a href="JavaScript:;">
			<div  style="background-color: #8DC43C;" class="center-left">
				<span style="color: #FFFFFF">实时长势</span>
			</div>
		</a>
		<a href="JavaScript:;">
			<div style="background-color: #FFA726;" class="center-right">
				<span style="color: #FFFFFF">浇水控制</span>
			</div>
		</a>
	</div>
	<div style="margin-top: 5px;overflow:hidden">
		<a href="JavaScript:;">
			<div  style="background-color: #0099FF;" class="center-left">
				<span style="color: #FFFFFF">生产管理决策</span>
			</div>
		</a>
		<a href="JavaScript:;">
			<div style="background-color: #AB47BC;" class="center-right">
				<span style="color: #FFFFFF">大棚详细信息</span>
			</div>
		</a>
	</div>
	<div style="margin-top: 5px;overflow:hidden">
		<a href="JavaScript:;">
			<div  style="background-color: #33CC52;" class="center-left">
				<span style="color: #FFFFFF">专家服务</span>
			</div>
		</a>
	</div>
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	
	
	
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
<script type="text/javascript">
function goHouseDetail(guid){
	window.location = "${wechatPath}greenhouses/housedetail.action?guid="+guid;
}
</script>
</body>
</html>