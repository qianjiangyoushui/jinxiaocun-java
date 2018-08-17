package com.ecfund.base.action.app.g0;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.model.g0.Medium;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.MediumService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.JsonUtils;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.JSONUtils;

import net.sf.json.JSONObject;

/**
 * 培养基
 * @author xxl
 *
 */
@Controller
@RequestMapping("/app_medium")
public class AppMediumAction {

	@Autowired
	private MediumService mediumService;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private DictionaryService dictService;
	/**
	 * 获取培养基来源
	 * @param out
	 * @param request
	 */
	@RequestMapping(value="/add.action",method=RequestMethod.POST)
	@ResponseBody
	public void add(PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			//获取培养基来源
			Dictionary dict=new Dictionary();
			dict.setBelongsid("4");
			List<Dictionary> come=dictService.find(dict);
			
			json.put("data", JSONUtils.fromArray(come));
			json.put("msg", "ok");
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value="/save.action",method=RequestMethod.POST)
	@ResponseBody
	public void save(String userid,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			
			//获取当前用户
			Users users=new Users();
			users.setGuid(userid);
			users=userService.view(users);
			Medium medium=(Medium)JsonUtils.json2obj(request.getParameter("medium"), Medium.class);
			
			//完善信息
			medium.setOperatorid(users.getGuid());
			medium.setCreatedate(new Date(System.currentTimeMillis()));
			mediumService.insert(medium);
			json.put("msg", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value="/page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(Page page,PrintWriter out,Medium medium){
		JSONObject json=new JSONObject();
		try {
			Page pagelist=mediumService.find(medium, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			json.put("data", pagelist);
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

}
