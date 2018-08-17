package com.ecfund.base.action.wap.sales;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.sales.Clients;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.sales.ClientsService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/client")
public class ClientAction {

	@Autowired
	private ClientsService clientService;

	@RequestMapping(value = "/list.action", method = RequestMethod.GET)
	public String list() {
		return "sales/client_list";
	}

	@RequestMapping(value = "/page.action", method = RequestMethod.POST)
	public void page(PrintWriter out,HttpServletRequest request,Page page){
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			Clients client=new Clients();
			client.setCompanyid(user.getCompany().getGuid());
			Page pagelist=clientService.find(client, page.getBegin(), page.getPageSize());
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
	public String add() {
		return "sales/client_add";
	}
	@RequestMapping(value = "/edit.action", method = RequestMethod.GET)
	public String edit(Clients client,Model model) throws Exception {
		client=clientService.view(client);
		model.addAttribute("client", client);
		return "sales/client_edit";
	}
	
	
	@RequestMapping(value = "/save.action", method = RequestMethod.POST)
	@ResponseBody
	public void save(Clients client,String address,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			//完善数据
			String[] strs=address.split(" ");
			client.setProvince(strs[0]);
			client.setCity(strs[1]);
			client.setArea(strs[2]);
			client.setCompanyid(user.getCompany().getGuid());
			client.setCreatedate(new Date(System.currentTimeMillis()));
			client.setOperatorid(user.getGuid());
			
			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
			clientService.saveClient(client,imageids);
			
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
	@RequestMapping(value = "/update.action", method = RequestMethod.POST)
	@ResponseBody
	public void update(Clients client,String address,HttpServletRequest request,PrintWriter out) {
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			//完善数据
			String[] strs=address.split(" ");
			client.setProvince(strs[0]);
			client.setCity(strs[1]);
			client.setArea(strs[2]);
			client.setCompanyid(user.getCompany().getGuid());
			client.setCreatedate(new Date(System.currentTimeMillis()));
			client.setOperatorid(user.getGuid());
			
//			JSONArray imageids=JSONArray.parseArray(request.getParameter("imageids"));
			
//			clientService.saveClient(client,imageids);
			clientService.update(client);
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
	
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public String index(String clientid,Model model){
		try {
			Clients client=new Clients();
			client.setGuid(clientid);
			client=clientService.view(client);
			model.addAttribute("client", client);
			return "sales/client_index";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	
	}
	
	@RequestMapping(value = "/detail.action", method = RequestMethod.GET)
	public String detail(String clientid,Model model) throws Exception {
		Clients client=new Clients();
		client.setGuid(clientid);
		client=clientService.view(client);
		model.addAttribute("client", client);
		return "sales/client_detail";
	}
}
