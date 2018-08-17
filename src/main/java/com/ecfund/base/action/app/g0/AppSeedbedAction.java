package com.ecfund.base.action.app.g0;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/app_seedbed")
public class AppSeedbedAction {

	@Autowired
	private SeedbedService seedbedService;

	@Autowired
	private UsersService userService;
	
	@Autowired
	private SeedfileService seedfileService;
	
	@Autowired
	private DictionaryService dictService;
	

	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	@ResponseBody
	public void save(Seedbed seedbed, HttpServletRequest request, PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			// 完善数据
			Users user = new Users();
			user.setGuid(request.getParameter("userid"));
			user = userService.view(user);

			seedbed.setCreatedate(new Date(System.currentTimeMillis()));
			seedbed.setCompanyid(user.getCompany().getGuid());
			seedbed.setOperatorid(user.getGuid());

			seedbedService.insert(seedbed);

			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

	/**
	 * 苗床详细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail.action", method = RequestMethod.POST)
	@ResponseBody
	public void detail(String seedbedid,PrintWriter out) {
		JSONObject json = new JSONObject();
		try {
			Seedbed seedbed=new Seedbed();
			seedbed.setGuid(seedbedid);
			seedbed=seedbedService.findDetail(seedbed);
			
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(seedbed, config);
			
			json.put("data", jsons);
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	@ResponseBody
	public void page(Page page,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			//获取当前登录用户
			Users users=new Users();
			users.setGuid(request.getParameter("userid"));
			users=userService.view(users);
			
			Seedbed seedbed=new Seedbed();
			seedbed.setCompanyid(users.getCompany().getGuid());
			
			Page pagelist=seedbedService.getPagelist(seedbed, page);
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
			
			Dictionary dict = new Dictionary();
			dict.setBelongsid("2");// 获取品种
			List<Dictionary> variety = dictService.find(dict);


			Dictionary dict2 = new Dictionary();
			dict2.setBelongsid("3");// 获取扩繁级别
			List<Dictionary> level = dictService.find(dict2);

			// 核心苗档案
			Seedfile seedfiles = new Seedfile();
			seedfiles.setCompanyid(users.getCompany().getGuid());
			seedfiles.setType("1");
			List<Seedfile> files = seedfileService.find(seedfiles);

			// 苗床
			Seedbed seedbeds = new Seedbed();
			seedbeds.setCompanyid(users.getCompany().getGuid());
			List<Seedbed> seedbedss = seedbedService.find(seedbeds);
			
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("page", jsons);
			map.put("years", DateUtil.getSysYear());
			map.put("variety", JSONArray.fromObject(variety, config));
			map.put("level", JSONArray.fromObject(level, config));
			map.put("files", JSONArray.fromObject(files, config));
			map.put("seedbeds", JSONArray.fromObject(seedbedss, config));
			
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
}
