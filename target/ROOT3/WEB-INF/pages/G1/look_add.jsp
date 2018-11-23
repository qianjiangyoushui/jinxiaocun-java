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
<title>附件上传</title>
<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="${wechatPath}css/demos.css">
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<style type="text/css">
.weui_cell {
  position: relative;
}
.weui_cell_select .weui_cell_bd:after {
  content: " ";
  display: inline-block;
  -webkit-transform: rotate(45deg);
          transform: rotate(45deg);
  height: 6px;
  width: 6px;
  border-width: 2px 2px 0 0;
  border-color: #C8C8CD;
  border-style: solid;
  position: relative;
  top: -2px;
  position: absolute;
  top: 50%;
  right: 15px;
  margin-top: -3px;
}

.weui_select_before .weui_cell_bd {
  padding-left: 15px;
}
.weui_select_before .weui_cell_bd:after {
  display: none;
}

.weui_cell_primary {
  -webkit-box-flex: 1;
  -webkit-flex: 1;
      -ms-flex: 1;
          flex: 1;
}

.weui_uploader_bd {
  margin-bottom: -4px;
  margin-right: -9px;
  overflow: hidden;
}

.weui_uploader_input_wrp {
  float: left;
  position: relative;
  margin-right: 9px;
  margin-bottom: 9px;
  width: 77px;
  height: 77px;
  border: 1px solid #D9D9D9;
}
.weui_uploader_input_wrp:before,
.weui_uploader_input_wrp:after {
  content: " ";
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
          transform: translate(-50%, -50%);
  background-color: #D9D9D9;
}
.weui_uploader_input_wrp:before {
  width: 2px;
  height: 39.5px;
}
.weui_uploader_input_wrp:after {
  width: 39.5px;
  height: 2px;
}
.weui_uploader_input_wrp:active {
  border-color: #999999;
}
.weui_uploader_input_wrp:active:before,
.weui_uploader_input_wrp:active:after {
  background-color: #999999;
}

.weui_uploader_input {
  position: absolute;
  z-index: 1;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
}

.weui_btn_primary {
  background-color: #04BE02;
}
.weui_btn_primary:not(.weui_btn_disabled):visited {
  color: #FFFFFF;
}
.weui_btn_primary:not(.weui_btn_disabled):active {
  color: rgba(255, 255, 255, 0.4);
  background-color: #039702;
}

.weui_btn {
  position: relative;
  display: block;
  margin-left: auto;
  margin-right: auto;
  padding-left: 14px;
  padding-right: 14px;
  box-sizing: border-box;
  font-size: 18px;
  text-align: center;
  text-decoration: none;
  color: #FFFFFF;
  line-height: 2.33333333;
  border-radius: 5px;
  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  overflow: hidden;
}

.weui_btn + .weui_btn {
  margin-top: 15px;
}
.weui_btn.weui_btn_inline + .weui_btn.weui_btn_inline {
  margin-top: auto;
  margin-left: 15px;
}

.weui_btn_area.weui_btn_area_inline .weui_btn {
  margin-top: auto;
  margin-right: 15px;
  width: 100%;
  -webkit-box-flex: 1;
  -webkit-flex: 1;
      -ms-flex: 1;
          flex: 1;
}
.weui_btn_area.weui_btn_area_inline .weui_btn:last-child {
  margin-right: 0;
}
.weui_btn:after {
  content: " ";
  width: 200%;
  height: 200%;
  position: absolute;
  top: 0;
  left: 0;
  border: 1px solid rgba(0, 0, 0, 0.2);
  -webkit-transform: scale(0.5);
          transform: scale(0.5);
  -webkit-transform-origin: 0 0;
          transform-origin: 0 0;
  box-sizing: border-box;
  border-radius: 10px;
}
.weui_btn.weui_btn_inline {
  display: inline-block;
}
</style>
<body style="background-color: #FFFFFF">
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		新增长势图片
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<c:if test="${sessionScope.user.depart.departid eq '3'}">
			<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/save.png" style="width: 20px" />
			</a>
			<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
		</c:if>
	</div>
	
	<form action="${wechatPath}wintertest/save.action" method="post">
		<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">描述</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="描述" name="description" id="description" style="margin-top: 5px;margin-bottom: 5px;" >
				</div>							
			</div>
		<div class="weui-cells weui-cells_form">
			<input type="hidden" name="serverIds" id="serverIds">
			<div class="weui_cell">
				<div class="weui_cell_bd weui_cell_primary">
					<div class="weui_uploader">
						<div class="weui_uploader_bd">
							<div class="weui_uploader_input_wrp" style="margin-top: 5px;margin-left:10px;">
								<input type="file" accept="image/*" class="weui_uploader_input">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div style="margin-top: 13px;">
			<input class="weui_btn weui_btn_primary" style="width: 100%;" type="button" onclick="submitinfo();" value="提交" />
		</div>
		<c:if test="${!empty images}">
			<a href="javascript:;" class="weui-btn weui-btn_primary" id="pb1">浏览附件</a>
		</c:if>
		
		<div class="mui-content" style="background-color:#fff">
		    <ul class="mui-table-view mui-grid-view" id="images">
		    	
			</ul>
		</div>
	</form>
	
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/swiper.js"></script>
	<!--图片上传 -->
	<script src="${wechatPath}js/upimagejs/mobileFix.mini.js"></script>
	<script src="${wechatPath}js/upimagejs/exif.js"></script>
	<script src="${wechatPath}js/upimagejs/lrz.js"></script>
	<script src="${wechatPath}js/upimagejs/upimage.js"></script>
	
	
	<script type="text/javascript">
	var pb1 = $.photoBrowser({
        items: [
			<c:forEach items="${images}" var="image">
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
      
      
      var desc='${description}';
      var type='${type}';
      var relatedid='${relatedid}';
      
      
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
			var els =document.getElementsByName("imageid");
			
			
			var description=$("#description").val().replace(/^\s+|\s+$/g,"");
			if(description==null||description==""){
				$.toptip('请输入描述', 'error');
				return false;
			}
			
			if(els.length<1){
				$.toptip('请上传图片！', 'error');
				return false;
			}
			var imageids = new Array(els.length);
			for (var i = 0,j= els.length;i<j;i++){
				imageids[i]=els[i].value;
			}
			var seedfileid = '${seedfileid}';
			
			
			$.ajax({
				url : "${wechatPath}wintertest/save.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					desc:desc,
					seedfileid : seedfileid,
					description : description,
					imageids:JSON.stringify(imageids)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("上传成功", function() {
							window.location.href="${wechatPath}${url}";
					  }, function() {
						  
					  });
					}
				},
				error : function(data) {
					$.alert("失败");
				}
			});
	  }
	
	</script>	
</body>
</html>
	