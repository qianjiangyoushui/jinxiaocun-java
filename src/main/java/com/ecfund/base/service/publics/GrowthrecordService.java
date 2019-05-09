package com.ecfund.base.service.publics;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.dao.publics.GrowthrecordDAO;
import com.ecfund.base.dao.seedfile.SeedfileDAO;
import com.ecfund.base.model.publics.Growthrecord;
import com.ecfund.base.model.publics.PerformanceCount;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.DateUtil;
import com.ecfund.base.util.common.Page;
import org.apache.commons.collections.list.GrowthList;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
            growthrecord.setType(seedfile.getType());
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

    public JSONObject performanceCount() throws Exception {
        JSONObject result = new JSONObject();
        /**
         * 1.查出本年度的所有批次。（根据120天的生长期，前推6个月的批次作为年度批次）
         * 2.算出每个批次的生长天数
         * 3.查出已上传照片的天数
         */
        Calendar today = Calendar.getInstance();
        int days = today.get(Calendar.DAY_OF_YEAR);
        int year = today.get(Calendar.YEAR);
        List<PerformanceCount> resultlist = new ArrayList<PerformanceCount>();
        if(days<=150){
            /**
             * 查询去年和今年的批次进行统计
             */
            /**
             * 统计每个批次上传图片的天数，放入内存中。
             */
            Map countMap = new HashMap();

            /**
             * 查询符合要求的批次信息
             */
            Calendar createCalendar = Calendar.getInstance();
            createCalendar.add(Calendar.DAY_OF_YEAR,-150);
            Seedfile seedfile = new Seedfile();
            seedfile.setVisible("1");
            seedfile.setYearBetween(year);
            seedfile.setCreatedate(createCalendar.getTime());
            List<Seedfile> list = seedfileDAO.find(seedfile);
            String[] batchArray = new String[0];
            if(list!=null&&list.size()>0){
                batchArray = new String[list.size()];
            }
            for(int i=0;i<list.size();i++){
                batchArray[i]=list.get(i).getGuid();
            }
            List<PerformanceCount> performanceCounts = new ArrayList<PerformanceCount>();
            if(batchArray.length>0){
                performanceCounts = growthrecordDAO.performanceCount(batchArray);
            }
            for (PerformanceCount count:performanceCounts ) {
                countMap.put(count.getBatchid(),count);
            }
            for (Seedfile sf:list) {

                if (sf.getStartdate()==null)continue;
                /**计算生长天数
                 * 1.种植日期和现在是否是同一年。
                 * 2.同一年的话，现在的天数减去种植日期的天数就是生长天数。
                 * 3.隔年的话，判断前一年是平年还是闰年，算出去年的天数，再加上今年的天数就是生长天数。
                 * 4.内存中取出该批次的上传天数
                 */
                int daycount=0;
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(sf.getStartdate());
                if(startCalendar.get(Calendar.YEAR)==year){
                    int day = startCalendar.get(Calendar.DAY_OF_YEAR);
                    daycount = days-day;
                }else{
                    /**
                     * 查看收获日期是否填写
                     */
                    if(sf.getRewarddate()==null){
                        GregorianCalendar gc = new GregorianCalendar();
                        if(gc.isLeapYear(startCalendar.get(Calendar.YEAR))){
                            //闰年
                            int day =366-startCalendar.get(Calendar.DAY_OF_YEAR);
                            daycount = days+day;
                        }else{
                            //平年
                            int day =365-startCalendar.get(Calendar.DAY_OF_YEAR);
                            daycount = days+day;
                        }
                    }else{
                        Calendar rewardCalendar =Calendar.getInstance();
                        rewardCalendar.setTime(sf.getRewarddate());
                        if(startCalendar.get(Calendar.YEAR)==rewardCalendar.get(Calendar.YEAR)){
                            int day = rewardCalendar.get(Calendar.DAY_OF_YEAR);
                            daycount = day-days;
                        }else{
                            int d = rewardCalendar.get(Calendar.DAY_OF_YEAR);
                            GregorianCalendar gc = new GregorianCalendar();
                            if(gc.isLeapYear(startCalendar.get(Calendar.YEAR))){
                                //闰年
                                int day =366-startCalendar.get(Calendar.DAY_OF_YEAR);
                                daycount = days+d;
                            }else{
                                //平年
                                int day =365-startCalendar.get(Calendar.DAY_OF_YEAR);
                                daycount = days+d;
                            }
                        }
                    }
                }
                PerformanceCount pc =new PerformanceCount();
                PerformanceCount pc2=(PerformanceCount) countMap.get(sf.getGuid());
                pc.setGrowthDays(daycount);
                //pc.setSeedfile(sf);
                pc.setBatchCode(sf.getBatch());
                pc.setCreateDate(sf.getStartdate());
                pc.setCompanyName(sf.getCompanyid());
                pc.setAddress(pc2==null?"":pc2.getAddress());
                pc.setUploadDays(pc2==null?0:pc2.getUploadDays());
                resultlist.add(pc);
            }
        }else{
            /**
             * 今年的批次
             */
            Seedfile seedfile = new Seedfile();
            seedfile.setVisible("1");
            seedfile.setYears(year);
            List<Seedfile> list = seedfileDAO.find(seedfile);
            for (Seedfile sf:list) {
                if (sf.getStartdate()==null)continue;
                /**计算生长天数
                 * 1.种植日期和现在是否是同一年。
                 * 2.同一年的话，现在的天数减去种植日期的天数就是生长天数。
                 * 3.隔年的话，判断前一年是平年还是闰年，算出去年的天数，再加上今年的天数就是生长天数。
                 * 4.内存中取出该批次的上传天数
                 */
                int daycount=0;
                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTime(sf.getStartdate());
                int day = startCalendar.get(Calendar.DAY_OF_YEAR);
                daycount = days-day;
                PerformanceCount pc = new PerformanceCount();
                pc.setGrowthDays(daycount);
                pc.setSeedfile(sf);
                pc.setBatchCode(sf.getBatch());
                pc.setCreateDate(sf.getStartdate());
                pc.setAddress(sf.getPlots().getPlotname());
                pc.setCompanyName(sf.getCompanyid());
                resultlist.add(pc);
            }
        }
        result.put("content",resultlist);
        result.put("success",true);
        return result;
    }

    public String[] batchInsertG1(Growthrecord growthrecord, String[] jsonArray,String[] jsonArray2)throws Exception {
        String[] result = new String[jsonArray.length];
        for (int i = 0; i < jsonArray.length; i++) {
            String guid = jsonArray[i];
            Seedfile seedfile = new Seedfile();
            seedfile.setGuid(guid);
            growthrecord.setBatchid(guid);
            growthrecord.setBatchcode(jsonArray2[i]);
            result[i]= insert(growthrecord);
        }
        return result;
    }

    public String  delRecord(String guid) {
        JSONObject result = new JSONObject();
        try {
            Growthrecord growthrecord = new Growthrecord();
            growthrecord.setGuid(guid);
            growthrecord = growthrecordDAO.view(growthrecord);
            if(DateUtil.computeDays(growthrecord.getCreatedate())>1){
                //不可以删除
                result.put("success",false);
                result.put("erro","数据超过一天不可以删除");
            }else{
                //可以删除
                growthrecordDAO.delete(growthrecord);
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
}