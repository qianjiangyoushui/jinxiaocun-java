package com.ecfund.base.service.publics;


import com.alibaba.fastjson.JSONArray;
import com.ecfund.base.dao.publics.GrowthrecordDAO;
import com.ecfund.base.dao.seedfile.SeedfileDAO;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.Page;
import org.apache.commons.collections.list.GrowthList;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-24 11:18
 * @filename GrowthrecordService.java
 *
 */

@Service
public class GrowthrecordService extends BaseService<Growthrecord> {

    @Autowired
    private GrowthrecordDAO growthrecordDAO;
    @Autowired
    private SeedfileDAO seedfileDAO;
    @Autowired
    public void setBaseDAO(GrowthrecordDAO growthrecordDAO) {
        super.setBaseDAO(growthrecordDAO);
    }

    public String[] batchInsert(Growthrecord growthrecord, String[] jsonArray,String[] jsonArray2)throws  Exception{
        String[] result = new String[jsonArray.length];
        for (int i = 0; i < jsonArray.length; i++) {
            String guid = jsonArray[i];
            Seedfile seedfile = new Seedfile();
            seedfile.setGuid(guid);
            seedfile = seedfileDAO.view("findg2g3",seedfile);
            growthrecord.setBatchid(guid);
            growthrecord.setPlot(seedfile.getPlots().getPlotname());
            growthrecord.setBatchcode(jsonArray2[i]);
            result[i]= insert(growthrecord);

        }
        return result;
    }

    public Page find(Growthrecord entity, int offset, int limit)throws Exception{
        Page pager = new Page();
        pager.setRows(growthrecordDAO.sqlSessionTemplate.selectList(entity.getClass().getSimpleName()
                + ".findContainImages", entity, new RowBounds(offset, limit)));
        pager.setCountItem(growthrecordDAO.findCount(entity));
        return pager;
    }

    public Page findPagelist(Growthrecord entity, int offset, int limit) throws Exception{
        Page pager = new Page();
        List<Growthrecord> list = growthrecordDAO.find(entity ,offset ,limit );
        if(list!=null&&list.size()>0){
            Growthrecord growthrecord = new Growthrecord();
            String[] array = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i]=list.get(i).getGuid();
            }
            growthrecord.setGuids(array);
            pager.setRows(growthrecordDAO.sqlSessionTemplate.selectList(entity.getClass().getSimpleName()
                    + ".findContainImages", growthrecord));
            pager.setCountItem(growthrecordDAO.findCount(entity));
        }else{
            pager.setRows(new ArrayList<Growthrecord>());
            pager.setCountItem(0);
        }
        return pager;
    }
}