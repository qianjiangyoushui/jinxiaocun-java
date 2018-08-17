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
		地块圈信息修改
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}plot/update.action" method="post" id="formid">
		<input type="hidden" name="type" value="${type}"/>
		<input type="hidden" name="guid" value="${plot.guid}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">地块圈名称：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="例如：坡底一号圈" name="plotname" id="plotname" value="${plot.plotname}" onkeyup="check();"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">地块圈简码：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="大写字母数字" name="plotcode" id="plotcode" value="${plot.plotcode}" onkeyup="checkcode();"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">灌溉类型：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="喷灌/滴灌/旱地" name="irrigatetype" value="${plot.irrigatetype}"
						id="irrigatetype" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">灌溉机品牌：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="例如（林赛、伊尔）" value="${plot.irrigbrand}"
						name="irrigbrand" id="irrigbrand"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">机井个数：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="有几口井" value="${plot.wellsamount}"
						name="wellsamount" id="wellsamount"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">机井描述：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="wellsdisc" id="wellsdisc" placeholder="描述机井详细信息比如：3口井（30米30吨、30米30吨、45米30吨）" rows="3" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;">
						${plot.wellsdisc}
					</textarea>
				</div>
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">与村庄距离：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="单位KM" value="${plot.distvillage}"
						name="distvillage" id="distvillage" 
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">与公路距离：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="单位KM" value="${plot.distroad}"
						name="distroad" id="distroad"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">与病害作物距离：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="与其他马铃薯、油菜作物的距离"  value="${plot.distother}"
						name="distother" id="distother"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">平面示意图</label>
				</div>
				<div class="weui-cell__bd">
					<span onclick="imgElement.click();" class="mui-icon mui-icon-camera" style="margin-top: 5px;margin-bottom: 5px;">
						<input class="" accept="image/*"  id="imgElement" type="file"  style="display: none" />
					</span>
				</div>
			</div>
			
		</div>
		
		<div class="mui-content" style="background-color:#fff">
		    <ul class="mui-table-view mui-grid-view" id="images">
		    	
			</ul>
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	
	<!--图片上传 -->
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	
	<script type="text/javascript">
	var type="1";
	var desc="地块平面图";
	var name='${plot.plotname}';
	
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
			  
			  var reg=/^[0-9]+(.[0-9]{0,2})?$/;//小数
			  var r = /^\+?[1-9][0-9]*$/;//整数
			  		
			 	  var  plotname=$("#plotname").val().replace(/^\s+|\s+$/g,"");
				  if(plotname==null || plotname==""){
					  $.toptip('请输入地块圈名称', 'error');
						return false;
				  }else{
					  var flag=check();
					  if(!flag){
						  $.toptip('地块圈名称已存在', 'error');
						  return false;
					  }
				  }
			 	  var  plotcode=$("#plotcode").val().replace(/^\s+|\s+$/g,"");
				  if(plotcode==null || plotcode==""){
					  $.toptip('请输入地块圈简码', 'error');
						return false;
				  }else{
					  var flag=checkcode();
					  if(!flag){
						  $.toptip('地块圈简码已存在', 'error');
						  return false;
					  }
				  }
				  var codereg=/^[A-Z0-9]+$/;
				  if(!codereg.test(plotcode)){
				    $.toptip('请输入地块圈简码，大写字母', 'error');
                  	return false;
				  }

				  var  irrigatetype=$("#irrigatetype").val().replace(/^\s+|\s+$/g,"");
				  if(irrigatetype==null || irrigatetype==""){
					  $.toptip('请输入灌溉类型', 'error');
						return false;
				  }
				  
				  var  irrigbrand=$("#irrigbrand").val().replace(/^\s+|\s+$/g,"");
				  if(irrigbrand==null || irrigbrand==""){
					  $.toptip('请输入灌溉机品牌', 'error');
						return false;
				  }
				  
				  var wellsamount=$("#wellsamount").val().replace(/^\s+|\s+$/g,"");
				  if(!r.test(wellsamount)){
				  	$.toptip('请输入正确机井个数，正整数', 'error');
						return false;
				  }
				  
				  var wellsdisc=$("#wellsdisc").val().replace(/^\s+|\s+$/g,"");
				  
				  var  distvillage=$("#distvillage").val();
				  if(!reg.test(distvillage)){
					  $.toptip('请输入正确村庄距离，最多两位小数', 'error');
						return false;
				  }
				  
				  var  distroad=$("#distroad").val();
				  if(!reg.test(distroad)){
					  $.toptip('请输入正确公路距离，最多两位小数', 'error');
						return false;
				  }
				  
				  var  distother=$("#distother").val();
				  if(!reg.test(distroad)){
					  $.toptip('请输入正确病害作物距离，最多两位小数', 'error');
						return false;
				  }
				   
				  $("#formid").submit();
		  })
		  
		  function check(){
			var plotname=$("#plotname").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			if(name==plotname){
				flag=true;
			}else{
				if(plotname!=null && plotname!=""){
					$.ajax({
						url : "${path}plot/check.action",
						type : "POST",
						async : false,
						dataType : 'json',
						data : {
							plotname:plotname
						},
						success : function(data) {
							if(data.msg=='fail'){
								$.toptip('地块圈名称已存在!', 'error');
								flag=false;
							}else if(data.msg=='ok'){
								flag= true;
							}
						},
						error : function(data) {
							flag= false;
						}
					});
				}
			}
			return flag;
		}
		  function checkcode(){
			var plotcode=$("#plotcode").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			if(name==plotname){
				flag=true;
			}else{
				if(plotname!=null && plotname!=""){
					$.ajax({
						url : "${path}plot/checkcode.action",
						type : "POST",
						async : false,
						dataType : 'json',
						data : {
							plotcode:plotcode
						},
						success : function(data) {
							if(data.msg=='fail'){
								$.toptip('地块圈简称已存在!', 'error');
								flag=false;
							}else if(data.msg=='ok'){
								flag= true;
							}
						},
						error : function(data) {
							flag= false;
						}
					});
				}
			}
			return flag;
		}
	</script>
</body>
</html>