package com.ecfund.base.action.wechat.g4;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.common.SelectItem;
import com.ecfund.base.model.eliteG1.*;
import com.ecfund.base.model.fertilization.Fertilization;
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.publics.*;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Menus;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.*;
import com.ecfund.base.service.g0.CulturemediumService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.g0.TrainseedService;
import com.ecfund.base.service.g2g3.FarmService;
import com.ecfund.base.service.g2g3.PlotsService;
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
@RequestMapping("/wechat/G4")
public class G4Action {

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
	private ManureService manureService;
	@Autowired
	private PlantprotectService plantprotectService;
	@Autowired
	private IrrigationService irrigationService;

	@Autowired
	private FarmService farmService;
	@Autowired
	private PlotsService plotsService;


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

	@RequestMapping(value = "/delete.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String delete(String guid,HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Seedfile seedfile = new Seedfile();
		seedfile.setGuid(guid);
		seedfile.setVisible("-1");
		seedfileService.update(seedfile);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", "");
		return result.toJSONString();
	}
	@RequestMapping(value = "/update.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String update(Seedfile seedfile,HttpServletRequest request) throws Exception{
		seedfileService.update(seedfile);
		JSONObject content = new JSONObject();
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
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


		// 大棚
		Farm farm = new Farm();
		farm.setCompanyid(user.getCompany().getGuid());
		List<Farm> farms = farmService.find(farm);
		Plots plot = new Plots();
		List<Plots> plots = new ArrayList();
		for (int i = 0; i < farms.size(); i++) {
			plot.setFarmid(farms.get(i).getGuid());
			List listplot = plotsService.find(plot);
			for (int j = 0; j < listplot.size(); j++) {
				String name = ((Plots) listplot.get(j)).getPlotname();
				((Plots) listplot.get(j)).setPlotname(farms.get(i).getFarmname() + "-" + name);
			}
			plots.addAll(listplot);
		}



		JSONObject content = new JSONObject();
		content.put("year",JSONObject.toJSON(selectItemList));
		content.put("variety",JSONObject.toJSON(variety));
		content.put("level",JSONObject.toJSON(level));
		content.put("houses",JSONObject.toJSON(plots));
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
		seedfile.setType("6");
		seedfile.setLevel("6");
		seedfile.setVisible("1");//可见
		seedfile.setCreatedate(new Date(System.currentTimeMillis()));
		seedfile.setOperatorid(user.getGuid());
		StringBuffer sb = new StringBuffer();
		if ("".equals(seedfile.getIsproduction()) || seedfile.getIsproduction() == null) {
			seedfile.setIsproduction("1");
		}
		Plots plots = new Plots();
		plots.setGuid(seedfile.getPlaceid());
		plots = plotsService.findPlots(plots);
		Farm farm = new Farm();
		farm.setGuid(plots.getFarmid());
		farm = farmService.view(farm);
		String grade = createGrade(seedfile.getLevel());
		String batchCode = seedfile.getYears()+"-"+farm.getFarmcode()+"-"+plots.getPlotcode()+"-"+seedfile.getVariety()+"-"+grade;
		seedfile.setBatch(batchCode);
		String guid = seedfileService.insert(seedfile,selectValue,selectName);
		JSONObject content = new JSONObject();
		content.put("guid",guid);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/manureSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String manureSave(HttpServletRequest request, Manure manure,int visible, String selectValue, String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		manure.setCreatedate(new Date());
		manure.setOperatorid(user.getGuid());
		String[] batchArray = selectValue.split(",");
		String[] r= this.manureService.batchsaveFertilization_wechat(manure,user,batchArray,visible);
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
	String protectSave(HttpServletRequest request,int visible, Plantprotect plantprotect, String selectValue, String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		plantprotect.setCreatedate(new Date());
		plantprotect.setOperatorid(user.getGuid());
		String[] batchArray = selectValue.split(",");
		String[] r= this.plantprotectService.batchsaveFertilization_wechat(plantprotect,user,batchArray,visible);
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
	String waterSave(HttpServletRequest request, Irrigation irrigation, int visible,String selectValue, String selectName) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		irrigation.setCreatedate(new Date());
		irrigation.setOperatorid(user.getGuid());
		String[] batchArray = selectValue.split(",");
		String[] r= irrigationService.batchsaveFertilization_wechat(irrigation,user,batchArray,visible);
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
		seedfile.setType("6");
		seedfile.setCompanyid(user.getCompany().getGuid());
		Page pagelist=seedfileService.findpageG2G3list(seedfile, page.getBegin(), page.getPageSize());
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

	@RequestMapping(value = "/batchlist.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String batchlist(Page page,Seedfile seedfile,HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		seedfile.setVisible("1");
		Page pagelist=seedfileService.find(seedfile, page.getBegin(), page.getPageSize());
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
			case "6":return "G4";
			default:return "G";
		}
	}
	@RequestMapping(value = "/detail.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String detail(Seedfile seedfile,HttpServletRequest request) throws Exception{
		seedfile = seedfileService.view("findg2g3",seedfile);

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

		Manure manure = new Manure();
		manure.setSeedfileid(seedfile.getGuid());
		List<Manure> manures = manureService.find(manure);

		Plantprotect plantprotect = new Plantprotect();
		plantprotect.setSeedfileid(seedfile.getGuid());
		List<Plantprotect> plantprotects = plantprotectService.find(plantprotect);


		Irrigation irrigation = new Irrigation();
		irrigation.setSeedfileid(seedfile.getGuid());
		List<Irrigation> waterings = irrigationService.find(irrigation);

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
	@RequestMapping(value = "/singledetail.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String singledetail(Seedfile seedfile,HttpServletRequest request) throws Exception{
		seedfile = seedfileService.view(seedfile);

		JSONObject content = new JSONObject();
		content.put("seedfile",seedfile);
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
	@RequestMapping("/test.action")
	public String test(){
		return "index/test";
	}
}
