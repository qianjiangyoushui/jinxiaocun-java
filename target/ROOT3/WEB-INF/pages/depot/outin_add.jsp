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
		<c:if test="${type==1 }">批次入库</c:if>
		<c:if test="${type==2}">批次出库</c:if>
		<c:if test=""></c:if>
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
					<label class="weui-label">种薯批次：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入仓库编号" name="batch" id="batch"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label"><c:if test="${type==1 }">入库吨数</c:if>
											<c:if test="${type==2}">出库吨数</c:if></label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" 
						placeholder="请输入吨数" name="outamount"
						id="outamount" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label"><c:if test="${type==1}">贮存方式：</c:if>
											<c:if test="${type==2}">出库用途：</c:if></label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" <c:if test="${type==2}">placeholder="销售\自用\菜蔬"</c:if>
							<c:if test="${type==1}">placeholder="贮存方式"</c:if> name="outuse" id="outuse"
							style="margin-top: 5px; margin-bottom: 5px;"/>
					</div>
				</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label"><c:if test="${type==1 }">入库时间</c:if>
											<c:if test="${type==2}">出库时间</c:if></label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入时间"
							name="outdate" id="outdate"
							style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">备注：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入备注"
							name="description" id="description"
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
		$("#outdate").calendar();
		var type="1";
		var desc="出入库拍照";
		
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
						url : "${wechatPath}outin/save.action",
						type : "POST",
						async : false,
						dataType : 'json',
						data : {
							batch:$("#batch").attr("data-values"),
							outamount:$("#outamount").attr("value"),
							outuse:$("#outuse").attr("value"),
							outdate:$("#outdate").attr("value"),
							description:$("#description").attr("value"),
							depotid:depotid,
							type:type,
							imageids:JSON.stringify(imageids)
						},
						success : function(data) {
							$.hideLoading();
							if(data.msg=='ok'){
								$.confirm("新增成功", function() {
									window.location.href="${wechatPath}outin/outin_index.action?guid=${depot.guid}";
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
			
			$("#batch").select({
				  title: "批次编号",
				  items: [
				<c:forEach items="${files}" var="file">       
				    {
				      title: '${file.batch}',
				      value: '${file.guid}',
				    },
		    	</c:forEach>
				  ]
				});
		</script>
	
</body>
</html>