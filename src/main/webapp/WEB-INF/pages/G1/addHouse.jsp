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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		网棚信息录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}greenhouses/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">大棚名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="大棚名称" name="housename" id="housename" style="margin-top: 5px;margin-bottom: 5px;" onkeyup="check();">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">距园区距离</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="距园区距离" name="distance" id="distance" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">大棚面积(平米)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="大棚面积(平米)" name="areas" id="areas" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">大概描述</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="大概描述" name="description" id="description" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">平面示意图</label>
				</div>
				<div class="weui-cell__bd">
					<span onclick="imgElement.click();" class="mui-icon mui-icon-camera" style="margin-top: 5px;margin-bottom: 5px;">
						<input class="" accept="image/*"  id="imgElement" type="file"  style="display: none" />
					</span>
				</div>
			</div>
			
			
			<div class="mui-content" style="background-color:#fff">
		   		 <ul class="mui-table-view mui-grid-view" id="images">
			    	
				</ul>
			</div>
			
		</div>
	</form>
<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<!--图片上传 -->
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	
	var type="3";
	var desc="大棚平面图";
	
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
	
	
	
		$("#submitid").on("click",function(){
			var housename=$("#housename").val().replace(/^\s+|\s+$/g,"");
			if(housename==null||housename==""){
				$.toptip('请输入大棚名称', 'error');
				return false;
			}else{
				  var flag=check();
				  if(!flag){
					  $.toptip('大棚名称已存在', 'error');
					  return false;
				  }
			  }
			
			var distance=$("#distance").val();
			if(distance==null||distance==""){
				$.toptip('请输入距园区距离', 'error');
				return false;
			}
			
			var areas=$("#areas").val();
			if(areas==null||areas==""){
				$.toptip('请输入大棚面积(亩)', 'error');
				return false;
			}
			
			var description=$("#description").val();
			if(description==null||description==""){
				$.toptip('请输入大概描述', 'error');
				return false;
			}
			
			
			var distance=$("#distance").val().replace(/^\s+|\s+$/g,"");
			var areas=$("#areas").val().replace(/^\s+|\s+$/g,"");
			var r = /^(0|[1-9]\d{0,2})(\.\d{1,2})?$/;
			if(!r.test(distance)){
				$.toptip('请输入正确距园区距离', 'error');
				return false;
			}
			if(!r.test(areas)){
				$.toptip('请输入正确大棚面积(亩)', 'error');
				return false;
			}
			
			$.showLoading();
			
			//var description=$("#description").val().replace(/^\s+|\s+$/g,"");
			
			var els =document.getElementsByName("imageid");
			
			var imageids = new Array(els.length);
			
			for (var i = 0,j= els.length;i<j;i++){
				imageids[i]=els[i].value;
			}
			
			$.ajax({
				url : "${wechatPath}greenhouses/save.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					housename : housename,
					distance : distance,
					areas : areas,
					description : description,
					imageids :JSON.stringify(imageids)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("新增成功", function() {
							window.location.href="${wechatPath}greenhouses/houseList.action";
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
		
		function check(){
			var housename=$("#housename").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			if(housename!=null && housename!=""){
				$.ajax({
					url : "${path}greenhouses/checkHouseName.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						housename : housename
					},
					success : function(data) {
						if(data.msg=='fail'){
							$.toptip('大棚名称已存在!', 'error');
							flag=false;
						}else if(data.msg=='ok'){
							flag= true;
						}
					},
					error : function(data) {
						flag= false;
					}
				});
			}
			return flag;
		}
		
	</script>
</body>
</html>