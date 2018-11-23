package com.ecfund.base.service.publics;

import com.ecfund.base.dao.publics.MessageDAO;
import com.ecfund.base.model.publics.Message;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends BaseService<Message> {

    //@Autowired
    //private MessageDAO messageDAO;

    @Autowired
    public void setBaseDAO(MessageDAO messageDAO) {
        super.setBaseDAO(messageDAO);
    }

}