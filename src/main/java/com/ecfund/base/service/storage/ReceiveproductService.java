package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.ReceiveproductDAO;
import com.ecfund.base.dao.storage.ReceiveproductdetailDAO;
import com.ecfund.base.model.storage.Receiveproduct;
import com.ecfund.base.model.storage.Receiveproductdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReceiveproductService extends BaseService<Receiveproduct> {

    @Autowired
    private ReceiveproductDAO receiveproductDAO;
    @Autowired
    private ReceiveproductdetailDAO receiveproductdetailDAO;

    @Autowired
    public void setBaseDAO(ReceiveproductDAO receiveproductDAO) {
        super.setBaseDAO(receiveproductDAO);
    }

    @Transactional(rollbackFor=Exception.class)
    public String insertAll(Receiveproduct receiveproduct) throws Exception {
        String guid = receiveproductDAO.insert(receiveproduct);
        for (Receiveproductdetail receiveproductdetail : receiveproduct.getDetailList()) {
            receiveproductdetail.setAppyid(guid);
            receiveproductdetailDAO.insert(receiveproductdetail);
        }
        return guid;
    }
}