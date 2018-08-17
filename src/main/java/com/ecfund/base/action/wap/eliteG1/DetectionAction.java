package com.ecfund.base.action.wap.eliteG1;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.service.publics.UpimageService;

/**
 * 
 * 检测报告
 * 
 * @author wf
 *
 */
@Controller
@RequestMapping("/detection")
public class DetectionAction {
	
	@Autowired
	private UpimageService upimgService;
	
	
	@RequestMapping(value = "/detectionPage.action", method = RequestMethod.GET)
	public String outDetetion(Model model,HttpServletRequest request) throws Exception{
		String url = request.getParameter("url");
		String relatedid = request.getParameter("relatedid");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		description=URLDecoder.decode(description,"UTF-8");
		String operate = request.getParameter("operate");
		request.setAttribute("operate", operate);
		try {
			model.addAttribute("url", url);
			model.addAttribute("relatedid", relatedid);
			model.addAttribute("type", type);
			model.addAttribute("description", description);
			
			Upimage imag=new Upimage();
			imag.setRelatedid(relatedid);
			imag.setImagetype(type);
			List<Upimage> images=upimgService.find(imag);
			model.addAttribute("images", images);
			return "G1/detection";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
		
	}

}
