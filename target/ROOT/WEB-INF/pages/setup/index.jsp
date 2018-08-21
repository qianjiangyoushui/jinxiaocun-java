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
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<link rel="stylesheet" href="${wechatPath}css/mui/app.css">
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css"
	href="${wechatPath}css/jquery-weui.min.css">
<link rel="stylesheet" href="${wechatPath}css/swiper-3.3.1.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">
<style>
.mui-card .mui-control-content {
	padding: 10px;
}

.mui-control-content {
	height: 150px;
}
</style>
<body style="background-color: #FFFFFF">
	<div
		style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		设置
		<a href="${wechatPath}index/index.action" style="position: absolute; left: 10px; top: 8px"> 
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a> 
	</div>

	<div class="weui-cells weui-cells_form infinite" style="margin-top: 0;">
		<div id="list">
			<div class="weui-cells" style="width: 100%; margin-top: 0px;">
				<a class="weui-cell weui-cell_access" href="${wechatPath}setup/usermsg.action">
					<div class="weui-cell__bd" style="margin-top: 10px;">
						<p style="font-size: 17px; color: #000000">
							个人信息
						</p>
					</div>
					<div class="weui-cell__ft"></div>
				</a>
				
				<a class="weui-cell weui-cell_access" href="${wechatPath}setup/updatepwd.action">
					<div class="weui-cell__bd" style="margin-top: 10px;">
						<p style="font-size: 17px; color: #000000">
							修改密码
						</p>
					</div>
					<div class="weui-cell__ft"></div>
				</a>
			</div>
			<div class="weui-cells" style="width: 100%; margin-top: 0px;">
				<a class="weui-cell weui-cell_access" id="outLogin">
					<div class="weui-cell__bd" style="margin-top: 10px;">
						<p style="font-size: 17px; color: #000000">
							退出
						</p>
					</div>
					<div class="weui-cell__ft"></div>
				</a>
			</div>
		</div>


		<div class="weui-loadmore" style="display: none;" id="dis">
			<i class="weui-loading"></i> <span class="weui-loadmore__tips">正在加载...</span>
		</div>
		<div class="weui-loadmore" style="display: none;" id="disp">
			<span class="weui-loadmore__tips">没有更多数据...</span>
		</div>
	</div>

	<div style="height: 555px; width: 100%"></div>


	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/mui.min.js"></script>
	<script type="text/javascript">
		$("#outLogin").on("click",function(){
			$.confirm("确认退出！", function() {
				    window.location.href="${wechatPath}setup/outLogin.action";
				  	OCModel.exit();//app退出
				  }, function() {
				  	
				  });

		})
	</script>
</body>
</html>