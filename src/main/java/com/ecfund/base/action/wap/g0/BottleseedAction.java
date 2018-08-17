package com.ecfund.base.action.wap.g0;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.JsonUtils;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JsonConfig;
import net.sf.json.JSONObject;
/**
 * 瓶苗G0管理
 * 
 * @author xxl
 *
 */
@Controller
@RequestMapping("/bottleseed")
public class BottleseedAction {

	@Autowired
	private DictionaryService dictService;

	@Autowired
	private SeedfileService seedfileService;

	@Autowired
	private SeedbedService seedbedService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index(String operate,HttpServletRequest request) {
		request.getSession().setAttribute("operate", operate);
		return "G0/index";
	}

	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(Model model) {
		List getSysYear = DateUtil.getSysYear();
		model.addAttribute("getSysYear", getSysYear);
		return "G0/list";
	}

	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	@ResponseBody
	public void page(Page page,Seedfile seedfile,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Users users=(Users)request.getSession().getAttribute("user");
			seedfile.setVisible("1");
			seedfile.setType("2");
			seedfile.setCompanyid(users.getCompany().getGuid());
			Page pagelist=seedfileService.findpagelist(seedfile, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
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

			// 核心苗档案
			Seedfile seedfile = new Seedfile();
			seedfile.setCompanyid(users.getCompany().getGuid());
			seedfile.setType("1");
			List<Seedfile> files = seedfileService.find(seedfile);
			model.addAttribute("files", files);

			// 苗床
			Seedbed seedbed = new Seedbed();
			seedbed.setCompanyid(users.getCompany().getGuid());
			List<Seedbed> seedbeds = seedbedService.find(seedbed);
			model.addAttribute("seedbeds", seedbeds);
			
			
			List getSysYear = DateUtil.getSysYear();
			model.addAttribute("getSysYear", getSysYear);
			
			return "G0/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public String save(Seedfile seedfile,HttpServletRequest request) {
		try {
			//完善数据
			Users users=(Users)request.getSession().getAttribute("user");
			seedfile.setCompanyid(users.getCompany().getGuid());
			seedfile.setType("2");
			seedfile.setVisible("1");//可见
			seedfile.setCreatedate(new Date(System.currentTimeMillis()));
			seedfile.setOperatorid(users.getGuid());
			StringBuffer sb = new StringBuffer();
			sb.append(seedfile.getYears()).append("-").append(seedfile.getStubble()).append("-").append(seedfile.getVariety()).append("-").append(createGrade(seedfile.getLevel()));
			seedfile.setBatch(sb.toString());
			seedfileService.insert(seedfile);
			
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
	/**
	 * 
	 * @param seedfileid
	 * @param operate  访问来源标记，返回按钮用
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/detail.action",method=RequestMethod.GET)
	public String detail(String seedfileid,String operate,Model model,HttpServletRequest request){
		try {
			String url=request.getRequestURI();
			url=url.substring(1, url.length());
			
			url=url+"?seedfileid="+seedfileid;
			model.addAttribute("url", url);
			
			Seedfile seedfile=new Seedfile();
			seedfile.setGuid(seedfileid);
			seedfile=seedfileService.findg0(seedfile);
			model.addAttribute("seedfile", seedfile);
			model.addAttribute("operate", operate);
			request.getSession().setAttribute("g0guid", seedfileid);
			return "G0/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value="/edit.action",method=RequestMethod.GET)
	public String edit(String type,String operate,Seedfile seedfile,Model model,HttpServletRequest request){
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
				seedfiles.setType("1");
				List<Seedfile> files = seedfileService.find(seedfiles);
				model.addAttribute("files", files);
			}
			
			if(type.equals("3")){//修改扩繁级别
				Dictionary dict3 = new Dictionary();
				dict3.setBelongsid("3");
				dict3.setKeyvalue(seedfile.getLevel());
				dict3=dictService.view(dict3);
				seedfile.setLevels(dict3);
				
				Dictionary dict2 = new Dictionary();
				dict2.setBelongsid("3");// 获取扩繁级别
				List<Dictionary> level = dictService.find(dict2);
				model.addAttribute("level", level);
			}
			
			if(type.equals("4")){//修改苗床
				Seedbed bed = new Seedbed();
				bed.setGuid(seedfile.getPlaceid());
				bed=seedbedService.view(bed);
				seedfile.setSeedbed(bed);
				
				// 苗床
				Seedbed seedbed = new Seedbed();
				seedbed.setCompanyid(users.getCompany().getGuid());
				List<Seedbed> seedbeds = seedbedService.find(seedbed);
				model.addAttribute("seedbeds", seedbeds);
			}

			if(type.equals("5")){
				List getSysYear = DateUtil.getSysYear();
				model.addAttribute("getSysYear", getSysYear);
			}
			model.addAttribute("operate", operate);
			model.addAttribute("seedfile", seedfile);
			return "G0/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	
	@RequestMapping(value="/update.action",method=RequestMethod.POST)
	public String update(Seedfile seedfile,String operate,String remark,HttpServletRequest request,Model model){
		try {
			//获取当前登录用户
			Users users=(Users)request.getSession().getAttribute("user");
			
			seedfileService.updateInfo(seedfile, remark, users);
			model.addAttribute("operate", operate);
			model.addAttribute("seedfileid", seedfile.getGuid());
			return "redirect:detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
	
	@RequestMapping(value="/check.action",method=RequestMethod.POST)
	@ResponseBody
	public void check(String batch,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			//获取当前登录用户
			Users users=(Users)request.getSession().getAttribute("user");
			
			Seedfile seed=new Seedfile();
			seed.setBatch(batch);
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
}
