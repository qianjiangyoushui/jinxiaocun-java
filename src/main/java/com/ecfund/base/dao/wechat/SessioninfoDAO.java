package com.ecfund.base.dao.wechat;

import com.ecfund.base.model.wechat.Sessioninfo;
import org.springframework.stereotype.Repository;

import com.ecfund.base.dao.BaseDAO;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-15 10:11
 * @filename SessioninfoDAO.java
 *
 */

@Repository
public class SessioninfoDAO extends BaseDAO<Sessioninfo> {

    public Sessioninfo findByOpenid(String openid){
        try {
            Sessioninfo sessioninfo = new Sessioninfo();
            sessioninfo.setOpenId(openid);
            return this.view(sessioninfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
