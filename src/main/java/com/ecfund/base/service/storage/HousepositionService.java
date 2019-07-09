package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.HousepositionDAO;
import com.ecfund.base.model.storage.Houseposition;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HousepositionService extends BaseService<Houseposition> {

    //@Autowired
    //private HousepositionDAO housepositionDAO;

    @Autowired
    public void setBaseDAO(HousepositionDAO housepositionDAO) {
        super.setBaseDAO(housepositionDAO);
    }

}