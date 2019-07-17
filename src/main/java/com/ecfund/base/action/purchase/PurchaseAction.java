package com.ecfund.base.action.purchase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.model.process.ProcessApprovers;
import com.ecfund.base.model.purchase.Purchaseapply;
import com.ecfund.base.model.purchase.Purchasebiling;
import com.ecfund.base.rabbitMQ.producer.MessageProducer;
import com.ecfund.base.rabbitMQ.producer.PurchaseApplyProducer;
import com.ecfund.base.service.process.ProcessApproversService;
import com.ecfund.base.service.purchase.PurchaseapplyService;
import com.ecfund.base.service.purchase.PurchasebilingService;
import com.ecfund.base.util.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("PurchaseAction")
@RequestMapping("/purchase")
public class PurchaseAction {
    private static final Logger mainLogger = LoggerFactory.getLogger(PurchaseAction.class);

    @Autowired
    private PurchaseapplyService purchaseapplyService;
    @Autowired
    private PurchasebilingService purchasebilingService;
    @Autowired
    private ProcessApproversService processApproversService;


    @RequestMapping(value = "/applyAdd.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String applyAdd(HttpServletRequest request, Purchaseapply purchaseapply, String productString) throws RollBackException {
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONArray detailList = JSONObject.parseArray(productString);
        JSONObject result = purchaseapplyService.save(user, purchaseapply, detailList);
        return result.toJSONString();
    }
    @RequestMapping(value = "/bilingAdd.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String bilingAdd(HttpServletRequest request, Purchasebiling purchasebiling, String productString) throws RollBackException {
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONArray detailList = JSONObject.parseArray(productString);
        JSONObject result = purchasebilingService.save(user, purchasebiling, detailList);
        return result.toJSONString();
    }
    @RequestMapping(value = "/applyInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String applyInfo(HttpServletRequest request,Purchaseapply purchaseapply){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONObject result =new JSONObject();
        JSONObject content =new JSONObject();
        try{
            purchaseapply=purchaseapplyService.view("findDetail",purchaseapply);
            ProcessApprovers approvers = new ProcessApprovers();
            approvers.setProcessinstanceid(purchaseapply.getProcessInstanceId());
            List<ProcessApprovers> approversList = processApproversService.find(approvers);
            content.put("purchaseApply",purchaseapply);
            content.put("approversList",approversList);
            result.put("success",true);
            result.put("content",content);
//            String s = "{\"processInstanceId\":\"2758b9a8-ddea-4529-a695-f7fb2bea54f7\",\"finishTime\":1563334447000,\"corpId\":\"ding32211ec91474443635c2f4657eb6378f\",\"EventType\":\"bpms_instance_change\",\"businessId\":\"201907171133000270843\",\"title\":\"贾晓东提交的订货审批\",\"type\":\"finish\",\"url\":\"https://aflow.dingtalk.com/dingtalk/mobile/homepage.htm?corpid=ding32211ec91474443635c2f4657eb6378f&dd_share=false&showmenu=true&dd_progress=false&back=native&procInstId=2758b9a8-ddea-4529-a695-f7fb2bea54f7&taskId=&swfrom=isv&dinghash=approval&dd_from=corp#approval\",\"result\":\"agree\",\"createTime\":1563334407000,\"processCode\":\"PROC-B90BF9E6-9D6E-42F1-9D65-EA44A6FCC71E\",\"bizCategoryId\":\"\",\"staffId\":\"024333162935579879\"}";
//            JSONObject object = JSONObject.parseObject(s);
//            messageProducer.sendMessage(object);

        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/billingInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String billingInfo(HttpServletRequest request,Purchasebiling purchasebiling){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONObject result =new JSONObject();
        JSONObject content =new JSONObject();
        try{
            purchasebiling=purchasebilingService.view("findDetail",purchasebiling);
            if(purchasebiling.getProcessInstanceId()!=null){
                ProcessApprovers approvers = new ProcessApprovers();
                approvers.setProcessinstanceid(purchasebiling.getProcessInstanceId());
                List<ProcessApprovers> approversList = processApproversService.find(approvers);
                content.put("approversList",approversList);
            }
            content.put("purchaseApply",purchasebiling);
            result.put("success",true);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }

        return result.toJSONString();
    }

    @RequestMapping(value = "/page.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String page(HttpServletRequest request, Purchaseapply purchaseapply, Page page) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            purchaseapply.setUserid(user.getUnionid());
            page = purchaseapplyService.find(purchaseapply, page.getBegin(), page.getPageSize());
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
    @RequestMapping(value = "/billingPage.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String billingPage(HttpServletRequest request, Purchasebiling purchasebiling, Page page) throws RollBackException {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            purchasebiling.setUserid(user.getUnionid());
            page = purchasebilingService.find(purchasebiling, page.getBegin(), page.getPageSize());
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

}
