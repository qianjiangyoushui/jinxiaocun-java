package com.ecfund.base.action.system;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecfund.base.common.ResultNameBean;
import com.ecfund.base.model.system.TfLoginUser;
import com.ecfund.base.service.system.TfLoginUserService;
import com.ecfund.base.util.common.MD5Utils;


@Controller
@RequestMapping("/syslogin")
public class LoginAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TfLoginUserService tfLoginUserService;

	/**
	 * 跳转到管理员登录页面
	 * @return
	 */
    @RequestMapping(value="/adminLogin.action",method=RequestMethod.GET)
	public String adminLogin(){
		return "/system/adminLogin";
	}
	
	/**
	 * 后台管理员登录
	 * @return
	 */ 
    @RequestMapping(value="/adminEnter.action",method=RequestMethod.POST)
	public String adminEnter(TfLoginUser entity,HttpServletRequest request){
		if (null == entity.getLoginname()|| "".equals(entity.getLoginname().trim())) {
			request.setAttribute("msg", "用户名未输入");
			return "/system/adminLogin";
		}
		//未输入密码
		if (null == entity.getLoginPass()
				|| "".equals(entity.getLoginPass().trim())) {
			request.setAttribute("msg", "密码未输入");
			return "/system/adminLogin";
		}
		try {
			// 检测用户名密码是否匹配
			entity.setLoginPass(MD5Utils.encryString(entity.getLoginPass()));
			TfLoginUser user = new TfLoginUser();
			user.setLoginName(entity.getLoginname());
			user.setLoginPass(entity.getLoginPass());
			user = tfLoginUserService.view(user);
			if (null == user) {
				request.setAttribute("msg", "用户名和密码不匹配");
				return "/system/adminLogin";
			} 
			request.getSession().setAttribute("user", user);
			return "redirect:/system/index.action";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "系统错误1");
			return "/system/adminLogin";
		}
	}
	
	public String logout() {
//		this.session.remove("loginUser");
		return "logout";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String adminLogout() {
//		this.session.remove("loginUser");
		return "loginerror";
	}
	
	

//	网站端

	/**
	 * 网站端登录页面
	 * @return
     */
	public String webLoginpage(){
		return ResultNameBean.JSPPAGE;
	}

	/**
	 * &#x767b;&#x5f55;&#x903b;&#x8f91;
	 * @return
	 */
	public String webLogin(){
		int result =0;
//		result = userService.webLogin(entity,session);
		if(result!=1){
			/**
			 * 调到登录页面
			 */
//			ws.setNamespace("login");
//			ws.setAction("Login");
//			ws.setMethod("webLoginpage");
//			ws.setTip("用户名或者密码错误");
			return ResultNameBean.REDIRECT;
		}else{
			/**
			 * 跳到主页
			 */
			return "webLoginSuccess";
		}
	}
	
	public String webLoginout(){
//		session.remove("loginUser");
		return "webLoginSuccess";
	}


}
