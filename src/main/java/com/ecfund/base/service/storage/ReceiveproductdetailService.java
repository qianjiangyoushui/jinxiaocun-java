package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.ReceiveproductdetailDAO;
import com.ecfund.base.model.storage.Receiveproductdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiveproductdetailService extends BaseService<Receiveproductdetail> {

    //@Autowired
    //private ReceiveproductdetailDAO receiveproductdetailDAO;

    @Autowired
    public void setBaseDAO(ReceiveproductdetailDAO receiveproductdetailDAO) {
        super.setBaseDAO(receiveproductdetailDAO);
    }

}