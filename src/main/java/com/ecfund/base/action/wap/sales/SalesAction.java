package com.ecfund.base.action.wap.sales;

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

import com.ecfund.base.model.depot.Depot;
import com.ecfund.base.model.sales.Clients;
import com.ecfund.base.model.sales.Salesrecord;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.depot.DepotService;
import com.ecfund.base.service.sales.ClientsService;
import com.ecfund.base.service.sales.SalesrecordService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;
import com.mysql.jdbc.StringUtils;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * 销售售后
 * @author xxl
 *
 */
@Controller
@RequestMapping("/sales")
public class SalesAction {
	
	@Autowired
	private SalesrecordService salesService;
	
	@Autowired
	private ClientsService clientService;
	
	@Autowired
	private SeedfileService seedfileService;
	
	@Autowired
	private DepotService depotService;
	
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public String index(){
		return "sales/index";
	}
	
	
	@RequestMapping(value="/list.action",method=RequestMethod.GET)
	public String list(String clientid,Model model){
		model.addAttribute("clientid", clientid);
		return "sales/sales_list";
	}

	@RequestMapping(value="/page.action",method=RequestMethod.POST)
	@ResponseBody
	public void page(PrintWriter out,String clientid,HttpServletRequest request,Page page){
		JSONObject json=new JSONObject();
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			Salesrecord sales=new Salesrecord();
			sales.setCompanyid(user.getCompany().getGuid());
			if(!StringUtils.isNullOrEmpty(clientid)){
				sales.setClientid(clientid);
			}
			Page pagelist=salesService.findlist(sales, page);
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));	
			JSONObject jsons=JSONObject.fromObject(pagelist, config);
			
			json.put("page", jsons);
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
	public String add(HttpServletRequest request,String clientid,Model model){
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			
			if(!StringUtils.isNullOrEmpty(clientid)){
				Clients client=new Clients();
				client.setGuid(clientid);
				client=clientService.view(client);
				model.addAttribute("client", client);
			}else{
				//获取客户列表
				Clients client=new Clients();
				client.setCompanyid(user.getCompany().getGuid());
				List<Clients> clients=clientService.find(client);
				model.addAttribute("clients", clients);
			}
			
			
			//查找可销售批次
			Seedfile seed=new Seedfile();
			seed.setCompanyid(user.getCompany().getGuid());
			List<Seedfile> seedfiles=seedfileService.findsales(seed);
			
			//查找仓库
			Depot depot=new Depot();
			depot.setCompanyid(user.getCompany().getGuid());
			List<Depot> depots=depotService.find(depot);
			
			
			model.addAttribute("seedfiles", seedfiles);
			model.addAttribute("depots", depots);
			
			return "sales/sales_add";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	@RequestMapping(value="/edit.action",method=RequestMethod.GET)
	public String edit(HttpServletRequest request,String clientid,String salesid,Model model){
		try {
			Users user=(Users)request.getSession().getAttribute("user");

			if(!StringUtils.isNullOrEmpty(clientid)){
				
			}else{
				//获取客户列表
				Clients client=new Clients();
				client.setCompanyid(user.getCompany().getGuid());
				List<Clients> clients=clientService.find(client);
				model.addAttribute("clients", clients);
			}
				
				
				Salesrecord sales=new Salesrecord();
				sales.setGuid(salesid);
				sales=salesService.view(sales);
				model.addAttribute("sales", sales);
			
				Clients client=new Clients();
				client.setGuid(sales.getClientid());
				client=clientService.view(client);
				model.addAttribute("client", client);
				
				//查找可销售批次
				Seedfile seed=new Seedfile();
				seed.setCompanyid(user.getCompany().getGuid());
				List<Seedfile> seedfiles=seedfileService.findsales(seed);
				
				//查找仓库
				Depot depot=new Depot();
				depot.setCompanyid(user.getCompany().getGuid());
				List<Depot> depots=depotService.find(depot);
				
				model.addAttribute("seedfiles", seedfiles);
				model.addAttribute("depots", depots);
				return "sales/sales_edit";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value="/detail.action",method=RequestMethod.GET)
	public String detail(HttpServletRequest request,String clientid,String salesid,Model model){
		try {
//				Users user=(Users)request.getSession().getAttribute("user");
				Salesrecord salesrecord=new Salesrecord();
				salesrecord.setGuid(salesid);
				salesrecord=salesService.view(salesrecord);
				

				//查找可销售批次
				Seedfile seed=new Seedfile();
				seed.setGuid(salesrecord.getSeedid());
				seed=seedfileService.view(seed);
				
				//查找仓库
				Depot depot=new Depot();
				depot.setGuid(salesrecord.getDepotid());
				depot=depotService.view(depot);
				
				//获取客户
				Clients client=new Clients();
				client.setGuid(salesrecord.getClientid());
				client=clientService.view(client);
				
				model.addAttribute("client", client);
				model.addAttribute("clientid", clientid);
				model.addAttribute("seed", seed);
				model.addAttribute("depot", depot);
				model.addAttribute("salesrecord", salesrecord);
			return "sales/sales_detail";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	
	@RequestMapping(value="/save.action",method=RequestMethod.POST)
	public String save(Salesrecord sales,String clientguid,HttpServletRequest request){
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			sales.setCompanyid(user.getCompany().getGuid());
			sales.setCreatedate(new Date(System.currentTimeMillis()));
			sales.setOperatorid(user.getGuid());
			
			Seedfile seed=new Seedfile();
			seed.setGuid(sales.getSeedid());
			seed=seedfileService.findg0(seed);
			sales.setBatch(seed.getYears()+"年"+seed.getVarietys().getBelongsname()+seed.getLevels().getBelongsname()+sales.getSalesamount()+"吨");
			salesService.insert(sales);
			if(StringUtils.isNullOrEmpty(clientguid)){
				return "redirect:list.action";
			}else{
				return "redirect:list.action?clientid="+clientguid;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	@RequestMapping(value="/update.action",method=RequestMethod.POST)
	public String update(Salesrecord sales,String clientguid,HttpServletRequest request){
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			sales.setCompanyid(user.getCompany().getGuid());
			sales.setCreatedate(new Date(System.currentTimeMillis()));
			sales.setOperatorid(user.getGuid());
			
			Seedfile seed=new Seedfile();
			seed.setGuid(sales.getSeedid());
			seed=seedfileService.findg0(seed);
			sales.setBatch(seed.getYears()+"年"+seed.getVarietys().getBelongsname()+seed.getLevels().getBelongsname()+sales.getSalesamount()+"吨");
			salesService.update(sales);
			if(StringUtils.isNullOrEmpty(clientguid)){
				return "redirect:list.action";
			}else{
				return "redirect:list.action?clientid="+clientguid;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
}
