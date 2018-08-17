package com.ecfund.base.service.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecfund.base.common.ListPageBean;
import com.ecfund.base.dao.system.TfDepartmentDAO;
import com.ecfund.base.dao.system.TfLoginUserDAO;
import com.ecfund.base.dao.system.TfRoleUserDAO;
import com.ecfund.base.dao.system.TfUserDeptDAO;
import com.ecfund.base.model.system.TfDepartment;
import com.ecfund.base.model.system.TfLoginUser;
import com.ecfund.base.model.system.TfRoleUser;
import com.ecfund.base.model.system.TfUserDept;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.MD5Utils;
import com.ecfund.base.util.common.TreeUtils;
import com.ecfund.base.util.common.UUIDCreate;
import com.ecfund.base.util.json.JSONUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2012-09-11 13:35
 * @filename TfLoginUserService.java
 * @author Hmilyld
 * 
 */

@Service
public class TfLoginUserService extends BaseService<TfLoginUser> {
	@Autowired
	private TfLoginUserDAO tfLoginUserDAO;
	@Autowired
	private TfDepartmentDAO tfDepartmentDAO;
	@Autowired
	private TfRoleUserDAO tfRoleUserDAO;
	@Autowired
	private TfUserDeptDAO tfUserDeptDAO;

	@Autowired
	public void setBaseDAO(TfLoginUserDAO tfLoginUserDAO) {
		super.setBaseDAO(tfLoginUserDAO);
	}

	/**
	 * 检查登录名是否重复
	 * 
	 * @param loginNames
	 *            JSON格式的用户名以及GUID，GUID用于区分是新增还是修改操作
	 * @return
	 * @throws Exception
	 */
	public String checkIsRepeat(String loginNames, String codes)
			throws Exception {
		// 格式化JSON数据
		JSONArray array = JSONArray.fromObject(loginNames);
		String newLoginName = ",";
		List<TfLoginUser> updateList = new ArrayList<TfLoginUser>();
		for (int i = 0; i < array.size(); i++) {
			// 判断是新增还是修改，分别放入两个List
			TfLoginUser user = (TfLoginUser) JSONObject.toBean(
					((JSONObject) array.get(i)), TfLoginUser.class);
			if (null == user.getGuid() || "".equals(user.getGuid())) {
				// 新增
				newLoginName += (user.getLoginName() + ",");
			} else {
				// 修改
				updateList.add(user);
			}
		}
		StringBuffer sb = new StringBuffer();
		// 判断新增数据是否有重复用户名
		TfLoginUser user = new TfLoginUser();
		user.setLikeLoginName(newLoginName);
		List<TfLoginUser> list = tfLoginUserDAO.find(user);
		if (list.size() != 0) {
			// 有重复
			for (TfLoginUser tmp : list) {
				sb.append(tmp.getLoginName() + "<br />");
			}
		}
		// 判断修改的数据是否有重复
		for (TfLoginUser tmp : updateList) {
			// 逐项检查
			TfLoginUser exitsUser = new TfLoginUser();
			exitsUser.setLoginName(tmp.getLoginName());
			exitsUser = tfLoginUserDAO.view(exitsUser);
			if (exitsUser != null && !exitsUser.getGuid().equals(tmp.getGuid())) {
				// 修改时候输入的登录名重复
				sb.append(tmp.getLoginName() + "<br />");
			}
		}
		String checkCode = checkIsRepeat(codes);
		String checkName = "";
		if (sb.length() != 0) {
			checkName = "对不起，您输入的如下用户存在重复编码，请修改！<br />" + sb.toString();
		}
		sb = new StringBuffer();
		if (!checkCode.equals("")) {
			sb.append(checkCode + "<br/>");
		}
		if (!checkName.equals("")) {
			sb.append(checkName);
		}
		if (sb.length() == 0) {
			return "0";
		} else {
			return sb.toString();
		}
	}

	/**
	 * 检测人员编码是否重复
	 * 
	 * @param loginNames
	 * @param codes
	 * @return
	 * @throws Exception
	 */
	private String checkIsRepeat(String codes) throws Exception {
		// 格式化JSON数据
		JSONArray array = JSONArray.fromObject(codes);
		String newCode = ",";
		List<TfLoginUser> updateList = new ArrayList<TfLoginUser>();
		for (int i = 0; i < array.size(); i++) {
			// 判断是新增还是修改，分别放入两个List
			TfLoginUser user = (TfLoginUser) JSONObject.toBean(
					((JSONObject) array.get(i)), TfLoginUser.class);
			if (null == user.getGuid() || "".equals(user.getGuid())) {
				// 新增
				newCode += (user.getCode() + ",");
			} else {
				// 修改
				updateList.add(user);
			}
		}
		StringBuffer sb = new StringBuffer();
		// 判断新增数据是否有重复编码
		TfLoginUser user = new TfLoginUser();
		user.setLikeCode(newCode);
		List<TfLoginUser> list = tfLoginUserDAO.find(user);
		if (list.size() != 0) {
			// 有重复
			for (TfLoginUser tmp : list) {
				sb.append(tmp.getCode() + "<br />");
			}
		}
		// 判断修改的数据是否有重复
		for (TfLoginUser tmp : updateList) {
			// 逐项检查
			TfLoginUser exitsUser = new TfLoginUser();
			exitsUser.setCode(tmp.getCode());
			exitsUser = tfLoginUserDAO.view(exitsUser);
			if (exitsUser != null && !exitsUser.getGuid().equals(tmp.getGuid())) {
				// 修改时候输入的编码重复
				sb.append(tmp.getCode() + "<br />");
			}
		}
		if (sb.length() != 0) {
			return "对不起，您输入的如下用户存在重复编码，请修改！<br />" + sb.toString();
		} else {
			return "";
		}
	}

	/**
	 * 通过部门来查询人员信息
	 * 
	 * @param tfUserDept
	 * @param start
	 * @param pageCount
	 * @return ListPageBean
	 * @throws Exception
	 */
	public ListPageBean findUserByDeptGuid(TfLoginUser tfLoginUser, int start,
			int pageCount) throws Exception {
		ListPageBean pager = new ListPageBean();
		pager.setRows(tfLoginUserDAO.findUserByDept(tfLoginUser, start,
				pageCount));
		pager.setTotal(tfLoginUserDAO.findUserByDeptGuidCount(tfLoginUser));
		return pager;
	}

	/**
	 * 根据虚拟组织机构查找该机构下人员信息，如蒙东公司下绩效委员会，则查找蒙东公司下所有绩效委员会下的人员信息
	 * 
	 * @param dept
	 * @param start
	 * @param pageCount
	 * @return
	 * @throws Exception
	 */
	public ListPageBean findUserByVirtual(TfDepartment dept, int start,
			int pageCount) throws Exception {
		dept = tfDepartmentDAO.view(dept);
		String topGuid = dept.getFulltree().substring(2, 34);
		TfLoginUser user = new TfLoginUser();
		user.setLikeDeptGuid(topGuid);
		if (dept.getName().indexOf("办公室") > -1) {
			user.setMeta2("0");
		} else {
			user.setMeta1("0");
		}
		user.setDel("0");
		return findUserByDeptGuid(user, start, pageCount);
	}

	/**
	 * 通过部门来查询人员数量
	 * 
	 * @param tfUserDept
	 * @throws Exception
	 */
	public int findUserTotalByDeptGuid(TfLoginUser tfLoginUser) {
		int total = tfLoginUserDAO.findUserByDeptGuidCount(tfLoginUser);
		return total;
	}

	/**
	 * 通过角色来查询人员数量
	 * 
	 * @param tfUserDept
	 * @throws Exception
	 */
	public int findUserTotalByRoleGuid(TfLoginUser tfLoginUser) {
		int total = tfLoginUserDAO.findUserByRoleGuidCount(tfLoginUser);
		return total;
	}

	/**
	 * 删除角色下的人员时对应删除人员信息表下对应的人员信息
	 * 
	 * @param entity
	 * @throws Exception
	 */
	public void delUserToRole(TfLoginUser entity) throws Exception {
		tfLoginUserDAO.delUserToRole(entity);
	}

	public String updateUser(TfLoginUser entity) throws Exception {
		TfLoginUser tf = new TfLoginUser();
		tf.setGuid(entity.getGuid());
		tf = tfLoginUserDAO.view(tf);
		String tfPass = tf.getLoginPass();
		String entityPass = MD5Utils.encryString(entity.getLoginPass());
		if (!tfPass.equals(entityPass)) {
			return "原始密码输入错误，请重新输入";
		}
		String nPass = entity.getNewPass();
		String cPass = entity.getConfirmPass();
		if (!nPass.equals(cPass)) {
			return "新设密码与确认密码不一致，请重新输入";
		}
		entity.setLoginPass(MD5Utils.encryString(entity.getNewPass()));
		tfLoginUserDAO.update(entity);
		return "密码修改成功!";
	}

	/**
	 * 根据json字符串，在添加或者修改用户表是同时关联其部门信息
	 * 
	 * @param json
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes" })
	@Transactional
	public void updateUserAndDeptToUser(Class tfLonginUser, String jsonArray) {
		try {
			JSONArray array = JSONArray.fromObject(jsonArray);
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				TfLoginUser entity = (TfLoginUser) JSONObject.toBean(obj,
						tfLonginUser);
				entity.setLastLoginDate(null);
				TfUserDept tfUserDept = new TfUserDept();
				TfRoleUser tfRoleUser = new TfRoleUser();
				tfUserDept.setIsPrimay(entity.getIsPrimay());
				String tmp = entity.getGuid();
				if (null == tmp || "".equals(tmp)) {
					entity.setLastLoginDate(null);
					String userGuid = UUIDCreate.get();// 自动生成ID
					tfUserDept.setDeptGuid(entity.getDeptGuid());// 将传入部门Guid中间表
					tfUserDept.setUserGuid(userGuid);// 将生成的guid传入中间表
					tfUserDept.setIsPrimay("1");
					tfRoleUser.setRoleGuid(entity.getRoleGuid());
					tfRoleUser.setUserGuid(userGuid);
					// 设置新增人员的默认密码为123456 By Hmilyld
					entity.setLoginPass(MD5Utils.encryString("123456"));
					entity.setGuid(userGuid);
					entity.setDel("0");
					entity.setWriteDate(new Date());
					tfLoginUserDAO.insertUserAndDeptToUser(entity, tfUserDept,
							tfRoleUser);
				} else {
					// 在更新人员信息表时更新人员与角色关联信息表,这个地方可以在前台页面判定角色是否已经修改（未实现），如果修改至更新人员信息表，如果角色更新则执行下面操作
					// 更新人员信息
					tfLoginUserDAO.update(entity);
					// 更新角色信息
					// 分隔roleGuid
					String[] rGuid = entity.getRoleGuid().split(",");
					TfRoleUser ru = new TfRoleUser();
					ru.setUserGuid(entity.getGuid());
					tfRoleUserDAO.delete(ru);
					if (rGuid.length == 0) {
						// 如果没有选择，则删除所有该用户的权限配置，同时插入一条空值
						tfRoleUserDAO.insert(ru);
					} else {
						for (String str : rGuid) {
							ru.setRoleGuid(str);
							tfRoleUserDAO.insert(ru);
						}
					}
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * 删除操作
	 * 
	 * @param entity
	 * @throws Exception
	 */

	public String delUser(TfLoginUser entity) throws Exception {
		String[] ids = null;
		String tmp = entity.getGuid();
		if (null != tmp) {
			ids = tmp.split(",");
			for (int i = 0; i < ids.length; i++) {
				entity.setGuid(ids[i]);
				entity.setDel("-1");
				tfLoginUserDAO.update(entity);
			}
		}
		return "数据删除成功";
	}

	/**
	 * 根据角色查找人员
	 * 
	 * @author 孙山伟
	 * @version 创建时间：2012-11-29 上午12:02:45
	 * @param entity
	 * @param start
	 * @param pageCount
	 * @return
	 */
	public ListPageBean findUserByroleGuid(TfLoginUser entity, int start,
			int pageCount) {
		ListPageBean pager = new ListPageBean();
		pager.setRows(tfLoginUserDAO.findUserByRoleGuid(entity, start,
				pageCount));
		pager.setTotal(tfLoginUserDAO.findUserByRoleGuidCount(entity));
		return pager;
	}


	/**
	 * 组织机构，在组织结构下增加人员。
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public String deptAndAllUserTree(TfDepartment entity) throws Exception {
		// 获取部门jsonArray
		JSONArray deptArray = getDeptTree(entity);
		// 获取该部门下人员相关操作
		TfLoginUser up = new TfLoginUser();
		up.setDeptGuid(entity.getParent());
		JSONArray userArray = getUserByDeptTree(up);
		// 合并两个JSONArray中的内容
		return mergerDeptAndUser(new JSONArray[] { deptArray, userArray });
	}
	
	public String deptAndRoleUserTree(TfDepartment entity,String roleName) throws Exception {
		// 获取部门jsonArray
		JSONArray deptArray = getDeptTree(entity);
		// 获取该部门下人员相关操作
		TfLoginUser up = new TfLoginUser();
		up.setDeptGuid(entity.getParent());
		up.setLikeRoleName(roleName);
		JSONArray userArray = getUserByDeptTree(up);
		// 合并两个JSONArray中的内容
		return mergerDeptAndUser(new JSONArray[] { deptArray, userArray });
	}

	/**
	 * 根据部门实体类，获取部门的JSONArray数据
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	private JSONArray getDeptTree(TfDepartment entity) throws Exception {
		// 获取部门相关操作
		List<TfDepartment> list = tfDepartmentDAO.find(entity);
		TreeUtils<TfDepartment> deptTree = new TreeUtils<TfDepartment>("guid",
				"name", null, null, null);
		deptTree.setDefaultIcon("department");
		JSONArray deptArray = JSONArray.fromObject(deptTree.convert(list));
		return deptArray;
	}

	private JSONArray getUserByDeptTree(TfLoginUser up) throws Exception {
		List<TfLoginUser> uList = tfLoginUserDAO.findUserByDept(up);
		TreeUtils<TfLoginUser> userTree = new TreeUtils<TfLoginUser>("guid",
				"realName", null, null, null);
		userTree.setDefaultIcon("user");
		userTree.setDefaultLeaf(true);
		JSONArray userArray = JSONArray.fromObject(userTree.convert(uList));
		for (int i = 0; i < userArray.size(); i++) {
			JSONObject obj = (JSONObject) userArray.get(i);
			obj.put("checked", false);
			userArray.set(i, obj);
		}
		return userArray;
	}

	/**
	 * 合并用户与部门的JSONArray，并返回字符串
	 * 
	 * @param jsonArray
	 * @return
	 */
	private String mergerDeptAndUser(JSONArray[] jsonArray) {
		JSONArray returnArray = new JSONArray();
		for (JSONArray array : jsonArray) {
			returnArray.addAll(array);
		}
		return JSONUtils.fromArray(returnArray);
	}
	
	/**
	 * @return
	 * 通过管理机关的部门GUID获取该部门下的所有员工
	 */
	public List<TfLoginUser> getUserByDept(TfLoginUser t){
		return tfLoginUserDAO.findUserByDeptGuid(t);
	}
	/**
	 * 保存用户的部门信息，角色信息
	 * @param entity
	 * @throws Exception 
	 */
	@Transactional
	public void saveUserDeptAndRole(TfLoginUser entity) throws Exception{
		String passwd = MD5Utils.encryString("111111");
		entity.setLoginPass(passwd);
		String userGuid = tfLoginUserDAO.insert(entity);
		TfUserDept tfUserDept = new TfUserDept();
		tfUserDept.setUserGuid(userGuid);
		tfUserDept.setDeptGuid(entity.getDeptGuid());
		tfUserDeptDAO.insert(tfUserDept);
		String[] roles = entity.getRoleGuid().split(",");
		for (String role : roles) {
			role=role.trim();
			TfRoleUser ru = new TfRoleUser();
			ru.setRoleGuid(role);
			ru.setUserGuid(userGuid);
			tfRoleUserDAO.insert(ru);
		}
	}

	@Transactional
	public void updateUserDeptAndRole(TfLoginUser entity) throws Exception {
		tfLoginUserDAO.update(entity);
		TfRoleUser ruser = new TfRoleUser();
		ruser.setUserGuid(entity.getGuid());
		tfRoleUserDAO.delete(ruser);
		
		String[] roles = entity.getRoleGuid().split(",");
		for (String role : roles) {
			role=role.trim();
			if(!role.equals("")){
				TfRoleUser ru = new TfRoleUser();
				ru.setUserGuid(entity.getGuid());
				ru.setRoleGuid(role);
				tfRoleUserDAO.insert(ru);
			}
		}
	}
}
