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
		核心苗信息录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;"  style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:void(0);" class="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	<div class="mui-content">
	<form action="${wechatPath}seedfile/save.action" method="post" id="formid" enctype="multipart/form-data">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;font-size:14px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">代码</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="核心苗代码数字或字母"  name="batch" id="batch" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">档案描述</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"   placeholder=" 描述该核心苗的附加信息"  name="description" id="description" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">品种</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="核心苗品种"   name="variety" id="variety" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">年份</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="核心苗育种年份或者引进年份" name="years" id="years" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在位置</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="种质资源库"  readonly="readonly" value="种质资源库" name="placeid" id="placeid" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病害监测报告</label>
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
		
		<div class="button_sp_area" style="margin:0 30%;">
	        <a href="javascript:history.go(-1);" class="weui-btn weui-btn_mini weui-btn_primary">取消</a>
	        <a href="javascript:;"   class=" submitid weui-btn weui-btn_mini weui-btn_default">确认</a>
      	</div>
	</form>
	</div>
	<div style="height:50px;"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<!--图片上传 -->
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	
<%-- 	<script src="${wechatPath}js/mui.min.js"></script>	 --%>
		
	<script type="text/javascript">
	var type="1";
	var desc="病害监测报告";
	
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
				checkbatch().then(function(result){
				    if(!result){
				        $.toptip('请填写正确的代码，数字字母-', 'error');
				        return false;
				    }
				});
				$.ajax({
					url : "${wechatPath}seedfile/save.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						batch:$("#batch").attr("value"),
						description:$("#description").attr("value"),
						variety:$("#variety").attr("data-values"),
						years:$("#years").attr("value"),
						placeid:$("#placeid").attr("value"),
						imageids:JSON.stringify(imageids)
					},
					success : function(data) {
						$.hideLoading();
						if(data.msg=='ok'){
							$.confirm("新增成功", function() {
								window.location.href="${wechatPath}seedfile/list.action";
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
			
		})
		
		
		var year=${getSysYear};
		var dictionary=${dictionary};
		$("#years").select({
			  title: "核心苗育种年份或者引进年份",
			  items:year,
			});
		$("#variety").select({
			  title: "核心苗育品种",
			  items:dictionary
			});
		
		function checkbatch(){
		  var batch=$("#batch").val().replace(/^\s+|\s+$/g,"");
          var codereg=/^[A-Z0-9/-]+$/;
		  var promise = new Promise(function(resolve,reject){
		    let result = true;
		    if(!codereg.test(batch)){
		        result=false;
		    }
		    resolve(result)
		  });
		  return promise;
		}
	</script>
</body>
</html>