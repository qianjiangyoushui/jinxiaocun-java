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
		苗床信息修改
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}seedbed/update.action" method="post" id="formid">
		<input type="hidden" name="guid" value="${seedbed.guid}"/>
		<input type="hidden" name="operate" value="${operate}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">苗床名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入苗床名称" value="${seedbed.seedbedname}"
						name="seedbedname" id="seedbedname"
						style="margin-top: 5px; margin-bottom: 5px;" onkeyup="check();"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">瓶苗个数</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" pattern="[0-9]*"
						placeholder="请输入可放置瓶苗个数" name="amount" value="${seedbed.amount}"
						id="amount" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">苗床描述</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入苗床描述" value="${seedbed.description}"
						name="description" id="description"
						style="margin-top: 5px; margin-bottom: 5px;"/>
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
			
		</div>
		
		<div class="mui-content" style="background-color:#fff">
		    <ul class="mui-table-view mui-grid-view" id="images">
		    	
			</ul>
		</div>
		
	</form>

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
	var type="1";
	var desc="苗床平面图";
	var name='${seedbed.seedbedname}';
	
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
			 	var  seedbedname=$("#seedbedname").val().replace(/^\s+|\s+$/g,"");
			  
				  if(seedbedname==null || seedbedname==""){
					  $.toptip('请输入苗床名称', 'error');
						return false;
				  }else{
					  var flag=check();
					  if(!flag){
						  $.toptip('苗床名称已存在', 'error');
						  return false;
					  }
				  }
				  
			  	var amount=$("#amount").val().replace(/^\s+|\s+$/g,"");
				var r = /^\+?[1-9][0-9]*$/;
				if(!r.test(amount)){
					$.toptip('请输入正整数', 'error');
					return false;
				}
				
				$("#formid").submit();
				
		  })
		  
		  function check(){
			var seedbedname=$("#seedbedname").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			
			if(seedbedname==name){
				flag=true;
			}else{
				if(seedbedname!=null && seedbedname!=""){
					$.ajax({
						url : "${path}seedbed/check.action",
						type : "POST",
						async : false,
						dataType : 'json',
						data : {
							seedbedname:seedbedname
						},
						success : function(data) {
							if(data.msg=='fail'){
								$.toptip('苗床名称已存在!', 'error');
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
			}
			return flag;
		}
	</script>
</body>
</html>