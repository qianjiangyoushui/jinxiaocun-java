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
<body>
	<div style="height: 50px; line-height: 50px; background: #f7f7f7; text-align: center; font-size: 16px; border-bottom: 1px solid #bdbbbc; position: relative">
	种植批次
		<a href="${wechatPath}company/presidentList.action" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		</div>

<div class="weui-tab" id="tab">
    <div class="weui-navbar">
        <div class="weui-navbar__item">商品薯</div>
        <div class="weui-navbar__item">一级种</div>
        <div class="weui-navbar__item">原种</div>
        <div class="weui-navbar__item">原原种</div>
        <div class="weui-navbar__item">瓶苗</div>
    </div>
    <div class="weui-tab__panel">
        <div class="weui-tab__content page_feedback">
        	<div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
        		<div id="list0"></div>
        	</div>
        </div>
        <div class="weui-tab__content">
            <div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
                <div id="list1"></div>
             </div>
         </div>
        <div class="weui-tab__content">
            <div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
        		<div id="list2"></div>
        	</div>
         </div>
        <div class="weui-tab__content">
            <div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
                <div id="list3"></div>
            </div>
         </div>
        <div class="weui-tab__content">
            <div class="weui-cells weui-cells_form infinite" style="margin-top: 0;" >
                <div id="list4"></div>
            </div>
         </div>
    </div>
</div>



<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
		<script src="${wechatPath}js/weui.min.js" type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>

	<!-- 	分页加载	 -->
	<script type="text/javascript">

weui.tab('#tab',{
    defaultIndex: 0,
    onChange: function(index){
        console.log(index);
        if(index == 0){
            $("#list0").empty();
            loadData(eval('${years}'),"${wechatPath}g2g3/presidentList.action",6).then(function(result){
            $("#list0").append(result);
            });
        }
        if(index == 1){
            $("#list1").empty();
            loadData(eval('${years}'),"${wechatPath}g2g3/presidentList.action",5).then(function(result){
            $("#list1").append(result);
            },function(){});
        }
        if(index == 2){
            $("#list2").empty();
            loadData(eval('${years}'),"${wechatPath}g2g3/presidentList.action",4).then(function(result){
            $("#list2").append(result);
            });
        }
        if(index == 3){
            $("#list3").empty();
            loadData(eval('${years}'),"${wechatPath}greenhouses/presidentList.action",3).then(function(result){
            $("#list3").append(result);
            });
        }
        if(index == 4){
            $("#list4").empty();
            loadData(eval('${years}'),"${wechatPath}bottleseed/presidentList.action",2).then(function(result){
            $("#list4").append(result);
            });
        }
    }
});

        var pageNo=1;
		var pageCount=100;//默认
		var loading = false;  //状态标记

    var loadData = function (years,url,type){
        var form = new FormData();
        form.append("year", years);
        form.append("pageNo", pageNo);
        form.append("type", type);
        let promise = new Promise(function(resolve,reject){
        var client = new XMLHttpRequest();
        client.open("post",url);
        client.onreadystatechange=handler;
        client.responseType="json";
        client.setRequestHeader("Accept","application/json");
        client.send(form);
        function handler(){
            if(this.readyState!=4){
                return;
            }
            if(this.status==200){
                  var content="";
                  var pagelist=this.response.page;
                  years=this.response.years;//检索条件
                  pageNo=1;//设置pageNo;
                  pageCount=pagelist.pageCount;//设置总页数
                  var rows=pagelist.rows;
                  for(var i=0;i<rows.length;i++){
                      if(i%2==0){
                          content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
                      }
                      if(i==rows.length-1 &&i>1){
                          if((i+1)%2!=0){
                              content+='<div class="weui-cells" style="width: 100%;margin-top: 0px;">';
                          }
                      }
                      if(type==6||type==5||type==4){
                          content+='<a class="weui-cell weui-cell_access" href="${wechatPath}g2g3/detail.action?years='+years+'&seedfileid='+rows[i].guid+'">';
                          content+='<div class="weui-cell__bd" style="margin-top:10px;">';
                          content+='<p style="font-size:17px;color:#000000">';
                          content+='批次编号:'+rows[i].batch+' &nbsp; 种植地:'+rows[i].plots.plotname;
                          content+='</p></div><div class="weui-cell__ft"></div></a>';
                      }else if(type==3){
                          content += '<a class="weui-cell weui-cell_access" href="${wechatPath}greenhouses/housedetail.action?guid='+rows[i].guid+'"><div class="weui-cell__bd">';
                          content += ' <p>批次编号：'+rows[i].batch+'定植于'+rows[i].greenhouses.housename+'</p>';
                          content += '</div><div class="weui-cell__ft"></div></a>';
                      }else if(type==2){
                          content+='<a class="weui-cell weui-cell_access" href="${wechatPath}bottleseed/detail.action?seedfileid='+rows[i].guid+'">';
                          content+='<div class="weui-cell__bd" style="margin-top:10px;">';
                          content+='<p style="font-size:17px;color:#000000">';
                          content+='批次编号:'+rows[i].batch+'  培养于'+rows[i].seedbed.seedbedname
                          content+='苗床</p></div><div class="weui-cell__ft"></div></a>';
                      }
                      if(i%2!=0){
                        content+='</div><div style="margin-bottom: 10px;width: 100%"></div>';
                      }
                      if(i==rows.length-1 &&i>1){
                          if((i+1)%2!=0){
                              content+='</div><div style="margin-bottom: 10px;width: 100%"></div>';
                          }
                      }
                  }
                  resolve(content);
            }else{
                reject(new Error(this.statusText));
            }
        }
      });
      return promise;
    }
    $(function(){
        $("#list0").empty();
        loadData(eval('${years}'),"${wechatPath}g2g3/presidentList.action",6).then(function(result){
        $("#list0").append(result);
        });
    })
	</script>
</body>
</html>