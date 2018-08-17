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
	
	<form action="${wechatPath}g2g3/update.action" method="post" id="formid">
		<input type="hidden" name="guid" value="${seedfile.guid}"/>
		<input type="hidden" name="type" value="${type}"/>
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">
			<c:if test="${!empty seedfile.variety}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">品种</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.varietys.belongsname}" data-values="${seedfile.variety}" name="variety_" id="variety_" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="variety" id="variety"  />
						<input type="hidden" name="remark" value="修改品种" />
					</div>							
				</div>
			</c:if>
			
<%-- 			<c:if test="${!empty seedfile.seedid}"> --%>
<!-- 				<div class="weui-cell"> -->
<!-- 					<div class="weui-cell__hd"> -->
<!-- 						<label class="weui-label">核心苗档案</label> -->
<!-- 					</div> -->
<!-- 					<div class="weui-cell__bd"> -->
<%-- 						<input class="weui-input" type="text" placeholder="${seedfile.parentseed.batch}" data-values="${seedfile.seedid}" name="seedid"  id="seedid" style="margin-top: 5px;margin-bottom: 5px;"> --%>
<!-- 						<input type="hidden" name="remark" value="修改核心苗来源" /> -->
<!-- 					</div>							 -->
<!-- 				</div> -->
<%-- 			</c:if> --%>
			
<%-- 			<c:if test="${!empty seedfile.years}"> --%>
<!-- 				<div class="weui-cell"> -->
<!-- 					<div class="weui-cell__hd"> -->
<!-- 						<label class="weui-label">年份</label> -->
<!-- 					</div> -->
<!-- 					<div class="weui-cell__bd"> -->
<%-- 						<input class="weui-input" type="text" placeholder="${seedfile.years}" data-values="${seedfile.years}" name="years"  id="years" style="margin-top: 5px;margin-bottom: 5px;"> --%>
<!-- 						<input type="hidden" name="remark" value="修改年份" /> -->
<!-- 					</div>							 -->
<!-- 				</div> -->
<%-- 			</c:if> --%>
			
			<c:if test="${!empty seedfile.level}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">扩繁级别</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.levels.belongsname}" data-values="${seedfile.level}" name="level_"  id="level_" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="level" id="level"  />
						<input type="hidden" name="remark" value="修改扩繁级别" />
					</div>							
				</div>
			</c:if>
			<c:if test="${!empty seedfile.muamount}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">种植面积</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.muamount}" data-values="${seedfile.muamount}" name="muamount"  id="muamount" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="remark" value="种植面积" />
					</div>							
				</div>
			</c:if>
			
			<c:if test="${!empty seedfile.placeid}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">地块信息</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="${seedfile.plots.plotname}" data-values="${seedfile.placeid}" name="placeid_"  id="placeid_" style="margin-top: 5px;margin-bottom: 5px;">
						<input type="hidden" name="placeid" id="placeid"  />
						<input type="hidden" name="remark" value="地块信息" />
					</div>							
				</div>
			</c:if>
			
			<c:if test="${edittype=='6'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">播种日期</label>
					</div>
					<div class="weui-cell__bd">
						开始：<input class="weui-input" type="text" placeholder="<fmt:formatDate value="${seedfile.startdate}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${seedfile.startdate}" pattern="yyyy-MM-dd"/>"   name="startdate" id="startdate"style="margin-top: 5px;margin-bottom: 5px;width:120px;">
						结束：<input class="weui-input" type="text"  placeholder="<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>" name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px;width:120px;"/>
						<input type="hidden" name="remark" value="播种日期" />
					</div>
				</div>
			</c:if>
			<c:if test="${edittype=='7'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">出苗日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="<fmt:formatDate value="${seedfile.buddingdate}" pattern="yyyy-MM-dd"/>" value="<fmt:formatDate value="${seedfile.startdate}" pattern="yyyy-MM-dd"/>"   name="buddingdate" id="buddingdate"style="margin-top: 5px;margin-bottom: 5px;width:120px;">
						<input type="hidden" name="remark" value="出苗日期" />
					</div>
				</div>
			</c:if>
			<c:if test="${edittype=='8'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">停肥日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="<fmt:formatDate value="${seedfile.stopmuck}" pattern="yyyy-MM-dd"/>"  value="<fmt:formatDate value="${seedfile.stopmuck}" pattern="yyyy-MM-dd"/>"   name="stopmuck" id="stopmuck"style="margin-top: 5px;margin-bottom: 5px;width:120px;">
						<input type="hidden" name="remark" value="停肥日期" />
					</div>
				</div>
			</c:if>
			<c:if test="${edittype=='9'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">停水日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="<fmt:formatDate value="${seedfile.stopwaterdate}" pattern="yyyy-MM-dd"/>"  value="<fmt:formatDate value="${seedfile.stopwaterdate}" pattern="yyyy-MM-dd"/>"   name="stopwaterdate" id="stopwaterdate"style="margin-top: 5px;margin-bottom: 5px;width:120px;">
						<input type="hidden" name="remark" value="停水日期" />
					</div>
				</div>
			</c:if>
			<c:if test="${edittype=='10'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">杀秧日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="<fmt:formatDate value="${seedfile.killdate}" pattern="yyyy-MM-dd"/>"  value="<fmt:formatDate value="${seedfile.killdate}" pattern="yyyy-MM-dd"/>"   name="killdate" id="killdate"style="margin-top: 5px;margin-bottom: 5px;width:120px;">
						<input type="hidden" name="remark" value="杀秧日期" />
					</div>
				</div>
			</c:if>
			<c:if test="${edittype=='11'}">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">收获日期</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="<fmt:formatDate value="${seedfile.rewarddate}" pattern="yyyy-MM-dd"/>"  value="<fmt:formatDate value="${seedfile.rewarddate}" pattern="yyyy-MM-dd"/>"   name="rewarddate" id="rewarddate"style="margin-top: 5px;margin-bottom: 5px;width:120px;">
						<input type="hidden" name="remark" value="收获日期" />
					</div>
				</div>
			</c:if>
			
			
			
<%-- 			<c:if test="${!empty seedfile.bottleamount}"> --%>
<!-- 				<div class="weui-cell"> -->
<!-- 					<div class="weui-cell__hd"> -->
<!-- 						<label class="weui-label">瓶苗个数</label> -->
<!-- 					</div> -->
<!-- 					<div class="weui-cell__bd"> -->
<%-- 						<input class="weui-input" type="number"  placeholder="${seedfile.bottleamount}" name="bottleamount" id="bottleamount" style="margin-top: 5px;margin-bottom: 5px;"> --%>
<!-- 						<input type="hidden" name="remark" value="修改瓶苗个数" /> -->
<!-- 					</div> -->
<!-- 				</div> -->
<%-- 			</c:if> --%>
		</div>
		
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" onclick="javascript:history.go(-1);" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
			<input type="button" onclick="submitinfo();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
	</form>
	<div style="height: 10px;width: 100%"></div>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	$("#buddingdate").calendar();
	$("#stopmuck").calendar();
	$("#stopwaterdate").calendar();
	$("#killdate").calendar();
	$("#rewarddate").calendar();
	
		function submitinfo(){
			<c:if test="${!empty seedfile.variety}">
				var variety=$("#variety_").data("values");
				if(variety==""){
					$.toptip('请选择品种', 'error');
					return false;
				}
				$("#variety").val(variety);
			</c:if>
			
			<c:if test="${!empty seedfile.seedid}">
				var seedid=$("#seedid_").data("values");
				if(seedid==""){
					$.toptip('请选择核心苗档案', 'error');
					return false;
				}
				$("#seedid").val(seedid);
			</c:if>
			
			<c:if test="${!empty seedfile.years}">
				var years=$("#years").data("values");
				if(years==""){
					$.toptip('请选择年份', 'error');
					return false;
				}
				$("#years").val(years);
			</c:if>
			
			<c:if test="${!empty seedfile.level}">
				var level=$("#level_").data("values");
				if(level==""){
					$.toptip('请选择扩繁级别', 'error');
					return false;
				}
				$("#level").val(level);
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
				var placeid=$("#placeid_").data("values");
				if(placeid==null||placeid==""){
					$.toptip('请选择扩所在地块', 'error');
					return false;
				}
				$("#placeid").val(placeid);
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
		$("#years").select({
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
		$("#variety_").select({
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
		$("#seedid_").select({
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
		$("#level_").select({
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
		
		<c:if test="${!empty plots}">
		$("#placeid_").select({
			  title: "选择所在地块",
			  items: [
			<c:forEach items="${plots}" var="plot"> 
			    {
			      title: '${plot.plotname}',
			      value: '${plot.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		</c:if>
	</script>
</body>
</html>