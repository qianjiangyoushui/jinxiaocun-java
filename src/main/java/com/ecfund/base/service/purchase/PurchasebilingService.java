package com.ecfund.base.service.purchase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.config.Constant;
import com.ecfund.base.dao.publics.AccountsuitDAO;
import com.ecfund.base.dao.purchase.BilingdetailDAO;
import com.ecfund.base.dao.purchase.PurchaseapplyDAO;
import com.ecfund.base.dao.purchase.PurchaseapplydetailDAO;
import com.ecfund.base.dao.purchase.PurchasebilingDAO;
import com.ecfund.base.model.process.DetailForm;
import com.ecfund.base.model.process.ProcessInstanceInputVO;
import com.ecfund.base.model.process.TextForm;
import com.ecfund.base.model.purchase.Bilingdetail;
import com.ecfund.base.model.purchase.Purchasebiling;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.service.process.ProcessinstanceService;
import com.ecfund.base.util.common.CurrencyUtil;
import com.ecfund.base.util.common.OrderCodeFactory;
import com.ecfund.base.util.dingtalk.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PurchasebilingService extends BaseService<Purchasebiling> {
    @Autowired
    private ProcessinstanceService processinstanceService;
    @Autowired
    private PurchaseapplyDAO purchaseapplyDAO;
    @Autowired
    private PurchaseapplydetailDAO purchaseapplydetailDAO;
    @Autowired
    private AccountsuitDAO accountsuitDAO;
    @Autowired
    private PurchasebilingDAO purchasebilingDAO;
    @Autowired
    private BilingdetailDAO bilingdetailDAO;

    @Autowired
    public void setBaseDAO(PurchasebilingDAO purchasebilingDAO) {
        super.setBaseDAO(purchasebilingDAO);
    }


    @Transactional(rollbackFor=RollBackException.class)
    public JSONObject save(OapiUserGetResponse user, Purchasebiling purchasebiling, JSONArray detailList) throws RollBackException {
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try{
            //JSONObject js = JSONObject.parseObject(productArray);
            String suitId = accountsuitDAO.getCurrentSuit();
            purchasebiling.setSuitid(suitId);
            purchasebiling.setStatus(1);
            purchasebiling.setCompnayid(Constant.APP_KEY);
            purchasebiling.setUserid(user.getUnionid());
            purchasebiling.setUsername(user.getName());
            purchasebiling.setCreatedate(Calendar.getInstance().getTime());
            purchasebiling.setCapital(CurrencyUtil.toChinaUpper(purchasebiling.getPaymoney().toString()));
            Random random = new Random();
            purchasebiling.setApplycode(OrderCodeFactory.getReturnCode(null));
            String guid = this.insert(purchasebiling);
            List<Bilingdetail> list = new ArrayList<Bilingdetail>();
            for(int i=0;i<detailList.size();i++) {
                Bilingdetail detail = detailList.getObject(i,Bilingdetail.class);
                detail.setAppyid(guid);
                detail.setSummoney(detail.getAmount().multiply(detail.getPrice()));
                detail.setCreatedate(Calendar.getInstance().getTime());
                bilingdetailDAO.insert(detail);
                list.add(detail);
            }
            purchasebiling.setDetailList(list);
            ServiceResult sr = startProcess(purchasebiling,user);
            /**
             * 流程启动失败回滚
             */
            if(!sr.isSuccess()){
                result.put("success",false);
                result.put("erro", sr.getMessage());
                throw new RollBackException();
            }else{
                String processInstanceId = sr.getResult().toString();
                purchasebiling.setProcessInstanceId(processInstanceId);
                purchasebiling.setGuid(guid);
                this.update(purchasebiling);
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
     * @param purchasebiling
     * @param user
     * @return
     * @throws Exception
     */
    private ServiceResult startProcess(Purchasebiling purchasebiling, OapiUserGetResponse user)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ProcessInstanceInputVO processInstance= new ProcessInstanceInputVO();
        processInstance.setOriginatorUserId(user.getUserid());
        processInstance.setDeptId(user.getDepartment().get(0));
        TextForm textForm1 = new TextForm("开单日期",sdf.format(purchasebiling.getApplydate()));
        TextForm textForm2 = new TextForm("单据类型",purchasebiling.getCategoryname());
        TextForm textForm3 = new TextForm("供应商",purchasebiling.getSupplyname());
        TextForm textForm4 = new TextForm("发票类型",purchasebiling.getBilltypename());
        TextForm textForm5 = new TextForm("收货总金额",purchasebiling.getSummoney().toString());
        TextForm textForm6 = new TextForm("本次付款金额",purchasebiling.getPaymoney().toString());
        TextForm textForm7 = new TextForm("开户行",purchasebiling.getBankname());
        TextForm textForm8 = new TextForm("结算账号",purchasebiling.getBankaccount());
        TextForm textForm9 = new TextForm("关联采购申请单",purchasebiling.getPurchaseapplycode());
        List<TextForm> textList = new ArrayList<TextForm>();
        textList.add(textForm1);
        textList.add(textForm2);
        textList.add(textForm3);
        textList.add(textForm4);
        textList.add(textForm5);
        textList.add(textForm6);
        textList.add(textForm7);
        textList.add(textForm8);
        textList.add(textForm9);
        processInstance.setTextForms(textList);
        List<DetailForm> list = new ArrayList<DetailForm>();
        int index=0;
        DetailForm detailForm = new DetailForm();
        detailForm.setName("采购开单明细");
        for(Bilingdetail detail :purchasebiling.getDetailList()) {
            index = index+1;
            TextForm dtextForm1 = new TextForm("名称", detail.getProductname());
            TextForm dtextForm2 = new TextForm("规格", detail.getNormal());
            TextForm dtextForm3 = new TextForm("单价", detail.getPrice().toString());
            TextForm dtextForm4 = new TextForm("数量", detail.getAmount().toString());
            TextForm dtextForm5 = new TextForm("合计金额",detail.getAmount().multiply(detail.getPrice()).toString());
            List<TextForm> dtextList = new ArrayList<TextForm>();
            dtextList.add(dtextForm1);
            dtextList.add(dtextForm2);
            dtextList.add(dtextForm3);
            dtextList.add(dtextForm4);
            dtextList.add(dtextForm5);
            DetailForm detailForm2 = new DetailForm();
            detailForm2.setTextForms(dtextList);
            list.add(detailForm2);
        }
        detailForm.setDetailForms(list);
        processInstance.setDetailForms(Arrays.asList(detailForm));
        return processinstanceService.startProcessInstance(processInstance,Constant.PROCESS_CODE_PURCHASE);
    }

    public void updateStatus(String processInstanceId, int status) {
        try {
            Purchasebiling purchaseapply = new Purchasebiling();
            purchaseapply.setProcessInstanceId(processInstanceId);
            purchaseapply = this.view(purchaseapply);
            Purchasebiling  p = new Purchasebiling();
            p.setStatus(status);
            p.setGuid(purchaseapply.getGuid());
            this.update(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Purchasebiling viewByProcessId(String processId){
        Purchasebiling purchasebiling = new Purchasebiling();
        purchasebiling.setProcessInstanceId(processId);
        try {
            return  this.view("findDetail",purchasebiling);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}