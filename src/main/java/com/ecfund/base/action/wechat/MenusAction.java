package com.ecfund.base.action.wechat;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.users.Menus;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 设置
 * @author xxl
 *
 */
@Controller
@RequestMapping("/wechat/menu")
public class MenusAction {

	@Autowired
	private UsersService userService;

	@RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String list(HttpServletRequest request) throws Exception{
		String skey = request.getHeader(Constants.WX_HEADER_SKEY);
		Users user = new Users();
		user.setGuid(skey);
		user = userService.findRole(user);
		List<Roles> roleList = user.getRolesList();
		List<Menus> list = new ArrayList();
		for (Roles role:roleList
			 ) {
			if("2".equals(role.getDescription()))continue;
			role = userService.findRoleMenulist(role);
			list.addAll(role.getMenuList());
		}
		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", JSONObject.toJSON(list));
		return result.toJSONString();
	}
	@RequestMapping(value = "/test.action",produces = "application/json;charset=utf-8")
	public @ResponseBody
	String test(HttpServletRequest request) throws Exception{

		JSONObject result = new JSONObject();
		result.put("success",true);
		result.put("content", "贾晓东");
		return result.toJSONString();
	}
}
