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
		病虫害发生详细情况
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<c:if test="${sessionScope.user.depart.departid eq '5'}">
			<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/save.png" style="width: 20px" />
			</a>
			<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
		</c:if>
	</div>
	
	<form action="${wechatPath}pest/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${pest.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" readonly="readonly" name="happendate" id="happendate" style="margin-top: 5px;margin-bottom: 5px" value="<fmt:formatDate value="${pest.happendate}" pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">病虫害种类</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" readonly="readonly" placeholder="请输入病虫害种类" name="pesttype" id="pesttype" style="margin-top: 5px;margin-bottom: 5px;" value="${pest.pesttype}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">位置编号</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" readonly="readonly" placeholder="请输入位置编号" name="placecode" id="placecode" style="margin-top: 5px;margin-bottom: 5px;" value="${pest.placecode}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">发生率(%)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" readonly="readonly" placeholder="请输入发生率，小数或整数" name="probability" id="probability" style="margin-top: 5px;margin-bottom: 5px;" value="${pest.probability}">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">调查人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" readonly="readonly" placeholder="请输入调查人" name="principal" id="principal" style="margin-top: 5px;margin-bottom: 5px;" value="${pest.principal}">
				</div>							
			</div>
			
			
			<c:if test="${!empty imgList}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">查询图片</label>
					</div>
					<div class="weui-cell__bd">
						<!-- <input class="" accept="image/*"  id="imgElement" type="file"  style="margin-top: 5px; margin-bottom: 5px;" /> -->
						<a href="javascript:;" id="pb1" class="weui-btn weui-btn_mini weui-btn_default" style="height:25px;">点击查看</a>
					</div>
				</div>
			</c:if>
			
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
	
	<script src="${wechatPath}js/swiper.js"></script>
	<script type="text/javascript">
	
	
	var type="111";
	var desc="病虫害情况图";
	
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
	
		/* $("#submitid").on("click",function(){
			var happendate=$("#happendate").val().replace(/^\s+|\s+$/g,"");
			if(happendate==null||happendate==""){
				$.toptip('请输入日期', 'error');
				return false;
			}
			
			var pesttype=$("#pesttype").val().replace(/^\s+|\s+$/g,"");
			if(pesttype==null||pesttype==""){
				$.toptip('请输入病虫害种类', 'error');
				return false;
			}
			
			var placecode=$("#placecode").val().replace(/^\s+|\s+$/g,"");
			if(placecode==null||placecode==""){
				$.toptip('请输入位置编号', 'error');
				return false;
			}
			
			var probability=$("#probability").val().replace(/^\s+|\s+$/g,"");
			if(probability==null||probability==""){
				$.toptip('请输入发生率', 'error');
				return false;
			}
			
			var principal=$("#principal").val().replace(/^\s+|\s+$/g,"");
			if(principal==null||principal==""){
				$.toptip('请输入调查人', 'error');
				return false;
			}
			var r = /^(0|[1-9]\d{0,2})(\.\d{1,2})?$/;
			if(!r.test(probability)){
				$.toptip('请输入正确发生率,整数或小数', 'error');
				return false;
			}
			var seedfileid = $("#seedfileid").val();
			 
			var els =document.getElementsByName("imageid");
			
			var imageids = new Array(els.length);
			
			for (var i = 0,j= els.length;i<j;i++){
				imageids[i]=els[i].value;
			}
			
			$.showLoading();
			
			
			$.ajax({
				url : "${wechatPath}pest/save.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					happendate : happendate,
					pesttype : pesttype,
					placecode : placecode,
					probability : probability,
					principal : principal,
					seedfileid : seedfileid,
					imageids :JSON.stringify(imageids)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("新增成功", function() {
							window.location.href="${wechatPath}pest/list.action?seedfileid="+seedfileid;
					  }, function() {
						  
					  });
					}
				},
				error : function(data) {
					$.hideLoading();
					$.alert("失败");
				}
			});
		}) */
		
		
		
		var pb1 = $.photoBrowser({
	        items: [
				<c:forEach items="${imgList}" var="image">
					"${image.url}",
				</c:forEach>
	        ],

	        onSlideChange: function(index) {
	          console.log(this, index);
	        },

	        onOpen: function() {
	          console.log("onOpen", this);
	        },
	        onClose: function() {
	          console.log("onClose", this);
	        }
	      });
		
	      $("#pb1").click(function() {
	        pb1.open();
	      });
		
		
	</script>
</body>
</html>