package com.ecfund.base.action.wechat.employee;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.users.Department;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.users.CompanyService;
import com.ecfund.base.service.users.RolesService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wechat/user")
public class UserAction {

    @Autowired
    private UsersService usersService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DictionaryService dictService;
    @Autowired
    private RolesService rolesService;

    @RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8")
    public String userList(HttpServletRequest request,Users users){
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try {
            users.setDel("-1");
            List<Users> list = usersService.find(users);
            content.put("list",list);
            result.put("success",true);
            result.put("content", content);
        }catch (Exception e ){
            JSONObject erro = new JSONObject();
            erro.put("msg",e.getMessage());
            result.put("success",false);
            result.put("erro",erro);
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/add.action",produces = "application/json;charset=utf-8")
    public String add(HttpServletRequest request,Users users){
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try {
            Dictionary dict = new Dictionary();
            dict.setBelongsid("1");
            List<Dictionary> dicts = dictService.find(dict);
            Roles roles = new Roles();
            roles.setDescription("1");
            List<Roles> rolesList = rolesService.find(roles);
            content.put("dicts",dicts);
            content.put("rolesList",rolesList);
            result.put("success",true);
            result.put("content", content);
        }catch (Exception e ){
            JSONObject erro = new JSONObject();
            erro.put("msg",e.getMessage());
            result.put("success",false);
            result.put("erro",erro);
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/save.action",produces = "application/json;charset=utf-8")
    public String save(HttpServletRequest request,Users user,String departid,String[] roles){
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try {
            // 完善数据
            user.setPassword(MD5Utils.encryString(user.getPassword()));
            user.setRegistdate(Calendar.getInstance().getTime());

            Department depart = new Department();
            depart.setCompanyid(user.getCompanyid());
            depart.setDepartid(departid);
            usersService.addEmployee(user, depart,roles);
            result.put("success",true);
            result.put("content", content);
        }catch (Exception e ){
            JSONObject erro = new JSONObject();
            erro.put("msg",e.getMessage());
            result.put("success",false);
            result.put("erro",erro);
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/update.action",produces = "application/json;charset=utf-8")
    public String update(HttpServletRequest request,Users user,String departguid,String departid,String[] roles){
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try {
            // 完善数据
            user.setPassword(MD5Utils.encryString(user.getPassword()));
            user.setRegistdate(Calendar.getInstance().getTime());
            Department depart = new Department();
            depart.setGuid(departguid);
            depart.setCompanyid(user.getCompanyid());
            depart.setDepartid(departid);
            usersService.updateEmployee(user, depart,roles);
            result.put("success",true);
            result.put("content", content);
        }catch (Exception e ){
            JSONObject erro = new JSONObject();
            erro.put("msg",e.getMessage());
            result.put("success",false);
            result.put("erro",erro);
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/del.action",produces = "application/json;charset=utf-8")
    public String del(HttpServletRequest request,String guid){
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try {
            // 完善数据
            Users user = new Users();
            user.setGuid(guid);
            user.setDel("-1");
            usersService.update(user);
            result.put("success",true);
            result.put("content", content);
        }catch (Exception e ){
            JSONObject erro = new JSONObject();
            erro.put("msg",e.getMessage());
            result.put("success",false);
            result.put("erro",erro);
        }
        return result.toJSONString();
    }
}

