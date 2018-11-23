package com.ecfund.base.action.wechat.g0;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.g0.Bottlestore;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.BottlestoreService;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/wechat/bottlestore")
public class StoreAction {
    @Autowired
    private UsersService userService;
    @Autowired
    private DictionaryService dictService;
    @Autowired
    private BottlestoreService bottlestoreService;
    @RequestMapping(value = "/save.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String save(HttpServletRequest request, Bottlestore bottlestore) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        bottlestore.setCompanyid(user.getCompany().getGuid());
        Calendar c =  Calendar.getInstance();
        c.setTime(bottlestore.getIndate());
        int month  = c.get(Calendar.MONTH);
        bottlestore.setMonth(month+1);
        bottlestoreService.insert(bottlestore);
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String list(HttpServletRequest request, Bottlestore bottlestore,Page page) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        bottlestore.setCompanyid(user.getCompany().getGuid());
        page = bottlestoreService.find(bottlestore,page.getBegin(), page.getPageSize());
        JSONObject content = new JSONObject();
        content.put("page",page);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/count.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String count(HttpServletRequest request, Bottlestore bottlestore,Page page) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        bottlestore.setCompanyid(user.getCompany().getGuid());
        List<Bottlestore> list = bottlestoreService.find("callProcedure",bottlestore);
        JSONObject content = new JSONObject();
        content.put("list",list);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
}
