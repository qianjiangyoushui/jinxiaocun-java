package com.ecfund.base.service.purchase;

import com.ecfund.base.dao.purchase.SupplierDAO;
import com.ecfund.base.model.purchase.Supplier;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService extends BaseService<Supplier> {

    //@Autowired
    //private SupplierDAO supplierDAO;

    @Autowired
    public void setBaseDAO(SupplierDAO supplierDAO) {
        super.setBaseDAO(supplierDAO);
    }

}