package com.ecfund.base.service.publics;


import com.ecfund.base.dao.publics.NyCalculateDAO;
import com.ecfund.base.model.publics.Nycalculate;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NyCalculateService extends BaseService<Nycalculate> {

    @Autowired
    private NyCalculateDAO nyCalculateDAO;

    @Autowired
    public void setBaseDAO(NyCalculateDAO nyCalculateDAO) {
        super.setBaseDAO(nyCalculateDAO);
    }

}