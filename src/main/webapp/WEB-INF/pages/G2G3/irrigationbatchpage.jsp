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
		新增灌溉记录情况记录
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
			<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/save.png" style="width: 20px" />
			</a>
			<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}irrigation/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${irrigation.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="irrigatedate" id="irrigatedate" style="margin-top: 5px;margin-bottom: 5px">
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">灌溉方式</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="(滴灌/喷灌)" name="irrigatestyle" id="irrigatestyle" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">灌溉水量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入灌溉水量" name="irrigatewater" id="irrigatewater" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">喷灌行走速度</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入5米/秒或5米/分钟等" name="speed" id="speed" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">当日降雨量(ML)</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入当日降雨量" name="rainfall" id="rainfall" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">灌溉前湿度</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入灌溉前湿度" name="humidity" id="humidity" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">备注</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入备注" name="remark" id="remark" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">记录人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入记录人" name="recordperson" id="recordperson" readonly="readonly" value="${users.username}" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">负责人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入负责人" name="principal" id="principal" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
                <div class="weui-cell__hd">
                    <label class="weui-label">选择种子批次</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text" placeholder="选择种子批次" name="batch" id="batch" style="margin-top: 5px;margin-bottom: 5px;">
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
	$("#irrigatedate").calendar();
	
	
	var type="115";
	var desc="灌溉情况图";
	var batchArray=[];
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
			var irrigatedate=$("#irrigatedate").val().replace(/^\s+|\s+$/g,"");
			if(irrigatedate==null||irrigatedate==""){
				$.toptip('请输入日期', 'error');
				return false;
			}
			
			var irrigatestyle=$("#irrigatestyle").val().replace(/^\s+|\s+$/g,"");
			if(irrigatestyle==null||irrigatestyle==""){
				$.toptip('请输入灌溉方式', 'error');
				return false;
			}
			
			var irrigatewater=$("#irrigatewater").val().replace(/^\s+|\s+$/g,"");
			if(irrigatewater==null||irrigatewater==""){
				$.toptip('请输入灌溉水量', 'error');
				return false;
			}
			
			var speed=$("#speed").val().replace(/^\s+|\s+$/g,"");
			if(speed==null||speed==""){
				$.toptip('请输入喷灌行走速度', 'error');
				return false;
			}
			
			var rainfall=$("#rainfall").val().replace(/^\s+|\s+$/g,"");
			if(rainfall==null||rainfall==""){
				$.toptip('请输入当日降雨量', 'error');
				return false;
			}
			
			var humidity=$("#humidity").val().replace(/^\s+|\s+$/g,"");
			if(humidity==null||humidity==""){
				$.toptip('请输入灌溉前湿度', 'error');
				return false;
			}
			
			var recordperson=$("#recordperson").val().replace(/^\s+|\s+$/g,"");
			if(recordperson==null||recordperson==""){
				$.toptip('请输入记录人', 'error');
				return false;
			}
			
			var principal=$("#principal").val().replace(/^\s+|\s+$/g,"");
			if(principal==null||principal==""){
				$.toptip('请输入负责人', 'error');
				return false;
			}
			
			
			var r = /^(0|[1-9]\d{0,2})(\.\d{1,3})?$/;
			if(!r.test(rainfall)){
				$.toptip('当日降雨量请输入正确数量', 'error');
				return false;
			}
            var batch=$("#batch").val().replace(/^\s+|\s+$/g,"");
			if(batch==null||batch==""){
				$.toptip('请选择批次', 'error');
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
				url : "${wechatPath}irrigation/batchsave.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					irrigatedate : irrigatedate,
					irrigatestyle : irrigatestyle,
					irrigatewater : irrigatewater,
					speed : speed,
					rainfall : rainfall,
					humidity : humidity,
					recordperson : recordperson,
					principal : principal,
					seedfileid : seedfileid,
					imageids :JSON.stringify(imageids),
					batch :JSON.stringify(batchArray)
				},
				success : function(data) {
					$.hideLoading();
					if(data.msg=='ok'){
						$.confirm("新增成功", function() {
							window.location.href="${wechatPath}g2g3/index.action?type=${type}";
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
		
		$("#irrigatestyle").select({
			  title: "选择",
			  items: [
			    {
			      title: '喷灌',
			      value: '喷灌',
			    },
			    {
			      title: '滴灌',
			      value: '滴灌',
			    }
			  ]
			});
		$("#batch").select({
            title: "选择施肥种子批次",
            multi: true,
            items: [],
            onClose:function(e){
            batchArray=[];
            var data = e.data.valuesArray;
            for(let i=0;i<data.length;i++){
                batchArray.push(data[i]);
            }
            }
         });
        $("#irrigatedate").on("change",function(v){
            var that = this;
            let manuredate = that.value;
            let year = manuredate.split("-")[0];
            $.ajax({
                url : "${wechatPath}seedfile/batchlist.action",
                type : "GET",
                async : false,
                dataType : 'json',
                data : {
                    years : year,
                    level:'${type}',
                    type:'${type}',
                    visible:1
                },
                success : function(data) {
                    let items = data;
                    let persons = [];
                    batchItem = "[";
                    for(let i=0;i<items.length;i++){
                        let item = items[i];
                        persons.push({title: item.title, value: item.value});
                     }
                    $("#batch").select("update", { items: persons })
                },
                error : function(data) {
                    $.hideLoading();
                    $.alert("失败"+data);
                }
            });
        });
	</script>
</body>
</html>