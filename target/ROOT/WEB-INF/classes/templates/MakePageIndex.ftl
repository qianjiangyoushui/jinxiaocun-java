[#ftl]
<#include "../common/macro.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>标题</title>
<@style />
<@jquery />
<@easyui />
<@javascript />
<script>
$(function(){
	var sUrl = "";
[#list colList as list]
	sUrl+=conn_param("${list['propname']}","${"$"}{entity.${list['propname']}!""}");
[/#list]
	$('#bList').datagrid({
		nowrap: false,
		striped: true,
		url:'通配Action!list.action?rand='+sUrl,
		idField:'guid',
		rownumbers:true,
		pagination:true,
		frozenColumns:[[
			{field:'ck',checkbox:true}
		]],
		columns:[[
			[#list colList as list]
			{field:'${list['propname']}',title:'标题',align:'center',width:200}[#if list_has_next],[/#if]
			[/#list]
		]],
		toolbar:[{
			text:'查询',
			iconCls:'icon-search',
			handler:function(){
				showDialog("search",400,180,function(){
					$("#forms").submit();
				});
			}
		},"-",{
			text:'新增',
			iconCls:'icon-add',
			handler:function(){
				go("通配Action!info.action");
			}
		},{
			text:'修改',
			iconCls:'icon-edit',
			handler:function(){
				edit("bList","通配Action!info.action","guid","");
			}
		},{
			text:'删除',
			iconCls:'icon-cut',
			handler:function(){
				mulDel("bList","通配Action!del.action","");
			}
		}]
	});
});
</script>
</head>
<body>
<table id="bList" class="display" border="false" fit="true">
</table>
<div id="search" class="easyui-dialog" title="快速搜索" closed="true"  cache="false" modal="true">
  <form class="form-horizontal" action="?" method="post" id="forms">
    <fieldset>
      [#list colList as list]
      <label for="${list['propname']}">标题</label>
      <input type="text" class="span4" id="${list['propname']}" name="${list['propname']}">
      [/#list]
    </fieldset>
  </form>
</div>
</body>
</html>