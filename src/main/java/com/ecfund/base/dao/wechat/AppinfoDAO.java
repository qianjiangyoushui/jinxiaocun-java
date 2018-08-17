package com.ecfund.base.dao.wechat;


import com.ecfund.base.model.wechat.Appinfo;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-15 10:06
 * @filename AppinfoDAO.java
 *
 */

@Repository
public class AppinfoDAO extends BaseDAO<Appinfo> {

    public Appinfo findByGuid(String guid){
        try{
            Appinfo appinfo = new Appinfo();
            appinfo.setGuid(guid);
            return this.view(appinfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}