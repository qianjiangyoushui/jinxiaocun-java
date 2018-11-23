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
<link rel="stylesheet" href="${wechatPath}css/mui/baocun.css">
<style type="text/css">
	 p{
		color: #000000;
		font-size: 17px;
	}
</style>
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		原原种(G1)溯源档案信息
		<c:choose>
		    <c:when test="${sessionScope.companyid!=null}">
        		<a href="${wechatPath}president/indexList.action?type=6&companyid=${sessionScope.companyid}" style="position: absolute; left: 10px; top: 8px">
        	 </c:when>
			<c:when test="${sessionScope.operate eq '5'}">
				<a href="${wechatPath}greenhouses/list.action?operate=${sessionScope.operate}" style="position: absolute; left: 10px; top: 8px">
					<img src="${wechatPath}icon/back.png" style="width: 20px" />
				</a>
			</c:when>
			<c:when test="${sessionScope.operate eq '3' || sessionScope.operate eq '4'}">
				<a href="${wechatPath}greenhouses/detail.action?guid=${seedfile.greenhouses.guid}&operate=${sessionScope.operate}" style="position: absolute; left: 10px; top: 8px">
					<img src="${wechatPath}icon/back.png" style="width: 20px" />
				</a>
			</c:when>
			<c:when test="${sessionScope.operate eq '6' || sessionScope.operate eq '7' || sessionScope.operate eq '9' || sessionScope.operate eq '10' || sessionScope.operate eq '8' || sessionScope.operate eq '11'}">
				<a href="${wechatPath}g2g3/detail.action?seedfileid=${sessionScope.g2g3guid}&operate=${sessionScope.operate}&g2g3Type={g2g3Type}" style="position: absolute; left: 10px; top: 8px">
					<img src="${wechatPath}icon/back.png" style="width: 20px" />
				</a>
			</c:when>
		</c:choose>
	</div>
	
	
	<div  class="bd" style="margin-top: -20px;">
	
	<div class="weui-cells">
          <a class="weui-cell weui-cell_access" href="javascript:;">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>批次编号：${seedfile.batch}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=1&variety=${seedfile.variety}&guid=${seedfile.guid}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>种植品种：${seedfile.varietys.belongsname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
     </div>
     
     <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access"  href="${wechatPath}bottleseed/detail.action?seedfileid=${seedfile.seedid}" >
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>瓶苗批次档案</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=3&level=${seedfile.level}&guid=${seedfile.guid}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>扩繁级别：${seedfile.levels.belongsname}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=4&placeid=${seedfile.placeid}&guid=${seedfile.guid}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>所在网棚：${seedfile.greenhouses.housename}</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
           <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=6&strainamount=${seedfile.strainamount}&guid=${seedfile.guid}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>定制株数：${seedfile.strainamount}株</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <!-- <div class="weui-cells" style="margin-top: 0px;">
         
        </div> -->
        
        <div class="weui-cells" style="margin-top: 0px;">
        <c:choose>
             	 <c:when test="${empty seedfile.stopwaterdate}"> 
             	 	<a class="weui-cell weui-cell_access"  <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=24&guid=${seedfile.guid}" </c:if>>
              </c:when>
              <c:otherwise>  
		  			<a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=24&startdate=<fmt:formatDate value="${seedfile.startdate }" pattern="yyyy-MM-dd"/>&enddate=<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}" </c:if>>
			  </c:otherwise>
			  </c:choose>
          
          
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>定植日期：<fmt:formatDate value="${seedfile.startdate }" pattern="yyyy-MM-dd"/>-<fmt:formatDate value="${seedfile.enddate}" pattern="yyyy-MM-dd"/></p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        <div class="weui-cells" style="margin-top: 0px;">
        <c:choose>
             	 <c:when test="${empty seedfile.rewarddate}"> 
             	 	<a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=21&guid=${seedfile.guid}" </c:if>>
              </c:when>
              <c:otherwise>  
		  			<a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=21&rewarddate=<fmt:formatDate value="${seedfile.rewarddate}" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}" </c:if>>
			  </c:otherwise>
			  </c:choose>
          
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>收获日期：
              <c:choose>
             	 <c:when test="${empty seedfile.rewarddate}"> 
             	 	(收获后点击维护)
              </c:when>
              <c:otherwise>  
		  			<fmt:formatDate value="${seedfile.rewarddate}" pattern="yyyy-MM-dd"/> 
			  </c:otherwise>
			  </c:choose>
              </p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
         <div class="weui-cells" style="margin-top: 0px;">
         <c:choose>
             	 <c:when test="${empty seedfile.stopwaterdate}"> 
             	 	<a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=22&guid=${seedfile.guid}" </c:if>>
              </c:when>
              <c:otherwise>  
		  			<a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=22&stopwaterdate=<fmt:formatDate value="${seedfile.stopwaterdate}" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}" </c:if>>
			  </c:otherwise>
			  </c:choose>
          
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>停水日期：
              <c:choose>
             	 <c:when test="${empty seedfile.stopwaterdate}"> 
             	 	(停水后点击维护)
              </c:when>
              <c:otherwise>  
		  			<fmt:formatDate value="${seedfile.stopwaterdate}" pattern="yyyy-MM-dd"/> 
			  </c:otherwise>
			  </c:choose>
              
              </p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
           <c:choose>
             	 <c:when test="${empty seedfile.stopwaterdate}"> 
             	 	<a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=23&guid=${seedfile.guid}" </c:if>>
              </c:when>
              <c:otherwise>  
		  			<a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '5'}"> href="${wechatPath}greenhouses/editSeedfile.action?type=23&killdate=<fmt:formatDate value="${seedfile.killdate}" pattern="yyyy-MM-dd"/>&guid=${seedfile.guid}" </c:if>>
			  </c:otherwise>
			  </c:choose>
           
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>杀秧日期：
              <c:choose>
             	 <c:when test="${empty seedfile.killdate}"> 
             	 	(杀秧后点击维护)
              </c:when>
              <c:otherwise>  
		  			<fmt:formatDate value="${seedfile.killdate}" pattern="yyyy-MM-dd"/> 
			  </c:otherwise>
			  </c:choose>
              </p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
        </div>
        
        
        <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}pest/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>病虫害发生情况</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
           <a class="weui-cell weui-cell_access" href="${wechatPath}eppo/list.action?seedfileid=${seedfile.guid}">
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
           <a class="weui-cell weui-cell_access" href="${wechatPath}fertilization/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>施肥记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          </div>
          
          <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" href="${wechatPath}watering/list.action?seedfileid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>灌溉记录</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
           <a class="weui-cell weui-cell_access" href="${wechatPath}yieldtest/list.action?seedfileid=${seedfile.guid}">
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
          </div>
          
          <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '3'}"> href="JavaScript:upload('${seedfile.guid}','瓶苗出库检测','12');" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>瓶苗出库检测结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '3'}"> href="${wechatPath}detection/detectionPage.action?relatedid=${seedfile.guid}&type=13&description=原原种入库检验&url=${url}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>原原种入库检验结果</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          </div>
          
          <div class="weui-cells" style="margin-top: 0px;">
          <a class="weui-cell weui-cell_access" <c:if test="${sessionScope.user.depart.departid eq '3'}"> href="${wechatPath}wintertest/winterPage.action?seedfileid=${seedfile.guid}" </c:if>>
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>冬季测试情况</p>
            </div>
            <div class="weui-cell__ft"></div>
          </a>
          <a class="weui-cell weui-cell_access" href="${wechatPath}logs/list.action?relatedid=${seedfile.guid}">
            <div class="weui-cell__bd" style="margin-top:8px;">
              <p>档案变动历史</p>
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
function gog0(id){
	window.location = "${wechatPath}bottleseed/detail.action?seedfileid="+id;
}

function upload(id,desc,type){
	desc=encodeURI(encodeURI(desc));
	window.location.href='${wechatPath}detection/detectionPage.action?relatedid='+id+'&type='+type+'&url=${url}&operate=${sessionScope.operate}&description='+desc;
}

		
</script>
</body>
