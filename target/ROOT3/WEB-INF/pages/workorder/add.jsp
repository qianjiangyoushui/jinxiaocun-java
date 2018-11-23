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
		<a href="${wechatPath}workorder/index.action" style="position: absolute; left: 10px; top: 8px">
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
					<label class="weui-label">任务名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="任务名称" name="ordername" id="ordername" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">开始时间</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="开始时间" name="startdate" id="startdate" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">完成时间</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="完成时间" name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="负责人" name="principals" id="principals" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="principal" id="principal">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">参与人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="参与人" name="partic" id="partic" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="canyuid" id="canyuid">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">任务要求</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="任务要求" name="taskrequest" id="taskrequest" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			
			
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	
	
	
	
	
		$("#submitid").on("click",function(){
			var ordername=$("#ordername").val().replace(/^\s+|\s+$/g,"");
			if(ordername==null||ordername==""){
				$.toptip('请输入任务名称', 'error');
				return false;
			}
			
			var startdate=$("#startdate").val().replace(/^\s+|\s+$/g,"");
			if(startdate==null||startdate==""){
				$.toptip('请输入开始时间', 'error');
				return false;
			}
			
			var enddate=$("#enddate").val().replace(/^\s+|\s+$/g,"");
			if(enddate==null||enddate==""){
				$.toptip('请输入完成时间', 'error');
				return false;
			}
			var principals=$("#principals").data("values");
			$("#principal").val(principals);
			var principal = $("#principal").val();
			if(principal==null||principal==""){
				$.toptip('请输入负责人', 'error');
				return false;
			}
			
			var canyuids=$("#partic").data("values");
			$("#canyuid").val(canyuids);
			var canyuid = $("#canyuid").val();
			if(canyuid==null||canyuid==""){
				$.toptip('请输入参与人', 'error');
				return false;
			}
			
			var taskrequest=$("#taskrequest").val().replace(/^\s+|\s+$/g,"");
			if(taskrequest==null||taskrequest==""){
				$.toptip('请输入任务要求', 'error');
				return false;
			}
			
			
			
			$.showLoading();
			var partic = $("#partic").val();
			$.ajax({
				url : "${wechatPath}workorder/save.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					ordername : ordername,
					startdate : startdate,
					enddate : enddate,
					principal : principal,
					canyuid : canyuid,
					partic : partic,
					taskrequest :taskrequest
				},
				success : function(data) {
					$.hideLoading();
					if(data.result){
						$.alert(data.message);
						window.location.href="${wechatPath}workorder/index.action";
					}
				},
				error : function(data) {
					$.hideLoading();
					$.alert("失败");
				}
			});
		})
		
		$("#principals").select({
		  title: "选择负责人",
		  items: [
		<c:forEach items="${userList}" var="user">
		    {
		      title: '${user.username}',
		      value: '${user.guid}'
		    },
  	</c:forEach>
		  ]
		});
	
	$("#partic").select({
		  title: "选择参与人",
		  multi: true,
		  items: [
		<c:forEach items="${userList}" var="user">       
		    {
		    	title: '${user.username}',
			    value: '${user.guid}'
		    },
  	</c:forEach>
		  ]
		});
		
	</script>
</body>
</html>