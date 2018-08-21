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
		溯源档案号录入
		<a href="${wechatPath}greenhouses/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}greenhouses/saveSeedfile.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">

			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">品种</label>
				</div>
				<div class="weui-cell__bd">
					<%-- <select  name="variety" id="variety" style="margin-top: 5px;margin-bottom: 5px;">
						<option value="0">选择品种</option>
						<c:forEach items="${variety}" var="vt">
							<option value="${vt.keyvalue}">${vt.belongsname}</option>
						</c:forEach>
					</select> --%>
					<input class="weui-input" type="text" placeholder="请选择品种" name="varietyss" id="varietyss" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="variety" id="variety">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">级别</label>
				</div>
				<div class="weui-cell__bd">
					<%-- <select  name="level"  id="level" style="margin-top: 5px;margin-bottom: 5px;">
						<option value="0">选择级别</option>
						<c:forEach items="${level}" var="lv">
							<option value="${lv.keyvalue}">${lv.belongsname}</option>
						</c:forEach>
					</select> --%>
					<input class="weui-input" type="text" placeholder="请选择级别" name="levelss" id="levelss" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="level" id="level">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">使用瓶苗档案</label>
				</div>
				<div class="weui-cell__bd">
					<%-- <select  name="seedid"  id="seedid" style="margin-top: 5px;margin-bottom: 5px;">
						<option value="0">选择使用瓶苗档案</option>
						<c:forEach items="${seedfileList}" var="file">
							<option value="${file.guid}">${file.batch}</option>
						</c:forEach>
					</select> --%>
					<input class="weui-input" type="text" placeholder="请选择使用瓶苗档案" name="seedids" id="seedids" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="seedid" id="seedid">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在网棚</label>
				</div>
				<div class="weui-cell__bd">
					<%-- <select  name="placeid"  id="placeid" style="margin-top: 5px;margin-bottom: 5px;">
						<option value="0">选择网棚</option>
						<c:forEach items="${greenhousesList}" var="gh">
							<option value="${gh.guid}">${gh.housename}</option>
						</c:forEach>
					</select> --%>
					<input class="weui-input" type="text" placeholder="请选择网棚" name="placeids" id="placeids" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="placeid" id="placeid">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">定植株数</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="请输入定植株数" name="strainamount" id="strainamount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">年份</label>
				</div>
				<div class="weui-cell__bd">
					<%-- <select  name="years"  id="years" style="margin-top: 5px;margin-bottom: 5px;">
						<option value="0">选择年份</option>
						<c:forEach items="${getSysYear}" var="year">
							<option value="${year}">${year}年</option>
						</c:forEach>
					</select> --%>
					<input class="weui-input" type="text" placeholder="请选择年份" name="yearss" id="yearss" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="years" id="years">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">当年第几茬</label>
				</div>
				<div class="weui-cell__bd">
					<!-- <select  name="stubble"  id="stubble" style="margin-top: 5px;margin-bottom: 5px;">
						<option value="0">选择</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
					</select> -->
					<input class="weui-input" type="text" name="stubbless" id="stubbless" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="stubble" id="stubble">
				</div>							
			</div>
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	
		$("#submitid").on("click",function(){


			
			var varietys = $("#varietyss").val();
			if(varietys ==null || varietys == ""){
				$.toptip('请选择品种', 'error');
				return false;
			}else{
				var variety=$("#varietyss").data("values");
				$("#variety").val(variety);
			}
			
			var levels = $("#levelss").val();
			if(levels ==null || levels == ""){
				$.toptip('请选择级别', 'error');
				return false;
			}else{
				var level=$("#levelss").data("values");
				$("#level").val(level);
			}
			
			var seedids = $("#seedids").val();
			if(seedids ==null || seedids == ""){
				$.toptip('请选择使用瓶苗档案', 'error');
				return false;
			}else{
				var seedid=$("#seedids").data("values");
				$("#seedid").val(seedid);
			}
			
			var placeids = $("#placeids").val();
			if(placeids ==null || placeids == ""){
				$.toptip('请选择网棚', 'error');
				return false;
			}else{
				var placeid=$("#placeids").data("values");
				$("#placeid").val(placeid);
			}
			
			var strainamount=$("#strainamount").val().replace(/^\s+|\s+$/g,"");
			if(strainamount==null||strainamount==""){
				$.toptip('请输入定植株数', 'error');
				return false;
			}
			
			var yearss = $("#yearss").val();
			if(yearss ==null || yearss == ""){
				$.toptip('请选择年份', 'error');
				return false;
			}else{
				var years=$("#yearss").data("values");
				$("#years").val(years);
			}
			
			var stubbles = $("#stubbless").val();
			if(stubbles ==null || stubbles == ""){
				$.toptip('请选择当年第几茬', 'error');
				return false;
			}else{
				var stubble=$("#stubbless").data("values");
				$("#stubble").val(stubble);
			}
			
			
			/* var level=$("#levelss").data("values");
			if(level==""){
				$.toptip('请选择级别', 'error');
				return false;
			}
			$("#level").val(level); */
			
			/* var seedid=$("#seedids").data("values");
			if(seedid==""){
				$.toptip('请选择使用瓶苗档案', 'error');
				return false;
			}
			$("#seedid").val(seedid); */
			
			/* var placeid=$("#placeids").data("values");
			if(placeid==""){
				$.toptip('请选择网棚', 'error');
				return false;
			}
			$("#placeid").val(placeid); */
			
			
			
			/* var years=$("#years").data("values");
			if(years==""){
				$.toptip('请选择年份', 'error');
				return false;
			}
			$("#years").val(years); */
			
			
			
			/* var stubble=$("#stubble").data("values");
			if(stubble==""){
				$.toptip('请选择当年第几茬', 'error');
				return false;
			}
			$("#stubble").val(stubble); */
			
			
           verity(years,$("#placeid").val(),$("#variety").val(),$("#level").val()).then(function(data){
                if(data.msg=="ok"){
                    $("#formid").submit();
                }else{
                    $.toptip('请重新填写，当前批次已经存在', 'error');
                }
            },function(erro){
                    $.toptip('出错了，联系管理员解决', 'error');
           });
		})
		
		
		
		$("#yearss").select({
			  title: "选择年份",
			  items: [
			<c:forEach items="${getSysYear}" var="year">         
			    {
			      title: '${year}年',
			      value: ${year}
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#varietyss").select({
			  title: "选择品种",
			  items: [
			<c:forEach items="${variety}" var="vt">       
			    {
			      title: '${vt.belongsname}',
			      value: '${vt.keyvalue}'
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#levelss").select({
			  title: "选择级别",
			  items: [
			<c:forEach items="${level}" var="lv">  
			    {
			      title: '${lv.belongsname}',
			      value: '${lv.keyvalue}',
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#seedids").select({
			  title: "选择瓶苗档案",
			  items: [
			<c:forEach items="${seedfileList}" var="file">       
			    {
			      title: '${file.batch}',
			      value: '${file.guid}'
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#placeids").select({
			  title: "选择网棚",
			  items: [
			<c:forEach items="${greenhousesList}" var="file">       
			    {
			      title: '${file.housename}',
			      value: '${file.guid}'
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#stubbless").select({
			  title: "选择",
			  items: [
			    {
			      title: '第一茬',
			      value: '1',
			    },
			    {
			      title: '第二茬',
			      value: '2',
			    },
			    {
			      title: '第三茬',
			      value: '3',
			    }
			  ]
			});


		function checkbatch(){
			var batch=$("#batch").val().replace(/^\s+|\s+$/g,"");
			var flag=true;
			if(batch!=null && batch!=""){
				$.ajax({
					url : "${path}bottleseed/check.action",
					type : "POST",
					async : false,
					dataType : 'json',
					data : {
						batch:batch
					},
					success : function(data) {
						if(data.msg=='fail'){
							$.toptip('批次编号已存在!', 'error');
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
		function verity(years,placeid,variety,level){
		    var form = new FormData();
		    form.append("year", years);
		    form.append("palceid", placeid);
		    form.append("variety", variety);
		    form.append("level", level);
		    var promise =  new Promise(function(resolve,reject){
		        var client = new XMLHttpRequest();
		        client.open("post","${path}g2g3/check.action");
		        client.onreadystatechange=handler;
		        client.responseType="json";
		        client.setRequestHeader("Accept","application/json");
		        client.send(form);
		        function handler(){
		            if(this.readyState!=4){
		                return;
		            }
		            if(this.status==200){
		                resolve(this.response);
		            }else{
		                reject(new Error(this.statusText));
		            }
		        }
		    });
		    return promise;
		}
	</script>
</body>
</html>