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
		溯源档案修改
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}bottleseed/update.action" method="post" id="formid">
		<input type="hidden" name="guid" value="${seedfile.guid}"/>
		<input type="hidden" name="operate" value="${operate}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<c:if test="${!empty seedfile.variety}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">品种</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.varietys.belongsname}" data-values="${seedfile.variety}" name="varietyss" id="varietyss" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="remark" value="修改品种" />
						<input type="hidden" name="variety"  id="variety"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.seedid}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">核心苗档案</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.parentseed.batch}" data-values="${seedfile.seedid}" name="seedids"  id="seedids" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="remark" value="修改核心苗来源" />
						<input type="hidden" name="seedid"  id="seedid"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.years}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">年份</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.years}" data-values="${seedfile.years}" name="year"  id="year" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="remark" value="修改年份" />
						<input type="hidden" name="years"  id="years"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.level}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">扩繁级别</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.levels.belongsname}" data-values="${seedfile.level}" name="levelss"  id="levelss" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="remark" value="修改扩繁级别" />
						<input type="hidden" name="level"  id="level"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.startdate}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">扩繁时间段</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="<fmt:formatDate value="${seedfile.startdate}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${seedfile.startdate}" pattern="yyyy-MM-dd"/>"   name="startdate" id="startdate"style="margin-top: 5px;margin-bottom: 5px;width:110px;">
						<input class="weui-input" type="text"  placeholder="<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>" name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px;width:110px;"/>
						<input type="hidden" name="remark" value="修改扩繁时间" />
					</div>
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.placeid}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">所在苗床</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.seedbed.seedbedname}" data-values="${seedfile.placeid}" name="placeids"  id="placeids" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="remark" value="修改所在苗床" />
						<input type="hidden" name="placeid"  id="placeid"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.bottleamount}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">瓶苗个数</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="number"  placeholder="${seedfile.bottleamount}" name="bottleamount" id="bottleamount" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="remark" value="修改瓶苗个数" />
					</div>
				</div>
			</c:if>
		</div>
		
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
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
	
		function submitinfo(){
			<c:if test="${!empty seedfile.variety}">
				var varietys=$("#varietyss").data("values");
				$("#variety").val(varietys);
				if($("#variety").val()==""){
					$.toptip('请选择品种', 'error');
					return false;
				}
			</c:if>
			
			<c:if test="${!empty seedfile.seedid}">
				var seedids=$("#seedids").data("values");
				$("#seedid").val(seedids);
				if($("#seedid").val()==""){
					$.toptip('请选择核心苗档案', 'error');
					return false;
				}
				
			</c:if>
			
			<c:if test="${!empty seedfile.years}">
				var year=$("#year").data("values");
				$("#years").val(year);
				if($("#years").val()==""){
					$.toptip('请选择年份', 'error');
					return false;
				}
				
			</c:if>
			
			<c:if test="${!empty seedfile.level}">
				var levels=$("#levelss").data("values");
				$("#level").val(levels);
				if($("#level").val()==""){
					$.toptip('请选择扩繁级别', 'error');
					return false;
				}
				
			</c:if>
			
			<c:if test="${!empty seedfile.startdate}">
				var startdate=$("#startdate").val().replace(/^\s+|\s+$/g,"");
				if(startdate==null||startdate==""){
					$.toptip('请选择扩繁时间', 'error');
					return false;
				}
				
				var enddate=$("#enddate").val().replace(/^\s+|\s+$/g,"");
				if(enddate==null||enddate==""){
					$.toptip('请选择扩繁时间', 'error');
					return false;
				}
			</c:if>
			
			<c:if test="${!empty seedfile.placeid}">
				var placeids=$("#placeids").data("values");
				$("#placeid").val(placeids);
				if($("#placeid").val()==""){
					$.toptip('请选择扩所在苗床', 'error');
					return false;
				}
				
			</c:if>
			
			
			<c:if test="${!empty seedfile.bottleamount}">
				var bottleamount=$("#bottleamount").val().replace(/^\s+|\s+$/g,"");
				if(bottleamount!=null){
					var r = /^\+?[1-9][0-9]*$/;
					if(!r.test(bottleamount)){
						$.toptip('请输入正确数量,整数', 'error');
						return false;
					}
				}
			</c:if>
			
			$("#formid").submit();
		}
		
		<c:if test="${!empty getSysYear}">
		$("#year").select({
			  title: "选择年份",
			  items: [
			<c:forEach items="${getSysYear}" var="year">         
			    {
			      title: '${year}年',
			      value: ${year},
			    },
	    	</c:forEach>
			  ]
			});
		</c:if>
		
		<c:if test="${!empty variety}">
		$("#varietyss").select({
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
		</c:if>
		
		<c:if test="${!empty files}">
		$("#seedids").select({
			  title: "选择核心苗档案",
			  items: [
			<c:forEach items="${files}" var="file">       
			    {
			      title: '${file.batch}',
			      value: '${file.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		</c:if>
		
		<c:if test="${!empty level}">
		$("#levelss").select({
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
		</c:if>
		
		<c:if test="${!empty seedbeds}">
		$("#placeids").select({
			  title: "选择所在苗床",
			  items: [
			<c:forEach items="${seedbeds}" var="bed"> 
			    {
			      title: '${bed.seedbedname}',
			      value: '${bed.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		</c:if>
	</script>
</body>
</html>