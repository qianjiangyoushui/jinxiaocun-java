package com.ecfund.base.service.publics;

import com.ecfund.base.dao.publics.UpimageDAO;
import com.ecfund.base.model.publics.Upimage;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.ImgCompress;
import com.ecfund.base.util.common.UUIDCreate;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-27 16:50
 * @filename UpimageService.java
 * 
 */

@Service
public class UpimageService extends BaseService<Upimage> {

	@Autowired
	private UpimageDAO upimageDAO;


	@Autowired
	public void setBaseDAO(UpimageDAO upimageDAO) {
		super.setBaseDAO(upimageDAO);
	}


	@Transactional
	public String imagesave(Users users, String files, String date, String type, String desc,String date1) throws Exception {

		Configuration config = new PropertiesConfiguration("systemConfig.properties");
		String filePath = config.getString("savePath") + users.getTelphone() + File.separator;
		String imagesPath = config.getString("upimagesPath");

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		
		
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		// BASE64Decoder 解码 files
		BASE64Decoder decoder = new BASE64Decoder();

		String[] imgs = files.split(",");
		// Base64解码
		byte[] b = decoder.decodeBuffer(imgs[1]);
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {// 调整异常数据
				b[i] += 256;
			}
		}

		String filename = UUIDCreate.get() ;
		OutputStream out = new BufferedOutputStream(new FileOutputStream(filePath + filename+ ".png"));

		out.write(b);
		out.flush();
		out.close();
		
		//保存缩略图
		ImgCompress ic = new ImgCompress( filePath + filename+ ".png", filePath + filename + "-suolue.png" );
		ic.resize(77, 77);
		
		Upimage img = new Upimage();
		img.setOperatorid(users.getGuid());
		if(date1 != null && !"".equals(date1)){
			img.setUploaddate(sdf.parse(date1.toString()));
		}else{
			img.setUploaddate(new Date(System.currentTimeMillis()));
		}
		img.setUrl(imagesPath + users.getTelphone() + File.separator + filename+"-suolue.png" );
		img.setImagetype(type);
		img.setImagename(desc);
				
		Upimage image = new Upimage();
		image.setOperatorid(users.getGuid());
		if(date1 != null && !"".equals(date1)){
			image.setUploaddate(sdf.parse(date1.toString()));
		}else{
			image.setUploaddate(new Date(System.currentTimeMillis()));
		}
		image.setUrl(imagesPath + users.getTelphone() + File.separator + filename+ ".png");
		image.setImagetype(type);
		image.setImagename(desc);
		// 获取拍摄时间
		if (!StringUtils.isNullOrEmpty(date)) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
			image.setTakedate(format.parse(date));
		}

		String imageid = this.insert(image);
		
		img.setRelatedid(imageid);//缩略图原图关联
		this.insert(img);
		
		return imageid;
	}

	/**
	 * 批量上传图片
	 * @param upimage
	 * @param jsonArray
	 * @return
	 * @throws Exception
	 */
	public String[] batchInsert(Upimage upimage,String[] jsonArray)throws  Exception{
		String[] result = new String[jsonArray.length];
		for (int i = 0; i < jsonArray.length; i++) {
			String guid = jsonArray[i];
			upimage.setRelatedid(guid);
			result[i]= insert(upimage);
		}
		return result;
	}
}