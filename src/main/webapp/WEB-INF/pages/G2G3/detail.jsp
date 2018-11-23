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
<style type="text/css">
	 p{
		color: #000000;
		font-size: 17px;
	}
</style>
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		<c:if test="${seedfile.type eq '4' }">G2溯源档案信息</c:if>
		<c:if test="${seedfile.type eq '5' }">G3溯源档案信息</c:if>
		<c:if test="${seedfile.type eq '6' }">商品薯溯源档案信息</c:if>
		
		<c:choose>
			<c:when test="${sessionScope.companyid!=null}">
				<a href="${wechatPath}president/indexList.action?type=6&companyid=${sessionScope.companyid}" style="position: absolute; left: 10px; top: 8px">
			</c:when>
			<c:when test="${sessionScope.operate eq '8' || sessionScope.operate eq '11'}">
				<a href="${wechatPath}g2g3/list.action?type=${sessionScope.g2g3Type}&operate=${sessionScope.operate}" style="position: absolute; left: 10px; top: 8px">
			</c:when>
			<c:otherwise>
				<a href="${wechatPath}plot/detail.action?plotid=${sessionScope.plotguid}&operate=${sessionScope.operate}&type=${sessionScope.g2g3Type}" style="position: absolute; left: 10px; top: 8px">
			</c:otherwise>
		</c:choose>
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
	</div>
	<div  class="bd" style="margin-top: -20px;">
		<div class="weui-cells">
          <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>批次编号：${seedfile.batch}</p>
            </div>
            <div class=""></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=1&type=${seedfile.type}&variety=${seedfile.variety}&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>品种：${seedfile.varietys.belongsname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <div class="weui-cells" style="margin-top: 0px;">
        <c:choose>
		   <c:when test="${seedfile.isproduction eq '0'}">  
		    	<a class="weui-cell weui-cell_access">
	               <div class="weui-cell__bd" style="margin-top:8px;">
					   <p>来源：${seedfile.source}</p>
	               </div>
	               <div class="weui-cell__ft"></div>
	             </a>
		   </c:when>
		   <c:otherwise>
		   <c:choose>
		    <c:when test="${seedfile.type eq '4' }">
		    <a class="weui-cell weui-cell_access" href="${wechatPath}greenhouses/housedetail.action?guid=${seedfile.seedid}" >
                   <div class="weui-cell__bd" style="margin-top:8px;">
                       <p>原原种批次档案</p>
                   </div>
                   <div class="weui-cell__ft"></div>
             </a>
		    </c:when>
		    <c:otherwise>
                 <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/detail.action?seedfileid=${seedfile.seedid}" >
                       <div class="weui-cell__bd" style="margin-top:8px;">
                           <c:if test="${seedfile.type eq '5' }"> <p>原种批次档案</p></c:if>
                           <c:if test="${seedfile.type eq '6' }"> <p>所用种薯批次档案</p></c:if>
                       </div>
                       <div class="weui-cell__ft"></div>
                 </a>
		    </c:otherwise>
		    </c:choose>
		   </c:otherwise>
		</c:choose>
          
          
          
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=3&type=${seedfile.type}&level=${seedfile.level}&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>扩繁级别：${seedfile.levels.belongsname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=4&type=${seedfile.type}&muamount=${seedfile.muamount}&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>种植面积：${seedfile.muamount}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=5&type=${seedfile.type}&placeid=${seedfile.placeid}&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>地块信息：${seedfile.plots.plotname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=6&type=${seedfile.type}&startdate=<fmt:formatDate value="${seedfile.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>播种日期：<fmt:formatDate value="${seedfile.startdate }" pattern="yyyy-MM-dd"/>至<fmt:formatDate value="${seedfile.enddate }" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=7&type=${seedfile.type}&buddingdate=<fmt:formatDate value="${seedfile.buddingdate }" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>出苗日期：<fmt:formatDate value="${seedfile.buddingdate }" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
         </div>
          <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=8&type=${seedfile.type}&stopmuck=<fmt:formatDate value="${seedfile.startdate }" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>停肥日期：<fmt:formatDate value="${seedfile.stopmuck }" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=9&type=${seedfile.type}&stopwaterdate=<fmt:formatDate value="${seedfile.stopwaterdate }" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>停水日期：<fmt:formatDate value="${seedfile.stopwaterdate }" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
           <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=10&type=${seedfile.type}&killdate=<fmt:formatDate value="${seedfile.killdate }" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>杀秧日期：<fmt:formatDate value="${seedfile.killdate }" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/edit.action?edittype=11&type=${seedfile.type}&rewarddate=<fmt:formatDate value="${seedfile.rewarddate }" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>收获日期：<fmt:formatDate value="${seedfile.rewarddate }" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}pest/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>田间病虫害发生情况</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}plantprotect/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>植保方案实施情况</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}aphid/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>蚜虫检测记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}manure/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>施肥记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}irrigation/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>灌溉记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/yieldtest/list.action?guid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>测产记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}growth/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>生长记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" onclick="upload('${seedfile.guid}','出库检测结果','21')">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>出库检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access"  onclick="upload('${seedfile.guid}','田间检测结果','1')">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>田间检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access"   onclick="upload('${seedfile.guid}','入库块茎检测结果','22')">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>入库块茎检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access"  href="${wechatPath}wintertest/winterPage.action?seedfileid=${seedfile.guid}" >
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>冬季测试情况</p>
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
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/mui.min.js"></script>
		<script type="text/javascript">
		function upload(id,desc,type){
			desc=encodeURI(encodeURI(desc));
			window.location.href='${wechatPath}upload/upload.action?relatedid='+id+'&type='+type+'&url=${url}&operate=${sessionScope.operate}&description='+desc;
		}
		</script>
</body>
</html>