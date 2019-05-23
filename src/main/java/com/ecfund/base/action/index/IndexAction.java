package com.ecfund.base.action.index;

import com.ecfund.base.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexAction {

	@Autowired
	private UsersService usersService;


}
