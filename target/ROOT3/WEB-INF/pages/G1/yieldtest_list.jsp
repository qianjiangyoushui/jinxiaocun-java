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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		测产记录
		<a href="${wechatPath}greenhouses/housedetail.action?guid=${seedfileid}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}yieldtest/add.action?seedfileid=${seedfileid}&munumber=${yieldtest.munumber}" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a class="munumber" href="${wechatPath}yieldtest/add.action?seedfileid=${seedfileid}&munumber=${yieldtest.munumber}" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建记录</a>
	</div>
	
	<div>
		<%-- <div class="bd">
				<div>
		          <a class="weui-cell weui-cell_access" href="JavaScript:;">
		            <div class="weui-cell__bd">
		              <strong style="font-size: 16px;color: #000000;"><fmt:formatDate value="${seed.startdate}" pattern="yyyy年MM月dd日"/></strong>
		              <p></p>
		              <span style="font-size: 12px;color: #666666">取样点数：${yieldtest.num}</span><br/>
		              <span style="font-size: 12px;color: #666666">批次种植株数：${yieldtest.strainamount}</span><br/>
		              <span style="font-size: 12px;color: #666666">样点株数：${yieldtest.plantnumber}</span><br/>
		              <span style="font-size: 12px;color: #666666">预计平均株产量：${yieldtest.avgperstrain}</span><br/>
		              <span style="font-size: 12px;color: #666666">预计批次总产量：${yieldtest.batchSum}</span><br/>
		              <span style="font-size: 12px;color: #666666">大薯块总产量：${yieldtest.bigweight}</span><br/>
		              <span style="font-size: 12px;color: #666666">中薯块总产量：${yieldtest.mediumweight}</span><br/>
		              <span style="font-size: 12px;color: #666666">小薯块总产量：${yieldtest.minweight}</span><br/>
		            </div>
		            <div class="weui-cell__ft"></div>
		          </a>
	        	</div>
        	</div>
		</div> --%>
		
		<div class="mui-content">
					<div class="weui-cell__bd" style="margin-top:10px;">
						<div style="margin-left: 18px;margin-top:5px;line-height: 18px;">
						<span style="font-size: 13px;line-height: 18px;" >样点株数：
						<c:if test="${not empty yieldtest.munumber}">
						<span id="munumber">${yieldtest.munumber}</span>
						</c:if> 
						</span>
						<c:if test="${empty yieldtest.munumber}">
						<span style="font-size: 13px;line-height: 18px;" id="munumber">10</span>
						<input type="button" onclick="alterinfo();" class="mui-btn mui-btn-danger" style="float:right;margin:10px 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="样点株数">
						</c:if>
						<br>
						<span style="font-size: 13px;line-height: 18px;">批次种植株数：${yieldtest.strainamount}</span>
						<%-- <span style="font-size: 13px;line-height: 18px;">样点株数：${yieldtest.strainamount}</span> --%>
						<br>
						<span style="font-size: 13px;line-height: 18px;">取样点数：${yieldtest.num}</span>
						<span style="font-size: 13px;line-height: 18px;">预计平均株产量：${yieldtest.avgperstrain}</span>
						<br>
						<span style="font-size: 13px;line-height: 18px;">预计批次总产量：${yieldtest.batchSum}</span>
						<span style="font-size: 13px;line-height: 18px;">大薯块总产量：${yieldtest.bigweight}</span>
						<br>
						<span style="font-size: 13px;line-height: 18px;">中薯块总产量：${yieldtest.mediumweight}</span>
						<span style="font-size: 13px;line-height: 18px;">小薯块总产量：${yieldtest.minweight}</span>
						</div>
					</div>
		</div>
		
	
<div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
	<input value="${seedfile.guid}" type="hidden" id="guid">
		<div id="list"></div>
		<div class="weui-loadmore" style="display: none;" id="dis">
			<i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载...</span>
		</div>
		<div class="weui-loadmore" style="display: none;" id="disp">
			<span class="weui-loadmore__tips">没有更多数据...</span>
		</div>
	</div>
	
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
		
		getdataPage();
		function getdataPage(){
			var seedfileid = '${seedfileid}';
			$("#dis").removeAttr("style");
			$.ajax({
			  url: "${wechatPath}g2g3/yieldtest/page.action",
			  async: true,
			  type:"POST",
			  dataType: 'json',
			  data: {"pageNo":pageNo,
				  seedfileid:seedfileid
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
//					  href="${wechatPath}/g2g3/yieldtest/detail.action?seedfileid='+rows[i].guid+'"
					  content+='<a class="weui-cell weui-cell_access" >';
					  content+='<div class="weui-cell__bd" style="margin-top:10px;">';
					  content+='<p style="font-size:17px;color:#000000">';
					  content+='样点(株数):'+rows[i].munumber+' &nbsp; 总个数:'+rows[i].amount+'&nbsp; 总重量:'+rows[i].weight;
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
		
		
		function alterinfo(){
			$.prompt("修改样点亩数", function(text) {
				  $("#munumber").html("").html(text);
				  $(".munumber").attr("href", "${wechatPath}yieldtest/add.action?seedfileid=${seedfileid}&munumber="+text);
				}, function() {
				  //点击取消后的回调函数
				});
			
		}
		
	</script>
</body>
</html>