package com.ecfund.base.service.sale;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.config.Constant;
import com.ecfund.base.dao.publics.AccountsuitDAO;
import com.ecfund.base.dao.sale.PreorderDAO;
import com.ecfund.base.dao.sale.PreorderdetailDAO;
import com.ecfund.base.model.sale.Preorder;
import com.ecfund.base.model.sale.Preorderdetail;
import com.ecfund.base.model.storage.Inoderdetail;
import com.ecfund.base.model.storage.Instockorder;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.CurrencyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Transactional(rollbackFor=RollBackException.class)
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
}