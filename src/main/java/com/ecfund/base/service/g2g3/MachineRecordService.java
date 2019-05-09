package com.ecfund.base.service.g2g3;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.dao.g2g3.MachineRecordDAO;
import com.ecfund.base.model.eliteG1.Irrigation;
import com.ecfund.base.model.g2g3.MachineRecord;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 本文件由系统框架代码生成工具自动生成，请修改此处注释内容
 *
 * @date 2019-04-19 11:22
 * @filename MachineRecordService.java
 *
 */

@Service
public class MachineRecordService extends BaseService<MachineRecord> {

    @Autowired
    private MachineRecordDAO machineRecordDAO;

    @Autowired
    public void setBaseDAO(MachineRecordDAO machineRecordDAO) {
        super.setBaseDAO(machineRecordDAO);
    }

    public String delRecord(String guid) {
        JSONObject result = new JSONObject();
        try {
            MachineRecord machineRecord = new MachineRecord();
            machineRecord.setGuid(guid);
            machineRecord = this.view(machineRecord);
            if(DateUtil.computeDays(machineRecord.getCreatedate())>1){
                //不可以删除
                result.put("success",false);
                result.put("erro","数据超过一天不可以删除");
            }else{
                //可以删除
                this.delete(machineRecord);
                result.put("success",true);
                result.put("content","删除成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro",e.getMessage());
        }
        return result.toJSONString();
    }

    public Page findPagelist(MachineRecord entity, int offset, int limit) throws Exception{
        Page pager = new Page();
        List<MachineRecord> list = machineRecordDAO.find(entity ,offset ,limit );
        if(list!=null&&list.size()>0){
            MachineRecord machineRecord = new MachineRecord();
            String[] array = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i]=list.get(i).getGuid();
            }
            machineRecord.setGuids(array);
            pager.setRows(machineRecordDAO.sqlSessionTemplate.selectList(entity.getClass().getSimpleName()
                    + ".findContainImages", machineRecord));
            pager.setCountItem(machineRecordDAO.findCount(entity));
        }else{
            pager.setRows(new ArrayList<Growthrecord>());
            pager.setCountItem(0);
        }
        return pager;
    }

}
