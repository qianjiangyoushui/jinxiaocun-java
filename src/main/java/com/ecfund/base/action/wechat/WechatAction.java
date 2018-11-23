package com.ecfund.base.action.wechat;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.publics.Token;
import com.ecfund.base.model.users.Company;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.TokenService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.service.wechat.SessionInfoService;
import com.ecfund.base.util.common.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 设置
 * @author xxl
 *
 */
@Controller
@RequestMapping("/wechat")
public class WechatAction {

	@Autowired
	private UsersService userService;
	@Autowired
	private SessionInfoService sessionInfoService;
	@Autowired
	private TokenService tokenService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index() {
		return "setup/index";
	}

	@RequestMapping(value = "/login.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String login(HttpServletRequest request) throws Exception{
		String code = request.getHeader(Constants.WX_HEADER_CODE);
		String userName = request.getParameter("userName");
		String passwd= request.getParameter("passwd");
		String content = sessionInfoService.login(code,"1",userName,passwd);
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content",JSONObject.parseObject(content));

//		String result = "";
		return result.toJSONString();
	}

	@RequestMapping("/binding.action")
	public @ResponseBody
	String binding(HttpServletRequest request,String openid) throws Exception{
		Users user = userService.findByOpenid(openid);
		JSONObject result = new JSONObject();
		if(user!=null){
			JSONObject content = new JSONObject();
			content.put("login","1");
			result.put("success",true);
			result.put("content",content.toJSONString());
		}else{
			JSONObject error = new JSONObject();
			error.put("login","0");
			result.put("success",false);
			result.put("error",error.toJSONString());
		}
		return result.toJSONString();
	}
	@RequestMapping("/test.action")
	public @ResponseBody
	String test(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		System.out.println(skey);
		JSONObject result = new JSONObject();
		return result.toJSONString();
	}
	@RequestMapping(value = "/doregist.action", method = RequestMethod.POST)
	public @ResponseBody
	String doregist(Users users, Company company,String telphone,String type,String code, String address, Model model) {
		JSONObject result = new JSONObject();
		try {
			Token token = new Token();
			token.setPhone(telphone);
			token.setStatus(1);
			token.setType(type);
			token.setCode(code);
			token = tokenService.view(token);
			JSONObject content = new JSONObject();
			if (token != null) {
				long now = System.currentTimeMillis();
				long sendtime = token.getSenddate().getTime();
				long s = (now - sendtime) / 1000;
				if (s> 60) {
					content.put("msg", "shixiao");//验证码失效，超时
					result.put("success",false);
				} else {
					users.setRegistdate(new Date(System.currentTimeMillis()));
					users.setPassword(MD5Utils.encryString(users.getPassword()));
					String[] adds=address.split(",");
					company.setProvince(adds[0]);
					company.setCity(adds[1]);
					company.setArea(adds[2]);
					company.setRegistdate(new Date(System.currentTimeMillis()));
					company.setStatus("1");
					String guid = userService.regist(users, company);
					content.put("guid",guid);
					content.put("msg", "ok");//验证通过
					result.put("success",true);
				}
				// 验证完成后 删除记录
				Token tok = new Token();
				tok.setPhone(telphone);
				tok.setType(type);
				tokenService.del(tok);
			} else {
				result.put("success",false);
				content.put("msg", "fail");//验证码错误
			}
			//完善数据

			result.put("content",content);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "系统错误,请联系管理员！");
			JSONObject error = new JSONObject();
			error.put("login","0");
			result.put("success",false);
			result.put("error",error.toJSONString());
		}
	return result.toJSONString();
	}

}
