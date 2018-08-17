package com.ecfund.base.action.wap.seedfile;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Message;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.publics.Logs;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.publics.LogsService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.DTOBuilder;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.JSONUtils;

@Controller
@RequestMapping("/seedfile")
public class SeedfileAction{

	@Autowired
	private LogsService logsService;
	@Autowired
	private SeedfileService seedfileService;
	@Autowired
	private UpimageService upimageService;
	@Autowired
	private DictionaryService dictionaryService;
	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list(Page page,PrintWriter out,Seedfile seedfile, HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			model.addAttribute("getSysYear", DateUtil.getSysYear());
			model.addAttribute("seedfile", seedfile);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "seedfile/list";
	}

	@RequestMapping(value = "/listAjax.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void listAjax(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		Message message=new Message();
		try {
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=getSeedfilePara(request,seedfile);
			Page listpage =seedfileService.findhxm(seedfile,0, Integer.MAX_VALUE);
			message.sendMsg(response, message.getJsonWeb(listpage,Page.class));
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
	@RequestMapping(value = "/batchlist.action", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public void  batchlist(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		Message message=new Message();
		try {
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			Users user=(Users)request.getSession().getAttribute("user");
			seedfile.setCompanyid(user.getCompany().getGuid());
			Page listpage =seedfileService.findhxm(seedfile,0, Integer.MAX_VALUE);
			StringBuffer sb = new StringBuffer();
			JSONArray jsa = new JSONArray();
			for (Object  file:listpage.getRows() ) {
				Seedfile s = (Seedfile) file;
				Item item = new Item(s.getBatch(),s.getGuid());
				jsa.add(item);
			}
			message.sendMsg(response, jsa.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
			message.setFlag(Message.FALSE);
			message.sendMsg(response, message.getJsonOne());
		}
	}
	@RequestMapping(value = "/add.action", method = RequestMethod.GET)
	public String add(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		Dictionary dictionary =new  Dictionary();
		dictionary.setBelongsid("2");
		List<Dictionary> dictlist=dictionaryService.find(dictionary);
		List list=new ArrayList();
		for (int i = 0; i < dictlist.size(); i++) {
			Map map=new HashMap();
			map.put("title", dictlist.get(i).getBelongsname());
			map.put("value",dictlist.get(i).getKeyvalue());
			list.add(map);
		}
		model.addAttribute("dictionary",JSONUtils.fromArray(list));
		model.addAttribute("getSysYear", JSONUtils.fromArray(DateUtil.getFrontYear()));
		return "seedfile/add";
	}
	
	@RequestMapping(value = "/edit.action", method = RequestMethod.POST)
	
	public String edit(Page page,Seedfile seedfile,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			Dictionary dictionary =new  Dictionary();
			dictionary.setBelongsid("2");
			List<Dictionary> dictlist=dictionaryService.find(dictionary);
			List list=new ArrayList();
			for (int i = 0; i < dictlist.size(); i++) {
				Map map=new HashMap();
				map.put("title", dictlist.get(i).getBelongsname());
				map.put("value",dictlist.get(i).getKeyvalue());
				list.add(map);
			}
			
			if(StringUtils.isNotBlank(seedfile.getVariety())){
				Dictionary variety =new  Dictionary();
				variety.setBelongsid("2");
				variety.setKeyvalue(seedfile.getVariety());
				variety=dictionaryService.view(variety);
				model.addAttribute("variety", variety);
			}
			model.addAttribute("seedfile", seedfile);
			model.addAttribute("dictionary",JSONUtils.fromArray(list));
			model.addAttribute("getSysYear", JSONUtils.fromArray(DateUtil.getFrontYear()));
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "seedfile/edit";
	}
//	@RequestMapping(value = "/imgedit.action", method = RequestMethod.GET)
//	
//	public String imgedit(Page page,Seedfile seedfile,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
//		try {
//			Upimage upimage=new Upimage();
//			upimage.setRelatedid(seedfile.getGuid());
//			List listimage=upimageService.find(upimage);
//			model.addAttribute("listimage", listimage);
//			model.addAttribute("dictionary","[]");
//			model.addAttribute("getSysYear", "[]");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "error/error";
//		}
//		return "seedfile/edit";
//	}

	@RequestMapping(value = "/save.action", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public void  save(Page page,Seedfile seedfile,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		JSONObject json=new  JSONObject();
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			seedfile=getSeedfile(request,seedfile);
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			seedfileService.save(seedfile, user, imageids);
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

	
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
			seedfile=seedfileService.view("findhxm",seedfile);
			model.addAttribute("seedfile", seedfile);
			
			String url=request.getRequestURI();
			url=url.substring(1, url.length());
			
			url=url+"?guid="+seedfile.getGuid();
			model.addAttribute("url", url);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		return "seedfile/detail";
	}
	
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	public String update(String objtype,String objtypename,Page page,PrintWriter out, HttpServletRequest request, HttpServletResponse response,Model model) {
		try {
			Users user = (Users) request.getSession().getAttribute("user");
			Seedfile seedfile=(Seedfile) DTOBuilder.getDTO(request,Seedfile.class);
//			seedfileService.update(seedfile);
//			Logs log=new Logs();
//			log.setDescription(objtypename);
//			log.setRelatedid(seedfile.getGuid());
//			log.setUsername(user.getUsername());
//			log.setOperatorid(user.getGuid());
//			log.setOperatedate(new Date());
			seedfileService.updateInfo(seedfile, objtypename, user);
			model.addAttribute("guid", seedfile.getGuid());
			return "redirect:detail.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	
	public Seedfile getSeedfile(HttpServletRequest request,Seedfile seedfile){
		Users user=(Users)request.getSession().getAttribute("user");
		seedfile.setOperatorid(user.getGuid());
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setCreatedate(new Date());
		seedfile.setLevel("1");
		seedfile.setType("1");
		seedfile.setVisible("1");
		return  seedfile;
	}
	public Seedfile getSeedfilePara(HttpServletRequest request,Seedfile seedfile){
		Users user=(Users)request.getSession().getAttribute("user");
		seedfile.setCompanyid(user.getCompany().getGuid());
		seedfile.setLevel("1");
		seedfile.setType("1");
		seedfile.setVisible("1");
//		if(DateUtil.isNullOrEmpty(seedfile.getYears()) ){
//			seedfile.setYears(Calendar.getInstance().get(Calendar.YEAR));
//		}
		return  seedfile;
	}
	class Item{

		String title="";
		String value="";
		public Item(){}

		public Item(String tittle, String value) {
			this.title = tittle;
			this.value = value;
		}
		public String getObject(){
			StringBuffer sb = new StringBuffer("");
			sb.append("{title:").append("\"").append(this.getTitle()).append("\"").append(",").append("value:").append("\"").append(this.getValue()).append("\"").append("}");
			return sb.toString();
		}

		public String getTitle() {
			return title;
		}

		public void setTittle(String tittle) {
			this.title = tittle;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
