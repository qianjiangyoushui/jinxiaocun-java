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
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<body style="background-color: #FFFFFF">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		客户信息录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}client/save.action" method="post" id="formid">
		<input  type="hidden" value="${client.guid }" name="guid" id="guid" />
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">客户姓名：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" value="${client.clientname }"
						name="clientname" id="clientname"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">联系方式：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" 
						value="${client.tlephone }" name="tlephone"
						id="tlephone" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种植面积：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" value="${client.plantarea }"
						name="plantarea" id="plantarea"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">地址：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" value="${client.province } ${client.city } ${client.area } ${client.street }"
						name="address" id="address"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>

<!-- 			<div class="weui-cell"> -->
<!-- 				<div class="weui-cell__hd"> -->
<!-- 					<label class="weui-label">头像</label> -->
<!-- 				</div> -->
<!-- 				<div class="weui-cell__bd"> -->
<!-- 					<span onclick="imgElement.click();" class="mui-icon mui-icon-camera" style="margin-top: 5px;margin-bottom: 5px;"> -->
<!-- 						<input class="" accept="image/*"  id="imgElement" type="file"  style="display: none" /> -->
<!-- 					</span> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
		
<!-- 		<div class="mui-content" style="background-color:#fff"> -->
<!-- 		    <ul class="mui-table-view mui-grid-view" id="images"> -->
		    	
<!-- 			</ul> -->
<!-- 		</div> -->
		
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/city-picker.min.js" type="text/javascript"></script>
	<!--图片上传 -->
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	
	<script type="text/javascript">
	var type ='1';
	var desc='客户头像';
	
	function uploadimg(src,date,type,desc){
		var imageid="";
		$.ajax({
			url : "${wechatPath}upload/imagesave.action",
			type : "POST",
			async : false,
			dataType : 'json',
			data : {
				files:src,
				date:date,
				type:type,
				desc:desc
			},
			success : function(data) {
				$.hideLoading();
				if(data.msg=='ok'){
					imageid=data.imageid;
				}
			},
			error : function(data) {
				$.alert("失败");
			}
		});
		return imageid;
	}
	
	 	$("#address").cityPicker({
		    title: "请选择所在地"
		  });
	
		  $("#submitid").on("click",function(){
			 	var  clientname=$("#clientname").val().replace(/^\s+|\s+$/g,"");
			  
				  if(clientname==null || clientname==""){
					  $.toptip('请输入客户姓名！', 'error');
						return false;
				  }
				  
			  	var tlephone=$("#tlephone").val().replace(/^\s+|\s+$/g,"");
				if(tlephone==null||tlephone==""){
					$.toptip('请输入联系方式', 'error');
					return false;
				}
				
				var plantarea=$("#plantarea").val().replace(/^\s+|\s+$/g,"");
				var reg=/^[0-9]+(.[0-9]{0,2})?$/;
				if(!reg.test(plantarea)){
					$.toptip('请输入正确种植面积,最多两位小数', 'error');
					return false;
				}
				
				var address=$("#address").val();
				if(address==null||address==""){
					$.toptip('请选择地址', 'error');
					return false;
				}
				
// 				var els =document.getElementsByName("imageid");
// 				if(els.length>1){
// 					$.toptip('只能上传一张头像!', 'error');
// 					return false;
// 				}
// 				var imageids = new Array(els.length);
// 				for (var i = 0,j= els.length;i<j;i++){
// 					imageids[i]=els[i].value;
// 				}
				
// 				$.showLoading();
				$.ajax({
					url : "${wechatPath}client/update.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						clientname:clientname,
						tlephone:tlephone,
						plantarea:plantarea,
						address:address,
						guid:$("#guid").attr("value")
// 						imageids:JSON.stringify(imageids)
					},
					success : function(data) {
						$.hideLoading();
						if(data.msg=='ok'){
							$.confirm("修改成功", function() {
								window.location.href="${wechatPath}client/index.action?clientid="+$("#guid").attr("value");
						  }, function() {
							  
						  });
						}
					},
					error : function(data) {
						$.hideLoading();
						$.alert("失败");
					}
				});
		  })
	</script>
</body>
</html>