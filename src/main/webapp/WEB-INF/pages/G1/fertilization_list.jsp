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
		施肥记录
		<a href="${wechatPath}greenhouses/housedetail.action?guid=${fertilization.seedfileid}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<c:if test="${sessionScope.user.depart.departid eq '5' ||sessionScope.user.depart.departid eq '8'}">
			<a href="${wechatPath}fertilization/add.action?seedfileid=${fertilization.seedfileid}" style="position: absolute; right: 27px; top: 0px;">
				<img src="${wechatPath}icon/add.png" style="width: 20px" />
			</a>
			<a href="${wechatPath}fertilization/add.action?seedfileid=${fertilization.seedfileid}" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建记录</a>
		</c:if>
	</div>
	
	<div id="list">
	<%-- <div style="height: 129px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0;background-color: #FFFFFF ">
		<div class="bd">
				<div>
		          <a class="weui-cell weui-cell_access" href="JavaScript:;">
		            <div class="weui-cell__bd">
		              <strong style="font-size: 16px;color: #000000;"><fmt:formatDate value="${seed.startdate}" pattern="yyyy年MM月dd日"/></strong>
		              <p></p>
		              <span style="font-size: 12px;color: #666666">病虫害种类：123</span><br/>
		              <span style="font-size: 12px;color: #666666">位置编号：123</span><br/>
		              <span style="font-size: 12px;color: #666666">发生率：123%</span><br/>
		              <span style="font-size: 12px;color: #666666">调查人：张三</span><br/>
		              <span style="font-size: 12px;color: #666666">记录人：张三</span><br/>
		            </div>
		            <div class="weui-cell__ft"></div>
		          </a>
	        	</div>
        	</div>
		</div> --%>
	</div>
	
	
	
	
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>

<!-- 	分页加载	 -->
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
			var id = '${fertilization.seedfileid}';
			$.ajax({
			  url: "${wechatPath}fertilization/pageList.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {"pageNo":pageNo,'seedfileid':id},
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
					 var da = new Date(rows[i].manuredate);
					 content += '<div style="height: 160px; margin-top: 5px;margin-bottom:8px;;border:1px solid  #F0F0F0;background-color: #FFFFFF ">';
					 content += '<div class="bd"><div> <a class="weui-cell weui-cell_access" href="${wechatPath}fertilization/detail.action?guid='+rows[i].guid+'"><div class="weui-cell__bd">';
					 content += '<strong style="font-size: 16px;color: #000000;">'+da.format('yyyy年MM月dd日')  +'</strong>';
					 content += '<p></p>';
					 content += '<span style="font-size: 12px;color: #666666">肥料名称：'+rows[i].muck+'</span><br/>';
					 content += '<span style="font-size: 12px;color: #666666">施用总量：'+rows[i].dosage+rows[i].dosageunit+'</span><br/>';
					 content += '<span style="font-size: 12px;color: #666666">施肥方式：'+rows[i].usetype+'</span><br/>';
					 content += '<span style="font-size: 12px;color: #666666">操作人：'+rows[i].byperson+'</span><br/>';
					 content += '</div><div class="weui-cell__ft"></div> </a></div></div></div>';
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
</html>