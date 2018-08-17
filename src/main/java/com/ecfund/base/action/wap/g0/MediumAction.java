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

import com.ecfund.base.model.g0.Medium;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.MediumService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.util.common.Page;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/medium")
public class MediumAction {

	@Autowired
	private MediumService mediumService;
	
	@Autowired
	private DictionaryService dictService;

	@RequestMapping(value="/list.action",method=RequestMethod.GET)
	public String list(Model model,String seedfileid,String operate){
		model.addAttribute("seedfileid", seedfileid);
		model.addAttribute("operate", operate);
		return "G0/medium_list";
	}
	
	@RequestMapping(value="/page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(Page page,PrintWriter out,Medium medium){
		JSONObject json=new JSONObject();
		try {
			Page pagelist=mediumService.find(medium, page.getBegin(), page.getPageSize());
			pagelist.setPageNo(page.getPageNo());
			json.put("page", pagelist);
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");
		}finally{
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping(value="/add.action",method=RequestMethod.GET)
	public String add(Model model,String operate,String seedfileid){
		try {
			//获取培养基来源
			Dictionary dict=new Dictionary();
			dict.setBelongsid("4");
			List<Dictionary> come=dictService.find(dict);
			model.addAttribute("comes", come);
			model.addAttribute("seedfileid", seedfileid);
			
			model.addAttribute("operate", operate);
			return "G0/medium_add";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
		
	}
	
	@RequestMapping(value="/save.action",method=RequestMethod.POST)
	public String save(HttpServletRequest request,String operate,Medium medium,Model model){
		try {
			//完善信息
			//获取当前登录用户
			Users users=(Users)request.getSession().getAttribute("user");
			medium.setOperatorid(users.getGuid());
			medium.setCreatedate(new Date(System.currentTimeMillis()));
			mediumService.savemedium(medium,users);
			
			model.addAttribute("operate", operate);
			model.addAttribute("seedfileid", medium.getSeedfileid());
			
			return "redirect:list.action";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}
	
}
