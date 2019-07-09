package com.ecfund.base.service.purchase;

import com.ecfund.base.dao.purchase.PurchaseapplydetailDAO;
import com.ecfund.base.model.purchase.Purchaseapplydetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseapplydetailService extends BaseService<Purchaseapplydetail> {

    //@Autowired
    //private PurchaseapplydetailDAO purchaseapplydetailDAO;

    @Autowired
    public void setBaseDAO(PurchaseapplydetailDAO purchaseapplydetailDAO) {
        super.setBaseDAO(purchaseapplydetailDAO);
    }

}