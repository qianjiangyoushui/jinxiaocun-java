package com.ecfund.base.action.publics;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.service.publics.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller("ReportAction")
@RequestMapping("/report")
public class ReportAction {

    @Autowired
    private ReportService reportService;
    @RequestMapping(value = "/purchaseApplyCount.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String purchaseApplyCount() {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            List list = reportService.purchaseApplyCount();
            BigDecimal summoney=BigDecimal.ZERO;
            for (Object o :list) {
                Map map = (Map) o;
                BigDecimal money = (BigDecimal) map.get("summoney");
                summoney = summoney.add(money);
            }
            Map map = new HashMap();
            map.put("summoney",summoney);
            map.put("name","总金额");
            list.add(map);
            result.put("success",true);
            content.put("list",list);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/purchaseBilingCount.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String purchaseBilingCount() {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            List list = reportService.purchaseBilingCount();
            BigDecimal summoney=BigDecimal.ZERO;
            for (Object o :list) {
                Map map = (Map) o;
                BigDecimal money = (BigDecimal) map.get("summoney");
                summoney = summoney.add(money);
            }
            Map map = new HashMap();
            map.put("summoney",summoney);
            map.put("name","总金额");
            list.add(map);
            result.put("success",true);
            content.put("list",list);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/stockInCount.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String stockInCount() {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            List list = reportService.stockInMoney();
            List list2 = reportService.stockInAmount();
            BigDecimal summoney=BigDecimal.ZERO;
            for (Object o :list) {
                Map map = (Map) o;
                BigDecimal money = (BigDecimal) map.get("summoney");
                summoney = summoney.add(money);
            }
            Map map = new HashMap();
            map.put("summoney",summoney);
            map.put("name","总金额");
            list.add(map);
            result.put("success",true);
            content.put("list",list);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/stockOutCount.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String stockOutCount() {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            List list = reportService.stockOutMoney();
            BigDecimal summoney=BigDecimal.ZERO;
            for (Object o :list) {
                Map map = (Map) o;
                BigDecimal money = (BigDecimal) map.get("summoney");
                summoney = summoney.add(money);
            }
            Map map = new HashMap();
            map.put("summoney",summoney);
            map.put("name","总金额");
            list.add(map);
            result.put("success",true);
            content.put("list",list);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }
    @RequestMapping(value = "/stockCount.action", produces = "application/json;charset=utf-8")
    public @ResponseBody
    String stockCount() {
        JSONObject result = new JSONObject();
        JSONObject content = new JSONObject();
        try{
            List list = reportService.stockMoney();
            List list2 = reportService.stockAmount();
            result.put("success",true);
            content.put("list",list);
            content.put("list2",list2);
            result.put("content",content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }

}
