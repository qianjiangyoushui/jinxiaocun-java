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
		新建售后服务信息
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}saledafter/save.action" method="post" id="formid">
		<input  type="hidden" id="clientid" name="clientid" value="${client.guid}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">销售记录：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择销售记录" name="records"  id="records" style="margin-top: 5px;margin-bottom: 5px;">
					<input  type="hidden" id="salesid" name="salesid"/>
				</div>							
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">培养方式</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入培养方式" name="traintype" id="traintype" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种植密度：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择种植密度" name="tlantdensity" id="tlantdensity" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label" style="text-aglin:inline;">切刀是否消毒：</label>
				</div>
				<div class="weui-cell__bd" style="float: right;margin-left: 50%">
					<input class="weui_switch"  type="checkbox"  name="isdisinfect"  id="isdisinfect" style="margin-top: 8px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label">烂薯是否剔除：</label>
				</div>
				<div class="weui-cell__bd" style="float: right;margin-left: 50%">
					<input class="weui_switch"  type="checkbox"  name="isremoved"  id="isremoved" style="margin-top: 8px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">烂薯率：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="rottenrate" id="rottenrate" placeholder="输入烂薯率" rows="2" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label">切种时是否拌药：</label>
				</div>
				<div class="weui-cell__bd" style="float: right;margin-left: 50%">
					<input class="weui_switch"  type="checkbox"  name="ismedicine"  id="ismedicine" style="margin-top: 8px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">拌药种类：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="medicinetype" id="medicinetype" placeholder="输入拌药种类" rows="2" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label">播种时否沟施杀菌剂：</label>
				</div>
				<div class="weui-cell__bd" style="float: right;margin-left: 50%">
					<input class="weui_switch"  type="checkbox"  name="isdisinfecte"  id="isdisinfecte" style="margin-top: 8px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">沟施杀菌剂种类：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="disinfecttype" id="disinfecttype" placeholder="输入沟施杀菌剂种类" rows="2" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">浇水情况：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="watering" id="watering" placeholder="输入浇水情况" rows="2" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">施肥情况：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="manure" id="manure" placeholder="输入施肥情况" rows="2" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病虫害防治情况：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="pests" id="pests" placeholder="输入病虫害防治情况" rows="2" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;"></textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">记录人：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  readonly="readonly" value="${client.clientname}" name="recordby" id="recordby" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">时间：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"   name="lastupdate" id="lastupdate" style="margin-top: 5px; margin-bottom: 5px;"/>
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
			<input type="button" onclick="submitinfo();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
		
	</form>
	<div style="height: 10px;width: 100%"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
		<!--图片上传 -->
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	<script type="text/javascript">
	
	$("#lastupdate").calendar();
	var type="1";
	var desc="售后服务";
	
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
	
		function submitinfo(){
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
			
			var records=$("#records").data("values");
			if(records==""){
				$.toptip('选择销售记录', 'error');
				flag=false;
			}
			$("#salesid").val(records);
			
			if(flag==true){
				var els =document.getElementsByName("imageid");
				
				var imageids = new Array(els.length);
				
				for (var i = 0,j= els.length;i<j;i++){
					imageids[i]=els[i].value;
				}
				
				$.ajax({
					url : "${wechatPath}saledafter/save.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						salesid:$("#salesid").attr("value"),
						traintype:$("#traintype").attr("value"),
						tlantdensity:$("#tlantdensity").attr("value"),
						isremoved:$("#isremoved").attr("value"),
						rottenrate:$("#rottenrate").attr("value"),
						isdisinfect:$("#isdisinfect").attr("value"),
						ismedicine:$("#ismedicine").attr("value"),
						medicinetype:$("#medicinetype").attr("value"),
						isdisinfecte:$("#isdisinfecte").attr("value"),
						disinfecttype:$("#disinfecttype").attr("value"),
						watering:$("#watering").attr("value"),
						manure:$("#manure").attr("value"),
						pests:$("#pests").attr("value"),
						recordby:$("#recordby").attr("value"),
						lastupdate:$("#lastupdate").attr("value"),
						clientid:$("#clientid").attr("value"),
						imageids:JSON.stringify(imageids)
					},
					success : function(data) {
						$.hideLoading();
						if(data.msg=='ok'){
							$.confirm("新增成功", function() {
								window.location.href="${wechatPath}saledafter/list.action?clientid=${client.guid}";
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
		
		$("#records").select({
			  title: "选择销售记录",
			  items: [
			<c:forEach items="${records}" var="record">         
			    {
			      title: '${record.batch}',
			      value: '${record.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		
	</script>
</body>
</html>