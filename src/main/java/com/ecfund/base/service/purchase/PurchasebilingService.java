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
import com.ecfund.base.model.purchase.Purchaseapply;
import com.ecfund.base.model.purchase.Purchaseapplydetail;
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
            //ServiceResult sr = startProcess(purchaseapply,user);
            /**
             * 流程启动失败回滚
             */
//            if(!sr.isSuccess()){
//                result.put("success",false);
//                result.put("erro", sr.getMessage());
//                throw new RollBackException();
//            }else{
//                String processInstanceId = sr.getResult().toString();
//                purchaseapply.setProcessInstanceId(processInstanceId);
//                purchaseapply.setGuid(guid);
//                this.update(purchaseapply);
//            }
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
     * @param purchaseapply
     * @param user
     * @return
     * @throws Exception
     */
    private ServiceResult startProcess(Purchaseapply purchaseapply, OapiUserGetResponse user)throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ProcessInstanceInputVO processInstance= new ProcessInstanceInputVO();
        processInstance.setOriginatorUserId(user.getUserid());
        processInstance.setDeptId(user.getDepartment().get(0));
        TextForm textForm1 = new TextForm("采购类型","农资采购");
        TextForm textForm2 = new TextForm("申请事由",purchaseapply.getReason());
        TextForm textForm3 = new TextForm("预计采购日期",sdf.format(purchaseapply.getApplydate()));
        TextForm textForm4 = new TextForm("总采购金额",purchaseapply.getSummoney().toString());
        TextForm textForm5 = new TextForm("明细数量",purchaseapply.getDetailList().size()+"种明细");
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
        detailForm.setName("采购申请明细");
        for(Purchaseapplydetail detail :purchaseapply.getDetailList()) {
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
        return processinstanceService.startProcessInstance(processInstance);
    }

}