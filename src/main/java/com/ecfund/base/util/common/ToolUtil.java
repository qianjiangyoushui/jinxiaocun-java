package com.ecfund.base.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ToolUtil {

	private static final Logger log = LoggerFactory.getLogger(ToolUtil.class);
    private final static String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
    * @Description: 取得文件大小
    * @param  f
    * @return long 
     */
	public static long getFileSizes(File f){
        long s=0;
        try{
        if (f!= null &&f.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(f);
            s= fis.available();
            fis.close();
        } else {
        	return 0L;
        }
        }catch(Exception e){
        	log.error("Get fileSizes failure",e);
        }
        return s;
    }

    /**
     * 序列化
     *
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        	log.error("序列化 failure",e);

        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public static Object unSerialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        	log.error("反序列化  failure",e);
        }
        return null;
    }

    public static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

	/**
	 * 字母还是数字？
	 * @param string
	 * @return
	 */
	public static boolean isEnglishAndNumber(String string){
		return string.matches("^[A-Za-z0-9]+$");
	}
	/**
	 * 字母还是数字?
	 * @param c
	 * @return
	 */
	public static boolean isEnglishAndNumber(char c){
		return c>=97&&c<=122 || c>=65&&c<=90 || c>=48&&c<=57;
	}
	/**
	 * Ascii码?
	 * @param c
	 * @return
	 */
	public static boolean isAscii(char c) {
		return c>=0 && c<=255;
	}
	/**
	 * 英文字符?
	 * @param c
	 * @return
	 */
	public static boolean isEnglish(char c) {
		return c>='a'&&c<='z' || c>='A'&&c<='Z';
	}

    public static boolean deleteFile(String filePath)
    {
        String path = ToolUtil.class.getResource("/").toString();

        int idx = path.indexOf("WEB-INF");
        path = path.substring(6, idx-1);
        File file = new File(path+filePath);
        System.out.println(path+filePath);
        if (file.isFile() && file.exists())
        {
            return file.delete();
        }
        return false;
    }
	/**
	 * 截取string的前size位并在其后附加...
	 * @param string
	 * @param size
	 * @return
	 */
	public static String cutString(String string,int size){
		if(string.length() > size){
			return string.substring(0, size) + "…";
		}else{
			return string;
		}
	}
	/**
	 * 截取string的后几位
	 */
	public static String getStrByLastSize(String string,int size){
		int length = string.length();
		if(length < size){
			return string;
		}else{
			return string.substring(length - size, length);
		}
	}
	
	public static long getNextHour(int hour){
		long result = 0;
		try {
			Calendar time = new GregorianCalendar();
			time.set(Calendar.HOUR_OF_DAY, hour);
			time.set(Calendar.MINUTE, 0); 
			time.set(Calendar.SECOND, 0);
			result = time.getTimeInMillis();
			if(new Date().getTime() > result){
				result = result + 86400000;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		return result;
	}
	/**
	 * 获取要生成的ID
	 * @param dbMaxId
	 * @param prefixion
	 * @param length
	 * @return
	 */
	public static  String getCurrObjectId(String dbMaxId,String prefixion,int length){
		StringBuffer sb=new StringBuffer();
		sb.append(prefixion);
		if(StringUtils.isEmpty(dbMaxId)||dbMaxId==null){
			for (int i = 1; i < length; i++) {
				sb.append("0");
			}
			sb.append("1");
		}else{
			Integer oldMax=Integer.valueOf(dbMaxId.substring(8));
			oldMax++;
			int id_subLength=String.valueOf(oldMax).length();
			for(int i=id_subLength;i<length;i++){
				sb.append("0");	
			}
			sb.append(oldMax);
		}
		return sb.toString();
	}
	/**
	 * 获取当前时间字符串
	 * @return yyyyMMdd
	 */
	public static String getCurrDateFormatter(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date());
	}
	
}
