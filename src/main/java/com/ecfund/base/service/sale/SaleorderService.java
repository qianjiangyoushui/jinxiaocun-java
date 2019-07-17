package com.ecfund.base.service.sale;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.config.Constant;
import com.ecfund.base.dao.publics.AccountsuitDAO;
import com.ecfund.base.dao.sale.SaleorderDAO;
import com.ecfund.base.dao.sale.SaleorderdetailDAO;
import com.ecfund.base.model.process.DetailForm;
import com.ecfund.base.model.process.ProcessInstanceInputVO;
import com.ecfund.base.model.process.TextForm;
import com.ecfund.base.model.sale.Saleorder;
import com.ecfund.base.model.sale.Saleorderdetail;
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
public class SaleorderService extends BaseService<Saleorder> {

    //@Autowired
    //private SaleorderDAO saleorderDAO;

    @Autowired
    public void setBaseDAO(SaleorderDAO saleorderDAO) {
        super.setBaseDAO(saleorderDAO);
    }
    @Autowired
    private SaleorderdetailDAO saleorderdetailDAO;
    @Autowired
    private AccountsuitDAO accountsuitDAO;

    @Autowired
    private ProcessinstanceService processinstanceService;

    @Transactional(rollbackFor=RollBackException.class)
    public JSONObject save(OapiUserGetResponse user, Saleorder preorder, JSONArray detailList) throws RollBackException {
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
            List<Saleorderdetail> list = new ArrayList<Saleorderdetail>();
            for(int i=0;i<detailList.size();i++) {
                Saleorderdetail detail = detailList.getObject(i,Saleorderdetail.class);
                detail.setApplyid(guid);
                detail.setStatus(1);
                detail.setSummoney(detail.getAmount().multiply(detail.getPrice()));
                detail.setCreatedate(Calendar.getInstance().getTime());
                saleorderdetailDAO.insert(detail);
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
    private ServiceResult startProcess(Saleorder preorder, OapiUserGetResponse user)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ProcessInstanceInputVO processInstance= new ProcessInstanceInputVO();
        processInstance.setOriginatorUserId(user.getUserid());
        processInstance.setDeptId(user.getDepartment().get(0));
        TextForm textForm1 = new TextForm("客户名称",preorder.getCustomername());
        TextForm textForm2 = new TextForm("结算方式",preorder.getPaytypename());
        TextForm textForm3 = new TextForm("数量",preorder.getSummount().toString());
        TextForm textForm4 = new TextForm("总金额",preorder.getSummoney().toString());
        TextForm textForm5 = new TextForm("应付金额",preorder.getPaymoney().toString());
        List<TextForm> textList = new ArrayList<TextForm>();
        textList.add(textForm1);
        textList.add(textForm2);
        textList.add(textForm3);
        textList.add(textForm4);
        textList.add(textForm5);
        processInstance.setTextForms(textList);
        List<DetailForm> list = new ArrayList<DetailForm>();
        int index=0;
        DetailForm detailForm = new DetailForm();
        detailForm.setName("订购明细");
        for(Saleorderdetail detail :preorder.getDetailList()) {
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
        return processinstanceService.startProcessInstance(processInstance,Constant.PROCESS_CODE_SALE);
    }
    public void updateStatus(String processInstanceId, int status) {
        try {
            Saleorder purchaseapply = new Saleorder();
            purchaseapply.setProcessInstanceId(processInstanceId);
            purchaseapply = this.view(purchaseapply);
            Saleorder  p = new Saleorder();
            p.setStatus(status);
            p.setGuid(purchaseapply.getGuid());
            this.update(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Saleorder viewByProcessId(String processId){
        Saleorder purchasebiling = new Saleorder();
        purchasebiling.setProcessInstanceId(processId);
        try {
            return  this.view("findDetail",purchasebiling);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}