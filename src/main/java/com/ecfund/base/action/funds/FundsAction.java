package com.ecfund.base.action.funds;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.model.funds.Paybill;
import com.ecfund.base.model.funds.Receivebill;
import com.ecfund.base.service.funds.PaybillService;
import com.ecfund.base.service.funds.ReceivebillService;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("FundsAction")
@RequestMapping("/funds")
public class FundsAction {


    @Autowired
    private PaybillService paybillService;
    @Autowired
    private ReceivebillService receivebillService;
    @RequestMapping(value = "/payPage.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String payPage(HttpServletRequest request, Paybill paybill, Page page) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            page = paybillService.find(paybill, page.getBegin(), page.getPageSize());
            result.put("success",true);
            content.put("page",page);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/payInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String payInfo(HttpServletRequest request, Paybill paybill) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            paybill = paybillService.view(paybill);
            result.put("success",true);
            content.put("paybill",paybill);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/payUpdate.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String payUpdate(HttpServletRequest request, Paybill paybill) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            paybillService.update(paybill);
            result.put("success",true);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/receivePage.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String receivePage(HttpServletRequest request, Receivebill receivebill, Page page) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            page = receivebillService.find(receivebill, page.getBegin(), page.getPageSize());
            result.put("success",true);
            content.put("page",page);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/receiveInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String receiveInfo(HttpServletRequest request, Receivebill receivebill) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            receivebill = receivebillService.view(receivebill);
            result.put("success",true);
            content.put("receivebill",receivebill);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/receiveUpdate.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String receiveUpdate(HttpServletRequest request, Receivebill receivebill) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            receivebillService.update(receivebill);
            result.put("success",true);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
}
