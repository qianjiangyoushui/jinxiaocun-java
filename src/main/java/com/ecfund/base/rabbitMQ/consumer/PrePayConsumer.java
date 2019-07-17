package com.ecfund.base.rabbitMQ.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ecfund.base.model.funds.Paybill;
import com.ecfund.base.model.purchase.Purchaseapply;
import com.ecfund.base.model.purchase.Purchasebiling;
import com.ecfund.base.service.funds.PaybillService;
import com.ecfund.base.service.purchase.PurchaseapplyService;
import com.ecfund.base.util.common.OrderCodeFactory;
import com.ecfund.base.util.dingtalk.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Calendar;

public class PrePayConsumer implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(PrePayConsumer.class);

    @Autowired
    private PurchaseapplyService purchaseapplyService;
    @Autowired
    private PaybillService paybillService;
    @Override
    public void onMessage(Message message) {
        logger.info("shijj receive message------->:{}", message);
        String resultStr = MessageUtil.getBodyContentAsString(message);
        JSONObject result = JSONObject.parseObject(resultStr);
        String processId = result.getString("content");
        Purchaseapply purchaseapply = purchaseapplyService.viewByProcessId(processId);
        if(purchaseapply.getPremoney().compareTo(BigDecimal.ZERO)!=1){
            return;
        }
        Paybill paybill = buildPayBill(purchaseapply);
        try {
            paybillService.insert(paybill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Paybill buildPayBill(Purchaseapply purchasebiling){
        Paybill paybill = new Paybill();
        paybill.setBusinesscode(purchasebiling.getApplycode());
        paybill.setBusinessid(purchasebiling.getGuid());
        paybill.setCode(OrderCodeFactory.getPayCode(0L));
        paybill.setCompanyid(purchasebiling.getCompnayid());
        paybill.setSuitid(purchasebiling.getSuitid());
        paybill.setMoney(purchasebiling.getSummoney());
        paybill.setPaymoney(purchasebiling.getPremoney());
        paybill.setBusinessid(purchasebiling.getUserid());
        paybill.setBusiusername(purchasebiling.getUsername());
        paybill.setPaytype("");
        paybill.setPaytypename("");
        paybill.setStatus(1);
        paybill.setType("1");
        paybill.setCreatedate(Calendar.getInstance().getTime());
        return paybill;
    }
}
