package com.ecfund.base.action.wechat.machine;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.g2g3.MachineRecord;
import com.ecfund.base.model.publics.Nycalculate;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.g2g3.MachineRecordService;
import com.ecfund.base.service.publics.GrowthrecordService;
import com.ecfund.base.service.publics.NyCalculateService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/wechat/machine")
public class MachineAction {

    @Autowired
    public MachineRecordService machineRecordService;
    @Autowired
    public UsersService userService;

    @RequestMapping(value = "/saveRecord.action",produces = "application/json;charset=utf-8")
    public @ResponseBody String saveRecord(HttpServletRequest request, MachineRecord machineRecord) {
        JSONObject result = new JSONObject();
        try {
            String skey = request.getHeader(Constants.WX_HEADER_SKEY);
            Users user = new Users();
            user.setGuid(skey);
            user = userService.view(user);
            Calendar calendar = Calendar.getInstance();
            String month = calendar.get(Calendar.MONTH)+1+"";
            String day = calendar.get(Calendar.DAY_OF_MONTH)+"";
            machineRecord.setMonth(month);
            machineRecord.setDay(day);
            machineRecord.setCreatedate(Calendar.getInstance().getTime());
            machineRecord.setCompanyid(user.getCompany().getGuid());
            machineRecord.setUserid(user.getGuid());
            machineRecord.setYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
            machineRecord.setDepartid(user.getDepart()!=null?user.getDepart().getDepartid():"");
            machineRecord.setSort(Calendar.getInstance().getTime());
            String guid = machineRecordService.insert(machineRecord);
            result.put("success",true);
            JSONObject content = new JSONObject();
            content.put("guid",guid);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return  result.toJSONString();
    }
    @RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8")
    public @ResponseBody String list(HttpServletRequest request, Page page,MachineRecord machineRecord) {
        JSONObject result = new JSONObject();
        try {
            String skey = request.getHeader(Constants.WX_HEADER_SKEY);
            Users user = new Users();
            user.setGuid(skey);
            user = userService.view(user);
            machineRecord.setCompanyid(user.getCompany().getGuid());
            page = machineRecordService.findPagelist(machineRecord,page.getBegin(),page.getPageSize());
            result.put("success",true);
            JSONObject content = new JSONObject();
            content.put("page",page);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return  result.toJSONString();
    }
    @RequestMapping(value = "/mylist.action",produces = "application/json;charset=utf-8")
    public @ResponseBody String mylist(HttpServletRequest request, Page page,MachineRecord machineRecord) {
        JSONObject result = new JSONObject();
        try {
            String skey = request.getHeader(Constants.WX_HEADER_SKEY);
            Users user = new Users();
            user.setGuid(skey);
            user = userService.view(user);
            machineRecord.setCompanyid(user.getCompany().getGuid());
            machineRecord.setUserid(skey);
            page = machineRecordService.findPagelist(machineRecord,page.getBegin(),page.getPageSize());
            result.put("success",true);
            JSONObject content = new JSONObject();
            content.put("page",page);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return  result.toJSONString();
    }
    @RequestMapping(value = "/delete.action",produces = "application/json;charset=utf-8")
    public @ResponseBody String delete(HttpServletRequest request, String guid) {
        return  machineRecordService.delRecord(guid);
    }
}
