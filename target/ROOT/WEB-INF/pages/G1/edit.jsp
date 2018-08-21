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
		<a href="javascript:save();" id="submitid" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}greenhouses/updateSeedfile.action" method="post" id="formid">
	<input type="hidden" name="guid" value="${seedfile.guid}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">

			<c:if test="${!empty seedfile.variety}">			
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">品种</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.varietys.belongsname}" data-values="${seedfile.variety}" name="varietyss" id="varietyss" style="margin-top: 5px;margin-bottom: 5px;" value="${seedfile.varietys.belongsname}">
						<input type="hidden" name="remark" value="修改品种" />
						<input type="hidden" name="variety" id="variety"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.level}">	
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">级别</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.levels.belongsname}" name="levelss" id="levelss" style="margin-top: 5px;margin-bottom: 5px;" value="${seedfile.levels.belongsname}">
						<input type="hidden" name="remark" value="修改级别" />
						<input type="hidden" name="level" id="level"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.greenhouses}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">所在网棚</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.greenhouses.housename}" name="placeidss" id="placeidss" style="margin-top: 5px;margin-bottom: 5px;" value="${seedfile.greenhouses.housename}">
						<input type="hidden" name="remark" value="所在网棚" />
						<input type="hidden" name="placeid" id="placeid"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.strainamount}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">定植株数</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入定植株数" name="strainamount" id="strainamount" style="margin-top: 5px;margin-bottom: 5px;" value="${seedfile.strainamount}">
						<input type="hidden" name="remark" value="定植株数" />
					</div>
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.years}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">年份</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请选择年份" name="yearss" id="yearss" style="margin-top: 5px;margin-bottom: 5px;" value="${seedfile.years}">
						<input type="hidden" name="remark" value="年份" />
						<input type="hidden" name="years" id="years"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.stubble}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">当年第几茬</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" name="stubbless" id="stubbless" style="margin-top: 5px;margin-bottom: 5px;" value="${seedfile.stubble}">
						<input type="hidden" name="remark" value="当年第几茬" />
						<input type="hidden" name="stubble" id="stubble"/>
					</div>							
				</div>
			</c:if>
			
			<c:if test="${type == '21'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">收获日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="收获日期" name="rewarddate" id="rewarddate" style="margin-top: 5px;margin-bottom: 5px;" <c:if test="${!empty seedfile.rewarddate}">value="<fmt:formatDate value="${seedfile.rewarddate}" pattern="yyyy-MM-dd"/>"</c:if>>
						<input type="hidden" name="remark" value="收获日期" />
					</div>							
				</div>
			</c:if>
			
			<c:if test="${type == '22'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">停水日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="停水日期" name="stopwaterdate" id="stopwaterdate" style="margin-top: 5px;margin-bottom: 5px;" <c:if test="${!empty seedfile.stopwaterdate}">value="<fmt:formatDate value="${seedfile.stopwaterdate}" pattern="yyyy-MM-dd"/>"</c:if>>
						<input type="hidden" name="remark" value="停水日期" />
					</div>							
				</div>
			</c:if>
			
			<c:if test="${type == '23'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">杀秧日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="杀秧日期" name="killdate" id="killdate" style="margin-top: 5px;margin-bottom: 5px;" <c:if test="${!empty seedfile.killdate}">value="<fmt:formatDate value="${seedfile.killdate}" pattern="yyyy-MM-dd"/>"</c:if>>
						<input type="hidden" name="remark" value="杀秧日期" />
					</div>
				</div>
			</c:if>
			
			<c:if test="${type == '24'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">定植日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="开始日期" value="<fmt:formatDate value="${seedfile.startdate}" pattern="yyyy-MM-dd"/>"   name="startdate" id="startdate"style="margin-top: 5px;margin-bottom: 5px;width:110px;">
						<input class="weui-input" type="text"  placeholder="结束日期" value="<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>" name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px;width:110px;"/>
						<input type="hidden" name="remark" value="定植日期" />
					</div>
				</div>
			</c:if>
			
		</div>
	</form>

	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	$("#rewarddate").calendar();
	$("#stopwaterdate").calendar();
	$("#killdate").calendar();
	
		function save(){
			<c:if test="${!empty seedfile.variety}">	
				var variety=$("#varietyss").data("values");
				if(variety==""){
					$.toptip('请选择品种', 'error');
					return false;
				}
				$("#variety").val(variety);
			</c:if>
			
			<c:if test="${!empty seedfile.level}">	
				var level=$("#levelss").data("values");
				if(level==""){
					$.toptip('请选择级别', 'error');
					return false;
				}
				$("#level").val(level);
			</c:if>
			
			
			<c:if test="${!empty seedfile.greenhouses}">
				var placeid=$("#placeidss").data("values");
				if(placeid==""){
					$.toptip('请选择网棚', 'error');
					return false;
				}
				$("#placeid").val(placeid);
			</c:if>
			
			<c:if test="${!empty seedfile.strainamount}">
				var strainamount=$("#strainamount").val().replace(/^\s+|\s+$/g,"");
				if(strainamount==null||strainamount==""){
					$.toptip('请输入定植株数', 'error');
					return false;
				}
			</c:if>
			
			<c:if test="${!empty seedfile.years}">
				var years=$("#yearss").data("values");
				if(years==""){
					$.toptip('请选择年份', 'error');
					return false;
				}
				$("#years").val(years);
			</c:if>
			
			<c:if test="${!empty seedfile.stubble}">
				var stubble=$("#stubbless").data("values");
				if(stubble==""){
					$.toptip('请选择当年第几茬', 'error');
					return false;
				}
				$("#stubble").val(stubble);
			</c:if>
			
			<c:if test="${type == '21'}">
				var rewarddate=$("#rewarddate").val();
				if(rewarddate==""){
					$.toptip('请选择收获日期', 'error');
					return false;
				}
				$("#rewarddate").val(rewarddate);
			</c:if>
			
			<c:if test="${type == '22'}">
				var stopwaterdate=$("#stopwaterdate").val();
				if(stopwaterdate==""){
					$.toptip('请选择停水日期', 'error');
					return false;
				}
				$("#stopwaterdate").val(stopwaterdate);
			</c:if>
			
			<c:if test="${type == '23'}">
				var killdate=$("#killdate").val();
				if(killdate==""){
					$.toptip('请选择杀秧日期', 'error');
					return false;
				}
				$("#killdate").val(killdate);
			</c:if>
			
			<c:if test="${type == '24'}">
			var startdate=$("#startdate").val();
			var enddate=$("#enddate").val();
			if(startdate==""){
				$.toptip('请选择开始日期', 'error');
				return false;
			}
			if(enddate==""){
				$.toptip('请选择结束日期', 'error');
				return false;
			}
			$("#enddate").val(enddate);
			$("#enddate").val(enddate);
		</c:if>
			
			
			$("#formid").submit();
		}
		
		
		
		
		<c:if test="${!empty seedfile.variety}">
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
		</c:if>
		
		<c:if test="${!empty seedfile.level}">
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
		</c:if>

		<c:if test="${!empty seedfile.greenhouses}">
			$("#placeidss").select({
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
		</c:if>
		
		<c:if test="${!empty seedfile.years}">
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
		</c:if>
		
		<c:if test="${!empty seedfile.stubble}">
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
		</c:if>
	</script>
</body>
</html>