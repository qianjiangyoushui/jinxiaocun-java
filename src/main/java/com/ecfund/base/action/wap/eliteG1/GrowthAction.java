package com.ecfund.base.action.wap.eliteG1;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.util.common.DTOBuilder;



@Controller
@RequestMapping("/growth")
public class GrowthAction {
	
	@Autowired
	private UpimageService upimgService;
	
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(String seedfileid, HttpServletRequest request,Model model) throws Exception{
		String url=request.getRequestURI();
		url=url.substring(1, url.length());
		
		url=url+"?seedfileid="+seedfileid;
		request.setAttribute("url", url);
		request.setAttribute("seedfileid", seedfileid);
		String operate = request.getParameter("operate");
		request.setAttribute("operate", operate);
		String relatedid = request.getParameter("relatedid");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Upimage upimage = new Upimage();
		upimage.setRelatedid(seedfileid);
		List<Upimage> list = this.upimgService.find(upimage);
		HashSet<Date> keySet = new HashSet<Date>();
		for (Upimage u:list) {
			keySet.add(u.getUploaddate());
		}
		TreeSet<Date> sorted = new TreeSet<Date>(keySet);
		Map imageMap = new TreeMap();
		Map imageMap2 = new TreeMap();
		for (Date date:sorted) {
			Upimage image= new Upimage();
			image.setUploaddate(date);
			image.setRelatedid(seedfileid);
			List<Upimage> list1 = this.upimgService.find(image);
			List<Upimage> list2 = new ArrayList<Upimage>();
			for (Upimage u:list1 ) {
				Upimage nu = new Upimage();
				nu.setRelatedid(u.getRelatedid());
				nu.setTakedate(u.getTakedate());
				nu.setUploaddate(u.getUploaddate());
				String urlStr = u.getUrl();
				urlStr = urlStr.replace(".png","-suolue.png");
				nu.setUrl(urlStr);
				list2.add(nu);
			}
			imageMap.put(date,list2);
			imageMap2.put(date,list1);
		}
		String mapString = com.alibaba.fastjson.JSONObject.toJSONString(imageMap2);
		model.addAttribute("imageList",imageMap);
		model.addAttribute("resultMap",mapString);
		return "G1/growth_list";
	}
	@RequestMapping(value = "/imageList.action", method = RequestMethod.POST)
	public void imgageList(HttpServletRequest request,PrintWriter out){
		JSONArray json=new JSONArray();
		try {
			String uploaddate = request.getParameter("uploaddate");
			String type = request.getParameter("type");
			String relatedid = request.getParameter("relatedid");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			Upimage upimage = new Upimage();
			upimage.setUploaddate(sdf.parse(uploaddate));
			upimage.setImagetype(type);
			upimage.setRelatedid(relatedid);
			List<Upimage> list = this.upimgService.find(upimage);
			json=JSONArray.fromObject(list);
		} catch (Exception e) {
			e.printStackTrace();
			//json.put("msg", "error");
		}finally{
			out.println(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(String relatedid,String type,String description ,String url, String date1, Model model,HttpServletRequest request){
		try {
			model.addAttribute("url", url);
			model.addAttribute("relatedid", relatedid);
			model.addAttribute("type", type);
			model.addAttribute("description", "生长记录");
			model.addAttribute("date1", date1);
			//description=URLDecoder.decode(description,"UTF-8");
			Upimage imag=new Upimage();
			imag.setRelatedid(relatedid);
			imag.setImagetype(type);
			List<Upimage> images=upimgService.find(imag);
			model.addAttribute("images", images);
			
			return "G1/growth_add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}

}
