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
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
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
		测产记录
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a class="munumber" href="${wechatPath}g2g3/yieldtest/add.action?guid=${seedfile.guid}&munumber=${mapcount.munumber}" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a class="munumber" href="${wechatPath}g2g3/yieldtest/add.action?guid=${seedfile.guid}&munumber=${mapcount.munumber}" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建样点</a>
			<input value="${seedfile.guid}" type="hidden" id="guid">
	</div>
	<div>
		<div class="mui-content">
					<div class="weui-cell__bd" style="margin-top:10px;">
						<div style="margin-left: 18px;margin-top:5px;line-height: 18px;">
						<span style="font-size: 13px;line-height: 18px;" >样点亩数：
						<c:if test="${not empty mapcount.munumber}">
						<span id="munumber">${mapcount.munumber}</span>
						</c:if>
						</span>
						<c:if test="${empty mapcount.munumber}">
						<span style="font-size: 13px;line-height: 18px;" id="munumber">0.04</span>
						<input type="button" onclick="alterinfo();" class="mui-btn mui-btn-danger" style="float:right;margin:10px 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="样点亩数">
						</c:if>
						<br>
						<span style="font-size: 13px;line-height: 18px;">批次种植亩数：<fmt:formatNumber value="${seedfile.muamount}" var="result" pattern="#.00" /><c:out value="${result}"/></span>
						<br>
						<span style="font-size: 13px;line-height: 18px;">取样点数：<fmt:formatNumber value="${mapcount eq null?0:mapcount.count}" var="result" pattern="#" /><c:out value="${result}"/>  </span>
						<span style="font-size: 13px;line-height: 18px;">预计平均亩产量：<fmt:formatNumber value="${mapcount eq null?0:mapcount.avgpermu}" var="result" pattern="#.00" /><c:out value="${result}"/> </span>
						<br>
						<span style="font-size: 13px;line-height: 18px;">预计批次总产量：<fmt:formatNumber value="${mapcount eq null?0:mapcount.avgpermu*seedfile.muamount}" var="result" pattern="#.00" /><c:out value="${result}"/> </span>
						<span style="font-size: 13px;line-height: 18px;">大薯块总产量：<fmt:formatNumber value="${mapcount eq null?0:(mapcount.avgbigsum*seedfile.muamount)}" var="result" pattern="#.00" /><c:out value="${result}"/> </span>
						<br>
						<span style="font-size: 13px;line-height: 18px;">中薯块总产量：<fmt:formatNumber value="${mapcount eq null?0:(mapcount.avgmedsum*seedfile.muamount)}" var="result" pattern="#.00" /><c:out value="${result}"/> </span>
						<span style="font-size: 13px;line-height: 18px;">小薯块总产量：<fmt:formatNumber value="${mapcount eq null?0:(mapcount.avgminsum*seedfile.muamount)}" var="result" pattern="#.00" /><c:out value="${result}"/> </span>
						</div>
					</div>
		</div>
	</div>
	<hr/>
	<div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
		<div id="list"></div>
		<div class="weui-loadmore" style="display: none;" id="dis">
			<i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载...</span>
		</div>
		<div class="weui-loadmore" style="display: none;" id="disp">
			<span class="weui-loadmore__tips">没有更多数据...</span>
		</div>
	</div>

	<div style="height: 555px; width: 100%"></div>
	<script src="${wechatPath}js/jquery-2.1.4.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script>
		function alterinfo(){
			$.prompt("修改样点亩数", function(text) {
				  $("#munumber").html("").html(text);
				  $(".munumber").attr("href", "${wechatPath}g2g3/yieldtest/add.action?guid=${seedfile.guid}&munumber="+text);
				}, function() {
				  //点击取消后的回调函数
				});
			
		}
		</script>
	
	<!-- 	分页加载	 -->
	<script type="text/javascript">
	 	var years=$(this).attr("value");
		var pageNo=1;	
		var pageCount=100;//默认
		var loading = false;  //状态标记
		
		$(document.body).infinite().on("infinite", function() {
		  if(loading) return;
		  loading = true;
		  if(pageNo<=pageCount){
			  getdataPage();
		  }else{
			  $("#disp").removeAttr("style");
		  }
		})
		
		function getdataPage(years){
			
			$("#dis").removeAttr("style");
			$.ajax({
			  url: "${wechatPath}g2g3/yieldtest/page.action",
			  async: true,
			  type:"POST",
			  dataType: 'json',
			  data: {"pageNo":pageNo,
				  seedfileid:$("#guid").attr("value")
			  },
			  error:function(data){
				  $.toptip("数据加载失败,请刷新",'error');
				  $("#dis").css("display", "none");
			  },
			  success: function(data){
				  var content="";
				  var pagelist=data.page;
				  
				  years=data.years;//检索条件
				  pageNo=pagelist.nextPage;//设置pageNo;
				  pageCount=pagelist.pageCount;//设置总页数
				  
				  var rows=pagelist.rows;
				  for(var i=0;i<rows.length;i++){
					  if(i%2==0){
						  content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
					  }
					  if(i==rows.length-1 &&i>1){
						  if((i+1)%2!=0){
							  content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
						  }
					  }
// 					  
					  content+='<a class="weui-cell weui-cell_access" href="${wechatPath}/g2g3/yieldtest/detail.action?seedfileid='+rows[i].guid+'">';
					  content+='<div class="weui-cell__bd" style="margin-top:10px;">';
					  content+='<p style="font-size:17px;color:#000000">';
					  content+='样点(亩):'+rows[i].munumber+' &nbsp; 总个数:'+rows[i].amount+'&nbsp; 总重量:'+rows[i].weight;
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
				  $("#list").append(content);
				  
				  $("#dis").css("display","none");
				  loading=false;
			  }
			});
		}
		
		
		//防止页面回退重复加载
		function info(years){
			$("#list").empty();
			pageNo=1;
			pageCount=100;//默认
			getdataPage(years);
		}
		info(years);
		
		
	</script>
</body>
</html>