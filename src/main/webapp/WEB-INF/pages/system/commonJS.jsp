<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- basic scripts -->

		<!--[if !IE]> -->

<%-- 		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script> --%>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='${path}js/assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='${path}js/assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="${path}js/assets/js/bootstrap.min.js"></script>
		<script src="${path}js/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->

		<script src="${path}js/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="${path}js/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="${path}js/assets/js/jquery.slimscroll.min.js"></script>
		<script src="${path}js/assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="${path}js/assets/js/jquery.sparkline.min.js"></script>
		<script src="${path}js/assets/js/flot/jquery.flot.min.js"></script>
		<script src="${path}js/assets/js/flot/jquery.flot.pie.min.js"></script>
		<script src="${path}js/assets/js/flot/jquery.flot.resize.min.js"></script>

		<script src="${path}js/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>
		<script src="${path}js/assets/js/fuelux/fuelux.tree.min.js"></script>
		<!-- ace scripts -->

		<script src="${path}js/assets/js/ace-elements.min.js"></script>
		<script src="${path}js/assets/js/ace.min.js"></script>
		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			jQuery(function($) {
				$('.sparkline').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
					$(this).sparkline('html', {tagValuesAttribute:'data-values', type: 'bar', barColor: barColor , chartRangeMin:$(this).data('min') || 0} );
				});
			})
		</script>