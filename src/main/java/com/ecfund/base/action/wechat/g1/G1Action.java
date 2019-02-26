package com.ecfund.base.action.wechat.g1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.common.SelectItem;
import com.ecfund.base.model.eliteG1.*;
import com.ecfund.base.model.fertilization.Fertilization;
import com.ecfund.base.model.g0.Culturemedium;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.g0.Trainseed;
import com.ecfund.base.model.publics.*;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Menus;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.*;
import com.ecfund.base.service.g0.CulturemediumService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.g0.TrainseedService;
import com.ecfund.base.service.publics.*;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 设置
 * @author xxl
 *
 */
@Controller
@RequestMapping("/wechat/G1")
public class G1Action {

	@Autowired
	private UsersService userService;
	@Autowired
	private DictionaryService dictService;
	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private GreenhousesService greenhousesService;
	@Autowired
	private CulturemediumService culturemediumService;
	@Autowired
	private TrainseedService trainseedService;
	@Autowired
	private IcondictService icondictService;
	@Autowired
	private GrowthrecordService growthrecordService;
	@Autowired
	private UpimageService upimageService;
	@Autowired
	private QualityrecordService qualityrecordService;
	@Autowired
	private  SeedbedService seedbedService;
	@Autowired
	private FileralationService fileralationService;
	@Autowired
	private FertilizationService fertilizationService;
	@Autowired
	private EppoService eppoService;
	@Autowired
	private PlantprotectService plantprotectService;
	@Autowired
	protected WateringService wateringService;


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

		//扩自批次
		Seedfile seedfiles = new Seedfile();
		seedfiles.setCompanyid(user.getCompany().getGuid());
		seedfiles.setType("2");
		List<Seedfile> fileList = seedfileService.find(seedfiles);
		// 大棚
		Greenhouses greenhouses = new Greenhouses();
		greenhouses.setCompanyid(user.getCompany().getGuid());
		List<Greenhouses> houses = greenhousesService.find(greenhouses);



		JSONObject content = new JSONObject();
		content.put("year",JSONObject.toJSON(selectItemList));
		content.put("variety",JSONObject.toJSON(variety));
		content.put("level",JSONObject.toJSON(level));
		content.put("fileList",JSONObject.toJSON(fileList));
		content.put("houses",JSONObject.toJSON(houses));
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/save.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String save(HttpServletRequest request,Seedfile seedfile,String selectValue,String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setType("3");
		seedfile.setLevel("3");
		seedfile.setVisible("1");//可见
		seedfile.setCreatedate(new Date(System.currentTimeMillis()));
		seedfile.setOperatorid(user.getGuid());
		Greenhouses greenhouses = new Greenhouses();
		greenhouses.setGuid(seedfile.getPlaceid());
		greenhouses = greenhousesService.view(greenhouses);
		StringBuffer sb = new StringBuffer();
		sb.append(seedfile.getYears()).append("-").append(greenhouses.getHousename()).append("-").append(seedfile.getVariety()).append("-").append(createGrade(seedfile.getLevel()));
		seedfile.setBatch(sb.toString());
		String guid = seedfileService.insert(seedfile,selectValue,selectName);
		JSONObject content = new JSONObject();
		content.put("guid",guid);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/fertilizeSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String fertilizeSave(HttpServletRequest request,Fertilization fertilization,String selectValue,String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		fertilization.setCreatedate(new Date());
		fertilization.setOperatorid(user.getGuid());
		String[] batchArray = selectValue.split(",");
		String[] r= this.fertilizationService.batchsaveFertilization_wechat(fertilization,user,batchArray);
		StringBuffer rr =new StringBuffer();
		for (String s:r ) {
			rr.append(s).append(",");
		}
		String rrr = rr.deleteCharAt(rr.length()-1).toString();
		JSONObject content = new JSONObject();
		content.put("guid",rrr);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/protectSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String protectSave(HttpServletRequest request,Eppo eppo,String selectValue,String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		eppo.setCreatedate(new Date());
		eppo.setOperatorid(user.getGuid());
		String[] batchArray = selectValue.split(",");
		String[] r= this.eppoService.batchsaveFertilization_wechat(eppo,user,batchArray);
		StringBuffer rr =new StringBuffer();
		for (String s:r ) {
			rr.append(s).append(",");
		}
		String rrr = rr.deleteCharAt(rr.length()-1).toString();
		JSONObject content = new JSONObject();
		content.put("guid",rrr);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/g4protectSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String g4protectSave(HttpServletRequest request,Plantprotect plantprotect,String selectValue,String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		plantprotect.setCreatedate(new Date());
		plantprotect.setOperatorid(user.getGuid());
		String[] batchArray = selectValue.split(",");
		String[] r= plantprotectService.batchsaveFertilization_wechat(plantprotect,user,batchArray,1);
		StringBuffer rr =new StringBuffer();
		for (String s:r ) {
			rr.append(s).append(",");
		}
		String rrr = rr.deleteCharAt(rr.length()-1).toString();
		JSONObject content = new JSONObject();
		content.put("guid",rrr);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/waterSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String waterSave(HttpServletRequest request, Watering watering, String selectValue, String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		watering.setCreatedate(new Date());
		watering.setOperatorid(user.getGuid());
		String[] batchArray = selectValue.split(",");
		String[] r= wateringService.batchsaveFertilization_wechat(watering,user,batchArray);
		StringBuffer rr =new StringBuffer();
		for (String s:r ) {
			rr.append(s).append(",");
		}
		String rrr = rr.deleteCharAt(rr.length()-1).toString();
		JSONObject content = new JSONObject();
		content.put("guid",rrr);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}

	/**
	 * 瓶苗档案
	 * @param page
	 * @param seedfile
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/filelist.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String filelist(Page page,Seedfile seedfile,HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		seedfile.setVisible("1");
		seedfile.setType("3");
		seedfile.setCompanyid(user.getCompany().getGuid());
		Page pagelist=seedfileService.findpagelist(seedfile, page.getBegin(), page.getPageSize());
		pagelist.setPageNo(page.getPageNo());
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		net.sf.json.JSONObject jsons= net.sf.json.JSONObject.fromObject(pagelist, config);
		JSONObject content = new JSONObject();
		content.put("page", jsons);
		content.put("years", seedfile.getYears());
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	private String createGrade(String level) {
		switch (level){
			case "1":return "";
			case "2":return "G0";
			case "3":return "G1";
			case "4":return "G2";
			case "5":return "G3";
			default:return "G";
		}
	}
	@RequestMapping(value = "/detail.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String detail(Seedfile seedfile,HttpServletRequest request) throws Exception{
		seedfile = seedfileService.getSeedfileByGuid(seedfile);

		Fileralation fileralation = new Fileralation();
		fileralation.setFileid(seedfile.getGuid());
		List<Fileralation> fileralations = fileralationService.find(fileralation);

		Growthrecord growthrecord = new Growthrecord();
		growthrecord.setBatchid(seedfile.getGuid());
		List<Growthrecord> recordList = growthrecordService.find("findContainImages",growthrecord);

		Qualityrecord qualityrecord = new Qualityrecord();
		qualityrecord.setBatchid(seedfile.getGuid());
		List<Qualityrecord> qualityList = qualityrecordService.find("findContainImages",qualityrecord);

		Upimage upimage = new Upimage();
		upimage.setRelatedid(seedfile.getGuid());
		List<Upimage> images = upimageService.find(upimage);

		Fertilization fertilization = new Fertilization();
		fertilization.setSeedfileid(seedfile.getGuid());
		List<Fertilization> manures = fertilizationService.find(fertilization);

		Eppo eppo = new Eppo();
		eppo.setSeedfileid(seedfile.getGuid());
		List<Eppo> plantprotects = eppoService.find(eppo);


		Watering watering = new Watering();
		watering.setSeedfileid(seedfile.getGuid());
		List<Watering> waterings = wateringService.find(watering);

		JSONObject content = new JSONObject();
		content.put("seedfile",seedfile);
		content.put("fileralations",fileralations);
		content.put("recordList",recordList);
		content.put("qualityList",qualityList);
		content.put("images",images);
		content.put("manures",manures);
		content.put("plantprotects",plantprotects);
		content.put("waterings",waterings);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/roomList.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String roomList(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		Greenhouses greenhouses = new Greenhouses();
		greenhouses.setCompanyid(user.getCompany().getGuid());
		List<Greenhouses> lists = greenhousesService.find(greenhouses);
		JSONObject content = new JSONObject();
		content.put("list",lists);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/roomAdd.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String roomAdd(HttpServletRequest request,Greenhouses greenhouses) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		greenhouses.setCompanyid(user.getCompany().getGuid());
		greenhouses.setOperatorid(user.getGuid());
		greenhouses.setCreatedate(Calendar.getInstance().getTime());
		String  guid = greenhousesService.insert(greenhouses);
		JSONObject content = new JSONObject();
		content.put("guid",guid);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content",content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/updateTime.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String updateTime(HttpServletRequest request,String type,String date,String selectValue, String selectName ) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		seedfileService.updateDatetime(selectValue,type,date);
		JSONObject content = new JSONObject();
		content.put("guid","");
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content",content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/growthrecordBatchsave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody String growthrecordBatchsave(HttpServletRequest request, Growthrecord growthrecord,String selectValue,String selectName ) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		Date createDate = growthrecord.getCreatedate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createDate);
		String month = calendar.get(Calendar.MONTH)+1+"";
		String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		growthrecord.setMonth(month);
		growthrecord.setMonth(month);
		growthrecord.setDay(day);
		growthrecord.setCompanyid(user.getCompany().getGuid());
		growthrecord.setUserid(user.getGuid());
		String[] array = selectValue.split(",");
		String[] array2 = selectName.split(",");
		String[] guid = growthrecordService.batchInsertG1(growthrecord,array,array2);
		StringBuffer rr =new StringBuffer();
		for (String s:guid ) {
			rr.append(s).append(",");
		}
		String rrr = rr.deleteCharAt(rr.length()-1).toString();
		JSONObject result = new JSONObject();
		JSONObject content = new JSONObject();
		content.put("guid",rrr);
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping("/test.action")
	public String test(){
		return "index/test";
	}
}
