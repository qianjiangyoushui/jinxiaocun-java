package com.ecfund.base.action.wechat.tools;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.publics.Nycalculate;
import com.ecfund.base.model.publics.PerformanceCount;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.service.publics.GrowthrecordService;
import com.ecfund.base.service.publics.NyCalculateService;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("/wechat/tools")
public class ToolsAction {

    @Autowired
    public NyCalculateService nyCalculateService;
    @Autowired
    SeedfileService seedfileService;
    @Autowired
    GrowthrecordService growthrecordService;
    @RequestMapping(value = "/protectionList.action",produces = "application/json;charset=utf-8")
    public @ResponseBody String protectionlist(HttpServletRequest request) {
        String timesStr = request.getParameter("times");
        String amountStr = request.getParameter("amount");
        String warterStr = request.getParameter("warter");
        BigDecimal times = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal warter = BigDecimal.ZERO;
        JSONObject result = new JSONObject();
        List<Nycalculate> resultList = new ArrayList<Nycalculate>();
        try {
            times = new BigDecimal(timesStr);
            amount = new BigDecimal(amountStr);
            warter = new BigDecimal(warterStr);
            Nycalculate nyCalculate = new Nycalculate();
            nyCalculate.setTimes(Integer.parseInt(timesStr));
            List<Nycalculate> list=  nyCalculateService.find(nyCalculate);
            for (Nycalculate  object : list) {
                if (!"0".equals(amountStr)) {
                    //按照亩数来计算
                    object.setSumuseage(object.getUseage().multiply(amount).setScale(2));
                    object.setSumusewater(object.getWater().multiply(amount).setScale(2));
                } else {
                    object.setSumuseage(warter.multiply(object.getUseage()).divide(object.getWater().setScale(2), 2));
                    object.setSumusewater(warter);
                }
                resultList.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = new JSONObject();
            result.put("state", "0");
            return result.toString();
        }
        result.put("content", resultList);
        result.put("state", "1");
        return result.toString();
    }

    @RequestMapping(value = "/performance.action",produces = "application/json;charset=utf-8")
    public @ResponseBody String performance(HttpServletRequest request) throws Exception {
        return growthrecordService.performanceCount().toJSONString();
    }
}
