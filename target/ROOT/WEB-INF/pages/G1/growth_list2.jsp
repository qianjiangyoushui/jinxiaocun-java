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
<link rel="stylesheet" type="text/css" href="${wechatPath}js/swiper/style.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}js/swiper/swiper.min.css">


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
		生长记录
		<c:choose>
		<c:when test="${sessionScope.operate == '5'}">
		<a href="${wechatPath}greenhouses/housedetail.action?guid=${seedfileid}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		</c:when>
		<c:otherwise>
		<a href="${wechatPath}g2g3/detail.action?seedfileid=${seedfileid}&operate=${sessionScope.operate}&type=${sessionScope.g2g3Type}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		</c:otherwise>
		</c:choose>
		
		
		
		<c:if test="${sessionScope.user.depart.departid eq '5' ||sessionScope.user.depart.departid eq '8'}">
			<a href="${wechatPath}growth/add.action?relatedid=${seedfileid}&type=11&description=G1生长记录&url=${url}" style="position: absolute; right: 27px; top: 0px;">
				<img src="${wechatPath}icon/add.png" style="width: 20px" />
			</a>
			<a href="JavaScript:add();" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建记录</a>
		</c:if>
	</div>
	
	<input type="hidden" id="date3" onchange="go(this.value,'${seedfileid}');">
	<div id="list">
	</div>
	<div class="weui-loadmore" id="loadImages">
  <i class="weui-loading"></i>
  <span class="weui-loadmore__tips">正在加载</span>
</div>
	<!-- <a href="javascript:;" id="pb1" class="weui-btn weui-btn_mini weui-btn_default" style="height:25px;">点击查看</a> -->
	<div class="imgList" thumblist="list" style="height:100%;width:100%;overflow:hidden">
		<%-- <img src="${wechatPath}icon/kaihu.jpg" height="110" width="30%" style="float:left">
		<img src="${wechatPath}icon/kaihu.jpg" height="110" width="30%" style="float:left">
		<img src="${wechatPath}icon/kaihu.jpg" height="110" width="30%" style="float:left"> --%>
	</div>
  
  <div id="ceng">
  <div class="swiper-container" id="origin-img">
    <div class="swiper-wrapper"></div>
    <div class="swiper-pagination"></div>
  </div></div>
  
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/swiper/swiper.min.js" type="text/javascript"></script>

<!-- 	分页加载	 -->
	<script type="text/javascript">
	$("#datetime-start").calendar();
	 $("#datetime-end").calendar();
	 
	 $("#list").calendar({
		  container: "#list",
		  input: "#date3"
		});
	 var imgsdata;
	 
	 var pb1;
	 var seedfileid = '${seedfileid}';
	// alert(seedfileid);
	 function go(value,seedfileid1){
		 $.ajax({
			  url: "${wechatPath}growth/imageList.action",
			  type:"POST",
			  async: true,
			  dataType: 'json',
			  data: {uploaddate:value,relatedid:seedfileid1,type:'11'},
			  error:function(data){
				  $.toptip("数据加载失败,请刷新",'error');
				  $("#pb1").hide();
			  },
			  success: function(data){
				   console.log(data);
				   var html = '';
				   if(data.length == 0){
						 // $("#pb1").hide();
						 $(".imgList").html("");
						 $("#loadImages").hide();
						  return;
					  }
					 // $("#pb1").show();
					 $(".imgList").html("");
					 $("#loadImages").show();
					  var img = '';
					  
					  for(var i=0;i<data.length;i++){
						 if(img == ''){
							 img = data[i].url;
						 }else{
							 img = img + ',' + data[i].url
						 }
						 html += '<img src="'+data[i].url+'" height="110" width="32%" style="float:left;margin-left:3px">';
					  }
					  
				   /* if(data.length == 0){
					  $("#pb1").hide();
					  return;
				  }
				  $("#pb1").show();
				  var img = '';
				  for(var i=0;i<data.length;i++){
					 if(img == ''){
						 img = data[i].url;
					 }else{
						 img = img + ',' + data[i].url
					 }
				  }
				  console.log(img);
				  pb1 = $.photoBrowser({
				        items: [
							img
				        ],

				        onSlideChange: function(index) {
				          console.log(this, index);
				        },

				        onOpen: function() {
				          console.log("onOpen", this);
				        },
				        onClose: function() {
				          console.log("onClose", this);
				        }
				      }); */
				      //alert(img);
				      //imgsdata={"list":[img]};
				      
				      
				       var imgs = img.split(",");
				        var img = [];
					  var flag = 0;
					  for(var i = 0 ; i < imgs.length ; i++){
						    img[i] = new Image()
						    img[i].src = imgs[i]
						    img[i].onload = function(){
						       //第i张图片加载完成
						       flag++
						       if( flag == imgs.length ){
						          //全部加载完成
						          console.log("加载完成");
						          $("#loadImages").hide();
						          $(".imgList").html(html);
						          var ceng=' <div class="swiper-container" id="origin-img">';
						    	  ceng += '  <div class="swiper-wrapper"></div>';
						    	  ceng+=' <div class="swiper-pagination"></div></div>';
						    	 $("#ceng").html(ceng);
						    	 
						    	 
						    	 var swiper = new Swiper('.swiper-container',{
										zoom:true,
										  width: window.innerWidth,
										  virtual: true,
											spaceBetween:20,
											pagination: {
									          el: '.swiper-pagination',
									          type: 'fraction',
									       },
										   on:{
									         click: function(){
									           $('#origin-img').fadeOut('fast');
											   this.virtual.slides.length=0;
									           this.virtual.cache=[]; 
											   swiperStatus=false;
											   
									       },
									    },
									  
									  });
							      
									  $('.imgList img').click(function(){
										  clickIndex=$(this).index();
									  
										  imglist=$(this).parent().attr('thumblist');
										  //imgs = imgsdata["list"];
										  
										 
										  for(i=0;i<imgs.length;i++){
											  
											   swiper.virtual.appendSlide('<div class="swiper-zoom-container"><img src="'+imgs[i]+'" /></div>');
											}
										  swiper.slideTo(clickIndex);
										  $('#origin-img').fadeIn('fast');
										  swiperStatus=true;
										  
									  });
						    	 
						    	 
						       }
						    }
					  } 
					 
					 
			         
				      
				      
				      
						
				      
			  }
			});
	 }
	 
	 $("#pb1").click(function() {
	        pb1.open();
	      });
	 
	 
		  
		 
			  
		//切换图状态禁止页面缩放	
			document.addEventListener('touchstart',function (event) {  
		            if(event.touches.length>1 && swiperStatus){  
		                event.preventDefault();  
		            }  
		        })  
		        var lastTouchEnd=0;  
		    document.addEventListener('touchend',function (event) {  
		            var now=(new Date()).getTime();  
		            if(now-lastTouchEnd<=300){  
		                event.preventDefault();  
		            }  
		            lastTouchEnd=now;  
		        },false)

		    document.addEventListener('touchmove',function(e){
			    if(swiperStatus){
		            e.preventDefault();
		        }
		    })	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	  var url = '${url}';
	  
	 function add(){
		 var date1 = $("#date3").val();
		 window.location="${wechatPath}growth/add.action?relatedid="+seedfileid+"&type=11&description=生长记录&url="+url+"&date1="+date1;
	 }
	</script>
</body>
</html>