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
		<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
		<link rel="stylesheet" href="${wechatPath}css/mui/app.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/jquery-weui.min.css">
		<link rel="stylesheet" href="${wechatPath}css/swiper-3.3.1.min.css">
		<link rel="stylesheet" type="text/css" href="${wechatPath}css/weui.css">
<style type="text/css">
	 p{
		color: #000000;
		font-size: 17px;
	}
</style>
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		瓶苗(G0)溯源档案信息
		<c:choose>
			<c:when test="${sessionScope.operate eq '1' || sessionScope.operate eq '2'}">
				<a href="${wechatPath}seedbed/detail.action?seedbedid=${seedfile.seedbed.guid}&operate=${sessionScope.operate}" style="position: absolute; left: 10px; top: 8px">
			</c:when>
			<c:when test="${sessionScope.operate eq '3' ||sessionScope.operate eq '4' ||sessionScope.operate eq '5' ||sessionScope.operate eq '6' ||sessionScope.operate eq '7' ||sessionScope.operate eq '8' ||sessionScope.operate eq '9' ||sessionScope.operate eq '10' ||sessionScope.operate eq '11'}">
				<a href="${wechatPath}greenhouses/housedetail.action?guid=${sessionScope.g1guid}&operate=${sessionScope.operate}" style="position: absolute; left: 10px; top: 8px">
			</c:when>
			<c:otherwise>
				<a href="${wechatPath}bottleseed/list.action" style="position: absolute; left: 10px; top: 8px">
			</c:otherwise>
		</c:choose>
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div style="margin-top: -20px;">
	<div  class="bd" >
		<div class="weui-cells">
          <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>批次编号：${seedfile.batch}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        
          <a class="weui-cell weui-cell_access"  <c:if test="${sessionScope.user.depart.departid eq '4'}"> href="${wechatPath}bottleseed/edit.action?type=1&variety=${seedfile.variety}&guid=${seedfile.guid}&operate=${sessionScope.operate}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>品种：${seedfile.varietys.belongsname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access"  href="${wechatPath}seedfile/detail.action?guid=${seedfile.seedid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>核心苗来源</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '4'}"> href="${wechatPath}bottleseed/edit.action?type=3&level=${seedfile.level}&guid=${seedfile.guid}&operate=${sessionScope.operate}"</c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>扩繁级别：${seedfile.levels.belongsname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '4'}"> href="${wechatPath}bottleseed/edit.action?type=6&bottleamount=${seedfile.bottleamount}&guid=${seedfile.guid}&operate=${sessionScope.operate}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>瓶苗个数：${seedfile.bottleamount}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '4'}"> href="${wechatPath}bottleseed/edit.action?type=4&placeid=${seedfile.placeid}&guid=${seedfile.guid}&operate=${sessionScope.operate}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>培养室：${seedfile.seedbed.seedbedname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access"  href="${wechatPath}medium/list.action?seedfileid=${seedfile.guid}&operate=${operate}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>培养基信息</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
	
	
		<div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '4'}"> href="${wechatPath}bottleseed/edit.action?type=7&startdate=<fmt:formatDate value="${seedfile.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}&operate=${sessionScope.operate}"</c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>扩繁时间段：<fmt:formatDate value="${seedfile.startdate }" pattern="yyyy-MM-dd"/>--<fmt:formatDate value="${seedfile.enddate }" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" onclick="upload('${seedfile.seedid}','核心苗检测结果','1')">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>核心苗检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" onclick="upload('${seedfile.guid}','基础苗检测结果','1')">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>基础苗检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" onclick="upload('${seedfile.guid}','扩繁苗检测结果','2')">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>扩繁苗检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}logs/list.action?relatedid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>档案修改历史</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
	</div>
    
    </div>
	

	<script src="${wechatPath}js/jquery-1.8.1.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/mui.min.js"> </script>
	
	<script type="text/javascript">
	function upload(id,desc,type){
		desc=encodeURI(encodeURI(desc));
		window.location.href='${wechatPath}upload/upload.action?relatedid='+id+'&type='+type+'&url=${url}&operate=${sessionScope.operate}&description='+desc;
	}
	</script>
		
</body>
</html>