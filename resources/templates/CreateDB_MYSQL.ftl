CREATE TABLE `${dbName}` (
  <#list col as col>
  `${col.name}` ${col.type} <#if col.isNull=='0'>NOT NULL<#else>DEFAULT ${col.defaultValue}</#if>,
  </#list>
  PRIMARY KEY (`${key}`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='${comment}';