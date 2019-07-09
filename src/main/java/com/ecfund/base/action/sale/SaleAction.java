package com.ecfund.base.action.sale;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.sale.Customer;
import com.ecfund.base.model.sale.Preorder;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.sale.CustomerService;
import com.ecfund.base.service.sale.PreorderService;
import com.ecfund.base.service.sale.SaleorderService;
import com.ecfund.base.util.common.OrderCodeFactory;
import com.ecfund.base.util.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("SaleAction")
@RequestMapping("/sale")
public class SaleAction {
    private static final Logger mainLogger = LoggerFactory.getLogger(SaleAction.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private DictionaryService  dictionaryService;
    @Autowired
    private PreorderService preorderService;
    @Autowired
    private SaleorderService saleorderService;

    @RequestMapping(value = "/saleorderAddPre.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String saleorderAddPre(HttpServletRequest request) throws Exception{
        String code = OrderCodeFactory.getSaleOderCode(null);
        Customer supplier = new Customer();
        supplier.setDisable(1);
        List<Customer> customerList = customerService.find(supplier);
        Dictionary d1 = new Dictionary();
        d1.setBelongsid("5");
        List<Dictionary> typeArray = dictionaryService.find(d1);
        Dictionary d2 = new Dictionary();
        d2.setBelongsid("2");
        List<Dictionary> ticketArray = dictionaryService.find(d2);
        JSONObject content = new JSONObject();
        content.put("customerList",customerList);
        content.put("typeArray",typeArray);
        content.put("ticketArray",ticketArray);
        content.put("code",code);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        mainLogger.info(result.toJSONString());
        return result.toJSONString();
    }

    @RequestMapping(value = "/saleorderAdd.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String saleorderAdd(HttpServletRequest request, Saleorder saleorder, String productString){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONArray detailList = JSONObject.parseArray(productString);
        try {
            JSONObject result = saleorderService.save(user, saleorder, detailList);
            return result.toJSONString();
        } catch (RollBackException e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("erro",e.getMessage());
            return result.toJSONString();
        }
    }
    @RequestMapping(value = "/saleorderpage.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String saleorderpage(HttpServletRequest request, Saleorder saleorder, Page page){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            saleorder.setUserid(user.getUnionid());
            page = saleorderService.find(saleorder, page.getBegin(), page.getPageSize());
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

    @RequestMapping(value = "/saleorderInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String saleorderInfo(HttpServletRequest request,Saleorder saleorder){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONObject result =new JSONObject();
        JSONObject content =new JSONObject();
        try{
            saleorder=saleorderService.view("findDetail",saleorder);
            content.put("preorder",saleorder);
            result.put("success",true);
            result.put("content",content);

        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/preorderAddPre.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String preorderAddPre(HttpServletRequest request) throws Exception{
        String code = OrderCodeFactory.getPreOderCode(null);
        Customer supplier = new Customer();
        supplier.setDisable(1);
        List<Customer> customerList = customerService.find(supplier);
        Dictionary d1 = new Dictionary();
        d1.setBelongsid("5");
        List<Dictionary> typeArray = dictionaryService.find(d1);
        JSONObject content = new JSONObject();
        content.put("customerList",customerList);
        content.put("typeArray",typeArray);
        content.put("code",code);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    @RequestMapping(value = "/preorderAdd.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String preorderAdd(HttpServletRequest request, Preorder preorder, String productString){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONArray detailList = JSONObject.parseArray(productString);
        try {
            JSONObject result = preorderService.save(user, preorder, detailList);
            return result.toJSONString();
        } catch (RollBackException e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("erro",e.getMessage());
            return result.toJSONString();
        }
    }
    @RequestMapping(value = "/preorderpage.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String preorderpage(HttpServletRequest request, Preorder preorder, Page page){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            preorder.setUserid(user.getUnionid());
            page = preorderService.find(preorder, page.getBegin(), page.getPageSize());
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

    @RequestMapping(value = "/preorderInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String preorderInfo(HttpServletRequest request,Preorder preorder){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONObject result =new JSONObject();
        JSONObject content =new JSONObject();
        try{
            preorder=preorderService.view("findDetail",preorder);
            content.put("preorder",preorder);
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
