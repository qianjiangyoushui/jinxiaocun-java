<?xml version="1.0" encoding="utf-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${tablename}">
	
	<!-- 缓存条数 -->
	<cache size="2000" />
	
	<!-- 条件语句 -->
	<sql id="WhereSQL">
		<where>
		<#list colList as list>
			<if test="${list['propname']} != null and ${list['propname']} != ''"> 
			AND T.${list['colname']} = #${"{"}${list['propname']}${"}"} 
			</if>
		</#list>
		</where>
	</sql>
	
	<!-- 查找语句 -->
	<select id="find" resultType="${tablename}" parameterType="${tablename}" flushCache="false" useCache="true">
    	SELECT 
	    	<#list colList as list>
	    	T.${list['colname']} AS ${list['propname']}<#if list_has_next>,</#if> 
	    	</#list>
    	FROM ${table} T
		<include refid="WhereSQL" />
		ORDER BY SORT
	</select>
	
	<!-- 获取总记录数语句 -->
	<select id="findCount" resultType="java.lang.Integer" parameterType="${tablename}" flushCache="false" useCache="true">
    	SELECT COUNT(*)  FROM ${table} T
		<include refid="WhereSQL" />
	</select>
	
	<!-- 插入语句 -->
	<insert id="insert" parameterType="${tablename}" flushCache="true"> 
		INSERT INTO ${table}
		(
			<#list colList as list>
			${list['colname']}<#if list_has_next>,</#if> 
			</#list>
		 ) 
		VALUES
		(
			<#list colList as list>
			#${"{"}${list['propname']},jdbcType=${list['type'][0]}${"}"}<#if list_has_next>,</#if> 
			</#list>
		) 
	</insert>
	
	<!-- 更新语句 -->
	<update id="update"  parameterType="${tablename}" flushCache="true"> 
		update ${table} 
		<set> 
			<#list colList as list>
			<if test="${list['propname']} != null">${list['colname']}=#${"{"}${list['propname']}${"}"},</if>
			</#list>
		</set>
		WHERE GUID = #${"{"}guid${"}"}
	</update> 
	
	<!-- 单选删除语句 -->
	<delete id="delete" parameterType="${tablename}" flushCache="true"> 
		DELETE FROM ${table} WHERE GUID = #${"{"}guid${"}"}
	</delete>
	
	<!-- 多选删除 -->
	<delete id="mulDel" parameterType="java.lang.String" flushCache="true">
		DELETE FROM ${table} WHERE GUID IN
		<foreach item="id" collection="array" open="(" separator="," close=")"> 
			#${"{"}id${"}"}
		</foreach> 
	</delete>
</mapper>
