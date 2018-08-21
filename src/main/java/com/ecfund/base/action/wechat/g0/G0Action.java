package com.ecfund.base.action.wechat.g0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.common.SelectItem;
import com.ecfund.base.model.g0.Culturemedium;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.g0.Trainseed;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.publics.Icondict;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Menus;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.CulturemediumService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.g0.TrainseedService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.publics.IcondictService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 设置
 * @author xxl
 *
 */
@Controller
@RequestMapping("/wechat/G0")
public class G0Action {

	@Autowired
	private UsersService userService;
	@Autowired
	private DictionaryService dictService;
	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private SeedbedService seedbedService;
	@Autowired
	private CulturemediumService culturemediumService;
	@Autowired
	private TrainseedService trainseedService;
	@Autowired
	private IcondictService icondictService;

	@RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String list(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.findRole(user);
		List<Roles> roleList = user.getRolesList();
		List<Menus> list = new ArrayList();
		for (Roles role:roleList
			 ) {
			role = userService.findRoleMenulist(role);
			list.addAll(role.getMenuList());
		}
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", JSONObject.toJSON(list));
		return result.toJSONString();
	}
	@RequestMapping(value = "/add.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String add(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		for(int i=year;i>(year-5);i--){
			SelectItem	selectItem = new SelectItem(i,String.valueOf(i));
			selectItemList.add(selectItem);
		}
		Dictionary dict = new Dictionary();
		dict.setBelongsid("2");// 获取品种
		List<Dictionary> variety = dictService.find(dict);


		Dictionary dict2 = new Dictionary();
		dict2.setBelongsid("3");// 获取扩繁级别
		List<Dictionary> level = dictService.find(dict2);

		// 核心苗档案
		Seedfile seedfile = new Seedfile();
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setType("1");
		List<Seedfile> files = seedfileService.find(seedfile);
		//扩自批次
		Seedfile seedfiles = new Seedfile();
		seedfiles.setCompanyid(user.getCompany().getGuid());
		seedfiles.setType("2");
		List<Seedfile> fileList = seedfileService.find(seedfiles);
		// 苗床
		Seedbed seedbed = new Seedbed();
		seedbed.setCompanyid(user.getCompany().getGuid());
		List<Seedbed> seedbeds = seedbedService.find(seedbed);
		//
		Culturemedium culturemedium = new Culturemedium();
		culturemedium.setCompanyguid(user.getCompany().getGuid());
		List<Culturemedium> mediumList = culturemediumService.find(culturemedium);

		JSONObject content = new JSONObject();
		content.put("year",JSONObject.toJSON(selectItemList));
		content.put("variety",JSONObject.toJSON(variety));
		content.put("level",JSONObject.toJSON(level));
		content.put("files",JSONObject.toJSON(files));
		content.put("fileList",JSONObject.toJSON(fileList));
		content.put("mediumList",JSONObject.toJSON(mediumList));
		content.put("seedbeds",JSONObject.toJSON(seedbeds));
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}

	@RequestMapping(value = "/mediumSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String mediumSave(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String trait = request.getParameter("trait");
		String formula = request.getParameter("formula");
		Culturemedium culturemedium = new Culturemedium();
		culturemedium.setName(name);
		culturemedium.setCode(code);
		culturemedium.setTrait(trait);
		culturemedium.setFormula(formula);
		culturemedium.setCompanyguid(user.getCompany().getGuid());
		culturemedium.setCreatedate(Calendar.getInstance().getTime());
		culturemediumService.insert(culturemedium);
		JSONObject content = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/mediumList.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String mediumList(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		Culturemedium culturemedium = new Culturemedium();
		culturemedium.setCompanyguid(user.getCompany().getGuid());
		List<Culturemedium> list = culturemediumService.find(culturemedium);
		JSONObject content = new JSONObject();
		content.put("list",JSONObject.toJSON(list));
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/trainSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String trainSave(HttpServletRequest request, Trainseed trainseed) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		trainseed.setCompanyguid(user.getCompany().getGuid());
		trainseed.setUserguid(user.getGuid());
		trainseed.setCreatedate(Calendar.getInstance().getTime());
		Icondict icondict = new Icondict();
		icondict.setBelongsid("2");
		icondict.setKeyvalue(trainseed.getVariety());
		icondict = icondictService.view(icondict);
		trainseed.setIcon(icondict.getUrl());
		String guid = trainseedService.insert(trainseed);
		JSONObject content = new JSONObject();
		content.put("guid",guid);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/trainList.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String trainList(HttpServletRequest request, Trainseed trainseed) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		trainseed.setCompanyguid(user.getCompany().getGuid());
		List<Trainseed> list = trainseedService.find(trainseed);
		JSONObject content = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", JSON.toJSON(list));
		return result.toJSONString();
	}
}
