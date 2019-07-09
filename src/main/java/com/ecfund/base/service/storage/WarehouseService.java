package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.WarehouseDAO;
import com.ecfund.base.model.storage.Warehouse;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService extends BaseService<Warehouse> {

    //@Autowired
    //private WarehouseDAO warehouseDAO;

    @Autowired
    public void setBaseDAO(WarehouseDAO warehouseDAO) {
        super.setBaseDAO(warehouseDAO);
    }

}