<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<jsp:include page="../system/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${path}js/jquery-easyui-1.4.4/themes/default/easyui.css">
	<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
                           种薯质量追溯后台管理系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->
				<jsp:include page="../system/navhead.jsp"></jsp:include>
				<!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<jsp:include page="../system/sidebar.jsp"></jsp:include>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li class="active">控制台</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
					</div>

					<div class="page-content">
						<div class="page-header">
							<h1>
								控制台
								<small>
									<i class="icon-double-angle-right"></i>
									 企业管理
								</small>
								<small>
									<i class="icon-double-angle-right"></i>
									 企业管理审核
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="col-xs-12">
								<div class="widget-container-span ui-sortable">
								<form action="${path }company/manager.action" method="post">
									<%-- <span class="input-icon">
										<input type="text" class="nav-search-input" placeholder="公司名" name="companyName">
										<i class="icon-search nav-search-icon"></i>
									</span>
									<input type="hidden" name="pageCount" id="pageCount"/>
									<input type="hidden" name="rows" id="rows" value="${rows}"/>
									<input type="submit" class="btn btn-xs btn-info" value="搜索"> --%>
								</form>
										<div class="widget-box">
											<div class="widget-header header-color-blue">
												<h5 class="bigger lighter">
													<i class="icon-table"></i>
													公司列表 <div style="display: inline-block;float: right;margin-right: 16px;"><%-- 每页显示条数 <select size="1" onchange="changerows()" id="selectrows" name="sample-table-2_length" aria-controls="sample-table-2"><option value="10" <c:if test="${rows==10}">selected</c:if>>10</option><option value="20" <c:if test="${rows==20}">selected</c:if>>20</option><option value="50" <c:if test="${rows==50}">selected</c:if>>50</option><option value="100" <c:if test="${rows==100}">selected</c:if>>100</option></select></div> --%>
												</h5>
												<div class="widget-toolbar widget-toolbar-light no-border">
												</div>
											</div>

											<div class="widget-body">
												<div class="widget-main no-padding">
													<table class="table table-striped table-bordered table-hover">
														<thead class="thin-border-bottom">
															<tr>
																<th>
																	<i class="icon-briefcase"></i>
																	公司名称
																	
																</th>

																<th>
																	<i class="icon-comment"></i>
																	负责人
																</th>
																
																<th>
																	<i class="icon-user"></i>
																	公司地址
																</th>
																<th class="hidden-480">种植面积（亩）</th>
																<th class="hidden-480">种植年份（年）</th>
																<th class="hidden-480">注册日期</th>
																<th class="hidden-480">审核状态</th>
																<th class="hidden-480">操作</th>
															</tr>
														</thead>

														<tbody>
														<c:forEach var="pager" items="${pager}">
																<tr>
																<td>${pager.companyname}</td>
																<td>${pager.contactperson}</td>
																<td>${pager.province}-${pager.city}-${pager.area}</td>
																<td>${pager.plantarea}</td>
																<td>${pager.plantyears}</td>
																<td><fmt:formatDate value="${pager.registdate}" pattern="yyyy-MM-dd"/></td>
																<td>
																	<c:if test="${pager.status == '1'}">未审核</c:if>
																	<c:if test="${pager.status == '2'}">审核通过</c:if>
																	<c:if test="${pager.status == '3'}">审核不通过</c:if>
																</td>
																<td>
																	<button onclick="aud('${pager.guid}','2')" class="btn btn-minier btn-success">通过</button>
																	<button onclick="aud('${pager.guid}','3')" class="btn btn-minier btn-success">不通过</button>
																</td>
																</tr>
														</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
																		<!-- 分页开始 -->					
										<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
				</td>
				<td style="vertical-align:top;">
					<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
										          <tr>
										            <td>&nbsp;&nbsp;共有 ${pages.countItem} 条记录，当前第  ${pages.pageNo}/${pages.pageCount}页</td>
										            <td><table border="0" align="right" cellpadding="0" cellspacing="0">
										                <tr>
										                 <s:if test="%{pager.indexPage>1}">
										                	<td width="50"><input type="button" class="delbtn" value="首&nbsp;&nbsp;页" onclick="this.blur();pageForm(1);"/></td>
														 	<td width="50"><input type="button" class="delbtn" value="上一页" onclick="this.blur();pageForm( ${pages.pageNo-1});"/></td>
														</s:if><s:else>
														 	<td width="50"><input type="button" class="delbtn" value="首&nbsp;&nbsp;页" disabled="disabled"/></td>
														 	<td width="50"><input type="button"class="delbtn"  value="上一页" disabled="disabled"/></td>
														</s:else>
														<s:if test="%{pager.indexPage>=pager.pageCount}">
															 <td width="50"><input type="button"class="delbtn"  value="下一页"  disabled="disabled"/></td>
										                  	 <td width="50"><input type="button" class="delbtn" value="尾&nbsp;&nbsp;页" disabled="disabled"/></td>
														</s:if><s:else>
															<td width="50"><input type="button" class="delbtn" value="下一页" onclick="this.blur();pageForm( ${pages.pageNo+1});" /></td>
										                  	<td width="50"><input type="button"class="delbtn"  value="尾&nbsp;&nbsp;页" onclick="this.blur();pageForm( ${pages.pageCount});"  /></td>
														</s:else>
										                  <td width="100" class="STYLE1">转到第
										                    <input name="toPageNum" id="gopage"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" type="text" size="4" style="height:20px; width:30px; border:1px solid #999999;" />页</td>
										                  <td width="40">
																<s:if test="%{pager.pageCount<=1}">
																	<input  id="gotoPage"  type="button" class="delbtn" value="跳转"  disabled="disabled"/>
																</s:if>
																<s:else>
																	<input  id="gotoPage" type="button" class="delbtn"  value="跳转" onclick="this.blur();pageForm();"/>
																</s:else>
														</td>
										                </tr>
										            </table>
										           </td>
										          </tr>
										    </table>
					</div>
				</td>
			</tr>
		</table></div>	
										<!-- 分页结束 -->
								</div>
							</div><!-- /.col-xs-12 -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
				<jsp:include page="../system/setting.jsp"></jsp:include>
			</div><!-- /.main-container-inner -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
<jsp:include page="../system/commonJS.jsp"></jsp:include>
<script type="text/javascript" src="${path}js/jquery.min.js"></script>
<script type="text/javascript" src="${path}js/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}js/jquery-easyui-1.4.4/locale/easyui-lang-zh_CN.js"></script>
<script src="${path}js/zDialog/zDrag.js" type="text/javascript"></script>
<script src="${path}js/zDialog/zDialog.js" type="text/javascript"></script>
<script type="text/javascript">
function pageForm(v){
	var totalpage = ${pages.countItem};
	var r = /^[0-9]*[1-9][0-9]*$/;
	if(r.test(v)){
		if(v<=0) v=1;
		if(v>totalpage) v=totalpage;
		$("#pageCount").val(v);
		document.forms[1].submit();
	}else{
		var p = $("#gopage").val();
		if(r.test(p)){
			if(p<=0) p=1;
			if(p>totalpage) p=totalpage;
			$("#pageCount").val(p);
			document.forms[1].submit();
		}
	}
}

function aud(guid,value){
	$.ajax({
		url : "${path}company/aud.action",
		type : "POST",
		async : false,
		dataType : 'json',
		data : {
			guid : guid,
			status : value
		},
		success : function(data) {
			if(data.result){
				alert(data.message);
				window.location.reload();
			}else{
				alert(data.message);
			}
		},
		error : function(data) {
			alert("失败");
		}
	});
}
</script>
</body>
</html>

