package com.ecfund.base.util.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.ecfund.base.util.common.FileUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtils {

	private Configuration cfg = null;

	/**
	 * 获取freemarker的配置.
	 * 
	 * @return
	 */
	protected Configuration getFreeMarkerCFG() throws Exception {
		if (null == cfg) {
			cfg = new Configuration();
		}
		return cfg;
	}

	/**
	 * 生成静态文件.
	 * 
	 * @param templateFileName
	 *            模板文件名,例如"sqlmap.ftl"
	 * @param propMap
	 *            用于处理模板的属性Object映射
	 * @throws Exception
	 * @throws IOException
	 */
	public String generateFile(String templateFileName,
			Map<String, Object> propMap) throws IOException, Exception {
		FileUtils fileUtils = new FileUtils();
		String path = fileUtils.getPath();
		getFreeMarkerCFG().setDirectoryForTemplateLoading(new File(path));
		Template t = getFreeMarkerCFG().getTemplate(templateFileName, "UTF-8");
		Writer out = new StringWriter(2048);
		t.process(propMap, out);
		String tmp = out.toString();
		out.close();
		return tmp;
	}

}