package com.ecfund.base.util.common;

import com.sshtools.j2ssh.SftpClient;
import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.AuthenticationProtocolState;
import com.sshtools.j2ssh.authentication.PasswordAuthenticationClient;
import com.sshtools.j2ssh.sftp.SftpFile;
import com.sshtools.j2ssh.transport.HostKeyVerification;
import com.sshtools.j2ssh.transport.TransportProtocolException;
import com.sshtools.j2ssh.transport.publickey.SshPublicKey;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {
	private static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
	public static SshClient connect() {
		SshClient client = null;
		int result = -1;
		try {
			client = new SshClient();
			client.connect(CommonResource.getKey("image_server_ip"),
					new HostKeyVerification() {

						@Override
						public boolean verifyHost(String arg0, SshPublicKey arg1)
								throws TransportProtocolException {
							return true;
						}
					});
			PasswordAuthenticationClient auth = new PasswordAuthenticationClient();
			auth.setUsername(CommonResource.getKey("image_server_username"));
			auth.setPassword(CommonResource.getKey("image_server_password"));
			result = client.authenticate(auth);
		} catch (Exception e) {
			log.error("The connection is Exception", e);
			return null;
		}
		if (AuthenticationProtocolState.COMPLETE == result) {
			return client;
		}else{
			return null;
		}
	}
	
	
	
	public static List<FileVO> getDirectory(String path){
		List<FileVO> list =  new ArrayList<FileVO>();
		String rootPath = CommonResource.getKey("image_path");
		String url = CommonResource.getKey("image_url");
		if(StringUtils.isNotBlankAndEmpty(path)){
			 rootPath = CommonResource.getKey("image_path")+path ;
			 url = CommonResource.getKey("image_url") +path ;
		}else {
			rootPath = CommonResource.getKey("image_path") ;
			url = CommonResource.getKey("image_url") ;
		}
		
		SshClient client = null;
		try {
			client = connect();
			if (null != client) {
				SftpClient sc = client.openSftpClient();
				 List<SftpFile> listFiles = sc.ls(rootPath); 
				 for (SftpFile sftpFile : listFiles) {
					 FileVO  fileVO = new FileVO();
					 if(StringUtils.isNotBlankAndEmpty(path)){
						 if(!sftpFile.getFilename().endsWith(".")){
						  fileVO.setFilePath(path+"/"+sftpFile.getFilename());
						  }
					 } else {
						 if(!sftpFile.getFilename().endsWith(".")){
							 fileVO.setFilePath(sftpFile.getFilename());
						 } 
					 }
					
					 //如果是目录
					boolean directory =  sftpFile.isDirectory();
					if(directory){
						fileVO.setType("1");
					}
					//如果是文件
					boolean file =  sftpFile.isFile();
					if(file&&(sftpFile.getFilename().endsWith("jpg")||sftpFile.getFilename().endsWith("png"))){
						 fileVO.setFileUrl(url+"/"+sftpFile.getFilename());
						 fileVO.setType("2");
					}
					if(!sftpFile.getFilename().endsWith(".")){
						list.add(fileVO);
					}
				}
				 
			}
		} catch (IOException e) {
			log.error("The upload happen IOException", e);
		} finally{
			if(client!=null)
				client.disconnect();
			client = null;
		}
		return list;
	}
	public static boolean upload(InputStream inputStream, String path, String fileName) {
		boolean status = false;
		SshClient client = null;
		try {
			client = connect();
			if (null != client) {
				SftpClient sc = client.openSftpClient();
				sc.mkdirs(path);
				sc.put(inputStream, path + fileName);
				status = true;
			}
		} catch (IOException e) {
			log.error("The upload happen IOException", e);
		} finally{
			if(client!=null)
				client.disconnect();
			client = null;
		}
		return status;
	}
	
	
	/** 
	* 删除文件 
	* 
	* @param directory 
	*            要删除文件所在目录 
	* @param deleteFile 
	*            要删除的文件 
	*            
	* @throws Exception      
	*/ 
	public static boolean delete(String directory, String deleteFile) {
		boolean status = false;
		SshClient client = null;
		try {
			client = connect();
			if (null != client) {
				SftpClient sc = client.openSftpClient();
				System.out.println(directory+":"+deleteFile);
				sc.cd(directory);
				sc.rm(deleteFile);
				status = true;
			}
		} catch (IOException e) {
			log.error("The upload happen IOException", e);
		} finally{
			if(client!=null)
				client.disconnect();
			client = null;
		}
		return status;
	}
	
	
	/**
	 * 图片直接上传，不做任何处理
	 * @param type head:头像  comment：评论 
	 *                
	 * @param inputStream
	 * @return
	 */
	public static String uploadFile(String path,InputStream inputStream ,String fileName){
		String rootPath = CommonResource.getKey("image_path")+path ;
		String url = CommonResource.getKey("image_url") +path + fileName;
		 //上传原图
		if(upload(inputStream, rootPath, fileName)){
			return url;
		}else{
			return "";
		}
	}
	
	/**
	 * 图片直接上传，不做任何处理
	 * @param type head:头像  comment：评论 
	 *                
	 * @param inputStream
	 * @return
	 */
	public static String uploadOriginal(String type,InputStream inputStream,String originalName){
		String extendType=getExtend(originalName);
		String currDate=ToolUtil.getCurrDateFormatter();
		String fileName = UUID.randomUUID().toString() + "."+extendType;
		String path = CommonResource.getKey("image_path") +type+"/"+ currDate + "/";
		String url = CommonResource.getKey("image_url") +type+"/" + currDate+ "/" + fileName;
		 //上传原图
		if(upload(inputStream, path, fileName)){
			return url;
		}else{
			return "";
		}
	}
   
	public static String upload(String userId, InputStream inputStream) {
		if (null == inputStream) {
			return "";
		}
		String fileName = UUID.randomUUID().toString() + ".jpg";
		String path = CommonResource.getKey("image_path") + userId + "/";
		String url = CommonResource.getKey("image_url") + "/" + userId + "/" + fileName;
		
	   //上传原图
		if(upload(inputStream, path, fileName)){
			return url;
		}else{
			return "";
		}
		
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i+1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	public static void ImgCompress(String inFileName,String outFileName,int w,int h){
		try{
			File file = new File(inFileName);// 读入文件
			Image img = ImageIO.read(file);      // 构造Image对象
			int width = img.getWidth(null);    // 得到源图宽
			int height = img.getHeight(null);  // 得到源图长
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
			File destFile = new File(outFileName);
			FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
			// 可以正常实现bmp、png、gif转jpg
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(image); // JPEG编码
			ImageIO.setUseCache(false);
			ImageIO.write(image, "JPEG", out);
			out.flush();
			out.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	public static String  ImgCompress(String path, InputStream inputStream, String fileName, int w, int h) {
		String filePath = CommonResource.getKey("image_path")+path+fileName;
		String url = CommonResource.getKey("image_url") +path + fileName;
		try{

			Image img = ImageIO.read(inputStream);      // 构造Image对象
			int width = img.getWidth(null);    // 得到源图宽
			int height = img.getHeight(null);  // 得到源图长
			BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
			File destFile = new File(filePath);
			FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
			// 可以正常实现bmp、png、gif转jpg
//		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//		encoder.encode(image); // JPEG编码
			ImageIO.setUseCache(false);
			ImageIO.write(image, "JPEG", out);
			out.flush();
			out.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		return url;
	}
}