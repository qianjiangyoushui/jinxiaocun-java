package com.ecfund.base.action.stock;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.config.Constant;
import com.ecfund.base.model.process.ProcessApprovers;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.purchase.Purchaseapply;
import com.ecfund.base.model.purchase.Supplier;
import com.ecfund.base.model.storage.*;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.purchase.SupplierService;
import com.ecfund.base.service.storage.*;
import com.ecfund.base.util.common.OrderCodeFactory;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

@Controller("StockAction")
@RequestMapping("/stock")
public class StockAction {

    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private HousepositionService housepositionService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private InstockorderService instockorderService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private OutstockorderService outstockorderService;
    @Autowired
    private SendproductService sendproductService;
    @Autowired
    private ReceiveproductService receiveproductService;
    @RequestMapping(value = "/add.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String add(HttpServletRequest request, Warehouse  warehouse) throws Exception{
        warehouse.setDisable(1);
        warehouse.setCreatedate(Calendar.getInstance().getTime());
        String guid = warehouseService.insert(warehouse);
        JSONObject content = new JSONObject();
        content.put("guid",guid);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/list.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String list(HttpServletRequest request, Warehouse  warehouse) throws Exception{
        List<Warehouse> list = warehouseService.find("findDetail",warehouse);
        JSONObject content = new JSONObject();
        content.put("list",list);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/info.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String info(HttpServletRequest request, Warehouse  warehouse) throws Exception{
        warehouse  = warehouseService.view("findDetail",warehouse);
        JSONObject content = new JSONObject();
        content.put("warehouse",warehouse);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/positionAdd.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String positionAdd(HttpServletRequest request, Houseposition houseposition) throws Exception{
        houseposition.setDisable(1);
        houseposition.setCreatedate(Calendar.getInstance().getTime());
        String guid = housepositionService.insert(houseposition);
        JSONObject content = new JSONObject();
        content.put("guid",guid);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/positionList.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String positionList(HttpServletRequest request, Houseposition houseposition) throws Exception{
        houseposition.setDisable(1);
        List<Houseposition> housepositionList = housepositionService.find(houseposition);
        JSONObject content = new JSONObject();
        content.put("housepositionList",housepositionList);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/positionDel.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String positionDel(HttpServletRequest request, Houseposition houseposition) throws Exception{
        houseposition.setDisable(2);
        housepositionService.update(houseposition);
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/inaddPre.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String inaddPre(HttpServletRequest request) throws Exception{
        String code = OrderCodeFactory.getStockInCode(null);
        Supplier supplier = new Supplier();
        supplier.setDisable(1);
        List<Supplier> supplierList = supplierService.find(supplier);
        Warehouse warehouse = new Warehouse();
        warehouse.setDisable(1);
        List<Warehouse> warehouseList = warehouseService.find(warehouse);
        Dictionary d1 = new Dictionary();
        d1.setBelongsid("3");
        List<Dictionary> typeArray = dictionaryService.find(d1);
        JSONObject content = new JSONObject();
        content.put("supplierList",supplierList);
        content.put("warehouseList",warehouseList);
        content.put("typeArray",typeArray);
        content.put("code",code);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    @RequestMapping(value = "/inadd.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String inadd(HttpServletRequest request, Instockorder instockorder, String productString){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONArray detailList = JSONObject.parseArray(productString);
        try {
            JSONObject result = instockorderService.save(user, instockorder, detailList);
            return result.toJSONString();
        } catch (RollBackException e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("erro",e.getMessage());
            return result.toJSONString();
        }
    }
    @RequestMapping(value = "/outpage.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String outpage(HttpServletRequest request, Outstockorder outstockorder, Page page){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            //outstockorder.setUserid(user.getUnionid());
            page = outstockorderService.find(outstockorder, page.getBegin(), page.getPageSize());
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
    @RequestMapping(value = "/outorderInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String outorderInfo(HttpServletRequest request,Outstockorder outstockorder){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONObject result =new JSONObject();
        JSONObject content =new JSONObject();
        try{
            outstockorder=outstockorderService.view("findDetail",outstockorder);
            content.put("outstockorder",outstockorder);
            result.put("success",true);
            result.put("content",content);

        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/outaddPre.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String outaddPre(HttpServletRequest request) throws Exception{
        String code = OrderCodeFactory.getStockOutCode(null);
        Warehouse warehouse = new Warehouse();
        warehouse.setDisable(1);
        List<Warehouse> warehouseList = warehouseService.find(warehouse);
        Dictionary d1 = new Dictionary();
        d1.setBelongsid("4");
        List<Dictionary> typeArray = dictionaryService.find(d1);
        JSONObject content = new JSONObject();
        content.put("warehouseList",warehouseList);
        content.put("typeArray",typeArray);
        content.put("code",code);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    @RequestMapping(value = "/outadd.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String outadd(HttpServletRequest request, Outstockorder outstockorder, String productString){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONArray detailList = JSONObject.parseArray(productString);
        try {
            JSONObject result = outstockorderService.save(user, outstockorder, detailList);
            return result.toJSONString();
        } catch (RollBackException e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("success",false);
            result.put("erro",e.getMessage());
            return result.toJSONString();
        }
    }
    @RequestMapping(value = "/inpage.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String inpage(HttpServletRequest request, Instockorder instockorder, Page page){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            //instockorder.setUserid(user.getUnionid());
            page = instockorderService.find(instockorder, page.getBegin(), page.getPageSize());
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
    @RequestMapping(value = "/inorderInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String inorderInfo(HttpServletRequest request,Instockorder instockorder){
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONObject result =new JSONObject();
        JSONObject content =new JSONObject();
        try{
            instockorder=instockorderService.view("findDetail",instockorder);
            content.put("instockorder",instockorder);
            result.put("success",true);
            result.put("content",content);

        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/sendList.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String sendList(HttpServletRequest request, Sendproduct sendproduct, Page page){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            sendproduct.setCompanyid(Constant.APP_KEY);
            page = sendproductService.find(sendproduct, page.getBegin(), page.getPageSize());
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
    @RequestMapping(value = "/sendInfo.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String sendInfo(HttpServletRequest request, Sendproduct sendproduct){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{

            sendproduct = sendproductService.view("findDetail",sendproduct);
            result.put("success",true);
            content.put("sendproduct",sendproduct);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/sendUpdate.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String sendUpdate(HttpServletRequest request, Sendproduct sendproduct){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            sendproductService.update(sendproduct);
            result.put("success",true);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/receiveList.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String receiveList(HttpServletRequest request, Receiveproduct receiveproduct, Page page){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            receiveproduct.setCompanyid(Constant.APP_KEY);
            page = receiveproductService.find(receiveproduct, page.getBegin(), page.getPageSize());
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
    String receiveInfo(HttpServletRequest request, Receiveproduct receiveproduct){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            receiveproduct = receiveproductService.view("findDetail",receiveproduct);
            result.put("success",true);
            content.put("receiveproduct",receiveproduct);
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
    String receiveUpdate(HttpServletRequest request, Receiveproduct receiveproduct){
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        try{
            receiveproductService.update(receiveproduct);
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
