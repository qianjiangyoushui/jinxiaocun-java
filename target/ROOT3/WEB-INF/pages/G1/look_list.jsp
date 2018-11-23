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
<body style="background-color: #FFFFFF">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		长势图片
		<a href="${wechatPath}greenhouses/housedetail.action?guid=${seedfileid}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<c:if test="${sessionScope.user.depart.departid eq '3'}">
			<a href="${wechatPath}wintertest/add.action?relatedid=${seedfileid}&type=116&description=原原种冬季测产长势图片&url=${url}" style="position: absolute; right: 27px; top: 0px;">
				<img src="${wechatPath}icon/add.png" style="width: 20px" />
			</a>
			<a href="javascript:;" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333"></a>
		</c:if>
	</div>
	 <%-- <div style="margin-top: 5px;overflow:hidden">
		<a href="${wechatPath}greenhouses/list.action">
			<div  style="background-color: #AB47BC;" class="center-left">
				<span style="color: #FFFFFF">溯源信息管理</span>
			</div>
		</a>
		<a href="${wechatPath}seedbed/list.action">
			<div style="background-color: #FFA726;" class="center-right">
				<span style="color: #FFFFFF">物联网生产控制</span>
			</div>
		</a>
	</div>
	<hr/>  --%>
	
	<div id="list">
		<%-- <a href="#" style="text-decoration:none; color:#333;border-bottom:1px solid #bdbbbc; display:block;padding:20px 0;background-color: #FFFFFF"">
				<table border="0" style="width:100% ;background-color: #FFFFFF">
				  <col style="width:10%"/>
				  <col style="width:70%"/>
				  <col style="width:15%"/>
				  <tr>
				    <td><div style="margin-left: 8px;"><img src="${wechatPath}icon/test.png"/></div></td>
				    <td align="left" valign="top"><div style="margin-left: 8px;"><strong style=" font-size:14px">${house.housename}</strong></div><div style=" font-size:12px; color:#999; margin-top:15px;margin-left: 8px; line-height:20px"><span >${house.description}</span></div></td>
				    <td align="right"><div style="margin-right: 10px;"><img src="${wechatPath}icon/right.png" style=" width:16px"/></div></td>
				  </tr>
				</table>
		</a> --%>
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
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
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
			var seedfileid = '${seedfileid}';
			$.ajax({
			  url: "${wechatPath}wintertest/lookList.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {"pageNo":pageNo,seedfileid:seedfileid},
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
					  console.log(rows[i].createdate);
					  var da = new Date(rows[i].createdate);
					  content+='<a style="text-decoration:none; color:#333;border-bottom:1px solid #bdbbbc; display:block;padding:20px 0;background-color: #FFFFFF" ';
					 
				      content += 'href="${wechatPath}wintertest/detail.action?guid='+rows[i].guid+'">';
					  content+='<table border="0" style="width:100% ;background-color: #FFFFFF"><col style="width:10%"/><col style="width:70%"/><col style="width:15%"/>';
					  content+='<tr><td><div style="margin-left: 8px;">';
					  if(rows[i].upimage[0] != null){
						  content += '<img src="'+rows[i].upimage[0].url+'" style="height:110px;width:100px"/>';
					  }
					  content += '</div></td>';
					  content+='<td align="left" valign="top"><div style="margin-left: 8px;"><strong style=" font-size:14px">'+rows[i].description+'</strong></div>'
					  content += '<div style=" font-size:12px; color:#999; margin-top:15px;margin-left: 8px; line-height:20px;text-align:right"><span ><br/><br/><span>共';
					  content+=rows[i].upimage.length +'张图片       '+da.format('yyyy年MM月dd日')  +'</span></div>';
					  content += '</td>';
					  content+='<td align="right"><div style="margin-right: 10px;"><img src="${wechatPath}icon/right.png" style=" width:16px"/></div></td></tr></table></a>';
				  }
				  $("#list").append(content);
				  
				  $("#dis").css("display","none");
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