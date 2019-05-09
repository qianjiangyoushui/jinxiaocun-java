package com.ecfund.base.action.wechat.g3;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.common.Constants;
import com.ecfund.base.common.SelectItem;
import com.ecfund.base.model.g2g3.Farm;
import com.ecfund.base.model.g2g3.Plots;
import com.ecfund.base.model.publics.Dictionary;
import com.ecfund.base.model.seedfile.Seedfile;
import com.ecfund.base.model.users.Users;
import com.ecfund.base.service.eliteG1.GreenhousesService;
import com.ecfund.base.service.eliteG1.IrrigationService;
import com.ecfund.base.service.eliteG1.ManureService;
import com.ecfund.base.service.eliteG1.PlantprotectService;
import com.ecfund.base.service.g0.CulturemediumService;
import com.ecfund.base.service.g0.SeedbedService;
import com.ecfund.base.service.g0.TrainseedService;
import com.ecfund.base.service.g2g3.FarmService;
import com.ecfund.base.service.g2g3.PlotsService;
import com.ecfund.base.service.publics.*;
import com.ecfund.base.service.seedfile.SeedfileService;
import com.ecfund.base.service.users.UsersService;
import com.ecfund.base.util.common.Page;
import com.ecfund.base.util.json.DateJsonValueProcessor;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/wechat/G3")
public class G3Action {
    @Autowired
    private UsersService userService;
    @Autowired
    private DictionaryService dictService;
    @Autowired
    private SeedfileService seedfileService;
    @Autowired
    private GreenhousesService greenhousesService;
    @Autowired
    private CulturemediumService culturemediumService;
    @Autowired
    private TrainseedService trainseedService;
    @Autowired
    private IcondictService icondictService;
    @Autowired
    private GrowthrecordService growthrecordService;
    @Autowired
    private UpimageService upimageService;
    @Autowired
    private QualityrecordService qualityrecordService;
    @Autowired
    private SeedbedService seedbedService;
    @Autowired
    private FileralationService fileralationService;
    @Autowired
    private ManureService manureService;
    @Autowired
    private PlantprotectService plantprotectService;
    @Autowired
    private IrrigationService irrigationService;

    @Autowired
    private FarmService farmService;
    @Autowired
    private PlotsService plotsService;

    @RequestMapping(value = "/filelist.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String filelist(Page page, Seedfile seedfile, HttpServletRequest request) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        seedfile.setVisible("1");
        seedfile.setCompanyid(user.getCompany().getGuid());
        Page pagelist=seedfileService.findpageG2G3list(seedfile, page.getBegin(), page.getPageSize());
        pagelist.setPageNo(page.getPageNo());
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        net.sf.json.JSONObject jsons= net.sf.json.JSONObject.fromObject(pagelist, config);
        JSONObject content = new JSONObject();
        content.put("page", jsons);
        content.put("years", seedfile.getYears());
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    @RequestMapping(value = "/add.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String add(HttpServletRequest request) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<SelectItem> selectItemList = new ArrayList<SelectItem>();
        for(int i=year;i>(year-5);i--){
            SelectItem	selectItem = new SelectItem(i,String.valueOf(i));
            selectItemList.add(selectItem);
        }
        Dictionary dict = new Dictionary();
        dict.setBelongsid("2");// 获取品种
        List<Dictionary> variety = dictService.find(dict);


        Dictionary dict2 = new Dictionary();
        dict2.setBelongsid("3");// 获取扩繁级别
        List<Dictionary> level = dictService.find(dict2);


        // 大棚
        Farm farm = new Farm();
        farm.setCompanyid(user.getCompany().getGuid());
        farm.setFarmtype(1);
        List<Farm> farms = farmService.find(farm);
        Plots plot = new Plots();
        List<Plots> plots = new ArrayList();
        for (int i = 0; i < farms.size(); i++) {
            plot.setFarmid(farms.get(i).getGuid());
            List listplot = plotsService.find(plot);
            for (int j = 0; j < listplot.size(); j++) {
                String name = ((Plots) listplot.get(j)).getPlotname();
                ((Plots) listplot.get(j)).setPlotname(farms.get(i).getFarmname() + "-" + name);
            }
            plots.addAll(listplot);
        }



        JSONObject content = new JSONObject();
        content.put("year",JSONObject.toJSON(selectItemList));
        content.put("variety",JSONObject.toJSON(variety));
        content.put("level",JSONObject.toJSON(level));
        content.put("houses",JSONObject.toJSON(plots));
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    @RequestMapping(value = "/save.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String save(HttpServletRequest request, Seedfile seedfile, String selectValue, String selectName) throws Exception{
        String skey = request.getHeader(Constants.WX_HEADER_SKEY);
        Users user = new Users();
        user.setGuid(skey);
        user = userService.view(user);
        seedfile.setCompanyid(user.getCompany().getGuid());
        seedfile.setVisible("1");//可见
        seedfile.setCreatedate(new Date(System.currentTimeMillis()));
        seedfile.setOperatorid(user.getGuid());
        StringBuffer sb = new StringBuffer();
        if ("".equals(seedfile.getIsproduction()) || seedfile.getIsproduction() == null) {
            seedfile.setIsproduction("1");
        }
        Plots plots = new Plots();
        plots.setGuid(seedfile.getPlaceid());
        plots = plotsService.findPlots(plots);
        Farm farm = new Farm();
        farm.setGuid(plots.getFarmid());
        farm = farmService.view(farm);
        String grade = createGrade(seedfile.getLevel());
        String batchCode = seedfile.getYears()+"-"+farm.getFarmcode()+"-"+plots.getPlotcode()+"-"+seedfile.getVariety()+"-"+grade;
        seedfile.setBatch(batchCode);
        String guid = seedfileService.insert(seedfile,selectValue,selectName);
        JSONObject content = new JSONObject();
        content.put("guid",guid);
        JSONObject result = new JSONObject();
        result.put("success",true);
        result.put("content", content);
        return result.toJSONString();
    }

    private String createGrade(String level) {
        switch (level){
            case "1":return "";
            case "2":return "G0";
            case "3":return "G1";
            case "4":return "G2";
            case "5":return "G3";
            case "6":return "G4";
            default:return "G";
        }
    }
    @RequestMapping(value = "/growthDel.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String growthDel(HttpServletRequest request, String guid) throws Exception {
        return growthrecordService.delRecord(guid);
    }
    @RequestMapping(value = "/manureDel.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String manureDel(HttpServletRequest request, String guid) throws Exception {
        return manureService.delRecord(guid);
    }
    @RequestMapping(value = "/protectDel.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String protectDel(HttpServletRequest request, String guid) throws Exception {
        return plantprotectService.delRecord(guid);
    }
    @RequestMapping(value = "/waterDel.action",produces = "application/json;charset=utf-8")
    public @ResponseBody
    String waterDel(HttpServletRequest request, String guid) throws Exception {
        return irrigationService.delRecord(guid);
    }
}
