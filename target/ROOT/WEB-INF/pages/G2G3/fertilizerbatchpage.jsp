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
		新增施肥记录
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
			<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
				<img src="${wechatPath}icon/save.png" style="width: 20px" />
			</a>
			<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>

	<form action="${wechatPath}manure/save.action" method="post" id="formid">
	<input class="weui-input" type="hidden" name="seedfileid" id="seedfileid" style="margin-top: 5px;margin-bottom: 5px;width:110px;" value="${manure.seedfileid}" readonly="readonly">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">施用日期</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" name="manuredate" id="manuredate" style="margin-top: 5px;margin-bottom: 5px">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">肥料名称</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入肥料名称" name="muck" id="muck" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">肥料厂家</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入肥料厂家" name="muckfactory" id="muckfactory" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">有效成分含量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入有效成分含量" name="content" id="content" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">亩施用量</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="亩施用量" name="dosage" id="dosage" style="margin-top: 5px;margin-bottom: 5px;width:110px">
					<input class="weui-input" type="text" placeholder="单位" name="dosageunit" id="dosageunit" style="margin-top: 5px;margin-bottom: 5px;width:110px">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">施用方法</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入施用方法" name="usetype" id="usetype" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">施用人</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请输入施用人" name="byperson" id="byperson" style="margin-top: 5px;margin-bottom: 5px;">
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
	$("#manuredate").calendar();
	var type="113";
	var desc="施肥情况图";
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
			var manuredate=$("#manuredate").val().replace(/^\s+|\s+$/g,"");
			if(manuredate==null||manuredate==""){
				$.toptip('请输入施用日期', 'error');
				return false;
			}

			var muck=$("#muck").val().replace(/^\s+|\s+$/g,"");
			if(muck==null||muck==""){
				$.toptip('请输入肥料名称', 'error');
				return false;
			}

			var muckfactory=$("#muckfactory").val().replace(/^\s+|\s+$/g,"");
			if(muckfactory==null||muckfactory==""){
				$.toptip('请输入肥料厂家', 'error');
				return false;
			}

			var content=$("#content").val().replace(/^\s+|\s+$/g,"");
			if(content==null||content==""){
				$.toptip('请输入有效成分含量', 'error');
				return false;
			}

			var dosage=$("#dosage").val().replace(/^\s+|\s+$/g,"");
			if(dosage==null||dosage==""){
				$.toptip('请输入亩施用量', 'error');
				return false;
			}

			var dosageunit=$("#dosageunit").data("values");
			if(dosageunit==null||dosageunit==""){
				$.toptip('请选择施用量单位', 'error');
				return false;
			}

			var usetype=$("#usetype").val().replace(/^\s+|\s+$/g,"");
			if(usetype==null||usetype==""){
				$.toptip('请输入施用方法', 'error');
				return false;
			}

			var byperson=$("#byperson").val().replace(/^\s+|\s+$/g,"");
			if(byperson==null||byperson==""){
				$.toptip('请输入施用人', 'error');
				return false;
			}

			var principal=$("#principal").val().replace(/^\s+|\s+$/g,"");
			if(principal==null||principal==""){
				$.toptip('请输入负责人', 'error');
				return false;
			}


			var r = /^(0|[1-9]\d{0,3})(\.\d{1,2})?$/;
			if(!r.test(content)){
				$.toptip('有效成分含量请输入正确数量', 'error');
				return false;
			}
			if(!r.test(dosage)){
				$.toptip('亩施用量请输入正确数量', 'error');
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
				url : "${wechatPath}manure/batchsave.action",
				type : "POST",
				async : false,
				dataType : 'json',
				data : {
					manuredate : manuredate,
					muck : muck,
					muckfactory : muckfactory,
					content : content,
					dosage : dosage,
					dosageunit : dosageunit,
					usetype : usetype,
					byperson : byperson,
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


		$("#dosageunit").select({
			  title: "选择单位",
			  items: [
			    {
				      title: 'KG',
				      value: 'KG'
				},
			    {
				      title: 'ML',
				      value: 'ML'
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
         $("#manuredate").on("change",function(v){
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