package com.ecfund.base.action.wap.eliteG1;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.eliteG1.Plantprotect;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.GreenhousesService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

/**
 * 
 * 原原种G1 大棚
 * 
 * @date 2017-07-31 14:52
 * @filename GreenhousesAction.java
 * @author wf 
 * 
 */

@Controller
@RequestMapping("/greenhouses")
public class GreenhousesAction{
	
	@Autowired
	private GreenhousesService greenhousesService;
	
	@Autowired
	private DictionaryService dictService;
	
	@Autowired
	private SeedfileService seedfileService;
	
	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		/*try {
			Greenhouses greenhouses = (Greenhouses) DTOBuilder.getDTO(request,Greenhouses.class);
			Page listpage =greenhousesService.find(greenhouses,page.getBegin(), page.getPageSize());
			model.addAttribute("list", listpage);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}*/
		return "G1/index";
	}
	
	@RequestMapping(value = "/pageGreenhouses.action", method = RequestMethod.POST)
	@ResponseBody
	public void pageGreenhouses(Page page,Seedfile seedfile,HttpServletRequest request,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			Greenhouses greenhouses = (Greenhouses) DTOBuilder.getDTO(request,Greenhouses.class);
			greenhouses.setCompanyid(user.getCompany().getGuid());
			Page listpage =greenhousesService.findlist(greenhouses,page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(listpage, config);
			json.put("page", jsons);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}

	@RequestMapping(value = "/presidentList.action", method = RequestMethod.POST)
	@ResponseBody
	public void presidentList(Page page,Seedfile seedfile,HttpServletRequest request,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			String companyid =(String) request.getSession().getAttribute("companyid");
			seedfile.setCompanyid(companyid);
			seedfile.setLevel("3");
			seedfile.setType("3");
			seedfile.setVisible("1");
			seedfile.setYears(null);
			Page listpage =seedfileService.findg1pagelist(seedfile,page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONObject jsons=JSONObject.fromObject(listpage, config);
			json.put("page", jsons);
			json.put("years", seedfile.getYears());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}

	@RequestMapping(value = "/pageG1Seesfile.action", method = RequestMethod.POST)
	@ResponseBody
	public void pageG1Seesfile(Page page,HttpServletRequest request,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=getSeedfilePara(request,seedfile);
			Page listpage =seedfileService.findg1pagelist(seedfile,page.getBegin(), page.getPageSize());
			listpage.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(listpage, config);
			json.put("page", jsons);
			json.put("years", seedfile.getYears());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}


	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(Page page,PrintWriter out, String operate,HttpServletRequest request, HttpServletResponse response,Model model) {
		/*try {
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=getSeedfilePara(request,seedfile);
			Page listpage =seedfileService.find(seedfile,page.getBegin(), page.getPageSize());
			model.addAttribute("listpage", listpage);
			model.addAttribute("getSysYear", DateUtil.getSysYear());
			model.addAttribute("curryear",seedfile.getYears());
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}*/
		request.getSession().setAttribute("operate", operate);
		List getSysYear = DateUtil.getSysYear();
		model.addAttribute("getSysYear", getSysYear);
		return "G1/list";
	}
	
	
	public Seedfile getSeedfilePara(HttpServletRequest request,Seedfile seedfile){
		Users user=(Users)request.getSession().getAttribute("user");
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setLevel("3");
		seedfile.setType("3");
		seedfile.setVisible("1");
		if(DateUtil.isNullOrEmpty(seedfile.getYears()) ){
			seedfile.setYears(Calendar.getInstance().get(Calendar.YEAR));
		}
		return  seedfile;
	}
	
	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(Model model, HttpServletRequest request) {
		try {

			Users users = (Users) request.getSession().getAttribute("user");

			Dictionary dict = new Dictionary();
			dict.setBelongsid("2");// 获取品种
			List<Dictionary> variety = dictService.find(dict);

			model.addAttribute("variety", variety);

			Dictionary dict2 = new Dictionary();
			dict2.setBelongsid("3");// 获取扩繁级别
			List<Dictionary> level = dictService.find(dict2);
			model.addAttribute("level", level);
			
			Seedfile seedfile = new Seedfile();
			seedfile.setType("2");
			seedfile.setCompanyid(users.getCompany().getGuid());
			List<Seedfile> seedfileList = this.seedfileService.find(seedfile);
			model.addAttribute("seedfileList", seedfileList);
			
			Greenhouses greenhouses = new Greenhouses();
			greenhouses.setCompanyid(users.getCompany().getGuid());
			List<Greenhouses> greenhousesList = this.greenhousesService.find(greenhouses);
			model.addAttribute("greenhousesList", greenhousesList);

			List getSysYear = DateUtil.getSysYear();
			model.addAttribute("getSysYear", getSysYear);
			
			return "G1/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	
	
	@RequestMapping(value = "/addHouse.action", method = RequestMethod.GET)
	public String addHouse(Model model, HttpServletRequest request) {
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			return "G1/addHouse";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	
	@RequestMapping(value = "/houseList.action", method = RequestMethod.GET)
	public String houseList(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model){
		/*try {
			Greenhouses greenhouses = (Greenhouses) DTOBuilder.getDTO(request,Greenhouses.class);
			Page listpage =greenhousesService.find(greenhouses,page.getBegin(), page.getPageSize());
			model.addAttribute("list", listpage);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}*/
		return "G1/houseList";
	}
	
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	@ResponseBody
	public void save(Greenhouses greenhouses, HttpServletRequest request,PrintWriter out) {
		JSONObject json=new  JSONObject();
		try {
			// 完善数据
			Users user = (Users) request.getSession().getAttribute("user");
			greenhouses.setCompanyid(user.getCompany().getGuid());
			greenhouses.setCreatedate(new Date(System.currentTimeMillis()));
			greenhouses.setOperatorid(user.getGuid());

			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			greenhousesService.save(greenhouses, user, imageids);
			
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}

	}
	
	
	@RequestMapping(value = "/saveSeedfile.action", method = RequestMethod.POST)
	public String  saveSeesfile(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=getSeedfile(request,seedfile);
			Greenhouses greenhouses = new Greenhouses();
			greenhouses.setGuid(seedfile.getPlaceid());
			greenhouses = greenhousesService.view(greenhouses);
			String grade = createGrade(seedfile.getLevel());
			StringBuffer batchBuff = new StringBuffer();
			batchBuff.append(seedfile.getYears()).append("-").append(greenhouses.getHousename()).append("-").append(seedfile.getVariety()).append("-").append(grade);
			seedfile.setBatch(batchBuff.toString());
			seedfileService.insert(seedfile);
			model.addAttribute("years",seedfile.getYears());
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "redirect:list.action";
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
	public Seedfile getSeedfile(HttpServletRequest request,Seedfile seedfile){
		Users user=(Users)request.getSession().getAttribute("user");
		seedfile.setOperatorid(user.getGuid());
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setCreatedate(new Date());
		//seedfile.setLevel("3");
		seedfile.setType("3");
		seedfile.setVisible("1");
		return  seedfile;
	}
	
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(HttpServletRequest request,String guid,String operate){
		try {
			Greenhouses house = new Greenhouses();
			house.setGuid(guid);
			house = this.greenhousesService.findHouses(house);
			request.setAttribute("house", house);
			request.getSession().setAttribute("operate", operate);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/detail";
	}
	
	@RequestMapping(value = "/housedetail.action", method = RequestMethod.GET)
	public String housedetail(HttpServletRequest request){
		try {
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile =seedfileService.getSeedfileByGuid(seedfile);
			request.setAttribute("seedfile", seedfile);
			
			String url=request.getRequestURI();
			url=url.substring(1, url.length());
			
			url=url+"?guid="+seedfile.getGuid();
			request.setAttribute("url", url);
			
			request.getSession().setAttribute("g1guid", seedfile.getGuid());
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/housedetail";
	}
	
	
	@RequestMapping(value = "/editSeedfile.action", method = RequestMethod.GET)
	public String editSeedfile(String type,Seedfile seedfile,Model model,HttpServletRequest request){
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			if(type.equals("1")){//修改品种
				//查询当前品种
				Dictionary dicts = new Dictionary();
				dicts.setBelongsid("2");//品种
				dicts.setKeyvalue(seedfile.getVariety());
				dicts=dictService.view(dicts);
				seedfile.setVarietys(dicts);
				
				Dictionary dict = new Dictionary();
				dict.setBelongsid("2");// 获取品种列表
				List<Dictionary> variety = dictService.find(dict);
				model.addAttribute("variety", variety);
			}
			
			if(type.equals("2")){//修改核心苗来源
				Seedfile file=new Seedfile();
				file.setGuid(seedfile.getSeedid());
				file=seedfileService.view(file);
				seedfile.setParentseed(file);
				
				
				// 核心苗档案
				Seedfile seedfiles = new Seedfile();
				seedfiles.setCompanyid(users.getCompany().getGuid());
				seedfiles.setType("3");
				List<Seedfile> files = seedfileService.find(seedfiles);
				model.addAttribute("files", files);
			}
			
			if(type.equals("3")){//修改级别
				Dictionary dict3 = new Dictionary();
				dict3.setBelongsid("3");
				dict3.setKeyvalue(seedfile.getLevel());
				dict3=dictService.view(dict3);
				seedfile.setLevels(dict3);
				
				Dictionary dict2 = new Dictionary();
				dict2.setBelongsid("3");// 获取级别
				List<Dictionary> level = dictService.find(dict2);
				model.addAttribute("level", level);
			}
			
			if(type.equals("4")){//修改网棚
				Greenhouses greenhouse = new Greenhouses();
				greenhouse.setGuid(seedfile.getPlaceid());
				greenhouse = this.greenhousesService.view(greenhouse);
				seedfile.setGreenhouses(greenhouse);
				
				// 网棚
				Greenhouses houses = new Greenhouses();
				houses.setCompanyid(users.getCompany().getGuid());
				List<Greenhouses> greenhousesList = this.greenhousesService.find(houses);
				model.addAttribute("greenhousesList", greenhousesList);			
			}

			if(type.equals("5")){
				List getSysYear = DateUtil.getSysYear();
				model.addAttribute("getSysYear", getSysYear);
			}
			
			
			model.addAttribute("seedfile", seedfile);
			model.addAttribute("type", type);
			return "G1/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
	
	
	@RequestMapping(value = "/updateSeedfile.action", method = RequestMethod.POST)
	public String updateSeedfile(Seedfile seedfile,String remark,HttpServletRequest request){
		try{
		//获取当前登录用户
		Users users=(Users)request.getSession().getAttribute("user");
		seedfileService.updateInfo(seedfile, remark, users);
		return "redirect:housedetail.action?guid="+seedfile.getGuid();
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/editGreenhouses.action", method = RequestMethod.GET)
	public String editGreenhouses(String houseid,String operate,Model model){
		try {
			Greenhouses greenhouses = new Greenhouses();
			greenhouses.setGuid(houseid);
			greenhouses = this.greenhousesService.view(greenhouses);
			model.addAttribute("operate", operate);
			model.addAttribute("house", greenhouses);
			return "G1/house_edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value = "/updateGreenhouses.action", method = RequestMethod.POST)
	public void updateGreenhouses(Greenhouses greenhouses, HttpServletRequest request,String operate,String imageid,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			greenhouses.setOperatorid(user.getGuid());
			
			this.greenhousesService.updateGreenhouses(greenhouses, imageid);
			
			request.setAttribute("operate", operate);
			request.setAttribute("houseid", greenhouses.getGuid());
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value = "/checkHouseName.action", method = RequestMethod.POST)
	public void checkHouseName(HttpServletRequest req,PrintWriter out,String housename){
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)req.getSession().getAttribute("user");
			Greenhouses house = new Greenhouses();
			house.setCompanyid(user.getCompany().getGuid());
			house.setHousename(housename);
			
			house = this.greenhousesService.view(house);
			if(house != null){
				json.put("msg", "fail");
			}else{
				json.put("msg", "ok");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

	/***
	 * 批量施肥录入页面
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fertilizationbatchpage.action", method = RequestMethod.GET)
	public String fertilizerbatchpage(String type,HttpServletRequest request,Model model) {
		return "G1/fertilizationbatchpage";
	}

	/**
	 * 批量植保用药录入页面
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/eppobatchpage.action", method = RequestMethod.GET)
	public String plantprotectbatchpage(String type,HttpServletRequest request,Model model) {
		Plantprotect plantprotect = (Plantprotect) DTOBuilder.getDTO(request,Plantprotect.class);
		request.setAttribute("plantprotect", plantprotect);
		Users users = (Users) request.getSession().getAttribute("user");
		request.setAttribute("uploadperson", users.getUsername());
		return "G1/eppobatchpage";
	}

	/**
	 * 批量灌溉录入页面
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/wateringbatchpage.action", method = RequestMethod.GET)
	public String irrigationbatchpage(String type,HttpServletRequest request,Model model) {
		Users users = (Users) request.getSession().getAttribute("user");
		Irrigation irrigation = (Irrigation) DTOBuilder.getDTO(request,Irrigation.class);
		request.setAttribute("irrigation", irrigation);
		request.setAttribute("users", users);
		return "G1/wateringbatchpage";
	}
}