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
		我的任务
		<a href="${wechatPath}index/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	
	<input type="hidden" id="date3" onchange="go(this.value);">
	<div id="list">
	</div>
	
	<div style="height: 50px;background: #716FFB;font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative"">任务列表</div>
	
	<div id="con">
		<%-- <div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >
			<div class="bd">
				<div>
		          	<a class="weui-cell weui-cell_access" href="">
			          	  <div class="weui-cell__bd">
				           	   <strong style="font-size: 16px;color: #000000;">批次</strong>
				           	   <p></p>
				               <span style="font-size: 12px;color: #666666">种植大棚：${house.housename}</span><br/>
				           	   <span style="font-size: 12px;color: #666666">定植日期：<fmt:formatDate value="${seed.startdate}" pattern="yyyy-MM-dd"/>-<fmt:formatDate value="${seed.enddate}" pattern="yyyy-MM-dd"/></span><br/>
				           	   <span style="font-size: 12px;color: #666666">品种：${seed.varietys.belongsname}</span><br/>
			               </div>
		            		<div class="weui-cell__ft"></div>
		         	 </a>
	        	</div>
	        	<div style="height: 40px; margin-top: -5px;margin-bottom:8px;;border-top:1px solid  #859393 ;background-color: #FFFFFF; text-align:right">
	        		<a href="javascript:history.go(-1);" class="weui-btn weui-btn_mini weui-btn_default">完成反馈</a>
	        		<a href="javascript:history.go(-1);" class="weui-btn weui-btn_mini weui-btn_default">领取</a>
	        	</div>
	       	</div>
		</div> --%>
	</div>
	
	
	
	
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/swiper.js"></script>

<!-- 	分页加载	 -->
	<script type="text/javascript">
	$("#datetime-start").calendar();
	 $("#datetime-end").calendar();
	 
	 $("#list").calendar({
		  container: "#list",
		  input: "#date3"
		});
	 var pageNo=1;	
	var pageCount=100;//默认
	var loading = false;  //状态标记
	 function go(value){
		
		 $.ajax({
			  url: "${wechatPath}tasks/taskList.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {startdate : value},
			  error:function(data){
				  $.toptip("数据加载失败,请刷新",'error');
			  },
			  success: function(data){
				  var content="";
				  var pagelist=data.page;
				  
				  pageNo=pagelist.nextPage;//设置pageNo;
				  pageCount=pagelist.pageCount;//设置总页数
				  
				  var rows=pagelist.rows;
				  console.log(rows);
				  for(var i=0;i<rows.length;i++){
					  content += '<div style="height: 129px; margin-top: 10px;margin-bottom:8px;;border:1px solid  #F0F0F0 ;background-color: #FFFFFF " >';
					  content += '	<div class="bd">';
					  content += '		<div>';
					  content += '			<a class="weui-cell weui-cell_access" href="">';
					  content += '				<div class="weui-cell__bd">';
					  content += '					<strong style="font-size: 16px;color: #000000;">'+rows[i].workorder.ordername+'</strong>';
					  content += '					<p></p>';
					  content += '					<span style="font-size: 12px;color: #666666">完成时间：'+rows[i].workorder.enddate+'</span><br/>';
					  content += '					<span style="font-size: 12px;color: #666666">负责人：'+rows[i].workorder.username+'</span><br/>';
					  content += '					<span style="font-size: 12px;color: #666666">参与人：'+rows[i].workorder.partic+'</span><br/>';
					  content += '				</div>';
					  content += '				<div class="weui-cell__ft"></div>';
					  content += '			</a>';
					  content += '		</div>';
					  content += '		<div style="height: 40px; margin-top: -10px;margin-bottom:8px;;border-top:1px solid  #859393 ;background-color: #FFFFFF; text-align:right">';
					  //content += '			<a href="${wechatPath}tasks/feedback.action?guid='+rows[i].guid+'" class="weui-btn weui-btn_mini weui-btn_default">完成反馈</a>';
					  content += '			<input type="button" value="完成反馈" onclick="feedback(\''+rows[i].guid+'\');">';
					  if(rows[i].status != 2){
						  //content += '			<a href="javascript:receive(\''+rows[i].guid+'\');" class="weui-btn weui-btn_mini weui-btn_default">领取</a>';
						  content += '			<input type="button" value="领取" onclick="receive(\''+rows[i].guid+'\');">';
					  }
					  content += '		</div>';
					  content += '	</div>';
					  content += '</div>';
				  }
				  $("#con").html(content);
			}
		 });
	 }
	
	function receive(value){
		$.ajax({
			  url: "${wechatPath}tasks/receive.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {guid : value},
			  error:function(data){
				  $.toptip("数据加载失败,请刷新",'error');
			  },
			  success: function(data){
				  if(data.result){
					  $.confirm("领取成功", function() {
						  location.reload();
						  }, function() {
						  //点击取消后的回调函数
						  }); 
				  }else{
					  
				  }
			  }
		 });
	}
	
	function feedback(value){
		window.location = '${wechatPath}tasks/feedback.action?guid='+value;
	}
	</script>
</body>
</html>