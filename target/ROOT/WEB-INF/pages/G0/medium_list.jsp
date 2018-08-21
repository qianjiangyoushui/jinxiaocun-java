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
		培养基信息来源
		<a href="${wechatPath}bottleseed/detail.action?seedfileid=${seedfileid}&operate=${operate}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<c:if test="${sessionScope.user.depart.departid eq '4'}">
			<a href="${wechatPath}medium/add.action?seedfileid=${seedfileid}&operate=${operate}" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/add.png" style="width: 20px" />
			</a>
			<a href="${wechatPath}medium/add.action?seedfileid=${seedfileid}&operate=${operate}" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新增</a>
		</c:if>
	</div>
    
	<div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
		<div id="list"></div>
    

		<div class="weui-loadmore" style="display: none;" id="dis">
			<i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载...</span>
		</div>
		<div class="weui-loadmore" style="display: none;" id="disp">
			<span class="weui-loadmore__tips">没有更多数据...</span>
		</div>
	</div>

	<div style="height: 155px; width: 100%"></div>


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
	 	var seedfileid="${seedfileid}";
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
		
		function getdataPage(){
			$("#dis").removeAttr("style");
			$.ajax({
			  url: "${wechatPath}medium/page.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {"pageNo":pageNo,
				"seedfileid":seedfileid    
			  },
			  error:function(data){
				  $.toptip("数据加载失败,请刷新",'error');
				  $("#dis").css("display", "none");
			  },
			  success: function(data){
				  var content="";
				  var pagelist=data.page;
				  
				  pageNo=pagelist.nextPage;//设置pageNo;
				  pageCount=pagelist.pageCount;//设置总页数
				  
				  var rows=pagelist.rows;
				  for(var i=0;i<rows.length;i++){
					  content+='<div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >';
					  content+='<div class="bd"><div>'
					  content+='<a class="weui-cell weui-cell_access"  href="${wechatPath}medium/detail.action?medium='+rows[i].guid+'">';
					  content+='<div class="weui-cell__bd"><strong style="font-size: 16px;color: #000000;">培养基名称：'+rows[i].mediumname+'</strong><p></p>';
					  content+='<span style="font-size: 12px;color: #666666">来源：'+rows[i].come.belongsname+'</span><br/>';
					  content+='<span style="font-size: 12px;color: #666666">成分比例：'+rows[i].scale;
					  content+='</div><div class="weui-cell__ft"></div></a></div></div></div>';
				  }
				  $("#list").append(content);
				  
				  $("#dis").css("display","none");
				  loading=false;
			  }
			});
		}
		
		function info(){
			$("#list").empty();
			pageNo=1;
			pageCount=100;//默认
			getdataPage();
		}
		info();
	</script>
</body>
</html>