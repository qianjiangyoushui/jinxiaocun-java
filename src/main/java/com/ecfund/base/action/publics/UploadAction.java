package com.ecfund.base.action.publics;

import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.service.publics.UpimageService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

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
	@RequestMapping(value = "/upload.action", method = RequestMethod.GET)
	public String upload(String relatedid, String type, String operate, String description, String url, Model model, HttpServletRequest request) {
		try {
			model.addAttribute("url", url);
			model.addAttribute("relatedid", relatedid);
			model.addAttribute("type", type);
			description = URLDecoder.decode(description, "UTF-8");
			model.addAttribute("description", description);
			model.addAttribute("operate", operate);
			Upimage imag = new Upimage();
			imag.setRelatedid(relatedid);
			imag.setImagetype(type);
			List<Upimage> images = upimgService.find(imag);
			model.addAttribute("images", images);
			return "upload/upload";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}


	@RequestMapping(value = "/upload2.action", method = RequestMethod.GET)
	public void upload2(String relatedid, String type, String operate, String description, String url, Model model, HttpServletRequest request) {
		Configuration cfg = new Configuration(Zone.zone0());
		UploadManager uploadManager = new UploadManager(cfg);
		String accessKey = "EOqBk-8ItK5IGy8D5jxeK4IA-d9-qV7JGsozVja7";
		String secretKey = "2FSm1QXygzJEmLoYBXAdmVBtpVoOSmMLKnmLl2G9";
		String bucket = "jiaxd-20";
//默认不指定key的情况下，以文件内容的hash值作为文件名
		String key = null;
		try {
			byte[] uploadBytes = "hello qiniu cloud".getBytes("utf-8");
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);
			try {
				Response response = uploadManager.put(uploadBytes, key, upToken);
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);
				System.out.println(putRet.hash);
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		} catch (UnsupportedEncodingException ex) {
			//ignore
		}

	}



}
