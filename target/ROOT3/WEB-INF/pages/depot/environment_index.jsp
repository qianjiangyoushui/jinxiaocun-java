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
<style type="text/css">
table,tr,td{
border:1px solid ;"
}
td{
border:0.5px solid ;"
}
</style>
</head>
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		${depot.depotcode }环境记录
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}environment/environment_add.action?type=1&guid=${depot.guid}" style="position: absolute; right: 17px; top: 0px;">
			<img src="${wechatPath}icon/add.png" style="width: 20px" />
		</a>
		<a href="${wechatPath}environment/environment_add.action?type=1&guid=${depot.guid}" style="position: absolute; right: 15px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建</a>
	</div>
	<div>
	<input type="hidden" id="date3" onchange="go(this.value);">
	<div id="list"></div>
	</div> 
	<div id="datalist"></div>
	
	<div id="container" style="min-width:390px;height:300px"></div>
	</body>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
<script src="${wechatPath}js/highcharts.js" type="text/javascript"></script>
<script src="${wechatPath}js/exporting.js" type="text/javascript"></script>


		<script type="text/javascript">
		
		$("#list").calendar({
			  container: "#list",
			  input: "#date3"
			});
		 
		
		 function go(value){
			 var guid = '${depot.guid}';
			 $.ajax({
				  url: "${wechatPath}environment/environment_page.action",
				  type:"POST",
				  async: false,
				  dataType: 'json',
				  data: {depotid:guid,
					  recorddate:value
					  },
				  error:function(data){
					  $.toptip("数据加载失败,请刷新",'error');
				  },
				  success: function(data){
					  $("#datalist").html("");
					  var content="";
					  var pagelist=data.page;
					  
					  pageNo=pagelist.nextPage;//设置pageNo;
					  pageCount=pagelist.pageCount;//设置总页数
					 // href="${wechatPath}outin/outin_index.action?guid='+rows[i].guid+'"
					  var rows=pagelist.rows;
					 
					  for(var i=0;i<rows.length;i++){
							content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
						  content+='<a class="weui-cell weui-cell_access" href="${wechatPath}environment/environment_detail.action?guid='+rows[i].guid+'">';
						  content+='<div class="weui-cell__bd" style="margin-top:10px;">';
						  content+='<p style="font-size:17px;color:#000000">';
						  content+=rows[i].recorddate +"&nbsp;&nbsp;"+rows[i].operatorid;
						  content+='</p></div><div class="weui-cell__ft"></div></a>';
						  content+='</div><div style="margin-bottom: 10px;width: 100%"></div>';
						 
					  }
					  var wendu=new Array;
					  for(var j=0;j<31;j++){
						  wendu[j]=0;
					  }
					 	var shidu=new Array;
					 	 for(var j=0;j<31;j++){
							shidu[j]=0;
						  }
					  var ml = data.monthlistjsons.monthlist;
					  for(var j=0;j<ml.length;j++){
						  wendu[new Date(ml[j].recorddate).getDate()]=ml[j].temperature;
						  shidu[new Date(ml[j].recorddate).getDate()]=ml[j].humidity;
					  }
					  
					  wendu='['+ wendu+']';
					  shidu='['+ shidu+']';
					  
					  $("#datalist").append(content);
					  $("#dis").css("display","none");
					  loading=false;
					  //曲线图
					  var chart = new Highcharts.Chart('container', {
							title: {
						        text: '温湿度曲线图',
						        x: -20
						    },
						    exporting:{  
						    	enabled:false 
						    	},
							xAxis: {
								title: {
						            text: '日期'
						        },
								tickInterval: 2,  //自定义刻度  
						          max:31,//纵轴的最大值  
						          min: 1,//纵轴的最小值  
						    },
						    yAxis: {
						        title: {
						            text: '温湿度 (°C)'
						        },
						        tickInterval: 1,  //自定义刻度  
						          max:30,//纵轴的最大值  
						          min: 0,//纵轴的最小值  
						    },
						    tooltip: {
						        valueSuffix: '°C'
						    },
						    legend: {
						        layout: 'vertical',
						        align: 'right',
						        verticalAlign: 'middle',
						        borderWidth: 0
						    },
						    series: [{
						        name: '温度',
						        data: eval(wendu)
								 
						    }, {
						        name: '湿度',
						        data:  eval(shidu)
						    }]
						});
				  }
				});
		 }
		</script>

</html>