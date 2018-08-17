package com.ecfund.base.action.app.g0;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.JsonUtils;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;
import com.mysql.jdbc.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/app_bottleseed")
public class AppBottleseedAction {
	
	
	@Autowired
	private SeedfileService seedfileService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private DictionaryService dictService;
	
	@Autowired
	private SeedbedService seedbedService;
	
	
	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	@ResponseBody
	public void page(Page page,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			//获取当前登录用户
			Users users=new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			Seedfile seedfile=new Seedfile();
			if(!StringUtils.isNullOrEmpty(request.getParameter("year"))){
				seedfile.setYears(Integer.parseInt(request.getParameter("year")));
			}
			seedfile.setVisible("1");
			seedfile.setType("2");
			seedfile.setCompanyid(users.getCompany().getGuid());
			Page pagelist=seedfileService.findpagelist(seedfile, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("page", jsons);
			map.put("years", DateUtil.getSysYear());
			json.put("data", map);
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}
	
	
	@RequestMapping(value = "/add.action", method = RequestMethod.POST)
	@ResponseBody
	public void add( HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {

			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			
			//获取当前登录用户
			Users users=new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			
			
			Dictionary dict = new Dictionary();
			dict.setBelongsid("2");// 获取品种
			List<Dictionary> variety = dictService.find(dict);


			Dictionary dict2 = new Dictionary();
			dict2.setBelongsid("3");// 获取扩繁级别
			List<Dictionary> level = dictService.find(dict2);

			// 核心苗档案
			Seedfile seedfile = new Seedfile();
			seedfile.setCompanyid(users.getCompany().getGuid());
			seedfile.setType("1");
			List<Seedfile> files = seedfileService.find(seedfile);

			// 苗床
			Seedbed seedbed = new Seedbed();
			seedbed.setCompanyid(users.getCompany().getGuid());
			List<Seedbed> seedbeds = seedbedService.find(seedbed);
			
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("variety", JSONArray.fromObject(variety, config));
			map.put("level", JSONArray.fromObject(level, config));
			map.put("files", JSONArray.fromObject(files, config));
			map.put("seedbeds", JSONArray.fromObject(seedbeds, config));
			map.put("years", DateUtil.getSysYear());
			
			json.put("msg", "ok");
			json.put("data", map);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
		
	}
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	@ResponseBody
	public void save(HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			//完善数据
			//获取当前登录用户
			Users users=new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			
			Seedfile seedfile=(Seedfile)JsonUtils.json2obj(request.getParameter("seedfile"), Seedfile.class);
			
			seedfile.setCompanyid(users.getCompany().getGuid());
			seedfile.setType("2");
			seedfile.setVisible("1");//可见
			seedfile.setCreatedate(new Date(System.currentTimeMillis()));
			seedfile.setOperatorid(users.getGuid());
			
			seedfileService.insert(seedfile);
			
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}
	
	
	@RequestMapping(value="/edit.action",method=RequestMethod.POST)
	@ResponseBody
	public void edit(String type,HttpServletRequest request,PrintWriter out){
		JSONObject json=new JSONObject();
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
		try {
			Users users = new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			
			if(type.equals("1")){//修改品种
				Dictionary dict = new Dictionary();
				dict.setBelongsid("2");// 获取品种列表
				List<Dictionary> variety = dictService.find(dict);
				json.put("data", JSONArray.fromObject(variety,config));
			}
			
			if(type.equals("2")){//修改核心苗来源
				// 核心苗档案
				Seedfile seedfiles = new Seedfile();
				seedfiles.setCompanyid(users.getCompany().getGuid());
				seedfiles.setType("1");
				List<Seedfile> files = seedfileService.find(seedfiles);
				json.put("data", JSONArray.fromObject(files,config));
			}
			
			if(type.equals("3")){//修改扩繁级别
				Dictionary dict2 = new Dictionary();
				dict2.setBelongsid("3");// 获取扩繁级别
				List<Dictionary> level = dictService.find(dict2);
				json.put("data", JSONArray.fromObject(level,config));
			}
			
			if(type.equals("4")){//修改苗床
				// 苗床
				Seedbed seedbed = new Seedbed();
				seedbed.setCompanyid(users.getCompany().getGuid());
				List<Seedbed> seedbeds = seedbedService.find(seedbed);
				json.put("data", JSONArray.fromObject(seedbeds,config));
			}

			if(type.equals("5")){//修改年份
				List getSysYear = DateUtil.getSysYear();
				json.put("data",getSysYear);
			}
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}
	
	
	/**
	 * 
	 * @param seedfile  包含 guid和 所要修改字段值 的json对象
	 * @param remark  修改标记，具体修改了哪儿
	 * @param userid 用户id
	 * @param request
	 * @param out
	 */
	@RequestMapping(value="/update.action",method=RequestMethod.POST)
	@ResponseBody
	public void update(Seedfile seedfile,String remark,String userid,HttpServletRequest request,PrintWriter out){
		JSONObject json=new JSONObject();
		try {
			//获取当前登录用户
			Users users=new Users();
			users.setGuid(userid);
			users=userService.view(users);
			
			seedfileService.updateInfo(seedfile, remark, users);
			
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
		
	}
}
