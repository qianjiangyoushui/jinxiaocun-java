package com.ecfund.base.action.wechat.qa;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.model.publics.Answer;
import com.ecfund.base.model.publics.Question;
import com.ecfund.base.service.publics.AnswerService;
import com.ecfund.base.service.publics.QuestionService;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/wechat/answer")
public class AnswerAction {
    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @RequestMapping(value = "/add.action",produces = "application/json;charset=utf-8")
    public  String add(HttpServletRequest request, Answer answer){
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        JSONObject object = answerService.add(skey,answer);
        return object.toJSONString();
    }
    @RequestMapping(value = "/pageList.action",produces = "application/json;charset=utf-8")
    public  String pageList(HttpServletRequest request, Answer answer,Page page){
        JSONObject result = new JSONObject();
        try {
           Page pageList = answerService.find(answer,page.getBegin(), page.getPageSize());
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
