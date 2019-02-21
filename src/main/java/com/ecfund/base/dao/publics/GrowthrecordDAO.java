package com.ecfund.base.dao.publics;


import com.ecfund.base.dao.BaseDAO;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.publics.PerformanceCount;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2018-08-24 11:17
 * @filename GrowthrecordDAO.java
 *
 */

@Repository
public class GrowthrecordDAO extends BaseDAO<Growthrecord> {

    public List<PerformanceCount> performanceCount(String[] ids){
        return sqlSessionTemplate.selectList(Growthrecord.class.getSimpleName() + ".findDayCount",ids);
    }
}
