package com.ecfund.base.action.index;

import com.ecfund.base.model.publics.Message;
import com.ecfund.base.model.users.Menus;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.MessageService;
import com.ecfund.base.service.users.UsersService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexAction {

	@Autowired
	private UsersService usersService;
	@Autowired
	private MessageService  messageService;

	@RequestMapping(value="/index.action",method=RequestMethod.GET)
	public String index(HttpServletRequest request, Model model)throws  Exception{
		Users user = (Users) request.getSession().getAttribute("user");
		user = usersService.findRole(user);
		List<Roles> roleList = user.getRolesList();
		List<Menus> list = new ArrayList();
		for (Roles role:roleList
				) {
			if("1".equals(role.getDescription()))continue;
			role = usersService.findRoleMenulist(role);
			list.addAll(role.getMenuList());
		}
		Collections.sort(list, new Comparator<Menus>() {
			@Override
			public int compare(Menus o1, Menus o2) {
				if(o1.getSort()>o2.getSort()){
					return 1;
				}else if(o1.getSort()<o2.getSort()){
					return -1;
				}else{
					return 0;
				}
			}
		});
		Message message = new Message();
		message.setUserid(user.getGuid());
		message.setState("待审核");
		List<Message> messageList= messageService.find(message);
		request.getSession().setAttribute("messageCount",messageList==null?0:messageList.size());
		model.addAttribute("menuList",list);
		return "index/index";
	}
	
	@RequestMapping(value="/message.action",method=RequestMethod.GET)
	public String messageList(HttpServletRequest request,Model model)throws Exception{
		Users users = (Users) request.getSession().getAttribute("user");
		Message message = new Message();
		message.setUserid(users.getGuid());
		List<Message> messageList= messageService.find(message);
		model.addAttribute("messageList",messageList);
		return "index/message";
	}
}
