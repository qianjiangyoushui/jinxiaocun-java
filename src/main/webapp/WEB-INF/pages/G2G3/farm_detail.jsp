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
<body >
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		农场详细信息
		<c:choose>
			<c:when test="${sessionScope.operate eq '6' ||sessionScope.operate eq '9'}">
				<a href="${wechatPath}g2g3/index.action?type=${sessionScope.g2g3Type}" style="position: absolute; left: 10px; top: 8px">
					<img src="${wechatPath}icon/back.png" style="width: 20px" />
				</a>
			</c:when>
			<c:when test="${sessionScope.operate eq '7' ||sessionScope.operate eq '10'}">
				<a href="${wechatPath}farm/list.action?type=${sessionScope.g2g3Type}" style="position: absolute; left: 10px; top: 8px">
					<img src="${wechatPath}icon/back.png" style="width: 20px" />
				</a>
			</c:when>
			<c:otherwise>
				<a href="${wechatPath}plot/detail.action?plotid=${sessionScope.plotguid}&operate=${sessionScope.operate}&type=${sessionScope.g2g3Type}" style="position: absolute; left: 10px; top: 8px">
			</c:otherwise>
		</c:choose>
		<a href="${wechatPath}plot/add.action?type=${type}&farmid=${farm.guid}" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}plot/add.action?farmid=${farm.guid}&type=${type}" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新增地块</a>
	</div>
	
	<div style="margin-top: 0px;">
		<div style="min-height: 378px;border:1px solid  #F0F0F0;background-color: #FFFFFF "  >
<!-- 			<div style="height: 52px;line-height: 52px;width: 50%;float: left;"> -->
<%-- 				<span style="color: #FF9800;margin-left: 18px;">${farm.farmname}</span> --%>
<!-- 			</div> -->
<!-- 			<div style="height: 52px;line-height: 52px;width: 50%;float: right;"></div> -->
			<div style="margin-left: 18px;margin-top:5px;line-height: 18px;">
				<span style="font-size: 13px;line-height: 18px;">农场名称：${farm.farmname}</span><br/>
				<span style="font-size: 13px;line-height: 18px;">租赁起始日期：${farm.leasestart}年--${farm.leaseend}年</span><br/>
				<span style="font-size: 13px;line-height: 18px;">租赁面积：${farm.leasearea }亩</span><br/>
				<span style="font-size: 13px;line-height: 18px;">实际可耕种面积：${farm.plantarea }亩</span><br/>
				<span style="font-size: 13px;line-height: 18px;">土嚷性质：${farm.soiltype }</span><br/>
				<span style="font-size: 13px;line-height: 18px;">土地类型：${farm.arableland }</span><br/>
				
				<span style="font-size: 13px;line-height: 18px;">年降雨量：${farm.rainfall }毫米</span><br/>
				<span style="font-size: 13px;line-height: 18px;">无霜期：${farm.nofrost }天</span><br/>
				<span style="font-size: 13px;line-height: 18px;">变压器个数：${farm.transformer }个（${farm.transfordesc}）</span><br/>
				<span style="font-size: 13px;line-height: 18px;">海拔高度：${farm.altitude }米</span><br/>
				<span style="font-size: 13px;line-height: 18px;">酸碱度：${farm.ph }</span><br/>
				<span style="font-size: 13px;line-height: 18px;">机械配置：${farm.mechanical }</span><br/>
				<span style="font-size: 13px;line-height: 18px;">人员配置情况：${farm.staffing }</span><br/>
				
				<div>
					<span style="font-size: 13px;line-height: 18px;">示意图：</span>
					<a href="javascript:;" id="pb1"  style="height:25px;">点击查看</a>&nbsp;&nbsp;&nbsp;&nbsp; <a href="${wechatPath}farm/edit.action?farmid=${farm.guid}&type=${type}"  style="height:25px;">农场信息维护</a>
				</div>
				
			</div>
		</div>
		
		<c:choose>
			<c:when test="${!empty farm.plots}">
				<c:forEach var="plot" items="${farm.plots}">
					<a style="text-decoration:none; color:#333;border-bottom:1px solid #bdbbbc; display:block;padding:20px 0;background-color: #FFFFFF" href="${wechatPath}plot/detail.action?plotid=${plot.guid}&type=${type}">
						<table border="0" style="width:100% ;background-color: #FFFFFF"><col style="width:10%"/><col style="width:70%"/><col style="width:15%"/>
						<tr><td><div style="margin-left: 8px;">
						<c:choose>
							<c:when test="${! (plot.url eq '')}">
								<img src="${plot.url}" style="width: 77px;height: 77px;"/>
							</c:when>
							<c:otherwise>
								<img src="${wechatPath}icon/test.png"/>
							</c:otherwise>
						</c:choose>
						</div></td>
						<td align="left" valign="top"><div style="margin-left: 8px;">
						<strong style=" font-size:14px">${plot.plotname}</strong>
						</div><div style=" font-size:12px; color:#999; margin-top:15px;margin-left: 8px; line-height:20px">
						<c:choose>
							<c:when test="${!empty plot.seedfiles}">
								<span>
									种植${plot.seedfiles[0].batch}批次原种
								<br/>
								</span>
								<span>
									${plot.seedfiles[0].varietys.belongsname}
								</span>
							</c:when>
							<c:otherwise>
								<span>
									没有种植作物！
								</span>
							</c:otherwise>
						</c:choose>
						</div></td>
						<td align="right"><div style="margin-right: 10px;"><img src="${wechatPath}icon/right.png" style=" width:16px"/></div></td></tr></table></a>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >
					没有地块！
				</div>
			</c:otherwise>
		</c:choose>
		
	</div>
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/swiper.js"></script>
	<script type="text/javascript">
	var pb1 = $.photoBrowser({
        items: [
			<c:forEach items="${farm.images}" var="image">
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