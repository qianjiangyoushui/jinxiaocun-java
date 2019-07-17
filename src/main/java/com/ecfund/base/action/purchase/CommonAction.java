package com.ecfund.base.action.purchase;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.purchase.ProductCategory;
import com.ecfund.base.model.purchase.Products;
import com.ecfund.base.model.purchase.Purchaseapply;
import com.ecfund.base.model.purchase.Supplier;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.purchase.ProductCategoryService;
import com.ecfund.base.service.purchase.ProductsService;
import com.ecfund.base.service.purchase.PurchaseapplyService;
import com.ecfund.base.service.purchase.SupplierService;
import com.ecfund.base.util.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("purchaseCommon")
@RequestMapping("/purchase/common")
public class CommonAction {
    private static final Logger logger = LoggerFactory.getLogger(CommonAction.class);

    @Autowired
    private ProductsService productsService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private PurchaseapplyService purchaseapplyService;
    @RequestMapping(value = "/productList.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String productList(HttpServletRequest request, Products products, Page page) throws Exception{
        products.setDisable(1);
        page = productsService.find(products,page.getBegin(),page.getPageSize());
        JSONObject content = new JSONObject();
        content.put("page",page);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/productAdd.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String productAdd(HttpServletRequest request, Products products) throws Exception{
        products.setDisable(1);
        String guid = productsService.insert(products);
        JSONObject content = new JSONObject();
        content.put("guid",guid);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/productDel.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String productDel(HttpServletRequest request, Products products) throws Exception{
        products.setDisable(2);
        productsService.update(products);
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/productInfo.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String productInfo(HttpServletRequest request, Products products) throws Exception{
        products = productsService.view(products);
        JSONObject content = new JSONObject();
        content.put("products",products);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/productEdit.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String productEdit(HttpServletRequest request, Products products) throws Exception{
        productsService.update(products);
        JSONObject content = new JSONObject();
        content.put("products",products);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/categoryList.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String categoryList(HttpServletRequest request, ProductCategory category) throws Exception{
        category.setDisable(1);
        List<ProductCategory> list = productCategoryService.find(category);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", list);
        return result.toJSONString();
    }
    @RequestMapping(value = "/supplierPage.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String supplierPage(HttpServletRequest request, Supplier supplier, Page page) throws Exception{
        supplier.setDisable(1);
        page = supplierService.find(supplier,page.getBegin(),page.getPageSize());
        JSONObject content = new JSONObject();
        content.put("page",page);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/supplierAdd.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String supplierAdd(HttpServletRequest request, Supplier supplier) throws Exception{
        supplier.setDisable(1);
        supplier.setType("1");
        String guid = supplierService.insert(supplier);
        JSONObject content = new JSONObject();
        content.put("guid",guid);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    @RequestMapping(value = "/supplierEdit.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String supplierEdit(HttpServletRequest request, Supplier supplier) {
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try{
            supplierService.update(supplier);
            result.put("success",true);
            result.put("content", content);
        }catch (Exception e){
            result.put("success",false);
            result.put("erro", e.getMessage());
        }
        return result.toJSONString();
    }


    @RequestMapping(value = "/purchasepre.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String purchasepre(HttpServletRequest request) throws RollBackException {
        OapiUserGetResponse user = (OapiUserGetResponse) request.getSession().getAttribute("user");
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            Dictionary d1 = new Dictionary();
            d1.setBelongsid("1");
            List<Dictionary> categoryArray = dictionaryService.find(d1);
            Dictionary d2 = new Dictionary();
            d2.setBelongsid("2");
            List<Dictionary> billtypeArray = dictionaryService.find(d2);
            Supplier s = new Supplier();
            List<Supplier> suppliers = supplierService.find(s);
            Purchaseapply purchaseapply = new Purchaseapply();
            purchaseapply.setStatus(2);
            purchaseapply.setUserid(user.getUnionid());
            List<Purchaseapply> applyArray = purchaseapplyService.find(purchaseapply);
            content.put("applyArray",applyArray);
            content.put("categoryArray",categoryArray);
            content.put("billtypeArray",billtypeArray);
            content.put("suppliers",suppliers);
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
