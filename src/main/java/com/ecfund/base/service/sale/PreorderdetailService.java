package com.ecfund.base.service.sale;

import com.ecfund.base.dao.sale.PreorderdetailDAO;
import com.ecfund.base.model.sale.Preorderdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreorderdetailService extends BaseService<Preorderdetail> {

    //@Autowired
    //private PreorderdetailDAO preorderdetailDAO;

    @Autowired
    public void setBaseDAO(PreorderdetailDAO preorderdetailDAO) {
        super.setBaseDAO(preorderdetailDAO);
    }

}