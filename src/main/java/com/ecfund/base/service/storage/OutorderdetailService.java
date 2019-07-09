package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.OutorderdetailDAO;
import com.ecfund.base.model.storage.Outorderdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutorderdetailService extends BaseService<Outorderdetail> {

    //@Autowired
    //private OutorderdetailDAO outorderdetailDAO;

    @Autowired
    public void setBaseDAO(OutorderdetailDAO outorderdetailDAO) {
        super.setBaseDAO(outorderdetailDAO);
    }

}