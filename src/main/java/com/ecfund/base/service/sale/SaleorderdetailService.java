package com.ecfund.base.service.sale;

import com.ecfund.base.dao.sale.SaleorderdetailDAO;
import com.ecfund.base.model.sale.Saleorderdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleorderdetailService extends BaseService<Saleorderdetail> {

    //@Autowired
    //private SaleorderdetailDAO saleorderdetailDAO;

    @Autowired
    public void setBaseDAO(SaleorderdetailDAO saleorderdetailDAO) {
        super.setBaseDAO(saleorderdetailDAO);
    }

}