package com.ecfund.base.rabbitMQ.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.funds.Paybill;
import com.ecfund.base.model.purchase.Purchasebiling;
import com.ecfund.base.service.funds.PaybillService;
import com.ecfund.base.service.purchase.PurchasebilingService;
import com.ecfund.base.util.common.OrderCodeFactory;
import com.ecfund.base.util.dingtalk.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Calendar;

public class PayConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(PayConsumer.class);

    @Autowired
    private PurchasebilingService purchasebilingService;
    @Autowired
    private PaybillService paybillService;
    @Override
    public void onMessage(Message message) {
        logger.info("shijj receive message------->:{}", message);
        String resultStr = MessageUtil.getBodyContentAsString(message);
        JSONObject result = JSONObject.parseObject(resultStr);
        String processId = result.getString("content");
        Purchasebiling purchasebiling = purchasebilingService.viewByProcessId(processId);
        if(purchasebiling.getPaymoney().compareTo(BigDecimal.ZERO)!=1){
            return;
        }
        Paybill paybill = buildPayBill(purchasebiling);
        try {
            paybillService.insert(paybill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Paybill buildPayBill(Purchasebiling purchasebiling){
        Paybill paybill = new Paybill();
        paybill.setAccount(purchasebiling.getBankaccount());
        paybill.setBusinesscode(purchasebiling.getApplycode());
        paybill.setBusinessid(purchasebiling.getGuid());
        paybill.setCode(OrderCodeFactory.getPayCode(0L));
        paybill.setCompanyid(purchasebiling.getCompnayid());
        paybill.setSuitid(purchasebiling.getSuitid());
        paybill.setMoney(purchasebiling.getSummoney());
        paybill.setPaymoney(purchasebiling.getPaymoney());
        paybill.setBusinessid(purchasebiling.getUserid());
        paybill.setBusiusername(purchasebiling.getUsername());
        paybill.setPaytype("");
        paybill.setPaytypename("");
        paybill.setStatus(1);
        paybill.setSupplyid(purchasebiling.getSupplyid());
        paybill.setSupplyname(purchasebiling.getSupplyname());
        paybill.setCreatedate(Calendar.getInstance().getTime());
        paybill.setType("2");
        return paybill;
    }
}
