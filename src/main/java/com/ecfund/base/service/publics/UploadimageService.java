package com.ecfund.base.service.publics;

/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 * jiaxd-create
 */

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.dao.publics.UploadimageDAO;
import com.ecfund.base.model.publics.Uploadimage;
import com.ecfund.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;

@Service
public class UploadimageService extends BaseService<Uploadimage> {

    //@Autowired
    //private UploadimageDAO uploadimageDAO;

    @Autowired
    public void setBaseDAO(UploadimageDAO uploadimageDAO) {
        super.setBaseDAO(uploadimageDAO);
    }

    @Transactional(rollbackFor =Exception.class )
    public JSONObject batchInsert(String key , String relationid) throws Exception {
        JSONObject result = new JSONObject();
        ArrayList<String> guidArray = new ArrayList<String>();
            Uploadimage uploadimage = new Uploadimage();
            uploadimage.setBstype("jxc_cgkd");
            uploadimage.setCompanyid("");
            uploadimage.setSuiteid("1");
            uploadimage.setRelationid(relationid);
            uploadimage.setCreatedate(Calendar.getInstance().getTime());
            uploadimage.setTakedate(Calendar.getInstance().getTime());
            uploadimage.setHost("http://qn.nmamtf.club/");
            uploadimage.setImagename(key);
            try {
                String guid = this.insert(uploadimage);
                guidArray.add(guid);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("success",false);
                result.put("erro", e.getMessage());
                throw e;
            }
        result.put("success",true);
        result.put("content", guidArray);
        return result;
    }
}