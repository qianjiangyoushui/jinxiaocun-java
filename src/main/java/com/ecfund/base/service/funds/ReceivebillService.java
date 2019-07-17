package com.ecfund.base.service.funds;

import com.ecfund.base.dao.funds.ReceivebillDAO;
import com.ecfund.base.model.funds.Receivebill;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceivebillService extends BaseService<Receivebill> {

    //@Autowired
    //private ReceivebillDAO receivebillDAO;

    @Autowired
    public void setBaseDAO(ReceivebillDAO receivebillDAO) {
        super.setBaseDAO(receivebillDAO);
    }

}