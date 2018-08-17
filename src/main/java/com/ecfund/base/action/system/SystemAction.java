package com.ecfund.base.action.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ecfund.base.model.system.TfLoginUser;
import com.ecfund.base.model.system.TfMenus;
import com.ecfund.base.service.system.TfMenusService;

/**
 * 
 * @author xxl
 *
 */
@Controller
@RequestMapping("/system")
public class SystemAction {

	@Autowired
	private TfMenusService tfMenusService;

	@RequestMapping(value = "/index.action", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		try {
			// 获取登录人员角色信息，继而获取该角色所拥有的菜单
			TfLoginUser loginUser = (TfLoginUser) request.getSession().getAttribute("user");
			String roleId = loginUser.getRoleGuid();
			if (null == roleId || "".equals(roleId)) {
				return "/system/system_index";
			}
			// 获取所有顶级菜单
			TfMenus menu = new TfMenus();
			menu.setRoleGuid(roleId);
			List<TfMenus> menuList = tfMenusService.findByRole(menu);
			request.getSession().setAttribute("menuList", menuList);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "系统错误");
			return "/system/adminLogin";
		}
		return "/system/system_index";
	}
}
