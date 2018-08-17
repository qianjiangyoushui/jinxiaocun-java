package com.ecfund.base.service.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.dao.system.TfMenusDAO;
import com.ecfund.base.dao.system.TfRoleMenuDAO;
import com.ecfund.base.model.system.TfMenus;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.TfMenusUtils;
import com.ecfund.base.util.common.TreeUtils;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-08-21 19:58
 * @filename TfMenusService.java
 * @author Hmilyld
 * 
 */

@Service
public class TfMenusService extends BaseService<TfMenus> {

	@Autowired
	private TfMenusDAO tfMenusDAO;
	@Autowired
	TfRoleMenuDAO tfRoleMenuDAO;

	@Autowired
	public void setBaseDAO(TfMenusDAO tfMenusDAO) {
		super.setBaseDAO(tfMenusDAO);
	}

	@Override
	public String delete(TfMenus entity) throws Exception {
		// 删除前检查是否还有下级菜单
		String[] ids = entity.getGuid().split(",");
		if (ids.length == 1) {
			// 检查是否还存在下级菜单
			TfMenus tmp = new TfMenus();
			tmp.setParent(ids[0]);
			tmp = tfMenusDAO.view(tmp);
			if (null != tmp) {
				return "请确认您需要删除的类别没有下级分类！";
			} else {
				tfMenusDAO.delete(entity);
			}
		} else {
			for (String id : ids) {
				TfMenus tmp = new TfMenus();
				tmp.setParent(id);
				tmp = tfMenusDAO.view(tmp);
				if (tmp != null) {
					TfMenus current = new TfMenus();
					current.setGuid(id);
					current = tfMenusDAO.view(current);
					return "请确认您需要删除的类别没有下级分类！[" + current.getName() + "]";
				}
			}
			tfMenusDAO.mulDel(ids);
		}
		return "删除成功";
	}

	/**
	 * 根据角色信息，获取该角色下所拥有的菜单内容
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author HMILYLD
	 */
	public List<TfMenus> findByRole(TfMenus entity) throws Exception {
		List<TfMenus> list = tfMenusDAO.findByRole(entity);
		List<TfMenus> parentList = new ArrayList<TfMenus>();
		// 获取所有顶级菜单信息
		for (TfMenus tmp : list) {
			if ("0".equals(tmp.getParent())) {
				parentList.add(tmp);
			}
		}
		// 组合菜单信息
		TfMenusUtils utils = new TfMenusUtils();
		return utils.convert(parentList, list);
	}

	/**
	 * 通过角色显示菜单信息，已经分配该角色的信息，checkbox前面选中，否则显示不选中
	 * 
	 * @author 孙山伟
	 * 
	 * @param roleGuid
	 * @return
	 * @throws Exception
	 */
	public String checkBoxMenus(String roleGuid) throws Exception {
		TfMenus entity = new TfMenus();
		entity.setRoleGuid(roleGuid);
		List<TfMenus> checkedList = tfMenusDAO.findByRole(entity);
		// 查出来所有已选中的菜单信息
		List<TfMenus> list = new ArrayList<TfMenus>();
		List<TfMenus> listtemp = new ArrayList<TfMenus>();
		String checked = "";
		for (TfMenus tmp : checkedList) {
			checked += (tmp.getGuid() + ",");
		}
		// checked = checked.substring(0, checked.length() - 1);
		// 查出来所有菜单信息
		TfMenus temp = new TfMenus();
		listtemp = tfMenusDAO.find(temp);
		List<TfMenus> parentList = new ArrayList<TfMenus>();
		for (TfMenus menu : listtemp) {
			menu.setChecked(checked.indexOf(menu.getGuid()) > -1);
			if ("0".equals(menu.getParent())) {
				parentList.add(menu);
			}
		}
		TfMenusUtils tfm = new TfMenusUtils();
		list = tfm.convert(parentList, listtemp);
		TreeUtils<TfMenus> utils = new TreeUtils<TfMenus>("guid", "name",
				"leaf", "icon", "children", "checked", new String[] {});
		utils.setLeafType("是");
		return utils.convert(list);
	}

	public String inserNode(TfMenus entity) throws Exception {
		return tfMenusDAO.insert(entity);
	}
	/**
	 * 根据角色guid获取菜单权限列表
	 * @param guids 根据角色guid在tf_role_menu表中获取的菜单guid集合
	 * @return 菜单权限列表
	 */
	public List<TfMenus> findMenuByRoleGuid(List<String> guids) {
		return tfMenusDAO.findMenuByRoleGuid(guids);
	}
}