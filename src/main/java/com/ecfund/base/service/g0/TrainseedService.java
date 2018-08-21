package com.ecfund.base.service.g0;

import com.ecfund.base.dao.g0.TrainseedDAO;
import com.ecfund.base.model.g0.Trainseed;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-17 15:56
 * @filename TrainseedService.java
 *
 */

@Service
public class TrainseedService extends BaseService<Trainseed> {

    //@Autowired
    //private TrainseedDAO trainseedDAO;

    @Autowired
    public void setBaseDAO(TrainseedDAO trainseedDAO) {
        super.setBaseDAO(trainseedDAO);
    }

}
