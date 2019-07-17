package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.SendproductDAO;
import com.ecfund.base.dao.storage.SendproductdetailDAO;
import com.ecfund.base.model.storage.Receiveproductdetail;
import com.ecfund.base.model.storage.Sendproduct;
import com.ecfund.base.model.storage.Sendproductdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendproductService extends BaseService<Sendproduct> {

    @Autowired
    private SendproductDAO sendproductDAO;
    @Autowired
    private SendproductdetailDAO sendproductdetailDAO;

    @Autowired
    public void setBaseDAO(SendproductDAO sendproductDAO) {
        super.setBaseDAO(sendproductDAO);
    }
    @Transactional(rollbackFor=Exception.class)
    public String insertAll(Sendproduct sendproduct) throws Exception {
        String guid = sendproductDAO.insert(sendproduct);
        for (Sendproductdetail receiveproductdetail : sendproduct.getDetailList()) {
            receiveproductdetail.setAppyid(guid);
            sendproductdetailDAO.insert(receiveproductdetail);
        }
        return guid;
    }
}