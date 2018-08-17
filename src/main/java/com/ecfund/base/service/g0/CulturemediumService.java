package com.ecfund.base.service.g0;

import com.ecfund.base.dao.g0.CulturemediumDAO;
import com.ecfund.base.model.g0.Culturemedium;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CulturemediumService extends BaseService<Culturemedium> {

    //@Autowired
    //private CulturemediumDAO culturemediumDAO;

    @Autowired
    public void setBaseDAO(CulturemediumDAO culturemediumDAO) {
        super.setBaseDAO(culturemediumDAO);
    }

}
