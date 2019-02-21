package com.ecfund.base.service.publics;


import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.dao.publics.AnswerDAO;
import com.ecfund.base.dao.publics.QuestionDAO;
import com.ecfund.base.dao.users.WxUserDAO;
import com.ecfund.base.model.publics.Answer;
import com.ecfund.base.model.publics.Question;
import com.ecfund.base.model.users.WxUser;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-01-11 15:34
 * @filename QuestionService.java
 *
 */

@Service
public class QuestionService extends BaseService<Question> {

    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private WxUserDAO wxUserDAO;
    @Autowired
    private AnswerDAO answerDAO;
    @Autowired
    public void setBaseDAO(QuestionDAO questionDAO) {
        super.setBaseDAO(questionDAO);
    }


    public JSONObject add(String skey, Question question) {
        JSONObject result = new JSONObject();
        try {
            question.setCreatedate(DateUtil.getCalendar().getTime());
            String url = question.getImagesurl();
            question.setWxuserid(skey);
            questionDAO.insert(question);
            result.put("success",true);
            result.put("content",true);
        }catch (Exception e ){
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result;
    }

    public JSONObject detail(String id) {
        JSONObject result = new JSONObject();
        try {
            Question question = new Question();
            question.setGuid(id);
            question = questionDAO.view(question);
            result.put("success",true);
            result.put("content",question);
        }catch (Exception e ){
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result;
    }

    public Page findPageList(Question question, int begin, int pageSize) throws Exception{
        Page pager = new Page();
        List<Question> list = questionDAO.find(question, begin, pageSize);
        List<Question> resultList = new ArrayList<Question>();
        for (Question q:list ) {
            Answer answer = new Answer();
            answer.setQuestionId(q.getGuid());
            int count = answerDAO.findCount(answer);
            q.setAnswerCount(count);
            resultList.add(q);
        }
        pager.setRows(resultList);
        pager.setCountItem(findCount(question));
        return pager;
    }
}
