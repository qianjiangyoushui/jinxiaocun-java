package com.ecfund.base.service.sale;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.config.Constant;
import com.ecfund.base.dao.publics.AccountsuitDAO;
import com.ecfund.base.dao.sale.PreorderDAO;
import com.ecfund.base.dao.sale.PreorderdetailDAO;
import com.ecfund.base.model.process.DetailForm;
import com.ecfund.base.model.process.ProcessInstanceInputVO;
import com.ecfund.base.model.process.TextForm;
import com.ecfund.base.model.sale.Preorder;
import com.ecfund.base.model.sale.Preorderdetail;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.process.ProcessinstanceService;
import com.ecfund.base.util.common.CurrencyUtil;
import com.ecfund.base.util.dingtalk.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class PreorderService extends BaseService<Preorder> {

    //@Autowired
    //private PreorderDAO preorderDAO;

    @Autowired
    private PreorderdetailDAO preorderdetailDAO;
    @Autowired
    public void setBaseDAO(PreorderDAO preorderDAO) {
        super.setBaseDAO(preorderDAO);
    }


    @Autowired
    private AccountsuitDAO accountsuitDAO;

    @Autowired
    private ProcessinstanceService processinstanceService;
    @Transactional(rollbackFor=Exception.class)
    public JSONObject save(OapiUserGetResponse user, Preorder preorder, JSONArray detailList) throws RollBackException {
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try{
            //JSONObject js = JSONObject.parseObject(productArray);
            String suitId = accountsuitDAO.getCurrentSuit();
            preorder.setSuitid(suitId);
            preorder.setStatus(1);
            preorder.setCompnayid(Constant.APP_KEY);
            preorder.setUserid(user.getUnionid());
            preorder.setUsername(user.getName());
            preorder.setCreatedate(Calendar.getInstance().getTime());
            preorder.setCapital(CurrencyUtil.toChinaUpper(preorder.getSummoney().toString()));
            String guid = this.insert(preorder);
            List<Preorderdetail> list = new ArrayList<Preorderdetail>();
            for(int i=0;i<detailList.size();i++) {
                Preorderdetail detail = detailList.getObject(i,Preorderdetail.class);
                detail.setApplyid(guid);
                detail.setStatus(1);
                detail.setSummoney(detail.getAmount().multiply(detail.getPrice()));
                detail.setCreatedate(Calendar.getInstance().getTime());
                preorderdetailDAO.insert(detail);
                list.add(detail);
            }
            preorder.setDetailList(list);

            ServiceResult sr = startProcess(preorder,user);
            /**
             * 流程启动失败回滚
             */
            if(!sr.isSuccess()){
                result.put("success",false);
                result.put("erro", sr.getMessage());
                throw new Exception();
            }else{
                String processInstanceId = sr.getResult().toString();
                preorder.setProcessInstanceId(processInstanceId);
                preorder.setGuid(guid);
                this.update(preorder);
            }

            content.put("guid",guid);
            result.put("success",true);
            result.put("content", content);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
            result.put("erro", e.getMessage());
            throw new RollBackException();
        }
        return result;
    }

    /**
     * 构建流程数据，启动流程
     * @param preorder
     * @param user
     * @return
     * @throws Exception
     */
    private ServiceResult startProcess(Preorder preorder, OapiUserGetResponse user)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ProcessInstanceInputVO processInstance= new ProcessInstanceInputVO();
        processInstance.setOriginatorUserId(user.getUserid());
        processInstance.setDeptId(user.getDepartment().get(0));
        TextForm textForm1 = new TextForm("客户名称",preorder.getCustomername());
        TextForm textForm2 = new TextForm("结算方式",preorder.getSaletypename());
        TextForm textForm3 = new TextForm("合同号",preorder.getBillcode());
        TextForm textForm4 = new TextForm("发货日期",sdf.format(preorder.getSenddate()));
        TextForm textForm5 = new TextForm("到货地点",preorder.getAddress());
        TextForm textForm6 = new TextForm("数量",preorder.getSummount().toString());
        TextForm textForm7 = new TextForm("总金额",preorder.getSummoney().toString());
        TextForm textForm8 = new TextForm("预付金额",preorder.getPremoney().toString());
        List<TextForm> textList = new ArrayList<TextForm>();
        textList.add(textForm1);
        textList.add(textForm2);
        textList.add(textForm3);
        textList.add(textForm4);
        textList.add(textForm5);
        textList.add(textForm6);
        textList.add(textForm7);
        textList.add(textForm8);
        processInstance.setTextForms(textList);
        List<DetailForm> list = new ArrayList<DetailForm>();
        int index=0;
        DetailForm detailForm = new DetailForm();
        detailForm.setName("订购明细");
        for(Preorderdetail detail :preorder.getDetailList()) {
            index = index+1;
            TextForm dtextForm1 = new TextForm("名称", detail.getProductname());
            TextForm dtextForm2 = new TextForm("规格", detail.getNormal());
            TextForm dtextForm3 = new TextForm("单位", detail.getUnit());
            TextForm dtextForm4 = new TextForm("单价", detail.getPrice().toString());
            TextForm dtextForm5 = new TextForm("数量", detail.getAmount().toString());
            TextForm dtextForm6 = new TextForm("金额",detail.getAmount().multiply(detail.getPrice()).toString());
            List<TextForm> dtextList = new ArrayList<TextForm>();
            dtextList.add(dtextForm1);
            dtextList.add(dtextForm2);
            dtextList.add(dtextForm3);
            dtextList.add(dtextForm4);
            dtextList.add(dtextForm5);
            dtextList.add(dtextForm6);
            DetailForm detailForm2 = new DetailForm();
            detailForm2.setTextForms(dtextList);
            list.add(detailForm2);
        }
        detailForm.setDetailForms(list);
        processInstance.setDetailForms(Arrays.asList(detailForm));
        return processinstanceService.startProcessInstance(processInstance,Constant.PROCESS_CODE_PRESALE);
    }

    public void updateStatus(String processInstanceId, int status) {
        try {
            Preorder purchaseapply = new Preorder();
            purchaseapply.setProcessInstanceId(processInstanceId);
            purchaseapply = this.view(purchaseapply);
            Preorder  p = new Preorder();
            p.setStatus(status);
            p.setGuid(purchaseapply.getGuid());
            this.update(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Preorder viewByProcessId(String processId){
        Preorder purchasebiling = new Preorder();
        purchasebiling.setProcessInstanceId(processId);
        try {
            return  this.view(purchasebiling);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}