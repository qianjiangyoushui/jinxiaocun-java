package com.ecfund.base.util.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 发送短信功能
 * @author Administrator
 *
 */
public class MessageUitls {
	
	
	/**
	 * 发送短信功能
	 * @param Mobile 手机号
	 * @param Content 短信内容
	 * @param send_time 时间
	 * @return 发送是否成功 ：大于等于0的数字，发送成功
	 * @throws Exception
	 */
	public static int sendMessage(String mobile,String content,String send_time)throws Exception{
		URL url = null;
		String CorpID = "SY0653";//帐号
		String Pwd = "123456";//密码
		String sign = "【种薯追溯】 ";//绑定签名
		content=sign.concat(content);
		String send_content = URLEncoder.encode(content.replaceAll("<br/>", " "), "GBK");//发送内容
		url = new URL("http://106818.com/WS/Send.aspx?CorpID=" + CorpID + "&Pwd=" + Pwd 
				+ "&Mobile=" + mobile + "&Content=" + send_content + "&Cell=&SendTime=" + send_time);
		BufferedReader in = null;
		int inputLine = -1;
		try {
			//System.out.println("开始发送短信手机号码为 ：" + Mobile);
			in = new BufferedReader(new InputStreamReader(url.openStream()));
			inputLine = new Integer(in.readLine()).intValue();
		} catch (Exception e) {
			System.err.println("网络异常,发送短信失败！");
		}
		//System.out.println("结束发送短信返回值：  "+inputLine);
		return inputLine;
	}
	
}
