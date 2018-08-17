package com.ecfund.base.service.seedfile;

import com.ecfund.base.dao.seedfile.CoreseedDAO;
import com.ecfund.base.model.seedfile.Coreseed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecfund.base.service.BaseService;


/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-03 17:48
 * @filename CoreseedService.java
 *
 */

@Service
public class CoreseedService extends BaseService<Coreseed> {

    //@Autowired
    //private CoreseedDAO coreseedDAO;

    @Autowired
    public void setBaseDAO(CoreseedDAO coreseedDAO) {
        super.setBaseDAO(coreseedDAO);
    }

}
