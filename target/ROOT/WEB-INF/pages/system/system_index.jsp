<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp"></jsp:include>

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
				<jsp:include page="navhead.jsp"></jsp:include>
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
				<jsp:include page="sidebar.jsp"></jsp:include>
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
									 首页
								</small>
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

<!-- 								<div class="row"> -->
<!-- 									<div class="space-6"></div> -->

<!-- 									<div class="col-sm-7 infobox-container"> -->
<!-- 										<div class="infobox infobox-green  "> -->
<!-- 											<div class="infobox-icon"> -->
<!-- 												<i class="icon-comments"></i> -->
<!-- 											</div> -->

<!-- 											<div class="infobox-data"> -->
<%-- 												<span class="infobox-data-number">${userCount}</span> --%>
<%-- 												<div class="infobox-content">${userCount}个用户</div> --%>
<!-- 											</div> -->
<!-- 											<div class="stat stat-success"></div> -->
<!-- 										</div> -->

<!-- 										<div class="infobox infobox-pink  "> -->
<!-- 											<div class="infobox-icon"> -->
<!-- 												<i class="icon-shopping-cart"></i> -->
<!-- 											</div> -->

<!-- 											<div class="infobox-data"> -->
<%-- 												<span class="infobox-data-number">${orderCount }</span> --%>
<%-- 												<div class="infobox-content">${orderCount }个订单</div> --%>
<!-- 											</div> -->
<!-- 											<div class="stat stat-important"></div> -->
<!-- 										</div> -->

<!-- 										<div class="infobox infobox-orange2  "> -->
<!-- 											<div class="infobox-chart"> -->
<%-- 												<span class="sparkline" data-values="196,128,202,177,154,94,100,170,224"></span> --%>
<!-- 											</div> -->

<!-- 											<div class="infobox-data"> -->
<%-- 												<span class="infobox-data-number">${shopCount }</span> --%>
<%-- 												<div class="infobox-content">${shopCount }家店铺</div> --%>
<!-- 											</div> -->

<!-- 											<div class="badge badge-success"> -->
<!-- 												<i class="icon-arrow-up"></i> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="space-6"></div> -->
<!-- 									</div> -->

<!-- 									<div class="vspace-sm"></div> -->
<!-- 								</div>/row -->
								<div class="hr hr32 hr-dotted"></div>
								<!-- PAGE CONTENT ENDS -->
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
				<jsp:include page="setting.jsp"></jsp:include>
			</div><!-- /.main-container-inner -->
			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
<jsp:include page="commonJS.jsp"></jsp:include>
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

