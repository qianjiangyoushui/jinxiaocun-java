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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		发起工单
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}greenhouses/save.action" method="post" id="formid">
	<input type="hidden" id="guid" name="guid" value="${tasks.guid}">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">任务名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="任务名称" readonly="readonly" name="ordername" id="ordername" style="margin-top: 5px;margin-bottom: 5px;" value="${tasks.workorder.ordername}">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">开始时间</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="开始时间" readonly="readonly" name="startdate" id="startdate" style="margin-top: 5px;margin-bottom: 5px;" value="<fmt:formatDate value="${tasks.workorder.startdate}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">完成时间</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="完成时间" readonly="readonly" name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px;" value="<fmt:formatDate value="${tasks.workorder.enddate}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="负责人" readonly="readonly" name="principals" id="principals" style="margin-top: 5px;margin-bottom: 5px;" value="${tasks.workorder.username}">
					<input type="hidden" name="principal" id="principal">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">参与人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="参与人" readonly="readonly" name="partic" id="partic" style="margin-top: 5px;margin-bottom: 5px;" value="${tasks.workorder.partic}">
					<input type="hidden" name="canyuid" id="canyuid">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">任务要求</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="任务要求" readonly="readonly" name="taskrequest" id="taskrequest" style="margin-top: 5px;margin-bottom: 5px;" value="${tasks.workorder.taskrequest}">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">完成描述</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="description" id="description" placeholder="完成描述" rows="3" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">拍照上传</label>
				</div>
				<div class="weui-cell__bd">
					<input class="" accept="image/*"  id="imgElement" type="file"  style="margin-top: 5px; margin-bottom: 5px;" />
				</div>
			</div>
			
			<div class="mui-content" style="background-color:#fff">
		    	<ul class="mui-table-view mui-grid-view" id="images">
				</ul>
			</div>
			
			
			
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
	
	var type="120";
	var desc="任务完成图";
	
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
			
			var description=$("#description").val().replace(/^\s+|\s+$/g,"");
			if(description==null||description==""){
				$.toptip('请输入完成描述', 'error');
				return false;
			}
			
			var els =document.getElementsByName("imageid");
			
			var imageids = new Array(els.length);
			
			for (var i = 0,j= els.length;i<j;i++){
				imageids[i]=els[i].value;
			}
			
			var guid = $("#guid").val();
			
			$.showLoading();
			$.ajax({
				url : "${wechatPath}tasks/update.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					guid : guid,
					description : description,
					imageids :JSON.stringify(imageids)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("成功", function() {
							window.location.href="${wechatPath}tasks/index.action";
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