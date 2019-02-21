package com.ecfund.base.action.wechat.qa;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.publics.Question;
import com.ecfund.base.service.publics.QuestionService;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wechat/question")
public class QuestionAction {
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/add.action",produces = "application/json;charset=utf-8")
    public  String add(HttpServletRequest request, Question question){
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        JSONObject object = questionService.add(skey,question);
        return object.toJSONString();
    }
    @RequestMapping(value = "/detail.action",produces = "application/json;charset=utf-8")
    public  String detail(HttpServletRequest request, String id){
        JSONObject object = questionService.detail(id);
        return object.toJSONString();
    }
    @RequestMapping(value = "/pageList.action",produces = "application/json;charset=utf-8")
    public  String pageList(HttpServletRequest request, Question question,Page page){
        JSONObject result = new JSONObject();
        try {
           Page pageList = questionService.findPageList(question,page.getBegin(), page.getPageSize());
           JSONObject content = new JSONObject();
           content.put("page",pageList);
           result.put("success",true);
           result.put("content", content);
       }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro", e.getMessage());
       }
        return result.toJSONString();
    }
}
