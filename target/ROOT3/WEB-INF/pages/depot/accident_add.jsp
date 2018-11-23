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
<body style="background-color: #FFFFFF">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
			事故信息添加
			<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick=" subinfo('${type}','${depot.guid }');"  style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="subinfo('${type}','${depot.guid }');" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}depot/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入日期" name="reportdate" id="reportdate"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">熏蒸情况：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" 
						placeholder="请输入熏蒸情况" name="fumigation"
						id="fumigation" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label" style="text-aglin:inline;">入库前是否消毒：</label>
				</div>
				<div class="weui-cell__bd" >
					<input class="weui_switch" style="float: right;" type="checkbox"  name="isdisinfect"  id="isdisinfect" style="margin-top: 8px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">温度保持范围：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入温度保持范围"
							name="temprange" id="temprange"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label" style="text-aglin:inline;">是否有变动：</label>
				</div>
				<div class="weui-cell__bd" >
					<input class="weui_switch" style="float: right;" type="checkbox"  name="ischange"  id="ischange" style="margin-top: 8px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">变动原因：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入变动原因"
							name="changereason" id="changereason"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">目前库存量：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="请输入目前库存量"
							name="instock" id="instock"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害及生理异常发生日期：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入日期"
							name="occurdate" id="occurdate"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害及生理异常发生位点：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入位点"
							name="occurpoint" id="occurpoint"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害及生理异常发生类型：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入类型"
							name="occurtype" id="occurtype"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label" style="text-aglin:inline;">库存马铃薯是否有位置变更：</label>
				</div>
				<div class="weui-cell__bd" >
					<input class="weui_switch" style="float: right;" type="checkbox"  name="placechange"  id="placechange" style="margin-top: 8px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">变更详情：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入变更详情"
							name="changedetail" id="changedetail"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">变更人员：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入变更人员"
							name="reportname" id="reportname"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">拍照上传</label>
				</div>
				<div class="weui-cell__bd">
				<span onclick="imgElement.click();" class="mui-icon mui-icon-camera" style="margin-top: 5px;margin-bottom: 5px;">
					<input class="" accept="image/*" type="file" id="imgElement"  style="display:none"/>
				</span>
				</div>
			</div>
			<div class="weui-cell" style="background-color:white;">
				<div class="mui-content" style="background-color:#fff;border-color:#fff">
			    <ul class="mui-table-view mui-grid-view" id="images" style="background-color:#fff;padding:0;">
				</ul>
				</div>
			</div>
		</div>
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
			<input type="button" onclick="subinfo('${type}','${depot.guid }')" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
			<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
		<script type="text/javascript">
		$("#reportdate").calendar();
		$("#occurdate").calendar();
		var type="1";
		var desc="事故拍照";
		
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
		
		
			function subinfo(type,depotid){
				$(".weui_switch").each(function(i){
					if($(this).attr("checked")=="checked"){
						$(this).attr("value","1");
					}else{
						$(this).attr("value","0")
					}
				})
				
				var flag=true;
				$(".weui-input").each(function (i){
					if($(this).attr("value")==null||$(this).attr("value")==""){
						flag=false;
					}
				})
				
				if(flag==true){
					
					var els =document.getElementsByName("imageid");
					
					var imageids = new Array(els.length);
					
					for (var i = 0,j= els.length;i<j;i++){
						imageids[i]=els[i].value;
					}
					
					$.ajax({
						url : "${wechatPath}accident/save.action",
						type : "POST",
						async : false,
						dataType : 'json',
						data : {
							depotid:depotid,
							reportdate:$("#reportdate").attr("value"),
							fumigation:$("#fumigation").attr("value"),
							isdisinfect:$("#isdisinfect").attr("value"),
							temprange:$("#temprange").attr("value"),
							ischange:$("#ischange").attr("value"),
							changereason:$("#changereason").attr("value"),
							instock:$("#instock").attr("value"),
							occurdate:$("#occurdate").attr("value"),
							occurpoint:$("#occurpoint").attr("value"),
							occurtype:$("#occurtype").attr("value"),
							placechange:$("#placechange").attr("value"),
							changedetail:$("#changedetail").attr("value"),
							reportname:$("#reportname").attr("value"),
							imageids:JSON.stringify(imageids)
						},
						success : function(data) {
							$.hideLoading();
							if(data.msg=='ok'){
								$.confirm("新增成功", function() {
									window.location.href="${wechatPath}accident/accident_index.action?guid=${depot.guid}";
							  }, function() {
								  
							  });
							}
						},
						error : function(data) {
							$.hideLoading();
							$.alert("失败");
						}
					});
				}else{
					$.toptip('请完善数据!', 'error');
				}
			}
			
		</script>
	
</body>
</html>