package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.SendproductdetailDAO;
import com.ecfund.base.model.storage.Receiveproduct;
import com.ecfund.base.model.storage.Receiveproductdetail;
import com.ecfund.base.model.storage.Sendproduct;
import com.ecfund.base.model.storage.Sendproductdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendproductdetailService extends BaseService<Sendproductdetail> {

//    @Autowired
//    private SendproductdetailDAO sendproductdetailDAO;

    @Autowired
    public void setBaseDAO(SendproductdetailDAO sendproductdetailDAO) {
        super.setBaseDAO(sendproductdetailDAO);
    }

}