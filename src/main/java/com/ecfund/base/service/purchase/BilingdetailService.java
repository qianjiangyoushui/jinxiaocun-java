package com.ecfund.base.service.purchase;

import com.ecfund.base.dao.purchase.BilingdetailDAO;
import com.ecfund.base.model.purchase.Bilingdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilingdetailService extends BaseService<Bilingdetail> {

//    @Autowired
//    private BilingdetailDAO bilingdetailDAO;

    @Autowired
    public void setBaseDAO(BilingdetailDAO bilingdetailDAO) {
        super.setBaseDAO(bilingdetailDAO);
    }

}