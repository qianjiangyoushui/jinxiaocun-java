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
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/demos.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">

<!--标准mui.css-->
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		售后技术服务信息列表
		<a href="${wechatPath}client/index.action?clientid=${clientid}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}saledafter/add.action?clientid=${clientid}" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}saledafter/add.action?clientid=${clientid}" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建记录</a>
	</div>
	
	<div class="weui-cells weui-cells_form infinite " style="margin-top: 0;" >
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

	<!-- 	分页加载	 -->
	<script type="text/javascript">
	$("#datetime-start").calendar();
	 $("#datetime-end").calendar();
	 
	 	var clientid='${clientid}';
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
			  url: "${wechatPath}saledafter/page.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {"pageNo":pageNo,'clientid':clientid},
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
					  content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
					  content+='<a class="weui-cell weui-cell_access" href="${wechatPath}saledafter/detail.action?guid='+rows[i].guid+'">';
					  content+='<div class="weui-cell__bd" style="margin-top:10px;">';
					  content+='<p style="font-size:17px;color:#000000">';
					  content+=rows[i].batch;
					  content+='</p></div><div class="weui-cell__ft"></div></a>';
					  content+='</div>';
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