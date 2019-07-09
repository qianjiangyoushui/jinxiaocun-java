package com.ecfund.base.service.publics;


import com.ecfund.base.dao.publics.DingmenuDAO;
import com.ecfund.base.model.publics.Dingmenu;
import com.ecfund.base.model.publics.Dingrm;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DingmenuService extends BaseService<Dingmenu> {

    @Autowired
    private DingmenuDAO dingmenuDAO;

    @Autowired
    public void setBaseDAO(DingmenuDAO dingmenuDAO) {
        super.setBaseDAO(dingmenuDAO);
    }

    public List<Dingmenu> findMenuByRole(Dingrm dingrm) {
        return dingmenuDAO.findMenuByRole(dingrm);
    }

}