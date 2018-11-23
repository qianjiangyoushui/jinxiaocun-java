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
		新增植保方案实施情况
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
			<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/save.png" style="width: 20px" />
			</a>
			<a href="javascript:;" id="submitid" class="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}eppo/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${eppo.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择日期" name="dodate" id="dodate" style="margin-top: 5px;margin-bottom: 5px">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用途</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用途" name="usetype" id="usetype" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药种类</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药种类" name="drugkind" id="drugkind" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药总量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药总量" name="drugdose" id="drugdose" style="margin-top: 5px;margin-bottom: 5px;width:110px">
					<input class="weui-input" type="text" placeholder="单位" name="drugdoseunit" id="drugdoseunit" style="margin-top: 5px;margin-bottom: 5px;width:110px">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">平米用药量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入平米用药量" name="squaredose" id="squaredose" style="margin-top: 5px;margin-bottom: 5px;width:110px">
					<input class="weui-input" type="text" placeholder="单位" name="squarunit" id="squarunit" style="margin-top: 5px;margin-bottom: 5px;width:110px">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">总用水量(L)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入亩用水量" name="waterdose" id="waterdose" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药方式</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入用药方式" name="drugusetype" id="drugusetype" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">用药时间段</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="startdate" id="startdate" style="margin-top: 5px;margin-bottom: 5px; width:110px">
					<input class="weui-input" type="text" name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px; width:110px">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">操作人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="请输入操作人" name="principal" id="principal" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入负责人" name="uploadperson" id="uploadperson" style="margin-top: 5px;margin-bottom: 5px;">
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
	$("#dodate").calendar();
	 $("#startdate").picker({
		 title : '请选择开始时间',
		 cols: [
		        {
		          textAlign: 'center',
		          values: ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']
		          //如果你希望显示文案和实际值不同，可以在这里加一个displayValues: [.....]
		        },
		        {
		          textAlign: 'center',
		          values: ':'
		        },
		        {
		          textAlign: 'center',
		          values: ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '44', '44', '45', '46', '47', '48', '49', '50', '51', '52', '55', '55', '55', '56', '57', '58', '59']
		        }
		      ]
		
	}); 
	 $("#enddate").picker({
		 title : '请选择结束时间',
		 cols: [
		        {
		          textAlign: 'center',
		          values: ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23']
		          //如果你希望显示文案和实际值不同，可以在这里加一个displayValues: [.....]
		        },
		        {
		          textAlign: 'center',
		          values: ':'
		        },
		        {
		          textAlign: 'center',
		          values: ['00', '01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24', '25', '26', '27', '28', '29', '30', '31', '32', '33', '34', '35', '36', '37', '38', '39', '40', '41', '42', '44', '44', '45', '46', '47', '48', '49', '50', '51', '52', '55', '55', '55', '56', '57', '58', '59']
		        }
		      ]
		
	}); 
	/* $("#startdate").calendar();
	$("#enddate").calendar(); */
	
	var type="119";
	var desc="植保情况图";
	
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
			 var dodate=$("#dodate").val().replace(/^\s+|\s+$/g,"");
			if(dodate==null||dodate==""){
				$.toptip('请输入日期', 'error');
				return false;
			}
			
			var usetype=$("#usetype").val().replace(/^\s+|\s+$/g,"");
			if(usetype==null||usetype==""){
				$.toptip('请输入用途', 'error');
				return false;
			}
			
			var drugkind=$("#drugkind").val().replace(/^\s+|\s+$/g,"");
			if(drugkind==null||drugkind==""){
				$.toptip('请输入用药种类', 'error');
				return false;
			}
			
			var drugdose=$("#drugdose").val().replace(/^\s+|\s+$/g,"");
			if(drugdose==null||drugdose==""){
				$.toptip('请输入用药总量', 'error');
				return false;
			}
			
			var drugdoseunit=$("#drugdoseunit").data("values");
			if(drugdoseunit==null||drugdoseunit==""){
				$.toptip('请选择用药总量单位', 'error');
				return false;
			}
			
			var squaredose=$("#squaredose").val().replace(/^\s+|\s+$/g,"");
			if(squaredose==null||squaredose==""){
				$.toptip('请输入平米用药量', 'error');
				return false;
			}
			
			var squarunit=$("#squarunit").data("values");
			if(squarunit==null||squarunit==""){
				$.toptip('请选择平米用药量单位', 'error');
				return false;
			}
			
			var waterdose=$("#waterdose").val().replace(/^\s+|\s+$/g,"");
			if(waterdose==null||waterdose==""){
				$.toptip('请输入总用水量', 'error');
				return false;
			}
			
			var drugusetype=$("#drugusetype").val().replace(/^\s+|\s+$/g,"");
			if(drugusetype==null||drugusetype==""){
				$.toptip('请输入用药方式', 'error');
				return false;
			}
			
			var startdate=$("#startdate").val().replace(/^\s+|\s+$/g,"");
			var enddate=$("#enddate").val().replace(/^\s+|\s+$/g,"");
			if((startdate==null||startdate=="") || (enddate==null||enddate=="")){
				$.toptip('请输入用药时间段', 'error');
				return false;
			}
			
			var principal=$("#principal").val().replace(/^\s+|\s+$/g,"");
			if(principal==null||principal==""){
				$.toptip('请输入操作人', 'error');
				return false;
			}
			
			var uploadperson=$("#uploadperson").val().replace(/^\s+|\s+$/g,"");
			if(uploadperson==null||uploadperson==""){
				$.toptip('请输入负责人', 'error');
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
				url : "${wechatPath}eppo/save.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					dodate : dodate,
					usetype : usetype,
					drugkind : drugkind,
					drugdose : drugdose,
					drugdoseunit : drugdoseunit,
					squaredose : squaredose,
					squarunit : squarunit,
					drugusetype : drugusetype,
					waterdose : waterdose,
					startdate : startdate,
					enddate : enddate,
					principal : principal,
					uploadperson : uploadperson,
					seedfileid : seedfileid,
					imageids :JSON.stringify(imageids)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("新增成功", function() {
							window.location.href="${wechatPath}eppo/list.action?seedfileid="+seedfileid;
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
		
		$("#squarunit").select({
			  title: "选择单位",
			  items: [
			    {
			      title: '克',
			      value: '克'
			    },
			    {
				      title: 'ML',
				      value: 'ML'
				    }
			  ]
			});
		
		$("#drugdoseunit").select({
			  title: "选择单位",
			  items: [
			    {
			      title: '克',
			      value: '克'
			    },
			    {
				      title: 'ML',
				      value: 'ML'
				}
			  ]
			});
		
	</script>
</body>
</html>