package com.ecfund.base.service.funds;

import com.ecfund.base.dao.funds.PaybillDAO;
import com.ecfund.base.model.funds.Paybill;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaybillService extends BaseService<Paybill> {

    //@Autowired
    //private PaybillDAO paybillDAO;

    @Autowired
    public void setBaseDAO(PaybillDAO paybillDAO) {
        super.setBaseDAO(paybillDAO);
    }

}