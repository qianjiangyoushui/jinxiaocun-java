package com.ecfund.base.util.common;

import java.util.ArrayList;
import java.util.List;

import com.ecfund.base.model.system.TfMenus;
import com.ecfund.base.service.system.TfMenusService;
import com.ecfund.base.util.common.BeanFactory;

public class TfMenusUtils {

	public List<TfMenus> convert(List<TfMenus> parentList, List<TfMenus> allList) {
		// 去除allList中parent为0的项
		for (int i = 0; i < allList.size(); i++) {
			if (allList.get(i).getParent().equals("0")) {
				allList.remove(i);
				i--;
			}
		}
		List<TfMenus> reMenus = new ArrayList<TfMenus>();
		for (TfMenus menu : parentList) {
			menu.setChildren(getChildList(menu, allList));
			reMenus.add(menu);
		}
		return reMenus;
	}

	/**
	 * 指定一个菜单信息，而后获取该菜单下的所有子菜单信息
	 * 
	 * @param parentMenu
	 * @param list
	 * @return
	 */
	private List<TfMenus> getChildList(TfMenus parentMenu, List<TfMenus> list) {
		String guid = parentMenu.getGuid();
		List<TfMenus> childList = new ArrayList<TfMenus>();
		for (TfMenus menu : list) {
			if (menu.getParent().equals(guid)) {
				menu.setChildren(getChildList(menu, list));
				childList.add(menu);
			}
		}
		return childList;
	}

	/**
	 * @author 孙山伟
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TfMenusService tfMenusService = (TfMenusService) BeanFactory
				.getBean("tfMenusService");
		TfMenus menu = new TfMenus();
		List<TfMenus> allList = tfMenusService.find(menu);
		menu.setParent("0");
		List<TfMenus> parentList = tfMenusService.find(menu);
		TfMenusUtils utils = new TfMenusUtils();
	    utils.convert(parentList, allList);
	}

}
