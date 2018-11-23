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
		网棚基地
			<a href="${wechatPath}greenhouses/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}greenhouses/addHouse.action" style="position: absolute; right: 27px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}greenhouses/addHouse.action" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建大棚</a>
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
			$.ajax({
			  url: "${wechatPath}greenhouses/pageGreenhouses.action",
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
					  content+='<a style="text-decoration:none; color:#333;border-bottom:1px solid #bdbbbc; display:block;padding:20px 0;background-color: #FFFFFF" ';
					  content += ' onclick="operate(\''+rows[i].guid+'\');">';
					  content+='<table border="0" style="width:100% ;background-color: #FFFFFF"><col style="width:10%"/><col style="width:70%"/><col style="width:15%"/>';
					  content+='<tr><td><div style="margin-left: 8px;">';
					  if(rows[i].url!=""){
						  content+='<img src="'+rows[i].url+'" style="width:77px;height:77px;"/>';
					  }else{
						  content += '<img src="${wechatPath}icon/test.png"/>';
					  }
					  content += '</div></td>';
					  content+='<td align="left" valign="top"><div style="margin-left: 8px;"><strong style=" font-size:14px">'+rows[i].housename+'</strong></div>'
					  if(rows[i].seedfiles[0].batch != null && rows[i].seedfiles[0].batch != ''){
						  content += '<div style=" font-size:12px; color:#999; margin-top:15px;margin-left: 8px; line-height:20px"><span >种植'+rows[i].seedfiles[0].batch+'批次原原种<br/><span>';
						  content+=rows[i].seedfiles[0].varietys.belongsname +'</span></div>';
					  }
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
		
		function operate(houseid){
			$.actions({
				  actions: [{
				    text: "大棚详情",
				    onClick: function() {
				    	window.location.href='${wechatPath}greenhouses/detail.action?guid='+houseid+'&operate=3';
				    }
				  }
				  ,{
					    text: "修改大棚",
					    onClick: function() {
					    	window.location.href='${wechatPath}greenhouses/editGreenhouses.action?houseid='+houseid+'&operate=3';
					    }
					  }
				  ]
				});
		}
	</script>
</body>
</html>