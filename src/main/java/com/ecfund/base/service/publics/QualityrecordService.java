package com.ecfund.base.service.publics;


import com.ecfund.base.dao.publics.QualityrecordDAO;
import com.ecfund.base.model.publics.Qualityrecord;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-27 10:46
 * @filename QualityrecordService.java
 *
 */

@Service
public class QualityrecordService extends BaseService<Qualityrecord> {

    //@Autowired
    //private QualityrecordDAO qualityrecordDAO;

    @Autowired
    public void setBaseDAO(QualityrecordDAO qualityrecordDAO) {
        super.setBaseDAO(qualityrecordDAO);
    }

}
