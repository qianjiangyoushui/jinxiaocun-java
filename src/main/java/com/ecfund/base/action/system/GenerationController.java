package com.ecfund.base.action.system;

import com.ecfund.base.model.system.TfMakeDefault;
import com.ecfund.base.service.system.TfMakeDefaultService;
import com.ecfund.base.util.json.JSONUtils;
import com.ecfund.base.util.templates.ICreateTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */

@Controller
@RequestMapping("/generation")
public class GenerationController {

	@Autowired
	@Qualifier("templatesService")
	private ICreateTemplates templatesService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index() {
		return "system/Generation_index";
	}

	@RequestMapping(value = "/generation.action", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String generation(TfMakeDefault entity, String tableName, String type) {
		String code = "";
		Map<String, String> map = new HashMap<String, String>();
		try {
			if ("dao".equals(type)) {
				code = templatesService.createDAO(entity, tableName);
			} else if ("service".equals(type)) {
				code = templatesService.createService(entity, tableName);
			} else if ("action".equals(type)) {
				code = templatesService.createAction(entity, tableName);
			} else if ("entity".equals(type)) {
				code = templatesService.createEntity(entity, tableName);
			} else if ("mapper".equals(type)) {
				code = templatesService.createSQLMap(entity, tableName);
			} else if ("ftlIndex".equals(type)) {
				code = templatesService.createFtlIndex(entity, tableName);
			} else if ("ftlInfo".equals(type)) {
				code = templatesService.createFtlInfo(entity, tableName);
			} else if ("js".equals(type)) {
				code = templatesService.createJS(entity, tableName);
			} else {
				code = "类别代码错误！";
			}
			map.put("info", "生成成功,请复制代码!");
			map.put("code", code);
		} catch (Exception ex) {
			ex.printStackTrace();
			map.put("code", code);
			map.put("info", "生成代码错误，错误信息如下：\n" + ex.getMessage());
		}
		System.out.println(code);
		System.out.println(JSONUtils.fromObject(map));
		return JSONUtils.fromObject(map);
	}
}
