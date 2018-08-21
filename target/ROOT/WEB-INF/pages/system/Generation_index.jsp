<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<script type="text/javascript" src="${path}js/jquery-1.8.1.min.js"></script>
<title>代码生成器</title>
</head>
<body>
<form id="fm"  method="post" novalidate>
<table>
<tr>
<td><label>daoPath</label></td>
<td><input type="text" name="daoPath"  id="daoPath"value="com.ecfund.base.dao"/></td>
</tr>
<tr>
<td><label>servicePath</label></td>
<td><input type="text" name="servicePath" id="servicePath" value="com.ecfund.base.service"/></td>
</tr>
<tr>
<td><label>actionPath</label></td>
<td><input type="text" name="actionPath" id="actionPath" value="com.ecfund.base.action"/></td>
</tr>
<tr>
<td><label>entityPath</label></td>
<td><input type="text" name="entityPath" id="entityPath" value="com.ecfund.base.model"></td>
</tr>
<tr>
<td><label>mapperPath</label></td>
<td><input type="text" name="mapperPath" id="mapperPath"value="com.ecfund.base.dao.mapper"/></td>
</tr>
<tr>
<td><label>tableName</label></td>
<td><input type="text" name="tableName" id="tableName"/></td>
</tr>
<tr>
<td><label>生成类型</label></td>
<td><select name="type" id="type">
	<option  value="entity" >entity</option>
	<option value="mapper">mapper</option>
	<option value="dao">dao</option>
	<option value="service">service</option>
	<option value="action">action</option>
</select></td>
</tr>
<tr>
<td colspan="2"><button id="btn"  type="button" onclick="make()">生成代码</button></td>
</tr>
</table>
</form>
<div id="div"></div>
<textarea rows="50" cols="100" id="textarea"></textarea>
<script type="text/javascript">
        var url;
        function make(){
        	var type;
        	type=$("#type").val();
        	url = '${path}generation/generation.action?type=' + type;
        	var daoPath = $("#daoPath").val();
        	var servicePath = $("#servicePath").val();
        	var actionPath = $("#actionPath").val();
        	var entityPath = $("#entityPath").val();
        	var mapperPath = $("#mapperPath").val();
        	var tableName = $("#tableName").val();
	        $.ajax({ //请求登录处理页
	            url: url, //登录处理页
	            dataType: "json",
	            //传送请求数据
	            data: { daoPath: daoPath, servicePath: servicePath, actionPath: actionPath, entityPath: entityPath, mapperPath: mapperPath, tableName:tableName},
	            success: function (data) { //登录成功后返回的数据
	            	//alert(data.code);
	            	$("#textarea").val(data.code);
	            }
	        });
        }
    </script>
</body>
</html>