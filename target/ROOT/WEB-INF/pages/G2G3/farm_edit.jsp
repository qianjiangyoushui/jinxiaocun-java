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
		农场信息录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}farm/update.action" method="post" id="formid">
		<input type="hidden" value="${farm.guid}" name="guid"/>
		<input type="hidden" value="${type}" name="type"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">农场名称：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="例如坡地农场" name="farmname" id="farmname" value="${farm.farmname}" onkeyup="check();"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">农场简称：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="大写字母" name="farmcode" id="farmcode" value="${farm.farmcode}" onkeyup="checkcode();"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">租赁面颊（亩）：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number"  placeholder="租赁合同面积" name="leasearea" value="${farm.leasearea}"
						id="leasearea" style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">可种植面积（亩）：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="实际可耕种面积" value="${farm.plantarea}"
						name="plantarea" id="plantarea"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">土壤性质：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="沙土/黏土" value="${farm.soiltype}"
						name="soiltype" id="soiltype"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">耕地类型：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="坡地/平地" value="${farm.arableland}"
						name="arableland" id="arableland"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">年降雨量：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="年平均降雨量（毫米）" value="${farm.rainfall}"
						name="rainfall" id="rainfall"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">无霜期：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="年无霜期天数" value="${farm.nofrost}"
						name="nofrost" id="nofrost"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">变压器个数：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="配置的变压器个数" value="${farm.transformer}"
						name="transformer" id="transformer"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">变压器描述：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="transfordesc" id="transfordesc" placeholder="描述每个变压器的配置情况，如125kw一台 380kw两台" rows="3" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;">
						${farm.transfordesc}
					</textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">机械配置：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="mechanical" id="mechanical" placeholder="例如：拖拉机3台：John Deer（1354）1台、John Deer （1204）1台、John Deer（484）1台；马铃薯四行播种机1台 ；美诺打药机1台；Grimm双行收获机1台" rows="3" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;">
						${farm.mechanical}
					</textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">人员配置：</label>
				</div>
				<div class="weui-cell__bd">
					<textarea class="weui-textarea" name="staffing" id="staffing" placeholder="农场员工配置" rows="3" style="margin-top: 5px; margin-bottom: 5px;border:solid 1px #E0E0E0; border-radius:10px; resize:none;">
						${farm.staffing}
					</textarea>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">海拔高度：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="单位（米）" value="${farm.altitude}"
						name="altitude" id="altitude"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">酸碱度：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="PH值" value="${farm.ph}"
						name="ph" id="ph"
						style="margin-top: 5px; margin-bottom: 5px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">租赁起止年份：</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="合同开始年份" value="${farm.leasestart}"   name="leasestart" id="leasestart"style="margin-top: 5px;margin-bottom: 5px;width:110px;">
					<input class="weui-input" type="number"  placeholder="合同结束年份" value="${farm.leaseend}"  name="leaseend" id="leaseend" style="margin-top: 5px;margin-bottom: 5px;width:110px;"/>
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">农场示意图</label>
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
		 var type='1';
		 var desc="农场平面图";
		 var name='${farm.farmname}';
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
			 	  var  farmname=$("#farmname").val().replace(/^\s+|\s+$/g,"");
				  if(farmname==null || farmname==""){
					  $.toptip('请输入农场名称', 'error');
						return false;
				  }else{
					  var flag=check();
					  if(!flag){
						  $.toptip('农场名称已存在', 'error');
						  return false;
					  }
				  }
				  var  farmcode=$("#farmcode").val().replace(/^\s+|\s+$/g,"");
                  if(farmcode==null || farmcode==""){
                      $.toptip('请输入农场简码', 'error');
                        return false;
                  }else{
                      var flag=checkcode();
                      if(!flag){
                          $.toptip('农场简称已存在', 'error');
                          return false;
                      }
                  }
                  var codereg=/^[A-Z0-9]+$/;
                  if(!codereg.test(farmcode)){
                    $.toptip('请输入农场简码，大写字母', 'error');
                        return false;
                  }
				  var reg=/^[0-9]+(.[0-9]{0,2})?$/;//小数
				  var r = /^\+?[1-9][0-9]*$/;//整数
				  
				  var  leasearea=$("#leasearea").val();
				  if(!reg.test(leasearea)){
					  $.toptip('请输入正确面积，最多两位小数', 'error');
						return false;
				  }
				  
				  var  plantarea=$("#plantarea").val();
				  if(!reg.test(plantarea)){
					  $.toptip('请输入正确面积，最多两位小数', 'error');
						return false;
				  }
				  
				  var  soiltype=$("#soiltype").val().replace(/^\s+|\s+$/g,"");
				  if(soiltype==null || soiltype==""){
					  $.toptip('请输入土壤性质', 'error');
						return false;
				  }
				  
				  var  arableland=$("#arableland").val().replace(/^\s+|\s+$/g,"");
				  if(arableland==null || arableland==""){
					  $.toptip('请输入耕地类型', 'error');
						return false;
				  }
				  
				  var  rainfall=$("#rainfall").val();
				  if(!reg.test(rainfall)){
					  $.toptip('请输入正确降雨量，最多两位小数', 'error');
						return false;
				  }
				  
				  var nofrost=$("#nofrost").val().replace(/^\s+|\s+$/g,"");
				  if(!r.test(nofrost)){
				  	$.toptip('请输入正确天数，正整数', 'error');
						return false;
				  }
				  
				  var transformer=$("#transformer").val().replace(/^\s+|\s+$/g,"");
				  if(!r.test(transformer)){
				  	$.toptip('请输入变压器个数，正整数', 'error');
						return false;
				  }
				  
				  var  altitude=$("#altitude").val();
				  if(!reg.test(altitude)){
					  $.toptip('请输入正确海拔高度，最多两位小数', 'error');
						return false;
				  }
				  
				  var  ph=$("#ph").val();
				  if(!reg.test(ph)){
					  $.toptip('请输入正确酸碱度，最多两位小数', 'error');
						return false;
				  }
				  
				  var leasestart=$("#leasestart").val().replace(/^\s+|\s+$/g,"");
				  if(!r.test(leasestart)){
						$.toptip('请输入租赁开始年份，4位整数', 'error');
						return false;
				  }
				  
				  var leaseend=$("#leaseend").val().replace(/^\s+|\s+$/g,"");
				  if(!r.test(leaseend)){
						$.toptip('请输入租赁结束年份，4位整数', 'error');
						return false;
				  }
			
				  $("#formid").submit();
		  })
		  
		  function check(){
			var farmname=$("#farmname").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			
			if(name==farmname){
				flag=true;
			}else{
				if(farmname!=null && farmname!=""){
					$.ajax({
						url : "${path}farm/check.action",
						type : "POST",
						async : false,
						dataType : 'json',
						data : {
							farmname:farmname
						},
						success : function(data) {
							if(data.msg=='fail'){
								$.toptip('农场名称已存在!', 'error');
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
			var farmcode=$("#farmcode").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			if(farmcode!=null && farmcode!=""){
				$.ajax({
					url : "${path}farm/checkcode.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						farmcode:farmcode
					},
					success : function(data) {
						if(data.msg=='fail'){
							$.toptip('农场简码已存在!', 'error');
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
			return flag;
		}

	</script>
</body>
</html>