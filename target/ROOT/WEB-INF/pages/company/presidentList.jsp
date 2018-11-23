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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
	区域内企业列表
		<a href="${wechatPath}index/index.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		</div>
	<div class="weui-cells weui-cells_form infinite" style="margin-top: 0;">

		<ul class="mui-table-view mui-table-view-chevron" id="list">
            <c:forEach items="${companyList}" var="company">
              <li class="mui-table-view-cell mui-media">
                  <a class="mui-navigate-right" href="${wechatPath}president/indexList.action?type=6&companyid=${company.guid}">
                    <img class="mui-media-object mui-pull-left" src="${wechatPath}icon/main.png">
                      <div class="mui-media-body">${company.companyname}<br/>
                        <p class="mui-ellipsis">${company.contactperson}</p>
                        <p class="mui-ellipsis">${company.province}${company.city}${company.area}</p>
                      </div>
                  </a>
              </li>
              </c:forEach>
		</ul>


	</div>

	<div style="height: 155px; width: 100%"></div>

<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>

	<!-- 	分页加载	 -->
	<script type="text/javascript">
	</script>
</body>
</html>