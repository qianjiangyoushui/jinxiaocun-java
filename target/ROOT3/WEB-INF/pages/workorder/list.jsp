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
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<link rel="stylesheet" href="${wechatPath}css/mui/baocun.css">
<style type="text/css">
	 p{
		color: #000000;
		font-size: 17px;
	}
</style>
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		工单执行情况
		<a href="${wechatPath}workorder/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div  class="bd" style="margin-top: -20px;" id="list">
	
		<%-- <div class="weui-cells">
	          <a class="weui-cell weui-cell_access" href="javascript:;">
	            <div class="weui-cell__bd" style="margin-top:8px;">
	              <p>批次编号：${seedfile.batch}</p>
	            </div>
	            <div class="weui-cell__ft"></div>
	          </a>
	     </div> --%>
     
	</div>
	
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
		
<script type="text/javascript">
var pageNo=1;	
var pageCount=100;//默认
var loading = false;  //状态标记

$(document.body).infinite().on("infinite", function() {
  if(loading) return;
  loading = true;
  if(pageNo<=pageCount){
	  getdataPage();
  }
})

function getdataPage(){
	$("#dis").removeAttr("style");
	$.ajax({
	  url: "${wechatPath}workorder/pageList.action",
	  type:"POST",
	  async: true,
	  dataType: 'json',
	  data: {"pageNo":pageNo},
	  error:function(data){
		  $.toptip("数据加载失败,请刷新",'error');
	  },
	  success: function(data){
		  var content="";
		  var pagelist=data.page;
		  
		  pageNo=pagelist.nextPage;//设置pageNo;
		  pageCount=pagelist.pageCount;//设置总页数
		  
		  var rows=pagelist.rows;
		  for(var i=0;i<rows.length;i++){
			 var da = new Date(rows[i].startdate);
			 /*  content += '<div style="height: 160px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0;background-color: #FFFFFF ">';
			 content += '<div class="bd"><div> <a class="weui-cell weui-cell_access" href="JavaScript:;"><div class="weui-cell__bd">';
			 content += '<strong style="font-size: 16px;color: #000000;">'+da.format('yyyy年MM月dd日')  +'</strong>';
			 content += '<p></p>';
			 content += '<span style="font-size: 12px;color: #666666">灌溉水量：'+rows[i].irrigatewater+'</span><br/>';
			 content += '<span style="font-size: 12px;color: #666666">喷灌行走速度：'+rows[i].speed+'</span><br/>';
			 content += '<span style="font-size: 12px;color: #666666">当日降雨量：'+rows[i].rainfall+'</span><br/>';
			 content += '<span style="font-size: 12px;color: #666666">灌溉前湿度：'+rows[i].humidity+'</span><br/>';
			 content += '<span style="font-size: 12px;color: #666666">记录人：'+rows[i].recordperson+'</span><br/>';
			 content += '</div><div class="weui-cell__ft"></div> </a></div></div></div>'; */
			 content += '<div class="weui-cells">';
			 content += ' <a class="weui-cell weui-cell_access" href="javascript:;">';
			 content += '<div class="weui-cell__bd" style="margin-top:8px;">';
			 if(rows[i].status == '1'){
				 content += '<p>'+rows[i].ordername+'   '+da.format('yyyy年MM月dd日')  +'   待领取</p>';
			 }else if(rows[i].status == '2'){
				 content += '<p>'+rows[i].ordername+'   '+da.format('yyyy年MM月dd日')  +'   已领取</p>';
			 }else{
				 content += '<p>'+rows[i].ordername+'   '+da.format('yyyy年MM月dd日')  +'   已反馈</p>';
			 }
			 
			 content += '</div>';
			 content += '<div class="weui-cell__ft"></div>';
			 content += '</a>';
			 content += '</div>';
		  }
		  $("#list").append(content);
		  
		 
		  loading=false;
	  }
	});
}

getdataPage();





Date.prototype.format = function(format){ 
	var o = { 
	"M+" : this.getMonth()+1, //month 
	"d+" : this.getDate(), //day 
	"h+" : this.getHours(), //hour 
	"m+" : this.getMinutes(), //minute 
	"s+" : this.getSeconds(), //second 
	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
	"S" : this.getMilliseconds() //millisecond 
	} 

	if(/(y+)/.test(format)) { 
	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
	if(new RegExp("("+ k +")").test(format)) { 
	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	} 
	} 
	return format; 
	} 
		
</script>
</body>
