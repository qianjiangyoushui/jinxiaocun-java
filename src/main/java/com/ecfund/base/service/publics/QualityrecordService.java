package com.ecfund.base.service.publics;


import com.ecfund.base.dao.publics.QualityrecordDAO;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.publics.Qualityrecord;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private QualityrecordDAO qualityrecordDAO;

    @Autowired
    public void setBaseDAO(QualityrecordDAO qualityrecordDAO) {
        super.setBaseDAO(qualityrecordDAO);
    }

    public Page findContainImagesPage(Qualityrecord entity, int offset, int limit) throws Exception{
        Page pager = new Page();
        List<Qualityrecord> list = qualityrecordDAO.find(entity ,offset ,limit );
        if(list!=null&&list.size()>0){
            Growthrecord growthrecord = new Growthrecord();
            String[] array = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i]=list.get(i).getGuid();
            }
            growthrecord.setGuids(array);
            pager.setRows(qualityrecordDAO.sqlSessionTemplate.selectList(entity.getClass().getSimpleName()
                    + ".findContainImages", growthrecord));
            pager.setCountItem(qualityrecordDAO.findCount(entity));
        }else{
            pager.setRows(new ArrayList<Growthrecord>());
            pager.setCountItem(0);
        }
        return pager;
    }
}
