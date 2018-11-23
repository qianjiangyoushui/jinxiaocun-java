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
		测产详情
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div class="mui-content">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
				<fmt:formatDate value="${yieldtest.testdate}" pattern="yyyy-MM-dd"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种薯级别</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.level }
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">项目(样点)</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.project }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">大薯块数量</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.bigamount }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">大薯块重量</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.bigweight }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">中薯块数量</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.mediumamount }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">中薯块重量</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.mediumweight }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">小薯块数量</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.minamount }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">小薯块重量</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.minweight }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">总数量</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.amount }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">总重量(kg)</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.weight }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">亩产(kg)</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.permu }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">测产人</label>
				</div>
				<div class="weui-cell__bd">
					${yieldtest.testperson }
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">拍照上传</label>
				</div>
				<div class="weui-cell__bd">
				</div>
		</div>
		   <div class="mui-content" >
		    <ul class="mui-table-view mui-grid-view" id="images">
		    	
			</ul>
		</div>
		</div>
		
			</div>
	<div style="height: 10px;width: 100%"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	<script type="text/javascript">
	$("#testdate").calendar();
	function submitinfo(){
		var flag=true;
		$(".weui-input").each(function (i){
			if($(this).attr("value").trim()==null||$(this).attr("value").trim()==""){
				flag=false;
			}
		})
		if(flag==true){
			var els =document.getElementsByName("files");
			var files = new Array(els.length);
			for (var i = 0,j= els.length;i<j;i++){
				files[i]=els[i].value;
			}
			$.ajax({
				url : "${wechatPath}g2g3/yieldtest/save.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					testdate:$("#testdate").attr("value"),
					munumber:$("#munumber").attr("value"),
					seedfileid:$("#guid").attr("value"),
					level:$("#level").attr("value"),
					project:$("#project").attr("value"),
					bigamount:$("#bigamount").attr("value"),
					bigweight:$("#bigweight").attr("value"),
					mediumamount:$("#mediumamount").attr("value"),
					mediumweight:$("#mediumweight").attr("value"),
					minamount:$("#minamount").attr("value"),
					minweight:$("#minweight").attr("value"),
					amount:$("#amount").attr("value"),
					weight:$("#weight").attr("value"),
					permu:$("#permu").attr("value"),
					testperson:$("#testperson").attr("value"),
					files:JSON.stringify(files)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("新增成功", function() {
							window.location.href="${wechatPath}g2g3/yieldtest/list.action?guid=${seedfile.guid}";
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
			$.alert("请完善数据！");
		}
		
	}
		
	</script>
</body>
</html>