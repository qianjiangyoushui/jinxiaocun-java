package com.ecfund.base.action.wechat.quality;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.common.SelectItem;
import com.ecfund.base.model.g0.Culturemedium;
import com.ecfund.base.model.g0.Seedbed;
import com.ecfund.base.model.g0.Trainseed;
import com.ecfund.base.model.publics.*;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Menus;
import com.ecfund.base.model.users.Roles;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g0.CulturemediumService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.g0.TrainseedService;
import com.ecfund.base.service.publics.*;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 设置
 * @author xxl
 *
 */
@Controller
@RequestMapping("/wechat/quality")
public class QualityAction {

    @Autowired
    private UsersService userService;

    @Autowired
    private QualityrecordService qualityrecordService;
    @Autowired
    private SeedfileService seedfileService;

    @RequestMapping(value = "/save.action",produces = "application/json;charset=utf-8")
    public @ResponseBody String save(HttpServletRequest request, Qualityrecord qualityrecord) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        Date createDate = qualityrecord.getCreatedate();
        String month = createDate.getMonth()+1+"";
        String day = createDate.getDate()+1+"";
        qualityrecord.setMonth(month);
        qualityrecord.setDay(day);
        qualityrecord.setCompanyid(user.getCompany().getGuid());
        qualityrecord.setType("1");//
        qualityrecord.setUserid(user.getGuid());
        qualityrecord.setVisible(1);
        String guid = qualityrecordService.insert(qualityrecord);
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        content.put("guid",guid);
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    @RequestMapping(value = "/filelist.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String filelist(Page page,Seedfile seedfile,HttpServletRequest request) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        seedfile.setVisible("1");
        seedfile.setCompanyid(user.getCompany().getGuid());
        Page pagelist=seedfileService.findpagelist(seedfile, page.getBegin(), page.getPageSize());
        pagelist.setPageNo(page.getPageNo());
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        net.sf.json.JSONObject jsons= net.sf.json.JSONObject.fromObject(pagelist, config);
        JSONObject content = new JSONObject();
        content.put("page", jsons);
        content.put("years", seedfile.getYears());
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/pagelist.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String pagelist(Page page,Qualityrecord qualityrecord,HttpServletRequest request) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        qualityrecord.setVisible(1);
        qualityrecord.setCompanyid(user.getCompany().getGuid());
        Page pagelist=qualityrecordService.findContainImagesPage(qualityrecord, page.getBegin(), page.getPageSize());
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", pagelist);
        return result.toJSONString();
    }

}
