<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<link rel="stylesheet" href="${wechatPath}css\weui.min.css">
<link rel="stylesheet" href="${wechatPath}css\jquery-weui\jquery-weui.css">


<!--标准mui.css-->
<link rel="stylesheet" href="${wechatPath}css/mui/mui.min.css">
<style type="text/css">
.center-left {
  width: 47.3%;
  float: left;
  margin-left: 7px;
  margin-bottom: 7px;
  height: 70px;
  text-align: center;
  line-height:70px;
  font-size: 16px;
  top: 50%
}
.center-right {
  width: 47.3%;
  float: right;
  margin-left: 7px;
  margin-bottom: 7px;
  margin-right:5px;
  text-align: center;
  line-height:70px;
  height: 70px;
  font-size: 16px;
}
.growth_list_body{
    display:-webkit-flex;
    display: flex;
    flex-direction:column;
    justify-content:flex-start;
    align-items:flex-start;
    align-content:flex-start;
}
.growth_list_item{
    display:-webkit-flex;
    display: flex;
    flex-direction:row;
    justify-content:flex-start;
    align-items:flex-start;
    align-content:flex-start;
     width: 100%;
     margin-top:10px;
     border-bottom:1px solid;
}
.growth_list_date{
    display:inline-flex;
    display: flex;
    flex-direction:row;
    justify-content:flex-start ;
    align-items:flex-start;
    align-content:flex-start;
    width: 80px;
    height: 80px;
    margin-right: 5px;
    margin-left: 5px;
    flex-grow:0;
    flex-shrink:0;
}
.growth_list_right{
    display:inline-flex;
    display: flex;
    flex-direction:column;
    justify-content:flex-start ;
    align-items:flex-start ;
    align-content:flex-start;
    flex-shrink:1;
}
.growth_image_list{
    display: flex;
    display:inline-flex;
    flex-direction:row;
    flex-wrap:wrap;
    justify-content:flex-start ;
    align-items:flex-start;
    align-content:flex-start;
    margin-bottom:10px;
}
.growth_image_item{
    width: 80px;
    height: 80px;
    margin-left: 5px;
    margin-top: 5px;

}
</style>
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
		生长记录
		<c:choose>
		<c:when test="${sessionScope.operate == '5'}">
		<a href="${wechatPath}greenhouses/housedetail.action?guid=${seedfileid}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		</c:when>
		<c:otherwise>
		<a href="${wechatPath}g2g3/detail.action?seedfileid=${seedfileid}&operate=${sessionScope.operate}&type=${sessionScope.g2g3Type}" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		</c:otherwise>
		</c:choose>



		<c:if test="${sessionScope.user.depart.departid eq '5' ||sessionScope.user.depart.departid eq '8'}">
			<a href="${wechatPath}growth/add.action?relatedid=${seedfileid}&type=11&description=G1生长记录&url=${url}" style="position: absolute; right: 27px; top: 0px;">
				<img src="${wechatPath}icon/add.png" style="width: 20px" />
			</a>
			<a href="JavaScript:add();" style="position: absolute; right: 10px; top: 15px; text-decoration: none; font-size: 12px; color: #333">新建记录</a>
		</c:if>
	</div>
	<input type="hidden" id="date3" onchange="go(this.value,'${seedfileid}');">
<div class="growth_list_body">
<c:forEach items="${imageList}" var="list" varStatus="index">
	<div class="growth_list_item">
	    <div class="growth_list_date">
	        <div style='font-size:20px'>${list.key.month+1}月</div>
	        <div style='font-size:10px'>${list.key.date}日</div>
	    </div>
	    <div class="growth_list_right">
	        <div><fmt:formatDate value="${list.value[0].uploaddate}" type="date" pattern="yyyy年MM月dd日" /></div>
	        <div class="growth_image_list">
	            <c:forEach  items="${list.value}" var="image" varStatus="index2">
	              <div class="growth_image_item">
                       <image class="image-item" name="${index2.count}" title="${list.key.time}" height="80px" width="80px" src="${image.url}"  />
	              </div>
	            </c:forEach>
	        </div>
	    </div>
    </div>
</c:forEach>
</div>


<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>

<!-- 如果使用了某些拓展插件还需要额外的JS -->
<script src="${wechatPath}js\jquey-weui\jquery-weui.min.js"></script>
<script src="${wechatPath}js\jquey-weui\swiper.min.js"></script>

<!-- 	分页加载	 -->
<script type="text/javascript">

    $(".image-item").click(function(e){
        let index = e.target.title;
        let name = e.target.name-1;
        let items = [];
        $.each(${resultMap},function(i,v){
            if(i==index){
                for(let k=0;k<v.length;k++){
                    items.push(v[k].url);
                }
                var pb = $.photoBrowser({
                   items: items,
                   initIndex:name
                 });
                 pb.open(name);
            }
        });
    })
    var url = '${url}';
    var seedfileid = '${seedfileid}';
	 function add(){
		 window.location="${wechatPath}growth/add.action?relatedid="+seedfileid+"&type=11&description=生长记录&url="+url;
	 }
</script>
</body>
</html>