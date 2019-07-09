package com.ecfund.base.action.publics;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.model.publics.Dingmenu;
import com.ecfund.base.model.publics.Dingrm;
import com.ecfund.base.service.publics.DingmenuService;
import com.ecfund.base.service.publics.DingrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/public")
public class MainIndexAction {
    private static final Logger logger = LoggerFactory.getLogger(MainIndexAction.class);


    @Autowired
    private DingrmService dingrmService;
    @Autowired
    private DingmenuService dingmenuService;

    @RequestMapping(value = "/menuIndex.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String menuIndex(HttpServletRequest request,Dingrm dingrm){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        List<OapiUserGetResponse.Roles> roles = user.getRoles();
        String[] ids=new String[roles.size()];
        for(int i=0;i<roles.size();i++){
            long id = roles.get(i).getId();
            System.out.println(id+":"+roles.get(i).getName());
            ids[i]=String.valueOf(id);
        }
        dingrm.setGuids(ids);
        List<Dingmenu> dingmenuList = dingmenuService.findMenuByRole(dingrm);
        JSONObject content = new JSONObject();
        content.put("menus",dingmenuList);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

}
