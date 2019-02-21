package com.ecfund.base.util.common;

import java.io.*;
import java.util.Properties;

public class CommonResource {
   
    //FIXME: 修改相对路径
    private static String resouceFile = "/systemConfig.properties";
    private static Properties messageData;

    static {
        BufferedReader br = null;
        messageData = new Properties();
        try {
            InputStream is = CommonResource.class.getResource(resouceFile).openStream();

            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            messageData.load(br);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public  static InputStream getResource(String resouceFilePath){
    	try {
			return CommonResource.class.getResource(resouceFilePath).openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }

    public static String getKey(String code) {
        return (String)messageData.get(code);
    }

}
