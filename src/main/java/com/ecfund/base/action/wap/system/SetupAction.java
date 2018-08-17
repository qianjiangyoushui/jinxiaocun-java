package com.ecfund.base.action.wap.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.MD5Utils;

/**
 * 设置
 * @author xxl
 *
 */
@Controller
@RequestMapping("/setup")
public class SetupAction {

	@Autowired
	private UsersService userService;
	
	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public String index(){
		return "setup/index";
	}
	
	@RequestMapping(value="/usermsg.action",method=RequestMethod.GET)
	public String usermsg(){
		return "setup/usermsg";
	}
	
	@RequestMapping(value="/updatepwd.action",method=RequestMethod.GET)
	public String updatepwd(){
		return "setup/updatepwd";
	}
	
	@RequestMapping(value="/savepwd.action",method=RequestMethod.POST)
	public String savepwd(String oldpwd,String newpwd1, Model model,HttpServletRequest request){
		try {
			Users user=(Users)request.getSession().getAttribute("user");
			Users users=new Users();
			users.setGuid(user.getGuid());
			users.setPassword(MD5Utils.encryString(oldpwd));
			users=userService.view(users);
			if(users!=null){
				Users u=new Users();
				u.setGuid(user.getGuid());
				u.setPassword(MD5Utils.encryString(newpwd1));
				userService.update(u);
				
				return "setup/index";
			}else{
				model.addAttribute("msg", "原密码输入错误");
				return "setup/updatepwd";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error/error";
		}
	}
	
	@RequestMapping(value="/outLogin.action",method=RequestMethod.GET)
	public String outLogin(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		return "redirect:/index/index.action";
	}
}
