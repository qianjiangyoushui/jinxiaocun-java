package com.ecfund.base.service.publics;

import com.ecfund.base.dao.publics.DingrmDAO;
import com.ecfund.base.model.publics.Dingrm;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DingrmService extends BaseService<Dingrm> {

    //@Autowired
    //private DingrmDAO dingrmDAO;

    @Autowired
    public void setBaseDAO(DingrmDAO dingrmDAO) {
        super.setBaseDAO(dingrmDAO);
    }

}