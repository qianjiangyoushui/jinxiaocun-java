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
		新增蚜虫监测记录
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" class="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}aphid/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${aphid.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="recorddate" id="recorddate" style="margin-top: 5px;margin-bottom: 5px">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">诱蚜盘编号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入诱蚜盘编号" name="aphidcode" id="aphidcode" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">蚜虫数量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入蚜虫数量" name="amount" id="amount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">调查人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入调查人" name="investigator" id="investigator" style="margin-top: 5px;margin-bottom: 5px;">
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
			
			<div class="button_sp_area" style="margin:0 30%;">
		        <a href="javascript:history.go(-1);" class="weui-btn weui-btn_mini weui-btn_primary">取消</a>
		        <a href="javascript:;"   class=" submitid weui-btn weui-btn_mini weui-btn_default">确认</a>
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
	
	$("#recorddate").calendar();
	
	var type="112";
	var desc="蚜虫情况图";
	
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
	
	
	
		$(".submitid").on("click",function(){
			var recorddate=$("#recorddate").val().replace(/^\s+|\s+$/g,"");
			if(recorddate==null||recorddate==""){
				$.toptip('请输入日期', 'error');
				return false;
			}
			
			var aphidcode=$("#aphidcode").val().replace(/^\s+|\s+$/g,"");
			if(aphidcode==null||aphidcode==""){
				$.toptip('请输入诱蚜盘编号', 'error');
				return false;
			}
			
			var amount=$("#amount").val().replace(/^\s+|\s+$/g,"");
			if(amount==null||amount==""){
				$.toptip('请输入发生率', 'error');
				return false;
			}
			
			var investigator=$("#investigator").val().replace(/^\s+|\s+$/g,"");
			if(investigator==null||investigator==""){
				$.toptip('请输入调查人', 'error');
				return false;
			}
			var r = /^\+?[1-9][0-9]*$/;
			if(!r.test(amount)){
				$.toptip('请输入正确数量,整数', 'error');
				return false;
			}
			
			var seedfileid = $("#seedfileid").val();
			

			var els =document.getElementsByName("imageid");
			
			var imageids = new Array(els.length);
			
			for (var i = 0,j= els.length;i<j;i++){
				imageids[i]=els[i].value;
			}
			
			$.showLoading();
			
			//$("#formid").submit();
			$.ajax({
				url : "${wechatPath}aphid/save.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					recorddate : recorddate,
					aphidcode : aphidcode,
					amount : amount,
					investigator : investigator,
					seedfileid : seedfileid,
					imageids :JSON.stringify(imageids)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("新增成功", function() {
							window.location.href="${wechatPath}aphid/list.action?seedfileid="+seedfileid;
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