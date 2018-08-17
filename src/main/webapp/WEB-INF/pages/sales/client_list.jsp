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
		客户信息
		<a href="${wechatPath}sales/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}client/add.action" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}client/add.action" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新增客户</a>
	</div>
	
	<div class="weui-cells weui-cells_form infinite " style="margin-top: 0;" >
		<a style="color: #000000" href="${wechatPath}client/add.action">
			<div style="height:77px;width: 100% ;border:solid 1px #E0E0E0; resize:none;">
				<table>
					<tr>
						<td ><div style="margin-left: 8px;margin-top: 6px;"><img src="${wechatPath}icon/touxiang.png" style="width: 77px;height: 64px;"/></div></td>
						<td><div  style="font-size:16px;margin-left: 20px;">新的客户</div></td>
					</tr>
				</table>
			</div>
		</a>
		
		<div id="list" style="margin-top: 10px;"></div>
    

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
		
		function getdataPage(){
			$("#dis").removeAttr("style");
			$.ajax({
			  url: "${wechatPath}client/page.action",
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
					  content+='<a style="color:#000000;" href="${wechatPath}client/index.action?clientid='+rows[i].guid+'">';
					  content+='<div style="height:77px;width: 100% ;border:solid 1px #E0E0E0; resize:none;"><table><tr><td>';
					  content+='<div style="margin-left: 8px;margin-top: 6px;"><img src="'+rows[i].url+'" style="width: 77px;height: 64px;"/></div></td>';
					  content+='<td><div  style="font-size:16px;margin-left: 20px;">'+rows[i].clientname+'</div></td>';
					  content+='<td><div  style="font-size:16px;margin-left: 20px;">'+rows[i].province+rows[i].city+rows[i].area+'</div></td>';
					  content+='</tr></table></div></a>';
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