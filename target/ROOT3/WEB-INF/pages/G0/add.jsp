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
		溯源档案号录入
		<a href="javascript:history.go(-1);" style="position: absolute; left: 10px; top: 8px">
			<img src="${wechatPath}icon/back.png" style="width: 20px" />
		</a>
		<a href="javascript:;" style="position: absolute; right: 15px; top: 0px;">
			<img src="${wechatPath}icon/save.png" style="width: 20px" />
		</a>
		<a href="javascript:;" onclick="submitinfo();" style="position: absolute; right: 13px; top: 15px; text-decoration: none; font-size: 12px; color: #333">保存</a>
	</div>
	
	<form action="${wechatPath}bottleseed/save.action" method="post" id="formid">
		<div class="weui-cells weui-cells_form" style="margin-top: 0px;">

			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">品种</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择品种" name="varietyss" id="varietyss" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="variety" id="variety">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">核心苗档案</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择核心苗档案" name="seedids"  id="seedids" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="seedid" id="seedid">
				</div>							
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">年份</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择年份" name="year"  id="year" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="years" id="years">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">扩繁级别</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择扩繁级别" name="levelss"  id="levelss" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="level" id="level">
				</div>							
			</div>
			
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">扩繁时间段</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text"    name="startdate" id="startdate"style="margin-top: 5px;margin-bottom: 5px;width:110px;">
					<input class="weui-input" type="text"   name="enddate" id="enddate" style="margin-top: 5px;margin-bottom: 5px;width:110px;"/>
				</div>
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">所在苗床</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="text" placeholder="请选择苗床" name="placeids"  id="placeids" style="margin-top: 5px;margin-bottom: 5px;">
					<input type="hidden" name="placeid" id="placeid">
				</div>							
			</div>
			
			<div class="weui-cell">
				<div class="weui-cell__hd">
					<label class="weui-label">瓶苗个数</label>
				</div>
				<div class="weui-cell__bd">
					<input class="weui-input" type="number"  placeholder="请输入瓶苗个数" name="bottleamount" id="bottleamount" style="margin-top: 5px;margin-bottom: 5px;">
				</div>
			</div>
            <div class="weui-cell">
                    <div class="weui-cell__hd">
                        <label class="weui-label">切繁次数</label>
                    </div>
                    <div class="weui-cell__bd">
                        <input class="weui-input"  type="text" name="stubbless" id="stubbless" style="margin-top: 5px;margin-bottom: 5px;">
                        <input type="hidden" name="stubble" id="stubble">
                    </div>
                </div>
		</div>
		
		<div class="weui-form-preview__item" style="text-align:center;margin:30px 10%; white-space:nowrap;">
			<input type="reset" class="mui-btn mui-btn-success" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #9CCC65;color: #FFFFFF" value="取消">
			<input type="button" onclick="submitinfo();" class="mui-btn mui-btn-danger" style="margin:0 20px;padding:4px 20px;width:100px;background-color: #E84E40;color: #FFFFFF" value="保存">
		</div>
	</form>
	<div style="height: 10px;width: 100%"></div>
	<jsp:include   page="../common/tabbar.jsp" flush="true"/>
	<script src="${wechatPath}js/jquery-1.8.1.min.js"
		type="text/javascript"></script>
	<script src="${wechatPath}js/jquery-weui.min.js"
		type="text/javascript"></script>
	<script type="text/javascript">
	$("#startdate").calendar();
	$("#enddate").calendar();
	
		function submitinfo(){
			
			var varietys=$("#varietyss").data("values");
			$("#variety").val(varietys);
			if($("#variety").val()==""){
				$.toptip('请选择品种', 'error');
				return false;
			}
			
			
			var seedids=$("#seedids").data("values");
			$("#seedid").val(seedids);
			if($("#seedid").val()==""){
				$.toptip('请选择核心苗档案', 'error');
				return false;
			}
			
			
			var year=$("#year").data("values");
			$("#years").val(year);
			if($("#years").val()==""){
				$.toptip('请选择年份', 'error');
				return false;
			}
			
			
			var levels=$("#levelss").data("values");
			$("#level").val(levels);
			if($("#level").val()==""){
				$.toptip('请选择扩繁级别', 'error');
				return false;
			}
			
			
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
			
			var placeids=$("#placeids").data("values");
			$("#placeid").val(placeids);
			if($("#placeid").val()==""){
				$.toptip('请选择扩所在苗床', 'error');
				return false;
			}
			
			
			var bottleamount=$("#bottleamount").val().replace(/^\s+|\s+$/g,"");
			var r = /^\+?[1-9][0-9]*$/;
			if(!r.test(bottleamount)){
				$.toptip('请输入正确数量,整数', 'error');
				return false;
			}
			var stubbles = $("#stubbless").val();
            if(stubbles ==null || stubbles == ""){
                $.toptip('请选择当年第几茬', 'error');
                return false;
            }else{
                var stubble=$("#stubbless").data("values");
                $("#stubble").val(stubble);
            }
             verity($("#years").val(),$("#stubble").val(),$("#variety").val(),$("#level").val()).then(function(data){
                    if(data.msg=="ok"){
                        $("#formid").submit();
                    }else{
                        $.toptip('请重新填写，当前批次已经存在', 'error');
                    }
                },function(erro){
                        $.toptip('出错了，联系管理员解决', 'error');
             });

		}
		
		$("#year").select({
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
		
		$("#varietyss").select({
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
		
		$("#seedids").select({
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
		
		$("#levelss").select({
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
		
		$("#placeids").select({
			  title: "选择所在苗床",
			  items: [
			<c:forEach items="${seedbeds}" var="bed"> 
			    {
			      title: '${bed.seedbedname}',
			      value: '${bed.guid}',
			    },
	    	</c:forEach>
			  ]
			});
		
		$("#stubbless").select({
          title: "选择当年第几次切繁",
          items: [{
              title: '当年第一次',
              value: '1',
            },{
              title: '当年第二次',
              value: '2',
            },{
              title: '当年第三次',
              value: '3',
            },
            {
              title: '当年第四次',
              value: '4',
             },
            {
              title: '当年第五次',
              value: '5',
            }
          ]
        });

		function verity(years,placeid,variety,level){
		    var form = new FormData();
		    form.append("year", years);
		    form.append("palceid", placeid);
		    form.append("variety", variety);
		    form.append("level", level);
		    var promise =  new Promise(function(resolve,reject){
		        var client = new XMLHttpRequest();
		        client.open("post","${path}g2g3/check.action");
		        client.onreadystatechange=handler;
		        client.responseType="json";
		        client.setRequestHeader("Accept","application/json");
		        client.send(form);
		        function handler(){
		            if(this.readyState!=4){
		                return;
		            }
		            if(this.status==200){
		                resolve(this.response);
		            }else{
		                reject(new Error(this.statusText));
		            }
		        }
		    });
		    return promise;
		}
	</script>
</body>
</html>