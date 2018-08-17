package com.ecfund.base.action.wap.g2g3;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ecfund.base.model.eliteG1.Greenhouses;
import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.eliteG1.Plantprotect;
import com.ecfund.base.service.eliteG1.GreenhousesService;
import com.ecfund.base.util.common.DTOBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.g2g3.FarmService;
import com.ecfund.base.service.g2g3.PlotsService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/g2g3")
public class G2AndG3Action {


	@Autowired
	private DictionaryService dictService;

	@Autowired
	private SeedfileService seedfileService;

	@Autowired
	private SeedbedService seedbedService;
	@Autowired
	private FarmService farmService;
	@Autowired
	private PlotsService plotsService;
	@Autowired
	private GreenhousesService greenhousesService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index(String type, Model model, HttpServletRequest request) {
		model.addAttribute("type", type);
		request.getSession().setAttribute("g2g3Type", type);
		return "G2G3/index";
	}

	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(String type, Model model, String years, HttpServletRequest request, String operate) {
		model.addAttribute("type", type);
		model.addAttribute("years", years);
		List getSysYear = DateUtil.getSysYear();
		model.addAttribute("getSysYear", getSysYear);
		request.getSession().setAttribute("operate", operate);
		return "G2G3/list";
	}


	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	@ResponseBody
	public void page(Page page, Seedfile seedfile, HttpServletRequest request, PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			seedfile.setVisible("1");
			seedfile.setType(seedfile.getType());
			seedfile.setCompanyid(users.getCompany().getGuid());
			Page pagelist = seedfileService.findpageG2G3list(seedfile, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			JSONObject jsons = JSONObject.fromObject(pagelist, config);
			json.put("page", jsons);
			json.put("years", seedfile.getYears());
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
			out.println(json.toString());
			out.flush();
			out.close();
		}

	}

	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(String type, Model model, HttpServletRequest request) {
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

			// 核心苗、瓶苗、原种id G0-G3
			Seedfile seedfile = new Seedfile();
			seedfile.setCompanyid(users.getCompany().getGuid());
			if ("4".equals(type)) {
				seedfile.setType("3");
			} else if ("5".equals(type)) {
				seedfile.setType("4");
			}

			List<Seedfile> files = seedfileService.find(seedfile);
			model.addAttribute("files", files);

			// 地块
			Farm farm = new Farm();
			farm.setCompanyid(users.getCompany().getGuid());
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
			model.addAttribute("plots", plots);
			model.addAttribute("type", type);

			model.addAttribute("getSysYear", DateUtil.getSysYear());

			return "G2G3/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public String save(String type, Seedfile seedfile, HttpServletRequest request, Model model) {
		try {
			//完善数据
			Users users = (Users) request.getSession().getAttribute("user");
			seedfile.setCompanyid(users.getCompany().getGuid());
			seedfile.setType(type);
			seedfile.setVisible("1");//可见
			seedfile.setCreatedate(new Date(System.currentTimeMillis()));
			seedfile.setOperatorid(users.getGuid());
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
			seedfileService.insert(seedfile);
			model.addAttribute("type", type);
			model.addAttribute("operate", request.getSession().getAttribute("operate"));
			return "redirect:list.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}


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


	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(String type, String seedfileid, Model model, HttpServletRequest request) {
		try {
			String url = request.getRequestURI();
			url = url.substring(1, url.length());

			url = url + "?seedfileid=" + seedfileid;
			model.addAttribute("url", url);

			Seedfile seedfile = new Seedfile();
			seedfile.setGuid(seedfileid);
			seedfile = seedfileService.view("findg2g3", seedfile);
			model.addAttribute("seedfile", seedfile);
			model.addAttribute("type", type);

			request.getSession().setAttribute("g2g3guid", seedfileid);
			return "G2G3/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/edit.action", method = RequestMethod.GET)
	public String edit(String type, String edittype, Seedfile seedfile, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("type", type);
			Users users = (Users) request.getSession().getAttribute("user");
			//修改品种
			if (edittype.equals("1")) {
				//查询当前品种
				Dictionary dicts = new Dictionary();
				dicts.setBelongsid("2");//品种
				dicts.setKeyvalue(seedfile.getVariety());
				dicts = dictService.view(dicts);
				seedfile.setVarietys(dicts);

				Dictionary dict = new Dictionary();
				dict.setBelongsid("2");// 获取品种列表
				List<Dictionary> variety = dictService.find(dict);
				model.addAttribute("variety", variety);
			}
//			
//			if(edittype.equals("2")){
////				修改核心苗来源
//				Seedfile file=new Seedfile();
//				file.setGuid(seedfile.getSeedid());
//				file=seedfileService.view(file);
//				seedfile.setParentseed(file);
//				
//				
//				// 核心苗档案
//				Seedfile seedfiles = new Seedfile();
//				seedfiles.setCompanyid(users.getCompany().getGuid());
//				seedfiles.setType("1");
//				List<Seedfile> files = seedfileService.find(seedfiles);
//				model.addAttribute("files", files);
//			}

			if (edittype.equals("3")) {
//				修改扩繁级别
				Dictionary dict3 = new Dictionary();
				dict3.setBelongsid("3");
				dict3.setKeyvalue(seedfile.getLevel());
				dict3 = dictService.view(dict3);
				seedfile.setLevels(dict3);

				Dictionary dict2 = new Dictionary();
				dict2.setBelongsid("3");// 获取扩繁级别
				List<Dictionary> level = dictService.find(dict2);
				model.addAttribute("level", level);
			}
			if (edittype.equals("4")) {
////				地块面积
//				seedfile.setMuamount(new BigDecimal(val));;
			}

			if (edittype.equals("5")) {
//				修改地块
				Plots plotsinfo = new Plots();
				plotsinfo.setGuid(seedfile.getPlaceid());
				plotsinfo = plotsService.view(plotsinfo);

//				
				Farm farminfro = new Farm();
				farminfro.setGuid(plotsinfo.getFarmid());
				farminfro = farmService.view(farminfro);
				plotsinfo.setPlotname(farminfro.getFarmname() + "-" + plotsinfo.getPlotname());
				seedfile.setPlots(plotsinfo);
				// 地块
				Farm farm = new Farm();
				farm.setCompanyid(users.getCompany().getGuid());
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
				model.addAttribute("plots", plots);
			}

//停水日期	
			model.addAttribute("edittype", edittype);

			model.addAttribute("seedfile", seedfile);
			return "G2G3/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}


	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public String update(String type, Seedfile seedfile, String remark, HttpServletRequest request, Model model) {
		try {
			//获取当前登录用户
			Users users = (Users) request.getSession().getAttribute("user");
			seedfileService.updateInfo(seedfile, remark, users);
			model.addAttribute("type", type);
			model.addAttribute("seedfileid", seedfile.getGuid());
			return "redirect:detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}

	}
	@RequestMapping(value="/check.action",method=RequestMethod.POST)
	@ResponseBody
	public void check(String year,String palceid,String variety,String level,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			//获取当前登录用户
			Users users=(Users)request.getSession().getAttribute("user");
//			Plots plots = new Plots();
//			plots.setGuid(palceid);
//			plots = plotsService.findPlots(plots);
//			Farm farm = new Farm();
//			farm.setGuid(plots.getFarmid());
//			farm = farmService.view(farm);
//			String grade = createGrade(level);
//			String code = year+"-"+farm.getFarmcode()+"-"+plots.getPlotcode()+"-"+variety+"-"+grade;
			String batchCode = buildBatchCode(year,palceid,variety,level);
			Seedfile seed=new Seedfile();
			seed.setBatch(batchCode);
			seed.setCompanyid(users.getCompany().getGuid());
			seed=seedfileService.view(seed);
			if(seed!=null){
				json.put("msg", "fail");//已存在
			}else{
				json.put("msg", "ok");//不存在
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

	/**
	 * 构建种薯批次编号
	 * @param year
	 * @param palceid
	 * @param variety
	 * @param level
	 * @return
	 * @throws Exception
	 */
	private String buildBatchCode(String year, String palceid, String variety, String level) throws  Exception{
		String batchCode="";
		if("5".equals(level)||"4".equals(level)){
			//原种一级种
			Plots plots = new Plots();
			plots.setGuid(palceid);
			plots = plotsService.findPlots(plots);
			Farm farm = new Farm();
			farm.setGuid(plots.getFarmid());
			farm = farmService.view(farm);
			String grade = createGrade(level);
			batchCode = year+"-"+farm.getFarmcode()+"-"+plots.getPlotcode()+"-"+variety+"-"+grade;
		}else if("3".equals(level)){
			//微型薯G1
			Greenhouses  greenhouses = new Greenhouses();
			greenhouses.setGuid(palceid);
			greenhouses = greenhousesService.view(greenhouses);
			String grade = createGrade(level);
			batchCode = year+"-"+greenhouses.getHousename()+"-"+variety+"-"+grade;
		}else if("2".equals(level)){
			//瓶苗
			String grade = createGrade(level);
			batchCode = year+"-"+palceid+"-"+variety+"-"+grade;
		}else{
			//其他的
		}
		return batchCode;
	}

	/***
	 * 批量施肥录入页面
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/fertilizerbatchpage.action", method = RequestMethod.GET)
	public String fertilizerbatchpage(String type,HttpServletRequest request,Model model) {
		model.addAttribute("type", type);
		return "G2G3/fertilizerbatchpage";
	}

	/**
	 * 批量植保用药录入页面
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/plantprotectbatchpage.action", method = RequestMethod.GET)
	public String plantprotectbatchpage(String type,HttpServletRequest request,Model model) {
		model.addAttribute("type", type);
		Plantprotect plantprotect = (Plantprotect) DTOBuilder.getDTO(request,Plantprotect.class);
		request.setAttribute("plantprotect", plantprotect);
		Users users = (Users) request.getSession().getAttribute("user");
		request.setAttribute("uploadperson", users.getUsername());
		return "G2G3/plantprotectbatchpage";
	}

	/**
	 * 批量灌溉录入页面
	 * @param type
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/irrigationbatchpage.action", method = RequestMethod.GET)
	public String irrigationbatchpage(String type,HttpServletRequest request,Model model) {
		model.addAttribute("type", type);
		Users users = (Users) request.getSession().getAttribute("user");
		Irrigation irrigation = (Irrigation) DTOBuilder.getDTO(request,Irrigation.class);
		request.setAttribute("irrigation", irrigation);
		request.setAttribute("users", users);
		return "G2G3/irrigationbatchpage";
	}
}
