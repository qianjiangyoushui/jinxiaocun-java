package com.ecfund.base.service.g0;

import com.ecfund.base.dao.g0.BottlestoreDAO;
import com.ecfund.base.model.g0.Bottlestore;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BottlestoreService extends BaseService<Bottlestore> {

    //@Autowired
    //private BottlestoreDAO bottlestoreDAO;

    @Autowired
    public void setBaseDAO(BottlestoreDAO bottlestoreDAO) {
        super.setBaseDAO(bottlestoreDAO);
    }

}