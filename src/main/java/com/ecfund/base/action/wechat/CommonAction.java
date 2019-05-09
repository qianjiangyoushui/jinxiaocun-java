package com.ecfund.base.action.wechat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ObjectArrayCodec;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Menus;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.publics.GrowthrecordService;
import com.ecfund.base.service.publics.UpimageService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.*;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * 设置
 * @author xxl
 *
 */
@Controller
@RequestMapping("/wechat/common")
public class CommonAction {
	private static final Logger logger = LoggerFactory.getLogger(CommonAction.class);
	@Autowired
	private UsersService userService;

	@Autowired
	private UpimageService upimgService;
	@Autowired
	private GrowthrecordService growthrecordService;
	@Autowired
	private DictionaryService dictService;
	@RequestMapping(value = "/dict.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String list(HttpServletRequest request) throws Exception{
		String key = request.getParameter("key");
		Dictionary dict = new Dictionary();
		dict.setBelongsid(key);
		List<Dictionary> variety = dictService.find(dict);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", JSONObject.toJSON(variety));
		return result.toJSONString();
	}


	@RequestMapping(value = "/upload.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String upload(HttpServletRequest request,@RequestParam("file") CommonsMultipartFile file) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		String guid = request.getParameter("guid");
		Configuration config = new PropertiesConfiguration("systemConfig.properties");
		String filePath = config.getString("savePath") + user.getTelphone() + File.separator;
		File targetPath = new File("filePath");
		if (!targetPath.exists()) {
			targetPath.mkdirs();
		}
		String imagesPath = config.getString("upimagesPath")+ user.getTelphone() + File.separator;
		try {
			Random rand = new Random();
			int code = rand.nextInt(100);
			String fileName = user.getTelphone() + "-" + System.currentTimeMillis() + "-" + code;
			String destPath = filePath + fileName + ".png";
			logger.debug("destPath=" + destPath);

			//真正写到磁盘上
			File imageFile = new File(destPath);
			File fileParent = imageFile.getParentFile();
			if(!fileParent.exists()){
				fileParent.mkdirs();
			}
			OutputStream out = new FileOutputStream(imageFile);
			InputStream in = file.getInputStream();
			logger.info("image"+file.getSize());
			int length = 0;
			byte[] buf = new byte[1024];
			// in.read(buf) 每次读到的数据存放在buf 数组中
			while ((length = in.read(buf)) != -1) {
				//在buf数组中取出数据写到（输出流）磁盘上
				out.write(buf, 0, length);
			}
			in.close();
			out.flush();
			out.close();
			ImgCompress ic = new ImgCompress( filePath + fileName+ ".png", filePath + fileName + "-suolue.png" );
			ic.resize(77, 77);
			Upimage upimage = new Upimage();
			upimage.setUploaddate(Calendar.getInstance().getTime());
			upimage.setUrl(imagesPath+fileName+ ".png");
			upimage.setUrl2(imagesPath+fileName+ "-suolue.png");
			upimage.setRelatedid(guid);
			upimage.setImagename(fileName+ ".png");
			upimage.setOperatorid(user.getGuid());
			upimgService.insert(upimage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", "");
		return result.toJSONString();
	}
	@RequestMapping(value = "/batchupload.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String batchupload(HttpServletRequest request,@RequestParam("file") CommonsMultipartFile file) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		String guid = request.getParameter("guid");
		Configuration config = new PropertiesConfiguration("systemConfig.properties");
		String filePath = config.getString("savePath") + user.getTelphone() + File.separator;
		File targetPath = new File("filePath");
		if (!targetPath.exists()) {
			targetPath.mkdirs();
		}
		String imagesPath = config.getString("upimagesPath")+ user.getTelphone() + File.separator;
		try {
			Random rand = new Random();
			int code = rand.nextInt(100);
			String fileName = user.getTelphone() + "-" + System.currentTimeMillis() + "-" + code ;
			String destPath = filePath + fileName+ ".png";
			//真正写到磁盘上
			File imageFile = new File(destPath);
			File fileParent = imageFile.getParentFile();
			if(!fileParent.exists()){
				fileParent.mkdirs();
			}
			OutputStream out = new FileOutputStream(imageFile);
			InputStream in = file.getInputStream();
			logger.info("image"+file.getSize());
			int length = 0;
			byte[] buf = new byte[1024];
			// in.read(buf) 每次读到的数据存放在buf 数组中
			while ((length = in.read(buf)) != -1) {
				//在buf数组中取出数据写到（输出流）磁盘上
				out.write(buf, 0, length);
			}
			in.close();
			out.flush();
			out.close();
			ImgCompress ic = new ImgCompress( filePath + fileName+ ".png", filePath + fileName + "-suolue.png" );
			ic.resize(77, 77);
			Upimage upimage = new Upimage();
			upimage.setUploaddate(Calendar.getInstance().getTime());
			upimage.setUrl(imagesPath+fileName+ ".png");
			upimage.setUrl2(imagesPath+fileName+ "-suolue.png");
			upimage.setImagename(fileName+ ".png");
			upimage.setOperatorid(user.getGuid());
			String[] array =  guid.split(",");
			upimgService.batchInsert(upimage,array);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", "");
		return result.toJSONString();
	}
	@RequestMapping(value = "/growthrecordSave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody String growthrecordSave(HttpServletRequest request, Growthrecord growthrecord,String selectValue,String selectName ) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		Date createDate = growthrecord.getCreatedate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createDate);
		String month = calendar.get(Calendar.MONTH)+1+"";
		String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		growthrecord.setMonth(month);
		growthrecord.setDay(day);
		growthrecord.setCompanyid(user.getCompany().getGuid());
		growthrecord.setUserid(user.getGuid());
		growthrecord.setVisible(1);
		String guid = growthrecordService.insert(growthrecord);
		JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("guid",guid);
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/growthrecordList.action",produces = "application/json;charset=utf-8")
	public @ResponseBody String growthrecordList(HttpServletRequest request,Growthrecord growthrecord, Page page) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		growthrecord.setCompanyid(user.getCompany().getGuid());
		page.setPageSize(10);
		Page  pageList = growthrecordService.findPagelist(growthrecord,page.getBegin(), page.getPageSize());
		JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("page",pageList);
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@RequestMapping(value = "/growthrecordBatchsave.action",produces = "application/json;charset=utf-8")
	public @ResponseBody String growthrecordBatchsave(HttpServletRequest request, Growthrecord growthrecord,String selectValue,String selectName ) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.view(user);
		Date createDate = growthrecord.getCreatedate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(createDate);
		String month = calendar.get(Calendar.MONTH)+1+"";
		String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
		growthrecord.setMonth(month);
		growthrecord.setMonth(month);
		growthrecord.setDay(day);
		growthrecord.setCompanyid(user.getCompany().getGuid());
		growthrecord.setUserid(user.getGuid());
		String[] array = selectValue.split(",");
		String[] array2 = selectName.split(",");
		String[] guid = growthrecordService.batchInsert(growthrecord,array,array2);
		StringBuffer rr =new StringBuffer();
		for (String s:guid ) {
			rr.append(s).append(",");
		}
		String rrr = rr.deleteCharAt(rr.length()-1).toString();
		JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("guid",rrr);
		result.put("success",true);
		result.put("content", content);
		return result.toJSONString();
	}
	@ResponseBody
	@RequestMapping(value = "uploadFile.action", method = RequestMethod.POST)
	public String uploadFile(HttpServletRequest request, MultipartFile file) {
		JSONObject result = new JSONObject();
		try {
			String wxSessionId = request.getParameter("wxSessionId");
			if (file == null || file.getInputStream() == null) {
				result.put("success",false);
				result.put("erro","图片服务器异常");
				return result.toJSONString();
			}

			String extendType = FileUploadUtil.getExtend(file.getOriginalFilename());
			String uuidName = UUID.randomUUID().toString().replace("-", "");
			String fileName = uuidName+ "." + extendType;
			String currDate = DateUtil.todayFormatString(new Date());
			String imgServerUrl = FileUploadUtil.uploadFile("qa/" + currDate + "/", file.getInputStream(), fileName);
			String outFileName = uuidName+"-suolue."+extendType;
			String suolueImageUrl = FileUploadUtil.ImgCompress("qa/" + currDate + "/",file.getInputStream(),outFileName,77,77);
			result.put("success",true);
			JSONObject content = new JSONObject();
			content.put("url",imgServerUrl);
			content.put("suolueUrl",suolueImageUrl);
			result.put("content",content);
			return result.toJSONString();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.put("success",false);
			result.put("erro",e.getMessage());
			return result.toJSONString();
		}
	}


	@RequestMapping(value="/updatepwd.action",method=RequestMethod.POST,produces = "application/json;charset=utf-8")
	public @ResponseBody String savepwd(String oldpwd,String newpwd1,String guid,HttpServletRequest request){
		JSONObject result = new JSONObject();
		try {
			Users users=new Users();
			users.setGuid(guid);
			users.setPassword(MD5Utils.encryString(oldpwd));
			users=userService.view(users);
			if(users!=null){
				Users u=new Users();
				u.setGuid(guid);
				u.setPassword(MD5Utils.encryString(newpwd1));
				userService.update(u);
				result.put("success",true);
				result.put("content","修改成功");
			}else{
				JSONObject erro = new JSONObject();
				result.put("success",false);
				result.put("erro","原密码输入有误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject erro = new JSONObject();
			result.put("success",false);
			result.put("erro",e.getMessage());
		}
		return result.toJSONString();
	}

}
