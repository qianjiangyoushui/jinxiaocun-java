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
		瓶苗(G0)溯源信息管理
		<a href="${wechatPath}bottleseed/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}bottleseed/add.action" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}bottleseed/add.action" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建档案</a>
	</div>
	<div>
		<div class="mui-content">
				<div style="padding: 10px 10px; " class="swiper-container">
					<div id="segmentedControl" class="swiper-wrapper mui-segmented-control mui-segmented-control-inverted mui-segmented-control-primary" style="overflow: initial;border:none;" >
						<c:forEach var="year" items="${getSysYear}"  varStatus="index">
						<c:if test="${(index.index)%3==0}"> 
						<div class="swiper-slide">
						</c:if>
						<a  <c:if test='${curryear ne year}'> class="mui-control-item yearclick" </c:if> 
						  value="${year}">${year}年</a>
						<c:if test="${(index.count)%3==0 ||index.count==5}"> 
						</div>
						</c:if>
					</c:forEach>
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


	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
		<script src="${wechatPath}js/swiper-3.3.1.jquery.min.js"></script>
		<script>
		var mySwiper = new Swiper('.swiper-container', {
		})
		</script>
	
	<!-- 	分页加载	 -->
	<script type="text/javascript">
	$("#datetime-start").calendar();
	 $("#datetime-end").calendar();
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
			  url: "${wechatPath}bottleseed/page.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {"pageNo":pageNo,
				  years:years  
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
					  content+='<a class="weui-cell weui-cell_access" href="${wechatPath}bottleseed/detail.action?seedfileid='+rows[i].guid+'">';
					  content+='<div class="weui-cell__bd" style="margin-top:10px;">';
					  content+='<p style="font-size:17px;color:#000000">';
					  content+='批次编号:'+rows[i].batch+'  培养于'+rows[i].seedbed.seedbedname
					  content+='苗床</p></div><div class="weui-cell__ft"></div></a>';
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
		
		$(".yearclick").on("tap",function(){
			$("#list").empty();//筛选前清楚list的内容
			pageNo=1;
			pageCount=100;//默认
			getdataPage($(this).attr("value"));
		})
		
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