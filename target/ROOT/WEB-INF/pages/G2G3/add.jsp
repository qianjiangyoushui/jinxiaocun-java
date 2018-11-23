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
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}g2g3/save.action" method="post" id="formid">
	<input type="hidden" name="type" value="${type}">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">品种</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="请选择品种" name="_varietys" id="_varietys" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="variety" id="variety">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">扩繁级别</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="请选择扩繁级别" name="_levels"  id="_levels" style="margin-top: 5px;margin-bottom: 5px;">
				<input type="hidden" name="level" id="level">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">种植亩数</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number"  placeholder="种植亩数" name="muamount"  id="muamount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在基地</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="请选择基地" name="_placeids"  id="_placeids" style="margin-top: 5px;margin-bottom: 5px;">
				<input type="hidden" name="placeid" id="placeid">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd" >
					<label class="weui-label">是否自产：</label>
				</div>
				<div class="weui-cell__bd" style="float: right;margin-left: 50%">
					<input class="weui_switch"  type="checkbox"  name="isproduction"  id="isproduction" style="margin-top: 8px;margin-bottom: 5px;" value="0" onclick="check();">
				</div>							
			</div>
			
			<div class="weui-cell" id="laiyuan" style="display:none">
				<div class="weui-cell__hd">
					<label class="weui-label">来源</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="来源" name="source"  id="source" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			
			
			<div class="weui-cell" id="yuanzhong">
				<div class="weui-cell__hd">
					<label class="weui-label">
					<c:if test="${type eq '4' }">
					原原种(G1)
					</c:if>
					<c:if test="${type eq '5' }">
					原种(G2)
					</c:if>
					<c:if test="${type eq '6' }">
                    	所用种薯批次
                    </c:if>
					</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="请选择所用种子批次" name="_seedids"  id="_seedids" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="seedid" id="seedid">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">年份</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number" placeholder="请选择年份" name="years"  id="years" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">第几茬</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"  placeholder="请选第几茬" name="stubble"  id="stubble" style="margin-top: 5px;margin-bottom: 5px;">
				</div>							
			</div>
		</div>
		
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="重置">
			<input type="button" onclick="submitinfo();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
	</form>
	<div style="height: 10px;width: 100%"></div>

	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	
	
	function check(){
		 if($('#isproduction').is(':checked')) {//是,选中的状态
		 	$("#yuanzhong").hide();
		 	$("#laiyuan").show();
		 }else{
			 $("#laiyuan").hide();
			 $("#yuanzhong").show();
		 }
	}
	
	
		function submitinfo(){
			var flag=true;
			/* $(".weui-input").each(function (i){
				if($(this).attr("value")==null||$(this).attr("value")==""){
					flag=false;
				}
			}) */

			
			var varietys=$("#_varietys").data("values");
			$("#variety").val(varietys);
			if($("#variety").val()==""){
				$.toptip('请选择品种', 'error');
				return false;
			}
			
			var levels=$("#_levels").data("values");
			$("#level").val(levels);
			if($("#level").val()==""){
				$.toptip('请选择扩繁级别', 'error');
				return false;
			}
			
			var muamount=$("#muamount").val().replace(/^\s+|\s+$/g,"");
			if(muamount==null||muamount==""){
				$.toptip('请输入种植亩数', 'error');
				return false;
			}
			
			var placeids=$("#_placeids").data("values");
			$("#placeid").val(placeids);
			if($("#placeid").val()==""){
				$.toptip('请选择种植基地', 'error');
				return false;
			}
			
			if($('#isproduction').is(':checked')) {//是,选中的状态
				var source=$("#source").val().replace(/^\s+|\s+$/g,"");
				if(source==null||source==""){
					$.toptip('请输入批次来源', 'error');
					return false;
				}
			}else{
				var seedid=$("#_seedids").data("values");
				$("#seedid").val(seedid);
				if($("#seedid").val()==""){
					$.toptip('请选择核心苗档案', 'error');
					return false;
				}
			}
			
			var years=$("#years").val().replace(/^\s+|\s+$/g,"");
			if(years==null||years==""){
				$.toptip('请选择年份', 'error');
				return false;
			}
			
			var stubble=$("#stubble").val().replace(/^\s+|\s+$/g,"");
			if(stubble==null||stubble==""){
				$.toptip('请选择第几茬', 'error');
				return false;
			}
            $("#variety").attr("value",$("#_varietys").attr("data-values"));
            $("#level").attr("value",$("#_levels").attr("data-values"));
            $("#placeid").attr("value",$("#_placeids").attr("data-values"));
            $("#seedid").attr("value",$("#_seedids").attr("data-values"));
            verity(years,$("#placeid").val(),$("#variety").val(),$("#level").val()).then(function(data){
                if(data.msg=="ok"){
                    $("#formid").submit();
                }else{
                    $.toptip('请重新填写，当前批次已经存在', 'error');
                }
            },function(erro){
                    $.toptip('出错了，联系管理员解决', 'error');
            });
		}
		
		
		$("#years").select({
			  title: "选择年份",
			  items: [
			<c:forEach items="${getSysYear}" var="year">         
			    {
			      title: '${year}',
			      value: '${year}',
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#_varietys").select({
			  title: "选择品种",
			  items: [
			<c:forEach items="${variety}" var="vt">       
			    {
			      title: '${vt.belongsname}',
			      value: '${vt.keyvalue}',
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#_seedids").select({
			  title: "所用种子档案",
			  items: [
			<c:forEach items="${files}" var="file">       
			    {
			      title: '${file.batch}',
			      value: '${file.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#_levels").select({
			  title: "选择扩繁级别",
			  items: [
			<c:forEach items="${level}" var="lv">  
			    {
			      title: '${lv.belongsname}',
			      value: '${lv.keyvalue}',
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#_placeids").select({
			  title: "选择所在基地",
			  items: [
			<c:forEach items="${plots}" var="plot"> 
			    {
			      title: '${plot.plotname}',
			      value: '${plot.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		$("#stubble").select({
			  title: "选择第几茬",
			  items: [{
			
			      title: '1',
			      value: '1',
			    },{
				
			      title: '2',
			      value: '2',
			    },{
				
			      title: '3',
			      value: '3',
			    },
			  ]
			});
		


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