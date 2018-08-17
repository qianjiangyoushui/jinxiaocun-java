package com.ecfund.base.service.users;

import com.ecfund.base.dao.users.RolesDAO;
import com.ecfund.base.model.users.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecfund.base.service.BaseService;
import com.ecfund.base.model.users.Company;
import com.ecfund.base.model.users.Department;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.dao.users.UsersDAO;

/**
 * 
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 * 
 * @date 2017-07-19 10:46
 * @filename UsersService.java
 * 
 */

@Service
public class UsersService extends BaseService<Users> {

	 @Autowired
	 private UsersDAO usersDAO;

	 @Autowired
	 private CompanyService companyService;

	 @Autowired
	 private DepartmentService departService;

	 @Autowired
	 private RolesDAO rolesDAO;
	@Autowired
	public void setBaseDAO(UsersDAO usersDAO) {
		super.setBaseDAO(usersDAO);
	}

	/**
	 * 用户注册
	 * @param user
	 * @param company
	 */
	@Transactional
	public void regist(Users user,Company company) throws Exception{
		String userid=this.insert(user);
		//完善公司信息
		company.setOperatorid(userid);

		String companyid=companyService.insert(company);

		//注册员工所属部门 （默认综合部）
		Department depart=new Department();
		depart.setCompanyid(companyid);
		depart.setUserid(userid);
		depart.setDepartid("2");//综合部

		departService.insert(depart);

	}
	
	/**
	 * 新增员工
	 * @param user
	 * @param depart
	 * @throws Exception
	 */
	@Transactional
	public void addEmployee(Users user,Department depart) throws Exception{
		String userid=this.insert(user);
		depart.setUserid(userid);
		departService.insert(depart);
	}
	
	@Transactional
	public void updateEmployee(Users user,Department depart) throws Exception{
		this.update(user);
		departService.update(depart);
	}

	public Users findByOpenid(String openid){
		return this.usersDAO.findByOpenid(openid);
	}

	public Roles findRoleMenulist(Roles roles){
		return rolesDAO.findMenuList(roles);
	}

	public Users findRole(Users users){
		return usersDAO.findRoleList(users);
	}
}