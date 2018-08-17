package com.ecfund.base.action.publics;

import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecfund.base.model.publics.Token;
import com.ecfund.base.service.publics.TokenService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/token")
public class TokenAction {

	@Autowired
	private TokenService tokenService;

	/**
	 * 获取验证码
	 * 
	 * @param out
	 * @param phone 手机号
	 * @param type  验证码类型 （1、注册）
	 */
	@RequestMapping(value = "/get.action", method = RequestMethod.POST)
	@ResponseBody
	public void getToken(PrintWriter out, String telphone, String type) {
		JSONObject json = new JSONObject();
		try {
			Token token = new Token();
			token.setPhone(telphone);
			token.setType(type);
			token = tokenService.view(token);
			
			if (token != null) {
				long now = System.currentTimeMillis();
				long sendtime = token.getSenddate().getTime();
				long s = (now - sendtime) / 1000;
				if (s < 60) {// 距离上次发送60秒后 才能再次发送
					json.put("msg", 60 - s);
					json.put("msg", "fail");
				} else {
					tokenService.getToken(telphone, type);
				}
			} else {
				tokenService.getToken(telphone, type);
			}
			json.put("msg", "ok");//发送成功
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");//系统错误
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

	/**
	 * 验证码校验
	 * 
	 * @param out
	 * @param phone
	 * @param code
	 * @param type
	 */
	@RequestMapping(value = "/check.action", method = RequestMethod.POST)
	@ResponseBody
	public void checkToken(PrintWriter out, String telphone, String code, String type) {
		JSONObject json = new JSONObject();
		try {
			Token token = new Token();
			token.setPhone(telphone);
			token.setStatus(1);
			token.setType(type);
			token.setCode(code);
			token = tokenService.view(token);
			
			if (token != null) {
				long now = System.currentTimeMillis();
				long sendtime = token.getSenddate().getTime();
				long s = (now - sendtime) / 1000;
				if (s> 60) {
					json.put("msg", "shixiao");//验证码失效，超时
				} else {
					json.put("msg", "ok");//验证通过
				}
				// 验证完成后 删除记录
				Token tok = new Token();
				tok.setPhone(telphone);
				tok.setType(type);
				tokenService.del(tok);
			} else {
				json.put("msg", "fail");//验证码错误
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", "error");//系统错误
		} finally {
			out.print(json.toString());
			out.flush();
			out.close();
		}
	}

}
