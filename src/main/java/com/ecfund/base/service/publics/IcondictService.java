package com.ecfund.base.service.publics;

import com.ecfund.base.dao.publics.IcondictDAO;
import com.ecfund.base.model.publics.Icondict;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-21 13:49
 * @filename IcondictService.java
 *
 */

@Service
public class IcondictService extends BaseService<Icondict> {

    //@Autowired
    //private IcondictDAO icondictDAO;

    @Autowired
    public void setBaseDAO(IcondictDAO icondictDAO) {
        super.setBaseDAO(icondictDAO);
    }

}