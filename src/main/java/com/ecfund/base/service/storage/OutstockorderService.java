package com.ecfund.base.service.storage;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.response.OapiUserGetResponse;
import com.ecfund.base.common.Exceptions.RollBackException;
import com.ecfund.base.config.Constant;
import com.ecfund.base.dao.publics.AccountsuitDAO;
import com.ecfund.base.dao.storage.OutorderdetailDAO;
import com.ecfund.base.dao.storage.OutstockorderDAO;
import com.ecfund.base.model.storage.Outorderdetail;
import com.ecfund.base.model.storage.Outstockorder;
import com.ecfund.base.service.BaseService;
import com.ecfund.base.util.common.CurrencyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class OutstockorderService extends BaseService<Outstockorder> {

    @Autowired
    private OutstockorderDAO outstockorderDAO;
    @Autowired
    private AccountsuitDAO accountsuitDAO;
    @Autowired
    private OutorderdetailDAO outorderdetailDAO;
    @Autowired
    public void setBaseDAO(OutstockorderDAO outstockorderDAO) {
        super.setBaseDAO(outstockorderDAO);
    }

    @Transactional(rollbackFor=RollBackException.class)
    public JSONObject save(OapiUserGetResponse user, Outstockorder instockorder, JSONArray detailList) throws RollBackException {
        JSONObject content = new JSONObject();
        JSONObject result = new JSONObject();
        try{
            //JSONObject js = JSONObject.parseObject(productArray);
            String suitId = accountsuitDAO.getCurrentSuit();
            instockorder.setSuitid(suitId);
            instockorder.setStatus(1);
            instockorder.setCompanyid(Constant.APP_KEY);
            instockorder.setUserid(user.getUnionid());
            instockorder.setUsername(user.getName());
            instockorder.setCreatedate(Calendar.getInstance().getTime());
            instockorder.setCapital(CurrencyUtil.toChinaUpper(instockorder.getMoney().toString()));
            String guid = this.insert(instockorder);
            List<Outorderdetail> list = new ArrayList<Outorderdetail>();
            for(int i=0;i<detailList.size();i++) {
                Outorderdetail detail = detailList.getObject(i,Outorderdetail.class);
                detail.setAppyid(guid);
                detail.setStatus(1);
                detail.setSummoney(detail.getAmount().multiply(detail.getPrice()));
                detail.setCreatedate(Calendar.getInstance().getTime());
                outorderdetailDAO.insert(detail);
                list.add(detail);
            }
            instockorder.setDetailList(list);
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