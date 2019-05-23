package com.ecfund.base.action.publics;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.UpimageService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/upload")
public class UploadAction {
	
	@Autowired
	private UpimageService upimgService;
	
	/**
	 * 
	 * @param relatedid  图片关联id
	 * @param type  图片类型
	 * @param description  图片名称 描述
	 * @param url 上传完成后 跳转 地址
	 * @param model 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/upload.action",method=RequestMethod.GET)
	public String upload(String relatedid,String type,String operate,String description ,String url,Model model,HttpServletRequest request){
		try {
			model.addAttribute("url", url);
			model.addAttribute("relatedid", relatedid);
			model.addAttribute("type", type);
			
			description=URLDecoder.decode(description,"UTF-8");
			
			model.addAttribute("description", description);
			model.addAttribute("operate", operate);
			
			Upimage imag=new Upimage();
			imag.setRelatedid(relatedid);
			imag.setImagetype(type);
			List<Upimage> images=upimgService.find(imag);
			model.addAttribute("images", images);
			
			return "upload/upload";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
		
		
	}
	


}
