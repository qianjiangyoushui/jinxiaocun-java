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
		销售档案号录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}sales/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种薯批次档案：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择种薯批次档案" name="seedids"  id="seedids" style="margin-top: 5px;margin-bottom: 5px;">
					<input  type="hidden" id="seedid" name="seedid"/>
				</div>							
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">销售数量（吨）：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="请输入销售数量（吨）" name="salesamount" id="salesamount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">销售日期：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择销售日期" name="salesdate" id="salesdate" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">运输车牌号：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入车牌号" name="carlicense"  id="carlicense" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">贮存仓库：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择仓库" name="depotids"  id="depotids" style="margin-top: 5px;margin-bottom: 5px;">
					<input  type="hidden" id="depotid" name="depotid"/>
				</div>							
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">销售客户</label>
				</div>
				<div class="weui-cell__bd">
					<c:if test='${!empty clients}'>
						<input class="weui-input" type="text" placeholder="请选择客户"   name="clientids" id="clientids"style="margin-top: 5px;margin-bottom: 5px;">
					</c:if>
					<c:if test='${empty clients}'>
						<input class="weui-input" type="text" value="${client.clientname}" data-values="${client.guid}"  name="clientids" id="clientids"style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="clientguid" value="${client.guid}"/>
					</c:if>
					<input  type="hidden" id="clientid" name="clientid"/>
				</div>
			</div>
			
		</div>
		
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
			<input type="button" onclick="submitinfo();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
	</form>
	<div style="height: 10px;width: 100%"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	$("#salesdate").calendar();
	
		function submitinfo(){
			
			var seedid=$("#seedids").data("values");
			$("#seedid").val(seedid);
			if($("#seedid").val()==""){
				$.toptip('请选择种薯档案', 'error');
				return false;
			}
			
			
			var salesamount=$("#salesamount").val().replace(/^\s+|\s+$/g,"");
			var reg=/^[0-9]+(.[0-9]{0,2})?$/;
			if(!reg.test(salesamount)){
				$.toptip('请输入正确数量,最多两位小数', 'error');
				return false;
			}
			
			var salesdate=$("#salesdate").val().replace(/^\s+|\s+$/g,"");
			if(salesdate==null || salesdate==""){
				$.toptip('请选择销售日期', 'error');
				return false;
			}
			
			var carlicense=$("#carlicense").val().replace(/^\s+|\s+$/g,"");
			if(carlicense==null || carlicense==""){
				$.toptip('请输入运输车牌', 'error');
				return false;
			}
			
			var depotid=$("#depotids").data("values");
			$("#depotid").val(depotid);
			if($("#depotid").val()==""){
				$.toptip('请选择仓库', 'error');
				return false;
			}
			
			
			var clientid=$("#clientids").data("values");
			$("#clientid").val(clientid);
			if($("#clientid").val()==""){
				$.toptip('请选择客户', 'error');
				return false;
			}
			
			
			$("#formid").submit();
		}
		
		<c:if test='${!empty clients}'>
		$("#clientids").select({
			  title: "选择客户",
			  items: [
			<c:forEach items="${clients}" var="client">         
			    {
			      title: '${client.clientname}',
			      value: '${client.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		</c:if>
		
		$("#depotids").select({
			  title: "选择仓库",
			  items: [
			<c:forEach items="${depots}" var="depot">         
			    {
			      title: '${depot.depotcode}',
			      value: '${depot.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		
		
		$("#seedids").select({
			  title: "选择种薯档案",
			  items: [
			<c:forEach items="${seedfiles}" var="file">       
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