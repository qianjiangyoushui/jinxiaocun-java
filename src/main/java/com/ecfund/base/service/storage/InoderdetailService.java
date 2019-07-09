package com.ecfund.base.service.storage;

import com.ecfund.base.dao.storage.InoderdetailDAO;
import com.ecfund.base.model.storage.Inoderdetail;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InoderdetailService extends BaseService<Inoderdetail> {

    //@Autowired
    //private InoderdetailDAO inoderdetailDAO;

    @Autowired
    public void setBaseDAO(InoderdetailDAO inoderdetailDAO) {
        super.setBaseDAO(inoderdetailDAO);
    }

}