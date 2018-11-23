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
		新增测产样点
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	<div class="mui-content">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" value="" placeholder="单行输入" name="testdate" id="testdate" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种薯级别</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" value="" placeholder="单行输入" name="level" id="level" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">项目(样点)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" value="" placeholder="单行输入" name="project"  id="project" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">大薯块数量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input jisuan" type="number" keyup=""  placeholder=">400g(个)" name="bigamount"  id="bigamount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">大薯块重量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input jisuan" type="number"  placeholder=">400g(kg)" name="bigweight"  id="bigweight" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">中薯块数量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input jisuan " type="number"  placeholder="30-400g(个)" name="mediumamount"  id="mediumamount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">中薯块重量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input jisuan" type="number"  placeholder="30-400g(kg)" name="mediumweight"  id="mediumweight" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">小薯块数量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input jisuan" type="number" placeholder="<30g(个)" name="minamount"  id="minamount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">小薯块重量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input jisuan" type="number"  placeholder="<30g(kg)" name="minweight"  id="minweight" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">总数量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number"  readonly="readonly" placeholder="自动计算" name="amount"  id="amount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">总重量(kg)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" readonly="readonly" placeholder="自动计算" name="weight"  id="weight" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">亩产(kg)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" readonly="readonly" placeholder="自动计算" name="permu"  id="permu" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">测产人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="单行输入" name="testperson"  id="testperson" style="margin-top: 5px;margin-bottom: 5px;">
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
		
			</div>
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="hidden" value="${seedfile.guid}" id="guid" name="guid">
			<input type="hidden" value="${yieldtest.munumber}" id="munumber" name="munumber">
			
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
			<input type="button" onclick="submitinfo();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
	
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
	

	$(".jisuan").keyup(function(){
			
// 		$(".jisuan").each(function(){
// 				if($(this).val()==""){
// 					$(this).attr("value",0);
// 				}
// 			});
		
		var bigamount=$("#bigamount").val()==""?0:$("#bigamount").val();
		var mediumamount=$("#mediumamount").val()==""?0:$("#mediumamount").val();
		var minamount=$("#minamount").val()==""?0:$("#minamount").val();
		
		var bigweight=$("#bigweight").val()==""?0:$("#bigweight").val();
		var mediumweight=$("#mediumweight").val()==""?0:$("#mediumweight").val();
		var minweight=$("#minweight").val()==""?0:$("#minweight").val();
		
		
		var munumber=$("#munumber").val()==""?0.04:$("#munumber").val();
		
		  $("#amount").attr("value",parseFloat(bigamount)+parseFloat(mediumamount)+parseFloat(minamount));
		  $("#weight").attr("value",parseFloat(bigweight)+parseFloat(mediumweight)+parseFloat(minweight));
		  $("#permu").attr("value",(parseFloat(bigweight)+parseFloat(mediumweight)+parseFloat(minweight))/parseFloat(munumber));
		  
	});
	
	
	
	$("#testdate").calendar();
	var type="1";
	var desc="测产样点";
	
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
		var flag=true;
		$(".weui-input").each(function (i){
			if($(this).attr("value").trim()==null||$(this).attr("value").trim()==""){
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
					imageids:JSON.stringify(imageids)
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