package com.ecfund.base.action.wap.eliteG1;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.eliteG1.Aphid;
import com.ecfund.base.model.eliteG1.Yieldtest;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.YieldtestService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

/**
 * 测产
 * 
 * @author wf
 *
 */

@Controller
@RequestMapping("/yieldtest")
public class YieldtestAction {
	
	@Autowired
	private YieldtestService yieldtestService;
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(HttpServletRequest request){
		try {
			String seedfileid = request.getParameter("seedfileid");
			Yieldtest yieldtest = (Yieldtest) DTOBuilder.getDTO(request,Yieldtest.class);
			yieldtest = this.yieldtestService.findSum(yieldtest);
			request.setAttribute("yieldtest", yieldtest);
			request.setAttribute("seedfileid", seedfileid);
			
			String operate = request.getParameter("operate");
			request.setAttribute("operate", operate);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "G1/yieldtest_list";
	}
	
	
	
	@RequestMapping(value = "/pageList.action", method = RequestMethod.POST)
	@ResponseBody
	public void pageList(Page page,String seedfileid,Yieldtest yieldtest,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Page pagelist=yieldtestService.find(yieldtest, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
			json.put("page", jsons);
		}catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}
	
	
	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(HttpServletRequest request){
		Yieldtest yieldtest = (Yieldtest) DTOBuilder.getDTO(request,Yieldtest.class);
		request.setAttribute("yieldtest", yieldtest);
		return "G1/yieldtest_add";
	}
	
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	public void save(HttpServletRequest request,Yieldtest yieldtest,PrintWriter out){
		JSONObject json=new  JSONObject();
		try {
			Users users = (Users) request.getSession().getAttribute("user");
			yieldtest.setCreatedate(new Date());
			yieldtest.setOperatorid(users.getGuid());
			
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			this.yieldtestService.saveYieldtest(yieldtest, users, imageids);
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
