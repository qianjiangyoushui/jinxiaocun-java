package com.ecfund.base.service.publics;

import com.ecfund.base.dao.publics.FileralationDAO;
import com.ecfund.base.model.publics.Fileralation;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileralationService extends BaseService<Fileralation> {

    //@Autowired
    //private FileralationDAO fileralationDAO;

    @Autowired
    public void setBaseDAO(FileralationDAO fileralationDAO) {
        super.setBaseDAO(fileralationDAO);
    }

}
