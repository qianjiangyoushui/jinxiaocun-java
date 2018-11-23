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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		职工用户管理
		<a href="${wechatPath}index/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}employee/add.action" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}employee/add.action" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">添加职工</a>
	</div>
	<div class="weui-cells weui-cells_form infinite" style="margin-top: 0;">

		<ul class="mui-table-view mui-table-view-chevron" id="list">

		</ul>

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

	<!-- 	分页加载	 -->
	<script type="text/javascript">
	$("#datetime-start").calendar();
	 $("#datetime-end").calendar();
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
		
		var getdataPage = function(){
			$("#dis").removeAttr("style");
			$.ajax({
			  url: "${wechatPath}employee/page.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {"pageNo":pageNo},
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
					  content+='<li class="mui-table-view-cell mui-media"><a class="mui-navigate-right" href="${wechatPath}employee/detail.action?userid='+rows[i].guid+'">';
					  content+='<img class="mui-media-object mui-pull-left" src="${wechatPath}icon/peple.png">';
					  content+='<div class="mui-media-body">';
					  content+=rows[i].username;
					  content+='<br/>';
					  content+='<p class="mui-ellipsis">';
					  content+=rows[i].depart.departname;
					  content+='</p></div></a></li>';
				  }
				  $("#list").append(content);
				  
				  $("#dis").css("display","none");
				  loading=false;
			  }
			});
		}
		
		getdataPage();
	</script>
</body>
</html>