package ${entityPackage};

<#list colList as list>
<#if list["type"][2]!=''>
import ${list["type"][2]};
</#if>
</#list>

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date ${.now?string('yyyy-MM-dd HH:mm')}
 * @filename ${tablename}.java
 * 
 */
 
public class ${tablename} implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
	
	<#list colList as list>
    private ${list["type"][1]} ${list['propname']};
    
    </#list>
	<#list colList as list>
    public ${list["type"][1]} get${list['propnameUp']}() {
        return ${list['propname']};
    }

    public void set${list['propnameUp']}(${list["type"][1]} ${list['propname']}) {
        this.${list['propname']} = ${list['propname']};
    }
    </#list>
}