package com.ecfund.base.service.publics;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.dao.publics.AnswerDAO;
import com.ecfund.base.model.publics.Answer;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class AnswerService extends BaseService<Answer> {

    @Autowired
    private AnswerDAO answerDAO;

    @Autowired
    public void setBaseDAO(AnswerDAO answerDAO) {
        super.setBaseDAO(answerDAO);
    }

    public JSONObject add(String skey, Answer answer) {
        JSONObject result = new JSONObject();
        try {
            answer.setCreatedate(Calendar.getInstance().getTime());
            answer.setWxUserId(skey);
            answerDAO.insert(answer);
            result.put("success",true);
            result.put("content",true);
        }catch (Exception e ){
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result;
    }

}