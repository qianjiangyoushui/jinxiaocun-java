package com.ecfund.base.action.wechat;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.users.Users;
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
}
