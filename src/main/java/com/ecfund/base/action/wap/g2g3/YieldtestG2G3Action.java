package com.ecfund.base.action.wap.g2g3;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.node.BigIntegerNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.eliteG1.Yieldtest;
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.YieldtestService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.g2g3.FarmService;
import com.ecfund.base.service.g2g3.PlotsService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import freemarker.template.utility.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/g2g3/yieldtest")
public class YieldtestG2G3Action {


	@Autowired
	private DictionaryService dictService;

	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private YieldtestService yieldtestService;

	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(String type,Model model,Seedfile seedfile)  {
		Yieldtest yieldtest=new Yieldtest();
		yieldtest.setSeedfileid(seedfile.getGuid());
		try {
			Map map = (Map) yieldtestService.view("findcount",yieldtest);
			model.addAttribute("mapcount", map);
			seedfile=seedfileService.view(seedfile);
			model.addAttribute("seedfile", seedfile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "G2G3/yieldtest/list";
	}
	

	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	@ResponseBody
	public void page(Page page,String seedfileid,Yieldtest yieldtest,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Page pagelist=yieldtestService.find(yieldtest, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
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
	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(String type,Model model, HttpServletRequest request,Seedfile seedfile,Yieldtest yieldtest) {
		try {
			model.addAttribute("seedfile", seedfile);
			if(yieldtest.getMunumber()==null||"".equals(yieldtest.getMunumber())){
				yieldtest.setMunumber(new BigDecimal("0.04"));
			}
			model.addAttribute("yieldtest", yieldtest);
			return "G2G3/yieldtest/add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void save(String type,Yieldtest yieldtest,PrintWriter out,HttpServletRequest request,Model model) {
		JSONObject json=new  JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			yieldtestService.save(yieldtest, user, imageids);
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

	
	@RequestMapping(value="/detail.action",method=RequestMethod.GET)
	public String detail(String type,String seedfileid,Model model,HttpServletRequest request){
		try {
			String url=request.getRequestURI();
			url=url.substring(1, url.length());
			
			url=url+"?seedfileid="+seedfileid;
			model.addAttribute("url", url);
			
			Yieldtest yieldtest=new Yieldtest();
			yieldtest.setGuid(seedfileid);
			yieldtest=yieldtestService.view(yieldtest);
			model.addAttribute("yieldtest", yieldtest);
			return "G2G3/yieldtest/detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value="/edit.action",method=RequestMethod.GET)
	public String edit(String type, String edittype,Seedfile seedfile,Model model,HttpServletRequest request){
		try {
			model.addAttribute("type", type);
			Users users = (Users) request.getSession().getAttribute("user");
			//淇敼鍝佺
			if(edittype.equals("1")){
				//鏌ヨ褰撳墠鍝佺
				Dictionary dicts = new Dictionary();
				dicts.setBelongsid("2");//鍝佺
				dicts.setKeyvalue(seedfile.getVariety());
				dicts=dictService.view(dicts);
				seedfile.setVarietys(dicts);
				
				Dictionary dict = new Dictionary();
				dict.setBelongsid("2");// 鑾峰彇鍝佺鍒楄〃
				List<Dictionary> variety = dictService.find(dict);
				model.addAttribute("variety", variety);
			}

			return "G2G3/yieldtest/edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	@RequestMapping(value="/update.action",method=RequestMethod.POST)
	public String update(String type,Seedfile seedfile,String remark,HttpServletRequest request,Model model){
		try {
			//鑾峰彇褰撳墠鐧诲綍鐢ㄦ埛
			Users users=(Users)request.getSession().getAttribute("user");
			seedfileService.updateInfo(seedfile, remark, users);
			model.addAttribute("type", type);
			model.addAttribute("seedfileid", seedfile.getGuid());
			return "redirect:detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
}
