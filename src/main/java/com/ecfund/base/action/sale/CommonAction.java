package com.ecfund.base.action.sale;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.purchase.ProductCategory;
import com.ecfund.base.model.purchase.Products;
import com.ecfund.base.model.purchase.Purchaseapply;
import com.ecfund.base.model.purchase.Supplier;
import com.ecfund.base.model.sale.Customer;
import com.ecfund.base.service.publics.DictionaryService;
import com.ecfund.base.service.purchase.ProductCategoryService;
import com.ecfund.base.service.purchase.ProductsService;
import com.ecfund.base.service.purchase.PurchaseapplyService;
import com.ecfund.base.service.purchase.SupplierService;
import com.ecfund.base.service.sale.CustomerService;
import com.ecfund.base.util.common.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("saleCommon")
@RequestMapping("/sale/common")
public class CommonAction {
    private static final Logger logger = LoggerFactory.getLogger(CommonAction.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/customerPage.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String supplierPage(HttpServletRequest request, Customer customer, Page page) throws Exception{
        customer.setDisable(1);
        page = customerService.find(customer,page.getBegin(),page.getPageSize());
        JSONObject content = new JSONObject();
        content.put("page",page);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }
    @RequestMapping(value = "/customerAdd.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String supplierAdd(HttpServletRequest request, Customer customer) throws Exception{
        customer.setDisable(1);
        customer.setType("1");
        String guid = customerService.insert(customer);
        JSONObject content = new JSONObject();
        content.put("guid",guid);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }



}
