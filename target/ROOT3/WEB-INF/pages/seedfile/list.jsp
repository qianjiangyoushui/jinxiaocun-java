<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
		<meta charset="utf-8">
		<title>核心苗溯源信息管理</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
		<link rel="stylesheet" href="${wechatPath}css/mui/app.css">
		<link rel="stylesheet" href="${wechatPath}css/swiper-3.3.1.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
		<style>
			.mui-card .mui-control-content {
				padding: 10px;
			}
			
			.mui-control-content {
				height: 150px;
			}
		</style>
	</head>

	<body style="background-color: #FFFFFF"> 
		<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		核心苗溯源信息管理
		<a href="${wechatPath}index/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}seedfile/add.action" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}seedfile/add.action" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建档案</a>
		</div>
		
		<div class="mui-content">
			<div  class="swiper-container" style="margin:10px 10px;">
				<div id="segmentedControl" class="swiper-wrapper mui-segmented-control mui-segmented-control-inverted mui-segmented-control-primary" style="overflow: initial;border:none;" >
					<c:forEach var="year" items="${getSysYear}"  varStatus="index">
						<c:if test="${(index.index)%3==0}"> 
						<div class="swiper-slide">
						</c:if>
						<a  <c:if test="${seedfile.years ne year}">class="mui-control-item yearclick"  </c:if> <c:if test="${seedfile.years eq year}"> class="mui-control-item yearclick mui-active" </c:if>  value="${year}">${year}年</a>
							<c:if test="${(index.count)%3==0 ||index.count==5}"> 
						</div>
						</c:if>
					</c:forEach>
			</div>
		</div>
			<div style="margin-top:5px;">
				<div id="list"></div>
			</div>
			<div class="weui-loadmore"  id="disp">
					   <span class="weui-loadmore__tips">没有更多数据...</span>
			</div>

			<jsp:include   page="../common/tabbar.jsp" flush="true"/>
			<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
		<script src="${wechatPath}js/swiper-3.3.1.jquery.min.js"></script>
		<script>
		var mySwiper = new Swiper('.swiper-container', {
		})
		$(".yearclick").on("tap",function(){
			seedfileList($(this).attr("value"));
		});
		
		function seedfileList(curryear){
			$.ajax({
				  url: "${wechatPath}seedfile/listAjax.action",
				  type:"POST",
				  async: true,
				  dataType: 'json',
				  data: {"years":curryear},
				  error:function(data){
					  $.toptip("数据加载失败,请刷新",'error');
				  },
				  success: function(data){
					  $("#list").html("");
					  var content="";
					  var rows=data.rows;
					  for(var i=0;i<rows.length;i++){
// 						  if(i%2==0){
// 							  content+='<ul class="mui-table-view" style="margin-top:10px;">';  
// 						  }
// 						  content+='<li class="mui-table-view-cell" >';
// 						  content+='<a class="mui-navigate-right" href="${wechatPath}seedfile/detail.action?years='+curryear+'&guid='+rows[i].guid+'"'+' style="font:normal normal 14px/1.5em arial;color:#525e71">批次编号:'+rows[i].batch+' 品种:'+rows[i].varietys.belongsname+'&nbsp;&nbsp;'+rows[i].code+'</a>';
// 						  content+='</li>'
// 						  if((i+1)%2==0){ content+='</ul>';}
						  
						  if(i%2==0){
							  content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
						  }
						  if(i==rows.length-1 &&i>1){
							  if((i+1)%2!=0){
								  content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
							  }
						  }
						  content+='<a class="weui-cell weui-cell_access" href="${wechatPath}seedfile/detail.action?years='+curryear+'&guid='+rows[i].guid+'">';
						  content+='<div class="weui-cell__bd" style="margin-top:10px;">';
						  content+='<p style="font-size:17px;color:#000000">';
						  content+='批次编号:'+rows[i].batch+' 品种:'+rows[i].varietys.belongsname+'&nbsp;&nbsp;'+rows[i].code;
						  content+='</p></div><div class="weui-cell__ft"></div></a>';
						  if(i%2!=0){
						  	content+='</div><div style="margin-bottom: 10px;width: 100%"></div>';
						  }
						  if(i==rows.length-1 &&i>1){
							  if((i+1)%2!=0){
								  content+='</div><div style="margin-bottom: 10px;width: 100%"></div>';
							  }
						  }
					  }
					  $("#list").html(content);
				  }
				});
		}
		seedfileList('${seedfile.years}');
		</script>

	</body>
</html>