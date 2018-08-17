package com.ecfund.base.action.wap.depot;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.depot.Accident;
import com.ecfund.base.model.depot.Depot;
import com.ecfund.base.model.depot.Environment;
import com.ecfund.base.model.depot.Outinstorage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.depot.AccidentService;
import com.ecfund.base.service.depot.DepotService;
import com.ecfund.base.service.depot.EnvironmentService;
import com.ecfund.base.service.depot.OutinstorageService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/environment")
public class EnvironmentAction {

	@Autowired
	private DepotService depotService;
	@Autowired
	private EnvironmentService environmentService;
	
	@RequestMapping(value="/environment_index.action",method=RequestMethod.GET)
	public String outin_index(Model model,Depot depot,HttpServletRequest request) throws Exception{
		depot=depotService.view(depot);
		model.addAttribute("depot", depot);
		return "depot/environment_index";
	}
	
	@RequestMapping(value="/environment_page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(String guid,Page page,Environment environment,PrintWriter out,HttpServletRequest request){
		JSONObject json=new JSONObject();
		try {
			Page pagelist=environmentService.find(environment, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
			json.put("page", jsons);
			json.put("month", environment.getRecorddate().getMonth());
			Map<String, Date> map=getFirstday_Lastday_Month(environment.getRecorddate());
			environment.setStartdate(map.get("first"));
			environment.setEnddate(map.get("last"));
			List monthlist=environmentService.findmonthList(environment);
			Map map1=new HashMap();
			map1.put("monthlist", monthlist);
			JSONObject monthlistjsons=JSONObject.fromObject(map1, config);
			json.put("monthlistjsons", monthlistjsons);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value="/environment_add.action",method=RequestMethod.GET)
	public String outin_add(Model model,String type,Depot depot,HttpServletRequest request) throws Exception{
		model.addAttribute("depot", depot);
		return "depot/environment_add";
	}

	
	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void save(PrintWriter out,Depot depot,HttpServletRequest request,Environment environment){
		JSONObject json=new  JSONObject();
		try {
			environment.setCreatedate(new Date());
			environmentService.insert(environment);
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
	
	@RequestMapping(value="/environment_detail.action",method=RequestMethod.GET)
	public String outin_detail(Model model,Environment environment,HttpServletRequest request) throws Exception{
		String url=request.getRequestURI();
		url=url.substring(1, url.length());
		
		url=url+"?guid="+environment.getGuid();
		model.addAttribute("url", url);
		
		environment=environmentService.view(environment);
		model.addAttribute("environment", environment);
		return "depot/environment_detail";
	}
	
	
	 private  Map<String, Date> getFirstday_Lastday_Month(Date date) throws ParseException {
	        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        calendar.add(Calendar.MONTH, 0);
	        Date theDate = calendar.getTime();
	        
	 
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.set(Calendar.DAY_OF_MONTH, 1);
	        String day_first = df.format(gcLast.getTime());
	        StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");
	        day_first = str.toString();

	        
	        calendar.add(Calendar.MONTH, 1);    
	        calendar.set(Calendar.DATE, 1);        
	        calendar.add(Calendar.DATE, -1);    
	        String day_last = df.format(calendar.getTime());
	        StringBuffer endStr = new StringBuffer().append(day_last).append(" 23:59:59");
	        day_last = endStr.toString();

	        Map<String, Date> map = new HashMap<String, Date>();
	        map.put("first",df.parse(day_first) );
	        map.put("last", df.parse(day_last) );
	        return map;
	    }

}
